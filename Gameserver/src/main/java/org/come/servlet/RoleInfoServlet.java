package org.come.servlet;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import com.auth0.jwt.JWTVerifier;
import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.until.GsonUtil;
import org.come.server.GameServer;
import org.come.bean.BackRoleInfo;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.come.entity.RoleTable;
import javax.servlet.http.HttpServlet;

public class RoleInfoServlet extends HttpServlet
{
    RoleTable roleTable;
    private static final long serialVersionUID = 1L;
    
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
        String rolename = request.getParameter("rolename");
        String qid = request.getParameter("qid");
        String pagenum = request.getParameter("pageNum");
        String statues = request.getParameter("statues");
        String plarcess = request.getParameter("username");
        String type = request.getParameter("type");
        if (type == null) {
            type = "0";
        }
        if (rolename == null) {
            rolename = "";
        }
        if (statues == null) {
            statues = "";
        }
        if (plarcess == null) {
            plarcess = "";
        }
        BackRoleInfo list = null;
        (this.roleTable = new RoleTable()).setUserString(this.sureType(type));
        if (qid == null) {
            this.roleTable.setQid(null);
        }
        else {
            this.roleTable.setQid(new BigDecimal(qid));
        }
        if (pagenum == null) {
            pagenum = "1";
        }
        this.roleTable.setStart(((int)Integer.valueOf(pagenum) - 1) * 8);
        this.roleTable.setEnd((int)Integer.valueOf(pagenum) * 8);
        if (statues.equals("3")) {
            this.roleTable.setUnknown("1");
        }
        if (statues.equals("4")) {
            this.roleTable.setActivity(new Short("1"));
        }
        if (statues.equals("5")) {
            this.roleTable.setActivity(new Short("0"));
        }
        if (statues.equals("6")) {
            this.roleTable.setActivity(null);
        }
        if (!rolename.equals("0")) {
            this.roleTable.setRolename(rolename);
        }
        else if (rolename.equals("0")) {
            this.roleTable.setRolename(null);
        }
        if (!plarcess.equals("0")) {
            this.roleTable.setLocalname(plarcess);
        }
        else if (plarcess.equals("0")) {
            this.roleTable.setLocalname(null);
        }
        int page1 = AllServiceUtil.getUserTableService().selectSumForRoleUserHaterNumber(this.roleTable);
        int page2 = page1 / 8;
        if (page1 % 8 > 0) {
            ++page2;
        }
        List<RoleTable> listall = AllServiceUtil.getUserTableService().selectSumForRoleUserHaterList(this.roleTable);
        list = new BackRoleInfo();
        for (RoleTable roleInfo : listall) {
            String status = "";
            if (GameServer.getRoleNameMap().get(roleInfo.getRolename()) != null) {
                status += "/1";
            }
            else {
                status += "/2";
            }
            if (roleInfo.getUnknown() != null) {
                status += "/3";
            }
            else {
                status += "/6";
            }
            if ((short)roleInfo.getActivity() != 0) {
                status += "/4";
            }
            else {
                status += "/5";
            }
            roleInfo.setStatues(status.replaceFirst("/", ""));
            list.setPages(page2);
            list.setPageNum((int)Integer.valueOf(pagenum));
            list.setList(listall);
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(list));
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
