package com.FOS.resources;

import com.FOS.dao.CustomerDAO;
import com.FOS.entity.Customer;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.persistence.PostUpdate;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private CustomerDAO customerDAO;

    public CustomerResource(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    //Get Customers by Phone ir Email
    @GET
    @UnitOfWork
    public List<Customer> findByMobileOrEmail(@QueryParam("data") Optional<String> data) {
        if (data.isPresent()) {
            return customerDAO.findByMobileOrEmail(data.get());
        } else {
            return customerDAO.findAll();
        }
    }

    //Get Customers By Id
    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Customer> findById(@PathParam("id") LongParam id) {
        return customerDAO.findById(id.get());
    }

    //Get Active Customers
    @GET
    @Path("/active")
    @UnitOfWork
    public List<Customer> findActive() {
        return customerDAO.findActive();
    }

    //Add Customer
    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer)
    {
        Response received=customerDAO.addCustomer(customer);
        return received;
    }

    //Update Customer
    @PUT
    @Path("/{id}")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") long id, Customer customer)
    {
        customer.setId(id);
        Response received=customerDAO.updateCustomer(id,customer);
        return received;
    }

    //Update IsActive
    @PUT
    @Path("/{id}/{isActive}")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIsActive(@PathParam("id") long id, @PathParam("isActive") boolean isActive)
    {
        Response received=customerDAO.updateIsActive(id,isActive);
        return received;
    }

    //Delete Customer
    @DELETE
    @Path("/{id}")
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") long id)
    {
        Response received=customerDAO.deleteCustomer(id);
        return received;
    }


}

