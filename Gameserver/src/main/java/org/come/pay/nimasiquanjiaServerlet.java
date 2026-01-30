package org.come.pay;

import java.io.PrintWriter;
import org.come.until.ThreadPoolUntil;
import org.come.ApiValid;
import org.come.bean.managerTable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import org.come.entity.PayvipBean;
import come.tool.Role.RoleData;
import redis.clients.jedis.Jedis;
import org.come.entity.UserTable;
import org.come.entity.Record;
import org.come.handler.MainServerHandler;
import org.come.tool.WriteOut;
import come.tool.Role.PrivateData;
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
import java.math.BigDecimal;
import org.come.bean.ApplyBean;
import org.come.until.APIHttpClient;
import org.come.redis.RedisPoolUntil;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.ExpensesReceipts;
import javax.servlet.http.HttpServlet;

public class nimasiquanjiaServerlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private String URL;
    
    public void creatMes(String mes) throws IOException {
        System.out.println(mes);
        ExpensesReceipts expensesReceipts = (ExpensesReceipts)GsonUtil.getGsonUtil().getgson().fromJson(mes, ExpensesReceipts.class);
        try {
            String[] vs = expensesReceipts.getPlayeracc().split("\\|");
            expensesReceipts.setPlayeracc(vs[0]);
        }
        catch (Exception ex) {}
        UserTable userTable = AllServiceUtil.getUserTableService().selectForUsername(expensesReceipts.getPlayeracc());
        Jedis jedis = RedisPoolUntil.getJedis();
        if (userTable == null || jedis.hget("order_number_control_orno", expensesReceipts.getErid() + "") != null) {
            if (jedis.hget("payReturnForpayServer", expensesReceipts.getErid() + "") != null) {
                String[] mes2 = jedis.hget("payReturnForpayServer", expensesReceipts.getErid() + "").split("=");
                APIHttpClient.sendMes(mes2[0], mes2[1]);
            }
            RedisPoolUntil.returnResource(jedis);
            return;
        }
        else {
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
            String mesStr = "用户:" + expensesReceipts.getPlayeracc() + ",充值仙玉：" + expensesReceipts.getYuanbao();
            AllServiceUtil.getRecordService().insert(new Record(8, mesStr));
            return;
        }
    }
    
    public nimasiquanjiaServerlet() {
        this.URL = "http://www.xy2o.com/" + GameServer.getGameServerPay() + "/getserverMes";
    }
    
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
//        String line = null;
//        StringBuilder sb = new StringBuilder();
//        while ((line = br.readLine()) != null) {
//            sb.append(line);
//        }
//        br.close();
//        managerTable manege = (managerTable)request.getSession().getAttribute("xy2o");
//        String token = request.getHeader("manage_token");
//        String sbStr = sb.toString();
//        if (sbStr.indexOf("wdltxyss") == -1 || sbStr.indexOf("zzswxy2o!@#HH") == -1 || manege == null || !ApiValid.vaildToken(token, manege.getUsername())) {
//            System.out.println("XhcServerlet非法请求,已踢出！！");
//            return;
//        }
//        response.setHeader("manage_token", ApiValid.getToken(manege.getUsername()));
//        ThreadPoolUntil.getThreadPoolUntil();
//        ThreadPoolUntil.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    nimasiquanjiaServerlet.this.creatMes(sb.toString());
//                }
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        PrintWriter pwPrintWriter = response.getWriter();
//        pwPrintWriter.flush();
//        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
