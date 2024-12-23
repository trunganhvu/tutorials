package org.anhvt.springbootmasterslavepostgre.config;

import org.anhvt.springbootmasterslavepostgre.enums.DataSourceType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // Way 1
        return DataSourceContextHolder.getDataSourceType();

        // Way 2
        // return isReadOnly.get() ? DataSourceType.READ : DataSourceType.WRITE;
    }

    private static final ThreadLocal<Boolean> isReadOnly = ThreadLocal.withInitial(() -> Boolean.FALSE);

    public static void setReadOnly() {
        isReadOnly.set(Boolean.TRUE);
    }

    public static void setWrite() {
        isReadOnly.set(Boolean.FALSE);
    }
}
