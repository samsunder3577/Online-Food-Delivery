package com.FOS.dao;

import com.FOS.entity.Menu;
import com.FOS.entity.Restaurant;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.ws.rs.core.Response;

public class MenuDAO extends AbstractDAO<Menu> {
    public MenuDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Response addMenu(long RestaurantID, Menu menu) {
        Session session =this.currentSession();
        System.out.println(RestaurantID);

        Restaurant restaurant=(Restaurant) session.load(Restaurant.class,RestaurantID);
        menu.setRestaurant(restaurant);
        session.save(menu);
        return Response.status(200).entity("Registered Successfully").build();
    }
}
