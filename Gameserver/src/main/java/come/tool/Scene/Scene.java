package come.tool.Scene;

import org.come.task.MonsterMoveBase;
import java.util.Map;
import io.netty.channel.ChannelHandlerContext;
import org.come.task.MapMonsterBean;
import org.come.bean.LoginResult;
import come.tool.Battle.BattleEnd;
import come.tool.Battle.BattleData;

/**
 * 服务端场景副本接口。
 *
 * 历史接口方法大量使用反编译遗留参数名和大小写混杂的方法名。
 * 当前阶段通过增加语义化默认方法和参数说明，逐步把调用方引导到更可读的名称上，
 * 同时保持旧接口签名不变，避免影响现有实现类。
 */
public interface Scene
{
    String UPMonster(BattleData battleData, String[] sceneMessages, int state, StringBuffer sceneBuffer);
    
    int battleEnd(BattleEnd battleEnd, LoginResult loginResult, MapMonsterBean mapMonsterBean, int battleType);
    
    void getAward(ChannelHandlerContext channelHandlerContext, LoginResult loginResult);
    
    Map<Integer, MonsterMoveBase> getMapMonster(StringBuffer rawSceneData, Map<Integer, MonsterMoveBase> currentMonsters, long currentTime);
    
    boolean isEnd();
    
    boolean isTime(long currentTime);
    
    String getSceneMsg(LoginResult loginResult, long sceneId, long mapId);

    /** 语义化别名：更新场景中的怪物或战斗信息。 */
    default String updateMonster(BattleData battleData, String[] sceneMessages, int state, StringBuffer sceneBuffer) {
        return UPMonster(battleData, sceneMessages, state, sceneBuffer);
    }

    /** 语义化别名：发放场景奖励。 */
    default void awardPlayer(ChannelHandlerContext channelHandlerContext, LoginResult loginResult) {
        getAward(channelHandlerContext, loginResult);
    }

    /** 语义化别名：构建场景怪物映射。 */
    default Map<Integer, MonsterMoveBase> buildMapMonster(StringBuffer rawSceneData, Map<Integer, MonsterMoveBase> currentMonsters, long currentTime) {
        return getMapMonster(rawSceneData, currentMonsters, currentTime);
    }

    /** 语义化别名：判断场景时间条件。 */
    default boolean isSceneTime(long currentTime) {
        return isTime(currentTime);
    }

    /** 语义化别名：生成场景同步消息。 */
    default String buildSceneMessage(LoginResult loginResult, long sceneId, long mapId) {
        return getSceneMsg(loginResult, sceneId, mapId);
    }
}
