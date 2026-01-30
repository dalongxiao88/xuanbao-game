package org.come.Jpanel;

import com.tool.btn.PetOperationPanelBtn;
import com.tool.btn.RuneOPerBtn1;
import com.tool.btn.goodbtn;
import com.tool.image.ImageMixDeal;
import com.tool.pet.PetProperty;
import com.tool.tcpimg.RichLabel;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;
import org.come.Frame.TestSetupJframe;
import org.come.Frame.ZhuFrame;
import org.come.bean.Skill;
import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.mouslisten.SpiritualMouslisten;
import org.come.mouslisten.SpiritualPetSkillsMouslisten;
import org.come.until.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class SpiritualJpanel extends JPanel {
    private JList<String> listpet;
    private static DefaultListModel<String> listModel;
    private Color fontcolor;
    private RichLabel namen;
    private JLabel[] labPetskills;
    public JLabel[] PetsListLabeldi;
    private String petskillID;
    private int petskillNO;
    private boolean shoptext;
    private int goodPosition;
    private JScrollPane jScrollPane;
    private PetOperationPanelBtn btnalchemy;
    private JLabel labRefined;
    private static JLabel[] GoodsListLabel;
    private goodbtn[] btnrights;
    private SpiritualMouslisten[] GoodsListListen;
    private SpiritualPetSkillsMouslisten[] showPetSkills;
    private RuneOPerBtn1 perBtn4;
    private int Flag;
    private int count;
    public static int idx;
    private static int xz;
    private int numType;
    private int lock;
    private ImageIcon icon1;
    private static JLabel effect;
    private static JLabel effectx;
    public static ImageIcon iconx;
    public static ImageIcon iconx1;
    private ImageIcon iconS;
    private ImageIcon iconH;
    private ImageIcon iconD;
    private int time;
    public ImageIcon iconx2;
    private int poo;

    public SpiritualJpanel() {
        this.labPetskills = new JLabel[9];
        this.PetsListLabeldi = new JLabel[9];
        this.petskillID = "";
        this.shoptext = false;
        this.GoodsListListen = new SpiritualMouslisten[24];
        this.showPetSkills = new SpiritualPetSkillsMouslisten[9];
        this.Flag = 0;
        this.count = 1;
        this.lock = 0;
        this.icon1 = new ImageIcon("img/xy2uiimg/goodorpet_lock.png");
        this.iconx2 = new ImageIcon("inkimg/background/border_quack.png");
        this.poo = 4;
        this.setPreferredSize(new Dimension(559, 497));
        this.setLayout(null);
        this.setOpaque(false);
        this.Redis();
    }

    public void Redis() {
        this.add((Component) SpiritualJpanel.effectx);
        this.add((Component) (this.labPetskills[0] = new JLabel()));
        this.add((Component) (this.labPetskills[1] = new JLabel()));
        this.add((Component) (this.labPetskills[2] = new JLabel()));
        this.add((Component) (this.labPetskills[3] = new JLabel()));
        this.add((Component) (this.labPetskills[4] = new JLabel()));
        this.add((Component) (this.labPetskills[5] = new JLabel()));
        this.add((Component) (this.labPetskills[6] = new JLabel()));
        this.add((Component) (this.labPetskills[7] = new JLabel()));
        this.add((Component) (this.labPetskills[8] = new JLabel()));
        this.labPetskills[0].setBounds(422, 75, 30, 31);
        this.labPetskills[1].setBounds(378, 103, 30, 31);
        this.labPetskills[2].setBounds(359, 150, 30, 31);
        this.labPetskills[3].setBounds(378, 194, 30, 31);
        this.labPetskills[4].setBounds(422, 217, 30, 31);
        this.labPetskills[5].setBounds(466, 195, 30, 31);
        this.labPetskills[6].setBounds(480, 149, 30, 31);
        this.labPetskills[7].setBounds(466, 103, 30, 31);
        this.labPetskills[8].setBounds(420, 142, 30, 31);
        int[] arr = {422, 75};
        this.showPetSkills[0] = new SpiritualPetSkillsMouslisten(0, this, arr);
        this.labPetskills[0].addMouseListener((MouseListener) this.showPetSkills[0]);
        arr = new int[]{378, 103};
        this.showPetSkills[1] = new SpiritualPetSkillsMouslisten(1, this, arr);
        this.labPetskills[1].addMouseListener((MouseListener) this.showPetSkills[1]);
        arr = new int[]{359, 150};
        this.showPetSkills[2] = new SpiritualPetSkillsMouslisten(2, this, arr);
        this.labPetskills[2].addMouseListener((MouseListener) this.showPetSkills[2]);
        arr = new int[]{378, 194};
        this.showPetSkills[3] = new SpiritualPetSkillsMouslisten(3, this, arr);
        this.labPetskills[3].addMouseListener((MouseListener) this.showPetSkills[3]);
        arr = new int[]{422, 217};
        this.showPetSkills[4] = new SpiritualPetSkillsMouslisten(4, this, arr);
        this.labPetskills[4].addMouseListener((MouseListener) this.showPetSkills[4]);
        arr = new int[]{466, 195};
        this.showPetSkills[5] = new SpiritualPetSkillsMouslisten(5, this, arr);
        this.labPetskills[5].addMouseListener((MouseListener) this.showPetSkills[5]);
        arr = new int[]{480, 149};
        this.showPetSkills[6] = new SpiritualPetSkillsMouslisten(6, this, arr);
        this.labPetskills[6].addMouseListener((MouseListener) this.showPetSkills[6]);
        arr = new int[]{466, 103};
        this.showPetSkills[7] = new SpiritualPetSkillsMouslisten(7, this, arr);
        this.labPetskills[7].addMouseListener((MouseListener) this.showPetSkills[7]);
        arr = new int[]{420, 142};
        this.showPetSkills[8] = new SpiritualPetSkillsMouslisten(8, this, arr);
        this.labPetskills[8].addMouseListener((MouseListener) this.showPetSkills[8]);
        (this.namen = new RichLabel("", UIUtils.TEXT_NAME_FONT, 255)).setBounds(240, 77, 120, 19);
        this.add((Component) this.namen);
        this.btnrights = new goodbtn[6];
        int i = 0;
        while (i < this.btnrights.length) {
            (this.btnrights[i] = new goodbtn("", 0, (JPanel) this, i)).setBounds(502, 263 + i * 29, 26, 28);
            this.btnrights[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    SpiritualJpanel.getEffect().setBounds(10000, 10000, 52, 52);
                    super.mouseClicked(e);
                }
            });
            this.add((Component) this.btnrights[i]);
            ++i;
        }
        SpiritualJpanel.listModel = new DefaultListModel();
        (this.listpet = new JList<String>(SpiritualJpanel.listModel) {
            {
                this.setOpaque(false);
            }
        }).setSelectionBackground(new Color(33, 42, 52));
        this.listpet.setSelectionMode(0);
        this.listpet.setCellRenderer(new MyRendererlin());
        this.listpet.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(final MouseEvent e) {
                SpiritualJpanel.this.clearConsume();
                final RoleSummoning pet = UserMessUntil.getChosePetMes();
                if (UserMessUntil.getChosePetMes() != null) {
                    String[] skills = null;
                    int[] pets = PetProperty.getPetHMASp(pet);
                    int openSeal = pet.getOpenSeal().intValue();
                    if (openSeal > 9) {
                        openSeal = 9;
                    }
                    int i = 0;
                    while (i < pets.length) {
                        pets[i] = 0;
                        ++i;
                    }
                    if (pet.getPetSkills() != null && !pet.getPetSkills().equals("")) {
                        skills = pet.getPetSkills().split("\\|");
                    }
                    i = 0;
                    while (i < openSeal) {
                        if ((skills != null && i < skills.length) || (i == 8 && pet.getBeastSkills() != null && !pet.getBeastSkills().equals(""))) {
                            final Skill skill = UserMessUntil.getSkillId((i == 8) ? pet.getBeastSkills() : skills[i]);
                            if (skill != null) {
                                SpiritualJpanel.this.labPetskills[i].setIcon((Icon) SpiritualJpanel.this.petskillIcon(skill.getSkillid()));
                                SpiritualJpanel.this.showPetSkills[i].setSkill(skill);
                            }
                        }
                        ++i;
                    }
                    if (UserMessUntil.getChosePetMes().getOpenSeal().intValue() <= 7) {
                        int j = UserMessUntil.getChosePetMes().getOpenSeal().intValue();
                        while (j < 8) {
                            SpiritualJpanel.this.labPetskills[j].setIcon(new ImageIcon("inkimg/xbackground/ss552.png"));
                            ++j;
                        }
                    }
                }
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                if (SpiritualJpanel.this.listpet.getSelectedIndex() != -1) {
                    UserMessUntil.setChosePetMes((RoleSummoning) UserMessUntil.getPetListTable().get(SpiritualJpanel.this.listpet.getSelectedIndex()));
                    PetAddPointMouslisten.showPetValue();
                }
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                ZhuFrame.getZhuJpanel().pettext();
                FormsManagement.HideForm(603);
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
            }

            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.isMetaDown()) {
                    FormsManagement.HideForm(17);
                }
            }
        });
        (this.jScrollPane = new JScrollPane((Component) this.listpet)).setVerticalScrollBarPolicy(22);
        this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
        this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        this.jScrollPane.setBounds(25, 281, 162, 181);
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.setHorizontalScrollBarPolicy(31);
        this.add((Component) this.jScrollPane);
        (this.labRefined = new JLabel()).setBounds(238, 105, 50, 51);
        this.labRefined.addMouseListener(new SpiritualMouslisten(24));
        this.add((Component) this.labRefined);
        if (MyIsif.getStyle().equals("水墨")) {
            (this.btnalchemy = new PetOperationPanelBtn("inkImg/button1/B20.png", 1, UIUtils.danxin, UIUtils.TEXT_FONT81, "灵返", 11)).setBounds(225, 180, 59, 24);

        } else {
            (this.btnalchemy = new PetOperationPanelBtn("inkImg/hongmu/6026.png", 1, UIUtils.danxin, UIUtils.TEXT_FONT81, "灵返", 11)).setBounds(230, 180, 60, 26);

        }
        this.add((Component) this.btnalchemy);
