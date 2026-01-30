package org.come.servlet;

import java.io.PrintWriter;
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

public class UpdateVersionServlet extends HttpServlet
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
        if (version == null || "".equals(version)) {
            String phoneVer = AllServiceUtil.getAppVersionService().selectPhoneVersion();
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write("当前版本号: " + phoneVer);
            pwPrintWriter.flush();
            pwPrintWriter.close();
        }
        else {
            String nowversion = "version-" + version;
            int res = AllServiceUtil.getAppVersionService().updatePhoneVersion(nowversion);
            if (res >= 1) {
                PrintWriter pwPrintWriter2 = response.getWriter();
                pwPrintWriter2.write("版本号修改成: " + nowversion);
                pwPrintWriter2.flush();
                pwPrintWriter2.close();
            }
            else {
                PrintWriter pwPrintWriter2 = response.getWriter();
                pwPrintWriter2.write("修改失败!!");
                pwPrintWriter2.flush();
                pwPrintWriter2.close();
            }
        }
    }
    
    @Override
    public void init() throws ServletException {
    }
}
