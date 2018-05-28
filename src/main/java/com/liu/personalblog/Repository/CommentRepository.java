package com.liu.personalblog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.liu.personalblog.Entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	@Query(value = "select * from comment where article_id=?1", nativeQuery = true)
	public List<Comment> findByArticleId(Integer ArticleId);
}
