/* 
 * create by 2020年2月3日 下午8:33:57
 */
package com.demo2.trade.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo2.support.utils.DateUtils;
import com.demo2.trade.entity.Order;
import com.demo2.trade.entity.OrderItem;
import com.demo2.trade.service.OrderService;

/**
 * @author fangang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
	@Autowired
	private OrderService service;

	@Test
	public void testSaveAndDeleteWithoutItems() {
		Order order = new Order();
		long id = 10012;
		order.setId(id);
		order.setCustomerId((long)10001);
		order.setAddressId((long)100010);
		order.setAmount((double)5000);
		order.setOrderTime(DateUtils.getDate("2020-01-01 00:00:00","YYYY-MM-DD hh:mm:ss"));
		order.setFlag("CREATE");
		service.createOrder(order);
		Order actual = service.checkOrder(id);
		actual.setExclude(new String[] {"address","customer","orderItems"});
		assertThat("error when insert", actual, equalTo(order));
		
		//order.setFlag("PAYMENT");
		service.modifyOrder(order);
		actual = service.checkOrder(id);
		actual.setExclude(new String[] {"address","customer","orderItems"});
		assertThat("error when update", actual, equalTo(order));
		
		service.deleteOrder(order);
		actual = service.checkOrder(id);
		assertNull("error when delete", actual);
	}

	@Test
	public void testSaveAndDeleteWithItems() {
		Order order = new Order();
		long id = 10011;
		order.setId(id);
		order.setCustomerId((long)10001);
		order.setAddressId((long)100010);
		order.setAmount((double)6600);
		order.setOrderTime(DateUtils.getDate("2020-01-01 00:00:00","YYYY-MM-DD hh:mm:ss"));
		order.setFlag("CREATE");
		
		List<OrderItem> orderItems = new ArrayList<>();
		OrderItem item1 = new OrderItem();
		item1.setId((long)100110);
		item1.setOrderId(id);
		item1.setProductId((long)30001);
		item1.setQuantity((double)1);
		item1.setPrice((double)4000);
		item1.setAmount((double)4000);
		orderItems.add(item1);
		
		OrderItem item2 = new OrderItem();
		item2.setId((long)100111);
		item2.setOrderId(id);
		item2.setProductId((long)30004);
		item2.setQuantity((double)100);
		item2.setPrice((double)26);
		item2.setAmount((double)2600);
		orderItems.add(item2);
		
		order.setOrderItems(orderItems);
		service.createOrder(order);
		Order actual = service.checkOrder(id);
		actual.setExclude(new String[] {"address","customer"});
		for(OrderItem item : actual.getOrderItems()) item.setExclude(new String[] {"product"});
		assertThat("error when insert", actual, equalTo(order));
		
		order.setAmount((double)7500);
		item2.setPrice((double)30);
		item2.setAmount((double)3000);
		
		OrderItem item3 = new OrderItem();
		item3.setId((long)100112);
		item3.setOrderId(id);
		item3.setProductId((long)30005);
		item3.setQuantity((double)10);
		item3.setPrice((double)50);
		item3.setAmount((double)500);
		orderItems.add(item3);
		
		service.modifyOrder(order);
		actual = service.checkOrder(id);
		actual.setExclude(new String[] {"address","customer"});
		for(OrderItem item4 : actual.getOrderItems()) item4.setExclude(new String[] {"product"});
		assertThat("error when update", actual, equalTo(order));
		
		service.deleteOrder(order);
		actual = service.checkOrder(id);
		assertNull("error when delete", actual);
	}

}
