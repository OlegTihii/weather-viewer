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

    private String city;
    private BigDecimal  latitude;
    private BigDecimal longitude;
    private String country;
    private Integer temperature;
    private Integer humidity;

}
