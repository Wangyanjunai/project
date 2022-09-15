package com.imooc.project.service;

import com.imooc.project.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.project.vo.ResourceVO;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author jack
 * @since 2022-09-15
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 根据角色id，查询该角色具有的资源
     *
     * @param roleId
     * @return
     */
    List<ResourceVO> listResourceByRoleId(Long roleId);
}
