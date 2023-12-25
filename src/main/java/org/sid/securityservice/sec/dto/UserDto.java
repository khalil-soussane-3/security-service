package org.sid.securityservice.sec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    String username;
    String password;
    String confirmpassword ;
    String role ;
}
