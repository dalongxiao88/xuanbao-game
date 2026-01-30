package org.come.servlet;

import java.util.List;
import com.auth0.jwt.JWTVerifier;
import org.come.until.GsonUtil;
import org.come.bean.UserxyandroledhbcrBean;
import org.come.entity.UserxyandroledhbcrEntity;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
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

public class AccountRechargeUserServlet extends HttpServlet
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
        String userid = request.getParameter("userid");
        if (userid != null && !"".equals(userid)) {
            List<UserxyandroledhbcrEntity> selectAccountRechargeUser = AllServiceUtil.getUserTableService().selectAccountRechargeUser(new BigDecimal(userid));
            List<UserxyandroledhbcrBean> beans = new ArrayList<>();
            for (int i = 0; i < selectAccountRechargeUser.size(); ++i) {
                UserxyandroledhbcrEntity entity = (UserxyandroledhbcrEntity)selectAccountRechargeUser.get(i);
                boolean is = true;
                for (int j = 0; j < beans.size(); ++j) {
                    ((UserxyandroledhbcrBean)beans.get(j)).getXdsum().add(entity.getXdsum());
                    ((UserxyandroledhbcrBean)beans.get(j)).getXsum().add(entity.getXsum());
                    ((UserxyandroledhbcrBean)beans.get(j)).getTime().add(entity.getTime());
                    is = false;
                }
                if (is) {
                    beans.add(new UserxyandroledhbcrBean(entity.getUsername(), entity.getUserid(), entity.getXsum(), entity.getXnow(), entity.getXdsum(), entity.getDsum(), entity.getSssum(), entity.getTime()));
                }
            }
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(beans));
            pwPrintWriter.flush();
            pwPrintWriter.close();
        }
    }
    
    @Override
    public void init() throws ServletException {
    }
}
