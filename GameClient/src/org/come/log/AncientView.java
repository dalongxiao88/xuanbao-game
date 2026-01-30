package org.come.log;

import com.tool.tcp.SpriteFactory;
import java.awt.Graphics;
import org.come.login.LoginMouslisten;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import org.come.login.LoginJpanel;
import org.come.entity.RegionResult;
import java.util.List;
import org.come.login.SpriteBtn;
import com.tool.tcp.Sprite;
import javax.swing.JLabel;
import org.come.view.View;

public class AncientView extends View
{
    private JLabel labMsgTip;
    public JLabel mouse;
    private long time;
    static Sprite tcp;
    static Sprite tcp_1;
    static Sprite logo;
    static Sprite tcp3;
    static Sprite tcp1;
    static Sprite tcp4;
    static Sprite tcp2;
    static Sprite back;
    private JLabel progressBg;
    private JLabel progress;
    private JLabel tishi;
    private String msg;
    private SpriteBtn btnRegister;
    private SpriteBtn dankai;
    private SpriteBtn time_card;
    private SpriteBtn index;
    private SpriteBtn team;
    private SpriteBtn quit;
    private AegisterView aegisterView;
    
    public AncientView(List<RegionResult> regionResultList, LoginJpanel loginJpanel) {
        this.time = 100L;
        this.setBounds(0, 0, LanderJPanel.width, LanderJPanel.high);
        this.setBackground(Color.YELLOW);
        this.redis(regionResultList, loginJpanel);
        (this.labMsgTip = new JLabel()).setBounds(45, 406, 300, 30);
        this.labMsgTip.setFont(new Font("楷体", 1, 16));
        this.labMsgTip.setForeground(Color.red);
        this.add(this.labMsgTip);
    }
    
    public void redis(List<RegionResult> regionResultList, LoginJpanel loginJpanel) {
        (this.progressBg = new JLabel(new ImageIcon("resource/NewUi/21.png"))).setBounds(100, 563, 600, 29);
        this.add(this.progressBg);
        (this.progress = new JLabel(new ImageIcon("resource/NewUi/22.png"))).setBounds(105, 563, 0, 29);
        this.add(this.progress);
        (this.btnRegister = new SpriteBtn("resource/intro/register.was", 481, 125, false)).setBounds(481, 125, 143, 37);
        this.btnRegister.addMouseListener(new LoginMouslisten(12, this.btnRegister, loginJpanel));
        this.add(this.btnRegister);
        (this.dankai = new SpriteBtn("resource/intro/into-game.was", 481, 75, false)).addMouseListener(new LoginMouslisten(13, this.dankai, loginJpanel));
        this.dankai.setHorizontalAlignment(0);
        this.dankai.setBounds(481, 75, 143, 37);
        this.add(this.dankai);
        (this.time_card = new SpriteBtn("resource/intro/time-card.was", 481, 175, false)).addMouseListener(new LoginMouslisten(3, this.time_card, loginJpanel));
        this.time_card.setHorizontalAlignment(0);
        this.time_card.setBounds(481, 175, 143, 37);
        this.add(this.time_card);
        (this.index = new SpriteBtn("resource/intro/index.was", 481, 225, false)).addMouseListener(new LoginMouslisten(4, this.index, loginJpanel));
        this.index.setHorizontalAlignment(0);
        this.index.setBounds(481, 225, 143, 37);
        this.add(this.index);
        (this.team = new SpriteBtn("resource/intro/team.was", 481, 275, false)).addMouseListener(new LoginMouslisten(5, this.team, loginJpanel));
        this.team.setHorizontalAlignment(0);
        this.team.setBounds(481, 275, 143, 37);
        this.add(this.team);
        (this.quit = new SpriteBtn("resource/intro/quit.was", 481, 325, false)).addMouseListener(new LoginMouslisten(-1, this.quit, loginJpanel));
        this.quit.setHorizontalAlignment(0);
        this.quit.setBounds(481, 325, 143, 37);
        this.add(this.quit);
        (this.tishi = new JLabel()).setForeground(Color.yellow);
        this.tishi.setBounds(24, 372, 600, 80);
        this.tishi.setText("<html><body><font color='#ffff00'>玩家建议及反馈：dhxy2@outlook.com</font><br/>   <html><body><font color='#ffff00'>  本游戏仅供学习交流，请在下载后24小时内删除</font><br/><html><body><font color='#ffff00'>游戏中使用的素材均来源于网易大话西游2客户端， 版权归网易公司所有</font>");
        this.tishi.setFont(new Font("宋体", 0, 13));
        this.add(this.tishi);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        AncientView.back.updateToTime(this.time, 0);
        AncientView.back.draw(g, 0, 0);
        this.time += 24L;
        AncientView.tcp.updateToTime(this.time, 0);
        AncientView.tcp.draw(g, 0, -5);
        AncientView.tcp4.updateToTime(this.time, 0);
        AncientView.tcp4.draw(g, 420, 400);
        AncientView.tcp4.getTime();
        AncientView.tcp_1.updateToTime(this.time, 0);
        AncientView.tcp_1.draw(g, 0, 440);
        AncientView.tcp1.updateToTime(this.time, 0);
        AncientView.tcp1.draw(g, 150, 50);
        AncientView.tcp1.getTime();
        AncientView.logo.updateToTime(this.time, 0);
        AncientView.logo.draw(g, 20, 20);
        AncientView.tcp3.updateToTime(this.time, 0);
        AncientView.tcp3.draw(g, 340, 204);
        AncientView.tcp3.getTime();
        AncientView.tcp2.updateToTime(this.time, 0);
        AncientView.tcp2.draw(g, 50, 216);
        if (this.msg != null) {
            g.setColor(Color.red);
            g.drawString(this.msg, 250, 465);
        }
        if (this.btnRegister != null) {
            this.btnRegister.draw(g);
        }
        if (this.time_card != null) {
            this.time_card.draw(g);
        }
        if (this.index != null) {
            this.index.draw(g);
        }
        if (this.team != null) {
            this.team.draw(g);
        }
        if (this.quit != null) {
            this.quit.draw(g);
        }
        if (this.dankai != null) {
            this.dankai.draw(g);
        }
    }
    
    public JLabel getProgressBg() {
        return this.progressBg;
    }
    
    public JLabel getProgress() {
        return this.progress;
    }
    
    public JLabel getTishi() {
        return this.tishi;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public AegisterView getAegisterView() {
        return this.aegisterView;
    }
    
    public void setAegisterView(AegisterView aegisterView) {
        this.aegisterView = aegisterView;
    }
    
    public JLabel getLabMsgTip() {
        return this.labMsgTip;
    }
    
    public void setLabMsgTip(JLabel labMsgTip) {
        this.labMsgTip = labMsgTip;
    }
    
    static {
        AncientView.tcp = SpriteFactory.VloadSprite("resource/intro/bar.was", null);
        AncientView.tcp_1 = SpriteFactory.VloadSprite("resource/intro/bar.was", null);
        AncientView.logo = SpriteFactory.VloadSprite("resource/intro/logo.was", null);
        AncientView.tcp3 = SpriteFactory.VloadSprite("resource/intro/gif2.was", null);
        AncientView.tcp1 = SpriteFactory.VloadSprite("resource/intro/gif1.was", null);
        AncientView.tcp4 = SpriteFactory.VloadSprite("resource/intro/gif4.was", null);
        AncientView.tcp2 = SpriteFactory.VloadSprite("resource/intro/gif3.was", null);
        AncientView.back = SpriteFactory.VloadSprite("resource/intro/background.was", null);
    }
}
