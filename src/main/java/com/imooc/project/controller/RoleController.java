package com.imooc.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.entity.Account;
import com.imooc.project.entity.Role;
import com.imooc.project.service.AccountService;
import com.imooc.project.service.ResourceService;
import com.imooc.project.service.RoleService;
import com.imooc.project.util.ResultUtil;
import com.imooc.project.vo.TreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Jimmy
 * @since 2020-12-31
 */
@Controller
@RequestMapping("/role")
@SuppressWarnings("deprecation")
public class RoleController {

    private RoleService roleService;

    private ResourceService resourceService;

    private AccountService accountService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    // 进入列表页
    @GetMapping("/toList")
    public String toList() {
        return "role/roleList";
    }

    // 查询方法
    @GetMapping("/list")
    @ResponseBody
    public R<Map<String, Object>> list(String roleName, Long page, Long limit) {

        LambdaQueryWrapper<Role> wrapper = Wrappers.<Role>lambdaQuery().like(StringUtils.isNotBlank(roleName),
                Role::getRoleName, roleName).orderByDesc(Role::getRoleId);

        Page<Role> rolePage = roleService.page(new Page<>(page, limit), wrapper);
        return ResultUtil.buildPageR(rolePage);
    }

    // 进入新增页
    @GetMapping("/toAdd")
    public String toAdd() {
        return "role/roleAdd";
    }

    // 新增角色
    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Role role) {
        return ResultUtil.buildR(roleService.saveRole(role));
    }


    // 进入更新页
    @GetMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        System.out.println("更新页 id = " + id);
        Role role = roleService.getById(id);
        model.addAttribute("role", role);
        return "role/roleUpdate";
    }

    // 修改
    @PutMapping
    @ResponseBody
    public R<Object> updateSubmit(@RequestBody Role role) {
        return ResultUtil.buildR(roleService.updateRole(role));
    }

    // 删除客户
    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id) {
        Integer count = accountService.lambdaQuery().eq(Account::getRoleId, id).count();
        if (count > 0) {
            return R.failed("有账号正拥有该角色");
        }

        return ResultUtil.buildR(roleService.removeById(id));
    }

    // 进入详情页
    @GetMapping("/toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {
        Role role = roleService.getById(id);
        model.addAttribute("role", role);
        return "role/roleDetail";
    }

    @GetMapping({"/listResource", "/listResource/{roleId}", "/listResource/{roleId}/{flag}"})
    @ResponseBody
    public R<List<TreeVO>> listResource(@PathVariable(required = false) Long roleId,
                                        @PathVariable(required = false) Integer flag) {
        return R.ok(resourceService.listResource(roleId, flag));
    }

}
