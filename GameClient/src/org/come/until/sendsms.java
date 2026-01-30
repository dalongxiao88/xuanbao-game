package org.come.until;

import org.dom4j.Element;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import java.io.IOException;
import org.apache.commons.httpclient.HttpException;
import org.dom4j.DocumentHelper;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.HttpClient;

public class sendsms
{
    private static String Url;
    
    public static String sendUNtil(String phone) {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(sendsms.Url);
        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");
        int mobile_code = (int)((Math.random() * 9.0 + 1.0) * 100000.0);
        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
        NameValuePair[] data = { new NameValuePair("account", "C69900359"), new NameValuePair("password", "6e0a061805272583c415c312caabaea4"), new NameValuePair("mobile", phone), new NameValuePair("content", content) };
        method.setRequestBody(data);
        try {
            client.executeMethod(method);
            String SubmitResult = method.getResponseBodyAsString();
            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();
            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");
            if ("2".equals(code)) {
                String var13 = String.valueOf(mobile_code);
                return var13;
            }
            return "error";
        }
        catch (HttpException var14) {
            var14.printStackTrace();
        }
        catch (IOException var15) {
            var15.printStackTrace();
        }
        catch (DocumentException var16) {
            var16.printStackTrace();
        }
        finally {
            method.releaseConnection();
        }
        return content;
    }
    
    static {
        sendsms.Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
    }
}
