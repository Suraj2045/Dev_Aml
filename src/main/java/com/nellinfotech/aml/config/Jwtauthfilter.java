package com.nellinfotech.aml.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class Jwtauthfilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Fetching the authorization header from the request.
        String authenticationHeader = request.getHeader(Iconstants.HEADER);
        
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            
            if (authenticationHeader != null && authenticationHeader.startsWith("Bearer")) {
                
                final String bearerTkn = authenticationHeader.replaceAll(Iconstants.BEARER_TOKEN, "");
                
                try {
                    
                    if (CorsUtils.isPreFlightRequest(request)) {
                        response.setStatus(HttpServletResponse.SC_OK);
                    }
                    // Parsing the jwt token.
                    Jws<Claims> claims = Jwts.parser().requireIssuer(Iconstants.ISSUER)
                            .setSigningKey(Iconstants.SECRET_KEY).parseClaimsJws(bearerTkn);
                    
                    // Obtaining the claims from the parsed jwt token.
                    String userid = (String) claims.getBody().get("userid");
                    String user = (String) claims.getBody().get("usr");
                    String roles = (String) claims.getBody().get("role");
                    
                    // Creating the list of granted-authorities for the received roles.
                    List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
                    for (String role : roles.split(","))
                        authority.add(new SimpleGrantedAuthority(role));
                    
                    // Creating an authentication object using the claims.
                    Myauthtoken authenticationTkn = new Myauthtoken(userid, null, authority);
                    // Storing the authentication object in the security context.
                    context.setAuthentication(authenticationTkn);
                } catch (SignatureException e) {
                    throw new ServletException("Invalid token.");
                }
            }
            
            filterChain.doFilter(request, response);
            context.setAuthentication(null);
        } catch (AuthenticationException ex) {
            throw new ServletException("Authentication exception.");
        }
    }
}