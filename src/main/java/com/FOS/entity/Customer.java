package com.FOS.entity;

import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
@DynamicUpdate
@NamedNativeQueries({
        @NamedNativeQuery(name = "com.FOS.entity.Customer.findAll",
                query = "select * from Customer c "),
        @NamedNativeQuery(name = "com.FOS.entity.Customer.findActive",
                query = "select * from Customer c where isActive=1"),
        @NamedNativeQuery(name = "com.FOS.entity.Customer.findByMobileOrEmail",
                query = "select * from Customer c where c.mobile = :data or c.emailID = :data"),
        @NamedNativeQuery(name = "com.FOS.entity.Customer.duplicateMobileOrEmail",
                query = "select * from Customer c where c.mobile= :mobile or c.emailID= :emailID"),
        @NamedNativeQuery(name = "com.FOS.entity.Customer.getId",
                query = "select * from Customer c where id=:id"),
        @NamedNativeQuery(name = "com.FOS.entity.Customer.deleteCustomer",
                query = "delete from Customer c where id= :id"),})

public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private long mobile;
    private String emailID;
    private boolean isActive;

   @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "customer")
    private Set<CustomerAddress> customerAddress;

   public Customer(){

   }

    public long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public long getMobile() { return mobile;}
    public String getEmailID(){ return emailID;}
    public boolean getIsActive(){ return isActive;}



    public void setId(long id) { this.id= id; }
    public void setFirstName(String firstName) { this.firstName=firstName; }
    public void setLastName(String lastName) { this.lastName=lastName; }
    public void setMobile(long mobile) { this.mobile=mobile;}
    public void setEmailID(String emailID){ this.emailID=emailID;}
    public void setIsActive(boolean isActive) { this.isActive=isActive;}
}