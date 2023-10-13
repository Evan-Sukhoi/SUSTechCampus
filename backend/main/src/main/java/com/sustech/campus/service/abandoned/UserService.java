package com.sustech.campus.service.abandoned;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sustech.campus.model.User;
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
    public boolean addUser(String name, String phone, String email, String password) {
        try {
            User user = new User();
            user.setName(name);
            user.setPhone(phone);
            user.setEmail(email);
            user.setPassword(password);
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 如果保存过程中出现异常，则返回 false
        }
    }

    // 根据用户ID查询用户
    public User getUserById(Integer userId) {
        return userRepository.customFindById(userId);
    }

    // 删除用户
    public void deleteUserById(Integer userId) {
        userRepository.customDeleteById(userId);
    }

    // 更新用户姓名
    public void updateUserName(Integer userId, String name) {
        userRepository.customUpdateName(userId, name);
    }

    // 更新用户电话号码
    public void updateUserPhone(Integer userId, String phone) {
        userRepository.customUpdatePhone(userId, phone);
    }

    // 更新用户邮箱
    public void updateUserEmail(Integer userId, String email) {
        userRepository.customUpdateEmail(userId, email);
    }

    // 更新用户密码
    public void updateUserPassword(Integer userId, String password) {
        userRepository.customUpdatePassword(userId, password);
    }

    // 查询所有用户
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 自定义查询方法，根据姓名查询用户
    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }
}
