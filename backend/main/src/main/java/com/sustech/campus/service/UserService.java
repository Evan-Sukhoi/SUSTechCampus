package com.sustech.campus.service;

import com.sustech.campus.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sustech.campus.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 添加用户
    public User addUser(User user) {
        return userRepository.save(user);
    }

    // 删除用户
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    // 更新用户信息
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // 查询用户信息
    public User getUser(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // 自定义查询方法
//    public List<User> findUsersByName(String name) {
//        //return userRepository.findByName(name);
//    }
}
