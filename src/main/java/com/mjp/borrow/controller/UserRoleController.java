package com.mjp.borrow.controller;

import cn.hutool.core.date.DateTime;
import com.mjp.borrow.base.ResultInfo;
import com.mjp.borrow.model.UserInfo;
import com.mjp.borrow.model.UserRole;
import com.mjp.borrow.model.dto.UserRoleConfig;
import com.mjp.borrow.service.IUserRoleService;
import com.mjp.borrow.utils.ControllerUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description：用户角色配置</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/19 10:41
 */
@RestController
@ApiOperation("用户角色配置")
@RequestMapping("userRole")
public class UserRoleController  {
    @Resource
    private IUserRoleService userRoleService;

    @ApiOperation("配置用户")
    @PostMapping("configUser")
    public ResultInfo configUser(@RequestBody UserRoleConfig userRoleConfig, HttpServletRequest request){
        UserInfo user = ControllerUtils.getCurAccount(request).getUser();
        if (userRoleConfig != null){
            List<UserRole> userRoles = trans2UserRole(userRoleConfig, user);
            userRoleService.configUser(userRoles);
        }
        return  ResultInfo.success();
    }

    private List<UserRole> trans2UserRole(UserRoleConfig userRoleConfig, UserInfo configMan){
        List<UserRole> userRoles = new ArrayList<>(16);
        userRoleConfig.getUserInfoList().forEach( item -> {
            UserRole userRole = new UserRole();
            userRole.setConfigMan(configMan);
            userRole.setRole(userRoleConfig.getRole());
            userRole.setUserInfo(item);
            userRole.setConfigTime(DateTime.now());
            userRoles.add(userRole);
        } );
        return  userRoles;

    }
}
