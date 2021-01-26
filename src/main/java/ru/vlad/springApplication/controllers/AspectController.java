package ru.vlad.springApplication.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlad.springApplication.aspect.AspectCache;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/aspect")
public class AspectController {
    private final AspectCache cache;

    @GetMapping("/get_cache")
    public Map<String, Pair<Long, Long>> getJSON() {
        return cache.getCache();
    }
}
