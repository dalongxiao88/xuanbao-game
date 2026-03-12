package org.come.servlet;

import java.io.PrintWriter;
import java.util.List;
import com.auth0.jwt.JWTVerifier;
import com.gl.controller.UserController;
import com.gl.model.Result;
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
import org.come.bean.UsertableListBean;
import org.come.entity.UserTable;
import javax.servlet.http.HttpServlet;

public class UserTableServerlet extends HttpServlet
{
    UserTable userTable;
    private UsertableListBean usertableListBean;
    int page;
    int pagenowget;
    
    public UserTableServerlet() {
        this.page = 0;
        this.pagenowget = 0;
    }
    
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
            // 安全修复：统一返回标准拒绝响应，替换原有不当文本内容。
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
        String username = request.getParameter("Username");
        String Pagenow = request.getParameter("Pagenow");
        String statues = request.getParameter("statues");
        String type = request.getParameter("type");
        String userPasw = request.getParameter("userPasw");
        String safePasw = request.getParameter("safePasw");
        String userip = request.getParameter("userip");
        String phone = request.getParameter("phone");
        (this.userTable = new UserTable()).setUseString(this.sureType(type));
        if (username != null) {
            this.userTable.setUsername(username);
        }
        else {
            this.userTable.setUsername(null);
        }
        if (statues.equals("1")) {
            this.userTable.setActivity(Short.valueOf((short)1));
        }
        else {
            this.userTable.setActivity(Short.valueOf((short)0));
        }
        if (Pagenow != null) {
            this.userTable.setStart(((int)Integer.valueOf(Pagenow) - 1) * 8);
            this.userTable.setEnd((int)Integer.valueOf(Pagenow) * 8);
        }
        if (userPasw != null) {
            this.userTable.setUserpwd(userPasw);
        }
        if (safePasw != null) {
            this.userTable.setSafety(safePasw);
        }
        if (userip != null) {
            this.userTable.setLoginip(userip);
            this.userTable.setRegisterip(userip);
        }
        if (phone != null) {
            this.userTable.setPhonenumber(phone);
        }
        List<UserTable> listForCondition = AllServiceUtil.getUserTableService().selectForConditionForUsertable(this.userTable);
        this.page = AllServiceUtil.getUserTableService().selectUsterTableForConcition(this.userTable);
        this.pagenowget = this.page / 8;
        if (this.page % 8 > 0) {
            ++this.pagenowget;
        }
        (this.usertableListBean = new UsertableListBean()).setUsertablelist(listForCondition);
        this.usertableListBean.setSumpage(this.pagenowget);
        this.usertableListBean.setNowpage((int)Integer.valueOf(Pagenow));
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(this.usertableListBean));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
    
    /**
     * TRACE[S-02][2026-03-13]: 用语义化字符串分支替换反编译残留的 hashCode 分派。
     * 保持原有排序语义不变，同时补充空值保护，避免继续依赖反编译结构。
     */
    public String sureType(String type) {
        if (type == null) {
            return "";
        }
        switch (type) {
            case "1":
                return "order by QID";
            case "2":
                return "order by SAFETY";
            case "3":
                return "order by USERPWD";
            case "4":
                return "order by USERNAME";
            case "5":
                return "order by REGISTERIP desc";
            case "6":
                return "order by LOGINIP desc";
            case "0":
            default:
                return "";
        }
    }
}


