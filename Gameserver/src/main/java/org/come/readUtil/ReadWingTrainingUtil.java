package org.come.readUtil;

import java.util.List;
import org.come.tool.SplitStringTool;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.entity.WingTraining;
import java.util.concurrent.ConcurrentHashMap;

public class ReadWingTrainingUtil
{
    public static ConcurrentHashMap<Long, WingTraining> selectWingTraining(String path, StringBuffer buffer) {
        ConcurrentHashMap<Long, WingTraining> map = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                WingTraining wingTraining = new WingTraining();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(wingTraining, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (wingTraining.getId() != 0) {
                        String[] vs = wingTraining.getBase().split("\\|");
                        double[] bases = new double[vs.length];
                        for (int k = 0; k < bases.length; ++k) {
                            bases[k] = Double.parseDouble(vs[k]);
                        }
                        wingTraining.setBases(bases);
                        List<String> list = SplitStringTool.splitString(wingTraining.getType());
                        for (int l = list.size() - 1; l >= 0; --l) {
                            map.put(new Long((String)list.get(l)), wingTraining);
                        }
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return map;
    }
}
