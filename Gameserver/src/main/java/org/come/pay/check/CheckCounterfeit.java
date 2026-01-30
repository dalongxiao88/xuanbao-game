package org.come.pay.check;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;

import com.gl.controller.UserController;
import com.gl.model.Result;
import redis.clients.jedis.Jedis;
import org.come.redis.RedisPoolUntil;
import org.come.ApiValid;
import org.come.bean.managerTable;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class CheckCounterfeit extends HttpServlet
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
        Jedis jedis = RedisPoolUntil.getJedis();
        try {
            String act = jedis.get("check_counterfeit");
            if (act == null) {
                act = "1";
            }
            if (act.equals("1")) {
                act = "0";
            }
            else {
                act = "1";
            }
            jedis.set("check_counterfeit", act);
            response.getWriter().println(act);
            response.getWriter().flush();
            response.getWriter().close();
        }
        catch (Exception var5) {
            var5.printStackTrace();
        }
        RedisPoolUntil.returnResource(jedis);
    }
    
    public static String getStatus() {
        Jedis jedis = RedisPoolUntil.getJedis();
        try {
            String str = jedis.get("check_counterfeit");
            if (str != null) {
                String var3 = str;
                return var3;
            }
        }
        catch (Exception var4) {
            var4.printStackTrace();
            return "-1";
        }
        finally {
            RedisPoolUntil.returnResource(jedis);
        }
        return "1";
    }
}
