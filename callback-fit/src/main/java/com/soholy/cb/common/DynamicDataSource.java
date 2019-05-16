package com.soholy.cb.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
  protected Object determineCurrentLookupKey() { return DynamicDataSourceHolder.getDataSource(); }
}
