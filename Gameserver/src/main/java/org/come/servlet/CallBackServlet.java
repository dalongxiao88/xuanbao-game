package org.come.servlet;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.entity.PayvipBean;
import come.tool.Role.RoleData;
import redis.clients.jedis.Jedis;
import org.come.entity.Record;
import org.come.handler.MainServerHandler;
import org.come.tool.WriteOut;
import come.tool.Role.PrivateData;
import org.come.until.APIHttpClient;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.action.monitor.MonitorUtil;
import org.come.bean.UseCardBean;
import come.tool.Role.RolePool;
import org.come.bean.ApplyPayBean;
import come.tool.Scene.LaborDay.LaborScene;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.come.bean.ApplyBean;
import org.come.redis.RedisPoolUntil;
import java.io.PrintWriter;
import java.math.BigDecimal;
import org.come.entity.UserTable;
import com.auth0.jwt.JWTVerifier;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.ExpensesReceipts;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class CallBackServlet extends HttpServlet
{
    private String URL;
    
    public CallBackServlet() {
        this.URL = "http://www.dongmengzhongchou.com:8080/TestMaven/updateExpensesReceipts";
    }
    
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write("caonima");
            pwPrintWriter.flush();
            pwPrintWriter.close();
            return;
        }
        User user = (User)request.getSession().getAttribute("BG_NAME_XY2");
        Object manger = request.getSession().getAttribute("manger");
        String token = request.getHeader("token");
        if (user == null) {
            response.sendError(401, "用户登录验证不正确");
            return;
        }
        if (token == null) {
            response.sendError(401, "用户登录验证不正确");
            return;
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        }
        catch (JWTVerificationException e) {
            response.sendError(401, "用户登录验证不正确");
            return;
        }
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String mes = request.getParameter("mes");
        String type = request.getParameter("type");
        ExpensesReceipts expensesReceipts = (ExpensesReceipts)GsonUtil.getGsonUtil().getgson().fromJson(mes, ExpensesReceipts.class);
        UserTable userTable = AllServiceUtil.getUserTableService().selectForUsername(expensesReceipts.getPlayeracc());
        BigDecimal sid = expensesReceipts.getSid();
        expensesReceipts.setSid(userTable.getQid());
        if ("2".equals(type)) {
            this.rechargeCallBack(expensesReceipts, sid, userTable);
        }
        else if ("3".equals(type)) {
            this.integralCallBack(expensesReceipts, userTable);
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(expensesReceipts));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    public void rechargeCallBack(ExpensesReceipts expensesReceipts, BigDecimal sid, UserTable userTable) {
        Jedis jedis = RedisPoolUntil.getJedis();
        expensesReceipts.setSid(userTable.getQid());
        try {
            ApplyBean applyBean = new ApplyBean();
            applyBean.setUserNameS(expensesReceipts.getPlayeracc());
            applyBean.setRealmoney(expensesReceipts.getRecharge() + "");
            BigDecimal addC = new BigDecimal(applyBean.getRealmoney());
            int type = expensesReceipts.getType();
            userTable.setPayintegration(Integer.valueOf((int)userTable.getPayintegration() + addC.intValue()));
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getInlineUserNameMap().get(applyBean.getUserNameS());
            LoginResult login = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (login != null) {
                AllServiceUtil.getUserTableService().updateUser(userTable);
                login.setPaysum(login.getPaysum().add(addC));
                login.setDaypaysum(login.getDaypaysum().add(addC));
                LaborScene.addRankValue(0, addC.intValue(), login);
                ApplyPayBean applyPayBean = new ApplyPayBean();
                applyPayBean.setAddM(addC);
                RoleData roleData = RolePool.getRoleData(login.getRole_id());
                PayvipBean vipBean = GameServer.getVIP(login.getPaysum().longValue());
                if (vipBean != null && roleData != null) {
                    UseCardBean limit = roleData.getLimit("SVIP");
                    if (limit == null) {
                        limit = new UseCardBean("VIP" + vipBean.getGrade(), "SVIP", "S" + (19 + (int)vipBean.getGrade()), -1L, vipBean.getIncreationtext());
                        roleData.addLimit(limit);
                        applyPayBean.setVIPBean(limit);
                    }
                    else if (!limit.getName().equals("VIP" + vipBean.getGrade())) {
                        limit.setName("VIP" + vipBean.getGrade());
                        limit.setSkin("S" + (19 + (int)vipBean.getGrade()));
                        limit.setValue(vipBean.getIncreationtext());
                        applyPayBean.setVIPBean(limit);
                    }
                }
                if (type == 2) {
                    long time = 0L;
                    if (expensesReceipts.getRecharge().intValue() == 30) {
                        time = 2592000000L;
                    }
                    else if (expensesReceipts.getRecharge().intValue() == 10) {
                        time = 604800000L;
                    }
                    else if (expensesReceipts.getRecharge().intValue() == 1) {
                        time = 3600000L;
                    }
                    if (time != 0L && roleData != null) {
                        UseCardBean limit2 = roleData.getLimit("VIP");
                        if (limit2 != null) {
                            limit2.setTime(limit2.getTime() + time);
                        }
                        else {
                            limit2 = new UseCardBean("VIP", "VIP", "1", System.currentTimeMillis() + time, "掉落率=1|经验加成=5|加强全系法术=5|召唤兽死亡不掉忠诚,血法|人物死亡惩罚减半");
                            roleData.addLimit(limit2);
                        }
                        applyPayBean.setUseCardBean(limit2);
                        applyPayBean.setMsg("激活了" + time / 1000L / 60L / 60L / 24L + "天月卡特权");
                    }
                }
                else if (type == 3 && login.getLowOrHihtpack() == 0) {
                    login.setLowOrHihtpack(1);
                    applyPayBean.setLowOrHihtpack(new BigDecimal(1));
                    applyPayBean.setMsg("开通了小资冲级礼包");
                }
                else if (type == 4 && login.getLowOrHihtpack() == 0) {
                    login.setLowOrHihtpack(2);
                    applyPayBean.setLowOrHihtpack(new BigDecimal(2));
                    applyPayBean.setMsg("开通了土豪冲级礼包");
                }
                else {
                    applyBean.setPaymoney(expensesReceipts.getYuanbao() + "");
                    login.setCodecard(login.getCodecard().add(new BigDecimal(applyBean.getPaymoney())));
                    MonitorUtil.getMoney().addX(new BigDecimal(applyBean.getPaymoney()).longValue(), 0);
                    MonitorUtil.getMoney().addC(addC.longValue());
                    login.setMoney(Integer.valueOf(((login.getMoney() != null) ? ((int)login.getMoney()) : 0) + addC.intValue()));
                    applyPayBean.setAddX(new BigDecimal(applyBean.getPaymoney()));
                    applyPayBean.setAddC(addC);
                    if (addC.longValue() >= 30L && login.getDayfirstinorno() == 0) {
                        login.setDayandpayorno(login.getDayandpayorno().add(new BigDecimal(1)));
                        login.setDayfirstinorno(1);
                        applyPayBean.setDayandpayorno(login.getDayandpayorno());
                    }
                    StringBuffer buffer = new StringBuffer();
                    if (type == 3 || type == 4) {
                        buffer.append("小资冲级礼包和土豪冲级礼包只能同时拥有一个,你已经有了");
                        buffer.append((login.getLowOrHihtpack() == 2) ? "土豪冲级礼包" : "小资冲级礼包");
                        buffer.append("本次充值变为正常仙玉充值.");
                    }
                    buffer.append("你充值积分:");
                    buffer.append(addC.intValue());
                    buffer.append(",获得仙玉:");
                    buffer.append(applyBean.getPaymoney());
                    applyPayBean.setMsg(buffer.toString());
                }
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().applyPay(GsonUtil.getGsonUtil().getgson().toJson(applyPayBean)));
                jedis.hset("order_number_control_orno", expensesReceipts.getErid() + "", expensesReceipts.getPaytime() + ":金额" + expensesReceipts.getRecharge());
                jedis.hset("payReturnForpayServer", expensesReceipts.getErid() + "", this.URL + "=" + GsonUtil.getGsonUtil().getgson().toJson(expensesReceipts) + "");
                APIHttpClient.sendMes(this.URL, GsonUtil.getGsonUtil().getgson().toJson(expensesReceipts));
            }
            else {
                if (expensesReceipts.getRoleid() != null) {
                    login = AllServiceUtil.getRoleTableService().selectRoleID(expensesReceipts.getRoleid());
                }
                else {
                    List<LoginResult> loginResults = AllServiceUtil.getUserTableService().findRoleByUserNameAndPassword(applyBean.getUserNameS(), null, null);
                    if (loginResults.size() != 0) {
                        login = (LoginResult)loginResults.get(0);
                    }
                }
                if (login != null) {
                    login.setPaysum(login.getPaysum().add(addC));
                    login.setDaypaysum(login.getDaypaysum().add(addC));
                    LaborScene.addRankValue(0, addC.intValue(), login);
                    if (type == 2) {
                        long time2 = 0L;
                        if (expensesReceipts.getRecharge().intValue() == 30) {
                            time2 = 2592000000L;
                        }
                        else if (expensesReceipts.getRecharge().intValue() == 10) {
                            time2 = 604800000L;
                        }
                        else if (expensesReceipts.getRecharge().intValue() == 1) {
                            time2 = 3600000L;
                        }
                        PrivateData privateData = new PrivateData();
                        privateData.setTimingGood(login.getTimingGood());
                        ConcurrentHashMap<String, UseCardBean> limitMap = privateData.initLimit(0L);
                        UseCardBean limit3 = (UseCardBean)limitMap.get("VIP");
                        if (limit3 != null) {
                            limit3.setTime(limit3.getTime() + time2);
                        }
                        else {
                            limit3 = new UseCardBean("VIP", "VIP", "1", System.currentTimeMillis() + time2, "掉落率=1|经验加成=5|加强全系法术=5|召唤兽死亡不掉忠诚,血法|人物死亡惩罚减半");
                            limitMap.put("VIP", limit3);
                        }
                        StringBuffer buffer2 = new StringBuffer();
                        for (UseCardBean cardBean : limitMap.values()) {
                            if (buffer2.length() != 0) {
                                buffer2.append("^");
                            }
                            buffer2.append(cardBean.getName());
                            buffer2.append("#");
                            buffer2.append(cardBean.getType());
                            buffer2.append("#");
                            buffer2.append(cardBean.getSkin());
                            buffer2.append("#");
                            buffer2.append(cardBean.getTime() / 60000L);
                            if (cardBean.getValue() != null && !cardBean.getValue().equals("")) {
                                buffer2.append("#");
                                buffer2.append(cardBean.getValue());
                            }
                        }
                        login.setTimingGood(buffer2.toString());
                    }
                    else if (type == 3 && login.getLowOrHihtpack() == 0) {
                        login.setLowOrHihtpack(1);
                    }
                    else if (type == 4 && login.getLowOrHihtpack() == 0) {
                        login.setLowOrHihtpack(2);
                    }
                    else {
                        applyBean.setPaymoney(expensesReceipts.getYuanbao() + "");
                        userTable.setCodecard(userTable.getCodecard().add(new BigDecimal(applyBean.getPaymoney())));
                        userTable.setMoney(Integer.valueOf((int)userTable.getMoney() + addC.intValue()));
                        MonitorUtil.getMoney().addX(new BigDecimal(applyBean.getPaymoney()).longValue(), 0);
                        MonitorUtil.getMoney().addC(addC.longValue());
                        if (addC.longValue() >= 30L && login.getDayfirstinorno() == 0) {
                            login.setDayandpayorno(login.getDayandpayorno().add(new BigDecimal(1)));
                            login.setDayfirstinorno(1);
                        }
                    }
                    try {
                        AllServiceUtil.getRoleTableService().updateRoleWhenExit(login);
                    }
                    catch (Exception e) {
                        WriteOut.addtxt("人物数据保存报错:" + GsonUtil.getGsonUtil().getgson().toJson(login), 9999L);
                    }
                }
                else {
                    userTable.setCodecard(userTable.getCodecard().add(new BigDecimal(applyBean.getPaymoney())));
                    userTable.setMoney(Integer.valueOf((int)userTable.getMoney() + addC.intValue()));
                    MonitorUtil.getMoney().addX(new BigDecimal(applyBean.getPaymoney()).longValue(), 0);
                    MonitorUtil.getMoney().addC(addC.longValue());
                }
                AllServiceUtil.getUserTableService().updateUser(userTable);
                jedis.hset("order_number_control_orno", expensesReceipts.getErid() + "", expensesReceipts.getPaytime() + ":金额" + expensesReceipts.getRecharge());
                APIHttpClient.sendMes(this.URL, GsonUtil.getGsonUtil().getgson().toJson(expensesReceipts));
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
            WriteOut.addtxt("充值报错:" + MainServerHandler.getErrorMessage(e2), 9999L);
        }
        RedisPoolUntil.returnResource(jedis);
        AllServiceUtil.getRecordService().insert(new Record(8, GsonUtil.getGsonUtil().getgson().toJson(expensesReceipts)));
    }
    
    public void integralCallBack(ExpensesReceipts expensesReceipts, UserTable userTable) {
        Jedis jedis = RedisPoolUntil.getJedis();
        expensesReceipts.setSid(userTable.getQid());
        try {
            ApplyBean applyBean = new ApplyBean();
            applyBean.setUserNameS(expensesReceipts.getPlayeracc());
            applyBean.setRealmoney(expensesReceipts.getRecharge() + "");
            BigDecimal addC = new BigDecimal(applyBean.getRealmoney());
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getInlineUserNameMap().get(applyBean.getUserNameS());
            LoginResult login = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (login != null) {
                AllServiceUtil.getUserTableService().updateUser(userTable);
                login.setPaysum(login.getPaysum().add(addC));
                LaborScene.addRankValue(0, addC.intValue(), login);
                ApplyPayBean applyPayBean = new ApplyPayBean();
                applyPayBean.setAddM(addC);
                PayvipBean vipBean = GameServer.getVIP(login.getPaysum().longValue());
                RoleData roleData = RolePool.getRoleData(login.getRole_id());
                if (vipBean != null && roleData != null) {
                    UseCardBean limit = roleData.getLimit("SVIP");
                    if (limit == null) {
                        limit = new UseCardBean("VIP" + vipBean.getGrade(), "SVIP", "S" + (19 + (int)vipBean.getGrade()), -1L, vipBean.getIncreationtext());
                        roleData.addLimit(limit);
                        applyPayBean.setVIPBean(limit);
                    }
                    else if (!limit.getName().equals("VIP" + vipBean.getGrade())) {
                        limit.setName("VIP" + vipBean.getGrade());
                        limit.setSkin("S" + (19 + (int)vipBean.getGrade()));
                        limit.setValue(vipBean.getIncreationtext());
                        applyPayBean.setVIPBean(limit);
                    }
                }
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().applyPay(GsonUtil.getGsonUtil().getgson().toJson(applyPayBean)));
                jedis.hset("order_number_control_orno", expensesReceipts.getErid() + "", expensesReceipts.getPaytime() + ":金额" + expensesReceipts.getRecharge());
                jedis.hset("payReturnForpayServer", expensesReceipts.getErid() + "", this.URL + "=" + GsonUtil.getGsonUtil().getgson().toJson(expensesReceipts) + "");
                APIHttpClient.sendMes(this.URL, GsonUtil.getGsonUtil().getgson().toJson(expensesReceipts));
            }
            else {
                if (expensesReceipts.getRoleid() != null) {
                    login = AllServiceUtil.getRoleTableService().selectRoleID(expensesReceipts.getRoleid());
                }
                else {
                    List<LoginResult> loginResults = AllServiceUtil.getUserTableService().findRoleByUserNameAndPassword(applyBean.getUserNameS(), null, null);
                    if (loginResults.size() != 0) {
                        login = (LoginResult)loginResults.get(0);
                    }
                }
                if (login != null) {
                    login.setPaysum(login.getPaysum().add(addC));
                    LaborScene.addRankValue(0, addC.intValue(), login);
                    try {
                        AllServiceUtil.getRoleTableService().updateRoleWhenExit(login);
                    }
                    catch (Exception e) {
                        WriteOut.addtxt("人物数据保存报错:" + GsonUtil.getGsonUtil().getgson().toJson(login), 9999L);
                    }
                }
                else {
                    userTable.setCodecard(userTable.getCodecard().add(new BigDecimal(applyBean.getPaymoney())));
                    userTable.setMoney(Integer.valueOf((int)userTable.getMoney() + addC.intValue()));
                    MonitorUtil.getMoney().addX(new BigDecimal(applyBean.getPaymoney()).longValue(), 0);
                    MonitorUtil.getMoney().addC(addC.longValue());
                }
                AllServiceUtil.getUserTableService().updateUser(userTable);
                jedis.hset("order_number_control_orno", expensesReceipts.getErid() + "", expensesReceipts.getPaytime() + ":金额" + expensesReceipts.getRecharge());
                APIHttpClient.sendMes(this.URL, GsonUtil.getGsonUtil().getgson().toJson(expensesReceipts));
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
            WriteOut.addtxt("充值报错:" + MainServerHandler.getErrorMessage(e2), 9999L);
        }
        RedisPoolUntil.returnResource(jedis);
    }
    
    @Override
    public void init() throws ServletException {
    }
}
