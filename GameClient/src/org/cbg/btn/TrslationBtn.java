package org.cbg.btn;

import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.RuneOperateJpanel;
import com.tool.btn.MoBanBtn;

public class TrslationBtn extends MoBanBtn
{
    private int caozuo;
    private RuneOperateJpanel runeOperateJpanel;
    
    public TrslationBtn(String iconpath, int type) {
        super(iconpath, type);
    }
    
    public TrslationBtn(String iconpath, int type, int caozuo, RuneOperateJpanel runeOperateJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.runeOperateJpanel = runeOperateJpanel;
    }
    
    public TrslationBtn(String iconpath, int type, Color[] colors, Font font, String text) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (MyIsif.getStyle().equals("水墨")) {
            try {
                if (this.caozuo == 1) {
                    this.runeOperateJpanel.getBtnCz().setIcons(CutButtonImage.cuts("inkImg/button/B186.png"));
                    this.runeOperateJpanel.getBtnSj().setIcons(CutButtonImage.cuts("inkImg/button/B183.png"));
                    this.runeOperateJpanel.setRuneType(1);
                    this.runeOperateJpanel.getPerBtn1().setText("重铸");
                    this.runeOperateJpanel.getPerBtn2().setText("重铸规则");
                    this.runeOperateJpanel.changRuneType();
                }
                else if (this.caozuo == 2) {
                    this.runeOperateJpanel.getBtnCz().setIcons(CutButtonImage.cuts("inkImg/button/B185.png"));
                    this.runeOperateJpanel.getBtnSj().setIcons(CutButtonImage.cuts("inkImg/button/B184.png"));
                    this.runeOperateJpanel.setRuneType(2);
                    this.runeOperateJpanel.getPerBtn1().setText("升级");
                    this.runeOperateJpanel.getPerBtn2().setText("升级规则");
                    this.runeOperateJpanel.changRuneType();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        else {
            try {
                if (this.caozuo == 1) {
                    this.runeOperateJpanel.getBtnCz().setIcons(CutButtonImage.cuts("img/xy2uiimg/重洗按钮2-W：30-H：138.png"));
                    this.runeOperateJpanel.getBtnSj().setIcons(CutButtonImage.cuts("img/xy2uiimg/升级按钮-W：30-H：138.png"));
                    this.runeOperateJpanel.setRuneType(1);
                    this.runeOperateJpanel.getPerBtn1().setText("重铸");
                    this.runeOperateJpanel.getPerBtn2().setText("重铸规则");
                    this.runeOperateJpanel.changRuneType();
                }
                else if (this.caozuo == 2) {
                    this.runeOperateJpanel.getBtnCz().setIcons(CutButtonImage.cuts("img/xy2uiimg/重洗按钮-W：30-H：138.png"));
                    this.runeOperateJpanel.getBtnSj().setIcons(CutButtonImage.cuts("img/xy2uiimg/升级按钮2-W：30-H：138.png"));
                    this.runeOperateJpanel.setRuneType(2);
                    this.runeOperateJpanel.getPerBtn1().setText("升级");
                    this.runeOperateJpanel.getPerBtn2().setText("升级规则");
                    this.runeOperateJpanel.changRuneType();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
