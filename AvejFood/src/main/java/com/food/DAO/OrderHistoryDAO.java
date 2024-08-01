package com.food.DAO;

import java.util.List;

import com.food.model.OrderHistory;

public interface OrderHistoryDAO {
	void addOrderHistry(OrderHistory orderHistry);
	
	OrderHistory getOrderHistry(int orderHistryId);
	
	void updateOrderHistry(OrderHistory orderHistry);
	
	void deleteOrderHistry(int orderHistryId);
	
	List<OrderHistory> getAllOrderHistory(OrderHistory orderHistry);

}
