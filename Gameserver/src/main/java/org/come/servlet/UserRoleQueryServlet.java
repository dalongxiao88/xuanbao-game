package org.come.servlet;

import java.io.PrintWriter;
import org.come.entity.UserTable;
import org.come.bean.LoginResult;
import com.gl.model.Result;
import com.auth0.jwt.JWTVerifier;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import com.gl.controller.UserController;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UserRoleQueryServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
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
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            return;
        }
        String type = request.getParameter("type");
        String rolename = request.getParameter("rolename");
        String roleid = request.getParameter("roleid");
        String userid = request.getParameter("userid");
        String res = "";
        if ("1".equals(type)) {
            LoginResult role = AllServiceUtil.getRoleTableService().selectRoleName(rolename);
            if (role == null) {
                res = "roleid=0";
            }
            else {
                res = "roleid=" + role.getRole_id();
            }
        }
        else if ("2".equals(type)) {
            LoginResult role = AllServiceUtil.getRoleTableService().selectRoleID(new BigDecimal(roleid));
            if (role == null) {
                res = "rolename=0";
            }
            else {
                res = "rolename=" + role.getRolename();
            }
        }
        else if ("3".equals(type)) {
            UserTable usertable = AllServiceUtil.getUserTableService().selectByPrimaryKey(new BigDecimal(userid));
            if (usertable == null) {
                res = "userAcc=&userMoney=0";
            }
            else {
                res = "userAcc=" + usertable.getUsername() + "&userMoney=" + usertable.getUsermoney();
            }
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(res);
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
    
    /**
     * TRACE[S-03][2026-03-13]: 去除反编译残留的 hashCode 分派结构。
     * 当前方法仍保留原有排序码到排序语句的映射，仅把实现恢复为可维护的字符串分支。
     */
    public String sureType(String type) {
        if (type == null) {
            return "";
        }
        switch (type) {
            case "1":
                return "order by rolename";
            case "2":
                return "order by LOCALNAME";
            case "3":
                return "order by QID";
            case "4":
                return "order by PASSWORD";
            case "5":
                return "order by GOLD desc";
            case "6":
                return "order by GRADE desc";
            case "7":
                return "order by HP desc";
            case "10":
                return "order by MP desc";
            case "11":
                return "order by CODECARD desc";
            case "12":
                return "order by PAYINTEGRATION desc";
            case "0":
            default:
                return "";
        }
    }
}
