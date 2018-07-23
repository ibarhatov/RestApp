package ru.ibarhatov.restapp.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

@Slf4j
public abstract class Logger {

    protected Object logging(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String className = thisJoinPoint.getTarget().getClass().getSimpleName();
        String methodName = thisJoinPoint.getSignature().getName();

        log.info("Call method " + className + "." + methodName + "()");
        Object result = thisJoinPoint.proceed();
        log.info("Method " + className + "." + methodName + "() returns " + result);

        return result;
    }
}
