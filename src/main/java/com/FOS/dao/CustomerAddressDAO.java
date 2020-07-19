package com.FOS.dao;

import com.FOS.entity.Customer;
import com.FOS.entity.CustomerAddress;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
        System.out.println(customer.getFirstName());
        return Optional.ofNullable(get(id));
    }
}
