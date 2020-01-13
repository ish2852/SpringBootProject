package com.sns.prj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.prj.domain.FeedVO;
import com.sns.prj.domain.FollowVO;
import com.sns.prj.domain.PostVO;
import com.sns.prj.domain.UserVO;
import com.sns.prj.repository.FeedDAO;
import com.sns.prj.repository.FollowDAO;
import com.sns.prj.util.UserUtil;

@Service("feedService")
public class FeedServiceImpl implements FeedService{
	@Autowired
	private FeedDAO  feedDAO;
	@Autowired
	private FollowDAO followDAO;

	@Override
	public int insertFeedByUserIdAndPostId(Long userId, Long postId) {
		int successBySql = 0;
		List<FollowVO> followList = followDAO.findAllByFolloweeId(userId);
		List<FeedVO> feedList = new ArrayList<FeedVO>();
		
		FeedVO feedVO = null;
		for(FollowVO followVO : followList) {
			feedVO = new FeedVO();
			feedVO.setUserId(followVO.getFollowerId());
			feedVO.setFolloweeId(userId);
			feedVO.setPostId(postId);
			feedList.add(feedVO);
		}
		feedDAO.saveAll(feedList);
		
		successBySql = 1;
		return successBySql;
	}

	@Override
	public int deleteFeedByPostId(Long postId) {
		int successBySql = 0;
		List<FeedVO> feedList = feedDAO.findAllByPostId(postId);
		feedDAO.deleteAll(feedList);
		
		successBySql = 1;
		return successBySql;
	}

	@Override
	public List<PostVO> getFeedPostListByUserId(Long userId) {
		List<PostVO> postList = new ArrayList<PostVO>();
		List<FeedVO> feedList = feedDAO.findAllByUserIdOrderByCreatedAtDesc(userId);
		List<FollowVO> followList = followDAO.findAllByFollowerId(userId);
		
		PostVO postVO = null;
		for(FeedVO feedVO : feedList) {
			postVO = feedVO.getPost();
			
			if(postVO.getUserId() != userId) {
				UserUtil.setIsFollowByFollowListAndUser(followList, postVO.getUserVO());
			}
			
			postList.add(postVO);
		}
		
		return postList;
	}
}
