package com.FOS.resources;

import com.FOS.dao.DeliveryPartnerDAO;
import com.FOS.entity.Customer;
import com.FOS.entity.DeliveryPartner;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.persistence.PostUpdate;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/DeliveryPartner")
@Produces(MediaType.APPLICATION_JSON)
public class DeliveryPartnerResource {
    private DeliveryPartnerDAO deliveryPartnerDAO;

    public DeliveryPartnerResource(DeliveryPartnerDAO deliveryPartnerDAO) {
        this.deliveryPartnerDAO = deliveryPartnerDAO;
    }

    //Get Delivery partner by phone or email
    @GET
    @UnitOfWork
    public List<DeliveryPartner> findByMobileOrEmail(@QueryParam("data") Optional<String> data) {
        if (data.isPresent()) {
            return deliveryPartnerDAO.findByMobileOrEmail(data.get());
        } else {
            return deliveryPartnerDAO.findAll();
        }
    }

    //Get Delivery partner by id
    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<DeliveryPartner> findById(@PathParam("id") LongParam id) {
        return deliveryPartnerDAO.findById(id.get());
    }

    //Get Active Delivery Partner
    @GET
    @Path("/Active")
    @UnitOfWork
    public List<DeliveryPartner> findActive() {
        return deliveryPartnerDAO.findActive();
    }

    //Add Delivery Partner
    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDeliveryPartner(DeliveryPartner DeliveryPartner)
    {
        Response received=deliveryPartnerDAO.addDeliveryPartner(DeliveryPartner);
        return received;
    }

    //Update IsActive
    @PUT
    @Path("/{id}/{isActive}")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIsActive(@PathParam("id") long id, @PathParam("isActive") boolean isActive)
    {
        Response received=deliveryPartnerDAO.updateIsActive(id,isActive);
        return received;
    }

    //Update Customer
    @PUT
    @Path("/{id}")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDeliveryPartner(@PathParam("id") long id, DeliveryPartner deliveryPartner)
    {
        deliveryPartner.setId(id);
        Response received=deliveryPartnerDAO.updateDeliveryPartner(id,deliveryPartner);
        return received;
    }

}

