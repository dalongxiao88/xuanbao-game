package org.come.servlet;

import com.auth0.jwt.JWTVerifier;
import org.come.until.GsonUtil;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import org.come.entity.ImportantgoodsluEntity;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class MonitoringGoodsUpdateServlet extends HttpServlet
{
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(this.getClass());
        out.println(", using the GET method");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
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
        String goodsname = request.getParameter("goodsname");
        String value = request.getParameter("value");
        String type = request.getParameter("type");
        String gid = request.getParameter("gid");
        String id = request.getParameter("id");
        ImportantgoodsluEntity importantgoodsluEntity = new ImportantgoodsluEntity();
        importantgoodsluEntity.setId(new BigDecimal(id));
        importantgoodsluEntity.setGid(new BigDecimal(gid));
        importantgoodsluEntity.setGoodsname(goodsname);
        importantgoodsluEntity.setValue(value);
        importantgoodsluEntity.setDatetime("2019-11-19");
        int result = 2;
        if ("1".equals(type)) {
            if (AllServiceUtil.getImportantgoodtrcordService().deleteImportantGoodsLu(importantgoodsluEntity) == 1) {
                result = 1;
            }
        }
        else if ("2".equals(type)) {
            if (AllServiceUtil.getImportantgoodtrcordService().updateImportantGoodsLu(importantgoodsluEntity) == 1) {
                result = 1;
            }
        }
        else if ("3".equals(type) && AllServiceUtil.getImportantgoodtrcordService().addImportantGoodsLu(importantgoodsluEntity) == 1) {
            result = 1;
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.write(GsonUtil.getGsonUtil().getgson().toJson(Integer.valueOf(result)));
        printWriter.flush();
        printWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
