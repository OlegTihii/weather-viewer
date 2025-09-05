package org.weather.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLocationsWeatherDto {

    String username;
    List<LocationDto> locationList;

}
