package com.sns.prj.service;

import com.sns.prj.domain.TokenVO;

public interface TokenService {

	TokenVO getTokenByUserId(Long userId);

	void insertTokenByUserId(Long userId);

	TokenVO getTokenByToken(String token);
}
