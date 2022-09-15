package com.imooc.project.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.imooc.project.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.project.vo.ResourceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author jack
 * @since 2022-09-15
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 查询登录人的资源
     *
     * @param wrapper
     * @return
     */
    List<ResourceVO> listResource(@Param(Constants.WRAPPER) Wrapper<Resource> wrapper);
}
