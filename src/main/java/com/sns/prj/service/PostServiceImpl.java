package com.sns.prj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.prj.domain.FollowVO;
import com.sns.prj.domain.PostVO;
import com.sns.prj.domain.UserVO;
import com.sns.prj.repository.FeedDAO;
import com.sns.prj.repository.FollowDAO;
import com.sns.prj.repository.PostDAO;
import com.sns.prj.util.UserUtil;

@Service("postService")
public class PostServiceImpl implements PostService{
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private FollowDAO FollowDAO;
	@Autowired
	private FeedDAO feedDAO;
	
	@Override
	public PostVO insertPost(Long userId, String title, String content) {
		PostVO postVO = new PostVO(userId, title, content);
		UserVO userVO = new UserVO();
		userVO.setId(userId);
		postVO.setUserVO(userVO);
		postVO = postDAO.save(postVO);
		return postVO;
	}

	@Override
	public List<PostVO> getPostList() {
		return postDAO.findAllByOrderByCreatedAtDesc();
	}

	@Override
	public PostVO getPostByIdAndUserId(Long postId, Long userId) {
		return postDAO.findByIdAndUserId(postId, userId);
	}

	@Override
	public int deletePostById(Long postId) {
		int successBySql = 0;
		
		PostVO postVO = new PostVO();
		postVO.setId(postId);
		postDAO.delete(postVO);
		
		successBySql = 1;
		return successBySql;
	}

	@Override
	public List<PostVO> getPostListByUserId(Long userId) {
		List<PostVO> postList = postDAO.findAllByOrderByCreatedAtDesc();
		List<FollowVO> followList = FollowDAO.findAllByFollowerId(userId);
		
		UserVO userVO = null;
		for(PostVO postVO : postList) {
			userVO = postVO.getUserVO();
			
			if(userVO.getId() != userId) {
				UserUtil.setIsFollowByFollowListAndUser(followList, userVO);
			}
			
		}
		return postList;
	}

	@Override
	public PostVO getPostAndWriterByPostId(Long postId) {
		return postDAO.findById(postId);
	}

	@Override
	public int updatePost(Long id, String title, String content) {
		int successBySql = 0;
		
		PostVO postVO = postDAO.findById(id);
		postVO.setTitle(title);
		postVO.setContent(content);
		postDAO.save(postVO);
		
		successBySql = 1;
		return successBySql;
	}
}
