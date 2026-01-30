package org.come.mouslisten;

import org.come.until.CutButtonImage;
import javax.swing.Icon;
import java.awt.event.MouseEvent;
import org.come.Jpanel.PalacePKJpanel;
import java.awt.event.MouseAdapter;

public class PkPutLabMouseListener extends MouseAdapter
{
    private int type;
    private PalacePKJpanel palacePKJpanel;
    
    public PkPutLabMouseListener(int type, PalacePKJpanel palacePKJpanel) {
        this.palacePKJpanel = palacePKJpanel;
        this.type = type;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.palacePKJpanel.isPutIntoBool() && this.palacePKJpanel.getWinnerType() != 2 && this.palacePKJpanel.getWinnerType() != 3) {
            this.palacePKJpanel.setPutTextType(this.type);
            if (this.palacePKJpanel.getPutChooseType()[this.type]) {
                this.palacePKJpanel.getPutLab()[this.type].setIcon((Icon)null);
                this.palacePKJpanel.getTextGold().setText("");
                this.palacePKJpanel.getPutUnit().setText("");
                this.palacePKJpanel.getFundString()[this.type] = "0";
                this.palacePKJpanel.getPutFundLab()[this.type].setText(this.palacePKJpanel.getUnitString()[this.type + 3] + this.palacePKJpanel.getFundString()[this.type] + this.palacePKJpanel.getUnitString()[this.type]);
            }
            else {
                this.palacePKJpanel.getPutLab()[this.type].setIcon(CutButtonImage.getImage("inkImg/button/B118.png", 7, 7));
                this.palacePKJpanel.getPutUnit().setText(this.palacePKJpanel.getUnitString()[this.type]);
                this.palacePKJpanel.getTextGold().setText("0");
            }
            this.palacePKJpanel.getPutChooseType()[this.type] = !this.palacePKJpanel.getPutChooseType()[this.type];
        }
    }
}
