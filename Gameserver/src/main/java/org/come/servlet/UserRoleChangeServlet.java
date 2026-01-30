package org.come.servlet;

import java.io.PrintWriter;
import org.come.entity.UserTable;
import org.come.entity.RoleTable;
import java.util.List;
import com.auth0.jwt.JWTVerifier;
import org.come.until.GsonUtil;
import org.come.bean.UserRoleChangeBean;
import org.come.until.AllServiceUtil;
import java.util.ArrayList;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UserRoleChangeServlet extends HttpServlet
{
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user1 = (User)request.getSession().getAttribute("BG_NAME_XY2");
        Object manger = request.getSession().getAttribute("manger");
        String token = request.getHeader("token");
        if (user1 == null) {
            response.sendError(401, "用户登录验证不正确");
            return;
        }
        if (token == null) {
            response.sendError(401, "用户登录验证不正确");
            return;
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user1.getPassword())).build();
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
        String userName = request.getParameter("userName");
        String otherUserName = request.getParameter("otherUserName");
        String type = request.getParameter("type");
        if (type.equals("1")) {
            List<RoleTable> roleList = new ArrayList<>();
            List<RoleTable> otherRoleList = new ArrayList<>();
            if (userName != null && !"".equals(userName)) {
                UserTable user2 = AllServiceUtil.getUserTableService().selectForUsername(userName);
                if (user2 != null) {
                    roleList = AllServiceUtil.getUserTableService().selectAllRoleTable(userName);
                }
            }
            if (otherUserName != null && !"".equals(otherUserName)) {
                UserTable user2 = AllServiceUtil.getUserTableService().selectForUsername(otherUserName);
                if (user2 != null) {
                    otherRoleList = AllServiceUtil.getUserTableService().selectAllRoleTable(otherUserName);
                }
            }
            UserRoleChangeBean roleChangeBean = new UserRoleChangeBean();
            roleChangeBean.setRoleList(roleList);
            roleChangeBean.setOtherRoleList(otherRoleList);
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(roleChangeBean));
            pwPrintWriter.flush();
            pwPrintWriter.close();
        }
        else if (type.equals("2")) {
            String roleId = request.getParameter("roleId");
            int con = 0;
            if (otherUserName != null && !"".equals(otherUserName)) {
                UserTable user2 = AllServiceUtil.getUserTableService().selectForUsername(otherUserName);
                if (user2 != null) {
                    List<RoleTable> otherRoleList2 = AllServiceUtil.getUserTableService().selectAllRoleTable(otherUserName);
                    if (otherRoleList2.size() >= 5) {
                        con = -11;
                    }
                    else {
                        con = AllServiceUtil.getUserTableService().roleChangeUser(userName, user2.getUser_id(), roleId);
                    }
                }
                else {
                    con = -12;
                }
            }
            PrintWriter pwPrintWriter2 = response.getWriter();
            pwPrintWriter2.write(GsonUtil.getGsonUtil().getgson().toJson(Integer.valueOf(con)));
            pwPrintWriter2.flush();
            pwPrintWriter2.close();
        }
    }
    
    @Override
    public void init() throws ServletException {
    }
}
