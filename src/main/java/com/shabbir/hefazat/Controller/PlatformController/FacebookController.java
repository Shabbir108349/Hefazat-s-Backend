package com.shabbir.hefazat.Controller.PlatformController;

import com.shabbir.hefazat.Entity.AllPlatformEntity.Facebook;
import com.shabbir.hefazat.Service.PlatformService.FacebookService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facebook")
public class FacebookController {
    @Autowired
    private FacebookService facebookService;

    @PostMapping("/add-facebook")
    public ResponseEntity<?> addFacebook(@RequestBody Facebook newFacebook){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return facebookService.addFacebook(newFacebook,username);
    }

    @PutMapping("/update-facebook/{id}")
    public ResponseEntity<?> updateFacebook(@RequestBody Facebook updateFacebook, @PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return facebookService.updateFacebook(updateFacebook,username,id);
    }

    @DeleteMapping("/delete-facebook/{id}")
    public ResponseEntity<?> deleteFacebook(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return facebookService.deleteFacebook(username,id);
    }
}
