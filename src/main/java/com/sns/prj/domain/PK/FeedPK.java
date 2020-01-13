package com.sns.prj.domain.PK;

import java.io.Serializable;

import javax.persistence.Id;

public class FeedPK implements Serializable {
	private Long userId;
	private Long followeeId;
	private Long postId;

}
