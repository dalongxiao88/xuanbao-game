package com.gl.service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.BasePageHelper;
import org.come.bean.ExpensesReceiptsInfo;
import java.util.concurrent.ConcurrentHashMap;
import org.come.entity.PayvipBean;
import come.tool.Role.RoleData;
import org.come.until.AchievemUtil;
import redis.clients.jedis.Jedis;
import org.come.entity.Record;
import org.come.handler.MainServerHandler;
import org.come.tool.WriteOut;
import come.tool.Role.PrivateData;
import org.come.until.GsonUtil;
import org.come.action.reward.DrawnitemsAction;
import org.come.action.monitor.MonitorUtil;
import org.come.bean.UseCardBean;
import come.tool.Role.RolePool;
import org.come.bean.ApplyPayBean;
import come.tool.Scene.LaborDay.LaborScene;
import org.come.bean.LoginResult;
import org.come.bean.ApplyBean;
import org.come.redis.RedisPoolUntil;
import org.apache.commons.httpclient.util.DateUtil;
import java.util.Calendar;
import org.come.entity.ExpensesReceipts;
import java.util.Random;
import io.netty.channel.ChannelHandlerContext;
import org.come.protocol.ParamTool;
import org.come.action.IAction;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.entity.Haters;
import org.come.entity.Mount;
import org.come.entity.Goodsrecord;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.HashMap;
import org.come.bean.AdminUserInfo;
import org.come.entity.Openareatable;
import org.come.entity.UserTable;
import java.util.Iterator;
import java.util.List;
import org.come.server.GameServer;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import org.apache.commons.lang.math.NumberUtils;
import com.github.pagehelper.util.StringUtil;
import org.come.entity.RoleTable;
import org.come.bean.BackRoleInfo;
import com.gl.model.Param;

public class PlayerService
{
    private static final int PageSize = 10;
    //roleName  角色名 pageNum	页码   从1开始 status	状态   3、禁言 4、封号5、未封号  6、未禁言userName	用户名
    public BackRoleInfo getRole(Param param) {
        String type = param.getValue1();
        String value = param.getValue2();
        int pageNum = param.getPageNum();
        int status = param.getStatus();
        int size = param.getPageSize();
        if (size < 10) {
            size = 10;
        }
        BackRoleInfo list = null;
        RoleTable roleTable = new RoleTable();
        roleTable.setQid(null);
        roleTable.setStart((pageNum - 1) * size);
        roleTable.setEnd(pageNum * size);
        switch (status) {
            case 3: {
                roleTable.setUnknown("1");
                break;
            }
            case 4: {
                roleTable.setActivity(new Short("1"));
                break;
            }
            case 5: {
                roleTable.setActivity(new Short("0"));
                break;
            }
            default: {
                roleTable.setActivity(null);
                break;
            }
        }
        if (StringUtil.isNotEmpty(type) && !"undefined".equals(type) && StringUtil.isNotEmpty(value) && !"undefined".equals(value)) {
            //设置角色名
            if (type.equals("1") && NumberUtils.isDigits(value)) {
                roleTable.setRole_id(new BigDecimal(value));
            }
            else if (type.equals("2")) {
                roleTable.setRolename(value);
            }
            else if (type.equals("3")) {
                roleTable.setLocalname(value);
            }
        }
        //查询总区域得玩家信息
        int total = AllServiceUtil.getUserTableService().selectSumForRoleUserHaterNumber(roleTable);
        int page = total / size;
        if (total % size > 0) {
            ++page;
        }
        roleTable.setUserString(" Order By role_id ASC");
        //查询状态下的角色
        List<RoleTable> listall = AllServiceUtil.getUserTableService().selectSumForRoleUserHaterList(roleTable);
        list = new BackRoleInfo();
        //进行状态实例化
        for (RoleTable roleInfo : listall) {
            // 玩家状态1、在线 2、下线 3、禁言 4、封号5、未封号  6、未禁言
            if (GameServer.getRoleNameMap().get(roleInfo.getRolename()) != null) {
                roleInfo.setStatues("在线");
            }
            else {
                roleInfo.setStatues("离线");
            }
            roleInfo.setUnknown(StringUtil.isEmpty(roleInfo.getUnknown()) ? "0" : roleInfo.getUnknown());
            // 清空密码，不将用户密码传到前端
            UserTable userTable = AllServiceUtil.getUserTableService().selectByPrimaryKey(roleInfo.getUser_id());
            roleInfo.setAccountName(userTable.getUsername());
            roleInfo.setAccountPwd(userTable.getUserpwd());
            Openareatable openareatable = AllServiceUtil.getOpenareatableService().selectOpenareatable(userTable.getQid().toString());
            roleInfo.setOtAreaid(openareatable.getOt_areaid().toString());
            roleInfo.setOtAreaname(openareatable.getOt_areaname());
            roleInfo.setOtAtid(openareatable.getOt_atid());
            roleInfo.setUptime(userTable.getUSERLASTLOGIN());
            roleInfo.setPaysum(new BigDecimal((int)userTable.getMoney()));
        }
        list.setList(listall);
        list.setPages(page);
        list.setPageNum(pageNum);
        list.setTotal((long)total);
        return list;
    }
    
