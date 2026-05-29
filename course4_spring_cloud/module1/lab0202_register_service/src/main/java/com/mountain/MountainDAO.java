package com.mountain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class MountainDAO {
    private static Map<Long, Mountain> summits = new HashMap<>();
    {
        summits.put(1L, new Mountain(1L, "Everest", "Himalayas", "Nepal"));
        summits.put(2L, new Mountain(2L, "K2", "Himalayas", "Pakistan"));
        summits.put(3L, new Mountain(3L, "Denali", "Alaska Range", "United States"));
        summits.put(4L, new Mountain(4L, "Aconcagua", "Andes", "Argentina"));
        summits.put(5L, new Mountain(5L, "Kangchenjunga", "Himalayas", "India/Nepal"));
    }

    public Optional<Mountain> get(Long key){
        return Optional.ofNullable(summits.get(key));
    }
}
