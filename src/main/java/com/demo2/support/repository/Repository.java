package com.demo2.support.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.demo2.support.dao.BasicDao;
import com.demo2.support.dao.impl.factory.Join;
import com.demo2.support.dao.impl.factory.Ref;
import com.demo2.support.dao.impl.factory.VObj;
import com.demo2.support.dao.impl.factory.VObjFactory;
import com.demo2.support.entity.Entity;
import com.demo2.support.utils.BeanUtils;

/**
 * The generic DDD repository for all of the services in the system. 
 * According to the configuration vObj.xml: 
 * 1)if the value object has any join, fill the join after load data.
 * 2)if the value object has any join and the join is aggregation, save the join data in same transaction.
 * @author fangang
 */
public class Repository implements BasicDao {
	private BasicDao dao;
	@Autowired
	private ApplicationContext context;
	
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
		T vo = dao.load(id, template);
		setJoins(vo);
		setRefs(vo);
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
	 * set the value object's joins, if it has.
	 * @param vo
	 */
	private <S extends Serializable> void setRefs(Entity<S> vo) {
		if(vo==null) return;
		VObj vObj = VObjFactory.getVObj(vo.getClass().getName());
		List<Ref> listOfRefs = vObj.getRefs();
		if(listOfRefs==null||listOfRefs.isEmpty()) return;
		
		for(Ref ref : listOfRefs) {
			ReferenceFactory<S> factory = new ReferenceFactory<>(context);
			factory.build(ref, vo);
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
	 * @param template
	 * @return whether the value object has reference and the reference is aggregation.
	 */
	private <S extends Serializable> boolean hasRefAndAggregation(Entity<S> template) {
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
	 * save all of the joins of a value object, if it has.
	 * @param vo the value object
	 */
	private void saveJoins(Object vo) {
		VObj vObj = VObjFactory.getVObj(vo.getClass().getName());
		List<Join> listOfJoins = vObj.getJoins();
		if(listOfJoins==null||listOfJoins.isEmpty()) return;
		
		for(Join join : listOfJoins) {
			if(!join.isAggregation()) continue;
			String name = join.getName();
			Object value = BeanUtils.getValueByField(vo, name);
			if(value==null) continue;
			if(Collection.class.isAssignableFrom(value.getClass())) {
				Collection<?> collection = (Collection<?>)value;
				dao.insertOrUpdateForList(collection);//is a lot of items.
			} else {
				dao.insertOrUpdate(value);//just one item.
			}
		}
	}
	
	/**
	 * delete all of the joins of a value object, if it has.
	 * @param vo the value object
	 */
	private void deleteJoins(Object vo) {
		VObj vObj = VObjFactory.getVObj(vo.getClass().getName());
		List<Join> listOfJoins = vObj.getJoins();
		if(listOfJoins==null||listOfJoins.isEmpty()) return;
		
		for(Join join : listOfJoins) {
			if(!join.isAggregation()) continue;
			String name = join.getName();
			Object value = BeanUtils.getValueByField(vo, name);
			if(value==null) continue;
			if(Collection.class.isAssignableFrom(value.getClass())) {
				Collection<?> collection = (Collection<?>)value;
				dao.deleteForList(collection);//is a lot of items.
			} else {
				dao.delete(value);//just one item.
			}
		}
	}

	@Override
	@Transactional
	public <T> void insert(T vo) {
		dao.insert(vo);
		saveJoins(vo);
	}

	@Override
	@Transactional
	public <T> void update(T vo) {
		dao.update(vo);
		saveJoins(vo);
	}

	@Override
	@Transactional
	public <T> void insertOrUpdate(T vo) {
		dao.insertOrUpdate(vo);
		saveJoins(vo);
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
		deleteJoins(vo);
	}

	@Override
	@Transactional
	public <T, S extends Collection<T>> void deleteForList(S list) {
		for(Object vo : list) delete(vo);
	}

	@Override
	public <S extends Serializable, T extends Entity<S>> List<T> loadAll(T template) {
		List<T> list = dao.loadAll(template);
		for(T vo : list) {
			setJoins(vo);
			setRefs(vo);
		}
		return list;
	}

	@Override
	@Transactional
	public <S extends Serializable, T extends Entity<S>> void delete(S id, T template) {
		if(hasJoinAndAggregation(template)||hasRefAndAggregation(template)) {
			T vo = dao.load(id, template);
			delete(vo);
		} else dao.delete(id, template);
	}

	@Override
	public <S extends Serializable, T extends Entity<S>> List<T> loadForList(List<S> ids, T template) {
		if(ids==null||template==null) return null;
		List<T> list = dao.loadForList(ids, template);
		for(T vo : list) {
			setJoins(vo);
			setRefs(vo);
		}
		return list;
	}

	@Override
	@Transactional
	public <S extends Serializable, T extends Entity<S>> void deleteForList(List<S> ids, T template) {
		if(hasJoinAndAggregation(template)||hasRefAndAggregation(template)) {
			List<T> list = dao.loadForList(ids, template);
			deleteForList(list);
		} else dao.deleteForList(ids, template);
	}
}
