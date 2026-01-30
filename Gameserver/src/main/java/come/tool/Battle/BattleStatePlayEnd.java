package come.tool.Battle;

import java.util.Iterator;
import java.util.List;
import come.tool.Scene.ZZS.ZZSScene;
import org.come.handler.SendMessage;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import come.tool.Scene.SceneUtil;
import org.come.bean.LoginResult;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
/**3战斗播放结束同步*/
public class BattleStatePlayEnd implements BattleState
{
    @Override
    public boolean handle(BattleData battleData) {
        int type = battleData.getBattlefield().endFighting(1);
        if (type != -1) {
            battleData.setWinCamp(type);
            return true;
        }
        if (battleData.getBattleType() == 31) {
            ChannelHandlerContext ctx = GameServer.getRoleNameMap().get(battleData.getTeam1()[0]);
            LoginResult log1 = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            ctx = GameServer.getRoleNameMap().get(battleData.getTeam2()[0]);
            LoginResult log2 = (ctx != null) ? ((LoginResult)GameServer.getAllLoginRole().get(ctx)) : null;
            if (log1 != null || log2 != null) {
                ZZSScene zzsScene = null;
                if (log1 != null) {
                    zzsScene = SceneUtil.getZZS(log1);
                }
                else if (log2 != null) {
                    zzsScene = SceneUtil.getZZS(log2);
                }
                if (zzsScene != null && zzsScene.getI() != 2) {
                    return true;
                }
            }
        }
        // 获得战斗人
        List<String> fightList = battleData.getParticipantlist();
        battleData.setRound(battleData.getRound() + 1);
        battleData.changeState(1);
        /**回合结束 广播进入决策*/
        String msg = Agreement.FightingRoundEndAgreement(battleData.getBattleNumber() + "");
        String msg2 = Agreement.FightingRoundInfoAgreement(GsonUtil.getGsonUtil().getgson().toJson(battleData.getFightingForesee()));
        for (String roleName : fightList) {
            SendMessage.sendMessageByRoleName(roleName, msg);
            SendMessage.sendMessageByRoleName(roleName, msg2);
        }
        battleData.getBattlefield().lingbao();
        return false;
    }
}
