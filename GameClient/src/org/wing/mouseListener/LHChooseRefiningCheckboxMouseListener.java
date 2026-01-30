package org.wing.mouseListener;

import org.come.until.CutButtonImage;
import javax.swing.Icon;
import java.awt.event.MouseEvent;
import org.wing.panel.LHMainPanel;
import java.awt.event.MouseAdapter;

public class LHChooseRefiningCheckboxMouseListener extends MouseAdapter
{
    private int p;
    private LHMainPanel LHMainPanel;
    
    public LHChooseRefiningCheckboxMouseListener(int p, LHMainPanel LHMainPanel) {
        this.p = p;
        this.LHMainPanel = LHMainPanel;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int num = 0;
        for (int refiningMoney = 0; refiningMoney < this.LHMainPanel.getWingRefiningType().length; ++refiningMoney) {
            if (this.LHMainPanel.getWingRefiningType()[refiningMoney]) {
                ++num;
            }
        }
        if (this.LHMainPanel.getWingRefiningType()[this.p]) {
            this.LHMainPanel.getWingRefining()[this.p].setIcon((Icon)null);
            --num;
        }
        else {
            if (num >= 3) {
                return;
            }
            this.LHMainPanel.getWingRefining()[this.p].setIcon(CutButtonImage.getImage("img/xy2uiimg/showjadesuit.png", 15, 15));
            ++num;
        }
        int refiningMoney = this.LHMainPanel.getRefiningMoney(num);
        this.LHMainPanel.getRefiningTitle().setText("当前锁定花费仙玉为：" + refiningMoney + "仙玉");
        this.LHMainPanel.getWingRefiningType()[this.p] = !this.LHMainPanel.getWingRefiningType()[this.p];
    }
}
