package com.bank.history.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Класс для логирования хендлера
 */
@Slf4j
@Aspect
@Component
public class HistoryHandlerAspectLogging {

    @AfterReturning("execution(* com.bank.history.handler.*.*(..))&&args(ex)")
    public void handleException(Exception ex) throws Throwable {
        log.error(ex.getMessage(), ex);
    }
}