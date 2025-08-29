package com.shabbir.hefazat.Service;

import com.shabbir.hefazat.Entity.User;
import com.shabbir.hefazat.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInDb = userRepository.findByUsername(username);
        if(userInDb != null){
            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(userInDb.getUsername())
                    .password(userInDb.getPassword())
                    .build();
        }
        throw new UsernameNotFoundException("username is not found");
    }
}
