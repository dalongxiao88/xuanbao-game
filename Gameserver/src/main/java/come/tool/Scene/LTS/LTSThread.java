package come.tool.Scene.LTS;

import come.tool.PK.PkMatch;

public class LTSThread implements Runnable
{
    private LTSScene ltsScene;
    
    public LTSThread(LTSScene ltsScene) {
        this.ltsScene = ltsScene;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 360; ++i) {
            this.ltsScene.OVERTIME(System.currentTimeMillis());
            try {
                Thread.sleep(20000L);
            }
            catch (Exception ex) {}
        }
        this.ltsScene.OVERTIME(System.currentTimeMillis() + PkMatch.OTHERTIME * 2L);
        this.ltsScene.setI(2);
    }
}
