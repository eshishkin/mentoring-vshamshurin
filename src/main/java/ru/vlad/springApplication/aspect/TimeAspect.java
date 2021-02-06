package ru.vlad.springApplication.aspect;

import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@Component
@Aspect
public class TimeAspect {
    private final Map<String, Pair<Long, Long>> cache = new ConcurrentHashMap<>();

    @Pointcut("execution(public * ru.vlad.springApplication.controllers.*.*(..))")
    public void processingControllerMethods() {}

    @Pointcut("execution(public * ru.vlad.springApplication.services.impl.*.*(..))")
    public void processingServiceMethods() {}

    @Around("processingControllerMethods()")
    public Object executionControllersTime(ProceedingJoinPoint joinPoint) throws Throwable {
        return getObject(joinPoint);
    }

    @Around("processingServiceMethods()")
    public Object executionServicesTime(ProceedingJoinPoint joinPoint) throws Throwable {
        return getObject(joinPoint);
    }

    private Object getObject(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        String methodName = joinPoint.getSignature().toString();
        cache.compute(methodName, (key, value) -> {
            if (value == null) {
                return Pair.of(executionTime, 1L);
            } else {
                return Pair.of(value.getFirst() + executionTime, value.getSecond() + 1);
            }
        });
        return proceed;
    }

    public Map<String, Pair<Long, Long>> getCache() {
        return cache;
    }
}
