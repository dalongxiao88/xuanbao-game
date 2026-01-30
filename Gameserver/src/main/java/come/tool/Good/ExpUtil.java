package come.tool.Good;

import come.tool.Role.RoleData;
import come.tool.Role.RolePool;
import come.tool.Stall.AssetUpdate;
import come.tool.newTask.Task;
import come.tool.newTask.TaskState;
import come.tool.newTask.TaskUtil;
import org.come.entity.*;
import org.come.model.PalData;

import java.util.concurrent.ConcurrentHashMap;
import org.come.model.Configure;
import io.netty.channel.ChannelHandlerContext;
import org.come.model.TaskData;
import org.come.server.GameServer;
import come.tool.Battle.BattleEnd;
import org.come.until.AchievemUtil;
import org.come.until.AllServiceUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import java.math.BigDecimal;
import come.tool.Battle.BattleMixDeal;
import org.come.bean.LoginResult;
import org.come.until.GsonUtil;

public class ExpUtil
{
    public static long YEAR;
    public static long DAY;
    
    public static void RoleRemoveExp(LoginResult loginResult, long removeExp) {
        int minLvl = minRoleLvl(loginResult);
        int grade = (int)loginResult.getGrade();
        long exp = loginResult.getExperience().longValue() - removeExp;
        boolean up = false;
        while (exp < 0L && grade > minLvl) {
            int lvl = BattleMixDeal.lvlint(--grade);
            exp += getRoleExp(loginResult.getTurnAround(), lvl);
            loginResult.setGrade(Integer.valueOf(lvl));
            loginResult.setBone(Integer.valueOf(lvl));
            loginResult.setSpir(Integer.valueOf(lvl));
            loginResult.setPower(Integer.valueOf(lvl));
            loginResult.setSpeed(Integer.valueOf(lvl));
            if (loginResult.getTurnAround() >= 4) {
                loginResult.setCalm(Integer.valueOf(lvl));
            }
            up = true;
        }
        loginResult.setExperience(new BigDecimal(exp));
        if (up) {
            loginResult.setGrade(Integer.valueOf(grade));
            loginResult.setHp(new BigDecimal(0));
            loginResult.setMp(new BigDecimal(0));
            //add@magor
            SendMessage.sendMessageToMapRoles(loginResult.getMapid(),Agreement.getAgreement().RoleLevelUpAgreement(loginResult.getRolename()+"|"+grade));
            AchievemUtil.detectionAchievem(loginResult, "人物升级");
            if(loginResult.getGrade()>=20) {
                AchievemUtil.detectionAchievem(loginResult, "等级20");
            }
            if(loginResult.getGrade()>=70) {
                AchievemUtil.detectionAchievem(loginResult, "等级70");
            }
            if(loginResult.getGrade()>=90) {
                AchievemUtil.detectionAchievem(loginResult, "等级90");
            }
            if(loginResult.getGrade()>=102) {
                AchievemUtil.detectionAchievem(loginResult, "等级102");
            }

        }
    }
    
    public static void increasePointAndValue(LoginResult loginResult) {
        loginResult.setGrade(Integer.valueOf((int)loginResult.getGrade() + 1));
        loginResult.setBone(Integer.valueOf((int)loginResult.getBone() + 1));
        loginResult.setSpir(Integer.valueOf((int)loginResult.getSpir() + 1));
        loginResult.setPower(Integer.valueOf((int)loginResult.getPower() + 1));
        loginResult.setSpeed(Integer.valueOf((int)loginResult.getSpeed() + 1));
        if (loginResult.getTurnAround() >= 4) {
            loginResult.setCalm(Integer.valueOf((int)loginResult.getCalm() + 1));
        }
        loginResult.setHp(new BigDecimal(0));
        loginResult.setMp(new BigDecimal(0));
        if(loginResult.getGrade()>=520) {
            AchievemUtil.detectionAchievem(loginResult, "飞升200");
        }
        if(loginResult.getGrade()>=510) {
            AchievemUtil.detectionAchievem(loginResult, "飞升190");
        }
        if(loginResult.getGrade()>=500) {
            AchievemUtil.detectionAchievem(loginResult, "飞升180");
        }
        if(loginResult.getGrade()>=480) {
            AchievemUtil.detectionAchievem(loginResult, "飞升160");
        }
    }
    
