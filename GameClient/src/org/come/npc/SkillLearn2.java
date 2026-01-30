package org.come.npc;

import org.come.bean.PrivateData;
import org.come.bean.Skill;
import org.come.until.SendRoleAndRolesummingUntil;
import org.come.until.AnalysisString;
import com.tool.image.ImageMixDeal;
import org.come.Jpanel.FrameMessageChangeJpanel;
import com.tool.role.RoleData;
import org.come.until.UserMessUntil;
import org.come.action.NpcMenuAction;

public class SkillLearn2 implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type == null || type.equals("")) {
            return;
        }
        Skill skill = UserMessUntil.getskill1(type);
        if (skill == null) {
            return;
        }
        PrivateData data = RoleData.getRoleData().getPrivateData();
        String[] vs = data.getSkill("F");
        if (vs != null) {
            for (int i = 0; i < vs.length; ++i) {
                if (vs[0].startsWith(skill.getSkillid())) {
                    FrameMessageChangeJpanel.addtext(5, "你已经学会了该技能", null, null);
                    return;
                }
            }
        }
        int id = Integer.parseInt(skill.getSkillid());
        int lvl = Integer.parseInt(skill.getSkilllevel());
        StringBuffer buffer = new StringBuffer();
        if (vs != null) {
            for (int j = 0; j < vs.length; ++j) {
                if (buffer.length() != 0) {
                    buffer.append("#");
                }
                buffer.append(vs[j]);
            }
        }
        if (buffer.length() != 0) {
            buffer.append("#");
        }
        buffer.append(id);
        buffer.append("_");
        int sld = AnalysisString.shuliandu((int)ImageMixDeal.userimg.getRoleShow().getGrade());
        buffer.append(sld);
        data.setSkills("F", buffer.toString());
        SendRoleAndRolesummingUntil.sendRole(data);
        FrameMessageChangeJpanel.addtext(6, "获得#R" + skill.getSkillname() + "#W效果！", null, null);
    }
}
