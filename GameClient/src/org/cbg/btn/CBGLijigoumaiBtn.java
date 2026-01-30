package org.cbg.btn;

import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.Frame.ZhuFrame;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.cbg.panel.TraslationCommodityMainJPane;
import com.tool.btn.MoBanBtn;

public class CBGLijigoumaiBtn extends MoBanBtn
{
    private TraslationCommodityMainJPane traslationCommodityMainJPane;
    
    public CBGLijigoumaiBtn(String iconpath, int type, Color[] colors, Font font, String text, TraslationCommodityMainJPane traslationCommodityMainJPane) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.traslationCommodityMainJPane = traslationCommodityMainJPane;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        Desktop desktop = Desktop.getDesktop();
        int type = 1;
        if (this.traslationCommodityMainJPane.getIncreaseAmount().isVisible()) {
            type = 1;
            if (this.traslationCommodityMainJPane.getGouxuan().getName().equals("1")) {
                ZhuFrame.getZhuJpanel().addPrompt2("所选物品还在公示期请先勾选确认");
                return;
            }
        }
        else {
            type = 2;
        }
        try {
            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.CBGBuy, this.traslationCommodityMainJPane.getName(), "#W确认花费#R" + this.traslationCommodityMainJPane.getJine().getText() + "#W仙玉来购买这件物品?");
        }
        catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}
