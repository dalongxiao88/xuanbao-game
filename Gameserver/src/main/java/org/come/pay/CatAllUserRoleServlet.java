package org.come.pay;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import cn.hutool.json.JSONUtil;
import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.pay.pojo.CatUserRole;
import org.come.bean.LoginResult;
import org.come.until.AllServiceUtil;
import java.util.ArrayList;
import org.come.ApiValid;
import org.come.bean.managerTable;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class CatAllUserRoleServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Result ipCheckResult = UserController.IPstop(request);
//        if (ipCheckResult != null) {
//            PrintWriter pwPrintWriter = response.getWriter();
//            pwPrintWriter.write("caonima");
//            pwPrintWriter.flush();
//            pwPrintWriter.close();
//            return;
//        }
        managerTable manege = (managerTable)request.getSession().getAttribute("xy2o");
        String token = request.getHeader("manage_token");
        String VALID_NAME = request.getParameter("wdltxyss");
        if (null == VALID_NAME || !VALID_NAME.equals("zzswxy2o!@#HH") || manege == null || !ApiValid.vaildToken(token, manege.getUsername())) {
            System.out.println("【PayvipBeanServlet】非法请求！！,已踢出");
            return;
        }
        response.setHeader("manage_token", ApiValid.getToken(manege.getUsername()));
        ArrayList<CatUserRole> roles = new ArrayList<>();
        try {
            List<LoginResult> list = AllServiceUtil.getUserTableService().findAllUserRoles();
            for (LoginResult ret : list) {
                roles.add(new CatUserRole(ret));
            }
        }
        catch (Exception var7) {
            var7.printStackTrace();
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(JSONUtil.toJsonStr(roles));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
}
