/* 
 * create by 2020年2月4日 下午5:54:31
 */
package com.demo2.support.repository;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

import com.demo2.support.dao.impl.factory.Ref;
import com.demo2.support.entity.Entity;
import com.demo2.support.exception.OrmException;
import com.demo2.support.utils.BeanUtils;

/**
 * @author fangang
 */
public class ReferenceFactory <S extends Serializable> {
	private Ref ref;
	private Entity<S> vo;
	private ApplicationContext context = null;
	
	public ReferenceFactory(ApplicationContext context) {
		this.context = context;
	}
	
	/**
	 * @param ref
	 * @param vo
	 */
	public void build(Ref ref, Entity<S> vo) {
		this.ref = ref;
		this.vo = vo;
		
		String refType = ref.getRefType();
		if("oneToOne".equals(refType)) {
			Object value = loadOfOneToOne(ref, vo);
			setValueOfRefToVo(value);
		}
		if("manyToOne".equals(refType)) {
			Object value = loadOfManyToOne(ref, vo);
			setValueOfRefToVo(value);
		}
		if("oneToMany".equals(refType)) {
			List<Object> list = loadOfOneToMany(ref, vo);
			setListOfRefToVo(list);
		}
		if("manyToMany".equals(refType)) {
			throw new OrmException("Don't support the many to many relation now!");
		}
	}
	
	/**
	 * @param ref
	 * @param vo
	 * @return
	 */
	private Object loadOfOneToOne(Ref ref, Entity<S> vo) {
		S id = vo.getId();
		String bean = ref.getBean();
		Object service = getBean(bean);
		String methodName = ref.getMethod();
		Method method = getMethod(service, methodName);
		try {
			return method.invoke(service, id);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new OrmException("error when invoking the service by reflect", e);
		}
	}
	
	/**
	 * @param ref
	 * @param vo
	 * @return
	 */
	private Object loadOfManyToOne(Ref ref, Entity<S> vo) {
		String refKey = ref.getRefKey();
		Object id = BeanUtils.getValueByField(vo, refKey);
		if(id==null) return null;
		String bean = ref.getBean();
		Object service = getBean(bean);
		String methodName = ref.getMethod();
		Method method = getMethod(service, methodName);
		try {
			return method.invoke(service, id);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new OrmException("error when invoking the service by reflect", e);
		}
	}
	
	/**
	 * @param ref
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Object> loadOfOneToMany(Ref ref, Entity<S> vo) {
		S id = vo.getId();
		String bean = ref.getBean();
		Object service = getBean(bean);
		String methodName = ref.getMethod();
		Method method = getMethod(service, methodName);
		try {
			return (List<Object>)method.invoke(method, id);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new OrmException("error when invoking the service by reflect", e);
		}
	}
	
	/**
	 * get bean in the spring context by name.
	 * @param name the bean name
	 * @return the instance of the bean
	 */
	private Object getBean(String name) {
		if(name==null||name.isEmpty()) throw new OrmException("The bean name is empty!");
		try {
			return context.getBean(name);
		} catch (NoSuchBeanDefinitionException e) {
			throw new OrmException("No such bean definition in the spring context!", e);
		} catch (BeansException e) {
			throw new OrmException("error when get the bean["+name+"]");
		}
	}
	
	/**
	 * get the method of the service by name, using reflect.
	 * @param service
	 * @param name the name of the method
	 * @return the reference of the method
	 */
	private Method getMethod(Object service, String name) {
		if(name==null||name.isEmpty()) throw new OrmException("The method name is empty!");
		Method[] allOfMethods = service.getClass().getDeclaredMethods();
		for(Method method : allOfMethods) {
			if(method.getName().equals(name)) return method;
		}
		throw new OrmException("No such method["+name+"] in the service["+service.getClass().getName()+"]");
	}
	
	/**
	 * set value of the join to the value object.
	 * @param list the list of value.
	 */
	private void setValueOfRefToVo(Object value) {
		String name = ref.getName();
		BeanUtils.setValueByField(vo, name, value);
	}
	
	/**
	 * set value of the join to the value object.
	 * @param list the list of value.
	 */
	private void setListOfRefToVo(List<Object> list) {
		String name = ref.getName();
		BeanUtils.setValueByField(vo, name, list);
	}
}
