package com.sns.prj.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sns.prj.domain.PK.FollowPK;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "follow")
@IdClass(FollowPK.class)
public class FollowVO {
	@Id
	private Long followeeId;
	@Id
	private Long followerId;
	@Temporal(value = TemporalType.TIMESTAMP)
	@CreationTimestamp
	@JsonFormat(timezone = "Asia/Seoul")
	private Date createdAt;

	public FollowVO() {
	}

	public FollowVO(Long followeeId, Long followerId) {
		this.followeeId = followeeId;
		this.followerId = followerId;
	}

}
