package com.sns.prj.service;

import java.util.List;

import com.sns.prj.domain.PostVO;

public interface PostService {

	PostVO getPostByIdAndUserId(Long postId, Long userId);

	PostVO insertPost(Long userId, String title, String content);

	List<PostVO> getPostList();

	int deletePostById(Long postId);

	List<PostVO> getPostListByUserId(Long userId);

	PostVO getPostAndWriterByPostId(Long postId);

	int updatePost(Long id, String title, String content);
}
