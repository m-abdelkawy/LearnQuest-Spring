package com.mountain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FirstAscentDAO {
    private static Map<Long, String> ascent = new HashMap<>();
    {
        ascent.put(1L, "9999");
        ascent.put(2L, "9999");
        ascent.put(3L, "9999");
    }

    public String get(long key){
        return ascent.get(key);
    }
}
