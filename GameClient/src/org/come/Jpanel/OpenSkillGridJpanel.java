package org.come.Jpanel;

import com.tool.tcp.SpriteFactory;
import java.util.List;
import org.come.Frame.OpenSkillGridJframe;
import org.come.until.GoodsListFromServerUntil;
import org.come.mouslisten.WLLMouslisten;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.BigDecimal;
import come.tool.FightingData.FBUtil;
import org.come.until.Arith;
import org.come.until.CustomFunction;
import org.come.Frame.PetSkillsJframe;
import org.come.until.Music;
import org.come.bean.Skill;
import java.util.Arrays;
import org.come.until.ScrollUI;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import java.awt.event.MouseMotionListener;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.until.UserMessUntil;
import org.come.entity.RoleSummoning;
import java.awt.Color;

import org.come.until.FormsManagement;
import org.come.Frame.ZhuFrame;
import org.come.until.MessagrFlagUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.come.until.CutButtonImage;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import com.tool.tcpimg.UIUtils;
import com.tool.image.ImageMixDeal;
import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import javax.swing.ImageIcon;
import com.tool.tcp.Sprite;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import com.tool.btn.DeleteSkillBtn;
import org.come.mouslisten.ShowPetSkillsJpMouslisten;
import com.tool.tcpimg.RichLabel;
import javax.swing.JLabel;
import org.come.entity.Goodstable;
import javax.swing.JPanel;

public class OpenSkillGridJpanel extends JPanel
{
    private Goodstable goodstable;
    private JLabel goodBox;
    public int skllNum;
    private RichLabel box;
    private WuLingPanel wuLing;
    private JLabel[] labPetskills;
    private JLabel[] qlPetskills;
    private JLabel[] naturalSkills;
    private static JLabel effect;
    private ShowPetSkillsJpMouslisten[] showPetSkills;
    private ShowPetSkillsJpMouslisten[] showQlPetSkills;
    private DeleteSkillBtn supportListBtn;
    private DeleteSkillBtn deleteSkillBtn;
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
    private DeleteSkillBtn xinyuan;
    private DeleteSkillBtn deleteQlSkillBtn;
    private JList<String> listNaturalskill;
    private JList<String> listpetskill;
    private DefaultListModel<String> modelNaturalskill;
    private DefaultListModel<String> modelpetskill;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private final boolean shoptext = false;
    private String petskillID;
    private int petskillNO;
    public static Sprite tcp;
    private static final ImageIcon yiru;
    private int goodPosition;
    private static DefaultListModel<String> listModel;
    private JList<String> listpet;
    private JScrollPane jScrollPane;
    private static DefaultListModel<Goodstable> listGooosModel;
    private static JList<Goodstable> listGoods;
    private JScrollPane GooodsJScrollPane;
    private FormsOnOffBtn offBtn;
    private int x;
    private int y;
    private JLabel effect2;
    public static int idx;
    private ImageIcon icon;
    private ImageIcon icon2;
    private ImageIcon icon3;
    private ImageIcon icon4;
    private ImageIcon icon6;

