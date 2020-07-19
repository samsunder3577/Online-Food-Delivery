package com.FOS.entity;

import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
@DynamicUpdate

public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private Timestamp orderTime;

    private Timestamp deliveryTime;

    private float price;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "orders")
    private List<OrderItem> orderItemList;

   // @ManyToOne(cascade= CascadeType.MERGE,fetch = FetchType.EAGER)
  //  @JoinColumn(name="ordersItemID")
  //  private OrderItem orderItem;

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="customerId")
    private Customer customer;

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="customerAddressId")
    private CustomerAddress customerAddress;

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="restaurantId")
    private Restaurant restaurant;

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="deliveryPartnerId")
    private DeliveryPartner deliveryPartner;

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="orderStatusId")
    private OrderStatus orderStatus;

   // private List<OrderItem> orderItemList;

    public long getId(){return id;};
    public Timestamp getOrderTime(){return orderTime;}
    public Timestamp getDeliveryTime(){return deliveryTime;}
    public float getPrice(){return price;}
    public Customer getCustomer() { return customer; }
    public CustomerAddress getCustomerAddress() { return customerAddress; }
    public DeliveryPartner getDeliveryPartner() { return deliveryPartner;}
    public Restaurant getRestaurant() { return restaurant; }
    public OrderStatus getOrderStatus() { return orderStatus; }
    public List<OrderItem> getOrderItemList() { return orderItemList; }


    public void setCustomer(Customer customer) {this.customer = customer;}
    public void setCustomerAddress(CustomerAddress customerAddress) {this.customerAddress = customerAddress;}
    public void setDeliveryPartner(DeliveryPartner deliveryPartner) {this.deliveryPartner = deliveryPartner;}
    public void setDeliveryTime(Timestamp deliveryTime) {this.deliveryTime = deliveryTime;}
    public void setOrderStatus(OrderStatus orderStatus) {this.orderStatus = orderStatus;}
    public void setId(long id) {this.id = id;}
    public void setOrderTime(Timestamp orderTime) {this.orderTime = orderTime;}
    public void setPrice(float price) {this.price = price;}
    public void setRestaurant(Restaurant restaurant) {this.restaurant = restaurant; }
    public void setOrderItemList(List<OrderItem> orderItemList) {this.orderItemList = orderItemList; }

}
