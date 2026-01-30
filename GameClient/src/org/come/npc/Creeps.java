package org.come.npc;

import org.come.Jpanel.WestboundLevelSubJpanel;
import org.come.until.SplitStringTool;
import java.util.ArrayList;
import java.util.List;
import org.come.until.Util;
import org.come.bean.RoleShow;
import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import come.tool.FightingData.FightingForesee;
import com.tool.image.ImageMixDeal;
import org.come.model.Robots;
import org.come.Jpanel.NPCJpanel;
import org.come.thread.TimeControlRunnable;
import com.tool.ModerateTask.TaskProgress;
import org.come.until.UserMessUntil;
import com.tool.ModerateTask.TaskMixDeal;
import org.come.Frame.NPCJfram;
import org.come.action.NpcMenuAction;

public class Creeps implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.startsWith("我是来击杀你的")) {
            NPCJpanel npcJpanel = NPCJfram.getNpcJfram().getNpcjpanel();
            if (npcJpanel.getTaskMonster() != null) {
                if (TaskMixDeal.istrigger(0, npcJpanel.getTaskMonster())) {
                    taskGuai(UserMessUntil.getRobot(String.valueOf(npcJpanel.getTaskMonster().getDId())), npcJpanel.getTaskMonster().getDName(), getND(type));
                }
            }
            else if (npcJpanel.getNpcInfoBean() != null) {
                String binding = npcJpanel.getNpcInfoBean().getNpctable().getBinding();
                if (binding != null && !binding.equals("") && TaskMixDeal.istrigger(Integer.parseInt(npcJpanel.getNpcInfoBean().getNpctable().getNpcid()), (TaskProgress)null)) {
                    taskGuai(UserMessUntil.getRobot(binding), npcJpanel.getNpcInfoBean().getNpctable().getNpcname(), getND(type));
                }
            }
        }
        else if ((type.equals("满地宝物先抢一个在说") || type.equals("我是来劫道的") || type.equals("攻击")) && !this.Minglei()) {
            TimeControlRunnable.addAllTask(null);
        }
        else if (type.equals("我要挑战八十一难")) {
            this.BSYNMinglei();
        }
    }
    public void BSYNMinglei() {
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        if (roleShow.getFighting() == 0) {
            FightingForesee fightingForesee = new FightingForesee();
            fightingForesee.setType(0);
            String team = roleShow.getTeam();
            if (team.split("\\|")[0].equals(roleShow.getRolename())) {
//				fightingForesee.setI(npcJpanel.getMapMonsterBean().getI());
                int nn = WestboundLevelSubJpanel.createNum+1;
                String gk = nn>10?nn+"":"0"+nn;
                fightingForesee.setRobotid((998100+Integer.parseInt(gk))+"");
                fightingForesee.setYidui(team);
                roleShow.getPlayer_Paths().clear();
                String sendMes = Agreement.FightingForeseeAgreement(GsonUtil.getGsonUtil().getgson().toJson(fightingForesee));
                SendMessageUntil.toServer(sendMes);
            }
        }
    }
    public static int getND(String type) {
        if (type.length() == 7) {
            return 0;
        }
        String ndString = type.substring(8, 10);
        return ndString.equals("困难") ? 1 : (ndString.equals("卓越") ? 2 : (ndString.equals("地狱") ? 3 : 4));
    }
    
    public static void taskGuai(Robots robots, String name, int nd) {
        if (robots != null && ImageMixDeal.userimg.getRoleShow().getFighting() == 0) {
            String team = ImageMixDeal.userimg.getRoleShow().getTeam();
            if (team.split("\\|")[0].equals(ImageMixDeal.userimg.getRoleShow().getRolename())) {
                ImageMixDeal.userimg.getRoleShow().getPlayer_Paths().clear();
                FightingForesee fightingForesee = new FightingForesee();
                fightingForesee.setYidui(team);
                fightingForesee.setAlias(name);
                fightingForesee.setRobotid(robots.getRobotid());
                fightingForesee.setNd(nd);
                fightingForesee.setType(2);
                String sendMes = Agreement.FightingForeseeAgreement(GsonUtil.getGsonUtil().getgson().toJson(fightingForesee));
                SendMessageUntil.toServer(sendMes);
            }
        }
    }
    
    public boolean Minglei() {
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        if (roleShow.getFighting() == 0) {
            NPCJpanel npcJpanel = NPCJfram.getNpcJfram().getNpcjpanel();
            if (npcJpanel.getMapMonsterBean().getType() != 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("怪物正在忙碌中");
            }
            else {
                FightingForesee fightingForesee = new FightingForesee();
                fightingForesee.setType(1);
                int robotid = (int)npcJpanel.getMapMonsterBean().getRobotid();
                if (robotid == 651 || robotid == 8000) {
                    if (roleShow.getTeam().split("\\|").length > 1) {
                        ZhuFrame.getZhuJpanel().addPrompt2("只能单人击杀");
                        return false;
                    }
                    fightingForesee.setType(4);
                }
                String team = roleShow.getTeam();
                if (team.split("\\|")[0].equals(roleShow.getRolename())) {
                    fightingForesee.setI((int)npcJpanel.getMapMonsterBean().getI());
                    fightingForesee.setYidui(team);
                    roleShow.getPlayer_Paths().clear();
                    String sendMes = Agreement.FightingForeseeAgreement(GsonUtil.getGsonUtil().getgson().toJson(fightingForesee));
                    SendMessageUntil.toServer(sendMes);
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void yeguai() {
        if (ImageMixDeal.userimg.getRoleShow().getFighting() == 0) {
            FightingForesee fightingForesee = new FightingForesee();
            String team = ImageMixDeal.userimg.getRoleShow().getTeam();
            if (team.split("\\|")[0].equals(ImageMixDeal.userimg.getRoleShow().getRolename())) {
                fightingForesee.setYidui(team);
                fightingForesee.setCreepids(geListMoster(Util.mapmodel.getGamemap().getMonster(), fightingForesee.getYidui().split("\\|").length));
                try {
                    fightingForesee.setType(0);
                    ImageMixDeal.userimg.getRoleShow().getPlayer_Paths().clear();
                    String sendMes = Agreement.FightingForeseeAgreement(GsonUtil.getGsonUtil().getgson().toJson(fightingForesee));
                    SendMessageUntil.toServer(sendMes);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public static void getfight(int type) {
        if (ImageMixDeal.userimg.getRoleShow().getFighting() == 0) {
            FightingForesee fightingForesee = new FightingForesee();
            if (ImageMixDeal.userimg.getRoleShow().getTeamInfo() != null && !ImageMixDeal.userimg.getRoleShow().getTeamInfo().equals("")) {
                fightingForesee.setYidui(ImageMixDeal.userimg.getRoleShow().getTeamInfo());
            }
            else {
                fightingForesee.setYidui(ImageMixDeal.userimg.getRoleShow().getRolename());
            }
            try {
                fightingForesee.setType(type);
                ImageMixDeal.userimg.getRoleShow().getPlayer_Paths().clear();
                String sendMes = Agreement.FightingForeseeAgreement(GsonUtil.getGsonUtil().getgson().toJson(fightingForesee));
                SendMessageUntil.toServer(sendMes);
            }
            catch (Exception var3) {
                var3.printStackTrace();
            }
        }
    }
    
    public static List<String> geListMoster(String monsterStr, int playerNum) {
        List<String> monsters = new ArrayList<>();
        List<String> monsterList = SplitStringTool.splitString(monsterStr);
        for (int i = 0; i < 10 && (i <= playerNum || Util.random.nextInt(2) != 1); ++i) {
            monsters.add((String)monsterList.get(Util.random.nextInt(monsterList.size())));
        }
        return monsters;
    }
}
