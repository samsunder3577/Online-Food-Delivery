package com.FOS.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="orderstatus")
@DynamicUpdate
public class OrderStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String status;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "orderStatus")
    private Set<Orders> orders;

    public long getId(){ return id;}
    public String getStatus(){return status;}

    public void setId(long id){this.id=id;}
    public void setStatus(String status){this.status=status;}
}
