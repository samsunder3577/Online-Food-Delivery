package com.FOS.resources;

import com.FOS.dao.CustomerAddressDAO;
import com.FOS.entity.Customer;
import com.FOS.entity.CustomerAddress;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.hibernate.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/customer/{customerID}/customerAddress")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerAddressResource {
    private CustomerAddressDAO customerAddressDAO;
    public CustomerAddressResource(CustomerAddressDAO customerAddressDAO){
        this.customerAddressDAO=customerAddressDAO;
    }

    //Add Customer Address
    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(@PathParam("customerID") long customerID, CustomerAddress customerAddress)
    {
        System.out.println(customerID);
        Response received=customerAddressDAO.addCustomerAddress(customerID,customerAddress);
        return received;
    }

    //Get CustomerAddress By Id
    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<CustomerAddress> findById(@PathParam("id") LongParam id,@PathParam("customerID") long customerID) {


        return customerAddressDAO.findById(id.get(),customerID);
    }

    //Update Customer Address
    @PUT
    @Path("/{id}")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomerAddress(@PathParam("id") long id, CustomerAddress customerAddress)
    {
        customerAddress.setId(id);
        Response received=customerAddressDAO.updateCustomerAddress(id,customerAddress);
        return received;
    }

    //Update IsActive
    @PUT
    @Path("/{id}/{isActive}")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIsActive(@PathParam("id") long id, @PathParam("isActive") boolean isActive)
    {
        Response received=customerAddressDAO.updateIsActive(id,isActive);
        return received;
    }

}
