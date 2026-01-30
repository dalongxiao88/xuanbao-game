package org.come.servlet.sale;

import java.util.Iterator;
import java.util.List;

import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.entity.UserTable;
import java.io.PrintWriter;
import org.come.bean.Role_bean;
import org.come.bean.LoginResult;
import java.util.ArrayList;
import org.come.until.AllServiceUtil;
import org.come.bean.Account;
import org.come.until.GsonUtil;
import org.come.until.HttpClient;
import org.come.nettyClient.UrlUntil;
import org.come.until.PayMd5;
import org.come.server.GameServer;
import org.come.bean.RoleListBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UserLoginServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String userName = request.getParameter("userName");
        String userpwd = request.getParameter("userPwd");
        String Sign = request.getParameter("sign");
        RoleListBean bean = new RoleListBean();
        if (PayMd5.encryption(userName + userpwd + GameServer.signNum).equals(Sign)) {
            String url = "http://" + UrlUntil.account_ip + ":" + UrlUntil.tomcat_port + "/" + UrlUntil.poject + "/userInfo/getUser";
            String param = "username=" + userName + "&password=" + userpwd;
            String res = "";
            int i = 0;
            while (i < 5) {
                res = HttpClient.sendPost(url, param);
                if (!"postError".equals(res)) {
                    break;
                }
                else {
                    ++i;
                }
            }
            if ("".equals(res)) {
                PrintWriter pwPrintWriter = response.getWriter();
                bean.setStatues(3);
                pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(bean));
                pwPrintWriter.flush();
                pwPrintWriter.close();
                return;
            }
            Account acc = (Account)GsonUtil.getGsonUtil().getgson().fromJson(res, Account.class);
            if (acc.getAc_flag() == null || "".equals(acc.getAc_flag())) {
                PrintWriter pwPrintWriter2 = response.getWriter();
                bean.setStatues(3);
                pwPrintWriter2.write(GsonUtil.getGsonUtil().getgson().toJson(bean));
                pwPrintWriter2.flush();
                pwPrintWriter2.close();
                return;
            }
            UserTable userTable = AllServiceUtil.getUserTableService().selectByFlag(acc.getAc_flag());
            if (userTable != null) {
                userName = userTable.getUsername();
                userpwd = userTable.getUserpwd();
                List<LoginResult> list = AllServiceUtil.getUserTableService().findRoleByUserNameAndPassword(userName, userpwd, null);
                List<Role_bean> beans = new ArrayList<>();
                for (LoginResult loginResult : list) {
                    Role_bean bean2 = new Role_bean();
                    bean2.setGangname(loginResult.getGangname());
                    bean2.setGrade(loginResult.getGrade());
                    bean2.setRace_name(loginResult.getRace_name());
                    bean2.setRole_id(loginResult.getRole_id());
                    bean2.setRolename(loginResult.getRolename());
                    bean2.setTitle(loginResult.getTitle());
                    bean2.setSkin(loginResult.getSpecies_id() + "");
                    beans.add(bean2);
                }
                bean.setRoleList(beans);
                bean.setStatues(1);
                bean.setUserId(userTable.getUser_id());
                bean.setUsermoney((double)userTable.getUsermoney());
                bean.setQid(userTable.getQid() + "");
                bean.setPhone(userTable.getPhonenumber());
            }
            else {
                bean.setStatues(3);
            }
        }
        PrintWriter pwPrintWriter3 = response.getWriter();
        pwPrintWriter3.write(GsonUtil.getGsonUtil().getgson().toJson(bean));
        pwPrintWriter3.flush();
        pwPrintWriter3.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
