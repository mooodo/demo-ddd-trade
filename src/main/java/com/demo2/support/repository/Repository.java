package com.demo2.support.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.demo2.support.dao.BasicDao;
import com.demo2.support.dao.impl.factory.Join;
import com.demo2.support.dao.impl.factory.VObj;
import com.demo2.support.dao.impl.factory.VObjFactory;
import com.demo2.support.entity.Entity;
import com.demo2.support.utils.BeanUtils;

/**
 * The generic DDD repository for all of the services in the system.
 * @author fangang
 */
public class Repository implements BasicDao {
	private BasicDao dao;
	
	/**
	 * @return the dao
	 */
	public BasicDao getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(BasicDao dao) {
		this.dao = dao;
	}

	@Override
	public <S extends Serializable, T extends Entity<S>> T load(S id, T template) {
		T vo = loadFromCache(id, template);
		if(vo!=null) return vo;
		vo = loadFromDb(id, template);
		setJoins(vo);
		return vo;
	}

	/**
	 * load the value object from cache. If no found, return null.
	 * @param id
	 * @param template
	 * @return the value object
	 */
	private <S extends Serializable, T extends Entity<S>> T loadFromCache(S id, T template) {
		return null;
	}

	/**
	 * load the value object from database. If no found, return null.
	 * @param id
	 * @param template
	 * @return the value object
	 */
	private <S extends Serializable, T extends Entity<S>> T loadFromDb(S id, T template) {
		T vo = dao.load(id, template);
		return vo;
	}
	
	/**
	 * set the value object's joins, if it has.
	 * @param vo
	 */
	private <S extends Serializable> void setJoins(Entity<S> vo) {
		if(vo==null) return;
		VObj vObj = VObjFactory.getVObj(vo.getClass().getName());
		List<Join> listOfJoins = vObj.getJoins();
		if(listOfJoins==null||listOfJoins.isEmpty()) return;
		
		for(Join join : listOfJoins) {
			GenericEntityFactory<S> factory = new GenericEntityFactory<>();
			factory.build(join, vo, dao);
		}
	}
	
	/**
	 * @param template
	 * @return whether the value object has join and the join is aggregation.
	 */
	private <S extends Serializable> boolean hasJoinAndAggregation(Entity<S> template) {
		if(template==null) return false;
		VObj vObj = VObjFactory.getVObj(template.getClass().getName());
		List<Join> listOfJoins = vObj.getJoins();
		if(listOfJoins==null||listOfJoins.isEmpty()) return false;
		int count = 0;
		for(Join join : listOfJoins) {
			if(join.isAggregation()) count++;
		}
		if(count>0) return true;
		return false;
	}
	
	/**
	 * save all of the items of a value object, if it has.
	 * @param vo the value object
	 */
	private void saveItems(Object vo) {
		VObj vObj = VObjFactory.getVObj(vo.getClass().getName());
		List<Join> listOfJoins = vObj.getJoins();
		if(listOfJoins==null||listOfJoins.isEmpty()) return;
		
		for(Join join : listOfJoins) {
			if(!join.isAggregation()) continue;
			String name = join.getName();
			Object items = BeanUtils.getValueByField(vo, name);
			if(items==null) continue;
			if(Collection.class.isAssignableFrom(items.getClass())) {
				@SuppressWarnings("unchecked")
				Collection<Object> collection = (Collection<Object>)items;
				dao.insertOrUpdateForList(collection);//is a lot of items.
			} else {
				dao.insertOrUpdate(items);//just one item.
			}
		}
	}
	
	/**
	 * delete all of the items of a value object, if it has.
	 * @param vo the value object
	 */
	private void deleteItems(Object vo) {
		VObj vObj = VObjFactory.getVObj(vo.getClass().getName());
		List<Join> listOfJoins = vObj.getJoins();
		if(listOfJoins==null||listOfJoins.isEmpty()) return;
		
		for(Join join : listOfJoins) {
			if(!join.isAggregation()) continue;
			String name = join.getName();
			Object items = BeanUtils.getValueByField(vo, name);
			if(items==null) continue;
			if(Collection.class.isAssignableFrom(items.getClass())) {
				@SuppressWarnings("unchecked")
				Collection<Object> collection = (Collection<Object>)items;
				dao.deleteForList(collection);//is a lot of items.
			} else {
				dao.delete(items);//just one item.
			}
		}
	}

	@Override
	@Transactional
	public <T> void insert(T vo) {
		dao.insert(vo);
		saveItems(vo);
	}

	@Override
	@Transactional
	public <T> void update(T vo) {
		dao.update(vo);
		saveItems(vo);
	}

	@Override
	@Transactional
	public <T> void insertOrUpdate(T vo) {
		dao.insertOrUpdate(vo);
		saveItems(vo);
	}

	@Override
	@Transactional
	public <T, S extends Collection<T>> void insertOrUpdateForList(S list) {
		for(Object vo : list) insertOrUpdate(vo);
	}

	@Override
	@Transactional
	public <T> void delete(T vo) {
		dao.delete(vo);
		deleteItems(vo);
	}

	@Override
	@Transactional
	public <T, S extends Collection<T>> void deleteForList(S list) {
		for(Object vo : list) delete(vo);
	}

	@Override
	public <S extends Serializable, T extends Entity<S>> List<T> loadAll(T template) {
		List<T> list = dao.loadAll(template);
		for(T vo : list) setJoins(vo);
		return list;
	}

	@Override
	@Transactional
	public <S extends Serializable, T extends Entity<S>> void delete(S id, T template) {
		if(hasJoinAndAggregation(template)) {
			T vo = dao.load(id, template);
			delete(vo);
		} else dao.delete(id, template);
	}

	@Override
	public <S extends Serializable, T extends Entity<S>> List<T> loadForList(List<S> ids, T template) {
		List<T> list = dao.loadForList(ids, template);
		for(T vo : list) setJoins(vo);
		return list;
	}

	@Override
	@Transactional
	public <S extends Serializable, T extends Entity<S>> void deleteForList(List<S> ids, T template) {
		if(hasJoinAndAggregation(template)) {
			List<T> list = dao.loadForList(ids, template);
			deleteForList(list);
		} else dao.deleteForList(ids, template);
	}
}
