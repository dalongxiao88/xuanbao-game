package org.come.servlet;

import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.bean.LoginResult;
import java.io.PrintWriter;
import org.come.entity.RoleTable;
import com.auth0.jwt.JWTVerifier;
import org.come.until.GsonUtil;
import org.come.entity.UserTable;
import io.netty.channel.ChannelHandlerContext;
import org.come.protocol.ParamTool;
import org.come.action.IAction;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.entity.Haters;
import org.come.until.AllServiceUtil;
import org.come.until.PayMd5;
import org.come.server.GameServer;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UserControlServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
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
        String RoleName = request.getParameter("RoleName");
        String Style = request.getParameter("Style");
        if (Style == null) {
            System.out.println("操作标识为空！！！");
            return;
        }
        int control = Integer.parseInt(Style);
        String Sign = request.getParameter("Sign");
        int YesOrNo = 0;
        String reason = request.getParameter("reason");
        String controlname = request.getParameter("controlname");
        if (PayMd5.encryption(RoleName + Style + GameServer.signNum).equals(Sign)) {
            if (control != 7 && control != 8) {
                RoleTable roleInfo = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(RoleName);
                if (roleInfo == null) {
                    System.out.println("找不到该角色！！！   " + RoleName);
                    return;
                }
                UserTable userInfo = AllServiceUtil.getUserTableService().selectByPrimaryKey(roleInfo.getUser_id());
                if (userInfo == null) {
                    System.out.println("找不到该角色的账号！！！   " + RoleName);
                    return;
                }
                switch (control) {
                    case 1: {
                        YesOrNo = 1;
                        Haters hater = AllServiceUtil.getHatersService().selectByPrimaryKey(roleInfo.getRole_id());
                        if (hater == null) {
                            Haters record = new Haters();
                            record.setRoleid(roleInfo.getRole_id());
                            AllServiceUtil.getHatersService().insert(record);
                            if (GameServer.getRoleNameMap().get(RoleName) != null) {
                                SendMessage.sendMessageByRoleName(RoleName, Agreement.getAgreement().tipAgreement("你已被禁言！！！"));
                            }
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 2: {
                        YesOrNo = 2;
                        if (GameServer.getRoleNameMap().get(RoleName) != null) {
                            SendMessage.sendMessageByRoleName(RoleName, Agreement.getAgreement().serverstopAgreement());
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 3: {
                        YesOrNo = 3;
                        if (GameServer.getRoleNameMap().get(RoleName) != null) {
                            ((IAction)ParamTool.ACTION_MAP.get("accountstop")).action((ChannelHandlerContext)GameServer.getRoleNameMap().get(RoleName), userInfo.getUsername());
                            break;
                        }
                        else {
                            UserTable table = new UserTable();
                            table.setUsername(userInfo.getUsername());
                            table.setActivity(Short.valueOf((short)1));
                            AllServiceUtil.getUserTableService().updateUser(table);
                            AllServiceUtil.getUserTableService().addRufenghaoControl(userInfo, roleInfo.getRolename(), reason, controlname, 1);
                            break;
                        }
                    }
                    case 4: {
                        YesOrNo = 4;
                        Haters hater2 = AllServiceUtil.getHatersService().selectByPrimaryKey(roleInfo.getRole_id());
                        if (hater2 != null) {
                            AllServiceUtil.getHatersService().deleteByPrimaryKey(hater2.getRoleid());
                            if (GameServer.getRoleNameMap().get(RoleName) != null) {
                                SendMessage.sendMessageByRoleName(RoleName, Agreement.getAgreement().tipAgreement("禁言已被解除！！！"));
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        else {
                            break;
                        }
                    }
                    case 5: {
                        YesOrNo = 5;
                        UserTable table2 = new UserTable();
                        table2.setUsername(userInfo.getUsername());
                        table2.setActivity(Short.valueOf((short)0));
                        AllServiceUtil.getUserTableService().updateUser(table2);
                        AllServiceUtil.getUserTableService().addRufenghaoControl(userInfo, roleInfo.getRolename(), reason, controlname, 2);
                        break;
                    }
                    default: {
                        System.out.println("未知的操作！！！");
                        break;
                    }
                }
            }
            else {
                UserTable userTable = AllServiceUtil.getUserTableService().selectForUsername(RoleName);
                if (userTable == null) {
                    System.out.println("找不到该角色的账号！！！   " + RoleName);
                    return;
                }
                if (control == 7) {
                    YesOrNo = 3;
                    if (GameServer.getInlineUserNameMap().get(RoleName) != null) {
                        ((IAction)ParamTool.ACTION_MAP.get("accountstop")).action((ChannelHandlerContext)GameServer.getInlineUserNameMap().get(RoleName), RoleName);
                    }
                    else {
                        UserTable table3 = new UserTable();
                        table3.setUsername(userTable.getUsername());
                        table3.setActivity(Short.valueOf((short)1));
                        AllServiceUtil.getUserTableService().updateUser(table3);
                        AllServiceUtil.getUserTableService().addRufenghaoControl(userTable, null, reason, controlname, 1);
                    }
                }
                else if (control == 8) {
                    YesOrNo = 5;
                    UserTable table3 = new UserTable();
                    table3.setUsername(userTable.getUsername());
                    table3.setActivity(Short.valueOf((short)0));
                    AllServiceUtil.getUserTableService().updateUser(table3);
                    AllServiceUtil.getUserTableService().addRufenghaoControl(userTable, null, reason, controlname, 2);
                }
            }
        }
        else {
            YesOrNo = 6;
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(Integer.valueOf(YesOrNo)));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
    
    public static boolean isNoTalk(ChannelHandlerContext ctx) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo != null) {
            Haters hater = AllServiceUtil.getHatersService().selectByPrimaryKey(roleInfo.getRole_id());
            if (hater != null) {
                SendMessage.sendMessageByRoleName(roleInfo.getRolename(), Agreement.getAgreement().tipAgreement("你已被禁言！！！"));
                return true;
            }
        }
        return false;
    }
}
