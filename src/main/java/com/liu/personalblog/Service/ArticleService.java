package com.liu.personalblog.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.liu.personalblog.DAO.ArticleDAO;
import com.liu.personalblog.Entity.Article;

@Component
@Service
public class ArticleService {
	@Autowired
	ArticleDAO articleDAO;
	// 每页显示文章数
	static final int singlePageCount = 8;

	public int insertArticle(Article article) {
		return articleDAO.insertArticle(article);
	}

	public Article selectById(Integer id) {
		return articleDAO.selectById(id);
	}

	public List<Article> selectLatestArticles(int offset, int limit) {
		return articleDAO.selectLatestArticles(offset, limit);
	}

	public List<Article> selectArticleByCategory(String category) {
		return articleDAO.selectArticleByCategory(category);
	}

	public List<Article> listAllArticles() {
		return articleDAO.listAllArticles();
	}

	public int getCountByCategory(String Category) {
		return articleDAO.getCountByCategory(Category);
	}

	public List<Article> getByCategory(String category) {
		return articleDAO.getByCategory(category);
	}

	public List<Article> getByAuthor(String author) {
		return articleDAO.getByAuhor(author);
	}

	public int getAriticleCount() {
		return articleDAO.getAriticleCount();
	}

	// 根据传入入的文章list进行分页
	public List<Article> listSearchByPage(List<Article> articles, Integer id) {

		int articleCount = articles.size();

		if (articleCount <= 0)
			return null;

		if (id * singlePageCount < articleCount) {

			return articles.subList((id - 1) * singlePageCount, id
					* singlePageCount);
		} else {
			return articles.subList((id - 1) * singlePageCount, articleCount);
		}
	}

	// 实现博客文章分页查询，默认每页4篇文章
	public List<Article> listBlogByPage(Integer id) {

		int articleCount = articleDAO.getAriticleCount();
		if (articleCount <= 0)
			return null;

		if (id * singlePageCount < articleCount) {
			return articleDAO.selectLatestArticles((id - 1) * singlePageCount,
					singlePageCount);
		} else {
			return articleDAO.selectLatestArticles((id - 1) * singlePageCount,
					articleCount - (id - 1) * singlePageCount);
		}
	}

	// 获取分页数量
	public int getPageCount(int totalArticleCount) {
		// int totalArticleCount = articleDAO.getAriticleCount();
		if (totalArticleCount == 0)
			return 1;

		if (totalArticleCount % singlePageCount == 0)
			return totalArticleCount / singlePageCount;
		return totalArticleCount / singlePageCount + 1;
	}

	public void deleteById(int id) {
		articleDAO.deleteById(id);
	}

	public List<Article> searchArticle(String keyWord) {
		return articleDAO.searchArticle(keyWord);
	}

	public List<Article> find6LatestArticleTitle() {
		return articleDAO.find6LatestArticleTitle();
	}

	public List<Article> find6HotestArticleTitle() {
		return articleDAO.find6HotestArticleTitle();
	}

	public Integer getCommentCount(Integer ArticleId) {
		return articleDAO.getCommentCount(ArticleId);
	}
}
