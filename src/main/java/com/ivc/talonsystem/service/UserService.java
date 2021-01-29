package com.ivc.talonsystem.service;

import java.util.List;

import com.ivc.talonsystem.entity.User;
import com.ivc.talonsystem.entity.AbstractCompany;

public interface UserService {
	User findById(int id);
    
    User findBySSO(String sso);
    
    void saveUser(User user);
     
    void updateUser(User user);
     
    void deleteUserBySSO(String sso);
 
    List<User> findAllUsers(); 
    
    List<User> findAllUsers(Integer offset, Integer maxResult);
    
    List<User> findAllUsers(String name, String surname, String nickname, AbstractCompany workat);
     
    boolean isUserSSOUnique(Integer id, String sso);
}
