package com.sns.prj.service;

import java.util.HashMap;
import java.util.List;

import com.sns.prj.domain.PostVO;

public interface FeedService {

	int insertFeedByUserIdAndPostId(Long userId, Long postId);

	int deleteFeedByPostId(Long postId);

	HashMap<String, Object> getFeedPostListByUserIdAndPage(Long userId, int page);

}
