/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springSecurity.controller;

import com.springSecurity.modal.UserInfo;
import com.springSecurity.service.EmployeeService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author galax
 */
@RestController
@RequestMapping(value = "/emp")
public class Employee {

    @Autowired
    private EmployeeService employeeService;
    
    
    @GetMapping(value = "/getEmp")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Map getEmployee() {
        Map mp = new HashMap();
        mp.put("id", "1");
        mp.put("name", "hello");
        mp.put("email", "hello@gmail.com");
        return mp;
    }
    
    
    @PostMapping(value = "/addEmployee")
    public Map addEmployee(@RequestBody UserInfo user) {
        Map mp = new HashMap();
        try{
            employeeService.addEmployee(user);
        }catch(Exception ex){
         mp.put("id", "1");
        mp.put("name", "hello");
        mp.put("email", "hello@gmail.com");
        }
       
        return mp;
    }
    
    
    
        @PostMapping(value = "/auth")
    public Map signin(@RequestBody UserInfo user) {
        Map mp = new HashMap();
        try{
            employeeService.signin(user);
        }catch(Exception ex){
       
        }
       
        return mp;
    }

}
