package com.tool.btn;

import org.come.until.UserData;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.ZhuFrame;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import org.come.Jpanel.TransferJpanel;
import org.come.Jpanel.UpgradeJpanel;
import org.come.Jpanel.WashJpanel;
import org.come.Jpanel.SynthesisJpanel;
import org.come.until.AccessSuitMsgUntil;
import java.awt.event.MouseEvent;

public class SwitchPageBtn extends MoBanBtn
{
    private int caozuo;
    
    public SwitchPageBtn(String iconpath, int type, int caozuo) {
        super(iconpath, type);
        this.caozuo = caozuo;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo == 1) {
            if (AccessSuitMsgUntil.accessIdlEqu(1) == null) {
                return;
            }
            if (SynthesisJpanel.page - 1 >= 0) {
                --SynthesisJpanel.page;
            }
        }
        else if (this.caozuo == 2) {
            if (AccessSuitMsgUntil.accessIdlEqu(1) == null) {
                return;
            }
            if (AccessSuitMsgUntil.accessIdlEqu(1).size() > (SynthesisJpanel.page + 1) * 18) {
                ++SynthesisJpanel.page;
            }
        }
        else if (this.caozuo == 3) {
            if (AccessSuitMsgUntil.accessIdlEqu(2) == null) {
                return;
            }
            if (WashJpanel.page - 1 >= 0) {
                --WashJpanel.page;
            }
        }
        else if (this.caozuo == 4) {
            if (AccessSuitMsgUntil.accessIdlEqu(2) == null) {
                return;
            }
            if (AccessSuitMsgUntil.accessIdlEqu(2).size() > (WashJpanel.page + 1) * 21) {
                ++WashJpanel.page;
            }
        }
        else if (this.caozuo == 5) {
            if (AccessSuitMsgUntil.accessIdlEqu(2) == null) {
                return;
            }
            if (UpgradeJpanel.page - 1 >= 0) {
                --UpgradeJpanel.page;
            }
        }
        else if (this.caozuo == 6) {
            if (AccessSuitMsgUntil.accessIdlEqu(2) == null) {
                return;
            }
            if (AccessSuitMsgUntil.accessIdlEqu(2).size() > (UpgradeJpanel.page + 1) * 21) {
                ++UpgradeJpanel.page;
            }
        }
        else if (this.caozuo == 7) {
            if (AccessSuitMsgUntil.accessIdlEqu(2) == null) {
                return;
            }
            if (TransferJpanel.page - 1 >= 0) {
                --TransferJpanel.page;
            }
        }
        else if (this.caozuo == 8) {
            if (AccessSuitMsgUntil.accessIdlEqu(2) == null) {
                return;
            }
            if (AccessSuitMsgUntil.accessIdlEqu(2).size() > (TransferJpanel.page + 1) * 9) {
                ++TransferJpanel.page;
            }
        }
        else if (this.caozuo == 9) {
            if (AccessSuitMsgUntil.accessIdlEqu(1) == null) {
                return;
            }
            if (TransferJpanel.page2 - 1 >= 0) {
                --TransferJpanel.page2;
            }
        }
        else if (this.caozuo == 10) {
            if (AccessSuitMsgUntil.accessIdlEqu(1) == null) {
                return;
            }
            if (AccessSuitMsgUntil.accessIdlEqu(1).size() > (TransferJpanel.page2 + 1) * 9) {
                ++TransferJpanel.page2;
            }
        }
        else if (this.caozuo == 11) {
            if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal(100000000)) < 0) {
                ZhuFrame.getZhuJpanel().addPrompt("需要1E金币才能增加上限..");
                return;
            }
            int num = RoleData.getRoleData().getPackRecord().getSuitNum() + 1;
            RoleData.getRoleData().getPackRecord().setSuitNum(num);
            GoodsListFromServerUntil.sendPackRecord(3, String.valueOf(num));
            UserData.uptael(new BigDecimal(100000000).longValue());
        }
    }
}
