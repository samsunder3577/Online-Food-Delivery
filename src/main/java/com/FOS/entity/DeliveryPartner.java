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

})
@DynamicUpdate
public class DeliveryPartner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private Long mobile;
    private String emailID;
    private int pincode;
    public boolean isAvailable;
    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "deliveryPartner")
    private Set<Orders> orders;


    public long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Long getMobile() { return mobile;}
    public Integer getPincode() { return pincode;}
    public String getEmailID(){ return emailID;}
    public boolean getIsActive(){ return isActive;}
    public boolean getIsAvailable(){ return isAvailable;}

    public void setId(long id) { this.id= id; }
    public void setFirstName(String firstName) { this.firstName=firstName; }
    public void setLastName(String lastName) { this.lastName=lastName; }
    public void setMobile(Long mobile) { this.mobile=mobile;}
    public void setEmailID(String emailID){ this.emailID=emailID;}
    public void setIsActive(boolean isActive) { this.isActive=isActive;}
    public void setIsAvailable(boolean isAvailable) { this.isAvailable=isAvailable;}
    public void setPincode(Integer pincode){this.pincode=pincode;}
}