package org.come.readUtil;

import java.util.ArrayList;
import java.math.BigDecimal;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.server.GolemStallConfig;
import org.come.server.GameServer;
import org.apache.commons.lang.StringUtils;
import org.come.tool.ReadExelTool;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

public class ReadGolemConfig
{
    public static List<String> GolemName;
    
    public static void getGolemConfig(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, String> configs = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!StringUtils.isBlank(result[i][0])) {
                configs.put(result[i][0], result[i][1]);
            }
        }
        GameServer.getGolemConfig().setConfigs(configs);
        String[][] result2;
        for (String[] strings : result2 = ReadExelTool.getResult("config/golemName.xls")) {
            for (int k = strings.length, p = 0; p < k; ++p) {
                if (!StringUtils.isBlank(strings[0]) && strings[p] != null && strings[p].length() < 8) {
                    ReadGolemConfig.GolemName.add(strings[p]);
                }
            }
        }
        int c = 0;
    }
    
    public static void getGolemStall(String path, StringBuffer buffer) {
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!StringUtils.isBlank(result[i][0]) && !StringUtils.isBlank(result[i][1])) {
                GolemStallConfig golemStallConfig = new GolemStallConfig();
                for (int j = 2; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(golemStallConfig, result[i][j], j - 2);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                    }
                }
                String s = result[i][0];
                int n = -1;
                switch (s.hashCode()) {
                    case 48: {
                        if (s.equals("0")) {
                            n = 0;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 49: {
                        if (s.equals("1")) {
                            n = 1;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 50: {
                        if (s.equals("2")) {
                            n = 2;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                    case 51: {
                        if (s.equals("3")) {
                            n = 3;
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }
                switch (n) {
                    case 0: {
                        GameServer.getGolemConfig().addGoodsStallConfig(new BigDecimal(result[i][1]), golemStallConfig);
                        break;
                    }
                    case 1: {
                        GameServer.getGolemConfig().addPetStallConfig(new BigDecimal(result[i][1]), golemStallConfig);
                        break;
                    }
                    case 2: {
                        GameServer.getGolemConfig().addLingBaoStallConfig(result[i][1], golemStallConfig);
                        break;
                    }
                    case 3: {
                        GameServer.getGolemConfig().addShouGouStallConfig(new BigDecimal(result[i][1]), golemStallConfig);
                        break;
                    }
                }
            }
        }
    }
    
    static {
        ReadGolemConfig.GolemName = new ArrayList<>();
    }
}
