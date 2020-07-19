package com.FOS.dao;

import com.FOS.entity.DeliveryPartner;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Query;
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


}