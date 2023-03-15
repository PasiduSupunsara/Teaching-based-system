package com.example.Teaching_based_system.JWT;


import com.example.Teaching_based_system.Repository.TokenRepo;
import com.example.Teaching_based_system.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.NestedServletException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;



@Component
@RequiredArgsConstructor
public class JWTTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader("Authorization");
            final String name;
            final String jwtToken;

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            jwtToken = authHeader.split(" ")[1].trim();
            name = jwtTokenUtil.extractUsername(jwtToken).split(",")[0];

            if (name != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(name);
                if (jwtTokenUtil.isTokenValid(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);

        }
        catch(NestedServletException e){
            response.setHeader("error","jwt error");
            response.setStatus(FORBIDDEN.value());
            //response.sendError(FORBIDDEN.value());
            Map<String,String> error=new HashMap<>();
            error.put("errorMessage",e.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),error);
        }
        catch(ExpiredJwtException e){
            response.setHeader("error","jwt error");
            response.setStatus(401);
            Map<String,String> error=new HashMap<>();
            error.put("errorMessage",e.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),error);
        }
        catch(Exception e){
            e.printStackTrace();
            response.setHeader("error",e.getMessage());
            response.setStatus(FORBIDDEN.value());
            //response.sendError(FORBIDDEN.value());
            Map<String,String> error=new HashMap<>();
            error.put("errorMessage",e.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(),error);
        }
    }
}
