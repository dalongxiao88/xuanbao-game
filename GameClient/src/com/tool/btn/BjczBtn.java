
package com.tool.btn;

import com.tool.tcpimg.UIUtils;
import org.come.Frame.WestboundLevelJframe;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.WestboundLevelJpanel;
import org.come.Jpanel.WestboundLevelSubJpanel;
import org.come.until.FormsManagement;

import java.awt.*;
import java.awt.event.MouseEvent;

public class BjczBtn extends MoBanBtn {
    private int caozuo;

    public BjczBtn(String iconpath, int type, String text) {
        super(iconpath, type);
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT1);
        this.setForeground(Color.yellow);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }

    public BjczBtn(String iconpath, int type) {
        super(iconpath, type);
    }

    public BjczBtn(String iconpath, int type, Color[] colors, String text, int caozuo) {
        super(iconpath, type, colors);
        this.setText(text);
        setFont(UIUtils.TEXT_HY88);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
    }

    public void chooseyes() {
    }

    public void chooseno() {
    }

    public void nochoose(MouseEvent e) {
    	if(this.caozuo == 3079) {
    		FormsManagement.showForm(3079);
    		WestboundLevelJpanel.updateDW();
        	return;
    	}else if(this.caozuo == 30791) {
    		if(WestboundLevelSubJpanel.createNum<81) {
    			WestboundLevelJframe.getWestboundLevelJpanel().westboundLevelTextJpanel.text = WestboundLevelJframe.getWestboundLevelJpanel().westboundLevelTextJpanel.basynText[WestboundLevelSubJpanel.createNum];
    			WestboundLevelJframe.getWestboundLevelJpanel().westboundLevelTextJpanel.initTimer();
    			WestboundLevelJframe.getWestboundLevelJpanel().westboundLevelTextJpanel.GBTS = true;
    			WestboundLevelJframe.getWestboundLevelJpanel().westboundLevelTextJpanel.setVisible(true);
    		}else {
    			ZhuFrame.getZhuJpanel().addPrompt("您已全部通关！");
    		}
        	return;
    	}else if(this.caozuo == 30792) {
    		WestboundLevelJpanel.updateHT();
        	return;
    	}else if(this.caozuo == 30793) {
    		WestboundLevelJpanel.updateDW();
        	return;
    	}
    }
}



