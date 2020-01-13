package com.sns.prj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sns.prj.domain.TokenVO;
import com.sns.prj.repository.TokenDAO;
import com.sns.prj.util.TokenUtil;

@Service("tokenService")
public class TokenServiceImpl implements TokenService{
	@Autowired
	TokenDAO tokenDAO;

	@Override
	public TokenVO getTokenByUserId(Long userId) {
		Pageable paging = PageRequest.of(0, 1, Sort.Direction.DESC, "createdAt"); 
		List<TokenVO> tokenList = tokenDAO.findAllByUserId(userId, paging);
		return tokenList.get(0);
	}

	@Override
	public void insertTokenByUserId(Long userId) {
		String token = TokenUtil.makeToken();
		
		TokenVO tokenVO = new TokenVO();
		tokenVO.setToken(token);
		tokenVO.setUserId(userId);
		tokenDAO.save(tokenVO);
	}

	@Override
	public TokenVO getTokenByToken(String token) {
		return tokenDAO.findById(token).get();
	}
	
}
