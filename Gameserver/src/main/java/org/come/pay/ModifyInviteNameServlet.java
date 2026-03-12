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

/**
 * 后台管理端：修改全服统一大区展示名称。
 */
public class ModifyInviteNameServlet extends HttpServlet {
    static OpenareatableService openareatableService;
    
    public ModifyInviteNameServlet() {
        ModifyInviteNameServlet.openareatableService = new OpenareatableServiceImpl();
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result ipCheckResult = UserController.IPstop(request);
        if (ipCheckResult != null) {
            // 安全修复：统一返回标准拒绝响应，替换原有不当文本内容。
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write("{\"code\":403,\"message\":\"Access denied\"}");
            pwPrintWriter.flush();
            pwPrintWriter.close();
            return;
        }
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String name = request.getParameter("name");
        if (!StrUtil.isBlankIfStr(name)) {
            String ret = "修改成功";
            try {
                List<Openareatable> list = ModifyInviteNameServlet.openareatableService.selectAllOpenareatable();
                for (Openareatable openareatable : list) {
                    openareatable.setOt_areaname(name);
                    ModifyInviteNameServlet.openareatableService.updateOpenareatable(openareatable);
                }
            }
            catch (Exception updateException) {
                ret = "保存失败，请发送错误信息给技术人员";
                updateException.printStackTrace();
            }
            PrintWriter pwPrintWriter = response.getWriter();
            pwPrintWriter.write(ret);
            pwPrintWriter.flush();
            pwPrintWriter.close();
        }
    }
    
    public static String getOt_areaname() {
        List<Openareatable> list = new OpenareatableServiceImpl().selectAllOpenareatable();
        Iterator<Openareatable> iterator = list.iterator();
        if (iterator.hasNext()) {
            Openareatable openareatable = iterator.next();
            return openareatable.getOt_areaname();
        }
        return "";
    }
}


