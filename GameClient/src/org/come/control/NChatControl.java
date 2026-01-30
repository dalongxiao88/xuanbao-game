package org.come.control;

import com.tool.image.ManimgAttribute;
import org.come.Jpanel.FrameMessageChangeJpanel;
import com.tool.image.ImageMixDeal;
import org.come.Jpanel.GameJpanel;
import org.come.until.GsonUtil;
import org.come.bean.NChatBean;
import org.come.action.FromServerAction;

public class NChatControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        NChatBean nChatBean = (NChatBean)GsonUtil.getGsonUtil().getgson().fromJson(mes, NChatBean.class);
        if (nChatBean.getId() != 0 && nChatBean.getId() != 1) {
            if (nChatBean.getId() == 8 || nChatBean.getId() == 9) {
                GameJpanel.getGameJpanel().addBox(nChatBean.getMessage(), nChatBean.getId(), nChatBean.getSkin());
                return;
            }
            if (nChatBean.getId() == 10) {
                GameJpanel.getGameJpanel().addBoos(nChatBean.getMessage(), nChatBean.getId(), nChatBean.getSkin());
                return;
            }
        }
        else {
            ManimgAttribute attribute = ImageMixDeal.huoquLogin(nChatBean.getRole());
            if (attribute != null) {
                attribute.Dialogue(nChatBean.getMessage());
            }
        }
        FrameMessageChangeJpanel.addtext(nChatBean.getId(), nChatBean.getMessage(), nChatBean.getRoleId(), nChatBean.getRole(), nChatBean.getGoodNum(), nChatBean.getGoodNumType());
    }
}