    public static void RoleExp(LoginResult loginResult, long addexp) {
        int mostLevel = maxRoleLvl(loginResult);
        int grade = (int)loginResult.getGrade();
        long exp = loginResult.getExperience().longValue() + addexp;
        int le = BattleMixDeal.lvlint(grade);
        long maxexp = getRoleExp(loginResult.getTurnAround(), le);
        boolean up = false;
        while (exp >= maxexp && grade < mostLevel) {
            exp -= maxexp;
            ++grade;
            loginResult.setGrade(grade);
            loginResult.setBone((int) loginResult.getBone() + 1);
            loginResult.setSpir((int) loginResult.getSpir() + 1);
            loginResult.setPower((int) loginResult.getPower() + 1);
            loginResult.setSpeed((int) loginResult.getSpeed() + 1);
            if (loginResult.getTurnAround() >= 4) {
                loginResult.setCalm(Integer.valueOf((int)loginResult.getCalm() + 1));
            }
            maxexp = getRoleExp(loginResult.getTurnAround(), BattleMixDeal.lvlint(grade));
            up = true;
        }
        loginResult.setExperience(new BigDecimal(exp));
        if (up) {
            loginResult.setGrade(grade);
            loginResult.setHp(new BigDecimal(0));
            loginResult.setMp(new BigDecimal(0));
            SendMessage.sendMessageToMapRoles(loginResult.getMapid(), Agreement.getAgreement().RoleLevelUpAgreement(loginResult.getRolename() + "|" + grade));
            AchievemUtil.detectionAchievem(loginResult, "人物升级");


            if(loginResult.getGrade()>=459) {
                AchievemUtil.detectionAchievem(loginResult, "等级180");
            }
            if(loginResult.getGrade()>=439) {
                AchievemUtil.detectionAchievem(loginResult, "等级160");
            }
            if(loginResult.getGrade()>=336) {
                AchievemUtil.detectionAchievem(loginResult, "等级140");
            }
            if(loginResult.getGrade()>=208) {
                AchievemUtil.detectionAchievem(loginResult, "等级120");
            }
            if(loginResult.getGrade()>=102) {
                AchievemUtil.detectionAchievem(loginResult, "等级102");
            }
            if(loginResult.getGrade()>=90) {
                AchievemUtil.detectionAchievem(loginResult, "等级90");
            }
            if(loginResult.getGrade()>=70) {
                AchievemUtil.detectionAchievem(loginResult, "等级70");
            }
            if(loginResult.getGrade()>=20) {
                AchievemUtil.detectionAchievem(loginResult, "等级20");
            }

        }
        AllServiceUtil.getRoleTableService().updateRoleWhenExit(loginResult);
    }
    
