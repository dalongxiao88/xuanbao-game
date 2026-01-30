package come.tool.Good;

import come.tool.Role.RoleData;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import come.tool.Role.RolePool;
import org.come.task.MapMonsterBean;
import java.math.BigDecimal;

public class FYModel
{
    private BigDecimal roleId;
    private String value;
    
    public FYModel(BigDecimal roleId, String value) {
        this.roleId = roleId;
        this.value = value;
    }
    
    public void die(MapMonsterBean monsterBean, String[] teams) {
        RoleData roleData = RolePool.getRoleData(this.roleId);
        ChannelHandlerContext ctx = (roleData != null) ? ((ChannelHandlerContext)GameServer.getRoleNameMap().get(roleData.getLoginResult().getRolename())) : null;
        LoginResult loginResult = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
        if (loginResult != null) {
            StringBuffer buffer = new StringBuffer();
            if (teams != null && teams.length != 0) {
                buffer.append("#R玩家#Y");
                buffer.append(teams[0]);
            }
            else {
                buffer.append("#R有人#Y");
            }
            buffer.append("击杀了你放出来的");
            buffer.append(monsterBean.getRobotname());
            DropUtil.getDrop3(loginResult, this.value, "放妖礼包", 22, 1.0, null, null, buffer.toString());
        }
    }
}
