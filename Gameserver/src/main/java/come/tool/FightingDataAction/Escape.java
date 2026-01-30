package come.tool.FightingDataAction;

import come.tool.Role.RoleData;
import java.math.BigDecimal;
import come.tool.Role.RolePool;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class Escape implements DataAction
{
    private String KRD;
    private String VIP;
    private String HPY;
    private String MPY;
    private String blood;
    private String Mana;
    
    public Escape() {
        this.KRD = "枯荣丹";
        this.VIP = "VIP";
        this.HPY = "六脉化神丸";
        this.MPY = "玉枢返虚丸";
        this.blood = "超级六脉化神丸_月";
        this.Mana = "超级玉枢返虚丸_月";
    }
    
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        int gl = 120;
        if (manData.getType() == 2) {
            gl = 101;
        }
        else if (battlefield.BattleType == 10) {
            if (manData.getCamp() == 1) {
                gl = -1;
            }
            else {
                gl = 50;
            }
        }
        else if (battlefield.BattleType == 15 || battlefield.BattleType == 16 || battlefield.BattleType == 101) {
            gl = -1;
        }
        if (Battlefield.random.nextInt(100) < gl) {
            fightingEvents.getOriginator().setStartState("逃跑成功");
            fightingEvents.getOriginator().setSkillsy("逃跑成功");
            if (battlefield.battleData.getBattleType() == 4 && manData.getType() == 2) {
                fightingEvents.getOriginator().setText("拜拜了您#76");
            }
            if (manData.getType() == 0) {
                for (int i = 0; i < 4; ++i) {
                    int rolepath = battlefield.Datapath(manData.getCamp(), manData.getMan() + i * 5);
                    if (rolepath != -1) {
                        ((ManData)battlefield.fightingdata.get(rolepath)).setStates(2);
                        ((ManData)battlefield.fightingdata.get(rolepath)).clearAddStates();
                    }
                }
            }
            else {
                manData.setStates(2);
            }
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(manData.getManname());
            LoginResult loginResult = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (loginResult != null) {
                RoleData roleData = RolePool.getRoleData(loginResult.getRole_id());
                boolean isH = roleData.getLimit(this.HPY) == null;
                boolean isM = roleData.getLimit(this.MPY) == null;
                boolean isHS = roleData.getLimit(this.blood) == null;
                boolean isMS = roleData.getLimit(this.Mana) == null;
                if (battlefield.isFightType()) {
                    if (manData.getHp() <= 0) {
                        loginResult.setHp(new BigDecimal((int)((double)manData.getHp_z() * 0.25)));
                        loginResult.setMp(new BigDecimal((int)((double)manData.getMp_z() * 0.25)));
                    }
                    else {
                        loginResult.setHp(new BigDecimal(isHS ? manData.getHp() : manData.getHp_z()));
                        loginResult.setMp(new BigDecimal(isMS ? manData.getMp() : manData.getMp_z()));
                    }
                    if (roleData.getLimit(this.blood) == null && roleData.getLimit(this.Mana) == null) {
                        loginResult.setHp(new BigDecimal(isH ? manData.getHp() : manData.getHp_z()));
                        loginResult.setMp(new BigDecimal(isM ? manData.getMp() : manData.getMp_z()));
                    }
                }
            }
        }
        else {
            fightingEvents.getOriginator().setStartState("逃跑失败");
            fightingEvents.getOriginator().setSkillsy("逃跑失败");
        }
        battlefield.NewEvents.add(fightingEvents);
    }
}
