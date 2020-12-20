package com.demo2.support.cache;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo2.support.cache.RedisCache;
import com.demo2.support.entity.User;
import com.demo2.trade.TradeApplication;

/**
 * @author fangang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=TradeApplication.class)
public class RedisCacheTest {
	@Autowired
	private RedisCache redisCache;

	@Test
	public void testSetGetAndDelete() {
		int key = 1;
		User vo = new User(key, "John");
		redisCache.set(vo);
		User actual = redisCache.get(key, new User());
		assertThat(actual, equalTo(vo));
		
		redisCache.delete(key, new User());
		User deleted = redisCache.get(key, new User());
		assertNull(deleted);
	}
	
	@Test
	public void testSetGetAndDeleteForList() {
		List<User> list = new ArrayList<>();
		List<Integer> ids = new ArrayList<>();
		int key1 = 1;
		User vo1 = new User(key1, "John");
		list.add(vo1);
		ids.add(key1);
		int key2 = 2;
		User vo2 = new User(key2, "Rose");
		list.add(vo2);
		ids.add(key2);
		
		redisCache.setForList(list);
		List<User> actual = redisCache.getForList(ids, new User());
		assertThat(actual, equalTo(list));
		
		redisCache.deleteForList(ids, new User());
		List<User> deleted = redisCache.getForList(ids, new User());
		for(User vo : deleted) assertNull(vo);
	}
}
