package org.smart4j.plugin.security;

/**
 * @author wangjianchun
 * @create 2018/1/12
 */
public interface SecurityConstant {

    String REALMS = "smart.plugin.security.realms";
    String REALMS_JDBC = "jdbc";
    String REALMS_CUSTOM = "custom";

    String SMART_SECURITY = "smart.plugin.security.custom.class";

    String JDBC_AUTHC_QUERY = "smart.plugin.security.jdbc.authc.query";
    String JDBC_ROLES_QUERY = "smart.plugin.security.jdbc.roles_query";
    String JDBC_PERMISSIONS_QUERY = "smart.plugin.security.jdbc.permissions_query";

    String CACHEABLE = "smart.plugin.security.cacheable";
}
