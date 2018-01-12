package org.smart4j.plugin.security;

import java.util.Set;

/**
 * Smart Security 接口
 * <br/>
 * 可在应用中实现该接口，或者在 smart.properties 文件中提供以下基于 SQL 的配置项：
 * <ul>
 *     <li>smart.plugin.security.jdbc.authc_query: 根据用户名获取密码</li>
 *     <li>smart.plugin.security.jdbc.roles_query: 根据用户名获取角色名集合</li>
 *     <li>smart.plugin.security.jdbc.permissions_query: 根据角色名获取权限名集合</li>
 * </ul>
 * @author wangjianchun
 * @create 2018/1/10
 */
public interface SmartSecurity {

    String getPassword(String username);

    Set<String> getRoleNameSet(String username);

    Set<String> getPermissionNameSet(String roleName);
}
