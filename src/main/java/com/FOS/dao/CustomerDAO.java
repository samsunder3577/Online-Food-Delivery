package com.FOS.dao;

import com.FOS.entity.Customer;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.ws.rs.core.Response;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Optional;
public class CustomerDAO extends AbstractDAO<Customer> {

    public CustomerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    //Find All Customer
    public List<Customer> findAll() {
        return list((org.hibernate.query.Query<Customer>) namedQuery("com.FOS.entity.Customer.findAll"));
    }

    //Find Active Customer
    public List<Customer> findActive() {
        return list((org.hibernate.query.Query<Customer>) namedQuery("com.FOS.entity.Customer.findActive"));
    }

    //Find By Phone or Email
    public List<Customer> findByMobileOrEmail(String data) {
        return list((org.hibernate.query.Query<Customer>) namedQuery("com.FOS.entity.Customer.findByMobileOrEmail")
                .setParameter("data",data));
    }

    //Find by Id
    public Optional<Customer> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    //Add Customer
    public Response addCustomer(Customer customer) {
        Session session =this.currentSession();
        List<Customer> existingCustomer=
                list((org.hibernate.query.Query<Customer>) namedQuery("com.FOS.entity.Customer.duplicateMobileOrEmail")
                        .setParameter("mobile",customer.getMobile())
                        .setParameter("emailID",customer.getEmailID()));
        System.out.println(existingCustomer);
        if(existingCustomer.isEmpty())
        {
            session.save(customer);
            return Response.status(200).entity("Registered Successfully").build();
        }
        else
            return Response.status(400).entity("Phone or Email Already Exists").build();
    }

    //Update Customer
    public Response  updateCustomer(long id,Customer customer){
        Session session =currentSession();
        Customer oldCustomer=session.load(Customer.class,id);
        Query query=namedQuery("com.FOS.entity.Customer.getId").setParameter("id",customer.getId());
        if(query.uniqueResult()!=null) {
            if(customer.getFirstName()!=null)
            {
                oldCustomer.setFirstName(customer.getFirstName());
            }
            if(customer.getLastName()!=null)
            {
                oldCustomer.setLastName(customer.getLastName());
            }
            if(customer.getMobile()!=null)
            {
                oldCustomer.setMobile(customer.getMobile());
            }
            if(customer.getEmailID()!=null)
            {
                oldCustomer.setEmailID(customer.getEmailID());
            }
           return Response.status(200).entity("Updated Successfully").build();
        }
        else
            return Response.status(400).entity("No such Customer Present").build();
    }

    //Update IsActive
    public Response updateIsActive(long id,boolean isActive){
        Session session =currentSession();
        Customer customer=session.load(Customer.class,id);
        Query query=namedQuery("com.FOS.entity.Customer.getId").setParameter("id",id);
        if(query.uniqueResult()!=null) {
            customer.setIsActive(isActive);
            return Response.status(200).entity("Updated Successfully").build();
        }
        else
            return Response.status(400).entity("No such Customer Present").build();
    }

    //Delete Customer
    public Response deleteCustomer(long id)
    {
        Session session =currentSession();
        Customer customer=session.load(Customer.class,id);
        Query query=namedQuery("com.FOS.entity.Customer.getId").setParameter("id",id);
        if(query.uniqueResult()!=null) {
            //Query query1= namedQuery("com.FOS.entity.Customer.deleteCustomer").setParameter("id",id);
           session.delete(customer);
            return Response.status(200).entity("Deleted Successfully").build();
        }
        else
        return Response.status(400).entity("No such Customer Present").build();
    }

}