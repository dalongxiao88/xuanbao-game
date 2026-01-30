package org.cbg.panel;

import org.come.until.Util;
import java.awt.Graphics;
import java.math.BigDecimal;
import org.cbg.until.TraslationTableRuleUntil;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.tool.Document.NumberDocument;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.until.CutButtonImage;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import org.cbg.btn.CBGSellBtn;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.tool.Document.InputNum;
import javax.swing.JPanel;

public class TraslationWantSentjishoujinqianJpanel extends JPanel implements InputNum
{
    private JLabel tupian;
    private JLabel time;
    private JLabel buyId;
    private JTextField bidsilver;
    private JTextField consignmentpriceYuan;
    private JTextField criceconfirmationYuan;
    private JTextField assignID;
    private JLabel univalent;
    private CBGSellBtn sendOk;
    private JScrollPane jScrollPane;
    private JTextArea area;
    private ImageIcon icon;
    
    public TraslationWantSentjishoujinqianJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.tupian = new JLabel()).setBounds(124, 34, 53, 51);
            this.tupian.setOpaque(false);
            this.tupian.setIcon(CutButtonImage.getCBG(2, null, 49, 49));
            this.add(this.tupian);
            (this.bidsilver = new JTextField()).setBounds(270, 64, 140, 18);
            this.bidsilver.setBorder(null);
            this.bidsilver.setOpaque(false);
            this.bidsilver.setVisible(true);
            this.bidsilver.setForeground(Color.white);
            this.bidsilver.setCaretColor(Color.white);
            this.bidsilver.setFont(UIUtils.TEXT_FONT1);
            this.bidsilver.setDocument(new NumberDocument(this.bidsilver, 0, this));
            this.add(this.bidsilver);
            (this.consignmentpriceYuan = new JTextField()).setBounds(270, 104, 100, 18);
            this.consignmentpriceYuan.setBorder(null);
            this.consignmentpriceYuan.setOpaque(false);
            this.consignmentpriceYuan.setVisible(true);
            this.consignmentpriceYuan.setForeground(Color.white);
            this.consignmentpriceYuan.setCaretColor(Color.white);
            this.consignmentpriceYuan.setDocument(new NumberDocument(this.consignmentpriceYuan, 7, this));
            this.add(this.consignmentpriceYuan);
            (this.criceconfirmationYuan = new JTextField()).setBounds(270, 131, 100, 18);
            this.criceconfirmationYuan.setBorder(null);
            this.criceconfirmationYuan.setOpaque(false);
            this.criceconfirmationYuan.setVisible(true);
            this.criceconfirmationYuan.setForeground(Color.white);
            this.criceconfirmationYuan.setCaretColor(Color.white);
            this.criceconfirmationYuan.setDocument(new NumberDocument(this.criceconfirmationYuan, 7));
            this.add(this.criceconfirmationYuan);
            (this.univalent = new JLabel()).setBounds(270, 158, 70, 18);
            this.univalent.setBorder(null);
            this.univalent.setOpaque(false);
            this.univalent.setVisible(true);
            this.univalent.setForeground(Color.white);
            this.add(this.univalent);
            (this.assignID = new JTextField()).setBounds(310, 208, 100, 18);
            this.assignID.setBorder(null);
            this.assignID.setOpaque(false);
            this.assignID.setVisible(true);
            this.assignID.setForeground(Color.white);
            this.assignID.setCaretColor(Color.white);
            this.assignID.setDocument(new NumberDocument(this.assignID, 7));
            this.add(this.assignID);
            (this.time = new JLabel()).setBounds(197, 184, 15, 15);
            this.time.setOpaque(false);
            this.time.setBorder(null);
            this.time.setBackground(null);
            this.time.setIcon(CutButtonImage.getImage("inkImg/button/13.png", -1, -1));
            this.add(this.time);
            (this.buyId = new JLabel()).setBounds(197, 207, 15, 15);
            this.buyId.setOpaque(false);
            this.buyId.setBorder(null);
            this.buyId.setBackground(null);
            this.add(this.buyId);
            this.buyId.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantSentjishoujinqianJpanel.this.buyId.getIcon() == null) {
                        TraslationWantSentjishoujinqianJpanel.this.buyId.setIcon(CutButtonImage.getImage("inkImg/button/13.png", -1, -1));
                    }
                    else {
                        TraslationWantSentjishoujinqianJpanel.this.buyId.setIcon(null);
                    }
                }
            });
            (this.sendOk = new CBGSellBtn("inkImg/button/18.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "确定销售", this)).setBounds(250, 252, 100, 26);
            this.add(this.sendOk);
            this.jScrollPane = new JScrollPane();
            (this.area = new JTextArea()).append("温馨提示：此功能是为了方便玩家更快捷寻找到需要的精品装备、道具，\n");
            this.area.append("避免有人挂太多的普通物品影响寄售的数量和质量特做以下设置；\n");
            this.area.append("1、寄售物品需缴纳%5仙玉，上架时直接从账户扣除，账户仙玉不足无法寄售；\n");
            this.area.append("2、寄售价格在100仙玉-10000000仙玉之间；\n");
            this.area.append("3、购买后请在我的集市中我的货物处取回道具。");
            this.area.setBounds(0, 0, 588, 120);
            this.area.setForeground(Color.white);
            this.area.setFont(new Font("宋体", 0, 12));
            this.area.setOpaque(false);
            TraslationTableRuleUntil.TableModel(this.jScrollPane, this.area);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane.setBounds(2, 296, 591, 82);
            this.add(this.jScrollPane);
        }
        else {
            this.setPreferredSize(new Dimension(590, 380));
            this.setOpaque(false);
            this.setLayout(null);
            (this.tupian = new JLabel()).setBounds(124, 34, 53, 51);
            this.tupian.setOpaque(false);
            this.tupian.setIcon(CutButtonImage.getCBG(2, null, 49, 49));
            this.add(this.tupian);
            (this.bidsilver = new JTextField()).setBounds(270, 60, 140, 18);
            this.bidsilver.setBorder(null);
            this.bidsilver.setOpaque(false);
            this.bidsilver.setVisible(true);
            this.bidsilver.setForeground(Color.white);
            this.bidsilver.setCaretColor(Color.white);
            this.bidsilver.setFont(UIUtils.TEXT_FONT1);
            this.bidsilver.setDocument(new NumberDocument(this.bidsilver, 0, this));
            this.add(this.bidsilver);
            (this.consignmentpriceYuan = new JTextField()).setBounds(270, 102, 70, 18);
            this.consignmentpriceYuan.setBorder(null);
            this.consignmentpriceYuan.setOpaque(false);
            this.consignmentpriceYuan.setVisible(true);
            this.consignmentpriceYuan.setForeground(Color.white);
            this.consignmentpriceYuan.setCaretColor(Color.white);
            this.consignmentpriceYuan.setDocument(new NumberDocument(this.consignmentpriceYuan, 7, this));
            this.add(this.consignmentpriceYuan);
            (this.criceconfirmationYuan = new JTextField()).setBounds(270, 129, 70, 18);
            this.criceconfirmationYuan.setBorder(null);
            this.criceconfirmationYuan.setOpaque(false);
            this.criceconfirmationYuan.setVisible(true);
            this.criceconfirmationYuan.setForeground(Color.white);
            this.criceconfirmationYuan.setCaretColor(Color.white);
            this.criceconfirmationYuan.setDocument(new NumberDocument(this.criceconfirmationYuan, 7));
            this.add(this.criceconfirmationYuan);
            (this.univalent = new JLabel()).setBounds(270, 156, 70, 18);
            this.univalent.setBorder(null);
            this.univalent.setOpaque(false);
            this.univalent.setVisible(true);
            this.univalent.setForeground(Color.white);
            this.add(this.univalent);
            (this.assignID = new JTextField()).setBounds(310, 206, 100, 18);
            this.assignID.setBorder(null);
            this.assignID.setOpaque(false);
            this.assignID.setVisible(true);
            this.assignID.setForeground(Color.white);
            this.assignID.setCaretColor(Color.white);
            this.assignID.setDocument(new NumberDocument(this.assignID, 7));
            this.add(this.assignID);
            (this.time = new JLabel()).setBounds(197, 182, 15, 15);
            this.time.setOpaque(false);
            this.time.setBorder(null);
            this.time.setBackground(null);
            this.time.setIcon(CutButtonImage.getImage("img/xy2uiimg/showjadesuit.png", -1, -1));
            this.add(this.time);
            (this.buyId = new JLabel()).setBounds(197, 205, 15, 15);
            this.buyId.setOpaque(false);
            this.buyId.setBorder(null);
            this.buyId.setBackground(null);
            this.add(this.buyId);
            this.buyId.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantSentjishoujinqianJpanel.this.buyId.getIcon() == null) {
                        TraslationWantSentjishoujinqianJpanel.this.buyId.setIcon(CutButtonImage.getImage("inkImg/button/13.png", -1, -1));
                    }
                    else {
                        TraslationWantSentjishoujinqianJpanel.this.buyId.setIcon(null);
                    }
                }
            });
            (this.sendOk = new CBGSellBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "确定销售", this)).setBounds(250, 252, 80, 26);
            this.add(this.sendOk);
            this.jScrollPane = new JScrollPane();
            (this.area = new JTextArea()).append("温馨提示：此功能是为了方便玩家更快捷寻找到需要的精品装备、道具，\n");
            this.area.append("避免有人挂太多的普通物品影响寄售的数量和质量特做以下设置；\n");
            this.area.append("1、寄售物品需缴纳%5仙玉，上架时直接从账户扣除，账户仙玉不足无法寄售；\n");
            this.area.append("2、寄售价格在100仙玉-10000000仙玉之间；\n");
            this.area.append("3、购买后请在我的集市中我的货物处取回道具。");
            this.area.setBounds(0, 0, 588, 120);
            this.area.setForeground(Color.white);
            this.area.setFont(new Font("宋体", 0, 12));
            this.area.setOpaque(false);
            TraslationTableRuleUntil.TableModel(this.jScrollPane, this.area);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane.setBounds(2, 296, 591, 82);
            this.add(this.jScrollPane);
        }
    }
    
    @Override
    public void upNum() {
        BigDecimal j1 = ((NumberDocument)this.bidsilver.getDocument()).getNum();
        BigDecimal j2 = ((NumberDocument)this.consignmentpriceYuan.getDocument()).getNum();
        if (j1.longValue() <= 0L || j2.longValue() <= 0L) {
            this.univalent.setText(null);
            return;
        }
        j1 = j1.divide(new BigDecimal(10000), 2, 5);
        j1 = j1.divide(j2, 2, 5);
        this.univalent.setText(j1.toString());
    }
    
    @Override
    public boolean isChange() {
        return false;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/34.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 590, 380, this);
            Util.drawMoney(g, 270, 48);
        }
        else {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/我要卖-寄售金钱w590,h380px，top97,left22px.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 590, 380, this);
            Util.drawMoney(g, 270, 46);
        }
    }
    
    public BigDecimal getJG() {
        BigDecimal j1 = ((NumberDocument)this.consignmentpriceYuan.getDocument()).getNum();
        BigDecimal j2 = ((NumberDocument)this.criceconfirmationYuan.getDocument()).getNum();
        if (j1.compareTo(j2) == 0) {
            return j1;
        }
        return null;
    }
    
    public BigDecimal getJB() {
        BigDecimal j1 = ((NumberDocument)this.bidsilver.getDocument()).getNum();
        if (j1.longValue() < 10000000L || j1.longValue() % 100000L != 0L) {
            return null;
        }
        return j1;
    }
    
    public BigDecimal getZD() {
        if (this.buyId.getIcon() != null) {
            BigDecimal j1 = ((NumberDocument)this.assignID.getDocument()).getNum();
            return j1;
        }
        return new BigDecimal(0);
    }
    
    public void qk() {
        this.bidsilver.setText(null);
        this.consignmentpriceYuan.setText(null);
        this.criceconfirmationYuan.setText(null);
        this.assignID.setText(null);
        this.univalent.setText(null);
    }
}
