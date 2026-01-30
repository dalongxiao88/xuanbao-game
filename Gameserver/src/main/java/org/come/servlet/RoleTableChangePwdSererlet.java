package org.come.servlet;

import java.io.PrintWriter;
import com.auth0.jwt.JWTVerifier;
import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Stall.AssetUpdate;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.until.PayMd5;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.come.entity.RoleTable;
import javax.servlet.http.HttpServlet;

public class RoleTableChangePwdSererlet extends HttpServlet
{
    private String controlResult;
    RoleTable roleTable;
    
    public RoleTableChangePwdSererlet() {
        this.controlResult = null;
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
        String roleid = request.getParameter("roleid");
        String goodsecret = request.getParameter("goodsecret");
        String secret = request.getParameter("secret");
        String sign = PayMd5.encryption(roleid + goodsecret);
        this.roleTable = new RoleTable();
        if (sign.equals(secret)) {
            LoginResult loginResultTemp = AllServiceUtil.getRoleTableService().selectRoleID(new BigDecimal(roleid));
            this.roleTable.setRole_id(new BigDecimal(roleid));
            this.roleTable.setPassword(goodsecret);
            int flag = AllServiceUtil.getRoleTableService().updateRolePwdForRid(this.roleTable);
            if (flag > 0) {
                this.controlResult = "Success";
                ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getInlineUserNameMap().get(loginResultTemp.getUserName());
                if (null != ctx) {
                    LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                    loginResult.setPassword(goodsecret);
                    AssetUpdate assetUpdate = new AssetUpdate();
                    assetUpdate.setType(25);
                    assetUpdate.setMsg("您成功修改背包解锁码为#G" + goodsecret + ",重新登录后生效!");
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                }
            }
            else {
                this.controlResult = "Erore";
            }
        }
        else {
            this.controlResult = "Erore";
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(this.controlResult));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
