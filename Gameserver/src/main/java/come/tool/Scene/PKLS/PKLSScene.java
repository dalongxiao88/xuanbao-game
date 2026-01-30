package come.tool.Scene.PKLS;

import org.come.task.MapMonsterBean;
import come.tool.Battle.BattleEnd;
import org.come.task.MonsterMoveBase;
import java.util.Map;
import come.tool.Battle.BattleData;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import java.math.BigDecimal;
import org.come.bean.LoginResult;
import org.come.until.GsonUtil;
import java.util.ArrayList;
import org.come.until.ReadTxtUtil;
import org.come.tool.ReadExelTool;
import java.util.List;
import come.tool.Scene.Scene;

public class PKLSScene implements Scene
{
    private List<LSTeam> LSTeams;
    
    public PKLSScene() {
        this.LSTeams = null;
        String text = ReadTxtUtil.readFile1(ReadExelTool.class.getResource("/").getPath() + "lsteam.txt");
        if (text == null || text.equals("")) {
            this.LSTeams = new ArrayList<>();
        }
        else {
            lsteamBean lsteamBean = (lsteamBean)GsonUtil.getGsonUtil().getgson().fromJson(text, lsteamBean.class);
            this.LSTeams = lsteamBean.getLSTeams();
            if (this.LSTeams == null) {
                this.LSTeams = new ArrayList<>();
            }
        }
    }
    
    public synchronized String addEnroll(LoginResult loginResult) {
        String[] teams = loginResult.getTeam().split("\\|");
        if (!teams[0].equals(loginResult.getRolename())) {
            return "你不是队长";
        }
        if (teams.length != 5) {
            return "必须队伍满5人才能报名";
        }
        BigDecimal[] roleids = new BigDecimal[teams.length];
        for (int i = 0; i < teams.length; ++i) {
            LoginResult login = null;
            if (i == 0) {
                login = loginResult;
            }
            else {
                ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(teams[i]);
                if (ctx != null) {
                    login = (LoginResult)GameServer.getAllLoginRole().get(ctx);
                }
            }
            if (login == null) {
                return teams[i] + "处于异常状态";
            }
            if ((int)login.getGrade() < 439) {
                return teams[i] + "未满3转160";
            }
            if (this.contains(login.getRole_id())) {
                return teams[i] + "已经报名过了";
            }
            roleids[i] = login.getRole_id();
        }
        this.LSTeams.add(new LSTeam(roleids));
        return "报名成功";
    }
    
    public boolean contains(BigDecimal id) {
        for (int i = this.LSTeams.size() - 1; i >= 0; --i) {
            if (((LSTeam)this.LSTeams.get(i)).contains(id)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String UPMonster(BattleData battleData, String[] teams, int type, StringBuffer buffer) {
        return null;
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
        return false;
    }
    
    @Override
    public boolean isTime(long time) {
        return false;
    }
    
    public List<LSTeam> getLSTeams() {
        return this.LSTeams;
    }
    
    public void setLSTeams(List<LSTeam> lSTeams) {
        this.LSTeams = lSTeams;
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
