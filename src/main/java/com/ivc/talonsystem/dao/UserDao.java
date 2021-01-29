package com.ivc.talonsystem.dao;

import java.util.List;

import com.ivc.talonsystem.entity.User;
import com.ivc.talonsystem.entity.AbstractCompany;

public interface UserDao{
	User findById(int id);
    
    User findBySSO(String sso);
 
    void save(User user);
     
    void deleteBySSO(String sso);
     
    List<User> findAllUsers();
    
    List<User> findAllUsers(Integer offset, Integer maxResult);
    
    List<User> findAllUsers(String name, String surname, String nickname, AbstractCompany workat);
}
