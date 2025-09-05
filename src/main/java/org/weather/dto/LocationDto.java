package org.weather.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LocationDto {

    String name;
    BigDecimal lat;
    BigDecimal lon;
    String country;
    String state;
    WeatherDto weatherDto;
}
