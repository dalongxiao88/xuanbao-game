package org.come.readUtil;

import java.util.Iterator;
import java.util.Map;
import org.come.until.GsonUtil;
import org.come.model.SkillModel;
import java.util.HashMap;
import org.come.bean.SkillBean;
import org.come.servlet.UpXlsAndTxtFile;
import org.come.handler.MainServerHandler;
import org.come.tool.SettModelMemberTool;
import org.come.tool.ReadExelTool;
import org.come.model.Skill;
import java.util.concurrent.ConcurrentHashMap;

public class ReadSkillUtil
{
    public static ConcurrentHashMap<String, Skill> getSkill(String path, StringBuffer buffer) {
        ConcurrentHashMap<String, Skill> skillMap = new ConcurrentHashMap<>();
        String[][] result = ReadExelTool.getResult("config/" + path + ".xls");
        for (int i = 2; i < result.length; ++i) {
            if (!result[i][0].equals("")) {
                Skill skill = new Skill();
                for (int j = 0; j < result[i].length; ++j) {
                    try {
                        SettModelMemberTool.setReflectRelative(skill, result[i][j], j);
                    }
                    catch (Exception e) {
                        UpXlsAndTxtFile.addStringBufferMessage(buffer, i, j, result[i][j], MainServerHandler.getErrorMessage(e));
                        return null;
                    }
                }
                if (skill.getSkillid() != 0) {
                    skillMap.put(skill.getSkillid() + "", skill);
                    skillMap.put(skill.getSkillname(), skill);
                }
            }
        }
        return skillMap;
    }
    
    public static String createSkill(ConcurrentHashMap<String, Skill> map) {
        SkillBean skillBean = new SkillBean();
        Map<String, SkillModel> skillMap = new HashMap<>();
        for (Skill skill : map.values()) {
            SkillModel skillModel = new SkillModel(skill);
            skillMap.put(skillModel.getSkillid(), skillModel);
        }
        skillBean.setSkillMap(skillMap);
        String msgString = GsonUtil.getGsonUtil().getgson().toJson(skillBean);
        return msgString;
    }
}
