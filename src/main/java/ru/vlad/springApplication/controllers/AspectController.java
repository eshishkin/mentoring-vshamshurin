package ru.vlad.springApplication.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlad.springApplication.aspect.AspectCache;
import ru.vlad.springApplication.services.AspectService;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/aspect")
public class AspectController {
    private final AspectCache cache;
    private final AspectService service;

    @GetMapping("/get_cache")
    public Map<String, Long> getJSON() {
        return service.getAverage(cache.getCache());
    }
}
