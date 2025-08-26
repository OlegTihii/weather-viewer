//package org.weather.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import lombok.*;
//
//import java.math.BigDecimal;
//
//@Getter
//@Setter
//@Builder
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class WeatherInfo {
//
//    private String name;
//    private BigDecimal lat;
//    private BigDecimal lon;
//    private String country;
//    private Integer temperature;
//    private Integer humidity;
//
//
//}
package org.weather.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo {

    private String name;
    private Coord coord;
    private Main main;
    private Sys sys;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Coord {
        private BigDecimal lat;
        private BigDecimal lon;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Main {
        private Integer temp;
        private Integer humidity;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Sys {
        private String country;
    }
}
