package com.tool.btn;

import org.come.until.CutButtonImage;
import org.come.Jpanel.FundBuyJpanel;
import java.awt.event.MouseEvent;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import javax.swing.JPanel;

public class OptionUncheckBtn extends MoBanBtn
{
    private String caozuo;
    private JPanel jPanel;
    
    public OptionUncheckBtn(String iconpath, int type, String text, String caozuo, JPanel jpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.jPanel = jpanel;
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY19);
        this.setForeground(new Color(255, 255, 255));
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
        try {
            String var4;
            switch ((var4 = this.caozuo).hashCode()) {
                case 2300980: {
                    if (var4.equals("30基金")) {
                        FundBuyJpanel buyJpanel = (FundBuyJpanel)this.jPanel;
                        buyJpanel.getThridFund().setIcons(CutButtonImage.cuts("inkImg/button/21.png"));
                        buyJpanel.getSixthFund().setIcons(CutButtonImage.cuts("inkImg/button/20.png"));
                        buyJpanel.getNinethFund().setIcons(CutButtonImage.cuts("inkImg/button/20.png"));
                        buyJpanel.setType("30");
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 2390353: {
                    if (var4.equals("60基金")) {
                        FundBuyJpanel buyJpanel = (FundBuyJpanel)this.jPanel;
                        buyJpanel.getThridFund().setIcons(CutButtonImage.cuts("inkImg/button/20.png"));
                        buyJpanel.getSixthFund().setIcons(CutButtonImage.cuts("inkImg/button/21.png"));
                        buyJpanel.getNinethFund().setIcons(CutButtonImage.cuts("inkImg/button/20.png"));
                        buyJpanel.setType("60");
                        break;
                    }
                    else {
                        break;
                    }
                }
                case 2479726: {
                    if (var4.equals("90基金")) {
                        FundBuyJpanel buyJpanel = (FundBuyJpanel)this.jPanel;
                        buyJpanel.getThridFund().setIcons(CutButtonImage.cuts("inkImg/button/20.png"));
                        buyJpanel.getSixthFund().setIcons(CutButtonImage.cuts("inkImg/button/20.png"));
                        buyJpanel.getNinethFund().setIcons(CutButtonImage.cuts("inkImg/button/21.png"));
                        buyJpanel.setType("90");
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        catch (Exception var5) {
            var5.printStackTrace();
        }
    }
}
