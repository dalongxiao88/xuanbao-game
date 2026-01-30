package org.come.readUtil;

import org.come.bean.PathPoint;
import java.util.ArrayList;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.Sghostpoint;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ReadSghostpointUtil
{
    public static ConcurrentHashMap<String, List<Sghostpoint>> getMonsterTask(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, List<Sghostpoint>> monsterMap = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Sghostpoint sghostpoint = new Sghostpoint();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflect(sghostpoint, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (sghostpoint.getPointidString() != 0) {
                        List<Sghostpoint> sghostpoints2 = (List<Sghostpoint>)monsterMap.get(sghostpoint.getPointtype());
                        if (sghostpoints2 == null) {
                            sghostpoints2 = new ArrayList<>();
                            monsterMap.put(sghostpoint.getPointtype(), sghostpoints2);
                        }
                        String[] v = sghostpoint.getPointpoint().split("\\|");
                        PathPoint[] points = new PathPoint[v.length];
                        for (int k = 0; k < v.length; ++k) {
                            String[] v2 = v[k].split(",");
                            points[k] = new PathPoint(Integer.parseInt(v2[0]), Integer.parseInt(v2[1]));
                        }
                        sghostpoint.setPoints(points);
                        sghostpoints2.add(sghostpoint);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return monsterMap;
    }
}
