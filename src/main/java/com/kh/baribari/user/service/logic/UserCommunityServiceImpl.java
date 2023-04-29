package com.kh.baribari.user.service.logic;

import com.kh.baribari.user.repository.UserCommunityRepository;
import com.kh.baribari.user.service.UserCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommunityServiceImpl implements UserCommunityService {
   @Autowired
   private UserCommunityRepository uCRepository;



}
