/* 
 * create by 2020年1月22日 下午6:59:24
 */
package com.demo2.trade.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo2.support.entity.ResultSet;
import com.demo2.support.service.QueryService;
import com.demo2.trade.entity.Customer;

/**
 * @author fangang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerQueryTest {
	@Autowired
	private ApplicationContext context;
	QueryService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = (QueryService)context.getBean("customerQry");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryWithNoParameter() {
		ResultSet resultSet = service.query(null);
		List<?> data = resultSet.getData();
		assertThat(data.size(), equalTo(4));
	}

	@Test
	public void testQueryWithParameter() {
		Map<String, Object> params = new HashMap<>();
		params.put("id", "10001");
		ResultSet resultSet = service.query(params);
		@SuppressWarnings("unchecked")
		List<Customer> data = (List<Customer>)resultSet.getData();
		assertThat(data.get(0).getId(), equalTo(new Long(10001)));
	}
	
	@Test
	public void testQureyWithPage() {
		Map<String, Object> params = new HashMap<>();
		params.put("page", 0);
		params.put("size", 2);
		ResultSet resultSet = service.query(params);
		List<?> data = resultSet.getData();
		assertThat(data.size(), equalTo(2));
		assertThat(resultSet.getPage(), equalTo(0));
		assertThat(resultSet.getSize(), equalTo(2));
		assertThat(resultSet.getCount(), equalTo(new Long(4)));
	}
	
	@Test
	public void testQureyWithStringOfPage() {
		Map<String, Object> params = new HashMap<>();
		params.put("size", "2");
		ResultSet resultSet = service.query(params);
		List<?> data = resultSet.getData();
		assertThat(data.size(), equalTo(2));
		assertThat(resultSet.getPage(), equalTo(0));
		assertThat(resultSet.getSize(), equalTo(2));
		assertThat(resultSet.getCount(), equalTo(new Long(4)));
	}
	
	@Test
	public void testQureyWithCount() {
		Map<String, Object> params = new HashMap<>();
		params.put("count", 3);
 		ResultSet resultSet = service.query(params);
		Long count = resultSet.getCount();
		assertThat(count, equalTo(new Long(3)));
	}
	
	@Test
	public void testQureyWithPageAndCount() {
		Map<String, Object> params = new HashMap<>();
		params.put("page", 1);
		params.put("size", 2);
		params.put("count", 5);
		ResultSet resultSet = service.query(params);
		List<?> data = resultSet.getData();
		assertThat(data.size(), equalTo(2));
		assertThat(resultSet.getPage(), equalTo(1));
		assertThat(resultSet.getSize(), equalTo(2));
		assertThat(resultSet.getCount(), equalTo(new Long(5)));
	}
	
	@Test
	public void testQueryWithAggregation() {
		Map<String, String> aggregation = new HashMap<>();
		aggregation.put("id", "count");
		aggregation.put("phone_number", "sum");
		Map<String, Object> params = new HashMap<>();
		params.put("aggregation", aggregation);
		ResultSet resultSet = service.query(params);
		
		Map<String, Object> aggValue = resultSet.getAggregation();
		assertThat(aggValue.get("id"), equalTo(new Long(4)));
		assertThat(aggValue.get("phone_number"), equalTo(new Double("59641458084")));
	}
}
