package com.FOS.dao;

import com.FOS.entity.*;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;

public class OrdersDAO extends AbstractDAO<Orders> {
    public OrdersDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void addOrder(Orders orders) throws NullPointerException{

        Session session =currentSession();
        Timestamp timeStamp=new Timestamp(System.currentTimeMillis());
        orders.setOrderTime(timeStamp);

        Restaurant restaurant= session.load(Restaurant.class,orders.getRestaurant().getId());
        Customer customer= session.load(Customer.class,orders.getCustomer().getId());
        CustomerAddress customerAddress= session.load(CustomerAddress.class,orders.getCustomerAddress().getId());
        DeliveryPartner deliveryPartner= session.load(DeliveryPartner.class,orders.getDeliveryPartner().getId());
        OrderStatus orderStatus= session.load(OrderStatus.class,orders.getOrderStatus().getId());

        orders.setRestaurant(restaurant);
        orders.setCustomer(customer);
        orders.setCustomerAddress(customerAddress);
        orders.setDeliveryPartner(deliveryPartner);
        orders.setOrderStatus(orderStatus);

        float total=0;
        for(int i=0;i<orders.getOrderItemList().size();i++)
        {
            orders.getOrderItemList().get(i).setOrders(orders);
            MenuItem menuItem = session.load(MenuItem.class, orders.getOrderItemList().get(i).getMenuItem().getId());
            orders.getOrderItemList().get(i).setSubtotal(orders.getOrderItemList().get(i).getQuantity()*menuItem.getPrice());
            System.out.println(orders.getOrderItemList().get(i).getSubtotal()+"KKGGISISISISI SUBTOTAL");
            total=total+orders.getOrderItemList().get(i).getSubtotal();
        }
        System.out.println(total+"is the price");
        orders.setPrice(total);
        session.save(orders);

    }
}
