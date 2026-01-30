package com.tool.btn;

import java.util.List;
import org.come.Jpanel.TestPetJpanel;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.PetPrderJpanel;

public class PetOrderBtn extends MoBanBtn
{
    private PetPrderJpanel jpanel;
    private int p;
    
    public PetOrderBtn(String iconpath, int type, int p, PetPrderJpanel jpanel) {
        super(iconpath, type);
        this.p = p;
        this.jpanel = jpanel;
    }
    
    public PetOrderBtn(String iconpath, int type, int p, String text, PetPrderJpanel jpanel) {
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
            index = data.getPetOrder().size() - 1;
        }
        List<String> list = data.updateOrderPet(currentIndex, index);
        if (list != null) {
            this.jpanel.init(list, index);
        }
        TestPetJpanel.showStar();
    }
}
