package org.come.npc;

import org.come.bean.PrivateData;
import org.come.bean.Skill;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.until.SendRoleAndRolesummingUntil;
import org.come.until.AnalysisString;
import com.tool.image.ImageMixDeal;
import org.come.Jpanel.FrameMessageChangeJpanel;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.action.NpcMenuAction;

public class SkillLearn implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        if (type == null || type.equals("")) {
            return;
        }
        Skill skill = UserMessUntil.getskill1(type);
        if (skill == null) {
            return;
        }
        PrivateData data = RoleData.getRoleData().getPrivateData();
        String[] vs = data.getSkill("S");
        if (vs != null) {
            for (int i = 0; i < vs.length; ++i) {
                if (vs[i].startsWith(skill.getSkillid())) {
                    FrameMessageChangeJpanel.addtext(5, "你已经学会了该技能", null, null);
                    return;
                }
            }
        }
        int id = Integer.parseInt(skill.getSkillid());
        int lvl = Integer.parseInt(skill.getSkilllevel());
        int leixing = this.learnlimit(id, lvl, vs);
        if (leixing == 0) {
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
            if (configure.getJineng().equals("开")) {
                buffer.append(sld);
            }
            else {
                buffer.append(1);
            }
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
    
    public int learnlimit(int id, int lvl, String[] vs) {
        if (lvl == 1) {
            return 0;
        }
        String qzid = id - 1 + "";
        int i = 0;
        while (i < vs.length) {
            if (vs[i].startsWith(qzid)) {
                if (Integer.parseInt(vs[i].split("_")[1]) > 200) {
                    return 0;
                }
                return 2;
            }
            else {
                ++i;
            }
        }
        return 1;
    }
}
