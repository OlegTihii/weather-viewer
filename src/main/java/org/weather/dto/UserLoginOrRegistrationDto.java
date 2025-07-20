package org.weather.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginOrRegistrationDto {
    private String username;
    private String password;
}
