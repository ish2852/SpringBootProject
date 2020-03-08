package com.sns.prj.service;

import java.util.HashMap;
import java.util.List;

import com.sns.prj.domain.PostVO;

public interface PostService {

	PostVO getPostByIdAndUserId(Long postId, Long userId);

	PostVO insertPost(Long userId, String title, String content);

	HashMap<String, Object> getPostListByPage(int page);

	int deletePostById(Long postId);

	HashMap<String, Object> getPostListByUserIdAndPage(Long userId, int page);

	PostVO getPostAndWriterByPostId(Long postId);

	int updatePost(Long id, String title, String content);
	
	void setViewsByPostMap(HashMap<String, Object> postMap);

	Long incrementViewsByPostId(Long postId);
}
