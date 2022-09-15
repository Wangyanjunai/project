package com.imooc.project.service;

import com.imooc.project.dto.LoginDTO;
import com.imooc.project.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
