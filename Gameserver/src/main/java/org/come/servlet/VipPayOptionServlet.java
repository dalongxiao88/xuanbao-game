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

/**
 * VIP支付选项管理接口
 * 功能：管理VIP支付配置选项，支持增删改查操作
 * 用于后台管理系统配置VIP支付相关参数
 */
public class VipPayOptionServlet extends HttpServlet
{
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    
    /**
     * 处理POST请求
     * 功能：处理VIP支付选项的增删改查操作
     *
     * @param request HTTP请求对象，包含type(操作类型)、payvip(支付配置)等参数
     * @param response HTTP响应对象
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // IP访问控制检查
        // 功能：验证请求来源IP是否在白名单中，防止未授权访问后台管理接口
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            // 修复：替换不当响应内容为专业的JSON错误响应
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write("{\"code\":403,\"message\":\"Access denied\"}");
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
