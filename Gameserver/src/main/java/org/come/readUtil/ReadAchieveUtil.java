package org.come.readUtil;

import java.util.List;
import come.tool.Good.DropModel;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.model.Achieve;
import java.util.ArrayList;
import org.come.tool.ReadExelTool;
import org.come.readBean.AllAchieve;

public class ReadAchieveUtil
{
    public static AllAchieve selectAchieves(String path, StringBuffer buffer) {
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        List<Achieve> achieves = new ArrayList<>();
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Achieve achieve = new Achieve();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(achieve, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (achieve.getId() != 0) {
                        achieve.setDropModel(new DropModel(achieve.getDropId().split("\\|")));
                        achieves.add(achieve);
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, result[i][0], MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        AllAchieve achieve2 = new AllAchieve(achieves);
        return achieve2;
    }
}
