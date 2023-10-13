package com.sustech.campus.service.abandoned;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sustech.campus.model.Comment;
import com.sustech.campus.repository.CommentRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // 添加评论
    public boolean addComment(Integer userId, String text, Integer buildingId, Double score, Integer adminId) {
        try {
            Comment comment = new Comment();
            comment.setUserId(userId);
            comment.setText(text);
            comment.setBuildingId(buildingId);
            comment.setScore(score);
            comment.setAdminId(adminId);
            commentRepository.save(comment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 如果保存过程中出现异常，则返回 false
        }
    }

    // 根据评论ID查询评论
    public Comment getCommentById(Integer commentId) {
        return commentRepository.customFindById(commentId);
    }

    // 删除评论
    public void deleteCommentById(Integer commentId) {
        commentRepository.customDeleteById(commentId);
    }

    // 更新评论文本
    public void updateCommentText(Integer commentId, String text) {
        commentRepository.customUpdateText(commentId, text);
    }

    // 更新评论分数
    public void updateCommentScore(Integer commentId, BigDecimal score) {
        commentRepository.customUpdateScore(commentId, score);
    }

    // 查询所有评论
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // 自定义查询方法，根据建筑ID查询评论
    public List<Comment> getCommentsByBuildingId(Integer buildingId) {
        return commentRepository.findByBuildingId(buildingId);
    }
}
