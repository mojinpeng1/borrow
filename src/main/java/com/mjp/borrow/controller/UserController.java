package com.mjp.borrow.controller;

import cn.hutool.core.io.IoUtil;
import com.mjp.borrow.base.ResultInfo;
import com.mjp.borrow.model.AccoutInfo;
import com.mjp.borrow.model.UserInfo;
import com.mjp.borrow.model.dto.LoginUser;
import com.mjp.borrow.model.dto.UserDto;
import com.mjp.borrow.service.IUserInfoService;
import com.mjp.borrow.utils.ControllerUtils;
import com.mjp.borrow.utils.MinioUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

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

    @PostMapping("uploadPic")
    @ApiOperation("上传头像")
    public  ResultInfo uploadPic(MultipartFile file,HttpServletRequest request){
        MinioUtil minioUtil = new MinioUtil();
        boolean pic = minioUtil.uploadToBucket(null, file, "pic");
        AccoutInfo curAccount = ControllerUtils.getCurAccount(request);
        UserInfo user = curAccount.getUser();
        if (pic){
            user.setPortrait(file.getOriginalFilename());
            userInfoService.updateUser(user);
        }
        return ResultInfo.success(user);
    }

    @GetMapping("getPic")
    @ApiOperation("获取头像信息")
    public  ResultInfo getPic(HttpServletRequest request, HttpServletResponse response) throws IOException {

        UserInfo user = ControllerUtils.getCurAccount(request).getUser();
        if(StringUtils.isBlank(user.getPortrait())){
            return null;
        }
        MinioUtil minioUtil = new MinioUtil();
        InputStream pic = minioUtil.getObject(null, "pic", user.getPortrait());
        response.reset();
        IoUtil.copy(pic,response.getOutputStream(),IoUtil.DEFAULT_BUFFER_SIZE);
        return null;
    }

}
