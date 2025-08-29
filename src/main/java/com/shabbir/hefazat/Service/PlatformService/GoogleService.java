package com.shabbir.hefazat.Service.PlatformService;

import com.shabbir.hefazat.Entity.AllPlatformEntity.Google;
import com.shabbir.hefazat.Entity.User;
import com.shabbir.hefazat.Repository.AllPlatform.GoogleRepo;
import com.shabbir.hefazat.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class GoogleService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GoogleRepo googleRepo;

    @Transactional
    public ResponseEntity<?> addGoogle(Google newGoogle, String username){
            User userInDb = userRepository.findByUsername(username);
            if (userInDb != null){
                Google saved = googleRepo.save(newGoogle);
                userInDb.getGoogleList().add(saved);
                userRepository.save(userInDb);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<?> updateGoogle(Google updateGoogle, String username, ObjectId id){
        User userInDb = userRepository.findByUsername(username);
        if(userInDb != null){
            List<Google> checkId = userInDb.getGoogleList().stream().filter(x -> x.getId().equals(id)).toList();
            if(!checkId.isEmpty()){
                Google googleFindById = googleRepo.findById(id).orElse(null);
                if(googleFindById != null){
                    googleFindById.setAccountName(updateGoogle.getAccountName() != null && updateGoogle.getAccountName() != "" ? updateGoogle.getAccountName() : googleFindById.getAccountName());
                    googleFindById.setEmail(updateGoogle.getEmail() != null && updateGoogle.getEmail() != "" ? updateGoogle.getEmail() : googleFindById.getEmail());
                    googleFindById.setBirthday(updateGoogle.getBirthday() != null && updateGoogle.getBirthday() != ""? updateGoogle.getBirthday() : googleFindById.getBirthday());
                    googleFindById.setPhone(updateGoogle.getPhone() != null && updateGoogle.getPhone() != ""? updateGoogle.getPhone() : googleFindById.getPhone());
                    googleFindById.setFirstname(updateGoogle.getFirstname() != null && updateGoogle.getFirstname() != "" ? updateGoogle.getFirstname() : googleFindById.getFirstname());
                    googleFindById.setLastname(updateGoogle.getLastname() != null && updateGoogle.getLastname() != ""? updateGoogle.getLastname() : googleFindById.getLastname());
                    googleRepo.save(googleFindById);
                    return new ResponseEntity<>(HttpStatus.ACCEPTED);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<?> deleteGoogle(String username,ObjectId id){
        User userInDb = userRepository.findByUsername(username);
        if(userInDb != null){
            List<Google> collect = userInDb.getGoogleList().stream().filter(x -> x.getId().equals(id)).toList();
            if(!collect.isEmpty()){
                boolean b = userInDb.getGoogleList().removeIf(x -> x.getId().equals(id));
                if(b){
                    googleRepo.deleteById(id);
                    userRepository.save(userInDb);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
