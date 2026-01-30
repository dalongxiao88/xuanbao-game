package org.come.servlet.sale;

import java.io.PrintWriter;
import java.text.DateFormat;
import org.come.entity.Salegoods;
import org.come.until.GsonUtil;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.come.entity.Roleorder;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class GoodsBuyServlet extends HttpServlet
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
        String saleid = request.getParameter("saleid");
        String roleid = request.getParameter("Role_ID");
        int result = 0;
        Salegoods salegoods = AllServiceUtil.getSalegoodsService().selectByPrimaryKey(new BigDecimal(saleid));
        if (salegoods != null) {
            if ((int)salegoods.getFlag() == 2) {
                Roleorder roleorder = new Roleorder();
                roleorder.setSaleid(salegoods.getSaleid());
                roleorder.setRoleid(new BigDecimal(roleid));
                roleorder.setStatus(Integer.valueOf(3));
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowdayTime = dateFormat.format(new Date());
                Date nowDate = null;
                try {
                    nowDate = dateFormat.parse(nowdayTime);
                    roleorder.setBuytime(nowDate);
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                AllServiceUtil.getRoleorderService().insert(roleorder);
                salegoods.setFlag(Integer.valueOf(4));
                AllServiceUtil.getSalegoodsService().updateByPrimaryKey(salegoods);
                result = 1;
            }
            else {
                result = 0;
            }
        }
        else {
            result = 0;
        }
        PrintWriter pwPrintWriter = response.getWriter();
        pwPrintWriter.write(GsonUtil.getGsonUtil().getgson().toJson(Integer.valueOf(result)));
        pwPrintWriter.flush();
        pwPrintWriter.close();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
