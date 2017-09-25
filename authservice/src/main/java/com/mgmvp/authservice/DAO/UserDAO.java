package com.mgmvp.authservice.DAO;

import com.mgmvp.authservice.model.User;

import java.util.ArrayList;

public interface UserDAO {
    void save (User user);
    void delete(User user);
    User findByUsername (String username);
    ArrayList<User> findByEmail (String email);
}
