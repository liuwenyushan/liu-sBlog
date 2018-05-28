package com.liu.personalblog.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liu.personalblog.Entity.Comment;
import com.liu.personalblog.Repository.CommentRepository;

@Component
public class CommentDAO {
	@Autowired
	CommentRepository commentRepository;

	public int insertComment(Comment comment) {
		Comment c = commentRepository.save(comment);
		return c.getId();
	}

	public void deleteCommentById(Integer id) {
		commentRepository.delete(id);
	}

	public List<Comment> getComments(Integer ArticleId) {
		return commentRepository.findByArticleId(ArticleId);
	}
}
