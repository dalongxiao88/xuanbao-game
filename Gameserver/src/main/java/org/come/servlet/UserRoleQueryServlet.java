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
    
    public String sureType(String type) {
        String returnMes = "";
        int n = -1;
        switch (type.hashCode()) {
            case 48: {
                if (type.equals("0")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 49: {
                if (type.equals("1")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 50: {
                if (type.equals("2")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 51: {
                if (type.equals("3")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 52: {
                if (type.equals("4")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 53: {
                if (type.equals("5")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
            case 54: {
                if (type.equals("6")) {
                    n = 6;
                    break;
                }
                else {
                    break;
                }
            }
            case 55: {
                if (type.equals("7")) {
                    n = 7;
                    break;
                }
                else {
                    break;
                }
            }
            case 1567: {
                if (type.equals("10")) {
                    n = 8;
                    break;
                }
                else {
                    break;
                }
            }
            case 1568: {
                if (type.equals("11")) {
                    n = 9;
                    break;
                }
                else {
                    break;
                }
            }
            case 1569: {
                if (type.equals("12")) {
                    n = 10;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                returnMes = "";
                break;
            }
            case 1: {
                returnMes = "order by rolename";
                break;
            }
            case 2: {
                returnMes = "order by LOCALNAME";
                break;
            }
            case 3: {
                returnMes = "order by QID";
                break;
            }
            case 4: {
                returnMes = "order by PASSWORD";
                break;
            }
            case 5: {
                returnMes = "order by GOLD desc";
                break;
            }
            case 6: {
                returnMes = "order by GRADE desc";
                break;
            }
            case 7: {
                returnMes = "order by HP desc";
                break;
            }
            case 8: {
                returnMes = "order by MP desc";
                break;
            }
            case 9: {
                returnMes = "order by CODECARD desc";
                break;
            }
            case 10: {
                returnMes = "order by PAYINTEGRATION desc";
                break;
            }
        }
        return returnMes;
    }
}
