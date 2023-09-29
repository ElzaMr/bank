package com.bank.history.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Класс для логгирования модуля History c использованием АОП
 * P.S. знаю что избыточно) тренируюсь на кошках :)
 */
@Aspect
@Slf4j
@Component
public class HistoryControllerAspectLogging {

    @Pointcut("within(com.bank.history.controller.*)")
    public void isRestControllerLayer() {
    }

    @AfterReturning(pointcut = "isRestControllerLayer()")
    public void addLogging(JoinPoint joinPoint) {
        if (joinPoint.getArgs() != null) {
            StringBuilder stringBuilder = new StringBuilder();
            final Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                stringBuilder.append(joinPoint.getSignature())
                        .append("The method worked - ")
                        .append(joinPoint.getSignature())
                        .append(", args = ")
                        .append(arg.toString());
            }
            log.debug(stringBuilder.toString() + joinPoint.getSignature());
        }

    }
}
