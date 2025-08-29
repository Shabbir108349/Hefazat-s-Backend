package com.shabbir.hefazat.Controller;

import com.shabbir.hefazat.Entity.User;
import com.shabbir.hefazat.Repository.UserRepository;
import com.shabbir.hefazat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @GetMapping("health-check")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User newUser){
       return userService.signup(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        return userService.login(user);
    }
}
