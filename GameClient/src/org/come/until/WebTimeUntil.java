package org.come.until;

import java.net.URLConnection;
import java.util.List;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WebTimeUntil
{
    public static long getNetworkTime() {
        try {
            String webUrl1 = "http://www.ntsc.ac.cn";
            String webUrl2 = "http://www.baidu.com";
            String webUrl3 = "http://www.taobao.com";
            String webUrl4 = "http://www.ntsc.ac.cn";
            String webUrl5 = "http://www.360.cn";
            String webUrl6 = "http://www.beijing-time.org";
            String webUrl7 = "http://www.163.com/";
            String webUrl8 = "https://www.tmall.com/";
            String webUrl9 = "http://time.tianqi.com";
            String webUrl10 = "http://tv.cctv.com";
            List<String> a = new ArrayList<>();
            a.add(webUrl1);
            a.add(webUrl2);
            a.add(webUrl3);
            a.add(webUrl4);
            a.add(webUrl5);
            a.add(webUrl6);
            a.add(webUrl7);
            a.add(webUrl8);
            a.add(webUrl9);
            a.add(webUrl10);
            URL url = new URL((String)a.get(Util.random.nextInt(10)));
            URLConnection conn = url.openConnection();
            conn.connect();
            long dateL = conn.getDate() + 30000L;
            return dateL;
        }
        catch (MalformedURLException var15) {
            var15.printStackTrace();
        }
        catch (IOException var16) {
            var16.printStackTrace();
        }
        return System.currentTimeMillis() + 30000L;
    }
}
