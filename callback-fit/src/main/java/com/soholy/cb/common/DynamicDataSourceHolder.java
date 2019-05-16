package com.soholy.cb.common;

/**
 * DynamicDataSourceHolder类用于切换要操作的数据源
 */
public class DynamicDataSourceHolder {

    public enum DbType {
        DEFAUALT("defaultDataSource"), WIFI("wifidataSource");

        private String dbType;

        DbType(String dbType) {
            this.dbType = dbType;
        }

        public String getDbType() {
            return dbType;
        }

        public void setDbType(String dbType) {
            this.dbType = dbType;
        }
    }

    private static final ThreadLocal<String> THREAD_DATA_SOURCE = new ThreadLocal();

    public static String getDataSource() {
        return THREAD_DATA_SOURCE.get();
    }

    public static void setWifiDataSource() {
        setDataSource(DbType.WIFI);
    }

    public static void setDefaultDataSouce() {
        THREAD_DATA_SOURCE.set(DbType.DEFAUALT.getDbType());
    }

    public static void setDataSource(DbType dbType) {
        THREAD_DATA_SOURCE.set(dbType.getDbType());
    }

    public static void clearDataSource() {
        THREAD_DATA_SOURCE.remove();
    }

}
