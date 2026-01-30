package org.come.servlet;

import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.bean.LoginResult;
import java.io.PrintWriter;
import java.util.Map;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.AllServiceUtil;
import come.tool.Stall.AssetUpdate;
import come.tool.Scene.LaborDay.LaborScene;
import org.come.until.GsonUtil;
import java.math.BigDecimal;
import java.util.HashMap;
import org.come.ApiValid;
import org.come.bean.managerTable;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class LaborClearServlet extends HttpServlet
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
        Map<String, Object> returnData = new HashMap<>();
        String roleId = request.getParameter("roleId");
        BigDecimal roleIdDec = null;
        try {
            roleIdDec = new BigDecimal(roleId);
        }
        catch (Exception e) {
            returnData.put("status", Integer.valueOf(500));
            PrintWriter printWriter = response.getWriter();
            printWriter.write(GsonUtil.getGsonUtil().getgson().toJson(returnData));
            printWriter.flush();
            printWriter.close();
            return;
        }
        LaborScene.clearRoleMapByRoleId(roleIdDec);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(25);
        assetUpdate.setMsg("你的抽奖次数被清理为0次");
        LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleByRoleId(roleIdDec);
        SendMessage.sendMessageByRoleName(loginResult.getRolename(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        returnData.put("status", Integer.valueOf(200));
        PrintWriter printWriter2 = response.getWriter();
        printWriter2.write(GsonUtil.getGsonUtil().getgson().toJson(returnData));
        printWriter2.flush();
        printWriter2.close();
    }
}
