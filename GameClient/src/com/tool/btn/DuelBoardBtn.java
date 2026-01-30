package com.tool.btn;

import org.come.until.CutButtonImage;
import org.come.until.ScrenceUntil;
import java.awt.event.MouseEvent;
import org.come.Jpanel.DuelBoardJpanel;

public class DuelBoardBtn extends MoBanBtn
{
    private int caozuo;
    private DuelBoardJpanel duelBoardJpanel;
    
    public DuelBoardBtn(String iconpath, int type, String text, int caozuo, DuelBoardJpanel duelBoardJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.duelBoardJpanel = duelBoardJpanel;
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
            try {
                if (this.duelBoardJpanel.isShowType()) {
                    this.duelBoardJpanel.getDuelBoardJframe().setBounds(ScrenceUntil.Screen_x - 18, 215, 18, 18);
                    this.duelBoardJpanel.getShowBtn().setBounds(0, 0, 18, 18);
                    this.duelBoardJpanel.getShowBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/34_png.button.xy_vscroll$down.png"));
                }
                else {
                    this.duelBoardJpanel.getDuelBoardJframe().setBounds(ScrenceUntil.Screen_x - 210, 215, 210, 188);
                    this.duelBoardJpanel.getShowBtn().setBounds(191, 0, 18, 18);
                    this.duelBoardJpanel.getShowBtn().setIcons(CutButtonImage.cuts("img/xy2uiimg/29_png.button.btn_8.png"));
                }
                this.duelBoardJpanel.setShowType(!this.duelBoardJpanel.isShowType());
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
