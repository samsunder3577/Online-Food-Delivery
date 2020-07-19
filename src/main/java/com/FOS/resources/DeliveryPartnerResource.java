package com.FOS.resources;

import com.FOS.dao.DeliveryPartnerDAO;
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
    private DeliveryPartnerDAO DeliveryPartnerDAO;

    public DeliveryPartnerResource(DeliveryPartnerDAO DeliveryPartnerDAO) {
        this.DeliveryPartnerDAO = DeliveryPartnerDAO;
    }

    //Get Delivery partner by phone or email
    @GET
    @UnitOfWork
    public List<DeliveryPartner> findByMobileOrEmail(@QueryParam("data") Optional<String> data) {
        if (data.isPresent()) {
            return DeliveryPartnerDAO.findByMobileOrEmail(data.get());
        } else {
            return DeliveryPartnerDAO.findAll();
        }
    }

    //Get Delivery partner by id
    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<DeliveryPartner> findById(@PathParam("id") LongParam id) {
        return DeliveryPartnerDAO.findById(id.get());
    }

    //Get Active Delivery Partner
    @GET
    @Path("/Active")
    @UnitOfWork
    public List<DeliveryPartner> findActive() {
        return DeliveryPartnerDAO.findActive();
    }

    //Add Delivery Partner
    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDeliveryPartner(DeliveryPartner DeliveryPartner)
    {
        Response received=DeliveryPartnerDAO.addDeliveryPartner(DeliveryPartner);
        return received;
    }


}

