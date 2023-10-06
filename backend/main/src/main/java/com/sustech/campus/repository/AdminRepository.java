package com.sustech.campus.repository;

import com.sustech.campus.model.Admin;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    // 根据主键查询
    @Query("SELECT a FROM Admin a WHERE a.adminId = :adminId")
    Admin customFindById(@Param("adminId") Integer adminId);

    // 根据主键删除
    @Modifying
    @Query("DELETE FROM Admin a WHERE a.adminId = :adminId")
    void customDeleteById(@Param("adminId") Integer adminId);

    // 添加 Admin
    @Modifying
    @Query("INSERT INTO Admin (name, phone, email, password) VALUES (:name, :phone, :email, :password)")
    void customAddAdmin(@Param("name") String name, @Param("phone") String phone, @Param("email") String email, @Param("password") String password);

    // 修改姓名
    @Modifying
    @Query("UPDATE Admin a SET a.name = :name WHERE a.adminId = :adminId")
    void customUpdateName(@Param("adminId") Integer adminId, @Param("name") String name);

    // 修改电话号码
    @Modifying
    @Query("UPDATE Admin a SET a.phone = :phone WHERE a.adminId = :adminId")
    void customUpdatePhone(@Param("adminId") Integer adminId, @Param("phone") String phone);

    // 修改邮箱
    @Modifying
    @Query("UPDATE Admin a SET a.email = :email WHERE a.adminId = :adminId")
    void customUpdateEmail(@Param("adminId") Integer adminId, @Param("email") String email);

    // 修改密码
    @Modifying
    @Query("UPDATE Admin a SET a.password = :password WHERE a.adminId = :adminId")
    void customUpdatePassword(@Param("adminId") Integer adminId, @Param("password") String password);
}

