
package org.come.Jpanel;

import com.tool.btn.FormsOnOffBtn;
import com.tool.btn.GemMakeBtn;
import com.tool.btn.PetOperationPanelBtn;
import com.tool.btn.goodbtn;
import com.tool.image.ImageMixDeal;
import com.tool.tcpimg.RichLabel;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.math.BigDecimal;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.come.Frame.ZhuFrame;
import org.come.bean.ConfigureBean;
import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;
import org.come.model.Configure;
import org.come.mouslisten.ChoseAlchemyGoodsMouslisten;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.until.*;

public class AlchemyJpanel extends JPanel {
    private JList<String> listpet;
    private DefaultListModel<String> listModel;
    private Color fontcolor;
    private JScrollPane jScrollPane;
    private PetOperationPanelBtn btnalchemy,ly,lf;
    private JLabel labRefined;
    private JLabel[] GoodsListLabel = new JLabel[24];
    private goodbtn[] btnrights;
    private final ChoseAlchemyGoodsMouslisten[] GoodsListListen = new ChoseAlchemyGoodsMouslisten[24];
    private int Flag = 0;
    private int count = 1;
    private ImageIcon icon;
    private int xz;
    // 滚动条
    private JLabel labgundong;
    private boolean shoptext;
    public ImageIcon iconx2;
    private int goodPosition;
    private RichLabel richLabel;

