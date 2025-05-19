package org.weather.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
