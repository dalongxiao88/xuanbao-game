package com.gl.controller;

import java.util.HashMap;
import org.come.entity.UserTable;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import java.math.BigDecimal;
import org.come.entity.Goodsrecord;
import com.github.pagehelper.PageInfo;
import org.come.until.AllServiceUtil;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import com.gl.token.UserToken;
import com.gl.service.ResultFactory;
import com.gl.service.PlayerService;
import com.gl.model.Result;
import javax.servlet.http.HttpServletRequest;
import com.gl.model.Param;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController
{
    //查询所有玩家角色
    @UserToken
    @PostMapping({ "/api/role" })
    public Result roles(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PlayerService service = new PlayerService();
        return ResultFactory.success(service.getRole(param));
    }
    //查询所有玩家角色（新）
    @UserToken
    @PostMapping({ "/api/roleList" })
    public Result roleList(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        Param param = new Param();
        param.setPageNum(Integer.parseInt(params.get("PageNum").toString()));
        param.setPageSize(Integer.parseInt(params.get("PageSize").toString()));
        param.setValue1(params.get("Value1").toString());
        param.setValue2(params.get("Value2").toString());
        PlayerService service = new PlayerService();
        return ResultFactory.success(service.getRole(param));
    }
    //修改解锁码(新)
    @UserToken
    @PostMapping({ "/api/updatelockpwd" })
    public Result updatelockpwd(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        Param param = new Param();
        param.setValue1(params.get("value1").toString());
        param.setValue2(params.get("value2").toString());
        PlayerService service = new PlayerService();
        if (service.editLockPassword(param)) {
            return ResultFactory.success(null);
        }
        return ResultFactory.fail("操作失败");
    }
    //修改解锁码
    @UserToken
    @PostMapping({ "/api/lockpwd" })
    public Result lockpwd(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PlayerService service = new PlayerService();
        if (service.editLockPassword(param)) {
            return ResultFactory.success(null);
        }
        return ResultFactory.fail("操作失败");
    }
    //设置GM特权
    @UserToken
    @PostMapping({ "/api/updateGMRole" })
    public Result updateGMRole(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PlayerService service = new PlayerService();
        if (service.updateGMRole(param)) {
            return ResultFactory.success(null);
        }
        return ResultFactory.fail("操作失败");
    }
    //删除角色
    @UserToken
    @PostMapping({ "/api/deleterole" })
    public Result deleterole(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PlayerService service = new PlayerService();
        if (service.deleteRolePwdForRid(param)) {
            return ResultFactory.success(null);
        }
        return ResultFactory.fail("操作失败");
    }
    //修改密码
    @UserToken
    @PostMapping({ "/api/updatePwdUser" })
    public Result updatePwdUser(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PlayerService service = new PlayerService();
        if (service.updatePwdUserForRid(param)) {
            return ResultFactory.success(null);
        }
        return ResultFactory.fail("操作失败");
    }
    //修改角色坐骑
    @UserToken
    @PostMapping({ "/api/updateMount" })
    public Result updateMount(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PlayerService service = new PlayerService();
        if (service.updateMountForRid(param)) {
            return ResultFactory.success(null);
        }
        return ResultFactory.fail("操作失败");
    }
    //查询交易记录
    @UserToken
    @PostMapping({ "/api/selectGoodsRecord" })
    public Result selectGoodsRecord(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PlayerService service = new PlayerService();
        return ResultFactory.success(service.selectGoodsRecord(param));
    }
    //查询交易记录
    @UserToken
    @PostMapping({ "/api/selectGoodsRecordNew" })
    public Result selectGoodsRecordNew(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PageInfo<Goodsrecord> goodsrecordPageInfo = AllServiceUtil.getGoodsrecordService().selectGoodsRecordNew(param);
        return ResultFactory.success(goodsrecordPageInfo);
    }
    //对玩家进行操作：禁言/解禁   封号/开启   踢下线
    @UserToken
    @PostMapping({ "/api/roleoperation" })
    public Result operation(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PlayerService service = new PlayerService();
        if (service.operation(param)) {
            return ResultFactory.success(null);
        }
        return ResultFactory.fail("操作失败，请确认该玩家是否存在");
    }
    //充值
    @UserToken
    @PostMapping({ "/api/recharge" })
    public Result recharge(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        UserTable userTable = AllServiceUtil.getUserTableService().selectByPrimaryKey(new BigDecimal(param.getValue1()));
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getInlineUserNameMap().get(userTable.getUsername());
        LoginResult login = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
        if (login == null) {
            return ResultFactory.fail("玩家不在线");
        }
        PlayerService service = new PlayerService();
        if (service.rechargeCallBack(param)) {
            return ResultFactory.success(null);
        }
        return ResultFactory.fail("操作失败");
    }
    //授权充值
    @UserToken
    @PostMapping({ "/api/userAdminRecharge" })
    public Result userAdminRecharge(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PlayerService service = new PlayerService();
        if (service.userAdminRechargeCallBack(param)) {
            return ResultFactory.success(null);
        }
        return ResultFactory.fail("操作失败");
    }
    //发卡人员列表管理
    @UserToken
    @PostMapping({ "/api/adminUserList" })
    public Result adminUserList(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PlayerService service = new PlayerService();
        return ResultFactory.success(service.adminUserList(param));
    }
    //发卡人员添加
    @UserToken
    @PostMapping({ "/api/insertUser" })
    public Result insertUser(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PlayerService service = new PlayerService();
        return ResultFactory.success(service.insertUser(param));
    }
    //发卡人员删除
    @UserToken
    @PostMapping({ "/api/deleteUser" })
    public Result deleteUser(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("ACCOUNT", param.getValue1());
        PlayerService service = new PlayerService();
        return ResultFactory.success(service.deleteUser(params));
    }
    //额度修改
    @UserToken
    @PostMapping({ "/api/updateUserAmount" })
    public Result updateUserAmount(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PlayerService service = new PlayerService();
        return ResultFactory.success(service.updateUserAmount(param));
    }
    //查询充值记录
    @UserToken
    @PostMapping({ "/api/rechargeinfo" })
    public Result rechargeinfo(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PlayerService service = new PlayerService();
        return ResultFactory.success(service.getReceipts(param));
    }
    //查询配置数据库
    @UserToken
    @PostMapping({ "/api/selectConfigure" })
    public Result selectConfigure(Param param, HttpServletRequest request) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        PlayerService service = new PlayerService();
        return ResultFactory.success(service.selectConfigure());
    }
    //查询配置数据库
    @UserToken
    @PostMapping({ "/api/updateConfigure" })
    public Result updateConfigure(HttpServletRequest request, Param param) {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult;
        }
        String fsd = request.getParameter("fsd");
        String cjlzg = request.getParameter("cjlzg");
        if (fsd != null && fsd != "") {
            param.setValue1(fsd);
        }
        if (cjlzg != null && cjlzg != "") {
            param.setValue2(cjlzg);
        }
        PlayerService service = new PlayerService();
        return ResultFactory.success(service.updateConfigure(param));
    }
}
