package com.thuan.identiy_service.controller;

import com.nimbusds.jose.JOSEException;
import com.thuan.identiy_service.dto.request.ApiResponse;
import com.thuan.identiy_service.dto.request.AuthenticationRequest;
import com.thuan.identiy_service.dto.request.IntrospectRequest;
import com.thuan.identiy_service.dto.response.AuthenticationResponse;
import com.thuan.identiy_service.dto.response.IntrospectResponse;
import com.thuan.identiy_service.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> auth(@RequestBody AuthenticationRequest authenticationRequest) {
        ApiResponse<AuthenticationResponse> apiResponse = ApiResponse.<AuthenticationResponse>builder().build();
        apiResponse.setResult(authenticationService.authenticate(authenticationRequest));
        return apiResponse;
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> auth(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        ApiResponse<IntrospectResponse> apiResponse = ApiResponse.<IntrospectResponse>builder().build();
        apiResponse.setResult(authenticationService.introspect(request));
        return apiResponse;
    }
}
