package org.weather.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WeatherDto {

    private String name;
    private List<Weather> weather;
    private Coord coord;
    private Sys sys;
    private Main main;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Coord {
        BigDecimal lon;
        BigDecimal lat;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Sys {
        String country;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Main {
        Integer temp;
        Integer humidity;
        Integer feels_like;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Weather {
        String main;
        String description;
        String icon;
    }




}
