package com.sns.prj.domain.PK;

import java.io.Serializable;

import javax.persistence.Id;

public class FollowPK implements Serializable {
	private Long followeeId;
	private Long followerId;
}
