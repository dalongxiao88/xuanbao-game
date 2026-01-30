package come.tool.Battle;

import org.come.entity.Goodstable;
import come.tool.FightingData.FightingSkill;
import org.come.model.Robots;
import come.tool.Stall.AssetUpdate;
import come.tool.FightingData.TypeUtil;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.come.entity.RoleSummoning;
import org.come.entity.Pal;
import come.tool.Role.RoleData;
import java.util.HashMap;
import java.util.ArrayList;
import org.come.until.AllServiceUtil;
import java.math.BigDecimal;
import come.tool.FightingData.FightingLingbao;
import come.tool.FightingData.FightingSummon;
import come.tool.Calculation.CalculationUtil;
import come.tool.FightingData.Battlefield;
import come.tool.Role.RolePool;
import come.tool.oneArena.OneArenaRole;
import java.util.Iterator;
import java.util.Map;
import come.tool.FightingData.FightingManData;
import come.tool.Calculation.BaseStar;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.tool.WriteOut;
import org.come.handler.MainServerHandler;
import come.tool.FightingData.ManData;
import come.tool.FightingData.PK_MixDeal;
//TODO 战斗开始
public class BattleStatePreview implements BattleState
{
    static String CHECKTS1;
    static String CHECKTS2;
    
    @Override
    public boolean handle(BattleData battleData) {
        try {
            if (PK_MixDeal.isBB(battleData.getBattlefield().BattleType)) {
                List<ManData> datas = battleData.getBattlefield().fightingdata;
                for (int j = datas.size() - 1; j >= 0; --j) {
                    ManData data = (ManData)datas.get(j);
                    if (data.getMan() < 5) {
                        data.addAddState("隐身", 0.0, 0.0, 9999);
                    }
                }
            }
            BaseStar star1 = this.loadRole(1, battleData, battleData.getTeam1());
            BaseStar star2 = null;
            if (PK_MixDeal.isArena(battleData.getBattlefield().BattleType) && battleData.getOneArenaRole2().getRoleId().longValue() > 0L) {
                star2 = this.loadRole(2, battleData, battleData.getOneArenaRole2());
            }
            else {
                star2 = this.loadRole(2, battleData, battleData.getTeam2());
            }
            battleData.getBattlefield().init(star1, star2);
            if (battleData.getBattleType() == 21) {
                battleData.getBattlefield().sldh();
            }
            List<FightingManData> playdatas = battleData.getBattlefield().Transformation();
            battleData.getPlayDatas().put(1, playdatas);
            battleData.changeState(1);
            battleData.setRound(1);
        }
        catch (Exception e) {
            e.printStackTrace();
            WriteOut.addtxt("战斗数据处理报错:" + MainServerHandler.getErrorMessage(e), 9999L);
            battleData.setWinCamp(-1);
            return true;
        }
        BattleConnection battleConnection = BattleThreadPool.getBattleConnection(battleData, (String)battleData.getParticipantlist().get(0));
        if (battleData.getBattlefield().NewEvents != null) {
            battleConnection.setState(4);
            battleConnection.setPlayeEvents(battleData.getBattlefield().NewEvents);
        }
        Map<String, FightingStatistics> fightingStatisticsMap = battleData.getFightingForesee().getFightingStatisticsMap(battleData.getBattlefield());
        battleData.getFightingForesee().setFightingStatisticsMap(fightingStatisticsMap);
        String msg1 = Agreement.FightingRoundInfoAgreement(GsonUtil.getGsonUtil().getgson().toJson(battleData.getFightingForesee()));
        String msg2 = Agreement.getAgreement().battleConnectionAgreement(GsonUtil.getGsonUtil().getgson().toJson(battleConnection));
        for (String string : battleData.getParticipantlist()) {
            SendMessage.sendMessageByRoleName(string, msg2);
            SendMessage.sendMessageByRoleName(string, msg1);
        }
        battleData.getBattlefield().lingbao();
        return false;
    }
    
