package com.sustech.campus.repository;

import com.sustech.campus.model.Blacklist;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sustech.campus.model.*;

import java.util.List;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Integer> {
    // 根据主键查询
    @Query("SELECT b FROM Blacklist b WHERE b.listId = :listId")
    Blacklist customFindById(@Param("listId") Integer listId);

    @Query("SELECT b FROM Blacklist b WHERE b.userId = :userId")
    List<Blacklist> customFindByUserId(@Param("listId") Integer userId);

    // 根据主键删除
    @Modifying
    @Query("DELETE FROM Blacklist b WHERE b.listId = :listId")
    void customDeleteById(@Param("listId") Integer listId);

    // 添加 Blacklist
    @Modifying
    @Query("INSERT INTO Blacklist (userId, adminId) VALUES (:userId, :adminId)")
    void customAddBlacklist(@Param("userId") Integer userId, @Param("adminId") Integer adminId);

    // 修改 userId
    @Modifying
    @Query("UPDATE Blacklist b SET b.userId = :userId WHERE b.listId = :listId")
    void customUpdateUserId(@Param("listId") Integer listId, @Param("userId") Integer userId);

    // 修改 adminId
    @Modifying
    @Query("UPDATE Blacklist b SET b.adminId = :adminId WHERE b.listId = :listId")
    void customUpdateAdminId(@Param("listId") Integer listId, @Param("adminId") Integer adminId);
}

