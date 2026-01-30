

package org.come.readUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.come.entity.XuanBao;
import org.come.handler.MainServerHandler;
import org.come.readBean.AllXuanbao;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.tool.ReadExelTool;
import org.come.tool.SettModelMemberTool;
import org.come.until.GsonUtil;

public class ReadXuanBaoUtil {


    public static ConcurrentHashMap<Integer, XuanBao> getallXuanbao(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, XuanBao> allbbuy = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");

        if (result != null) {
            for(int i = 1; i < result.length; ++i) {
                if (!result[i][0].equals("")) {
                    XuanBao bbuy = new XuanBao();

                    for(int j = 0; j < result[i].length; ++j) {
                        try {
                            SettModelMemberTool.setReflectRelative(bbuy, result[i][j], j);
                        } catch (Exception var8) {
                            UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(var8));
                            return null;
                        }
                    }

                    allbbuy.put(bbuy.getId(), bbuy);
                }
            }
        }

        return allbbuy;
    }

    public static String createBbuy(ConcurrentHashMap<Integer, XuanBao> map) {
        AllXuanbao allBbuy = new AllXuanbao();
        Map<Integer, XuanBao> allbbuy = new HashMap<>();

        for (XuanBao bbuy : map.values()) {
            allbbuy.put(bbuy.getId(), bbuy);
        }
        allBbuy.setaMap(allbbuy);
        return GsonUtil.getGsonUtil().getgson().toJson(allBbuy);
    }
}
