package com.FOS.resources;

import com.FOS.dao.InventoryDAO;
import com.FOS.dao.MenuItemDAO;
import com.FOS.entity.Customer;
import com.FOS.entity.Inventory;
import com.FOS.entity.MenuItem;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Restaurant/{RestaurantID}/menuItem/{MenuItemID}/Inventory")
@Produces(MediaType.APPLICATION_JSON)
public class InventoryResource {
    private InventoryDAO inventoryDAO;
    public InventoryResource(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addInventory(@PathParam("MenuItemID") long MenuItemID,
                                 @PathParam("RestaurantID") long RestaurantID, Inventory inventory)
    {
        System.out.println(MenuItemID+ "is the MenuItemID");
        Response received=inventoryDAO.addInventory(MenuItemID,RestaurantID,inventory);
        return received;
    }

    //Update Customer
    @PUT
    @Path("/add/{id}/{value}")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAddCustomer(@PathParam("id") long id, @PathParam("value") long value)
    {
        Response received=inventoryDAO.updateAddInventory(id,value);
        return received;
    }
    //Update Customer
    @PUT
    @Path("/sub/{id}/{value}")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSubCustomer(@PathParam("id") long id, @PathParam("value") long value)
    {
        Response received=inventoryDAO.updateSubInventory(id,value);
        return received;
    }
}
