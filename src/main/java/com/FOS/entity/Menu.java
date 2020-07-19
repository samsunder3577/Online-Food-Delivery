package com.FOS.entity;

import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "menu")
@DynamicUpdate

public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="RestaurantID")
    private Restaurant restaurant;

    public long getId(){return id;};
    public String getName(){return name;}
    public Restaurant getRestaurant(){return restaurant;}

    public void setId(long id){this.id=id;}
    public void setName(String name){this.name=name;}
    public void setRestaurant(Restaurant restaurant){this.restaurant=restaurant;}

}
