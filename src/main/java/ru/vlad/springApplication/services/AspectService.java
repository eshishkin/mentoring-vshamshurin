package ru.vlad.springApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import ru.vlad.springApplication.aspect.TimeAspect;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class AspectService {
    private final TimeAspect timeAspect;

    public Map<String, Long> getAverage() {
        Map<String, Pair<Long, Long>> data = new HashMap<>(timeAspect.getCache());
        Map<String, Long> formattedCache = new HashMap<>();
        for (Map.Entry<String, Pair<Long, Long>> pair : data.entrySet()) {
            long average = pair.getValue().getFirst() / pair.getValue().getSecond();
            formattedCache.put(pair.getKey(), average);
        }
        return formattedCache;
    }
}
