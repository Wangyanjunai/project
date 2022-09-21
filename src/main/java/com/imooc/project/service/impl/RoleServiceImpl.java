package com.imooc.project.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.imooc.project.dao.RoleResourceMapper;
import com.imooc.project.entity.Role;
import com.imooc.project.dao.RoleMapper;
import com.imooc.project.entity.RoleResource;
import com.imooc.project.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jack
 * @since 2022-09-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private RoleResourceMapper roleResourceMapper;

    @Autowired
    public void setRoleResourceMapper(RoleResourceMapper roleResourceMapper) {
        this.roleResourceMapper = roleResourceMapper;
    }

    /**
     * 新增角色以及角色具有的资源
     *
     * @param role 角色
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRole(Role role) {
        save(role);
        Long roleId = role.getRoleId();
        List<Long> resourceIds = role.getResourceIds();
        if (CollectionUtils.isNotEmpty(resourceIds)) {
            for (Long resourceId : resourceIds) {
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleId(roleId);
                roleResource.setResourceId(resourceId);
                this.roleResourceMapper.insert(roleResource);
            }
        }
        return false;
    }
}
