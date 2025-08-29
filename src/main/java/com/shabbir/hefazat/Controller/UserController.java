package com.shabbir.hefazat.Controller;

import com.shabbir.hefazat.Entity.AllPlatformEntity.Facebook;
import com.shabbir.hefazat.Entity.AllPlatformEntity.Google;
import com.shabbir.hefazat.Entity.User;
import com.shabbir.hefazat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("/update-user/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable String username){
        return userService.updateUser(updateUser,username);
    }

    @DeleteMapping("/delete-user/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        return userService.deleteUser(username);
    }

    @GetMapping("/get-google-list")
    public ResponseEntity<List<Google>> getGoogleList(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getGoogleList(username);
    }

    @GetMapping("/get-facebook-list")
    public ResponseEntity<List<Facebook>> getFacebookList(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getFacebookList(username);
    }

}
