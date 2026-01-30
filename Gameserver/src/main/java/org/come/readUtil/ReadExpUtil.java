package org.come.readUtil;

import java.util.Iterator;
import org.come.until.GsonUtil;
import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;
import org.come.bean.RoleExpBean;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.model.Exp;
import org.come.tool.ReadExelTool;
import java.util.concurrent.ConcurrentHashMap;

public class ReadExpUtil
{
    public static ConcurrentHashMap<Integer, Long> getExp(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, Long> roleExpMap = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Exp exp = new Exp();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(exp, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (!exp.getExpid().equals("")) {
                        roleExpMap.put(Integer.valueOf(Integer.parseInt(exp.getExpid())), Long.valueOf(Long.parseLong(exp.getExpvalues())));
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return roleExpMap;
    }
    
    public static String createExp(ConcurrentHashMap<Integer, Long> map) {
        RoleExpBean roleExpBean = new RoleExpBean();
        Map<Integer, BigDecimal> rolePetExpMap = new HashMap<>();
        for (Map.Entry<Integer, Long> entrys : map.entrySet()) {
            rolePetExpMap.put(entrys.getKey(), new BigDecimal((long)entrys.getValue()));
        }
        roleExpBean.setRolePetExpMap(rolePetExpMap);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(roleExpBean);
        return msgString;
    }
}
