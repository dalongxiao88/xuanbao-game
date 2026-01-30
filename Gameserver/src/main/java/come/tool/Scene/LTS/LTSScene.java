package come.tool.Scene.LTS;

import org.come.task.MapMonsterBean;
import come.tool.Battle.BattleEnd;
import org.come.task.MonsterMoveBase;
import java.util.Map;
import org.come.model.Dorp;
import come.tool.Good.DropUtil;
import come.tool.Battle.BattleData;
import come.tool.Role.RoleCard;
import java.math.BigDecimal;
import come.tool.PK.PKPool;
import come.tool.PK.PkMatch;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import come.tool.Scene.Scene;

public class LTSScene implements Scene
{
    private int I;
    private ConcurrentHashMap<String, LTSRole> ltsMap;
    private List<LTSRole> top;
    private long mapID;
    private LTSThread ltsThread;
    private LTSArena[] arenas;
    
    public LTSScene() {
        this.mapID = 1386L;
        this.arenas = new LTSArena[5];
        for (int i = 0; i < this.arenas.length; ++i) {
            this.arenas[i] = new LTSArena(510 + i);
        }
        this.I = 1;
        this.top = new ArrayList<>();
        this.ltsMap = new ConcurrentHashMap<>();
        this.ltsThread = new LTSThread(this);
        Thread T1 = new Thread(this.ltsThread);
        T1.start();
        NChatBean bean = new NChatBean();
        bean.setId(9);
        bean.setMessage("#R擂台争霸赛已经开启，#Y请大家，前往长安城擂台或洛阳城客栈门口，找NPC进场！");
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
        bean.setId(5);
        msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public LTSRole getRole(String role) {
        LTSRole ltsRole = (LTSRole)this.ltsMap.get(role);
        if (ltsRole == null) {
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(role);
            if (ctx != null) {
                LoginResult login = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                ltsRole = new LTSRole(login.getRole_id(), login.getRolename());
                this.ltsMap.put(ltsRole.getRole(), ltsRole);
            }
        }
        return ltsRole;
    }
    
    public void Cancel(PkMatch pkMatch) {
        PKPool.getPkPool().returnStake(pkMatch.getPkMan1(), pkMatch.getpKStake1(), "退回下注");
        LTSArena arena = this.getArena(pkMatch);
        if (arena == null) {
            return;
        }
        arena.setBattleNumber(0);
        arena.setPkMatch(null);
        PKPool.getPkPool().deletePkMatch(pkMatch.getPkMan1().getRoleId(), (pkMatch.getPkMan2() != null) ? pkMatch.getPkMan2().getRoleId() : null);
    }
    
    public void OVERTIME(long time) {
        for (int i = 0; i < this.arenas.length; ++i) {
            PkMatch pkMatch = this.arenas[i].getPkMatch();
            if (pkMatch != null && pkMatch.isOverTime2(time)) {
                PKPool.getPkPool().returnStake(pkMatch.getPkMan1(), pkMatch.getpKStake1(), "退回下注");
                this.addJF(pkMatch, 0, pkMatch.getPkMan1().getRoleId());
                PKPool.getPkPool().deletePkMatch(pkMatch.getPkMan1().getRoleId(), (pkMatch.getPkMan2() != null) ? pkMatch.getPkMan2().getRoleId() : null);
            }
        }
    }
    
    public void addJF(PkMatch pkMatch, int type, BigDecimal id) {
        LTSArena arena = this.getArena(pkMatch);
        if (arena == null) {
            return;
        }
        arena.setBattleNumber(0);
        arena.setPkMatch(null);
        if (type == -1) {
            return;
        }
        boolean is = false;
        if (type == 0) {
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(pkMatch.getPkMan1().getRoleName());
            LoginResult loginResult = null;
            if (ctx != null) {
                loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
            }
            if (loginResult == null) {
                return;
            }
            String[] teams = loginResult.getTeam().split("\\|");
            for (int j = 0; j < teams.length; ++j) {
                LTSRole ltsRole = this.getRole(teams[j]);
                if (ltsRole != null && ltsRole.getBZnum() < 5) {
                    ltsRole.setBZnum(ltsRole.getBZnum() + 1);
                    ltsRole.battle(true, true, arena.getlLvl(), 0);
                    if (this.PointRanking(ltsRole)) {
                        is = true;
                    }
                }
            }
            return;
        }
        else {
            boolean ZL = false;
            RoleCard roleSB = null;
            int lsnum = 0;
            String zj = null;
            int lsnum2 = 0;
            String zj2 = null;
            if (pkMatch.getPkMan1().getRoleId().compareTo(id) == 0) {
                ZL = false;
                roleSB = pkMatch.getPkMan2();
            }
            else {
                ZL = true;
                roleSB = pkMatch.getPkMan1();
            }
            if (roleSB == null) {
                return;
            }
            ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleSB.getRoleName());
            LoginResult loginResult2 = null;
            if (ctx2 != null) {
                loginResult2 = (LoginResult)GameServer.getAllLoginRole().get(ctx2);
            }
            String[] teams2 = null;
            if (loginResult2 == null) {
                teams2 = new String[] { roleSB.getRoleName() };
            }
            else {
                teams2 = loginResult2.getTeam().split("\\|");
            }
            for (int i = 0; i < teams2.length; ++i) {
                LTSRole ltsRole2 = this.getRole(teams2[i]);
                if (ltsRole2 != null) {
                    if (lsnum < ltsRole2.getLSnum()) {
                        lsnum = ltsRole2.getLSnum();
                        zj = ltsRole2.getRole();
                    }
                    ltsRole2.battle(false, ZL, arena.getlLvl(), 0);
                    if (this.PointRanking(ltsRole2)) {
                        is = true;
                    }
                }
            }
            if (pkMatch.getPkMan1().getRoleId().compareTo(id) != 0) {
                ZL = false;
                roleSB = pkMatch.getPkMan2();
            }
            else {
                ZL = true;
                roleSB = pkMatch.getPkMan1();
            }
            if (roleSB == null) {
                return;
            }
            ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleSB.getRoleName());
            loginResult2 = null;
            if (ctx2 != null) {
                loginResult2 = (LoginResult)GameServer.getAllLoginRole().get(ctx2);
            }
            teams2 = null;
            if (loginResult2 == null) {
                teams2 = new String[] { roleSB.getRoleName() };
            }
            else {
                teams2 = loginResult2.getTeam().split("\\|");
            }
            for (int i = 0; i < teams2.length; ++i) {
                LTSRole ltsRole2 = this.getRole(teams2[i]);
                if (ltsRole2 != null) {
                    ltsRole2.battle(true, ZL, arena.getlLvl(), 0);
                    if (this.PointRanking(ltsRole2)) {
                        is = true;
                    }
                    if (i == 0) {
                        lsnum2 = ltsRole2.getLSnum();
                        zj2 = ltsRole2.getRole();
                    }
                }
            }
            if (lsnum >= 3) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("#G");
                buffer.append(zj2);
                buffer.append("#24威武霸气，前来应战，#Y最终大战三百回合#24，一锤定音，成功KO了#F");
                buffer.append(zj);
                buffer.append(",终结了他的");
                buffer.append(this.getWZ(lsnum));
                buffer.append(",此时大吼一声#24 还有谁#24");
                NChatBean bean = new NChatBean();
                bean.setId((lsnum >= 8) ? 4 : 5);
                bean.setMessage(buffer.toString());
                String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                SendMessage.sendMessageToAllRoles(msg);
            }
            else if (lsnum2 >= 3) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("#R");
                buffer.append(this.getWZ(lsnum2));
                if (lsnum2 <= 4) {
                    buffer.append("#Y横冲直装，犹如一批黑马,#G");
                    buffer.append(zj2);
                    buffer.append("#Y横空出世，犹如战神附体，#Y何人赶来应战，跪求一败！#132");
                }
                else if (lsnum2 <= 8) {
                    buffer.append("#Y观音姐姐，唐僧哥哥，开发组在这一刻灵魂附体#G");
                    buffer.append(zj2);
                    buffer.append("#Y身上！！！神挡杀神佛挡杀佛，#24在此高吼一声，何人敢战！#132");
                }
                else {
                    buffer.append("#Y王者霸气，已经无法遮挡#24#G");
                    buffer.append(zj2);
                    buffer.append("#Y给了全场在做得各位一个眼神#132似再吧再表示#G在座的所有人都是渣渣。#132");
                }
                NChatBean bean = new NChatBean();
                bean.setId((lsnum2 >= 8) ? 4 : 5);
                bean.setMessage(buffer.toString());
                String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                SendMessage.sendMessageToAllRoles(msg);
            }
            if (is) {
                SendMessage.sendMessageToMapRoles(Long.valueOf(this.mapID), Agreement.getAgreement().duelBoradDataAgreement(this.getRanking()));
            }
            if (pkMatch.getPkMan1().getRoleId().compareTo(id) == 0) {
                pkMatch = PKPool.getPkPool().addPkMatch(pkMatch.getPkMan1(), null, pkMatch.getpKStake1(), 11);
                arena.setPkMatch(pkMatch);
            }
            return;
        }
    }
    
    public String getWZ(int ci) {
        return ci + "连胜";
    }
    
    public String getRanking() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < this.top.size(); ++i) {
            if (buffer.length() != 0) {
                buffer.append("|");
            }
            LTSRole role = (LTSRole)this.top.get(i);
            buffer.append(role.getRole());
            buffer.append("&");
            buffer.append(role.getHSnum());
            buffer.append("&");
            buffer.append(role.getJf());
        }
        return buffer.toString();
    }
    
    public synchronized boolean PointRanking(LTSRole role) {
        this.top.remove(role);
        boolean is = true;
        boolean is2 = false;
        int i = 0;
        while (i < this.top.size()) {
            LTSRole zzsRole = (LTSRole)this.top.get(i);
            if (zzsRole.getJf() < role.getJf()) {
                is = false;
                this.top.add(i, role);
                is2 = true;
                break;
            }
            else {
                ++i;
            }
        }
        if (is) {
            if (this.top.size() < 5) {
                this.top.add(role);
                is2 = true;
            }
        }
        else if (this.top.size() > 5) {
            for (i = this.top.size() - 1; i >= 5; --i) {
                this.top.remove(i);
                is2 = true;
            }
        }
        return is2;
    }
    
    public LTSArena getArena(int id) {
        for (int i = 0; i < this.arenas.length; ++i) {
            if (this.arenas[i].getlId() == id) {
                return this.arenas[i];
            }
        }
        return null;
    }
    
    public LTSArena getArena(PkMatch pkMatch) {
        if (pkMatch == null) {
            return null;
        }
        for (int i = 0; i < this.arenas.length; ++i) {
            if (this.arenas[i].getPkMatch() != null && this.arenas[i].getPkMatch() == pkMatch) {
                return this.arenas[i];
            }
        }
        return null;
    }
    
    public boolean isLZ(BigDecimal roleId) {
        for (int i = 0; i < this.arenas.length; ++i) {
            if (this.arenas[i].getPkMatch() != null && this.arenas[i].getPkMatch().getPkMan1().getRoleId().compareTo(roleId) == 0) {
                return true;
            }
        }
        return false;
    }
    
    public LTSArena getLZ(BigDecimal roleId) {
        for (int i = 0; i < this.arenas.length; ++i) {
            if (this.arenas[i].getPkMatch() != null && this.arenas[i].getPkMatch().getPkMan1().getRoleId().compareTo(roleId) == 0) {
                return this.arenas[i];
            }
        }
        return null;
    }
    
    @Override
    public String UPMonster(BattleData battleData, String[] teams, int type, StringBuffer buffer) {
        return null;
    }
    
    @Override
    public void getAward(ChannelHandlerContext ctx, LoginResult loginResult) {
        if (!this.isEnd()) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("活动还未结束无法领奖"));
            return;
        }
        LTSRole ltsRole = (LTSRole)this.ltsMap.get(loginResult.getRolename());
        if (ltsRole == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你未参与本次获得"));
            return;
        }
        if (ltsRole.isAward()) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你已经领取过奖励了"));
            return;
        }
        ltsRole.setAward(true);
        boolean is = true;
        if (ltsRole.getZBnum() + ltsRole.getHSnum() >= 3) {
            Dorp dorp = GameServer.getDorp("2101");
            if (dorp != null) {
                DropUtil.getDrop(loginResult, dorp.getDorpValue(), "擂台赛参与礼包", 22, 1.0, null);
            }
            is = false;
        }
        if (ltsRole.getHSnum() >= 5) {
            Dorp dorp = GameServer.getDorp("2102");
            if (dorp != null) {
                DropUtil.getDrop(loginResult, dorp.getDorpValue(), "擂台赛五胜礼包", 22, 1.0, null);
            }
            is = false;
        }
        if (this.top.contains(ltsRole)) {
            Dorp dorp = GameServer.getDorp("2103");
            if (dorp != null) {
                DropUtil.getDrop(loginResult, dorp.getDorpValue(), "擂台霸主礼包", 22, 1.0, null);
            }
            is = false;
        }
        if (is) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你未达到领取标准"));
            return;
        }
    }
    
    @Override
    public Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer buffer, Map<Integer, MonsterMoveBase> moveMap, long mapId) {
        return moveMap;
    }
    
    @Override
    public boolean isEnd() {
        return this.I != 1;
    }
    
    public void setI(int i) {
        this.I = i;
        NChatBean bean = new NChatBean();
        bean.setId(4);
        bean.setMessage("#R本次擂台争霸赛已经结束");
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    @Override
    public boolean isTime(long time) {
        return true;
    }
    
    @Override
    public String getSceneMsg(LoginResult loginResult, long oldMapId, long mapId) {
        return null;
    }
    
    @Override
    public int battleEnd(BattleEnd battleEnd, LoginResult loginResult, MapMonsterBean bean, int v) {
        return 0;
    }
}
