package com.cleverpine.query.exception.handler;

import com.cleverpine.cpspringerrorutil.logger.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExceptionHandlerLogger implements Logger {
    @Override
    public void error(String s, Throwable throwable) {
        log.error(s, throwable);
    }
}
