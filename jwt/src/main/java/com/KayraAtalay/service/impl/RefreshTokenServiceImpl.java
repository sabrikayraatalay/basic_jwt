package com.KayraAtalay.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KayraAtalay.entities.RefreshToken;
import com.KayraAtalay.entities.User;
import com.KayraAtalay.jwt.AuthResponse;
import com.KayraAtalay.jwt.JwtService;
import com.KayraAtalay.jwt.RefreshTokenRequest;
import com.KayraAtalay.repository.RefreshTokenRepository;
import com.KayraAtalay.service.IRefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private JwtService jwtService;

	public boolean isRefreshTokenExpired(Date expiredDate) {
		return new Date().before(expiredDate);
	}

	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
		refreshToken.setUser(user);

		return refreshToken;
	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {
		Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());

		if (optional.isEmpty()) {
			System.out.println("INVALID REFRESH TOKEN : " + request.getRefreshToken());
		}

		RefreshToken refreshToken = optional.get();

		if (!isRefreshTokenExpired(refreshToken.getExpireDate())) {
			System.out.println("REFREST TOKEN IS EXPIRED : " + request.getRefreshToken());
		}

		String accessToken = jwtService.generateToken(refreshToken.getUser());

		RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(refreshToken.getUser()));

		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}

}
