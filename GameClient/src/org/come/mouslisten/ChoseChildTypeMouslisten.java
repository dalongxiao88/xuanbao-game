package org.come.mouslisten;

import org.come.until.CutButtonImage;
import org.come.Frame.TestChildJframe;
import com.updateNew.MyIsif;
import java.awt.event.MouseEvent;
import org.come.Jpanel.TestChildCardJpanel;
import java.awt.event.MouseListener;

public class ChoseChildTypeMouslisten implements MouseListener
{
    private TestChildCardJpanel childCardJpanel;
    private int type;
    
    public ChoseChildTypeMouslisten(int type, TestChildCardJpanel childCardJpanel) {
        this.type = type;
        this.childCardJpanel = childCardJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        try {
            if (MyIsif.getStyle().equals("水墨")) {
                if (this.type == 1) {
                    this.childCardJpanel.getCar().show(this.childCardJpanel, "l1");
                    TestChildJframe.getTestChildJframe().getTestChildJpanel().getLabAttr().setIcons(CutButtonImage.cuts("inkImg/button/B180.png"));
                    TestChildJframe.getTestChildJframe().getTestChildJpanel().getLabTalent().setIcons(CutButtonImage.cuts("inkImg/button/B181.png"));
                }
                if (this.type == 2) {
                    this.childCardJpanel.getCar().show(this.childCardJpanel, "l2");
                    TestChildJframe.getTestChildJframe().getTestChildJpanel().getLabAttr().setIcons(CutButtonImage.cuts("inkImg/button/B179.png"));
                    TestChildJframe.getTestChildJframe().getTestChildJpanel().getLabTalent().setIcons(CutButtonImage.cuts("inkImg/button/B182.png"));
                }
            }
            else {
                if (this.type == 1) {
                    this.childCardJpanel.getCar().show(this.childCardJpanel, "l1");
                    TestChildJframe.getTestChildJframe().getTestChildJpanel().getLabAttr().setIcons(CutButtonImage.cuts("img/xy2uiimg/childattr_checked.png"));
                    TestChildJframe.getTestChildJframe().getTestChildJpanel().getLabTalent().setIcons(CutButtonImage.cuts("img/xy2uiimg/childtalent_unchecked.png"));
                }
                if (this.type == 2) {
                    this.childCardJpanel.getCar().show(this.childCardJpanel, "l2");
                    TestChildJframe.getTestChildJframe().getTestChildJpanel().getLabAttr().setIcons(CutButtonImage.cuts("img/xy2uiimg/childattr_unchecked.png"));
                    TestChildJframe.getTestChildJframe().getTestChildJpanel().getLabTalent().setIcons(CutButtonImage.cuts("img/xy2uiimg/childtalent_checked.png"));
                }
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
