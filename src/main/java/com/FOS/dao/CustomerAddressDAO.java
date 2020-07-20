package com.FOS.dao;

import com.FOS.entity.Customer;
import com.FOS.entity.CustomerAddress;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.ws.rs.core.Response;
import java.util.*;

public class CustomerAddressDAO extends AbstractDAO<CustomerAddress> {
    public CustomerAddressDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Response addCustomerAddress(long customerID,CustomerAddress customerAddress) {
        Session session =currentSession();
        System.out.println(customerID);

            Customer customer= session.load(Customer.class,customerID);
            customerAddress.setCustomer(customer);
            session.save(customerAddress);
            return Response.status(200).entity("Registered Successfully").build();
    }

    //Find by Id
    public Optional<CustomerAddress> findById(long id,long customerID) {

        Session session =currentSession();
        Customer customer= session.load(Customer.class,customerID);
        return Optional.ofNullable(get(id));
    }

    //Update Customer Address
    public Response  updateCustomerAddress(long id,CustomerAddress customerAddress){
        Session session =currentSession();
        CustomerAddress oldCustomerAddress=session.load(CustomerAddress.class,id);
        Query query=namedQuery("com.FOS.entity.CustomerAddress.getId")
                .setParameter("id",customerAddress.getId());
        if(query.uniqueResult()!=null) {
            if(customerAddress.getLatitude()!=null)
            {
                oldCustomerAddress.setLatitude(customerAddress.getLatitude());
            }
            if(customerAddress.getLongitude()!=null)
            {
                oldCustomerAddress.setLongitude(customerAddress.getLongitude());
            }
            if(customerAddress.getAddress()!=null)
            {
                oldCustomerAddress.setAddress(customerAddress.getAddress());
            }
            if(customerAddress.getPincode()!=null)
            {
                oldCustomerAddress.setPincode(customerAddress.getPincode());
            }
            return Response.status(200).entity("Updated Successfully").build();
        }
        else
            return Response.status(400).entity("No such Customer Present").build();
    }

    //Update IsActive
    public Response updateIsActive(long id,boolean isActive){
        Session session =currentSession();
        CustomerAddress customerAddress=session.load(CustomerAddress.class,id);
        Query query=namedQuery("com.FOS.entity.CustomerAddress.getId").setParameter("id",id);
        if(query.uniqueResult()!=null) {
            customerAddress.setIsActive(isActive);
            return Response.status(200).entity("Updated Successfully").build();
        }
        else
            return Response.status(400).entity("No such Customer Present").build();
    }
}
