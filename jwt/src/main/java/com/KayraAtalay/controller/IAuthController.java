package com.KayraAtalay.controller;

import com.KayraAtalay.dto.DtoUser;
import com.KayraAtalay.jwt.AuthRequest;
import com.KayraAtalay.jwt.AuthResponse;
import com.KayraAtalay.jwt.RefreshTokenRequest;

public interface IAuthController {
	
	public DtoUser register(AuthRequest request);

    public AuthResponse authenticate(AuthRequest request);
    
    public AuthResponse refreshToken(RefreshTokenRequest request);
}
