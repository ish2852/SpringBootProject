package com.sns.prj.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString()
@Entity
@Table(name="token")
public class TokenVO {
	@Id
	private String token;
	private Long userId;
	@Temporal(value = TemporalType.TIMESTAMP)
	@CreationTimestamp
	@JsonFormat(timezone = "Asia/Seoul")
	private Date createdAt;

	public TokenVO() {

	}

}