    public AdminUserInfo adminUserList(Param param) {
        Map<String, Object> map = new HashMap<>();
        map.put("ACCOUNT", param.getValue2());
        int size = param.getPageSize();
        if (size < 10) {
            size = 10;
        }
        AdminUserInfo list = null;
        //查询总区
        int total = 500;
        //查询
        List<Map<String, Object>> listall = AllServiceUtil.getRoleTableService().selectadminUserList(map);
        Map<String, Object> mapS = new HashMap<>();
        mapS.put("ACCOUNT", "");
        mapS.put("PWD", "");
        listall.add(mapS);
        list = new AdminUserInfo();
        //进行状态实例化
        list.setList(listall);
        list.setPages(1);
        list.setPageNum(1);
        list.setTotal((long)total);
        return list;
    }
    
    public boolean insertUser(Param param) {
        Map<String, Object> map = new HashMap<>();
        // 账号
        map.put("ACCOUNT", param.getValue1());
        // 密码
        map.put("PWD", param.getValue2());
        // 总额度
        map.put("TOTALAMOUNT", param.getValue3());
        // 剩余额度
        map.put("REMAINING", param.getValue3());
        // 备注
        map.put("REMARK", param.getValue4());
        // 创建时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("TIME", sdf.format(new Date()));

        int flag = AllServiceUtil.getRoleTableService().insertUser(map);
        return flag > 0;
    }
    //param	value1 角色ID 删除user
    public boolean deleteUser(Map<String, Object> param) {
        AllServiceUtil.getRoleTableService().deleteUser(param);
        return true;
    }
    //额度修改user
    // @param param	value1 角色ID
    // @return
    public boolean updateUserAmount(Param param) {
        // 创建一个Map用于查询条件
        Map<String, Object> map = new HashMap<>();
        map.put("ACCOUNT", param.getValue1());
        // 查询用户列表
        List<Map<String, Object>> listall = AllServiceUtil.getRoleTableService().selectadminUserList(map);
        // 如果查询结果不为空
        if (listall != null && listall.size() > 0) {
            Map<String, Object> params = new HashMap<>();
            // 获取 param.getValue2() 的第一个字符
            char fir = param.getValue2().charAt(0);
            String str = String.valueOf(fir);
            // 如果第一个字符是 "+"
            if (str.equals("+")) {
                // 去掉 "+" 符号，获取金额值
                String mon = "";
                mon = param.getValue2().replace("+", "");
                Long z = Long.parseLong(listall.get(0).get("TOTALAMOUNT").toString());
                Long s = Long.parseLong(((Map<String, Object>)listall.get(0)).get("REMAINING").toString());
                z = z + Long.parseLong(mon);
                s = s + Long.parseLong(mon);
                params.put("TOTALAMOUNT", z);
                params.put("REMAINING", s);
            }
            else if (str.equals("-")) {
                String mon = "";
                mon = param.getValue2().replace("-", "");
                Long z = Long.parseLong(listall.get(0).get("TOTALAMOUNT").toString());
                Long s = Long.parseLong(listall.get(0).get("REMAINING").toString());
                z = z - Long.parseLong(mon);
                s = s - Long.parseLong(mon);
                if ((long)s < 0L) {
                    return false;
                }
                params.put("TOTALAMOUNT", z);
                params.put("REMAINING", s);
            }
            else {
                Long z2 = Long.parseLong((listall.get(0)).get("TOTALAMOUNT").toString());
                Long s2 = Long.parseLong((listall.get(0)).get("REMAINING").toString());
                z2 = z2 + Long.parseLong(param.getValue2());
                s2 = s2 + Long.parseLong(param.getValue2());
                params.put("TOTALAMOUNT", z2);
                params.put("REMAINING", s2);
            }
            params.put("ACCOUNT", param.getValue1());
            AllServiceUtil.getRoleTableService().updateUserAmount(params);
        }
        return true;
    }
    
