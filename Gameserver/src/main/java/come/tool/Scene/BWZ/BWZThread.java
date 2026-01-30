package come.tool.Scene.BWZ;

public class BWZThread implements Runnable
{
    private BWZScene btyScene;
    
    public BWZThread(BWZScene btyScene) {
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
            BWZScene btyScene = this.btyScene;
            ++btyScene.time;
        }
    }
}