    public static void IncreaseNedanExp(RoleSummoning pet, Goodstable goodstable, BattleEnd battleEnd, long addexp) {
        String[] vs = goodstable.getValue().split("\\|");
        String[] stringLevel = vs[2].split("=")[1].split("转");
        int zs = Integer.parseInt(stringLevel[0]);
        int lvl = Integer.parseInt(stringLevel[1]);
        long exp = Long.parseLong(vs[3].split("=")[1]) + addexp;
        int petlvl = BattleMixDeal.petLvlint((int)pet.getGrade());
        int maxlvl = getNedanMostLevel(zs);
        if (zs >= pet.getTurnRount() && lvl >= petlvl) {
            return;
        }
        if (lvl >= 200) {
            return;
        }
        long maxexp = getBBNeiExp(zs, lvl + 1);
        while (exp >= maxexp && exp > 0L) {
            if (lvl + 1 > maxlvl) {
                if (zs >= 4) {
                    break;
                }
                else if (zs + 1 > pet.getTurnRount()) {
                    break;
                }
                else {
                    ++zs;
                    lvl = 0;
                    maxexp = getBBNeiExp(zs, lvl + 1);
                    exp = 0L;
                }
            }
            else if (zs >= pet.getTurnRount() && lvl + 1 > petlvl) {
                break;
            }
            else {
                exp -= maxexp;
                ++lvl;
                maxexp = getBBNeiExp(zs, lvl + 1);
            }
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(vs[0]);
        buffer.append("|");
        buffer.append(vs[1]);
        buffer.append("|内丹等级=");
        buffer.append(zs);
        buffer.append("转");
        buffer.append(lvl);
        buffer.append("|经验=");
        buffer.append(exp);
        goodstable.setValue(buffer.toString());
        AllServiceUtil.getGoodsTableService().updateGoodRedis(goodstable);
        battleEnd.upAssetData("G" + goodstable.getRgid() + "=" + zs + "=" + lvl + "=" + exp);
        battleEnd.upMsg("你的召唤兽" + pet.getSummoningname() + "的" + goodstable.getGoodsname() + "内丹获得" + addexp + "经验");
    }
    
    public static int maxRoleLvl(LoginResult loginResult) {
        switch (loginResult.getTurnAround()) {
            case 0: {
                return 102;
            }
            case 1: {
                return 210;
            }
            case 2: {
                return 338;
            }
            case 3: {
                return 459;
            }
            case 4: {
                return (int)loginResult.getGrade();
            }
            default: {
                return 519;
            }
        }
    }
    
    public static int minRoleLvl(LoginResult loginResult) {
        switch (loginResult.getTurnAround()) {
            case 0: {
                return 1;
            }
            case 1: {
                return 103;
            }
            case 2: {
                return 211;
            }
            case 3: {
                return 339;
            }
            case 4: {
                return 460;
            }
            default: {
                return 460;
            }
        }
    }
    
    public static long getRoleExp(int TurnAround, int grade) {
        if (grade > 199) {
            grade = 199;
        }
        long exp = GameServer.getExp(grade);
        if (TurnAround >= 3) {
            exp *= 2L;
        }
        if (grade > 100 && exp < 5000000L) {
            exp = new BigDecimal("6181894660").longValue();
        }
        return exp;
    }
    
    public static long getBBNeiExp(int TurnAround, int grade) {
        return (long)((double)getRoleExp(TurnAround, grade) * 0.7);
    }
    
    public static void PetExp(RoleSummoning pet, long addexp) {
        PetExp(pet, addexp, null);
    }
    
    public static void PetExp(RoleSummoning pet, long addexp, LoginResult loginResult) {
        int mostLevel = getPetMostLevel(pet);
        int grade = pet.getGrade();
        long exp = pet.getExp().longValue() + addexp;
        for (long maxexp = getRoleExp(pet.getTurnRount(), BattleMixDeal.petLvlint(grade)); exp >= maxexp && grade < mostLevel; maxexp = getRoleExp(pet.getTurnRount(), BattleMixDeal.petLvlint(grade))) {
            exp -= maxexp;
            ++grade;
            pet.setBone(pet.getBone() + 1);
            pet.setSpir(pet.getSpir() + 1);
            pet.setPower(pet.getPower() + 1);
            pet.setSpeed(pet.getSpeed() + 1);
            pet.setFaithful(100);
            if (pet.getTurnRount() >= 4) {
                pet.setCalm(Integer.valueOf((int)pet.getCalm() + 1));
                if (loginResult != null) {
                    ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
                    UsePetAction.lvlPpenSkillSea1l(pet, ctx, loginResult);
                }
            }
        }
        pet.setExp(new BigDecimal(exp));
        if (grade > pet.getGrade()) {
            pet.setGrade(Integer.valueOf(grade));
            pet.setFaithful(Integer.valueOf(100));
        }
    }
    
    public static int getPetMostLevel(RoleSummoning pet) {
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = s.get(Integer.valueOf(1));
        switch (pet.getTurnRount()) {
            case 0: {
                return 100;
            }
            case 1: {
                return 221;
            }
            case 2: {
                return 362;
            }
            case 3: {
                return 543;
            }
            default: {
                return 744 + Integer.parseInt(configure.getZhsdjsx());
            }
        }
    }
    
    public static void MountAddES(BattleEnd battleEnd, Mount mount, int addexp, int addsld) {
        battleEnd.upMsg("坐骑 " + mount.getMountname() + "获得" + addexp + "点经验," + addsld + "点技能熟练度");
        int fs = 0;
        if (addexp > 0) {
            if ((int)mount.getMountlvl() < 100 || ((int)mount.getMountlvl() > 100 && (int)mount.getMountlvl() < 200)) {
                MountExp(mount, addexp);
            }
            else {
                ++fs;
                battleEnd.upMsg("坐骑 " + mount.getMountname() + " 已达最高等级100级！！");
            }
        }
        if (addsld > 0) {
            int up = 100000;
            if ((int)mount.getMountlvl() > 100) {
                ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
                Configure configure = (Configure)s.get(Integer.valueOf(1));
                up = Integer.parseInt(configure.getZqsld());
            }
            if ((int)mount.getProficiency() < up) {
                int proficiency = (int)mount.getProficiency() + addsld;
                if (proficiency > up) {
                    proficiency = up;
                }
                mount.setProficiency(Integer.valueOf(proficiency));
            }
            else {
                ++fs;
                battleEnd.upMsg("坐骑 " + mount.getMountname() + " 的技能熟练度已达到峰值！！");
            }
        }
        if (fs != 2) {
            AllServiceUtil.getMountService().updateMountRedis(mount);
            battleEnd.upAssetData("M" + mount.getMid() + "=" + mount.getMountlvl() + "=" + mount.getExp() + "=" + mount.getProficiency());
        }
    }
    
    public static void MountExp(Mount mount, int addexp) {
        int grade = (int)mount.getMountlvl();
        int exp = (int)mount.getExp() + addexp;
        for (long maxexp = (long)getMountExp(grade); (long)exp >= maxexp && grade != 100 && grade < 200; maxexp = (long)getMountExp(++grade)) {}
        mount.setExp(Integer.valueOf(exp));
        if (grade > (int)mount.getMountlvl()) {
            mount.setMountlvl(Integer.valueOf(grade));
        }
    }
    
    public static void MountMove(Mount mount, int addexp) {
        int moveGrade = (int)mount.getMoveGrade() + addexp;
        mount.setMoveGrade(Integer.valueOf(moveGrade));
    }
    
    public static int getMountExp(int grade) {
        if (grade > 100) {
            grade -= 90;
        }
        int nextexp = (grade + 1) * (grade + 1) * 15;
        return nextexp;
    }
    
    public static void FlyExp(Fly fly, int addexp) {
        int grade = (int)fly.getFlylvl();
        int exp = (int)fly.getExp() + addexp;
        for (long maxexp = (long)getFlyExp(grade); (long)exp > maxexp && grade != 100; maxexp = (long)getFlyExp(++grade)) {}
        fly.setExp(Integer.valueOf(exp));
        if (grade > (int)fly.getFlylvl()) {
            fly.setFlylvl(Integer.valueOf(grade));
        }
    }
    
    public static int getFlyExp(int grade) {
        if (grade > 100) {
            grade -= 90;
        }
        int nextexp = (grade + 1) * (grade + 1) * 15;
        return nextexp;
    }
    
    public static int getNedanMostLevel(int turn) {
        return (turn == 0) ? 100 : ((turn == 1) ? 120 : ((turn == 2) ? 140 : ((turn == 3) ? 170 : 200)));
    }
    
    public static String LFExp(Lingbao lingbao, long addexp) {
        int lvl = lingbao.getLingbaolvl().intValue();
        long exp = lingbao.getLingbaoexe().longValue();
        long maxexp = LFExp(lvl);
        exp += addexp;
        StringBuffer buffer = new StringBuffer();
        buffer.append("你的");
        buffer.append(lingbao.getBaoname());
        buffer.append("获得了");
        buffer.append(LFExptoString(addexp));
        buffer.append("道行");
        boolean l = false;
        while (exp >= maxexp) {
            if (lvl >= 200) {
                break;
            }
            else if (lvl != 0 && lvl % 30 == 0) {
                exp = maxexp;
                buffer.append("|突破后才可继续升级");
                break;
            }
            else {
                exp -= maxexp;
                maxexp = LFExp(++lvl);
                l = true;
            }
        }
        if (l) {
            buffer.append("|你的");
            buffer.append(lingbao.getBaoname());
            buffer.append("升级了");
        }
        lingbao.setLingbaolvl(new BigDecimal(lvl));
        lingbao.setLingbaoexe(new BigDecimal(exp));
        return buffer.toString();
    }
    
    public static String LFqh(Lingbao lingbao, long addqh) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("你的");
        buffer.append(lingbao.getBaoname());
        buffer.append("获得了");
        buffer.append(LFqhtoString(addqh));
        buffer.append("契合度");
        return buffer.toString();
    }
    
