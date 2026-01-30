package org.come.Jpanel;

import com.tool.tcp.SpriteFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import come.tool.FightingData.FBUtil;
import java.util.List;
import java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import org.come.until.CustomFunction;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.entity.RoleSummoning;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.XYJframe;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import org.come.until.CutButtonImage;
import org.come.bean.Skill;
import org.come.Frame.PetSkillsJframe;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import org.come.mouslisten.WLLMouslisten;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import javax.swing.ImageIcon;
import com.tool.btn.GoodAndPetLockBtn;
import com.tool.tcp.Sprite;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import com.tool.btn.DeleteSkillBtn;
import org.come.mouslisten.ShowPetSkillsMouslisten;
import javax.swing.JLabel;
import com.tool.tcpimg.RichLabel;
import javax.swing.JPanel;

public class PetSkillsJpanel extends JPanel
{
    private RichLabel box;
    public int skllNum;
    private JLabel[] labPetskills;// 召唤兽的九个技能框
    private JLabel[] labPetskillBacks;// 召唤兽的天生技能背景框
    private JLabel[] labPetskillsTS;// 召唤兽的天生技能
    private JLabel[] qlPetskills;//启灵技能
    private JLabel effect;//启灵技能
    public static ShowPetSkillsMouslisten[] showPetSkills;// 召唤兽的九个技能框
    private ShowPetSkillsMouslisten[] showPetSkillsTS;
    private ShowPetSkillsMouslisten[] showQlPetSkills;
    private DeleteSkillBtn supportListBtn;// 支援列表
    private DeleteSkillBtn deleteSkillBtn;// 删除技能
    private DeleteSkillBtn fengyin;
    private DeleteSkillBtn fengyin1;
    private DeleteSkillBtn fengyin2;
    private DeleteSkillBtn fengyin3;
    private DeleteSkillBtn fengyin4;
    private DeleteSkillBtn fengyin5;
    private DeleteSkillBtn fengyin6;
    private DeleteSkillBtn fengyin7;
    private DeleteSkillBtn fengyin8;
    private DeleteSkillBtn fengyin9;
    private DeleteSkillBtn diceskill;
    private DeleteSkillBtn deleteQlSkillBtn;
    private JList<String> listNaturalskill;// 天生技能列表
    private JList<String> listpetskill;// 后面学习的技能列表
    private DefaultListModel<String> modelNaturalskill;// 天生技能列表对象
    private DefaultListModel<String> modelpetskill;// 后面学习的技能列表对象
    private JScrollPane jScrollPane1;// 天生技能列表滚动条
    private JScrollPane jScrollPane2;// 后面学习的技能列表滚动条
    private final boolean shoptext = false;// 判断是否显示预选框描述
    private String petskillID;// 选中的召唤兽技能id
    private int petskillNO;// 选中的召唤兽技能位置
    public static Sprite tcp;
    private int goodPosition;// 判断预选框的位置
    private GoodAndPetLockBtn btnlock;// 召唤兽技能加锁按钮
    private GoodAndPetLockBtn btnunlock;// 召唤兽技能解锁按钮
    private ImageIcon icon;
    private ImageIcon licon;
    private FormsOnOffBtn offBtn;
    int[][] positions_h_8;
    int[][] positions_h_6;
    int[][] positions_s_8;
    int[][] positions_s_6;
    private WuLingPanel wuLing;
    public PetSkillsJpanel() throws Exception {
        wuLing = new WuLingPanel();
        this.add(wuLing);
        this.labPetskills = new JLabel[9];
        this.labPetskillBacks = new JLabel[9];
        this.labPetskillsTS = new JLabel[9];
        this.qlPetskills = new JLabel[6];
        this.effect = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
            }
        };
        this.showPetSkillsTS = new ShowPetSkillsMouslisten[5];
        this.showQlPetSkills = new ShowPetSkillsMouslisten[6];
        this.petskillID = "";
        this.licon = new ImageIcon("img/xy2uiimg/78_png.xy2uiimg.btn_plock.png");
        this.positions_h_8 = new int[][] { { 165, 140, 26, 26 }, { 93, 190, 26, 26 }, { 73, 257, 26, 26 }, { 100, 320, 26, 26 }, { 165, 360, 26, 26 }, { 230, 320, 26, 26 }, { 260, 253, 26, 26 }, { 230, 190, 26, 26 } };
        this.positions_h_6 = new int[][] { { 170, 160, 26, 26 }, { 88, 210, 26, 26 }, { 88, 290, 26, 26 }, { 170, 342, 26, 26 }, { 250, 290, 26, 26 }, { 251, 212, 26, 26 } };
        this.positions_s_8 = new int[][] { { 191, 131, 26, 26 }, { 122, 177, 26, 26 }, { 101, 243, 26, 26 }, { 122, 310, 26, 26 }, { 191, 352, 26, 26 }, { 260, 309, 26, 26 }, { 287, 243, 26, 26 }, { 260, 177, 26, 26 } };
        this.positions_s_6 = new int[][] { { 192, 150, 26, 26 }, { 111, 196, 26, 26 }, { 111, 276, 26, 26 }, { 192, 327, 26, 26 }, { 276, 276, 26, 26 }, { 276, 196, 26, 26 } };
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));

        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/S100J9.png");
            (this.diceskill = new DeleteSkillBtn("inkImg/button1/筛子.png", 1, "摇骰子", 1)).setBounds(232, 202, 25, 24);
            this.diceskill.addMouseListener(new WLLMouslisten(502));
            this.diceskill.setVisible(false);
            this.add(this.diceskill);
            this.add(this.effect);
            this.box = new RichLabel();
            if (this.skllNum == 7 || this.skllNum == 5) {
                if (this.skllNum == 7) {
                    this.setPreferredSize(new Dimension(570, 520));
                }
                else {
                    this.setPreferredSize(new Dimension(570, 520));
                }
            }
            else {
                this.setPreferredSize(new Dimension(570, 520));
            }
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            // 关闭按钮事件
            this.offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 18);
            if (this.skllNum == 7 || this.skllNum == 5) {
                if (this.skllNum == 7) {
                    this.offBtn.setBounds(533, 10, 25, 25);
                }
                else {
                    this.offBtn.setBounds(533, 10, 25, 25);
                }
            }
            else {
                this.offBtn.setBounds(533, 10, 25, 25);
            }
            this.add(this.offBtn);
            // 天生技能列表对象
            this.modelNaturalskill = new DefaultListModel<>();
            // 天生技能列表
            (this.listNaturalskill = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            this.listNaturalskill.setSelectionForeground(Color.YELLOW);
            this.listNaturalskill.setForeground(Color.YELLOW);
            this.listNaturalskill.setFont(new Font("微软雅黑", 1, 14));
            this.listNaturalskill.setBackground(UIUtils.Color_BACK);// 设置列表框为透明背景
            this.listNaturalskill.setModel(this.modelNaturalskill);
            this.listNaturalskill.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (PetSkillsJpanel.this.listNaturalskill.getSelectedValue() != null) {
                        String skillname = (String)PetSkillsJpanel.this.listNaturalskill.getSelectedValue();
                        // 获得该召唤兽技能
                        Skill skill = UserMessUntil.getskill1(skillname);
                        // 先判断这个表格是否有召唤兽技能信息
                        if (skill != null) {// 有召唤兽技能
                            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().skillmsg(skill, 0);
                        }
                        else {
                            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getBox().setText(null);
                        }
                    }
                }
            });
            if (this.skllNum == 7 || this.skllNum == 5) {
                if (this.skllNum == 5) {
                    // 天生技能列表滚动条
                    if (this.showPetSkillsTS.length > 0) {
                        for (int i = 0; i < this.showPetSkillsTS.length; ++i) {
                            (this.labPetskillsTS[i] = new JLabel()).setBounds(147 + i * 35 + i * 50 - this.showPetSkillsTS.length * 5, 45, 50, 50);
                            JLabel jLabel = new JLabel();
                            jLabel.setBounds(127 + i * 35 + i * 50 - this.showPetSkillsTS.length * 5, 51, 50, 50);
                            int[] arr = { 127 + i * 35 + i * 50 - this.showPetSkillsTS.length * 5, 51 };
                            this.showPetSkillsTS[i] = new ShowPetSkillsMouslisten(i, this, arr, Boolean.valueOf(false));
                            this.labPetskillsTS[i].addMouseListener(this.showPetSkillsTS[i]);
                            this.add(this.labPetskillsTS[i]);
                            (this.labPetskillBacks[i] = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/danxin/goodse/9.png", 56, 56));
                            this.labPetskillBacks[i].setBounds(144 + i * 35 + i * 50 - this.showPetSkillsTS.length * 5, 43, 56, 56);
                            this.add(this.labPetskillBacks[i]);
                        }
                    }
                }
                else if (this.skllNum == 7 && this.showPetSkillsTS.length > 0) {
                    for (int i = 0; i < this.showPetSkillsTS.length; ++i) {
                        (this.labPetskillsTS[i] = new JLabel()).setBounds(147 + i * 35 + i * 50 - this.showPetSkillsTS.length * 5, 45, 50, 50);
                        JLabel jLabel = new JLabel();
                        jLabel.setBounds(127 + i * 35 + i * 50 - this.showPetSkillsTS.length * 5, 51, 50, 50);
                        int[] arr = { 127 + i * 35 + i * 50 - this.showPetSkillsTS.length * 5, 51 };
                        this.showPetSkillsTS[i] = new ShowPetSkillsMouslisten(i, this, arr, Boolean.valueOf(false));
                        this.labPetskillsTS[i].addMouseListener(this.showPetSkillsTS[i]);
                        this.add(this.labPetskillsTS[i]);
                        (this.labPetskillBacks[i] = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/danxin/goodse/9.png", 56, 56));
                        this.labPetskillBacks[i].setBounds(144 + i * 35 + i * 50 - this.showPetSkillsTS.length * 5, 43, 56, 56);
                        this.add(this.labPetskillBacks[i]);
                    }
                }
            }
            else if (this.showPetSkillsTS.length > 0) {
                for (int i = 0; i < this.showPetSkillsTS.length; ++i) {
                    (this.labPetskillsTS[i] = new JLabel()).setBounds(131 + i * 35 + i * 50 - this.showPetSkillsTS.length * 5, 50, 50, 50);
                    JLabel jLabel = new JLabel();
                    jLabel.setBounds(127 + i * 35 + i * 50 - this.showPetSkillsTS.length * 5, 51, 50, 50);
                    int[] arr = { 127 + i * 35 + i * 50 - this.showPetSkillsTS.length * 5, 51 };
                    this.showPetSkillsTS[i] = new ShowPetSkillsMouslisten(i, this, arr, Boolean.valueOf(false));
                    this.labPetskillsTS[i].addMouseListener(this.showPetSkillsTS[i]);
                    this.add(this.labPetskillsTS[i]);
                    (this.labPetskillBacks[i] = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/danxin/goodse/9.png", 56, 56));
                    this.labPetskillBacks[i].setBounds(128 + i * 35 + i * 50 - this.showPetSkillsTS.length * 5, 48, 56, 56);
                    this.add(this.labPetskillBacks[i]);
                }
            }
            // 后面学习的技能列表滚动条
            if (this.skllNum == 7) {
                (this.jScrollPane2 = new JScrollPane(this.box)).setVerticalScrollBarPolicy(22);
                this.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
                this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
                this.jScrollPane2.getViewport().setOpaque(false);
                this.jScrollPane2.setOpaque(false);
                this.jScrollPane2.setBounds(322, 133, 220, 288);
                this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
                this.jScrollPane2.setHorizontalScrollBarPolicy(31);
                this.add(this.jScrollPane2);
            }
            else {
                (this.jScrollPane2 = new JScrollPane(this.box)).setVerticalScrollBarPolicy(22);
                this.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
                this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
                this.jScrollPane2.getViewport().setOpaque(false);
                this.jScrollPane2.setOpaque(false);
                this.jScrollPane2.setBounds(322, 133, 220, 288);
                this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
                this.jScrollPane2.setHorizontalScrollBarPolicy(31);
                this.add(this.jScrollPane2);
            }
            if (this.skllNum == 7 || this.skllNum == 5) {
                if (this.skllNum == 7) {
                    int sx = 103;
                    // 支援列表
                    (this.supportListBtn = new DeleteSkillBtn("inkImg/button/49.png", 1, "支援列表")).setBounds(63, 288 + sx, 68, 17);
                    this.add(this.supportListBtn);
                    // 删除技能
                    (this.deleteSkillBtn = new DeleteSkillBtn("inkImg/button/49.png", 1, "删除技能")).setBounds(230, 288 + sx, 68, 17);
                    this.add(this.deleteSkillBtn);
                    //重修技能
                    (this.fengyin = new DeleteSkillBtn("inkImg/button/49.png", 1, "重修技能")).setBounds(234, 143, 68, 17);
                }
                else {
                    (this.supportListBtn = new DeleteSkillBtn("inkImg/button/49.png", 1, "支援列表")).setBounds(63, 390, 68, 17);
                    this.add(this.supportListBtn);
                    (this.deleteSkillBtn = new DeleteSkillBtn("inkImg/button/49.png", 1, "删除技能")).setBounds(230, 390, 68, 17);
                    this.add(this.deleteSkillBtn);
                    (this.fengyin = new DeleteSkillBtn("inkImg/button/49.png", 1, "重修技能")).setBounds(234, 143, 68, 17);
                }
            }
            else {
                (this.supportListBtn = new DeleteSkillBtn("inkImg/button/49.png", 1, "支援列表")).setBounds(63, 390, 68, 17);
                this.add(this.supportListBtn);
                (this.deleteSkillBtn = new DeleteSkillBtn("inkImg/button/49.png", 1, "删除技能")).setBounds(230, 390, 68, 17);
                this.add(this.deleteSkillBtn);
                (this.deleteQlSkillBtn = new DeleteSkillBtn("inkImg/button/49.png", 1, "删除启灵")).setBounds(450, 424, 68, 17);
                this.add(this.deleteQlSkillBtn);
                (this.fengyin = new DeleteSkillBtn("inkImg/button/49.png", 1, "重修技能")).setBounds(234, 143, 68, 17);
            }
            if (this.skllNum == 5) {
                // 第一行第一个技能框
                (this.labPetskills[0] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(155, 163, 50, 50);
                JLabel jLabel2 = new JLabel();
                jLabel2.setBounds(155, 163, 50, 50);
                int[] arr2 = { 155, 163 };
                PetSkillsJpanel.showPetSkills[0] = new ShowPetSkillsMouslisten(0, this, arr2);
                this.labPetskills[0].addMouseListener(PetSkillsJpanel.showPetSkills[0]);
                this.add(this.labPetskills[0]);
                // 第二行第一个技能框1
                (this.labPetskills[1] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(72, 253, 50, 50);
                jLabel2 = new JLabel();
                jLabel2.setBounds(72, 253, 50, 50);
                arr2 = new int[] { 72, 253 };
                PetSkillsJpanel.showPetSkills[1] = new ShowPetSkillsMouslisten(1, this, arr2);
                this.labPetskills[1].addMouseListener(PetSkillsJpanel.showPetSkills[1]);
                this.add(this.labPetskills[1]);
                // 第二行第二个技能框
                (this.labPetskills[4] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(154, 252, 50, 50);
                arr2 = new int[] { 154, 252 };
                PetSkillsJpanel.showPetSkills[4] = new ShowPetSkillsMouslisten(4, this, arr2);
                this.labPetskills[4].addMouseListener(PetSkillsJpanel.showPetSkills[4]);
                this.add(this.labPetskills[4]);
                // 第二行第三个技能框
                (this.labPetskills[3] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(237, 252, 50, 50);
                arr2 = new int[] { 237, 252 };
                PetSkillsJpanel.showPetSkills[3] = new ShowPetSkillsMouslisten(3, this, arr2);
                this.labPetskills[3].addMouseListener(PetSkillsJpanel.showPetSkills[3]);
                this.add(this.labPetskills[3]);
                // 第三行第一个技能框
                (this.labPetskills[2] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(154, 341, 50, 50);
                arr2 = new int[] { 154, 341 };
                PetSkillsJpanel.showPetSkills[2] = new ShowPetSkillsMouslisten(2, this, arr2);
                this.labPetskills[2].addMouseListener(PetSkillsJpanel.showPetSkills[2]);
                this.add(this.labPetskills[2]);
            }
            //7技能格
            else if (this.skllNum == 7) {
                int sx = 103;
                int sy = -2;
                // 第一行第一个技能框
                (this.labPetskills[0] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(158 + sy, 60 + sx, 50, 50);
                JLabel jLabel3 = new JLabel();
                jLabel3.setBounds(158 + sy, 60 + sx, 50, 50);
                int[] arr3 = { 158 + sy, 60 + sx };
                PetSkillsJpanel.showPetSkills[0] = new ShowPetSkillsMouslisten(0, this, arr3);
                this.labPetskills[0].addMouseListener(PetSkillsJpanel.showPetSkills[0]);
                this.add(this.labPetskills[0]);
                // 第二行第一个技能框1
                (this.labPetskills[1] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(74 + sy, 110 + sx, 50, 50);
                jLabel3 = new JLabel();
                jLabel3.setBounds(74 + sy, 110 + sx, 50, 50);
                arr3 = new int[] { 74 + sy, 110 + sx };
                PetSkillsJpanel.showPetSkills[1] = new ShowPetSkillsMouslisten(1, this, arr3);
                this.labPetskills[1].addMouseListener(PetSkillsJpanel.showPetSkills[1]);
                this.add(this.labPetskills[1]);
                // 第二行第二个技能框
                (this.labPetskills[5] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(239 + sy, 111 + sx, 50, 50);
                arr3 = new int[] { 239 + sy, 111 + sx };
                PetSkillsJpanel.showPetSkills[5] = new ShowPetSkillsMouslisten(5, this, arr3);
                this.labPetskills[5].addMouseListener(PetSkillsJpanel.showPetSkills[5]);
                this.add(this.labPetskills[5]);
                // 第四行第一个技能框
                (this.labPetskills[2] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(74 + sy, 189 + sx, 50, 50);
                arr3 = new int[] { 74 + sy, 189 + sx };
                PetSkillsJpanel.showPetSkills[2] = new ShowPetSkillsMouslisten(2, this, arr3);
                this.labPetskills[2].addMouseListener(PetSkillsJpanel.showPetSkills[2]);
                this.add(this.labPetskills[2]);
                // 第四行第二个技能框
                (this.labPetskills[4] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(239 + sy, 189 + sx, 50, 50);
                arr3 = new int[] { 239 + sy, 189 + sx };
                PetSkillsJpanel.showPetSkills[4] = new ShowPetSkillsMouslisten(4, this, arr3);
                this.labPetskills[4].addMouseListener(PetSkillsJpanel.showPetSkills[4]);
                this.add(this.labPetskills[4]);
                // 第五行第一个技能框
                (this.labPetskills[3] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(156 + sy, 238 + sx, 50, 50);
                arr3 = new int[] { 156 + sy, 238 + sx };
                PetSkillsJpanel.showPetSkills[3] = new ShowPetSkillsMouslisten(3, this, arr3);
                this.labPetskills[3].addMouseListener(PetSkillsJpanel.showPetSkills[3]);
                this.add(this.labPetskills[3]);
                // 第三行第一个技能框
                (this.labPetskills[6] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(157 + sy, 149 + sx, 50, 50);
                arr3 = new int[] { 157 + sy, 149 + sx };
                PetSkillsJpanel.showPetSkills[6] = new ShowPetSkillsMouslisten(6, this, arr3);
                this.labPetskills[6].addMouseListener(PetSkillsJpanel.showPetSkills[6]);
                this.add(this.labPetskills[6]);
            }
            else {
                //9技能格
                // 第一行第一个技能框
                (this.labPetskills[0] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(158, 142, 50, 50);
                JLabel jLabel2 = new JLabel();
                jLabel2.setBounds(158, 142, 50, 50);
                int[] arr2 = { 158, 142 };
                PetSkillsJpanel.showPetSkills[0] = new ShowPetSkillsMouslisten(0, this, arr2);
                this.labPetskills[0].addMouseListener(PetSkillsJpanel.showPetSkills[0]);
                this.add(this.labPetskills[0]);
                // 第二行第一个技能框1
                (this.labPetskills[1] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(90, 185, 50, 50);
                jLabel2 = new JLabel();
                jLabel2.setBounds(90, 185, 50, 50);
                arr2 = new int[] { 90, 185 };
                PetSkillsJpanel.showPetSkills[1] = new ShowPetSkillsMouslisten(1, this, arr2);
                this.labPetskills[1].addMouseListener(PetSkillsJpanel.showPetSkills[1]);
                this.add(this.labPetskills[1]);
                // 第四行第二个技能框2
                (this.labPetskills[5] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(228, 317, 50, 50);
                arr2 = new int[] { 228, 317 };
                PetSkillsJpanel.showPetSkills[5] = new ShowPetSkillsMouslisten(5, this, arr2);
                this.labPetskills[5].addMouseListener(PetSkillsJpanel.showPetSkills[5]);
                this.add(this.labPetskills[5]);
                // 第三行第一个技能框
                (this.labPetskills[2] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(60, 251, 50, 50);
                arr2 = new int[] { 60, 251 };
                PetSkillsJpanel.showPetSkills[2] = new ShowPetSkillsMouslisten(2, this, arr2);
                this.labPetskills[2].addMouseListener(PetSkillsJpanel.showPetSkills[2]);
                this.add(this.labPetskills[2]);
                // 第五行第一个技能框
                (this.labPetskills[4] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(159, 361, 50, 50);
                arr2 = new int[] { 159, 361 };
                PetSkillsJpanel.showPetSkills[4] = new ShowPetSkillsMouslisten(4, this, arr2);
                this.labPetskills[4].addMouseListener(PetSkillsJpanel.showPetSkills[4]);
                this.add(this.labPetskills[4]);
                // 第四行第一个技能框
                (this.labPetskills[3] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(90, 317, 50, 50);
                arr2 = new int[] { 90, 317 };
                PetSkillsJpanel.showPetSkills[3] = new ShowPetSkillsMouslisten(3, this, arr2);
                this.labPetskills[3].addMouseListener(PetSkillsJpanel.showPetSkills[3]);
                this.add(this.labPetskills[3]);
                // 第三行第二个技能框神兽技能
                (this.labPetskills[8] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(159, 251, 50, 50);
                arr2 = new int[] { 159, 251 };
                PetSkillsJpanel.showPetSkills[8] = new ShowPetSkillsMouslisten(8, this, arr2);
                this.labPetskills[8].addMouseListener(PetSkillsJpanel.showPetSkills[8]);
                this.add(this.labPetskills[8]);
                // 第二行第二个技能框8
                (this.labPetskills[7] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(228, 185, 50, 50);
                arr2 = new int[] { 228, 185 };
                PetSkillsJpanel.showPetSkills[7] = new ShowPetSkillsMouslisten(7, this, arr2);
                this.labPetskills[7].addMouseListener(PetSkillsJpanel.showPetSkills[7]);
                this.add(this.labPetskills[7]);
                // 第三行第三个技能框7
                (this.labPetskills[6] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(258, 251, 50, 50);
                arr2 = new int[] { 258, 251 };
                PetSkillsJpanel.showPetSkills[6] = new ShowPetSkillsMouslisten(6, this, arr2);
                this.labPetskills[6].addMouseListener(PetSkillsJpanel.showPetSkills[6]);
                this.add(this.labPetskills[6]);
                //启灵技能
                this.add(this.labPetskills[6]);
                (this.qlPetskills[0] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(88, 450, 50, 50);
                arr2 = new int[] { 88, 450 };
                this.showQlPetSkills[0] = new ShowPetSkillsMouslisten(0, this, arr2);
                this.qlPetskills[0].addMouseListener(this.showQlPetSkills[0]);
                this.add(this.qlPetskills[0]);
                (this.qlPetskills[1] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(155, 450, 50, 50);
                arr2 = new int[] { 155, 450 };
                this.showQlPetSkills[1] = new ShowPetSkillsMouslisten(1, this, arr2);
                this.qlPetskills[1].addMouseListener(this.showQlPetSkills[1]);
                this.add(this.qlPetskills[1]);
                (this.qlPetskills[2] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(224, 450, 50, 50);
                arr2 = new int[] { 224, 450 };
                this.showQlPetSkills[2] = new ShowPetSkillsMouslisten(2, this, arr2);
                this.qlPetskills[2].addMouseListener(this.showQlPetSkills[2]);
                this.add(this.qlPetskills[2]);
                (this.qlPetskills[3] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(319, 450, 50, 50);
                arr2 = new int[] { 319, 450 };
                this.showQlPetSkills[3] = new ShowPetSkillsMouslisten(3, this, arr2);
                this.qlPetskills[3].addMouseListener(this.showQlPetSkills[3]);
                this.add(this.qlPetskills[3]);
                (this.qlPetskills[4] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(388, 450, 50, 50);
                arr2 = new int[] { 388, 450 };
                this.showQlPetSkills[4] = new ShowPetSkillsMouslisten(4, this, arr2);
                this.qlPetskills[4].addMouseListener(this.showQlPetSkills[4]);
                this.add(this.qlPetskills[4]);
                (this.qlPetskills[5] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(456, 450, 50, 50);
                arr2 = new int[] { 456, 450 };
                this.showQlPetSkills[5] = new ShowPetSkillsMouslisten(5, this, arr2);
                this.qlPetskills[5].addMouseListener(this.showQlPetSkills[5]);
                this.add(this.qlPetskills[5]);
            }
            // 召唤兽加锁按钮
            (this.btnlock = new GoodAndPetLockBtn("inkImg/button1/K156.png", 1, 5)).setBounds(60, 112, 19, 20);
            this.add(this.btnlock);
            // 召唤兽加锁按钮
            (this.btnunlock = new GoodAndPetLockBtn("inkImg/button1/K157.png", 1, 6)).setBounds(80, 112, 19, 20);
            this.add(this.btnunlock);
        }
        else {
            this.icon = new ImageIcon("img/xy2uiimg/34_png.xy2uiimg.wxs_bg.png");
            (this.diceskill = new DeleteSkillBtn("inkImg/button1/筛子.png", 1, "摇骰子", 1)).setBounds(234, 200, 25, 24);
            this.diceskill.addMouseListener(new WLLMouslisten(502));
            this.diceskill.setVisible(false);
            this.add(this.diceskill);
            this.add(this.effect);
            this.box = new RichLabel();
            this.setPreferredSize(new Dimension(547, 478));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            this.offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 18);
            if (this.skllNum == 7 || this.skllNum == 5) {
                this.offBtn.setBounds(457, 0, 25, 25);
            }
            else {
                this.offBtn.setBounds(467, 0, 25, 25);
            }
            this.add(this.offBtn);
            if (this.showPetSkillsTS.length > 0) {
                for (int i = 0; i < this.showPetSkillsTS.length; ++i) {
                    (this.labPetskillsTS[i] = new JLabel()).setBounds(134 + i * 15 + i * 50 - this.showPetSkillsTS.length * 5, 63, 50, 50);
                    this.showPetSkillsTS[i] = new ShowPetSkillsMouslisten(i);
                    this.labPetskillsTS[i].addMouseListener(this.showPetSkillsTS[i]);
                    this.add(this.labPetskillsTS[i]);
                    (this.labPetskillBacks[i] = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/danxin/goodse/9_3.png", 57, 56));
                    this.labPetskillBacks[i].setBounds(130 + i * 15 + i * 50 - this.showPetSkillsTS.length * 5, 60, 56, 56);
                    this.add(this.labPetskillBacks[i]);
                }
            }
            this.modelNaturalskill = new DefaultListModel<>();
            (this.listNaturalskill = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            this.listNaturalskill.setSelectionForeground(Color.YELLOW);
            this.listNaturalskill.setForeground(Color.YELLOW);
            this.listNaturalskill.setFont(new Font("微软雅黑", 1, 14));
            this.listNaturalskill.setBackground(UIUtils.Color_BACK);
            this.listNaturalskill.setModel(this.modelNaturalskill);
            this.listNaturalskill.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (PetSkillsJpanel.this.listNaturalskill.getSelectedValue() != null) {
                        String skillname = (String)PetSkillsJpanel.this.listNaturalskill.getSelectedValue();
                        Skill skill = UserMessUntil.getskill1(skillname);
                        if (skill != null) {
                            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().skillmsg(skill, 0);
                            if (XYJframe.getXYJframe().getXyJpanel().isVisible() && skill.getSkillname().equals("一念成圣")) {
                                RoleSummoning pet = UserMessUntil.getChosePetMes();
                                if (pet.getSkill().equals("1293")) {
                                    SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("OPENXL&" + pet.getSummoningid() + "&" + pet.getSid()));
                                }
                            }
                        }
                        else {
                            PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getBox().setText(null);
                        }
                    }
                }
            });
            if (this.skllNum == 7 || this.skllNum == 5) {
                if (this.skllNum == 5) {
                    (this.jScrollPane1 = new JScrollPane(this.listNaturalskill)).setVerticalScrollBarPolicy(22);
                    this.jScrollPane1.getVerticalScrollBar().setUI(new SrcollPanelUI());
                    this.jScrollPane1.getViewport().setOpaque(false);
                    this.jScrollPane1.setOpaque(false);
                    this.jScrollPane1.setBounds(300, 55, 165, 89);
                    this.jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
                    this.jScrollPane1.setHorizontalScrollBarPolicy(31);
                    this.add(this.jScrollPane1);
                }
                else if (this.skllNum == 7 && this.showPetSkillsTS.length > 0) {
                    for (int i = 0; i < this.showPetSkillsTS.length; ++i) {
                        (this.labPetskillsTS[i] = new JLabel()).setBounds(90 + i * 15 + i * 50 - this.showPetSkillsTS.length * 5, 60, 50, 50);
                        this.showPetSkillsTS[i] = new ShowPetSkillsMouslisten(i);
                        this.labPetskillsTS[i].addMouseListener(this.showPetSkillsTS[i]);
                        this.add(this.labPetskillsTS[i]);
                        (this.labPetskillBacks[i] = new JLabel()).setIcon(CutButtonImage.getImage("inkImg/danxin/goodse/9_3.png", 57, 56));
                        this.labPetskillBacks[i].setBounds(90 + i * 35 + i * 50 - this.showPetSkillsTS.length * 5, 57, 57, 56);
                        this.add(this.labPetskillBacks[i]);
                    }
                }
            }
            else {
                (this.jScrollPane1 = new JScrollPane(this.listNaturalskill)).setVerticalScrollBarPolicy(22);
                this.jScrollPane1.getVerticalScrollBar().setUI(new SrcollPanelUI());
                this.jScrollPane1.getViewport().setOpaque(false);
                this.jScrollPane1.setOpaque(false);
                this.jScrollPane1.setBounds(295, 59, 170, 84);
                this.jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
                this.jScrollPane1.setHorizontalScrollBarPolicy(31);
                this.add(this.jScrollPane1);
            }
            if (this.skllNum == 7) {
                (this.jScrollPane2 = new JScrollPane(this.box)).setVerticalScrollBarPolicy(22);
                this.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
                this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(25);
                this.jScrollPane2.getViewport().setOpaque(false);
                this.jScrollPane2.setOpaque(false);
                this.jScrollPane2.setBounds(280, 151, 167, 283);
                this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
                this.jScrollPane2.setHorizontalScrollBarPolicy(31);
                this.add(this.jScrollPane2);
            }
            else {
                (this.jScrollPane2 = new JScrollPane(this.box)).setVerticalScrollBarPolicy(22);
                this.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
                this.jScrollPane2.getVerticalScrollBar().setUnitIncrement(30);
                this.jScrollPane2.getViewport().setOpaque(false);
                this.jScrollPane2.setOpaque(false);
                this.jScrollPane2.setBounds(295, 150, 170, 173);
                this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
                this.jScrollPane2.setHorizontalScrollBarPolicy(31);
                this.add(this.jScrollPane2);
            }
            if (this.skllNum == 7 || this.skllNum == 5) {
                int sx = 130;
                int sy = -10;
                if (this.skllNum == 7) {
                    (this.supportListBtn = new DeleteSkillBtn("inkImg/hongmu/a7.png", 1, "支援列表")).setBounds(33 + sy, 275 + sx, 68, 17);
                    this.add(this.supportListBtn);
                    (this.deleteSkillBtn = new DeleteSkillBtn("inkImg/hongmu/a7.png", 1, "删除技能")).setBounds(200 + sy, 275 + sx, 68, 17);
                    this.add(this.deleteSkillBtn);
                    (this.fengyin = new DeleteSkillBtn("inkImg/hongmu/a7.png", 1, "重修技能")).setBounds(200, 156, 68, 17);
                }
                else {
                    (this.supportListBtn = new DeleteSkillBtn("inkImg/hongmu/a7.png", 1, "支援列表")).setBounds(33, 275, 68, 17);
                    this.add(this.supportListBtn);
                    (this.deleteSkillBtn = new DeleteSkillBtn("inkImg/hongmu/a7.png", 1, "删除技能")).setBounds(200, 275, 68, 17);
                    this.add(this.deleteSkillBtn);
                    (this.fengyin = new DeleteSkillBtn("inkImg/hongmu/a7.png", 1, "重修技能")).setBounds(200, 156, 68, 17);
                }
            }
            else {
                (this.supportListBtn = new DeleteSkillBtn("inkImg/hongmu/a7.png", 1, "支援列表")).setBounds(33, 298, 68, 17);
                this.add(this.supportListBtn);
                (this.deleteSkillBtn = new DeleteSkillBtn("inkImg/hongmu/a7.png", 1, "删除技能")).setBounds(200, 298, 68, 17);
                this.add(this.deleteSkillBtn);
                (this.deleteQlSkillBtn = new DeleteSkillBtn("inkImg/hongmu/a7.png", 1, "删除启灵")).setBounds(370, 333, 68, 17);
                this.add(this.deleteQlSkillBtn);
                (this.fengyin = new DeleteSkillBtn("inkImg/hongmu/a7.png", 1, "重修技能")).setBounds(210, 45, 68, 17);
            }
            if (this.skllNum == 5) {
                // 召唤兽加锁按钮
                (this.btnlock = new GoodAndPetLockBtn("img/xy2uiimg/24_png.button.btn_lock.png", 1, 5)).setBounds(240, 150, 19, 20);
                this.add(this.btnlock);
                // 召唤兽加锁按钮
                (this.btnunlock = new GoodAndPetLockBtn("img/xy2uiimg/27_png.button.btn_unlock.png", 1, 6)).setBounds(50, 150, 19, 20);
                this.add(this.btnunlock);
                // 第一行第一个技能框
                (this.labPetskills[0] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(117, 68, 55, 55);
                PetSkillsJpanel.showPetSkills[0] = new ShowPetSkillsMouslisten(0);
                this.labPetskills[0].addMouseListener(PetSkillsJpanel.showPetSkills[0]);
                this.add(this.labPetskills[0]);
                // 第二行第一个技能框
                (this.labPetskills[1] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(31, 157, 50, 50);
                PetSkillsJpanel.showPetSkills[1] = new ShowPetSkillsMouslisten(1);
                this.labPetskills[1].addMouseListener(PetSkillsJpanel.showPetSkills[1]);
                this.add(this.labPetskills[1]);
                // 第四行第一个技能框
                (this.labPetskills[2] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(204, 157, 50, 50);
                PetSkillsJpanel.showPetSkills[2] = new ShowPetSkillsMouslisten(2);
                this.labPetskills[2].addMouseListener(PetSkillsJpanel.showPetSkills[2]);
                this.add(this.labPetskills[2]);
                // 第四行第二个技能框
                (this.labPetskills[4] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(118, 157, 50, 50);
                PetSkillsJpanel.showPetSkills[4] = new ShowPetSkillsMouslisten(4);
                this.labPetskills[4].addMouseListener(PetSkillsJpanel.showPetSkills[4]);
                this.add(this.labPetskills[4]);
                // 第五行第一个技能框
                (this.labPetskills[3] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(117, 245, 50, 50);
                PetSkillsJpanel.showPetSkills[3] = new ShowPetSkillsMouslisten(3);
                this.labPetskills[3].addMouseListener(PetSkillsJpanel.showPetSkills[3]);
                this.add(this.labPetskills[3]);
            }
            //7技能格加锁
            else if (this.skllNum == 7) {
                // 召唤兽加锁按钮
                (this.btnlock = new GoodAndPetLockBtn("img/xy2uiimg/24_png.button.btn_lock.png", 1, 5)).setBounds(240, 150, 19, 20);
                this.add(this.btnlock);
                // 召唤兽加锁按钮
                (this.btnunlock = new GoodAndPetLockBtn("img/xy2uiimg/27_png.button.btn_unlock.png", 1, 6)).setBounds(50, 150, 19, 20);
                this.add(this.btnunlock);
                int sx = 114;
                int sy = -40;
                // 第一行第一个技能框
                (this.labPetskills[0] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(152 + sy, 65 + sx, 50, 50);
                PetSkillsJpanel.showPetSkills[0] = new ShowPetSkillsMouslisten(0);
                this.labPetskills[0].addMouseListener(PetSkillsJpanel.showPetSkills[0]);
                this.add(this.labPetskills[0]);
                // 第二行第一个技能框
                (this.labPetskills[1] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(68 + sy, 116 + sx, 50, 50);
                PetSkillsJpanel.showPetSkills[1] = new ShowPetSkillsMouslisten(1);
                this.labPetskills[1].addMouseListener(PetSkillsJpanel.showPetSkills[1]);
                this.add(this.labPetskills[1]);
                // 第二行第二个技能框
                (this.labPetskills[5] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(235 + sy, 116 + sx, 50, 50);
                PetSkillsJpanel.showPetSkills[5] = new ShowPetSkillsMouslisten(5);
                this.labPetskills[5].addMouseListener(PetSkillsJpanel.showPetSkills[5]);
                this.add(this.labPetskills[5]);
                // 第四行第一个技能框
                (this.labPetskills[2] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(68 + sy, 195 + sx, 50, 50);
                PetSkillsJpanel.showPetSkills[2] = new ShowPetSkillsMouslisten(2);
                this.labPetskills[2].addMouseListener(PetSkillsJpanel.showPetSkills[2]);
                this.add(this.labPetskills[2]);
                // 第四行第二个技能框
                (this.labPetskills[4] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(235 + sy, 195 + sx, 50, 50);
                PetSkillsJpanel.showPetSkills[4] = new ShowPetSkillsMouslisten(4);
                this.labPetskills[4].addMouseListener(PetSkillsJpanel.showPetSkills[4]);
                this.add(this.labPetskills[4]);
                // 第五行第一个技能框
                (this.labPetskills[3] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(153 + sy, 243 + sx, 50, 50);
                PetSkillsJpanel.showPetSkills[3] = new ShowPetSkillsMouslisten(3);
                this.labPetskills[3].addMouseListener(PetSkillsJpanel.showPetSkills[3]);
                this.add(this.labPetskills[3]);
                // 第三行第一个技能框
                (this.labPetskills[6] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(153 + sy, 155 + sx, 50, 50);
                PetSkillsJpanel.showPetSkills[6] = new ShowPetSkillsMouslisten(6);
                this.labPetskills[6].addMouseListener(PetSkillsJpanel.showPetSkills[6]);
                this.add(this.labPetskills[6]);
            }
            else {
                // 召唤兽加锁按钮
                (this.btnlock = new GoodAndPetLockBtn("img/xy2uiimg/24_png.button.btn_lock.png", 1, 5)).setBounds(240, 150, 19, 20);
                this.add(this.btnlock);
                // 召唤兽加锁按钮
                (this.btnunlock = new GoodAndPetLockBtn("img/xy2uiimg/27_png.button.btn_unlock.png", 1, 6)).setBounds(78, 50, 19, 20);
                this.add(this.btnunlock);
                // 第一行第一个技能框
                (this.labPetskills[0] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(130, 47, 50, 50);
                JLabel jLabel2 = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                };
                jLabel2.setBounds(130, 47, 50, 50);
                int[] arr2 = { 130, 47 };
                PetSkillsJpanel.showPetSkills[0] = new ShowPetSkillsMouslisten(0, this, arr2);
                this.labPetskills[0].addMouseListener(PetSkillsJpanel.showPetSkills[0]);
                this.add(this.labPetskills[0]);
                // 第二行第一个技能框1
                (this.labPetskills[1] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(61, 90, 50, 50);
                jLabel2 = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                };
                jLabel2.setBounds(61, 90, 50, 50);
                arr2 = new int[] { 61, 90 };
                PetSkillsJpanel.showPetSkills[1] = new ShowPetSkillsMouslisten(1, this, arr2);
                this.labPetskills[1].addMouseListener(PetSkillsJpanel.showPetSkills[1]);
                this.add(this.labPetskills[1]);
                // 第二行第二个技能框2
                (this.labPetskills[5] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(198, 225, 50, 50);
                arr2 = new int[] { 198, 225 };
                PetSkillsJpanel.showPetSkills[5] = new ShowPetSkillsMouslisten(5, this, arr2);
                this.labPetskills[5].addMouseListener(PetSkillsJpanel.showPetSkills[5]);
                this.add(this.labPetskills[5]);
                // 第四行第一个技能框
                (this.labPetskills[2] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(31, 159, 50, 50);
                arr2 = new int[] { 31, 159 };
                PetSkillsJpanel.showPetSkills[2] = new ShowPetSkillsMouslisten(2, this, arr2);
                this.labPetskills[2].addMouseListener(PetSkillsJpanel.showPetSkills[2]);
                this.add(this.labPetskills[2]);
                // 第四行第二个技能框676
                (this.labPetskills[4] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(130, 269, 50, 50);
                arr2 = new int[] { 130, 269 };
                PetSkillsJpanel.showPetSkills[4] = new ShowPetSkillsMouslisten(4, this, arr2);
                this.labPetskills[4].addMouseListener(PetSkillsJpanel.showPetSkills[4]);
                this.add(this.labPetskills[4]);
                // 第五行第一个技能框
                (this.labPetskills[3] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(63, 224, 50, 50);
                arr2 = new int[] { 63, 224 };
                PetSkillsJpanel.showPetSkills[3] = new ShowPetSkillsMouslisten(3, this, arr2);
                this.labPetskills[3].addMouseListener(PetSkillsJpanel.showPetSkills[3]);
                this.add(this.labPetskills[3]);
                // 第三行第一个技能框神兽技能
                (this.labPetskills[8] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(131, 159, 50, 50);
                arr2 = new int[] { 131, 159 };
                PetSkillsJpanel.showPetSkills[8] = new ShowPetSkillsMouslisten(8, this, arr2);
                this.labPetskills[8].addMouseListener(PetSkillsJpanel.showPetSkills[8]);
                this.add(this.labPetskills[8]);
                // 第五行第二个技能框8
                (this.labPetskills[7] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(195, 90, 50, 50);
                arr2 = new int[] { 195, 90 };
                PetSkillsJpanel.showPetSkills[7] = new ShowPetSkillsMouslisten(7, this, arr2);
                this.labPetskills[7].addMouseListener(PetSkillsJpanel.showPetSkills[7]);
                this.add(this.labPetskills[7]);
                // 第一行第二个技能框7
                (this.labPetskills[6] = new JLabel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        ImageIcon icon1 = (ImageIcon)this.getIcon();
                        if (icon1 != null) {
                            int iconWidth = icon1.getIconWidth();
                            if (iconWidth < 50) {
                                g.drawImage(icon1.getImage(), 3, 3, null);
                            }
                            else {
                                g.drawImage(icon1.getImage(), 0, 0, null);
                            }
                        }
                    }
                }).setBounds(227, 157, 50, 50);
                arr2 = new int[] { 227, 157 };
                PetSkillsJpanel.showPetSkills[6] = new ShowPetSkillsMouslisten(6, this, arr2);
                this.labPetskills[6].addMouseListener(PetSkillsJpanel.showPetSkills[6]);
                this.add(this.labPetskills[6]);
                //启灵技能
                (this.qlPetskills[0] = new JLabel()).setBounds(33, 359, 50, 50);
                arr2 = new int[] { 33, 359 };
                this.showQlPetSkills[0] = new ShowPetSkillsMouslisten(0, this, arr2);
                this.qlPetskills[0].addMouseListener(this.showQlPetSkills[0]);
                this.add(this.qlPetskills[0]);
                (this.qlPetskills[1] = new JLabel()).setBounds(104, 359, 50, 50);
                arr2 = new int[] { 104, 359 };
                this.showQlPetSkills[1] = new ShowPetSkillsMouslisten(1, this, arr2);
                this.qlPetskills[1].addMouseListener(this.showQlPetSkills[1]);
                this.add(this.qlPetskills[1]);
                (this.qlPetskills[2] = new JLabel()).setBounds(175, 359, 50, 50);
                arr2 = new int[] { 175, 359 };
                this.showQlPetSkills[2] = new ShowPetSkillsMouslisten(2, this, arr2);
                this.qlPetskills[2].addMouseListener(this.showQlPetSkills[2]);
                this.add(this.qlPetskills[2]);
                (this.qlPetskills[3] = new JLabel()).setBounds(264, 359, 50, 50);
                arr2 = new int[] { 264, 359 };
                this.showQlPetSkills[3] = new ShowPetSkillsMouslisten(3, this, arr2);
                this.qlPetskills[3].addMouseListener(this.showQlPetSkills[3]);
                this.add(this.qlPetskills[3]);
                (this.qlPetskills[4] = new JLabel()).setBounds(333, 359, 50, 50);
                arr2 = new int[] { 333, 359 };
                this.showQlPetSkills[4] = new ShowPetSkillsMouslisten(4, this, arr2);
                this.qlPetskills[4].addMouseListener(this.showQlPetSkills[4]);
                this.add(this.qlPetskills[4]);
                (this.qlPetskills[5] = new JLabel()).setBounds(401, 359, 50, 50);
                arr2 = new int[] { 401, 359 };
                this.showQlPetSkills[5] = new ShowPetSkillsMouslisten(5, this, arr2);
                this.qlPetskills[5].addMouseListener(this.showQlPetSkills[5]);
                this.add(this.qlPetskills[5]);
            }
            this.btnlock.setBounds(245, 150, 19, 20);
            this.btnunlock.setBounds(265, 150, 19, 20);
            this.btnlock.setBounds(240, 150, 19, 20);
            this.jScrollPane1.setVisible(false);
        }
        try {
            //关闭启灵
            for (JLabel qlPetskill : this.qlPetskills) {
                qlPetskill.setVisible(false);
            }
            this.deleteQlSkillBtn.setVisible(false);
        }
        catch (Exception ex) {}
    }
    
    public void refuPetSkillsJpanel() {
//        wuLing = new WuLingPanel();
//        this.add(wuLing);
        if ("水墨".equals(MyIsif.getStyle())) {
            RoleSummoning pet = UserMessUntil.getChosePetMes();
            if (pet.getTurnRount() == 4) {
                this.skllNum = 9;
            }
            else {
                this.skllNum = 7;
            }
            if (this.skllNum == 7) {
                if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                    this.icon = new ImageIcon("inkImg/background/S100J7.png");
                }
                else {
                    this.icon = new ImageIcon("inkImg/background/S100J6.png");
                }
            }
            else if (this.skllNum == 9) {
                if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                    this.icon = new ImageIcon("inkImg/background/S100J9.png");
                }
                else {
                    this.icon = new ImageIcon("inkImg/background/S100J8.png");
                }
            }
            this.add(this.effect);
            if (this.skllNum == 7 || this.skllNum == 5) {
                if (this.skllNum == 7) {
                    this.setPreferredSize(new Dimension(570, 445));
                }
                else {
                    this.setPreferredSize(new Dimension(570, 445));
                }
            }
            else {
                this.setPreferredSize(new Dimension(570, 520));
            }
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            if (this.skllNum == 7 || this.skllNum == 5) {
                if (this.skllNum == 7) {
                    this.offBtn.setBounds(533, 10, 25, 25);
                }
                else {
                    this.offBtn.setBounds(533, 10, 25, 25);
                }
            }
            else {
                this.offBtn.setBounds(533, 10, 25, 25);
            }
            if (this.skllNum == 7) {
                this.jScrollPane2.setBounds(321, 133, 220, 288);
            }
            else {
                this.jScrollPane2.setBounds(321, 133, 220, 288);
            }
            if (this.skllNum == 7 || this.skllNum == 5) {
                if (this.skllNum == 7) {
                    int sx = 103;
                    this.supportListBtn.setBounds(63, 288 + sx, 68, 17);
                    this.deleteSkillBtn.setBounds(230, 288 + sx, 68, 17);
                }
                else {
                    this.supportListBtn.setBounds(63, 391, 68, 17);
                    this.deleteSkillBtn.setBounds(230, 391, 68, 17);
                }
            }
            else {
                this.supportListBtn.setBounds(63, 391, 68, 17);
                this.deleteSkillBtn.setBounds(230, 391, 68, 17);
            }
            if (this.skllNum == 5) {
                (this.labPetskills[0] = new JLabel()).setBounds(155, 163, 50, 50);
                JLabel jLabel = new JLabel();
                jLabel.setBounds(155, 163, 50, 50);
                int[] arr = { 155, 163 };
                PetSkillsJpanel.showPetSkills[0] = new ShowPetSkillsMouslisten(0, this, arr);
                this.labPetskills[0].addMouseListener(PetSkillsJpanel.showPetSkills[0]);
                this.add(this.labPetskills[0]);
                (this.labPetskills[1] = new JLabel()).setBounds(72, 253, 50, 50);
                jLabel = new JLabel();
                jLabel.setBounds(72, 253, 50, 50);
                arr = new int[] { 72, 253 };
                PetSkillsJpanel.showPetSkills[1] = new ShowPetSkillsMouslisten(1, this, arr);
                this.labPetskills[1].addMouseListener(PetSkillsJpanel.showPetSkills[1]);
                this.add(this.labPetskills[1]);
                (this.labPetskills[4] = new JLabel()).setBounds(154, 252, 50, 50);
                arr = new int[] { 154, 252 };
                PetSkillsJpanel.showPetSkills[4] = new ShowPetSkillsMouslisten(4, this, arr);
                this.labPetskills[4].addMouseListener(PetSkillsJpanel.showPetSkills[4]);
                this.add(this.labPetskills[4]);
                (this.labPetskills[3] = new JLabel()).setBounds(237, 252, 50, 50);
                arr = new int[] { 237, 252 };
                PetSkillsJpanel.showPetSkills[3] = new ShowPetSkillsMouslisten(3, this, arr);
                this.labPetskills[3].addMouseListener(PetSkillsJpanel.showPetSkills[3]);
                this.add(this.labPetskills[3]);
                (this.labPetskills[2] = new JLabel()).setBounds(154, 341, 50, 50);
                arr = new int[] { 154, 341 };
                PetSkillsJpanel.showPetSkills[2] = new ShowPetSkillsMouslisten(2, this, arr);
                this.labPetskills[2].addMouseListener(PetSkillsJpanel.showPetSkills[2]);
                this.add(this.labPetskills[2]);
            }
            else if (this.skllNum == 7) {
                int sx = 100;
                int sy = 1;
                this.labPetskills[0].setBounds(156 + sy, 62 + sx, 50, 50);
                this.labPetskills[1].setBounds(73 + sy, 108 + sx, 50, 50);
                this.labPetskills[5].setBounds(238 + sy, 109 + sx, 50, 50);
                this.labPetskills[2].setBounds(74 + sy, 189 + sx, 50, 50);
                this.labPetskills[4].setBounds(239 + sy, 189 + sx, 50, 50);
                this.labPetskills[3].setBounds(156 + sy, 241 + sx, 50, 50);
                this.labPetskills[6].setBounds(157 + sy, 152 + sx, 50, 50);
                if (this.labPetskills.length > 7) {
                    if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                        this.labPetskills[6].setVisible(true);
                    }
                    else {
                        this.labPetskills[6].setVisible(false);
                    }
                    this.labPetskills[7].setVisible(false);
                    this.labPetskills[8].setVisible(false);
                }
            }
            else {
                this.labPetskills[0].setBounds(154, 141, 50, 50);
                this.labPetskills[1].setBounds(87, 188, 50, 50);
                this.labPetskills[5].setBounds(222, 320, 50, 50);
                this.labPetskills[2].setBounds(65, 254, 50, 50);
                this.labPetskills[4].setBounds(154, 362, 50, 50);
                this.labPetskills[3].setBounds(87, 320, 50, 50);
                this.labPetskills[6].setBounds(247, 254, 50, 50);
                if (this.labPetskills.length > 7) {
                    this.labPetskills[8].setBounds(154, 254, 50, 50);
                    this.labPetskills[7].setBounds(222, 188, 50, 50);
                    this.labPetskills[7].setVisible(true);
                    this.labPetskills[8].setVisible(true);
                    this.labPetskills[6].setVisible(true);
                    if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                        this.labPetskills[8].setVisible(true);
                    }
                    else {
                        this.labPetskills[8].setVisible(false);
                    }
                }
            }
        }
        else {
            RoleSummoning pet = UserMessUntil.getChosePetMes();
            if (pet.getTurnRount() == 4) {
                this.skllNum = 9;
            }
            else {
                this.skllNum = 7;
            }
            if (this.skllNum == 7) {
                if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                    this.icon = new ImageIcon("inkImg/hongmu/hm-jn7.png");
                }
                else {
                    this.icon = new ImageIcon("inkImg/hongmu/hm-jn6.png");
                }
            }
            else if (this.skllNum == 9) {
                if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                    this.icon = new ImageIcon("inkImg/hongmu/hm-jn9.png");
                }
                else {
                    this.icon = new ImageIcon("inkImg/hongmu/hm-jn8.png");
                }
            }
            this.add(this.effect);
            if (this.skllNum == 7 || this.skllNum == 5) {
                if (this.skllNum == 7) {
                    this.setPreferredSize(new Dimension(570, 445));
                }
                else {
                    this.setPreferredSize(new Dimension(570, 445));
                }
            }
            else {
                this.setPreferredSize(new Dimension(570, 520));
            }
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            if (this.skllNum == 7 || this.skllNum == 5) {
                if (this.skllNum == 7) {
                    this.offBtn.setBounds(533, 10, 25, 25);
                }
                else {
                    this.offBtn.setBounds(533, 10, 25, 25);
                }
            }
            else {
                this.offBtn.setBounds(533, 10, 25, 25);
            }
            if (this.skllNum == 7) {
                this.jScrollPane2.setBounds(295, 146, 220, 288);
            }
            else {
                this.jScrollPane2.setBounds(295, 146, 220, 288);
            }
            if (this.skllNum == 7) {
                if (this.skllNum == 7) {
                    int sx = 113;
                    this.supportListBtn.setBounds(37, 295 + sx, 68, 17);
                    this.deleteSkillBtn.setBounds(210, 295 + sx, 68, 17);
                }
                else {
                    this.supportListBtn.setBounds(37, 408, 68, 17);
                    this.deleteSkillBtn.setBounds(210, 408, 68, 17);
                }
            }
            else {
                this.supportListBtn.setBounds(37, 408, 68, 17);
                this.deleteSkillBtn.setBounds(210, 408, 68, 17);
            }
            if (this.skllNum == 7) {
                int sx = 114;
                int sy = -23;
                this.labPetskills[0].setBounds(153 + sy, 59 + sx, 50, 50);
                this.labPetskills[1].setBounds(70 + sy, 105 + sx, 50, 50);
                this.labPetskills[5].setBounds(236 + sy, 108 + sx, 50, 50);
                this.labPetskills[2].setBounds(71 + sy, 187 + sx, 50, 50);
                this.labPetskills[4].setBounds(236 + sy, 188 + sx, 50, 50);
                this.labPetskills[3].setBounds(153 + sy, 239 + sx, 50, 50);
                this.labPetskills[6].setBounds(155 + sy, 152 + sx, 50, 50);
                if (this.labPetskills.length > 7) {
                    if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                        this.labPetskills[6].setVisible(true);
                    }
                    else {
                        this.labPetskills[6].setVisible(false);
                    }
                    this.labPetskills[7].setVisible(false);
                    this.labPetskills[8].setVisible(false);
                }
            }
            else {
                this.labPetskills[0].setBounds(128, 155, 50, 50);
                this.labPetskills[1].setBounds(61, 201, 50, 50);
                this.labPetskills[5].setBounds(195, 333, 50, 50);
                this.labPetskills[2].setBounds(39, 267, 50, 50);
                this.labPetskills[4].setBounds(128, 375, 50, 50);
                this.labPetskills[3].setBounds(61, 333, 50, 50);
                this.labPetskills[6].setBounds(221, 267, 50, 50);
                if (this.labPetskills.length > 7) {
                    this.labPetskills[8].setBounds(128, 267, 50, 50);
                    this.labPetskills[7].setBounds(195, 201, 50, 50);
                    this.labPetskills[7].setVisible(true);
                    this.labPetskills[8].setVisible(true);
                    this.labPetskills[6].setVisible(true);
                    if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                        this.labPetskills[8].setVisible(true);
                    }
                    else {
                        this.labPetskills[8].setVisible(false);
                    }
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < this.showPetSkillsTS.length; ++i) {}
        if (this.skllNum == 7 || this.skllNum == 5) {
            if (MyIsif.getStyle().equals("水墨")) {
                if (this.skllNum == 7) {
                    g.drawImage(this.icon.getImage(), 0, 0, this);
                    if (PetSkillsJpanel.showPetSkills != null && PetSkillsJpanel.showPetSkills.length > 0) {
                        for (int i = 0; i < PetSkillsJpanel.showPetSkills.length; ++i) {
                            if (UserMessUntil.getChosePetMes().getPetSkilllock() != null) {
                                String[] list;
                                for (String sd : list = UserMessUntil.getChosePetMes().getPetSkilllock().split("\\|")) {
                                    if (PetSkillsJpanel.showPetSkills[i].getSkill() != null && sd.equals(PetSkillsJpanel.showPetSkills[i].getSkill().getSkillid())) {
                                        if (i == 0) {
                                            g.drawImage(this.licon.getImage(), 187, 148, 16, 18, this);
                                        }
                                        if (i == 1) {
                                            g.drawImage(this.licon.getImage(), 105, 198, 16, 18, this);
                                        }
                                        if (i == 2) {
                                            g.drawImage(this.licon.getImage(), 105, 278, 16, 18, this);
                                        }
                                        if (i == 3) {
                                            g.drawImage(this.licon.getImage(), 187, 325, 16, 18, this);
                                        }
                                        if (i == 4) {
                                            g.drawImage(this.licon.getImage(), 270, 278, 16, 18, this);
                                        }
                                        if (i == 5) {
                                            g.drawImage(this.licon.getImage(), 270, 198, 16, 18, this);
                                        }
                                    }
                                }
                            }
                        }
                        for (int i = 0; i < PetSkillsJpanel.showPetSkills.length - 1; ++i) {
                            if (PetSkillsJpanel.showPetSkills[i] != null) {
                                if (PetSkillsJpanel.showPetSkills[i].getSkill() == null) {
                                    if (i < (int)UserMessUntil.getChosePetMes().getOpenSeal()) {
                                        ShowPetSkillsMouslisten showPetSkillsT = PetSkillsJpanel.showPetSkills[i];
                                        int[] pos = this.positions_s_6[i];
                                        this.diceskill.setBounds(pos[0], pos[1], pos[2], pos[3]);
                                        this.diceskill.setVisible(true);
                                        break;
                                    }
                                }
                                else {
                                    this.diceskill.setVisible(false);
                                }
                            }
                        }
                    }
                }
                else {
                    g.drawImage(this.icon.getImage(), 0, 0, 570, 445, this);
                    if (PetSkillsJpanel.showPetSkills != null && PetSkillsJpanel.showPetSkills.length > 0) {
                        for (int i = 0; i < PetSkillsJpanel.showPetSkills.length; ++i) {
                            if (UserMessUntil.getChosePetMes().getPetSkilllock() != null) {
                                String[] list;
                                for (String sd : list = UserMessUntil.getChosePetMes().getPetSkilllock().split("\\|")) {
                                    if (PetSkillsJpanel.showPetSkills[i].getSkill() != null && sd.equals(PetSkillsJpanel.showPetSkills[i].getSkill().getSkillid())) {
                                        ShowPetSkillsMouslisten showPetSkillsT2 = PetSkillsJpanel.showPetSkills[i];
                                        int[] xy = showPetSkillsT2.getXy();
                                        if (i == 0) {
                                            g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                        }
                                        if (i == 1) {
                                            g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                        }
                                        if (i == 2) {
                                            g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                        }
                                        if (i == 3) {
                                            g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                        }
                                    }
                                }
                            }
                        }
                        for (int i = 0; i < PetSkillsJpanel.showPetSkills.length - 1; ++i) {
                            if (PetSkillsJpanel.showPetSkills[i] != null) {
                                if (PetSkillsJpanel.showPetSkills[i].getSkill() == null) {
                                    if (i < (int)UserMessUntil.getChosePetMes().getOpenSeal()) {
                                        ShowPetSkillsMouslisten showPetSkillsT = PetSkillsJpanel.showPetSkills[i];
                                        int[] pos = this.positions_s_8[i];
                                        this.diceskill.setBounds(pos[0], pos[1], pos[2], pos[3]);
                                        this.diceskill.setVisible(true);
                                        break;
                                    }
                                }
                                else {
                                    this.diceskill.setVisible(false);
                                }
                            }
                        }
                    }
                }
            }
            else if (this.skllNum == 7 || this.skllNum == 9) {
                g.drawImage(this.icon.getImage(), 0, 0, this);
                if (PetSkillsJpanel.showPetSkills != null && PetSkillsJpanel.showPetSkills.length > 0) {
                    for (int i = 0; i < PetSkillsJpanel.showPetSkills.length; ++i) {
                        if (UserMessUntil.getChosePetMes().getPetSkilllock() != null) {
                            String[] list;
                            for (String sd : list = UserMessUntil.getChosePetMes().getPetSkilllock().split("\\|")) {
                                if (PetSkillsJpanel.showPetSkills[i].getSkill() != null && sd.equals(PetSkillsJpanel.showPetSkills[i].getSkill().getSkillid())) {
                                    if (i == 0) {
                                        g.drawImage(this.licon.getImage(), 150, 160, 16, 18, this);
                                    }
                                    if (i == 1) {
                                        g.drawImage(this.licon.getImage(), 65, 210, 16, 18, this);
                                    }
                                    if (i == 2) {
                                        g.drawImage(this.licon.getImage(), 65, 290, 16, 18, this);
                                    }
                                    if (i == 3) {
                                        g.drawImage(this.licon.getImage(), 150, 335, 16, 18, this);
                                    }
                                    if (i == 4) {
                                        g.drawImage(this.licon.getImage(), 234, 290, 16, 18, this);
                                    }
                                    if (i == 5) {
                                        g.drawImage(this.licon.getImage(), 234, 210, 16, 18, this);
                                    }
                                }
                            }
                        }
                    }
                    for (int i = 0; i < PetSkillsJpanel.showPetSkills.length - 1; ++i) {
                        if (PetSkillsJpanel.showPetSkills[i] != null) {
                            if (PetSkillsJpanel.showPetSkills[i].getSkill() == null) {
                                if (i < (int)UserMessUntil.getChosePetMes().getOpenSeal()) {
                                    ShowPetSkillsMouslisten showPetSkillsT = PetSkillsJpanel.showPetSkills[i];
                                    int[] pos = this.positions_h_6[i];
                                    this.diceskill.setBounds(pos[0], pos[1], pos[2], pos[3]);
                                    this.diceskill.setVisible(true);
                                    break;
                                }
                            }
                            else {
                                this.diceskill.setVisible(false);
                            }
                        }
                    }
                }
            }
            else {
                g.drawImage(this.icon.getImage(), 0, 0, 482, 366, this);
            }
        }
        else if (MyIsif.getStyle().equals("水墨")) {
            g.drawImage(this.icon.getImage(), 0, 0, this);
            if (PetSkillsJpanel.showPetSkills != null && PetSkillsJpanel.showPetSkills.length > 0) {
                for (int i = 0; i < PetSkillsJpanel.showPetSkills.length; ++i) {
                    if (UserMessUntil.getChosePetMes() != null && UserMessUntil.getChosePetMes().getPetSkilllock() != null) {
                        String[] list;
                        for (String sd : list = UserMessUntil.getChosePetMes().getPetSkilllock().split("\\|")) {
                            if (PetSkillsJpanel.showPetSkills[i].getSkill() != null && sd.equals(PetSkillsJpanel.showPetSkills[i].getSkill().getSkillid())) {
                                ShowPetSkillsMouslisten showPetSkillsT2 = PetSkillsJpanel.showPetSkills[i];
                                int[] xy = showPetSkillsT2.getXy();
                                if (i == 0) {
                                    g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                }
                                if (i == 1) {
                                    g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                }
                                if (i == 2) {
                                    g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                }
                                if (i == 3) {
                                    g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                }
                                if (i == 4) {
                                    g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                }
                                if (i == 5) {
                                    g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < PetSkillsJpanel.showPetSkills.length - 1; ++i) {
                    if (PetSkillsJpanel.showPetSkills[i] != null) {
                        if (PetSkillsJpanel.showPetSkills[i].getSkill() == null) {
                            if (i < (int)UserMessUntil.getChosePetMes().getOpenSeal()) {
                                ShowPetSkillsMouslisten showPetSkillsT = PetSkillsJpanel.showPetSkills[i];
                                int[] pos = this.positions_s_8[i];
                                this.diceskill.setBounds(pos[0], pos[1], pos[2], pos[3]);
                                this.diceskill.setVisible(true);
                                break;
                            }
                        }
                        else {
                            this.diceskill.setVisible(false);
                        }
                    }
                }
            }
        }
        else {
            g.drawImage(this.icon.getImage(), 0, 0, this);
            if (PetSkillsJpanel.showPetSkills != null && PetSkillsJpanel.showPetSkills.length > 0) {
                for (int i = 0; i < PetSkillsJpanel.showPetSkills.length; ++i) {
                    if (UserMessUntil.getChosePetMes().getPetSkilllock() != null) {
                        String[] list;
                        for (String sd : list = UserMessUntil.getChosePetMes().getPetSkilllock().split("\\|")) {
                            if (PetSkillsJpanel.showPetSkills[i].getSkill() != null && sd.equals(PetSkillsJpanel.showPetSkills[i].getSkill().getSkillid())) {
                                ShowPetSkillsMouslisten showPetSkillsT2 = PetSkillsJpanel.showPetSkills[i];
                                int[] xy = showPetSkillsT2.getXy();
                                if (i == 0) {
                                    g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                }
                                if (i == 1) {
                                    g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                }
                                if (i == 2) {
                                    g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                }
                                if (i == 3) {
                                    g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                }
                                if (i == 4) {
                                    g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                }
                                if (i == 5) {
                                    g.drawImage(this.licon.getImage(), xy[0] + 50, xy[1] - 15, 16, 18, this);
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < PetSkillsJpanel.showPetSkills.length - 1; ++i) {
                    if (PetSkillsJpanel.showPetSkills[i] != null) {
                        if (PetSkillsJpanel.showPetSkills[i].getSkill() == null) {
                            if (i < (int)UserMessUntil.getChosePetMes().getOpenSeal()) {
                                ShowPetSkillsMouslisten showPetSkillsT = PetSkillsJpanel.showPetSkills[i];
                                int[] xy2 = showPetSkillsT.getXy();
                                this.diceskill.setBounds(xy2[0] + 35, xy2[1] + 95, 25, 24);
                                this.diceskill.setVisible(true);
                                break;
                            }
                        }
                        else {
                            this.diceskill.setVisible(false);
                        }
                    }
                }
            }
        }
    }
    
    public JLabel getEffect() {
        return this.effect;
    }
    
    public void setEffect(JLabel effect) {
        this.effect = effect;
    }
    
    public void skillmsg(Skill skill, int skillwl) {
        this.skillmsg(UserMessUntil.getChosePetMes(), skill, skillwl);
    }
    
    public void skillmsg(RoleSummoning pet, Skill skill, int skillwl) {
        this.box.setText(null);
        if (skill != null) {
//			if (skillwl > 0) {
//				box.addText("  该技能处于#G悟灵状态#r");
//				box.addText("     等级：" + skillwl + "/10级 #r");
//			}
//
//			else if (Integer.parseInt(skill.getSkillid()) == 1264 || Integer.parseInt(skill.getSkillid()) == 1265 ||
//					Integer.parseInt(skill.getSkillid()) == 1266 || Integer.parseInt(skill.getSkillid()) == 1267 ||
//					Integer.parseInt(skill.getSkillid()) == 1268 || Integer.parseInt(skill.getSkillid()) == 1269 ||
//					Integer.parseInt(skill.getSkillid()) == 1271 ||
//					Integer.parseInt(skill.getSkillid()) == 1272 || Integer.parseInt(skill.getSkillid()) == 1273) {
//				box.addText("     此技能可以#G悟灵#r");
//				box.addText(" #r");
//			}
//			else {
//
//			}

            if (Integer.parseInt(skill.getSkillid()) < 1100) {
                String setText = this.setText(skill, 1.0, 200);
                if (setText != null) {
                    this.box.addText(setText);
                }
                Dimension d = this.box.computeSize(130);
                wuLing.setLocation(jScrollPane2.getX() + 5, WuLingPanel.y + (int) d.getHeight() - 10);
            } else {
                String msg = skill.getRemark();
                double sv = Double.parseDouble(skill.getGrow());// 技能成长
                long qm = UserMessUntil.getChosePetMes().getFriendliness();
                double value = Double.parseDouble(skill.getValue());// 介值
                //天降流火
                if (skill.getSkillid().equals("1237")) {
                    msg = msg.replace("{0}", String.format("%.2f", value + CustomFunction.XS(qm, sv)));
                    msg = msg.replace("{1}", String.format("%.2f", 25 + CustomFunction.XS(qm, sv)));
                    //归去来兮
                    //一御当千
                } else if (skill.getSkillid().equals("1238") || skill.getSkillid().equals("1240")) {
                    msg = msg.replace("{0}", String.format("%.2f", value + CustomFunction.XS(qm, sv)));
                    msg = msg.replace("{1}", String.format("%.2f", Double.parseDouble(20 + "") + CustomFunction.XS(qm, Double.parseDouble(20 + ""))));
                } else if (skill.getSkillid().equals("1243")) {
                    msg = msg.replace("{0}", String.format("%.2f", value + CustomFunction.XS(qm, sv)));
                } else if (skill.getSkillid().equals("1216")) {
                    msg = msg.replace("{0}", String.format("%.2f", value + CustomFunction.XS(qm, sv)));
                    msg = msg.replace("{1}", String.format("%.2f", value + CustomFunction.XS(qm, sv)));
                } else if (skill.getSkillid().equals("1900")) {
                    msg = msg.replace("{0}", String.valueOf((int) (112 + CustomFunction.XS(pet.getFriendliness(), 4))));
                }
                String[] v = PetSkillsJpanel.StringReplace(msg).split("\\|");
                int i = 0;
                while (i < v.length) {
                    String vg;
                    String[] v1 = v[i].split("=");
                    if (skill.getSkilltype().equals("4") && v1[1].equals("0/999")) {
                        int max = 999;
                        if (StringUtils.isNotBlank(pet.getPetSkills())) {
                            List<String> ids = Arrays.asList("1606", "1607", "1608", "1828", "1829", "1830", "1840", "1841", "1842", "1867", "1868", "1869");
                            String[] skills = pet.getPetSkills().split("\\|");
                            for (int j = 0; j < skills.length; j++) {
                                if (ids.contains(skills[j])) {
                                    max = 5000; // 终极修炼进度
                                }
                            }
                        }
                        v1[1] = UserMessUntil.getChosePetMes().getTrainNum() + "/" + max;
                    }
                    if ((vg = this.gongshi(v, i)) != null) {
                        this.box.addText("#c" + v1[0] + " " + v1[1] + "#c" + v[++i].split("=")[0] + vg + "#r");
                    } else {
                        this.box.addText("#c" + v1[0] + " " + v1[1] + "#r");
                    }
                    ++i;
                }

                if (MyIsif.getStyle().equals("水墨"))
                    wuLing.setLocation(jScrollPane2.getX() + 5, jScrollPane2.getViewport().getHeight()-30);//水墨悟灵技能说明位置
                else
                    wuLing.setLocation(jScrollPane2.getX()+5 , jScrollPane2.getViewport().getHeight()-5);//红木悟灵技能说明位置

            }
            wuLing.update(skill, skillwl);
        }
    }
    
    public String setText(Skill skill, double sld, int lvl) {
        String msg = skill.getRemark();
        int id = Integer.parseInt(skill.getSkillid());
        if (id < 1001 || id > 1100) {
            return null;
        }
        int level = Integer.parseInt(skill.getSkilllevel());
        double sv = Double.parseDouble(skill.getGrow());
        double mv = Double.parseDouble(skill.getDielectric());
        double value = Double.parseDouble(skill.getValue());
        String type = (id <= 1005) ? "混乱" : ((id <= 1010) ? "封印" : ((id <= 1015) ? "昏睡" : ((id <= 1020) ? "中毒" : ((id <= 1025) ? "震慑" : ((id <= 1030) ? "力量" : ((id <= 1035) ? "抗性" : ((id <= 1040) ? "加速" : ((id <= 1045) ? "风" : ((id <= 1050) ? "雷" : ((id <= 1055) ? "水" : ((id <= 1060) ? "火" : ((id <= 1065) ? "鬼火" : ((id <= 1070) ? "三尸虫" : ((id <= 1075) ? "遗忘" : ((id <= 1080) ? "smmh" : ((id <= 1085) ? "霹雳" : ((id <= 1090) ? "沧波" : ((id <= 1095) ? "甘霖" : "扶摇"))))))))))))))))));
        msg = msg.replace("|个数|", FBUtil.geshu(level, (int)sld, type) + "");
        if (id <= 1015 || (id >= 1071 && id <= 1075)) {
            BigDecimal skillhitrate = new BigDecimal(value + sv * new BigDecimal(Math.pow(sld, 0.3)).setScale(2, 4).doubleValue());
            msg = msg.replace("|几率|", skillhitrate.setScale(2, 4).toString());
            msg = msg.replace("|回合|", "7");
        }
        else if (id <= 1020) {
            BigDecimal skillhitrate = new BigDecimal((double)(int)((value + sld * sv / 1000.0) * 1000.0) / 15.0 * 17.0);
            msg = msg.replace("|伤害|", (level > 3) ? "15" : ((level > 1) ? "12.5" : "10"));
            msg = msg.replace("|几率|", skillhitrate.setScale(2, 4).toString());
            msg = msg.replace("|回合|", "3");
        }
        else if (id <= 1040 || (id >= 1076 && id <= 1080)) {
            BigDecimal skillhitrate = new BigDecimal((double)(int)((value + sld * sv / 1000.0) * 1000.0) / 10.0);
            msg = msg.replace("|回合|", "7");
            msg = msg.replace("|伤害|", skillhitrate.setScale(2, 4).toString());
        }
        else if (id <= 1065 || id >= 1081) {
            BigDecimal skillhitrate = new BigDecimal((value + sv * (1.0 + 5.0 * sld / 5000.0 * (10.0 - sld / 5000.0) / 2.0)) * (double)lvl);
            msg = msg.replace("|伤害|", skillhitrate.intValue() + "");
        }
        else if (id <= 1070) {
            msg = msg.replace("|伤害|", (int)((value * (double)lvl + sld * sv / 1000.0) * 1000.0) + "");
            msg = msg.replace("|几率|", (int)(value * 100.0 + (double)(int)(sld / 250.0)) + "");
        }
        msg = msg.replace("|蓝|", (int)(mv * (sld / 25000.0 + 1.0)) + "");
        return msg;
    }
    
    public String gongshi(String[] v, int i) {
        if (v.length > i + 1) {
            String[] vs = v[i + 1].split("=");
            if (vs.length > 1 && (vs[1].equals("{公式一}") || vs[1].equals("{公式二}") || vs[1].equals("{公式三}") || vs[1].equals("{公式四}") || vs[1].equals("{公式五}"))) {
                return "1";
            }
        }
        return null;
    }
    
    public static String StringReplace(String vs) {
        Pattern p = Pattern.compile(">(.*?)<");
        Matcher m = p.matcher(vs);
        Pattern pend = Pattern.compile("'#(.*?)'");
        Matcher mend = pend.matcher(vs);
        StringBuffer sb1 = new StringBuffer();
        while (mend.find()) {
            String s = mend.group();
            if (!sb1.toString().equals("")) {
                sb1.append("|" + s.split("'#")[1].split("'")[0]);
            }
            else {
                sb1.append(s.split("'#")[1].split("'")[0]);
            }
        }
        StringBuffer sb2 = new StringBuffer();
        while (m.find()) {
            String s2 = m.group();
            if (!s2.equals("><")) {
                if (!sb2.toString().equals("")) {
                    sb2.append("|" + s2.split(">")[1].split("<")[0]);
                }
                else {
                    sb2.append(s2.split(">")[1].split("<")[0]);
                }
            }
        }
        StringBuffer buffer = new StringBuffer();
        String[] v = sb1.toString().split("\\|");
        String[] v2 = sb2.toString().split("\\|");
        for (int i = 0; i < v.length && i < v2.length; ++i) {
            if (!buffer.toString().equals("")) {
                buffer.append("|" + v[i] + "=" + v2[i]);
            }
            else {
                buffer.append(v[i] + "=" + v2[i]);
            }
        }
        return buffer.toString();
    }
    
    public JList<String> getListNaturalskill() {
        return this.listNaturalskill;
    }
    
    public void setListNaturalskill(JList<String> listNaturalskill) {
        this.listNaturalskill = listNaturalskill;
    }
    
    public JList<String> getListpetskill() {
        return this.listpetskill;
    }
    
    public void setListpetskill(JList<String> listpetskill) {
        this.listpetskill = listpetskill;
    }
    
    public DefaultListModel<String> getModelNaturalskill() {
        return this.modelNaturalskill;
    }
    
    public void setModelNaturalskill(DefaultListModel<String> modelNaturalskill) {
        this.modelNaturalskill = modelNaturalskill;
    }
    
    public DefaultListModel<String> getModelpetskill() {
        return this.modelpetskill;
    }
    
    public void setModelpetskill(DefaultListModel<String> modelpetskill) {
        this.modelpetskill = modelpetskill;
    }
    
    public JScrollPane getjScrollPane1() {
        return this.jScrollPane1;
    }
    
    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }
    
    public JScrollPane getjScrollPane2() {
        return this.jScrollPane2;
    }
    
    public void setjScrollPane2(JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }
    
    public RichLabel getBox() {
        return this.box;
    }
    
    public void setBox(RichLabel box) {
        this.box = box;
    }
    
    public JLabel[] getLabPetskills() {
        return this.labPetskills;
    }
    
    public void setLabPetskills(JLabel[] labPetskills) {
        this.labPetskills = labPetskills;
    }
    
    public ShowPetSkillsMouslisten[] getShowPetSkills() {
        return PetSkillsJpanel.showPetSkills;
    }
    
    public void setShowPetSkills(ShowPetSkillsMouslisten[] showPetSkills) {
        PetSkillsJpanel.showPetSkills = showPetSkills;
    }
    
    public DeleteSkillBtn getSupportListBtn() {
        return this.supportListBtn;
    }
    
    public void setSupportListBtn(DeleteSkillBtn supportListBtn) {
        this.supportListBtn = supportListBtn;
    }
    
    public DeleteSkillBtn getDeleteSkillBtn() {
        return this.deleteSkillBtn;
    }
    
    public void setDeleteSkillBtn(DeleteSkillBtn deleteSkillBtn) {
        this.deleteSkillBtn = deleteSkillBtn;
    }
    
    public String getPetskillID() {
        return this.petskillID;
    }
    
    public void setPetskillID(String petskillID) {
        this.petskillID = petskillID;
    }
    
    public int getPetskillNO() {
        return this.petskillNO;
    }
    
    public void setPetskillNO(int petskillNO) {
        this.petskillNO = petskillNO;
    }
    
    public JLabel[] getQlPetskills() {
        return this.qlPetskills;
    }
    
    public void setQlPetskills(JLabel[] qlPetSkills) {
        this.qlPetskills = qlPetSkills;
    }
    
    public ShowPetSkillsMouslisten[] getShowQlPetSkills() {
        return this.showQlPetSkills;
    }
    
    public void setShowQlPetSkills(ShowPetSkillsMouslisten[] showQlPetSkills) {
        this.showQlPetSkills = showQlPetSkills;
    }
    
    public Sprite getTcp() {
        return PetSkillsJpanel.tcp;
    }
    
    public void setTcp(Sprite tcp) {
        PetSkillsJpanel.tcp = tcp;
    }
    
    public void mouseEntered(MouseEvent e) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
    }
    
    public void mouseExited(MouseEvent e) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
    }
    
    public ShowPetSkillsMouslisten[] getShowPetSkillsTS() {
        return this.showPetSkillsTS;
    }
    
    public void setShowPetSkillsTS(ShowPetSkillsMouslisten[] showPetSkillsTS) {
        this.showPetSkillsTS = showPetSkillsTS;
    }
    
    public JLabel[] getLabPetskillsTS() {
        return this.labPetskillsTS;
    }
    
    public void setLabPetskillsTS(JLabel[] labPetskillsTS) {
        this.labPetskillsTS = labPetskillsTS;
    }
    
    public JLabel[] getLabPetskillBacks() {
        return this.labPetskillBacks;
    }
    
    static {
        PetSkillsJpanel.showPetSkills = new ShowPetSkillsMouslisten[9];
        PetSkillsJpanel.tcp = SpriteFactory.VloadSprite("resource/mouse/flicker.tcp", null);
    }
}
