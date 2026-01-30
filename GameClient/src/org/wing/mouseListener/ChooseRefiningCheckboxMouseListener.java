package org.wing.mouseListener;

import org.come.until.CutButtonImage;
import javax.swing.Icon;
import java.awt.event.MouseEvent;
import org.wing.panel.WingMainPanel;
import java.awt.event.MouseAdapter;

public class ChooseRefiningCheckboxMouseListener extends MouseAdapter
{
    private int p;
    private WingMainPanel wingMainPanel;
    
    public ChooseRefiningCheckboxMouseListener(int p, WingMainPanel wingMainPanel) {
        this.p = p;
        this.wingMainPanel = wingMainPanel;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        int num = 0;
        for (int refiningMoney = 0; refiningMoney < this.wingMainPanel.getWingRefiningType().length; ++refiningMoney) {
            if (this.wingMainPanel.getWingRefiningType()[refiningMoney]) {
                ++num;
            }
        }
        if (this.wingMainPanel.getWingRefiningType()[this.p]) {
            this.wingMainPanel.getWingRefining()[this.p].setIcon((Icon)null);
            --num;
        }
        else {
            if (num >= 3) {
                return;
            }
            this.wingMainPanel.getWingRefining()[this.p].setIcon(CutButtonImage.getImage("img/xy2uiimg/showjadesuit.png", 15, 15));
            ++num;
        }
        int refiningMoney = this.wingMainPanel.getRefiningMoney(num);
        this.wingMainPanel.getRefiningTitle().setText("当前锁定花费仙玉为：" + refiningMoney + "仙玉");
        this.wingMainPanel.getWingRefiningType()[this.p] = !this.wingMainPanel.getWingRefiningType()[this.p];
    }
}
