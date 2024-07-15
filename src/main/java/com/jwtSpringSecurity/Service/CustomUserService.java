/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwtSpringSecurity.Service;

import com.jwtSpringSecurity.Modal.CustomUserDetails;
import com.jwtSpringSecurity.Modal.UserModal;
import com.jwtSpringSecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author nitis
 */
@Service
public class CustomUserService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                  UserModal user= userRepository.findByUsername(username);
                          if(user==null){
            throw  new UsernameNotFoundException("No User");
        }    
        return new CustomUserDetails(user);
    }
    
}