    private RichLabel namen;
    public AlchemyJpanel() throws Exception {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = item.get(new BigDecimal(1));
        this.shoptext = false;
        this.iconx2 = new ImageIcon("resource/jiuUI/jiuuijiemian/border_quack.png");
        if(MyIsif.getStyle().equals("水墨")) {
            labgundong = new JLabel(new ImageIcon("inkImg/button/23.png"));
            this.labgundong.setBounds(197, 288, 0, 0);
            this.add(this.labgundong);
            this.setPreferredSize(new Dimension(559, 471));
            this.setLayout(null);
            this.setOpaque(false);
            // 关闭按钮事件
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 17);
            offBtn.setBounds(559 - 37, 10, 25, 25);
            this.add(offBtn);
            // 添加组件
            // 宠物列表对象
            listModel = new DefaultListModel<String>();
            // labgundong.setBounds(123, 268, 17, 152);
            // this.add(labgundong);
            (this.richLabel = new RichLabel("", UIUtils.TEXT_FONT2, 255)).setBounds(380, 65, this.richLabel.getWidth(), this.richLabel.getHeight());
            this.add((Component)this.richLabel);
            (this.namen = new RichLabel("", UIUtils.TEXT_NAME_FONT, 255)).setBounds(240, 77, this.richLabel.getWidth(), this.richLabel.getHeight());
            this.add((Component)this.namen);
            // 宠物列表
            listpet = new JList<String>();
            listpet.setOpaque(false);
            listpet.setSelectionBackground(new Color(33, 42, 52));
            fontcolor = Color.GREEN;
            listpet.setSelectionForeground(fontcolor);
            listpet.setForeground(fontcolor);
            listpet.setFont(new Font("微软雅黑", 0, 14));
            listpet.setBackground(UIUtils.Color_BACK); // 设置列表框为透明背景
            listpet.setOpaque(false);
            listpet.setModel(listModel);
            listpet.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (listpet.getSelectedIndex() != -1) {
                        UserMessUntil.setChosePetMes(UserMessUntil.getPetListTable().get(listpet.getSelectedIndex()));
                        PetAddPointMouslisten.showPetValue();
                        if (UserMessUntil.getChosePetMes() != null) {
                            final StringBuffer buffer = new StringBuffer();
                            buffer.append("#r#c8EDEFF炼妖次数:#R");
                            buffer.append(UserMessUntil.getChosePetMes().getAlchemynum());
                            buffer.append("/#R");
                            buffer.append(configure.getLyssx());
                            buffer.append("#c8EDEFF");
                            String[] tss = null;
                            String[] lys = null;
                            if (UserMessUntil.getChosePetMes().getResistance() != null && !UserMessUntil.getChosePetMes().getResistance().equals("")) {
                                tss = UserMessUntil.getChosePetMes().getResistance().split("\\|");
                            }
                            if (UserMessUntil.getChosePetMes().getLyk() != null && !UserMessUntil.getChosePetMes().getLyk().equals("")) {
                                lys = UserMessUntil.getChosePetMes().getLyk().split("\\|");
                            }
                            if (tss != null) {
                                double t = 0.0;
                                double l = 0.0;
                                int i = 0;
                                while (i < tss.length) {
                                    final String[] v = tss[i].split("=");
                                    t = Double.parseDouble(v[1]);
                                    final String qz = v[0] + "=";
                                    l = 0.0;
                                    if (lys != null) {
                                        int j = 0;
                                        while (j < lys.length) {
                                            if (lys[j] != null && lys[j].startsWith(qz)) {
                                                l = Double.parseDouble(lys[j].split("=")[1]);
                                                l = ((l < 45.0) ? l : 45.0);
                                                lys[j] = null;
                                                break;
                                            } else {
                                                if (lys[j] != null && qz.startsWith("抗物理") && lys[j].startsWith("物理吸收率")) {
                                                    l = Double.parseDouble(lys[j].split("=")[1]);
                                                    l = ((l < 45.0) ? l : 45.0);
                                                    lys[j] = null;
                                                    break;
                                                } else {
                                                    ++j;
                                                }
                                            }
                                        }
                                    }
                                    buffer.append("#r");
                                    buffer.append(v[0]);
                                    buffer.append(": ");
                                    buffer.append("#r");
                                    buffer.append(t + l);
                                    buffer.append("(#W");
                                    buffer.append(t);
                                    buffer.append("#c8EDEFF+");
                                    buffer.append(l);
                                    buffer.append(")#c8EDEFF");
                                    buffer.append("#c00FFFF[");
                                    buffer.append("75");
                                    buffer.append("]#c8EDEFF");
                                    ++i;
                                }
                            }
                            if (lys != null) {
                                int size = 0;
                                int k = 0;
                                while (k < lys.length) {
                                    if (lys[k] != null) {
                                        buffer.append((size % 2 == 0) ? "#r" : "");
                                        final String[] kx = lys[k].split("=");
                                        buffer.append(kx[0]);
                                        buffer.append(": ");
                                        buffer.append((Double.valueOf(kx[1]).doubleValue() > 75.0) ? 75.0 : Double.valueOf(kx[1]).doubleValue());
                                        buffer.append("#r");
                                        ++size;
                                    }
                                    ++k;
                                }
                            }
                            AlchemyJpanel.this.richLabel.setTextSize(buffer.toString(), 255);
                        }
                    }
                }


                @Override
                public void mouseExited(MouseEvent e) {

                    ZhuFrame.getZhuJpanel().pettext();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (UserMessUntil.getChosePetMes() != null) {
                        ZhuFrame.getZhuJpanel().creatpettext(UserMessUntil.getChosePetMes());
                    }
                }

                @Override
                public void mouseClicked(MouseEvent e) {

                    // 打开了窗体
                    if (e.isMetaDown()) {// 检测鼠标右键单击
                        FormsManagement.HideForm(17);
                    }

                }
            });

            // 宠物列表滚动条
            jScrollPane = new JScrollPane(listpet);
            jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            jScrollPane.getViewport().setOpaque(false);
            jScrollPane.setOpaque(false);
            jScrollPane.setBounds(23 + 21, 250 + 19, 117 + 54, 185);
            // jScrollPane.setBorder(BorderFactory.createLineBorder(new Color(31, 7,
            // 8)));
            jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            this.add(jScrollPane);

            // 放置炼化石
            labRefined = new JLabel();
            labRefined.setBounds(260, 93, 50, 51);
            labRefined.addMouseListener(new ChoseAlchemyGoodsMouslisten(24));
            this.add(labRefined);

            // 炼妖按钮
            btnalchemy = new PetOperationPanelBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16,"炼妖");
            btnalchemy.setBounds(202 + 54, 160, 59, 24);
            this.add(btnalchemy);
