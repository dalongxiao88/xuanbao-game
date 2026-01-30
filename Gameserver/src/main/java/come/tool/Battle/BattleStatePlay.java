package come.tool.Battle;
/**2等待战斗播放*/
public class BattleStatePlay implements BattleState
{
    @Override
    public boolean handle(BattleData battleData) {
        //播放的指令数
        long Pass = System.currentTimeMillis() - battleData.getBattletime();
        if (Pass < battleData.getPlayTime()) {//最短播放时间
            return false;
        }
        if (Pass > battleData.getPlayTime() * 6L) {//最长播放时间
            battleData.changeState(3);
            return false;
        }
        if (battleData.getCalculator() != 0) {//已有播放结束
            int size = battleData.getParticipantlist().size();
            if (battleData.getCalculator() >= size) {
                battleData.changeState(3);//全部播放结束
            }
            else {
                Pass += battleData.getBattletime() - battleData.getPlayEndTime();//离第一个播放结束时间差
                if (Pass > 3000L) {
                    battleData.changeState(3);
                }
            }
        }
        return false;
    }
}
