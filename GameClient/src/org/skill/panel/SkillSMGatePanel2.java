package org.skill.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.come.Frame.MsgJframe;
import org.come.Frame.MsgJframe1;
import org.come.bean.LoginResult;
import org.come.bean.Skill;
import org.come.until.CutButtonImage;
import org.come.until.FormsManagement;
import org.come.until.UserMessUntil;
import org.come.view.View;
import org.skill.btn.SkillTYCBtn5;

import com.tool.role.RoleData;
import com.tool.tcpimg.RichLabel;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;

public class SkillSMGatePanel2 extends JPanel
{
    static int gd;
    private FaMenItemView[] faMenItemViews;
    private JLabel[] jLabels;
    private static JLabel[] skillValue;
    private static JLabel[] skillBar;
    private static JLabel[] skillArt;
    private static JLabel[] skillfmName;
    private static ImageIcon icon;
    private SkillTYCBtn5 switchBtn;
    private SkillTYCBtn5 switchBtn1;
    private SkillTYCBtn5 switchBtn2;
    private SkillTYCBtn5 cz;
    private List lists;
    private SkillTYCBtn5[] skillTYCBtn5s;
    public ImageIcon icon1;
    public ImageIcon icon2;
    
    public SkillTYCBtn5[] getSkillTYCBtn5s() {
        return this.skillTYCBtn5s;
    }
    
    public void setSkillTYCBtn5s(SkillTYCBtn5[] skillTYCBtn5s) {
        this.skillTYCBtn5s = skillTYCBtn5s;
    }
    
