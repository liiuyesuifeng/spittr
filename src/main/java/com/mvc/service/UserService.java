package com.mvc.service;

import com.mvc.eitity.User;

import java.util.List;

public interface UserService {
    public User queryUserById(String userId);
    public List<User> queryUserList(User user);
    public void saveUser(User user);
}
