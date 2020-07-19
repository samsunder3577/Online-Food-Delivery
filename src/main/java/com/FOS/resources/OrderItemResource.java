package com.FOS.resources;


import com.FOS.dao.CustomerDAO;
import com.FOS.dao.OrderItemDAO;
import com.FOS.entity.OrderItem;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Order/{orderId}/orderItem")
@Produces(MediaType.APPLICATION_JSON)
public class OrderItemResource {
    private OrderItemDAO orderItemDAO;
    public OrderItemResource(OrderItemDAO orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }

    @PUT
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrderItem(OrderItem orderItem)
    {
        orderItemDAO.addOrderItem(orderItem);
        return Response.ok().build();
    }
}
