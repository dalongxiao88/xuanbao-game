package org.come.servlet.sale;

import java.io.PrintWriter;
import org.come.bean.LoginResult;
import org.come.entity.UserTable;
import org.come.until.GsonUtil;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UserMoneyControlServlet extends HttpServlet
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String userid = request.getParameter("Userid");
        String money = request.getParameter("Money");
        String type = request.getParameter("Type");
        int YesOrNo = 1;
        UserTable userInfo = null;
        if ("2".equals(type)) {
            LoginResult roleInfo = AllServiceUtil.getRoleTableService().selectRoleByRoleId(new BigDecimal(userid));
            BigDecimal user_id = roleInfo.getUser_id();
            if (user_id.compareTo(new BigDecimal(0)) == -1) {
                user_id = new BigDecimal(0).subtract(user_id);
            }
            userInfo = AllServiceUtil.getUserTableService().selectByPrimaryKey(user_id);
            DecimalFormat df = new DecimalFormat("######0.00");
            Double balance = Double.valueOf((double)userInfo.getUsermoney() + Double.parseDouble(money));
            balance = Double.valueOf(df.format(balance));
            userInfo.setUsermoney(balance);
        }
        else {
            userInfo = AllServiceUtil.getUserTableService().selectByPrimaryKey(new BigDecimal(userid));
            if ((double)userInfo.getUsermoney() < Double.parseDouble(money)) {
                YesOrNo = 2;
            }
            else {
                DecimalFormat df2 = new DecimalFormat("######0.00");
                Double balance2 = Double.valueOf((double)userInfo.getUsermoney() - Double.parseDouble(money));
                balance2 = Double.valueOf(df2.format(balance2));
                userInfo.setUsermoney(balance2);
            }
        }
        AllServiceUtil.getUserTableService().updateUser(userInfo);
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(Integer.valueOf(YesOrNo)));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