    public BaseStar loadRole(int camp, BattleData battleData, OneArenaRole oneArenaRole) {
        RoleData data = RolePool.getLineRoleData(oneArenaRole.getRoleId());
        if (data == null) {
            return null;
        }
        boolean isBB = PK_MixDeal.isBB(battleData.getBattlefield().BattleType);
        boolean isPal = PK_MixDeal.isPal(battleData.getBattlefield().BattleType);
        List<ManData> datas = battleData.getBattlefield().fightingdata;
        BaseStar star = null;
        ManData manData = new ManData(camp, Battlefield.Fightingpath(camp, 0));
        CalculationUtil.loadRoleBattle(manData, data.getLoginResult(), data, null, null, null, null, battleData);
        if (star == null && manData.getBaseStar() != null) {
            star = manData.getBaseStar();
            star.setMan(manData.getMan());
        }
        if (isBB) {
            manData.addAddState("隐身", 0.0, 0.0, 9999);
            manData.getLings().clear();
            manData.setChild(null);
            for (int j = 0; j < manData.getPets().size(); ++j) {
                manData.getPets().get(j).setBB(isBB);
            }
        }
        datas.add(manData);
        ManData petData = null;
        int k = manData.getPets().size() - 1;
        while (k >= 0) {
            if (manData.getPets().get(k).getPlay() == 1) {
                int zt = manData.getPets().get(k).getAttendPet(manData.isAi);
                if (zt == 0) {
                    petData = manData.getPets().get(k).getPet(manData.isAi);
                    break;
                }
                else {
                    break;
                }
            }
            else {
                --k;
            }
        }
        if (petData != null) {
            datas.add(petData);
        }
        k = 0;
        while (k < manData.getLings().size()) {
            if (manData.getLings().get(k).getPlay() == 1) {
                datas.add(manData.getLings().get(k).getLingbaonData());
                break;
            }
            else {
                ++k;
            }
        }
        if (manData.getChild() != null) {
            datas.add(manData.getChild());
        }
        //添加伙伴
        if (isPal) {
            for (int index = 0, size = 4; index < size && index < data.getPs().size(); ++index) {
                BigDecimal id = data.getPs().get(index);
                Pal pal = AllServiceUtil.getPalService().selectPalByID(id);
                if (pal != null) {
                    ManData palData = new ManData(camp, Battlefield.Fightingpath(camp, index + 1), pal, manData.getlvl(), manData);
                    if (pal.getSummoning() != null) {
                        RoleSummoning pet = pal.getSummoning();
                        ManData palPet = new ManData(camp, palData.getMan() + 5, pet, "");
                        palPet.isPalPet = true;
                        datas.add(palPet);
                    }
                    if (pal.getLingbao() != null) {//伙伴灵宝
                        ManData lb = new ManData(pal.getLingbao(), palData);
                        FightingLingbao ling = new FightingLingbao(lb, 1);
                        List<FightingLingbao> lings = new ArrayList<>();
                        lings.add(ling);
                        palData.setLings(lings);
                        datas.add(lb);
                    }
                    if (pal.getBaby() != null) {//伙伴孩子
                        Map<String, Double> map = new HashMap<>();
                        ManData baby = new ManData(pal.getBaby(), palData.getCamp(), palData.getMan(), map);
                        palData.setChild(baby);
                        datas.add(baby);
                    }
                    datas.add(palData);
                }
            }
        }
        return null;
    }
    
