package com.hy.dusk.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Strings;
import com.hy.dusk.common.util.HeaderUtil;
import com.hy.dusk.exception.ApplicationException;
import com.hy.dusk.exception.ApplicationThrowable;
import com.hy.dusk.exception.SystemErrorEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseContainer<T> implements Serializable {
    private static final long serialVersionUID = 527784070591826406L;
    private String resultCode;
    private String message;
    private String errorId;
    private String transactionId;
    private T data;
    private Long serverTime;
    public static <T> ResponseContainer<T> success() {
        return success(null,null);
    }
    public static <T> ResponseContainer<T> success(T data, String message) {
        ResponseContainer<T> result = new ResponseContainer<>();
//        result.setTransactionId(HeaderUtil.getTransactionId());
        result.setResultCode("success");
        result.setMessage(Strings.isNullOrEmpty(message)? "SUCCESS" : message);
        result.setData(data);
        result.setServerTime(System.currentTimeMillis());
        return result;
    }
    public static <T> ResponseContainer<T> error(String errorId, ApplicationThrowable ex) {
        return error(ex.getErrorCode(), errorId, ex.getMessage());
    }

    public static <T> ResponseContainer<T> error(String errorId, String errorMsg) {
        return error(SystemErrorEnum.UNDEFINED_ERROR.getErrorCode(), errorId, errorMsg);
    }

    public static <T> ResponseContainer<T> error(ApplicationException ex) {
        return error(ex.getErrorCode(), ex.getErrorId(), ex.getMessage());
    }

    public static <T> ResponseContainer<T> error(String code, String errorId, String errorMsg) {
        return error(code, errorId, errorMsg, null);
    }

    private static <T> ResponseContainer<T> error(String errorCode, String errorId, String errorMsg, T data) {
        ResponseContainer<T> result = new ResponseContainer<>();
        result.setTransactionId(HeaderUtil.getTransactionId());
        result.setResultCode(StringUtils.isBlank(errorCode) ? SystemErrorEnum.INTERNAL_ERROR.getErrorCode() : errorCode);
        result.setMessage(StringUtils.isBlank(errorMsg) ? ApplicationException.getI18nMessage(SystemErrorEnum.INTERNAL_ERROR) : errorMsg);
        result.setErrorId(errorId);
        result.setData(data);
        result.setServerTime(System.currentTimeMillis()/1000);
        return result;
    }
    public ResponseContainer() {
    }
}