    public boolean deleteSQL(RoleTable param) {
        AllServiceUtil.getRoleTableService().deleteTableSQL(param);
        return true;
    }
    /**
     * 根服务器清档
     * @param param		value1 角色ID   value2 新解锁码
     * @return
     */
    public boolean editLockPassword(Param param) {
        // 获取角色ID
        String roleid = param.getValue1();
        // 修改的角色解锁码
        String goodsecret = param.getValue2();

        RoleTable roleTable = new RoleTable();
        roleTable.setRole_id(new BigDecimal(roleid));
        roleTable.setPassword(goodsecret);
        int flag = AllServiceUtil.getRoleTableService().updateRolePwdForRid(roleTable);
        return flag > 0;
    }
    
    public boolean updateGMRole(Param param) {
        String roleid = param.getValue1();
        String goodsecret = param.getValue2();
        RoleTable roleTable = new RoleTable();
        roleTable.setRole_id(new BigDecimal(roleid));
        roleTable.setGmshoptype(goodsecret);
        int flag = AllServiceUtil.getRoleTableService().updateRoleGMForRid(roleTable);
        return flag > 0;
    }
    
    public boolean deleteRolePwdForRid(Param param) {
        String roleid = param.getValue1();
        RoleTable roleTable = new RoleTable();
        roleTable.setRole_id(new BigDecimal(roleid));
        int flag = AllServiceUtil.getRoleTableService().deleteRolePwdForRid(roleTable);
        return flag > 0;
    }
    
    public boolean updatePwdUserForRid(Param param) {
        String localname = param.getValue1();
        String pwd = param.getValue2();
        UserTable userTable = new UserTable();
        userTable.setUsername(localname);
        userTable.setUserpwd(pwd);
        int flag = AllServiceUtil.getUserTableService().updatePwdUserForRid(userTable);
        return flag > 0;
    }
    
