package com.imooc.project.service;

import com.imooc.project.entity.Role;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Jimmy
 * @since 2020-12-31
 */
public interface RoleService extends MyService<Role> {

    boolean saveRole(Role role);

    boolean updateRole(Role role);

}
