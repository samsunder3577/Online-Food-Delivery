package com.FOS.dao;

import com.FOS.entity.Restaurant;
import com.FOS.entity.Restaurant;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

public class RestaurantDAO extends AbstractDAO<Restaurant> {
    public RestaurantDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Restaurant> findAll(){
        List<Restaurant> list = list((CriteriaQuery<Restaurant>) namedQuery("com.FOS.entity.Restaurant.findAll"));
        return list;
    }

    public List<Restaurant> findByMobileOrName(String data) {
        return list((org.hibernate.query.Query<Restaurant>) namedQuery("com.FOS.entity.Restaurant.findByMobileOrName")
                .setParameter("data",data));
    }

    //Find by Id
    public Optional<Restaurant> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    //Find Active Restaurant
    public List<Restaurant> findActive() {
        return list((org.hibernate.query.Query<Restaurant>) namedQuery("com.FOS.entity.Restaurant.findActive"));
    }

    //Add Restaurant
    public Response addRestaurant(Restaurant restaurant) {
        Session session =this.currentSession();
        List<Restaurant> existingRestaurant=
                list((org.hibernate.query.Query<Restaurant>) namedQuery("com.FOS.entity.Restaurant.duplicatePhone")
                        .setParameter("phone",restaurant.getPhone()));
        System.out.println(existingRestaurant);
        if(existingRestaurant.isEmpty())
        {
            session.save(restaurant);
            return Response.status(200).entity("Registered Successfully").build();
        }
        else
            return Response.status(400).entity("Phone Already Exists").build();
    }

}
