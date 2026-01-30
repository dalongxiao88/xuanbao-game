package org.come.readUtil;

import java.util.Iterator;
import java.util.Map;
import org.come.until.GsonUtil;
import java.util.HashMap;
import org.come.readBean.AllTx;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.bean.RoleTxBean;
import java.util.concurrent.ConcurrentHashMap;

public class ReadTxUtil
{
    public static ConcurrentHashMap<Integer, RoleTxBean> selectDecoration(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, RoleTxBean> allTXs = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                RoleTxBean bean = new RoleTxBean();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(bean, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                allTXs.put(Integer.valueOf(bean.getGid()), bean);
            }
        }
        return allTXs;
    }
    
    public static String createTX(ConcurrentHashMap<Integer, RoleTxBean> map) {
        AllTx allTx = new AllTx();
        Map<Integer, RoleTxBean> txmap = new HashMap<>();
        for (RoleTxBean bean : map.values()) {
            txmap.put(Integer.valueOf(bean.getGid()), bean);
        }
        allTx.setTxmap(txmap);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allTx);
        return msgString;
    }
}
