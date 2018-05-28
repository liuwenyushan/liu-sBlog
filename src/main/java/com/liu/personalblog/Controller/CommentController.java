package com.liu.personalblog.Controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liu.personalblog.Entity.Comment;
import com.liu.personalblog.Entity.User;
import com.liu.personalblog.Service.CommentService;
import com.liu.personalblog.Service.UserService;

@Controller
public class CommentController {
	@Autowired
	CommentService commentService;
	@Autowired
	UserService userService;

	@RequestMapping("/addComment/{articleId}")
	public String addComment(@PathVariable("articleId") Integer articleId,
			String message, HttpSession session) {
		Comment comment = new Comment();
		comment.setArticleId(articleId);
		comment.setContent(message);

		comment.setCreatedate(new Date());

		if (session.getAttribute("user") == null) {
			comment.setName("匿名用户");
			comment.setAvatarId("0");
		} else {
			String username = (String) session.getAttribute("user");
			comment.setName(username);
			User user = userService.getUserByName(username);
			comment.setAvatarId(user.getAvatar());
		}
		commentService.insertComment(comment);

		return "redirect:/single/" + articleId;
	}

	@RequestMapping("/deleteComment/{articleId}/{commentId}")
	public String deleteComment(@PathVariable("articleId") Integer articleId,
			@PathVariable("commentId") Integer CommentId) {
		commentService.deleteCommentById(CommentId);
		return "redirect:/single/" + articleId;
	}
}
