package org.come.servlet;

import java.io.PrintWriter;
import com.auth0.jwt.JWTVerifier;
import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.until.GetTime;
import java.util.List;
import org.come.until.InterfaceControlUntil;
import org.come.bean.ServiceArea;
import org.come.until.GsonUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gl.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.come.serviceImpl.UserTableServiceImpl;
import org.come.service.IUserTableService;
import org.come.bean.RequestReturnBean;
import org.come.serviceImpl.ExpensesReceiptsServiceImpl;
import org.come.serviceImpl.ServiceAreaServiceImpl;
import org.come.serviceImpl.managerTableServiceImpl;
import javax.servlet.http.HttpServlet;

public class ControlFromClientForMesServlet extends HttpServlet
{
    private managerTableServiceImpl impl;
    private ServiceAreaServiceImpl serimpl;
    private ExpensesReceiptsServiceImpl expenImpl;
    private RequestReturnBean requestReturnBean;
    private IUserTableService userTableService;
    private ServiceAreaServiceImpl serviceAreaServiceImpl;
    private ExpensesReceiptsServiceImpl expensesReceiptsServiceImpl;
    private static final long serialVersionUID = 1L;
    
    public ControlFromClientForMesServlet() {
        this.impl = new managerTableServiceImpl();
        this.serimpl = new ServiceAreaServiceImpl();
        this.requestReturnBean = new RequestReturnBean();
        this.userTableService = new UserTableServiceImpl();
        this.serviceAreaServiceImpl = new ServiceAreaServiceImpl();
        this.expensesReceiptsServiceImpl = new ExpensesReceiptsServiceImpl();
    }
    
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Result ipCheckResult = UserController.IPstop(request);
//        if (ipCheckResult != null) {
//            PrintWriter pwPrintWriter = response.getWriter();
//            pwPrintWriter.write("caonima");
//            pwPrintWriter.flush();
//            pwPrintWriter.close();
//            return;
//        }
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
        String condition = request.getParameter("Service");
        this.requestReturnBean = (RequestReturnBean)GsonUtil.getGsonUtil().getgson().fromJson(condition, RequestReturnBean.class);
        if (!this.requestReturnBean.getRequresHeader().equals("10006")) {
            if (this.requestReturnBean.getRequresHeader().equals("10007")) {
                ServiceArea service1 = (ServiceArea)GsonUtil.getGsonUtil().getgson().fromJson(this.requestReturnBean.getContent(), ServiceArea.class);
                this.requestReturnBean = InterfaceControlUntil.serviceMoneyControl(this.requestReturnBean.getContent(), this.serviceAreaServiceImpl, this.expensesReceiptsServiceImpl);
                if (service1.getControlStyle() == 10005) {
                    request.getSession().setAttribute("areaName", GsonUtil.getGsonUtil().getgson().fromJson(this.requestReturnBean.getContent(), List.class));
                }
            }
            else {
                this.requestReturnBean.setContent("请求失败");
                this.requestReturnBean.setStyle("error");
                this.requestReturnBean.setRequresHeader("000000");
                this.requestReturnBean.setReturnDate(GetTime.getNowMinit());
            }
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(this.requestReturnBean));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
