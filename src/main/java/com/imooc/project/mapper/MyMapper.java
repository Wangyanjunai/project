package com.imooc.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/*** 功能描述: 自定义Mapper
* @param:
* @return:
* @auther:
* @date:
*/
public interface MyMapper<T> extends BaseMapper<T> {

    /*** 功能描述: 逻辑删除带自动填充功能
    * @param:
    * @return:
    * @auther:
    * @date:
    */
    int deleteByIdWithFill(T entity);
}
