package org.come.servlet;

import java.io.PrintWriter;
import com.auth0.jwt.JWTVerifier;
import org.come.until.GsonUtil;
import org.come.nettyClient.UrlUntil;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import org.come.until.PayMd5;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.come.entity.UserTable;
import javax.servlet.http.HttpServlet;

public class UsertableSafenumberServerlet extends HttpServlet
{
    private String controlResult;
    private UserTable userTable;
    
    public UsertableSafenumberServerlet() {
        this.controlResult = null;
    }
    
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
        String userid = request.getParameter("userid");
        String goodsecret = request.getParameter("goodsecret");
        String secret = request.getParameter("secret");
        String type = request.getParameter("type");
        String getsecret = PayMd5.encryption(userid + goodsecret);
        this.userTable = new UserTable();
        String account_flag = AllServiceUtil.getUserTableService().selectUserFlagById(new BigDecimal(userid));
        if (getsecret.equals(secret)) {
            int flag = 0;
            if (type.equals("1")) {
                this.userTable.setUser_id(new BigDecimal(userid));
                this.userTable.setSafety(goodsecret);
                flag = AllServiceUtil.getUserTableService().updateUsterWithUid(this.userTable);
                UrlUntil.accountAction("updateSafely", account_flag, this.userTable.getSafety());
            }
            else if (type.equals("2")) {
                this.userTable.setUser_id(new BigDecimal(userid));
                this.userTable.setUserpwd(goodsecret);
                flag = AllServiceUtil.getUserTableService().updateUsterWithUidforuserpasswd(this.userTable);
                UrlUntil.accountAction("updatePasw", account_flag, this.userTable.getUserpwd());
            }
            if (flag > 0) {
                this.controlResult = "Success";
            }
            else {
                this.controlResult = "Erore";
            }
        }
        else {
            this.controlResult = "Erore";
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(this.controlResult));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
