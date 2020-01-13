package com.sns.prj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sns.prj.domain.PostVO;

public interface PostDAO extends CrudRepository<PostVO, String>{

	List<PostVO> findAllByOrderByCreatedAtDesc();

	PostVO findByIdAndUserId(Long postId, Long userId);

	PostVO findById(Long id);

}
