package org.come.control;

import com.tool.image.ManimgAttribute;
import com.tool.image.ImageMixDeal;
import org.come.action.FromServerAction;

public class RoleLevelUpControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        String[] v = mes.split("\\|");
        ManimgAttribute attribute = ImageMixDeal.huoquLogin(v[0]);
        if (attribute != null) {
            if (attribute.getLeixing() != 0) {
                attribute.getRoleShow().setGrade(Integer.valueOf(Integer.parseInt(v[1])));
            }
            ManimgAttribute.addEffects("升级", attribute.getRoleShow().getX(), attribute.getRoleShow().getY());
        }
    }
}
