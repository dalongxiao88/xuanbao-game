package org.come.servlet;

import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.bean.Goodsbuyrecordsumbean;
import java.util.List;
import com.auth0.jwt.JWTVerifier;
import org.come.until.GsonUtil;
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

/**
 * 仙玉商品查询接口
 * 功能：查询仙玉商品购买记录，支持按时间、商品名称、类型等条件筛选
 * 用于后台管理系统查看商品交易数据和统计信息
 */
public class XianYuGoodsServlet extends HttpServlet
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
    
    /**
     * 处理POST请求
     * 功能：查询闲鱼商品购买记录，支持多条件筛选和分页
     *
     * @param request HTTP请求对象，包含time(时间)、goodsname(商品名)、page(页码)、type(类型)参数
     * @param response HTTP响应对象
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // IP访问控制检查
        // 功能：验证请求来源IP是否在白名单中，防止未授权访问后台管理接口
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            // 修复：替换不当响应内容为专业的JSON错误响应
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write("{\"code\":403,\"message\":\"Access denied\"}");
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
        String time = request.getParameter("time");
        String goodsname = request.getParameter("goodsname");
        String page = request.getParameter("page");
        String type = request.getParameter("type");
        List<Goodsbuyrecordsumbean> selectXianYuGoodsbuy = AllServiceUtil.getGoodsTableService().selectXianYuGoodsbuy(time, goodsname, Integer.parseInt(page), type);
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(selectXianYuGoodsbuy));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
