package com.microservices.demo.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.microservices.demo.Exception.BlogAPIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

//	@Value("${app.jwt-secret}")
//	private String jwtSecret;

//	@Value("${app.jwt-expiration-milliseconds}")
//	private int jwtExpirationInMs;

	@Autowired
	private Environment env;

	// generate token

	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(
				currentDate.getTime() + Long.parseLong(env.getProperty("app.jwt-expiration-milliseconds")));

		String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, env.getProperty("app.jwt-secret")).compact();
		return token;
	}

	// get username from the token
	public String getUsernameFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(env.getProperty("app.jwt-secret")).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	// validate JWT token
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(env.getProperty("app.jwt-secret")).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
		}
	}

}
