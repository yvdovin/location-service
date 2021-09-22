package com.happy.location_service.cache;

import com.happy.location_service.model.City;
import com.happy.location_service.utils.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CitiesCache {

    private final CSVReader citiesReader;
    private final Map<Character, List<City>> cache = new HashMap<>();

    @PostConstruct
    public void warmCache() throws Exception {
        List<City> cities = citiesReader.readAllCities();
        for (City city : cities) {
            Character firstCharacter = city.getName().charAt(0);
            List<City> citiesByCharacter = cache.get(firstCharacter);
            if (citiesByCharacter == null) {
                citiesByCharacter = new ArrayList<>();
                citiesByCharacter.add(city);
                cache.put(firstCharacter, citiesByCharacter);
            }
            citiesByCharacter.add(city);
        }
    }

    public List<City> getByFirstCharacterInName(Character character) {
        return cache.get(character);
    }
}
