package org.come.control;

import org.come.until.FormsManagement;
import org.come.Frame.PalacePKJframe;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import org.come.action.NpcMenuAction;

public class SurfaceAssemblyControl implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("我要下挑战书")) {
            int grade = (int)ImageMixDeal.userimg.getRoleShow().getGrade() - 279;
            if (grade < 50) {
                ZhuFrame.getZhuJpanel().addPrompt2("您当前的等级不能下挑战书！！");
                return;
            }
            PalacePKJframe.getPalacePKJframe().getPkJpanel().changeView(0);
            FormsManagement.showForm(66);
        }
        else if (type.equals("我要下战书")) {
            int grade = (int)ImageMixDeal.userimg.getRoleShow().getGrade();
            if (grade < 399) {
                ZhuFrame.getZhuJpanel().addPrompt2("您当前的等级不能下战书！！");
                return;
            }
            PalacePKJframe.getPalacePKJframe().getPkJpanel().changeView(1);
            FormsManagement.showForm(66);
        }
    }
}
