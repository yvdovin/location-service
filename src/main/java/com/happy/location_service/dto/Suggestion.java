package com.happy.location_service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class Suggestion {
    private String name;
    private Double latitude;
    private Double longitude;
    private Double score;
}
