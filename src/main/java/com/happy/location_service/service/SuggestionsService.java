package com.happy.location_service.service;

import com.happy.location_service.cache.CitiesCache;
import com.happy.location_service.dto.Suggestion;
import com.happy.location_service.model.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SuggestionsService {

    private final CitiesCache cache;

    public List<Suggestion> calculateSuggestions(String cityName, Double latitude, Double longitude) {
        DecimalFormat df = new DecimalFormat("#.#");
        final List<City> cities = cache.getByFirstCharacterInName(cityName.charAt(0));
        final List<City> appropriateCities = new ArrayList<>();
        for (City city : cities) {
            if (city.getName().startsWith(cityName)) {
                appropriateCities.add(city);
            }
        }
        List<Suggestion> suggestions = new ArrayList<>();

        for (City city : appropriateCities) {
            final Suggestion suggestion = new Suggestion();
            double score = 0;
            if (latitude != null && longitude != null) {
                double distance = Math.acos(Math.sin(latitude * Math.PI / 180) * Math.sin(city.getLatitude() * Math.PI / 180) +
                        Math.cos(latitude * Math.PI / 180) * Math.cos(city.getLatitude() * Math.PI / 180) *
                                Math.cos(longitude * Math.PI / 180 - city.getLongitude() * Math.PI / 180)) * 6400;
                if(distance > 1200) {
                    score = 0;
                }else {
                    score = Double.parseDouble(df.format(1 - distance / 1200).replace(',', '.'));
                }
            }
            suggestion.setName(String.format("%s - %s - %s", city.getName(), city.getProvince(), city.getCountry().getCityName()))
                    .setLatitude(city.getLatitude())
                    .setLongitude(city.getLongitude())
                    .setScore(score);
            suggestions.add(suggestion);
        }
        suggestions.sort(Comparator.comparing(Suggestion::getScore));
        return suggestions;
    }
}

