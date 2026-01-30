package org.come.action.wqx;

import java.util.Calendar;

public class KJThread implements Runnable
{
    public Boolean b;
    private WenQuXingScene wenQuXingScene;
    
    public KJThread(WenQuXingScene wenQuXingScene) {
        this.b = Boolean.valueOf(false);
        this.wenQuXingScene = wenQuXingScene;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                if ((boolean)this.b) {
                    Thread.sleep(7200000L);
                    this.b = Boolean.valueOf(false);
                }
                Calendar calendar = Calendar.getInstance();
                int minute = calendar.get(11);
                if (minute % 2 == 0 && minute > 10) {
                    this.wenQuXingScene.sendTiMU();
                    Thread.sleep(70000L);
                }
                else {
                    Thread.sleep(5000L);
                }
                continue;
            }
            catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
