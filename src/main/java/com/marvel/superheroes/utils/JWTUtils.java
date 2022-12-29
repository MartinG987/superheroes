package com.marvel.superheroes.utils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Util para generar el token JWT
 * @author Tincho
 *
 */
public class JWTUtils {

	
	public static String getJWTToken(String username, String secret) {
		return new StringBuilder()
								.append("Bearer ")		
								.append(getTokenJwt(username, secret)).toString();
	}

	
	private static List<GrantedAuthority> getGranted() {
		return  AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
	}
	
	
	private static String getTokenJwt(String username, String secret) {
		return Jwts
				.builder()
				.setId("marvelJWT")
				.setSubject(username)
				.claim("authorities",
						getGranted().stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,secret.getBytes()).compact();
	}
	
}
