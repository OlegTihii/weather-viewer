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

    String city;
    BigDecimal latitude;
    BigDecimal longitude;
    String country;
    String state;
}