    public OpenSkillGridJpanel() throws Exception {
        this.labPetskills = new JLabel[9];
        this.qlPetskills = new JLabel[6];
        this.showPetSkills = new ShowPetSkillsJpMouslisten[9];
        this.showQlPetSkills = new ShowPetSkillsJpMouslisten[6];
        this.petskillID = "";
        this.x = 5;
        this.y = 5;
        this.effect2 = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(OpenSkillGridJpanel.yiru.getImage(), 0, 0, 51, 51, this);
                OpenSkillGridJpanel.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
                OpenSkillGridJpanel.tcp.draw(g, 0, 0);
            }
        };
        this.icon = new ImageIcon("img/toutusm/smjpd.png");
        this.icon2 = new ImageIcon("img/xy2uiimg/41_png.xy2uiimg.wxs_border.png");
        this.icon3 = new ImageIcon("img/background/9.png");
        this.icon4 = new ImageIcon("img/qiehuan/34_png.xy2uiimg.wxs_bg.png");
        this.icon6 = new ImageIcon("img/xy2uiimg/ss768.png");
        this.add(OpenSkillGridJpanel.effect);
        this.add(this.effect2);
        this.box = new RichLabel("", UIUtils.TEXT_FONT1);
        if ("水墨".equals(MyIsif.getStyle())) {
            this.setPreferredSize(new Dimension(485, 445));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            this.add(this.offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 188888));
            (this.goodBox = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    if (OpenSkillGridJpanel.this.goodstable != null) {
                        ImageIcon image = CutButtonImage.getImage("img/item/" + OpenSkillGridJpanel.this.goodstable.getSkin() + ".png", 40, 40);
                        g.drawImage(image.getImage(), OpenSkillGridJpanel.this.x, OpenSkillGridJpanel.this.y, this);
                    }
                }
            }).setBounds(210, 335, 50, 50);
            this.goodBox.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    OpenSkillGridJpanel.this.x = 4;
                    OpenSkillGridJpanel.this.y = 4;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    OpenSkillGridJpanel.this.x = 5;
                    OpenSkillGridJpanel.this.y = 5;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE1)) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
                    }
                    if (OpenSkillGridJpanel.this.goodstable != null) {
                        ZhuFrame.getZhuJpanel().creatgoodtext(OpenSkillGridJpanel.this.goodstable);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE12)) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                    }
                    FormsManagement.HideForm(24);
                }
            });
            this.add(this.goodBox);
            OpenSkillGridJpanel.listModel = new DefaultListModel<>();
            (this.listpet = new JList<String>(OpenSkillGridJpanel.listModel) {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            this.listpet.setSelectionMode(0);
            this.listpet.setCellRenderer(new MyRendererOld());
            this.listpet.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (OpenSkillGridJpanel.this.listpet.getSelectedIndex() != -1) {
                        UserMessUntil.setChosePetMes((RoleSummoning)UserMessUntil.getPetListTable().get(OpenSkillGridJpanel.this.listpet.getSelectedIndex()));
                        PetAddPointMouslisten.showPetValue();
                        if (UserMessUntil.getChosePetMes() != null) {
                            OpenSkillGridJpanel.this.refreshPetSkills();
                        }
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    ZhuFrame.getZhuJpanel().pettext();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.isMetaDown()) {
                        FormsManagement.HideForm(17);
                    }
                }
            });
            this.listpet.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    if (e.getY() / 22 < OpenSkillGridJpanel.this.listpet.getModel().getSize()) {
                        TestPetJpanel.warNum = OpenSkillGridJpanel.this.listpet.locationToIndex(e.getPoint());
                        ZhuFrame.getZhuJpanel().creatpettext((RoleSummoning)UserMessUntil.getPetListTable().get(TestPetJpanel.warNum));
                    }
                }
            });
            (this.jScrollPane = new JScrollPane(this.listpet)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(47, 60, 151, 165);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            OpenSkillGridJpanel.listGooosModel = new DefaultListModel<>();
            (OpenSkillGridJpanel.listGoods = new JList<Goodstable>(OpenSkillGridJpanel.listGooosModel) {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            OpenSkillGridJpanel.listGoods.setSelectionMode(0);
            OpenSkillGridJpanel.listGoods.setCellRenderer(new MyRendererGood());
            OpenSkillGridJpanel.listGoods.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    OpenSkillGridJpanel this$0 = OpenSkillGridJpanel.this;
                    if (OpenSkillGridJpanel.listGoods.getSelectedIndex() != -1) {
                        OpenSkillGridJpanel this$2 = OpenSkillGridJpanel.this;
                        OpenSkillGridJpanel this$3 = OpenSkillGridJpanel.this;
                        OpenSkillGridJpanel.this.goodstable = (Goodstable) OpenSkillGridJpanel.listGoods.getSelectedValue();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    ZhuFrame.getZhuJpanel().pettext();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.isMetaDown()) {
                        FormsManagement.HideForm(17);
                    }
                }
            });
            OpenSkillGridJpanel.listGoods.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    if (e.getY() / 25 < OpenSkillGridJpanel.listGoods.getModel().getSize()) {
                        OpenSkillGridJpanel.idx = OpenSkillGridJpanel.listGoods.locationToIndex(e.getPoint());
                    }
                }
            });
            (this.GooodsJScrollPane = new JScrollPane(OpenSkillGridJpanel.listGoods)).setVerticalScrollBarPolicy(22);
            this.GooodsJScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.GooodsJScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.GooodsJScrollPane.getViewport().setOpaque(false);
            this.GooodsJScrollPane.setOpaque(false);
            this.GooodsJScrollPane.setBounds(45, 253, 153, 165);
            this.GooodsJScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.GooodsJScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.GooodsJScrollPane);
            (this.supportListBtn = new DeleteSkillBtn("inkImg/button1/B22.png", 1, "获取技能")).setBounds(232, 396, 100, 30);
            this.supportListBtn.setColors(UIUtils.COLOR_BLACK);
            this.add(this.supportListBtn);
            (this.deleteSkillBtn = new DeleteSkillBtn("inkImg/button1/B22.png", 1, "开技能格")).setColors(UIUtils.COLOR_BLACK);
            this.deleteSkillBtn.setBounds(335, 396, 100, 30);
            this.add(this.deleteSkillBtn);
            int[] arr = { 160, 141 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[0] = new ShowPetSkillsJpMouslisten(0, this, arr);
            this.labPetskills[0].addMouseListener(this.showPetSkills[0]);
            this.add(this.labPetskills[0]);
            arr = new int[] { 91, 184 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[1] = new ShowPetSkillsJpMouslisten(1, this, arr);
            this.labPetskills[1].addMouseListener(this.showPetSkills[1]);
            this.add(this.labPetskills[1]);
            arr = new int[] { 228, 317 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[5] = new ShowPetSkillsJpMouslisten(5, this, arr);
            this.labPetskills[5].addMouseListener(this.showPetSkills[5]);
            this.add(this.labPetskills[5]);
            arr = new int[] { 61, 251 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[2] = new ShowPetSkillsJpMouslisten(2, this, arr);
            this.labPetskills[2].addMouseListener(this.showPetSkills[2]);
            this.add(this.labPetskills[2]);
            arr = new int[] { 160, 360 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[4] = new ShowPetSkillsJpMouslisten(4, this, arr);
            this.labPetskills[4].addMouseListener(this.showPetSkills[4]);
            this.add(this.labPetskills[4]);
            arr = new int[] { 91, 317 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[3] = new ShowPetSkillsJpMouslisten(3, this, arr);
            this.labPetskills[3].addMouseListener(this.showPetSkills[3]);
            this.add(this.labPetskills[3]);
            arr = new int[] { 161, 251 };
            //9技能
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[8] = new ShowPetSkillsJpMouslisten(8, this, arr);
            this.labPetskills[8].addMouseListener(this.showPetSkills[8]);
            this.add(this.labPetskills[8]);
            arr = new int[] { 228, 184 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[7] = new ShowPetSkillsJpMouslisten(7, this, arr);
            this.labPetskills[7].addMouseListener(this.showPetSkills[7]);
            this.add(this.labPetskills[7]);
            arr = new int[] { 259, 251 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[6] = new ShowPetSkillsJpMouslisten(6, this, arr);
            this.labPetskills[6].addMouseListener(this.showPetSkills[6]);
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
            }).setBounds(33, 359, 50, 50);
            arr = new int[] { 33, 359 };
            this.showQlPetSkills[0] = new ShowPetSkillsJpMouslisten(0, this, arr);
            this.qlPetskills[0].addMouseListener(this.showQlPetSkills[0]);
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
            }).setBounds(104, 359, 50, 50);
            arr = new int[] { 104, 359 };
            this.showQlPetSkills[1] = new ShowPetSkillsJpMouslisten(1, this, arr);
            this.qlPetskills[1].addMouseListener(this.showQlPetSkills[1]);
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
            }).setBounds(175, 359, 50, 50);
            arr = new int[] { 175, 359 };
            this.showQlPetSkills[2] = new ShowPetSkillsJpMouslisten(2, this, arr);
            this.qlPetskills[2].addMouseListener(this.showQlPetSkills[2]);
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
            }).setBounds(264, 359, 50, 50);
            arr = new int[] { 264, 359 };
            this.showQlPetSkills[3] = new ShowPetSkillsJpMouslisten(3, this, arr);
            this.qlPetskills[3].addMouseListener(this.showQlPetSkills[3]);
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
            }).setBounds(333, 359, 50, 50);
            arr = new int[] { 333, 359 };
            this.showQlPetSkills[4] = new ShowPetSkillsJpMouslisten(4, this, arr);
            this.qlPetskills[4].addMouseListener(this.showQlPetSkills[4]);
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
            }).setBounds(401, 359, 50, 50);
            arr = new int[] { 401, 359 };
            this.showQlPetSkills[5] = new ShowPetSkillsJpMouslisten(5, this, arr);
            this.qlPetskills[5].addMouseListener(this.showQlPetSkills[5]);
        }
        else {
            this.setPreferredSize(new Dimension(459, 470));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (this.goodBox = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    if (OpenSkillGridJpanel.this.goodstable != null) {
                        ImageIcon image = CutButtonImage.getImage("img/item/" + OpenSkillGridJpanel.this.goodstable.getSkin() + ".png", 40, 40);
                        g.drawImage(image.getImage(), OpenSkillGridJpanel.this.x, OpenSkillGridJpanel.this.y, this);
                    }
                }
            }).setBounds(185, 348, 50, 50);
            this.goodBox.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    OpenSkillGridJpanel.this.x = 4;
                    OpenSkillGridJpanel.this.y = 4;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    OpenSkillGridJpanel.this.x = 5;
                    OpenSkillGridJpanel.this.y = 5;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE1)) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
                    }
                    if (OpenSkillGridJpanel.this.goodstable != null) {
                        ZhuFrame.getZhuJpanel().creatgoodtext(OpenSkillGridJpanel.this.goodstable);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (MessagrFlagUntil.ImgFlagImg.equals(MessagrFlagUntil.MOUSE12)) {
                        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
                    }
                    FormsManagement.HideForm(24);
                }
            });
            this.add(this.goodBox);
            this.add(this.offBtn = new FormsOnOffBtn("img/qiehuan/s74.png", 1, 188888));
            OpenSkillGridJpanel.listModel = new DefaultListModel<>();
            (this.listpet = new JList<String>(OpenSkillGridJpanel.listModel) {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            this.listpet.setSelectionMode(0);
            this.listpet.setCellRenderer(new MyRendererOld());
            this.listpet.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (OpenSkillGridJpanel.this.listpet.getSelectedIndex() != -1) {
                        UserMessUntil.setChosePetMes((RoleSummoning)UserMessUntil.getPetListTable().get(OpenSkillGridJpanel.this.listpet.getSelectedIndex()));
                        PetAddPointMouslisten.showPetValue();
                        if (UserMessUntil.getChosePetMes() != null) {
                            OpenSkillGridJpanel.this.refreshPetSkills();
                        }
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    ZhuFrame.getZhuJpanel().pettext();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.isMetaDown()) {
                        FormsManagement.HideForm(17);
                    }
                }
            });
            this.listpet.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    if (e.getY() / 22 < OpenSkillGridJpanel.this.listpet.getModel().getSize()) {
                        TestPetJpanel.warNum = OpenSkillGridJpanel.this.listpet.locationToIndex(e.getPoint());
                        ZhuFrame.getZhuJpanel().creatpettext((RoleSummoning)UserMessUntil.getPetListTable().get(TestPetJpanel.warNum));
                    }
                }
            });
            (this.jScrollPane = new JScrollPane(this.listpet)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(17, 70, 151, 165);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            OpenSkillGridJpanel.listGooosModel = new DefaultListModel<>();
            (OpenSkillGridJpanel.listGoods = new JList<Goodstable>(OpenSkillGridJpanel.listGooosModel) {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            OpenSkillGridJpanel.listGoods.setSelectionMode(0);
            OpenSkillGridJpanel.listGoods.setCellRenderer(new MyRendererGood());
            OpenSkillGridJpanel.listGoods.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    OpenSkillGridJpanel this$0 = OpenSkillGridJpanel.this;
                    if (OpenSkillGridJpanel.listGoods.getSelectedIndex() != -1) {
                        OpenSkillGridJpanel this$2 = OpenSkillGridJpanel.this;
                        OpenSkillGridJpanel this$3 = OpenSkillGridJpanel.this;
                        OpenSkillGridJpanel.this.goodstable = (Goodstable) OpenSkillGridJpanel.listGoods.getSelectedValue();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    ZhuFrame.getZhuJpanel().pettext();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.isMetaDown()) {
                        FormsManagement.HideForm(17);
                    }
                }
            });
            OpenSkillGridJpanel.listGoods.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    if (e.getY() / 25 < OpenSkillGridJpanel.listGoods.getModel().getSize()) {
                        OpenSkillGridJpanel.idx = OpenSkillGridJpanel.listGoods.locationToIndex(e.getPoint());
                    }
                }
            });
            (this.GooodsJScrollPane = new JScrollPane(OpenSkillGridJpanel.listGoods)).setVerticalScrollBarPolicy(22);
            this.GooodsJScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.GooodsJScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.GooodsJScrollPane.getViewport().setOpaque(false);
            this.GooodsJScrollPane.setOpaque(false);
            this.GooodsJScrollPane.setBounds(17, 265, 153, 165);
            this.GooodsJScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.GooodsJScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.GooodsJScrollPane);
            (this.supportListBtn = new DeleteSkillBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "获取技能")).setColors(UIUtils.COLOR_RED_BTNTEXT);
            this.supportListBtn.setBounds(200, 396, 100, 30);
            this.supportListBtn.setFont(UIUtils.TEXT_BOLD_FONT);
            this.add(this.supportListBtn);
            (this.deleteSkillBtn = new DeleteSkillBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "开技能格")).setBounds(300, 396, 100, 30);
            this.deleteSkillBtn.setColors(UIUtils.COLOR_RED_BTNTEXT);
            this.deleteSkillBtn.setFont(UIUtils.TEXT_BOLD_FONT);
            this.add(this.deleteSkillBtn);
            int[] arr = { 160, 141 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[0] = new ShowPetSkillsJpMouslisten(0, this, arr);
            this.labPetskills[0].addMouseListener(this.showPetSkills[0]);
            this.add(this.labPetskills[0]);
            arr = new int[] { 92, 184 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[1] = new ShowPetSkillsJpMouslisten(1, this, arr);
            this.labPetskills[1].addMouseListener(this.showPetSkills[1]);
            this.add(this.labPetskills[1]);
            arr = new int[] { 228, 317 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[5] = new ShowPetSkillsJpMouslisten(5, this, arr);
            this.labPetskills[5].addMouseListener(this.showPetSkills[5]);
            this.add(this.labPetskills[5]);
            arr = new int[] { 61, 251 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[2] = new ShowPetSkillsJpMouslisten(2, this, arr);
            this.labPetskills[2].addMouseListener(this.showPetSkills[2]);
            this.add(this.labPetskills[2]);
            arr = new int[] { 160, 360 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[4] = new ShowPetSkillsJpMouslisten(4, this, arr);
            this.labPetskills[4].addMouseListener(this.showPetSkills[4]);
            this.add(this.labPetskills[4]);
            arr = new int[] { 91, 317 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[3] = new ShowPetSkillsJpMouslisten(3, this, arr);
            this.labPetskills[3].addMouseListener(this.showPetSkills[3]);
            this.add(this.labPetskills[3]);
            arr = new int[] { 161, 251 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[8] = new ShowPetSkillsJpMouslisten(8, this, arr);
            this.labPetskills[8].addMouseListener(this.showPetSkills[8]);
            this.add(this.labPetskills[8]);
            arr = new int[] { 228, 184 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[7] = new ShowPetSkillsJpMouslisten(7, this, arr);
            this.labPetskills[7].addMouseListener(this.showPetSkills[7]);
            this.add(this.labPetskills[7]);
            arr = new int[] { 259, 251 };
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
            }).setBounds(arr[0], arr[1], 50, 50);
            this.showPetSkills[6] = new ShowPetSkillsJpMouslisten(6, this, arr);
            this.labPetskills[6].addMouseListener(this.showPetSkills[6]);
            this.add(this.labPetskills[6]);
            (this.qlPetskills[0] = new JLabel()).setBounds(33, 359, 50, 50);
            arr = new int[] { 33, 359 };
            this.showQlPetSkills[0] = new ShowPetSkillsJpMouslisten(0, this, arr);
            this.qlPetskills[0].addMouseListener(this.showQlPetSkills[0]);
            (this.qlPetskills[1] = new JLabel()).setBounds(104, 359, 50, 50);
            arr = new int[] { 104, 359 };
            this.showQlPetSkills[1] = new ShowPetSkillsJpMouslisten(1, this, arr);
            this.qlPetskills[1].addMouseListener(this.showQlPetSkills[1]);
            (this.qlPetskills[2] = new JLabel()).setBounds(175, 359, 50, 50);
            arr = new int[] { 175, 359 };
            this.showQlPetSkills[2] = new ShowPetSkillsJpMouslisten(2, this, arr);
            this.qlPetskills[2].addMouseListener(this.showQlPetSkills[2]);
            (this.qlPetskills[3] = new JLabel()).setBounds(264, 359, 50, 50);
            arr = new int[] { 264, 359 };
            this.showQlPetSkills[3] = new ShowPetSkillsJpMouslisten(3, this, arr);
            this.qlPetskills[3].addMouseListener(this.showQlPetSkills[3]);
            (this.qlPetskills[4] = new JLabel()).setBounds(333, 359, 50, 50);
            arr = new int[] { 333, 359 };
            this.showQlPetSkills[4] = new ShowPetSkillsJpMouslisten(4, this, arr);
            this.qlPetskills[4].addMouseListener(this.showQlPetSkills[4]);
            (this.qlPetskills[5] = new JLabel()).setBounds(401, 359, 50, 50);
            arr = new int[] { 401, 359 };
            this.showQlPetSkills[5] = new ShowPetSkillsJpMouslisten(5, this, arr);
            this.qlPetskills[5].addMouseListener(this.showQlPetSkills[5]);
        }
        this.add(this.wuLing = new WuLingPanel());
    }

    public void addNaturalSkills(RoleSummoning pet) {
        this.deleteNaturalSkills();
        if (pet != null) {
            String[] skills = pet.getSkill().split("\\|");
            int length = (skills != null) ? skills.length : 0;
            boolean is = Arrays.asList(new String[] { "754", "755", "756", "757", "758", "759", "760" }).contains(pet.getSummoningid());
            if (is) {
                ++length;
            }
            if (length > 0) {
                this.naturalSkills = new JLabel[length];
                int x = 280 - 56 * this.naturalSkills.length / 2;
                int y = 52;
                if ("水墨UI".equals(MyIsif.getStyle())) {
                    y = 50;
                }
                else {
                    y = 60;
                }
                for (int i = 0; i < length; ++i) {
                    (this.naturalSkills[i] = new JLabel()).setBounds(x, y, 53, 53);
                    RoleSummoning temPet = null;
                    Skill skill = null;
                    ImageIcon img1;
                    if (i >= skills.length) {
                        temPet = pet;
                        img1 = new ImageIcon("img/878D5747.png");
                    }
                    else {
                        img1 = new ImageIcon("img/fighting-skill/" + skills[i] + ".png");
                        skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(skills[i]);
                    }
                    img1.setImage(img1.getImage().getScaledInstance(53, 53, 10));
                    this.naturalSkills[i].setIcon(img1);
                    this.naturalSkills[i].addMouseListener(this.getNaturalMouseListener(skill, temPet));
                    this.add(this.naturalSkills[i]);
                    x += 60;
                }
            }
        }
    }

    public MouseListener getNaturalMouseListener(Skill skill, RoleSummoning pet) {
        return new MouseListener() {
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
                MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (pet != null) {
                    FormsManagement.showForm(701);
                }
                Music.addyinxiao("开关窗口.mp3");
                if (skill != null) {
                    PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().skillmsg(skill, 0);
                }
                else {
                    PetSkillsJframe.getPetSkillsJframe().getPetSkillsJpanel().getBox().setText(null);
                }
            }
        };
    }

    public void deleteNaturalSkills() {
        if (this.naturalSkills != null) {
            for (JLabel naturalSkill : this.naturalSkills) {
                this.remove(naturalSkill);
            }
        }
        this.naturalSkills = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if ("水墨".equals(MyIsif.getStyle())) {
            g.drawImage(this.icon.getImage(), 0, 0, this);
            if (this.naturalSkills != null) {
                for (JLabel naturalSkill : this.naturalSkills) {
                    g.drawImage(this.icon3.getImage(), naturalSkill.getX() - 3, naturalSkill.getY() - 2, this);
                }
            }
            if (this.goodstable != null) {
                g.setColor(Color.yellow);
                g.setFont(UIUtils.TEXT_FONT78);
                g.drawString("技能：", 277, 352);
                g.drawString("技能等级：", 277, 375);
                String value = this.goodstable.getValue();
                String[] split = value.split("\\|");
                g.setColor(Color.green);
                for (int i = 0; i < split.length; ++i) {
                    String s = split[i].split("=")[1];
                    g.drawString(s, 325 + i * 23, 352 + i * 22);
                }
            }
        }
        else {
            g.drawImage(this.icon.getImage(), 0, 0, this);
            if (this.naturalSkills != null) {
                for (JLabel naturalSkill : this.naturalSkills) {
                    g.drawImage(this.icon6.getImage(), naturalSkill.getX() - 3, naturalSkill.getY() - 2, this);
                }
            }
            if (this.goodstable != null) {
                g.setColor(Color.yellow);
                g.setFont(UIUtils.TEXT_FONT78);
                g.drawString("技能：", 257, 365);
                g.drawString("技能等级：", 257, 388);
                String value = this.goodstable.getValue();
                String[] split = value.split("\\|");
                g.setColor(Color.green);
                for (int i = 0; i < split.length; ++i) {
                    String s = split[i].split("=")[1];
                    g.drawString(s, 305 + i * 23, 365 + i * 22);
                }
            }
        }
    }

    public static JLabel getEffect() {
        return OpenSkillGridJpanel.effect;
    }

    public void setEffect(JLabel effect) {
        OpenSkillGridJpanel.effect = effect;
    }

    public void skillmsg(Skill skill, int skillwl) {
        this.box.setText(null);
        StringBuffer buffer = new StringBuffer();
        if (skill != null) {
            if (Integer.parseInt(skill.getSkillid()) < 1100) {
                String setText = this.setText(skill, 1.0, 200);
                if (setText != null) {
                    this.box.addText(setText);
                }
                Dimension d = this.box.computeSize(130);
                this.wuLing.setLocation(this.jScrollPane2.getX() + 5, WuLingPanel.y + (int)d.getHeight() - 10);
            }
            else {
                String msg = skill.getRemark();
                double sv = Double.parseDouble(skill.getGrow());
                long qm = (long)UserMessUntil.getChosePetMes().getFriendliness();
                double value = Double.parseDouble(skill.getValue());
                if (skill.getSkillid().equals("1237")) {
                    msg = msg.replace("{0}", String.format("%.2f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }));
                    msg = msg.replace("{1}", String.format("%.2f", new Object[] { Double.valueOf(25.0 + CustomFunction.XS(qm, sv)) }));
                }
                else if (skill.getSkillid().equals("1238") || skill.getSkillid().equals("1240")) {
                    msg = msg.replace("{0}", String.format("%.2f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }));
                    double v = Double.parseDouble((skill.getValue1() == null || "".equals(skill.getValue1())) ? "0" : skill.getValue1());
                    double xs = CustomFunction.XS(qm, Double.parseDouble((skill.getGrow1() == null || "".equals(skill.getGrow1())) ? "0" : skill.getGrow1()));
                    msg = msg.replace("{1}", String.format("%.2f", new Object[] { Double.valueOf(Arith.add(v, xs)) }));
                }
                else if (skill.getSkillid().equals("1241")) {
                    msg = msg.replace("{0}", String.format("%.2f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }));
                }
                else if (skill.getSkillid().equals("1216")) {
                    msg = msg.replace("{0}", String.format("%.2f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }));
                    msg = msg.replace("{1}", String.format("%.2f", new Object[] { Double.valueOf(value + CustomFunction.XS(qm, sv)) }));
                }
                String[] v2 = StringReplace(msg).split("\\|");
                for (int i = 0; i < v2.length; ++i) {
                    String[] v3 = v2[i].split("=");
                    if (skill.getSkilltype().equals("4") && v3[1].equals("0/999")) {
                        v3[1] = UserMessUntil.getChosePetMes().getTrainNum() + "/999";
                    }
                    String vg;
                    if ((vg = this.gongshi(v2, i)) != null) {
                        this.box.addText("#c" + v3[0] + " " + v3[1] + "#c" + v2[++i].split("=")[0] + vg + "#r");
                    }
                    else {
                        this.box.addText("#c" + v3[0] + " " + v3[1] + "#r");
                    }
                }
                Dimension d2 = this.box.computeSize(130);
                this.box.setSize(d2);
                this.box.setPreferredSize(d2);
                this.wuLing.setLocation(this.jScrollPane2.getX() + 5, WuLingPanel.y + this.box.getHeight() - 10);
            }
            this.wuLing.update(skill, skillwl);
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

    public void XySkill() {
        if (UserMessUntil.getChosePetMes().getSummoningskin().equals("900106")) {
            if ("水墨UI".equals(MyIsif.getStyle())) {
                this.xinyuan.setBounds(248, 55, 55, 47);
            }
            else {
                this.xinyuan.setBounds(248, 68, 55, 47);
            }
            this.xinyuan.addMouseListener(new WLLMouslisten(40));
            this.xinyuan.setVisible(true);
        }
        else {
            this.xinyuan.setVisible(false);
        }
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

    public ShowPetSkillsJpMouslisten[] getShowPetSkills() {
        return this.showPetSkills;
    }

    public void setShowPetSkills(ShowPetSkillsJpMouslisten[] showPetSkills) {
        this.showPetSkills = showPetSkills;
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

    public ShowPetSkillsJpMouslisten[] getShowQlPetSkills() {
        return this.showQlPetSkills;
    }

    public void setShowQlPetSkills(ShowPetSkillsJpMouslisten[] showQlPetSkills) {
        this.showQlPetSkills = showQlPetSkills;
    }

    public JLabel getEffect2() {
        return this.effect2;
    }

    public void setEffect2(JLabel effect2) {
        this.effect2 = effect2;
    }

    public Sprite getTcp() {
        return OpenSkillGridJpanel.tcp;
    }

    public void setTcp(Sprite tcp) {
        OpenSkillGridJpanel.tcp = tcp;
    }

    public WuLingPanel getWuLing() {
        return this.wuLing;
    }

    public void mouseEntered(MouseEvent e) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE12);
    }

    public void mouseExited(MouseEvent e) {
        MessagrFlagUntil.setMouse(MessagrFlagUntil.MOUSE1);
    }

    public void refuPetSkillsJpanel() {
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        if (pet == null) {
            pet = (RoleSummoning)UserMessUntil.getPetListTable().get(0);
            UserMessUntil.setChosePetMes(pet);
        }
        if ("水墨".equals(MyIsif.getStyle())) {
            this.offBtn.setBounds(450, 10, 25, 25);
            if (pet.getTurnRount() == 4) {
                this.skllNum = 9;
            }
            else {
                this.skllNum = 7;
            }
            if (this.skllNum == 7) {
                if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                    this.icon = new ImageIcon("Client/神通天演册/40×40/龙/xp/smjpd7.png");
                }
                else {
                    this.icon = new ImageIcon("Client/神通天演册/40×40/龙/xp/smjpd6.png");
                }
            }
            else if (this.skllNum == 9) {
                if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                    this.icon = new ImageIcon("Client/神通天演册/40×40/龙/xp/smjpd9.png");
                }
                else {
                    this.icon = new ImageIcon("Client/神通天演册/40×40/龙/xp/smjpd8.png");
                }
            }
            this.add(OpenSkillGridJpanel.effect);
            if (this.skllNum == 7 || this.skllNum == 5) {
                if (this.skllNum == 7) {
                    int sx = 103;
                    this.supportListBtn.setBounds(220, 288 + sx, 100, 30);
                    this.deleteSkillBtn.setBounds(350, 288 + sx, 100, 30);
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
                this.showPetSkills[0] = new ShowPetSkillsJpMouslisten(0, this, arr);
                this.labPetskills[0].addMouseListener(this.showPetSkills[0]);
                this.add(this.labPetskills[0]);
                (this.labPetskills[1] = new JLabel()).setBounds(72, 253, 50, 50);
                jLabel = new JLabel();
                jLabel.setBounds(72, 253, 50, 50);
                arr = new int[] { 72, 253 };
                this.showPetSkills[1] = new ShowPetSkillsJpMouslisten(1, this, arr);
                this.labPetskills[1].addMouseListener(this.showPetSkills[1]);
                this.add(this.labPetskills[1]);
                (this.labPetskills[4] = new JLabel()).setBounds(154, 252, 50, 50);
                arr = new int[] { 154, 252 };
                this.showPetSkills[4] = new ShowPetSkillsJpMouslisten(4, this, arr);
                this.labPetskills[4].addMouseListener(this.showPetSkills[4]);
                this.add(this.labPetskills[4]);
                (this.labPetskills[3] = new JLabel()).setBounds(237, 252, 50, 50);
                arr = new int[] { 237, 252 };
                this.showPetSkills[3] = new ShowPetSkillsJpMouslisten(3, this, arr);
                this.labPetskills[3].addMouseListener(this.showPetSkills[3]);
                this.add(this.labPetskills[3]);
                (this.labPetskills[2] = new JLabel()).setBounds(154, 341, 50, 50);
                arr = new int[] { 154, 341 };
                this.showPetSkills[2] = new ShowPetSkillsJpMouslisten(2, this, arr);
                this.labPetskills[2].addMouseListener(this.showPetSkills[2]);
                this.add(this.labPetskills[2]);
            }
            else if (this.skllNum == 7) {
                int sx = 5;
                int sy = 151;
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
                int sx = 150;
                int sy = -95;
                this.labPetskills[0].setBounds(sx + 154, sy + 141, 50, 50);
                this.labPetskills[1].setBounds(sx + 87, sy + 188, 50, 50);
                this.labPetskills[5].setBounds(sx + 222, sy + 320, 50, 50);
                this.labPetskills[2].setBounds(sx + 65, sy + 254, 50, 50);
                this.labPetskills[4].setBounds(sx + 154, sy + 362, 50, 50);
                this.labPetskills[3].setBounds(sx + 87, sy + 320, 50, 50);
                this.labPetskills[6].setBounds(sx + 247, sy + 254, 50, 50);
                if (this.labPetskills.length > 7) {
                    this.labPetskills[8].setBounds(sx + 154, sy + 254, 50, 50);
                    this.labPetskills[7].setBounds(sx + 222, sy + 188, 50, 50);
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
            int sx = 103;
            this.supportListBtn.setBounds(220, 288 + sx, 100, 30);
            this.deleteSkillBtn.setBounds(350, 288 + sx, 100, 30);
        }
        else {
            this.offBtn.setBounds(433, 0, 25, 25);
            if (pet.getTurnRount() == 4) {
                this.skllNum = 9;
            }
            else {
                this.skllNum = 7;
            }
            if (this.skllNum == 7) {
                if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                    this.icon = new ImageIcon("Client/神通天演册/40×40/龙/特效/hmjpd7.png");
                }
                else {
                    this.icon = new ImageIcon("Client/神通天演册/40×40/龙/特效/hmjpd6.png");
                }
            }
            else if (this.skllNum == 9) {
                if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                    this.icon = new ImageIcon("Client/神通天演册/40×40/龙/特效/hmjpd9.png");
                }
                else {
                    this.icon = new ImageIcon("Client/神通天演册/40×40/龙/特效/hmjpd8.png");
                }
            }
            this.add(OpenSkillGridJpanel.effect);
            int sx = 103;
            if (this.skllNum == 5) {
                (this.labPetskills[0] = new JLabel()).setBounds(155, 163, 50, 50);
                JLabel jLabel2 = new JLabel();
                jLabel2.setBounds(155, 163, 50, 50);
                int[] arr2 = { 155, 163 };
                this.showPetSkills[0] = new ShowPetSkillsJpMouslisten(0, this, arr2);
                this.labPetskills[0].addMouseListener(this.showPetSkills[0]);
                this.add(this.labPetskills[0]);
                (this.labPetskills[1] = new JLabel()).setBounds(72, 253, 50, 50);
                jLabel2 = new JLabel();
                jLabel2.setBounds(72, 253, 50, 50);
                arr2 = new int[] { 72, 253 };
                this.showPetSkills[1] = new ShowPetSkillsJpMouslisten(1, this, arr2);
                this.labPetskills[1].addMouseListener(this.showPetSkills[1]);
                this.add(this.labPetskills[1]);
                (this.labPetskills[4] = new JLabel()).setBounds(154, 252, 50, 50);
                arr2 = new int[] { 154, 252 };
                this.showPetSkills[4] = new ShowPetSkillsJpMouslisten(4, this, arr2);
                this.labPetskills[4].addMouseListener(this.showPetSkills[4]);
                this.add(this.labPetskills[4]);
                (this.labPetskills[3] = new JLabel()).setBounds(237, 252, 50, 50);
                arr2 = new int[] { 237, 252 };
                this.showPetSkills[3] = new ShowPetSkillsJpMouslisten(3, this, arr2);
                this.labPetskills[3].addMouseListener(this.showPetSkills[3]);
                this.add(this.labPetskills[3]);
                (this.labPetskills[2] = new JLabel()).setBounds(154, 341, 50, 50);
                arr2 = new int[] { 154, 341 };
                this.showPetSkills[2] = new ShowPetSkillsJpMouslisten(2, this, arr2);
                this.labPetskills[2].addMouseListener(this.showPetSkills[2]);
                this.add(this.labPetskills[2]);
            }
            else if (this.skllNum == 7) {
                sx = 18;
                int sy = 126;
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
                sx = 125;
                int sy = -82;
                this.labPetskills[0].setBounds(sx + 154, sy + 141, 50, 50);
                this.labPetskills[1].setBounds(sx + 87, sy + 188, 50, 50);
                this.labPetskills[5].setBounds(sx + 222, sy + 320, 50, 50);
                this.labPetskills[2].setBounds(sx + 65, sy + 254, 50, 50);
                this.labPetskills[4].setBounds(sx + 154, sy + 362, 50, 50);
                this.labPetskills[3].setBounds(sx + 87, sy + 320, 50, 50);
                this.labPetskills[6].setBounds(sx + 247, sy + 254, 50, 50);
                if (this.labPetskills.length > 7) {
                    this.labPetskills[8].setBounds(sx + 154, sy + 254, 50, 50);
                    this.labPetskills[7].setBounds(sx + 222, sy + 188, 50, 50);
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
            sx = 123;
            this.supportListBtn.setBounds(190, 288 + sx, 100, 30);
            this.deleteSkillBtn.setBounds(320, 288 + sx, 100, 30);
        }
    }

    public static void showListModel() {
        OpenSkillGridJpanel.listModel.removeAllElements();
        for (RoleSummoning pet : UserMessUntil.getPetListTable()) {
            OpenSkillGridJpanel.listModel.addElement(pet.getSummoningname());
        }
    }

    public static void showGoodsListModel() {
        OpenSkillGridJpanel.listGooosModel.removeAllElements();
        List<Goodstable> chaxunss = GoodsListFromServerUntil.chaxunss(2326L);
        for (Goodstable goodstable : chaxunss) {
            OpenSkillGridJpanel.listGooosModel.addElement(goodstable);
        }
        OpenSkillGridJframe.getOpenSkillGridJframe().getOpenSkillGridJpanel().setGoodstable((Goodstable)getListGooosModel().get(0));
        OpenSkillGridJpanel.listGoods.setSelectedIndex(0);
        OpenSkillGridJpanel.listGoods.setSelectionMode(0);
    }

    public void refreshPetSkills() {
        this.refuPetSkillsJpanel();
        OpenSkillGridJpanel petSkillsJpanel = OpenSkillGridJframe.getOpenSkillGridJframe().getOpenSkillGridJpanel();
        int Skillwl1 = 0;
        if (UserMessUntil.getChosePetMes() != null && UserMessUntil.getChosePetMes().getPetSkills() != null && !UserMessUntil.getChosePetMes().getPetSkills().equals("")) {
            String[] petnaturalskill = UserMessUntil.getChosePetMes().getPetSkills().split("\\|");
            for (int i = 0; i < 8; ++i) {
                petSkillsJpanel.getLabPetskills()[i].setIcon(null);
                petSkillsJpanel.getShowPetSkills()[i].setSkill(null);
            }
            for (int i = 0; i < petnaturalskill.length; ++i) {
                Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(petnaturalskill[i]);
                ImageIcon img = new ImageIcon("img/skill/wxs_" + petnaturalskill[i] + ".png");
                img.setImage(img.getImage().getScaledInstance(50, 50, 10));
                petSkillsJpanel.getLabPetskills()[i].setIcon(img);
                petSkillsJpanel.getShowPetSkills()[i].setSkill(skill);
            }
            for (int i = 0; i < petnaturalskill.length; ++i) {
                Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(petnaturalskill[i]);
                ImageIcon img = new ImageIcon("img/skill/wxs_" + petnaturalskill[i] + ".png");
                img.setImage(img.getImage().getScaledInstance(50, 50, 10));
                if (UserMessUntil.getChosePetMes().getPetSkillswl() != null && !UserMessUntil.getChosePetMes().getPetSkillswl().equals("")) {
                    String[] petskillswl = UserMessUntil.getChosePetMes().getPetSkillswl().split("\\|");
                    for (int i2 = 0; i2 < petskillswl.length; ++i2) {
                        String[] level = petskillswl[i2].split("=");
                        if (petnaturalskill[i].equals(level[0])) {
                            Skillwl1 = Integer.parseInt(level[1]);
                        }
                    }
                }
                petSkillsJpanel.getShowPetSkills()[i].setSkillwl(Skillwl1);
                if (Skillwl1 == 0) {
                    petSkillsJpanel.getLabPetskills()[i].setIcon(img);
                }
                else {
                    ImageIcon img2 = new ImageIcon("img/skill/wxs_" + petnaturalskill[i] + "x.png");
                    img2.setImage(img2.getImage().getScaledInstance(52, 52, 10));
                    petSkillsJpanel.getLabPetskills()[i].setIcon(img2);
                }
                petSkillsJpanel.getShowPetSkills()[i].setSkill(skill);
                Skillwl1 = 0;
            }
            if ((int)UserMessUntil.getChosePetMes().getOpenSeal() <= 7) {
                for (int i = (int)UserMessUntil.getChosePetMes().getOpenSeal(); i < 8; ++i) {
                    petSkillsJpanel.getLabPetskills()[i].setIcon(new ImageIcon("img/xy2uiimg/111_png.xy2uiimg.wxs_fy.png"));
                    petSkillsJpanel.getShowPetSkills()[i].setSkill(null);
                }
            }
            if ((int)UserMessUntil.getChosePetMes().getFoPenSeal() <= 7) {
                for (int i = (int)UserMessUntil.getChosePetMes().getFoPenSeal(); i < 8; ++i) {
                    petSkillsJpanel.getLabPetskills()[i].setIcon(new ImageIcon("img/xy2uiimg/115.png"));
                    petSkillsJpanel.getShowPetSkills()[i].setSkill(null);
                }
            }
        }
        else {
            for (int i = 0; i < 8; ++i) {
                petSkillsJpanel.getLabPetskills()[i].setIcon(null);
                petSkillsJpanel.getShowPetSkills()[i].setSkill(null);
            }
            if ((int)UserMessUntil.getChosePetMes().getOpenSeal() <= 7) {
                for (int i = (int)UserMessUntil.getChosePetMes().getOpenSeal(); i < 8; ++i) {
                    petSkillsJpanel.getLabPetskills()[i].setIcon(new ImageIcon("img/xy2uiimg/111_png.xy2uiimg.wxs_fy.png"));
                    petSkillsJpanel.getShowPetSkills()[i].setSkill(null);
                }
            }
            if ((int)UserMessUntil.getChosePetMes().getFoPenSeal() <= 7) {
                for (int i = (int)UserMessUntil.getChosePetMes().getFoPenSeal(); i < 8; ++i) {
                    petSkillsJpanel.getLabPetskills()[i].setIcon(new ImageIcon("img/xy2uiimg/115.png"));
                    petSkillsJpanel.getShowPetSkills()[i].setSkill(null);
                }
            }
        }
        if (UserMessUntil.getChosePetMes() != null && UserMessUntil.getChosePetMes().getPetQlSkills() != null && !UserMessUntil.getChosePetMes().getPetQlSkills().equals("")) {
            String[] petnaturalskill = UserMessUntil.getChosePetMes().getPetQlSkills().split("\\|");
            for (int i = 0; i < 6; ++i) {
                petSkillsJpanel.getQlPetskills()[i].setIcon(null);
                petSkillsJpanel.getShowQlPetSkills()[i].setSkill(null);
            }
            for (int i = 0; i < petnaturalskill.length; ++i) {
                Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(petnaturalskill[i]);
                ImageIcon img = new ImageIcon("img/skill/wxs_" + petnaturalskill[i] + ".png");
                img.setImage(img.getImage().getScaledInstance(50, 50, 10));
            }
            for (int i = 0; i < petnaturalskill.length; ++i) {
                Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(petnaturalskill[i]);
                ImageIcon img = new ImageIcon("img/skill/wljn_" + petnaturalskill[i] + ".png");
                img.setImage(img.getImage().getScaledInstance(50, 50, 10));
                petSkillsJpanel.getQlPetskills()[i].setIcon(img);
                petSkillsJpanel.getShowQlPetSkills()[i].setSkill(skill);
            }
            if ((int)UserMessUntil.getChosePetMes().getOpenql() <= 5) {
                for (int i = (int)UserMessUntil.getChosePetMes().getOpenql(); i < 6; ++i) {
                    petSkillsJpanel.getQlPetskills()[i].setIcon(new ImageIcon("img/xy2uiimg/111_png.xy2uiimg.wxs_fy.png"));
                    petSkillsJpanel.getShowQlPetSkills()[i].setSkill(null);
                }
            }
        }
        else {
            for (int i = 0; i < 6; ++i) {
                petSkillsJpanel.getQlPetskills()[i].setIcon(null);
                petSkillsJpanel.getShowQlPetSkills()[i].setSkill(null);
            }
            if ((int)UserMessUntil.getChosePetMes().getOpenql() <= 6) {
                for (int i = (int)UserMessUntil.getChosePetMes().getOpenql(); i < 6; ++i) {
                    petSkillsJpanel.getQlPetskills()[i].setIcon(new ImageIcon("img/xy2uiimg/60_png.xy2uiimg.clip_wxi.png"));
                    petSkillsJpanel.getShowQlPetSkills()[i].setSkill(null);
                }
            }
        }
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        Integer skllNum = Integer.valueOf(7);
        if (pet.getTurnRount() == 4) {
            skllNum = Integer.valueOf(9);
        }
        else {
            skllNum = Integer.valueOf(7);
        }
        if (UserMessUntil.getChosePetMes().getBeastSkills() != null && !UserMessUntil.getChosePetMes().getBeastSkills().equals("") && !UserMessUntil.getChosePetMes().getBeastSkills().equals("-1")) {
            if ((int)skllNum == 7) {
                if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                    petSkillsJpanel.getLabPetskills()[6].setIcon(null);
                    petSkillsJpanel.getShowPetSkills()[6].setSkill(null);
                    Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(UserMessUntil.getChosePetMes().getBeastSkills());
                    ImageIcon img = new ImageIcon("img/skill/wxs_" + UserMessUntil.getChosePetMes().getBeastSkills() + ".png");
                    img.setImage(img.getImage().getScaledInstance(50, 50, 10));
                    petSkillsJpanel.getLabPetskills()[6].setIcon(img);
                    petSkillsJpanel.getShowPetSkills()[6].setSkill(skill);
                }
            }
            else if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                petSkillsJpanel.getLabPetskills()[8].setIcon(null);
                petSkillsJpanel.getShowPetSkills()[8].setSkill(null);
                Skill skill = (Skill)UserMessUntil.getSkillBean().getSkillMap().get(UserMessUntil.getChosePetMes().getBeastSkills());
                ImageIcon img = new ImageIcon("img/skill/wxs_" + UserMessUntil.getChosePetMes().getBeastSkills() + ".png");
                img.setImage(img.getImage().getScaledInstance(50, 50, 10));
                petSkillsJpanel.getLabPetskills()[8].setIcon(img);
                petSkillsJpanel.getShowPetSkills()[8].setSkill(skill);
            }
        }
        else if ((int)skllNum == 7) {
            if (pet.getSsn().equals("4") || pet.getSsn().equals("3")) {
                if (UserMessUntil.getChosePetMes().getBeastSkills() != null && UserMessUntil.getChosePetMes().getBeastSkills().equals("-1")) {
                    petSkillsJpanel.getLabPetskills()[6].setIcon(null);
                }
                else {
                    petSkillsJpanel.getLabPetskills()[6].setIcon(new ImageIcon("img/xy2uiimg/111_png.xy2uiimg.wxs_fy.png"));
                }
                petSkillsJpanel.getShowPetSkills()[6].setSkill(null);
            }
        }
        else if ((int)skllNum == 9 && (pet.getSsn().equals("4") || pet.getSsn().equals("3"))) {
            if (UserMessUntil.getChosePetMes().getBeastSkills() != null && UserMessUntil.getChosePetMes().getBeastSkills().equals("") && UserMessUntil.getChosePetMes().getBeastSkills().equals("-1")) {
                petSkillsJpanel.getLabPetskills()[8].setIcon(null);
            }
            else {
                petSkillsJpanel.getLabPetskills()[8].setIcon(new ImageIcon("img/xy2uiimg/111_png.xy2uiimg.wxs_fy.png"));
            }
            petSkillsJpanel.getShowPetSkills()[8].setSkill(null);
        }
    }

    public static DefaultListModel<Goodstable> getListGooosModel() {
        return OpenSkillGridJpanel.listGooosModel;
    }

    public static void setListGooosModel(DefaultListModel<Goodstable> listGooosModel) {
        OpenSkillGridJpanel.listGooosModel = listGooosModel;
    }

    public Goodstable getGoodstable() {
        return this.goodstable;
    }

    public void setGoodstable(Goodstable goodstable) {
        this.goodstable = goodstable;
    }

    static {
        OpenSkillGridJpanel.effect = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        OpenSkillGridJpanel.tcp = SpriteFactory.VloadSprite("resource/mouse/flicker.tcp", null);
        yiru = new ImageIcon("inkimg/background/S290.png");
        OpenSkillGridJpanel.idx = -1;
    }
}
