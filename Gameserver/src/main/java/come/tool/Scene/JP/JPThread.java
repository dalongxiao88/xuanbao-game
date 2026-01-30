package come.tool.Scene.JP;

public class JPThread implements Runnable
{
    private JPScene jpScene;
    
    public JPThread(JPScene jpScene) {
        this.jpScene = jpScene;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                this.jpScene.end();
                this.jpScene.open();
                Thread.sleep(10000L);
                continue;
            }
            catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
