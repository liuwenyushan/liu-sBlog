package com.liu.personalblog.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liu.personalblog.Entity.Article;
import com.liu.personalblog.Entity.User;
import com.liu.personalblog.Service.ArticleService;
import com.liu.personalblog.Service.UserService;
import com.liu.personalblog.Util.ResultSet;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	ArticleService articleService;

	@RequestMapping("/{username}/{pageId}")
	public ModelAndView user(@PathVariable("pageId") Integer pageId,
			@PathVariable("username") String username, Model model) {

		User user = userService.getUserByName(username);
		List<Article> aritcles = articleService.getByAuthor(username);

		int articleCount = aritcles.size();
		Integer pageCount = articleService.getPageCount(articleCount);

		if (pageId <= 0) {
			pageId = 1;
		}
		if (pageId > pageCount) {
			pageId = pageCount;
		}

		model.addAttribute("user", user);
		model.addAttribute("articles",
				articleService.listSearchByPage(aritcles, pageId));
		model.addAttribute("pageCount", pageCount);// 分页页面数
		model.addAttribute("pageId", pageId);// 当前页面ID
		model.addAttribute("totalArticleCount", aritcles.size());
		return new ModelAndView("user", "articleModel", model);
	}

	@RequestMapping("/login")
	@ResponseBody
	public ResultSet userlogin(String username, String password,
			HttpSession session) {
		Map<String, Object> map = userService.login(username, password);
		ResultSet result = new ResultSet();
		// 登陆失败
		if (map == null) {
			result.setSuccess(false);
			result.setCode("-1");
			result.setMsg("用户不存在或密码错误！");

		}// 登陆成功
		else {

			User user = (User) map.get("user");
			session.setAttribute("user", user.getName());
			session.setAttribute("userId", user.getId());
			session.setAttribute("avatar", user.getAvatar());

			result.setSuccess(true);
			result.setCode("200");
			result.setMsg("登陆成功");
		}
		return result;
	}

	@RequestMapping("/logout")
	public String userLogOut(HttpSession session) {
		session.setAttribute("user", null);
		session.setAttribute("userId", null);
		return "redirect:/index";
	}

	@RequestMapping("/register")
	@ResponseBody
	public ResultSet register(@Valid User user, BindingResult bindingResult) {

		ResultSet result = new ResultSet();
		if (bindingResult.hasErrors()) {
			result.setSuccess(false);
			result.setCode("-1");
			result.setMsg(bindingResult.getFieldError().getDefaultMessage());
		} else if (userService.getUserByName(user.getName()) != null) {
			result.setSuccess(false);
			result.setCode("500");
			result.setMsg("已存在相同用户！");
		} else {
			userService.addUser(user);
			result.setSuccess(true);
			result.setCode("200");
			result.setMsg("注册成功");
		}

		return result;
	}

}
