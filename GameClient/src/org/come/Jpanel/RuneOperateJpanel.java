package org.come.Jpanel;

import org.come.entity.Goodstable;
import org.come.until.Util;
import org.come.until.GoodsListFromServerUntil;
import java.awt.Graphics;
import org.come.mouslisten.WLLMouslisten;
import org.come.mouslisten.TransmuteMouslisten;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.goodbtn;
import javax.swing.JLabel;
import com.tool.btn.RuneOPerBtn;
import com.tool.btn.DazaoBtn;
import java.math.BigDecimal;
import com.tool.btn.GoodPanelBtn;
import org.cbg.btn.TrslationBtn;
import javax.swing.JPanel;

public class RuneOperateJpanel extends JPanel
{
    private TrslationBtn btnSj;
    private TrslationBtn btnCz;
    private GoodPanelBtn clearAll;
    private BigDecimal money;
    private DazaoBtn perBtn1;
    private RuneOPerBtn perBtn2;
    private JLabel[] GoodsListLabel;
    private goodbtn[] btnrights;
    private JLabel labResult;
    private int runeType;
    private ImageIcon iconBack;
    private ImageIcon icon;
    private ImageIcon icon2;
    
    public RuneOperateJpanel() {
        this.money = new BigDecimal(0);
        this.GoodsListLabel = new JLabel[31];
        this.runeType = 1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(392, 505));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 70);
            offBtn.setBounds(355, 10, 25, 25);
            this.add(offBtn);
            (this.btnSj = new TrslationBtn("inkImg/button/B183.png", 1, 2, this)).setBounds(125, 16, 70, 35);
            this.add(this.btnSj);
            (this.btnCz = new TrslationBtn("inkImg/button/B186.png", 1, 1, this)).setBounds(53, 16, 70, 35);
            this.add(this.btnCz);
            (this.perBtn1 = new DazaoBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "重铸", this)).setBounds(274, 240, 59, 24);
            this.add(this.perBtn1);
            (this.perBtn2 = new RuneOPerBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "重铸规则", 3)).setBounds(273, 204, 68, 17);
            this.add(this.perBtn2);
            (this.labResult = new JLabel()).setBounds(176, 170, 53, 51);
            this.labResult.setVisible(false);
            this.add(this.labResult);
            for (int i = 0; i < this.GoodsListLabel.length; ++i) {
                int row = i % 6;
                int col = i / 6;
                (this.GoodsListLabel[i] = new JLabel()).addMouseListener(new TransmuteMouslisten(i, this));
                if (i == 24) {
                    this.GoodsListLabel[i].setBounds(176, 98, 53, 51);
                }
                else if (i == 25) {
                    this.GoodsListLabel[i].setBounds(176, 170, 53, 51);
                }
                else if (i > 25) {
                    this.GoodsListLabel[i].setBounds(62 + (i - 26) * 57, 98, 53, 51);
                    this.GoodsListLabel[i].setVisible(false);
                }
                else {
                    this.GoodsListLabel[i].setBounds(48 + row * 51, 283 + col * 51, 49, 49);
                }
                this.add(this.GoodsListLabel[i]);
            }
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("inkImg/button1/C0" + (i + 1) + ".png", 0, this, i)).setBounds(354, 283 + i * 35, 39, 31);
                this.add(this.btnrights[i]);
            }
        }
        else {
            this.setPreferredSize(new Dimension(392, 505));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 70);
            offBtn.setBounds(365, 0, 23, 23);
            this.add(offBtn);
            (this.btnSj = new TrslationBtn("img/xy2uiimg/升级按钮-W：30-H：138.png", 1, 2, this)).setBounds(3, 174, 30, 46);
            this.add(this.btnSj);
            (this.btnCz = new TrslationBtn("img/xy2uiimg/重洗按钮2-W：30-H：138.png", 1, 1, this)).setBounds(3, 225, 30, 46);
            this.add(this.btnCz);
            (this.perBtn1 = new DazaoBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "重铸", this)).setBounds(289, 110, 60, 26);
            this.add(this.perBtn1);
            (this.perBtn2 = new RuneOPerBtn("inkImg/hongmu/a7.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "重铸规则", 3)).setBounds(281, 143, 68, 17);
            this.add(this.perBtn2);
            (this.labResult = new JLabel()).setBounds(176, 170, 53, 51);
            this.labResult.setVisible(false);
            this.add(this.labResult);
            (this.clearAll = new GoodPanelBtn("inkImg/hongmu/a3.png", 1, "整")).addMouseListener(new WLLMouslisten(17));
            this.clearAll.setBounds(365, 460, 17, 17);
            this.add(this.clearAll);
            for (int i = 0; i < this.GoodsListLabel.length; ++i) {
                int row = i % 6;
                int col = i / 6;
                (this.GoodsListLabel[i] = new JLabel()).addMouseListener(new TransmuteMouslisten(i, this));
                if (i == 24) {
                    this.GoodsListLabel[i].setBounds(115, 48, 53, 51);
                }
                else if (i == 25) {
                    this.GoodsListLabel[i].setBounds(229, 48, 53, 51);
                }
                else if (i > 25) {
                    this.GoodsListLabel[i].setBounds(53 + (i - 26) * 57, 48, 53, 51);
                    this.GoodsListLabel[i].setVisible(false);
                }
                else {
                    this.GoodsListLabel[i].setBounds(38 + row * 51, 187 + col * 51, 49, 49);
                }
                this.add(this.GoodsListLabel[i]);
            }
            this.btnrights = new goodbtn[6];
            for (int i = 0; i < this.btnrights.length; ++i) {
                (this.btnrights[i] = new goodbtn("img/xy2uiimg/44_png.button.tab_pak.png", 0, this, i)).setBounds(341, 173 + i * 51, 16, 49);
                this.add(this.btnrights[i]);
            }
        }
    }
    
    public void changRuneType() {
        for (int i = 24; i < this.GoodsListLabel.length; ++i) {
            if (i < 26) {
                this.GoodsListLabel[i].setVisible(this.runeType == 1);
                TransmuteMouslisten.goods3[i - 24] = null;
            }
            else {
                this.GoodsListLabel[i].setVisible(this.runeType != 1);
                TransmuteMouslisten.goods4[i - 26] = null;
            }
            this.GoodsListLabel[i].setIcon(null);
        }
        this.labResult.setVisible(this.runeType != 1);
        this.money = new BigDecimal("0");
    }
    
    public void btntype(int path) {
        for (int i = 0; i < path; ++i) {
            this.btnrights[i].btntypechange(2);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background/S93.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 392, 505, this);
            if (this.runeType == 1) {
                if (this.icon == null) {
                    this.icon = new ImageIcon("inkImg/background/S95.png");
                }
                g.drawImage(this.icon.getImage(), 46, 50, 310, 230, this);
            }
            else {
                if (this.icon2 == null) {
                    this.icon2 = new ImageIcon("inkImg/background/S94.png");
                }
                g.drawImage(this.icon2.getImage(), 46, 50, 310, 230, this);
            }
            GoodsListFromServerUntil.draw(g, 48, 283);
            Util.drawMoney(g, 147, 269);
            if (this.money != null) {
                Util.drawPrice(g, this.money, 147, 249);
            }
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/xy2uiimg/S93.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 392, 505, this);
            if (this.runeType == 1) {
                if (this.icon == null) {
                    this.icon = new ImageIcon("img/xy2uiimg/S95.png");
                }
                g.drawImage(this.icon.getImage(), 33, 35, 324, 128, this);
            }
            else {
                if (this.icon2 == null) {
                    this.icon2 = new ImageIcon("img/xy2uiimg/S94.png");
                }
                g.drawImage(this.icon2.getImage(), 33, 35, 324, 128, this);
            }
            GoodsListFromServerUntil.draw(g, 32, 174);
            Util.drawMoney(g, 98, 146);
            if (this.money != null) {
                Util.drawPrice(g, this.money, 98, 122);
            }
        }
    }
    
    public TrslationBtn getBtnSj() {
        return this.btnSj;
    }
    
    public void setBtnSj(TrslationBtn btnSj) {
        this.btnSj = btnSj;
    }
    
    public TrslationBtn getBtnCz() {
        return this.btnCz;
    }
    
    public void setBtnCz(TrslationBtn btnCz) {
        this.btnCz = btnCz;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public DazaoBtn getPerBtn1() {
        return this.perBtn1;
    }
    
    public void setPerBtn1(DazaoBtn perBtn1) {
        this.perBtn1 = perBtn1;
    }
    
    public RuneOPerBtn getPerBtn2() {
        return this.perBtn2;
    }
    
    public void setPerBtn2(RuneOPerBtn perBtn2) {
        this.perBtn2 = perBtn2;
    }
    
    public goodbtn[] getBtnrights() {
        return this.btnrights;
    }
    
    public void setBtnrights(goodbtn[] btnrights) {
        this.btnrights = btnrights;
    }
    
    public JLabel getLabResult() {
        return this.labResult;
    }
    
    public void setLabResult(JLabel labResult) {
        this.labResult = labResult;
    }
    
    public int getRuneType() {
        return this.runeType;
    }
    
    public void setRuneType(int runeType) {
        this.runeType = runeType;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public ImageIcon getIcon2() {
        return this.icon2;
    }
    
    public void setIcon2(ImageIcon icon2) {
        this.icon2 = icon2;
    }
    
    public JLabel[] getGoodsListLabel() {
        return this.GoodsListLabel;
    }
    
    public void setGoodsListLabel(JLabel[] goodsListLabel) {
        this.GoodsListLabel = goodsListLabel;
    }
}
