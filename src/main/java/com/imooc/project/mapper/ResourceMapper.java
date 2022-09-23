package com.imooc.project.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.imooc.project.entity.Resource;
import com.imooc.project.vo.ResourceVO;
import com.imooc.project.vo.TreeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author Jimmy
 * @since 2020-12-31
 */
public interface ResourceMapper extends MyMapper<Resource> {

    List<ResourceVO> listResource(@Param(Constants.WRAPPER)Wrapper<Resource> wrapper);

    List<TreeVO> listResourceByRoleId(@Param(Constants.WRAPPER)Wrapper<Resource> wrapper, @Param("roleId") Long roleId);
}
