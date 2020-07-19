package com.FOS.entity;

import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="restaurant")
@NamedNativeQueries({
        @NamedNativeQuery(name = "com.FOS.entity.Restaurant.findAll",
                query = "select * from Restaurant r "),

        @NamedNativeQuery(name = "com.FOS.entity.Restaurant.findByMobileOrName",
                query = "select * from Restaurant r where r.phone = :data or r.name = :data"),

        @NamedNativeQuery(name = "com.FOS.entity.Restaurant.findActive",
                query = "select * from Restaurant r where isActive=1"),

        @NamedNativeQuery(name = "com.FOS.entity.Restaurant.duplicatePhone",
                query = "select * from Restaurant r where r.phone= :phone"),

        @NamedNativeQuery(name = "com.FOS.entity.Restaurant.updateRestaurantName",
                query = "update Restaurant r set r.name= :name where r.phone= :phone"),

        @NamedNativeQuery(name = "com.FOS.entity.Restaurant.updateRestaurantDescription",
                query = "update Restaurant r set r.description= :description where r.phone= :phone"),

        @NamedNativeQuery(name = "com.FOS.entity.Restaurant.updateRestaurantAddressAndPincode",
                query = "update Restaurant r set r.address =:address , r.pincode=:pincode where r.phone= :phone"),

        @NamedNativeQuery(name = "com.FOS.entity.Restaurant.updateRestaurantServiceable",
                query = "update Restaurant r set r.isServiceable =:serviceable where r.phone= :phone"),

        @NamedNativeQuery(name = "com.FOS.entity.Restaurant.updateRestaurantActive",
                query = "update Restaurant r set r.isActive =:active where r.phone= :phone"),

        @NamedNativeQuery(name = "com.FOS.entity.Restaurant.updateRestaurantInactive",
                query = "update Restaurant r set r.isActive =:active, r.isServiceable= :active where r.phone= :phone"),

        @NamedNativeQuery(name = "com.FOS.entity.Restaurant.updateRestaurantPhone",
                query = "update Restaurant r set r.phone =:newPhone where r.phone= :phone"),


})
@DynamicUpdate
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private String address;
    private int pincode;
    private boolean isServiceable;
    private long phone;
    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "restaurant")
    private Set<Menu> menu;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "restaurant")
    private Set<Inventory> inventory;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "restaurant")
    private Set<Orders> orders;

    public long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getLatitude() { return latitude;}
    public double getLongitude(){ return longitude;}
    public String getAddress(){ return address;}
    public int getPincode(){return pincode;}
    public boolean getIsServiceable(){return isServiceable;}
    public long getPhone(){return phone;}
    public boolean getIsActive(){return isActive;}

    public void setId() { this.id=id; }
    public void setName() { this.name= name; }
    public void setDescription() { this.description= description; }
    public void setLatitude() { this.latitude= latitude;}
    public void setLongitude(){ this.longitude= longitude;}
    public void setAddress(){ this.address= address;}
    public void setPincode(){this.pincode= pincode;}
    public void setIsServiceable(){this.isServiceable= isServiceable;}
    public void setPhone(){this.phone= phone;}
    public void setIsActive(){this.isActive= isActive;}

}