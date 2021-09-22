package com.happy.location_service.controller;

import com.happy.location_service.dto.SuggestionsResponse;
import com.happy.location_service.service.SuggestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SuggestionsController {

    private final SuggestionsService suggestionsService;

    @GetMapping("/suggestions")
    public ResponseEntity<SuggestionsResponse> getSuggestions(@RequestParam String q,
                                                              @RequestParam(required = false) Double latitude,
                                                              @RequestParam(required = false) Double longitude,
                                                              RequestEntity requestEntity) {

        SuggestionsResponse suggestionsResponse = new SuggestionsResponse()
                .setSuggestions(suggestionsService.calculateSuggestions(q, latitude, longitude));
        return ResponseEntity.ok(suggestionsResponse);
    }
}
