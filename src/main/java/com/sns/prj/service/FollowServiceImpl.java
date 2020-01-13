package com.sns.prj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.prj.domain.FollowVO;
import com.sns.prj.repository.FollowDAO;

@Service("followService")
public class FollowServiceImpl implements FollowService{
	@Autowired
	private FollowDAO followDAO;

	@Override
	public int insertFollow(Long followeeId, Long followerId) {
		int successBySql = 0;
		FollowVO followVO = new FollowVO(followeeId, followerId);
		followVO = followDAO.save(followVO);
		if(followVO.getCreatedAt() != null) {
			successBySql = 1;
		}
		return successBySql;
	}

	@Override
	public int deleteFollowByFolloweeIdAndFollowerId(Long followeeId, Long followerId) {
		int successBySql = 0;
		FollowVO followVO = followDAO.findByFolloweeIdAndFollowerId(followeeId, followerId);
		if(followVO.getCreatedAt() != null) {
			successBySql = 1;
			followDAO.delete(followVO);
		}
		return successBySql;
	}

}
