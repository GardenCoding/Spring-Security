/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springSecurity.config;

import com.springSecurity.modal.UserInfo;
import com.springSecurity.repo.UserRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author galax
 */
public class UserDetailFromDb implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<UserInfo>  user=userRepo.findByName(username);
         if(user.isPresent()){
             return new UserDetailInfo(user.get().getName(),user.get().getPassword(),user.get().getRole());
         }
         else throw new UsernameNotFoundException(username+"not found");
    

    }

}