//
//            ly = new PetOperationPanelBtn("inkImg/button/sm-ly-1.png", 1, 100, this);
//            ly.setBounds(46, 14, 75, 33);
//            this.add(ly);
//            lf = new PetOperationPanelBtn("inkImg/button/sm-lf-2.png", 1, 101, this);
//            lf.setBounds(46+78, 18, 75, 33);
//            this.add(lf);

            // 存放物品的表格1~24
            for (int i = 0; i < 24; i++) {
                GoodsListLabel[i] = new JLabel();
                GoodsListListen[i] = new ChoseAlchemyGoodsMouslisten(i);
                GoodsListLabel[i].addMouseListener(GoodsListListen[i]);
                if ((Flag < 6) && (count == 1)) {
                    GoodsListLabel[i].setBounds(215 + Flag * 50, 250, 49, 49);
                    Flag++;// 用来标记行s
                    this.add(GoodsListLabel[i]);
                }
                if ((Flag < 6) && (count == 2)) {
                    GoodsListLabel[i].setBounds(215 + Flag * 50, 299, 49, 49);
                    Flag++;// 用来标记行
                    this.add(GoodsListLabel[i]);
                }
                if ((Flag < 6) && (count == 3)) {
                    GoodsListLabel[i].setBounds(215 + Flag * 50, 348, 49, 49);
                    Flag++;// 用来标记行
                    this.add(GoodsListLabel[i]);
                }
                if ((Flag < 6) && (count == 4)) {
                    GoodsListLabel[i].setBounds(215 + Flag * 50, 397, 49, 49);
                    Flag++;// 用来标记行
                    this.add(GoodsListLabel[i]);

                } else if (Flag == 6) {
                    Flag = 0;
                    count++;
                }
            }

            // 背包四个页数的按钮
            btnrights = new goodbtn[6];
            for (int i = 0; i < btnrights.length; i++) {
                btnrights[i] = new goodbtn("inkImg/button1/C0" + (i+1) + ".png", 0, this, i);
                btnrights[i].setBounds(526, 248 + i * 35, 35, 31);
                this.add(btnrights[i]);
            }
        }else {
            labgundong = new JLabel(new ImageIcon("img/xy2uiimg/gundongtiao_副本.png"));
            this.labgundong.setBounds(165, 298, 17, 148);
            this.add(this.labgundong);
            this.setPreferredSize(new Dimension(535, 497));
            this.setLayout(null);
            this.setOpaque(false);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 17);
            offBtn.setBounds(465, 0, 25, 25);
            this.add(offBtn);
            (this.richLabel = new RichLabel("", UIUtils.TEXT_FONT2, 255)).setBounds(329, 29, this.richLabel.getWidth(), this.richLabel.getHeight());
            this.add((Component)this.richLabel);
            (this.namen = new RichLabel("", UIUtils.TEXT_NAME_FONT, 255)).setBounds(240, 77, this.richLabel.getWidth(), this.richLabel.getHeight());
            this.add((Component)this.namen);
            this.listModel = new DefaultListModel<>();
            this.listpet = new JList<>();
            this.listpet.setOpaque(false);
            this.listpet.setSelectionBackground(new Color(33, 42, 52));
            this.fontcolor = Color.GREEN;
            this.listpet.setSelectionForeground(this.fontcolor);
            this.listpet.setForeground(this.fontcolor);
            this.listpet.setFont(new Font("微软雅黑", 0, 14));
            this.listpet.setBackground(UIUtils.Color_BACK);
            this.listpet.setOpaque(false);
            this.listpet.setModel(this.listModel);
            this.listpet.addMouseListener(new MouseListener() {
                public void mouseReleased(MouseEvent e) {
                }

                public void mousePressed(MouseEvent e) {
                    if (AlchemyJpanel.this.listpet.getSelectedIndex() != -1) {
                        UserMessUntil.setChosePetMes(UserMessUntil.getPetListTable().get(AlchemyJpanel.this.listpet.getSelectedIndex()));
                        PetAddPointMouslisten.showPetValue();
                        if (UserMessUntil.getChosePetMes() != null) {
                            final StringBuffer buffer = new StringBuffer();
                            buffer.append("#r#c8EDEFF炼妖次数:#R");
                            buffer.append(UserMessUntil.getChosePetMes().getAlchemynum());
                            buffer.append("/#R");
                            buffer.append(configure.getLyssx());
                            buffer.append("#c8EDEFF");
                            String[] tss = null;
                            String[] lys = null;
                            if (UserMessUntil.getChosePetMes().getResistance() != null && !UserMessUntil.getChosePetMes().getResistance().equals("")) {
                                tss = UserMessUntil.getChosePetMes().getResistance().split("\\|");
                            }
                            if (UserMessUntil.getChosePetMes().getLyk() != null && !UserMessUntil.getChosePetMes().getLyk().equals("")) {
                                lys = UserMessUntil.getChosePetMes().getLyk().split("\\|");
                            }
                            if (tss != null) {
                                double t = 0.0;
                                double l = 0.0;
                                int i = 0;
                                while (i < tss.length) {
                                    final String[] v = tss[i].split("=");
                                    t = Double.parseDouble(v[1]);
                                    final String qz = v[0] + "=";
                                    l = 0.0;
                                    if (lys != null) {
                                        int j = 0;
                                        while (j < lys.length) {
                                            if (lys[j] != null && lys[j].startsWith(qz)) {
                                                l = Double.parseDouble(lys[j].split("=")[1]);
                                                l = ((l < 45.0) ? l : 45.0);
                                                lys[j] = null;
                                                break;
                                            } else {
                                                if (lys[j] != null && qz.startsWith("抗物理") && lys[j].startsWith("物理吸收率")) {
                                                    l = Double.parseDouble(lys[j].split("=")[1]);
                                                    l = ((l < 45.0) ? l : 45.0);
                                                    lys[j] = null;
                                                    break;
                                                } else {
                                                    ++j;
                                                }
                                            }
                                        }
                                    }
                                    buffer.append("#r");
                                    buffer.append(v[0]);
                                    buffer.append(": ");
                                    buffer.append("#r");
                                    buffer.append(t + l);
                                    buffer.append("(#W");
                                    buffer.append(t);
                                    buffer.append("#c8EDEFF+");
                                    buffer.append(l);
                                    buffer.append(")#c8EDEFF");
                                    buffer.append("#c00FFFF[");
                                    buffer.append("75");
                                    buffer.append("]#c8EDEFF");
                                    ++i;
                                }
                            }
                            if (lys != null) {
                                int size = 0;
                                int k = 0;
                                while (k < lys.length) {
                                    if (lys[k] != null) {
                                        buffer.append((size % 2 == 0) ? "#r" : "");
                                        final String[] kx = lys[k].split("=");
                                        buffer.append(kx[0]);
                                        buffer.append(": ");
                                        buffer.append((Double.valueOf(kx[1]).doubleValue() > 75.0) ? 75.0 : Double.valueOf(kx[1]).doubleValue());
                                        buffer.append("#r");
                                        ++size;
                                    }
                                    ++k;
                                }
                            }
                            AlchemyJpanel.this.richLabel.setTextSize(buffer.toString(), 255);
                            AlchemyJpanel.this.richLabel.setBorder(BorderFactory.createLineBorder(Color.red));
                        }
                    }

                }

                public void mouseExited(MouseEvent e) {
                    ZhuFrame.getZhuJpanel().pettext();
                }

                public void mouseEntered(MouseEvent e) {
                    if (UserMessUntil.getChosePetMes() != null) {
                        ZhuFrame.getZhuJpanel().creatpettext(UserMessUntil.getChosePetMes());
                    }

                }

                public void mouseClicked(MouseEvent e) {
                    if (e.isMetaDown()) {
                        FormsManagement.HideForm(17);
                    }

                }
            });
            this.jScrollPane = new JScrollPane(this.listpet);
            this.jScrollPane.setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(23, 279, 160, 187);
            jScrollPane.setBorder(BorderFactory.createLineBorder(new Color(31, 7,8)));
            this.jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            this.add(this.jScrollPane);
            this.labRefined = new JLabel();
            this.labRefined.setBounds(219, 65, 50, 51);
            this.labRefined.addMouseListener(new ChoseAlchemyGoodsMouslisten(24));
            this.add(this.labRefined);
            this.btnalchemy = new PetOperationPanelBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "炼妖");
            this.btnalchemy.setBounds(230, 180, 60, 26);
            this.add(this.btnalchemy);
