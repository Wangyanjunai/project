package com.imooc.project.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.entity.Account;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 账号表 Mapper 接口
 * </p>
 *
 * @author Jimmy
 * @since 2020-12-31
 */
public interface AccountMapper extends MyMapper<Account> {
    /**
     * 分页查询账号
     */
    IPage<Account> accountPage(Page<Account> page, @Param(Constants.WRAPPER)Wrapper<Account> wrapper);

    /*** 功能描述:根据accountId查询账号信息
    * @param:
    * @return:
    * @auther:
    * @date:
    */
    Account selectAccountById(Long id);

}
