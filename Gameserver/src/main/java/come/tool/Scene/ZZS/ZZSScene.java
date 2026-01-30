package come.tool.Scene.ZZS;

import org.come.task.MonsterMoveBase;
import java.util.Map;
import org.come.task.MapMonsterBean;
import come.tool.Battle.BattleEnd;
import org.come.model.Dorp;
import come.tool.Good.DropUtil;
import come.tool.Battle.BattleData;
import org.come.action.gang.GangBattleAction;
import org.come.protocol.ParamTool;
import org.come.action.IAction;
import come.tool.Battle.BattleThreadPool;
import come.tool.Battle.FightingForesee;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import java.util.Iterator;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import java.util.ArrayList;
import org.come.until.GsonUtil;
import org.come.bean.ChangeMapBean;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import come.tool.Scene.Scene;

public class ZZSScene implements Scene
{
    private int I;
    public int type;
    private ConcurrentHashMap<String, ZZSRole> zzsMap;
    private List<ZZSRole> top;
    private long mapID;
    private String TP;
    private ZZSThread zzsThread;
    private List<ZZSRole> matchs;
    
    public ZZSScene(int type) {
        this.type = type;
        this.mapID = (long)(3329 + type);
        ChangeMapBean change = new ChangeMapBean();
        change.setMapid(this.mapID + "");
        change.setMapx(1200);
        change.setMapy(900);
        this.TP = GsonUtil.getGsonUtil().getgson().toJson(change);
        this.init();
    }
    
    public void init() {
        this.I = 1;
        this.top = new ArrayList<>();
        this.matchs = new ArrayList<>();
        this.zzsMap = new ConcurrentHashMap<>();
        this.zzsThread = new ZZSThread(this);
        Thread T1 = new Thread(this.zzsThread);
        T1.start();
    }
    
    public void upI(int ST) {
        if (this.I >= ST) {
            return;
        }
        this.I = ST;
        if (this.I == 2) {
            NChatBean bean = new NChatBean();
            bean.setId(5);
            bean.setMessage("#c00FFFF种族比武#Y正式开始，请各位大侠准备好后点场内NPC进行匹配#50");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToMapRoles(Long.valueOf(this.mapID), msg);
        }
        else if (this.I == 3) {
            NChatBean bean = new NChatBean();
            bean.setId(5);
            bean.setMessage("#Y种族赛中场休息#R5分钟#Y后进入淘汰赛,排名#R前10#Y的玩家获得淘汰赛资格,其他玩家请离场");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToMapRoles(Long.valueOf(this.mapID), msg);
        }
        else if (this.I == 4) {
            NChatBean bean = new NChatBean();
            bean.setId(5);
            bean.setMessage("#R种族赛#Y进入淘汰赛,玩家自行PK,#R战败2场#Y将被踢出地图,剩下最后一个就是获胜者");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToMapRoles(Long.valueOf(this.mapID), msg);
            this.clearRole();
        }
        else if (this.I == 5) {
            NChatBean bean = new NChatBean();
            bean.setId(5);
            bean.setMessage("#R种族赛结束 ");
            String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
            SendMessage.sendMessageToMapRoles(Long.valueOf(this.mapID), msg);
        }
    }
    
    public void match() {
        this.matchs.clear();
        boolean is = true;
        for (ZZSRole value : this.zzsMap.values()) {
            if (this.isMatch(value)) {
                is = true;
                int i = 0;
                while (i < this.matchs.size()) {
                    ZZSRole role = (ZZSRole)this.matchs.get(i);
                    if (role.getJf() > value.getJf()) {
                        is = false;
                        this.matchs.add(i, value);
                        break;
                    }
                    else {
                        ++i;
                    }
                }
                if (is) {
                    this.matchs.add(value);
                }
                else {
                    continue;
                }
            }
        }
        int size = this.matchs.size();
        int j = 0;
        while (j < size) {
            ZZSRole role2 = (ZZSRole)this.matchs.get(j);
            if (++j >= size) {
                break;
            }
            else {
                ZZSRole role3 = (ZZSRole)this.matchs.get(j);
                if (Math.abs(role3.getJf() - role2.getJf()) > 60) {
                    continue;
                }
                else {
                    ++j;
                    role2.setI(0);
                    role3.setI(0);
                    this.BattleInto(role2, role3);
                }
            }
        }
    }
    
