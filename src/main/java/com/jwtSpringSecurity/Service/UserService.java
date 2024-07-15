/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwtSpringSecurity.Service;

import com.jwtSpringSecurity.Modal.UserModal;
import com.jwtSpringSecurity.Repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author nitis
 */

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder  passwordEncoder;
    
    public Map<String,Object> signup(UserModal mp){
    Map<String,Object> response=new HashMap();
    try{
       UserModal data= userRepository.findByEmail(mp.getEmail());
        if(data!=null){
         response.put("msg", "User already exit");
        response.put("status", "error");
        }else{
            UserModal users=new UserModal();
            users.setEmail(mp.getEmail());
            users.setUsername(mp.getUsername());
            users.setPassword(this.passwordEncoder.encode(mp.getPassword()));
            users.setRole(mp.getRole());
        userRepository.save(users);
        response.put("msg", "Signup Success");
        response.put("status", "success");
        }
  
    }catch(Exception e){
        e.printStackTrace();
        response.put("msg", "Something went wrong");
        response.put("status", "error");
    }
    return response;
    }
    
}