    public static void PalExp(BattleEnd battleEnd, Pal pal, long addExp) {
        PalData palData = GameServer.getPalData(pal.getpId());
        if (palData == null) {
            return;
        }
        int lvl = pal.getLvl();
        long exp = pal.getExp();
        long maxExp = palExp(lvl);
        if ((lvl == 60 || lvl == 100 || lvl == 140 || lvl == 180) && exp >= maxExp) {
            battleEnd.upMsg("伙伴已经达到等级上限,请先去突破");
        }
        else if (lvl >= 200) {
            return;
        }
        for (exp += addExp; exp > maxExp && lvl != 60 && lvl != 100 && lvl != 140 && lvl != 180 && lvl != 200; exp -= maxExp, maxExp = palExp(++lvl)) {}
        pal.setExp(exp);
        pal.setLvl(lvl);
        AllServiceUtil.getPalService().updatePal(pal);
        battleEnd.upAssetMsg("你的伙伴:#R" + palData.getName() + "#Y获得#R" + addExp + "#Y经验");
        battleEnd.getAssetUpdate().setPal(pal);
    }
    
    public static long LFExp(int lvl) {
        return (long)(lvl * lvl * 15 - (lvl - 1) * (lvl - 1) * 15);
    }
    
    public static long LFExp2(int lvl) {
        return (long)(lvl * lvl * 15);
    }
    
