package ru.vlad.springApplication.aspect;

import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Aspect
public class TimeAspect {
    private final AspectCache cache;

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
        if (!cache.getCache().containsKey(methodName)) {
            Pair<Long, Long> pair = Pair.of(executionTime, (long) 1);
            cache.put(methodName, pair);
        } else {
            Pair<Long, Long> pair2 = Pair.of(executionTime, cache.getCache().get(methodName).getSecond() + 1);
            cache.put(methodName, pair2);
        }
        return proceed;
    }
}
