package com.tool.ModerateTask;

import org.come.bean.RoleShow;
import org.come.bean.LoginResult;
import com.tool.role.RoleData;
import org.come.until.AnalysisString;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;

public class CreateTask
{
    public static CreateTask createTask;
    
    public static CreateTask getCreateTask() {
        if (CreateTask.createTask == null) {
            CreateTask.createTask = new CreateTask();
        }
        return CreateTask.createTask;
    }
    
    public boolean isReceive2(Task task) {
        TaskData taskData = task.getTaskData();
        String lvl = taskData.getLvl();
        int TeamSum = taskData.getTeamSum();
        return this.islvl(TeamSum, lvl);
    }
    
    public boolean islvl(int TeamSum, String lvl) {
        int minlvl = 0;
        int maxlvl = 999;
        int turn = -1;
        int maxturn = 4;
        int maxLvl = 999;
        if (lvl != null && !lvl.equals("")) {
            String[] lvls = lvl.split("\\|");
            String[] vs = lvls[0].split("\\-");
            minlvl = Integer.parseInt(vs[0]);
            maxlvl = Integer.parseInt(vs[1]);
            if (lvls.length >= 2) {
                turn = Integer.parseInt(lvls[1]);
            }
            if (lvls.length == 3) {
                vs = lvls[2].split("\\-");
                maxturn = Integer.parseInt(vs[0]);
                maxLvl = Integer.parseInt(vs[1]);
            }
        }
        if (TeamSum == 0) {
            if (ImageMixDeal.userimg.getTeams().length != 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("该任务只能一个人完成，检查组队和伙伴");
                return false;
            }
            int manlvl = AnalysisString.lvlint((int)ImageMixDeal.userimg.getRoleShow().getGrade());
            if (manlvl < minlvl || manlvl > maxlvl) {
                ZhuFrame.getZhuJpanel().addPrompt2("等级不满足在" + minlvl + "-" + maxlvl + "范围");
                return false;
            }
            int manturn = ImageMixDeal.userimg.getRoleShow().getTurnAround();
            if (manturn < turn) {
                ZhuFrame.getZhuJpanel().addPrompt2("转生次数最少" + turn + "次");
                return false;
            }
            if (manturn > maxturn || (manturn == maxturn && manlvl > maxLvl)) {
                ZhuFrame.getZhuJpanel().addPrompt2("最大到" + maxturn + "转" + maxLvl + "级");
                return false;
            }
        }
        else if (TeamSum == 1) {
            int manlvl = AnalysisString.lvlint((int)ImageMixDeal.userimg.getRoleShow().getGrade());
            if (manlvl < minlvl || manlvl > maxlvl) {
                ZhuFrame.getZhuJpanel().addPrompt2("等级不满足在" + minlvl + "-" + maxlvl + "范围");
                return false;
            }
            int manturn = ImageMixDeal.userimg.getRoleShow().getTurnAround();
            if (manturn < turn) {
                ZhuFrame.getZhuJpanel().addPrompt2("转生次数最少" + turn + "次");
                return false;
            }
            if (manturn > maxturn || (manturn == maxturn && manlvl > maxLvl)) {
                ZhuFrame.getZhuJpanel().addPrompt2("最大到" + maxturn + "转" + maxLvl + "级");
                return false;
            }
        }
        else {
            String[] v = ImageMixDeal.userimg.getTeams();
            if (v != null) {
                int palSum = 0;
                LoginResult login = RoleData.getRoleData().getLoginResult();
                if (login.getPals() != null && !login.getPals().equals("")) {
                    palSum = login.getPals().split("\\|").length;
                }
                if (v.length + palSum < TeamSum) {
                    ZhuFrame.getZhuJpanel().addPrompt2("队伍人数不够" + TeamSum + "个人,先凑齐人数在来吧");
                    return false;
                }
                for (int i = 0; i < v.length; ++i) {
                    RoleShow roleShow = ImageMixDeal.huoquLogin(v[i]).getRoleShow();
                    int manlvl2 = AnalysisString.lvlint((int)roleShow.getGrade());
                    if (manlvl2 < minlvl || manlvl2 > maxlvl) {
                        ZhuFrame.getZhuJpanel().addPrompt2("队伍等级不满足在" + minlvl + "-" + maxlvl + "范围");
                        return false;
                    }
                    int manturn2 = roleShow.getTurnAround();
                    if (manturn2 < turn) {
                        ZhuFrame.getZhuJpanel().addPrompt2("转生次数最少" + turn + "次");
                        return false;
                    }
                    if (manturn2 > maxturn || (manturn2 == maxturn && manlvl2 > maxLvl)) {
                        ZhuFrame.getZhuJpanel().addPrompt2("最大到" + maxturn + "转" + maxLvl + "级");
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
