/* 
 * Created by 2019年4月17日
 */
package com.demo2.support.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.demo2.support.entity.Entity;

/**
 * The basic dao for generic insert, update, delete operations.
 * @author fangang
 */
public interface BasicDao {
	/**
	 * insert a value object into table.
	 * @param vo
	 */
	public <T> void insert(T vo);
	/**
	 * update a value object.
	 * @param vo
	 */
	public <T> void update(T vo);
	/**
	 * if not exists, then insert, else update.
	 * @param vo
	 */
	public <T> void insertOrUpdate(T vo);
	/**
	 * insert a list of value objects, and if exists, then update.
	 * @param list
	 */
	public <T, S extends Collection<T>> void insertOrUpdateForList(S list);
	/**
	 * delete a value object.
	 * note: you must load the value object first or do like this: 
	 * <pre>
	 * User user = new User();
	 * user.setId("C0001");
	 * dao.delete(user);
	 * </pre>
	 * @param vo
	 */
	public <T> void delete(T vo);
	/**
	 * delete a list of value objects.
	 * @param list
	 */
	public <T, S extends Collection<T>> void deleteForList(S list);
	/**
	 * @param ids
	 * @param template
	 */
	public <S extends Serializable, T extends Entity<S>> void deleteForList(List<S> ids, T template);
	/**
	 * load an entity by id.
	 * @param id
	 * @param template just an empty object to know which class
	 * @return entity
	 */
	public <S extends Serializable, T extends Entity<S>> T load(S id, T template);
	/**
	 * load a list of entity by their ids.
	 * @param ids the list of id
	 * @param template just an empty object to know which class
	 * @return list of entity
	 */
	public <S extends Serializable, T extends Entity<S>> List<T> loadForList(List<S> ids, T template);
	/**
	 * load all entities.
	 * @param template just an empty object to know which class
	 * @return list of entities.
	 */
	public <S extends Serializable, T extends Entity<S>> List<T> loadAll(T template);
	/**
	 * delete an entity by id.
	 * @param id
	 * @param template just an empty object to know which class
	 */
	public <S extends Serializable, T extends Entity<S>> void delete(S id, T template);
}
