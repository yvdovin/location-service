package com.happy.location_service.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenCache {
    private Map<String, Long> cache = new HashMap<>();
}