//            ly = new PetOperationPanelBtn("inkImg/button/hm-ly-1.png", 1, 100, this);
//            ly.setBounds(46, 14, 75, 33);
//            this.add(ly);
//            lf = new PetOperationPanelBtn("inkImg/button/hm-lf-2.png", 1, 101, this);
//            lf.setBounds(46+78, 14, 75, 33);
//            this.add(lf);


            for(int i = 0; i < 24; ++i) {
                this.GoodsListLabel[i] = new JLabel();
                this.GoodsListListen[i] = new ChoseAlchemyGoodsMouslisten(i);
                this.GoodsListLabel[i].addMouseListener(this.GoodsListListen[i]);
                if (this.Flag < 6 && this.count == 1) {
                    this.GoodsListLabel[i].setBounds(157 + this.Flag * 51, 234, 49, 49);
                    ++this.Flag;
                    this.add(this.GoodsListLabel[i]);
                }

                if (this.Flag < 6 && this.count == 2) {
                    this.GoodsListLabel[i].setBounds(157 + this.Flag * 51, 285, 49, 49);
                    ++this.Flag;
                    this.add(this.GoodsListLabel[i]);
                }

                if (this.Flag < 6 && this.count == 3) {
                    this.GoodsListLabel[i].setBounds(157 + this.Flag * 51, 336, 49, 49);
                    ++this.Flag;
                    this.add(this.GoodsListLabel[i]);
                }

                if (this.Flag < 6 && this.count == 4) {
                    this.GoodsListLabel[i].setBounds(157 + this.Flag * 51, 387, 49, 49);
                    ++this.Flag;
                    this.add(this.GoodsListLabel[i]);
                } else if (this.Flag == 6) {
                    this.Flag = 0;
                    ++this.count;
                }
            }

            // 背包六个页数的按钮
            btnrights = new goodbtn[6];

            for(int i = 0; i < this.btnrights.length; ++i) {
                btnrights[i] = new goodbtn("inkImg/hongmu/SBG.png", 0, this, i);
                this.btnrights[i].setBounds(502, 265 + i * 33, 24, 33);
                this.add(this.btnrights[i]);
            }
        }
    }
    public void xuanzhong(final Goodstable goodstable, final int shopPlace) {
        if (goodstable == null) {
            if (this.xz != -1) {
                this.GoodsListLabel[this.xz].setBorder(null);
            }
            this.xz = -1;
        }
        else {
            if (this.xz != -1) {
                this.GoodsListLabel[this.xz].setBorder(null);
            }
            this.xz = shopPlace;
            this.GoodsListLabel[this.xz].setBorder(BorderFactory.createLineBorder(Color.red));
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        this.jScrollPane.setBorder(BorderFactory.createLineBorder(Color.red));
//        this.jScrollPane.setBounds(23, 279, 160, 187);

        if(MyIsif.getStyle().equals("水墨")) {
            if (icon == null)
                icon = new ImageIcon("inkImg/background1/B256.png");
            // 炼妖
            g.drawImage(icon.getImage(), 0, 0, 559, 471, this);
            if (UserMessUntil.getChosePetMes() != null) {
                if (TestPetJpanel.part==null) {
                    TestPetJpanel.part=UserMessUntil.getChosePetMes().getPart();
                }
                TestPetJpanel.part.draw(g, 120, 190, 0, ImageMixDeal.userimg.getTime());
            }
            // 画物品的图片和数量
            GoodsListFromServerUntil.draw(g, 223, 250);
        }else {
            if (this.icon == null) {
//        		this.icon = new ImageIcon("img/xy2uiimg/109_png.xy2uiimg.alchemy_bg.png");
                this.icon = new ImageIcon("img/xy2uiimg/ss638.png");
            }

            g.drawImage(this.icon.getImage(), 0, 0, 535, 497, this);
            if (UserMessUntil.getChosePetMes() != null) {
                if (TestPetJpanel.part == null) {
                    TestPetJpanel.part = UserMessUntil.getChosePetMes().getPart();
                }

                TestPetJpanel.part.draw(g, 90, 210, 0, ImageMixDeal.userimg.getTime());
            }

            GoodsListFromServerUntil.draw(g, 195, 264);
            if (this.shoptext) {
                final int shop_x = this.goodPosition % 6;
                final int shop_y = this.goodPosition / 6;
                g.drawImage(this.iconx2.getImage(), 195 + shop_x * 51, 263 + shop_y * 51, (ImageObserver)this);
            }
            this.richLabel.setLocation(357,89);
        }
        if (this.listModel.size() > 8) {
            this.remove(this.labgundong);
        }
    }
    public void PaintingText(final int goodPosition) {
        this.goodPosition = goodPosition;
        this.shoptext = true;
    }

    public void ClearText() {
        this.shoptext = false;
    }
    public JList<String> getListpet() {
        return this.listpet;
    }

    public void setListpet(JList<String> listpet) {
        this.listpet = listpet;
    }

    public DefaultListModel<String> getListModel() {
        return this.listModel;
    }

    public void setListModel(DefaultListModel<String> listModel) {
        this.listModel = listModel;
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

    public PetOperationPanelBtn getBtnalchemy() {
        return this.btnalchemy;
    }

    public void setBtnalchemy(PetOperationPanelBtn btnalchemy) {
        this.btnalchemy = btnalchemy;
    }

    public JLabel getLabRefined() {
        return this.labRefined;
    }

    public void setLabRefined(JLabel labRefined) {
        this.labRefined = labRefined;
    }

    public JLabel[] getGoodsListLabel() {
        return this.GoodsListLabel;
    }

    public void setGoodsListLabel(JLabel[] goodsListLabel) {
        this.GoodsListLabel = goodsListLabel;
    }

    public int getFlag() {
        return this.Flag;
    }

    public void setFlag(int flag) {
        this.Flag = flag;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public goodbtn[] getBtnrights() {
        return this.btnrights;
    }

    public void setBtnrights(goodbtn[] btnrights) {
        this.btnrights = btnrights;
    }

    public ImageIcon getIconx2() {
        return this.iconx2;
    }

    public void setIconx2(final ImageIcon iconx2) {
        this.iconx2 = iconx2;
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

    /** 切换 */
    public void qh(final int p) {
        if(MyIsif.getStyle().equals("水墨")) {
            if (p == 100) {
                try {
                    ly.setIcons(CutButtonImage.cuts("inkImg/button/sm-ly-1.png"));
                    lf.setIcons(CutButtonImage.cuts("inkImg/button/sm-lf-2.png"));
                    ly.setBounds(46, 14, 75, 33);
                    lf.setBounds(46+78, 18, 75, 33);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    ly.setIcons(CutButtonImage.cuts("inkImg/button/sm-ly-2.png"));
                    lf.setIcons(CutButtonImage.cuts("inkImg/button/sm-lf-1.png"));
                    ly.setBounds(46, 18, 75, 33);
                    lf.setBounds(46+78, 14, 75, 33);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else {
            if (p == 100) {
                try {
                    this.ly.setIcons(CutButtonImage.cuts("inkImg/button/hm-ly-1.png"));
                    this.lf.setIcons(CutButtonImage.cuts("inkImg/button/hm-lf-2.png"));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else {
                try {
                    this.ly.setIcons(CutButtonImage.cuts("inkImg/button/hm-ly-2.png"));
                    this.lf.setIcons(CutButtonImage.cuts("inkImg/button/hm-lf-1.png"));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
