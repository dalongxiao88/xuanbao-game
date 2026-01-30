package org.come.servlet;

import java.io.PrintWriter;
import java.util.Map;

import com.gl.controller.UserController;
import com.gl.model.Result;
import come.tool.Stall.AssetUpdate;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import java.util.HashMap;
import org.come.ApiValid;
import org.come.bean.managerTable;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class SendSystemMessageServlet extends HttpServlet
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
        Map<String, Integer> returnData = new HashMap<>();
        String message = request.getParameter("message");
        String type = request.getParameter("type");
        if (type.equals("1") || type.equals("0")) {
            NChatBean bean = new NChatBean();
            bean.setId(9);
            bean.setMessage("#Y" + message);
            String msgLiaoTian = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToAllRoles(msgLiaoTian);
        }
        if (type.equals("2") || type.equals("0")) {
            AssetUpdate assetUpdate = new AssetUpdate();
            assetUpdate.setType(25);
            assetUpdate.setMsg("#Y" + message);
            String msgLiaoTian = Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate));
            SendMessage.sendMessageToAllRoles(msgLiaoTian);
        }
        returnData.put("status", Integer.valueOf(200));
        PrintWriter printWriter = response.getWriter();
        printWriter.write(GsonUtil.getGsonUtil().getgson().toJson(returnData));
        printWriter.flush();
        printWriter.close();
    }
}
