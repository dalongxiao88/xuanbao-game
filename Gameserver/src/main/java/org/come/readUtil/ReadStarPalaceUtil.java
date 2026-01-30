package org.come.readUtil;

import come.tool.Calculation.BaseQl;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.entity.StarPalace;
import java.util.concurrent.ConcurrentHashMap;

public class ReadStarPalaceUtil
{
    public static ConcurrentHashMap<String, StarPalace> selectStarPalace(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, StarPalace> map = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                StarPalace starPalace = new StarPalace();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(starPalace, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (starPalace.getType() != null && !starPalace.getType().equals("")) {
                        BaseQl[] starSXs = null;
                        if (starPalace.getValue() != null && !starPalace.getValue().equals("")) {
                            String[] vs = starPalace.getValue().split("\\|");
                            starSXs = new BaseQl[vs.length];
                            for (int k = 0; k < vs.length; ++k) {
                                String[] vss = vs[k].split("=");
                                starSXs[k] = new BaseQl(vss[0], Double.parseDouble(vss[1]));
                            }
                        }
                        else {
                            starSXs = new BaseQl[0];
                        }
                        starPalace.setVs(starSXs);
                        map.put(starPalace.getType(), starPalace);
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
