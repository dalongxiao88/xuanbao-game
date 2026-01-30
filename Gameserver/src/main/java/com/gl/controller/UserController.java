package com.gl.controller;

import java.util.HashMap;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import sun.misc.BASE64Decoder;
import java.security.PublicKey;
import java.net.NetworkInterface;
import java.net.InetAddress;
import org.springframework.web.bind.annotation.GetMapping;
import com.gl.token.UserToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.github.pagehelper.util.StringUtil;
import com.gl.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletException;
import com.gl.util.RandomValidateCode;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import com.gl.service.TokenService;
import org.come.until.ReadTxtUtil;
import org.come.tool.ReadExelTool;
import javax.servlet.http.HttpSession;
import com.gl.model.User;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import com.gl.service.ResultFactory;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import com.gl.model.Result;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    // 存放登录失败的用户IP，五分钟内不允许登录
    public static Map<String, Date> errorUser;
    //下面是加密
    public static final String publicKeyStr = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAr61/xJKFtJqguGtCLTv0\r\ncDqpv6cHdfmXC4cU8RthVx0PrMS86wOZbChexl5yldeFQQuCPuN65eNhCTmIm/Ow\r\nHREi80JfOf0fJrvjeav3AwwEx8b4N9Su5kCIZzzAOtGdmDcWX2IyKs2B3WYsx2Wa\r\nuSoR3Ve3UduOkPKQtD+205VwferSACHRyUaK2hXNlezVYxclqySNW6y4TUIKb5tE\r\nDnsWFevMr9AD1gPpQQRUi0SppKrAYQGqrqfJ6HddZdghMtYdoNTzywy6m12bVMCu\r\nQ6FmQQebgVdpeZLIUu9Lnh0EDKO742wIhvdlv2SLcMasphJ+rusq5yk915eei3mM\r\nfskBzC5vRZfdE7L5C73Pzhkcdx4DcFtL3nAYodssr47ltAyrimR+n/t6tpu4ml9x\r\nWunrp+/Jtl2G/fViBJ3fQtxgwr00so3L0Gn9S/YF13t0niC4j4QTz0u0rxOjLflJ\r\nJ7Lgn0IzGubOLEWMzLW8KE3J+LyRZmzGnIxDnzY7HHcwBk+C3GWgtpU9b5N3aVXu\r\np+GHry2L+H+VYmxlxjRgL1z160ZnZJ6C2EdpsB1O3YzW4SIEO7vcurM9hv5lq7DY\r\njw8jsjy1xof5lahT1A2PTBAFFOt+wHxKRCpkQ0l3lOM9i2+HwhihGKGBYdyOqhyu\r\n7RibyDz91vF6NltYVBpmHhkCAwEAAQ==";

    public static Result IPstop(HttpServletRequest request) {

        String clientIP = request.getRemoteAddr();
        if (clientIP!= null&&clientIP.startsWith("127.0.0")) {
            return null;
        }
        String filePath = "C:\\IP.txt";
        try {         //这里开始取消IP锁
            boolean exists = Files.exists(Paths.get(filePath, new String[0]), new LinkOption[0]);
            if (!exists) {
                return ResultFactory.fail("IP不存在，请联系管理员解封！ ");
            }
            long fileSize = Files.size(Paths.get(filePath, new String[0]));
            if (fileSize == 0L) {
                return ResultFactory.fail("IP不存在，请联系管理员解封！ ");
            }
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            boolean foundMatch = false;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] ips = line.split("\\|");
                int length = ips.length;
                int i = 0;
                while (i < length) {
                    String ip = ips[i];
                    if (clientIP.equals(ip.trim())) {
                        foundMatch = true;
                        break;
                    }
                    else {
                        ++i;
                    }
                }
                if (foundMatch) {
                    break;
                }
            }
            if (!foundMatch) {
                return ResultFactory.fail("该IP已被封禁，请联系管理员解封！");
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }//这里结束IP锁
        return null;
    }
    //处理登录功能
    @PostMapping({ "/api/login" })
    public Result login(User user, HttpSession session, HttpServletRequest request) {
        // 检查IP是否被限制

        Result ipCheckResult = IPstop(request);
        if (ipCheckResult != null) {
            return ipCheckResult; // 如果IP被限制，返回限制结果
        }
        // 获取用户名密码格式为 用户名|&|密码
        String up = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "administrator.db");
        String[] nameAndPwd = up.split("\\|&\\|");// 分割用户名和密码
        // 验证用户名和密码
        if (nameAndPwd[0].equals(user.getUserName()) && nameAndPwd[1].equals(user.getPassword())) {
            TokenService tokenService = new TokenService();
            String token = tokenService.getToken(user);
            session.setAttribute("manger", user.getUserName());
            session.setAttribute("BG_NAME_XY2", user);
            return ResultFactory.success(token);
        }
        return ResultFactory.fail("用户名或密码错误，请重新登录！ ");
    }
    
    @RequestMapping({ "/api/checkcode" })
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        // 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0L);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //处理注册功能
    @PostMapping({ "/api/register" })
    public Result register(User user, HttpServletRequest request, HttpServletResponse res) {
        String code = user.getCode();
        if (StringUtils.isEmpty(code)) {
            return ResultFactory.fail("验证码不可为空");
        }
        HttpSession sesion = request.getSession();
        if (!code.equals(sesion.getAttribute("randomcode_key"))) {
            // 避免一个验证码被多次使用暴力破解验证一次自动删除
            sesion.removeAttribute("randomcode_key");
            return ResultFactory.fail("验证码不正确");
        }
        // 验证通过也要马上删除掉验证码
        sesion.removeAttribute("randomcode_key");
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        UserService service = new UserService();
        String msg = service.register(user, ip);
        if (StringUtil.isEmpty(msg)) {
            return ResultFactory.success("注册成功");
        }
        return ResultFactory.fail(msg);
    }
    //退出登录
    @CrossOrigin(origins = { "*" }, maxAge = 3600L)
    @RequestMapping({ "/api/logout" })
    public Result logout(HttpSession session) {
        session.invalidate();
        return ResultFactory.success(null);
    }
    //首页基础统计
    @UserToken
    @CrossOrigin(origins = { "*" }, maxAge = 3600L)
    @GetMapping({ "/api/stat" })
    public Result stat(HttpSession session) {
        UserService service = new UserService();
        return ResultFactory.success(service.getData());
    }
    
    public static String m() throws Exception {
        // 获取网卡，获取地址
        InetAddress ia = InetAddress.getLocalHost();
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < mac.length; ++i) {
            if (i != 0) {
                sb.append("-");
            }
            // 字节转换为整数
            int temp = mac[i] & 0xFF;
            String str = Integer.toHexString(temp);
            if (str.length() == 1) {
                sb.append("0" + str);
            }
            else {
                sb.append(str);
            }
        }
        return sb.toString().toUpperCase();
    }
    
    private static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }
    
    public static String decryptByPublicKey(String content) throws Exception {
        // 获取公钥
        PublicKey publicKey = getPublicKey("MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAr61/xJKFtJqguGtCLTv0\r\ncDqpv6cHdfmXC4cU8RthVx0PrMS86wOZbChexl5yldeFQQuCPuN65eNhCTmIm/Ow\r\nHREi80JfOf0fJrvjeav3AwwEx8b4N9Su5kCIZzzAOtGdmDcWX2IyKs2B3WYsx2Wa\r\nuSoR3Ve3UduOkPKQtD+205VwferSACHRyUaK2hXNlezVYxclqySNW6y4TUIKb5tE\r\nDnsWFevMr9AD1gPpQQRUi0SppKrAYQGqrqfJ6HddZdghMtYdoNTzywy6m12bVMCu\r\nQ6FmQQebgVdpeZLIUu9Lnh0EDKO742wIhvdlv2SLcMasphJ+rusq5yk915eei3mM\r\nfskBzC5vRZfdE7L5C73Pzhkcdx4DcFtL3nAYodssr47ltAyrimR+n/t6tpu4ml9x\r\nWunrp+/Jtl2G/fViBJ3fQtxgwr00so3L0Gn9S/YF13t0niC4j4QTz0u0rxOjLflJ\r\nJ7Lgn0IzGubOLEWMzLW8KE3J+LyRZmzGnIxDnzY7HHcwBk+C3GWgtpU9b5N3aVXu\r\np+GHry2L+H+VYmxlxjRgL1z160ZnZJ6C2EdpsB1O3YzW4SIEO7vcurM9hv5lq7DY\r\njw8jsjy1xof5lahT1A2PTBAFFOt+wHxKRCpkQ0l3lOM9i2+HwhihGKGBYdyOqhyu\r\n7RibyDz91vF6NltYVBpmHhkCAwEAAQ==");
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, publicKey);
        byte[] cipherText = new BASE64Decoder().decodeBuffer(content);
        byte[] decryptText = cipher.doFinal(cipherText);
        return new String(decryptText);
    }
    
    static {
        UserController.errorUser = new HashMap<>();
    }
}
