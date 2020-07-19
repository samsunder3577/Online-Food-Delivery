package com.FOS.dao;

import com.FOS.entity.Menu;
import com.FOS.entity.MenuItem;
import com.FOS.entity.Restaurant;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.ws.rs.core.Response;

public class MenuItemDAO extends AbstractDAO<MenuItem> {

    public MenuItemDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Response addMenuItem(long MenuID, MenuItem menuItem) {
        Session session =this.currentSession();
        System.out.println(MenuID+" Is the menu Id");

        Menu menu=(Menu) session.load(Menu.class,MenuID);
        menuItem.setMenu(menu);
        session.save(menuItem);
        return Response.status(200).entity("Registered Successfully").build();
    }
}