    public SkillSMGatePanel2() {
        this.icon1 = new ImageIcon("inkImg/background/S287.png");
        this.icon2 = new ImageIcon("inkImg/background/S288.png");
        BigDecimal scoretype = RoleData.getRoleData().getLoginResult().getScoretype("法门选定");
        this.jLabels = new JLabel[3];
        for (int i = 0; i < this.jLabels.length; ++i) {
            (this.jLabels[i] = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D)g.create();
                    g2.setColor(new Color(39, 42, 39, 111));
                    g2.fillRect(0, 0, this.getWidth(), this.getHeight());
                }
            }).setBounds(5 + 180 * i, 10, 170, 300);
            if (scoretype.intValue() == 0) {
                this.jLabels[i].setVisible(false);
            }
            else if (i == scoretype.intValue() - 1) {
                this.jLabels[i].setVisible(false);
            }
            else {
                this.jLabels[i].setVisible(true);
            }
            this.add(this.jLabels[i]);
        }
        SkillSMGatePanel2.skillValue = new JLabel[6];
        SkillSMGatePanel2.skillArt = new JLabel[6];
        SkillSMGatePanel2.skillBar = new JLabel[6];
        SkillSMGatePanel2.skillfmName = new JLabel[6];
        for (int i = 0; i < 6; ++i) {
            SkillSMGatePanel2.skillBar[i] = new JLabel();
            SkillSMGatePanel2.skillValue[i] = new JLabel();
            SkillSMGatePanel2.skillArt[i] = new JLabel();
            SkillSMGatePanel2.skillfmName[i] = new JLabel();
        }
        for (int i = 0; i < SkillSMGatePanel2.skillValue.length; ++i) {
            SkillSMGatePanel2.skillValue[i].setForeground(Color.white);
            SkillSMGatePanel2.skillfmName[i].setForeground(Color.green);
        }
        for (int i = 0; i < SkillSMGatePanel2.skillValue.length; ++i) {
            SkillSMGatePanel2.skillValue[i].setFont(new Font("黑体", 0, 13));
            SkillSMGatePanel2.skillfmName[i].setFont(new Font("黑体", 0, 15));
        }
        for (int i = 0; i < 6; ++i) {
            SkillSMGatePanel2.skillBar[i].setIcon(CutButtonImage.getImage("inkImg/background/55.png", 148, 15));
            SkillSMGatePanel2.skillArt[i].setIcon(CutButtonImage.getImage("inkImg/button/27.png", 141, 10));
        }
        for (int i = 0; i < 5; ++i) {
            SkillSMGatePanel2.skillBar[i].setOpaque(false);
            SkillSMGatePanel2.skillArt[i].setOpaque(false);
            SkillSMGatePanel2.skillValue[i].setOpaque(false);
            SkillSMGatePanel2.skillfmName[i].setOpaque(false);
        }
        for (int i = 0; i < 6; ++i) {
            SkillSMGatePanel2.skillBar[i].setBounds((11 + i * 176 > 500) ? (11 + i * 176 - 528) : (11 + i * 176), (i > 2) ? 273 : 189, 148, 15);
            SkillSMGatePanel2.skillArt[i].setBounds((15 + i * 176 > 500) ? (15 + i * 176 - 528) : (15 + i * 176), (i > 2) ? 275 : 191, 141, 10);
            SkillSMGatePanel2.skillValue[i].setBounds((13 + i * 176 > 500) ? (13 + i * 176 - 528) : (13 + i * 176), (i > 2) ? 273 : 189, 143, 12);
            SkillSMGatePanel2.skillfmName[i].setBounds((85 + i * 178 > 500) ? (85 + i * 176 - 526) : (85 + i * 178), (i > 2) ? 223 : 140, 60, 38);
        }
        for (int i = 0; i < SkillSMGatePanel2.skillValue.length; ++i) {
            SkillSMGatePanel2.skillValue[i].setHorizontalAlignment(0);
        }
        for (int i = 0; i < 6; ++i) {}
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        SkillSMGatePanel2.gd = (int)loginResult.getFmsld() / 60;
        for (int j = 0; j < 6; ++j) {}
        if (MyIsif.getStyle().equals("水墨")) {
            this.setBounds(47, 65, 600, 340);
        }
        else {
            this.setBounds(25, 80, 600, 340);
        }
        this.setOpaque(false);
        this.setLayout(null);
        this.skillTYCBtn5s = new SkillTYCBtn5[3];
        if (MyIsif.getStyle().equals("水墨")) {
            (this.switchBtn = new SkillTYCBtn5("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "选定", UIUtils.TEXT_HY16, 0, this)).setBounds(50, 270, 59, 24);
            this.add(this.switchBtn);
            (this.switchBtn1 = new SkillTYCBtn5("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "选定", UIUtils.TEXT_HY16, 1, this)).setBounds(230, 270, 59, 24);
            this.add(this.switchBtn1);
            (this.switchBtn2 = new SkillTYCBtn5("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "选定", UIUtils.TEXT_HY16, 2, this)).setBounds(405, 270, 59, 24);
            this.add(this.switchBtn2);
            (this.cz = new SkillTYCBtn5("inkImg/button/2.png", 1, UIUtils.COLOR_WHITE, "重置", 1000, this)).setBounds(this.getWidth() - 100, this.getHeight() - 20, 34, 17);
            this.add(this.cz);
        }
        else {
            (this.switchBtn = new SkillTYCBtn5("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, "选定", UIUtils.TEXT_HY19, 0, this)).setBounds(55, 270, 80, 27);
            this.add(this.switchBtn);
            (this.switchBtn1 = new SkillTYCBtn5("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, "选定", UIUtils.TEXT_HY19, 1, this)).setBounds(235, 270, 80, 27);
            this.add(this.switchBtn1);
            (this.switchBtn2 = new SkillTYCBtn5("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, "选定", UIUtils.TEXT_HY19, 2, this)).setBounds(415, 270, 80, 27);
            this.add(this.switchBtn2);
            (this.cz = new SkillTYCBtn5("inkImg/hongmu/B30h.png", 1, UIUtils.COLOR_BTNPUTONG, "重置", 1000, this)).setBounds(this.getWidth() - 100, this.getHeight() - 20, 34, 17);
            this.add(this.cz);
        }
        this.faMenItemViews = new FaMenItemView[3];
        for (int j = 0; j < this.faMenItemViews.length; ++j) {
            (this.faMenItemViews[j] = new FaMenItemView()).setBounds(5 + 180 * j, 10, 170, 300);
            this.faMenItemViews[j].setVisible(false);
            this.add(this.faMenItemViews[j]);
        }
        this.skillTYCBtn5s[0] = this.switchBtn;
        this.skillTYCBtn5s[1] = this.switchBtn1;
        this.skillTYCBtn5s[2] = this.switchBtn2;
        class MListener extends MouseAdapter {
			private int numType;

			public MListener(int numType) {
				this.numType = numType;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (skillfmName[this.numType].isVisible()) {
					Skill skill = UserMessUntil.getskill1(skillfmName[this.numType].getText());
					if (skill == null) {
						return;
					}
					MsgJframe.getJframe().getJapnel().SM1(skill);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				FormsManagement.HideForm(46);
			}
		}
        for (int j = 0; j < SkillSMGatePanel2.skillfmName.length; ++j) {
            MListener l = new MListener(j);
            SkillSMGatePanel2.skillfmName[j].addMouseListener(l);
        }
    }
    
    public void getRaceSkillPanel(BigDecimal raceId, String sex) {
        for (FaMenItemView faMenItemView : this.faMenItemViews) {
            if (faMenItemView != null) {
                faMenItemView.setVisible(false);
            }
        }
        if (raceId.compareTo(new BigDecimal("10001")) == 0) {
            this.setIcon(CutButtonImage.getImage("inkImg/background/fmr.png", 524, 340));
            if (sexisMan(sex)) {
                RichLabel richLabel = new RichLabel();
                richLabel.addText("适合血抗型人族#r#r使用技能提高自身的受到的伤害减免和仙法鬼火物理抗性提高自身的生存能力。");
                this.faMenItemViews[0].showFaMenItemView("守中", richLabel, "清心静气", "以静制动", this.icon2);
                RichLabel richLabel2 = new RichLabel();
                richLabel2.addText("适合血抗型人族#r使用技能提高自身的受到的伤害减免和仙法鬼火物理抗性提高自身的生存能力。");
                this.faMenItemViews[1].showFaMenItemView("血刃", richLabel2, "利刃加身", "积羽沉舟", this.icon2);
                RichLabel richLabel3 = new RichLabel();
                richLabel3.addText("适合血抗型人族#r使用技能提高自身的受到的伤害减免和仙法鬼火物理抗性提高自身的生存能力。");
                this.faMenItemViews[2].showFaMenItemView("乱神", richLabel3, "神不守舍", "扑朔迷离", this.icon2);
            }
            else {
                RichLabel richLabel = new RichLabel();
                richLabel.addText("适合血抗型人族#r#r使用技能提高自身的受到的伤害减免和仙法鬼火物理抗性提高自身的生存能力。");
                this.faMenItemViews[0].showFaMenItemView("守中", richLabel, "清心静气", "以静制动", this.icon2);
                RichLabel richLabel4 = new RichLabel();
                richLabel4.addText("适合血抗型人族#r使用技能提高自身的受到的伤害减免和仙法鬼火物理抗性提高自身的生存能力。");
                this.faMenItemViews[2].showFaMenItemView("乱神", richLabel4, "神不守舍", "扑朔迷离", this.icon2);
                RichLabel richLabel5 = new RichLabel();
                richLabel5.addText("适合物理型人族#r提高自身攻击力、忽视防御和命中，攻击时有几率额外分裂攻击和忽视敌方躲闪、反震和反击。");
                this.faMenItemViews[1].showFaMenItemView("神力", richLabel5, "神力加身", "云飞烟灭", this.icon2);
            }
        }
        else if (raceId.compareTo(new BigDecimal("10002")) == 0) {
            if (sexisMan(sex)) {
                RichLabel richLabel = new RichLabel();
                richLabel.addText("适合综合型魔族#r#r增加自身和队友的法术命中率和法术躲闪率，提升队伍整体战斗力。");
                this.faMenItemViews[0].showFaMenItemView("机变", richLabel, "幻影迷踪", "心无旁骛", this.icon2);
                RichLabel richLabel2 = new RichLabel();
                richLabel2.addText("适合物理型魔族#r提高自身攻击力和速度，攻击时有几率额外分裂攻击和回复己方单位气血。");
                this.faMenItemViews[1].showFaMenItemView("神力", richLabel2, "力挽狂澜", "披荆斩棘", this.icon2);
                RichLabel richLabel3 = new RichLabel();
                richLabel3.addText("适合辅助型魔族#r增加己方召唤兽的属性和进场的机会，提升自己的辅助能力。");
                this.faMenItemViews[2].showFaMenItemView("御兽", richLabel3, "兽魂俯首", "困兽之斗", this.icon2);
            }
            else {
                RichLabel richLabel = new RichLabel();
                richLabel.addText("适合防御型魔族及鬼族#r#r增加自身及队友的法术躲闪几率和伤害减免，提升队伍的防御能力。");
                this.faMenItemViews[0].showFaMenItemView("戒定", richLabel, "刚柔兼备", "妙法莲华", this.icon2);
                RichLabel richLabel2 = new RichLabel();
                richLabel2.addText("适合物理型魔族#r提高自身攻击力和速度，攻击时有几率额外分裂攻击和回复己方单位气血。");
                this.faMenItemViews[1].showFaMenItemView("神力", richLabel2, "势如破竹", "暴虎冯河", this.icon2);
                RichLabel richLabel3 = new RichLabel();
                richLabel3.addText("适合辅助型魔族#r增加己方召唤兽的属性和进场的机会，提升自己的辅助能力。");
                this.faMenItemViews[2].showFaMenItemView("御兽", richLabel3, "兽魂俯首", "困兽之斗", this.icon2);
            }
        }
        else if (raceId.compareTo(new BigDecimal("10003")) == 0) {
            if (sexisMan(sex)) {
                RichLabel richLabel = new RichLabel();
                richLabel.addText("适合压制型仙族#r增加自身及队友的法术命中和法术暴击几率，提升队伍的持续战斗能力。");
                this.faMenItemViews[0].showFaMenItemView("灵光", richLabel, "凝神一击", "一气呵成", this.icon2);
                RichLabel richLabel2 = new RichLabel();
                richLabel2.addText("适合爆发型仙族及鬼族#r加深敌方受到自己和队友的伤害，提升队伍的整体输出能力。");
                this.faMenItemViews[1].showFaMenItemView("攻坚", richLabel2, "无坚不摧", "韬光养晦", this.icon2);
                RichLabel richLabel3 = new RichLabel();
                richLabel3.addText("适合稳定型仙族#r使自己造成的伤害按照一定百分比转化为至圣伤害，提升自己的输出能力。");
                this.faMenItemViews[2].showFaMenItemView("破壁", richLabel3, "气吞山河", "催筋断骨", this.icon2);
            }
            else {
                RichLabel richLabel = new RichLabel();
                richLabel.addText("适合压制型仙族#r增加自身及队友的法术命中和法术暴击几率，提升队伍的持续战斗能力。");
                this.faMenItemViews[0].showFaMenItemView("灵光", richLabel, "凝神一击", "一气呵成", this.icon2);
                RichLabel richLabel2 = new RichLabel();
                richLabel2.addText("适合爆发型仙族及鬼族#r加深敌方受到自己和队友的伤害，提升队伍的整体输出能力。");
                this.faMenItemViews[1].showFaMenItemView("攻坚", richLabel2, "无坚不摧", "韬光养晦", this.icon2);
                RichLabel richLabel3 = new RichLabel();
                richLabel3.addText("适合稳定型仙族#r使自己造成的伤害按照一定百分比转化为至圣伤害，提升自己的输出能力。");
                this.faMenItemViews[2].showFaMenItemView("破壁", richLabel3, "气吞山河", "催筋断骨", this.icon2);
            }
        }
        else if (raceId.compareTo(new BigDecimal("10004")) == 0) {
            if (sexisMan(sex)) {
                RichLabel richLabel = new RichLabel();
                richLabel.addText("适合爆发型仙族及鬼族#r加深敌方受到自己和队友的伤害，提升队伍的整体输出能力。");
                this.faMenItemViews[1].showFaMenItemView("攻坚", richLabel, "无坚不摧", "韬光养晦", this.icon2);
                RichLabel richLabel2 = new RichLabel();
                richLabel2.addText("适合回复型鬼族#r为己方单位回复气血时额外增加护盾，提升队伍生存能力。");
                this.faMenItemViews[0].showFaMenItemView("护持", richLabel2, "法魂护体", "血蛊佑身", this.icon2);
                RichLabel richLabel3 = new RichLabel();
                richLabel3.addText("适合血抗型鬼族#r增加自身人法抗性、法术躲闪几率和反震，提高自身的生存能力。");
                this.faMenItemViews[2].showFaMenItemView("正心", richLabel3, "气聚神凝", "明镜止水", this.icon2);
            }
            else {
                RichLabel richLabel = new RichLabel();
                richLabel.addText("适合防御型魔族及鬼族#r#r增加自身及队友的法术躲闪几率和伤害减免，提升队伍的防御能力。");
                this.faMenItemViews[0].showFaMenItemView("戒定", richLabel, "刚柔兼备", "妙法莲华", this.icon2);
                RichLabel richLabel2 = new RichLabel();
                richLabel2.addText("适合爆发型仙族及鬼族#r加深敌方受到自己和队友的伤害，提升队伍的整体输出能力。");
                this.faMenItemViews[1].showFaMenItemView("攻坚", richLabel2, "无坚不摧", "韬光养晦", this.icon2);
                RichLabel richLabel3 = new RichLabel();
                richLabel3.addText("适合控制型鬼族#r降低敌方目标造成的伤害，提升队伍的整体防御能力。");
                this.faMenItemViews[2].showFaMenItemView("禁咒", richLabel3, "失魂落魄", "人鬼殊途", this.icon2);
            }
        }
        else if (raceId.compareTo(new BigDecimal("10005")) == 0) {
            RichLabel richLabel = new RichLabel();
            richLabel.addText("适合辅助型龙族#r#r使己方单位击杀敌方单位时可回复气血，提高队伍的生存能力。");
            this.faMenItemViews[0].showFaMenItemView("潜龙", richLabel, "鱼龙潜跃", "虎踞龙蟠", this.icon2);
            RichLabel richLabel2 = new RichLabel();
            richLabel2.addText("适合输出型龙族#r使自己造成的物理伤害转化为至圣伤害，提升自己的输出能力。");
            this.faMenItemViews[2].showFaMenItemView("亢龙", richLabel2, "行气如虹", "浩气凌云", this.icon2);
            RichLabel richLabel3 = new RichLabel();
            richLabel3.addText("适合输出型龙族#r提高自己连击时的伤害和连击率，提升自己的输出能力。");
            this.faMenItemViews[1].showFaMenItemView("惊龙", richLabel3, "神龙摆尾", "积健为雄", this.icon2);
        }
        for (FaMenItemView faMenItemView : this.faMenItemViews) {
            if (MyIsif.getStyle().equals("水墨")) {
                faMenItemView.setBorder(BorderFactory.createEmptyBorder());
            }
            else {
                faMenItemView.setBorder(BorderFactory.createLineBorder(new Color(221, 109, 10)));
            }
            faMenItemView.setVisible(true);
        }
        BigDecimal scoretype = RoleData.getRoleData().getLoginResult().getScoretype("法门选定");
        if (scoretype.intValue() != 0) {
            for (SkillTYCBtn5 skillTYCBtn5 : this.skillTYCBtn5s) {
                skillTYCBtn5.setVisible(false);
            }
            this.skillTYCBtn5s[scoretype.intValue() - 1].nochoose(null);
        }
        else {
            for (SkillTYCBtn5 skillTYCBtn5 : this.skillTYCBtn5s) {
                skillTYCBtn5.setVisible(true);
            }
            for (JLabel jLabel : this.jLabels) {
                jLabel.setVisible(false);
            }
        }
    }
    
    public void changeBtn(int typeBtn) throws Exception {
        this.switchBtn.setIcons(CutButtonImage.cuts("inkImg/button/32.png"));
        this.switchBtn1.setIcons(CutButtonImage.cuts("inkImg/button/32.png"));
        this.switchBtn2.setIcons(CutButtonImage.cuts("inkImg/button/32.png"));
    }
    
    public static boolean sexisMan(String sex) {
        return "男".equals(sex);
    }
    
    public void changeSkillBar(String skillName) {
        String[] skillNames = skillName.split("\\|");
        for (int i = 0; i < skillNames.length; ++i) {}
    }
    
    public static JLabel[] getSkillValue() {
        return SkillSMGatePanel2.skillValue;
    }
    
    public static void setSkillValue(JLabel[] skillValue) {
        SkillSMGatePanel2.skillValue = skillValue;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    public ImageIcon getIcon() {
        return SkillSMGatePanel2.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        SkillSMGatePanel2.icon = icon;
    }
    
    public FaMenItemView[] getFaMenItemViews() {
        return this.faMenItemViews;
    }
    
    public void setFaMenItemViews(FaMenItemView[] faMenItemViews) {
        this.faMenItemViews = faMenItemViews;
    }
    
    public JLabel[] getjLabels() {
        return this.jLabels;
    }
    
    public void setjLabels(JLabel[] jLabels) {
        this.jLabels = jLabels;
    }
    
    public SkillTYCBtn5 getSwitchBtn() {
        return this.switchBtn;
    }
    
    public void setSwitchBtn(SkillTYCBtn5 switchBtn) {
        this.switchBtn = switchBtn;
    }
    
    public SkillTYCBtn5 getSwitchBtn1() {
        return this.switchBtn1;
    }
    
    public void setSwitchBtn1(SkillTYCBtn5 switchBtn1) {
        this.switchBtn1 = switchBtn1;
    }
    
    public SkillTYCBtn5 getSwitchBtn2() {
        return this.switchBtn2;
    }
    
    public void setSwitchBtn2(SkillTYCBtn5 switchBtn2) {
        this.switchBtn2 = switchBtn2;
    }
    
    public class FaMenItemView extends View
    {
        private ImageIcon backImg;
        private String title;
        private RichLabel richLabel;
        private String skillName1;
        private String skillName2;
        private JLabel txtBox;
        private JLabel skillItemArt1;
        private JLabel skillItemArt2;
        private JLabel skillItemSld1;
        private JLabel skillItemSld2;
        private ImageIcon imageIcon1;
        private ImageIcon imageIcon2;
        private JLabel f1;
        private JLabel f2;
        private Boolean b;
        private Skill skill1;
        private Skill skill2;
        private SkillTYCBtn5 xl1;
        private SkillTYCBtn5 xl2;
        
        public FaMenItemView() {
            this.b = Boolean.valueOf(true);
        }
        
        public FaMenItemView(String title, RichLabel richLabel) {
            this.b = Boolean.valueOf(true);
            this.title = title;
            this.richLabel = richLabel;
        }
        
        public void init() {
            this.skillItemSld1.setVisible(false);
            this.skillItemSld2.setVisible(false);
            this.skillItemArt1.setVisible(false);
            this.skillItemArt2.setVisible(false);
            this.xl1.setVisible(false);
            this.xl2.setVisible(false);
            this.setB(Boolean.valueOf(true));
            this.backImg = SkillSMGatePanel2.this.icon2;
        }
        
        public void showFaMenItemView(String title, RichLabel richLabel, String skillName1, String skillName2, ImageIcon icon) {
            if (this.xl1 == null) {
                this.txtBox = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        richLabel.paint(g);
                    }
                };
                (this.xl1 = new SkillTYCBtn5("inkImg/button/2.png", 1, UIUtils.COLOR_WHITE, "修炼", 998, this)).setBounds(123, 188, 34, 17);
                (this.xl2 = new SkillTYCBtn5("inkImg/button/2.png", 1, UIUtils.COLOR_WHITE, "修炼", 999, this)).setBounds(123, 270, 34, 17);
                this.skillItemSld1 = new JLabel("0/0");
                this.skillItemSld2 = new JLabel("0/0");
                this.skillItemArt1 = new JLabel();
                this.skillItemArt2 = new JLabel();
                (this.f2 = new JLabel()).setBounds(10, 201, 37, 37);
                this.f2.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                    }
                    
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        MsgJframe1.getJframe1().getJapnel1().smx(FaMenItemView.this.skill2, 1.0, 200);
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        FormsManagement.HideForm(603);
                    }
                });
                (this.f1 = new JLabel()).setBounds(10, 145, 37, 37);
                this.f1.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                    }
                    
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        MsgJframe1.getJframe1().getJapnel1().smx(FaMenItemView.this.skill1, 1.0, 200);
                    }
                    
                    @Override
                    public void mouseExited(MouseEvent e) {
                        FormsManagement.HideForm(603);
                    }
                });
                this.add(this.f1);
                this.add(this.f2);
                this.skillItemArt2 = new JLabel();
                this.add(this.xl1);
                this.add(this.xl2);
                this.add(this.skillItemSld2);
                this.add(this.skillItemSld1);
                this.add(this.skillItemArt1);
                this.add(this.skillItemArt2);
                this.add(this.txtBox);
            }
            this.backImg = icon;
            this.title = title;
            this.richLabel = richLabel;
            this.skillName1 = skillName1;
            this.skillName2 = skillName2;
            this.skill1 = UserMessUntil.getskill1(skillName1);
            this.skill2 = UserMessUntil.getskill1(skillName2);
            this.xl1.setVisible(false);
            this.xl2.setVisible(false);
            this.skillItemSld1.setFont(UIUtils.TEXT_FONT2);
            this.skillItemSld1.setHorizontalTextPosition(0);
            this.skillItemSld1.setHorizontalAlignment(0);
            this.skillItemSld1.setBounds(18, 191, 98, 10);
            this.skillItemSld1.setForeground(Color.white);
            this.skillItemSld1.setVisible(false);
            this.skillItemSld2.setHorizontalAlignment(0);
            this.skillItemSld2.setHorizontalTextPosition(0);
            this.skillItemSld2.setFont(UIUtils.TEXT_FONT2);
            this.skillItemSld2.setBounds(18, 273, 98, 10);
            this.skillItemSld2.setForeground(Color.white);
            this.skillItemSld2.setVisible(false);
            this.skillItemArt1.setIcon(CutButtonImage.getImage("img/soaringSkill/技能条w140,h8px.png", 100, 11));
            this.skillItemArt1.setBounds(18, 190, 98, 12);
            this.skillItemArt1.setForeground(Color.white);
            this.skillItemArt1.setVisible(false);
            this.skillItemArt2.setIcon(CutButtonImage.getImage("img/soaringSkill/技能条w140,h8px.png", 100, 11));
            this.skillItemArt2.setBounds(18, 272, 98, 12);
            this.skillItemArt2.setForeground(Color.white);
            this.skillItemArt2.setVisible(false);
            this.imageIcon1 = new ImageIcon(new ImageIcon("img/fighting-skill/" + this.skill1.getSkillid() + ".png").getImage().getScaledInstance(37, 37, 10));
            this.imageIcon2 = new ImageIcon(new ImageIcon("img/fighting-skill/" + this.skill2.getSkillid() + ".png").getImage().getScaledInstance(37, 37, 10));
            this.richLabel.setSize(150, 16);
            this.txtBox.setBounds(10, 60, 150, 80);
        }
        
        public ImageIcon getBackImg() {
            return this.backImg;
        }
        
        public void setBackImg(ImageIcon backImg) {
            this.backImg = backImg;
        }
        
        public void setBackImg(ImageIcon backImg, Boolean b) {
            this.backImg = backImg;
            this.setB(b);
        }
        
        public Boolean getB() {
            return this.b;
        }
        
        public void setB(Boolean b) {
            this.b = b;
            if ((boolean)b) {
                this.f1.setBounds(10, 145, 37, 37);
                this.f2.setBounds(10, 201, 37, 37);
            }
            else {
                this.f1.setBounds(17, 145, 37, 37);
                this.f2.setBounds(17, 226, 37, 37);
            }
        }
        
        public String getTitle() {
            return this.title;
        }
        
        public void setTitle(String title) {
            this.title = title;
        }
        
        public RichLabel getRichLabel() {
            return this.richLabel;
        }
        
        public void setRichLabel(RichLabel richLabel) {
            this.richLabel = richLabel;
        }
        
        public void setSkill1(Skill skill1) {
            this.skill1 = skill1;
        }
        
        public Skill getSkill2() {
            return this.skill2;
        }
        
        public void setSkill2(Skill skill2) {
            this.skill2 = skill2;
        }
        
        public Skill getSkill1() {
            return this.skill1;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(this.backImg.getImage(), 0, 0, null);
            if ((boolean)this.b) {
                g.drawImage(this.imageIcon1.getImage(), 10, 145, null);
                g.drawImage(this.imageIcon2.getImage(), 10, 201, null);
                g.setColor(Color.white);
                g.drawString(this.skillName1, 60, 158);
                g.drawString("主动技能", 60, 178);
                g.drawString(this.skillName2, 60, 213);
                g.drawString("被动技能", 60, 233);
                Graphics2D g2d = (Graphics2D)g.create();
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2d.setColor(Color.orange);
                g2d.setFont(UIUtils.TEXT_HY34);
                g2d.drawString(this.title, 53, 30);
                g2d.drawString(this.title, 53, 30);
                g2d.drawString(this.title, 53, 30);
            }
            else {
                g.drawImage(this.imageIcon1.getImage(), 17, 145, null);
                g.drawImage(this.imageIcon2.getImage(), 17, 225, null);
                g.setColor(Color.white);
                g.drawString(this.skillName1, 67, 158);
                g.drawString("主动技能", 67, 178);
                g.drawString(this.skillName2, 67, 238);
                g.drawString("被动技能", 67, 258);
                Graphics2D g2d = (Graphics2D)g.create();
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2d.setColor(Color.orange);
                g2d.setFont(UIUtils.TEXT_HY34);
                g2d.drawString(this.title, 53, 30);
                g2d.drawString(this.title, 53, 30);
                g2d.drawString(this.title, 53, 30);
            }
        }
        
        public void refreshSkillSld() {
            BigDecimal f1 = RoleData.getRoleData().getLoginResult().getScoretype("法门1");
            BigDecimal f2 = RoleData.getRoleData().getLoginResult().getScoretype("法门2");
            Integer grade = RoleData.getRoleData().getLoginResult().getGrade();
            BigDecimal roleGradeMaxFMsld = SkillTYCBtn5.getRoleGradeMaxFMsld((int)grade);
            this.skillItemSld1.setText(f1.toString() + "/" + roleGradeMaxFMsld);
            this.skillItemSld2.setText(f2.toString() + "/" + roleGradeMaxFMsld);
            BigDecimal v = new BigDecimal(100.0 / roleGradeMaxFMsld.doubleValue());
            int i = f1.multiply(v).intValue();
            int i2 = f2.multiply(v).intValue();
            this.getSkillItemArt1().setBounds(18, 190, i, 12);
            this.getSkillItemArt2().setBounds(18, 272, i2, 12);
        }
        
        public JLabel getSkillItemArt1() {
            return this.skillItemArt1;
        }
        
        public void setSkillItemArt1(JLabel skillItemArt1) {
            this.skillItemArt1 = skillItemArt1;
        }
        
        public JLabel getSkillItemArt2() {
            return this.skillItemArt2;
        }
        
        public void setSkillItemArt2(JLabel skillItemArt2) {
            this.skillItemArt2 = skillItemArt2;
        }
        
        public JLabel getSkillItemSld1() {
            return this.skillItemSld1;
        }
        
        public void setSkillItemSld1(JLabel skillItemSld1) {
            this.skillItemSld1 = skillItemSld1;
        }
        
        public JLabel getSkillItemSld2() {
            return this.skillItemSld2;
        }
        
        public void setSkillItemSld2(JLabel skillItemSld2) {
            this.skillItemSld2 = skillItemSld2;
        }
        
        public SkillTYCBtn5 getXl1() {
            return this.xl1;
        }
        
        public void setXl1(SkillTYCBtn5 xl1) {
            this.xl1 = xl1;
        }
        
        public SkillTYCBtn5 getXl2() {
            return this.xl2;
        }
        
        public void setXl2(SkillTYCBtn5 xl2) {
            this.xl2 = xl2;
        }
    }
}
