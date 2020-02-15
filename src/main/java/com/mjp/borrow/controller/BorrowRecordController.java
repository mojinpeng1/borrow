package com.mjp.borrow.controller;

import cn.hutool.core.date.DateTime;
import com.mjp.borrow.base.ResultInfo;
import com.mjp.borrow.base.exception.CommonException;
import com.mjp.borrow.model.BorrowRecord;
import com.mjp.borrow.model.UserInfo;
import com.mjp.borrow.service.IBorrowRecordService;
import com.mjp.borrow.service.IUserRoleService;
import com.mjp.borrow.utils.ControllerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description：借用controller</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/19 11:28
 */
@Api(tags = "借用")
@RestController
@RequestMapping("/borrow")
public class BorrowRecordController {

    @Resource
    private IBorrowRecordService borrowRecordService;

    @Resource
    private IUserRoleService userRoleService;

    @PostMapping("addOne")
    @ApiOperation("新增一条借用记录")
    // TODO 该处需要修改(比如借的产品现在是什么状态)
    public ResultInfo addOne(@RequestBody BorrowRecord borrowRecord, HttpServletRequest request) {
        UserInfo user = ControllerUtils.getCurAccount(request).getUser();
        if (borrowRecord == null) {
            throw new CommonException("参数异常");
        }
        borrowRecord.setBorrowMan(user);
        borrowRecord.setBorrowTime(DateTime.now());
        borrowRecord.setStatus(BorrowRecord.STATUS_CREATED);
        return ResultInfo.success(borrowRecordService.addOne(borrowRecord));
    }

    @PostMapping("queryBorrow")
    @ApiOperation("查询借用记录")
    // TODO 有时间需要做条件查询
    public ResultInfo queryBorrow(HttpServletRequest request) {
        //如果管理员角色查询所有,如果是普通用户只能查询自己的
        UserInfo user = ControllerUtils.getCurAccount(request).getUser();
        boolean admin = userRoleService.isAdmin(user);
        if (admin) {
            return ResultInfo.success(borrowRecordService.queryAll());
        } else {
            return ResultInfo.success(borrowRecordService.queryBorrow(user));
        }
    }

    @PostMapping("queryBorrowRecord")
    @ApiOperation("查询以前的借用记录")
    public ResultInfo queryBorrowRecord(HttpServletRequest request) {
        return ResultInfo.success(borrowRecordService.queryBorrowRecord());
    }

    @GetMapping("closeBorrow")
    @ApiOperation("关闭借用")
    public ResultInfo closeBorrow(@RequestParam Long brid, HttpServletRequest request) {
        UserInfo user = ControllerUtils.getCurAccount(request).getUser();
        borrowRecordService.closeBorrow(brid, user);
        return ResultInfo.success();
    }

    @GetMapping("confirm")
    @ApiOperation("确认借用")
    public ResultInfo confirm(@RequestParam Long brid, HttpServletRequest request) {
        UserInfo user = ControllerUtils.getCurAccount(request).getUser();
        borrowRecordService.confirm(brid, user);
        return ResultInfo.success();
    }

    @GetMapping("confirmOut")
    @ApiOperation("确认借出")
    public ResultInfo confirmOut(@RequestParam Long brid, HttpServletRequest request) {
        UserInfo user = ControllerUtils.getCurAccount(request).getUser();
        borrowRecordService.confirmOut(brid, user);
        return ResultInfo.success();
    }

    @GetMapping("reback")
    @ApiOperation("归还")
    public ResultInfo reback(@RequestParam Long brid, HttpServletRequest request) {
        UserInfo user = ControllerUtils.getCurAccount(request).getUser();
        borrowRecordService.reback(brid, user);
        return ResultInfo.success();
    }


}
