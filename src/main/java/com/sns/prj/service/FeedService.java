package com.sns.prj.service;

import java.util.List;

import com.sns.prj.domain.PostVO;

public interface FeedService {

	int insertFeedByUserIdAndPostId(Long userId, Long postId);

	int deleteFeedByPostId(Long postId);

	List<PostVO> getFeedPostListByUserId(Long userId);

}
