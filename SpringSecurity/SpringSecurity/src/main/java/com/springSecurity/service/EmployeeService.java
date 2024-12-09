/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springSecurity.service;

import com.springSecurity.modal.UserInfo;
import java.util.Map;

/**
 *
 * @author galax
 */
public interface EmployeeService {
    public Map addEmployee(UserInfo user);
    public Map signin(UserInfo user);
}
