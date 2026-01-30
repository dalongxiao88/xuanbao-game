package come.tool.FightingDataAction;

import org.come.tool.Arith;
import java.util.Random;
import java.util.List;
import come.tool.FightingData.FightingState;
import java.util.ArrayList;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Stall.AssetUpdate;
import org.come.action.monitor.MonitorUtil;
import java.math.BigDecimal;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import come.tool.FightingData.Battlefield;
import come.tool.FightingData.FightingEvents;
import come.tool.FightingData.ManData;

public class Stealing implements DataAction
{
    static final int MAXGL = 1000000;
    static final int MAXGL2 = 975000;
    static final int MAXGL3 = 950000;
    static final int MAXGL4 = 925000;
    static final int GL_1 = 20;
    static final int GL_2 = 100;
    static final int GL_3 = 450;
    static final int GL_4 = 1800;
    static final int GL_5 = 8500;
    static final int GL_6 = 41500;
    static final int GL_7 = 200000;
    static final long JS = 30000000L;
    
    @Override
    public void analysisAction(ManData manData, FightingEvents fightingEvents, String type, Battlefield battlefield) {
        ManData roleData = null;
        int i = 0;
        while (i < battlefield.fightingdata.size()) {
            ManData data = (ManData)battlefield.fightingdata.get(i);
            if (data.getType() == 0) {
                roleData = data;
                break;
            }
            else {
                ++i;
            }
        }
        if (roleData == null) {
            return;
        }
        ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(roleData.getManname());
        LoginResult login = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (login == null) {
            return;
        }
        long money = login.getGold().longValue();
        long money2 = this.getMoney(battlefield.CurrentRound, roleData);
        if (money2 > money) {
            money2 = money;
        }
        money -= money2;
        login.setGold(new BigDecimal(money));
        MonitorUtil.getMoney().useD(money2);
        AssetUpdate assetUpdate = new AssetUpdate();
        assetUpdate.setType(AssetUpdate.STEALING);
        assetUpdate.updata("D=" + -money2);
        SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().assetAgreement(GsonUtil.getGsonUtil().getgson().toJson(assetUpdate)));
        roleData.setMoney(roleData.getMoney() + money2);
        roleData.setMoney2(money2);
        List<FightingState> Accepterlist = new ArrayList<>();
        FightingState f1 = new FightingState();
        f1.setCamp(manData.getCamp());
        f1.setMan(manData.getMan());
        f1.setStartState("法术攻击");
        Accepterlist.add(f1);
        FightingState state = new FightingState();
        state.setCamp(roleData.getCamp());
        state.setMan(roleData.getMan());
        state.setStartState("代价");
        StringBuffer buffer = new StringBuffer();
        buffer.append("#Y被偷取#R");
        buffer.append(money2);
        buffer.append("#Y金钱");
        state.setText(buffer.toString());
        Accepterlist.add(state);
        fightingEvents.setOriginator(null);
        fightingEvents.setAccepterlist(Accepterlist);
        battlefield.NewEvents.add(fightingEvents);
    }
    
    public long getMoney(int h, ManData manData) {
        long money = 30000000L;
        if (h <= 2) {
            if (h == 1) {
                money = 15000000L;
            }
            money += (long)(Battlefield.random.nextInt((int)money) * 1);
        }
        else {
            int gl = 0;
            if (h < 3) {
                gl = Battlefield.random.nextInt(975000);
            }
            else if (h < 4) {
                gl = Battlefield.random.nextInt(950000);
            }
            else if (h < 5) {
                gl = Battlefield.random.nextInt(925000);
            }
            else {
                gl = Battlefield.random.nextInt(1000000);
            }
            if (gl < 20) {
                money = 60000000000L;
                money += (long)(Battlefield.random.nextInt((int)money / 2) * 8);
            }
            else if (gl < 100) {
                money = 30000000000L;
                money += (long)(Battlefield.random.nextInt((int)money) * 1);
            }
            else if (gl < 450) {
                money = 6000000000L;
                money += (long)(Battlefield.random.nextInt((int)money / 2) * 8);
            }
            else if (gl < 1800) {
                money = 3000000000L;
                money += (long)(Battlefield.random.nextInt((int)money) * 1);
            }
            else if (gl < 8500) {
                money = 600000000L;
                money += (long)(Battlefield.random.nextInt((int)money / 2) * 8);
            }
            else if (gl < 41500) {
                money = 300000000L;
                money += (long)(Battlefield.random.nextInt((int)money) * 1);
            }
            else if (gl < 200000) {
                money = 60000000L;
                money += (long)(Battlefield.random.nextInt((int)money / 2) * 8);
            }
            else {
                Double d = Double.valueOf(Arith.div((double)(new Random().nextInt(100) + 150), 100.0));
                money = (long)((double)manData.getMoney() * (double)d);
                if (money > 2000000000L) {
                    money = 2000000000L;
                }
            }
        }
        return money;
    }
    
    public static void main(String[] args) {
        Stealing stealing = new Stealing();
        int size = 200000000;
        int lj = 0;
        for (int k = 0; k < 10; ++k) {
            ++lj;
            long z = 0L;
            for (int i = 0; i < size; ++i) {
                int j = 0;
                while (j < lj) {
                    long x = stealing.getMoney(j, null);
                    z -= x;
                    if (x > 200000L) {
                        z += 2L * x;
                        break;
                    }
                    else {
                        if (j == lj - 1) {
                            z += 2L * x;
                        }
                        ++j;
                    }
                }
            }
            System.out.println("偷钱次数" + lj);
            System.out.println("平均收益" + z / (long)size);
        }
    }
}
