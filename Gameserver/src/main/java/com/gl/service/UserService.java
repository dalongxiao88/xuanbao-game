package com.gl.service;

import org.apache.commons.httpclient.util.DateUtil;
import java.util.Date;
import java.util.Calendar;
import org.come.server.GameServer;
import org.come.entity.RoleTable;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import org.come.redis.RedisCacheUtil;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.entity.UserTable;
import com.gl.model.User;

public class UserService
{
    public static final String USERNAME = "BG_NAME_XY2";
    
    public String register(User user, String clientIP) {
        UserTable userTable = new UserTable();
        userTable.setUsername(user.getUserName());
        userTable.setUserpwd(user.getPassword());
        userTable.setSafety(user.getSecurity());
        userTable.setTuiji(user.getRecommend());
        if ("".equals(userTable.getUsername()) || "".equals(userTable.getUserpwd()) || "".equals(userTable.getSafety())) {
            return "信息不可为空";
        }
        String checkUserAcc = this.checkUserAcc(userTable.getUsername());
        if (!"true".equals(checkUserAcc)) {
            return checkUserAcc;
        }
        if (userTable.getUserpwd().length() < 6 || userTable.getUserpwd().length() > 16) {
            return "密码不可少于6位 不可超过16位";
        }
        if (userTable.getSafety().length() < 6 || userTable.getSafety().length() > 16) {
            return "安全码不可少于6位 不可超过16位";
        }
        String tuiji = userTable.getTuiji();
        if (tuiji == null || "".equals(tuiji)) {
            return "请输入推荐码";
        }
        List<BigDecimal> sid = AllServiceUtil.getOpenareatableService().selectTuijiNum(tuiji);
        if (sid.size() == 0) {
            return "没有找到该推荐码,请检查是否正确!";
        }
        userTable.setQid((BigDecimal)sid.get(0));
        UserTable sameUser = AllServiceUtil.getUserTableService().findUserByUserNameAndUserPassword(userTable.getUsername(), null);
        if (sameUser == null) {
            userTable.setRegisterip(clientIP);
            userTable.setUser_id(RedisCacheUtil.getUser_pk());
            int isSuccess = AllServiceUtil.getUserTableService().insertIntoUser(userTable);
            if (isSuccess > 0) {
                return "";
            }
        }
        else if (sameUser != null) {
            return "该账号已存在";
        }
        return "注册信息有误";
    }
    
    public String checkUserAcc(String acc) {
        if (acc.length() < 8 || acc.length() > 20) {
            return "账号格式必须为8-20个字母和数字";
        }
        if (this.check(acc)) {
            return "true";
        }
        return "账号不可为纯字母、纯数字或带有符号!";
    }
    
    public boolean check(String acc) {
        String reg = "^(\\d+[A-Za-z]+[A-Za-z0-9]*)|([A-Za-z]+\\d+[A-Za-z0-9]*)$";
        return acc.matches(reg);
    }
    
    public List<Map<String, String>> getData() {
        List<Map<String, String>> pmap = new ArrayList<>();
        Map<String, String> map = new LinkedHashMap<>();
        int palyTotal = AllServiceUtil.getUserTableService().selectSumForRoleUserHaterNumber(new RoleTable());
        map.put("icon", "el-icon-s-custom");
        map.put("title", "游戏内玩家总数");
        map.put("total", palyTotal + "");
        map.put("bgColor", "#ebcc6f");
        pmap.add(map);
        int goodsTotal = GameServer.getAllGoodsMap().size();
        map = new LinkedHashMap<>();
        map.put("icon", "el-icon-s-shop");
        map.put("title", "物品种类总数");
        map.put("total", goodsTotal + "");
        map.put("bgColor", "#3acaa9");
        pmap.add(map);
        UserTable userTable = new UserTable();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(11, 8);
        calendar.add(5, -3);
        userTable.setUSERLASTLOGIN(DateUtil.formatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
        int userTotal = AllServiceUtil.getUserTableService().selectUsterTableForConcition(userTable);
        map = new LinkedHashMap<>();
        map.put("icon", "el-icon-s-comment");
        map.put("title", "三日内活跃玩家");
        map.put("total", userTotal + "");
        map.put("bgColor", "#67c4ed");
        pmap.add(map);
        map = new LinkedHashMap<>();
        map.put("icon", "el-icon-s-check");
        map.put("title", "当前在线玩家");
        map.put("total", GameServer.getAllLoginRole().size() + "");
        map.put("bgColor", "#f56c6c");
        pmap.add(map);
        return pmap;
    }
}
