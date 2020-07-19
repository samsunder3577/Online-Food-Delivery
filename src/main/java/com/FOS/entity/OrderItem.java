package com.FOS.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orderitem")
@DynamicUpdate
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long quantity;
    private float subtotal;

    @ManyToOne(cascade= CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="ordersId")
    private Orders orders;

    @ManyToOne(cascade= CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="menuItemId")
    private MenuItem menuItem;

    public long getId(){return id;}
    public long getQuantity(){return quantity;}
    public float getSubtotal(){return subtotal;}
    public Orders getOrders(){return orders;}
    public MenuItem getMenuItem(){return menuItem;}

    public void setId(long id){this.id=id;}
    public void setQuantity(long quantity){this.quantity=quantity;}
    public void setSubtotal(float subtotal){this.subtotal=subtotal;}
    public void setOrders(Orders orders){this.orders=orders;}
    public void setMenuItem(MenuItem menuItem){this.menuItem=menuItem;}

}
