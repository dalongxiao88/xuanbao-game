package come.tool.Scene;

import org.come.model.Skill;
import come.tool.Scene.DNTG.DNTGScene;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class SceneAction implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
        if (roleInfo == null) {
            return;
        }
        String[] vs = message.split("\\|");
        int SceneId = Integer.parseInt(vs[0]);
        Scene scene = SceneUtil.getScene(SceneId);
        if (scene != null && SceneId == 1011) {
            DNTGScene dntgScene = (DNTGScene)scene;
            if (vs[1].startsWith("L")) {
                Skill skill = GameServer.getSkill(vs[1].substring(1));
                if (skill != null && skill.getSkillid() >= 10001 && skill.getSkillid() <= 10008) {
                    dntgScene.learnSLJC(roleInfo, skill);
                }
            }
        }
    }
}
