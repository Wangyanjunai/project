package com.imooc.project.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.dto.LoginDTO;
import com.imooc.project.entity.Account;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author Jimmy
 * @since 2020-12-31
 */
public interface AccountService extends MyService<Account> {
    LoginDTO login(String username, String password);

    IPage<Account> accountPage(Page<Account> page, Wrapper<Account> wrapper);

    Account getAccountById(Long id);
}
