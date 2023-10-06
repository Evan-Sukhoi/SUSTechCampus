package com.sustech.campus.repository;

import com.sustech.campus.model.Comment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Blob;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // 根据主键查询评论
    @Query("SELECT c FROM Comment c WHERE c.commentId = :commentId")
    Comment customFindById(@Param("commentId") Integer commentId);

    // 根据主键删除评论
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.commentId = :commentId")
    void customDeleteById(@Param("commentId") Integer commentId);

    // 添加评论
    @Modifying
    @Query("INSERT INTO Comment (user_ID, time, text, image_ID, building_ID, score, admin_ID, video) " +
            "VALUES (:userId, :time, :text, :imageId, :buildingId, :score, :adminId, :video)")
    void customAddComment(@Param("userId") Integer userId, @Param("time") Timestamp time, @Param("text") String text,
                          @Param("imageId") Integer imageId, @Param("buildingId") Integer buildingId,
                          @Param("score") BigDecimal score, @Param("adminId") Integer adminId,
                          @Param("video") Blob video);

    // 修改评论内容
    @Modifying
    @Query("UPDATE Comment c SET c.text = :text WHERE c.commentId = :commentId")
    void customUpdateText(@Param("commentId") Integer commentId, @Param("text") String text);

    // 修改评论分数
    @Modifying
    @Query("UPDATE Comment c SET c.score = :score WHERE c.commentId = :commentId")
    void customUpdateScore(@Param("commentId") Integer commentId, @Param("score") BigDecimal score);
}

