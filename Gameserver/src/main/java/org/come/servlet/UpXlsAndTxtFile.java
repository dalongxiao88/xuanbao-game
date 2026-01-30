package org.come.servlet;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.readUtil.ReadPoolUtil;
import java.io.FileOutputStream;
import org.come.until.GsonUtil;
import java.io.File;
import org.come.tool.ReadExelTool;
import org.come.tool.NewAESUtil;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.come.ApiValid;
import org.come.bean.managerTable;
import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import javax.servlet.http.HttpServlet;

public class UpXlsAndTxtFile extends HttpServlet
{
    public static String pwdUp;
    public static String lineFeed;
    public static Map<String, Integer> XLSmap;
    
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(this.getClass());
        out.println(", using the GET method");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "ISO-8859-1"));
        List<String> strings = new ArrayList<>();
        String line = null;
        boolean is = true;
        while ((line = bufferedReader.readLine()) != null) {
            if (is) {
                is = false;
            }
            else {
                strings.add(line);
            }
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < strings.size() / 6; ++i) {
            String key = (String)strings.get(i * 6);
            String[] keyArr = key.split("\"");
            map.put(keyArr[1], strings.get(6 * i + 4));
        }
        String params = (String)map.get("params");
        params = NewAESUtil.AESJDKDncode(params);
        params = params.substring(13);
        StringBuffer buffer = new StringBuffer();
        if (params != null && !"".equals(params) && params.equals(UpXlsAndTxtFile.pwdUp)) {
            map.remove("params");
            File directory = new File(ReadExelTool.class.getResource("/").getPath() + "config");
            if (!directory.exists()) {
                directory.mkdir();
            }
            Set<Map.Entry<String, String>> entrySet = map.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                String key2 = (String)entry.getKey();
                System.err.println("替换的文件key=" + key2);
                Integer I = (Integer)UpXlsAndTxtFile.XLSmap.get(key2);
                if (I == null) {
                    buffer.append(key2);
                    buffer.append("没有读取该文件的方法.");
                    buffer.append(UpXlsAndTxtFile.lineFeed);
                }
                else {
                    File oldFile = new File(directory.getAbsolutePath() + File.separatorChar + key2);
                    if (oldFile.exists()) {
                        File newfile = new File(directory.getAbsolutePath() + File.separatorChar + "OLD" + key2);
                        if (newfile.exists()) {
                            newfile.delete();
                        }
                        oldFile.renameTo(newfile);
                    }
                    String value = (String)entry.getValue();
                    String aesjdkEncode = NewAESUtil.AESJDKDncode(value);
                    aesjdkEncode = aesjdkEncode.substring(13);
                    byte[] bsArr = (byte[])GsonUtil.getGsonUtil().getgson().fromJson(aesjdkEncode, byte[].class);
                    FileOutputStream fos = new FileOutputStream(directory.getAbsolutePath() + File.separatorChar + key2);
                    fos.write(bsArr);
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (ReadPoolUtil.readTypeTwo(buffer, (int)I)) {
                        oldFile = new File(directory.getAbsolutePath() + File.separatorChar + "OLD" + key2);
                        if (oldFile.exists()) {
                            oldFile.delete();
                        }
                        buffer.append(key2);
                        buffer.append("替换旧文件替换成功.");
                        buffer.append(UpXlsAndTxtFile.lineFeed);
                    }
                    else {
                        File newFile = new File(directory.getAbsolutePath() + File.separatorChar + key2);
                        newFile.delete();
                        oldFile = new File(directory.getAbsolutePath() + File.separatorChar + "OLD" + key2);
                        oldFile.renameTo(newFile);
                        buffer.append(key2);
                        buffer.append("读取错误");
                        buffer.append(UpXlsAndTxtFile.lineFeed);
                    }
                }
            }
        }
        else {
            buffer.append("验证码不正确");
            buffer.append(UpXlsAndTxtFile.lineFeed);
        }
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(NewAESUtil.AESJDKEncode(buffer.toString()).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
    
    public static Object getReadFileClass(StringBuffer buffer, String fileName, String path) {
        return null;
    }
    
    public static void addStringBufferMessage(StringBuffer buffer, int i, int j, String value, String errorMsg) {
        buffer.append("第");
        buffer.append(i);
        buffer.append("列");
        buffer.append("第");
        buffer.append(j);
        buffer.append("行,数据");
        buffer.append(value);
        buffer.append("错误,错误信息:");
        buffer.append(errorMsg);
        buffer.append(UpXlsAndTxtFile.lineFeed);
    }
    
    public static void addStringBufferMessageTxtFailure(StringBuffer buffer, String fileName, String errorMsg) {
        buffer.append(fileName);
        buffer.append("文件生成txt失败,错误信息:");
        buffer.append(errorMsg);
        buffer.append(UpXlsAndTxtFile.lineFeed);
    }
    
    public static void addStringBufferMessageTxtSuccess(StringBuffer buffer, String fileName, String textName) {
        buffer.append(fileName);
        buffer.append("文件生成");
        buffer.append(textName);
        buffer.append(".txt成功");
        buffer.append(UpXlsAndTxtFile.lineFeed);
    }
    
    public static void addStringBufferMessageTxtUnfound(StringBuffer buffer, String fileName) {
        buffer.append(fileName);
        buffer.append("没有生成txt文件的方法");
        buffer.append(UpXlsAndTxtFile.lineFeed);
    }
    
    public void deviceClassForMes(String fileName, StringBuffer buffer) {
    }
    
    @Override
    public void init() throws ServletException {
    }
    
    static {
        UpXlsAndTxtFile.pwdUp = "X123456";
        UpXlsAndTxtFile.lineFeed = "\r\n";
        (UpXlsAndTxtFile.XLSmap = new HashMap<>()).put("pet.xls", Integer.valueOf(0));
        UpXlsAndTxtFile.XLSmap.put("petExchange.xls", Integer.valueOf(2));
        UpXlsAndTxtFile.XLSmap.put("map.xls", Integer.valueOf(3));
        UpXlsAndTxtFile.XLSmap.put("npc.xls", Integer.valueOf(4));
        UpXlsAndTxtFile.XLSmap.put("door.xls", Integer.valueOf(5));
        UpXlsAndTxtFile.XLSmap.put("taskSet.xls", Integer.valueOf(6));
        UpXlsAndTxtFile.XLSmap.put("taskData.xls", Integer.valueOf(6));
        UpXlsAndTxtFile.XLSmap.put("palData.xls", Integer.valueOf(7));
        UpXlsAndTxtFile.XLSmap.put("boos.xls", Integer.valueOf(8));
        UpXlsAndTxtFile.XLSmap.put("monster.xls", Integer.valueOf(9));
        UpXlsAndTxtFile.XLSmap.put("robots.xls", Integer.valueOf(10));
        UpXlsAndTxtFile.XLSmap.put("item.xls", Integer.valueOf(11));
        UpXlsAndTxtFile.XLSmap.put("newequip.xls", Integer.valueOf(12));
        UpXlsAndTxtFile.XLSmap.put("alchemy.xls", Integer.valueOf(13));
        UpXlsAndTxtFile.XLSmap.put("decorate.xls", Integer.valueOf(14));
        UpXlsAndTxtFile.XLSmap.put("godstone.xls", Integer.valueOf(15));
        UpXlsAndTxtFile.XLSmap.put("palEquip.xls", Integer.valueOf(16));
        UpXlsAndTxtFile.XLSmap.put("shop.xls", Integer.valueOf(17));
        UpXlsAndTxtFile.XLSmap.put("eshop.xls", Integer.valueOf(18));
        UpXlsAndTxtFile.XLSmap.put("lShop.xls", Integer.valueOf(19));
        UpXlsAndTxtFile.XLSmap.put("sghostpoint.xls", Integer.valueOf(20));
        UpXlsAndTxtFile.XLSmap.put("xianqi.xls", Integer.valueOf(21));
        UpXlsAndTxtFile.XLSmap.put("lingbao.xls", Integer.valueOf(22));
        UpXlsAndTxtFile.XLSmap.put("lingbaofushi.xls", Integer.valueOf(23));
        UpXlsAndTxtFile.XLSmap.put("gem.xls", Integer.valueOf(24));
        UpXlsAndTxtFile.XLSmap.put("skill.xls", Integer.valueOf(25));
        UpXlsAndTxtFile.XLSmap.put("drop.xls", Integer.valueOf(26));
        UpXlsAndTxtFile.XLSmap.put("dntg.xls", Integer.valueOf(27));
        UpXlsAndTxtFile.XLSmap.put("bbuy.xls", Integer.valueOf(28));
        UpXlsAndTxtFile.XLSmap.put("suit.xls", Integer.valueOf(29));
        UpXlsAndTxtFile.XLSmap.put("tx.xls", Integer.valueOf(30));
        UpXlsAndTxtFile.XLSmap.put("present.xls", Integer.valueOf(31));
        UpXlsAndTxtFile.XLSmap.put("exp.xls", Integer.valueOf(32));
        UpXlsAndTxtFile.XLSmap.put("mount.xls", Integer.valueOf(33));
        UpXlsAndTxtFile.XLSmap.put("color.xls", Integer.valueOf(34));
        UpXlsAndTxtFile.XLSmap.put("child.xls", Integer.valueOf(35));
        UpXlsAndTxtFile.XLSmap.put("draw.xls", Integer.valueOf(36));
        UpXlsAndTxtFile.XLSmap.put("acard.xls", Integer.valueOf(37));
        UpXlsAndTxtFile.XLSmap.put("title.xls", Integer.valueOf(38));
        UpXlsAndTxtFile.XLSmap.put("event.xls", Integer.valueOf(39));
        UpXlsAndTxtFile.XLSmap.put("wingTraining.xls", Integer.valueOf(40));
        UpXlsAndTxtFile.XLSmap.put("starPalace.xls", Integer.valueOf(41));
        UpXlsAndTxtFile.XLSmap.put("tyc.xls", Integer.valueOf(42));
        UpXlsAndTxtFile.XLSmap.put("babyresult.xls", Integer.valueOf(43));
        UpXlsAndTxtFile.XLSmap.put("guide.xls", Integer.valueOf(44));
        UpXlsAndTxtFile.XLSmap.put("active.xls", Integer.valueOf(45));
        UpXlsAndTxtFile.XLSmap.put("achieve.xls", Integer.valueOf(46));
        UpXlsAndTxtFile.XLSmap.put("lh.xls", Integer.valueOf(47));
        UpXlsAndTxtFile.XLSmap.put("Meridians.xls", Integer.valueOf(48));
        UpXlsAndTxtFile.XLSmap.put("goodsExchange.xls", Integer.valueOf(49));
        UpXlsAndTxtFile.XLSmap.put("qiandao.xls", Integer.valueOf(48));
        UpXlsAndTxtFile.XLSmap.put("itemExchange.xls", Integer.valueOf(50));
        UpXlsAndTxtFile.XLSmap.put("GMshopItem.xls", 51);
        UpXlsAndTxtFile.XLSmap.put("configure.xls", 52);
        UpXlsAndTxtFile.XLSmap.put("fly.xls", 53);
        UpXlsAndTxtFile.XLSmap.put("lhtj.xls", 54);
        UpXlsAndTxtFile.XLSmap.put("taskList.xls", 55);
        UpXlsAndTxtFile.XLSmap.put("achievement.xls", 56);
        UpXlsAndTxtFile.XLSmap.put("bt.xls", 57);
        UpXlsAndTxtFile.XLSmap.put("car.xls", 72);
    }
}
