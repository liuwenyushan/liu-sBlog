package com.liu.personalblog.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liu.personalblog.Entity.Article;
import com.liu.personalblog.Service.ArticleService;
import com.liu.personalblog.Util.ResultSet;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	ArticleService articleService;

	// 添加文章
	@RequestMapping("/addArticle")
	@ResponseBody
	public ResultSet addArticle(Article article, BindingResult bindingResult,
			HttpSession session) {

		/* articleService.insertArticle(article); */

		return saveArticle(article, session, bindingResult);
	}

	// 删除文章
	@RequestMapping("/delete/{id}")
	@ResponseBody
	public ResultSet deleteArticle(@PathVariable("id") Integer id,
			HttpSession session) {
		ResultSet result = new ResultSet();
		String author = articleService.selectById(id).getAuthor();
		String user = (String) session.getAttribute("user");
		if (author.equals(user)) {
			articleService.deleteById(id);
			result.setSuccess(true);
			result.setCode("200");
			result.setMsg("删除成功");

		} else {
			result.setSuccess(false);
			result.setCode("-1");
			result.setMsg("没有权限，删除失败！");
		}
		return result;
	}

	// 保存文章
	@RequestMapping("/save")
	@ResponseBody
	public ResultSet saveArticle(@Valid Article article, HttpSession session,
			BindingResult bindingResult) {
		ResultSet result = new ResultSet();
		if (bindingResult.hasErrors()) {
			result.setSuccess(false);
			result.setCode("-1");
			// 获取异常信息
			result.setMsg(bindingResult.getFieldError().getDefaultMessage());
			result.setData(null);
			return result;
		}
		article.setAuthor((String) session.getAttribute("user"));
		Integer articleId = articleService.insertArticle(article);
		result.setSuccess(true);
		result.setCode("200");
		// 返回保存的文章的id
		result.setData((Integer) articleId);

		return result;

	}

}
