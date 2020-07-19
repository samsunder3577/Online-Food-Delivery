package com.FOS.resources;

import com.FOS.dao.MenuItemDAO;
import com.FOS.entity.MenuItem;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Restaurant/{RestaurantID}/menu/{MenuID}/menuItem")
@Produces(MediaType.APPLICATION_JSON)
public class MenuItemResource {
    private MenuItemDAO menuItemDAO;

    public MenuItemResource(MenuItemDAO menuItemDAO) {
        this.menuItemDAO = menuItemDAO;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMenuItem(@PathParam("MenuID") long MenuId, MenuItem menuItem)
    {
        System.out.println(MenuId+ "is the menuId");
        Response received=menuItemDAO.addMenuItem(MenuId,menuItem);
        return received;
    }
}
