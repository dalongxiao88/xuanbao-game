package org.come.Jpanel;

import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import org.come.bean.LoginResult;
import org.come.until.CutButtonImage;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import java.awt.Graphics;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.mouslisten.WLLMouslisten;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import java.awt.Color;
import org.come.mouslisten.PointChangeMouslisten;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.GoodPanelBtn;
import org.soaring.btn.CharacterBtn;
import com.tool.btn.MouseStyleBtn;
import com.tool.btn.JpanelOnJalbelBtn;
import org.come.Frame.Teststatejframe;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class TeststateJpanel extends JPanel implements MouseListener
{
    private final long serialVersionUID = 1L;
    private JLabel[] lianghaos;
    private Teststatejframe teststatejframe;
    private int expsize;
    private JLabel label;
    private int level;
    private JLabel labrolename;
    private JLabel labappeleid;
    private JLabel labappellation;
    private JLabel labrace;
    private JLabel labgangs;
    private JLabel labprestige;
    private JLabel labrecord;
    private JLabel labrisegrade;
    private JLabel labrolelevel;
    private JLabel labHP;
    private JLabel labMP;
    private JLabel labAP;
    private JLabel labSP;
    private JLabel labCP;
    private JLabel labEXP;
    private JLabel labrootbone;
    private JLabel labintelligence;
    private JLabel labpower;
    private JLabel labspeed;
    private JLabel labability;
    private JLabel labpointnumber;
    private JLabel labrolehead;
    private JpanelOnJalbelBtn btnmount;
    private JpanelOnJalbelBtn wingBtn;
    private JpanelOnJalbelBtn btnsure;
    private JpanelOnJalbelBtn btnroleresistance;
    private JpanelOnJalbelBtn btndskills;
    private JpanelOnJalbelBtn btnstall;
    private JpanelOnJalbelBtn btnset;
    private JpanelOnJalbelBtn btnset1;
    private JpanelOnJalbelBtn btnSevenTwo;
    private MouseStyleBtn btnXLXP;
    private CharacterBtn nameBtn;
    private CharacterBtn idBtn;
    private CharacterBtn labappellationBtn;
    private CharacterBtn attributeBtn;
    private CharacterBtn attributeBtnMenu;
    private CharacterBtn attributeBtn1;
    private CharacterBtn attributeBtn2;
    private CharacterBtn transformationBtn;
    private CharacterBtn QBBtn;
    private CharacterBtn[] dians;
    private static GoodPanelBtn btnlock1;
    private static GoodPanelBtn btnlock;
    private JLabel labroleheadFrame;
    private JLabel labroleheadBlack;
    private ImageIcon icon2;
    private static String qhnum;
    private ImageIcon icon;
    private String style;
    
    public TeststateJpanel(Teststatejframe teststatejframe) throws Exception {
        this.dians = new CharacterBtn[10];
        this.style = MyIsif.getStyle();
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String cbkg = configure.getCbgnkg();
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(359, 450));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 0);
            offBtn.setBounds(322, 10, 25, 25);
            this.add(offBtn);
            for (int i = 0; i < this.dians.length; ++i) {
                if (i % 2 == 0) {
                    this.dians[i] = new CharacterBtn("inkImg/button/12.png", 1, 10 + i);
                }
                else {
                    this.dians[i] = new CharacterBtn("inkImg/button/11.png", 1, 10 + i);
                }
                this.dians[i].setBounds(310 + i % 2 * 14, 209 + i / 2 * 24, 12, 16);
                this.dians[i].addMouseListener(new PointChangeMouslisten(i));
                this.add(this.dians[i]);
            }
            (this.labrolename = new JLabel()).setBounds(215, 32, 120, 15);
            this.labrolename.setForeground(Color.WHITE);
            this.labrolename.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labrolename);
            (this.labappeleid = new JLabel()).setBounds(215, 58, 120, 15);
            this.labappeleid.setForeground(Color.WHITE);
            this.labappeleid.setFont(new Font("宋体", 0, 14));
            this.add(this.labappeleid);
            (this.labgangs = new JLabel()).setBounds(215, 80, 120, 15);
            this.labgangs.setForeground(Color.WHITE);
            this.labgangs.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labgangs);
            (this.labprestige = new JLabel()).setBounds(215, 105, 120, 15);
            this.labprestige.setForeground(Color.WHITE);
            this.labprestige.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labprestige);
            (this.labrecord = new JLabel()).setBounds(215, 128, 120, 15);
            this.labrecord.setForeground(Color.WHITE);
            this.labrecord.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labrecord);
            (this.labappellation = new JLabel()).setBounds(215, 150, 120, 18);
            this.labappellation.setForeground(Color.WHITE);
            this.labappellation.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labappellation);
            (this.labrace = new JLabel()).setBounds(48, 148, 110, 16);
            this.labrace.setForeground(new Color(8, 4, 8));
            this.labrace.setFont(UIUtils.TEXT_HY17);
            this.labrace.setHorizontalAlignment(0);
            this.add(this.labrace);
            (this.labrisegrade = new JLabel()).setBounds(91, 153, 55, 15);
            this.labrisegrade.setForeground(Color.WHITE);
            this.labrisegrade.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labrisegrade);
            (this.labrolelevel = new JLabel()).setBounds(80, 188, 80, 15);
            this.labrolelevel.setForeground(Color.WHITE);
            this.labrolelevel.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labrolelevel);
            (this.labHP = new JLabel()).setBounds(80, 212, 125, 15);
            this.labHP.setForeground(Color.WHITE);
            this.labHP.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labHP);
            (this.labMP = new JLabel()).setBounds(80, 236, 125, 15);
            this.labMP.setForeground(Color.WHITE);
            this.labMP.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labMP);
            (this.labAP = new JLabel()).setBounds(80, 260, 125, 15);
            this.labAP.setForeground(Color.WHITE);
            this.labAP.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labAP);
            (this.labSP = new JLabel()).setBounds(80, 284, 125, 15);
            this.labSP.setForeground(Color.WHITE);
            this.labSP.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labSP);
            (this.labCP = new JLabel()).setBounds(80, 309, 125, 15);
            this.labCP.setForeground(Color.WHITE);
            this.labCP.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labCP);
            (this.labEXP = new JLabel()).setBounds(91, 307, 125, 15);
            this.labEXP.setForeground(Color.WHITE);
            this.labEXP.setFont(new Font("微软雅黑", 1, 12));
            this.add(this.labEXP);
            (this.labrootbone = new JLabel()).setBounds(266, 210, 61, 15);
            this.labrootbone.setForeground(Color.WHITE);
            this.labrootbone.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labrootbone);
            (this.labintelligence = new JLabel()).setBounds(266, 234, 61, 15);
            this.labintelligence.setForeground(Color.WHITE);
            this.labintelligence.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labintelligence);
            (this.labpower = new JLabel()).setBounds(266, 258, 61, 15);
            this.labpower.setForeground(Color.WHITE);
            this.labpower.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labpower);
            (this.labspeed = new JLabel()).setBounds(266, 282, 61, 15);
            this.labspeed.setForeground(Color.WHITE);
            this.labspeed.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labspeed);
            (this.labability = new JLabel("0")).setBounds(266, 306, 61, 15);
            this.labability.setForeground(Color.WHITE);
            this.labability.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labability);
            (this.labpointnumber = new JLabel()).setBounds(292, 188, 41, 15);
            this.labpointnumber.setForeground(Color.WHITE);
            this.labpointnumber.setFont(new Font("微软雅黑", 1, 14));
            this.add(this.labpointnumber);
            (this.labrolehead = new JLabel()).setBounds(67, 39, 75, 100);
            this.add(this.labrolehead);
            (this.btnsure = new JpanelOnJalbelBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_BTNTEXT, 0, "确认加点", this)).setBounds(160, 405, 68, 17);
            this.add(this.btnsure);
            (this.btnXLXP = new MouseStyleBtn("inkImg/button1/B21.png", 1, "玄宝", "玄宝")).setBounds(163, 405, 99, 24);
            this.add(this.btnXLXP);
            (TeststateJpanel.btnlock = new GoodPanelBtn("inkImg/button1/B21.png", 1, "", "星 盘")).setBounds(163, 382, 99, 24);
            TeststateJpanel.btnlock.setVisible(false);
            this.add(TeststateJpanel.btnlock);
            (TeststateJpanel.btnlock1 = new GoodPanelBtn("inkImg/button1/B21.png", 1, "", "星 录")).setBounds(163, 358, 99, 24);
            TeststateJpanel.btnlock1.setVisible(false);
            this.add(TeststateJpanel.btnlock1);
            if (cbkg.equals("开")) {
                (this.btnmount = new JpanelOnJalbelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, 1, "灵 宝", this)).setBounds(204, 372, 59, 24);
                this.add(this.btnmount);
                (this.wingBtn = new JpanelOnJalbelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, 9, "翅 膀", this)).setBounds(135, 372, 59, 24);
                this.add(this.wingBtn);
                (this.btnroleresistance = new JpanelOnJalbelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, 2, "抗 性", this)).setBounds(275, 372, 59, 24);
                this.add(this.btnroleresistance);
                (this.btnSevenTwo = new JpanelOnJalbelBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, 8, "七十二变", this)).setBounds(28, 372, 99, 24);
                this.add(this.btnSevenTwo);
            }
            else {
                (this.btnSevenTwo = new JpanelOnJalbelBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, 8, "七十二变", this)).setBounds(48, 372, 99, 24);
                this.add(this.btnSevenTwo);
                (this.btnmount = new JpanelOnJalbelBtn("inkImg/button1/B21.png", 1, UIUtils.COLOR_BLACK, 1, "灵 宝", this)).setBounds(163, 372, 99, 24);
                this.add(this.btnmount);
                (this.btnroleresistance = new JpanelOnJalbelBtn("inkImg/button1/B21.png", 1, UIUtils.COLOR_BLACK, 2, "抗 性", this)).setBounds(255, 372, 99, 24);
                this.add(this.btnroleresistance);
                (this.btnset = new JpanelOnJalbelBtn("inkImg/button1/B21.png", 1, UIUtils.COLOR_BLACK, 7, "作 坊", this)).setBounds(255, 405, 99, 24);
                this.add(this.btnset);
            }
            (this.btndskills = new JpanelOnJalbelBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, 4, "人物技能", this)).setBounds(48, 405, 99, 24);
            this.add(this.btndskills);
            (this.btnset = new JpanelOnJalbelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, 7, "作 坊", this)).setBounds(255, 405, 59, 24);
            this.add(this.btnset);
            (this.nameBtn = new CharacterBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, "名字", 1)).setBounds(173, 31, 34, 17);
            this.add(this.nameBtn);
            (this.idBtn = new CharacterBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, "ID", 2)).setBounds(173, 55, 34, 17);
            this.add(this.idBtn);
            (this.labappellationBtn = new CharacterBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, "称谓", 3)).setBounds(173, 151, 34, 17);
            this.add(this.labappellationBtn);
            (this.attributeBtn = new CharacterBtn("inkImg/button1/B32.png", 1, UIUtils.COLOR_BTNTEXT, "切属性", 4)).setBounds(165, 186, 51, 17);
            (this.attributeBtnMenu = new CharacterBtn("inkImg/button1/B32.png", 1, UIUtils.COLOR_BTNTEXT, "切换", 7)).setBounds(165, 188, 51, 17);
            this.add(this.attributeBtnMenu);
            (this.attributeBtn1 = new CharacterBtn("inkImg/button1/B32.png", 1, UIUtils.COLOR_BTNTEXT, "切属性", 4)).setVisible(false);
            this.add(this.attributeBtn1);
            (this.attributeBtn2 = new CharacterBtn("inkImg/button1/B32.png", 1, UIUtils.COLOR_BTNTEXT, "切修正", 6)).setVisible(false);
            this.add(this.attributeBtn2);
            (this.transformationBtn = new CharacterBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, "转换", 5)).setBounds(178, 330, 34, 17);
            this.add(this.transformationBtn);
            this.attributeBtn1.setBounds(165, 205, 51, 17);
            this.attributeBtn2.setBounds(165, 223, 51, 17);
            (this.QBBtn = new CharacterBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_BTNTEXT, "头像切换", 8)).setBounds(70, 164, 68, 17);
            this.add(this.QBBtn);
        }
        else {
            this.setPreferredSize(new Dimension(335, 477));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 0);
            offBtn.setBounds(310, 0, 23, 23);
            this.add(offBtn);
            for (int i = 0; i < this.dians.length; ++i) {
                if (i % 2 == 0) {
                    this.dians[i] = new CharacterBtn("img/xy2uiimg/26_png.button.btn_left.png", 1, 10 + i);
                }
                else {
                    this.dians[i] = new CharacterBtn("img/xy2uiimg/54_png.button.btn_right.png", 1, 10 + i);
                }
                this.dians[i].setBounds(281 + i % 2 * 13, 225 + i / 2 * 24, 13, 13);
                this.dians[i].addMouseListener(new PointChangeMouslisten(i));
                this.add(this.dians[i]);
            }
            Font font = new Font("宋体", 0, 14);
            (this.labrolename = new JLabel()).setBounds(190, 46, 120, 15);
            this.labrolename.setForeground(Color.WHITE);
            this.labrolename.setFont(font);
            this.add(this.labrolename);
            (this.labappeleid = new JLabel()).setBounds(190, 71, 120, 15);
            this.labappeleid.setForeground(Color.orange);
            this.labappeleid.setFont(font);
            this.add(this.labappeleid);
            (this.labgangs = new JLabel()).setBounds(190, 95, 120, 15);
            this.labgangs.setForeground(Color.WHITE);
            this.labgangs.setFont(font);
            this.add(this.labgangs);
            (this.labprestige = new JLabel()).setBounds(190, 119, 120, 15);
            this.labprestige.setForeground(Color.WHITE);
            this.labprestige.setFont(font);
            this.add(this.labprestige);
            (this.labrecord = new JLabel()).setBounds(190, 143, 120, 15);
            this.labrecord.setForeground(Color.WHITE);
            this.labrecord.setFont(font);
            this.add(this.labrecord);
            (this.labappellation = new JLabel()).setBounds(190, 165, 120, 18);
            this.labappellation.setForeground(Color.WHITE);
            this.labappellation.setFont(font);
            this.add(this.labappellation);
            (this.labrace = new JLabel()).setBounds(22, 165, 110, 15);
            this.labrace.setForeground(new Color(187, 165, 75));
            this.labrace.setFont(UIUtils.TEXT_HY17);
            this.labrace.setHorizontalAlignment(0);
            this.add(this.labrace);
            font = new Font("宋体", 1, 15);
            (this.labrisegrade = new JLabel()).setBounds(69, 154, 55, 15);
            this.labrisegrade.setForeground(Color.WHITE);
            this.labrisegrade.setFont(font);
            this.add(this.labrisegrade);
            (this.attributeBtnMenu = new CharacterBtn("inkImg/hongmu/B32h.png", 1, UIUtils.COLOR_BTNPUTONG, "切换", 7)).setBounds(140, 200, 51, 17);
            this.add(this.attributeBtnMenu);
            (this.attributeBtn1 = new CharacterBtn("inkImg/hongmu/B32h.png", 1, UIUtils.COLOR_BTNPUTONG, "切属性", 4)).setVisible(false);
            this.add(this.attributeBtn1);
            (this.attributeBtn2 = new CharacterBtn("inkImg/hongmu/B32h.png", 1, UIUtils.COLOR_BTNPUTONG, "切修正", 6)).setVisible(false);
            this.add(this.attributeBtn2);
            (this.btnXLXP = new MouseStyleBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "星盘星录", "星盘星录", "")).setBounds(128, 415, 80, 30);
            this.add(this.btnXLXP);
            (TeststateJpanel.btnlock = new GoodPanelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "星 盘", 0, "")).setBounds(128, 391, 80, 30);
            TeststateJpanel.btnlock.setVisible(false);
            this.add(TeststateJpanel.btnlock);
            (TeststateJpanel.btnlock1 = new GoodPanelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "星 录", 0, "")).setBounds(128, 367, 80, 30);
            TeststateJpanel.btnlock1.setVisible(false);
            this.add(TeststateJpanel.btnlock1);
            this.attributeBtn1.setBounds(140, 218, 51, 17);
            this.attributeBtn2.setBounds(140, 236, 51, 17);
            (this.labrolelevel = new JLabel()).setBounds(58, 202, 80, 15);
            this.labrolelevel.setForeground(Color.WHITE);
            this.labrolelevel.setFont(font);
            this.add(this.labrolelevel);
            (this.labHP = new JLabel()).setBounds(58, 226, 125, 15);
            this.labHP.setForeground(Color.WHITE);
            this.labHP.setFont(font);
            this.add(this.labHP);
            (this.labMP = new JLabel()).setBounds(58, 250, 125, 15);
            this.labMP.setForeground(Color.WHITE);
            this.labMP.setFont(font);
            this.add(this.labMP);
            (this.labAP = new JLabel()).setBounds(58, 274, 125, 15);
            this.labAP.setForeground(Color.WHITE);
            this.labAP.setFont(font);
            this.add(this.labAP);
            (this.labSP = new JLabel()).setBounds(58, 298, 125, 15);
            this.labSP.setForeground(Color.WHITE);
            this.labSP.setFont(font);
            this.add(this.labSP);
            (this.labCP = new JLabel()).setBounds(58, 322, 125, 15);
            this.labCP.setForeground(Color.WHITE);
            this.labCP.setFont(font);
            this.add(this.labCP);
            (this.labEXP = new JLabel()).setBounds(58, 345, 125, 15);
            this.labEXP.addMouseListener(new WLLMouslisten(30));
            this.labEXP.setForeground(Color.WHITE);
            this.labEXP.setFont(font);
            this.add(this.labEXP);
            (this.labrootbone = new JLabel()).setBounds(243, 225, 61, 15);
            this.labrootbone.setForeground(Color.WHITE);
            this.labrootbone.setFont(font);
            this.add(this.labrootbone);
            (this.labintelligence = new JLabel()).setBounds(243, 249, 61, 15);
            this.labintelligence.setForeground(Color.WHITE);
            this.labintelligence.setFont(font);
            this.add(this.labintelligence);
            (this.labpower = new JLabel()).setBounds(243, 273, 61, 15);
            this.labpower.setForeground(Color.WHITE);
            this.labpower.setFont(font);
            this.add(this.labpower);
            (this.labspeed = new JLabel()).setBounds(243, 297, 61, 15);
            this.labspeed.setForeground(Color.WHITE);
            this.labspeed.setFont(font);
            this.add(this.labspeed);
            (this.labability = new JLabel("0")).setBounds(243, 322, 61, 15);
            this.labability.setForeground(Color.WHITE);
            this.labability.setFont(font);
            this.add(this.labability);
            (this.labpointnumber = new JLabel()).setBounds(270, 202, 41, 15);
            this.labpointnumber.setForeground(Color.WHITE);
            this.labpointnumber.setFont(font);
            this.add(this.labpointnumber);
            (this.labroleheadFrame = new JLabel()).setBounds(36, 45, 87, 112);
            this.labroleheadFrame.setIcon(new ImageIcon("img/soaring/头像框w87,h122px.png"));
            this.add(this.labroleheadFrame);
            (this.labrolehead = new JLabel()).setBounds(43, 45, 75, 112);
            this.add(this.labrolehead);
            (this.labroleheadBlack = new JLabel()).setBounds(36, 45, 87, 112);
            this.labroleheadBlack.setIcon(new ImageIcon("img/soaring/头像背景w87,h122px.png"));
            this.add(this.labroleheadBlack);
            (this.btnsure = new JpanelOnJalbelBtn("inkImg/hongmu/a20.png", 1, UIUtils.COLOR_BTNTEXT, 0, "", this)).setBounds(160, 345, 68, 17);
            this.add(this.btnsure);
            if (cbkg.equals("开")) {
                (this.btnmount = new JpanelOnJalbelBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, 1, "灵 宝", this, "")).setBounds(111, 382, 60, 30);
                this.add(this.btnmount);
                (this.wingBtn = new JpanelOnJalbelBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, 9, "翅 膀", this, "")).setBounds(184, 382, 60, 30);
                this.add(this.wingBtn);
                (this.btnroleresistance = new JpanelOnJalbelBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, 2, "抗 性", this, "")).setBounds(254, 382, 60, 30);
                this.add(this.btnroleresistance);
                (this.btnSevenTwo = new JpanelOnJalbelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, 8, "七十二变", this, "")).setBounds(18, 382, 80, 30);
                this.add(this.btnSevenTwo);
            }
            else {
                (this.btnmount = new JpanelOnJalbelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, 1, "灵 宝", this, "")).setBounds(128, 382, 80, 30);
                this.add(this.btnmount);
                (this.btnroleresistance = new JpanelOnJalbelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, 2, "抗 性", this, "")).setBounds(230, 382, 80, 30);
                this.add(this.btnroleresistance);
                (this.btnSevenTwo = new JpanelOnJalbelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, 8, "七十二变", this, "")).setBounds(25, 382, 80, 30);
                this.add(this.btnSevenTwo);
            }
            (this.btndskills = new JpanelOnJalbelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, 4, "人物技能", this, "")).setBounds(25, 415, 80, 30);
            this.add(this.btndskills);
            (this.btnset = new JpanelOnJalbelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, 7, "作 坊", this, "")).setBounds(230, 415, 80, 30);
            this.add(this.btnset);
            (this.nameBtn = new CharacterBtn("inkImg/hongmu/a19.png", 1, UIUtils.COLOR_BTNTEXT, "", 1)).setBounds(148, 45, 34, 17);
            this.add(this.nameBtn);
            (this.idBtn = new CharacterBtn("inkImg/hongmu/a14.png", 1, UIUtils.COLOR_BTNTEXT, "", 2)).setBounds(148, 69, 34, 17);
            this.add(this.idBtn);
            (this.labappellationBtn = new CharacterBtn("inkImg/hongmu/a15.png", 1, UIUtils.COLOR_BTNTEXT, "", 3)).setBounds(148, 165, 34, 17);
            this.add(this.labappellationBtn);
            (this.transformationBtn = new CharacterBtn("inkImg/hongmu/a21.png", 1, UIUtils.COLOR_BTNTEXT, "", 5)).setBounds(189, 345, 34, 17);
            this.add(this.transformationBtn);
            (this.QBBtn = new CharacterBtn("inkImg/hongmu/a7.png", 1, UIUtils.COLOR_BTNPUTONG, "头像切换", 8)).setBounds(42, 180, 68, 17);
            this.add(this.QBBtn);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        LoginResult login = RoleData.getRoleData().getLoginResult();
        this.labEXP.setText(login.getExperience().toString());
        if ((int)login.getCanpoint() == 0) {
            for (int i = 0; i < this.dians.length; ++i) {
                if ((int)login.getGrade() <= 3) {
                    this.dians[8].setVisible(false);
                    this.dians[9].setVisible(false);
                }
                this.dians[i].setVisible(false);
            }
            this.btnsure.setVisible(false);
        }
        else {
            for (int i = 0; i < this.dians.length; ++i) {
                if ((int)login.getGrade() <= 3) {
                    this.dians[8].setVisible(false);
                    this.dians[9].setVisible(false);
                }
                else {
                    this.dians[0].setVisible(true);
                    this.dians[1].setVisible(true);
                    this.dians[2].setVisible(true);
                    this.dians[3].setVisible(true);
                    this.dians[4].setVisible(true);
                    this.dians[5].setVisible(true);
                    this.dians[6].setVisible(true);
                    this.dians[7].setVisible(true);
                }
            }
            this.btnsure.setVisible(true);
        }
        if (this.style.equals("水墨")) {
            if (this.level <= 3) {
                this.icon = new ImageIcon("inkImg/background1/B101.png");
                g.drawImage(this.icon.getImage(), 0, 0, 359, 450, this);
            }
            else {
                this.icon = new ImageIcon("inkImg/background1/B102.png");
                g.drawImage(this.icon.getImage(), 0, 0, 359, 450, this);
            }
            String num = login.getLiangHao();
            if (StringUtils.isNotEmpty(num)) {
                String[] nums = num.split("");
                String type = String.valueOf(login.getLianghaotype());
                for (int j = 0; j < nums.length; ++j) {
                    int xnum = 213 + 12 * j;
                    g.drawImage(CutButtonImage.getImage("inkImg/number/" + type + nums[j] + ".png", 11, 15).getImage(), xnum, 56, 11, 15, this);
                }
            }
        }
        else {
            if (this.level <= 3) {
                this.icon = new ImageIcon("img/soaring/人物属性面板新（飞升前）w335,h477px.png");
                g.drawImage(this.icon.getImage(), 0, 0, 335, 477, this);
            }
            else {
                this.icon = new ImageIcon("img/soaring/人物属性面板新w335,h477px.png");
                g.drawImage(this.icon.getImage(), 0, 0, 335, 477, this);
            }
            String num = login.getLiangHao();
            if (StringUtils.isNotEmpty(num)) {
                String[] nums = num.split("");
                String type = String.valueOf(login.getLianghaotype());
                for (int j = 0; j < nums.length; ++j) {
                    int xnum = 188 + 12 * j;
                    g.drawImage(CutButtonImage.getImage("inkImg/number/" + type + nums[j] + ".png", 11, 15).getImage(), xnum, 70, 11, 15, this);
                }
            }
        }
    }
    
    public int getdian(int type) {
        try {
            if (type == 0) {
                type = Integer.parseInt(this.labrootbone.getText());
            }
            else if (type == 1) {
                type = Integer.parseInt(this.labintelligence.getText());
            }
            else if (type == 2) {
                type = Integer.parseInt(this.labpower.getText());
            }
            else if (type == 3) {
                type = Integer.parseInt(this.labspeed.getText());
            }
            else if (type == 4) {
                type = Integer.parseInt(this.labability.getText());
            }
            else if (type == 5) {
                type = Integer.parseInt(this.labpointnumber.getText());
            }
        }
        catch (Exception e) {
            type = 0;
        }
        return type;
    }
    
    public void adddian(int type, int point) {
        try {
            if (type == 0) {
                type = Integer.parseInt(this.labrootbone.getText());
                this.labrootbone.setText(type + point + "");
            }
            else if (type == 1) {
                type = Integer.parseInt(this.labintelligence.getText());
                this.labintelligence.setText(type + point + "");
            }
            else if (type == 2) {
                type = Integer.parseInt(this.labpower.getText());
                this.labpower.setText(type + point + "");
            }
            else if (type == 3) {
                type = Integer.parseInt(this.labspeed.getText());
                this.labspeed.setText(type + point + "");
            }
            else if (type == 4) {
                type = Integer.parseInt(this.labability.getText());
                this.labability.setText(type + point + "");
            }
            type = Integer.parseInt(this.labpointnumber.getText());
            this.labpointnumber.setText(type - point + "");
        }
        catch (Exception ex) {}
    }
    
    public void changeSoaringPanel(int lvltrue) {
        this.level = lvltrue;
        if (lvltrue <= 3) {
            this.transformationBtn.setVisible(false);
            this.dians[8].setVisible(false);
            this.dians[9].setVisible(false);
            this.labability.setVisible(false);
            this.labCP.setVisible(false);
            if (MyIsif.getStyle().equals("水墨")) {
                this.labEXP.setBounds(80, 308, 125, 15);
                this.icon = CutButtonImage.getImage("inkImg/background1/B101.png", -1, -1);
                this.btnsure.setBounds(269, 309, 68, 17);
            }
            else {
                this.labEXP.setBounds(58, 322, 125, 15);
                this.icon = CutButtonImage.getImage("img/soaring/人物属性面板新（飞升前）w335,h477px.png", -1, -1);
                this.btnsure.setBounds(239, 322, 68, 17);
            }
        }
        else {
            this.transformationBtn.setVisible(true);
            this.dians[8].setVisible(true);
            this.dians[9].setVisible(true);
            this.labability.setVisible(true);
            this.labCP.setVisible(true);
            if (MyIsif.getStyle().equals("水墨")) {
                this.labEXP.setBounds(80, 332, 125, 15);
                this.icon = CutButtonImage.getImage("img/background1/B102.png", -1, -1);
                this.btnsure.setBounds(269, 333, 68, 17);
            }
            else {
                this.labEXP.setBounds(58, 345, 125, 15);
                this.icon = CutButtonImage.getImage("img/soaring/人物属性面板新w335,h477px.png", -1, -1);
                this.btnsure.setBounds(239, 345, 68, 17);
            }
        }
    }
    
    public static void showIsTeamBtnS(boolean type, int num) {
        if (num == 0) {
            TeststateJpanel.btnlock.setVisible(type);
            TeststateJpanel.btnlock1.setVisible(type);
        }
        else if (TeststateJpanel.btnlock.isVisible()) {
            TeststateJpanel.btnlock.setVisible(false);
            TeststateJpanel.btnlock1.setVisible(false);
        }
        else {
            TeststateJpanel.btnlock.setVisible(true);
            TeststateJpanel.btnlock1.setVisible(true);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        FormsManagement.HideForm(0);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public Teststatejframe getTeststatejframe() {
        return this.teststatejframe;
    }
    
    public void setTeststatejframe(Teststatejframe teststatejframe) {
        this.teststatejframe = teststatejframe;
    }
    
    public JLabel getLabel() {
        return this.label;
    }
    
    public void setLabel(JLabel label) {
        this.label = label;
    }
    
    public JLabel getLabrolename() {
        return this.labrolename;
    }
    
    public void setLabrolename(JLabel labrolename) {
        this.labrolename = labrolename;
    }
    
    public JLabel getLabappellation() {
        return this.labappellation;
    }
    
    public void setLabappellation(JLabel labappellation) {
        this.labappellation = labappellation;
    }
    
    public JLabel getLabrace() {
        return this.labrace;
    }
    
    public void setLabrace(JLabel labrace) {
        this.labrace = labrace;
    }
    
    public JLabel getLabgangs() {
        return this.labgangs;
    }
    
    public void setLabgangs(JLabel labgangs) {
        this.labgangs = labgangs;
    }
    
    public JLabel getLabprestige() {
        return this.labprestige;
    }
    
    public void setLabprestige(JLabel labprestige) {
        this.labprestige = labprestige;
    }
    
    public JLabel getLabrecord() {
        return this.labrecord;
    }
    
    public void setLabrecord(JLabel labrecord) {
        this.labrecord = labrecord;
    }
    
    public JLabel getLabrolelevel() {
        return this.labrolelevel;
    }
    
    public void setLabrolelevel(JLabel labrolelevel) {
        this.labrolelevel = labrolelevel;
    }
    
    public JLabel getLabHP() {
        return this.labHP;
    }
    
    public void setLabHP(JLabel labHP) {
        this.labHP = labHP;
    }
    
    public JLabel getLabMP() {
        return this.labMP;
    }
    
    public void setLabMP(JLabel labMP) {
        this.labMP = labMP;
    }
    
    public JLabel getLabAP() {
        return this.labAP;
    }
    
    public void setLabAP(JLabel labAP) {
        this.labAP = labAP;
    }
    
    public JLabel getLabSP() {
        return this.labSP;
    }
    
    public void setLabSP(JLabel labSP) {
        this.labSP = labSP;
    }
    
    public JLabel getLabEXP() {
        return this.labEXP;
    }
    
    public void setLabEXP(JLabel labEXP) {
        this.labEXP = labEXP;
    }
    
    public JLabel getLabrootbone() {
        return this.labrootbone;
    }
    
    public void setLabrootbone(JLabel labrootbone) {
        this.labrootbone = labrootbone;
    }
    
    public JLabel getLabintelligence() {
        return this.labintelligence;
    }
    
    public void setLabintelligence(JLabel labintelligence) {
        this.labintelligence = labintelligence;
    }
    
    public JLabel getLabpower() {
        return this.labpower;
    }
    
    public void setLabpower(JLabel labpower) {
        this.labpower = labpower;
    }
    
    public JLabel getLabspeed() {
        return this.labspeed;
    }
    
    public void setLabspeed(JLabel labspeed) {
        this.labspeed = labspeed;
    }
    
    public JLabel getLabpointnumber() {
        return this.labpointnumber;
    }
    
    public void setLabpointnumber(JLabel labpointnumber) {
        this.labpointnumber = labpointnumber;
    }
    
    public int getExpsize() {
        return this.expsize;
    }
    
    public void setExpsize(int expsize) {
        this.expsize = expsize;
    }
    
    public JLabel getLabrisegrade() {
        return this.labrisegrade;
    }
    
    public void setLabrisegrade(JLabel labrisegrade) {
        this.labrisegrade = labrisegrade;
    }
    
    public JLabel getLabrolehead() {
        return this.labrolehead;
    }
    
    public void setLabrolehead(JLabel labrolehead) {
        this.labrolehead = labrolehead;
    }
    
    public JLabel getLabappeleid() {
        return this.labappeleid;
    }
    
    public void setLabappeleid(JLabel labappeleid) {
        this.labappeleid = labappeleid;
    }
    
    public JLabel getLabCP() {
        return this.labCP;
    }
    
    public void setLabCP(JLabel labCP) {
        this.labCP = labCP;
    }
    
    public JLabel getLabability() {
        return this.labability;
    }
    
    public void setLabability(JLabel labability) {
        this.labability = labability;
    }
    
    public JpanelOnJalbelBtn getBtnmount() {
        return this.btnmount;
    }
    
    public void setBtnmount(JpanelOnJalbelBtn btnmount) {
        this.btnmount = btnmount;
    }
    
    public JpanelOnJalbelBtn getBtnsure() {
        return this.btnsure;
    }
    
    public void setBtnsure(JpanelOnJalbelBtn btnsure) {
        this.btnsure = btnsure;
    }
    
    public JpanelOnJalbelBtn getBtnroleresistance() {
        return this.btnroleresistance;
    }
    
    public void setBtnroleresistance(JpanelOnJalbelBtn btnroleresistance) {
        this.btnroleresistance = btnroleresistance;
    }
    
    public JpanelOnJalbelBtn getBtndskills() {
        return this.btndskills;
    }
    
    public void setBtndskills(JpanelOnJalbelBtn btndskills) {
        this.btndskills = btndskills;
    }
    
    public JpanelOnJalbelBtn getBtnstall() {
        return this.btnstall;
    }
    
    public void setBtnstall(JpanelOnJalbelBtn btnstall) {
        this.btnstall = btnstall;
    }
    
    public JpanelOnJalbelBtn getBtnset() {
        return this.btnset;
    }
    
    public void setBtnset(JpanelOnJalbelBtn btnset) {
        this.btnset = btnset;
    }
    
    public CharacterBtn getNameBtn() {
        return this.nameBtn;
    }
    
    public void setNameBtn(CharacterBtn nameBtn) {
        this.nameBtn = nameBtn;
    }
    
    public CharacterBtn getIdBtn() {
        return this.idBtn;
    }
    
    public void setIdBtn(CharacterBtn idBtn) {
        this.idBtn = idBtn;
    }
    
    public CharacterBtn getLabappellationBtn() {
        return this.labappellationBtn;
    }
    
    public void setLabappellationBtn(CharacterBtn labappellationBtn) {
        this.labappellationBtn = labappellationBtn;
    }
    
    public CharacterBtn getAttributeBtn() {
        return this.attributeBtn;
    }
    
    public void setAttributeBtn(CharacterBtn attributeBtn) {
        this.attributeBtn = attributeBtn;
    }
    
    public CharacterBtn getTransformationBtn() {
        return this.transformationBtn;
    }
    
    public void setTransformationBtn(CharacterBtn transformationBtn) {
        this.transformationBtn = transformationBtn;
    }
    
    public CharacterBtn[] getDians() {
        return this.dians;
    }
    
    public void setDians(CharacterBtn[] dians) {
        this.dians = dians;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public long getSerialversionuid() {
        return 1L;
    }
    
    public JpanelOnJalbelBtn getWingBtn() {
        return this.wingBtn;
    }
    
    public void setWingBtn(JpanelOnJalbelBtn wingBtn) {
        this.wingBtn = wingBtn;
    }
    
    public CharacterBtn getAttributeBtnMenu() {
        return this.attributeBtnMenu;
    }
    
    public void setAttributeBtnMenu(CharacterBtn attributeBtnMenu) {
        this.attributeBtnMenu = attributeBtnMenu;
    }
    
    public CharacterBtn getAttributeBtn1() {
        return this.attributeBtn1;
    }
    
    public void setAttributeBtn1(CharacterBtn attributeBtn1) {
        this.attributeBtn1 = attributeBtn1;
    }
    
    public CharacterBtn getAttributeBtn2() {
        return this.attributeBtn2;
    }
    
    public static String getQhnum() {
        return TeststateJpanel.qhnum;
    }
    
    public static void setQhnum(String qhnum) {
        TeststateJpanel.qhnum = qhnum;
    }
    
    static {
        TeststateJpanel.qhnum = "2";
    }
}
