/* 
 * create by 2020年1月22日 下午6:59:24
 */
package com.demo2.trade.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.List;

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

/**
 * @author fangang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerQueryTest {
	@Autowired
	private ApplicationContext context;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testQueryWithNoPeremeter() {
		QueryService service = (QueryService)context.getBean("customerQry");
		ResultSet resultSet = service.query(null);
		List<?> data = resultSet.getData();
		assertThat(data.size(), equalTo(2));
	}

}
