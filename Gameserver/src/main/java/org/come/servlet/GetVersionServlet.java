package org.come.servlet;

import org.come.entity.AppVersion;
import java.util.List;
import java.io.PrintWriter;
import com.auth0.jwt.JWTVerifier;
import org.come.until.GsonUtil;
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

public class GetVersionServlet extends HttpServlet
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
        String version = request.getParameter("version");
        String sign = request.getParameter("sign");
        if ("1".equals(sign)) {
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write("");
            pwPrintWriter.flush();
            pwPrintWriter.close();
        }
        else {
            List<AppVersion> appVersion = AllServiceUtil.getAppVersionService().selectVersionUrl(version, sign);
            String appVerJson = GsonUtil.getGsonUtil().getgson().toJson(appVersion);
            PrintWriter pwPrintWriter2 = response.getWriter();
            pwPrintWriter2.write(appVerJson);
            pwPrintWriter2.flush();
            pwPrintWriter2.close();
        }
    }
    
    @Override
    public void init() throws ServletException {
    }
}
