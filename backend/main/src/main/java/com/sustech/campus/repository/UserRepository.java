package com.sustech.campus.repository;

import com.sustech.campus.model.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // 根据主键查询
    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    User customFindById(@Param("userId") Integer userId);

    // 根据主键删除
    @Modifying
    @Query("DELETE FROM User u WHERE u.userId = :userId")
    void customDeleteById(@Param("userId") Integer userId);

    // 添加 User
    @Modifying
    @Query("INSERT INTO User (name, phone, email, password) VALUES (:name, :phone, :email, :password)")
    void customAddUser(@Param("name") String name, @Param("phone") String phone, @Param("email") String email, @Param("password") String password);

    // 修改姓名
    @Modifying
    @Query("UPDATE User u SET u.name = :name WHERE u.userId = :userId")
    void customUpdateName(@Param("userId") Integer userId, @Param("name") String name);

    // 修改电话号码
    @Modifying
    @Query("UPDATE User u SET u.phone = :phone WHERE u.userId = :userId")
    void customUpdatePhone(@Param("userId") Integer userId, @Param("phone") String phone);

    // 修改邮箱
    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.userId = :userId")
    void customUpdateEmail(@Param("userId") Integer userId, @Param("email") String email);

    // 修改密码
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.userId = :userId")
    void customUpdatePassword(@Param("userId") Integer userId, @Param("password") String password);
}

