package com.KayraAtalay.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.KayraAtalay.controller.IAuthController;
import com.KayraAtalay.dto.DtoUser;
import com.KayraAtalay.jwt.AuthRequest;
import com.KayraAtalay.jwt.AuthResponse;
import com.KayraAtalay.jwt.RefreshTokenRequest;
import com.KayraAtalay.service.IAuthService;
import com.KayraAtalay.service.IRefreshTokenService;

import jakarta.validation.Valid;

@RestController
public class AuthControllerImpl implements IAuthController{
	
	@Autowired
	private IAuthService authService;
	
	@Autowired
	private IRefreshTokenService refreshTokenService;

	@PostMapping(path = "/register")
	@Override
	public DtoUser register(@Valid @RequestBody AuthRequest request) {
				return authService.register(request);
	}

	@PostMapping(path = "/authenticate")
	@Override
	public AuthResponse authenticate(@Valid @RequestBody AuthRequest request) {
				return authService.authenticate(request);
	}

	@PostMapping(path = "/refreshtoken")
	@Override
	public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request) {
		return refreshTokenService.refreshToken(request);
	}

}
