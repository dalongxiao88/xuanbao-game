package org.come.readUtil;

import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.model.InitKeju;
import org.come.tool.ReadExelTool;
import org.come.entity.Keju;
import java.util.concurrent.ConcurrentHashMap;

public class ReadKejuUtil
{
    public static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Keju>> getAllKeju(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Keju>> getAllKeju = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                InitKeju keju = new InitKeju();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(keju, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (!keju.getRaceid().equals("")) {
                        ConcurrentHashMap<Integer, Keju> hashMap = (ConcurrentHashMap<Integer, Keju>)getAllKeju.get(keju.getMid());
                        if (hashMap == null) {
                            hashMap = new ConcurrentHashMap<>();
                            getAllKeju.put(keju.getMid(), hashMap);
                        }
                        Keju kejuBean = new Keju();
                        Keju.result = result;
                        kejuBean.setMid(keju.getMid());
                        kejuBean.setWenti(keju.getWenti());
                        kejuBean.setA(keju.getA());
                        kejuBean.setB(keju.getB());
                        kejuBean.setC(keju.getC());
                        kejuBean.setD(keju.getD());
                        hashMap.put(Integer.valueOf(Integer.parseInt(String.valueOf(keju.getMid()))), kejuBean);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return getAllKeju;
    }
}
