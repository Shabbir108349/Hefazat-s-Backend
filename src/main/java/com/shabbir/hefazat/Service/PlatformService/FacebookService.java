package com.shabbir.hefazat.Service.PlatformService;

import com.shabbir.hefazat.Entity.AllPlatformEntity.Facebook;
import com.shabbir.hefazat.Entity.AllPlatformEntity.Google;
import com.shabbir.hefazat.Entity.User;
import com.shabbir.hefazat.Repository.AllPlatform.FacebookRepo;
import com.shabbir.hefazat.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacebookService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FacebookRepo facebookRepo;

    @Transactional
    public ResponseEntity<?> addFacebook(Facebook newFacebook, String username){
        User userInDb = userRepository.findByUsername(username);
        if (userInDb != null){
            Facebook saved = facebookRepo.save(newFacebook);
            userInDb.getFacebookList().add(saved);
            userRepository.save(userInDb);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<?> updateFacebook(Facebook updateFacebook, String username, ObjectId id){
        User userInDb = userRepository.findByUsername(username);
        if(userInDb != null){
            List<Facebook> checkId = userInDb.getFacebookList().stream().filter(x -> x.getId().equals(id)).toList();
            if(!checkId.isEmpty()){
                Facebook facebookFindById = facebookRepo.findById(id).orElse(null);
                if(facebookFindById != null){
                    facebookFindById.setAccountName(updateFacebook.getAccountName() != null && updateFacebook.getAccountName() != "" ? updateFacebook.getAccountName() : facebookFindById.getAccountName());
                    facebookFindById.setEmail(updateFacebook.getEmail() != null && updateFacebook.getEmail() != "" ? updateFacebook.getEmail() : facebookFindById.getEmail());
                    facebookFindById.setBirthday(updateFacebook.getBirthday() != null && updateFacebook.getBirthday() != ""? updateFacebook.getBirthday() : facebookFindById.getBirthday());
                    facebookFindById.setPhone(updateFacebook.getPhone() != null && updateFacebook.getPhone() != ""? updateFacebook.getPhone() : facebookFindById.getPhone());
                    facebookFindById.setFirstname(updateFacebook.getFirstname() != null && updateFacebook.getFirstname() != "" ? updateFacebook.getFirstname() : facebookFindById.getFirstname());
                    facebookFindById.setLastname(updateFacebook.getLastname() != null && updateFacebook.getLastname() != ""? updateFacebook.getLastname() : facebookFindById.getLastname());
                    facebookRepo.save(facebookFindById);
                    return new ResponseEntity<>(HttpStatus.ACCEPTED);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<?> deleteFacebook(String username,ObjectId id){
        User userInDb = userRepository.findByUsername(username);
        if(userInDb != null){
            List<Facebook> collect = userInDb.getFacebookList().stream().filter(x -> x.getId().equals(id)).toList();
            if(!collect.isEmpty()){
                boolean b = userInDb.getFacebookList().removeIf(x -> x.getId().equals(id));
                if(b){
                    facebookRepo.deleteById(id);
                    userRepository.save(userInDb);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
