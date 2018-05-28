package com.liu.personalblog.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liu.personalblog.Entity.Article;
import com.liu.personalblog.Repository.ArticleRepository;

@Component
public class ArticleDAO {

	@Autowired
	ArticleRepository articleRepository;

	public int insertArticle(Article article) {
		article.setCreateDate(new Date());
		return articleRepository.save(article).getId();
	}

	public Article selectById(Integer id) {
		return articleRepository.findOne(id);
	}

	public List<Article> selectLatestArticles(int offset, int limit) {
		return articleRepository.selectLatestArticles(offset, limit);
	}

	public List<Article> selectArticleByCategory(String category) {
		return articleRepository.findByCategory(category);
	}

	public List<Article> listAllArticles() {
		return articleRepository.findAll();
	}

	public long getArticleCount() {
		return articleRepository.count();
	}

	public int getCountByCategory(String Category) {
		return articleRepository.getCountByCategory(Category);
	}

	public List<Article> getByCategory(String category) {
		return articleRepository.findByCategory(category);
	}

	public List<Article> getByAuhor(String author) {
		return articleRepository.findByAuthor(author);
	}

	public int getAriticleCount() {
		return articleRepository.getArticleCount();
	}

	public void deleteById(int id) {
		articleRepository.delete(id);
	}

	public List<Article> searchArticle(String keyWord) {
		return articleRepository.searchArticle(keyWord);
	}

	public List<Article> find6LatestArticleTitle() {
		return articleRepository.find6LatestArticleTitle();
	}

	public List<Article> find6HotestArticleTitle() {
		return articleRepository.find6HotestArticleTitle();
	}

	public Integer getCommentCount(Integer ArticleId) {
		return articleRepository.getCommentCount(ArticleId);
	}
}
