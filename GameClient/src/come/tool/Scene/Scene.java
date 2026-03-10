package come.tool.Scene;

import java.awt.Graphics;

/**
 * 客户端场景接口。
 *
 * 当前保留原始大小写混杂的方法名以兼容既有实现，
 * 同时补充语义化默认方法，便于后续逐步清理反编译痕迹。
 */
public interface Scene
{
    public static final int DNTGID = 1011;
    public static final int SCENE_ID_DNTG = DNTGID;
    
    int getSceneId();
    
    void draw(Graphics graphics, long frameTime);
    
    boolean Monitor(int mouseX, int mouseY);
    
    void UPData(String[] sceneData);
    
    void end();

    /** 语义化别名：绘制场景。 */
    default void drawScene(Graphics graphics, long frameTime) {
        draw(graphics, frameTime);
    }

    /** 语义化别名：处理场景中的鼠标命中检测。 */
    default boolean monitor(int mouseX, int mouseY) {
        return Monitor(mouseX, mouseY);
    }

    /** 语义化别名：更新场景数据。 */
    default void updateData(String[] sceneData) {
        UPData(sceneData);
    }
}
