package come.tool.Scene.BTY;

public class BTYThread implements Runnable
{
    private BTYScene btyScene;
    
    public BTYThread(BTYScene btyScene) {
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
            BTYScene btyScene = this.btyScene;
            ++btyScene.time;
        }
    }
}
