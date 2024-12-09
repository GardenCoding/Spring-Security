/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springSecurity.serviceImpl;

import com.springSecurity.config.UserDetailFromDb;
import com.springSecurity.modal.UserInfo;
import com.springSecurity.repo.UserRepo;
import com.springSecurity.service.EmployeeService;
import com.springSecurity.utils.JwtUtil;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author galax
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
 @Autowired
    private JwtUtil jwtUtil;
    @Override
    public Map addEmployee(UserInfo user) {
        Map response = new HashMap();

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
        } catch (Exception ex) {

        }

        return response;
    }

    @Override
    public Map signin(UserInfo user) {
        Map response = new HashMap();
        try {
         String token=   jwtUtil.generateToken(new UserDetailFromDb().loadUserByUsername(user.getName()));
         response.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
