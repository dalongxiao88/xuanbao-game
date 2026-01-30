package come.tool.Scene.TGDB;

import come.tool.Battle.BattleEnd;
import org.come.task.MonsterMoveBase;
import java.util.Map;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import come.tool.Battle.BattleData;
import org.come.model.Boos;
import org.come.task.MapMonsterBean;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NChatBean;
import org.come.task.MonsterUtil;
import org.come.server.GameServer;
import org.come.model.Robots;
import come.tool.Scene.Scene;

public class TGDBScene implements Scene
{
    private int I;
    private TGDBMonster[] tgdbMonsters;
    static String MSG1;
    static String MSG2;
    static String MSG3;
    
    public TGDBScene() {
        this.init();
    }
    
    public void init() {
        this.I = 1;
        this.tgdbMonsters = new TGDBMonster[4];
        (this.tgdbMonsters[0] = new TGDBMonster()).setI(1);
        this.tgdbMonsters[0].setHp(5);
        this.tgdbMonsters[0].setBean(this.getMonster((Robots)GameServer.getAllRobot().get("801"), 0));
        (this.tgdbMonsters[1] = new TGDBMonster()).setI(2);
        this.tgdbMonsters[1].setHp(5);
        this.tgdbMonsters[1].setBean(this.getMonster((Robots)GameServer.getAllRobot().get("802"), 1));
        (this.tgdbMonsters[2] = new TGDBMonster()).setI(3);
        this.tgdbMonsters[2].setHp(5);
        this.tgdbMonsters[2].setBean(this.getMonster((Robots)GameServer.getAllRobot().get("803"), 2));
        (this.tgdbMonsters[3] = new TGDBMonster()).setI(4);
        this.tgdbMonsters[3].setHp(5);
        this.tgdbMonsters[3].setBean(this.getMonster((Robots)GameServer.getAllRobot().get("804"), 3));
        for (int i = 0; i < this.tgdbMonsters.length; ++i) {
            MonsterUtil.addEMonster(this.tgdbMonsters[i].getBean());
        }
        NChatBean bean = new NChatBean();
        bean.setId(5);
        bean.setMessage("#R天帝宝库#W活动开启，请各位勇士前往东海渔村找天赎星君进入！");
        String msg = Agreement.getAgreement().chatAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
        SendMessage.sendMessageToAllRoles(msg);
    }
    
    public MapMonsterBean getMonster(Robots robots, int i) {
        MapMonsterBean bean = new MapMonsterBean();
        bean.setI(Integer.valueOf(MonsterUtil.getIncreasesum()));
        bean.setRobotid(Integer.valueOf(robots.getRobotID()));
        bean.setRobotname(robots.getRobotname());
        bean.setRobotskin(robots.getRobotskin());
        bean.setSX(1002);
        bean.setMap(new Long((long)(i + 3325)));
        bean.setX(Integer.valueOf(0));
        bean.setY(0L);
        return bean;
    }
    
    public void open() {
        MonsterUtil.refreshMonsters(this.getBoos(), null, null, null);
        if (this.I == 5) {
            for (int i = 0; i < this.tgdbMonsters.length; ++i) {
                MonsterUtil.removeMonster2(this.tgdbMonsters[i].getBean());
            }
        }
    }
    
    public Boos getBoos() {
        String id = this.I + 138 + "";
        Boos boos = (Boos)GameServer.boosesMap.get(id);
        if (boos == null) {
            boos = (Boos)MonsterUtil.booses.get(0);
        }
        return boos;
    }
    
    public String isBattle(MapMonsterBean bean) {
        int i = 0;
        while (i < this.tgdbMonsters.length) {
            TGDBMonster tgdbMonster = this.tgdbMonsters[i];
            if (bean.getI() == tgdbMonster.getBean().getI()) {
                if (tgdbMonster.getHp() <= 0) {
                    return TGDBScene.MSG2;
                }
                return null;
            }
            else {
                ++i;
            }
        }
        return TGDBScene.MSG3;
    }
    
    @Override
    public String UPMonster(BattleData battleData, String[] teams, int type, StringBuffer buffer) {
        MapMonsterBean bean = battleData.getMonsterBean();
        for (int i = 0; i < this.tgdbMonsters.length; ++i) {
            TGDBMonster tgdbMonster = this.tgdbMonsters[i];
            if (bean.getI() == tgdbMonster.getBean().getI()) {
                if (type == 2) {
                    if (tgdbMonster.getHp() > 0) {
                        tgdbMonster.setHp(tgdbMonster.getHp() - 1);
                        if (tgdbMonster.getHp() <= 0) {
                            ++this.I;
                            this.open();
                        }
                    }
                }
                else if (type != 1) {
                    if (type == 0) {}
                }
            }
        }
        return null;
    }
    
    public int isC(int c) {
        if (c == this.I) {
            return 1;
        }
        if (c < this.I) {
            return 0;
        }
        return 2;
    }
    
    public TGDBMonster[] getTgdbMonsters() {
        return this.tgdbMonsters;
    }
    
    @Override
    public void getAward(ChannelHandlerContext ctx, LoginResult loginResult) {
    }
    
    @Override
    public Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer buffer, Map<Integer, MonsterMoveBase> moveMap, long mapId) {
        return moveMap;
    }
    
    @Override
    public boolean isEnd() {
        return this.I != 5;
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
    
    static {
        TGDBScene.MSG1 = Agreement.getAgreement().PromptAgreement("挑战队列已经满了,请等待其他玩家挑战结束");
        TGDBScene.MSG2 = Agreement.getAgreement().PromptAgreement("我已经战败了,去找其他层的守护者吧");
        TGDBScene.MSG3 = Agreement.getAgreement().PromptAgreement("我在这里干什么???");
    }
}
