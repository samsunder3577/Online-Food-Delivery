package com.FOS;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class FoodOrderingConfiguration extends Configuration {
    @NotNull
    @Valid
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();
        @JsonProperty
        private String message;
        @JsonProperty
        private int messageRepetitions;
        @JsonProperty("database")
        public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
        }

        public String getMessage() {
            return "Message";
        }
        public int getMessageRepetitions() {
            return 10;
        }
}
