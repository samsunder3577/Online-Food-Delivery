package com.FOS.resources;

import com.FOS.dao.MenuDAO;
import com.FOS.entity.Menu;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Restaurant/{RestaurantID}/menu")
@Produces(MediaType.APPLICATION_JSON)
public class MenuResource {
    private MenuDAO menuDAO;
    public MenuResource(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMenu(@PathParam("RestaurantID") long RestaurantID, Menu menu)
    {
        Response received=menuDAO.addMenu(RestaurantID,menu);
        return received;
    }
}
