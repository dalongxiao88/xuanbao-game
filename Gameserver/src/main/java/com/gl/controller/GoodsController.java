package com.gl.controller;

import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import come.tool.Stall.AssetUpdate;
import org.come.entity.Goodstable;
import java.util.List;
import java.util.stream.Collectors;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.gl.model.Param;
import org.springframework.web.bind.annotation.GetMapping;
import com.gl.token.UserToken;
import com.gl.service.ResultFactory;
import com.gl.service.GoodsService;
import com.gl.model.Result;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController
{

     // 获得所有物品

    @UserToken
    @GetMapping({ "/api/goods" })
    public Result getGoods(HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        GoodsService service = new GoodsService();
        return ResultFactory.success(service.goodsMap());
    }
    //生成兑换码
    @UserToken
    @PostMapping({ "/api/exchange" })
    public Result exchange(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        GoodsService service = new GoodsService();
        if (service.createExchange(param)) {
            return ResultFactory.success(null);
        }
        return ResultFactory.fail("推广码生成失败");
    }
    //查询兑换码
    @UserToken
    @RequestMapping({ "/api/exchanges" })
    public Result getExchange(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        GoodsService service = new GoodsService();
        return ResultFactory.success(service.getExchange(param));
    }
    //发送物品
    @UserToken
    @PostMapping({ "/api/sendgoods" })
    public Result sendgoods(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        GoodsService service = new GoodsService();
        if (service.sendGoods(param)) {
            return ResultFactory.success(null);
        }
        return ResultFactory.fail("物品发送失败，请确认玩家或物品是否存在");
    }
    
    @UserToken
    @GetMapping({ "/api/good/getUserGood" })
    public Result getUserGood(BigDecimal roleId, String roleName, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        List<Goodstable> goodTableList = AllServiceUtil.getGoodsTableService().getGoodsByRoleID(roleId);
        return ResultFactory.success(goodTableList.stream().collect(Collectors.toList()));//==0修改背包物品 1修改穿上物品
    }
    
    @UserToken
    @PostMapping({ "/api/good/updGood" })
    public Result updUserGood(Goodstable vo, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        Goodstable goodstable = AllServiceUtil.getGoodsTableService().getGoodsByRgID(vo.getRgid());
        goodstable.setValue(vo.getValue());
        goodstable.setQhv(vo.getQhv());
        goodstable.setQht(vo.getQht());
        goodstable.setType(vo.getType());
        goodstable.setGoodsname(vo.getGoodsname());
        goodstable.setSkin(vo.getSkin());
        goodstable.setUsetime(vo.getUsetime());
        AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(100);
        assetUpdate.setGood(goodstable);
        assetUpdate.setMsg("属性值已更改#24#24#24");
        ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext)GameServer.getRoleNameMap().get(vo.getEquipShow());
        SendMessage.sendMessageToSlef(channelHandlerContext, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        return ResultFactory.success("属性值生效");
    }
}
