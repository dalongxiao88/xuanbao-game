package org.come.Jpanel.spot.stall;

import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import org.come.until.UserStallUntil;
import javax.swing.BorderFactory;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import com.tool.btn.spot.SpotPublishBtn;
import org.come.Jpanel.spot.box.SpotBoxJpanel;
import org.come.Jpanel.spot.SpotJpanel;

public abstract class SpotStallJpanel extends SpotJpanel
{
    private final SpotBoxJpanel spotBoxJpanel;
    private final SpotPublishBtn goodPanelBtn;
    private final JTextField textName;
    private final SpotPublishBtn changeNameBtn;
    private ImageIcon icon;
    
    public SpotStallJpanel(SpotBoxJpanel spotBoxJpanel, String backPath, String type) {
        super(spotBoxJpanel, type);
        (this.textName = new JTextField()).setFont(UIUtils.TEXT_FONT);
        this.textName.setOpaque(false);
        this.textName.setCaretColor(Color.WHITE);
        this.textName.setForeground(Color.WHITE);
        this.textName.setBorder(BorderFactory.createEmptyBorder());
        this.textName.setText(UserStallUntil.getStall().getStall());
        this.icon = new ImageIcon(backPath);
        this.setPreferredSize(new Dimension(659, 460));
        FormsOnOffBtn guanbi = new FormsOnOffBtn("inkImg/button/1.png", 1, 800);
        guanbi.setBounds(629, 5, 25, 25);
        this.add(guanbi);
        this.textName.setBounds(346, 22, 90, 18);
        (this.changeNameBtn = new SpotPublishBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "更改", 1, this)).setBounds(430, 22, 34, 17);
        (this.goodPanelBtn = new SpotPublishBtn("inkImg/button/B409.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "收摊", 0, this)).setBounds(290, 387, 79, 25);
        this.add(this.textName);
        this.add(this.changeNameBtn);
        this.add(this.goodPanelBtn);
        this.spotBoxJpanel = spotBoxJpanel;
    }
    
    @Override
    public void updateCommoditys() {
        this.textName.setText(UserStallUntil.getStall().getStall());
    }
    
    @Override
    protected SwitchBtn[] initSwitchBtns(String type) {
        SwitchBtn[] switchBtns = new SwitchBtn[3];
        if (type.equals("sell")) {
            switchBtns[0] = new SwitchBtn("inkImg/button/B433.png", 1, "sell");
        }
        else {
            switchBtns[0] = new SwitchBtn("inkImg/button/B434.png", 1, "sell");
        }
        switchBtns[0].setBounds(61, 13, 85, 33);
        if (type.equals("purchase")) {
            switchBtns[1] = new SwitchBtn("inkImg/button/B435.png", 1, "purchase");
        }
        else {
            switchBtns[1] = new SwitchBtn("inkImg/button/B436.png", 1, "purchase");
        }
        switchBtns[1].setBounds(153, 13, 85, 33);
        if (type.equals("record")) {
            switchBtns[2] = new SwitchBtn("inkImg/button/B437.png", 1, "record");
        }
        else {
            switchBtns[2] = new SwitchBtn("inkImg/button/B438.png", 1, "record");
        }
        switchBtns[2].setBounds(245, 13, 85, 33);
        return switchBtns;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon != null) {
            g.drawImage(this.icon.getImage(), 0, 0, this);
        }
    }
    
    public String getStallName() {
        return this.textName.getText();
    }
}
