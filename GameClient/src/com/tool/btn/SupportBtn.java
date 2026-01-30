package com.tool.btn;

import java.util.List;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.SupportListJpanel;

public class SupportBtn extends MoBanBtn
{
    private SupportListJpanel jpanel;
    private int p;
    
    public SupportBtn(String iconpath, int type, int p, SupportListJpanel jpanel) {
        super(iconpath, type);
        this.p = p;
        this.jpanel = jpanel;
    }
    
    public SupportBtn(String iconpath, int type, int p, String text, SupportListJpanel jpanel) {
        super(iconpath, type, UIUtils.COLOR_BTNTEXT);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.p = p;
        this.jpanel = jpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        int currentIndex = this.jpanel.getListpet().getSelectedIndex();
        if (currentIndex == -1) {
            return;
        }
        RoleData data = RoleData.getRoleData();
        int index = -1;
        if (this.p == 0) {
            index = currentIndex - 1;
        }
        else if (this.p == 1) {
            index = currentIndex + 1;
        }
        else if (this.p == 2) {
            index = 0;
        }
        else {
            index = data.getHelpBb().size() - 1;
        }
        List<String> list = data.CHelpBb(currentIndex, index);
        if (list != null) {
            this.jpanel.init(list, index);
        }
    }
}
