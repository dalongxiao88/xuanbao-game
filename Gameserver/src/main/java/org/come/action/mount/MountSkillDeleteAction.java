package org.come.action.mount;

import org.come.entity.MountSkill;
import java.util.List;
import org.come.entity.Mount;
import java.math.BigDecimal;
import org.come.until.AllServiceUtil;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class MountSkillDeleteAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        Mount mount = AllServiceUtil.getMountService().selectMountsByMID(new BigDecimal(message));
        if (mount == null) {
            return;
        }
        List<MountSkill> skills = mount.getMountskill();
        if (skills != null && skills.size() != 0) {
            skills.clear();
            mount.setMountskill(skills);
            AllServiceUtil.getMountService().updateMountRedis(mount);
        }
        System.out.println("MountSkillDeleteAction 为什么删除技能！！");
        AllServiceUtil.getMountskillService().deleteMountskills(new BigDecimal(message));
    }
}
