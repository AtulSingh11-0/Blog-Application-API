package com.blog.blogapplication.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  @Autowired
  UserDetailsService userDetailsService;

  @Autowired
  private JwtTokenHelper jwtTokenHelper;
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    String username = null;
    String token = null;

    if(authHeader.startsWith("Bearer ") && authHeader != null) {
      token = authHeader.substring(7);
      try {
        username = this.jwtTokenHelper.extractUsername(token);
      } catch (IllegalArgumentException ex) {
        System.out.println("Unable to get JWT token");
      } catch (ExpiredJwtException ex) {
        System.out.println("JWT token has expired");
      }catch (MalformedJwtException ex) {
        System.out.println("Invalid JWT token");
      }
    } else {
      System.out.println("JWT token doesnt begin with bearer");
    }

    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      if(jwtTokenHelper.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    } else {
      System.out.println("Invalid JWT token");
    }
    filterChain.doFilter(request, response);
  }
}
