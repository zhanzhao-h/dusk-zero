package com.hy.dusk.common.log;

import com.hy.dusk.common.constants.Constants;
import com.hy.dusk.common.util.HeaderUtil;
import com.hy.dusk.common.util.JsonUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * @author: ZhaoZhan
 * @date: 2022-12-23 15:53
 */
@Aspect
@Component
public class GenLogAspect {
    private static final Logger log = LoggerFactory.getLogger(GenLogAspect.class);
    private static final String SEPARATOR = ", ";
    private static final String UNKNOWN_PARAM_NAME = "arg";

    public GenLogAspect() {
    }

    @Pointcut("@annotation(com.hy.dusk.common.log.GenLog) || @within(com.hy.dusk.common.log.GenLog)")
    public void pointcut() {
    }

    @Around("pointcut() && @annotation(genLog)")
    public Object logDelegateLayer(ProceedingJoinPoint joinPoint, GenLog genLog) throws Throwable {
        GenLogEnum value = genLog.value();
        StopWatch stopWatch = new StopWatch();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String webLogFlag = "";
        if (GenLogEnum.WEB.name().equals(value.name())){
            getRequestParam(joinPoint);
            webLogFlag = "Web_Request_";
        }
        stopWatch.start();
        this.doLog(joinPoint,webLogFlag);
        Object result = joinPoint.proceed();
        try {
            stopWatch.stop();
            //todo:是否打印返参奢者可选
            log.info(" [{}], --{}end-- <<{}.{}>> timer in milliseconds: {} ms, result: {}", HeaderUtil.getTransactionId(),webLogFlag,className, methodName, stopWatch.getTotalTimeMillis(), result != null && !this.isPrimitive(result.getClass()) && !this.isIgnoredClass(result.getClass()) ? JsonUtil.obj2JsonStr(result) : result);
        } catch (Exception var7) {
            log.error("[{}],Error occurred when processing log", HeaderUtil.getTransactionId(),var7);
        }
        return result;
    }

    private boolean isPrimitive(Class<?> clazz) {
        return clazz.isPrimitive() || clazz == String.class || Number.class.isAssignableFrom(clazz);
    }

    private boolean isIgnoredClass(Class<?> clazz) {
        return clazz.getPackage().getName().contains(".http");
    }

    public void doLog(JoinPoint joinPoint, String webLogFlag) {
        if (joinPoint != null) {
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            Method method = signature.getMethod();
            String className = signature.getDeclaringTypeName();
            String methodName = method.getName();
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNames = u.getParameterNames(method);
            Object[] paramsArgsValue = joinPoint.getArgs();
            try {
                StringBuilder sb = new StringBuilder("[{}],");
                if (ArrayUtils.isEmpty(paramsArgsValue)) {
                    sb.append(" --{}start-- <<{}.{}>>");
                } else {
                    sb.append(" --{}start--  <<{}.{}>> with parameters: ");
                    this.joiningParams(paramNames, paramsArgsValue, sb);
                }
                String logContent = StringUtils.removeEndIgnoreCase(sb.toString(), SEPARATOR);
                log.info(logContent, HeaderUtil.getTransactionId(), webLogFlag, className, methodName);
            } catch (Exception var11) {
                log.error("[{}],Error occurred when processing log.",HeaderUtil.getTransactionId(), var11);
            }

        }
    }

    /**
     *  方法参数值处理
     * @author zhan.zhao
     * @param paramNames
     * @param paramsArgsValue
     * @param sb
     * @return void
     * @date 2021/4/15  18:01
     */
    private void joiningParams(String[] paramNames, Object[] paramsArgsValue, StringBuilder sb) {
        for(int i = 0; i < paramsArgsValue.length; ++i) {
            String name = UNKNOWN_PARAM_NAME + i;
            if (paramNames != null) {
                name = paramNames[i];
            }
            Object value = paramsArgsValue[i];
            sb.append(name).append("=");
            if (value != null) {
                if (this.isPrimitive(value.getClass())) {
                    sb.append(value).append(SEPARATOR);
                } else {
                    sb.append(JsonUtil.obj2JsonStr(value)).append(SEPARATOR);
                }
            }
        }

    }

    /**
     *  web层 打印请求信息:  url, ip,token
     * @author zhan.zhao
     * @param joinPoint
     * @return void
     * @date 2021/4/15  16:20
     */
    private void getRequestParam(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 打印请求内容
        log.info("[{}]: URL : {} - {}?{}",HeaderUtil.getTransactionId(),request.getMethod(),request.getRequestURL().toString(),request.getQueryString());
        log.info("[{}]: IP : {}" ,HeaderUtil.getTransactionId(),request.getRemoteAddr());
        log.info("[{}]: HEADER : {}" ,HeaderUtil.getTransactionId(),request.getHeader(Constants.AUTHORIZATION));
    }


}
