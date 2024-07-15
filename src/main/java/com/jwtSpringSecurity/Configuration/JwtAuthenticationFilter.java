///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.jwtSpringSecurity.Configuration;
//
//import com.jwtSpringSecurity.Helper.JwtUtil;
//import com.jwtSpringSecurity.Modal.CustomUserDetails;
//import com.jwtSpringSecurity.Service.CustomUserService;
//import java.io.IOException;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
///**
// *
// * @author nitis
// */
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private CustomUserService customUserService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String requestTokenHeader = request.getHeader("Authorization");
//        String username = null;
//        String jwtToken = null;
//        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
//            jwtToken = requestTokenHeader.substring(7);
//            System.out.println("jwtToken **********" + jwtToken);
//            try {
//                username = this.jwtUtil.getUsernameFromToken(jwtToken);
//                System.out.println("username ********** " + username);
//                UserDetails userDetail = this.customUserService.loadUserByUsername(username);
//                System.out.println("username userDetail********** " + userDetail.getPassword());
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        System.out.println("outerUsername" + username);
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() != null) {
//            System.out.println(SecurityContextHolder.getContext().getAuthentication() != null);
//            try {
//                UserDetails userDetail = this.customUserService.loadUserByUsername(username);
//                System.out.println("userDetail **********" + userDetail.getPassword() + " " + userDetail.getUsername());
//                UsernamePasswordAuthenticationToken UsernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetail,null, userDetail.getAuthorities());
//                UsernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        filterChain.doFilter(request, response);
//        System.out.println("afert " + request.getContextPath());
//        System.out.println("after processing " + response.toString());
//
//    }
//
//}

package com.jwtSpringSecurity.Configuration;

import com.jwtSpringSecurity.Helper.JwtUtil;
import com.jwtSpringSecurity.Modal.CustomUserDetails;
import com.jwtSpringSecurity.Service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserService customUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);

            try {
                username = this.jwtUtil.getUsernameFromToken(jwtToken);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetail = this.customUserService.loadUserByUsername(username);

                    if (this.jwtUtil.validateToken(jwtToken, userDetail)) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetail, null, userDetail.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            } catch (Exception ex) {
                // Handle the exception, for example, return an error response or log it.
            }
        }

        filterChain.doFilter(request, response);
    }
}

