package org.geekbang.ecommerce.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> DATA_SOURCE = new ThreadLocal<>();

    public static String getDataSource() {
        return DATA_SOURCE.get();
    }

    public static void setDataSource(String dataSource) {
        DATA_SOURCE.set(dataSource);
    }

    public static void clearDataSource() {
        DATA_SOURCE.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSource.getDataSource();
    }

}
