package org.come.servlet;

import java.io.*;
import java.util.Map;

import com.gl.controller.UserController;
import com.gl.model.Result;
import org.come.tool.ReadExelTool;
import com.google.gson.reflect.TypeToken;
import org.come.until.GsonUtil;
import java.util.List;
import org.come.tool.NewAESUtil;
import java.util.HashMap;
import java.util.ArrayList;

import org.come.ApiValid;
import org.come.bean.managerTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class DownXlsAndTxtFile extends HttpServlet
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
        Map<String, String> map1 = new HashMap<>();
        for (int i = 0; i < strings.size() / 6; ++i) {
            String key = (String)strings.get(i * 6);
            String[] keyArr = key.split("\"");
            map1.put(keyArr[1], strings.get(6 * i + 4));
        }
        String key2 = (String)map1.get("pwd");
        key2 = NewAESUtil.AESJDKDncode(key2);
        key2 = key2.substring(13);
        StringBuffer buffer = new StringBuffer();
        Map<String, String> map2 = new HashMap<>();
        if (key2 != null && !"".equals(key2) && key2.equals(UpXlsAndTxtFile.pwdUp)) {
            String value = (String)map1.get("params");
            if (value != null && !"".equals(value)) {
                String dncode = NewAESUtil.AESJDKDncode(value);
                dncode = dncode.substring(13);
                List<String> list = (List)GsonUtil.getGsonUtil().getgson().fromJson(dncode, new TypeToken<List<String>>() {}.getType());
                File directory = new File(ReadExelTool.class.getResource("/").getPath() + "config");
                if (!directory.exists()) {
                    buffer.append("下载失败");
                    buffer.append("\r\n");
                }
                else {
                    for (int j = 0; j < list.size(); ++j) {
                        File file = new File(directory.getAbsolutePath() + File.separatorChar + (String)list.get(j));
                        if (file.exists()) {
                            byte[] byteArray = InputStream2ByteArray(file);
                            String encode = NewAESUtil.AESJDKEncode(GsonUtil.getGsonUtil().getgson().toJson(byteArray));
                            map2.put(list.get(j), encode);
                        }
                        else {
                            buffer.append("找不到");
                            buffer.append((String)list.get(j));
                            buffer.append("\r\n");
                        }
                    }
                }
            }
        }
        else {
            buffer.append("验证码不正确");
            buffer.append("\r\n");
        }
        map2.put("params", NewAESUtil.AESJDKEncode(buffer.toString()));
        String json = GsonUtil.getGsonUtil().getgson().toJson(map2);
        String encode2 = NewAESUtil.AESJDKEncode(json);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(encode2.getBytes("utf-8"));
        outputStream.flush();
        outputStream.close();
    }
    
    public static byte[] InputStream2ByteArray(File file) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(file);
            data = toByteArray(in);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return data;
    }
    
    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }
    
    @Override
    public void init() throws ServletException {
    }
}
