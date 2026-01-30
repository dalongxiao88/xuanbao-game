package org.come.servlet;

import java.io.PrintWriter;
import com.auth0.jwt.JWTVerifier;
import org.come.nettyClient.UrlUntil;
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

public class IpAddressControlServerlet extends HttpServlet
{
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String conttype = "3";
        String IPAddress = request.getParameter("IPAddress");
        String type = request.getParameter("type");
        if (!IPAddress.equals("") && IPAddress != null) {
            conttype = "3";
            if (type.equals("2")) {
                int a = AllServiceUtil.getUserTableService().insertFromIpaddressmac(IPAddress);
                if (a > 0) {
                    UrlUntil.ipAction("banIp", IPAddress);
                    conttype = "1";
                }
                else {
                    conttype = "3";
                }
            }
            if (type.equals("3")) {
                int a = AllServiceUtil.getUserTableService().deleteFromIpaddressmac(IPAddress);
                if (a > 0) {
                    UrlUntil.ipAction("liftIp", IPAddress);
                    conttype = "1";
                }
                else {
                    conttype = "3";
                }
            }
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(conttype);
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
