package com.imooc.project.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.entity.Account;
import com.imooc.project.entity.Role;
import com.imooc.project.query.AccountQuery;
import com.imooc.project.service.AccountService;
import com.imooc.project.service.RoleService;
import com.imooc.project.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 账号表 前端控制器
 * </p>
 *
 * @author jack
 * @since 2022-09-15
 */
@Slf4j
@Controller
@RequestMapping("account")
@SuppressWarnings("deprecation")
public class AccountController {

    private AccountService accountService;

    private RoleService roleService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 进入列表页
     *
     * @return
     */
    @GetMapping("toList")
    public String toList() {
        return "account/accountList";
    }

    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(AccountQuery query) {
        QueryWrapper<Account> wrapper = Wrappers.<Account>query();
        wrapper.like(StringUtils.isNotBlank(query.getRealName()), "a.real_name", query.getRealName())
                .like(StringUtils.isNotBlank(query.getEmail()), "a.email", query.getEmail())
                .orderByDesc("a.account_id").eq("a.deleted", 0);
        String createTimeRange = query.getCreateTimeRange();
        if (StringUtils.isNotBlank(createTimeRange)) {
            String[] timeArray = createTimeRange.split(" - ");
            wrapper.ge("a.create_time", timeArray[0]).le("a.create_time", timeArray[1]);
            wrapper.eq("a.deleted", 0).orderByDesc("a.account_id");
        }
        return ResultUtil.buildPageR(this.accountService.accountPage(new Page<Account>(query.getPage(), query.getLimit()), wrapper));
    }

    /**
     * 进入新增页
     *
     * @return
     */
    @GetMapping("toAdd")
    public String toAdd(Model model) {
        List<Role> roles = this.roleService.list(Wrappers.<Role>lambdaQuery().orderByAsc(Role::getRoleId));
        model.addAttribute("roles", roles);
        return "account/accountAdd";
    }

    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Account account) {
        setPasswordAndSalt(account);
        return ResultUtil.buildR(this.accountService.save(account));
    }


    /**
     * 进入修改页
     *
     * @return
     */
    @GetMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        List<Role> roles = this.roleService.list(Wrappers.<Role>lambdaQuery().orderByAsc(Role::getRoleId));
        Account account = this.accountService.getById(id);
        model.addAttribute("roles", roles);
        model.addAttribute("account", account);
        return "account/accountUpdate";
    }

    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody Account account) {
        if (StringUtils.isNotBlank(account.getPassword())) {
            setPasswordAndSalt(account);
        } else {
            account.setPassword(null);
        }
        return ResultUtil.buildR(this.accountService.updateById(account));
    }

    /**
     * 进入详情页
     *
     * @return
     */
    @GetMapping("toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {
        Account account = this.accountService.getAccountById(id);
        log.info("account={}", account);
        model.addAttribute("account", account);
        return "account/accountDetail";
    }

    /**
     * 逻辑删除
     *
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id, HttpSession session) {
        Object account = session.getAttribute("account");
        if (account instanceof Account) {
            if (Objects.equals(((Account) account).getAccountId(), id)) {
                return R.failed("不能删除自己哦！");
            }
        }
        return ResultUtil.buildR(this.accountService.removeById(id));
    }

    /**
     * 重名验证
     *
     * @param username  用户名
     * @param accountId 账号id
     * @return
     */
    @GetMapping({"/{username}", "/{username}/{accountId}"})
    @ResponseBody
    public R<Object> checkUsername(@PathVariable String username, @PathVariable(required = false) Long accountId) {
        Integer count = accountService.lambdaQuery().eq(Account::getUsername, username).ne(accountId != null, Account::getAccountId, accountId).count();
        return R.ok(count);
    }

    /**
     * 设置加密密码和加密盐
     *
     * @param account
     */
    private void setPasswordAndSalt(Account account) {
        String password = account.getPassword();
        String salt = UUID.fastUUID().toString().replaceAll("-", "");
        MD5 md5 = new MD5(salt.getBytes());
        account.setSalt(salt);
        account.setPassword(md5.digestHex(password));
    }
}
