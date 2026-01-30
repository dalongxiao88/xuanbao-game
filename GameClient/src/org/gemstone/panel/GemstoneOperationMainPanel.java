package org.gemstone.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;
import org.gemstone.btn.GemstoneOperationBtn;

import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;

public class GemstoneOperationMainPanel extends JPanel
{
    private GemstoneOperationBtn syntherisCheckBtn;
    private GemstoneOperationBtn recastCheckBtn;
    private GemstoneOperationBtn appraisalCheckBtn;
    private GemstoneOperationBtn helpBtn;
    private GemstoneOperationBtn upBtn;
    private GemstoneOperationBtn downBtn;
    private int ys;
    private JLabel[] labels;
    private Goodstable[] goodsLabes;
    public int yss;
    private boolean shoptext;
    private int goodPosition;
    public static ImageIcon iconx2;
    private int typePanel;
    private GemstoneOperationMainCardPanel gemstoneOperationMainCardPanel;
    private ImageIcon iconBack;
    int num;
    
    public GemstoneOperationMainPanel() {
        this.goodsLabes = new Goodstable[2];
        this.typePanel = 1;
        this.num = 0;
        this.shoptext = false;
        class MListener extends MouseAdapter {
            private int p;

            public MListener(int p) {
                this.p = p;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Goodstable goodstable = GoodsListFromServerUntil.getGemstoneOperationSuit(GemstoneOperationMainPanel.this.yss, this.p);
                if (goodstable != null) {
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                GemstoneOperationMainPanel.this.ClearText();
                ZhuFrame.getZhuJpanel().cleargoodtext();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                for (int i = 0; i < GemstoneOperationMainPanel.this.labels.length; ++i) {
                    GemstoneOperationMainPanel.this.labels[i].setBorder(null);
                }
                ((JLabel)e.getSource()).setBorder(BorderFactory.createLineBorder(Color.yellow));
                Goodstable gemstoneOperationSuit = GoodsListFromServerUntil.getGemstoneOperationSuit(GemstoneOperationMainPanel.this.yss, this.p);
                if (gemstoneOperationSuit == null) {
                    return;
                }
                int numTen = 0;
                GemstoneOperationMainCardPanel gemstoneOperationMainCardPanel = GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel().getGemstoneOperationMainCardPanel();
                switch (GemstoneOperationMainPanel.this.typePanel) {
                    case 1: {
                        for (int j = 0; j < GemstoneOperationMainPanel.this.goodsLabes.length; ++j) {
                            if (GemstoneOperationMainPanel.this.goodsLabes[j] != null) {
                                ++numTen;
                                continue;
                            }
                            if (!(gemstoneOperationSuit.getType().equals(744L) || gemstoneOperationSuit.getType().equals(746L) || gemstoneOperationSuit.getType().equals(749L) || gemstoneOperationSuit.getType().equals(752L) || gemstoneOperationSuit.getType().equals(755L) || gemstoneOperationSuit.getType().equals(758L) || gemstoneOperationSuit.getType().equals(761L) || gemstoneOperationSuit.getType().equals(764L) || gemstoneOperationSuit.getType().equals(767L))) {
                                ZhuFrame.getZhuJpanel().addPrompt2("该宝石不能合成");
                                return;
                            }
                            int levelTwo = Integer.parseInt(GemstoneOperationMainPanel.cuttingString(gemstoneOperationSuit, 1));
                            if (levelTwo >= 10) {
                                ZhuFrame.getZhuJpanel().addPrompt2("宝石最高等级10级");
                                return;
                            }
                            if (GemstoneOperationMainPanel.this.goodsLabes[0] != null) {
                                int levelOne = Integer.parseInt(GemstoneOperationMainPanel.cuttingString(GemstoneOperationMainPanel.this.goodsLabes[0], 1));
                                if (levelOne == levelTwo) {
                                    GemstoneOperationMainPanel.this.changeIcon(gemstoneOperationSuit, j, gemstoneOperationMainCardPanel.getGemstoneOperationSynthesisPanel().getGemstoneNumber());
                                    break;
                                }
                                ZhuFrame.getZhuJpanel().addPrompt2("两颗宝石等级不一致");
                                break;
                            }
                            if (GemstoneOperationMainPanel.this.goodsLabes[1] != null) {
                                int levelOne = Integer.parseInt(GemstoneOperationMainPanel.cuttingString(GemstoneOperationMainPanel.this.goodsLabes[0], 1));
                                if (levelOne == levelTwo) {
                                    GemstoneOperationMainPanel.this.changeIcon(gemstoneOperationSuit, j, gemstoneOperationMainCardPanel.getGemstoneOperationSynthesisPanel().getGemstoneNumber());
                                    break;
                                }
                                ZhuFrame.getZhuJpanel().addPrompt2("两颗宝石等级不一致");
                                break;
                            }
                            GemstoneOperationMainPanel.this.changeIcon(gemstoneOperationSuit, j, gemstoneOperationMainCardPanel.getGemstoneOperationSynthesisPanel().getGemstoneNumber());
                            break;
                        }
                        if (numTen != 2) break;
                        ZhuFrame.getZhuJpanel().addPrompt2("已经选择了两颗宝石");
                        break;
                    }
                    case 2: {
                        for (int j = 0; j < GemstoneOperationMainPanel.this.goodsLabes.length; ++j) {
                            if (GemstoneOperationMainPanel.this.goodsLabes[j] != null) {
                                if (j == 0) {
                                    gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReelectGoods().setType(GemstoneOperationMainPanel.this.goodsLabes[j].getType());
                                }
                                ++numTen;
                                continue;
                            }
                            if (!(gemstoneOperationSuit.getType().equals(744L) || gemstoneOperationSuit.getType().equals(746L) || gemstoneOperationSuit.getType().equals(749L) || gemstoneOperationSuit.getType().equals(752L) || gemstoneOperationSuit.getType().equals(755L) || gemstoneOperationSuit.getType().equals(758L) || gemstoneOperationSuit.getType().equals(761L) || gemstoneOperationSuit.getType().equals(764L) || gemstoneOperationSuit.getType().equals(767L))) {
                                ZhuFrame.getZhuJpanel().addPrompt2("该宝石不能重铸");
                                return;
                            }
                            if (GemstoneOperationMainPanel.this.goodsLabes[0] != null) {
                                int levelOne = Integer.parseInt(GemstoneOperationMainPanel.cuttingString(GemstoneOperationMainPanel.this.goodsLabes[0], 1));
                                int levelTwo = Integer.parseInt(GemstoneOperationMainPanel.cuttingString(gemstoneOperationSuit, 1));
                                if (levelTwo == levelOne - 4||(levelOne==10&&levelTwo==10)) {
                                    GemstoneOperationMainPanel.this.changeIcon(gemstoneOperationSuit, j, gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getGemstoneNumber());
                                    break;
                                }
                                ZhuFrame.getZhuJpanel().addPrompt2("材料宝石等级要比主宝石等级小4(10级宝石可用10级提升价值)");
                                break;
                            }
                            if (GemstoneOperationMainPanel.this.goodsLabes[1] != null) {
                                int levelTwo = Integer.parseInt(GemstoneOperationMainPanel.cuttingString(gemstoneOperationSuit, 1));
                                if (levelTwo >= 6) {
                                    GemstoneOperationMainPanel.this.changeIcon(gemstoneOperationSuit, j, gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getGemstoneNumber());
                                    break;
                                }
                                ZhuFrame.getZhuJpanel().addPrompt2("主宝石等级要大于等于6");
                                break;
                            }
                            int levelTwo = Integer.parseInt(GemstoneOperationMainPanel.cuttingString(gemstoneOperationSuit, 1));
                            if (levelTwo >= 6) {
                                GemstoneOperationMainPanel.this.changeIcon(gemstoneOperationSuit, j, gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getGemstoneNumber());
                                break;
                            }
                            ZhuFrame.getZhuJpanel().addPrompt2("主宝石等级要大于等于6");
                            break;
                        }
                        if (numTen != 2) break;
                        ZhuFrame.getZhuJpanel().addPrompt2("已经选择了两颗宝石");
                        break;
                    }
                    case 3: {
                        for (int j = 0; j < GemstoneOperationMainPanel.this.goodsLabes.length; ++j) {
                            if (GemstoneOperationMainPanel.this.goodsLabes[j] != null) {
                                ++numTen;
                                continue;
                            }
                            if (GemstoneOperationMainPanel.this.goodsLabes[0] != null) {
                                if (!(gemstoneOperationSuit.getType().equals(744L) || gemstoneOperationSuit.getType().equals(746L) || gemstoneOperationSuit.getType().equals(749L) || gemstoneOperationSuit.getType().equals(752L) || gemstoneOperationSuit.getType().equals(755L) || gemstoneOperationSuit.getType().equals(758L) || gemstoneOperationSuit.getType().equals(761L) || gemstoneOperationSuit.getType().equals(764L) || gemstoneOperationSuit.getType().equals(767L))) {
                                    ZhuFrame.getZhuJpanel().addPrompt2("该宝石不能添加到材料宝石");
                                    return;
                                }
                                int levelOne = Integer.parseInt(GemstoneOperationMainPanel.cuttingString(GemstoneOperationMainPanel.this.goodsLabes[0], 1));
                                int levelTwo = Integer.parseInt(GemstoneOperationMainPanel.cuttingString(gemstoneOperationSuit, 1));
                                if (levelTwo == levelOne - 3) {
                                    GemstoneOperationMainPanel.this.changeIcon(gemstoneOperationSuit, j, gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getGemstoneNumber());
                                    break;
                                }
                                ZhuFrame.getZhuJpanel().addPrompt2("材料宝石等级要比奇异宝石小3级");
                                break;
                            }
                            if (GemstoneOperationMainPanel.this.goodsLabes[1] != null) {
                                if (gemstoneOperationSuit.getType().equals(770L)) {
                                    GemstoneOperationMainPanel.this.changeIcon(gemstoneOperationSuit, j, gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getGemstoneNumber());
                                    break;
                                }
                                ZhuFrame.getZhuJpanel().addPrompt2("要奇异宝石");
                                break;
                            }
                            if (gemstoneOperationSuit.getType().equals(770L)) {
                                GemstoneOperationMainPanel.this.changeIcon(gemstoneOperationSuit, j, gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getGemstoneNumber());
                                break;
                            }
                            ZhuFrame.getZhuJpanel().addPrompt2("要奇异宝石");
                            break;
                        }
                        if (numTen != 2) break;
                        ZhuFrame.getZhuJpanel().addPrompt2("已经选择了两颗宝石");
                        break;
                    }
                }
                GemstoneOperationMainPanel.this.repaintBtn();
            }
        }
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(382, 445));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 85);
            offBtn.setBounds(345, 10, 25, 25);
            this.add(offBtn);
            (this.upBtn = new GemstoneOperationBtn("inkImg/button/B42.png", 1, 11, this)).setBounds(300, 412, 18, 18);
            this.add(this.upBtn);
            (this.downBtn = new GemstoneOperationBtn("inkImg/button/B43.png", 1, 12, this)).setBounds(319, 412, 18, 18);
            this.add(this.downBtn);
            this.syntherisCheckBtn = new GemstoneOperationBtn("inkImg/button1/K54.png", 1, 2);
            this.recastCheckBtn = new GemstoneOperationBtn("inkImg/button1/K55.png", 1, 4);
            this.appraisalCheckBtn = new GemstoneOperationBtn("inkImg/button1/K57.png", 1, 6);
            this.helpBtn = new GemstoneOperationBtn("inkImg/button/19.png", 1, UIUtils.COLOR_BTNTEXT, "?", UIUtils.TEXT_FONT, 0);
            this.labels = new JLabel[12];
            for (int i = 0; i < this.labels.length; ++i) {
                (this.labels[i] = new JLabel()).setOpaque(false);
                this.labels[i].setBounds(50 + i % 6 * 51, 308 + i / 6 * 51, 48, 48);
                MListener ml = new MListener(i);
                this.labels[i].addMouseListener(ml);
                this.add(this.labels[i]);
            }
            this.syntherisCheckBtn.setOpaque(false);
            this.recastCheckBtn.setOpaque(false);
            this.appraisalCheckBtn.setOpaque(false);
            this.helpBtn.setOpaque(false);
            this.syntherisCheckBtn.setBounds(48, 13, 75, 33);
            this.recastCheckBtn.setBounds(126, 13, 75, 33);
            this.appraisalCheckBtn.setBounds(204, 13, 75, 33);
            this.helpBtn.setBounds(338, 49, 17, 17);
            this.add(this.syntherisCheckBtn);
            this.add(this.recastCheckBtn);
            this.add(this.appraisalCheckBtn);
            this.add(this.gemstoneOperationMainCardPanel = new GemstoneOperationMainCardPanel());
        }
        else {
            this.setPreferredSize(new Dimension(356, 466));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkimg/hongmu/s74.png", 1, 85);
            offBtn.setBounds(331, 2, 23, 23);
            this.add(offBtn);
            (this.upBtn = new GemstoneOperationBtn("inkimg/hongmu/ss534.png", 1, 11, this)).setBounds(295, 420, 19, 20);
            this.add(this.upBtn);
            (this.downBtn = new GemstoneOperationBtn("inkimg/hongmu/ss565.png", 1, 12, this)).setBounds(314, 420, 19, 20);
            this.add(this.downBtn);
            this.syntherisCheckBtn = new GemstoneOperationBtn("img/gemstone/合成未w63,h78px.png", 1, 2);
            this.recastCheckBtn = new GemstoneOperationBtn("img/gemstone/重铸未w63,h78px.png", 1, 4);
            this.appraisalCheckBtn = new GemstoneOperationBtn("img/gemstone/鉴定未w63,h78px.png", 1, 6);
            this.helpBtn = new GemstoneOperationBtn("img/gemstone/？w17,h51px.png", 1, UIUtils.COLOR_BTNTEXT, "?", UIUtils.TEXT_FONT, 0);
            this.labels = new JLabel[12];
            for (int i = 0; i < this.labels.length; ++i) {
                (this.labels[i] = new JLabel()).setOpaque(false);
                this.labels[i].setBounds(26 + i % 6 * 51, 319 + i / 6 * 51, 48, 48);
                MListener ml2 = new MListener(i);
                this.labels[i].addMouseListener(ml2);
                this.add(this.labels[i]);
            }
            this.syntherisCheckBtn.setOpaque(false);
            this.recastCheckBtn.setOpaque(false);
            this.appraisalCheckBtn.setOpaque(false);
            this.helpBtn.setOpaque(false);
            this.syntherisCheckBtn.setBounds(22, 30, 63, 26);
            this.recastCheckBtn.setBounds(85, 30, 63, 26);
            this.appraisalCheckBtn.setBounds(148, 30, 63, 26);
            this.helpBtn.setBounds(312, 60, 17, 17);
            this.add(this.syntherisCheckBtn);
            this.add(this.recastCheckBtn);
            this.add(this.appraisalCheckBtn);
            this.add(this.gemstoneOperationMainCardPanel = new GemstoneOperationMainCardPanel());
        }
    }
    
    public void lingFan(boolean type) {
        if (type) {
            if (this.yss + 1 < this.labels.length && this.labels[this.yss + 1] != null) {
                this.lingNumChange(this.yss + 1);
            }
        }
        else if (this.yss > 0) {
            this.lingNumChange(this.yss - 1);
        }
    }
    
    public void lingNumChange(int number) {
        this.yss = number;
    }
    
    public void PaintingText(int goodPosition) {
        this.goodPosition = goodPosition;
        this.shoptext = true;
    }
    
    public void ClearText() {
        this.shoptext = false;
    }
    
    public void changeIcon(Goodstable goods, int num, JLabel[] labels) {
        this.goodsLabes[num] = goods;
        if (goods != null) {
            labels[num].setIcon(CutButtonImage.getGemstoneIcon(goods.getSkin(), 53, 51));
        }
        else {
            labels[num].setIcon(null);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background1/B265.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 382, 445, this);
            if (this.shoptext) {
                int shop_x = this.goodPosition % 6;
                int shop_y = this.goodPosition / 6;
                g.drawImage(GemstoneOperationMainPanel.iconx2.getImage(), 50 + shop_x * 51, 308 + shop_y * 51, this);
            }
            GoodsListFromServerUntil.drawGemstoneOperationSuitH(g, 50, 308, this.yss, 0);
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/gemstone/宝石_底板w356,h466.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 356, 466, this);
            if (this.shoptext) {
                int shop_x = this.goodPosition % 6;
                int shop_y = this.goodPosition / 6;
                g.drawImage(GemstoneOperationMainPanel.iconx2.getImage(), 25 + shop_x * 51, 318 + shop_y * 51, this);
            }
            GoodsListFromServerUntil.drawGemstoneOperationSuitS(g, 26, 320, this.yss, 0);
        }
    }
    
    public void repaintBtn() {
        if (MyIsif.getStyle().equals("水墨")) {
            switch (this.typePanel) {
                case 1: {
                    for (int k = 0; k < this.goodsLabes.length; ++k) {
                        if (this.goodsLabes[k] == null) {
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationSynthesisPanel().getSynthesisBtn().setBtn(-1);
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationSynthesisPanel().getSynthesisBtn().setIcon(CutButtonImage.getImage("inkImg/button1/aj1h.png", -1, -1));
                            return;
                        }
                    }
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationSynthesisPanel().getSynthesisBtn().setBtn(1);
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationSynthesisPanel().getSynthesisBtn().setIcon(CutButtonImage.getImage("inkImg/button1/B20.png", -1, -1));
                    break;
                }
                case 2: {
                    for (int k = 0; k < this.goodsLabes.length; ++k) {
                        if (this.goodsLabes[k] == null) {
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setBtn(-1);
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setIcon(CutButtonImage.getImage("inkImg/button1/aj1h.png", 60, 26));
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReelectBtn().setBtn(-1);
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReelectBtn().setIcon(CutButtonImage.getImage("inkImg/button/39.png", 68, 17));
                            return;
                        }
                    }
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setBtn(1);
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setIcon(CutButtonImage.getImage("inkImg/button1/B20.png", 60, 78));
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReelectBtn().setBtn(1);
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReelectBtn().setIcon(CutButtonImage.getImage("inkImg/button/49.png", 68, 51));
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getGemstoneOperationRecastTypePanel().repaintGemstoneIcon();
                    break;
                }
                case 3: {
                    for (int k = 0; k < this.goodsLabes.length; ++k) {
                        if (this.goodsLabes[k] == null) {
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setBtn(-1);
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setIcon(CutButtonImage.getImage("inkImg/button1/aj1h.png", -1, -1));
                            return;
                        }
                    }
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setBtn(1);
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setIcon(CutButtonImage.getImage("inkImg/button1/B20.png", 60, 78));
                    break;
                }
            }
        }
        else {
            switch (this.typePanel) {
                case 1: {
                    for (int k = 0; k < this.goodsLabes.length; ++k) {
                        if (this.goodsLabes[k] == null) {
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationSynthesisPanel().getSynthesisBtn().setBtn(-1);
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationSynthesisPanel().getSynthesisBtn().setIcon(CutButtonImage.getImage("img/gemstone/合成w60,h26.png", -1, -1));
                            return;
                        }
                    }
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationSynthesisPanel().getSynthesisBtn().setBtn(1);
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationSynthesisPanel().getSynthesisBtn().setIcon(CutButtonImage.getImage("img/gemstone/合成w60,h78.png", -1, -1));
                    break;
                }
                case 2: {
                    for (int k = 0; k < this.goodsLabes.length; ++k) {
                        if (this.goodsLabes[k] == null) {
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setBtn(-1);
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setIcon(CutButtonImage.getImage("img/xy2uiimg/112gray.png", 65, 24));
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReelectBtn().setBtn(-1);
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReelectBtn().setIcon(CutButtonImage.getImage("img/gemstone/重选类型w68,h17px.png", 68, 17));
                            return;
                        }
                    }
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setBtn(1);
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setIcon(CutButtonImage.getImage("img/xy2uiimg/112.png", 65, 24));
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReelectBtn().setBtn(1);
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReelectBtn().setIcon(CutButtonImage.getImage("img/gemstone/重选类型w68,h51px.png", 68, 51));
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getGemstoneOperationRecastTypePanel().repaintGemstoneIcon();
                    break;
                }
                case 3: {
                    for (int k = 0; k < this.goodsLabes.length; ++k) {
                        if (this.goodsLabes[k] == null) {
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setBtn(-1);
                            this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setIcon(CutButtonImage.getImage("img/xy2uiimg/112gray.png", 65, 24));
                            return;
                        }
                    }
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setBtn(1);
                    this.gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReacastBtn().setIcon(CutButtonImage.getImage("img/xy2uiimg/112.png", 65, 24));
                    break;
                }
            }
        }
    }
    
    public static String cuttingString(Goodstable goods, int num) {
        if (goods.getType().equals(Long.valueOf(744L))) {
            return "1";
        }
        String value = goods.getValue();
        if (value == null) {
            return null;
        }
        String[] split = value.split("\\|");
        String[] split2 = split[num - 1].split("=");
        return split2[1];
    }
    
    public GemstoneOperationBtn getSyntherisCheckBtn() {
        return this.syntherisCheckBtn;
    }
    
    public void setSyntherisCheckBtn(GemstoneOperationBtn syntherisCheckBtn) {
        this.syntherisCheckBtn = syntherisCheckBtn;
    }
    
    public GemstoneOperationBtn getRecastCheckBtn() {
        return this.recastCheckBtn;
    }
    
    public void setRecastCheckBtn(GemstoneOperationBtn recastCheckBtn) {
        this.recastCheckBtn = recastCheckBtn;
    }
    
    public GemstoneOperationBtn getAppraisalCheckBtn() {
        return this.appraisalCheckBtn;
    }
    
    public void setAppraisalCheckBtn(GemstoneOperationBtn appraisalCheckBtn) {
        this.appraisalCheckBtn = appraisalCheckBtn;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public GemstoneOperationBtn getHelpBtn() {
        return this.helpBtn;
    }
    
    public void setHelpBtn(GemstoneOperationBtn helpBtn) {
        this.helpBtn = helpBtn;
    }
    
    public int getYs() {
        return this.ys;
    }
    
    public void setYs(int ys) {
        this.ys = ys;
    }
    
    public JLabel[] getLabels() {
        return this.labels;
    }
    
    public void setLabels(JLabel[] labels) {
        this.labels = labels;
    }
    
    public int getTypePanel() {
        return this.typePanel;
    }
    
    public void setTypePanel(int typePanel) {
        this.typePanel = typePanel;
    }
    
    public int getNum() {
        return this.num;
    }
    
    public void setNum(int num) {
        this.num = num;
    }
    
    public Goodstable[] getGoodsLabes() {
        return this.goodsLabes;
    }
    
    public void setGoodsLabes(Goodstable[] goodsLabes) {
        this.goodsLabes = goodsLabes;
    }
    
    public GemstoneOperationMainCardPanel getGemstoneOperationMainCardPanel() {
        return this.gemstoneOperationMainCardPanel;
    }
    
    public void setGemstoneOperationMainCardPanel(GemstoneOperationMainCardPanel gemstoneOperationMainCardPanel) {
        this.gemstoneOperationMainCardPanel = gemstoneOperationMainCardPanel;
    }
    
    static {
        GemstoneOperationMainPanel.iconx2 = new ImageIcon("jiuUI/jiuuijiemian/border_quack.png");
    }
}