    public BaseStar loadRole(int camp, BattleData battleData, String[] teams) {
        if (teams == null || teams.length == 0) {
            return null;
        }
        List<ManData> datas = battleData.getBattlefield().fightingdata;
        BaseStar star = null;
        boolean isXK = battleData.getBattleNumber() % 8 == 0;//判断是否扣除战力   一次扣除10点
        boolean isBB = PK_MixDeal.isBB(battleData.getBattlefield().BattleType);
        boolean isPal = PK_MixDeal.isPal(battleData.getBattlefield().BattleType);
        Robots robots = battleData.getRobots();
        if (robots != null && robots.getIsPal() != null) {
            isPal = false;
        }
        if (isPal) {
            isPal = (teams.length < 5);
        }
        for (int i = 0; i < teams.length; ++i) {
            ChannelHandlerContext ctx = GameServer.getRoleNameMap().get(teams[i]);
            LoginResult log = (ctx != null) ? GameServer.getAllLoginRole().get(ctx) : null;
            if (log != null) {
                RoleData data = RolePool.getRoleData(log.getRole_id());
                ManData manData = new ManData(camp, Battlefield.Fightingpath(camp, i));
                manData.setRace_name(data.getLoginResult().getRace_name());
                manData.isAi = log.isGolem();
                CalculationUtil.loadRoleBattle(manData, log, data, null, null, null, null, battleData);
                FightingSkill skill = manData.getSkillType(TypeUtil.TY_RH_YLFY);
                if (skill != null) {
                    if (camp == 1 && (battleData.getBattlefield().YLFY1 == null || battleData.getBattlefield().YLFY1.getSkillhurt() < skill.getSkillhurt())) {
                        battleData.getBattlefield().YLFY1 = skill;
                    }
                    else if (camp == 2 && (battleData.getBattlefield().YLFY2 == null || battleData.getBattlefield().YLFY2.getSkillhurt() < skill.getSkillhurt())) {
                        battleData.getBattlefield().YLFY2 = skill;
                    }
                }
                if (star == null && manData.getBaseStar() != null) {
                    star = manData.getBaseStar();
                    star.setMan(manData.getMan());
                }
                if (isBB) {
                    manData.addAddState("隐身", 0.0, 0.0, 9999);
                    manData.getLings().clear();
                    manData.setChild(null);
                    for (int j = 0; j < manData.getPets().size(); ++j) {
                        manData.getPets().get(j).setBB(isBB);
                    }
                }
                datas.add(manData);
                if (manData.getXk_id() != null) {
                    Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(manData.getXk_id());
                    if (good != null && good.getType() == 520L) {
                        String[] vs = null;
                        if (isXK) {//判断是否扣除战力
                            if (vs == null) {
                                vs = good.getValue().split("\\|");
                            }
                            int zl = Integer.parseInt(vs[2].split("=")[1]);
                            zl -= 10;
                            if (zl <= 0) {
                                zl = 0;
                            }
                            vs[2] = "战力=" + zl;
                            StringBuffer buffer = new StringBuffer();
                            for (int k = 0; k < vs.length; ++k) {
                                if (buffer.length() != 0) {
                                    buffer.append("|");
                                }
                                buffer.append(vs[k]);
                            }
                            good.setValue(buffer.toString());
                            if (zl != 0) {
                                AllServiceUtil.getGoodsTableService().updateGoodRedis(good);
                            }
                            else {
                                AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, null, null, Integer.valueOf(4));
                            }
                            AssetUpdate assetUpdate = new AssetUpdate(AssetUpdate.USEGOOD);
                            assetUpdate.setGood(good);
                            assetUpdate.setMsg((zl == 0) ? "你的星卡战力为0,取消参战状态" : null);
                            SendMessage.sendMessageByRoleName(manData.getManname(), Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
                        }
                    }
                }
                ManData petData = null;
                int l = manData.getPets().size() - 1;
                while (l >= 0) {
                    if (manData.getPets().get(l).getPlay() == 1) {
                        int zt = manData.getPets().get(l).getAttendPet(manData.isAi);
                        if (!manData.isAi) {
                            zt = manData.getPets().get(l).getAttendPet(manData.isAi);
                        }
                        if (zt == 1) {
                            SendMessage.sendMessageToSlef(ctx, BattleStatePreview.CHECKTS1);
                            break;
                        }
                        else if (zt == 2) {
                            SendMessage.sendMessageToSlef(ctx, BattleStatePreview.CHECKTS2);
                            break;
                        }
                        else {
                            petData = manData.getPets().get(l).getPet(manData.isAi);
                            break;
                        }
                    }
                    else {
                        --l;
                    }
                }
                if (petData != null) {
                    datas.add(petData);
                }
                l = 0;
                while (l < manData.getLings().size()) {
                    if (((FightingLingbao)manData.getLings().get(l)).getPlay() == 1) {
                        datas.add(((FightingLingbao)manData.getLings().get(l)).getLingbaonData());
                        break;
                    }
                    else {
                        ++l;
                    }
                }
                if (manData.getChild() != null) {
                    datas.add(manData.getChild());
                }
                //添加伙伴
                if (i == 0 && isPal) {
                    for (int index = 0, size = 5 - teams.length; index < size && index < data.getPs().size(); ++index) {
                        BigDecimal id = data.getPs().get(index);
                        Pal pal = AllServiceUtil.getPalService().selectPalByID(id);
                        if (pal != null) {
                            ManData palData = new ManData(camp, Battlefield.Fightingpath(camp, index + teams.length), pal, manData.getlvl(), manData);
                            if (pal.getSummoning() != null) {
                                RoleSummoning pet = pal.getSummoning();
                                ManData palPet = new ManData(camp, palData.getMan() + 5, pet, "");
                                datas.add(palPet);
                            }
                            if (pal.getLingbao() != null) {//伙伴灵宝
                                ManData lb = new ManData(pal.getLingbao(), palData);
                                FightingLingbao ling = new FightingLingbao(lb, 1);
                                List<FightingLingbao> lings = new ArrayList<>();
                                lings.add(ling);
                                palData.setLings(lings);
                                datas.add(lb);
                            }
                            if (pal.getBaby() != null) {//伙伴孩子
                                Map<String, Double> map = new HashMap<>();
                                ManData baby = new ManData(pal.getBaby(), palData.getCamp(), palData.getMan(), map);
                                palData.setChild(baby);
                                datas.add(baby);
                            }
                            datas.add(palData);
                        }
                    }
                }
            }
        }
        return star;
    }
    
    static {
        BattleStatePreview.CHECKTS1 = Agreement.getAgreement().PromptAgreement("召唤兽忠诚度过低不愿意参加战斗");
        BattleStatePreview.CHECKTS2 = Agreement.getAgreement().PromptAgreement("你当前参战的召唤兽亲密少于20W无法参战");
    }
}
