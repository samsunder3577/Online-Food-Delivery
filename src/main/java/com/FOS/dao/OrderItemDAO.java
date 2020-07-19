package com.FOS.dao;

import com.FOS.entity.MenuItem;
import com.FOS.entity.OrderItem;
import com.FOS.entity.Orders;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OrderItemDAO extends AbstractDAO<Orders> {
    public OrderItemDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void addOrderItem(OrderItem orderItem) {
        Session session = currentSession();
        Orders orders = session.load(Orders.class, orderItem.getOrders().getId());
        MenuItem menuItem = session.load(MenuItem.class, orderItem.getMenuItem().getId());
        orderItem.setSubtotal(orderItem.getQuantity()*orderItem.getMenuItem().getPrice());
        orderItem.setMenuItem(menuItem);
        orderItem.setOrders(orders);
        session.save(orderItem);
    }
}
