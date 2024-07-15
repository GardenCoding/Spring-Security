/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwtSpringSecurity.Controller;

import com.jwtSpringSecurity.Helper.JwtUtil;
import com.jwtSpringSecurity.Modal.UserModal;
import com.jwtSpringSecurity.Service.CustomUserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nitis
 */
@RestController
public class JwtController {
    
    @Autowired
    private CustomUserService customUserService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @RequestMapping(value="/token", method = RequestMethod.POST)
    public ResponseEntity<?>generateToken(@RequestBody UserModal user){
        System.out.println("token class"+user.toString());
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        }catch(Exception ex){
        ex.printStackTrace();
        }
        //find area
       UserDetails userDetail =this.customUserService.loadUserByUsername(user.getUsername());
       String token= jwtUtil.generateToken(userDetail);
        System.out.println(token);
       Map mp=new HashMap();
       mp.put("token", token);
       return ResponseEntity.ok(mp);
        
    }
}
