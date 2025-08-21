package org.weather.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    private String login;
    private String session;

}
