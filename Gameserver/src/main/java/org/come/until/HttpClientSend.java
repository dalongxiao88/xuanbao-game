package org.come.until;

import java.net.HttpURLConnection;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import java.io.UnsupportedEncodingException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import java.nio.charset.Charset;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import java.net.URLConnection;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

public class HttpClientSend
{
    public static String sendPost(String url, String param) {
        OutputStreamWriter ow = null;
        BufferedReader in = null;
        StringBuffer result = null;
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            ow = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            ow.write(param);
            ow.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            result = new StringBuffer();
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        finally {
            try {
                if (ow != null) {
                    ow.close();
                }
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
    
    public static String post(String json, String URL) {
        String obj = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(URL);
        httppost.addHeader("Content-type", "application/json; charset=utf-8");
        httppost.setHeader("Accept", "application/json");
        try {
            StringEntity s = new StringEntity(json, Charset.forName("UTF-8"));
            s.setContentEncoding("UTF-8");
            httppost.setEntity(s);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    obj = EntityUtils.toString(entity, "UTF-8");
                }
            }
            finally {
                response.close();
            }
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        finally {
            try {
                httpclient.close();
            }
            catch (IOException e4) {
                e4.printStackTrace();
            }
        }
        return obj;
    }
    
    public static void sendGet(String URL, String param) {
        try {
            URL url = new URL(URL + "?" + param);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) {
                result.append(line + "\n");
            }
            connection.disconnect();
            System.out.println(result.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
