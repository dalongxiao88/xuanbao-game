package org.come.servlet;

import java.io.PrintWriter;
import org.come.entity.Goodsrecord;
import com.github.pagehelper.PageInfo;
import com.auth0.jwt.JWTVerifier;
import org.come.until.GsonUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.come.serviceImpl.GoodsrecordServiceImpl;
import org.come.service.IGoodsrecordService;
import javax.servlet.http.HttpServlet;

public class GoodsRecordServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private IGoodsrecordService goodsrecordService;
    
    public GoodsRecordServlet() {
        this.goodsrecordService = new GoodsrecordServiceImpl();
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
        String condition = request.getParameter("goodsrecord");
        Integer pageNum = Integer.valueOf(Integer.parseInt(request.getParameter("pageNum")));
        PageInfo<Goodsrecord> list = this.goodsrecordService.selectGoodsRecord(pageNum, condition);
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(list));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
