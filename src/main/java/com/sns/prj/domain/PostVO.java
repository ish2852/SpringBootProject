package com.sns.prj.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString()
@Entity
@Table(name = "post")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostVO implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable=false, insertable=false) 
	private Long userId;
	private String title;
	private String content;
	@Temporal(value = TemporalType.TIMESTAMP)
	@CreationTimestamp
	@JsonFormat(timezone = "Asia/Seoul")
	private Date createdAt;

	@ManyToOne(optional = false)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	@JsonProperty(value = "user")
	private UserVO userVO;
	
	@Transient
	@JsonProperty(value = "views")
	private Long views;

	public PostVO() {
	}

	public PostVO(Long userId, String title, String content) {
		this.userId = userId;
		this.title = title;
		this.content = content;
	}

	public PostVO(Long id, Long userId) {
		this.id = id;
		this.userId = userId;
	}

}
