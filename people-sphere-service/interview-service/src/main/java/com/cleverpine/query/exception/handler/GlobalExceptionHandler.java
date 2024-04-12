package com.cleverpine.query.exception.handler;

import com.cleverpine.cpspringerrorutil.handler.BaseGlobalExceptionHandler;
import com.cleverpine.cpspringerrorutil.mapper.ExceptionTypeMapper;
import com.cleverpine.cpspringerrorutil.model.ErrorResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends BaseGlobalExceptionHandler {

    @Autowired
    public GlobalExceptionHandler(
            ExceptionTypeMapper exceptionTypeMapper,
            ExceptionHandlerLogger baseLogger,
            @Value("${spring.profiles.active:dev}") String activeProfile) {
        super(exceptionTypeMapper, baseLogger, activeProfile.equals("dev"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseModel> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return createErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
