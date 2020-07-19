package com.FOS.entity;

import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "deliverypartner")
@NamedNativeQueries({
        @NamedNativeQuery(name = "com.FOS.entity.DeliveryPartner.findAll",
                query = "select * from DeliveryPartner c "),

        @NamedNativeQuery(name = "com.FOS.entity.DeliveryPartner.findActive",
                query = "select * from DeliveryPartner c where isActive=1"),

        @NamedNativeQuery(name = "com.FOS.entity.DeliveryPartner.findByMobileOrEmail",
                query = "select * from DeliveryPartner c where c.mobile = :data or c.emailID = :data"),

        @NamedNativeQuery(name = "com.FOS.entity.DeliveryPartner.duplicateMobileOrEmail",
                query = "select * from DeliveryPartner c where c.mobile= :mobile or c.emailID= :emailID"),

        @NamedNativeQuery(name = "com.FOS.entity.DeliveryPartner.updateDeliveryPartnerName",
                query = "update DeliveryPartner c set c.firstName= :firstName , c.lastName= :lastName where c.emailID= :data or c.mobile= :data"),

        @NamedNativeQuery(name = "com.FOS.entity.DeliveryPartner.updateDeliveryPartnerAvailable",
                query = "update DeliveryPartner c set c.isAvailable= :available where c.emailID= :data or c.mobile= :data"),

        @NamedNativeQuery(name = "com.FOS.entity.DeliveryPartner.updateDeliveryPartnerActive",
                query = "update DeliveryPartner c set c.isActive= :Active where c.emailID= :data or c.mobile= :data"),

        @NamedNativeQuery(name = "com.FOS.entity.DeliveryPartner.updateDeliveryPartnerInActive",
                query = "update DeliveryPartner c set c.isActive= :Active, c.isAvailable=:Active where c.emailID= :data or c.mobile= :data"),

        @NamedNativeQuery(name = "com.FOS.entity.DeliveryPartner.updateDeliveryPartnerPhone",
                query = "update DeliveryPartner c set c.mobile= :phone where c.emailID= :email"),

        @NamedNativeQuery(name = "com.FOS.entity.DeliveryPartner.updateDeliveryPartnerEmail",
                query = "update DeliveryPartner c set c.emailID= :email where c.mobile= :phone")



})
@DynamicUpdate
public class DeliveryPartner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private long mobile;
    private String emailID;
    private int pincode;
    public boolean isAvailable;
    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "deliveryPartner")
    private Set<Orders> orders;


    public long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public long getMobile() { return mobile;}
    public int getPincode() { return pincode;}
    public String getEmailID(){ return emailID;}
    public boolean getIsActive(){ return isActive;}
    public boolean getIsAvailable(){ return isAvailable;}

    public void setFirstName(String firstName) { this.firstName=firstName; }
    public void setLastName(String lastName) { this.lastName=lastName; }
    public void setMobile(long mobile) { this.mobile=mobile;}
    public void setEmailID(String emailID){ this.emailID=emailID;}
    public void setIsActive(boolean isActive) { this.isActive=isActive;}
    public void setIsAvailable(boolean isAvailable) { this.isAvailable=isAvailable;}
    public void setPincode(int pincode){this.pincode=pincode;}
}