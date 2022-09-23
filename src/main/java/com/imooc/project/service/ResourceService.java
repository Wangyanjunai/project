package com.imooc.project.service;

import com.imooc.project.entity.Resource;
import com.imooc.project.vo.ResourceVO;
import com.imooc.project.vo.TreeVO;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author Jimmy
 * @since 2020-12-31
 */
public interface ResourceService extends MyService<Resource> {
    // 根据角色id, 查询该角色所具有的资源
    List<ResourceVO> listResourceByRoleId(Long roleId);

    // 查询系统资源, 供前端渲染
    List<TreeVO> listResource(Long roleId, Integer flag);

    HashSet<String> convert(List<ResourceVO> resourceVOS);
}
