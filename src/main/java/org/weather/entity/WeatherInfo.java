package org.weather.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
public class WeatherInfo {

    private String city;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String country;
    private Integer temperature;
    private Integer humidity;
}
