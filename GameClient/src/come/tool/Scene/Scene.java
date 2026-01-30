package come.tool.Scene;

import java.awt.Graphics;

public interface Scene
{
    public static final int DNTGID = 1011;
    
    int getSceneId();
    
    void draw(Graphics p0, long p1);
    
    boolean Monitor(int p0, int p1);
    
    void UPData(String[] p0);
    
    void end();
}
