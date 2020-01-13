package com.sns.prj.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sns.prj.domain.PK.FeedPK;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "feed")
@IdClass(FeedPK.class)
public class FeedVO implements Serializable{
	@Id
	private Long userId;
	@Id
	private Long followeeId;
	@Id
	private Long postId;
	@Temporal(value = TemporalType.TIMESTAMP)
	@CreationTimestamp
	@JsonFormat(timezone = "Asia/Seoul")
	private Date createdAt;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "postId", referencedColumnName = "id", insertable=false, updatable=false)
	@JsonProperty(value = "post")
	private PostVO post;
	
	public FeedVO() {
	}
	
	public FeedVO(Long userId, Long followeeId) {
		this.userId = userId;
		this.followeeId = followeeId;
	}
	
}
