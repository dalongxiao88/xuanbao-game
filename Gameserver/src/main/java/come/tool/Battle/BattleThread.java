package come.tool.Battle;

import org.come.tool.WriteOut;
import org.come.handler.MainServerHandler;
import java.util.ArrayList;
import java.util.List;

public class BattleThread implements Runnable
{
    /**战斗预知超时*/
    public static final int OVERTIME_PREVIE = 5000;
    /**战斗决策超时*/
    public static final int OVERTIME_POLICY = 63000;
    /**单条指令最短播放时*/
    public static final int OVERTIME_PLAY_MIN = 400;
    /**播放结束同步超时*/
    public static final int OVERTIME_PLAY_END = 3000;
    private List<Integer> Numbers;
    
    public BattleThread(int id) {
        (this.Numbers = new ArrayList<>()).add(id);
    }
    
    @Override
    public void run() {
        long end;
        long start = end = System.currentTimeMillis();
        do {
            start = System.currentTimeMillis();
            for (int i = this.Numbers.size() - 1; i >= 0; --i) {
                int id = -1;
                try {
                    id = (int)this.Numbers.get(i);
                    BattleData battleData = (BattleData)BattleThreadPool.BattleDatas.get(id);
                    if (battleData == null) {
                        this.Numbers.remove(i);
                    }
                    else if (BattleStateType.getBattleStateById(battleData.getBattleState()).handle(battleData)) {
                        BattleThreadPool.removeBattleData(battleData);
                        this.Numbers.remove(i);
                    }
                }
                catch (Exception e) {
                    try {
                        e.printStackTrace();
                        WriteOut.addtxt("战斗报错:" + MainServerHandler.getErrorMessage(e), 9999L);
                        BattleData battleData2 = (BattleData)BattleThreadPool.BattleDatas.get(id);
                        if (battleData2 != null) {
                            battleData2.setWinCamp(-2);
                            BattleThreadPool.removeBattleData(battleData2);
                        }
                    }
                    catch (Exception ex) {}
                    this.Numbers.remove(i);
                    e.printStackTrace();
                }
            }
            end = System.currentTimeMillis();
            long Pass = 600L - (end - start);
            try {
                if (Pass > 0L) {
                    Thread.sleep(Pass);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } while (this.Numbers.size() != 0 || !BattleThreadPool.removeBattleThread(this));
    }
    
    public List<Integer> getNumbers() {
        return this.Numbers;
    }
    
    public void setNumbers(List<Integer> numbers) {
        this.Numbers = numbers;
    }
}
