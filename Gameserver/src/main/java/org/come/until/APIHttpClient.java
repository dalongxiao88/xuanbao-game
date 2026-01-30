package org.come.until;

import org.apache.http.HttpResponse;
import java.io.IOException;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import java.nio.charset.Charset;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.HttpClient;
import org.apache.commons.logging.Log;

public class APIHttpClient
{
    private static String apiURL;
    private Log logger;
    private static final String pattern = "yyyy-MM-dd HH:mm:ss:SSS";
    private HttpClient httpClient;
    private HttpPost method;
    private long startTime;
    private long endTime;
    private int status;
    
    public APIHttpClient(String url) {
        this.logger = LogFactory.getLog(this.getClass());
        this.httpClient = null;
        this.method = null;
        this.startTime = 0L;
        this.endTime = 0L;
        this.status = 0;
        if (url != null) {
            APIHttpClient.apiURL = url;
        }
        if (APIHttpClient.apiURL != null) {
            this.httpClient = new DefaultHttpClient();
            this.method = new HttpPost(APIHttpClient.apiURL);
        }
    }
    
    public String post(String parameters) {
        String body = null;
        this.logger.info("parameters:" + parameters);
        if ((this.method != null & parameters != null) && !"".equals(parameters.trim())) {
            try {
                this.method.addHeader("Content-type", "application/json; charset=utf-8");
                this.method.setHeader("Accept", "application/json");
                this.method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
                this.startTime = System.currentTimeMillis();
                HttpResponse response = this.httpClient.execute(this.method);
                this.endTime = System.currentTimeMillis();
                int statusCode = response.getStatusLine().getStatusCode();
                this.logger.info("statusCode:" + statusCode);
                this.logger.info("调用API 花费时间(单位：毫秒)：" + (this.endTime - this.startTime));
                if (statusCode != 200) {
                    this.logger.error("Method failed:" + response.getStatusLine());
                    this.status = 1;
                }
                body = EntityUtils.toString(response.getEntity());
            }
            catch (IOException e) {
                this.status = 3;
            }
            finally {
                this.logger.info("调用接口状态：" + this.status);
            }
        }
        return body;
    }
    
    public static void sendMes(String url, String mes) {
        APIHttpClient ac = new APIHttpClient(url);
        ac.post(mes);
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public long getStartTime() {
        return this.startTime;
    }
    
    public long getEndTime() {
        return this.endTime;
    }
    
    static {
        APIHttpClient.apiURL = "http://127.0.0.1/GameServer.v6.0/servlet/AppServerlet";
    }
}
