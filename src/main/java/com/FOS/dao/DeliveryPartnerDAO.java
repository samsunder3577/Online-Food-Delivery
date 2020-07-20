package com.FOS.dao;

import com.FOS.entity.Customer;
import com.FOS.entity.DeliveryPartner;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
public class DeliveryPartnerDAO extends AbstractDAO<DeliveryPartner> {

    public DeliveryPartnerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    //Find All DeliveryPartner
    public List<DeliveryPartner> findAll() {
        return list((org.hibernate.query.Query<DeliveryPartner>) namedQuery("com.FOS.entity.DeliveryPartner.findAll"));
    }

    //Find Active DeliveryPartner
    public List<DeliveryPartner> findActive() {
        return list((org.hibernate.query.Query<DeliveryPartner>) namedQuery("com.FOS.entity.DeliveryPartner.findActive"));
    }

    //Find By Phone or Email
    public List<DeliveryPartner> findByMobileOrEmail(String data) {
        return list((org.hibernate.query.Query<DeliveryPartner>) namedQuery("com.FOS.entity.DeliveryPartner.findByMobileOrEmail")
                .setParameter("data",data));
    }

    //Find by Id
    public Optional<DeliveryPartner> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    //Add DeliveryPartner
    public Response addDeliveryPartner(DeliveryPartner DeliveryPartner) {
        Session session =this.currentSession();
        List<DeliveryPartner> existingDeliveryPartner=
                list((org.hibernate.query.Query<DeliveryPartner>) namedQuery("com.FOS.entity.DeliveryPartner.duplicateMobileOrEmail")
                        .setParameter("mobile",DeliveryPartner.getMobile())
                        .setParameter("emailID",DeliveryPartner.getEmailID()));
        System.out.println(existingDeliveryPartner);
        if(existingDeliveryPartner.isEmpty())
        {
            session.save(DeliveryPartner);
            return Response.status(200).entity("Registered Successfully").build();
        }
        else
            return Response.status(400).entity("Phone or Email Already Exists").build();
    }

    //Update Delivery Partner
    public Response  updateDeliveryPartner(long id, DeliveryPartner deliveryPartner){
        Session session =currentSession();
        DeliveryPartner oldDeliveryPartner=session.load(DeliveryPartner.class,id);
        Query query=namedQuery("com.FOS.entity.DeliveryPartner.getId").setParameter("id",deliveryPartner.getId());
        if(query.uniqueResult()!=null) {
            if(deliveryPartner.getFirstName()!=null)
            {
                oldDeliveryPartner.setFirstName(deliveryPartner.getFirstName());
            }
            if(deliveryPartner.getLastName()!=null)
            {
                oldDeliveryPartner.setLastName(deliveryPartner.getLastName());
            }
            if(deliveryPartner.getMobile()!=null)
            {
                oldDeliveryPartner.setMobile(deliveryPartner.getMobile());
            }
            if(deliveryPartner.getEmailID()!=null)
            {
                oldDeliveryPartner.setEmailID(deliveryPartner.getEmailID());
            }
            if(deliveryPartner.getPincode()!=null)
            {
                oldDeliveryPartner.setPincode(deliveryPartner.getPincode());
            }
            return Response.status(200).entity("Updated Successfully").build();
        }
        else
            return Response.status(400).entity("No such Customer Present").build();
    }

    //Update IsActive
    public Response updateIsActive(long id,boolean isActive){
        Session session =currentSession();
        DeliveryPartner deliveryPartner=session.load(DeliveryPartner.class,id);
        Query query=namedQuery("com.FOS.entity.DeliveryPartner.getId").setParameter("id",id);
        if(query.uniqueResult()!=null) {
            deliveryPartner.setIsActive(isActive);
            return Response.status(200).entity("Updated Successfully").build();
        }
        else
            return Response.status(400).entity("No such Customer Present").build();
    }


}