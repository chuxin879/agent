package com.daxiang.controller;

import com.daxiang.exception.AgentException;
import com.daxiang.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jiangyitao.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 没处理到的异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        log.error("未处理的异常", e);
        return Response.error(e.getMessage());
    }

    /**
     * AgentException
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(AgentException.class)
    public Response handleBusinessException(AgentException e) {
        return Response.fail(e.getMessage());
    }

    /**
     * 参数校验不通过，非@RequestBody
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Response handleBindException(BindException e) {
        return Response.fail(e.getFieldError().getDefaultMessage());
    }

    /**
     * 参数校验不通过，@RequestBody
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return Response.fail(e.getBindingResult().getFieldError().getDefaultMessage());
    }

}