    public boolean isMatch(ZZSRole value) {
        if (value.getI() != 1) {
            return false;
        }
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(value.getRole());
        if (ctx == null) {
            value.setI(0);
            return false;
        }
        LoginResult log = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (log == null) {
            value.setI(0);
            return false;
        }
        if ((long)log.getMapid() != this.mapID) {
            value.setI(0);
            return false;
        }
        if ((int)log.getFighting() != 0) {
            value.setI(0);
            return false;
        }
        return true;
    }
    
    public boolean isEnd(ZZSRole value) {
        if (value.getI() != 3) {
            return false;
        }
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(value.getRole());
        if (ctx == null) {
            return false;
        }
        LoginResult log = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        return log != null && (long)log.getMapid() == this.mapID;
    }
    
    public void BattleInto(ZZSRole m1, ZZSRole m2) {
        FightingForesee foresee = new FightingForesee();
        foresee.setYidui(m1.getRole());
        foresee.setErdui(m2.getRole());
        foresee.setType(31);
        BattleThreadPool.addBattle((ChannelHandlerContext)GameServer.getRoleNameMap().get(m1.getRole()), foresee);
    }
    
    public void BattleEnd(String m1, String m2, int type) {
        if (m1 != null) {
            ZZSRole role1 = (ZZSRole)this.zzsMap.get(m1);
            if (role1 != null && role1.Battle(true, type)) {
                this.PointRanking(role1);
            }
        }
        if (m2 != null) {
            ZZSRole role2 = (ZZSRole)this.zzsMap.get(m2);
            if (role2 != null) {
                if (role2.Battle(false, type)) {
                    this.PointRanking(role2);
                }
                if (type == 32 && role2.getZBnum() >= 2) {
                    IAction action = (IAction)ParamTool.ACTION_MAP.get("changemap");
                    ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(role2.getRole());
                    if (ctx != null) {
                        action.action(ctx, GangBattleAction.OUTCA);
                    }
                }
            }
        }
    }
    
    public synchronized void PointRanking(ZZSRole role) {
        this.top.remove(role);
        boolean is = true;
        int i = 0;
        while (i < this.top.size()) {
            ZZSRole zzsRole = (ZZSRole)this.top.get(i);
            if (zzsRole.getJf() < role.getJf()) {
                is = false;
                this.top.add(i, role);
                break;
            }
            else {
                ++i;
            }
        }
        if (is) {
            if (this.top.size() < 10) {
                this.top.add(role);
            }
        }
        else if (this.top.size() > 10) {
            for (i = this.top.size() - 1; i > 10; --i) {
                this.top.remove(i);
            }
        }
    }
    
    public void addRole(ChannelHandlerContext ctx, LoginResult loginResult) {
        if (this.zzsMap.get(loginResult.getRolename()) == null) {
            ZZSRole role = new ZZSRole(loginResult.getRole_id(), loginResult.getRolename());
            this.zzsMap.put(role.getRole(), role);
            if (this.top.size() < 10) {
                this.PointRanking(role);
            }
        }
        IAction action = (IAction)ParamTool.ACTION_MAP.get("changemap");
        action.action(ctx, this.TP);
    }
    
    public ZZSRole getRole(LoginResult loginResult) {
        return (ZZSRole)this.zzsMap.get(loginResult.getRolename());
    }
    
    @Override
    public String UPMonster(BattleData battleData, String[] teams, int type, StringBuffer buffer) {
        return null;
    }
    
