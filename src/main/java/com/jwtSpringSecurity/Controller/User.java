/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwtSpringSecurity.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwtSpringSecurity.Modal.UserModal;
import com.jwtSpringSecurity.Service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nitis
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class User {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping(value = "/signup", consumes = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.TEXT_PLAIN_VALUE})
    public Map<String, Object> signup(@RequestBody String mp) {
        Map<String, Object> resultMap = new HashMap();
        try {
//            Map map = mapper.readValue(mp, LinkedCaseInsensitiveMap.class);
            UserModal map = mapper.readValue(mp, UserModal.class);
            resultMap = userService.signup(map);
        } catch (Exception e) {
            resultMap.put("status", "exception");
            resultMap.put("OK", "Unable to add user");
        }
        return resultMap;
    }

    @GetMapping(value = "/getid")
    public Map<String, Object> getid() {
        Map<String, Object> resultMap = new HashMap();
        try {
            resultMap.put("status", "success");
        } catch (Exception e) {
            resultMap.put("status", "exception");
            resultMap.put("OK", "Unable to add user");
        }
        return resultMap;
    }
}
