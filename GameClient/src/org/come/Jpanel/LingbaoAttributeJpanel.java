package org.come.Jpanel;

import org.come.until.UserData;
import com.tool.role.ExpUtil;
import java.math.BigDecimal;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import com.tool.role.RoleLingFa;
import org.come.model.Lingbao;
import org.come.mouslisten.LingFaFanYeMouslisten;
import org.come.mouslisten.LingFaFuSkill;
import java.awt.Dimension;
import com.tool.tcpimg.UIUtils;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.awt.geom.RoundRectangle2D;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.tcpimg.ChatBox;
import com.tool.btn.LingbaoPagingBtn;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LingbaoAttributeJpanel extends JPanel
{
    private JLabel labLingbao;
    private JLabel labQuality;
    private JLabel labCurrentlel;
    private JLabel labAttribute;
    private JLabel labResistance;
    private JTextField labLingName;
    private LingbaoPagingBtn btnRelease;
    private final LingbaoPagingBtn btnDelete;
    private final LingbaoPagingBtn btnBreak;
    private final LingbaoPagingBtn btnTransformation;
    private final LingbaoPagingBtn btnLast1;
    private final LingbaoPagingBtn btnNext1;
    private final LingbaoPagingBtn btnLast2;
    private final LingbaoPagingBtn btnNext2;
    private final LingbaoPagingBtn btnGevPal;
    private JLabel[] RuneListLabel;
    private JLabel[] SkillsListLabel;
    private JLabel[] LingbaoListLabel;
    private JLabel[] MagicListLabel;
    private int Flag;
    private int Flag1;
    private final int count = 1;
    private int count1;
    private ChatBox box;
    private ChatBox zsbox;
    private int boxx;
    private int boxy;
    private int boxw;
    private int boxh;
    private final ImageIcon fushiimg;
    private final ImageIcon skillimg;
    private ImageIcon icon;
    private ImageIcon lingimg;
    private final ImageIcon dian;
    private int s;
    private int w;
    
    public LingbaoAttributeJpanel() {
        this.RuneListLabel = new JLabel[5];
        this.SkillsListLabel = new JLabel[5];
        this.LingbaoListLabel = new JLabel[8];
        this.MagicListLabel = new JLabel[8];
        this.Flag = 0;
        this.Flag1 = 0;
        this.count1 = 1;
        this.box = new ChatBox();
        this.zsbox = new ChatBox();
        this.fushiimg = new ImageIcon("img/lingbao/msg/圆封.png");
        this.skillimg = new ImageIcon("img/lingbao/msg/封.png");
        this.dian = new ImageIcon("img/lingbao/msg/dian.png");
        this.s = 0;
        this.w = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            (this.labLingName = new JTextField() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D)g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0.0, 0.0, (double)this.getWidth(), (double)this.getHeight(), 7.0, 7.0);
                    g2d.setComposite(AlphaComposite.getInstance(3, 0.8f));
                    g2d.setColor(new Color(56, 53, 46, 238));
                    g2d.fill(roundedRectangle);
                    g2d.setColor(Color.white);
                    super.paintComponent(g);
                }
            }).setBounds(56, 10, 150, 18);
            this.labLingName.setForeground(Color.WHITE);
            this.labLingName.setOpaque(false);
            this.labLingName.setBorder(BorderFactory.createLineBorder(Color.red));
            this.labLingName.setFont(new Font("微软雅黑", 0, 13));
            this.labLingName.setBorder(BorderFactory.createEmptyBorder());
            this.labLingName.setCaretColor(Color.darkGray);
            this.add(this.labLingName);
            (this.btnRelease = new LingbaoPagingBtn("inkImg/button/19.png", 1, 999, "改", UIUtils.White)).setBounds(235, 435, 18, 18);
            this.add(this.btnRelease);
            this.labLingName.setBounds(120, 80, 100, 18);
            this.btnRelease.setBounds(230, 80, 18, 18);
            this.setPreferredSize(new Dimension(538, 469));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (this.labQuality = new JLabel()).setBounds(183, 218, 64, 17);
            this.labQuality.setForeground(Color.ORANGE);
            this.labQuality.setFont(new Font("微软雅黑", 0, 12));
            this.add(this.labQuality);
            (this.labCurrentlel = new JLabel()).setBounds(180, 250, 68, 16);
            this.labCurrentlel.setForeground(Color.WHITE);
            this.labCurrentlel.setFont(new Font("微软雅黑", 0, 14));
            this.add(this.labCurrentlel);
            (this.labLingbao = new JLabel()).setBounds(90, 97, 134, 154);
            this.add(this.labLingbao);
            (this.btnDelete = new LingbaoPagingBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY88, 5, "删 除", this.labLingName)).setBounds(107, 297, 59, 24);
            this.add(this.btnDelete);
            (this.btnBreak = new LingbaoPagingBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY88, 10, "突 破", this.labLingName)).setBounds(190, 297, 59, 24);
            this.add(this.btnBreak);
            this.labLingName.setBounds(120, 80, 100, 18);
            this.btnRelease.setBounds(230, 80, 18, 18);
            (this.btnTransformation = new LingbaoPagingBtn("inkImg/button1/B30.png", 1, 4, "转换")).setBounds(465, 162, 34, 17);
            this.add(this.btnTransformation);
            (this.labAttribute = new JLabel()).setBounds(298, 75, 194, 74);
            this.add(this.labAttribute);
            (this.labResistance = new JLabel()).setBounds(353, 164, 116, 16);
            this.labResistance.setForeground(Color.YELLOW);
            this.labResistance.setFont(new Font("微软雅黑", 0, 14));
            this.add(this.labResistance);
            (this.btnGevPal = new LingbaoPagingBtn("inkImg/button1/B21.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, 111, "装备伙伴")).setBounds(143, 47, 80, 30);
            this.add(this.btnGevPal);
            for (int i = 0; i < 5; ++i) {
                (this.RuneListLabel[i] = new JLabel()).addMouseListener(new LingFaFuSkill(true, i));
                this.RuneListLabel[i].setIcon(this.fushiimg);
                (this.SkillsListLabel[i] = new JLabel()).addMouseListener(new LingFaFuSkill(false, i));
                this.SkillsListLabel[i].setIcon(this.skillimg);
                if (this.Flag < 5) {
                    this.getClass();
                    if (true == true) {
                        if (i == 4) {
                            this.RuneListLabel[i].setBounds(312 + this.Flag * 37, 215, 27, 27);
                        }
                        else {
                            this.RuneListLabel[i].setBounds(312 + this.Flag * 37, 215, 27, 27);
                        }
                        this.SkillsListLabel[i].setBounds(310 + this.Flag * 39, 284, 28, 28);
                        ++this.Flag;
                        this.add(this.RuneListLabel[i]);
                        this.add(this.SkillsListLabel[i]);
                    }
                }
            }
            (this.btnLast1 = new LingbaoPagingBtn("inkImg/button/10.png", 1, 0, null)).setBounds(215, 435, 20, 21);
            this.add(this.btnLast1);
            (this.btnNext1 = new LingbaoPagingBtn("inkImg/button/9.png", 1, 1, null)).setBounds(235, 435, 20, 21);
            this.add(this.btnNext1);
            (this.btnLast2 = new LingbaoPagingBtn("inkImg/button/10.png", 1, 2, null)).setBounds(447, 435, 20, 21);
            this.add(this.btnLast2);
            (this.btnNext2 = new LingbaoPagingBtn("inkImg/button/9.png", 1, 3, null)).setBounds(467, 435, 20, 21);
            this.add(this.btnNext2);
            for (int i = 0; i < 8; ++i) {
                this.LingbaoListLabel[i] = new JLabel();
                this.MagicListLabel[i] = new JLabel();
                if (this.Flag1 < 4 && this.count1 == 1) {
                    this.LingbaoListLabel[i].setBounds(75 + this.Flag1 * 51, 333, 48, 48);
                    this.LingbaoListLabel[i].addMouseListener(new LingFaFanYeMouslisten(true, i));
                    this.MagicListLabel[i].setBounds(306 + this.Flag1 * 51, 333, 48, 48);
                    this.MagicListLabel[i].addMouseListener(new LingFaFanYeMouslisten(false, i));
                    ++this.Flag1;
                    this.add(this.LingbaoListLabel[i]);
                    this.add(this.MagicListLabel[i]);
                }
                if (this.Flag1 < 4 && this.count1 == 2) {
                    this.LingbaoListLabel[i].setBounds(75 + this.Flag1 * 51, 381, 48, 48);
                    this.LingbaoListLabel[i].addMouseListener(new LingFaFanYeMouslisten(true, i));
                    this.MagicListLabel[i].setBounds(306 + this.Flag1 * 51, 381, 48, 48);
                    this.MagicListLabel[i].addMouseListener(new LingFaFanYeMouslisten(false, i));
                    ++this.Flag1;
                    this.add(this.LingbaoListLabel[i]);
                    this.add(this.MagicListLabel[i]);
                }
                else if (this.Flag1 == 4) {
                    this.Flag1 = 0;
                    ++this.count1;
                }
            }
        }
        else {
            this.setPreferredSize(new Dimension(512, 496));
            this.setLayout(null);
            this.setBackground(Color.BLACK);
            (this.labLingName = new JTextField() {
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D)g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0.0, 0.0, (double)this.getWidth(), (double)this.getHeight(), 7.0, 7.0);
                    g2d.setComposite(AlphaComposite.getInstance(3, 0.8f));
                    g2d.setColor(new Color(101, 56, 9, 200));
                    g2d.fill(roundedRectangle);
                    g2d.setColor(Color.white);
                    super.paintComponent(g);
                }
            }).setBounds(56, 10, 150, 18);
            this.labLingName.setForeground(Color.WHITE);
            this.labLingName.setOpaque(false);
            this.labLingName.setBorder(BorderFactory.createLineBorder(Color.red));
            this.labLingName.setFont(new Font("微软雅黑", 0, 13));
            this.labLingName.setBorder(BorderFactory.createEmptyBorder());
            this.labLingName.setCaretColor(Color.darkGray);
            this.add(this.labLingName);
            (this.btnRelease = new LingbaoPagingBtn("inkImg/hongmu/btn-small-1-bg.png", 1, 999, "改", UIUtils.COLOR_BTNPUTONG)).setBounds(235, 435, 178, 17);
            this.add(this.btnRelease);
            this.labLingName.setBounds(100, 100, 100, 18);
            this.btnRelease.setBounds(200, 100, 18, 18);
            (this.labQuality = new JLabel()).setBounds(153, 234, 64, 17);
            this.labQuality.setForeground(Color.ORANGE);
            this.labQuality.setFont(new Font("微软雅黑", 0, 12));
            this.add(this.labQuality);
            (this.labCurrentlel = new JLabel()).setBounds(162, 266, 68, 16);
            this.labCurrentlel.setForeground(Color.WHITE);
            this.labCurrentlel.setFont(new Font("微软雅黑", 0, 14));
            this.add(this.labCurrentlel);
            (this.labLingbao = new JLabel()).setBounds(90, 97, 134, 154);
            this.add(this.labLingbao);
            (this.btnDelete = new LingbaoPagingBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, 5, "删 除")).setBounds(82, 309, 60, 26);
            this.add(this.btnDelete);
            (this.btnBreak = new LingbaoPagingBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, 10, "突 破")).setBounds(165, 309, 60, 26);
            this.add(this.btnBreak);
            (this.btnTransformation = new LingbaoPagingBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, 4, "转换")).setBounds(435, 177, 34, 17);
            this.add(this.btnTransformation);
            (this.labAttribute = new JLabel()).setBounds(278, 95, 194, 74);
            this.add(this.labAttribute);
            (this.labResistance = new JLabel()).setBounds(324, 177, 116, 16);
            this.labResistance.setForeground(Color.YELLOW);
            this.labResistance.setFont(new Font("微软雅黑", 0, 14));
            this.add(this.labResistance);
            (this.btnGevPal = new LingbaoPagingBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_YELLOW, UIUtils.TEXT_HY88, 111, "装备伙伴")).setBounds(110, 60, 80, 30);
            this.add(this.btnGevPal);
            for (int i = 0; i < 5; ++i) {
                (this.RuneListLabel[i] = new JLabel()).addMouseListener(new LingFaFuSkill(true, i));
                this.RuneListLabel[i].setIcon(this.fushiimg);
                (this.SkillsListLabel[i] = new JLabel()).addMouseListener(new LingFaFuSkill(false, i));
                this.SkillsListLabel[i].setIcon(this.skillimg);
                if (this.Flag < 5) {
                    this.getClass();
                    if (true == true) {
                        if (i == 4) {
                            this.RuneListLabel[i].setBounds(288 + this.Flag * 37, 230, 26, 26);
                        }
                        else {
                            this.RuneListLabel[i].setBounds(287 + this.Flag * 37, 230, 26, 26);
                        }
                        this.SkillsListLabel[i].setBounds(284 + this.Flag * 39, 299, 28, 28);
                        ++this.Flag;
                        this.add(this.RuneListLabel[i]);
                        this.add(this.SkillsListLabel[i]);
                    }
                }
            }
            (this.btnLast1 = new LingbaoPagingBtn("img/xy2uiimg/29_png.button.btn_8.png", 1, 0, null)).setBounds(215, 449, 20, 21);
            this.add(this.btnLast1);
            (this.btnNext1 = new LingbaoPagingBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 1, null)).setBounds(235, 449, 20, 21);
            this.add(this.btnNext1);
            (this.btnLast2 = new LingbaoPagingBtn("img/xy2uiimg/29_png.button.btn_8.png", 1, 2, null)).setBounds(447, 449, 20, 21);
            this.add(this.btnLast2);
            (this.btnNext2 = new LingbaoPagingBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 3, null)).setBounds(467, 449, 20, 21);
            this.add(this.btnNext2);
            for (int i = 0; i < 8; ++i) {
                this.LingbaoListLabel[i] = new JLabel();
                this.MagicListLabel[i] = new JLabel();
                if (this.Flag1 < 4 && this.count1 == 1) {
                    this.LingbaoListLabel[i].setBounds(48 + this.Flag1 * 51, 346, 48, 48);
                    this.LingbaoListLabel[i].addMouseListener(new LingFaFanYeMouslisten(true, i));
                    this.MagicListLabel[i].setBounds(282 + this.Flag1 * 51, 346, 48, 48);
                    this.MagicListLabel[i].addMouseListener(new LingFaFanYeMouslisten(false, i));
                    ++this.Flag1;
                    this.add(this.LingbaoListLabel[i]);
                    this.add(this.MagicListLabel[i]);
                }
                if (this.Flag1 < 4 && this.count1 == 2) {
                    this.LingbaoListLabel[i].setBounds(48 + this.Flag1 * 51, 397, 48, 48);
                    this.LingbaoListLabel[i].addMouseListener(new LingFaFanYeMouslisten(true, i));
                    this.MagicListLabel[i].setBounds(282 + this.Flag1 * 51, 397, 48, 48);
                    this.MagicListLabel[i].addMouseListener(new LingFaFanYeMouslisten(false, i));
                    ++this.Flag1;
                    this.add(this.LingbaoListLabel[i]);
                    this.add(this.MagicListLabel[i]);
                }
                else if (this.Flag1 == 4) {
                    this.Flag1 = 0;
                    ++this.count1;
                }
            }
        }
    }
    
    public void shuxingzhanshi(Lingbao lingbao) {
        if (lingbao != null) {
            this.lingimg = RoleLingFa.lingbaoimg(lingbao.getSkin(), -1, -1);
            this.labLingName.setText(lingbao.getBaoname());
            for (int i = 0; i < 5; ++i) {
                if (lingbao.getFusum() > i) {
                    this.RuneListLabel[i].setIcon(null);
                }
                else {
                    this.RuneListLabel[i].setIcon(this.fuskillopen(true));
                }
            }
            if (lingbao.getFushis() != null && !lingbao.getFushis().equals("")) {
                String[] v = lingbao.getFushis().split("\\|");
                for (int size = 0; size < v.length; ++size) {
                    Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(v[size]));
                    if (goodstable != null) {
                        ImageIcon imageIcon = new ImageIcon("img/lingbao/msg/" + goodstable.getSkin() + ".png");
                        this.RuneListLabel[size].setIcon(imageIcon);
                    }
                }
            }
            for (int i = 0; i < 5; ++i) {
                if (lingbao.getSkillsum() > i) {
                    this.SkillsListLabel[i].setIcon(null);
                }
                else {
                    this.SkillsListLabel[i].setIcon(this.fuskillopen(false));
                }
            }
            String[] v = null;
            if (lingbao.getSkills() != null) {
                v = lingbao.getSkills().split("\\|");
            }
            if (v != null) {
                for (int size = 0; size < v.length; ++size) {
                    ImageIcon imageIcon2 = this.skillskin(v[size].split("=")[0]);
                    imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(33, 33, 1));
                    this.SkillsListLabel[size].setIcon(imageIcon2);
                }
            }
            this.labQuality.setText(lingbao.getBaoquality());
            this.labCurrentlel.setText(lingbao.getLingbaolvl() + "级");
            int size = (int)(lingbao.getLingbaoexe().doubleValue() / (double)ExpUtil.LFExp(lingbao.getLingbaolvl().intValue()) * 65.0);
            if (size > 65) {
                size = 65;
            }
            this.s = size / 10;
            this.w = size % 10;
            this.labResistance.setText(lingbao.getKangxing());
            (this.box = new ChatBox()).addText("#G契合度" + lingbao.getLingbaoqihe() + " 活跃度" + lingbao.hyz() + "#W(" + lingbao.getBaoactive() + ")", 210);
            this.box.addText("#G速度" + lingbao.spz() + "#W(" + lingbao.getBaospeed() + ") #G援助" + UserData.xiaoshu(lingbao.zyz() * 100.0) + "#W(" + UserData.xiaoshu(lingbao.getAssistance() * 100.0) + ")", 195);
            this.box.addText("#G回复" + UserData.xiaoshu(lingbao.replyz() * 100.0) + "#W(" + UserData.xiaoshu(Double.parseDouble(lingbao.getBaoreply()) * 100.0) + ") #G落宝" + UserData.xiaoshu(lingbao.shotz() * 100.0) + "#W(" + UserData.xiaoshu(lingbao.getBaoshot() * 100.0) + ")", 195);
            this.box.addText("#G伤害" + UserData.xiaoshu(lingbao.apz() * 100.0) + "#W(" + UserData.xiaoshu(Double.parseDouble(lingbao.getBaoap()) * 100.0) + ") #G抗落宝" + UserData.xiaoshu(lingbao.resistshopz() * 100.0) + "#W(" + UserData.xiaoshu(lingbao.getResistshop() * 100.0) + ")", 195);
        }
        else {
            this.lingimg = null;
            this.box.removemsg();
            for (int i = 0; i < 5; ++i) {
                this.RuneListLabel[i].setIcon(this.fuskillopen(true));
                this.SkillsListLabel[i].setIcon(this.fuskillopen(false));
            }
        }
    }
    
    public ImageIcon fuskillopen(boolean type) {
        return type ? this.fushiimg : this.skillimg;
    }
    
    public ImageIcon skillskin(String skin) {
        return new ImageIcon("img/lingbao/skill/" + skin + ".png");
    }
    
    public void sj(Lingbao lingbao) {
        if (lingbao != null) {
            Lingbao lingbao2 = RoleLingFa.getRoleLingFa().getChoseBao();
            if (lingbao2 == lingbao) {
                this.labCurrentlel.setText(lingbao.getLingbaolvl() + "级");
                int size = (int)(lingbao.getLingbaoexe().doubleValue() / (double)ExpUtil.LFExp(lingbao.getLingbaolvl().intValue()) * 65.0);
                if (size > 65) {
                    size = 65;
                }
                this.s = size / 10;
                this.w = size % 10;
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B236.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 538, 469, this);
            if (this.lingimg != null) {
                for (int i = 0; i < this.s; ++i) {
                    g.drawImage(this.dian.getImage(), 185 + 10 * i, 277, 10, 7, null);
                }
                g.drawImage(this.dian.getImage(), 185 + this.s * 10, 277, this.w, 7, null);
                g.drawImage(this.lingimg.getImage(), 125, 95, 120, 120, this);
                Graphics g2 = g.create(304, 77, this.box.getWidth(), this.box.getHeight());
                this.box.paint(g2);
                g2.dispose();
                Graphics g3 = g.create(this.boxx, this.boxy, this.boxw, this.boxh);
                this.zsbox.paint(g3);
                g3.dispose();
            }
            RoleLingFa.getRoleLingFa().drawL(g, 75, 333);
            RoleLingFa.getRoleLingFa().drawF(g, 306, 333);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/灵宝查看.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 512, 496, this);
            if (this.lingimg != null) {
                for (int i = 0; i < this.s; ++i) {
                    g.drawImage(this.dian.getImage(), 162 + 10 * i, 292, 10, 3, null);
                }
                g.drawImage(this.dian.getImage(), 162 + this.s * 10, 292, this.w, 3, null);
                g.drawImage(this.lingimg.getImage(), 95, 95, 120, 120, this);
                Graphics g2 = g.create(280, 90, this.box.getWidth(), this.box.getHeight());
                this.box.paint(g2);
                g2.dispose();
                Graphics g3 = g.create(this.boxx, this.boxy, this.boxw, this.boxh);
                this.zsbox.paint(g3);
                g3.dispose();
            }
            RoleLingFa.getRoleLingFa().drawL(g, 48, 346);
            RoleLingFa.getRoleLingFa().drawF(g, 282, 346);
        }
    }
    
    public void zsskill(String v, boolean type, int x, int y) {
        this.zsbox.removemsg();
        String[] vs = v.split("=");
        this.zsbox.addText("#W" + vs[0], 250);
        this.zsbox.addText("#Y【组合技能】: " + vs[1] + "人合技", 250);
        this.zsbox.addText("#Y【组合类型】: 攻击类", 250);
        if (type) {
            this.zsbox.addText("#Y【目标数量】: " + (Integer.parseInt(vs[1]) + 1), 250);
        }
        else {
            this.zsbox.addText("#Y【目标数量】: " + vs[1], 250);
        }
        this.zsbox.addText("#G目标剩余法量越低伤害越高", 250);
        this.boxw = this.zsbox.getWidth();
        this.boxh = this.zsbox.getHeight();
        this.boxx = x - this.zsbox.getWidth() / 2;
        this.boxy = y - this.boxh - 5;
    }
    
    public void zsfs(Goodstable goodstable) {
        this.zsbox.removemsg();
        this.boxx = 0;
        this.boxy = 0;
        this.boxw = 0;
        this.boxh = 0;
    }
    
    public void removemsg() {
        this.zsbox.removemsg();
    }
    
    public JLabel getLabLingbao() {
        return this.labLingbao;
    }
    
    public void setLabLingbao(JLabel labLingbao) {
        this.labLingbao = labLingbao;
    }
    
    public JLabel getLabQuality() {
        return this.labQuality;
    }
    
    public void setLabQuality(JLabel labQuality) {
        this.labQuality = labQuality;
    }
    
    public JLabel getLabCurrentlel() {
        return this.labCurrentlel;
    }
    
    public void setLabCurrentlel(JLabel labCurrentlel) {
        this.labCurrentlel = labCurrentlel;
    }
    
    public JLabel getLabAttribute() {
        return this.labAttribute;
    }
    
    public void setLabAttribute(JLabel labAttribute) {
        this.labAttribute = labAttribute;
    }
    
    public JLabel getLabResistance() {
        return this.labResistance;
    }
    
    public void setLabResistance(JLabel labResistance) {
        this.labResistance = labResistance;
    }
    
    public JLabel[] getRuneListLabel() {
        return this.RuneListLabel;
    }
    
    public void setRuneListLabel(JLabel[] runeListLabel) {
        this.RuneListLabel = runeListLabel;
    }
    
    public JLabel[] getSkillsListLabel() {
        return this.SkillsListLabel;
    }
    
    public void setSkillsListLabel(JLabel[] skillsListLabel) {
        this.SkillsListLabel = skillsListLabel;
    }
    
    public JLabel[] getLingbaoListLabel() {
        return this.LingbaoListLabel;
    }
    
    public void setLingbaoListLabel(JLabel[] lingbaoListLabel) {
        this.LingbaoListLabel = lingbaoListLabel;
    }
    
    public JLabel[] getMagicListLabel() {
        return this.MagicListLabel;
    }
    
    public void setMagicListLabel(JLabel[] magicListLabel) {
        this.MagicListLabel = magicListLabel;
    }
    
    public ChatBox getZsbox() {
        return this.zsbox;
    }
    
    public void setZsbox(ChatBox zsbox) {
        this.zsbox = zsbox;
    }
    
    public JTextField getLabLingName() {
        return this.labLingName;
    }
    
    public void setLabLingName(JTextField labLingName) {
        this.labLingName = labLingName;
    }
    
    public LingbaoPagingBtn getBtnRelease() {
        return this.btnRelease;
    }
    
    public void setBtnRelease(LingbaoPagingBtn btnRelease) {
        this.btnRelease = btnRelease;
    }
}
