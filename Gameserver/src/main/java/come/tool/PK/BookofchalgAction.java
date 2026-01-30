package come.tool.PK;

import org.come.bean.NChatBean;
import java.math.BigDecimal;
import come.tool.Stall.AssetUpdate;
import come.tool.Scene.LTS.LTSRole;
import come.tool.Scene.Scene;
import come.tool.Scene.LTS.LTSArena;
import come.tool.Battle.BattleThreadPool;
import come.tool.Battle.BattleData;
import come.tool.Role.RoleCard;
import come.tool.Scene.LTS.LTSScene;
import come.tool.Scene.SceneUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class BookofchalgAction implements IAction
{
    static long CD;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult login1 = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (login1 == null) {
            return;
        }
        PalacePkBean palacePkBean = (PalacePkBean)GsonUtil.getGsonUtil().getgson().fromJson(message, PalacePkBean.class);
        palacePkBean.setExp(null);
        if (palacePkBean.getType() == 0 || palacePkBean.getType() == 11) {
            PKStake pKStake = this.YZ(palacePkBean, login1);
            if (pKStake == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("不够资本"));
                return;
            }
            LoginResult login2 = null;
            if (palacePkBean.getType() == 0) {
                ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(palacePkBean.getUsername());
                if (ctx2 == null) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你输入的玩家还没上线"));
                    return;
                }
                login2 = (LoginResult)GameServer.getAllLoginRole().get(ctx2);
            }
            PkMatch pkMatch = PKPool.getPkPool().seekPkMatch(login1.getRole_id());
            if (pkMatch != null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你身上有战书未解决没法重新申请战书"));
                return;
            }
            String[] teams = login1.getTeam().split("\\|");
            if (!teams[0].equals(login1.getRolename())) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你不是队长没法下战书"));
                return;
            }
            LTSArena arena = null;
            if (palacePkBean.getType() == 11) {
                Scene scene = SceneUtil.getScene(1008);
                if (scene == null || scene.isEnd()) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("擂台赛未开启"));
                    return;
                }
                LTSScene ltsScene = (LTSScene)scene;
                arena = ltsScene.getArena(palacePkBean.getNtype());
                if (arena == null) {
                    return;
                }
                if (arena.getPkMatch() != null) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("已经有人在摆擂了"));
                    return;
                }
                long time = System.currentTimeMillis();
                for (int i = 0; i < teams.length; ++i) {
                    LoginResult login3 = null;
                    if (i == 0) {
                        login3 = login1;
                    }
                    else {
                        ChannelHandlerContext ctx3 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[i]);
                        if (ctx3 != null) {
                            login3 = (LoginResult)GameServer.getAllLoginRole().get(ctx3);
                        }
                    }
                    if (login3 == null || (int)login3.getFighting() != 0) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("队伍中有人没在状态"));
                        return;
                    }
                    if ((int)login3.getGrade() > arena.getMaxLvl()) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你们等级太高了"));
                        return;
                    }
                    if ((int)login3.getGrade() < 399) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你们等级太低了"));
                        return;
                    }
                    LTSRole ltsRole = ltsScene.getRole(login3.getRolename());
                    if (ltsRole == null || ltsRole.getZBnum() >= 3) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(login3.getRolename() + "失败次数过多没有参赛资格了"));
                        return;
                    }
                    if (time <= ltsRole.getTime()) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement(login3.getRolename() + "处于调整时间内,无法战斗"));
                        return;
                    }
                }
            }
            RoleCard roleCard1 = new RoleCard(login1.getRole_id(), login1.getRolename(), login1.getUserName());
            RoleCard roleCard2 = null;
            if (login2 != null) {
                roleCard2 = new RoleCard(login2.getRole_id(), login2.getRolename(), login2.getUserName());
            }
            PkMatch match = PKPool.getPkPool().addPkMatch(roleCard1, roleCard2, pKStake, palacePkBean.getType());
            if (match == null) {
                return;
            }
            if (arena != null) {
                arena.setPkMatch(match);
            }
            AssetUpdate assetUpdate = this.YZKC(pKStake, login1);
            this.laba(palacePkBean, login1, assetUpdate);
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
            if (palacePkBean.getType() == 0) {
                palacePkBean.setPId(match.getPid());
                palacePkBean.setUsername(login1.getRolename());
                message = Agreement.getAgreement().bookofchalgAgreement(GsonUtil.getGsonUtil().getgson().toJson(palacePkBean));
                SendMessage.sendMessageByRoleName(login2.getRolename(), message);
            }
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("战书申请成功,战书存在期间一些功能被限制"));
        }
        else if (palacePkBean.getType() == 1) {
            PkMatch pkMatch2 = PKPool.getPkPool().seekPkMatch(palacePkBean.getPId());
            if (pkMatch2 == null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("该战书已经过期了"));
                return;
            }
            if (pkMatch2.getState() != 0) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("战书已经被生效了"));
                return;
            }
            String[] teams2 = login1.getTeam().split("\\|");
            if (!teams2[0].equals(login1.getRolename())) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你不是队长没法同意"));
                return;
            }
            LoginResult login4 = null;
            ChannelHandlerContext ctx4 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(pkMatch2.getPkMan1().getRoleName());
            if (ctx4 != null) {
                login4 = (LoginResult)GameServer.getAllLoginRole().get(ctx4);
            }
            if (login4 == null) {
                PKPool.getPkPool().cancelPkMatch(pkMatch2);
                if (pkMatch2.getType() == 11) {
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("有一方认怂了战书作废"));
                }
                return;
            }
            else {
                String[] teams3 = login4.getTeam().split("\\|");
                if (!teams3[0].equals(login4.getRolename())) {
                    PKPool.getPkPool().cancelPkMatch(pkMatch2);
                    if (pkMatch2.getType() == 11) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("有一方认怂了战书作废"));
                    }
                    return;
                }
                else {
                    PKStake pKStake2 = pkMatch2.getpKStake1();
                    pKStake2 = this.YZ(pKStake2, login1);
                    if (pKStake2 == null) {
                        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("不够资本"));
                        return;
                    }
                    LTSScene ltsScene = null;
                    LTSArena arena2 = null;
                    if (pkMatch2.getType() == 11) {
                        Scene scene2 = SceneUtil.getScene(1008);
                        if (scene2 == null || scene2.isEnd()) {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("擂台赛未开启"));
                            return;
                        }
                        ltsScene = (LTSScene)scene2;
                        if (ltsScene.isLZ(login1.getRole_id())) {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已经是擂主了"));
                            return;
                        }
                        arena2 = ltsScene.getArena(pkMatch2);
                        if (arena2 == null) {
                            return;
                        }
                        if (teams3.length > teams2.length) {
                            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("必须大于等于擂主队伍人数:" + teams3.length));
                            return;
                        }
                    }
                    BattleData battleData = new BattleData();
                    battleData.setBattleType(33);
                    battleData.setBattletime(System.currentTimeMillis());
                    battleData.setTeam1(teams3);
                    battleData.setTeam2(teams2);
                    String value = BattleThreadPool.check2(teams3, battleData, 0, arena2, ltsScene);
                    if (value != null) {
                        SendMessage.sendMessageByRoleName(teams2[0], value);
                        SendMessage.sendMessageByRoleName(teams3[0], value);
                        return;
                    }
                    value = BattleThreadPool.check2(teams2, battleData, 1, arena2, ltsScene);
                    if (value != null) {
                        SendMessage.sendMessageByRoleName(teams2[0], value);
                        SendMessage.sendMessageByRoleName(teams3[0], value);
                        return;
                    }
                    AssetUpdate assetUpdate2 = this.YZKC(pKStake2, login1);
                    this.laba(palacePkBean, login1, assetUpdate2);
                    SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate2)));
                    pkMatch2.PKAgree(pKStake2);
                    if (arena2 != null) {
                        RoleCard roleCard3 = new RoleCard(login1.getRole_id(), login1.getRolename(), login1.getUserName());
                        PKPool.getPkPool().addLTSPkMatch(roleCard3, pkMatch2);
                    }
                    battleData.setBattleNumber(BattleThreadPool.getIncreasesum());
                    battleData.setPkMatch(pkMatch2);
                    pkMatch2.setBattleNumber(battleData.getBattleNumber());
                    battleData.setCalculator(battleData.getParticipantlist().size());
                    PKPool.getPkPool().endPkMatch(battleData.getRoleId1(), battleData.getRoleId2());
                    if (arena2 != null) {
                        arena2.setBattleNumber(battleData.getBattleNumber());
                    }
                    BattleThreadPool.addPool(battleData);
                }
            }
        }
        else if (palacePkBean.getType() == 2) {
            PkMatch pkMatch2 = PKPool.getPkPool().seekPkMatch(login1.getRole_id());
            if (pkMatch2 != null && pkMatch2.getType() == 0 && pkMatch2.getState() <= 1) {
                PKPool.getPkPool().cancelPkMatch(pkMatch2);
            }
        }
    }
    
    public PKStake YZ(PalacePkBean pkBean, LoginResult login) {
        long money = 0L;
        long xianYu = 0L;
        long exp = 0L;
        long charge = 2000000L;
        if (pkBean.getGold() != null) {
            money = pkBean.getGold().longValue();
        }
        if (pkBean.getXianyu() != null) {
            xianYu = pkBean.getXianyu().longValue();
        }
        if (pkBean.getExp() != null) {
            xianYu = pkBean.getExp().longValue();
        }
        if (money < 0L || xianYu < 0L || exp < 0L || charge < 0L) {
            return null;
        }
        if (login.getGold().longValue() < money + charge || login.getCodecard().longValue() < xianYu || login.getExperience().longValue() < exp) {
            return null;
        }
        return new PKStake(charge, money, xianYu, exp);
    }
    
    public PKStake YZ(PKStake pkStake, LoginResult login) {
        long money = pkStake.getMoney();
        long xianYu = pkStake.getXianYu();
        long exp = pkStake.getExp();
        long charge = 0L;
        if (money < 0L || xianYu < 0L || exp < 0L || charge < 0L) {
            return null;
        }
        if (login.getGold().longValue() < money + charge || login.getCodecard().longValue() < xianYu || login.getExperience().longValue() < exp) {
            return null;
        }
        return new PKStake(charge, money, xianYu, exp);
    }
    
    public AssetUpdate YZKC(PKStake pkStake, LoginResult login) {
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.STEALING);
        login.setGold(new BigDecimal(login.getGold().longValue() - (pkStake.getMoney() + pkStake.getCharge())));
        assetUpdate.updata("D=" + -(pkStake.getMoney() + pkStake.getCharge()));
        if (pkStake.getXianYu() != 0L) {
            login.setCodecard(new BigDecimal(login.getCodecard().longValue() - pkStake.getXianYu()));
            assetUpdate.updata("X=" + -pkStake.getXianYu());
        }
        if (pkStake.getExp() != 0L) {
            login.setExperience(new BigDecimal(login.getExperience().longValue() - pkStake.getExp()));
            assetUpdate.updata("E=" + -pkStake.getExp());
        }
        return assetUpdate;
    }
    
    public void laba(PalacePkBean pkBean, LoginResult login, AssetUpdate asset) {
        if (pkBean.getType() == 11) {
            NChatBean bean = new NChatBean();
            bean.setId(5);
            bean.setMessage("#G" + login.getRolename() + "#c00FFFF再#Y擂台挑战赛中#c00FFFF成功抢夺擂台擂主，挑战天下豪杰，有那位英雄敢来应战#80.");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToMapRoles(Long.valueOf(1386L), msg);
        }
        if (pkBean.getChoices() == 0 || pkBean.getSendStr() == null || pkBean.getSendStr().equals("")) {
            return;
        }
        int type = 0;
        if ((pkBean.getChoices() & 0x1) == 0x1 && login.getGold().longValue() > 5000000L) {
            login.setGold(new BigDecimal(login.getGold().longValue() - 5000000L));
            asset.updata("D=-5000000");
            type = 5;
        }
        if ((pkBean.getChoices() >>> 1 & 0x1) == 0x1 && login.getCodecard().longValue() > 100L) {
            login.setCodecard(new BigDecimal(login.getCodecard().longValue() - 100L));
            asset.updata("X=-100");
            if (type == 5) {
                type = 7;
            }
            else {
                type = 4;
            }
        }
        if (type != 0) {
            NChatBean bean2 = new NChatBean();
            bean2.setId(type);
            bean2.setMessage(pkBean.getSendStr());
            String msg2 = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean2));
            SendMessage.sendMessageToAllRoles(msg2);
        }
    }
    
    public int lvlint(int lvl) {
        if (lvl <= 102) {
            return lvl;
        }
        if (lvl <= 210) {
            return lvl - 102 + 14;
        }
        if (lvl <= 338) {
            return lvl - 210 + 14;
        }
        if (lvl <= 459) {
            return lvl - 338 + 59;
        }
        return lvl - 459 + 139;
    }
    
    static {
        BookofchalgAction.CD = 1000000L;
    }
}
