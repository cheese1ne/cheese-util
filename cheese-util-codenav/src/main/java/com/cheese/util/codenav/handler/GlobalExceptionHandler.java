package com.cheese.util.codenav.handler;

import com.baomidou.mybatisplus.extension.api.R;
import com.cheese.util.codenav.common.constant.ServletResponseConstant;
import com.cheese.util.codenav.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理，处理控制层中抛出的异常
 *
 * @author sobann
 */
@Slf4j
@Component
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常，可以返回异常信息
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public R<String> businessExceptionHandle(BusinessException e) {
        log.error(e.getMessage(), e);
        return R.failed(e.getMessage());
    }

    /**
     * 运行时异常，可以返回异常信息
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public R<String> runTimeExceptionHandle(RuntimeException e) {
        log.error(e.getMessage(), e);
        return R.failed(e.getMessage());
    }

    /**
     * 未定义异常，返回服务器错误信息
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public R<String> exceptionHandle(Exception e) {
        log.error(e.getMessage(), e);
        return R.failed(ServletResponseConstant.SERVER_ERROR);
    }


}
