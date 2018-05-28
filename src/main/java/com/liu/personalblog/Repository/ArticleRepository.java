package com.liu.personalblog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.liu.personalblog.Entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

	public List<Article> findByCategory(String category);

	public List<Article> findByAuthor(String author);

	@Query(value = "select * from article order by id desc limit ?1,?2", nativeQuery = true)
	public List<Article> selectLatestArticles(int offset, int limit);

	@Query(value = "select count(id) from article where category=?1", nativeQuery = true)
	public int getCountByCategory(String Category);

	@Query(value = "select count(id) from article", nativeQuery = true)
	public int getArticleCount();

	@Query(value = "select * from article where content like %?1% or title like %?1%", nativeQuery = true)
	public List<Article> searchArticle(String keyWord);

	@Query(value = "select * from article order by id desc limit 0,6", nativeQuery = true)
	public List<Article> find6LatestArticleTitle();

	@Query(value = "select * from article order by (select count(id) from comment where article_id=article.id) desc limit 0,6", nativeQuery = true)
	public List<Article> find6HotestArticleTitle();

	@Query(value = "select count(id) from comment where article_id=?1", nativeQuery = true)
	public Integer getCommentCount(Integer ArticleId);
}
