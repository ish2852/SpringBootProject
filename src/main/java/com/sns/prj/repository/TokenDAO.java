package com.sns.prj.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.sns.prj.domain.TokenVO;

public interface TokenDAO extends CrudRepository<TokenVO, String> {

	List<TokenVO> findAllByUserId(Long userId, Pageable paging);

}
