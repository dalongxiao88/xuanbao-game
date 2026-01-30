package org.come.servlet;

import java.util.List;
import java.io.PrintWriter;
import com.auth0.jwt.JWTVerifier;
import org.come.until.AllServiceUtil;
import org.come.entity.RolesummoningRoleUser;
import org.come.until.GsonUtil;
import org.come.entity.SearchSumminglist;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class RoleSummoningRoleUserServlet extends HttpServlet
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
        String summingId = request.getParameter("summingId");
        String roleName = request.getParameter("RoleName");
        String skill = request.getParameter("skill");
        String pagenum = request.getParameter("Pagenum");
        String style = request.getParameter("style");
        SearchSumminglist result = new SearchSumminglist();
        if (summingId == null || roleName == null || skill == null || pagenum == null || style == null) {
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(result));
            pwPrintWriter.flush();
            pwPrintWriter.close();
            return;
        }
        RolesummoningRoleUser rru = new RolesummoningRoleUser();
        rru.setSummoningid(summingId);
        rru.setRolename(roleName);
        rru.setSkill(skill);
        if ("".equals(pagenum)) {
            rru.setPageNow(Integer.valueOf(0));
        }
        else {
            rru.setPageNow(Integer.valueOf(pagenum));
        }
        if ("0".equals(style)) {
            rru.setOrderBy("");
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
                case 51: {
                    if (style.equals("3")) {
                        n = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    rru.setOrderBy(" ORDER BY SUMMONINGID ");
                    break;
                }
                case 1: {
                    rru.setOrderBy(" ORDER BY SUMMONINGNAME ");
                    break;
                }
                case 2: {
                    rru.setOrderBy(" ORDER BY ROLENAME ");
                    break;
                }
            }
        }
        Integer goodCount = AllServiceUtil.getRoleSummoningService().selectRsRUCount(rru);
        Integer sumPage = Integer.valueOf(0);
        if ((int)goodCount % 10 == 0) {
            sumPage = Integer.valueOf((int)goodCount / 10);
        }
        else {
            sumPage = Integer.valueOf((int)goodCount / 10 + 1);
        }
        List<RolesummoningRoleUser> rsRUList = AllServiceUtil.getRoleSummoningService().selectRsRU(rru);
        result.setRolesummingList(rsRUList);
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