    @Override
    public void getAward(ChannelHandlerContext ctx, LoginResult loginResult) {
        ZZSRole zzsRole = this.getRole(loginResult);
        if (zzsRole == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你没参加种族赛"));
            return;
        }
        List<ZZSAward> awards = zzsRole.getAwards();
        if (awards == null) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("未达到奖励要求"));
            return;
        }
        int size = 0;
        for (int i = 0; i < awards.size(); ++i) {
            ZZSAward award = (ZZSAward)awards.get(i);
            if (!award.isAward()) {
                ++size;
                award.setAward(true);
                Dorp dorp = this.Award(award);
                if (dorp != null) {
                    if (award.getType() == 1) {
                        DropUtil.getDrop(loginResult, dorp.getDorpValue(), "种族赛参与礼包", 22, 1.0, null);
                    }
                    else if (award.getType() == 2) {
                        DropUtil.getDrop(loginResult, dorp.getDorpValue(), "种族赛5场胜利礼包", 22, 1.0, null);
                    }
                    else if (award.getType() == 3) {
                        DropUtil.getDrop(loginResult, dorp.getDorpValue(), "种族赛3连胜礼包", 22, 1.0, null);
                    }
                    else if (award.getType() == 4) {
                        DropUtil.getDrop(loginResult, dorp.getDorpValue(), "种族赛10强礼包", 22, 1.0, null);
                    }
                    else if (award.getType() == 5) {
                        DropUtil.getDrop(loginResult, dorp.getDorpValue(), "种族赛冠军礼包", 22, 1.0, null);
                    }
                }
            }
        }
        if (size == 0) {
            SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你的奖励都领取完了"));
        }
    }
    
    public Dorp Award(ZZSAward award) {
        Dorp dorp = GameServer.getDorp(award.getType() + 399 + "");
        if (dorp == null) {
            return null;
        }
        return dorp;
    }
    
    public String getRanking(ZZSRole value) {
        StringBuffer buffer = new StringBuffer();
        if (this.I == 2) {
            buffer.append("处于匹配赛阶段  ");
        }
        else {
            buffer.append("不处于匹配赛阶段  ");
        }
        buffer.append("当前积分排名 ");
        int v = -1;
        for (int i = 0; i < this.top.size(); ++i) {
            ZZSRole role = (ZZSRole)this.top.get(i);
            if (role.getId().compareTo(value.getId()) == 0) {
                v = i;
            }
            if (i == 0) {
                buffer.append("第一:");
                buffer.append(role.getRole());
                buffer.append(" 积分:");
                buffer.append(role.getJf());
            }
            else if (i == 1) {
                buffer.append("   第二:");
                buffer.append(role.getRole());
                buffer.append(" 积分:");
                buffer.append(role.getJf());
            }
            else if (i == 2) {
                buffer.append("   第三:");
                buffer.append(role.getRole());
                buffer.append(" 积分:");
                buffer.append(role.getJf());
            }
        }
        buffer.append("   你当前积分为:");
        buffer.append(value.getJf());
        buffer.append(" 目前排名:");
        if (v == -1) {
            buffer.append("未上榜");
        }
        else {
            buffer.append(v + 1);
        }
        return buffer.toString();
    }
    
    public void clearRole() {
        IAction action = (IAction)ParamTool.ACTION_MAP.get("changemap");
        for (ZZSRole value : this.zzsMap.values()) {
            if (!this.top.contains(value)) {
                ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(value.getRole());
                if (ctx != null) {
                    LoginResult log = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                    if (log != null && (long)log.getMapid() == this.mapID) {
                        action.action(ctx, GangBattleAction.OUTCA);
                    }
                    else {
                        continue;
                    }
                }
                else {
                    continue;
                }
            }
            else {
                value.setI(3);
                value.addAward(4);
            }
        }
    }
    
    public void isTT() {
        this.matchs.clear();
        for (ZZSRole value : this.zzsMap.values()) {
            if (this.isEnd(value)) {
                this.matchs.add(value);
                if (this.matchs.size() >= 2) {
                    break;
                }
                else {
                    continue;
                }
            }
        }
        if (this.matchs.size() <= 1) {
            if (this.matchs.size() == 1) {
                ZZSRole role = (ZZSRole)this.matchs.get(0);
                role.addAward(5);
                NChatBean bean = new NChatBean();
                bean.setId(5);
                StringBuffer buffer = new StringBuffer();
                buffer.append("#Y玩家#G");
                buffer.append(role.getRole());
                buffer.append("#Y获得种族赛-");
                buffer.append((this.type == 0) ? "人" : ((this.type == 1) ? "魔" : ((this.type == 2) ? "仙" : ((this.type == 3) ? "鬼" : "龙"))));
                buffer.append("#Y的#R冠军");
                bean.setMessage(buffer.toString());
                String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                SendMessage.sendMessageToAllRoles(msg);
            }
            this.upI(5);
        }
    }
    
    @Override
    public boolean isEnd() {
        return this.I != 5;
    }
    
    public int getI() {
        return this.I;
    }
    
    public int getType() {
        return this.type;
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
    
    @Override
    public Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer buffer, Map<Integer, MonsterMoveBase> moveMap, long mapId) {
        return moveMap;
    }
}
