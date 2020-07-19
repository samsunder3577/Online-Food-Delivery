package com.FOS.resources;

import com.FOS.dao.OrdersDAO;
import com.FOS.entity.Orders;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrdersResource {
    private OrdersDAO ordersDAO;
    public OrdersResource(OrdersDAO ordersDAO){
        this.ordersDAO=ordersDAO;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder(Orders orders )
    {
        ordersDAO.addOrder(orders);
        return Response.status(200).build();
    }
}
