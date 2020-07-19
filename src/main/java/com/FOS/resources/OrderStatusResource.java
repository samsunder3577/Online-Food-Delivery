package com.FOS.resources;

import com.FOS.dao.OrderStatusDAO;
import com.FOS.entity.OrderStatus;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orderStatus")
@Produces(MediaType.APPLICATION_JSON)
public class OrderStatusResource {
    private OrderStatusDAO orderStatusDAO;
    public OrderStatusResource(OrderStatusDAO orderStatusDAO){
        this.orderStatusDAO=orderStatusDAO;
    }
    //Add Order Status
    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrderStatus(OrderStatus orderStatus)
    {
        Response received=orderStatusDAO.addOrderStatus(orderStatus);
        return received;
    }
}
