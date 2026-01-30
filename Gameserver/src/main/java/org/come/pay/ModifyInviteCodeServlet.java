package org.come.pay;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.entity.Openareatable;
import cn.hutool.core.util.StrUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.come.serviceImpl.OpenareatableServiceImpl;
import org.come.service.OpenareatableService;
import javax.servlet.http.HttpServlet;

public class ModifyInviteCodeServlet extends HttpServlet
{
    static OpenareatableService openareatableService;
    
    public ModifyInviteCodeServlet() {
        ModifyInviteCodeServlet.openareatableService = new OpenareatableServiceImpl();
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String code = request.getParameter("code");
        if (!StrUtil.isBlankIfStr(code)) {
            String ret = "修改成功";
            try {
                List<Openareatable> list = ModifyInviteCodeServlet.openareatableService.selectAllOpenareatable();
                for (Openareatable openareatable : list) {
                    openareatable.setOt_atid(code);
                    ModifyInviteCodeServlet.openareatableService.updateOpenareatable(openareatable);
                }
            }
            catch (Exception var8) {
                ret = "保存失败，请发送错误信息给技术人员";
                var8.printStackTrace();
            }
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write(ret);
            pwPrintWriter.flush();
            pwPrintWriter.close();
        }
    }
    
    public static String getOt_atid() {
        List<Openareatable> list = new OpenareatableServiceImpl().selectAllOpenareatable();
        Iterator<Openareatable> var2 = list.iterator();
        if (var2.hasNext()) {
            Openareatable openareatable = (Openareatable)var2.next();
            return openareatable.getOt_atid();
        }
        return "";
    }
}
