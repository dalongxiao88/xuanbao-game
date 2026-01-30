package org.come.servlet;

import java.util.List;
import java.io.PrintWriter;
import com.auth0.jwt.JWTVerifier;
import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.until.AllServiceUtil;
import org.come.entity.MountRoleUser;
import org.come.until.GsonUtil;
import org.come.entity.SearchMontList;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class MountRoleUserServlet extends HttpServlet
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String zuijiname = request.getParameter("Zuijiname");
        String roleName = request.getParameter("RoleName");
        String pagenum = request.getParameter("Pagenum");
        String style = request.getParameter("style");
        SearchMontList result = new SearchMontList();
        if (zuijiname == null || roleName == null || pagenum == null || style == null) {
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(result));
            pwPrintWriter.flush();
            pwPrintWriter.close();
            return;
        }
        MountRoleUser mru = new MountRoleUser();
        mru.setMountname(zuijiname);
        mru.setRolename(roleName);
        if ("".equals(pagenum)) {
            mru.setPageNum(Integer.valueOf(0));
        }
        else {
            mru.setPageNum(Integer.valueOf(pagenum));
        }
        if ("".equals(style)) {
            mru.setOrderBy("");
        }
        else {
            int n = -1;
            switch (style.hashCode()) {
                case 49: {
                    if (style.equals("1")) {
                        n = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 50: {
                    if (style.equals("2")) {
                        n = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    mru.setOrderBy(" ORDER BY MOUNTNAME ");
                    break;
                }
                case 1: {
                    mru.setOrderBy(" ORDER BY ROLENAME ");
                    break;
                }
            }
        }
        Integer goodCount = AllServiceUtil.getMountService().selectMountCount(mru);
        Integer sumPage = Integer.valueOf(0);
        if ((int)goodCount % 10 == 0) {
            sumPage = Integer.valueOf((int)goodCount / 10);
        }
        else {
            sumPage = Integer.valueOf((int)goodCount / 10 + 1);
        }
        List<MountRoleUser> mountList = AllServiceUtil.getMountService().selectMount(mru);
        result.setMontlist(mountList);
        result.setSumpage((int)sumPage);
        PrintWriter pwPrintWriter2 = response.getWriter();
        pwPrintWriter2.write(GsonUtil.getGsonUtil().getgson().toJson(result));
        pwPrintWriter2.flush();
        pwPrintWriter2.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
