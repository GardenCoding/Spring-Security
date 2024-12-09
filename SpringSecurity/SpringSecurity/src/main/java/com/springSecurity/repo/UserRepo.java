/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springSecurity.repo;

import com.springSecurity.modal.UserInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author galax
 */
@Repository
public interface UserRepo extends JpaRepository<UserInfo, Long>{
    public Optional<UserInfo> findByName(String name);
    
}