    public boolean updateConfigure(Param param) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "1");
        System.out.println("测试：============" + param.getValue1() + "," + param.getValue2());
        if (param.getValue1() != null || param.getValue2() != null) {
            if (param.getValue1() != null) {
                map.put("fsdnum", param.getValue1());
            }
            if (param.getValue2() != null) {
                map.put("cjlzgnum", param.getValue2());
            }
            int flag = AllServiceUtil.getUserTableService().updateConfigure(map);
            return flag > 0;
        }
        else {
            return false;
        }
    }
    
    public List<Goodsrecord> selectGoodsRecord(Param param) {
        Goodsrecord goodsrecord = new Goodsrecord();
        return AllServiceUtil.getGoodsrecordService().selectGoodsrecordList(goodsrecord);
    }
    
    public List<Map<String, Object>> selectConfigure() {
        return AllServiceUtil.getRoleTableService().selectConfigure();
    }
    
    public boolean updateMountForRid(Param param) {
        String roleid = param.getValue1();
        String mountid = param.getValue2();
        String sv = param.getValue3();
        String[] sxl = sv.split(",");
        String SPRI = "";
        if (!sxl[0].equals("0")) {
            SPRI = sxl[0];
        }
        String POWER = "";
        if (!sxl[1].equals("0")) {
            POWER = sxl[1];
        }
        String BONE = "";
        if (!sxl[2].equals("0")) {
            BONE = sxl[2];
        }
        Mount mount = new Mount();
        mount.setRoleid(new BigDecimal(roleid));
        mount.setMountid(Integer.parseInt(mountid));
        if (!SPRI.equals("")) {
            mount.setSpri(Integer.parseInt(SPRI));
        }
        if (!POWER.equals("")) {
            mount.setPower(Integer.parseInt(POWER));
        }
        if (!BONE.equals("")) {
            mount.setBone(Integer.parseInt(BONE));
        }
        int flag = AllServiceUtil.getMountService().updateMountForRid(mount);
        return flag > 0;
    }
    
    public boolean operation(Param param) {
        String roleName = param.getValue1();
        String type = param.getValue2();
        String reason = param.getValue3();
        String controlname = "ADMIN";
        if (StringUtil.isEmpty(type)) {
            return false;
        }
        int control = Integer.parseInt(type);
        if (control != 7 && control != 8) {
            RoleTable roleInfo = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(roleName);
            if (roleInfo == null) {
                return false;
            }
            UserTable userInfo = AllServiceUtil.getUserTableService().selectByPrimaryKey(roleInfo.getUser_id());
            if (userInfo == null) {
                return false;
            }
            switch (control) {
                case 1: {
                    Haters hater = AllServiceUtil.getHatersService().selectByPrimaryKey(roleInfo.getRole_id());
                    if (hater == null) {
                        Haters record = new Haters();
                        record.setRoleid(roleInfo.getRole_id());
                        AllServiceUtil.getHatersService().insert(record);
                        if (GameServer.getRoleNameMap().get(roleName) != null) {
                            SendMessage.sendMessageByRoleName(roleName, Agreement.getAgreement().tipAgreement("你的行为违规，已被系统禁言"));
                        }
                    }
                    return true;
                }
                case 2: {
                    if (GameServer.getRoleNameMap().get(roleName) != null) {
                        SendMessage.sendMessageByRoleName(roleName, Agreement.getAgreement().serverstopAgreement());
                    }
                    return true;
                }
                case 3: {
                    if (GameServer.getRoleNameMap().get(roleName) != null) {
                        ((IAction)ParamTool.ACTION_MAP.get("accountstop")).action((ChannelHandlerContext)GameServer.getRoleNameMap().get(roleName), userInfo.getUsername());
                    }
                    else {
                        UserTable table = new UserTable();
                        table.setUsername(userInfo.getUsername());
                        table.setActivity((short)1);
                        AllServiceUtil.getUserTableService().updateUser(table);
                        AllServiceUtil.getUserTableService().addRufenghaoControl(userInfo, roleInfo.getRolename(), reason, controlname, 1);
                    }
                    return true;
                }
                case 4: {
                    Haters hater2 = AllServiceUtil.getHatersService().selectByPrimaryKey(roleInfo.getRole_id());
                    if (hater2 != null) {
                        AllServiceUtil.getHatersService().deleteByPrimaryKey(hater2.getRoleid());
                        if (GameServer.getRoleNameMap().get(roleName) != null) {
                            SendMessage.sendMessageByRoleName(roleName, Agreement.getAgreement().tipAgreement("禁言已被解除"));
                        }
                    }
                    return true;
                }
                case 5: {
                    UserTable table2 = new UserTable();
                    table2.setUsername(userInfo.getUsername());
                    table2.setActivity((short)0);
                    AllServiceUtil.getUserTableService().updateUser(table2);
                    AllServiceUtil.getUserTableService().addRufenghaoControl(userInfo, roleInfo.getRolename(), reason, controlname, 2);
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        else {
            return false;
        }
    }
    
    public boolean rechargeCallBack(Param param) {
        String user_id = param.getValue1();
        String recharge = param.getValue2();
        String yuanbao = param.getValue3();
        String count = param.getValue4();
        String saveType = param.getValue5();
        if (StringUtil.isEmpty(saveType)) {
            return false;
        }
        int type = Integer.parseInt(saveType);
        if (StringUtil.isEmpty(user_id)) {
            return false;
        }
        if (StringUtil.isEmpty(yuanbao)) {
            yuanbao = "0";
        }
        BigDecimal userId = new BigDecimal(user_id);
        UserTable userTable = AllServiceUtil.getUserTableService().selectByPrimaryKey(userId);
        Random r = new Random(System.currentTimeMillis());
        ExpensesReceipts expensesReceipts = new ExpensesReceipts();
        expensesReceipts.setErid(new BigDecimal(System.currentTimeMillis() + "" + r.nextInt(9999)));
        expensesReceipts.setPlayeracc(userTable.getUsername());
        expensesReceipts.setSid(userTable.getQid());
        expensesReceipts.setRecharge(new BigDecimal(recharge));
        expensesReceipts.setYuanbao(new BigDecimal(yuanbao));
        expensesReceipts.setType(type);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(11, 8);
        expensesReceipts.setPaytime(DateUtil.formatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
        Jedis jedis = RedisPoolUntil.getJedis();
        try {
            ApplyBean applyBean = new ApplyBean();
            applyBean.setUserNameS(expensesReceipts.getPlayeracc());
            applyBean.setRealmoney(expensesReceipts.getRecharge() + "");
            BigDecimal addC = new BigDecimal(applyBean.getRealmoney());
            userTable.setPayintegration(Integer.valueOf((int)userTable.getPayintegration() + addC.intValue()));
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getInlineUserNameMap().get(applyBean.getUserNameS());
            LoginResult login = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (login != null) {
                AllServiceUtil.getUserTableService().updateUser(userTable);
                login.setPaysum(login.getPaysum().add(addC));
                login.setDaypaysum(login.getDaypaysum().add(addC));
                if (StringUtil.isNotEmpty(count) && !"undefined".equals(count)) {
                    LaborScene.addRankValue(0, Integer.parseInt(count) * 10, login);
                }
                ApplyPayBean applyPayBean = new ApplyPayBean();
                applyPayBean.setAddM(addC);
                expensesReceipts.setRoleid(login.getRole_id());
                expensesReceipts.setBuyroleName(login.getRolename());
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
                    long time = 86400000L * (long)expensesReceipts.getRecharge().intValue();
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
                    login.setMoney(((login.getMoney() != null) ? ((int)login.getMoney()) : 0) + addC.intValue());
                    login.setScore(DrawnitemsAction.Splice(login.getScore(), "充值积分=" + addC.longValue(), 2));
                    applyPayBean.setAddX(new BigDecimal(applyBean.getPaymoney()));
                    applyPayBean.setAddC(addC);
                    applyPayBean.setAddCZJF(addC);
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
                if(login.getPaysum().longValue()>=100){
                    AchievemUtil.detectionAchievem(login, "充值100");
                }
                if(login.getPaysum().longValue()>=500){
                    AchievemUtil.detectionAchievem(login, "充值500");
                }
                if(login.getPaysum().longValue()>=1000){
                    AchievemUtil.detectionAchievem(login, "充值1000");
                }
                if(login.getPaysum().longValue()>=1500){
                    AchievemUtil.detectionAchievem(login, "充值1500");
                }
                // 在线也要同步数据库
                AllServiceUtil.getRoleTableService().updateRoleWhenExit(login);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().applyPay(GsonUtil.getGsonUtil().getgson().toJson(applyPayBean)));
                // 确保第一次处理订单(确保充值成功)
                jedis.hset("order_number_control_orno", expensesReceipts.getErid() + "", expensesReceipts.getPaytime() + ":金额" + expensesReceipts.getRecharge());
                jedis.hset("payReturnForpayServer", expensesReceipts.getErid() + "", "Sinmahod=" + GsonUtil.getGsonUtil().getgson().toJson(expensesReceipts) + "");
            }
            else {// 不在线充值
                if (expensesReceipts.getRoleid() != null) {
                    login = AllServiceUtil.getRoleTableService().selectRoleID(expensesReceipts.getRoleid());
                }
                else {
                    List<LoginResult> loginResults = AllServiceUtil.getUserTableService().findRoleByUserNameAndPassword(applyBean.getUserNameS(), null, null);
                    if (loginResults.size() != 0) {
                        login = loginResults.get(0);
                    }
                }

                if (login != null) {
                    if(login.getPaysum().longValue()>=100){
                        AchievemUtil.detectionAchievem(login, "充值100");
                    }
                    if(login.getPaysum().longValue()>=500){
                        AchievemUtil.detectionAchievem(login, "充值500");
                    }
                    if(login.getPaysum().longValue()>=1000){
                        AchievemUtil.detectionAchievem(login, "充值1000");
                    }
                    if(login.getPaysum().longValue()>=1500){
                        AchievemUtil.detectionAchievem(login, "充值1500");
                    }
                    login.setPaysum(login.getPaysum().add(addC));
                    login.setDaypaysum(login.getDaypaysum().add(addC));
                    if (StringUtil.isNotEmpty(count) && !"undefined".equals(count)) {
                        LaborScene.addRankValue(0, Integer.parseInt(count) * 10, login);
                    }
                    expensesReceipts.setRoleid(login.getRole_id());
                    expensesReceipts.setBuyroleName(login.getRolename());
                    if (type == 2) {
                        long time2 = 3600000L * (long)expensesReceipts.getRecharge().intValue();
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
                        applyBean.setPaymoney(expensesReceipts.getYuanbao() + "");// 充值的元宝数量
                        userTable.setCodecard(userTable.getCodecard().add(new BigDecimal(applyBean.getPaymoney())));
                        userTable.setMoney(userTable.getMoney() + addC.intValue());
                        MonitorUtil.getMoney().addX(new BigDecimal(applyBean.getPaymoney()).longValue(), 0);
                        MonitorUtil.getMoney().addC(addC.longValue());
                        login.setScore(DrawnitemsAction.Splice(login.getScore(), "充值积分=" + addC.longValue(), 2));
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
                    userTable.setMoney(userTable.getMoney() + addC.intValue());
                    MonitorUtil.getMoney().addX(new BigDecimal(applyBean.getPaymoney()).longValue(), 0);
                    MonitorUtil.getMoney().addC(addC.longValue());
                }
                AllServiceUtil.getUserTableService().updateUser(userTable);
                jedis.hset("order_number_control_orno", expensesReceipts.getErid() + "", expensesReceipts.getPaytime() + ":金额" + expensesReceipts.getRecharge());
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
            WriteOut.addtxt("充值报错:" + MainServerHandler.getErrorMessage(e2), 9999L);
        }
        RedisPoolUntil.returnResource(jedis);
        AllServiceUtil.getRecordService().insert(new Record(8, GsonUtil.getGsonUtil().getgson().toJson(expensesReceipts)));
        AllServiceUtil.getExpensesReceiptsService().insert(expensesReceipts);
        return true;
    }
    
    public boolean userAdminRechargeCallBack(Param param) {
        String recharge = "0";
        String user_id = param.getValue1();
        String yuanbao = param.getValue3();
        String adminUser = param.getValue4();
        PlayerService service = new PlayerService();
        Param params = new Param();
        params.setValue2(adminUser);
        AdminUserInfo s = service.adminUserList(params);
        List<Map<String, Object>> userList = s.getList();
        System.out.println("ADMIN用户账号：" + ((Map<String, Object>)userList.get(0)).get("ACCOUNT"));
        System.out.println("用户总额度：" + ((Map<String, Object>)userList.get(0)).get("TOTALAMOUNT"));
        System.out.println("用户剩余额度：" + ((Map<String, Object>)userList.get(0)).get("REMAINING"));
        System.out.println("本次充值：" + yuanbao);
        Long TOTALAMOUNT = Long.valueOf(Long.parseLong(((Map<String, Object>)userList.get(0)).get("TOTALAMOUNT").toString()));
        Long REMAINING = Long.valueOf(Long.parseLong(((Map<String, Object>)userList.get(0)).get("REMAINING").toString()));
        Long yuanbaos = Long.valueOf(Long.parseLong(yuanbao));
        if ((long)REMAINING - (long)yuanbaos < 0L) {
            System.out.println("余额不足！！！");
            return false;
        }
        String saveType = param.getValue5();
        if (StringUtil.isEmpty(saveType)) {
            return false;
        }
        int type = Integer.parseInt(saveType);
        if (StringUtil.isEmpty(user_id)) {
            return false;
        }
        if (StringUtil.isEmpty(yuanbao)) {
            yuanbao = "0";
        }
        BigDecimal userId = new BigDecimal(user_id);
        UserTable userTable = AllServiceUtil.getUserTableService().selectByPrimaryKey(userId);
        Random r = new Random(System.currentTimeMillis());
        ExpensesReceipts expensesReceipts = new ExpensesReceipts();
        expensesReceipts.setErid(new BigDecimal(System.currentTimeMillis() + "" + r.nextInt(9999)));
        expensesReceipts.setPlayeracc(userTable.getUsername());
        expensesReceipts.setSid(userTable.getQid());
        expensesReceipts.setRecharge(new BigDecimal(recharge));
        expensesReceipts.setYuanbao(new BigDecimal(yuanbao));
        expensesReceipts.setType(type);
        expensesReceipts.setQuname(((Map<String, Object>)userList.get(0)).get("ACCOUNT").toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(11, 8);
        expensesReceipts.setPaytime(DateUtil.formatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
        Jedis jedis = RedisPoolUntil.getJedis();
        try {
            ApplyBean applyBean = new ApplyBean();
            applyBean.setUserNameS(expensesReceipts.getPlayeracc());
            applyBean.setRealmoney(expensesReceipts.getRecharge() + "");
            BigDecimal addC = new BigDecimal(applyBean.getRealmoney());
            userTable.setPayintegration(Integer.valueOf((int)userTable.getPayintegration() + addC.intValue()));
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getInlineUserNameMap().get(applyBean.getUserNameS());
            LoginResult login = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (login != null) {
                AllServiceUtil.getUserTableService().updateUser(userTable);
                login.setPaysum(login.getPaysum().add(addC));
                login.setDaypaysum(login.getDaypaysum().add(addC));
                ApplyPayBean applyPayBean = new ApplyPayBean();
                applyPayBean.setAddM(addC);
                expensesReceipts.setRoleid(login.getRole_id());
                expensesReceipts.setBuyroleName(login.getRolename());
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
                    long time = 86400000L * (long)expensesReceipts.getRecharge().intValue();
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
                AllServiceUtil.getRoleTableService().updateRoleWhenExit(login);
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().applyPay(GsonUtil.getGsonUtil().getgson().toJson(applyPayBean)));
                jedis.hset("order_number_control_orno", expensesReceipts.getErid() + "", expensesReceipts.getPaytime() + ":金额" + expensesReceipts.getRecharge());
                jedis.hset("payReturnForpayServer", expensesReceipts.getErid() + "", "Sinmahod=" + GsonUtil.getGsonUtil().getgson().toJson(expensesReceipts) + "");
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
                    expensesReceipts.setRoleid(login.getRole_id());
                    expensesReceipts.setBuyroleName(login.getRolename());
                    if (type == 2) {
                        long time2 = 3600000L * (long)expensesReceipts.getRecharge().intValue();
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
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
            WriteOut.addtxt("充值报错:" + MainServerHandler.getErrorMessage(e2), 9999L);
        }
        RedisPoolUntil.returnResource(jedis);
        AllServiceUtil.getRecordService().insert(new Record(8, GsonUtil.getGsonUtil().getgson().toJson(expensesReceipts)));
        AllServiceUtil.getExpensesReceiptsService().insert(expensesReceipts);
        Map<String, Object> parama = new HashMap<>();
        Long sy = Long.valueOf((long)REMAINING - (long)yuanbaos);
        parama.put("ACCOUNT", ((Map<String, Object>)userList.get(0)).get("ACCOUNT"));
        parama.put("TOTALAMOUNT", TOTALAMOUNT);
        parama.put("REMAINING", sy);
        AllServiceUtil.getRoleTableService().updateUserAmount(parama);
        return true;
    }
    
    public ExpensesReceiptsInfo getReceipts(Param param) {
        String searchType = param.getValue1();
        String searchValue = param.getValue2();
        String type = param.getValue3();
        int pageNum = param.getPageNum();
        int size = param.getPageSize();
        if (size < 15) {
            size = 15;
        }
        ExpensesReceipts expensesReceipts = new ExpensesReceipts();
        if (StringUtil.isNotEmpty(type) || NumberUtils.isDigits(type)) {
            expensesReceipts.setType(Integer.parseInt(type));
        }
        if (StringUtil.isNotEmpty(searchType)) {
            if ("3".equals(searchType) && StringUtil.isNotEmpty(searchValue)) {
                expensesReceipts.setBuyroleName(searchValue);
            }
            if (("2".equals(searchType) && StringUtil.isNotEmpty(searchValue)) || NumberUtils.isDigits(searchValue)) {
                expensesReceipts.setRoleid(new BigDecimal(searchValue));
            }
            if ("1".equals(searchType) && StringUtil.isNotEmpty(searchValue)) {
                expensesReceipts.setPlayeracc(searchValue);
            }
        }
        int total = AllServiceUtil.getExpensesReceiptsService().selectAllTotal(expensesReceipts);
        int page = total / size;
        if (total % size > 0) {
            ++page;
        }
        BasePageHelper.startPage(pageNum, size);
        List<ExpensesReceipts> list = AllServiceUtil.getExpensesReceiptsService().selectAll(expensesReceipts);
        PageInfo<ExpensesReceipts> pageInfo = new PageInfo(list);
        ExpensesReceiptsInfo info = new ExpensesReceiptsInfo();
        info.setList(pageInfo.getList());
        info.setPages(page);
        info.setPageNum(pageNum);
        info.setTotal((long)total);
        return info;
    }
}
