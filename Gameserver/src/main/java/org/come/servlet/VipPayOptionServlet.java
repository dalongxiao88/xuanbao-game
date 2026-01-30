package org.come.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.server.GameServer;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.PayvipBean;
import java.util.HashMap;
import org.come.ApiValid;
import org.come.bean.managerTable;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class VipPayOptionServlet extends HttpServlet
{
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        Map returnData = new HashMap<>();
        String payvip = request.getParameter("payvip");
        String type = request.getParameter("type");
        PayvipBean payvipBean = (PayvipBean)GsonUtil.getGsonUtil().getgson().fromJson(payvip, PayvipBean.class);
        if (type.equals("insert")) {
            AllServiceUtil.getPayvipBeanServer().insertPayvioBean(payvipBean);
        }
        else if (type.equals("del")) {
            String id = request.getParameter("id");
            AllServiceUtil.getPayvipBeanServer().deletePayvioBean(Integer.valueOf(Integer.parseInt(id)));
        }
        else if (type.equals("find")) {
            List<PayvipBean> list = AllServiceUtil.getPayvipBeanServer().selectAllVip();
            returnData.put("list", list);
        }
        else {
            AllServiceUtil.getPayvipBeanServer().updatePayvioBean(payvipBean);
        }
        GameServer.refreshBean();
        returnData.put("status", Integer.valueOf(200));
        PrintWriter printWriter = response.getWriter();
        printWriter.write(GsonUtil.getGsonUtil().getgson().toJson(returnData));
        printWriter.flush();
        printWriter.close();
    }
}
