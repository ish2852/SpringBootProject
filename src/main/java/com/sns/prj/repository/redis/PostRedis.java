package com.sns.prj.repository.redis;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class PostRedis implements Serializable {  
	@Autowired
	private RedisTemplate<String, Long> redisTemplate;
	
	public Long getViewsByPostId(Long postId) {
		ValueOperations<String, Long> vop = redisTemplate.opsForValue();
		Long views = (Long) vop.get("Views:"+postId);
		
        if(views == null) {
        	 vop.set("Views:"+postId, 0L);
        	 views = 0L;
        }
		return views;
	}
	
	public Long addViewsByPostId(Long postId) {
		ValueOperations<String, Long> vop = redisTemplate.opsForValue();
        Long views =  vop.increment("Views:"+postId, 1L);
        return views;
	}
	
	public Boolean deleteViewsByPostId(Long postId) {
		return redisTemplate.delete("Views:"+postId);
	}
}
