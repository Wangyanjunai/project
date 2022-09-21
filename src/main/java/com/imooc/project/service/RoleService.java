package com.imooc.project.service;

import com.imooc.project.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author jack
 * @since 2022-09-15
 */
public interface RoleService extends IService<Role> {

    /**
     * 新增角色以及角色具有的资源
     *
     * @param role 角色
     * @return
     */
    boolean saveRole(Role role);

}
