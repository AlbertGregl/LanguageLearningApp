package algebra.hr.bll.service;

import algebra.hr.dal.entity.User;

import java.util.List;

//i let this interface not extend the generic one because i dont need all crud operations since the username is the id!
//also if we want to add email support later we will maybe need email methods!
public interface UserService {
    List<User> findAll();
    User findByUsername(String username);
    User save(User user);
    void deleteById(int id);

}
