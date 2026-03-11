package com.tool.btn;

import org.come.until.CutButtonImage;
import org.come.Jpanel.FundBuyJpanel;
import java.awt.event.MouseEvent;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import javax.swing.JPanel;

/**
 * 基金档位选择按钮。
 * 点击后同步切换基金购买面板的按钮高亮和当前选中的基金档位。
 */
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
            String optionType = this.caozuo;
            FundBuyJpanel buyJpanel = (FundBuyJpanel)this.jPanel;
            if ("30基金".equals(optionType)) {
                buyJpanel.getThridFund().setIcons(CutButtonImage.cuts("inkImg/button/21.png"));
                buyJpanel.getSixthFund().setIcons(CutButtonImage.cuts("inkImg/button/20.png"));
                buyJpanel.getNinethFund().setIcons(CutButtonImage.cuts("inkImg/button/20.png"));
                buyJpanel.setType("30");
            }
            else if ("60基金".equals(optionType)) {
                buyJpanel.getThridFund().setIcons(CutButtonImage.cuts("inkImg/button/20.png"));
                buyJpanel.getSixthFund().setIcons(CutButtonImage.cuts("inkImg/button/21.png"));
                buyJpanel.getNinethFund().setIcons(CutButtonImage.cuts("inkImg/button/20.png"));
                buyJpanel.setType("60");
            }
            else if ("90基金".equals(optionType)) {
                buyJpanel.getThridFund().setIcons(CutButtonImage.cuts("inkImg/button/20.png"));
                buyJpanel.getSixthFund().setIcons(CutButtonImage.cuts("inkImg/button/20.png"));
                buyJpanel.getNinethFund().setIcons(CutButtonImage.cuts("inkImg/button/21.png"));
                buyJpanel.setType("90");
            }
        }
        catch (Exception updateOptionException) {
            updateOptionException.printStackTrace();
        }
    }
}
