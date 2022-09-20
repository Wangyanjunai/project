package com.imooc.project.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.project.dto.LoginDTO;
import com.imooc.project.entity.Account;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author jack
 * @since 2022-09-15
 */
public interface AccountService extends IService<Account> {

    LoginDTO login(String username, String password);

    /**
     * 分页查询账号
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Account> accountPage(Page<Account> page, Wrapper<Account> wrapper);
}
