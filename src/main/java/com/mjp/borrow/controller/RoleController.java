package com.mjp.borrow.controller;

import cn.hutool.core.date.DateTime;
import com.mjp.borrow.base.ResultInfo;
import com.mjp.borrow.model.Role;
import com.mjp.borrow.model.UserInfo;
import com.mjp.borrow.service.IRoleService;
import com.mjp.borrow.utils.ControllerUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description：角色controller</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/19 10:32
 */
@RestController
@ApiOperation("角色")
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;
    @PostMapping("/addRole")
    @ApiOperation("添加角色")
    public ResultInfo addRole(@RequestBody Role role, HttpServletRequest request){
        role.setCreateTime(DateTime.now());
        Role role1 =  roleService.addRole(role);
        return  ResultInfo.success(role1);
    }

    @GetMapping("deleteRole/{roleId}")
    @ApiOperation("删除角色")
    public  ResultInfo deleteRole(@PathVariable Long roleId){
        roleService.deleteRole(roleId);
        return  ResultInfo.success();
    }
}
