package org.weather.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherDto {

    private String city;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String country;
    private Integer temperature;
    private Integer humidity;

    @Override
    public String toString() {
        return "LocationDto{" +
                "city='" + city + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", country='" + country + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }

}
