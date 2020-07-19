package com.FOS.dao;

import com.FOS.entity.OrderStatus;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.ws.rs.core.Response;

public class OrderStatusDAO extends AbstractDAO<OrderStatus> {
    public OrderStatusDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
//Add OrderStatus
    public Response addOrderStatus(OrderStatus orderStatus) {
        Session session =this.currentSession();
        session.save(orderStatus);
        return Response.status(200).entity("Registered Successfully").build();
    }
}
