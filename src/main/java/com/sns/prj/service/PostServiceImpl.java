package com.sns.prj.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public HashMap<String, Object> getPostListByPage(int page) {
		Pageable paging = PageRequest.of(page, 5);
		
		Page<PostVO> pageInfo = postDAO.findAllByOrderByCreatedAtDesc(paging);
		
		HashMap<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("post", pageInfo.getContent());
		returnData.put("hasNextPage", pageInfo.hasNext());
		return returnData;
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
	public HashMap<String, Object> getPostListByUserIdAndPage(Long userId, int page) {
		Pageable paging = PageRequest.of(page, 5);

		Page<PostVO> pageInfo = postDAO.findAllByOrderByCreatedAtDesc(paging);
		List<PostVO> postList = pageInfo.getContent();
		List<FollowVO> followList = FollowDAO.findAllByFollowerId(userId);
		
		UserVO userVO = null;
		for(PostVO postVO : postList) {
			userVO = postVO.getUserVO();
			
			if(userVO.getId() != userId) {
				UserUtil.setIsFollowByFollowListAndUser(followList, userVO);
			}
		}
		
		HashMap<String, Object> returnData = new HashMap<String, Object>();
		returnData.put("post", postList);
		returnData.put("hasNextPage", pageInfo.hasNext());
		return returnData;
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
