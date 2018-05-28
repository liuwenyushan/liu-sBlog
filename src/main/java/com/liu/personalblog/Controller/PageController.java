package com.liu.personalblog.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.liu.personalblog.Entity.Article;
import com.liu.personalblog.Entity.User;
import com.liu.personalblog.Service.ArticleService;
import com.liu.personalblog.Service.CommentService;

@Controller
public class PageController {

	@Autowired
	ArticleService articleService;

	@Autowired
	CommentService commentService;

	@RequestMapping(value = { "/index", "/" })
	public ModelAndView index(Model model) {
		model.addAttribute("articles",
				articleService.selectLatestArticles(0, 4));
		model.addAttribute("user", new User());
		model.addAttribute("title", "首页");
		model.addAttribute("newArticles",
				articleService.find6LatestArticleTitle());
		model.addAttribute("hotArticles",
				articleService.find6HotestArticleTitle());
		return new ModelAndView("index", "articleModel", model);
	}

	@RequestMapping("/about")
	public String about() {
		return "about";
	}

	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}

	// 浏览单篇文章
	@RequestMapping("/single/{id}")
	public ModelAndView single(@PathVariable("id") Integer id, Model model) {
		Article article = articleService.selectById(id);
		// article.setContent(md2htmlUtil.parseMd(article.getContent()));

		model.addAttribute("article", article);
		model.addAttribute("title", article.getTitle());
		model.addAttribute("newArticles",
				articleService.find6LatestArticleTitle());
		model.addAttribute("hotArticles",
				articleService.find6HotestArticleTitle());
		model.addAttribute("comments", commentService.getComments(id));
		model.addAttribute("commentCount", articleService.getCommentCount(id));

		return new ModelAndView("single", "articleModel", model);
	}

	// 新建博文
	@RequestMapping("/edit")
	public ModelAndView editNew(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return new ModelAndView("redirect:/index");
		} else {
			model.addAttribute("article", new Article());
			model.addAttribute("title", "创建博文");

			return new ModelAndView("edit", "articleModel", model);
		}
	}

	// 编辑博文
	@RequestMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Integer id,
			HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return new ModelAndView("redirect:/index");
		} else {
			model.addAttribute("article", articleService.selectById(id));
			model.addAttribute("title", "编辑博文");
			return new ModelAndView("edit", "articleModel", model);
		}
	}

	// 返回所有博客列表，默认每页4篇文章
	@RequestMapping("/bloglist/{id}")
	public ModelAndView blogListPage(@PathVariable("id") Integer pageId,
			Model model) {
		// 总页面数
		int pageCount = articleService.getPageCount(articleService
				.getAriticleCount());
		// 处理页面id溢出问题
		if (pageId <= 0) {
			pageId = 1;
		}
		if (pageId > pageCount) {
			pageId = pageCount;
		}
		model.addAttribute("type", "list");
		model.addAttribute("articles", articleService.listBlogByPage(pageId));
		model.addAttribute("pageCount", pageCount);// 分页页面数
		model.addAttribute("pageId", pageId);// 当前页面ID
		model.addAttribute("newArticles",
				articleService.find6LatestArticleTitle());
		model.addAttribute("hotArticles",
				articleService.find6HotestArticleTitle());
		model.addAttribute("title", "博客列表");
		return new ModelAndView("bloglist", "articleModel", model);
	}

	// 分页返回查询结果
	@RequestMapping("/search/{id}")
	public ModelAndView SearchPage(@PathVariable("id") Integer pageId,
			String keyword, Model model) {

		if (keyword == null)
			return new ModelAndView("redirect:/index");
		List<Article> articles = articleService.searchArticle(keyword);
		int articleCount = articles.size();
		Integer pageCount = articleService.getPageCount(articleCount);

		if (pageId <= 0) {
			pageId = 1;
		}
		if (pageId > pageCount) {
			pageId = pageCount;
		}

		model.addAttribute("type", "search");
		model.addAttribute("articles",
				articleService.listSearchByPage(articles, pageId));
		model.addAttribute("pageCount", pageCount);// 分页页面数
		model.addAttribute("pageId", pageId);// 当前页面ID
		model.addAttribute("newArticles",
				articleService.find6LatestArticleTitle());
		model.addAttribute("hotArticles",
				articleService.find6HotestArticleTitle());
		model.addAttribute("title", "搜索结果");
		model.addAttribute("keyword", keyword);

		return new ModelAndView("bloglist", "articleModel", model);
	}

	// 按照分类返回查询结果
	@RequestMapping("/category/{name}/{id}")
	public ModelAndView getByCategory(@PathVariable("name") String category,
			@PathVariable("id") Integer pageId, Model model) {
		if (category == null)
			return new ModelAndView("redirect:/index");
		List<Article> articles = articleService.getByCategory(category);

		int articleCount = articles.size();
		Integer pageCount = articleService.getPageCount(articleCount);

		if (pageId <= 0) {
			pageId = 1;
		}
		if (pageId > pageCount) {
			pageId = pageCount;
		}

		model.addAttribute("type", "category");
		model.addAttribute("articles",
				articleService.listSearchByPage(articles, pageId));
		model.addAttribute("pageCount", pageCount);// 分页页面数
		model.addAttribute("pageId", pageId);// 当前页面ID
		model.addAttribute("newArticles",
				articleService.find6LatestArticleTitle());
		model.addAttribute("hotArticles",
				articleService.find6HotestArticleTitle());
		model.addAttribute("title", "搜索结果");
		model.addAttribute("category", category);

		return new ModelAndView("bloglist", "articleModel", model);
	}

	@RequestMapping("/register")
	public String register() {
		return "register";
	}
}
