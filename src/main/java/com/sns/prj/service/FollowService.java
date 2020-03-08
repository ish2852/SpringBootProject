package com.sns.prj.service;

public interface FollowService {
	int insertFollow(Long followeeId, Long followerId );

	int deleteFollowByFolloweeIdAndFollowerId(Long followeeId, Long followerId);
}
