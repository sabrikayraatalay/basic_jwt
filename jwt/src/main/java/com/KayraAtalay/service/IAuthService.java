package com.KayraAtalay.service;

import com.KayraAtalay.dto.DtoUser;
import com.KayraAtalay.jwt.AuthRequest;
import com.KayraAtalay.jwt.AuthResponse;

public interface IAuthService {
	
	public DtoUser register(AuthRequest request);

    public AuthResponse authenticate(AuthRequest request);


}
