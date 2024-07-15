/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwtSpringSecurity.Repository;
import com.jwtSpringSecurity.Modal.UserModal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author nitis
 */
public interface UserRepository extends JpaRepository<UserModal, Long>{
   public UserModal findByEmail(String email);
   public  UserModal findByUsername(String username);
}
