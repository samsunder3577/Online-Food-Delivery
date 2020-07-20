package com.FOS.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "inventory")
@DynamicUpdate
@NamedNativeQueries({
        @NamedNativeQuery(name = "com.FOS.entity.Inventory.getId",
                query = "select * from Inventory c where id=:id"),
})
public class Inventory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="RestaurantID")
    private Restaurant restaurant;

    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="MenuItemID")
    private MenuItem menuItem;

    private long quantity;

    public long getId(){return id;};
    public long getQuantity(){return quantity;}

    public void setId(long id){this.id=id;}
    public void setQuantity(long quantity){this.quantity=quantity;}
    public void setRestaurant(Restaurant restaurant){this.restaurant=restaurant;}
    public void setMenuItem(MenuItem menuItem){this.menuItem=menuItem;}

}
