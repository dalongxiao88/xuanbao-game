package org.come.servlet;

import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.ApiValid;
import org.come.bean.managerTable;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.come.serviceImpl.GoodsexchangeServiceImpl;
import org.come.service.IGoodsExchangeService;
import javax.servlet.http.HttpServlet;

public class ExchangeServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private IGoodsExchangeService goodsExchangeService;
    
    public ExchangeServlet() {
        this.goodsExchangeService = new GoodsexchangeServiceImpl();
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
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write("caonima");
            pwPrintWriter.flush();
            pwPrintWriter.close();
            return;
        }
        managerTable manege = (managerTable)request.getSession().getAttribute("xy2o");
        String token = request.getHeader("manage_token");
        String VALID_NAME = request.getParameter("wdltxyss");
        if (null == VALID_NAME || !VALID_NAME.equals("zzswxy2o!@#HH") || manege == null || !ApiValid.vaildToken(token, manege.getUsername())) {
            System.out.println("【PayvipBeanServlet】非法请求！！,已踢出");
            return;
        }
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
    }
    
    @Override
    public void init() throws ServletException {
    }
}
