package org.sid.securityservice.sec.controller;

import org.sid.securityservice.sec.dto.AuthenticationRequest;
import org.sid.securityservice.sec.dto.AuthenticationResponse;
import org.sid.securityservice.sec.dto.RefreshTokenRequest;
import org.sid.securityservice.sec.service.IAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/v1/")
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/token")
    public ResponseEntity<AuthenticationResponse> jwtToken(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.accessToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AuthenticationResponse> jwtToken( @RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest.getRefreshToken()));
    }
}