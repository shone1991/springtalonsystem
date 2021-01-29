package com.ivc.talonsystem.dao;

import java.util.List;

import com.ivc.talonsystem.entity.UserProfile;

public interface UserProfileDao {
	List<UserProfile> findAll();
    
    UserProfile findByType(String type);
     
    UserProfile findById(int id);
}
