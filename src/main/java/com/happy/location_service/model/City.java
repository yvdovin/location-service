package com.happy.location_service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class City {
    private String name;
    private String province;
    private Country country;
    private Double longitude;
    private Double latitude;
}
