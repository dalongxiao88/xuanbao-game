package org.come.action.mount;

import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

import org.come.bean.LoginResult;
import org.come.entity.Mount;
import java.util.ArrayList;
import org.come.model.Configure;
import org.come.server.GameServer;
import org.come.until.AchievemUtil;
import org.come.until.AllServiceUtil;
import org.come.until.GsonUtil;
import org.come.entity.MountSkill;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class MountSkillStuAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        MountSkill mountSkill = (MountSkill)GsonUtil.getGsonUtil().getgson().fromJson(message, MountSkill.class);
        Mount mount = AllServiceUtil.getMountService().selectMountsByMID(mountSkill.getMid());
        if (mount == null) {
            return;
        }
        List<MountSkill> skills = mount.getMountskill();
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = s.get(1);
        //判断是否飞行坐骑
        if (skills != null && skills.size() >= Integer.parseInt(configure.getZqjnsx()) && (int)mount.getMountid() < 8) {
            return;
        }
        if (skills != null && skills.size() >= Integer.parseInt(configure.getZqjnsx()) + 1 && (int)mount.getMountid() > 7) {
            return;
        }
        if (skills == null) {
            skills = new ArrayList<>();
        }
        skills.add(mountSkill);
        mount.setMountskill(skills);
        AllServiceUtil.getMountService().updateMountRedis(mount);
        // 添加坐骑技能
        AllServiceUtil.getMountskillService().insertMountskills(mountSkill);
        if(mountSkill!=null) {
            LoginResult loginResult=GameServer.getAllLoginRole().get(ctx);
            if(mount.getMountskill().size()>=1) {
                AchievemUtil.detectionAchievem(loginResult, "一个坐骑技能");
            }
            if(mount.getMountskill().size()>=2) {
                AchievemUtil.detectionAchievem(loginResult, "两个坐骑技能");
            }
        }
    }
}
