package com.lxq.hotchpotch.common.web.exception;

import com.lxq.hotchpotch.common.core.constant.ErrorCode;
import com.lxq.hotchpotch.common.web.exception.example.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author luxinqiang
 */
@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Map<String, Object> permissionExceptionHandle(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ErrorCode.A0300.getCode());
        map.put("msg", ErrorCode.A0300.getDesc());
        return map;
    }

}
