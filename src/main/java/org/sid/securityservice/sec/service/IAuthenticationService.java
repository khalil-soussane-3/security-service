package org.sid.securityservice.sec.service;

import org.sid.securityservice.sec.dto.AuthenticationResponse;

public interface IAuthenticationService {
    AuthenticationResponse accessToken(String username, String password);
    AuthenticationResponse refreshToken(String refreshToken);

}