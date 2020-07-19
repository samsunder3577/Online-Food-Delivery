package com.FOS.resources;

import com.FOS.dao.RestaurantDAO;
import com.FOS.entity.Restaurant;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/Restaurant")
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantResource {
    private RestaurantDAO RestaurantDAO;

    public RestaurantResource(RestaurantDAO RestaurantDAO) {
        this.RestaurantDAO = RestaurantDAO;
    }

    @GET
    @UnitOfWork
    public List<Restaurant> findByMobileOrName(@QueryParam("data") Optional<String> data) {
        if (data.isPresent()) {
            return RestaurantDAO.findByMobileOrName(data.get());
        } else {
            return RestaurantDAO.findAll();
        }
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Restaurant> findById(@PathParam("id") LongParam id) {
        return RestaurantDAO.findById(id.get());
    }

    @GET
    @Path("/Active")
    @UnitOfWork
    public List<Restaurant> findActive() {
        return RestaurantDAO.findActive();
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRestaurant(Restaurant restaurant)
    {
        Response received=RestaurantDAO.addRestaurant(restaurant);
        return received;
    }


}