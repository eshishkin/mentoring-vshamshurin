package ru.vlad.springApplication.aspect;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AspectCache {
    private final Map<String, Pair<Long, Long>> cache = new ConcurrentHashMap<>();

    public void put(String methodName, Pair<Long, Long> time) {
        cache.put(methodName, time);
    }

    public Map<String, Pair<Long, Long>> getCache() {
        return cache;
    }
}
