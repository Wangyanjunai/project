package com.imooc.project.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.imooc.project.dto.LoginDTO;
import com.imooc.project.entity.Account;
import com.imooc.project.dao.AccountMapper;
import com.imooc.project.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author jack
 * @since 2022-09-15
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public LoginDTO login(String username, String password) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setPath("redirect:/");
        Account account = lambdaQuery().eq(Account::getUsername, username).one();
        if (account == null) {
            loginDTO.setError("用户名不存在");
            return loginDTO;
        }
        MD5 md5 = new MD5(account.getSalt().getBytes());
        String digestHex = md5.digestHex(password);
        if (!digestHex.equals(account.getPassword())) {
            loginDTO.setError("密码错误");
            return loginDTO;
        }
        loginDTO.setPath("login/main");
        loginDTO.setAccount(account);
        return loginDTO;
    }
}
