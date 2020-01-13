package com.sns.prj.service;

import com.sns.prj.domain.FollowVO;

public interface FollowService {
	int insertFollow(Long followeeId, Long followerId );

	int deleteFollowByFolloweeIdAndFollowerId(Long followeeId, Long followerId);
}
