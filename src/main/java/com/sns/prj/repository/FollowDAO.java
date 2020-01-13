package com.sns.prj.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sns.prj.domain.FollowVO;

public interface FollowDAO extends CrudRepository<FollowVO, String>{

	void deleteByFolloweeIdAndFollowerId(Long followeeId, Long followerId);

	FollowVO findByFolloweeIdAndFollowerId(Long followeeId, Long followerId);
	
	List<FollowVO> findAllByFollowerId(Long userId);

	List<FollowVO> findAllByFolloweeId(Long userId);
	
}
