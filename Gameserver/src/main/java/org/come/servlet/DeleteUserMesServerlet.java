package org.come.servlet;

import java.io.PrintWriter;

import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.entity.UserTable;
import com.auth0.jwt.JWTVerifier;
import org.come.until.AllServiceUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class DeleteUserMesServerlet extends HttpServlet
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
        String Username = request.getParameter("Username");
        String Uid = request.getParameter("Uid");
        String Controlstyle = "";
        if (Username != null) {
            UserTable userTable = AllServiceUtil.getUserTableService().selectForUsername(Username);
            if (userTable != null) {
                int a = AllServiceUtil.getUserTableService().delectUsertableForUsername(Username);
                if (a > 0) {
                    int a2 = AllServiceUtil.getUserTableService().deleteRoletableForUid(userTable.getUser_id());
                    if (a2 > 0) {
                        Controlstyle = "1";
                    }
                    else {
                        Controlstyle = "2";
                    }
                }
                else {
                    Controlstyle = "2";
                }
            }
            else {
                Controlstyle = "3";
            }
        }
        else {
            Controlstyle = "3";
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(Controlstyle);
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
