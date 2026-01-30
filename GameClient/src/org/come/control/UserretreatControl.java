package org.come.control;

import com.tool.image.ImageMixDeal;
import org.come.action.FromServerAction;

public class UserretreatControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        String[] vs = mes.split("\\|");
        for (int i = 0; i < vs.length; ++i) {
            ImageMixDeal.Playerimgmap.remove(vs[i]);
        }
    }
}
