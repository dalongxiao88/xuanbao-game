package come.tool.Good;

import cn.hutool.core.util.StrUtil;
import come.tool.FightingData.Battlefield;
import org.come.action.npc.NpcComposeAction;
import org.come.bean.NChatBean;
import org.come.entity.Record;
import org.come.action.summoning.SummonPetAction;
import org.come.model.*;
import org.come.task.MonsterUtil;
import come.tool.Scene.SceneUtil;
import org.come.task.MapZB;
import org.come.until.GsonUtil;
import java.util.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.come.action.lottery.DrawBase;
import come.tool.newTask.Task;
import come.tool.Role.PrivateData;
import java.util.Collections;
import java.util.Comparator;
import org.come.entity.RandomNum;
import java.util.ArrayList;
import org.come.action.sys.ChangeMapAction;
import org.come.bean.ChangeMapBean;
import come.tool.newTask.TaskConsume;
import come.tool.newTask.TaskUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.tool.WriteOut;
import come.tool.Mixdeal.AnalysisString;
import org.apache.commons.lang.StringUtils;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import come.tool.Battle.BattleEnd;
import org.come.entity.RoleSummoning;
import java.util.concurrent.ConcurrentHashMap;

import come.tool.newGang.GangDomain;
import org.come.entity.Goodstable;
import org.come.bean.XXGDBean;
import come.tool.Role.RoleData;
import come.tool.newGang.GangUtil;
import org.come.entity.Titletable;
import org.come.until.AllServiceUtil;
import org.come.action.suit.SuitMixdeal;
import java.math.BigDecimal;
import org.come.action.reward.DrawnitemsAction;
import org.come.action.monitor.MonitorUtil;
import come.tool.Battle.BattleMixDeal;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import come.tool.Stall.AssetUpdate;
import org.come.bean.LoginResult;
import java.util.Random;

public class DropUtil
{
    public static Random random;
    
