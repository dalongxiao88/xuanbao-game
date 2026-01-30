package come.tool.Scene.TAST;

public class MrThread implements Runnable
{
    private MrScene btyScene;
    
    public MrThread(MrScene btyScene) {
        this.btyScene = btyScene;
    }
    
    @Override
    public void run() {
        while (this.btyScene.isEnd()) {
            if (this.btyScene.time % 180L == 0L) {
                this.btyScene.open();
            }
            try {
                this.btyScene.move();
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
            MrScene btyScene = this.btyScene;
            ++btyScene.time;
        }
    }
}
