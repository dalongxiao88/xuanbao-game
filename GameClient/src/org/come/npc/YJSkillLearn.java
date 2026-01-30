package org.come.npc;

import org.come.bean.PrivateData;
import org.come.bean.Skill;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.Jpanel.FrameMessageChangeJpanel;
import org.come.until.SendRoleAndRolesummingUntil;
import org.come.until.AnalysisString;
import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;

public class YJSkillLearn
{
    private static final String SKILL_PREFIX = "S";
    
    public static void YJSkill(String type) {
        try {
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            if (type == null || type.isEmpty()) {
                return;
            }
            Skill skill = UserMessUntil.getskill1(type);
            if (skill == null) {
                return;
            }
            PrivateData data = RoleData.getRoleData().getPrivateData();
            String[] skillVersions = data.getSkill("S");
            if (skillVersions != null) {
                for (String v : skillVersions) {
                    if (v.startsWith(skill.getSkillid())) {
                        return;
                    }
                }
            }
            int id = Integer.parseInt(skill.getSkillid());
            int lvl = Integer.parseInt(skill.getSkilllevel());
            int leixing = learnlimit(id, lvl, skillVersions);
            if (leixing == 0) {
                StringBuilder buffer = new StringBuilder();
                if (skillVersions != null) {
                    for (String version : skillVersions) {
                        if (buffer.length() > 0) {
                            buffer.append("#");
                        }
                        buffer.append(version);
                    }
                }
                if (buffer.length() > 0) {
                    buffer.append("#");
                }
                buffer.append(id);
                buffer.append("_");
                int sld = AnalysisString.shuliandu((int)ImageMixDeal.userimg.getRoleShow().getGrade());
                buffer.append(configure.getJineng().equals("开") ? sld : 1);
                data.setSkills("S", buffer.toString());
                SendRoleAndRolesummingUntil.sendRole(data);
                FrameMessageChangeJpanel.addtext(5, "你学会了" + skill.getSkillname(), null, null);
            }
            else if (leixing == 2) {
                FrameMessageChangeJpanel.addtext(5, "你还不够火候学习当前技能", null, null);
            }
            else if (leixing == 1) {
                FrameMessageChangeJpanel.addtext(5, "年轻人不要好高骛远,先去学习上一个技能!", null, null);
            }
        }
        catch (NumberFormatException e) {
            System.err.println("技能学习解析错误: " + e.getMessage());
        }
        catch (Exception e2) {
            System.err.println("技能学习发生错误: " + e2.getMessage());
        }
    }
    
    public static int learnlimit(int id, int lvl, String[] vs) {
        if (lvl == 1) {
            return 0;
        }
        String qzid = id - 1 + "";
        if (vs != null) {
            for (String v : vs) {
                if (v.startsWith(qzid)) {
                    int requiredLvl = Integer.parseInt(v.split("_")[1]);
                    return (requiredLvl > 200) ? 0 : 2;
                }
            }
        }
        return 1;
    }
}
