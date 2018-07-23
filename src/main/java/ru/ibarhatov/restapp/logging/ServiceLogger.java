package ru.ibarhatov.restapp.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceLogger extends Logger {

    @Around("within(ru.ibarhatov.restapp.services.*)")
    public Object controllerLogging(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        return logging(thisJoinPoint);
    }
}
