package org.skill.panel;

import org.come.until.FormsManagement;
import org.come.Frame.MsgJframe;
import java.awt.event.MouseEvent;
import org.come.until.CutButtonImage;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import com.tool.tcp.SpriteFactory;
import java.awt.Rectangle;
import org.come.entity.RoleSummoning;
import org.come.until.Music;
import org.come.Frame.ZhuFrame;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.come.bean.Skill;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import io.netty.util.internal.StringUtil;
import org.come.until.UserMessUntil;
import org.come.bean.LxPointBean;
import com.tool.image.ImageMixDeal;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import com.tool.tcp.Sprite;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.PetLxBtn;
import javax.swing.JPanel;

public class LxPanel extends JPanel
{
    private static final long serialVersionUID = -1725180769538448434L;
    private PetLxBtn wulixiBtn;
    private PetLxBtn fashuxiBtn;
    private PetLxBtn fuzhuxiBtn;
    private PetLxBtn backBtn;
    private PetLxBtn xiulian;
    private PetLxBtn xilian;
    private PetLxBtn queding;
    private PetLxBtn openSkillBtn;
    private XSkillLx[] wuli;
    private XSkillLx[] fashu;
    private XSkillLx[] fuzhu;
    private static JLabel lingxidian;
    private int dianshu;
    private int panelType;
    private ImageIcon icon;
    static Sprite tcp;
    private static final int[] W;
    private static final int[] F;
    private static final int[] Z;
    