    public static AssetUpdate getDrop(LoginResult login, DropModel model, String msg, AssetUpdate assetUpdate, double expXs1, double expXs2, int num, int type) {
        if (model == null) {
            return assetUpdate;
        }
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(login.getRolename());
        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        if (ctx == null || roleData == null) {
            return assetUpdate;
        }
        int lvl = BattleMixDeal.lvlint((int)login.getGrade());
        StringBuffer buffer = new StringBuffer();
        if (model.getMaxDrop() == null || (int)model.getMaxDrop() > lvl) {
            if (model.getMoney() != null) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                }
                assetUpdate.updata("D=" + model.getMoney());
                login.setGold(login.getGold().add(model.getMoney()));
                if (type == 24) {
                    MonitorUtil.getMoney().addD(model.getMoney().longValue(), 0);
                    MonitorUtil.getDropQM1().add(model.getMoney().longValue());
                }
                else {
                    MonitorUtil.getMoney().addD(model.getMoney().longValue(), 1);
                    MonitorUtil.getDropQM2().add(model.getMoney().longValue());
                }
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得金钱");
                buffer.append(model.getMoney());
            }
            if (model.getCodeCard() != null) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                }
                assetUpdate.updata("X=" + model.getCodeCard());
                login.setCodecard(login.getCodecard().add(model.getCodeCard()));
                MonitorUtil.getMoney().addX(model.getCodeCard().longValue(), 2);
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得仙玉");
                buffer.append(model.getCodeCard());
            }
        }
        if (model.getExp() != null || model.getExpFix() != null) {
            long exp = 0L;
            if (model.getExp() != null) {
                exp = (long)((double)exp + (double)model.getExp().longValue() * expXs1);
            }
            if (model.getExpFix() != null) {
                exp += model.getExpFix().longValue();
            }
            exp = (long)((double)exp * ((double)model.getExps(num) + expXs2));
            int grade = (int)login.getGrade();
            if (grade > 459) {
                exp /= 2L;
            }
            else {
                exp *= 1L;
            }
            if (exp != 0L) {
                if (model.getMaxRole() != null && (int)model.getMaxRole() < lvl) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你超过了经验最大获取等级");
                }
                else {
                    ExpUtil.RoleExp(login, exp);
                    if (assetUpdate == null) {
                        assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                    }
                    assetUpdate.updata("R" + login.getGrade() + "=" + login.getExperience() + "=" + login.getHp() + "=" + login.getMp());
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你获得#Y" + exp + "#W经验");
                }
            }
        }
        if (model.getTypes() != null && (model.getMaxDrop() == null || (int)model.getMaxDrop() > lvl)) {
            for (int i = 0; i < model.getTypes().size(); ++i) {
                DropType dropType = (DropType)model.getTypes().get(i);
                if (dropType.getDropType() == 1) {
                    if (assetUpdate == null) {
                        assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                    }
                    assetUpdate.updata(dropType.getKey() + "=" + dropType.getValue());
                    login.setScore(DrawnitemsAction.Splice(login.getScore(), dropType.getKey() + "=" + dropType.getValue(), 2));
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你获得");
                    buffer.append(dropType.getKey());
                    buffer.append(dropType.getValue());
                }
                else if (dropType.getDropType() == 2) {
                    if (roleData.getPackRecord().addOther(dropType.getValue() + "")) {
                        EventModel eventModel = GameServer.getEvent((int)dropType.getValue());
                        if (eventModel != null) {
                            eventModel.resetRanking(login.getRole_id(), login.getRolename());
                        }
                    }
                }
                else if (dropType.getDropType() == 3) {
                    if (assetUpdate == null) {
                        assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                    }
                    assetUpdate.updata(dropType.getKey() + "=" + dropType.getValue());
                    login.setKill(DrawnitemsAction.Splice(login.getKill(), dropType.getKey() + "=" + dropType.getValue(), 5));
                }
                else if (dropType.getDropType() == 4) {
                    if (model.getMaxGood() != null && (int)model.getMaxGood() < num) {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append("达到最大物品获取次数");
                    }
                    else if (roleData.isGoodFull()) {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append("你背包已满");
                    }
                    else {
                        XXGDBean bean = getGoods(dropType.getDropGood());
                        if (bean != null) {
                            BigDecimal id = new BigDecimal(bean.getId());
                            Goodstable goodstable = GameServer.getGood(id);
                            if ((id.longValue() >= 0L || !roleData.getPackRecord().isTX(-id.longValue() + "")) && goodstable != null) {
                                if (buffer.length() != 0) {
                                    buffer.append("|");
                                }
                                buffer.append("你获得");
                                buffer.append(bean.getSum());
                                buffer.append("个");
                                buffer.append(goodstable.getGoodsname());
                                if (assetUpdate == null) {
                                    assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                                }
                                if (!login.isGolem()) {
                                    AddGoodAction.addGood(assetUpdate, goodstable, login, roleData, bean, type);
                                }
                                SuitMixdeal.good(goodstable, login.getRolename(), msg, type);
                            }
                        }
                    }
                }
                else if (dropType.getDropType() == 5) {
                    XXGDBean bean = getGoods(dropType.getDropGood());
                    if (bean != null) {
                        AddMonster(login.getRole_id(), login.getRolename(), bean.getId(), null);
                    }
                }
                else if (dropType.getDropType() == 6) {
                    XXGDBean bean = getGoods(dropType.getDropGood());
                    if (bean != null) {
                        Title title = GameServer.getTitle(bean.getId());
                        if (title != null) {
                            if (AllServiceUtil.getTitletableService().selectRoleTitle(login.getRole_id(), title.getTitlename()) == null) {
                                Titletable titletable = new Titletable();
                                titletable.setTitlename(title.getTitlename());
                                titletable.setRoleid(login.getRole_id());
                                titletable.setLimittime(title.getLimitTime());
                                AllServiceUtil.getTitletableService().createRoleTitle(titletable);
                                if (buffer.length() != 0) {
                                    buffer.append("|");
                                }
                                buffer.append("你获得称谓:");
                                buffer.append(title.getTitlename());
                            }
                            else {
                                buffer.append("你已经拥有称谓:");
                                buffer.append(title.getTitlename());
                            }
                        }
                    }
                }
                else if (dropType.getDropType() == 7) {
                    if (assetUpdate == null) {
                        assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                    }
                    assetUpdate.updata("B=" + dropType.getValue());
                    login.setContribution(login.getContribution().add(new BigDecimal((int)dropType.getValue())));
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你获得帮贡");
                    buffer.append(dropType.getValue());
                    GangDomain gangDomain = GangUtil.getGangDomain(login.getGang_id());
                    if (gangDomain != null) {
                        gangDomain.addBG((long)(int)dropType.getValue());
                    }
                }
                else if (dropType.getDropType() == 8) {
                    if (assetUpdate == null) {
                        assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                    }
                    assetUpdate.updata("S=" + dropType.getValue());
                    login.setSavegold(login.getSavegold().add(new BigDecimal((int)dropType.getValue())));
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你获得师贡");
                    buffer.append(dropType.getValue());
                }
            }
        }
        if (buffer.length() != 0) {
            if (assetUpdate == null) {
                assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            }
            assetUpdate.upmsg(buffer.toString());
        }
        return assetUpdate;
    }
    
    public static AssetUpdate getDropYk(LoginResult login, DropModel model, String msg, AssetUpdate assetUpdate, double expXs1, double expXs2, int num, int type) {
        if (model == null) {
            return assetUpdate;
        }
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(login.getRolename());
        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        if (ctx == null || roleData == null) {
            return assetUpdate;
        }
        int lvl = BattleMixDeal.lvlint((int)login.getGrade());
        StringBuffer buffer = new StringBuffer();
        if (model.getMaxDrop() == null || (int)model.getMaxDrop() > lvl) {
            if (model.getMoney() != null) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                }
                assetUpdate.updata("D=" + model.getMoney());
                login.setGold(login.getGold().add(model.getMoney()));
                if (type == 24) {
                    MonitorUtil.getMoney().addD(model.getMoney().longValue(), 0);
                    MonitorUtil.getDropQM1().add(model.getMoney().longValue());
                }
                else {
                    MonitorUtil.getMoney().addD(model.getMoney().longValue(), 1);
                    MonitorUtil.getDropQM2().add(model.getMoney().longValue());
                }
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得金钱");
                buffer.append(model.getMoney());
            }
            if (model.getCodeCard() != null) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                }
                assetUpdate.updata("X=" + model.getCodeCard());
                login.setCodecard(login.getCodecard().add(model.getCodeCard()));
                MonitorUtil.getMoney().addX(model.getCodeCard().longValue(), 2);
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得仙玉");
                buffer.append(model.getCodeCard());
            }
        }
        if (model.getExp() != null || model.getExpFix() != null) {
            long exp = 0L;
            if (model.getExp() != null) {
                exp = (long)((double)exp + (double)model.getExp().longValue() * expXs1);
            }
            if (model.getExpFix() != null) {
                exp += model.getExpFix().longValue();
            }
            exp = (long)((double)exp * ((double)model.getExps(num) + expXs2));
            int grade = (int)login.getGrade();
            if (grade > 459) {
                exp /= 2L;
            }
            else {
                exp *= 1L;
            }
            if (exp != 0L) {
                if (model.getMaxRole() != null && (int)model.getMaxRole() < lvl) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你超过了经验最大获取等级");
                }
                else {
                    ExpUtil.RoleExp(login, exp);
                    if (assetUpdate == null) {
                        assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                    }
                    assetUpdate.updata("R" + login.getGrade() + "=" + login.getExperience() + "=" + login.getHp() + "=" + login.getMp());
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你获得" + exp + "经验");
                }
            }
        }
        if (model.getTypes() != null && (model.getMaxDrop() == null || (int)model.getMaxDrop() > lvl)) {
            for (int i = 0; i < model.getTypes().size(); ++i) {
                DropType dropType = (DropType)model.getTypes().get(i);
                if (dropType.getDropType() == 1) {
                    if (assetUpdate == null) {
                        assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                    }
                    assetUpdate.updata(dropType.getKey() + "=" + dropType.getValue());
                    login.setScore(DrawnitemsAction.Splice(login.getScore(), dropType.getKey() + "=" + dropType.getValue(), 2));
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你获得");
                    buffer.append(dropType.getKey());
                    buffer.append(dropType.getValue());
                }
                else if (dropType.getDropType() == 2) {
                    if (roleData.getPackRecord().addOther(dropType.getValue() + "")) {
                        EventModel eventModel = GameServer.getEvent((int)dropType.getValue());
                        if (eventModel != null) {
                            eventModel.resetRanking(login.getRole_id(), login.getRolename());
                        }
                    }
                }
                else if (dropType.getDropType() == 3) {
                    if (assetUpdate == null) {
                        assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                    }
                    assetUpdate.updata(dropType.getKey() + "=" + dropType.getValue());
                    login.setKill(DrawnitemsAction.Splice(login.getKill(), dropType.getKey() + "=" + dropType.getValue(), 5));
                }
                else if (dropType.getDropType() == 4) {
                    if (model.getMaxGood() != null && (int)model.getMaxGood() < num) {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append("达到最大物品获取次数");
                    }
                    else if (roleData.isGoodFull()) {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append("你背包已满");
                    }
                    else {
                        XXGDBean bean = getGoods(dropType.getDropGood());
                        if (bean != null) {
                            BigDecimal id = new BigDecimal(bean.getId());
                            Goodstable goodstable = GameServer.getGood(id);
                            if ((id.longValue() >= 0L || !roleData.getPackRecord().isTX(-id.longValue() + "")) && goodstable != null) {
                                if (buffer.length() != 0) {
                                    buffer.append("|");
                                }
                                buffer.append("你获得");
                                buffer.append(bean.getSum());
                                buffer.append("个");
                                buffer.append(goodstable.getGoodsname());
                                if (assetUpdate == null) {
                                    assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                                }
                                AddGoodAction.addGood(assetUpdate, goodstable, login, roleData, bean, type);
                            }
                        }
                    }
                }
                else if (dropType.getDropType() == 5) {
                    XXGDBean bean = getGoods(dropType.getDropGood());
                    if (bean != null) {
                        AddMonster(login.getRole_id(), login.getRolename(), bean.getId(), null);
                    }
                }
                else if (dropType.getDropType() == 6) {
                    XXGDBean bean = getGoods(dropType.getDropGood());
                    if (bean != null) {
                        Title title = GameServer.getTitle(bean.getId());
                        if (title != null) {
                            if (AllServiceUtil.getTitletableService().selectRoleTitle(login.getRole_id(), title.getTitlename()) == null) {
                                Titletable titletable = new Titletable();
                                titletable.setTitlename(title.getTitlename());
                                titletable.setRoleid(login.getRole_id());
                                titletable.setLimittime(title.getLimitTime());
                                AllServiceUtil.getTitletableService().createRoleTitle(titletable);
                                if (buffer.length() != 0) {
                                    buffer.append("|");
                                }
                                buffer.append("你获得称谓:");
                                buffer.append(title.getTitlename());
                            }
                            else {
                                buffer.append("你已经拥有称谓:");
                                buffer.append(title.getTitlename());
                            }
                        }
                    }
                }
                else if (dropType.getDropType() == 7) {
                    if (assetUpdate == null) {
                        assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                    }
                    assetUpdate.updata("B=" + dropType.getValue());
                    login.setContribution(login.getContribution().add(new BigDecimal((int)dropType.getValue())));
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你获得帮贡");
                    buffer.append(dropType.getValue());
                    GangDomain gangDomain = GangUtil.getGangDomain(login.getGang_id());
                    if (gangDomain != null) {
                        gangDomain.addBG((long)(int)dropType.getValue());
                    }
                }
                else if (dropType.getDropType() == 8) {
                    if (assetUpdate == null) {
                        assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                    }
                    assetUpdate.updata("S=" + dropType.getValue());
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    login.setSavegold(login.getSavegold().add(new BigDecimal((int)dropType.getValue())));
                    buffer.append("你获得师贡");
                    buffer.append(dropType.getValue());
                }
            }
        }
        ConcurrentHashMap<Integer, Configure> s = GameServer.getAllConfigure();
        Configure configure = (Configure)s.get(5);
        if (buffer.length() != 0) {
            if (assetUpdate == null) {
                assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
            }
            assetUpdate.upmsg(buffer.toString());
        }
        return assetUpdate;
    }
    
    public static long getDrop(LoginResult login, RoleSummoning pet, DropModel model, String msg, BattleEnd battleEnd, double expXs1, double expXs2, int num, int type, int sum, int dropXS, int ndXS) {
        return getDrop(login, pet, model, msg, battleEnd, expXs1, expXs2, num, type, sum, dropXS, ndXS, null);
    }
    
    public static long getDrop(LoginResult login, RoleSummoning pet, DropModel model, String msg, BattleEnd battleEnd, double expXs1, double expXs2, int num, int type, int sum, int dropXS, int ndXS, Robots robots) {
        long goodExp = 0L;
        if (model == null) {
            return goodExp;
        }
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(login.getRolename());
        RoleData roleData = RolePool.getRoleData(login.getRole_id());
        if (ctx == null || roleData == null) {
            return goodExp;
        }
        double xs = (ndXS == 0) ? 1.0 : ((ndXS == 1) ? 1.3 : ((ndXS == 2) ? 1.7 : ((ndXS == 3) ? 2.2 : 2.8)));
        int lvl = BattleMixDeal.lvlint((int)login.getGrade());
        StringBuffer buffer = new StringBuffer();
        if (robots != null && model.getMaxKill() != null) {
            Integer count = Integer.valueOf(0);
            String v = (String)RedisControl.getV(RedisParameterUtil.ROBOT, login.getRole_id() + "_" + robots.getRobotID(), String.class);
            if (StringUtils.isNotBlank(v)) {
                count = Integer.valueOf(Integer.parseInt(v));
            }
            count = Integer.valueOf((int)count + 1);
            RedisControl.insertKey(RedisParameterUtil.ROBOT, login.getRole_id() + "_" + robots.getRobotID(), count.toString());
            if (model.getMaxKill() != null && (int)count > (int)model.getMaxKill()) {
                battleEnd.upAssetMsg(robots.getRobotname() + "每日击杀次数已达上限，无法获得奖励！");
                return goodExp;
            }
        }
        if (model.getMaxDrop() == null || (int)model.getMaxDrop() > lvl) {
            if (model.getMoney() != null) {
                long money = model.getMoney().longValue();
                money = (long)((double)money * xs);
                if (dropXS != 0) {
                    money *= (long)(1 + dropXS);
                }
                if (money != 0L) {
                    battleEnd.upAssetData("D=" + money);
                    login.setGold(login.getGold().add(new BigDecimal(money)));
                    if (type == 24) {
                        MonitorUtil.getMoney().addD(money, 0);
                        MonitorUtil.getDropQM1().add(money);
                    }
                    else {
                        MonitorUtil.getMoney().addD(money, 1);
                        MonitorUtil.getDropQM2().add(money);
                    }
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你获得金钱#Y");
                    buffer.append(money + "#W两");
                }
            }
            if (model.getCodeCard() != null) {
                battleEnd.upAssetData("X=" + model.getCodeCard());
                login.setCodecard(login.getCodecard().add(model.getCodeCard()));
                MonitorUtil.getMoney().addX(model.getCodeCard().longValue(), 2);
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得仙玉");
                buffer.append(model.getCodeCard());
            }
        }
        if (model.getExp() != null || model.getExpFix() != null) {
            long exp = 0L;
            if (model.getExp() != null) {
                exp = (long)((double)exp + (double)model.getExp().longValue() * expXs1);
            }
            if (model.getExpFix() != null) {
                exp += model.getExpFix().longValue();
            }
            exp = (long)((double)exp * ((double)model.getExps(num) + expXs2));
            exp = (long)((double)exp * xs);
            int grade = (int)login.getGrade();
            if (grade > 459) {
                exp /= 2L;
            }
            else {
                exp *= 1L;
            }
            if (dropXS != 0) {
                exp *= (long)(1 + dropXS);
            }
            if (exp != 0L) {
                if (model.getMaxRole() != null && (int)model.getMaxRole() < lvl) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你超过了经验最大获取等级");
                }
                else {
                    ExpUtil.RoleExp(login, exp);
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你获得#Y" + exp + "#W经验");
                }
                if (pet != null) {
                    if (model.getMaxPet() != null && (int)model.getMaxPet() < BattleMixDeal.petLvlint((int)pet.getGrade())) {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append("你的召唤兽超过了经验最大获取等级");
                    }
                    else {
                        if (grade > 459) {
                            exp *= 2L;
                        }
                        exp *= 2L;
                        ExpUtil.PetExp(pet, exp, login);
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append("你的召唤兽" + pet.getSummoningname() + "获得#Y" + exp + "#W经验");
                        goodExp = exp;
                    }
                }
            }
        }
        if (model != null && model.getSkillGrow() != null && model.getSkillId() != null && (int)model.getSkillGrow() > 0 && model.getSkillId().compareTo(BigDecimal.ZERO) > 0) {
            StringBuffer sb = new StringBuffer();
            PrivateData privateData = roleData.getPrivateData();
            String[] vs = privateData.getSkill("S");
            int maxsld = AnalysisString.shuliandu((int)roleData.getLoginResult().getGrade());
            int addsld = (int)model.getSkillGrow();
            if (vs == null) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你还未学习技能");
            }
            else {
                boolean a = false;
                for (int i = 0; i < vs.length; ++i) {
                    String[] vs2 = vs[i].split("_");
                    Integer id = Integer.valueOf(Integer.parseInt(vs2[0]));
                    Integer val = Integer.valueOf(Integer.parseInt(vs2[1]));
                    Skill kill = GameServer.getSkill(String.valueOf(id));
                    if ((int)id == model.getSkillId().intValue()) {
                        if ((int)val >= maxsld) {
                            sb.append(vs[i] + "#");
                            if (buffer.length() != 0) {
                                buffer.append("|");
                            }
                            buffer.append("您的该技能已满");
                        }
                        else if ((int)val + addsld >= maxsld) {
                            sb.append(id);
                            sb.append("_" + String.valueOf(maxsld) + "#");
                            int addslds = maxsld - (int)val;
                            if (buffer.length() != 0) {
                                buffer.append("|");
                            }
                            buffer.append("您的" + ((kill != null) ? kill.getSkillname() : "") + "技能增了" + addslds + "点熟练度");
                            battleEnd.upAssetData("SKILL" + id + "=" + String.valueOf(addslds));
                        }
                        else {
                            sb.append(id);
                            sb.append("_" + String.valueOf((int)val + addsld) + "#");
                            if (buffer.length() != 0) {
                                buffer.append("|");
                            }
                            buffer.append("您的" + ((kill != null) ? kill.getSkillname() : "") + "技能增了" + addsld + "点熟练度");
                            battleEnd.upAssetData("SKILL" + id + "=" + String.valueOf(addsld));
                        }
                    }
                    else {
                        sb.append(vs[i] + "#");
                    }
                }
            }
            if (sb.toString().endsWith("#")) {
                privateData.setSkills("S", sb.toString().substring(0, sb.toString().indexOf("#")));
            }
            else {
                privateData.setSkills("S", sb.toString());
            }
            roleData.upPrivateData(privateData);
        }
        if (model.getTaskId() != null) {
            int rwjl = DropUtil.random.nextInt(100);
            Integer taskJL = model.getTaskJL();
            if (rwjl < (int)taskJL) {
                int nsize = 0;
                Integer taskID = model.getTaskId();
                if ((int)taskID < 1000) {
                    WriteOut.addtxt("非法领取任务" + taskID + ":" + login.getRole_id() + ":" + login.getRolename(), 9999L);
                }
                TaskData taskData = GameServer.getTaskData((int)taskID);
                if (taskData == null) {}
                String[] teams = login.getTeam().split("\\|");
                int max = 0;
                int length = 0;
                if (taskData != null) {
                    length = ((taskData.getTeamSum() <= 1) ? 1 : teams.length);
                }
                int size = teams.length + roleData.PSize();
                if (size > 5) {
                    size = 5;
                }
                LoginResult[] logs = new LoginResult[length];
                for (int j = 0; j < length; ++j) {
                    LoginResult logint = null;
                    if (j == 0) {
                        logint = login;
                    }
                    else {
                        ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[j]);
                        if (ctx2 != null) {
                            logint = (LoginResult)GameServer.getAllLoginRole().get(ctx2);
                        }
                    }
                    if (logint == null) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(teams[j] + "处于异常状态"));
                    }
                    logs[j] = logint;
                    int lvlin = 0;
                    if (logint != null) {
                        lvlin = BattleMixDeal.lvlint((int)logint.getGrade());
                    }
                    if (lvlin > max) {
                        max = lvlin;
                    }
                }
                TaskConsume taskConsume = null;
                Object object = TaskUtil.isTaskReceive(nsize == 0, taskData, max, size, logs);
                if (object != null) {
                    if (object instanceof String) {
                        SendMessage.sendMessageToSlef(ctx, (String)object);
                    }
                    else if (object instanceof TaskConsume) {
                        taskConsume = (TaskConsume)object;
                    }
                }
                if (object == null) {
                    TaskUtil.taskConsume(taskConsume, logs);
                    if (taskData.getDoorID() != 0) {
                        Door door = GameServer.getDoor(taskData.getDoorID());
                        if (door != null) {
                            ChangeMapBean changeMapBean = new ChangeMapBean();
                            changeMapBean.setMapid(door.getDoormap());
                            String[] vs3 = door.getDoorpoint().split("\\|");
                            changeMapBean.setMapx(Integer.parseInt(vs3[0]));
                            changeMapBean.setMapy(Integer.parseInt(vs3[1]));
                            ChangeMapAction.ChangeMap(changeMapBean, ctx);
                        }
                    }
                    if (nsize == 0) {
                        TaskUtil.addSumReceive(taskData, logs);
                    }
                    Task task = TaskUtil.createTask((int)taskID, max);
                    task.setTaskState(2);
                    if (task.getProgress() != null) {
                        StringBuffer buffer2 = new StringBuffer();
                        buffer2.append(task.getTaskId());
                        buffer2.append("=");
                        buffer2.append(task.getTaskState());
                        if (task.getTime() != 0L) {
                            buffer2.append("=T");
                            buffer2.append(task.getTime() / 1000L);
                        }
                        TaskUtil.Progress(task, buffer2);
                        if (nsize == 0 && taskData.getTaskSet().getSumreceive() != 0) {
                            buffer2.append("|C");
                            buffer2.append(taskData.getTaskSetID());
                            buffer2.append("=R");
                        }
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append("你领取了" + taskData.getTaskName() + "任务");
                        String msg2 = Agreement.getAgreement().TaskNAgreement(buffer2.toString());
                        for (int k = 0; k < logs.length; ++k) {
                            RoleData data = RolePool.getRoleData(logs[k].getRole_id());
                            data.addTask(task, k == 0);
                            SendMessage.sendMessageByRoleName(logs[k].getRolename(), msg2);
                        }
                    }
                }
            }
        }
        if (model.getTypes() != null && (model.getMaxDrop() == null || (int)model.getMaxDrop() > lvl)) {
            for (int l = 0; l < model.getTypes().size(); ++l) {
                DropType dropType = (DropType)model.getTypes().get(l);
                if (dropType.getDropType() == 1) {
                    int value = (int)dropType.getValue();
                    value = (int)((double)value * xs);
                    battleEnd.upAssetData(dropType.getKey() + "=" + value);
                    login.setScore(DrawnitemsAction.Splice(login.getScore(), dropType.getKey() + "=" + value, 2));
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你获得");
                    buffer.append(dropType.getKey());
                    buffer.append(value);
                }
                else if (dropType.getDropType() == 2) {
                    if (roleData.getPackRecord().addOther(dropType.getValue() + "")) {
                        EventModel eventModel = GameServer.getEvent((int)dropType.getValue());
                        if (eventModel != null) {
                            eventModel.resetRanking(login.getRole_id(), login.getRolename());
                        }
                    }
                }
                else if (dropType.getDropType() == 3) {
                    battleEnd.upAssetData(dropType.getKey() + "=" + dropType.getValue());
                    login.setKill(DrawnitemsAction.Splice(login.getKill(), dropType.getKey() + "=" + dropType.getValue(), 5));
                }
                else if (dropType.getDropType() == 14) {
                    for (int m = 0; m < dropType.getDropGood().getDraws().length; ++m) {
                        DrawBase drawBase = dropType.getDropGood().getDraws()[m];
                        if (isV(drawBase.getV())) {
                            String dropId = drawBase.getDropId();
                            Skill skill = GameServer.getSkill(dropId + "");
                            UsePetAction.addPetSkill(pet, null, ctx, login, skill);
                        }
                    }
                }
                else if (dropType.getDropType() == 4) {
                    if (model.getMaxGood() != null && (int)model.getMaxGood() < num) {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append("达到最大物品获取次数");
                    }
                    else if (roleData.isGoodFull()) {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append("你背包已满");
                    }
                    else {
                        XXGDBean bean = getGoods(dropType.getDropGood(), getEmptyXS(sum, num, dropXS, ndXS));
                        if (bean != null) {
                            BigDecimal id2 = new BigDecimal(bean.getId());
                            Goodstable goodstable = GameServer.getGood(id2);
                            if ((id2.longValue() >= 0L || !roleData.getPackRecord().isTX(-id2.longValue() + "")) && goodstable != null && (goodstable.getType() != 1105L || (boolean)model.getTeam())) {
                                if (goodstable.getType() == 1001L) {
                                    AssetUpdate assetUpdate = new AssetUpdate();
                                    if (battleEnd != null && battleEnd.getAssetUpdate() != null) {
                                        assetUpdate = battleEnd.getAssetUpdate();
                                    }
                                    if (goodstable.getType() == 1001L) {
                                        String[] s = goodstable.getValue().split("=")[1].split("&");
                                        List<RandomNum> randomNums = new ArrayList<>();
                                        for (String string : s) {
                                            String[] m2 = string.split("\\$");
                                            RandomNum randomNum = new RandomNum();
                                            randomNum.setProbability(Integer.parseInt(m2[0]));
                                            String[] n = m2[1].split("-");
                                            randomNum.setMin(Integer.parseInt(n[0]));
                                            randomNum.setMax(Integer.parseInt(n[1]));
                                            randomNums.add(randomNum);
                                        }
                                        Collections.sort(randomNums, new Comparator<RandomNum>() {
                                            @Override
                                            public int compare(RandomNum p1, RandomNum p2) {
                                                return Integer.compare(p1.getProbability(), p2.getProbability());
                                            }
                                        });
                                        Boolean b = Boolean.valueOf(false);
                                        BigDecimal number = BigDecimal.ZERO;
                                        for (RandomNum randomNum2 : randomNums) {
                                            if (randomNum2.getProbability() > new Random().nextInt(100)) {
                                                b = Boolean.valueOf(true);
                                                int money2 = randomNum2.getMin() + new Random().nextInt(randomNum2.getMax() - randomNum2.getMin());
                                                number = new BigDecimal(money2 + "");
                                                break;
                                            }
                                        }
                                        if (number.longValue() == 0L) {
                                            int min = ((RandomNum)randomNums.get(randomNums.size() - 1)).getMin();
                                            number = new BigDecimal(min + "");
                                        }
                                        if (goodstable.getValue().startsWith("金钱")) {
                                            long money3 = number.longValue();
                                            if (money3 != 0L) {
                                                battleEnd.upAssetData("D=" + money3);
                                                login.setGold(login.getGold().add(new BigDecimal(money3)));
                                                if (type == 24) {
                                                    MonitorUtil.getMoney().addD(money3, 0);
                                                    MonitorUtil.getDropQM1().add(money3);
                                                }
                                                else {
                                                    MonitorUtil.getMoney().addD(money3, 1);
                                                    MonitorUtil.getDropQM2().add(money3);
                                                }
                                                if (buffer.length() != 0) {
                                                    buffer.append("|");
                                                }
                                                buffer.append("你获得金钱");
                                                buffer.append(money3);
                                            }
                                        }
                                        else if (goodstable.getValue().startsWith("仙玉")) {
                                            long money3 = number.longValue();
                                            if (money3 != 0L) {
                                                battleEnd.upAssetData("X=" + money3);
                                                login.setCodecard(login.getCodecard().add(new BigDecimal(money3)));
                                                MonitorUtil.getMoney().addX(money3, 1);
                                                if (buffer.length() != 0) {
                                                    buffer.append("|");
                                                }
                                                buffer.append("你获得仙玉#Y");
                                                buffer.append(money3);
                                            }
                                        }
                                    }
                                }
                                else {
                                    if (buffer.length() != 0) {
                                        buffer.append("|");
                                    }
                                    buffer.append("你获得");
                                    buffer.append(bean.getSum());
                                    buffer.append("个");
                                    buffer.append(goodstable.getGoodsname());
                                    if (battleEnd.getAssetUpdate() == null) {
                                        battleEnd.setAssetUpdate(new AssetUpdate(AssetUpdate.USEGOOD));
                                    }
                                    AddGoodAction.addGood(battleEnd.getAssetUpdate(), goodstable, login, roleData, bean, type);
                                    SuitMixdeal.good(goodstable, login.getRolename(), msg, type);
                                }
                            }
                        }
                    }
                }
                else if (dropType.getDropType() == 13 && (boolean)model.getTeam()) {
                    if (model.getMaxGood() != null && (int)model.getMaxGood() < num) {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append("达到最大物品获取次数");
                    }
                    else if (roleData.isGoodFull()) {
                        if (buffer.length() != 0) {
                            buffer.append("|");
                        }
                        buffer.append("你背包已满");
                    }
                    else {
                        XXGDBean bean = getGoods(dropType.getDropGood(), getEmptyXS(sum, num, dropXS, ndXS));
                        if (bean != null) {
                            BigDecimal id2 = new BigDecimal(bean.getId());
                            Goodstable goodstable = GameServer.getGood(id2);
                            if ((id2.longValue() >= 0L || !roleData.getPackRecord().isTX(-id2.longValue() + "")) && goodstable != null && (goodstable.getType() != 1105L || (boolean)model.getTeam())) {
                                if (goodstable.getType() == 1001L) {
                                    AssetUpdate assetUpdate = new AssetUpdate();
                                    if (goodstable.getType() == 1001L) {
                                        assetUpdate.setType(AssetUpdate.USERGOOD);
                                        String[] s = goodstable.getValue().split("=")[1].split("&");
                                        List<RandomNum> randomNums = new ArrayList<>();
                                        for (String string : s) {
                                            String[] m2 = string.split("\\$");
                                            RandomNum randomNum = new RandomNum();
                                            randomNum.setProbability(Integer.parseInt(m2[0]));
                                            String[] n = m2[1].split("-");
                                            randomNum.setMin(Integer.parseInt(n[0]));
                                            randomNum.setMax(Integer.parseInt(n[1]));
                                            randomNums.add(randomNum);
                                        }
                                        Collections.sort(randomNums, new Comparator<RandomNum>() {
                                            @Override
                                            public int compare(RandomNum p1, RandomNum p2) {
                                                return Integer.compare(p1.getProbability(), p2.getProbability());
                                            }
                                        });
                                        Boolean b = Boolean.valueOf(false);
                                        BigDecimal number = BigDecimal.ZERO;
                                        for (RandomNum randomNum2 : randomNums) {
                                            if (randomNum2.getProbability() > new Random().nextInt(100)) {
                                                b = Boolean.valueOf(true);
                                                int money2 = randomNum2.getMin() + new Random().nextInt(randomNum2.getMax() - randomNum2.getMin());
                                                number = new BigDecimal(money2 + "");
                                                break;
                                            }
                                        }
                                        if (number.longValue() == 0L) {
                                            int min = ((RandomNum)randomNums.get(randomNums.size() - 1)).getMin();
                                            number = new BigDecimal(min + "");
                                        }
                                        if (goodstable.getValue().startsWith("金钱")) {
                                            long money3 = number.longValue();
                                            if (money3 != 0L) {
                                                battleEnd.upAssetData("D=" + money3);
                                                login.setGold(login.getGold().add(new BigDecimal(money3)));
                                                if (type == 24) {
                                                    MonitorUtil.getMoney().addD(money3, 0);
                                                    MonitorUtil.getDropQM1().add(money3);
                                                }
                                                else {
                                                    MonitorUtil.getMoney().addD(money3, 1);
                                                    MonitorUtil.getDropQM2().add(money3);
                                                }
                                                if (buffer.length() != 0) {
                                                    buffer.append("|");
                                                }
                                                buffer.append("你获得金钱");
                                                buffer.append(money3 + "#W两");
                                            }
                                        }
                                        else if (goodstable.getValue().startsWith("仙玉")) {
                                            long money3 = number.longValue();
                                            if (money3 != 0L) {
                                                battleEnd.upAssetData("X=" + money3);
                                                login.setGold(login.getGold().add(new BigDecimal(money3)));
                                                if (type == 24) {
                                                    MonitorUtil.getMoney().addD(money3, 0);
                                                    MonitorUtil.getDropQM1().add(money3);
                                                }
                                                else {
                                                    MonitorUtil.getMoney().addD(money3, 1);
                                                    MonitorUtil.getDropQM2().add(money3);
                                                }
                                                if (buffer.length() != 0) {
                                                    buffer.append("|");
                                                }
                                                buffer.append("你获得仙玉#Y");
                                                buffer.append(money3 + "#W个");
                                            }
                                        }
                                    }
                                }
                                else {
                                    if (buffer.length() != 0) {
                                        buffer.append("|");
                                    }
                                    buffer.append("你获得");
                                    buffer.append(bean.getSum());
                                    buffer.append("个");
                                    buffer.append(goodstable.getGoodsname());
                                    if (battleEnd.getAssetUpdate() == null) {
                                        battleEnd.setAssetUpdate(new AssetUpdate(AssetUpdate.USEGOOD));
                                    }
                                    AddGoodAction.addGood(battleEnd.getAssetUpdate(), goodstable, login, roleData, bean, type);
                                    SuitMixdeal.good(goodstable, login.getRolename(), msg, type);
                                }
                            }
                        }
                    }
                }
                else if (dropType.getDropType() == 5) {
                    XXGDBean bean = getGoods(dropType.getDropGood());
                    if (bean != null) {
                        AddMonster(login.getRole_id(), login.getRolename(), bean.getId(), null);
                    }
                }else if (dropType.getDropType() == DropType.ZXGOODS) {//放妖=x
                    if (roleData.isGoodFull()) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("背包已满#4无法触发鸡驴大神的到来！#36"));
                        continue;
                    }
                    if (dropType.getKey() != null && dropType.getKey() != "") {
                        String[] vs = dropType.getKey().split("\\$");
                        if (Battlefield.isV(Integer.parseInt(vs[2]))) {
                            String goods = "";
                            Dorp dorp = GameServer.getDorp(vs[0]);
                            if (dorp != null) {
                                goods = dorp.getDorpValue();
                            }
                            String[] vss = goods.split("&");
                            List<String> goodsIds = new ArrayList<>();
                            for (int ii = 1; ii < vss.length; ii++) {
                                String[] v = vss[ii].split("\\$");
                                for (String vd : v[0].split("-")) {
                                    goodsIds.add(vd);
                                }
                            }
                            Collections.shuffle(goodsIds);
                            String doodsid = "";
                            for (int iiii = 0; iiii < 5; iiii++) {
                                doodsid += goodsIds.get(iiii) + "|";
                            }
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().roleZXGOODAgreement(doodsid));
                            NChatBean bean = new NChatBean();
                            bean.setId(5);
                            bean.setMessage("#Y恭喜玩家#R" + roleData.getLoginResult().getRolename() + "#c00FFFF触发#G鸡驴大神#c00FFFF降临，获得自选物品的资格！！！");
                            SendMessage.sendMessageToAllRoles(Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean)));
                        }
                    }
                }else if (dropType.getDropType() == 6) {
                    XXGDBean bean = getGoods(dropType.getDropGood());
                    if (bean != null) {
                        Title title = GameServer.getTitle(bean.getId());
                        if (title != null) {
                            if (AllServiceUtil.getTitletableService().selectRoleTitle(login.getRole_id(), title.getTitlename()) == null) {
                                Titletable titletable = new Titletable();
                                titletable.setTitlename(title.getTitlename());
                                titletable.setRoleid(login.getRole_id());
                                titletable.setLimittime(title.getLimitTime());
                                AllServiceUtil.getTitletableService().createRoleTitle(titletable);
                                if (buffer.length() != 0) {
                                    buffer.append("|");
                                }
                                buffer.append("你获得称谓:");
                                buffer.append(title.getTitlename());
                            }
                            else {
                                buffer.append("你已经拥有称谓:");
                                buffer.append(title.getTitlename());
                            }
                        }
                    }
                }
                else if (dropType.getDropType() == 7) {
                    int value = (int)dropType.getValue();
                    value = (int)((double)value * xs);
                    battleEnd.upAssetData("B=" + value);
                    login.setContribution(login.getContribution().add(new BigDecimal(value)));
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你获得帮贡");
                    buffer.append(value);
                    GangDomain gangDomain = GangUtil.getGangDomain(login.getGang_id());
                    if (gangDomain != null) {
                        gangDomain.addBG((long)value);
                    }
                }
                else if (dropType.getDropType() == 8) {
                    int value = (int)dropType.getValue();
                    value = (int)((double)value * xs);
                    battleEnd.upAssetData("S=" + value);
                    login.setSavegold(login.getSavegold().add(new BigDecimal((int)dropType.getValue())));
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你获得师贡");
                    buffer.append(value);
                }
            }
        }
        if (buffer.length() != 0) {
            battleEnd.upAssetMsg(buffer.toString());
        }
        return goodExp;
    }
    
    public static void getDrop(LoginResult loginResult, String value, String msg, int type, double xs, String data) {
        getDrop4(loginResult, value, msg, type, xs, data, null, null, null);
    }
    
    public static void getDrop2(LoginResult loginResult, String value, String msg, int type, double xs, String data, String dataMes) {
        getDrop4(loginResult, value, msg, type, xs, data, dataMes, null, null);
    }
    
    public static void getDrop3(LoginResult loginResult, String value, String msg, int type, double xs, String data, String dataMes, String message) {
        getDrop4(loginResult, value, msg, type, xs, data, dataMes, message, null);
    }
    
    public static void getDrop4(LoginResult loginResult, String value, String msg, int type, double xs, String data, String dataMes, String message, String task) {
        if (value == null || value.equals("")) {
            return;
        }
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
        if (ctx == null) {
            return;
        }
        boolean is = true;
        if (type == 999) {
            is = false;
            type = 25;
        }
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        AssetUpdate assetUpdate = null;
        StringBuffer buffer = new StringBuffer();
        String[] vs;
        for (String v : vs = value.split("\\|")) {
            String[] thing = v.split("=");
            if (thing[0].equals("物品")) {
                if (is && roleData.isGoodFull()) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你背包已满");
                }
                else {
                    XXGDBean bean = getGoods(thing[1]);
                    if (bean != null) {
                        BigDecimal id = new BigDecimal(bean.getId());
                        Goodstable goodstable = GameServer.getGood(id);
              //          if ((id.longValue() >= 0L || !roleData.getPackRecord().isTX(-id.longValue() + "")) && goodstable != null) {
              //              if (assetUpdate == null) {
              //                  assetUpdate = new AssetUpdate(type);
              //              }
                        if (goodstable.getType() == 60029 || goodstable.getType() == 60030 || goodstable.getType() == 60031) {
                            goodstable.setRole_id(loginResult.getRole_id());
                            goodstable = GameServer.getTreeGood(goodstable);
                        }
                        if(goodstable != null && StrUtil.isNotBlank(goodstable.getGoodsname()) && (goodstable.getType() > 605 && goodstable.getType() <= 610||goodstable.getType() > 1605 && goodstable.getType() <= 1610) && !goodstable.getGoodsname().contains("新手")){
                            goodstable.setValue(NpcComposeAction.getGoodsAttribute(goodstable));
                        }
                        //特效物品判断是拥有特效
                        if (id.longValue() < 0 && roleData.getPackRecord().isTX(-id.longValue() + "")) {
                            continue;
                        }
                        if (goodstable == null) {
                            continue;
                        }
                        if (assetUpdate == null) {
                            assetUpdate = new AssetUpdate(type);
                        }

                            if (goodstable.getType() == 1001L) {
                                String[] s = goodstable.getValue().split("=")[1].split("&");
                                List<RandomNum> randomNums = new ArrayList<>();
                                for (String string : s) {
                                    String[] m = string.split("\\$");
                                    RandomNum randomNum = new RandomNum();
                                    randomNum.setProbability(Integer.parseInt(m[0]));
                                    String[] n = m[1].split("-");
                                    randomNum.setMin(Integer.parseInt(n[0]));
                                    randomNum.setMax(Integer.parseInt(n[1]));
                                    randomNums.add(randomNum);
                                }
                                Collections.sort(randomNums, new Comparator<RandomNum>() {
                                    @Override
                                    public int compare(RandomNum p1, RandomNum p2) {
                                        return Integer.compare(p1.getProbability(), p2.getProbability());
                                    }
                                });
                                Boolean b = Boolean.valueOf(false);
                                BigDecimal number = BigDecimal.ZERO;
                                for (RandomNum randomNum2 : randomNums) {
                                    if (randomNum2.getProbability() > new Random().nextInt(100)) {
                                        b = Boolean.valueOf(true);
                                        int money = randomNum2.getMin() + new Random().nextInt(randomNum2.getMax() - randomNum2.getMin());
                                        number = new BigDecimal(money + "");
                                        break;
                                    }
                                }
                                if (number.longValue() == 0L) {
                                    int min = ((RandomNum)randomNums.get(randomNums.size() - 1)).getMin();
                                    number = new BigDecimal(min + "");
                                }
                                if (goodstable.getValue().startsWith("金钱")) {
                                    long money2 = number.longValue();
                                    if (money2 != 0L) {
                                        assetUpdate.updata("D=" + money2);
                                        loginResult.setGold(loginResult.getGold().add(new BigDecimal(money2)));
                                        if (type == 24) {
                                            MonitorUtil.getMoney().addD(money2, 0);
                                            MonitorUtil.getDropQM1().add(money2);
                                        }
                                        else {
                                            MonitorUtil.getMoney().addD(money2, 1);
                                            MonitorUtil.getDropQM2().add(money2);
                                        }
                                        if (buffer.length() != 0) {
                                            buffer.append("|");
                                        }
                                        buffer.append("你获得金钱");
                                        buffer.append(money2);
                                    }
                                }
                                else if (goodstable.getValue().startsWith("仙玉")) {
                                    long money2 = number.longValue();
                                    if (money2 != 0L) {
                                        assetUpdate.updata("X=" + money2);
                                        loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(money2)));
                                        MonitorUtil.getMoney().addX(money2, 1);
                                        if (buffer.length() != 0) {
                                            buffer.append("|");
                                        }
                                        buffer.append("你获得仙玉#Y");
                                        buffer.append(money2);
                                    }
                                }
                            }
                            else {
                                if (buffer.length() != 0) {
                                    buffer.append("|");
                                }
                                buffer.append("你获得");
                                buffer.append(bean.getSum());
                                buffer.append("个");
                                buffer.append(goodstable.getGoodsname());
                                AddGoodAction.addGood(assetUpdate, goodstable, loginResult, roleData, bean, type);
                               // SuitMixdeal.good(goodstable, loginResult.getRolename(), msg, type);
                                SuitMixdeal.good2222(goodstable, loginResult, msg, type);
                            }
                        }
                    }
                }

            else if (thing[0].equals("金钱")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                long money3 = (long)new Long(thing[1]);
                assetUpdate.updata("D=" + money3);
                loginResult.setGold(loginResult.getGold().add(new BigDecimal(money3)));
                MonitorUtil.getMoney().addD(money3, 3);
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得金钱");
                buffer.append(money3);
                MonitorUtil.getDropHM().add(money3);
            }
            else if (thing[0].equals("仙玉")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                long money3 = (long)new Long(thing[1]);
                assetUpdate.updata("X=" + money3);
                loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(money3)));
                MonitorUtil.getMoney().addX(money3, 1);
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得仙玉");
                buffer.append(money3);
            }
            else if (thing[0].equals("经验")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                long addexp = (long)((double)(long)new Long(thing[1]) * xs);
                int grade = (int)loginResult.getGrade();
                if (grade > 459) {
                    addexp /= 2L;
                }
                else {
                    addexp *= 1L;
                }
                ExpUtil.RoleExp(loginResult, addexp);
                assetUpdate.updata("R" + loginResult.getGrade() + "=" + loginResult.getExperience() + "=" + loginResult.getHp() + "=" + loginResult.getMp());
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得经验#R");
                buffer.append(addexp);
            }
            else if (thing[0].equals("称谓")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                XXGDBean bean = getGoods(thing[1]);
                if (bean != null) {
                    Title title = GameServer.getTitle(bean.getId());
                    if (title != null) {
                        Titletable titletablecheck = AllServiceUtil.getTitletableService().selectRoleTitle(loginResult.getRole_id(), title.getTitlename());
                        if (titletablecheck == null) {
                            Titletable titletable = new Titletable();
                            titletable.setTitlename(title.getTitlename());
                            titletable.setRoleid(loginResult.getRole_id());
                            titletable.setLimittime(title.getLimitTime());
                            AllServiceUtil.getTitletableService().createRoleTitle(titletable);
                            if (buffer.length() != 0) {
                                buffer.append("|");
                            }
                            buffer.append("你获得称谓:");
                            buffer.append(title.getTitlename());
                        }
                        else if (titletablecheck.getLimittime() >= 0) {
                            Date recordTime = titletablecheck.getRecordtime();
                            Calendar c = Calendar.getInstance();
                            c.setTime(recordTime);
                            c.add(12, titletablecheck.getLimittime());
                            long endTime = c.getTimeInMillis();
                            Calendar calendar_now = Calendar.getInstance();
                            long startTime = calendar_now.getTime().getTime();
                            long midTime = (endTime - startTime) / 1000L;
                            if (midTime > 0L) {
                                titletablecheck.setLimittime(title.getLimitTime() + (int)midTime / 60);
                                AllServiceUtil.getTitletableService().updateByPrimaryKey(titletablecheck);
                            }
                            else {
                                titletablecheck.setRecordtime(new Date());
                                titletablecheck.setLimittime(title.getLimitTime());
                            }
                            AllServiceUtil.getTitletableService().updateByPrimaryKey(titletablecheck);
                            if (buffer.length() != 0) {
                                buffer.append("|");
                            }
                            buffer.append("你再次获得称谓:");
                            buffer.append(title.getTitlename());
                        }
                        else {
                            assetUpdate.setMsg("你已经拥有称谓:" + title.getTitlename());
                            if (buffer.length() != 0) {
                                buffer.append("|");
                            }
                            buffer.append("你已经拥有称谓:");
                            buffer.append(title.getTitlename());
                        }
                    }
                }
            }
            else if (thing[0].equals("记录")) {
                if (roleData.getPackRecord().addOther(thing[1])) {
                    EventModel eventModel = GameServer.getEvent(Integer.parseInt(thing[1]));
                    if (eventModel != null) {
                        eventModel.resetRanking(loginResult.getRole_id(), loginResult.getRolename());
                    }
                }
            }
            else if (thing[0].equals("放妖")) {
                XXGDBean bean = getGoods(thing[1]);
                if (bean != null) {
                    AddMonster(loginResult.getRole_id(), loginResult.getRolename(), bean.getId(), null);
                }
            }
            else if (thing[0].endsWith("积分") || thing[0].equals("比斗奖章") || thing[0].equals("星芒")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                assetUpdate.updata(thing[0] + "=" + thing[1]);
                loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), thing[0] + "=" + thing[1], 2));
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得");
                buffer.append(thing[0]);
                buffer.append(thing[1]);
            }
            else if (thing[0].startsWith("击杀")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                assetUpdate.updata(thing[0] + "=" + thing[1]);
                loginResult.setKill(DrawnitemsAction.Splice(loginResult.getKill(), thing[0] + "=" + thing[1], 5));
            }
            else if (thing[0].equals("帮贡")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                long money3 = (long)new Long(thing[1]);
                assetUpdate.updata("B=" + money3);
                loginResult.setContribution(loginResult.getContribution().add(new BigDecimal(money3)));
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得帮贡");
                buffer.append(money3);
                GangDomain gangDomain = GangUtil.getGangDomain(loginResult.getGang_id());
                if (gangDomain != null) {
                    gangDomain.addBG(money3);
                }
            }
            else if (thing[0].equals("师贡")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                long money3 = (long)new Long(thing[1]);
                assetUpdate.updata("S=" + money3);
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                loginResult.setSavegold(loginResult.getSavegold().add(new BigDecimal(money3)));
                buffer.append("你获得师贡");
                buffer.append(money3);
            }
            else if (thing[0].startsWith("SKILL")) {
                assetUpdate = doGetDropSkill(loginResult, assetUpdate, type, buffer, v);
            }
        }
        if (assetUpdate != null) {
            if (message != null) {
                assetUpdate.upmsg(message);
            }
            if (buffer.length() != 0) {
                assetUpdate.upmsg(buffer.toString());
            }
            if (data != null) {
                assetUpdate.setVip(data);
            }
            if (dataMes != null) {
                assetUpdate.updata(dataMes);
            }
            if (task != null) {
                assetUpdate.setTask(task);
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        }
    }
    
    public static AssetUpdate getCZJF(LoginResult loginResult, String value, String msg, int type, double xs, String data, String dataMes, String message, String task) {
        if (value == null || value.equals("")) {
            return null;
        }
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
        if (ctx == null) {
            return null;
        }
        boolean is = true;
        if (type == 999) {
            is = false;
            type = 25;
        }
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        AssetUpdate assetUpdate = null;
        StringBuffer buffer = new StringBuffer();
        String[] vs;
        for (String v : vs = value.split("\\|")) {
            String[] thing = v.split("=");
            if (thing[0].equals("物品")) {
                if (is && roleData.isGoodFull()) {
                    if (buffer.length() != 0) {
                        buffer.append("|");
                    }
                    buffer.append("你背包已满");
                }
                else {
                    XXGDBean bean = getGoods(thing[1]);
                    if (bean != null) {
                        BigDecimal id = new BigDecimal(bean.getId());
                        Goodstable goodstable = GameServer.getGood(id);
                        if ((id.longValue() >= 0L || !roleData.getPackRecord().isTX(-id.longValue() + "")) && goodstable != null) {
                            if (assetUpdate == null) {
                                assetUpdate = new AssetUpdate(type);
                            }
                            if (goodstable.getType() == 1001L) {
                                String[] s = goodstable.getValue().split("=")[1].split("&");
                                List<RandomNum> randomNums = new ArrayList<>();
                                for (String string : s) {
                                    String[] m = string.split("\\$");
                                    RandomNum randomNum = new RandomNum();
                                    randomNum.setProbability(Integer.parseInt(m[0]));
                                    String[] n = m[1].split("-");
                                    randomNum.setMin(Integer.parseInt(n[0]));
                                    randomNum.setMax(Integer.parseInt(n[1]));
                                    randomNums.add(randomNum);
                                }
                                Collections.sort(randomNums, new Comparator<RandomNum>() {
                                    @Override
                                    public int compare(RandomNum p1, RandomNum p2) {
                                        return Integer.compare(p1.getProbability(), p2.getProbability());
                                    }
                                });
                                Boolean b = Boolean.valueOf(false);
                                BigDecimal number = BigDecimal.ZERO;
                                for (RandomNum randomNum2 : randomNums) {
                                    if (randomNum2.getProbability() > new Random().nextInt(100)) {
                                        b = Boolean.valueOf(true);
                                        int money = randomNum2.getMin() + new Random().nextInt(randomNum2.getMax() - randomNum2.getMin());
                                        number = new BigDecimal(money + "");
                                        break;
                                    }
                                }
                                if (number.longValue() == 0L) {
                                    int min = ((RandomNum)randomNums.get(randomNums.size() - 1)).getMin();
                                    number = new BigDecimal(min + "");
                                }
                                if (goodstable.getValue().startsWith("金钱")) {
                                    long money2 = number.longValue();
                                    if (money2 != 0L) {
                                        assetUpdate.updata("D=" + money2);
                                        loginResult.setGold(loginResult.getGold().add(new BigDecimal(money2)));
                                        if (type == 24) {
                                            MonitorUtil.getMoney().addD(money2, 0);
                                            MonitorUtil.getDropQM1().add(money2);
                                        }
                                        else {
                                            MonitorUtil.getMoney().addD(money2, 1);
                                            MonitorUtil.getDropQM2().add(money2);
                                        }
                                        if (buffer.length() != 0) {
                                            buffer.append("|");
                                        }
                                        buffer.append("你获得金钱");
                                        buffer.append(money2);
                                    }
                                }
                                else if (goodstable.getValue().startsWith("仙玉")) {
                                    long money2 = number.longValue();
                                    if (money2 != 0L) {
                                        assetUpdate.updata("X=" + money2);
                                        loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(money2)));
                                        MonitorUtil.getMoney().addX(money2, 1);
                                        if (buffer.length() != 0) {
                                            buffer.append("|");
                                        }
                                        buffer.append("你获得仙玉#Y");
                                        buffer.append(money2);
                                    }
                                }
                            }
                            else {
                                if (buffer.length() != 0) {
                                    buffer.append("|");
                                }
                                buffer.append("你获得");
                                buffer.append(bean.getSum());
                                buffer.append("个");
                                buffer.append(goodstable.getGoodsname());
                                AddGoodAction.addGood(assetUpdate, goodstable, loginResult, roleData, bean, type);
                                SuitMixdeal.good(goodstable, loginResult.getRolename(), msg, type);
                            }
                        }
                    }
                }
            }
            else if (thing[0].equals("金钱")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                long money3 = (long)new Long(thing[1]);
                assetUpdate.updata("D=" + money3);
                loginResult.setGold(loginResult.getGold().add(new BigDecimal(money3)));
                MonitorUtil.getMoney().addD(money3, 3);
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得金钱");
                buffer.append(money3);
                MonitorUtil.getDropHM().add(money3);
            }
            else if (thing[0].equals("仙玉")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                long money3 = (long)new Long(thing[1]);
                assetUpdate.updata("X=" + money3);
                loginResult.setCodecard(loginResult.getCodecard().add(new BigDecimal(money3)));
                MonitorUtil.getMoney().addX(money3, 1);
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得仙玉");
                buffer.append(money3);
            }
            else if (thing[0].equals("经验")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                long addexp = (long)((double)(long)new Long(thing[1]) * xs);
                int grade = (int)loginResult.getGrade();
                if (grade > 459) {
                    addexp /= 2L;
                }
                else {
                    addexp *= 1L;
                }
                ExpUtil.RoleExp(loginResult, addexp);
                assetUpdate.updata("R" + loginResult.getGrade() + "=" + loginResult.getExperience() + "=" + loginResult.getHp() + "=" + loginResult.getMp());
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得经验#R");
                buffer.append(addexp);
            }
            else if (thing[0].equals("称谓")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                XXGDBean bean = getGoods(thing[1]);
                if (bean != null) {
                    Title title = GameServer.getTitle(bean.getId());
                    if (title != null) {
                        Titletable titletablecheck = AllServiceUtil.getTitletableService().selectRoleTitle(loginResult.getRole_id(), title.getTitlename());
                        if (titletablecheck == null) {
                            Titletable titletable = new Titletable();
                            titletable.setTitlename(title.getTitlename());
                            titletable.setRoleid(loginResult.getRole_id());
                            titletable.setLimittime(title.getLimitTime());
                            AllServiceUtil.getTitletableService().createRoleTitle(titletable);
                            if (buffer.length() != 0) {
                                buffer.append("|");
                            }
                            buffer.append("你获得称谓:");
                            buffer.append(title.getTitlename());
                        }
                        else if (titletablecheck.getLimittime() >= 0) {
                            Date recordTime = titletablecheck.getRecordtime();
                            Calendar c = Calendar.getInstance();
                            c.setTime(recordTime);
                            c.add(12, titletablecheck.getLimittime());
                            long endTime = c.getTimeInMillis();
                            Calendar calendar_now = Calendar.getInstance();
                            long startTime = calendar_now.getTime().getTime();
                            long midTime = (endTime - startTime) / 1000L;
                            if (midTime > 0L) {
                                titletablecheck.setLimittime(title.getLimitTime() + (int)midTime / 60);
                                AllServiceUtil.getTitletableService().updateByPrimaryKey(titletablecheck);
                            }
                            else {
                                titletablecheck.setRecordtime(new Date());
                                titletablecheck.setLimittime(title.getLimitTime());
                            }
                            AllServiceUtil.getTitletableService().updateByPrimaryKey(titletablecheck);
                            if (buffer.length() != 0) {
                                buffer.append("|");
                            }
                            buffer.append("你再次获得称谓:");
                            buffer.append(title.getTitlename());
                        }
                        else {
                            assetUpdate.setMsg("你已经拥有称谓:" + title.getTitlename());
                            if (buffer.length() != 0) {
                                buffer.append("|");
                            }
                            buffer.append("你已经拥有称谓:");
                            buffer.append(title.getTitlename());
                        }
                    }
                }
            }
            else if (thing[0].equals("记录")) {
                if (roleData.getPackRecord().addOther(thing[1])) {
                    EventModel eventModel = GameServer.getEvent(Integer.parseInt(thing[1]));
                    if (eventModel != null) {
                        eventModel.resetRanking(loginResult.getRole_id(), loginResult.getRolename());
                    }
                }
            }
            else if (thing[0].equals("放妖")) {
                XXGDBean bean = getGoods(thing[1]);
                if (bean != null) {
                    AddMonster(loginResult.getRole_id(), loginResult.getRolename(), bean.getId(), null);
                }
            }
            else if (thing[0].endsWith("积分") || thing[0].equals("比斗奖章") || thing[0].equals("星芒")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                assetUpdate.updata(thing[0] + "=" + thing[1]);
                loginResult.setScore(DrawnitemsAction.Splice(loginResult.getScore(), thing[0] + "=" + thing[1], 2));
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得");
                buffer.append(thing[0]);
                buffer.append(thing[1]);
            }
            else if (thing[0].startsWith("击杀")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                assetUpdate.updata(thing[0] + "=" + thing[1]);
                loginResult.setKill(DrawnitemsAction.Splice(loginResult.getKill(), thing[0] + "=" + thing[1], 5));
            }
            else if (thing[0].equals("帮贡")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                long money3 = (long)new Long(thing[1]);
                assetUpdate.updata("B=" + money3);
                loginResult.setContribution(loginResult.getContribution().add(new BigDecimal(money3)));
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你获得帮贡");
                buffer.append(money3);
                GangDomain gangDomain = GangUtil.getGangDomain(loginResult.getGang_id());
                if (gangDomain != null) {
                    gangDomain.addBG(money3);
                }
            }
            else if (thing[0].equals("师贡")) {
                if (assetUpdate == null) {
                    assetUpdate = new AssetUpdate(type);
                }
                long money3 = (long)new Long(thing[1]);
                assetUpdate.updata("S=" + money3);
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                loginResult.setSavegold(loginResult.getSavegold().add(new BigDecimal(money3)));
                buffer.append("你获得师贡");
                buffer.append(money3);
            }
            else if (thing[0].startsWith("SKILL")) {
                assetUpdate = doGetDropSkill(loginResult, assetUpdate, type, buffer, v);
            }
        }
        if (assetUpdate != null) {
            if (message != null) {
                assetUpdate.upmsg(message);
            }
            if (buffer.length() != 0) {
                assetUpdate.upmsg(buffer.toString());
            }
            if (data != null) {
                assetUpdate.setVip(data);
            }
            if (dataMes != null) {
                assetUpdate.updata(dataMes);
            }
            if (task != null) {
                assetUpdate.setTask(task);
            }
            return assetUpdate;
        }
        else {
            return null;
        }
    }
    
    public static AssetUpdate doGetDropSkill(LoginResult loginResult, AssetUpdate assetUpdate, int type, StringBuffer buffer, String parval) {
        if (assetUpdate == null) {
            assetUpdate = new AssetUpdate(type);
        }
        if (StringUtils.isNotEmpty(parval) && parval.split("=").length == 2) {
            StringBuffer sb = new StringBuffer();
            String[] parvals = parval.split("=");
            String killId = parvals[0].substring(5, parvals[0].length());
            RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
            PrivateData privateData = roleData.getPrivateData();
            String[] vs = privateData.getSkill("S");
            int maxsld = AnalysisString.shuliandu((int)roleData.getLoginResult().getGrade());
            int addsld = Integer.parseInt(parvals[1]);
            if (vs == null) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("你还未学习技能");
                return assetUpdate;
            }
            else {
                boolean a = false;
                for (int i = 0; i < vs.length; ++i) {
                    String[] vs2 = vs[i].split("_");
                    Integer id = Integer.valueOf(Integer.parseInt(vs2[0]));
                    Integer val = Integer.valueOf(Integer.parseInt(vs2[1]));
                    Skill kill = GameServer.getSkill(String.valueOf(id));
                    if ((int)id == Integer.parseInt(killId)) {
                        if ((int)val >= maxsld) {
                            sb.append(vs[i] + "#");
                            if (buffer.length() != 0) {
                                buffer.append("|");
                            }
                            buffer.append("您的该技能已满");
                        }
                        else if ((int)val + addsld >= maxsld) {
                            sb.append(id);
                            sb.append("_" + String.valueOf(maxsld) + "#");
                            int addslds = maxsld - (int)val;
                            if (buffer.length() != 0) {
                                buffer.append("|");
                            }
                            buffer.append("您的" + ((kill != null) ? kill.getSkillname() : "") + "技能增了" + addslds + "点熟练度");
                            assetUpdate.updata("SKILL" + id + "=" + addslds);
                        }
                        else {
                            sb.append(id);
                            sb.append("_" + String.valueOf((int)val + addsld) + "#");
                            if (buffer.length() != 0) {
                                buffer.append("|");
                            }
                            buffer.append("您的" + ((kill != null) ? kill.getSkillname() : "") + "技能增了" + addsld + "点熟练度");
                            assetUpdate.updata("SKILL" + id + "=" + String.valueOf(addsld));
                        }
                    }
                    else {
                        sb.append(vs[i] + "#");
                    }
                }
                if (sb.toString().endsWith("#")) {
                    privateData.setSkills("S", sb.toString().substring(0, sb.toString().indexOf("#")));
                }
                else {
                    privateData.setSkills("S", sb.toString());
                }
                roleData.upPrivateData(privateData);
            }
        }
        return assetUpdate;
    }
    
    public static void AddMonster(BigDecimal roleID, String rolename, String message, MapZB mapZB) {
        String[] vs = message.split("#");
        for (int i = 0; i < vs.length; ++i) {
            if (vs[i].equals("1001") || vs[i].equals("1002") || vs[i].equals("1007") || vs[i].equals("1008") || vs[i].equals("1013")) {
                SceneUtil.additionalScene(Integer.parseInt(vs[i]));
            }
            else {
                Boos boos = (Boos)GameServer.boosesMap.get(vs[i]);
                if (boos != null) {
                    MonsterUtil.refreshMonsters(boos, roleID, rolename, mapZB);
                }
            }
        }
    }
    
    public static XXGDBean getGoods(String goodses) {
        String[] goods = goodses.split("&");
        double lk = Double.parseDouble(goods[0]);
        if (isV(lk)) {
            return null;
        }
        for (int i = 1; i < goods.length; ++i) {
            String[] canGetGoods = goods[i].split("\\$");
            if (isV(Double.parseDouble(canGetGoods[2]))) {
                String[] getGoods = canGetGoods[0].split("-");
                XXGDBean bean = new XXGDBean();
                bean.setId(getGoods[SummonPetAction.random.nextInt(getGoods.length)]);
                bean.setSum(Integer.parseInt(canGetGoods[1]));
                return bean;
            }
        }
        return null;
    }
    
    public static int getEmptyXS(int sum, int num, int dropXS, int ndXS) {
        int empty = -5 * dropXS;
        empty = -5 * ndXS;
        num /= 60;
        if (num > 15) {
            num = 15;
        }
        empty += num;
        sum /= 120;
        if (sum > 10) {
            sum = 10;
        }
        empty += sum;
        return empty;
    }
    
    public static XXGDBean getGoods(DropGood dropGood, int addEmpty) {
        if (dropGood.getEmpty() > 0.0) {
            if (addEmpty == 0) {
                if (isV(dropGood.getEmpty())) {
                    return null;
                }
            }
            else {
                if (dropGood.getEmpty() >= 70.0) {
                    addEmpty /= 3;
                }
                else if (dropGood.getEmpty() >= 50.0) {
                    addEmpty /= 2;
                }
                else if (dropGood.getEmpty() <= 20.0) {
                    addEmpty = (int)((double)addEmpty * 1.5);
                }
                if (isV(dropGood.getEmpty() + (double)addEmpty)) {
                    return null;
                }
            }
        }
        for (int i = 0; i < dropGood.getDraws().length; ++i) {
            DrawBase drawBase = dropGood.getDraws()[i];
            if (isV(drawBase.getV())) {
                XXGDBean bean = new XXGDBean();
                bean.setId(drawBase.getDropId());
                bean.setSum(drawBase.getSum());
                return bean;
            }
        }
        return null;
    }
    
    public static XXGDBean getGoods(DropGood dropGood) {
        if (isV(dropGood.getEmpty())) {
            return null;
        }
        for (int i = 0; i < dropGood.getDraws().length; ++i) {
            DrawBase drawBase = dropGood.getDraws()[i];
            if (isV(drawBase.getV())) {
                XXGDBean bean = new XXGDBean();
                bean.setId(drawBase.getDropId());
                bean.setSum(drawBase.getSum());
                return bean;
            }
        }
        return null;
    }
    
    public static boolean isV(double value) {
        value *= 100.0;
        return value > (double)SummonPetAction.random.nextInt(10000);
    }
    
    public static boolean isDH(String ab, LoginResult loginResult) {
        String[] vs = ab.substring(1).split("\\|");
        if (!vs[vs.length - 1].equals("#AAFFFO")) {
            return false;
        }
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(loginResult.getRolename());
        XXGDBean bean = new XXGDBean();
        bean.setId(vs[1]);
        bean.setSum(Integer.parseInt(vs[2]));
        RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
        AssetUpdate assetUpdate = new AssetUpdate(Integer.parseInt(vs[0]));
        BigDecimal id = new BigDecimal(bean.getId());
        Goodstable goodstable = GameServer.getGood(id);
        if (id.longValue() < 0L && roleData.getPackRecord().isTX(-id.longValue() + "")) {
            return true;
        }
        if (goodstable == null) {
            return true;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("单独兑换接口:");
        buffer.append(id);
        buffer.append(",");
        buffer.append(bean.getSum() + "个" + goodstable.getGoodsname());
        buffer.append(",玩家:");
        buffer.append(loginResult.getRole_id());
        buffer.append("_");
        buffer.append(loginResult.getRolename());
        AllServiceUtil.getRecordService().insert(new Record(4, buffer.toString()));
        AddGoodAction.addGood(assetUpdate, goodstable, loginResult, roleData, bean, assetUpdate.getType());
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        String msg = "dh:" + ab + ((loginResult != null) ? loginResult.getRole_id() : null);
        WriteOut.addtxt(msg, 9999L);
        return true;
    }
    
    static {
        DropUtil.random = new Random();
    }
}
