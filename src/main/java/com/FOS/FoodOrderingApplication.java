package com.FOS;

import com.FOS.dao.*;
import com.FOS.entity.*;
import com.FOS.resources.*;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jersey.jackson.JsonProcessingExceptionMapper;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class FoodOrderingApplication extends Application<FoodOrderingConfiguration>{
    private static HibernateBundle<FoodOrderingConfiguration> staticHibernateBundle=null;
    public static HibernateBundle<FoodOrderingConfiguration> getStaticHibernateBundle(){
        return staticHibernateBundle;
    }

    private final HibernateBundle<FoodOrderingConfiguration> hibernateBundle =
            new HibernateBundle<FoodOrderingConfiguration>(Customer.class,
                    CustomerAddress.class,
                    DeliveryPartner.class,
                    Restaurant.class,
                    Menu.class,
                    MenuItem.class,
                    Inventory.class,
                    Orders.class,
                    OrderStatus.class,
                    OrderItem.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(FoodOrderingConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    private static final Logger LOGGER = LoggerFactory.getLogger(FoodOrderingApplication.class);

    public static void main(String[] args) throws Exception {
        new FoodOrderingApplication().run(args);
    }

    @Override
    public String getName() {
        return "Database";
    }

    @Override
    public void initialize(final Bootstrap<FoodOrderingConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(FoodOrderingConfiguration foodOrderingConfiguration, Environment environment) throws Exception {
        LOGGER.info("Method App#run() called");
        for (int i=0; i < foodOrderingConfiguration.getMessageRepetitions(); i++) {
            System.out.println(foodOrderingConfiguration.getMessage());
        }
        final CustomerDAO customerDAO = new CustomerDAO(hibernateBundle.getSessionFactory());
        final CustomerAddressDAO customerAddressDAO= new CustomerAddressDAO(hibernateBundle.getSessionFactory());
        final RestaurantDAO restaurantDAO = new RestaurantDAO(hibernateBundle.getSessionFactory());
        final DeliveryPartnerDAO deliveryPartnerDAO = new DeliveryPartnerDAO(hibernateBundle.getSessionFactory());
        final MenuDAO menuDAO = new MenuDAO(hibernateBundle.getSessionFactory());
        final MenuItemDAO menuItemDAO=new MenuItemDAO(hibernateBundle.getSessionFactory());
        final InventoryDAO inventoryDAO=new InventoryDAO(hibernateBundle.getSessionFactory());
        final OrdersDAO ordersDAO=new OrdersDAO(hibernateBundle.getSessionFactory());
        final OrderStatusDAO orderStatusDAO=new OrderStatusDAO(hibernateBundle.getSessionFactory());
        final OrderItemDAO orderItemDAO=new OrderItemDAO(hibernateBundle.getSessionFactory());

        environment.jersey().register(new CustomerResource(customerDAO));
        environment.jersey().register(new RestaurantResource(restaurantDAO));
        environment.jersey().register(new CustomerAddressResource(customerAddressDAO));
        environment.jersey().register(new DeliveryPartnerResource(deliveryPartnerDAO));
        environment.jersey().register(new MenuResource(menuDAO));
        environment.jersey().register(new MenuItemResource(menuItemDAO));
        environment.jersey().register(new InventoryResource(inventoryDAO));
        environment.jersey().register(new OrdersResource(ordersDAO));
        environment.jersey().register(new OrderStatusResource(orderStatusDAO));
        environment.jersey().register(new OrderItemResource(orderItemDAO));
        environment.jersey().register(new JsonProcessingExceptionMapper(true));
    }

}
