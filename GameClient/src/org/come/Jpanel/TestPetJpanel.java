package org.come.Jpanel;

import org.come.mouslisten.PetJpanelJlistChoseMouslisten;
import org.come.until.*;

import java.awt.event.MouseEvent;
import java.awt.RenderingHints;

import com.tool.image.ImageMixDeal;
import java.awt.Graphics2D;
import java.awt.Graphics;

import org.come.Frame.PetPrderJframe;
import java.util.ArrayList;
import com.tool.role.RoleData;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.mouslisten.WLLMouslisten;
import org.come.mouslisten.PointChangeMouslisten;
import javax.swing.BorderFactory;

import come.tool.Fighting.Fightingimage;
import java.util.List;
import come.tool.Fighting.TypeState;
import org.come.entity.RoleSummoning;
import come.tool.Fighting.FightingMixDeal;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import java.awt.Font;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.math.BigDecimal;
import org.come.model.Configure;
import com.tool.tcp.NewPart;
import org.soaring.btn.CharacterBtn;
import com.tool.btn.NeidanBtn;
import javax.swing.JTextArea;
import com.tool.btn.GoodAndPetLockBtn;
import com.tool.btn.PetPanelBtn;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Color;
import org.come.entity.PackRecord;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class TestPetJpanel extends JPanel implements MouseListener
{
    private static final ImageIcon ICON_TIANFU;//天赋图标
    private ImageIcon icon;
    private static TestPetJpanel testPetJpanel;
    private static JList<String> listpet;
    private static DefaultListModel<String> listModel;
    private PackRecord packRecord;
    private Color fontcolor;
    private Color niubikelas;
    private JScrollPane jScrollPane;
    private int level;
    private static JTextField labname;
    private static JLabel lablevel;
    private static JLabel labloyalty;
    private static JLabel labclose;
    private static JLabel labHP;
    private static JLabel labMP;
    private static JLabel labAP;
    private static JLabel labSP;
    private static JLabel labCP;
    private static JLabel labEXP;
    private static JLabel labrootbone;
    private static JLabel labintelligence;
    private static JLabel labpower;
    private static JLabel labspeed;
    private static JLabel labconcentrate;
    private static JLabel labpointnumber;
    private static JLabel changeImg;
    private static JLabel tfLab;
    private static JLabel tianfucount;
    private PetPanelBtn btnpetnamech;
    private PetPanelBtn lingxi;
    private PetPanelBtn btnwar;
    private PetPanelBtn btnpetgk;
    private PetPanelBtn btnpetyc;
    private PetPanelBtn btnaddlittle;
    private PetPanelBtn btndomesticated;
    private PetPanelBtn btnpetkills;
    private PetPanelBtn btnpetarticles;
    private PetPanelBtn btnsmeltmonster;
    private PetPanelBtn btnrelease;
    private PetPanelBtn btnpalPet;
    private PetPanelBtn btnRolequality;
    private PetPanelBtn btnsummon;
    private PetPanelBtn btnOrder;
    private PetPanelBtn btnpetc;
    private PetPanelBtn btnpetq;
    private GoodAndPetLockBtn btnlock;
    private GoodAndPetLockBtn btnunlock;
    private static JLabel petsum;
    private JTextArea area;
    private static NeidanBtn[] labNedan;
    private CharacterBtn[] dians;
    ImageIcon imageIcon;
    public static int warNum;
    public static NewPart part;

    public boolean isBigOsmall() {
        return bigOsmall;
    }

    public void setBigOsmall(boolean bigOsmall) {
        this.bigOsmall = bigOsmall;
    }
    public PetPanelBtn getBtnTouOrName() {
        return btnTouOrName;
    }

    public void setBtnTouOrName(PetPanelBtn btnTouOrName) {
        this.btnTouOrName = btnTouOrName;
    }

    //新增
    public static boolean bigOsmall;//切换界面1
    private PetPanelBtn btnBigOrSmall;//切换界面

    private PetPanelBtn btnTouOrName;//切换头像
    public static boolean TouOrName;//切换头像1
    private PetPanelBtn btnaction;//切换动作
    private PetPanelBtn btndir1;//切换方向
    private PetPanelBtn btndir2;//切换方向

    public static int dir = 0;
    public static int action = 9;
    public static boolean change = false;//5454

    private FormsOnOffBtn offBtn;


    public TestPetJpanel() throws Exception {

        int zhong = 400;
        this.btnaction = new PetPanelBtn("inkImg/button/156.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "", 780, this);
        this.btnaction.setBounds(zhong, 193, 24, 25);//切换动作
        this.add(btnaction);

        this.btndir1 = new PetPanelBtn("inkImg/button/90.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "", 781, this);
        this.btndir1.setBounds(zhong-47, 193-2, 28, 17);//左方向按钮
        this.add(btndir1);

        this.btndir2 = new PetPanelBtn("inkImg/button/91.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "", 782, this);
        this.btndir2.setBounds(zhong+40, 193-2, 28, 17);//切换箭头
        this.add(btndir2);


        this.dians = new CharacterBtn[10];// 创建包含10个角色按钮的数组
        this.imageIcon = new ImageIcon("img/123_副本.png");//按钮图标
        //布局基准坐标
        int x1 = 195;
        int x0 = 206;
        //读取配置信息
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        // 根据界面风格初始化不同布局
        if (MyIsif.getStyle().equals("水墨")) {
            (btnBigOrSmall = new PetPanelBtn("inkImg/button/88.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "", 778, this)).setBounds(15, 512-33, 24, 34);
            this.add(btnBigOrSmall);
            // 水墨风格界面初始化
            (btnTouOrName = new PetPanelBtn("inkImg/button/86.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "", 779, this)).setBounds(60, 20, 20, 20);
            this.add(btnTouOrName);

            this.add(this.getChangeImg());
            (TestPetJpanel.testPetJpanel = this).setPreferredSize(new Dimension(393, 512));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 6);
            offBtn.setBounds(588-35, 10, 25, 25);
            this.add(offBtn);
            TestPetJpanel.listModel = new DefaultListModel<>();
            (TestPetJpanel.listpet = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(20, 110, 210));
            this.fontcolor = Color.white;
            TestPetJpanel.listpet.setSelectionForeground(this.fontcolor);
            TestPetJpanel.listpet.setForeground(this.fontcolor);
            TestPetJpanel.listpet.setFont(new Font("微软雅黑", 1, 14));
            TestPetJpanel.listpet.setBackground(UIUtils.Color_BACK);
            TestPetJpanel.listpet.setModel(TestPetJpanel.listModel);
            TestPetJpanel.listpet.setSelectionMode(0);
            DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
                Color color = new Color(203, 181, 91);

                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (TestPetJpanel.warNum == index) {
                        this.setForeground(Color.orange);
                    }
                    else if (FightingMixDeal.State != 0) {
                        List<RoleSummoning> plist = UserMessUntil.getPetListTable();
                        Fightingimage fightingimage = FightingMixDeal.getdata(0);
                        if (fightingimage != null && fightingimage.getFightingManData() != null) {
                            List<TypeState> data = fightingimage.getFightingManData().cxxx("召唤兽");
                            if (data != null && data.size() > 0) {
                                RoleSummoning roleSummoning = (RoleSummoning)plist.get(index);
                                for (int i = 0; i < data.size(); ++i) {
                                    if (((TypeState)data.get(i)).getState() != 0 && roleSummoning.getSid().intValue() == Integer.parseInt(((TypeState)data.get(i)).getType())) {
                                        this.setForeground(Color.red);
                                    }
                                }
                            }
                        }
                    }
                    return this;
                }
            };
            TestPetJpanel.listpet.setCellRenderer(new MyRenderer());
            TestPetJpanel.listpet.addMouseListener(new PetJpanelJlistChoseMouslisten(TestPetJpanel.listpet, this));
            TestPetJpanel.listpet.addMouseMotionListener(new PetJpanelJlistChoseMouslisten(TestPetJpanel.listpet, this));
            (this.jScrollPane = new JScrollPane(TestPetJpanel.listpet)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(47, 42, 193-4, 453);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            TestPetJpanel.labNedan = new NeidanBtn[Integer.parseInt(configure.getNdslsx())];
            for (int i = 0; i < Integer.parseInt(configure.getNdslsx()); ++i) {
                (TestPetJpanel.labNedan[i] = new NeidanBtn("inkImg/button/B161-1.png", 1, i)).setBounds(262, 60 + i * 18, 72, 18);
                this.add(TestPetJpanel.labNedan[i]);//内胆显示
            }
            for (int i = 0; i < this.dians.length; ++i) {
                if (i % 2 == 0) {
                    this.dians[i] = new CharacterBtn("inkImg/button/12.png", 1, 10 + i);//加点
                }
                else {
                    this.dians[i] = new CharacterBtn("inkImg/button/11.png", 1, 10 + i);//加点
                }
                this.dians[i].setBounds(x1+349 + i % 2 * 14, 313 + i / 2 * 27, 12, 16);
                this.dians[i].addMouseListener(new PointChangeMouslisten(i + 10));
                this.add(this.dians[i]);
            }
            Font font = new Font("微软雅黑", 1, 14);
            (TestPetJpanel.labname = new JTextField()).setBounds(x0+81, 193+35, 99, 18);//名称输入框
            TestPetJpanel.labname.setForeground(Color.white);
            TestPetJpanel.labname.setOpaque(false);
            TestPetJpanel.labname.setBorder(BorderFactory.createEmptyBorder());
            TestPetJpanel.labname.setFont(font);
            TestPetJpanel.labname.setCaretColor(Color.white);
            this.add(TestPetJpanel.labname);
            (TestPetJpanel.lablevel = new JLabel()).setBounds(x0+81, 221+37, 99, 15);//等级显示
            TestPetJpanel.lablevel.setForeground(Color.WHITE);
            TestPetJpanel.lablevel.setFont(font);
            this.add(TestPetJpanel.lablevel);
            (TestPetJpanel.labloyalty = new JLabel()).setBounds(x0+81, 247+39, 99, 15);//忠诚度显示
            TestPetJpanel.labloyalty.setForeground(Color.WHITE);
            TestPetJpanel.labloyalty.setFont(font);
            this.add(TestPetJpanel.labloyalty);
            (TestPetJpanel.labclose = new JLabel()).setBounds(481, 258, 99, 15);//关闭按钮
            TestPetJpanel.labclose.setForeground(Color.WHITE);
            TestPetJpanel.labclose.setFont(font);
            this.add(TestPetJpanel.labclose);
            (TestPetJpanel.labHP = new JLabel()).setBounds(x0+81, 297+41-27, 135, 15);
            TestPetJpanel.labHP.setForeground(Color.WHITE);
            TestPetJpanel.labHP.setFont(font);
            this.add(TestPetJpanel.labHP);
            (TestPetJpanel.labMP = new JLabel()).setBounds(x0+81, 322+43-27, 135, 15);
            TestPetJpanel.labMP.setForeground(Color.WHITE);
            TestPetJpanel.labMP.setFont(font);
            this.add(TestPetJpanel.labMP);
            (TestPetJpanel.labAP = new JLabel()).setBounds(x0+81, 346+45-27, 135, 15);
            TestPetJpanel.labAP.setForeground(Color.WHITE);
            TestPetJpanel.labAP.setFont(font);
            this.add(TestPetJpanel.labAP);
            (TestPetJpanel.labSP = new JLabel()).setBounds(x0+81, 372+47-27, 135, 15);
            TestPetJpanel.labSP.setForeground(Color.WHITE);
            TestPetJpanel.labSP.setFont(font);
            this.add(TestPetJpanel.labSP);
            (TestPetJpanel.labCP = new JLabel("135")).setBounds(x0+81-27, 399+49, 135, 15);
            TestPetJpanel.labCP.setForeground(Color.WHITE);
            TestPetJpanel.labCP.setFont(font);
            this.add(TestPetJpanel.labCP);
            (TestPetJpanel.labEXP = new JLabel()).setBounds(x0+81-27, 422+49, 135, 15);//待写
            TestPetJpanel.labEXP.setForeground(Color.WHITE);
            TestPetJpanel.labEXP.setFont(new Font("微软雅黑", 1, 12));
            this.add(TestPetJpanel.labEXP);
            (TestPetJpanel.labrootbone = new JLabel()).setBounds(x1+272, 297+15, 60, 15);
            TestPetJpanel.labrootbone.setForeground(Color.WHITE);
            TestPetJpanel.labrootbone.setFont(font);
            this.add(TestPetJpanel.labrootbone);
            (TestPetJpanel.labintelligence = new JLabel()).setBounds(x1+272, 322+17, 60, 15);
            TestPetJpanel.labintelligence.setForeground(Color.WHITE);
            TestPetJpanel.labintelligence.setFont(font);
            this.add(TestPetJpanel.labintelligence);
            (TestPetJpanel.labpower = new JLabel()).setBounds(x1+272, 346+19, 60, 15);
            TestPetJpanel.labpower.setForeground(Color.WHITE);
            TestPetJpanel.labpower.setFont(font);
            this.add(TestPetJpanel.labpower);
            (TestPetJpanel.labspeed = new JLabel()).setBounds(x1+272, 372+21, 60, 15);
            TestPetJpanel.labspeed.setForeground(Color.WHITE);
            TestPetJpanel.labspeed.setFont(font);
            this.add(TestPetJpanel.labspeed);
            (TestPetJpanel.labconcentrate = new JLabel("0")).setBounds(x1+272, 398+23, 60, 15);
            TestPetJpanel.labconcentrate.setForeground(Color.WHITE);
            TestPetJpanel.labconcentrate.setFont(font);
            this.add(TestPetJpanel.labconcentrate);
            (TestPetJpanel.labpointnumber = new JLabel()).setBounds(x1+325, 271+14, 40, 15);
            TestPetJpanel.labpointnumber.setForeground(Color.WHITE);
            TestPetJpanel.labpointnumber.setFont(font);
            this.add(TestPetJpanel.labpointnumber);
            (this.btnOrder = new PetPanelBtn("inkImg/button1/B31.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "排", Integer.valueOf(25), this)).setBounds(238+30, 20, 18, 18);
            this.add(this.btnOrder);
            (this.btnpetc = new PetPanelBtn("inkImg/button1/B31.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "存", Integer.valueOf(24), this)).setBounds(260+30, 20, 18, 18);
            this.add(this.btnpetc);
            (this.btnpetq = new PetPanelBtn("inkImg/button1/B31.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "取", Integer.valueOf(23), this)).setBounds(282+30, 20, 18, 18);
            this.add(this.btnpetq);
            (this.btnpetgk = new PetPanelBtn("inkImg/button/19.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "观", Integer.valueOf(20), this)).setBounds(220+30, 20, 20, 20);
            (this.btnpetyc = new PetPanelBtn("inkImg/button/19.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "隐", Integer.valueOf(21), this)).setBounds(245+30, 20, 20, 20);
            (this.btnpalPet = new PetPanelBtn("inkImg/button1/B31.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "伙", Integer.valueOf(110), this)).setBounds(216+30, 20, 18, 18);
            this.add(this.btnpalPet);
            (this.btnpetnamech = new PetPanelBtn("inkImg/button1/B31.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "改", Integer.valueOf(1), this)).setBounds(x0+193, 194+35, 18, 18);
            this.add(this.btnpetnamech);
            (this.btndomesticated = new PetPanelBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "驯养", Integer.valueOf(5), this)).setBounds(x0+177, 245+39, 34, 17);
            this.add(this.btndomesticated);
            (this.btnrelease = new PetPanelBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "放生", Integer.valueOf(9), this)).setBounds(x0+177, 219+38, 34, 17);
            this.add(this.btnrelease);
            (this.lingxi = new PetPanelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY88, "灵犀", Integer.valueOf(601), this)).setBounds(x1+238, 241-15, 59, 24);
            this.lingxi.setVisible(false);
            (this.btnwar = new PetPanelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "参战", Integer.valueOf(2), this)).setBounds(x1+315, 241-15, 59, 24);
            this.add(this.btnwar);
            (this.btnaddlittle = new PetPanelBtn("inkImg/button1/B32.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "确认加点", Integer.valueOf(4), this)).setBounds(195+260+310-117, 398+17, 51, 17);
            this.add(this.btnaddlittle);
            (this.btnpetarticles = new PetPanelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "物品", Integer.valueOf(7), this)).setBounds(x1+59, 448+10, 59, 24);
            this.add(this.btnpetarticles);
            (this.btnsmeltmonster = new PetPanelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "炼妖", Integer.valueOf(8), this)).setBounds(x1+137, 448+10, 59, 24);
            this.add(this.btnsmeltmonster);
            (this.btnsummon = new PetPanelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "召唤", Integer.valueOf(11), this)).setBounds(x1+175, 448+10, 68, 26);
            (this.btnRolequality = new PetPanelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "抗性", Integer.valueOf(10), this)).setBounds(x1+213, 448+10, 59, 24);
            this.add(this.btnRolequality);
            (this.btnpetkills = new PetPanelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "技能", Integer.valueOf(6), this)).setBounds(x1+291, 448+10, 59, 24);
            this.add(this.btnpetkills);
            (this.btnlock = new GoodAndPetLockBtn("inkImg/button1/K156.png", 1, 3)).setBounds(304+30, 20, 18, 18);
            this.add(this.btnlock);
            (this.btnunlock = new GoodAndPetLockBtn("inkImg/button1/K157.png", 1, 4)).setBounds(326+30, 20, 18, 18);
            this.add(this.btnunlock);
            (TestPetJpanel.petsum = new JLabel()).setForeground(Color.WHITE);//召唤兽数量
            TestPetJpanel.petsum.setFont(new Font("仿宋", 1, 15));
            this.add(TestPetJpanel.petsum);
        }
        else {
            (btnTouOrName = new PetPanelBtn("inkImg/hongmu/HM86.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "", 779, this)).setBounds(60, 20, 20, 20);
            this.add(btnTouOrName);

            (btnBigOrSmall = new PetPanelBtn("inkImg/hongmu/HM88.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "", 778, this)).setBounds(15, 512-34, 24, 34);
            this.add(btnBigOrSmall);

            this.add(this.getChangeImg());
            (TestPetJpanel.testPetJpanel = this).setPreferredSize(new Dimension(356, 517));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 6);
            offBtn.setBounds(333, 2, 23, 23);
            this.add(offBtn);
            TestPetJpanel.listModel = new DefaultListModel<>();
            (TestPetJpanel.listpet = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            this.fontcolor = Color.white;
            TestPetJpanel.listpet.setSelectionForeground(this.fontcolor);
            TestPetJpanel.listpet.setForeground(this.fontcolor);
            TestPetJpanel.listpet.setFont(new Font("微软雅黑", 1, 14));
            TestPetJpanel.listpet.setBackground(UIUtils.Color_BACK);
            TestPetJpanel.listpet.setModel(TestPetJpanel.listModel);
            TestPetJpanel.listpet.setSelectionMode(0);
            DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
                final Color color = Color.white;

                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (TestPetJpanel.warNum == index) {
                        this.setForeground(Color.orange);
                    }
                    else if (FightingMixDeal.State != 0) {
                        List<RoleSummoning> plist = UserMessUntil.getPetListTable();
                        Fightingimage fightingimage = FightingMixDeal.getdata(0);
                        if (fightingimage != null && fightingimage.getFightingManData() != null) {
                            List<TypeState> data = fightingimage.getFightingManData().cxxx("召唤兽");
                            if (data != null && data.size() > 0) {
                                RoleSummoning roleSummoning = (RoleSummoning)plist.get(index);
                                for (int i = 0; i < data.size(); ++i) {
                                    if (((TypeState)data.get(i)).getState() != 0 && roleSummoning.getSid().intValue() == Integer.parseInt(((TypeState)data.get(i)).getType())) {
                                        this.setForeground(Color.red);
                                    }
                                }
                            }
                        }
                    }
                    return this;
                }
            };
            TestPetJpanel.listpet.setCellRenderer(new MyRenderer());
            TestPetJpanel.listpet.addMouseListener(new PetJpanelJlistChoseMouslisten(TestPetJpanel.listpet, this));
            TestPetJpanel.listpet.addMouseMotionListener(new PetJpanelJlistChoseMouslisten(TestPetJpanel.listpet, this));
            (this.jScrollPane = new JScrollPane(TestPetJpanel.listpet)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(25, 55, 145, 132);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            TestPetJpanel.labNedan = new NeidanBtn[Integer.parseInt(configure.getNdslsx())];
            for (int i = 0; i < Integer.parseInt(configure.getNdslsx()); ++i) {
                (TestPetJpanel.labNedan[i] = new NeidanBtn("img/xy2uiimg/4_png.button.btn_pet_bar.png", 1, i)).setBounds(178, 62 + i * 18, 15, 15);
                this.add(TestPetJpanel.labNedan[i]);
            }
            for (int i = 0; i < this.dians.length; ++i) {
                if (i % 2 == 0) {
                    this.dians[i] = new CharacterBtn("img/xy2uiimg/26_png.button.btn_left.png", 1, 10 + i);//加点左
                }
                else {
                    this.dians[i] = new CharacterBtn("img/xy2uiimg/54_png.button.btn_right.png", 1, 10 + i);//加点右
                }
                this.dians[i].setBounds(305 + i % 2 * 13, 313 + i / 2 * 25, 13, 13);
                this.dians[i].addMouseListener(new PointChangeMouslisten(i + 10));
                this.add(this.dians[i]);
            }//红木界面
            Font font = new Font("微软雅黑", 1, 14);// 使用微软雅黑字体，粗体，14号大小
            (TestPetJpanel.labname = new JTextField()).setBounds(59, 208, 99, 18);//宠物名称输入框
            TestPetJpanel.labname.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labname.setOpaque(false);
            TestPetJpanel.labname.setBorder(BorderFactory.createEmptyBorder());
            TestPetJpanel.labname.setCaretColor(new Color(227, 223, 13));
            this.add(TestPetJpanel.labname);
            (TestPetJpanel.lablevel = new JLabel()).setBounds(59, 234, 99, 15);//等级显示
            TestPetJpanel.lablevel.setForeground(Color.YELLOW);
            TestPetJpanel.lablevel.setFont(font);
            this.add(TestPetJpanel.lablevel);
            (TestPetJpanel.labloyalty = new JLabel()).setBounds(59, 260, 99, 15);//忠诚度
            TestPetJpanel.labloyalty.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labloyalty.setFont(font);
            this.add(TestPetJpanel.labloyalty);
            (TestPetJpanel.labclose = new JLabel("3213115611")).setBounds(59, 286, 99, 15);
            TestPetJpanel.labclose.addMouseListener(new WLLMouslisten(22));
            TestPetJpanel.labclose.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labclose.setFont(font);
            this.add(TestPetJpanel.labclose);
            (TestPetJpanel.labHP = new JLabel()).setBounds(59, 312, 120, 15);//气血
            TestPetJpanel.labHP.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labHP.setFont(font);
            this.add(TestPetJpanel.labHP);
            (TestPetJpanel.labMP = new JLabel()).setBounds(59, 337, 120, 15);//法力
            TestPetJpanel.labMP.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labMP.setFont(font);
            this.add(TestPetJpanel.labMP);
            (TestPetJpanel.labAP = new JLabel()).setBounds(59, 362, 120, 15);//攻击力
            TestPetJpanel.labAP.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labAP.setFont(font);
            this.add(TestPetJpanel.labAP);
            (TestPetJpanel.labSP = new JLabel()).setBounds(59, 387, 120, 15);//速度
            TestPetJpanel.labSP.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labSP.setFont(font);
            this.add(TestPetJpanel.labSP);
            (TestPetJpanel.labCP = new JLabel("135")).setBounds(59, 412, 120, 15);
            TestPetJpanel.labCP.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labCP.setFont(font);
            this.add(TestPetJpanel.labCP);
            (TestPetJpanel.labEXP = new JLabel()).setBounds(59, 437, 120, 15);//经验
            TestPetJpanel.labEXP.addMouseListener(new WLLMouslisten(23));
            TestPetJpanel.labEXP.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labEXP.setFont(new Font("微软雅黑", 1, 12));
            this.add(TestPetJpanel.labEXP);
            (TestPetJpanel.labrootbone = new JLabel()).setBounds(238, 312, 60, 15);//根骨
            TestPetJpanel.labrootbone.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labrootbone.setFont(font);
            this.add(TestPetJpanel.labrootbone);
            (TestPetJpanel.labintelligence = new JLabel()).setBounds(238, 337, 60, 15);//灵性
            TestPetJpanel.labintelligence.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labintelligence.setFont(font);
            this.add(TestPetJpanel.labintelligence);
            (TestPetJpanel.labpower = new JLabel()).setBounds(238, 362, 60, 15);//力量
            TestPetJpanel.labpower.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labpower.setFont(font);
            this.add(TestPetJpanel.labpower);
            (TestPetJpanel.labspeed = new JLabel()).setBounds(238, 387, 60, 15);//敏捷
            TestPetJpanel.labspeed.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labspeed.setFont(font);
            this.add(TestPetJpanel.labspeed);
            (TestPetJpanel.labconcentrate = new JLabel("0")).setBounds(238, 412, 60, 15);
            TestPetJpanel.labconcentrate.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labconcentrate.setFont(font);
            this.add(TestPetJpanel.labconcentrate);
            (TestPetJpanel.labpointnumber = new JLabel()).setBounds(290, 286, 40, 15);//点数
            TestPetJpanel.labpointnumber.setForeground(new Color(227, 223, 13));
            TestPetJpanel.labpointnumber.setFont(font);
            this.add(TestPetJpanel.labpointnumber);
            (this.btnpetnamech = new PetPanelBtn("img/soaring/改w17,h51px.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT2, "改", Integer.valueOf(1), this)).setBounds(158, 209, 17, 17);
            this.add(this.btnpetnamech);
            (this.btndomesticated = new PetPanelBtn("img/soaring/驯养w34,h51px.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT2, "驯养", Integer.valueOf(5), this)).setBounds(144, 260, 34, 17);
            this.add(this.btndomesticated);
            (this.btnrelease = new PetPanelBtn("img/soaring/放生w34,h51px.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT2, "放生", Integer.valueOf(9), this)).setBounds(144, 234, 34, 17);
            this.add(this.btnrelease);
            (this.lingxi = new PetPanelBtn("inkImg/hongmu/50x50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT1B, "灵犀", Integer.valueOf(601), this)).setBounds(220, 251, 50, 30);
            this.lingxi.setVisible(false);
            (this.btnwar = new PetPanelBtn("inkImg/hongmu/50x50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT1B, "参战", Integer.valueOf(2), this)).setBounds(280, 251, 50, 30);
            this.add(this.btnwar);
            (this.btnaddlittle = new PetPanelBtn("img/soaring/确认加点w68,h51px.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "", Integer.valueOf(4), this)).setBounds(250, 437, 68, 17);
            this.add(this.btnaddlittle);
            (this.btnpetarticles = new PetPanelBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT1B, "物品", Integer.valueOf(7), this)).setBounds(26, 465, 60, 26);
            this.add(this.btnpetarticles);
            (this.btnsmeltmonster = new PetPanelBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT1B, "炼妖", Integer.valueOf(8), this)).setBounds(106, 465, 60, 26);
            this.add(this.btnsmeltmonster);
            (this.btnsummon = new PetPanelBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT1B, "召唤", Integer.valueOf(11), this)).setBounds(147, 465, 60, 26);
            (this.btnRolequality = new PetPanelBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT1B, "抗性", Integer.valueOf(10), this)).setBounds(189, 465, 60, 26);
            this.add(this.btnRolequality);
            (this.btnpetkills = new PetPanelBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT1B, "技能", Integer.valueOf(6), this)).addMouseListener(new WLLMouslisten(33));
            this.btnpetkills.setBounds(270, 465, 60, 26);
            this.add(this.btnpetkills);
            (this.btnlock = new GoodAndPetLockBtn("img/xy2uiimg/24_png.button.btn_lock.png", 1, 3)).setBounds(272, 33, 19, 19);
            this.add(this.btnlock);
            (this.btnunlock = new GoodAndPetLockBtn("img/xy2uiimg/27_png.button.btn_unlock.png", 1, 4)).setBounds(294, 33, 19, 19);
            this.add(this.btnunlock);
            (this.btnOrder = new PetPanelBtn("inkImg/hongmu/btn-small-1-bg.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT2, "排", Integer.valueOf(25), this)).setBounds(198, 33, 19, 19);
            this.add(this.btnOrder);
            (this.btnpetc = new PetPanelBtn("inkImg/hongmu/btn-small-1-bg.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT2, "存", Integer.valueOf(24), this)).setBounds(220, 33, 19, 19);
            this.add(this.btnpetc);
            (this.btnpetq = new PetPanelBtn("inkImg/hongmu/btn-small-1-bg.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT2, "取", Integer.valueOf(23), this)).setBounds(242, 33, 19, 19);
            this.add(this.btnpetq);
            (this.btnpetgk = new PetPanelBtn("inkImg/hongmu/btn-small-1-bg.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT2, "观", Integer.valueOf(20), this)).setBounds(200, 37, 19, 19);
            (this.btnpetyc = new PetPanelBtn("inkImg/hongmu/btn-small-1-bg.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT2, "隐", Integer.valueOf(21), this)).setBounds(220, 37, 19, 19);
            (this.btnpalPet = new PetPanelBtn("inkImg/hongmu/btn-small-1-bg.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT2, "伙", Integer.valueOf(110), this)).setBounds(176, 33, 19, 19);
            this.add(this.btnpalPet);
            (TestPetJpanel.petsum = new JLabel()).setForeground(new Color(187, 165, 75));
            TestPetJpanel.petsum.setFont(new Font("仿宋", 1, 14));
            this.add(TestPetJpanel.petsum);
        }
        if (MyIsif.getStyle().equals("水墨")) {
            (TestPetJpanel.tianfucount = new JLabel()).setForeground(Color.WHITE);
            TestPetJpanel.tianfucount.setBackground(UIUtils.Color_BACK);
            TestPetJpanel.tianfucount.setFont(UIUtils.TEXT_FONT2);
            TestPetJpanel.tianfucount.setBounds(352, 38, 40, 20);
            this.add(TestPetJpanel.tianfucount);
            (TestPetJpanel.tfLab = new JLabel()).setBackground(UIUtils.Color_BACK);
            TestPetJpanel.tfLab.setBounds(303, 39, 71, 20);
            TestPetJpanel.tfLab.setIcon(TestPetJpanel.ICON_TIANFU);
            this.add(TestPetJpanel.tfLab);
        }
        else {
            (TestPetJpanel.tianfucount = new JLabel()).setForeground(Color.WHITE);
            TestPetJpanel.tianfucount.setBackground(UIUtils.Color_BACK);
            TestPetJpanel.tianfucount.setFont(UIUtils.TEXT_FONT2);
            TestPetJpanel.tianfucount.setBounds(315, 56, 40, 20);
            this.add(TestPetJpanel.tianfucount);
            (TestPetJpanel.tfLab = new JLabel()).setBackground(UIUtils.Color_BACK);
            TestPetJpanel.tfLab.setBounds(265, 57, 71, 20);
            TestPetJpanel.tfLab.setIcon(TestPetJpanel.ICON_TIANFU);
            this.add(TestPetJpanel.tfLab);
        }
        bigOsmall = true;
        ChangeJpanel();
    }

    public void ChangeJpanel(){
        if (bigOsmall ) {

            int zhong = 400;
            this.btnaction.setBounds(zhong, 193, 24, 25);
            this.btndir1.setBounds(zhong-47, 193-2, 28, 17);
            this.btndir2.setBounds(zhong+40, 193-2, 28, 17);

            this.btnaction.setVisible(true);
            this.btndir1.setVisible(true);
            this.btndir2.setVisible(true);


            int x1 = 195;
            int x0 = 206;
            if (MyIsif.getStyle().equals("水墨")) {
                btnBigOrSmall.setBounds(15, 512-32, 24, 34);
                this.add(this.getChangeImg());
                TestPetJpanel.testPetJpanel.setPreferredSize(new Dimension(588, 512));
                this.setLayout(null);
                offBtn.setBounds(588-35, 10, 25, 25);

//                TestPetJpanel.listpet.setSelectionBackground(new Color(20, 110, 210));
                this.jScrollPane.setBounds(47, 42, 193-4, 453);
                if (TestPetJpanel.labNedan != null&&TestPetJpanel.labNedan.length>0) {
                    for (int i = 0; i < TestPetJpanel.labNedan.length; i++) {
                        TestPetJpanel.labNedan[i].setBounds(262, 60 + i * 18, 72, 18);
                    }
                }
                for (int i = 0; i < this.dians.length; ++i) {
                    this.dians[i].setBounds(x1+349 + i % 2 * 14, 313 + i / 2 * 27, 12, 16);
                }

                TestPetJpanel.labname .setBounds(x0+81, 193+35, 99, 18);
                TestPetJpanel.lablevel .setBounds(x0+81, 221+37, 99, 15);
                TestPetJpanel.labloyalty .setBounds(x0+81, 247+39, 99, 15);

                TestPetJpanel.labclose .setBounds(481, 258, 99, 15);
                TestPetJpanel.labHP .setBounds(x0+81, 297+41-27, 135, 15);
                TestPetJpanel.labMP .setBounds(x0+81, 324+41-27, 135, 15);
                TestPetJpanel.labAP .setBounds(x0+81, 351+41-27, 135, 15);
                TestPetJpanel.labSP .setBounds(x0+81, 377+41-27, 135, 15);
                TestPetJpanel.labCP .setBounds(x0+81, 403+41-27, 135, 15);
                TestPetJpanel.labEXP.setBounds(x0+81-27, 422+49, 135, 15);

                TestPetJpanel.labrootbone .setBounds(x1+272, 297+15, 60, 15);

                TestPetJpanel.labintelligence .setBounds(x1+272, 322+17, 60, 15);
                TestPetJpanel.labpower .setBounds(x1+272, 346+19, 60, 15);

                TestPetJpanel.labspeed .setBounds(x1+272, 372+21, 60, 15);

                TestPetJpanel.labconcentrate .setBounds(x1+272, 398+23, 60, 15);

                TestPetJpanel.labpointnumber .setBounds(x1+325, 271+14, 40, 15);
                this.btnOrder.setBounds(238+30, 20, 18, 18);//排
                this.btnpetc .setBounds(260+30, 20, 18, 18);//存
                this.btnpetq .setBounds(282+30, 20, 18, 18);//取
                this.btnpetgk .setBounds(220+30, 20, 20, 20);//观
                this.btnpetyc .setBounds(245+30, 20, 20, 20);//隐
                this.btnpalPet .setBounds(216+30, 20, 18, 18);//伙
                this.btnpetnamech .setBounds(x0+193, 194+35, 18, 18);//改
                this.btndomesticated .setBounds(x0+177, 245+39, 34, 17);//驯养
                this.btnrelease.setBounds(x0+177, 219+38, 34, 17);//放生
                this.lingxi .setBounds(x1+238, 241-15, 59, 24);//灵犀
                this.btnwar.setBounds(x1+315, 241-15, 59, 24);//参战
                this.btnaddlittle .setBounds(195+260+310-117, 398+17+8, 51, 17);//确认加点
                this.btnpetarticles.setBounds(x1+59, 448+10+8, 59, 24);//物品
                this.btnsmeltmonster .setBounds(x1+137, 448+10+8, 59, 24);//炼妖
                this.btnRolequality .setBounds(x1+213, 448+10+8, 59, 24);//抗性
                this.btnpetkills.setBounds(x1+291, 448+10+8, 59, 24);//技能
                this.btnlock .setBounds(304+30, 20, 18, 18);//锁
                this.btnunlock .setBounds(326+30, 20, 18, 18);//解锁

                TestPetJpanel.tianfucount.setBounds(352+180, 40, 40, 20);
                TestPetJpanel.tfLab.setBounds(303+180, 41, 71, 20);

            }
            else {
                btnBigOrSmall.setBounds(15, 512-32, 24, 34);
                this.add(this.getChangeImg());
                TestPetJpanel.testPetJpanel.setPreferredSize(new Dimension(588, 512));
                this.setLayout(null);
                offBtn.setBounds(588-25, 0, 25, 25);

                TestPetJpanel.listpet.setSelectionBackground(new Color(20, 110, 210));
                this.jScrollPane.setBounds(47, 42, 193-4, 453);
                if (TestPetJpanel.labNedan != null&&TestPetJpanel.labNedan.length>0) {
                    for (int i = 0; i < TestPetJpanel.labNedan.length; i++) {
                        TestPetJpanel.labNedan[i].setBounds(262, 60 + i * 18, 72, 18);
                    }
                }
                for (int i = 0; i < this.dians.length; ++i) {
                    this.dians[i].setBounds(x1+349 + i % 2 * 14, 313 + i / 2 * 27, 12, 16);
                }
                Font font = new Font("微软雅黑", 1, 14);
                TestPetJpanel.labname .setBounds(x0+81, 193+35, 99, 18);
                TestPetJpanel.lablevel .setBounds(x0+81, 221+37, 99, 15);
                TestPetJpanel.labloyalty .setBounds(x0+81, 247+39, 99, 15);

                TestPetJpanel.labclose .setBounds(481, 258, 99, 15);
                TestPetJpanel.labHP .setBounds(x0+81, 297+41-27, 135, 15);
                TestPetJpanel.labMP .setBounds(x0+81, 322+43-27, 135, 15);
                TestPetJpanel.labAP .setBounds(x0+81, 346+45-27, 135, 15);
                TestPetJpanel.labSP .setBounds(x0+81, 372+47-27, 135, 15);
                TestPetJpanel.labCP .setBounds(x0+81, 403+41-27, 135, 15);
                TestPetJpanel.labEXP.setBounds(x0+81-27, 422+49, 135, 15);

                TestPetJpanel.labrootbone .setBounds(x1+272, 297+15, 60, 15);

                TestPetJpanel.labintelligence .setBounds(x1+272, 322+17, 60, 15);
                TestPetJpanel.labpower .setBounds(x1+272, 346+19, 60, 15);

                TestPetJpanel.labspeed .setBounds(x1+272, 372+21, 60, 15);

                TestPetJpanel.labconcentrate .setBounds(x1+272, 398+23, 60, 15);

                TestPetJpanel.labpointnumber .setBounds(x1+325, 271+14, 40, 15);
                this.btnOrder.setBounds(238+30, 20, 18, 18);//排
                this.btnpetc .setBounds(260+30, 20, 18, 18);//存
                this.btnpetq .setBounds(282+30, 20, 18, 18);//取
                this.btnpetgk .setBounds(220+30, 20, 20, 20);//观
                this.btnpetyc .setBounds(245+30, 20, 20, 20);//隐
                this.btnpalPet .setBounds(216+30, 20, 18, 18);//伙
                this.btnpetnamech .setBounds(x0+193, 194+35, 18, 18);//改
                this.btndomesticated .setBounds(x0+177, 245+39, 34, 17);//驯养
                this.btnrelease.setBounds(x0+177, 219+38, 34, 17);//放生
                this.lingxi .setBounds(x1+238, 241-15, 59, 24);//灵犀
                this.btnwar.setBounds(x1+315, 241-15, 59, 24);//参战
                this.btnaddlittle .setBounds(195+260+310-117, 398+17, 68, 17);//确认加点
                this.btnpetarticles.setBounds(x1+59, 448+10+8, 59, 24);//物品
                this.btnsmeltmonster .setBounds(x1+137, 448+10+8, 59, 24);//炼妖
                this.btnRolequality .setBounds(x1+213, 448+10+8, 59, 24);//抗性
                this.btnpetkills.setBounds(x1+291, 448+10+8, 59, 24);//技能
                this.btnlock .setBounds(304+30, 20, 18, 18);//锁
                this.btnunlock .setBounds(326+30, 20, 18, 18);//解锁

                TestPetJpanel.tianfucount.setBounds(352+180, 40, 40, 20);
                TestPetJpanel.tfLab.setBounds(303+180, 41, 71, 20);
            }
            btnTouOrName.setBounds(60, 20, 20, 20);
        }else {
            this.btnaction.setVisible(false);
            this.btndir1.setVisible(false);
            this.btndir2.setVisible(false);

            if (MyIsif.getStyle().equals("水墨")) {
                btnBigOrSmall.setBounds(15, 490-32, 24, 34);
                this.add(this.getChangeImg());
                (TestPetJpanel.testPetJpanel = this).setPreferredSize(new Dimension(393, 490));
                this.setLayout(null);
                offBtn .setBounds(356, 10, 25, 25);

                this.jScrollPane.setBounds(50, 40, 166, 136);

                if (TestPetJpanel.labNedan != null &&TestPetJpanel.labNedan.length>0) {
                    for (int i = 0; i < TestPetJpanel.labNedan.length; ++i) {
                        TestPetJpanel.labNedan[i] .setBounds(216, 40 + i * 18, 18, 18);
                    }
                }

                for (int i = 0; i < this.dians.length; ++i) {
                    this.dians[i].setBounds(349 + i % 2 * 14, 297 + i / 2 * 25, 12, 16);
                }
                TestPetJpanel.labname .setBounds(81, 193, 99, 18);

                TestPetJpanel.lablevel .setBounds(81, 221, 99, 15);

                TestPetJpanel.labloyalty .setBounds(81, 247, 99, 15);
                TestPetJpanel.labclose .setBounds(81, 271, 99, 15);
                TestPetJpanel.labHP .setBounds(81, 297, 135, 15);
                TestPetJpanel.labMP .setBounds(81, 322, 135, 15);
                TestPetJpanel.labAP .setBounds(81, 346, 135, 15);
                TestPetJpanel.labSP .setBounds(81, 372, 135, 15);
                TestPetJpanel.labCP.setBounds(81, 399, 135, 15);
                TestPetJpanel.labEXP .setBounds(81, 422, 135, 15);
                TestPetJpanel.labrootbone .setBounds(272, 297, 60, 15);
                TestPetJpanel.labintelligence .setBounds(272, 322, 60, 15);
                TestPetJpanel.labpower .setBounds(272, 346, 60, 15);
                TestPetJpanel.labspeed .setBounds(272, 372, 60, 15);
                TestPetJpanel.labconcentrate .setBounds(272, 398, 60, 15);
                TestPetJpanel.labpointnumber .setBounds(325, 271, 40, 15);
                this.btnOrder .setBounds(238, 20, 18, 18);
                this.btnpetc .setBounds(260, 20, 18, 18);
                this.btnpetq .setBounds(282, 20, 18, 18);
                this.btnpetgk .setBounds(220, 20, 20, 20);
                this.btnpetyc .setBounds(245, 20, 20, 20);
                this.btnpalPet .setBounds(216, 20, 18, 18);
                this.btnpetnamech.setBounds(193, 194, 18, 18);
                this.btndomesticated .setBounds(177, 245, 34, 17);
                this.btnrelease .setBounds(177, 219, 34, 17);
                this.lingxi .setBounds(238, 241, 59, 24);
                this.btnwar .setBounds(315, 241, 59, 24);
                this.btnaddlittle .setBounds(260, 398, 51, 17);
                this.btnpetarticles .setBounds(59, 448, 59, 24);
                this.btnsmeltmonster .setBounds(137, 448, 59, 24);
                this.btnRolequality .setBounds(213, 448, 59, 24);
                this.btnpetkills .setBounds(291, 448, 59, 24);
                this.btnlock .setBounds(304, 20, 18, 18);
                this.btnunlock .setBounds(326, 20, 18, 18);

                TestPetJpanel.tianfucount.setBounds(352, 38, 40, 20);
                TestPetJpanel.tfLab.setBounds(303, 39, 71, 20);
                btnTouOrName.setBounds(57, 20, 20, 20);
            } else {
                btnBigOrSmall.setBounds(2, 517-34, 24, 34);
                this.add(this.getChangeImg());
                (TestPetJpanel.testPetJpanel = this).setPreferredSize(new Dimension(356, 517));
                this.setLayout(null);
                offBtn.setBounds(333, 2, 23, 23);

                this.jScrollPane.setBounds(25, 55, 145, 132);

                if (TestPetJpanel.labNedan != null && TestPetJpanel.labNedan.length>0) {
                    for (int i = 0; i < TestPetJpanel.labNedan.length; ++i) {
                        TestPetJpanel.labNedan[i] .setBounds(178, 62 + i * 18, 15, 15);
                    }
                }

                for (int i = 0; i < this.dians.length; ++i) {
                    this.dians[i].setBounds(305 + i % 2 * 13, 313 + i / 2 * 25, 13, 13);
                }

                TestPetJpanel.labname .setBounds(59, 208, 99, 18);
                TestPetJpanel.lablevel .setBounds(59, 234, 99, 15);

                TestPetJpanel.labloyalty .setBounds(59, 260, 99, 15);
                TestPetJpanel.labclose .setBounds(59, 286, 99, 15);
                TestPetJpanel.labHP .setBounds(59, 312, 120, 15);
                TestPetJpanel.labMP .setBounds(59, 337, 120, 15);
                TestPetJpanel.labAP .setBounds(59, 362, 120, 15);
                TestPetJpanel.labSP .setBounds(59, 387, 120, 15);
                TestPetJpanel.labCP  .setBounds(59, 412, 120, 15);
                TestPetJpanel.labEXP .setBounds(59, 437, 120, 15);
                TestPetJpanel.labrootbone .setBounds(238, 312, 60, 15);
                TestPetJpanel.labintelligence .setBounds(238, 337, 60, 15);
                TestPetJpanel.labpower .setBounds(238, 362, 60, 15);
                TestPetJpanel.labspeed .setBounds(238, 387, 60, 15);
                TestPetJpanel.labconcentrate .setBounds(238, 412, 60, 15);
                TestPetJpanel.labpointnumber .setBounds(290, 286, 40, 15);
                this.btnpetnamech .setBounds(158, 209, 17, 17);
                this.btndomesticated .setBounds(144, 260, 34, 17);
                this.btnrelease .setBounds(144, 234, 34, 17);
                this.lingxi .setBounds(220, 251, 50, 30);
                this.btnwar .setBounds(280, 251, 50, 30);
                this.btnaddlittle .setBounds(250, 437, 68, 17);
                this.btnpetarticles .setBounds(26, 465, 60, 26);
                this.btnsmeltmonster .setBounds(106, 465, 60, 26);
                this.btnRolequality .setBounds(189, 465, 60, 26);
                this.btnpetkills.setBounds(270, 465, 60, 26);
                this.btnlock .setBounds(272, 33, 19, 19);
                this.btnunlock .setBounds(294, 33, 19, 19);
                this.btnOrder .setBounds(198, 33, 19, 19);
                this.btnpetc .setBounds(220, 33, 19, 19);
                this.btnpetq .setBounds(242, 33, 19, 19);
                this.btnpetgk .setBounds(200, 37, 19, 19);
                this.btnpetyc .setBounds(220, 37, 19, 19);
                this.btnpalPet .setBounds(176, 33, 19, 19);
                TestPetJpanel.tianfucount.setBounds(315, 56, 40, 20);

                TestPetJpanel.tfLab.setBounds(265, 57, 71, 20);
                btnTouOrName.setBounds(23, 33, 20, 20);
            }
        }


        if (UserMessUntil.getChosePetMes()!=null) {

            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = (Configure)item.get(new BigDecimal(1));
            this.level = AnalysisString.petTurnRount(UserMessUntil.getChosePetMes().getGrade());
            if (this.level <= 3) {
                this.dians[8].setVisible(false);
                this.dians[9].setVisible(false);
                TestPetJpanel.labconcentrate.setVisible(false);
                TestPetJpanel.labCP.setVisible(false);
                if (MyIsif.getStyle().equals("水墨")) {
                    if (bigOsmall) {
                        TestPetJpanel.labEXP.setBounds(81+206, 399+21, 135, 15);
                        this.btnaddlittle.setBounds(310+200, 398+18, 51, 17);
                    }else {
                        TestPetJpanel.labEXP.setBounds(81, 399, 135, 15);
                        this.btnaddlittle.setBounds(310, 398, 68, 17);

                    }
                }
                else {
                    if (bigOsmall) {
                        TestPetJpanel.labEXP.setBounds(81+206, 399+21, 135, 15);
                        this.btnaddlittle.setBounds(310+200, 398+18, 51, 17);
                    }else {
                        TestPetJpanel.labEXP.setBounds(59, 412, 135, 15);
                        this.btnaddlittle.setBounds(250, 412, 68, 17);
                    }
                }
                if (configure.getLingxi().equals("开")) {
                    this.lingxi.setVisible(false);
                }
            }
            else {
                this.dians[8].setVisible(true);
                this.dians[9].setVisible(true);
                TestPetJpanel.labconcentrate.setVisible(true);
                TestPetJpanel.labCP.setVisible(true);

                if (configure.getLingxi().equals("开")) {
                    this.lingxi.setVisible(true);
                    this.add(this.lingxi);
                }
                if (MyIsif.getStyle().equals("水墨")) {
                    if (bigOsmall) {
                        TestPetJpanel.labEXP.setBounds(81+206, 399+38+8, 135, 15);
                        this.btnaddlittle.setBounds(310+200, 398+38+8, 51, 17);

                    }else {
                        TestPetJpanel.labEXP.setBounds(81, 425, 135, 15);
                        this.btnaddlittle.setBounds(310, 425, 51, 17);

                    }
                }
                else {
                    if (bigOsmall) {
                        TestPetJpanel.labEXP.setBounds(81+206, 399+38+8, 135, 15);
                        this.btnaddlittle.setBounds(310+200, 398+38+8, 68, 17);

                    }else {
                        TestPetJpanel.labEXP.setBounds(59, 437, 135, 15);
                        this.btnaddlittle.setBounds(250, 437, 68, 17);
                    }
                }
            }
        }

        if (TouOrName) {
//            TouOrName = false;
            this.getListpet().setFixedCellHeight(38);
            if (MyIsif.getStyle().equals("水墨")) {
                this.getBtnTouOrName().setIcons(CutButtonImage.cuts("inkImg/button/86.png"));
            } else {
                this.getBtnTouOrName().setIcons(CutButtonImage.cuts("inkImg/hongmu/HM86.png"));
            }
        } else  {
//            TouOrName = true ;
            this.getListpet().setFixedCellHeight(22);
            if (MyIsif.getStyle().equals("水墨")) {
                this.getBtnTouOrName().setIcons(CutButtonImage.cuts("inkImg/button/87.png"));
            } else {
                this.getBtnTouOrName().setIcons(CutButtonImage.cuts("inkImg/hongmu/HM87.png"));
            }
        }
        this.repaint();
    }

    public int getdian(int type) {
        try {
            if (type == 0) {
                type = Integer.parseInt(TestPetJpanel.labrootbone.getText());
            }
            else if (type == 1) {
                type = Integer.parseInt(TestPetJpanel.labintelligence.getText());
            }
            else if (type == 2) {
                type = Integer.parseInt(TestPetJpanel.labpower.getText());
            }
            else if (type == 3) {
                type = Integer.parseInt(TestPetJpanel.labspeed.getText());
            }
            else if (type == 4) {
                type = Integer.parseInt(TestPetJpanel.labconcentrate.getText());
            }
            else if (type == 5) {
                type = Integer.parseInt(TestPetJpanel.labpointnumber.getText());
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
                type = Integer.parseInt(TestPetJpanel.labrootbone.getText());
                TestPetJpanel.labrootbone.setText(type + point + "");
            }
            else if (type == 1) {
                type = Integer.parseInt(TestPetJpanel.labintelligence.getText());
                TestPetJpanel.labintelligence.setText(type + point + "");
            }
            else if (type == 2) {
                type = Integer.parseInt(TestPetJpanel.labpower.getText());
                TestPetJpanel.labpower.setText(type + point + "");
            }
            else if (type == 3) {
                type = Integer.parseInt(TestPetJpanel.labspeed.getText());
                TestPetJpanel.labspeed.setText(type + point + "");
            }
            else if (type == 4) {
                type = Integer.parseInt(TestPetJpanel.labconcentrate.getText());
                TestPetJpanel.labconcentrate.setText(type + point + "");
            }
            type = Integer.parseInt(TestPetJpanel.labpointnumber.getText());
            TestPetJpanel.labpointnumber.setText(type - point + "");
        }
        catch (Exception ex) {}
    }
    
    private static void petOrder() {
        List<RoleSummoning> roleSummonings = UserMessUntil.getPetListTable();
        RoleData.getRoleData().addOrderPet(roleSummonings);
        RoleData roleData = RoleData.getRoleData();
        List<BigDecimal> petOrder = roleData.getPetOrder();
        List<RoleSummoning> newRoleSummonings = new ArrayList<>();
        for (int i = 0; i < petOrder.size(); ++i) {
            RoleSummoning pet = UserMessUntil.getPetRgid((BigDecimal)petOrder.get(i));
            if (pet != null) {
                newRoleSummonings.add(pet);
            }
        }
        UserMessUntil.setPetListTable(newRoleSummonings);
        if (PetPrderJframe.getPetPrderJframe().isVisible()) {
            PetPrderJframe.getPetPrderJframe().getPetPrderJpanel().init(roleData.getOrderPetName());
        }
    }
    
    public static void showStar() {
        petOrder();
        if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null) {
            TestPetJpanel.listModel.removeAllElements();
            for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
                TestPetJpanel.listModel.add(i, ((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSummoningname());
                if (RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSid()) == 0) {
                    TestPetJpanel.listModel.set(i, "( * )" + ((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSummoningname());
                    setWarNum(i);
                }
                if (((RoleSummoning)UserMessUntil.getPetListTable().get(i)).isShow()) {
                    TestPetJpanel.listModel.set(i, "(观)" + ((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSummoningname());
                }
                else if (!((RoleSummoning)UserMessUntil.getPetListTable().get(i)).isShow() && RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSid()) != 0) {
                    TestPetJpanel.listModel.set(i, ((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSummoningname());
                }
            }
        }
        else {
            TestPetJpanel.listModel.removeAllElements();
            for (int i = 0; i < UserMessUntil.getPetListTable().size(); ++i) {
                TestPetJpanel.listModel.add(i, ((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSummoningname());
            }
        }
    }
    
    public static void showListModel(List<RoleSummoning> pets, BigDecimal petid) {
        petOrder();
        TestPetJpanel.listModel.removeAllElements();
        TestPetJpanel.warNum = -1;
        for (int i = 0, length = pets.size(); i < length; ++i) {
            RoleSummoning pet = (RoleSummoning)pets.get(i);
            if (petid != null && petid.compareTo(pet.getSid()) == 0) {
                TestPetJpanel.warNum = i;
                TestPetJpanel.listModel.addElement("( * )" + pet.getSummoningname());
            }
            else {
                TestPetJpanel.listModel.addElement(pet.getSummoningname());
            }
            if (((RoleSummoning)UserMessUntil.getPetListTable().get(i)).isShow()) {
                TestPetJpanel.listModel.set(i, "(观)" + ((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSummoningname());
            }
            else if (!((RoleSummoning)UserMessUntil.getPetListTable().get(i)).isShow() && ((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSid() != null && RoleData.getRoleData().getLoginResult().getSummoning_id() != null && RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSid()) != 0) {
                TestPetJpanel.listModel.set(i, ((RoleSummoning)UserMessUntil.getPetListTable().get(i)).getSummoningname());
            }
        }
    }
    
    public void changeSoaringPanel(int lvltrue) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        this.level = lvltrue;
        if (lvltrue <= 3) {
            this.dians[8].setVisible(false);
            this.dians[9].setVisible(false);
            TestPetJpanel.labconcentrate.setVisible(false);
            TestPetJpanel.labCP.setVisible(false);
            if (MyIsif.getStyle().equals("水墨")) {
                if (bigOsmall) {
                    //195+260+310-117, 398+17
                    TestPetJpanel.labEXP.setBounds(81+206, 399+21, 135, 15);
                    this.btnaddlittle.setBounds(310+200, 398+18, 51, 17);
                    this.icon = CutButtonImage.getImage("inkImg/cloud1/SFSMZHS.png", -1, -1);
                }else {
                    TestPetJpanel.labEXP.setBounds(81, 399, 135, 15);
                    this.btnaddlittle.setBounds(310, 398, 51, 17);
                    this.icon = CutButtonImage.getImage("inkImg/background1/B100.png", -1, -1);
                }
            }
            else {
                if (bigOsmall) {
                    //195+260+310-117, 398+17
                    TestPetJpanel.labEXP.setBounds(81+206, 399+21, 135, 15);
                    this.btnaddlittle.setBounds(310+200, 398+18, 68, 17);
                    this.icon = CutButtonImage.getImage("inkImg/cloud1/SFSMZHS红木.png", -1, -1);
                }else {
                    TestPetJpanel.labEXP.setBounds(59, 412, 135, 15);
                    this.btnaddlittle.setBounds(250, 412, 68, 17);
                    this.icon = CutButtonImage.getImage("img/soaring/召唤兽(无飞升)w356,h517px.png", -1, -1);
                }
            }
            if (configure.getLingxi().equals("开")) {
                this.lingxi.setVisible(false);
            }
        }
        else {
            this.dians[8].setVisible(true);
            this.dians[9].setVisible(true);
            TestPetJpanel.labconcentrate.setVisible(true);
            TestPetJpanel.labCP.setVisible(true);
            if (configure.getLingxi().equals("开")) {
                this.lingxi.setVisible(true);
                this.add(this.lingxi);
            }
            if (MyIsif.getStyle().equals("水墨")) {
                if (bigOsmall) {
                    //195+260+310-117, 398+17
                    TestPetJpanel.labEXP.setBounds(81+206, 399+38+8, 135, 15);
                    this.btnaddlittle.setBounds(310+200, 398+38+8, 51, 17);
                    this.icon = CutButtonImage.getImage("inkImg/cloud1/SFSMZHS1.png", -1, -1);
                }else {
                    TestPetJpanel.labEXP.setBounds(81, 425, 135, 15);
                    this.btnaddlittle.setBounds(310, 425, 51, 17);
                    this.icon = CutButtonImage.getImage("inkImg/background1/B105.png", -1, -1);
                }
            }
            else {
                if (bigOsmall) {
                    //195+260+310-117, 398+17
                    TestPetJpanel.labEXP.setBounds(81+206, 399+38+8, 135, 15);
                    this.btnaddlittle.setBounds(310+200, 398+38+8, 68, 17);
                    this.icon = CutButtonImage.getImage("inkImg/cloud1/SFSMZHS红木1.png", -1, -1);
                }else {
                    TestPetJpanel.labEXP.setBounds(59, 437, 135, 15);
                    this.btnaddlittle.setBounds(250, 437, 68, 17);
                    this.icon = CutButtonImage.getImage("img/soaring/召唤兽w356,h517px.png", -1, -1);
                }
            }
        }
    }
    private long time;
    private ImageIcon iconditu = CutButtonImage.getImage("inkImg/button/B161-11.png", -1, -1);;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        int sun = UserMessUntil.getPetListTable().size();
        int num = Integer.parseInt(configure.getXdzhssx());

        Graphics2D graphics2d = (Graphics2D)g;
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.level <= 3) {
                if (bigOsmall) {
                    this.icon = new ImageIcon("skin/cloud1/SFSMZHS.png");
                }else {
                    this.icon = new ImageIcon("inkImg/background1/B100.png");
                }
            }
            else {
                if (bigOsmall) {
                    this.icon = new ImageIcon("skin/cloud1/SFSMZHS1.png");//点化图
                }else {
                    this.icon = new ImageIcon("inkImg/background1/B105.png");
                }
            }
            if (bigOsmall) {
                TestPetJpanel.petsum.setText("(" + sun + "/" + num + ")");
                TestPetJpanel.petsum.setBounds(75+58, 23, 135, 15);
            }else {
                TestPetJpanel.petsum.setText("召唤兽(" + sun + "/" + num + ")");
                TestPetJpanel.petsum.setBounds(75, 23, 135, 15);
            }

        }
        else {
            if (this.level <= 3) {
                if (bigOsmall) {
                    this.icon = new ImageIcon("skin/cloud1/SFSMZHS红木.png");
                }else {
                    this.icon = new ImageIcon("img/soaring/召唤兽(无飞升)w356,h517px.png");
                }
            }
            else {
                if (bigOsmall) {
                    this.icon = new ImageIcon("skin/cloud1/SFSMZHS红木1.png");
                }else {
                    this.icon = new ImageIcon("img/soaring/召唤兽w356,h517px.png");
                }
            }
            if (bigOsmall) {
                TestPetJpanel.petsum.setText("(" + sun + "/" + num + ")");
                TestPetJpanel.petsum.setBounds(75+58, 23, 135, 15);
            }else {
                TestPetJpanel.petsum.setText("召唤兽(" + sun + "/" + num + ")");
                TestPetJpanel.petsum.setBounds(50, 37, 135, 15);
            }

        }
        if (MyIsif.getStyle().equals("水墨")) {
            if (bigOsmall) {
                graphics2d.drawImage(this.icon.getImage(), 0, 0, 588, 512, this);
            }else {
                graphics2d.drawImage(this.icon.getImage(), 0, 0, 393, 490, this);
            }

        }
        else {
            if (bigOsmall) {
                graphics2d.drawImage(this.icon.getImage(), 0, 0, 588, 512, this);
            }else {
                graphics2d.drawImage(this.icon.getImage(), 0, 0, 356, 517, this);
            }
        }
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        if (pet != null) {
            if (TestPetJpanel.part == null||change) {
                if ( change ) {
                    change=false;
                }
                TestPetJpanel.part = pet.getPart(action);
            }
            if (TestPetJpanel.part != null) {
                if (MyIsif.getStyle().equals("水墨")) {
                    if (bigOsmall) {
                        TestPetJpanel.part.draw(g, 110+305, 180, dir, ImageMixDeal.userimg.getTime());
                    }else {
                        TestPetJpanel.part.draw(g, 305, 205, dir, ImageMixDeal.userimg.getTime());
                    }
                }
                else {
                    if (bigOsmall) {
                        TestPetJpanel.part.draw(g, 110+305, 180, dir, ImageMixDeal.userimg.getTime());
                    }else {
                        TestPetJpanel.part.draw(g, 260, 205, dir, ImageMixDeal.userimg.getTime());
                    }
                }
            }
            if (pet.getPetlock() == 1) {
                GoodsListFromServerUntil.drawPetLock(graphics2d, pet);
            }
        }
        if (TestPetJpanel.listModel.size() <= 0) {
            for (int i = 0; i < Integer.parseInt(configure.getNdslsx()); ++i) {
                TestPetJpanel.labNedan[i].setIcon(null);
            }
        }

        if (bigOsmall) {
            if (TestPetJpanel.labNedan != null) {
                for (int i = 0; i < TestPetJpanel.labNedan.length; i++) {
                    if (TestPetJpanel.labNedan[i] != null && TestPetJpanel.labNedan[i].getGoodstable() != null) {
                        graphics2d.drawImage(this.iconditu.getImage(), 262, 4 + (i + 1) * 18 + 37, 60, 23, this);

                        String name = TestPetJpanel.labNedan[i].getGoodstable().getGoodsname();
                        name = name.substring(0, 2);
                        graphics2d.setColor(Color.white);
                        graphics2d.setFont(UIUtils.TEXT_FONT60);
                        graphics2d.drawString(name, 262 + 20, 4 + (i + 1) * 18 + 52);
                        //262, 60 + i * 18, 72, 18);
                    }
                }
            }
        }
        graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
    public void initS(List<String> list) {
        TestPetJpanel.listModel.clear();
        for (int i = 0; i < list.size(); ++i) {
            TestPetJpanel.listModel.addElement(list.get(i));
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
        FormsManagement.HideForm(6);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public static JList<String> getListpet() {
        return TestPetJpanel.listpet;
    }
    
    public static void setListpet(JList<String> listpet) {
        TestPetJpanel.listpet = listpet;
    }
    
    public Color getFontcolor() {
        return this.fontcolor;
    }
    
    public void setFontcolor(Color fontcolor) {
        this.fontcolor = fontcolor;
    }
    
    public JScrollPane getjScrollPane() {
        return this.jScrollPane;
    }
    
    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
    
    public static JTextField getLabname() {
        return TestPetJpanel.labname;
    }
    
    public static void setLabname(JTextField labname) {
        TestPetJpanel.labname = labname;
    }
    
    public static JLabel getLablevel() {
        return TestPetJpanel.lablevel;
    }
    
    public static void setLablevel(JLabel lablevel) {
        TestPetJpanel.lablevel = lablevel;
    }
    
    public static JLabel getLabloyalty() {
        return TestPetJpanel.labloyalty;
    }
    
    public static void setLabloyalty(JLabel labloyalty) {
        TestPetJpanel.labloyalty = labloyalty;
    }
    
    public static JLabel getLabHP() {
        return TestPetJpanel.labHP;
    }
    
    public static void setLabHP(JLabel labHP) {
        TestPetJpanel.labHP = labHP;
    }
    
    public static JLabel getLabMP() {
        return TestPetJpanel.labMP;
    }
    
    public static void setLabMP(JLabel labMP) {
        TestPetJpanel.labMP = labMP;
    }
    
    public static JLabel getLabAP() {
        return TestPetJpanel.labAP;
    }
    
    public static void setLabAP(JLabel labAP) {
        TestPetJpanel.labAP = labAP;
    }
    
    public static JLabel getLabSP() {
        return TestPetJpanel.labSP;
    }
    
    public static void setLabSP(JLabel labSP) {
        TestPetJpanel.labSP = labSP;
    }
    
    public static JLabel getLabEXP() {
        return TestPetJpanel.labEXP;
    }
    
    public static void setLabEXP(JLabel labEXP) {
        TestPetJpanel.labEXP = labEXP;
    }
    
    public static JLabel getLabrootbone() {
        return TestPetJpanel.labrootbone;
    }
    
    public static void setLabrootbone(JLabel labrootbone) {
        TestPetJpanel.labrootbone = labrootbone;
    }
    
    public static JLabel getLabintelligence() {
        return TestPetJpanel.labintelligence;
    }
    
    public static void setLabintelligence(JLabel labintelligence) {
        TestPetJpanel.labintelligence = labintelligence;
    }
    
    public static JLabel getLabpower() {
        return TestPetJpanel.labpower;
    }
    
    public static void setLabpower(JLabel labpower) {
        TestPetJpanel.labpower = labpower;
    }
    
    public static JLabel getLabspeed() {
        return TestPetJpanel.labspeed;
    }
    
    public static void setLabspeed(JLabel labspeed) {
        TestPetJpanel.labspeed = labspeed;
    }
    
    public static JLabel getLabpointnumber() {
        return TestPetJpanel.labpointnumber;
    }
    
    public static void setLabpointnumber(JLabel labpointnumber) {
        TestPetJpanel.labpointnumber = labpointnumber;
    }
    
    public PetPanelBtn getBtnpetnamech() {
        return this.btnpetnamech;
    }
    
    public void setBtnpetnamech(PetPanelBtn btnpetnamech) {
        this.btnpetnamech = btnpetnamech;
    }
    
    public PetPanelBtn getBtnaddlittle() {
        return this.btnaddlittle;
    }
    
    public void setBtnaddlittle(PetPanelBtn btnaddlittle) {
        this.btnaddlittle = btnaddlittle;
    }
    
    public PetPanelBtn getBtndomesticated() {
        return this.btndomesticated;
    }
    
    public void setBtndomesticated(PetPanelBtn btndomesticated) {
        this.btndomesticated = btndomesticated;
    }
    
    public PetPanelBtn getBtnpetkills() {
        return this.btnpetkills;
    }
    
    public void setBtnpetkills(PetPanelBtn btnpetkills) {
        this.btnpetkills = btnpetkills;
    }
    
    public PetPanelBtn getBtnpetarticles() {
        return this.btnpetarticles;
    }
    
    public void setBtnpetarticles(PetPanelBtn btnpetarticles) {
        this.btnpetarticles = btnpetarticles;
    }
    
    public PetPanelBtn getBtnsmeltmonster() {
        return this.btnsmeltmonster;
    }
    
    public void setBtnsmeltmonster(PetPanelBtn btnsmeltmonster) {
        this.btnsmeltmonster = btnsmeltmonster;
    }
    
    public PetPanelBtn getBtnrelease() {
        return this.btnrelease;
    }
    
    public void setBtnrelease(PetPanelBtn btnrelease) {
        this.btnrelease = btnrelease;
    }
    
    public PetPanelBtn getBtnsummon() {
        return this.btnsummon;
    }
    
    public void setBtnsummon(PetPanelBtn btnsummon) {
        this.btnsummon = btnsummon;
    }
    
    public static NeidanBtn[] getLabNedan() {
        return TestPetJpanel.labNedan;
    }
    
    public static void setLabNedan(NeidanBtn[] labNedan) {
        TestPetJpanel.labNedan = labNedan;
    }
    
    public static JLabel getLabclose() {
        return TestPetJpanel.labclose;
    }
    
    public static void setLabclose(JLabel labclose) {
        TestPetJpanel.labclose = labclose;
    }
    
    public static JLabel getLabCP() {
        return TestPetJpanel.labCP;
    }
    
    public static void setLabCP(JLabel labCP) {
        TestPetJpanel.labCP = labCP;
    }
    
    public static JLabel getLabconcentrate() {
        return TestPetJpanel.labconcentrate;
    }
    
    public static void setLabconcentrate(JLabel labconcentrate) {
        TestPetJpanel.labconcentrate = labconcentrate;
    }
    
    public PetPanelBtn getBtnwar() {
        return this.btnwar;
    }
    
    public void setBtnwar(PetPanelBtn btnwar) {
        this.btnwar = btnwar;
    }
    
    public PetPanelBtn getLingxi() {
        return this.lingxi;
    }
    
    public void setLingxi(PetPanelBtn lingxi) {
        this.lingxi = lingxi;
    }
    
    public PetPanelBtn getBtnRolequality() {
        return this.btnRolequality;
    }
    
    public void setBtnRolequality(PetPanelBtn btnRolequality) {
        this.btnRolequality = btnRolequality;
    }
    
    public GoodAndPetLockBtn getBtnlock() {
        return this.btnlock;
    }
    
    public void setBtnlock(GoodAndPetLockBtn btnlock) {
        this.btnlock = btnlock;
    }
    
    public GoodAndPetLockBtn getBtnunlock() {
        return this.btnunlock;
    }
    
    public void setBtnunlock(GoodAndPetLockBtn btnunlock) {
        this.btnunlock = btnunlock;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public static NewPart getPart() {
        return TestPetJpanel.part;
    }
    
    public static void setPart(NewPart part) {
        TestPetJpanel.part = part;
    }
    
    public static TestPetJpanel getTestPetJpanel() {
        return TestPetJpanel.testPetJpanel;
    }
    
    public static void setTestPetJpanel(TestPetJpanel testPetJpanel) {
        TestPetJpanel.testPetJpanel = testPetJpanel;
    }
    
    public static int getWarNum() {
        return TestPetJpanel.warNum;
    }
    
    public static void setWarNum(int warNum) {
        TestPetJpanel.warNum = warNum;
    }
    
    public JLabel getChangeImg() {
        if (TestPetJpanel.changeImg == null) {
            (TestPetJpanel.changeImg = new JLabel()).setOpaque(false);
            TestPetJpanel.changeImg.setBounds(194, 66, 158, 218);
        }
        return TestPetJpanel.changeImg;
    }
    
    public void setChangeImg(JLabel changeImg) {
        TestPetJpanel.changeImg = changeImg;
    }
    
    public static JLabel getTfLab() {
        return TestPetJpanel.tfLab;
    }
    
    public static JLabel getTianfucount() {
        return TestPetJpanel.tianfucount;
    }
    
    static {
        ICON_TIANFU = new ImageIcon("inkImg/hongmu/S7072.png");
        TestPetJpanel.labNedan = new NeidanBtn[100];
        TestPetJpanel.warNum = -1;
    }
}
