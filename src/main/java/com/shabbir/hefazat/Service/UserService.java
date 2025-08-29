package com.shabbir.hefazat.Service;

import com.shabbir.hefazat.Entity.AllPlatformEntity.Facebook;
import com.shabbir.hefazat.Entity.AllPlatformEntity.Google;
import com.shabbir.hefazat.Entity.User;
import com.shabbir.hefazat.Repository.AllPlatform.FacebookRepo;
import com.shabbir.hefazat.Repository.AllPlatform.GoogleRepo;
import com.shabbir.hefazat.Repository.UserRepository;
import com.shabbir.hefazat.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private GoogleRepo googleRepo;
    @Autowired
    private FacebookRepo facebookRepo;

    public ResponseEntity<?> signup(User newUser){
        try {
            userRepository.save(newUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> login(User user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            String token = jwtUtils.generateToken(user.getUsername());
            return new ResponseEntity<>(token,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateUser(User updateUser,String username){
        User userInDb = userRepository.findByUsername(username);
        if(userInDb != null){
            userInDb.setUsername(updateUser.getUsername() != null && updateUser.getUsername() != "" ? updateUser.getUsername() : userInDb.getUsername() );
            userInDb.setPassword(updateUser.getPassword() != null && updateUser.getPassword() != "" ? updateUser.getPassword() : userInDb.getPassword());
            userRepository.save(userInDb);
            return  new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> deleteUser(String username){
        User userInDb = userRepository.findByUsername(username);
        if(userInDb != null){
            List<Google> googleList = userInDb.getGoogleList();
            for(Google e : googleList){
                googleRepo.deleteById(e.getId());
            }
            List<Facebook> facebookList = userInDb.getFacebookList();
            for(Facebook e : facebookList){
                facebookRepo.deleteById(e.getId());
            }
            userRepository.deleteById(userInDb.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Google>> getGoogleList(String username){
        User userInDb = userRepository.findByUsername(username);
        if(userInDb != null){
            List<Google> googleList = userInDb.getGoogleList();
            return new ResponseEntity<>(googleList,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Facebook>> getFacebookList(String username){
        User userInDb = userRepository.findByUsername(username);
        if(userInDb != null){
            List<Facebook> facebookList = userInDb.getFacebookList();
            return new ResponseEntity<>(facebookList,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }



}
