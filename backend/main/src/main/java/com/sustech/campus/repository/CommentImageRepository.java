package com.sustech.campus.repository;

import com.sustech.campus.model.CommentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sustech.campus.model.CommentImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sustech.campus.model.CommentImage;

import java.util.List;

@Repository
public interface CommentImageRepository extends JpaRepository<CommentImage, Integer> {

    // 根据 commentId 和 imageId 删除记录
    @Modifying
    @Query("DELETE FROM CommentImage ci WHERE ci.commentId = :commentId AND ci.imageId = :imageId")
    void customDeleteByCommentIdAndImageId(@Param("commentId") Integer commentId, @Param("imageId") Integer imageId);

    // 添加 CommentImage
    @Modifying
    @Query("INSERT INTO CommentImage (commentId, imageId) VALUES (:commentId, :imageId)")
    void customAddCommentImage(@Param("commentId") Integer commentId, @Param("imageId") Integer imageId);

    // 修改 commentId 属性
    @Modifying
    @Query("UPDATE CommentImage ci SET ci.commentId = :commentId WHERE ci.imageId = :imageId")
    void customUpdateCommentId(@Param("imageId") Integer imageId, @Param("commentId") Integer commentId);

    // 自定义查询，根据 commentId 查询所有相关记录
    @Query("SELECT ci FROM CommentImage ci WHERE ci.commentId = :commentId")
    List<CommentImage> customFindAllByCommentId(@Param("commentId") Integer commentId);
}


