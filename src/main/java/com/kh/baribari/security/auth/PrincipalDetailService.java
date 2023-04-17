package com.kh.baribari.security.auth;

import com.kh.baribari.user.domain.Role;
import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 시큐리티 session(Authentication(userDetails))
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId);
        List<Role> role = userRepository.findByUserNo(user.getUserNo());
        if(user != null){
            return new PrincipalDetails(user,role);
        }else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
