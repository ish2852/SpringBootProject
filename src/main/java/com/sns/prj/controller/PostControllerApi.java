package com.sns.prj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sns.prj.domain.PostVO;
import com.sns.prj.domain.TokenVO;
import com.sns.prj.service.FeedService;
import com.sns.prj.service.PostService;
import com.sns.prj.service.TokenService;

@RestController
public class PostControllerApi {
	@Autowired
	private PostService postService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private FeedService feedService;

	@PostMapping("/post")
	public Object insertPost(@CookieValue(value = "accesstoken", required = false) String token,
			@RequestBody PostVO postVO) {

		if (token != null && postVO != null) {
			TokenVO tokenVo = tokenService.getTokenByToken(token);
			postVO.setUserId(tokenVo.getUserId());

			postVO = postService.insertPost(postVO.getUserId(), postVO.getTitle(), postVO.getContent());
			feedService.insertFeedByUserIdAndPostId(tokenVo.getUserId(), postVO.getId());
			postVO.setUserVO(null);
		} else {
			postVO = null;
		}

		return postVO;
	}

	@GetMapping("/post")
	public Object getPostList(@CookieValue(value = "accesstoken", required = false) String token) {
		Object data = null;
		if (token != null) {
			TokenVO tokenVO = tokenService.getTokenByToken(token);
			data = postService.getPostListByUserId(tokenVO.getUserId());
		} else {
			data = postService.getPostList();
		}
		return data;
	}

	@GetMapping("/post/feed")
	public Object getFeedPostListOfUser(@CookieValue(value = "accesstoken", required = false) String token) {
		Object data = null;
		if (token != null) {
			TokenVO tokenVO = tokenService.getTokenByToken(token);
			data = feedService.getFeedPostListByUserId(tokenVO.getUserId());
		}
		return data;
	}

	@GetMapping("/post/{postId}")
	public Object getPost(@PathVariable Long postId) {
		return postService.getPostAndWriterByPostId(postId);
	}

	@DeleteMapping("/post/{postId}")
	public Object deletePost(@CookieValue(value = "accesstoken", required = false) String token,
			@PathVariable Long postId) {
		int successBySql = 0;
		PostVO postVO = null;

		if (token != null) {
			TokenVO tokenVO = tokenService.getTokenByToken(token);
			postVO = postService.getPostByIdAndUserId(postId, tokenVO.getUserId());
		}
		
		if (postVO != null) {
			feedService.deleteFeedByPostId(postId);
			successBySql = postService.deletePostById(postId);
		}
		return successBySql;
	}

	@PutMapping("/post")
	public Object postModify(@CookieValue(value = "accesstoken", required = false) String token,
			@RequestBody PostVO PostVO) {

		PostVO beforePost = null;
		int successBySql = 0;

		if (token != null && PostVO.getId() != 0) {
			TokenVO tokenVO = tokenService.getTokenByToken(token);
			beforePost = postService.getPostByIdAndUserId(PostVO.getId(), tokenVO.getUserId());
		}

		if (beforePost != null) {
			successBySql = postService.updatePost(PostVO.getId(), PostVO.getTitle(), PostVO.getContent());
		}

		return successBySql;
	}

}