//        // 灵返规则
//        perBtn4 = new RuneOPerBtn1("", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "", 5);
//        perBtn4.setBounds(175, 72, 19, 20);
//        this.add(perBtn4);
        i = 0;
        while (i < 24) {
            final int row = i % 6;
            final int col = i / 6;
            arr = new int[]{197 + row * 51, 266 + col * 51};
            SpiritualJpanel.GoodsListLabel[i] = new JLabel();
            this.GoodsListListen[i] = new SpiritualMouslisten(i, this, arr);
            SpiritualJpanel.GoodsListLabel[i].addMouseListener((MouseListener) this.GoodsListListen[i]);
            SpiritualJpanel.GoodsListLabel[i].setBounds(197 + row * 51, 266 + col * 51, 50, 50);
            ++this.Flag;
            this.add((Component) SpiritualJpanel.GoodsListLabel[i]);
            ++i;
        }
        this.add((Component) SpiritualJpanel.effect);
    }

    private ImageIcon petskillIcon(final String skillid) {
        return CutButtonImage.getImage("img/skill/wxs_" + skillid + ".png", 30, 31);
    }

    public void clearConsume() {
        int i = 0;
        while (i < this.labPetskills.length) {
            this.labPetskills[i].setIcon(null);
            ++i;
        }
    }

    public void xuanzhong(final Goodstable goodstable, final int shopPlace) {
        if (goodstable == null) {
            if (SpiritualJpanel.xz != -1) {
                SpiritualJpanel.GoodsListLabel[SpiritualJpanel.xz].setBorder(null);
            }
            SpiritualJpanel.xz = -1;
        } else {
            if (SpiritualJpanel.xz != -1) {
                SpiritualJpanel.GoodsListLabel[SpiritualJpanel.xz].setBorder(null);
            }
            SpiritualJpanel.xz = shopPlace;
            SpiritualJpanel.GoodsListLabel[SpiritualJpanel.xz].setBorder(BorderFactory.createLineBorder(Color.red));
        }
    }

    public static void showListModel(final List<RoleSummoning> pets, final BigDecimal petid) {
        SpiritualJpanel.listModel.removeAllElements();
        final Iterator<RoleSummoning> iterator = pets.iterator();
        while (iterator.hasNext()) {
            final RoleSummoning pet = (RoleSummoning) iterator.next();
            SpiritualJpanel.listModel.addElement(pet.getSummoningname());
        }
    }

    public void PaintingText(final int goodPosition) {
        this.goodPosition = goodPosition;
        this.shoptext = true;
    }

    public void ClearText() {
        this.shoptext = false;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final TestSetupJpanel ss = TestSetupJframe.getTestSetupJframe().getTestSetupJpanel();
        final RoleSummoning pet = UserMessUntil.getChosePetMes();
        if (!"水墨".equals(MyIsif.getStyle())) {
            if (this.iconS == null) {
                this.iconS = new ImageIcon("inkImg/hongmu/ss773.png");
            }
            g.drawImage(this.iconS.getImage(), 0, 0, 536, 497, (ImageObserver) this);
            GoodsListFromServerUntil.draw(g, 195, 263);
            if (this.shoptext) {
                final int shop_x = this.goodPosition % 6;
                final int shop_y = this.goodPosition / 6;
                g.drawImage(this.iconx2.getImage(), 195 + shop_x * 51, 263 + shop_y * 51, (ImageObserver) this);
            }
            this.time += 5;
            if (UserMessUntil.getChosePetMes() != null) {
                if (TestPetJpanel.part == null) {
                    TestPetJpanel.part = UserMessUntil.getChosePetMes().getPart();
                }
                TestPetJpanel.part.draw(g, 110, 220, 0, ImageMixDeal.userimg.getTime() + (long) this.time);
                this.lock = pet.getPetlock();
                if (this.lock == 1) {
                    g.drawImage(this.icon1.getImage(), 154, 77, 10, 12, (ImageObserver) this);
                }
            }
        } else {
            if (this.iconH == null) {
                this.iconH = new ImageIcon("inkImg/background/ss541-1.png");
            }
            g.drawImage(this.iconH.getImage(), 0, 0, 559, 471, (ImageObserver) this);
            GoodsListFromServerUntil.draw(g, 219, 251);
            if (this.shoptext) {
                final int shop_x = this.goodPosition % 6;
                final int shop_y = this.goodPosition / 6;
                g.drawImage(this.iconx2.getImage(), 219 + shop_x * 51, 251 + shop_y * 51, (ImageObserver) this);
            }
            this.time += 5;
            if (UserMessUntil.getChosePetMes() != null) {
                if (TestPetJpanel.part == null) {
                    TestPetJpanel.part = UserMessUntil.getChosePetMes().getPart();
                }
                TestPetJpanel.part.draw(g, 110, 220, 0, ImageMixDeal.userimg.getTime() + (long) this.time);
                this.lock = pet.getPetlock();
                if (this.lock == 1) {
                    g.drawImage(this.icon1.getImage(), 172, 68, 10, 12, (ImageObserver) this);
                }
            }
        }
    }

    public JList<String> getListpet() {
        return this.listpet;
    }

    public void setListpet(final JList<String> listpet) {
        this.listpet = listpet;
    }

    public DefaultListModel<String> getListModel() {
        return SpiritualJpanel.listModel;
    }

    public void setListModel(final DefaultListModel<String> listModel) {
        SpiritualJpanel.listModel = listModel;
    }

    public Color getFontcolor() {
        return this.fontcolor;
    }

    public void setFontcolor(final Color fontcolor) {
        this.fontcolor = fontcolor;
    }

    public JScrollPane getjScrollPane() {
        return this.jScrollPane;
    }

    public void setjScrollPane(final JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }

    public PetOperationPanelBtn getBtnalchemy() {
        return this.btnalchemy;
    }

    public void setBtnalchemy(final PetOperationPanelBtn btnalchemy) {
        this.btnalchemy = btnalchemy;
    }

    public JLabel getLabRefined() {
        return this.labRefined;
    }

    public void setLabRefined(final JLabel labRefined) {
        this.labRefined = labRefined;
    }

    public static JLabel[] getGoodsListLabel() {
        return SpiritualJpanel.GoodsListLabel;
    }

    public void setGoodsListLabel(final JLabel[] goodsListLabel) {
        SpiritualJpanel.GoodsListLabel = goodsListLabel;
    }

    public int getFlag() {
        return this.Flag;
    }

    public void setFlag(final int flag) {
        this.Flag = flag;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public goodbtn[] getBtnrights() {
        return this.btnrights;
    }

    public void setBtnrights(final goodbtn[] btnrights) {
        this.btnrights = btnrights;
    }

    public RichLabel getNamen() {
        return this.namen;
    }

    public void setNamen(final RichLabel namen) {
        this.namen = namen;
    }

    public boolean isShoptext() {
        return this.shoptext;
    }

    public void setShoptext(final boolean shoptext) {
        this.shoptext = shoptext;
    }

    public int getGoodPosition() {
        return this.goodPosition;
    }

    public void setGoodPosition(final int goodPosition) {
        this.goodPosition = goodPosition;
    }

    public SpiritualMouslisten[] getGoodsListListen() {
        return this.GoodsListListen;
    }

    public void setGoodsListListen(final SpiritualMouslisten[] goodsListListen) {
        this.GoodsListListen = goodsListListen;
    }

    public int getNumType() {
        return this.numType;
    }

    public void setNumType(final int numType) {
        this.numType = numType;
    }

    public int getLock() {
        return this.lock;
    }

    public void setLock(final int lock) {
        this.lock = lock;
    }

    public ImageIcon getIcon1() {
        return this.icon1;
    }

    public void setIcon1(final ImageIcon icon1) {
        this.icon1 = icon1;
    }

    public static int getIdx() {
        return SpiritualJpanel.idx;
    }

    public static void setIdx(final int idx) {
        SpiritualJpanel.idx = idx;
    }

    public static ImageIcon getIconx() {
        return SpiritualJpanel.iconx;
    }

    public static void setIconx(final ImageIcon iconx) {
        SpiritualJpanel.iconx = iconx;
    }

    public ImageIcon getIconS() {
        return this.iconS;
    }

    public void setIconS(final ImageIcon iconS) {
        this.iconS = iconS;
    }

    public ImageIcon getIconH() {
        return this.iconH;
    }

    public void setIconH(final ImageIcon iconH) {
        this.iconH = iconH;
    }

    public ImageIcon getIconD() {
        return this.iconD;
    }

    public void setIconD(final ImageIcon iconD) {
        this.iconD = iconD;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(final int time) {
        this.time = time;
    }

    public ImageIcon getIconx2() {
        return this.iconx2;
    }

    public void setIconx2(final ImageIcon iconx2) {
        this.iconx2 = iconx2;
    }

    public int getPoo() {
        return this.poo;
    }

    public void setPoo(final int poo) {
        this.poo = poo;
    }

    public static int getXz() {
        return SpiritualJpanel.xz;
    }

    public static void setXz(final int xz) {
        SpiritualJpanel.xz = xz;
    }

    public static JLabel getEffect() {
        return SpiritualJpanel.effect;
    }

    public static void setEffect(final JLabel effect) {
        SpiritualJpanel.effect = effect;
    }

    public JLabel[] getLabPetskills() {
        return this.labPetskills;
    }

    public void setLabPetskills(final JLabel[] labPetskills) {
        this.labPetskills = labPetskills;
    }

    public JLabel[] getPetsListLabeldi() {
        return this.PetsListLabeldi;
    }

    public void setPetsListLabeldi(final JLabel[] petsListLabeldi) {
        this.PetsListLabeldi = petsListLabeldi;
    }

    public String getPetskillID() {
        return this.petskillID;
    }

    public void setPetskillID(final String petskillID) {
        this.petskillID = petskillID;
    }

    public int getPetskillNO() {
        return this.petskillNO;
    }

    public void setPetskillNO(final int petskillNO) {
        this.petskillNO = petskillNO;
    }

    public SpiritualPetSkillsMouslisten[] getShowPetSkills() {
        return this.showPetSkills;
    }

    public void setShowPetSkills(final SpiritualPetSkillsMouslisten[] showPetSkills) {
        this.showPetSkills = showPetSkills;
    }

    public static JLabel getEffectx() {
        return SpiritualJpanel.effectx;
    }

    public static void setEffectx(final JLabel effectx) {
        SpiritualJpanel.effectx = effectx;
    }

    public RuneOPerBtn1 getPerBtn4() {
        return perBtn4;
    }

    public void setPerBtn2(RuneOPerBtn1 perBtn2) {
        this.perBtn4 = perBtn2;
    }

    static {
        GoodsListLabel = new JLabel[24];
        idx = -1;
        xz = -1;
        effect = new JLabel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                g.drawImage(SpiritualJpanel.iconx.getImage(), 0, 0, 50, 50, (ImageObserver) this);
            }
        };
        effectx = new JLabel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                g.drawImage(SpiritualJpanel.iconx1.getImage(), 0, 0, 34, 34, (ImageObserver) this);
            }
        };
        iconx = CutButtonImage.getImage("inkimg/background/ss231.png", 52, 52);
        iconx1 = CutButtonImage.getImage("inkimg/background/ss231.png", 34, 34);
    }
}
