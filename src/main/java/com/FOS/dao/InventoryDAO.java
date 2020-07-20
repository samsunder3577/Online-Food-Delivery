package com.FOS.dao;

import com.FOS.entity.*;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.ws.rs.core.Response;

public class InventoryDAO extends AbstractDAO<Menu> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    //Add Inventory
    public Response addInventory(long MenuItemID,long RestaurantID ,Inventory inventory) {
        Session session =this.currentSession();
        System.out.println(MenuItemID);

        MenuItem menuItem=(MenuItem) session.load(MenuItem.class,MenuItemID);
        Restaurant restaurant=(Restaurant) session.load(Restaurant.class,RestaurantID);
        inventory.setMenuItem(menuItem);
        inventory.setRestaurant(restaurant);
        session.save(inventory);
        return Response.status(200).entity("Registered Successfully").build();
    }

    //Update Add Inventory
    public Response  updateAddInventory(long id, long value){
        Session session =currentSession();
        Inventory oldInventory=session.load(Inventory.class,id);
        Query query=namedQuery("com.FOS.entity.Inventory.getId").setParameter("id",id);
        if(query.uniqueResult()!=null) {
           long total=0;
           total=oldInventory.getQuantity()+value ;
           oldInventory.setQuantity(total);
            return Response.status(200).entity("Updated Successfully").build();
        }
        else
            return Response.status(400).entity("No such Customer Present").build();
    }

    //Update Sub Inventory
    public Response  updateSubInventory(long id, long value){
        Session session =currentSession();
        Inventory oldInventory=session.load(Inventory.class,id);
        Query query=namedQuery("com.FOS.entity.Inventory.getId").setParameter("id",id);
        if(query.uniqueResult()!=null) {
            long total=0;
            if(oldInventory.getQuantity()-value<0)
            {
                return Response.status(200).entity("Only "+oldInventory.getQuantity()+" is Available").build();
            }
            else {
                total = oldInventory.getQuantity() - value;
                oldInventory.setQuantity(total);
                return Response.status(200).entity("Updated Successfully").build();
            }
        }
        else
            return Response.status(400).entity("No such Customer Present").build();
    }
}
