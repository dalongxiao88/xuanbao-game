package org.come.npc;

import org.come.until.UserData;
import org.come.good.Medicine;
import org.come.Frame.ZhuFrame;
import com.tool.image.ImageMixDeal;
import org.come.action.NpcMenuAction;

public class Sleep implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("江湖救急啊，请救救我和我的宝宝吧")) {
            this.HelpOthers();
        }
        else if (type.equals("我要住店(扣除2000银两)")) {
            this.Hotel();
        }
    }
    
    public void HelpOthers() {
        if ((int)ImageMixDeal.userimg.getRoleShow().getGrade() > 30) {
            ZhuFrame.getZhuJpanel().addPrompt2("不属救助范围内");
        }
        else {
            Medicine.Red(0, 100);
            Medicine.Blue(0, 100);
        }
    }
    
    public void Hotel() {
        if (UserData.uptael(2000L)) {
            Medicine.Red(0, 100);
            Medicine.Blue(0, 100);
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("2000块都没有");
        }
    }
}