    public LxPanel() throws Exception {
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/B316.png");
            this.setPreferredSize(new Dimension(644, 405));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 601);
            offBtn.setBounds(614, 5, 25, 25);
            this.add(offBtn);
            (this.wulixiBtn = new PetLxBtn("inkImg/button/B363.png", 1, 1)).setBounds(50, 12, 171, 340);
            this.add(this.wulixiBtn);
            (this.fashuxiBtn = new PetLxBtn("inkImg/button/B364.png", 1, 2)).setBounds(236, 12, 171, 340);
            this.add(this.fashuxiBtn);
            (this.fuzhuxiBtn = new PetLxBtn("inkImg/button/B365.png", 1, 3)).setBounds(422, 12, 171, 340);
            this.add(this.fuzhuxiBtn);
            (this.backBtn = new PetLxBtn("inkImg/button/B366.png", 1, 0)).setBounds(5, 310, 55, 43);
            this.add(this.backBtn);
            this.backBtn.setVisible(false);
            (this.xiulian = new PetLxBtn("inkImg/button1/B20.png", 1, "修炼", 5, "")).setBounds(180, 363, 59, 24);
            this.add(this.xiulian);
            (this.xilian = new PetLxBtn("inkImg/button1/B20.png", 1, "洗点", 6, "")).setBounds(260, 363, 59, 24);
            this.add(this.xilian);
            (this.queding = new PetLxBtn("inkImg/button1/B22.png", 1, "确定加点", 7, this, "")).setBounds(490, 363, 99, 24);
            this.add(this.queding);
            this.queding.setVisible(false);
            (this.openSkillBtn = new PetLxBtn("inkImg/button1/B22.png", 1, "一键开启", 10, this, "")).setBounds(385, 363, 99, 24);
            this.add(this.openSkillBtn);
            this.openSkillBtn.setVisible(false);
            (LxPanel.lingxidian = new JLabel()).setBounds(145, 370, 45, 14);
            LxPanel.lingxidian.setForeground(Color.WHITE);
            LxPanel.lingxidian.setFont(new Font("微软雅黑", 1, 12));
            LxPanel.lingxidian.setVerticalTextPosition(0);
            LxPanel.lingxidian.setHorizontalTextPosition(0);
            LxPanel.lingxidian.setText("0");
            this.add(LxPanel.lingxidian);
            this.panelType = -1;
        }
        else {
            this.icon = new ImageIcon("img/xy2uiimg/S267.png");
            this.setPreferredSize(new Dimension(644, 405));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/S74.png", 1, 601);
            offBtn.setBounds(619, 0, 25, 25);
            this.add(offBtn);
            (this.wulixiBtn = new PetLxBtn("inkImg/button/B363.png", 1, 1)).setBounds(50, 24, 171, 340);
            this.add(this.wulixiBtn);
            (this.fashuxiBtn = new PetLxBtn("inkImg/button/B364.png", 1, 2)).setBounds(236, 24, 171, 340);
            this.add(this.fashuxiBtn);
            (this.fuzhuxiBtn = new PetLxBtn("inkImg/button/B365.png", 1, 3)).setBounds(422, 24, 171, 340);
            this.add(this.fuzhuxiBtn);
            (this.backBtn = new PetLxBtn("inkImg/button/B366.png", 1, 0)).setBounds(5, 310, 55, 43);
            this.add(this.backBtn);
            this.backBtn.setVisible(false);
            (this.xiulian = new PetLxBtn("inkImg/hongmu/6026.png", 1, "修炼", 5)).setBounds(190, 368, 60, 26);
            this.add(this.xiulian);
            (this.xilian = new PetLxBtn("inkImg/hongmu/6026.png", 1, "洗点", 6)).setBounds(260, 368, 60, 26);
            this.add(this.xilian);
            (this.queding = new PetLxBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "确定加点", 7, this)).setBounds(490, 368, 80, 26);
            this.add(this.queding);
            this.queding.setVisible(false);
            (this.openSkillBtn = new PetLxBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "一键开启", 10, this)).setBounds(385, 368, 80, 26);
            this.add(this.openSkillBtn);
            this.openSkillBtn.setVisible(false);
            (LxPanel.lingxidian = new JLabel()).setBounds(145, 370, 45, 14);
            LxPanel.lingxidian.setForeground(Color.WHITE);
            LxPanel.lingxidian.setFont(new Font("微软雅黑", 1, 12));
            LxPanel.lingxidian.setVerticalTextPosition(0);
            LxPanel.lingxidian.setHorizontalTextPosition(0);
            LxPanel.lingxidian.setText("0");
            this.add(LxPanel.lingxidian);
            this.panelType = -1;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon != null) {
            g.drawImage(this.icon.getImage(), 0, 0, 644, 405, this);
        }
        LxPanel.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
        LxPanel.tcp.draw(g, 352, 100);
    }
    
    public void changePanel(int type) {
        if (type == this.panelType) {
            return;
        }
        if (MyIsif.getStyle().equals("水墨")) {
            switch (type) {
                case 0: {
                    this.wulixiBtn.setVisible(true);
                    this.fashuxiBtn.setVisible(true);
                    this.fuzhuxiBtn.setVisible(true);
                    this.backBtn.setVisible(false);
                    this.queding.setVisible(false);
                    this.openSkillBtn.setVisible(false);
                    this.icon = new ImageIcon("inkImg/background1/B316.png");
                    this.panelType = 0;
                    break;
                }
                case 1: {
                    this.wulixiBtn.setVisible(false);
                    this.fashuxiBtn.setVisible(false);
                    this.fuzhuxiBtn.setVisible(false);
                    this.backBtn.setVisible(true);
                    this.queding.setVisible(true);
                    this.openSkillBtn.setVisible(true);
                    this.icon = new ImageIcon("inkImg/background1/B317.png");
                    this.panelType = 1;
                    break;
                }
                case 2: {
                    this.wulixiBtn.setVisible(false);
                    this.fashuxiBtn.setVisible(false);
                    this.fuzhuxiBtn.setVisible(false);
                    this.backBtn.setVisible(true);
                    this.queding.setVisible(true);
                    this.openSkillBtn.setVisible(true);
                    this.icon = new ImageIcon("inkImg/background1/B318.png");
                    this.panelType = 2;
                    break;
                }
                case 3: {
                    this.wulixiBtn.setVisible(false);
                    this.fashuxiBtn.setVisible(false);
                    this.fuzhuxiBtn.setVisible(false);
                    this.backBtn.setVisible(true);
                    this.queding.setVisible(true);
                    this.openSkillBtn.setVisible(true);
                    this.icon = new ImageIcon("inkImg/background1/B319.png");
                    this.panelType = 3;
                    break;
                }
                default: {
                    this.wulixiBtn.setVisible(true);
                    this.fashuxiBtn.setVisible(true);
                    this.fuzhuxiBtn.setVisible(true);
                    this.backBtn.setVisible(false);
                    this.queding.setVisible(false);
                    this.openSkillBtn.setVisible(false);
                    this.icon = new ImageIcon("inkImg/background1/B316.png");
                    this.panelType = 0;
                    break;
                }
            }
        }
        else {
            switch (type) {
                case 0: {
                    this.wulixiBtn.setVisible(true);
                    this.fashuxiBtn.setVisible(true);
                    this.fuzhuxiBtn.setVisible(true);
                    this.backBtn.setVisible(false);
                    this.queding.setVisible(false);
                    this.openSkillBtn.setVisible(false);
                    this.icon = new ImageIcon("img/xy2uiimg/S267.png");
                    this.panelType = 0;
                    break;
                }
                case 1: {
                    this.wulixiBtn.setVisible(false);
                    this.fashuxiBtn.setVisible(false);
                    this.fuzhuxiBtn.setVisible(false);
                    this.backBtn.setVisible(true);
                    this.queding.setVisible(true);
                    this.openSkillBtn.setVisible(true);
                    this.icon = new ImageIcon("img/xy2uiimg/S268.png");
                    this.panelType = 1;
                    break;
                }
                case 2: {
                    this.wulixiBtn.setVisible(false);
                    this.fashuxiBtn.setVisible(false);
                    this.fuzhuxiBtn.setVisible(false);
                    this.backBtn.setVisible(true);
                    this.queding.setVisible(true);
                    this.openSkillBtn.setVisible(true);
                    this.icon = new ImageIcon("img/xy2uiimg/S269.png");
                    this.panelType = 2;
                    break;
                }
                case 3: {
                    this.wulixiBtn.setVisible(false);
                    this.fashuxiBtn.setVisible(false);
                    this.fuzhuxiBtn.setVisible(false);
                    this.backBtn.setVisible(true);
                    this.queding.setVisible(true);
                    this.openSkillBtn.setVisible(true);
                    this.icon = new ImageIcon("img/xy2uiimg/S270.png");
                    this.panelType = 3;
                    break;
                }
                default: {
                    this.wulixiBtn.setVisible(true);
                    this.fashuxiBtn.setVisible(true);
                    this.fuzhuxiBtn.setVisible(true);
                    this.backBtn.setVisible(false);
                    this.queding.setVisible(false);
                    this.openSkillBtn.setVisible(false);
                    this.icon = new ImageIcon("img/xy2uiimg/S267.png");
                    this.panelType = 0;
                    break;
                }
            }
        }
        this.showType(type);
    }
    
    public void showType(int type) {
        switch (type) {
            case 0: {
                if (this.wuli != null) {
                    for (XSkillLx lx : this.wuli) {
                        lx.setVisible(false);
                    }
                }
                if (this.fashu != null) {
                    for (XSkillLx lx : this.fashu) {
                        lx.setVisible(false);
                    }
                }
                if (this.fuzhu != null) {
                    for (XSkillLx lx : this.fuzhu) {
                        lx.setVisible(false);
                    }
                    break;
                }
                else {
                    break;
                }
            }
            case 1: {
                if (this.fashu != null) {
                    for (XSkillLx lx : this.fashu) {
                        lx.setVisible(false);
                    }
                }
                if (this.fuzhu != null) {
                    for (XSkillLx lx : this.fuzhu) {
                        lx.setVisible(false);
                    }
                }
                if (this.wuli == null) {
                    this.wuli = this.getWuli();
                    for (XSkillLx lx : this.wuli) {
                        this.add(lx);
                    }
                    break;
                }
                else {
                    for (XSkillLx lx : this.wuli) {
                        lx.setVisible(true);
                    }
                    break;
                }
            }
            case 2: {
                if (this.fuzhu != null) {
                    for (XSkillLx lx : this.fuzhu) {
                        lx.setVisible(false);
                    }
                }
                if (this.wuli != null) {
                    for (XSkillLx lx : this.wuli) {
                        lx.setVisible(false);
                    }
                }
                if (this.fashu == null) {
                    this.fashu = this.getFashu();
                    for (XSkillLx lx : this.fashu) {
                        this.add(lx);
                    }
                    break;
                }
                else {
                    for (XSkillLx lx : this.fashu) {
                        lx.setVisible(true);
                    }
                    break;
                }
            }
            case 3: {
                if (this.fashu != null) {
                    for (XSkillLx lx : this.fashu) {
                        lx.setVisible(false);
                    }
                }
                if (this.wuli != null) {
                    for (XSkillLx lx : this.wuli) {
                        lx.setVisible(false);
                    }
                }
                if (this.fuzhu == null) {
                    this.fuzhu = this.getFuzhu();
                    for (XSkillLx lx : this.fuzhu) {
                        this.add(lx);
                    }
                    break;
                }
                else {
                    for (XSkillLx lx : this.fuzhu) {
                        lx.setVisible(true);
                    }
                    break;
                }
            }
            case 4: {
                if (this.fashu != null) {
                    for (XSkillLx lx : this.fashu) {
                        lx.setVisible(false);
                        lx.reset();
                    }
                }
                if (this.wuli != null) {
                    for (XSkillLx lx : this.wuli) {
                        lx.setVisible(false);
                        lx.reset();
                    }
                }
                if (this.fuzhu != null) {
                    for (XSkillLx lx : this.fuzhu) {
                        lx.setVisible(false);
                        lx.reset();
                    }
                    break;
                }
                else {
                    break;
                }
            }
        }
    }
    //召唤兽灵犀几点修改地方和服务端一起
    public XSkillLx[] getWuli() {
        XSkillLx[] lx = new XSkillLx[24];
        LxPointBean[] points = new LxPointBean[24];
        points[0] = new LxPointBean(1, 11003, 3, 0);
        lx[0] = new XSkillLx(points[0], 6, 1, 1);
        points[1] = new LxPointBean(1, 11001, 3, 0);
        lx[1] = new XSkillLx(points[1], 6, 2, 1);
        points[2] = new LxPointBean(1, 11004, 3, 0);
        lx[2] = new XSkillLx(points[2], 6, 3, 1);
        points[3] = new LxPointBean(1, 11005, 3, 0);
        lx[3] = new XSkillLx(points[3], 6, 4, 1);
        points[4] = new LxPointBean(1, 11006, 3, 0);
        lx[4] = new XSkillLx(points[4], 6, 5, 1);
        points[5] = new LxPointBean(1, 11007, 3, 0);
        lx[5] = new XSkillLx(points[5], 6, 6, 1);
        points[6] = new LxPointBean(2, 11008, 3, 0);
        lx[6] = new XSkillLx(points[6], 4, 1, 1);
        points[7] = new LxPointBean(2, 11009, 3, 0);
        lx[7] = new XSkillLx(points[7], 4, 2, 1);
        points[8] = new LxPointBean(2, 11010, 3, 0);
        lx[8] = new XSkillLx(points[8], 4, 3, 1);
        points[9] = new LxPointBean(2, 11011, 3, 0);
        lx[9] = new XSkillLx(points[9], 4, 4, 1);
        points[10] = new LxPointBean(3, 11012, 3, 0);
        lx[10] = new XSkillLx(points[10], 2, 1, 1);
        points[11] = new LxPointBean(3, 11013, 3, 0);
        lx[11] = new XSkillLx(points[11], 2, 2, 1);
        points[12] = new LxPointBean(4, 11014, 3, 0);
        lx[12] = new XSkillLx(points[12], 2, 1, 1);
        points[13] = new LxPointBean(4, 11015, 3, 0);
        lx[13] = new XSkillLx(points[13], 2, 2, 1);
        points[14] = new LxPointBean(5, 11016, 3, 0);
        lx[14] = new XSkillLx(points[14], 2, 1, 1);
        points[15] = new LxPointBean(5, 11017, 3, 0);
        lx[15] = new XSkillLx(points[15], 2, 2, 1);
        points[16] = new LxPointBean(6, 11018, 4, 0);
        lx[16] = new XSkillLx(points[16], 2, 1, 1);
        points[17] = new LxPointBean(6, 11019, 4, 0);
        lx[17] = new XSkillLx(points[17], 2, 2, 1);
        points[18] = new LxPointBean(7, 11020, 5, 0);
        lx[18] = new XSkillLx(points[18], 6, 1, 1);
        points[19] = new LxPointBean(7, 11021, 5, 0);
        lx[19] = new XSkillLx(points[19], 6, 2, 1);
        points[20] = new LxPointBean(7, 11022, 5, 0);
        lx[20] = new XSkillLx(points[20], 6, 3, 1);
        points[21] = new LxPointBean(7, 11023, 30, 0);
        lx[21] = new XSkillLx(points[21], 6, 4, 1);
        points[22] = new LxPointBean(7, 11024, 30, 0);
        lx[22] = new XSkillLx(points[22], 6, 5, 1);
        points[23] = new LxPointBean(7, 11025, 30, 0);
        lx[23] = new XSkillLx(points[23], 6, 6, 1);
        return lx;
    }
    
    public XSkillLx[] getFashu() {
        XSkillLx[] lx = new XSkillLx[23];
        LxPointBean[] points = new LxPointBean[23];
        points[0] = new LxPointBean(1, 11001, 3, 0);
        lx[0] = new XSkillLx(points[0], 6, 1, 2);
        points[1] = new LxPointBean(1, 11004, 3, 0);
        lx[1] = new XSkillLx(points[1], 6, 2, 2);
        points[2] = new LxPointBean(1, 11002, 3, 0);
        lx[2] = new XSkillLx(points[2], 6, 3, 2);
        points[3] = new LxPointBean(1, 11005, 3, 0);
        lx[3] = new XSkillLx(points[3], 6, 4, 2);
        points[4] = new LxPointBean(1, 11007, 3, 0);
        lx[4] = new XSkillLx(points[4], 6, 5, 2);
        points[5] = new LxPointBean(1, 11026, 3, 0);
        lx[5] = new XSkillLx(points[5], 6, 6, 2);
        points[6] = new LxPointBean(2, 11027, 3, 0);
        lx[6] = new XSkillLx(points[6], 3, 1, 2);
        points[7] = new LxPointBean(2, 11028, 4, 0);
        lx[7] = new XSkillLx(points[7], 3, 2, 2);
        points[8] = new LxPointBean(2, 11029, 4, 0);
        lx[8] = new XSkillLx(points[8], 3, 3, 2);
        points[9] = new LxPointBean(3, 11031, 3, 0);
        lx[9] = new XSkillLx(points[9], 2, 1, 2);
        points[10] = new LxPointBean(3, 11032, 3, 0);
        lx[10] = new XSkillLx(points[10], 2, 2, 2);
        points[11] = new LxPointBean(4, 11033, 3, 0);
        lx[11] = new XSkillLx(points[11], 2, 1, 2);
        points[12] = new LxPointBean(4, 11034, 3, 0);
        lx[12] = new XSkillLx(points[12], 2, 2, 2);
        points[13] = new LxPointBean(5, 11035, 3, 0);
        lx[13] = new XSkillLx(points[13], 2, 1, 2);
        points[14] = new LxPointBean(5, 11030, 3, 0);
        lx[14] = new XSkillLx(points[14], 2, 2, 2);
        points[15] = new LxPointBean(6, 11036, 4, 0);
        lx[15] = new XSkillLx(points[15], 2, 1, 2);
        points[16] = new LxPointBean(6, 11037, 4, 0);
        lx[16] = new XSkillLx(points[16], 2, 2, 2);
        points[17] = new LxPointBean(7, 11039, 5, 0);
        lx[17] = new XSkillLx(points[17], 6, 1, 2);
        points[18] = new LxPointBean(7, 11040, 5, 0);
        lx[18] = new XSkillLx(points[18], 6, 2, 2);
        points[19] = new LxPointBean(7, 11041, 5, 0);
        lx[19] = new XSkillLx(points[19], 6, 3, 2);
        points[20] = new LxPointBean(7, 11042, 30, 0);
        lx[20] = new XSkillLx(points[20], 6, 4, 2);
        points[21] = new LxPointBean(7, 11043, 30, 0);
        lx[21] = new XSkillLx(points[21], 6, 5, 2);
        points[22] = new LxPointBean(7, 11044, 30, 0);
        lx[22] = new XSkillLx(points[22], 6, 6, 2);
        return lx;
    }
    
    public XSkillLx[] getFuzhu() {
        XSkillLx[] lx = new XSkillLx[23];
        LxPointBean[] points = new LxPointBean[23];
        points[0] = new LxPointBean(1, 11001, 3, 0);
        lx[0] = new XSkillLx(points[0], 6, 1, 3);
        points[1] = new LxPointBean(1, 11004, 3, 0);
        lx[1] = new XSkillLx(points[1], 6, 2, 3);
        points[2] = new LxPointBean(1, 11002, 3, 0);
        lx[2] = new XSkillLx(points[2], 6, 3, 3);
        points[3] = new LxPointBean(1, 11005, 3, 0);
        lx[3] = new XSkillLx(points[3], 6, 4, 3);
        points[4] = new LxPointBean(1, 11045, 3, 0);
        lx[4] = new XSkillLx(points[4], 6, 5, 3);
        points[5] = new LxPointBean(1, 11046, 3, 0);
        lx[5] = new XSkillLx(points[5], 6, 6, 3);
        points[6] = new LxPointBean(2, 11047, 3, 0);
        lx[6] = new XSkillLx(points[6], 3, 1, 3);
        points[7] = new LxPointBean(2, 11048, 5, 0);
        lx[7] = new XSkillLx(points[7], 3, 2, 3);
        points[8] = new LxPointBean(2, 11049, 5, 0);
        lx[8] = new XSkillLx(points[8], 3, 3, 3);
        points[9] = new LxPointBean(3, 11050, 5, 0);
        lx[9] = new XSkillLx(points[9], 2, 1, 3);
        points[10] = new LxPointBean(3, 11051, 5, 0);
        lx[10] = new XSkillLx(points[10], 2, 2, 3);
        points[11] = new LxPointBean(4, 11052, 3, 0);
        lx[11] = new XSkillLx(points[11], 2, 1, 3);
        points[12] = new LxPointBean(4, 11053, 3, 0);
        lx[12] = new XSkillLx(points[12], 2, 2, 3);
        points[13] = new LxPointBean(5, 11054, 5, 0);
        lx[13] = new XSkillLx(points[13], 2, 1, 3);
        points[14] = new LxPointBean(5, 11055, 5, 0);
        lx[14] = new XSkillLx(points[14], 2, 2, 3);
        points[15] = new LxPointBean(6, 11056, 4, 0);
        lx[15] = new XSkillLx(points[15], 2, 1, 3);
        points[16] = new LxPointBean(6, 11057, 4, 0);
        lx[16] = new XSkillLx(points[16], 2, 2, 3);
        points[17] = new LxPointBean(7, 11058, 5, 0);
        lx[17] = new XSkillLx(points[17], 6, 1, 3);
        points[18] = new LxPointBean(7, 11059, 5, 0);
        lx[18] = new XSkillLx(points[18], 6, 2, 3);
        points[19] = new LxPointBean(7, 11060, 5, 0);
        lx[19] = new XSkillLx(points[19], 6, 3, 3);
        points[20] = new LxPointBean(7, 11061, 30, 0);
        lx[20] = new XSkillLx(points[20], 6, 4, 3);
        points[21] = new LxPointBean(7, 11062, 30, 0);
        lx[21] = new XSkillLx(points[21], 6, 5, 3);
        points[22] = new LxPointBean(7, 11063, 30, 0);
        lx[22] = new XSkillLx(points[22], 6, 6, 3);
        return lx;
    }
    
    public void openSkill() {
        XSkillLx[] skillLxes = null;
        switch (this.panelType) {
            case 1: {
                skillLxes = this.wuli;
                break;
            }
            case 2: {
                skillLxes = this.fashu;
                break;
            }
            case 3: {
                skillLxes = this.fuzhu;
                break;
            }
        }
        if (skillLxes != null) {
            String lingxi = UserMessUntil.getChosePetMes().getLingxi();
            if (StringUtil.isNullOrEmpty(lingxi)) {
                lingxi = "Lx=0&Lv=0&Point=0&Open=11001_0|11002_0|11003_0|11004_0|11005_0|11006_0|11007_0|11008_0|11009_0|11010_0|11026_0|11027_0|11028_0|11029_0|11045_0|11046_0|11047_0|11048_0|11049_0&10";
            }
            String[] param = lingxi.split("&");
            if (param.length != 5) {
                return;
            }
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < skillLxes.length; ++i) {
                if (skillLxes[i].icon5 != null) {
                    if (buffer.length() > 0) {
                        buffer.append("_");
                    }
                    buffer.append(skillLxes[i].lxPointBean.getTableId());
                }
            }
            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.OpenLingXi, UserMessUntil.getChosePetMes().getSid() + "&" + buffer.toString(), "#Y消耗#R所有#G灵犀丹#Y为#G" + UserMessUntil.getChosePetMes().getSummoningname() + "#Y开启技能格？");
        }
    }
    
    public String getSkillMsg(Skill skill, XSkillLx xSkillLx) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#cffffff【等级】\t" + xSkillLx.getLv() + "/" + xSkillLx.getMaxLv());
        buffer.append("#r#cffffff【技能类别】\t被动");
        buffer.append("#r#G" + calLxNumber(skill.getRemark(), xSkillLx.getLv()));
        buffer.append("#r #r#cffffff学习条件:");
        buffer.append("#r#Y召唤兽要达到#R点化" + skill.getSkilllevel() + "#Y级");
        if (StringUtils.isNotEmpty(skill.getDielectric()) && !"0.0".equals(skill.getDielectric())) {
            buffer.append("#r#Y需已分配点数#R" + skill.getDielectric().split("\\.")[0] + "#Y点");
        }
        if (StringUtils.isNotEmpty(skill.getSkillralation())) {
            buffer.append("#r #r#Y与#R" + UserMessUntil.getSkillId(skill.getSkillralation()).getSkillname() + "#Y互斥");
        }
        if (Integer.parseInt(skill.getSkillid()) >= 11020 && Integer.parseInt(skill.getSkillid()) <= 11022) {
            buffer.append("#r #r#Y可同时修炼#R一往无前、有备无患、将功补过#Y中的两种技能");
        }
        else if (Integer.parseInt(skill.getSkillid()) >= 11023 && Integer.parseInt(skill.getSkillid()) <= 11025) {
            buffer.append("#r #r#Y可同时修炼#R奋不顾身、卷土重来、惊涛骇浪#Y中的两种技能");
        }
        else if (Integer.parseInt(skill.getSkillid()) >= 11039 && Integer.parseInt(skill.getSkillid()) <= 11041) {
            buffer.append("#r #r#Y可同时修炼#R一飞冲天、展翅欲飞、青云直上#Y中的两种技能");
        }
        else if (Integer.parseInt(skill.getSkillid()) >= 11042 && Integer.parseInt(skill.getSkillid()) <= 11044) {
            buffer.append("#r #r#Y可同时修炼#R大开杀戒、锥心刺骨、哀兵必败#Y中的两种技能");
        }
        else if (Integer.parseInt(skill.getSkillid()) >= 11058 && Integer.parseInt(skill.getSkillid()) <= 11060) {
            buffer.append("#r #r#Y可同时修炼#R有仇必报、春色满园、步步相逼#Y中的两种技能");
        }
        else if (Integer.parseInt(skill.getSkillid()) >= 11061 && Integer.parseInt(skill.getSkillid()) <= 11063) {
            buffer.append("#r #r#Y可同时修炼#R飘然出尘、碧荷凝露、焕然新生#Y中的两种技能");
        }
        if (xSkillLx.getLv() != 0 && xSkillLx.getLv() < xSkillLx.getMaxLv()) {
            buffer.append("#r #r#cffffff下一等级:");
            buffer.append("#r#G" + calLxNumber(skill.getRemark(), xSkillLx.getLv() + 1));
        }
        buffer.append("");
        return buffer.toString();
    }
    
    public static String calLxNumber(String msg, int lvl) {
        Matcher mat = Pattern.compile("<([^>]*)>").matcher(msg);
        lvl = ((lvl > 0) ? (lvl - 1) : lvl);
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (mat.find()) {
            String str = mat.group();
            str = str.replaceAll("<", "").replaceAll(">", "");
            if (str.indexOf("+") > -1) {
                String[] num = str.split("\\+");
                if (num.length == 2) {
                    double s = Double.parseDouble(num[0]);
                    double e = Double.parseDouble(num[1]);
                    String txt = "#R" + (s + e * (double)lvl + "").split("\\.")[0] + "#G";
                    sb.append(msg.substring(idx, mat.start()));
                    sb.append(txt);
                    idx = mat.end();
                }
                else {
                    continue;
                }
            }
            else if (str.indexOf("-") > -1) {
                String[] num = str.split("-");
                if (num.length == 2) {
                    double s = Double.parseDouble(num[0]);
                    double e = Double.parseDouble(num[1]);
                    String txt = "#R" + (s - e * (double)lvl + "").split("\\.")[0] + "#G";
                    sb.append(msg.substring(idx, mat.start()));
                    sb.append(txt);
                    idx = mat.end();
                }
                else {
                    continue;
                }
            }
            else {
                continue;
            }
        }
        sb.append(msg.substring(idx));
        return sb.toString();
    }
    
    public void changePoint(XSkillLx xSkilllx, boolean is) {
        if (is) {
            if ((int)xSkilllx.lxPointBean.getNowPoint() >= (int)xSkilllx.lxPointBean.getMaxPoint()) {
                ZhuFrame.getZhuJpanel().addPrompt2("此技能已达到修炼上限");
                return;
            }
            if (this.dianshu < 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("灵犀点数不足");
                return;
            }
            RoleSummoning pet = UserMessUntil.getChosePetMes();
            int lv = (int)pet.getGrade() - 544;
            if (!StringUtil.isNullOrEmpty(xSkilllx.getSkill().getSkilllevel()) && !xSkilllx.getSkill().getSkilllevel().equals("0") && lv < Integer.parseInt(xSkilllx.getSkill().getSkilllevel())) {
                ZhuFrame.getZhuJpanel().addPrompt2("召唤兽等级不满足条件");
                return;
            }
            if (xSkilllx.isLock()) {
                ZhuFrame.getZhuJpanel().addPrompt2("#R技能栏已锁");
                return;
            }
            if (Integer.parseInt(LxPanel.lingxidian.getText()) == 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("召唤兽灵犀点数不足");
                return;
            }
            int tA = (xSkilllx.getType() + 1) % 3;
            int tB = (xSkilllx.getType() + 2) % 3;
            if (this.getDianshu(tA) > 0 || this.getDianshu(tB) > 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你已经选择了其他类型的灵犀技能，如果要更新请先清空其他类型灵犀技能的点数");
                return;
            }
            Skill skill = xSkilllx.getSkill();
            if (StringUtils.isNotEmpty(skill.getDielectric()) && !"0.0".equals(skill.getDielectric())) {
                int point = Integer.parseInt(skill.getDielectric().split("\\.")[0]);
                if (point > this.getQianzhi(xSkilllx)) {
                    ZhuFrame.getZhuJpanel().addPrompt2("前置点数不足#R" + point + "#Y点！");
                    return;
                }
            }
            if (StringUtils.isNotEmpty(skill.getSkillralation()) && this.getCountTemp(Integer.parseInt(skill.getSkillralation()), xSkilllx.getType()) > 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("此技能与#R" + UserMessUntil.getSkillId(skill.getSkillralation()).getSkillname() + "#Y为互斥关系，无法同时修炼");
                return;
            }
            if ((boolean)this.getFrontSkill((int)xSkilllx.lxPointBean.getTableId(), xSkilllx.getType())) {
                if (xSkilllx.getType() == 1) {
                    if ((int)xSkilllx.lxPointBean.getTableId() < 11020 && (int)xSkilllx.lxPointBean.getTableId() % 2 == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 2 + "").getSkillname() + "#Y或#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 1 + "").getSkillname() + "#Y！");
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() < 11020 && (int)xSkilllx.lxPointBean.getTableId() % 2 == 1) {
                        ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 2 + "").getSkillname() + "#Y或#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 3 + "").getSkillname() + "#Y！");
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == 11020) {
                        ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 2 + "").getSkillname() + "、" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 1 + "").getSkillname() + "#Y！");
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == 11021) {
                        ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 2 + "").getSkillname() + "、" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 3 + "").getSkillname() + "#Y！");
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == 11022) {
                        ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 3 + "").getSkillname() + "、" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 4 + "").getSkillname() + "#Y！");
                    }
                }
                else if (xSkilllx.getType() == 2) {
                    if ((int)xSkilllx.lxPointBean.getTableId() == 11030) {
                        ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId("11033").getSkillname() + "#Y或#R" + UserMessUntil.getSkillId("11034").getSkillname() + "#Y！");
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == 11036 || (int)xSkilllx.lxPointBean.getTableId() == 11037) {
                        ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId("11030").getSkillname() + "#Y或#R" + UserMessUntil.getSkillId("11035").getSkillname() + "#Y！");
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() < 11039 && (int)xSkilllx.lxPointBean.getTableId() % 2 == 1) {
                        ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 2 + "").getSkillname() + "#Y或#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 1 + "").getSkillname() + "#Y！");
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() < 11039 && (int)xSkilllx.lxPointBean.getTableId() % 2 == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 2 + "").getSkillname() + "#Y或#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 3 + "").getSkillname() + "#Y！");
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == 11039) {
                        ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 2 + "").getSkillname() + "、" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 3 + "").getSkillname() + "#Y！");
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == 11040) {
                        ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 3 + "").getSkillname() + "、" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 4 + "").getSkillname() + "#Y！");
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == 11041) {
                        ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 4 + "").getSkillname() + "、" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 5 + "").getSkillname() + "#Y！");
                    }
                }
                else if ((int)xSkilllx.lxPointBean.getTableId() < 11058 && (int)xSkilllx.lxPointBean.getTableId() % 2 == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 2 + "").getSkillname() + "#Y或#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 1 + "").getSkillname() + "#Y！");
                }
                else if ((int)xSkilllx.lxPointBean.getTableId() < 11058 && (int)xSkilllx.lxPointBean.getTableId() % 2 == 1) {
                    ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 2 + "").getSkillname() + "#Y或#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 3 + "").getSkillname() + "#Y！");
                }
                else if ((int)xSkilllx.lxPointBean.getTableId() == 11058) {
                    ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 2 + "").getSkillname() + "、" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 1 + "").getSkillname() + "#Y！");
                }
                else if ((int)xSkilllx.lxPointBean.getTableId() == 11059) {
                    ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 2 + "").getSkillname() + "、" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 3 + "").getSkillname() + "#Y！");
                }
                else if ((int)xSkilllx.lxPointBean.getTableId() == 11060) {
                    ZhuFrame.getZhuJpanel().addPrompt2("需要前置技能#R" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 3 + "").getSkillname() + "、" + UserMessUntil.getSkillId((int)xSkilllx.lxPointBean.getTableId() - 4 + "").getSkillname() + "#Y！");
                }
                return;
            }
            else {
                if (((int)xSkilllx.lxPointBean.getTableId() == 11023 || (int)xSkilllx.lxPointBean.getTableId() == 11024 || (int)xSkilllx.lxPointBean.getTableId() == 11025) && (int)UserMessUntil.getChosePetMes().getPower() < 450) {
                    Skill v = UserMessUntil.getSkillId(xSkilllx.lxPointBean.getTableId() + "");
                    ZhuFrame.getZhuJpanel().addPrompt("学习#R" + v.getSkillname() + " #Y需要宠物力量达到450！");
                    return;
                }
                if (!this.selectMax((int)xSkilllx.lxPointBean.getTableId(), xSkilllx.getType())) {
                    ZhuFrame.getZhuJpanel().addPrompt2("超出可同时修炼限制，此召唤兽不可修炼此技能");
                    return;
                }
                --this.dianshu;
                xSkilllx.setPoint((int)xSkilllx.lxPointBean.getNowPoint() + 1);
                LxPanel.lingxidian.setText(this.dianshu + "");
                Music.addyinxiao("按钮.mp3");
                Rectangle bounds = this.getBounds();
                int x = (int)bounds.getX();
                int y = (int)bounds.getY();
                this.setBounds(x - 2, y - 1, this.getWidth(), this.getHeight());
            }
        }
        else {
            if ((int)xSkilllx.lxPointBean.getNowPoint() <= 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("没有可以减少的点数了");
                return;
            }
            if (this.getCount((int)xSkilllx.lxPointBean.getTableId()) >= (int)xSkilllx.lxPointBean.getNowPoint()) {
                ZhuFrame.getZhuJpanel().addPrompt2("此点数已被保存，如要减少清使用洗点功能重新分配灵犀点");
                return;
            }
            if (!this.jian(xSkilllx.getCol(), xSkilllx.getType())) {
                ZhuFrame.getZhuJpanel().addPrompt2("此技能被其他技能依赖，请先清空后面有前置点数需求的技能点数");
                return;
            }
            ++this.dianshu;
            xSkilllx.setPoint((int)xSkilllx.lxPointBean.getNowPoint() - 1);
            LxPanel.lingxidian.setText(this.dianshu + "");
        }
    }
    
    public Boolean getFrontSkill(int skillId, int type) {
        switch (type) {
            case 1: {
                for (XSkillLx xSkilllx : this.wuli) {
                    if (skillId >= 11014 && skillId < 11020 && ((int)xSkilllx.lxPointBean.getTableId() == skillId - 1 || (int)xSkilllx.lxPointBean.getTableId() == skillId - 2 || (skillId > 11014 && (int)xSkilllx.lxPointBean.getTableId() == skillId - 3))) {
                        if (xSkilllx.getLv() > 0) {
                            return Boolean.valueOf(false);
                        }
                    }
                    else {
                        if (skillId <= 11013 || skillId >= 11023) {
                            return Boolean.valueOf(false);
                        }
                        if (skillId >= 11020 && skillId <= 11023 && ((int)xSkilllx.lxPointBean.getTableId() == 11018 || (int)xSkilllx.lxPointBean.getTableId() == 11019) && xSkilllx.getLv() > 0) {
                            return Boolean.valueOf(false);
                        }
                    }
                }
                break;
            }
            case 2: {
                for (XSkillLx xSkilllx : this.fashu) {
                    if (skillId == 11030 && ((int)xSkilllx.lxPointBean.getTableId() == 11033 || (int)xSkilllx.lxPointBean.getTableId() == 11034) && xSkilllx.getLv() > 0) {
                        return Boolean.valueOf(false);
                    }
                }
                if (skillId == 11030) {
                    return Boolean.valueOf(true);
                }
                for (XSkillLx xSkilllx : this.fashu) {
                    if ((skillId == 11036 || skillId == 11037) && ((int)xSkilllx.lxPointBean.getTableId() == 11030 || (int)xSkilllx.lxPointBean.getTableId() == 11035) && xSkilllx.getLv() > 0) {
                        return Boolean.valueOf(false);
                    }
                }
                if (skillId == 11036 || skillId == 11037) {
                    return Boolean.valueOf(true);
                }
                XSkillLx[] fashu3 = this.fashu;
                int length4 = fashu3.length;
                int l = 0;
                while (l < length4) {
                    XSkillLx xSkilllx = fashu3[l];
                    if (skillId == 11030 && ((int)xSkilllx.lxPointBean.getTableId() == 11033 || (int)xSkilllx.lxPointBean.getTableId() == 11034)) {
                        if (xSkilllx.getLv() > 0) {
                            return Boolean.valueOf(false);
                        }
                        return Boolean.valueOf(true);
                    }
                    else {
                        if (skillId >= 11033 && skillId <= 11035 && ((int)xSkilllx.lxPointBean.getTableId() == skillId - 1 || (int)xSkilllx.lxPointBean.getTableId() == skillId - 2 || (skillId > 11033 && (int)xSkilllx.lxPointBean.getTableId() == skillId - 3))) {
                            if (xSkilllx.getLv() > 0) {
                                return Boolean.valueOf(false);
                            }
                        }
                        else {
                            if (skillId <= 11032 || skillId >= 11042) {
                                return Boolean.valueOf(false);
                            }
                            if (skillId >= 11039 && skillId <= 11041 && ((int)xSkilllx.lxPointBean.getTableId() == 11036 || (int)xSkilllx.lxPointBean.getTableId() == 11037) && xSkilllx.getLv() > 0) {
                                return Boolean.valueOf(false);
                            }
                        }
                        ++l;
                    }
                }
                break;
            }
            case 3: {
                for (XSkillLx xSkilllx : this.fuzhu) {
                    if (skillId >= 11051 && skillId < 11058 && ((int)xSkilllx.lxPointBean.getTableId() == skillId - 1 || (int)xSkilllx.lxPointBean.getTableId() == skillId - 2 || (skillId > 11014 && (int)xSkilllx.lxPointBean.getTableId() == skillId - 3))) {
                        if (xSkilllx.getLv() > 0) {
                            return Boolean.valueOf(false);
                        }
                    }
                    else {
                        if (skillId <= 11050 || skillId >= 11061) {
                            return Boolean.valueOf(false);
                        }
                        if (skillId >= 11058 && skillId <= 11060 && ((int)xSkilllx.lxPointBean.getTableId() == 11056 || (int)xSkilllx.lxPointBean.getTableId() == 11057) && xSkilllx.getLv() > 0) {
                            return Boolean.valueOf(false);
                        }
                    }
                }
                break;
            }
        }
        return Boolean.valueOf(true);
    }
    
    public String getLingxi() {
        if (this.panelType == 0) {
            return "";
        }
        StringBuilder str = new StringBuilder();
        switch (this.panelType) {
            case 1: {
                for (XSkillLx xSkilllx : this.wuli) {
                    if (xSkilllx.getLv() > 0) {
                        if (str.toString().length() == 0) {
                            str.append("Lx=" + this.panelType + "&");
                        }
                        else {
                            str.append("|");
                        }
                        str.append(xSkilllx.lxPointBean.getTableId() + "_" + xSkilllx.getLv());
                    }
                }
                break;
            }
            case 2: {
                for (XSkillLx xSkilllx : this.fashu) {
                    if (xSkilllx.getLv() > 0) {
                        if (str.toString().length() == 0) {
                            str.append("Lx=" + this.panelType + "&");
                        }
                        else {
                            str.append("|");
                        }
                        str.append(xSkilllx.lxPointBean.getTableId() + "_" + xSkilllx.getLv());
                    }
                }
                break;
            }
            case 3: {
                for (XSkillLx xSkilllx : this.fuzhu) {
                    if (xSkilllx.getLv() > 0) {
                        if (str.toString().length() == 0) {
                            str.append("Lx=" + this.panelType + "&");
                        }
                        else {
                            str.append("|");
                        }
                        str.append(xSkilllx.lxPointBean.getTableId() + "_" + xSkilllx.getLv());
                    }
                }
                break;
            }
        }
        return str.toString();
    }
    
    public boolean jian(int col, int type) {
        if (col == 7) {
            return true;
        }
        switch (type) {
            case 1: {
                for (XSkillLx xSkilllx : this.wuli) {
                    if (col < xSkilllx.getCol() && xSkilllx.getLv() > 0) {
                        Skill skill = xSkilllx.getSkill();
                        if (StringUtils.isNotEmpty(skill.getDielectric()) && !"0.0".equals(skill.getDielectric())) {
                            int point = Integer.parseInt(skill.getDielectric().split("\\.")[0]);
                            if (point > this.getQianzhi(xSkilllx) - 1) {
                                return false;
                            }
                        }
                    }
                }
                break;
            }
            case 2: {
                for (XSkillLx xSkilllx : this.fashu) {
                    if (col < xSkilllx.getCol() && xSkilllx.getLv() > 0) {
                        Skill skill = xSkilllx.getSkill();
                        if (StringUtils.isNotEmpty(skill.getDielectric()) && !"0.0".equals(skill.getDielectric())) {
                            int point = Integer.parseInt(skill.getDielectric().split("\\.")[0]);
                            if (point > this.getQianzhi(xSkilllx) - 1) {
                                return false;
                            }
                        }
                    }
                }
                break;
            }
            case 3: {
                for (XSkillLx xSkilllx : this.fuzhu) {
                    if (col < xSkilllx.getCol() && xSkilllx.getLv() > 0) {
                        Skill skill = xSkilllx.getSkill();
                        if (StringUtils.isNotEmpty(skill.getDielectric()) && !"0.0".equals(skill.getDielectric())) {
                            int point = Integer.parseInt(skill.getDielectric().split("\\.")[0]);
                            if (point > this.getQianzhi(xSkilllx) - 1) {
                                return false;
                            }
                        }
                    }
                }
                break;
            }
        }
        return true;
    }
    
    public int getQianzhi(XSkillLx lx) {
        int col = lx.getCol();
        int sum = 0;
        switch (lx.getType()) {
            case 1: {
                for (XSkillLx xSkilllx : this.wuli) {
                    sum += xSkilllx.getLv();
                }
                break;
            }
            case 2: {
                for (XSkillLx xSkilllx : this.fashu) {
                    sum += xSkilllx.getLv();
                }
                break;
            }
            case 3: {
                for (XSkillLx xSkilllx : this.fuzhu) {
                    sum += xSkilllx.getLv();
                }
                break;
            }
        }
        return sum;
    }
    
    public boolean selectMax(int skillId, int type) {
        int idx = -1;
        int upCount = 0;
        int downCount = 0;
        switch (type) {
            case 1: {
                int i = 0;
                while (i < LxPanel.W.length) {
                    if (LxPanel.W[i] == skillId) {
                        idx = i;
                        break;
                    }
                    else {
                        ++i;
                    }
                }
                if (idx == -1) {
                    return true;
                }
                for (XSkillLx xSkilllx : this.wuli) {
                    if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.W[0] && idx != 0 && xSkilllx.getLv() > 0) {
                        ++upCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.W[1] && idx != 1 && xSkilllx.getLv() > 0) {
                        ++upCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.W[2] && idx != 2 && xSkilllx.getLv() > 0) {
                        ++upCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.W[3] && idx != 3 && xSkilllx.getLv() > 0) {
                        ++downCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.W[4] && idx != 4 && xSkilllx.getLv() > 0) {
                        ++downCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.W[5] && idx != 5 && xSkilllx.getLv() > 0) {
                        ++downCount;
                    }
                }
                break;
            }
            case 2: {
                int i = 0;
                while (i < LxPanel.F.length) {
                    if (LxPanel.F[i] == skillId) {
                        idx = i;
                        break;
                    }
                    else {
                        ++i;
                    }
                }
                if (idx == -1) {
                    return true;
                }
                for (XSkillLx xSkilllx : this.fashu) {
                    if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.F[0] && idx != 0 && xSkilllx.getLv() > 0) {
                        ++upCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.F[1] && idx != 1 && xSkilllx.getLv() > 0) {
                        ++upCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.F[2] && idx != 2 && xSkilllx.getLv() > 0) {
                        ++upCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.F[3] && idx != 3 && xSkilllx.getLv() > 0) {
                        ++downCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.F[4] && idx != 4 && xSkilllx.getLv() > 0) {
                        ++downCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.F[5] && idx != 5 && xSkilllx.getLv() > 0) {
                        ++downCount;
                    }
                }
                break;
            }
            case 3: {
                int i = 0;
                while (i < LxPanel.Z.length) {
                    if (LxPanel.Z[i] == skillId) {
                        idx = i;
                        break;
                    }
                    else {
                        ++i;
                    }
                }
                if (idx == -1) {
                    return true;
                }
                for (XSkillLx xSkilllx : this.fuzhu) {
                    if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.Z[0] && idx != 0 && xSkilllx.getLv() > 0) {
                        ++upCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.Z[1] && idx != 1 && xSkilllx.getLv() > 0) {
                        ++upCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.Z[2] && idx != 2 && xSkilllx.getLv() > 0) {
                        ++upCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.Z[3] && idx != 3 && xSkilllx.getLv() > 0) {
                        ++downCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.Z[4] && idx != 4 && xSkilllx.getLv() > 0) {
                        ++downCount;
                    }
                    else if ((int)xSkilllx.lxPointBean.getTableId() == LxPanel.Z[5] && idx != 5 && xSkilllx.getLv() > 0) {
                        ++downCount;
                    }
                }
                break;
            }
            default: {
                return false;
            }
        }
        if (idx < 3) {
            return upCount <= 1 && downCount == 0;
        }
        return downCount <= 1;
    }
    
    public int getDianshu(int type) {
        int dianshu = 0;
        switch (type) {
            case 1: {
                for (XSkillLx xSkilllx : this.wuli) {
                    dianshu += xSkilllx.getLv();
                }
                break;
            }
            case 2: {
                for (XSkillLx xSkilllx : this.fashu) {
                    dianshu += xSkilllx.getLv();
                }
                break;
            }
            case 3: {
                for (XSkillLx xSkilllx : this.fuzhu) {
                    dianshu += xSkilllx.getLv();
                }
                break;
            }
        }
        return dianshu;
    }
    
    public int getCountTemp(int skillId, int type) {
        switch (type) {
            case 1: {
                for (XSkillLx xSkilllx : this.wuli) {
                    if ((int)xSkilllx.lxPointBean.getTableId() == skillId) {
                        return xSkilllx.getLv();
                    }
                }
                break;
            }
            case 2: {
                for (XSkillLx xSkilllx : this.fashu) {
                    if ((int)xSkilllx.lxPointBean.getTableId() == skillId) {
                        return xSkilllx.getLv();
                    }
                }
                break;
            }
            case 3: {
                for (XSkillLx xSkilllx : this.fuzhu) {
                    if ((int)xSkilllx.lxPointBean.getTableId() == skillId) {
                        return xSkilllx.getLv();
                    }
                }
                break;
            }
        }
        return 0;
    }
    
    public int getCount(int skillId) {
        String lingxi = UserMessUntil.getChosePetMes().getLingxi();
        if (StringUtil.isNullOrEmpty(lingxi)) {
            return 0;
        }
        String[] param = lingxi.split("&");
        if (param.length != 4) {
            return 0;
        }
        String jn = param[3].split("=")[1];
        String[] jineng;
        for (String idStr : jineng = jn.split("\\|")) {
            String[] ids_count = idStr.split("_");
            int id = Integer.parseInt(ids_count[0]);
            int count = Integer.parseInt(ids_count[1]);
            if (id == skillId) {
                return count;
            }
        }
        return 0;
    }
    
    public void init() {
        String lingxi = UserMessUntil.getChosePetMes().getLingxi();
        if (StringUtil.isNullOrEmpty(lingxi)) {
            lingxi = "Lx=0&Lv=0&Point=0&Open=11001_0|11002_0|11003_0|11004_0|11005_0|11006_0|11007_0|11008_0|11009_0|11010_0|11011_0|11026_0|11027_0|11028_0|11029_0|11045_0|11046_0|11047_0|11048_0|11049_0&10";
        }
        String[] param = lingxi.split("&");
        if (param.length != 5) {
            return;
        }
        if (this.wuli != null) {
            for (XSkillLx lx : this.wuli) {
                lx.reset();
            }
        }
        if (this.fashu != null) {
            for (XSkillLx lx : this.fashu) {
                lx.reset();
            }
        }
        if (this.fuzhu != null) {
            for (XSkillLx lx : this.fuzhu) {
                lx.reset();
            }
        }
        int type = Integer.parseInt(param[0].split("=")[1]);
        String xl = param[1].split("=")[1];
        this.dianshu = Integer.parseInt(param[2].split("=")[1]);
        String jn = param[3].split("=")[1];
        int ds = 0;
        String[] jineng;
        for (String idStr : jineng = jn.split("\\|")) {
            String[] ids_count = idStr.split("_");
            int id = Integer.parseInt(ids_count[0]);
            int count = Integer.parseInt(ids_count[1]);
            ds += count;
            if (this.wuli == null) {
                this.wuli = this.getWuli();
                for (XSkillLx lx2 : this.wuli) {
                    this.add(lx2);
                    if ((int)lx2.lxPointBean.getTableId() == id) {
                        lx2.setPoint((type == 1) ? count : 0);
                        lx2.unlock();
                    }
                }
            }
            else {
                for (XSkillLx lx2 : this.wuli) {
                    if ((int)lx2.lxPointBean.getTableId() == id) {
                        lx2.setPoint((type == 1) ? count : 0);
                        lx2.unlock();
                    }
                }
            }
            if (this.fashu == null) {
                this.fashu = this.getFashu();
                for (XSkillLx lx2 : this.fashu) {
                    this.add(lx2);
                    if ((int)lx2.lxPointBean.getTableId() == id) {
                        lx2.setPoint((type == 2) ? count : 0);
                        lx2.unlock();
                    }
                }
            }
            else {
                for (XSkillLx lx2 : this.fashu) {
                    if ((int)lx2.lxPointBean.getTableId() == id) {
                        lx2.setPoint((type == 2) ? count : 0);
                        lx2.unlock();
                    }
                }
            }
            if (this.fuzhu == null) {
                this.fuzhu = this.getFuzhu();
                for (XSkillLx lx2 : this.fuzhu) {
                    this.add(lx2);
                    if ((int)lx2.lxPointBean.getTableId() == id) {
                        lx2.setPoint((type == 3) ? count : 0);
                        lx2.unlock();
                    }
                }
            }
            else {
                for (XSkillLx lx2 : this.fuzhu) {
                    if ((int)lx2.lxPointBean.getTableId() == id) {
                        lx2.setPoint((type == 3) ? count : 0);
                        lx2.unlock();
                    }
                }
            }
        }
        this.dianshu -= ds;
        LxPanel.lingxidian.setText(this.dianshu + "");
    }
    
    public PetLxBtn getWulixiBtn() {
        return this.wulixiBtn;
    }
    
    public void setWulixiBtn(PetLxBtn wulixiBtn) {
        this.wulixiBtn = wulixiBtn;
    }
    
    public PetLxBtn getFashuxiBtn() {
        return this.fashuxiBtn;
    }
    
    public void setFashuxiBtn(PetLxBtn fashuxiBtn) {
        this.fashuxiBtn = fashuxiBtn;
    }
    
    public PetLxBtn getFuzhuxiBtn() {
        return this.fuzhuxiBtn;
    }
    
    public void setFuzhuxiBtn(PetLxBtn fuzhuxiBtn) {
        this.fuzhuxiBtn = fuzhuxiBtn;
    }
    
    static {
        LxPanel.tcp = SpriteFactory.VloadSprite("resource/mouse/tx/tx12.tcp", null);
        W = new int[] { 11020, 11021, 11022, 11023, 11024, 11025 };
        F = new int[] { 11039, 11040, 11041, 11042, 11043, 11044 };
        Z = new int[] { 11058, 11059, 11060, 11061, 11062, 11063 };
    }
    
    class XSkillLx extends JComponent implements MouseListener
    {
        private int type;
        private LxPointBean lxPointBean;
        private ImageIcon icon1;
        private ImageIcon icon2;
        private ImageIcon icon3;
        private String value;
        private ImageIcon icon4;
        private ImageIcon icon5;
        private int px;
        private int py;
        private int maxPoint;
        private int col;
        
        public XSkillLx(LxPointBean lxPointBean, int col, int idx, int type) {
            this.type = type;
            this.lxPointBean = lxPointBean;
            this.addMouseListener(this);
            this.px = 40 + (int)lxPointBean.getLv() * 70;
            this.py = 40 + (150 - col * 25) + (idx - 1) * 50;
            this.col = (int)lxPointBean.getLv();
            this.maxPoint = (int)lxPointBean.getMaxPoint();
            this.reset();
        }
        
        public void reset() {
            this.icon1 = CutButtonImage.getImage("inkImg/background/S271.png", -1, -1);
            this.icon2 = CutButtonImage.LxSkill((int)this.lxPointBean.getTableId());
            this.setIcon4();
            this.lock();
            this.setPoint(0);
            this.setBounds(this.px, this.py, 50, 50);
        }
        
        public void lock() {
            this.icon5 = CutButtonImage.getImage("img/xy2uiimg/78_png.xy2uiimg.btn_plock.png", -1, -1);
        }
        
        public void unlock() {
            this.icon5 = null;
        }
        
        public int getCol() {
            return this.col;
        }
        
        public void setPoint(int point) {
            this.value = this.lxPointBean.setPoint(Integer.valueOf(point));
            if (this.maxPoint < 10) {
                this.icon3 = CutButtonImage.getImage("inkImg/background/S39.png", -1, -1);
            }
            else {
                this.icon3 = CutButtonImage.getImage("inkImg/background/lx.png", -1, -1);
            }
        }
        
        public int getLv() {
            return (int)this.lxPointBean.getNowPoint();
        }
        
        public int getMaxLv() {
            return (int)this.lxPointBean.getMaxPoint();
        }
        
        public Skill getSkill() {
            return UserMessUntil.getSkillId(this.lxPointBean.getTableId() + "");
        }
        
        public boolean isLock() {
            return this.icon5 != null;
        }
        
        public void setIcon4() {
            this.icon4 = CutButtonImage.getImage("img/soaringSkill/蒙版.png", -1, -1);
        }
        
        public int getType() {
            return this.type;
        }
        
        public Boolean getFrontSkill(int skillId, int type) {
            switch (type) {
                case 1: {
                    for (XSkillLx xSkilllx : LxPanel.this.wuli) {
                        if (skillId >= 11014 && skillId < 1120 && (int)xSkilllx.lxPointBean.getTableId() == skillId - 2 && xSkilllx.getLv() > 0) {
                            return Boolean.valueOf(false);
                        }
                    }
                    break;
                }
                case 2: {
                    for (XSkillLx xSkilllx : LxPanel.this.fashu) {
                        if (skillId >= 11033 && (int)xSkilllx.lxPointBean.getTableId() == skillId - 2 && xSkilllx.getLv() > 0) {
                            return Boolean.valueOf(false);
                        }
                    }
                    break;
                }
                case 3: {
                    for (XSkillLx xSkilllx : LxPanel.this.fuzhu) {
                        if (skillId >= 11051 && (int)xSkilllx.lxPointBean.getTableId() == skillId - 2 && xSkilllx.getLv() > 0) {
                            return Boolean.valueOf(false);
                        }
                    }
                    break;
                }
            }
            return Boolean.valueOf(true);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (this.icon1 != null) {
                g.drawImage(this.icon1.getImage(), 0, 0, 45, 45, this);
            }
            if (this.icon2 != null) {
                g.drawImage(this.icon2.getImage(), 4, 4, 38, 38, this);
            }
            if (this.icon3 != null) {
                g.drawImage(this.icon3.getImage(), 3, 28, this);
            }
            if (this.value != null) {
                g.setColor(Color.yellow);
                g.setFont(UIUtils.TEXT_FONT);
                g.drawString(this.value, (this.maxPoint > 10) ? ((this.value.length() > 4) ? 6 : 10) : 7, 39);
            }
            if (this.getLv() == 0 && this.icon4 != null) {
                g.drawImage(this.icon4.getImage(), 4, 4, 38, 38, this);
            }
            if (this.icon5 != null) {
                g.drawImage(this.icon5.getImage(), 15, 15, 16, 18, this);
            }
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 1) {
                if (this.icon5 != null) {
                    String lingxi = UserMessUntil.getChosePetMes().getLingxi();
                    if (StringUtil.isNullOrEmpty(lingxi)) {
                        lingxi = "Lx=0&Lv=0&Point=0&Open=11001_0|11002_0|11003_0|11004_0|11005_0|11006_0|11007_0|11008_0|11009_0|11010_0|11026_0|11027_0|11028_0|11029_0|11045_0|11046_0|11047_0|11048_0|11049_0&10";
                    }
                    String[] param = lingxi.split("&");
                    if (param.length != 5) {
                        return;
                    }
                    String jn = param[3].split("=")[1];
                    String[] jineng = jn.split("\\|");
                    Integer[] sum = { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(4), Integer.valueOf(8), Integer.valueOf(10), Integer.valueOf(12), Integer.valueOf(15), Integer.valueOf(15), Integer.valueOf(15) };
                    int type = Integer.parseInt(param[0].split("=")[1]);
                    if (type == 0) {
                        type = Integer.parseInt(param[4]) - 10;
                    }
                    else {
                        type = Integer.parseInt(param[4]) - 10 + 1;
                    }
                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.OpenLingXi, UserMessUntil.getChosePetMes().getSid() + "&" + this.lxPointBean.getTableId(), "#Y消耗#R" + sum[type] + "#Y个#G灵犀丹#Y为#G" + UserMessUntil.getChosePetMes().getSummoningname() + "#Y开启此技能格？#R（使用后结果必定为成功,扣除的灵犀丹会根据失败次数扣除总数）");
                    return;
                }
                else if (e.isControlDown()) {
                    XSkillLx source = (XSkillLx)e.getSource();
                    for (int i = 0; i < source.getMaxLv(); ++i) {
                        LxPanel.this.changePoint(this, true);
                    }
                }
                else {
                    LxPanel.this.changePoint(this, true);
                }
            }
            else if (e.getButton() == 3) {
                if (e.isControlDown()) {
                    XSkillLx source = (XSkillLx)e.getSource();
                    for (int i = 0; i < source.getMaxLv(); ++i) {
                        LxPanel.this.changePoint(this, false);
                    }
                }
                else {
                    LxPanel.this.changePoint(this, false);
                }
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            if (this.icon4 != null) {
                return;
            }
            ++this.py;
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            if (this.icon4 != null) {
                return;
            }
            --this.py;
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            Skill skill = UserMessUntil.getSkillId(this.lxPointBean.getTableId() + "");
            MsgJframe.getJframe().getJapnel().LX((skill != null) ? skill.getSkillname() : "", LxPanel.this.getSkillMsg(skill, this));
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            FormsManagement.HideForm(46);
        }
    }
}
