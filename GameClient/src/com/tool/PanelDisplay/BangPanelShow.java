package com.tool.PanelDisplay;

import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.FormsManagement;
import com.tool.image.ImageMixDeal;

public class BangPanelShow
{
    public static void Show() {
        if (ImageMixDeal.userimg.getRoleShow().getGang_id() != null && ImageMixDeal.userimg.getRoleShow().getGang_id().intValue() != 0) {
            if (FormsManagement.getframe(5).isVisible()) {
                FormsManagement.HideForm(5);
            }
            else {
                String senmes = Agreement.getAgreement().IntogangAgreement(ImageMixDeal.userimg.getRoleShow().getGang_id().toString());
                SendMessageUntil.toServer(senmes);
            }
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("你没有帮派!");
        }
    }
}
