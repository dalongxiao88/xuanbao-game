package org.come.servlet;

import java.io.PrintWriter;
import java.util.Map;
import com.auth0.jwt.JWTVerifier;
import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.server.GameServer;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.ChongjipackBean;
import java.util.HashMap;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UpdateChongjipackServlet extends HttpServlet
{
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        Map<String, Object> returnData = new HashMap<>();
        String chongjipack = request.getParameter("chongjipack");
        String type = request.getParameter("type");
        ChongjipackBean chongjipackBean = (ChongjipackBean)GsonUtil.getGsonUtil().getgson().fromJson(chongjipack, ChongjipackBean.class);
        if (type.equals("insert")) {
            AllServiceUtil.getChongjipackServeice().insertChongjipack(chongjipackBean);
        }
        else if (type.equals("del")) {
            String id = request.getParameter("id");
            AllServiceUtil.getChongjipackServeice().deleteChongjipack(Integer.valueOf(Integer.parseInt(id)));
        }
        else {
            AllServiceUtil.getChongjipackServeice().updateChongjipack(chongjipackBean);
        }
        GameServer.refreshBean();
        returnData.put("status", Integer.valueOf(200));
        PrintWriter printWriter = response.getWriter();
        printWriter.write(GsonUtil.getGsonUtil().getgson().toJson(returnData));
        printWriter.flush();
        printWriter.close();
    }
}
