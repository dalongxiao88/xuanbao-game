package come.tool.Scene;

import org.come.task.MonsterMoveBase;
import java.util.Map;
import io.netty.channel.ChannelHandlerContext;
import org.come.task.MapMonsterBean;
import org.come.bean.LoginResult;
import come.tool.Battle.BattleEnd;
import come.tool.Battle.BattleData;

public interface Scene
{
    String UPMonster(BattleData p0, String[] p1, int p2, StringBuffer p3);
    
    int battleEnd(BattleEnd p0, LoginResult p1, MapMonsterBean p2, int p3);
    
    void getAward(ChannelHandlerContext p0, LoginResult p1);
    
    Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer p0, Map<Integer, MonsterMoveBase> p1, long p2);
    
    boolean isEnd();
    
    boolean isTime(long p0);
    
    String getSceneMsg(LoginResult p0, long p1, long p2);
}
