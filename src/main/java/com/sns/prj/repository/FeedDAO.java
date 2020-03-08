package com.sns.prj.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.sns.prj.domain.FeedVO;

public interface FeedDAO extends CrudRepository<FeedVO, String>{

	List<FeedVO> findAllByUserId(Long userId);

	List<FeedVO> findAllByPostId(Long postId);

	Page<FeedVO> findAllByUserIdOrderByCreatedAtDesc(Long userId, Pageable paging);


}
