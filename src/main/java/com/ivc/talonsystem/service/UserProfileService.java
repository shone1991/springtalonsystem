package com.ivc.talonsystem.service;

import java.util.List;

import com.ivc.talonsystem.entity.UserProfile;

public interface UserProfileService {
	UserProfile findById(int id);
	 
    UserProfile findByType(String type);
     
    List<UserProfile> findAll();
}
