package com.main;

import java.io.IOException;

import org.come.login.LoginJpanel;
import org.come.until.AESUtil;
import org.come.until.CreateTextUtil;
import org.come.until.GsonUtil;
import org.come.test.Main;
import com.updateNew.MyIsif;
import org.come.entity.ServerInfo;
import org.come.log.SetJframe;
import org.come.login.LoginFrame;
import org.come.log.LanderJFrame;

public class UpdateMain
{
    public static LanderJFrame landerJFrame;
    public static LoginFrame frame;
    public static String Param;
    public static SetJframe setJframe;
    public static String linkUrl;
    public static String updateIP;
    public static ServerInfo serverInfo;

    public static void main(String[] args) throws Exception {
        MyIsif.ifs = "DK";
        boolean sign = false;
        for (String arg : args) {
            if (!arg.startsWith("-gameParam") || arg.split("=")[1].equals("D")) {}
            if (arg.startsWith("-index")) {
                sign = true;
            }
        }
        if (sign) {
            try {
                Main.main(args);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        else {
            getSetJframe();
            UpdateMain.landerJFrame = new LanderJFrame();
            Thread thread = new Thread(UpdateMain.landerJFrame);
            thread.start();
            return;
        }
    }

    public static SetJframe getSetJframe() {
        if (UpdateMain.setJframe == null) {
            UpdateMain.setJframe = new SetJframe();
        }
        return UpdateMain.setJframe;
    }

    public static void setSetJframe(SetJframe setJframe) {
        UpdateMain.setJframe = setJframe;
    }

    public static void LoadServerInfo() throws IOException {
        setServerInfo((ServerInfo)GsonUtil.getGsonUtil().getgson().fromJson(AESUtil.AESJDKDncode(CreateTextUtil.getContent("resource\\other\\server.json"), ">LA~h4FNKPMJW099vd0999"), ServerInfo.class));
    }

    public static ServerInfo getServerInfo() {
        return UpdateMain.serverInfo;
    }

    public static void setServerInfo(ServerInfo serverInfo) {
        UpdateMain.serverInfo = serverInfo;
    }

    static {
        UpdateMain.Param = "DK";
        UpdateMain.linkUrl = "http://81.70.45.106:"+ LoginJpanel.Webport+"/link/";
        UpdateMain.updateIP = "http://81.70.45.106:"+ LoginJpanel.Webport+"/updateNewFile";
    }
}
