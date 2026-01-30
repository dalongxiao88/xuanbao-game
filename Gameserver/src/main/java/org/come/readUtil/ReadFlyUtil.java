package org.come.readUtil;

import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.model.InitFly;
import org.come.tool.ReadExelTool;
import org.come.entity.Fly;
import java.util.concurrent.ConcurrentHashMap;

public class ReadFlyUtil
{
    public static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Fly>> getAllFly(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Fly>> getAllFly = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                InitFly fly = new InitFly();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(fly, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (!fly.getRaceid().equals("")) {
                        ConcurrentHashMap<Integer, Fly> hashMap = (ConcurrentHashMap<Integer, Fly>)getAllFly.get(Integer.valueOf(Integer.parseInt(fly.getRaceid())));
                        if (hashMap == null) {
                            hashMap = new ConcurrentHashMap<>();
                            getAllFly.put(Integer.valueOf(Integer.parseInt(fly.getRaceid())), hashMap);
                        }
                        Fly flyBean = new Fly();
                        flyBean.setSkin(Integer.valueOf(Integer.parseInt(fly.getSkin())));
                        flyBean.setFlystate(Integer.valueOf(Integer.parseInt(fly.getFlystate())));
                        flyBean.setFlyname(fly.getflyname());
                        flyBean.setFlytid(Integer.valueOf(Integer.parseInt(fly.getflyid())));
                        flyBean.setFlylvl(Integer.valueOf(Integer.parseInt(fly.getflylvl())));
                        flyBean.setGradeexp(Integer.valueOf(Integer.parseInt(fly.getExp())));
                        hashMap.put(Integer.valueOf(Integer.parseInt(fly.getflyid())), flyBean);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return getAllFly;
    }
}
