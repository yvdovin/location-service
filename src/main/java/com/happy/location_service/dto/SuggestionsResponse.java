package com.happy.location_service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class SuggestionsResponse {
    private List<Suggestion> suggestions;
}
