package org.come.tool;

import org.dom4j.Element;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import java.io.IOException;
import org.apache.commons.httpclient.HttpException;
import org.dom4j.DocumentHelper;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.HttpClient;
import org.come.until.RuntimeConfig;

public class PhoneVersionUtil
{
    private static String Url;
    private static String Account;
    private static String Password;
    private static int mobile_code;
    
    public static int sendsMes(String phone) {
        if (phone == null || phone.trim().isEmpty() || "CHANGE_ME".equals(PhoneVersionUtil.Account) || "CHANGE_ME".equals(PhoneVersionUtil.Password)) {
            return 0;
        }
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(PhoneVersionUtil.Url);
        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");
        PhoneVersionUtil.mobile_code = (int)((Math.random() * 9.0 + 1.0) * 100000.0);
        String content = new String("您的验证码是：" + PhoneVersionUtil.mobile_code + "。请不要把验证码泄露给其他人。");
        NameValuePair[] data = { new NameValuePair("account", PhoneVersionUtil.Account), new NameValuePair("password", PhoneVersionUtil.Password), new NameValuePair("mobile", phone), new NameValuePair("content", content) };
        method.setRequestBody(data);
        try {
            client.executeMethod(method);
            String SubmitResult = method.getResponseBodyAsString();
            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();
            String code = root.elementText("code");
            if ("2".equals(code)) {
                System.out.println("短信提交成功");
            }
        }
        catch (HttpException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        catch (DocumentException e3) {
            e3.printStackTrace();
        }
        finally {
            method.releaseConnection();
        }
        return PhoneVersionUtil.mobile_code;
    }
    
    public static void main(String[] args) {
        int sendsMes = sendsMes("15280023756");
        System.out.println("验证码: " + sendsMes);
    }
    
    static {
        PhoneVersionUtil.Url = RuntimeConfig.get("sms.url", "http://106.ihuyi.cn/webservice/sms.php?method=Submit");
        PhoneVersionUtil.Account = RuntimeConfig.get("sms.account", "CHANGE_ME");
        PhoneVersionUtil.Password = RuntimeConfig.get("sms.password", "CHANGE_ME");
        PhoneVersionUtil.mobile_code = 0;
    }
}
