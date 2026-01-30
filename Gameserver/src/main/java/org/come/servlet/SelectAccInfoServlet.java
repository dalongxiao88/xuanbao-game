package org.come.servlet;

import java.io.PrintWriter;
import java.util.List;
import com.auth0.jwt.JWTVerifier;
import java.math.BigDecimal;

import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.redis.RedisCacheUtil;
import org.come.entity.UserTable;
import org.come.bean.Account;
import org.come.until.GsonUtil;
import java.util.ArrayList;
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

public class SelectAccInfoServlet extends HttpServlet
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
        String type = request.getParameter("type");
        if (!"autoReg".equals(type)) {
            if (type.equals("all")) {
                List<UserTable> list = AllServiceUtil.getUserTableService().findAllUser();
                if (list == null) {
                    list = new ArrayList<>();
                }
                PrintWriter pwPrintWriter = response.getWriter();
                pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(list));
                pwPrintWriter.flush();
                pwPrintWriter.close();
            }
            else if (type.equals("one")) {
                String userName = request.getParameter("userName");
                UserTable result = AllServiceUtil.getUserTableService().findUserByUserNameAndUserPassword(userName, null);
                List<UserTable> list2 = new ArrayList<>();
                if (result != null) {
                    list2.add(result);
                }
                PrintWriter pwPrintWriter2 = response.getWriter();
                pwPrintWriter2.write(GsonUtil.getGsonUtil().getgson().toJson(list2));
                pwPrintWriter2.flush();
                pwPrintWriter2.close();
            }
        }
        else {
            String userName = request.getParameter("info");
            Account ac = (Account)GsonUtil.getGsonUtil().getgson().fromJson(userName, Account.class);
            String username = ac.getAc_account();
            String password = ac.getAc_pasw();
            String safely = ac.getAc_safely();
            String userflag = ac.getAc_flag();
            String tuiji = ac.getAc_tuijian();
            String phone = ac.getAc_phone();
            String registerip = ac.getAc_regip();
            UserTable usertable = AllServiceUtil.getUserTableService().selectByFlag(userflag);
            if (usertable == null) {
                while (true) {
                    UserTable result2 = AllServiceUtil.getUserTableService().findUserByUserNameAndUserPassword(username, null);
                    if (result2 == null) {
                        break;
                    }
                    else {
                        username += "zr";
                    }
                }
                List<BigDecimal> sid = AllServiceUtil.getOpenareatableService().selectTuijiNum(tuiji);
                UserTable userTable = new UserTable();
                userTable.setUser_id(RedisCacheUtil.getUser_pk());
                userTable.setUsername(username);
                userTable.setUserpwd(password);
                userTable.setSafety(safely);
                userTable.setFlag(userflag);
                userTable.setTuiji(tuiji);
                userTable.setQid((BigDecimal)sid.get(0));
                userTable.setPhonenumber(phone);
                userTable.setRegisterip(registerip);
                int isSuccess = AllServiceUtil.getUserTableService().insertIntoUser(userTable);
                String res = "";
                if (isSuccess <= 0) {
                    res = "no";
                }
                else {
                    res = "yes";
                }
                PrintWriter pwPrintWriter3 = response.getWriter();
                pwPrintWriter3.write(res);
                pwPrintWriter3.flush();
                pwPrintWriter3.close();
                return;
            }
            else {
                PrintWriter pwPrintWriter4 = response.getWriter();
                pwPrintWriter4.write("exist");
                pwPrintWriter4.flush();
                pwPrintWriter4.close();
            }
        }
    }
    
    @Override
    public void init() throws ServletException {
    }
}
