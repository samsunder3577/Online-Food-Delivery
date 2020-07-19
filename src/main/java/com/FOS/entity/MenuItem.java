package com.FOS.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="menuitem")
@DynamicUpdate
public class MenuItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private float price;
    private String type;
    private long image;
    private boolean isActive;

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="menuId")
    private Menu menu;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "menuItem")
    private List<Inventory> inventory;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "menuItem")
    private Set<OrderItem> orderItem;

    public long getId(){return id;}
    public String getName(){return this.name;}
    public String getDescription(){return description;}
    public float getPrice(){return price;}
    public String getType(){return type;}
    public boolean getIsActive(){return isActive;}

    public void setId(long id){this.id=id;}
    public void setDescription(String description){this.description=description;}
    public void setPrice(float price){this.price=price;}
    public void setType(String type){this.type=type;}
    public void setName(String name){this.name=name;}
    public void setActive(boolean isActive){this.isActive=isActive;}
    public void setMenu(Menu menu){this.menu=menu;}
}
