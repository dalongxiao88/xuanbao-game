package org.come.Jpanel.spot.buy;

import com.tool.Stall.Stall;
import java.awt.Color;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import org.come.until.UserStallUntil;
import java.awt.event.MouseEvent;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.btn.spot.SpotPublishBtn;
import org.come.Jpanel.spot.box.SpotBuyBoxJpanel;
import org.come.Jpanel.spot.SpotJpanel;

public abstract class SpotBuyJpanel extends SpotJpanel
{
    private final SpotBuyBoxJpanel spotBuyBoxJpanel;
    private final SpotPublishBtn addFriendBtn;
    private final SpotPublishBtn addFollowBtn;
    private ImageIcon icon;
    
    public SpotBuyJpanel(SpotBuyBoxJpanel spotBuyBoxJpanel, String backPath, String type) {
        super(spotBuyBoxJpanel, type);
        this.icon = new ImageIcon(backPath);
        this.setPreferredSize(new Dimension(687, 487));
        FormsOnOffBtn guanbi = new FormsOnOffBtn("inkImg/button/1.png", 1, 801) {
            @Override
            public void nochoose(MouseEvent e) {
                UserStallUntil.hideBuyStall();
            }
        };
        guanbi.setBounds(652, 10, 25, 25);
        this.add(guanbi);
        (this.addFriendBtn = new SpotPublishBtn("inkImg/button/16.png", 1, 0, this)).setBounds(468, 22, 18, 17);
        (this.addFollowBtn = new SpotPublishBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "关注", 1, this)).setBounds(430, 22, 34, 17);
        this.add(this.addFriendBtn);
        this.add(this.addFollowBtn);
        this.spotBuyBoxJpanel = spotBuyBoxJpanel;
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
        Stall stall = this.spotBuyBoxJpanel.getStall();
        if (stall != null) {
            g.setFont(UIUtils.TEXT_FONT1);
            g.setColor(Color.WHITE);
            g.drawString(this.spotBuyBoxJpanel.getStall().getStall(), 340, 35);
        }
        this.addFollowBtn.setBounds(486, 22, 34, 17);
    }
    
    public Stall getStall() {
        return this.spotBuyBoxJpanel.getStall();
    }
}
