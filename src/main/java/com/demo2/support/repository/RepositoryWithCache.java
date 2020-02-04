/* 
 * create by 2020年2月3日 下午12:38:09
 */
package com.demo2.support.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.demo2.support.cache.BasicCache;
import com.demo2.support.dao.BasicDao;
import com.demo2.support.entity.Entity;

/**
 * The DDD repository using cache: 
 * 1) if load a value object, load from cache first.
 * 2) if not in the cache, set to cache after query from database.
 * 3) if update or delete a value object, delete from cache.
 * @author fangang
 */
public class RepositoryWithCache extends Repository implements BasicDao {
	private BasicCache cache;

	/**
	 * @return the cache
	 */
	public BasicCache getCache() {
		return cache;
	}

	/**
	 * @param cache the cache to set
	 */
	public void setCache(BasicCache cache) {
		this.cache = cache;
	}

	@Override
	public <S extends Serializable, T extends Entity<S>> T load(S id, T template) {
		T vo = cache.get(id, template);
		if(vo!=null) return vo;
		vo = super.load(id, template);
		cache.set(vo);
		return vo;
	}

	@Override
	public <S extends Serializable, T extends Entity<S>> List<T> loadForList(List<S> ids, T template) {
		if(ids==null||template==null) return null;
		
		List<T> vos = cache.getForList(ids, template);
		List<S> otherIds = getIdsNotInCache(ids, vos);
		if(otherIds.isEmpty()) return vos; //cache get all of the value objects.
		
		List<T> list = super.loadForList(otherIds, template);
		
		if(otherIds.size()==ids.size()) return list; //all of the value objects query for database.
		return fillOtherVosIn(ids, vos, list); //fill the value objects query for db in the list of value objects get in cache.
	}
	
	/**
	 * @param ids
	 * @param vos
	 * @return all of the id not in cache
	 */
	private <S extends Serializable, T extends Entity<S>> List<S> getIdsNotInCache(List<S> ids, List<T> vos) {
		List<S> otherIds = new ArrayList<>();
		Iterator<T> vo = vos.iterator();
		Iterator<S> id = ids.iterator();
		while(vo.hasNext()&&id.hasNext()) {
			if(vo.next()==null) otherIds.add(id.next());
		}
		return otherIds;
	}
	
	/**
	 * fill the value objects, which load from other source, in the list of value objects load from cache.
	 * @param ids
	 * @param vos the list of vos load from cache
	 * @param otherVos the other vos load from other source
	 * @return the list of value objects
	 */
	private <S extends Serializable, T extends Entity<S>> List<T> fillOtherVosIn(List<S> ids, List<T> vos, List<T> otherVos) {
		Map<S,T> map = new HashMap<>();
		for(T value : otherVos) {
			map.put(value.getId(), value);
		}
		
		Iterator<T> voIter = vos.iterator();
		Iterator<S> idIter = ids.iterator();
		while(voIter.hasNext()&&idIter.hasNext()) {
			T vo = voIter.next();
			if(vo!=null) continue;
			vo = map.get(idIter.next());
		}
		return vos;
	}

	@Override
	public <T> void update(T vo) {
		super.update(vo);
		if(vo instanceof Entity) 
			deleteCache((Entity<?>) vo);
	}

	/**
	 * @param vo the value object
	 */
	private <S extends Serializable, T extends Entity<S>> void deleteCache(T vo) {
		cache.delete(vo.getId(), vo);
	}

	@Override
	public <T> void insertOrUpdate(T vo) {
		super.insertOrUpdate(vo);
		if(vo instanceof Entity) 
			deleteCache((Entity<?>) vo);
	}

	@Override
	public <S extends Serializable, T extends Entity<S>> void delete(S id, T template) {
		super.delete(id, template);
		cache.delete(id, template);
	}

	@Override
	public <T> void delete(T vo) {
		super.delete(vo);
		if(vo instanceof Entity) 
			deleteCache((Entity<?>) vo);
	}

	@Override
	public <S extends Serializable, T extends Entity<S>> void deleteForList(List<S> ids, T template) {
		super.deleteForList(ids, template);
		cache.deleteForList(ids, template);
	}
}
