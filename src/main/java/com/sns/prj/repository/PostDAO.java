package com.sns.prj.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sns.prj.domain.PostVO;

public interface PostDAO extends CrudRepository<PostVO, String>{

	Page<PostVO> findAllByOrderByCreatedAtDesc(Pageable paging);

	PostVO findByIdAndUserId(Long postId, Long userId);

	PostVO findById(Long id);

}
