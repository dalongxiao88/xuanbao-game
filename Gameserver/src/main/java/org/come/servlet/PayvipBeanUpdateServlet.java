package org.come.servlet;

import java.io.PrintWriter;
import com.auth0.jwt.JWTVerifier;
import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.server.GameServer;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.PayvipBean;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class PayvipBeanUpdateServlet extends HttpServlet
{
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
        String type = request.getParameter("Type");
        String Gson = request.getParameter("Gson");
        PayvipBean payvipBean = (PayvipBean)GsonUtil.getGsonUtil().getgson().fromJson(Gson, PayvipBean.class);
        String Controlstyle = "";
        int aa = Integer.parseInt(type);
        switch (aa) {
            case 1: {
                int a = AllServiceUtil.getPayvipBeanServer().updatePayvioBean(payvipBean);
                if (a > 0) {
                    Controlstyle = "1";
                    break;
                }
                else {
                    Controlstyle = "2";
                    break;
                }
            }
            case 2: {
                int a2 = AllServiceUtil.getPayvipBeanServer().deletePayvioBean(payvipBean.getId());
                if (a2 > 0) {
                    Controlstyle = "1";
                    break;
                }
                else {
                    Controlstyle = "2";
                    break;
                }
            }
            case 3: {
                int a3 = AllServiceUtil.getPayvipBeanServer().insertPayvioBean(payvipBean);
                if (a3 > 0) {
                    Controlstyle = "1";
                    break;
                }
                else {
                    Controlstyle = "2";
                    break;
                }
            }
        }
        GameServer.refreshBean();
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(Controlstyle);
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
