package com.shabbir.hefazat.Controller.PlatformController;

import com.shabbir.hefazat.Entity.AllPlatformEntity.Google;
import com.shabbir.hefazat.Service.PlatformService.GoogleService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/google")
public class GoogleController {
    @Autowired
    private GoogleService googleService;

    @PostMapping("/add-google")
    public ResponseEntity<?> addGoogle(@RequestBody Google newGoogle){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
       return googleService.addGoogle(newGoogle,username);
    }

    @PutMapping("/update-google/{id}")
    public ResponseEntity<?> updateGoogle(@RequestBody Google updateGoogle, @PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return googleService.updateGoogle(updateGoogle,username,id);
    }

    @DeleteMapping("/delete-google/{id}")
    public ResponseEntity<?> deleteGoogle(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return googleService.deleteGoogle(username,id);
    }


}
