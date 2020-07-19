package com.FOS.dao;

import com.FOS.entity.Inventory;
import com.FOS.entity.Menu;
import com.FOS.entity.MenuItem;
import com.FOS.entity.Restaurant;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.ws.rs.core.Response;

public class InventoryDAO extends AbstractDAO<Menu> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

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
}
