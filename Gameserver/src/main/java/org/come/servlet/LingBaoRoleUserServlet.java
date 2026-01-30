package org.come.servlet;

import java.util.List;
import java.io.PrintWriter;
import com.auth0.jwt.JWTVerifier;
import org.come.until.AllServiceUtil;
import org.come.entity.LingbaoRoleUser;
import org.come.until.GsonUtil;
import org.come.entity.SearchLingbaoList;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class LingBaoRoleUserServlet extends HttpServlet
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
        String lingbaoname = request.getParameter("Lingbaoname");
        String roleName = request.getParameter("RoleName");
        String pagenum = request.getParameter("Pagenum");
        String style = request.getParameter("style");
        SearchLingbaoList result = new SearchLingbaoList();
        if (lingbaoname == null || roleName == null || pagenum == null || style == null) {
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(result));
            pwPrintWriter.flush();
            pwPrintWriter.close();
            return;
        }
        LingbaoRoleUser lru = new LingbaoRoleUser();
        lru.setBaoname(lingbaoname);
        lru.setRolename(roleName);
        if ("".equals(pagenum)) {
            lru.setPageNow(Integer.valueOf(0));
        }
        else {
            lru.setPageNow(Integer.valueOf(pagenum));
        }
        if ("0".equals(style)) {
            lru.setOrderBy("");
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
                    lru.setOrderBy(" ORDER BY BAONAME ");
                    break;
                }
                case 1: {
                    lru.setOrderBy(" ORDER BY ROLENAME ");
                    break;
                }
            }
        }
        Integer lruCount = AllServiceUtil.getLingbaoService().selectLingBaoRUCount(lru);
        List<LingbaoRoleUser> lruList = AllServiceUtil.getLingbaoService().selectLingBaoRU(lru);
        Integer sumpage = Integer.valueOf(0);
        if ((int)lruCount % 10 == 0) {
            sumpage = Integer.valueOf((int)lruCount / 10);
        }
        else {
            sumpage = Integer.valueOf((int)lruCount / 10 + 1);
        }
        result.setLingbaolist(lruList);
        result.setSumpage((int)sumpage);
        PrintWriter pwPrintWriter2 = response.getWriter();
        pwPrintWriter2.write(GsonUtil.getGsonUtil().getgson().toJson(result));
        pwPrintWriter2.flush();
        pwPrintWriter2.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
