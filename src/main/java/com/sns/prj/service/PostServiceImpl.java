package com.sns.prj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sns.prj.domain.FollowVO;
import com.sns.prj.domain.PostVO;
import com.sns.prj.domain.UserVO;
import com.sns.prj.repository.FollowDAO;
import com.sns.prj.repository.PostDAO;
import com.sns.prj.repository.redis.PostRedis;
import com.sns.prj.util.UserUtil;

@Service("postService")
public class PostServiceImpl implements PostService{
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private FollowDAO FollowDAO;
	@Autowired
	private PostRedis postRedis;

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
	@Cacheable(value= "postList", key = "#page", cacheManager="cacheManager")
	public HashMap<String, Object> getPostListByPage(int page) {
		Pageable paging = PageRequest.of(page, 5);
		
		Page<PostVO> pageInfo = postDAO.findAllByOrderByCreatedAtDesc(paging);
		
		HashMap<String, Object> postMap = new HashMap<String, Object>();
		postMap.put("post", pageInfo.getContent());
		postMap.put("hasNextPage", pageInfo.hasNext());
		return postMap;
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
		
		postRedis.deleteViewsByPostId(postId);
		
		successBySql = 1;
		return successBySql;
	}

	@Override
	@Cacheable(value= "userPostList", key = "{#userId, #page}", cacheManager="cacheManager")
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
		
		HashMap<String, Object> postMap = new HashMap<String, Object>();
		postMap.put("post", postList);
		postMap.put("hasNextPage", pageInfo.hasNext());
		return postMap;
	}

	@Override
	@Cacheable(value= "post", key = "#postId", cacheManager="cacheManager")
	public PostVO getPostAndWriterByPostId(Long postId) {
		PostVO postVO = postDAO.findById(postId);
		return postVO;
	}
	
	@Override
	public Long incrementViewsByPostId(Long postId) {
		return postRedis.addViewsByPostId(postId);
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

	@Override
	public void setViewsByPostMap(HashMap<String, Object> postMap) {
		List<PostVO> postList = (List<PostVO>) postMap.get("post");
		for(PostVO postVO : postList) { 
			Long views = postRedis.getViewsByPostId(postVO.getId());
			postVO.setViews(views);
		}
	}
	
	
}
