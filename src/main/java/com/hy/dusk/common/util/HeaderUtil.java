package com.hy.dusk.common.util;

import cn.hutool.core.util.RandomUtil;
import com.google.common.base.Strings;
import com.hy.dusk.common.constants.Constants;
import com.hy.dusk.common.plugin.HttpHeaderContext;


/**
 * @author zhan.zhao
 *
 */
public interface HeaderUtil {
    static String getTransactionId() {
        if (Strings.isNullOrEmpty(HttpHeaderContext.getPassThruHeaderMap().get(Constants.X_TRANSACTION_ID))) {
            String transactionId = String.valueOf(System.currentTimeMillis()).concat("_").concat(RandomUtil.randomString(12));
            HttpHeaderContext.getPassThruHeaderMap().put(Constants.X_TRANSACTION_ID,transactionId);
            return transactionId;
        }
        //存入header
        return HttpHeaderContext.getPassThruHeaderMap().get(Constants.X_TRANSACTION_ID);
    }
}