    public static String LFExptoString(long exp) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(exp / ExpUtil.YEAR);
        buffer.append("年");
        exp %= ExpUtil.YEAR;
        buffer.append(exp / ExpUtil.DAY);
        buffer.append("天");
        buffer.append(exp %= ExpUtil.DAY);
        buffer.append("时辰");
        return buffer.toString();
    }
    
    public static String LFqhtoString(long exp) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("10000点");
        return buffer.toString();
    }
    
    public static long palExp(int lvl) {
        return (long)(3000 * (lvl - 20));
    }
    
    static {
        ExpUtil.YEAR = 4380L;
        ExpUtil.DAY = 12L;
    }
    public static void addTaskCw(TaskData taskData, LoginResult loginResult) {
        ChannelHandlerContext ctx=GameServer.getRoleNameMap().get(loginResult.getRolename());
        Task task = TaskUtil.createTask(taskData.getTaskID(), 1, null);
        task.setTaskState(TaskState.doTasking);
        StringBuffer buffer = new StringBuffer();
        buffer.append(task.getTaskId());
        buffer.append("=");
        buffer.append(task.getTaskState());
        if (task.getTime() != 0) {
            buffer.append("=T");
            buffer.append(task.getTime() / 1000);
        }
        TaskUtil.Progress(task, buffer);
        String msg = Agreement.getAgreement().TaskNAgreement(buffer.toString());
        RoleData data = RolePool.getRoleData(loginResult.getRole_id());
        data.addTask(task, true);
        SendMessage.sendMessageByRoleName(loginResult.getRolename(), msg);
        AssetUpdate assetUpdate=new AssetUpdate();
        if(taskData.getTaskSetID() == 399) {
            assetUpdate.updata("T剧情");
            SendMessage.sendMessageToSlef(ctx,Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
    }
    public static String XBExp(XuanBao lingbao, long addexp) {
        int lvl = lingbao.lvl;
        long exp = lingbao.getXuanyun();
        long maxexp = XBExp(lvl);
        exp += addexp;
        StringBuffer buffer = new StringBuffer();
        buffer.append("你的");
        buffer.append(lingbao.getName());
        buffer.append("获得了");
        buffer.append(addexp);
        buffer.append("玄蕴");
        boolean l;
        for(l = false; exp >= maxexp && lvl < 200; l = true) {
            exp -= maxexp;
            ++lvl;
            maxexp = XBExp(lvl);
        }
        if (l) {
            buffer.append("|你的");
            buffer.append(lingbao.getName());
            buffer.append("升级了");
        }
        lingbao.setLvl(lvl);
        lingbao.setXuanyun((int)exp);
        return buffer.toString();
    }
    public static long XBExp(int lvl) {
        return lvl <= 0 ? 500L : ((long) lvl * lvl * 290 - (long) (lvl - 1) * (lvl - 1) * 290);
    }
}
