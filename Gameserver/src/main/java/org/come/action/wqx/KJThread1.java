package org.come.action.wqx;

public class KJThread1 implements Runnable
{
    private Boolean b;
    private Long time;
    private WenQuXingScene wenQuXingScene;
    private Long endTiime;
    
    public KJThread1(WenQuXingScene wenQuXingScene, Long time) {
        this.b = Boolean.valueOf(true);
        this.endTiime = Long.valueOf(60000L);
        this.time = time;
        this.wenQuXingScene = wenQuXingScene;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000L);
                long l = System.currentTimeMillis() - (long)this.time;
                if (l <= (long)this.endTiime) {
                    continue;
                }
                this.wenQuXingScene.ClearingQuestions();
                return;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
