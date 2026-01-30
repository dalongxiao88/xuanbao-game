package com.main;

import java.io.IOException;
import org.come.until.AESUtil;
import org.come.until.CreateTextUtil;
import org.come.until.GsonUtil;
import com.updateNew.MyIsif;
import org.come.entity.ServerInfo;
import org.come.login.LoginFrame;
import org.come.log.LanderJFrame;

public class Main
{
    public static LanderJFrame landerJFrame;
    public static LoginFrame frame;
    public static ServerInfo serverInfo;
    
    public static void main(String[] args) throws Exception {
        MyIsif.ifs = "DK";
        boolean sign = false;
        for (String arg : args) {
            if (arg.startsWith("-gameParam") && arg.split("=")[1].equals("D")) {
                DKMain.main(args);
                return;
            }
            if (arg.startsWith("-index")) {
                sign = true;
            }
        }
        if (sign) {
            try {
                org.come.test.Main.main(args);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        else {
            com.tool.tab.Main.main(args);
            return;
        }
    }
    
    public static void LoadServerInfo() throws IOException {
        setServerInfo((ServerInfo)GsonUtil.getGsonUtil().getgson().fromJson(AESUtil.AESJDKDncode(CreateTextUtil.getContent("resource\\other\\server.json"), ">LA~h4FNKPMJW099vd0999"), ServerInfo.class));
    }
    
    public static ServerInfo getServerInfo() {
        return Main.serverInfo;
    }
    
    public static void setServerInfo(ServerInfo serverInfo) {
        Main.serverInfo = serverInfo;
    }
}
