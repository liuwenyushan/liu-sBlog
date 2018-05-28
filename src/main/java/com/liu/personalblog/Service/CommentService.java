package com.liu.personalblog.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liu.personalblog.DAO.CommentDAO;
import com.liu.personalblog.Entity.Comment;

@Service
public class CommentService {
	@Autowired
	CommentDAO commentDAO;

	public int insertComment(Comment comment) {
		return commentDAO.insertComment(comment);
	}

	public void deleteCommentById(Integer id) {
		commentDAO.deleteCommentById(id);
		;
	}

	public List<Comment> getComments(Integer ArticleId) {
		return commentDAO.getComments(ArticleId);
	}
}
