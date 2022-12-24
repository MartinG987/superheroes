package com.marvel.superheroes.security;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Filtro basico para seguridad JWT
 * @author Tincho
 *
 */
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private  String HEADER = "Authorization";
	private  String PREFIX = "Bearer ";
	private  String SECRET = "mySecretKey";
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {
			if (existeJWTToken(request, response)) {
				Claims claims = validateToken(request);
				if (claims.get("authorities") != null) {
					setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
				}
			} else {
						SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
			
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			return;
		}
	}	

	
	private Claims validateToken(HttpServletRequest request) {
		return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(request.getHeader(HEADER).replace(PREFIX, "")).getBody();
	}
	
	private void setUpSpringAuthentication(Claims claims) {
		SecurityContextHolder.getContext().setAuthentication(getAuth(claims));
	}

	private UsernamePasswordAuthenticationToken getAuth(Claims claims) {
		return  new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				((List<String>)claims.get("authorities")).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}

	private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
		return  ( request.getHeader(HEADER) == null || ! request.getHeader(HEADER).startsWith(PREFIX)) ? false : true;
	}

}