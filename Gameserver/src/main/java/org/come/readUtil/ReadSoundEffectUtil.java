package org.come.readUtil;

import org.come.until.GsonUtil;
import org.come.readBean.AllSoundEffect;
import org.apache.commons.lang3.StringUtils;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.entity.SoundEffect;
import java.util.concurrent.ConcurrentHashMap;

public class ReadSoundEffectUtil
{
    public static ConcurrentHashMap<Integer, SoundEffect> getAllSoundEffect(String path, StringBuffer buffer) {
        ConcurrentHashMap<Integer, SoundEffect> getAllSoundEffect = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 1; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                SoundEffect soundEffect = new SoundEffect();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(soundEffect, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                try {
                    if (soundEffect.getSkinId() != null && !StringUtils.isBlank(soundEffect.getSkinId())) {
                        SoundEffect soundEffect2 = (SoundEffect)getAllSoundEffect.get(Integer.valueOf(Integer.parseInt(soundEffect.getSkinId())));
                        if (soundEffect2 == null) {
                            getAllSoundEffect.put(Integer.valueOf(Integer.parseInt(soundEffect.getSkinId().toString())), soundEffect);
                        }
                    }
                }
                catch (Exception e2) {
                    UpXlsAndTxtFile.addStringBufferMessage(buffer, i, 0, "解析错误", MainServerHandler.getErrorMessage(e2));
                    return null;
                }
            }
        }
        return getAllSoundEffect;
    }
    
    public static String createTxtSoundEffect(ConcurrentHashMap<Integer, SoundEffect> map) {
        AllSoundEffect allSoundEffect = new AllSoundEffect();
        allSoundEffect.setAllSoundEffect(map);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(allSoundEffect);
        return msgString;
    }
}
