/* 
 * create by 2020年1月30日 下午12:12:14
 */
package com.demo2.support.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.demo2.support.entity.Entity;
import com.demo2.support.exception.DaoException;

/**
 * The cache implement for redis.
 * @author fangang
 */
public class RedisCache implements BasicCache {
	private static Log log = LogFactory.getLog(RedisCache.class);
	private static String SPLITTER = "#";
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public <S extends Serializable, T extends Entity<S>> void set(T vo) {
		if(vo==null) return;
		String key = generateKey(vo, vo.getId());
		log.debug("set a value object to cache: {key: "+key+", value: "+vo+"}");
		redisTemplate.opsForValue().set(key, vo);
	}

	@Override
	public <S extends Serializable, T extends Entity<S>> T get(S id, T template) {
		if(id==null||template==null) return null;
		String key = generateKey(template, id);
		Object vo = redisTemplate.opsForValue().get(key);
		if(vo==null) return null;
		log.debug("get a value object from cache: {key: "+key+", value: "+vo+"}");
		return object2Entity(vo, template);
	}

	@Override
	public <S extends Serializable, T extends Entity<S>> void delete(S id, T template) {
		if(id==null||template==null) return;
		String key = generateKey(template, id);
		log.debug("delete a value object from cache: {key: "+key+"}");
		redisTemplate.delete(key);
	}

	@Override
	public <S extends Serializable, T extends Entity<S>> void setForList(List<T> vos) {
		if(vos==null||vos.isEmpty()) return;
		Map<String, Entity<S>> map = new HashMap<>();
		for(Entity<S> vo : vos) {
			String key = generateKey(vo, vo.getId());
			map.put(key, vo);
		}
		log.debug("set a list of value objects to cache: {values: "+vos+"}");
		redisTemplate.opsForValue().multiSet(map);
	}

	@Override
	public <S extends Serializable, T extends Entity<S>> List<T> getForList(List<S> ids, T template) {
		if(ids==null||template==null) return null;
		List<String> keys = new ArrayList<>();
		for(S id : ids) {
			String key = generateKey(template, id);
			keys.add(key);
		}
		List<Object> values = redisTemplate.opsForValue().multiGet(keys);
		if(values==null) return null;
		List<T> vos = new ArrayList<>();
		for(Object value : values) {
			T vo = object2Entity(value, template);
			vos.add(vo);
		}
		log.debug("get a list of value objects from cache: {keys: "+keys+", values: "+vos+"}");
		return vos;
	}

	@Override
	public <S extends Serializable, T extends Entity<S>> void deleteForList(List<S> ids, T template) {
		if(ids==null||template==null) return;
		List<String> keys = new ArrayList<>();
		for(S id : ids) {
			String key = generateKey(template, id);
			keys.add(key);
		}
		log.debug("delete a list of value objects from cache: {keys: "+keys+"}");
		redisTemplate.delete(keys);
	}
	
	/**
	 * @param obj
	 * @param template
	 * @return convert an object to entity
	 */
	@SuppressWarnings("unchecked")
	private <S extends Serializable, T extends Entity<S>> T object2Entity(Object obj, T template) {
		if(obj==null||template==null) return null;
		if(!template.getClass().equals(obj.getClass()))
			throw new DaoException("the object must be an entity["+obj.getClass()+"]");
		return (T)obj;
	}
	
	/**
	 * @param vo the value object
	 * @param id
	 * @return generate the key with a certain rule.
	 */
	private <S extends Serializable, T extends Entity<S>> String generateKey(T vo, S id) {
		String clazz = vo.getClass().getName();
		return clazz + SPLITTER + id;
	}
}
