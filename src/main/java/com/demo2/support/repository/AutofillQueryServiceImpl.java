/*
 * created by 2019年7月29日 下午3:45:48
 */
package com.demo2.support.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.demo2.support.dao.BasicDao;
import com.demo2.support.dao.impl.factory.Join;
import com.demo2.support.dao.impl.factory.VObj;
import com.demo2.support.dao.impl.factory.VObjFactory;
import com.demo2.support.entity.Entity;
import com.demo2.support.entity.ResultSet;
import com.demo2.support.service.impl.QueryServiceImpl;

/**
 * The implement of the query service that 
 * it auto fill each of the object property of the item of the query result set, like this: 
 * fill the addresses of each the customer of the result set.
 * @author fangang
 */
public class AutofillQueryServiceImpl extends QueryServiceImpl {
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
	
	@SuppressWarnings("unchecked")
	@Override
	protected ResultSet afterQuery(Map<String, Object> params, ResultSet resultSet) {
		//if no result, do nothing.
		List<?> list = resultSet.getData();
		if(list==null||list.isEmpty())
			return super.afterQuery(params, resultSet);
		
		//if the value object hasn't any join, do nothing.
		Object firstOfVo = list.get(0);
		List<Join> listOfJoins = listOfJoins(firstOfVo);
		if(listOfJoins==null||listOfJoins.isEmpty())
			return super.afterQuery(params, resultSet);
		
		//auto fill value objects for each joins.
		for(Join join : listOfJoins) {
			//TODO have exception
			autofill((List<Entity<Serializable>>)list, join);
		}
		return super.afterQuery(params, resultSet);
	}
	
	/**
	 * list all of the joins in the value object.
	 * @param vo the value object
	 * @return the list of joins
	 */
	private List<Join> listOfJoins(Object vo) {
		VObj vObj = VObjFactory.getVObj(vo.getClass().getName());
		return vObj.getJoins();
	}
	
	/**
	 * auto fill all of the joins in the value object.
	 * @param list
	 * @param join
	 */
	private <S extends Serializable, T extends Entity<S>> void autofill(List<T> list, Join join) {
		if(list==null||list.isEmpty()) return;
		for(T vo : list) {
			GenericEntityFactory<S> factory = new GenericEntityFactory<S>();
			factory.build(join, vo, dao);
		}
	}
	
//	/**
//	 * auto fill all of the joins in the value object.
//	 * @param list
//	 * @param join
//	 */
//	private <S extends Serializable, T extends Entity<S>> void autofill(List<T> list, Join join) {
//		List<S> listOfIds = new ArrayList<>();
//		String key = join.getJoinKey();
//		//TODO no implements oneToMany and manyToMany because of the performance reason.
//		if(!"oneToOne".equals(join.getJoinType())&&!"manyToOne".equals(join.getJoinType())) return;
//		
//		//get all of the ids.
//		if(list==null||list.isEmpty()) return;
//		for(T vo : list) {
//			@SuppressWarnings("unchecked")
//			//TODO have exception
//			S id = (S)BeanUtils.getValueByField(vo, key);
//			listOfIds.add(id);
//		}
//		
//		//load each of the entities by its id.
//		Entity<S> template = BeanUtils.createEntity(join.getClazz(), listOfIds.get(0));
//		List<Entity<S>> listOfEntity = dao.loadForList(listOfIds, template);
//		
//		//TODO when give the interface and method, how get the implement of feign provide?
//		
//		Map<Object, Entity<S>> mapOfEntity = new HashMap<>();
//		for(Entity<S> entity : listOfEntity) {
//			mapOfEntity.put(entity.getId(), entity);
//		}
//		
//		for(T vo : list) {
//			@SuppressWarnings("unchecked")
//			//TODO have exception
//			S id = (S)BeanUtils.getValueByField(vo, key);
//			Entity<S> entity = mapOfEntity.get(id);
//			BeanUtils.setValueByField(vo, join.getName(), entity);
//		}
//	}
}
