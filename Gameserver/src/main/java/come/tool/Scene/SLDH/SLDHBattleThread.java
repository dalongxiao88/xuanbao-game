package come.tool.Scene.SLDH;

public class SLDHBattleThread implements Runnable
{
    private SLDHScene sldhScene;
    
    public SLDHBattleThread(SLDHScene sldhScene) {
        this.sldhScene = sldhScene;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(SLDHScene.JG / 5L * 3L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.sldhScene.PKOpen();
    }
}
