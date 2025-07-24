package com.KayraAtalay.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.KayraAtalay.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	public static final String SECRET_KEY_STRING = "rebiOFlPGPliBYsX9yQns3N5/NnzzChemiUo9vMEPxs=";
	
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claimsMap = new HashMap<>();
		claimsMap.put("role", "ADMIN");
		
	 return Jwts
			 .builder()
			 .setSubject(userDetails.getUsername())
			 .addClaims(claimsMap)
			 .setIssuedAt(new Date()) // token create time
			 .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *2)) // works for 2 hours
			 .signWith(getKey(), SignatureAlgorithm.HS256)	
			 .compact();
	}
	
	
	
	public  Object getClaimsByKey(String token, String key) {
		Claims claims = getClaims(token);
	return	claims.get(key);
	}
	
	
	public  Claims getClaims(String token){
		Claims claims = Jwts
				.parser()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token).getBody();
		
		return claims;
	}
	
	
	public <T> T exportToken(String token, Function<Claims, T> claimsFunction) {
		Claims claims = getClaims(token);
		
		return claimsFunction.apply(claims);
		
	}
	
	
	
	public String getUsernameByToken(String token) {
		 return exportToken(token, Claims::getSubject);
	}
	
	
	public boolean isTokenExpired(String token) {
		Date expireDate = exportToken(token, Claims::getExpiration);
		
		return new Date().before(expireDate);
		
		
	}
	public  Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY_STRING);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
