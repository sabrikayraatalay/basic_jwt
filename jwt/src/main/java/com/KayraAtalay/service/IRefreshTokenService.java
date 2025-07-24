package com.KayraAtalay.service;

import com.KayraAtalay.jwt.AuthResponse;
import com.KayraAtalay.jwt.RefreshTokenRequest;

public interface IRefreshTokenService {
	
	public AuthResponse refreshToken(RefreshTokenRequest request);

}
