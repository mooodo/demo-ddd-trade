/*
 * created by 2019年7月23日 上午10:11:43
 */
package com.demo2.support.utils;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.demo2.support.entity.Entity;
import com.demo2.support.exception.OrmException;

/**
 * The utility for the bean.
 * @author fangang
 */
public class BeanUtils {
	
	/**
	 * create an entity by class name.
	 * @param className
	 * @return the entity
	 */
	public static <S extends Serializable> Entity<S> createEntity(String className, S id) {
		try {
			@SuppressWarnings("unchecked")
			Class<? extends Entity<S>> clazz = (Class<? extends Entity<S>>) Class.forName(className).asSubclass(Entity.class);
			Entity<S> entity = createEntity(clazz);
			entity.setId(id);
			return entity;
		} catch (ClassNotFoundException e) {
			throw new OrmException("error because the entity["+className+"] must exits and extends the class [Entity]", e);
		}
	}
	
	/**
	 * create an entity by class
	 * @param clazz
	 * @return the entity
	 */
	public static <S extends Serializable> Entity<S> createEntity(Class<? extends Entity<S>> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new OrmException("error when instance the entity["+clazz.getName()+"]", e);
		}
	}
	
	/**
	 * get the value from a bean by field name.
	 * @param bean
	 * @param fieldName
	 * @return the value
	 */
	public static Object getValueByField(Object bean, String fieldName) {
		if(bean==null||fieldName==null) return null;
		try {
			Field field = bean.getClass().getDeclaredField(fieldName);
			boolean isAccessible = field.isAccessible();
			if(!isAccessible) field.setAccessible(true);
			Object value = field.get(bean);
			field.setAccessible(isAccessible);
			return value;
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			throw new OrmException("error when get value from the bean[bean:"+bean+",field:"+fieldName+"]", e);
		}
	}
	
	/**
	 * set the value to the bean by the field name.
	 * @param bean
	 * @param fieldName
	 * @param value
	 */
	public static void setValueByField(Object bean, String fieldName, Object value) {
		try {
			Field field = bean.getClass().getDeclaredField(fieldName);
			boolean isAccessible = field.isAccessible();
			if(!isAccessible) field.setAccessible(true);
			field.set(bean, value);
			field.setAccessible(isAccessible);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new OrmException("error when set the value to the bean", e);
		}
	}
	
	/**
	 * set the value to the bean by the field name.
	 * @param bean
	 * @param fieldName
	 * @param value
	 */
	public static void setValueByField(Object bean, String fieldName, BeanCallback callback) {
		try {
			Field field = bean.getClass().getDeclaredField(fieldName);
			Class<?> clazz = field.getType();
			Object value = callback.getValue(clazz);
			
			setValueByField(bean, fieldName, value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException e) {
			throw new OrmException("error when set the value to the bean", e);
		}
	}
	
	public interface BeanCallback {
		public Object getValue(Class<?> clazz);
	}
}
