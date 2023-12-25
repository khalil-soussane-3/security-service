package org.sid.securityservice.sec.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChangePasswordDto {
    private String username;
    private String oldPassword;
    private String newPassword;
}