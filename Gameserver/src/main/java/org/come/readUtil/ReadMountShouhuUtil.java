package org.come.readUtil;

import org.come.until.GsonUtil;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import java.util.ArrayList;
import java.util.List;

public class ReadMountShouhuUtil
{
    private static List<MountShouhu> mountShouhus;
    
    public static List<MountShouhu> selectmountshouhu(String path, StringBuffer buffer) {
        ReadMountShouhuUtil.mountShouhus = new ArrayList<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                MountShouhu mountShouhu = new MountShouhu();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(mountShouhu, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                ReadMountShouhuUtil.mountShouhus.add(mountShouhu);
            }
        }
        return ReadMountShouhuUtil.mountShouhus;
    }
    
    public static String creatMountShouhu(List<MountShouhu> list) {
        AllMountShouhu allMountShouhu = new AllMountShouhu();
        allMountShouhu.setAllmountshouhu(list);
        return GsonUtil.getGsonUtil().getgson().toJson(allMountShouhu);
    }
    
    static {
        ReadMountShouhuUtil.mountShouhus = new ArrayList<>();
    }
}
