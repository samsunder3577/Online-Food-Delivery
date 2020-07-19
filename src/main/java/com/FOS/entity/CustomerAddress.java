package com.FOS.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="customeraddress")

@DynamicUpdate
public class CustomerAddress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double latitude;
    private double longitude;
    private String address;
    private int pincode;
    private boolean isActive;

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="customerId")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "customerAddress")
    private Set<Orders> orders;

    public CustomerAddress(){
    }

    public long getId(){return id;}
    public double getLatitude(){return latitude;}
    public double getLongitude(){return longitude;}
    public String getAddress(){return address;}
    public int getPincode(){return pincode;}
    public boolean getIsActive(){return isActive;}
    public Customer getCustomer(){return customer;}

    public void setId(long id){this.id=id;}
    public void setLatitude(double latitude){this.latitude=latitude;}
    public void setLongitude(double longitude){this.longitude=longitude;}
    public void setAddress(String address){this.address=address;}
    public void setPincode(int pincode){this.pincode=pincode;}
    public void setIsActive(boolean isActive){this.isActive=isActive;}
    public void setCustomer(Customer customer){this.customer=customer;}

}
