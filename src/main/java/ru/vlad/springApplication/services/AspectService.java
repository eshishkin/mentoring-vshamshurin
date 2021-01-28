package ru.vlad.springApplication.services;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AspectService {

    public Map<String, Long> getAverage(Map<String, Pair<Long, Long>> cache) {
        Map<String, Long> formattedCache = new HashMap<>();
        for (Map.Entry<String, Pair<Long, Long>> pair : cache.entrySet()) {
            long average = pair.getValue().getFirst() / pair.getValue().getSecond();
            formattedCache.put(pair.getKey(), average);
        }
        return formattedCache;
    }
}
