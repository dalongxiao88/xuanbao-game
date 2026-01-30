package come.tool.BangBattle;

import org.come.server.GolemServer;

public class BangThread extends Thread
{
    private BangBattlePool pool;
    
    public BangThread(BangBattlePool pool) {
        this.pool = pool;
    }
    
    @Override
    public void run() {
        while (true) {
            if (this.pool.BangFights.size()==0) {
                GolemServer.isFighting=false;//机器人帮战结束
            }
            for (int i = this.pool.BangFights.size() - 1; i >= 0; --i) {
                BangFight fight = (BangFight)this.pool.BangFights.get(i);
                try {
                    fight.process();
                    if (fight.isEnd()) {
                        this.pool.BangFights.remove(fight);
                        this.pool.WinOrLose(fight);
                    }
                }
                catch (Exception e) {
                    fight.isVictory();
                    this.pool.BangFights.remove(fight);
                    this.pool.WinOrLose(fight);
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1200L);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
