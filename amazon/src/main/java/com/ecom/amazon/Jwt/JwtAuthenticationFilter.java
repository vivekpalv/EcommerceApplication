package com.ecom.amazon.Jwt;

import com.ecom.amazon.Security.CustomUserDetailService;
import com.ecom.amazon.Utility.ApplicationEnvironments;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtility;
    private final UserDetailsService userDetailsService;
    private final CustomUserDetailService customUserDetailService;

    public JwtAuthenticationFilter(JwtUtility jwtUtility, UserDetailsService userDetailsService, CustomUserDetailService customUserDetailService) {
        this.jwtUtility = jwtUtility;
        this.userDetailsService = userDetailsService;
        this.customUserDetailService = customUserDetailService;
    }

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
////        return super.shouldNotFilter(request);
//        String servletPath = request.getServletPath();
//        System.out.println("servletPath: "+servletPath);
////        return ApplicationEnvironments.getShouldNotFilterPathsByJwt().contains(servletPath);
//        return servletPath.equals("/authentication/loginCustomer") || servletPath.equals("/authentication/loginVendor");
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String tokenWithoutBearer = null;
        String role = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
             tokenWithoutBearer = authorizationHeader.substring(7);
            username = jwtUtility.extractUsername(tokenWithoutBearer);
            role = jwtUtility.extractRole(tokenWithoutBearer);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UserDetails userDetails = customUserDetailService.loadUserByUsernameAndRole(username, role);

            if (jwtUtility.validateToken(tokenWithoutBearer, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
