package com.sns.prj.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sns.prj.domain.FeedVO;
import com.sns.prj.domain.PostVO;

public interface FeedDAO extends CrudRepository<FeedVO, String>{

	List<FeedVO> findAllByUserId(Long userId);

	@Query(value="SELECT p.id , p.user_id, p.title, p.content, p.created_at, u.id , u.username, u.password, u.created_at"+
			"		FROM feed AS f" + 
			"			JOIN post AS p" + 
			"				ON f.post_id = p.id" + 
			"			JOIN user AS u" + 
			"				ON f.followee_id = u.id" + 
			"		WHERE f.user_id = ?1" + 
			"		ORDER BY p.created_at ASC", nativeQuery=true)
	List<PostVO> getFeedPostListByUserId(Long userId);

	List<FeedVO> findAllByPostId(Long postId);

	Page<FeedVO> findAllByUserIdOrderByCreatedAtDesc(Long userId, Pageable paging);


}
