package come.tool.BangBattle;

import come.tool.FightingData.Battlefield;
import come.tool.newGang.GangDomain;
import come.tool.Good.DropUtil;
import come.tool.newGang.GangUtil;
import org.come.action.reward.DrawnitemsAction;
import come.tool.Stall.AssetUpdate;
import org.come.bean.LoginResult;
import org.come.action.gang.GangBattleAction;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import org.come.protocol.ParamTool;
import org.come.action.IAction;
import java.util.Iterator;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import java.util.Map;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class BangFight
{
    public static String SLJL;//获取帮战胜利奖励
    public static String SLEXP;
    public static String SBJL;//获取帮战失败奖励
    public static String SBEXP;
    public static String QC;//结束状态清除"gangstate//0|0|0"
    public static int HURT;//普通伤害
    public static int HURT_LONG;//龙神伤害
    public static int HURT_GAO;//高手伤害
    public static int TIME_GAO;//挑战赛间隔
    public static int TIME_END;//帮战最大时长
    public int BangFightID;//沙盘id
    public int sum;//场次
    public int BangState;//状态  0未开启  1开启  2关闭
    public BangPoints Camp_Left_ID;//帮战成员
    private ConcurrentHashMap<String, Member> Camp_Left_Man;
    public BangPoints Camp_Right_ID;
    private ConcurrentHashMap<String, Member> Camp_Right_Man;
    private Build LongCannon;
    private Build Left_Gate;
    private Build Left_FIRE_1;
    private Build Left_FIRE_2;
    private Build Left_ICE_1;
    private Build Left_ICE_2;
    private Build Right_Gate;
    private Build Right_FIRE_1;
    private Build Right_FIRE_2;
    private Build Right_ICE_1;
    private Build Right_ICE_2;
    private long PKCountDown;
    public int PKstate;
    public Member Launch;
    public long time;
    
    public BigDecimal getPK() {
        if (this.PKstate == 0 && this.Launch != null) {
            return this.Launch.getCamp();
        }
        return null;
    }
    
    public BangFight(int sum, BangPoints Camp_Left_ID, BangPoints Camp_Right_ID) {
        this.Camp_Left_Man = new ConcurrentHashMap<>();
        this.Camp_Right_Man = new ConcurrentHashMap<>();
        this.LongCannon = new Build(3, 0);
        this.Left_Gate = new Build(0, 1);
        this.Left_FIRE_1 = new Build(1, 2);
        this.Left_FIRE_2 = new Build(1, 3);
        this.Left_ICE_1 = new Build(2, 4);
        this.Left_ICE_2 = new Build(2, 5);
        this.Right_Gate = new Build(0, 11);
        this.Right_FIRE_1 = new Build(1, 12);
        this.Right_FIRE_2 = new Build(1, 13);
        this.Right_ICE_1 = new Build(2, 14);
        this.Right_ICE_2 = new Build(2, 15);
        this.Camp_Left_ID = Camp_Left_ID;
        this.Camp_Right_ID = Camp_Right_ID;
        if (this.Camp_Left_ID == null) {
            this.Camp_Left_ID = new BangPoints(new BigDecimal(-1));
        }
        if (this.Camp_Right_ID == null) {
            this.Camp_Right_ID = new BangPoints(new BigDecimal(-1));
        }
        this.sum = sum;
        this.PKCountDown = (long)BangFight.TIME_GAO;
    }
    
    public Build getBuild(int bh) {
        switch (bh) {
            case 0: {
                return this.LongCannon;
            }
            case 1: {
                return this.Left_Gate;
            }
            case 2: {
                return this.Left_FIRE_1;
            }
            case 3: {
                return this.Left_FIRE_2;
            }
            case 4: {
                return this.Left_ICE_1;
            }
            case 5: {
                return this.Left_ICE_2;
            }
            case 11: {
                return this.Right_Gate;
            }
            case 12: {
                return this.Right_FIRE_1;
            }
            case 13: {
                return this.Right_FIRE_2;
            }
            case 14: {
                return this.Right_ICE_1;
            }
            case 15: {
                return this.Right_ICE_2;
            }
            default: {
                return this.LongCannon;
            }
        }
    }
    
    public boolean addMember(String RoleName, BigDecimal gang_id) {
        Map<String, Member> map = this.getMap(gang_id);
        if (map == null) {
            return false;
        }
        if (map.get(RoleName) != null) {
            ((Member)map.get(RoleName)).setState(1);
            ((Member)map.get(RoleName)).setTime2(5L);//30
            return true;
        }
        map.put(RoleName, new Member(RoleName, gang_id));
        return true;
    }
    
    public boolean removeMember(String RoleName, BigDecimal gang_id) {
        Map<String, Member> map = this.getMap(gang_id);
        if (map == null) {
            return false;
        }
        if (map.get(RoleName) != null) {
            ((Member)map.get(RoleName)).setState(-1);
        }
        return true;
    }
    
    public Member getMember(String RoleName, BigDecimal gang_id) {
        Map<String, Member> map = this.getMap(gang_id);
        if (map == null) {
            return null;
        }
        Member member = (Member)map.get(RoleName);
        if (member == null) {
            member = new Member(RoleName, gang_id);
            map.put(RoleName, member);
        }
        Map<String, Member> map2 = this.getMap2(gang_id);
        if (map2 != null && map2.get(RoleName) != null) {
            member.setState(((Member)map2.get(RoleName)).getState());
            map2.remove(RoleName);
        }
        return member;
    }
    
    public Map<String, Member> getMap(BigDecimal gang_id) {
        if (gang_id == null) {
            return null;
        }
        if (this.Camp_Left_ID != null && gang_id.compareTo(this.Camp_Left_ID.getId()) == 0) {
            return this.Camp_Left_Man;
        }
        if (this.Camp_Right_ID != null && gang_id.compareTo(this.Camp_Right_ID.getId()) == 0) {
            return this.Camp_Right_Man;
        }
        return null;
    }
    
    public Map<String, Member> getMap2(BigDecimal gang_id) {
        if (gang_id == null) {
            return null;
        }
        if (this.Camp_Left_ID != null && gang_id.compareTo(this.Camp_Left_ID.getId()) != 0) {
            return this.Camp_Left_Man;
        }
        if (this.Camp_Right_ID != null && gang_id.compareTo(this.Camp_Right_ID.getId()) != 0) {
            return this.Camp_Right_Man;
        }
        return null;
    }
    
    public void BattleNews(String msg) {
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(msg);
        msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        this.Msg(msg);
    }
    
    public boolean iscamp(BigDecimal gang_id) {
        return gang_id == null || (this.Camp_Left_ID != null && gang_id.compareTo(this.Camp_Left_ID.getId()) == 0) || this.Camp_Right_ID == null || gang_id.compareTo(this.Camp_Right_ID.getId()) != 0;
    }
    
    public void Msg(String msg) {
        for (Member value : this.Camp_Left_Man.values()) {
            if (value.getState() != -1) {
                SendMessage.sendMessageByRoleName(value.getKey(), msg);
            }
        }
        for (Member value : this.Camp_Right_Man.values()) {
            if (value.getState() != -1) {
                SendMessage.sendMessageByRoleName(value.getKey(), msg);
            }
        }
    }
    
    public BigDecimal getBuildCamp(int bh) {
        if (bh <= 5) {
            if (this.Camp_Left_ID == null) {
                return new BigDecimal(-1);
            }
            return this.Camp_Left_ID.getId();
        }
        else {
            if (this.Camp_Right_ID == null) {
                return new BigDecimal(-1);
            }
            return this.Camp_Right_ID.getId();
        }
    }
    
    public Member getrole(String RoleName) {
        Member role = null;
        if (role == null && this.Camp_Left_Man != null) {
            role = (Member)this.Camp_Left_Man.get(RoleName);
        }
        if (role == null && this.Camp_Right_Man != null) {
            role = (Member)this.Camp_Right_Man.get(RoleName);
        }
        return role;
    }
    
    public void getzk(String RoleName, boolean is) {
        Member role = this.getrole(RoleName);
        if (role == null) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("0|");
        if (this.BangState == 1) {
            buffer.append(1);
        }
        else {
            buffer.append(0);
        }
        buffer.append("|");
        buffer.append(role.getState());
        if (is) {
            for (int i = 0; i < 11; ++i) {
                Build build = this.getBuild((i > 5) ? (i + 5) : i);
                buffer.append("|");
                buffer.append(build.getBh());
                buffer.append("=");
                buffer.append(build.getType());
                buffer.append("=");
                buffer.append(build.getSurvival());
                buffer.append("=");
                buffer.append(build.getHp());
            }
        }
        SendMessage.sendMessageByRoleName(RoleName, Agreement.getAgreement().gangstate(buffer.toString()));
    }
    
    public void getzk_role(Member role) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("0|");
        if (this.BangState == 1) {
            buffer.append(1);
        }
        else {
            buffer.append(0);
        }
        buffer.append("|");
        buffer.append(role.getState());
        SendMessage.sendMessageByRoleName(role.getKey(), Agreement.getAgreement().gangstate(buffer.toString()));
    }
    
    public void getzk_Build(Build build) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(1);
        buffer.append("|");
        buffer.append(build.getBh());
        buffer.append("=");
        buffer.append(build.getType());
        buffer.append("=");
        buffer.append(build.getSurvival());
        buffer.append("=");
        buffer.append(build.getHp());
        this.Msg(Agreement.getAgreement().gangstate(buffer.toString()));
    }
    
    public void getzk_L_Build(Build build) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(1);
        buffer.append("|");
        buffer.append(build.getBh());
        buffer.append("=");
        buffer.append(build.getType());
        buffer.append("=");
        buffer.append(build.getSurvival());
        buffer.append("=");
        buffer.append(build.getHp());
        buffer.append("|0=3=");
        buffer.append((build.getBh() == 1) ? 3 : 6);
        buffer.append("=600");
        this.Msg(Agreement.getAgreement().gangstate(buffer.toString()));
    }
    
    public void process() {
        if (this.BangState != 1) {
            return;
        }
        ++this.time;
        for (Member value : this.Camp_Left_Man.values()) {
            if (value.process()) {
                this.getzk_role(value);
            }
        }
        for (Member value : this.Camp_Right_Man.values()) {
            if (value.process()) {
                this.getzk_role(value);
            }
        }
        --this.PKCountDown;
        if (this.PKCountDown <= 0L) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("#R新的一轮挑战#Y开始了，请各位核心人员速速前往挑战擂台。");
            if (this.PKstate == 0) {
                if (this.Launch == null) {
                    buffer.append("本轮挑战赛没有人参与!");
                }
                else {
                    Build door = this.getDoor(this.Launch.getKey());
                    if (door.setHp(door.getHp() - BangFight.HURT_GAO)) {
                        this.getzk_Build(door);
                    }
                    buffer.append("竟然没有人接受#G");
                    buffer.append(this.Launch.getKey());
                    buffer.append("#Y的挑战被惩罚扣除城门#G600#Y血量,剩余血量#G" + door.getHp());
                    this.Launch.setState(0);
                    this.getzk_role(this.Launch);
                }
            }
            this.PKCountDown = (long)BangFight.TIME_GAO;
            this.PKstate = 0;
            this.Launch = null;
            this.BattleNews(buffer.toString());
        }
        for (int i = 0; i < 11; ++i) {
            Build build = this.getBuild((i > 5) ? (i + 5) : i);
            if (build.getHp() > 0 && build.getState() != Build.IDLE) {
                if (build.getState() == Build.ENERGY) {
                    if (build.istime()) {
                        this.getzk_Build(build);
                        Member role = this.getrole(build.getRoleName());
                        if (role != null) {
                            role.setState(0);
                            this.getzk_role(role);
                            StringBuffer buffer2 = new StringBuffer();
                            buffer2.append("玩家#G");
                            buffer2.append(build.getRoleName());
                            buffer2.append("#Y给#G");
                            buffer2.append(build.getName());
                            buffer2.append("#Y充能成功");
                            role = this.getMember(role);
                            if (role == null) {
                                buffer2.append(",可惜不生效");
                            }
                            else {
                                buffer2.append(",对玩家#G");
                                buffer2.append(role.getKey());
                                buffer2.append("#Y产生了效果");
                                if (build.getType() == Build.TOWER_FIRE) {
                                    this.daduan(role.getKey(), 1);
                                    IAction action = (IAction)ParamTool.ACTION_MAP.get("changemap");
                                    action.action((ChannelHandlerContext)GameServer.getRoleNameMap().get(role.getKey()), this.iscamp(role.getCamp()) ? GangBattleAction.HOME_Left : GangBattleAction.HOME_Right);
                                }
                                else {
                                    this.daduan(role.getKey(), 2);
                                    ChannelHandlerContext ctx2 = (ChannelHandlerContext)GameServer.getRoleNameMap().get(role.getKey());
                                    if (ctx2 != null) {
                                        LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx2);
                                        if (loginResult != null) {
                                            String[] v = loginResult.getTeam().split("\\|");
                                            for (int j = 0; j < v.length; ++j) {
                                                if (!v[j].equals(role.getKey())) {
                                                    this.daduan(v[j], 2);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            this.BattleNews(buffer2.toString());
                        }
                        build.setRoleName(null);
                    }
                }
                else if (build.getState() == Build.BEATEN) {
                    if (build.istime()) {
                        if (build.setHp(build.getHp() - BangFight.HURT)) {
                            this.getzk_Build(build);
                        }
                        Member role = this.getrole(build.getRoleName());
                        if (role != null) {
                            role.setState(0);
                            this.getzk_role(role);
                            StringBuffer bufferR = new StringBuffer();
                            bufferR.append("玩家#G");
                            bufferR.append(build.getRoleName());
                            bufferR.append("#Y成功攻击#G");
                            bufferR.append(build.getName());
                            bufferR.append("#Y造成10点伤害,剩余血量#G" + build.getHp());
                            this.BattleNews(bufferR.toString());
                        }
                        build.setRoleName(null);
                    }
                }
                else if (build.getState() == Build.ATTACK && build.istime()) {
                    Build door2 = this.getDoor(build.getRoleName());
                    if (door2.setHp(door2.getHp() - BangFight.HURT_LONG)) {
                        this.getzk_L_Build(door2);
                    }
                    Member role2 = this.getrole(build.getRoleName());
                    if (role2 != null) {
                        role2.setState(0);
                        this.getzk_role(role2);
                        StringBuffer buffer3 = new StringBuffer();
                        buffer3.append("玩家#G");
                        buffer3.append(build.getRoleName());
                        buffer3.append("#Y成功操控#G龙神大炮#Y给敌方城门造成180点伤害,剩余血量#G" + door2.getHp());
                        this.BattleNews(buffer3.toString());
                    }
                    build.setRoleName(null);
                }
            }
        }
    }
    
    public void addFeat(Member role, int f) {
        String jf = "帮派积分=" + f;
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.updata(jf);
        assetUpdate.setType(AssetUpdate.USERGOOD);
        String msg = Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate));
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(role.getKey());
        if (ctx != null) {
            LoginResult log = (LoginResult)GameServer.getAllLoginRole().get(ctx);
            if (log != null) {
                String[] v = log.getTeam().split("\\|");
                for (int i = 0; i < v.length; ++i) {
                    ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(v[i]);
                    if (ctx != null) {
                        log = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                        if (log != null) {
                            log.setScore(DrawnitemsAction.Splice(log.getScore(), jf, 2));
                            SendMessage.sendMessageToSlef(ctx, msg);
                        }
                    }
                }
            }
        }
    }
    
    public Build getDoor(String role) {
        if (this.Camp_Left_Man.get(role) != null) {
            return this.Right_Gate;
        }
        return this.Left_Gate;
    }
    
    public boolean isEnd() {
        if (this.BangState != 1 || this.time >= (long)BangFight.TIME_END || this.Left_Gate.getHp() <= 0 || this.Right_Gate.getHp() <= 0) {
            this.isVictory();
            return true;
        }
        return false;
    }
    
    public void isVictory() {
        if (this.Left_Gate.getHp() <= 0) {
            this.PVictory(this.Camp_Right_ID, this.Camp_Left_ID);
        }
        else if (this.Right_Gate.getHp() <= 0) {
            this.PVictory(this.Camp_Left_ID, this.Camp_Right_ID);
        }
        else if (this.Left_Gate.getHp() >= this.Right_Gate.getHp()) {
            this.PVictory(this.Camp_Left_ID, this.Camp_Right_ID);
        }
        else {
            this.PVictory(this.Camp_Right_ID, this.Camp_Left_ID);
        }
        this.BangState = 2;
    }
    
    public void PVictory(BangPoints s, BangPoints b) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("帮战结束");
        GangDomain gangDomain = GangUtil.getGangDomain(s.getId());
        if (gangDomain != null) {
            buffer.append("帮派:#G");
            buffer.append(gangDomain.getGang().getGangname());
            buffer.append("#Y获得胜利");
        }
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage(buffer.toString());
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        Map<String, Member> v = (s == this.Camp_Left_ID) ? this.Camp_Left_Man : this.Camp_Right_Man;
        for (Member value : v.values()) {
            value.setState(0);
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(value.getKey());
            if (ctx != null) {
                LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                if (loginResult != null) {
                    if (value.getTime() > 300L) {
                        DropUtil.getDrop(loginResult, BangFight.SLJL, "帮战胜利礼包", 22, 300.0, null);
                    }
                    else {
                        DropUtil.getDrop(loginResult, BangFight.SLEXP, "帮战胜利礼包", 22, (double)value.getTime(), null);
                    }
                    SendMessage.sendMessageToSlef(ctx, BangFight.QC);
                    SendMessage.sendMessageToSlef(ctx, msg);
                }
                else {
                    continue;
                }
            }
        }
        v = ((b == this.Camp_Left_ID) ? this.Camp_Left_Man : this.Camp_Right_Man);
        for (Member value : v.values()) {
            value.setState(0);
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(value.getKey());
            if (ctx != null) {
                LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                if (loginResult != null) {
                    if (value.getTime() > 300L) {
                        DropUtil.getDrop(loginResult, BangFight.SBJL, "帮战参与礼包", 22, 300.0, null);
                    }
                    else {
                        DropUtil.getDrop(loginResult, BangFight.SBEXP, "帮战参与礼包", 22, (double)value.getTime(), null);
                    }
                    SendMessage.sendMessageToSlef(ctx, BangFight.QC);
                    SendMessage.sendMessageToSlef(ctx, msg);
                }
                else {
                    continue;
                }
            }
        }
    }
    
    public Member getMember(Member role) {
        Map<String, Member> mans = null;
        if (this.Camp_Left_Man.get(role.getKey()) != null) {
            mans = this.Camp_Right_Man;
        }
        else {
            mans = this.Camp_Left_Man;
        }
        Member man = null;
        int size = mans.size();
        for (Member value : mans.values()) {
            if (value.getState() == 0 || value.getState() == 4) {
                LoginResult loginResult = (LoginResult)GameServer.getAllLoginRole().get(GameServer.getRoleNameMap().get(value.getKey()));
                if (loginResult != null) {
                    if ((int)loginResult.getFighting() != 0) {
                        continue;
                    }
                    else {
                        man = value;
                        if (Battlefield.random.nextInt(size) <= 2) {
                            break;
                        }
                        else {
                            continue;
                        }
                    }
                }
                else {
                    continue;
                }
            }
        }
        return man;
    }
    
    public String daduan2(String rolename, int type) {
        Member role = this.getrole(rolename);
        if (role == null) {
            return null;
        }
        if (type == 0) {
            if (role.getState() == 2) {
                return "玩家" + role.getKey() + "处于冰冻状态";
            }
            if (role.getState() == 3) {
                return "玩家" + role.getKey() + "处于挑战状态";
            }
            if (role.getState() == 1) {
                return "玩家" + role.getKey() + "处于休息状态";
            }
            Build door = this.getBuild(rolename);
            if (door != null) {
                door.setRoleName(null);
                door.setState(Build.IDLE);
                door.setTime(0L);
            }
            if (role.getState() != 0) {
                role.setState(0);
                this.getzk_role(role);
            }
        }
        else if (type == 1) {
            role.setState(0);
            this.getzk_role(role);
            this.Launch = null;
            this.PKstate = 1;
        }
        else if (type == 2) {
            if (role.getState() == 3) {
                return "玩家" + role.getKey() + "处于挑战状态";
            }
            Build door = this.getBuild(rolename);
            if (door != null) {
                if (door.getType() == Build.TOWER_LONG) {
                    return "玩家正在点火快点阻止他";
                }
                door.setState(Build.IDLE);
                door.setTime(0L);
                door.setRoleName(null);
            }
            if (role.getState() != 0) {
                role.setState(0);
                this.getzk_role(role);
            }
        }
        else if (type == 3) {
            Build door = this.getBuild(rolename);
            if (door != null) {
                door.setState(Build.IDLE);
                door.setTime(0L);
                door.setRoleName(null);
            }
            if (role.getState() != 0) {
                role.setState(0);
                this.getzk_role(role);
            }
        }
        return null;
    }
    
    public void daduan(String rolename, int state) {
        Member role = this.getrole(rolename);
        if (role == null) {
            return;
        }
        Build door = this.getBuild(rolename);
        if (door != null) {
            door.setRoleName(null);
            door.setState(Build.IDLE);
            door.setTime(0L);
        }
        if (role.getState() != state) {
            if (state == 1) {
                role.setTime2(8L);//40
            }
            else if (state == 2) {
                role.setTime2(4L);//20
            }
            role.setState(state);
            this.getzk_role(role);
        }
    }
    
    public Build getBuild(String role) {
        for (int i = 0; i < 11; ++i) {
            Build build = this.getBuild((i > 5) ? (i + 5) : i);
            if (build.getRoleName().equals(role)) {
                return build;
            }
        }
        return null;
    }
    
    public void PKP(String role) {
        Build door = this.getDoor(role);
        if (door.setHp(door.getHp() - BangFight.HURT_GAO)) {
            this.getzk_Build(door);
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("#R玩家#G");
        buffer.append(role);
        buffer.append("#R带领队伍获得挑战赛的胜利给敌方城门造成#R600点伤害,剩余血量#R" + door.getHp());
        this.BattleNews(buffer.toString());
    }
    
    static {
        BangFight.SLJL = "经验=1|物品=0&204$1$100";
        BangFight.SLEXP = "经验=1";
        BangFight.SBJL = "经验=1|物品=0&204$1$100";
        BangFight.SBEXP = "经验=1";
        BangFight.QC = Agreement.getAgreement().gangstate("0|0|0");
        BangFight.HURT = 10;
        BangFight.HURT_LONG = 180;
        BangFight.HURT_GAO = 600;
        BangFight.TIME_GAO = 300;
        BangFight.TIME_END = 3600;
    }
}
