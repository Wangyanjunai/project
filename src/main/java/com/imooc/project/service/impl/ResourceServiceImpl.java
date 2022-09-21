package com.imooc.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.project.entity.Resource;
import com.imooc.project.dao.ResourceMapper;
import com.imooc.project.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.project.vo.ResourceVO;
import com.imooc.project.vo.TreeVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author jack
 * @since 2022-09-15
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {


    /**
     * 根据角色id，查询该角色具有的资源
     *
     * @param roleId
     * @return
     */
    @Override
    public List<ResourceVO> listResourceByRoleId(Long roleId) {
        QueryWrapper<Resource> queryWrapper = Wrappers.query();
        queryWrapper.eq("rr.role_id", roleId).isNull("re.parent_id").orderByAsc("re.sort");
        List<ResourceVO> resourceVOS = this.baseMapper.listResource(queryWrapper);
        resourceVOS.forEach(r -> {
            Long resourceId = r.getResourceId();
            QueryWrapper<Resource> subWrapper = Wrappers.query();
            subWrapper.eq("rr.role_id", roleId).eq("re.parent_id", resourceId).orderByAsc("re.sort");
            List<ResourceVO> subResourceVOS = this.baseMapper.listResource(subWrapper);
            if (CollectionUtils.isNotEmpty(subResourceVOS)) {
                r.setSubs(subResourceVOS);
            }
        });
        return resourceVOS;
    }

    /**
     * 查询系统资源，供前端组件渲染
     *
     * @return
     */
    @Override
    public List<TreeVO> listResource() {
        LambdaQueryWrapper wrapper = Wrappers.<Resource>lambdaQuery().isNull(Resource::getParentId).orderByAsc(Resource::getSort);
        List<Resource> resources = list(wrapper);
        List<TreeVO> treeVOS = resources.stream().map(resource -> {
            TreeVO treeVO = new TreeVO();
            treeVO.setId(resource.getResourceId());
            treeVO.setTitle(resource.getResourceName());
            LambdaQueryWrapper subWrapper = Wrappers.<Resource>lambdaQuery().eq(Resource::getParentId, resource.getResourceId()).orderByAsc(Resource::getSort);
            List<Resource> subResources = list(subWrapper);
            if (CollectionUtils.isNotEmpty(subResources)) {
                List<TreeVO> children = subResources.stream().map(subResource -> {
                    TreeVO subTreeVO = new TreeVO();
                    subTreeVO.setId(subResource.getResourceId());
                    subTreeVO.setTitle(subResource.getResourceName());
                    return subTreeVO;
                }).collect(Collectors.toList());
                treeVO.setChildren(children);
            }
            return treeVO;
        }).collect(Collectors.toList());
        return treeVOS;
    }
}
