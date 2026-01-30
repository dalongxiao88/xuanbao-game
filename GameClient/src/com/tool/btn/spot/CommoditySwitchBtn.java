package com.tool.btn.spot;

import java.awt.event.MouseEvent;
import org.come.until.CutButtonImage;
import org.come.Jpanel.spot.BuyBox;
import javax.swing.ImageIcon;
import com.tool.btn.MoBanBtn;

public class CommoditySwitchBtn extends MoBanBtn
{
    protected int index;
    private ImageIcon[][] backIcons;
    private BuyBox buyBox;
    
    public CommoditySwitchBtn(String path1, String path2, BuyBox buyBox, int index, boolean isSelect) {
        super("", 1);
        this.setBackIcons(path1, path2);
        this.selectBtnt(isSelect);
        this.buyBox = buyBox;
        this.index = index;
    }
    
    protected void setBackIcons(String path1, String path2) {
        try {
            (this.backIcons = new ImageIcon[2][3])[0] = CutButtonImage.cuts(path1);
            this.backIcons[1] = CutButtonImage.cuts(path2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void selectBtnt(boolean isSelect) {
        if (isSelect) {
            this.setIcons(this.backIcons[1]);
        }
        else {
            this.setIcons(this.backIcons[0]);
        }
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        CommoditySwitchBtn[] btnGroup = this.buyBox.getCommoditySwitchBtns();
        for (int i = 0; i < btnGroup.length; ++i) {
            if (i == this.index) {
                btnGroup[i].selectBtnt(true);
            }
            else {
                btnGroup[i].selectBtnt(false);
            }
        }
        this.buyBox.switchTo(this.index);
    }
}
