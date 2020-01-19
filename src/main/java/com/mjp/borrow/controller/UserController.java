package com.mjp.borrow.controller;

import com.mjp.borrow.base.ResultInfo;
import com.mjp.borrow.model.AccoutInfo;
import com.mjp.borrow.model.UserInfo;
import com.mjp.borrow.model.dto.LoginUser;
import com.mjp.borrow.model.dto.UserDto;
import com.mjp.borrow.service.IUserInfoService;
import com.mjp.borrow.utils.ControllerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>Description：用户</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/18 11:55
 */
@RestController
@Slf4j
@Api(tags = "用户")
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private IUserInfoService userInfoService;

    @PostMapping("addUser")
    @ApiOperation(value = "添加用户")
    public ResultInfo addUser(@RequestBody UserDto userDto) {
        log.info(userDto.toString());
        return ResultInfo.success(userInfoService.addUser(userDto));
    }

    @GetMapping("checkUser")
    @ApiOperation(value = "检验用户code是否存在")
    public ResultInfo checkUser(@RequestParam(name = "userCode") String userCode) {
        UserInfo userInfo = userInfoService.checkUser(userCode);
        if (userInfo == null) {
            return ResultInfo.success();
        } else {
            return ResultInfo.error("用户已存在！");
        }
    }

    @PostMapping("login")
    @ApiOperation(value = "登录")
    public ResultInfo login(@RequestBody LoginUser loginUser, HttpServletRequest request){
        AccoutInfo accoutInfo = userInfoService.login(loginUser);
        ControllerUtils.setCurAccount(request,accoutInfo);
        return ResultInfo.success(accoutInfo);
    }

    @PostMapping("update")
    @ApiOperation("更新信息")
    public  ResultInfo update(@RequestBody UserInfo userInfo ){
        userInfoService.updateUser(userInfo);
        return  ResultInfo.success();
    }
}
