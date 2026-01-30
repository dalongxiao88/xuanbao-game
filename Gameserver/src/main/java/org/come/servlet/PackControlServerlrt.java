package org.come.servlet;

import java.util.List;
import java.io.PrintWriter;
import com.auth0.jwt.JWTVerifier;
import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import org.come.entity.GoodsRoleUser;
import org.come.until.GsonUtil;
import org.come.entity.SearchGoodstableList;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class PackControlServerlrt extends HttpServlet
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
        String goodsname = request.getParameter("Goodsname");
        String RoleName = request.getParameter("RoleName");
        String Goodsvalue = request.getParameter("Goodsvalue");
        String Statues = request.getParameter("Statues");
        String goodsId = request.getParameter("goodsId");
        String Pagenum = request.getParameter("Pagenum");
        String style = request.getParameter("style");
        SearchGoodstableList result = new SearchGoodstableList();
        if (goodsname == null || RoleName == null || Goodsvalue == null || Statues == null || goodsId == null || Pagenum == null || style == null) {
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(result));
            pwPrintWriter.flush();
            pwPrintWriter.close();
            return;
        }
        GoodsRoleUser goodsRoleUser = new GoodsRoleUser();
        goodsRoleUser.setGoodsname(goodsname);
        goodsRoleUser.setRolename(RoleName);
        goodsRoleUser.setValue(Goodsvalue);
        if ("".equals(goodsId)) {
            goodsRoleUser.setGoodsid(new BigDecimal(0));
        }
        else {
            goodsRoleUser.setGoodsid(new BigDecimal(goodsId));
        }
        if ("".equals(Pagenum)) {
            goodsRoleUser.setPageNow(Integer.valueOf(0));
        }
        else {
            goodsRoleUser.setPageNow(Integer.valueOf(Pagenum));
        }
        if ("0".equals(style)) {
            goodsRoleUser.setOrderBy("");
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
                case 52: {
                    if (style.equals("4")) {
                        n = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 53: {
                    if (style.equals("5")) {
                        n = 4;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n) {
                case 0: {
                    goodsRoleUser.setOrderBy(" ORDER BY GOODSNAME ");
                    break;
                }
                case 1: {
                    goodsRoleUser.setOrderBy(" ORDER BY GOODSID ");
                    break;
                }
                case 2: {
                    goodsRoleUser.setOrderBy(" ORDER BY VALUE ");
                    break;
                }
                case 3: {
                    goodsRoleUser.setOrderBy(" ORDER BY USETIME desc");
                    break;
                }
                case 4: {
                    goodsRoleUser.setOrderBy(" ORDER BY ROLENAME ");
                    break;
                }
            }
        }
        if ("".equals(Statues)) {
            goodsRoleUser.setStatus("");
        }
        else {
            int n2 = -1;
            switch (Statues.hashCode()) {
                case 54: {
                    if (Statues.equals("6")) {
                        n2 = 0;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 55: {
                    if (Statues.equals("7")) {
                        n2 = 1;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 56: {
                    if (Statues.equals("8")) {
                        n2 = 2;
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 57: {
                    if (Statues.equals("9")) {
                        n2 = 3;
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
            switch (n2) {
                case 0: {
                    goodsRoleUser.setStatus("0");
                    break;
                }
                case 1: {
                    goodsRoleUser.setStatus("1");
                    break;
                }
                case 2: {
                    goodsRoleUser.setStatus("2");
                    break;
                }
                case 3: {
                    goodsRoleUser.setStatus("");
                    break;
                }
            }
        }
        Integer goodCount = AllServiceUtil.getGoodsRoleUsertService().selectGoodsCount(goodsRoleUser);
        Integer sumPage = Integer.valueOf(0);
        if ((int)goodCount % 10 == 0) {
            sumPage = Integer.valueOf((int)goodCount / 10);
        }
        else {
            sumPage = Integer.valueOf((int)goodCount / 10 + 1);
        }
        List<GoodsRoleUser> goodsList = AllServiceUtil.getGoodsRoleUsertService().selectGoodsByPage(goodsRoleUser);
        result.setListGoodsTable(goodsList);
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
