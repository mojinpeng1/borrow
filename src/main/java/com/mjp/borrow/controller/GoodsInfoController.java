package com.mjp.borrow.controller;

import cn.hutool.core.date.DateTime;
import com.mjp.borrow.base.ResultInfo;
import com.mjp.borrow.base.exception.CommonException;
import com.mjp.borrow.model.AccoutInfo;
import com.mjp.borrow.model.GoodsInfo;
import com.mjp.borrow.model.UserInfo;
import com.mjp.borrow.service.IGoodsInfoService;
import com.mjp.borrow.utils.ControllerUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Description：物品</p>
 *
 * @author mojinpeng
 * @version 1.0
 * @date 2020/1/18 17:49
 */
@RestController
@Slf4j
@RequestMapping(value = "/goods")
@Api(tags = "物品管理")
public class GoodsInfoController {
    @Resource
    private IGoodsInfoService goodsInfoService;

    @PostMapping("/addGoods")
    @ApiOperation(value = "新建物品")
    public ResultInfo addGoods(@RequestBody GoodsInfo goodsInfo, HttpServletRequest request){

        AccoutInfo curAccount = ControllerUtils.getCurAccount(request);
        UserInfo user = curAccount.getUser();
        goodsInfo.setCreateMan(user);
        goodsInfo.setUpdateMan(user);
        goodsInfo.setLocationMan(user);
        goodsInfo.setCreateTime(DateTime.now());
        return  ResultInfo.success(goodsInfoService.addGoods(goodsInfo));
    }

    @GetMapping("checkGoods")
    @ApiOperation(value = "检查物品编码是否重复")
    public ResultInfo checkGoods(@RequestParam String goodsCode){
        if(StringUtils.isNotBlank(goodsCode)) {
            if (goodsInfoService.checkGoods(goodsCode)){
                return ResultInfo.success();
            }else{
                return ResultInfo.error("该编码已经存在!");
            }
        }else {
            throw new CommonException("参数异常!");
        }
    }

    @GetMapping("deleteGoods/{goodsId}")
    @ApiOperation(value = "删除物品")
    public  ResultInfo deleteGoods(@PathVariable long goodsId){
        goodsInfoService.deleteGoods(goodsId);
        return ResultInfo.success();
    }

    @PostMapping("update")
    @ApiOperation("更新物品信息")
    public ResultInfo update(@RequestBody GoodsInfo goodsInfo,HttpServletRequest request){
        goodsInfo.setUpdateMan(ControllerUtils.getCurAccount(request).getUser());
        goodsInfo.setUpdatetime(DateTime.now());
        goodsInfoService.updateGoods(goodsInfo);
        return  ResultInfo.success();
    }
}
