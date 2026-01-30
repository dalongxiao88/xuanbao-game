package org.come.control;

import com.tool.image.ImageMixDeal;
import org.come.action.FromServerAction;

public class SceneControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        ImageMixDeal.upScene(mes);
    }
}
