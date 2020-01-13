package com.sns.prj.util;

import java.util.List;

import com.sns.prj.domain.FollowVO;
import com.sns.prj.domain.UserVO;

public class UserUtil {
	
	public static void setIsFollowByFollowListAndUser(List<FollowVO> followList, UserVO userVO) {
		userVO.setIsFollow(false);
		
		for(FollowVO followVO : followList) {
			if(followVO.getFolloweeId() == userVO.getId()) {
				userVO.setIsFollow(true);
				break;
			}
		}
	}
}
