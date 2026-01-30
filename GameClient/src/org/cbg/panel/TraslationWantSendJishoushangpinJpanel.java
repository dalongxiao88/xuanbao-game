package org.cbg.panel;

import org.come.entity.RoleSummoning;
import java.math.BigDecimal;
import java.awt.Graphics;
import org.cbg.until.TraslationTableRuleUntil;
import org.come.until.CutButtonImage;
import com.tool.Document.NumberDocument;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.cbg.btn.TrslationBtn;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.cbg.entity.Salegoods;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import org.cbg.btn.CBGSellBtn;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TraslationWantSendJishoushangpinJpanel extends JPanel
{
    private JLabel tupian;
    private JLabel wupin;
    private JLabel lingbao;
    private JLabel zhaohuanshou;
    private JTextField sale;
    private JTextField confirm;
    private JTextField assignID;
    private JLabel time;
    private JLabel buyId;
    private CBGSellBtn sendOk;
    private JScrollPane jScrollPane;
    private JTextArea area;
    private TraslationWantSendJishoushangpinFenleiJpanel traslationWantSendJishoushangpinFenleiJpanel;
    private Salegoods salegoods;
    private ImageIcon icon;
    
    public TraslationWantSendJishoushangpinJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setBackground(null);
            this.setOpaque(false);
            this.setLayout(null);
            this.add(this.traslationWantSendJishoushangpinFenleiJpanel = new TraslationWantSendJishoushangpinFenleiJpanel());
            (this.wupin = new TrslationBtn("inkImg/button/B157.png", 1)).setBounds(10, 10, 30, 63);
            this.add(this.wupin);
            (this.lingbao = new TrslationBtn("inkImg/button/B156.png", 1)).setBounds(10, 75, 30, 63);
            this.add(this.lingbao);
            (this.zhaohuanshou = new TrslationBtn("inkImg/button/B160.png", 1)).setBounds(10, 140, 30, 63);
            this.add(this.zhaohuanshou);
            (this.tupian = new JLabel()).setBackground(null);
            this.tupian.setBounds(437, 13, 54, 53);
            this.tupian.setOpaque(false);
            this.tupian.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantSendJishoushangpinJpanel.this.thGood(null);
                }
            });
            this.add(this.tupian);
            this.wupin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantSendJishoushangpinJpanel.this.traslationWantSendJishoushangpinFenleiJpanel.change(0);
                }
            });
            this.lingbao.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantSendJishoushangpinJpanel.this.traslationWantSendJishoushangpinFenleiJpanel.change(1);
                }
            });
            this.zhaohuanshou.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantSendJishoushangpinJpanel.this.traslationWantSendJishoushangpinFenleiJpanel.change(2);
                }
            });
            (this.sale = new JTextField()).setBounds(456, 75, 70, 18);
            this.sale.setOpaque(false);
            this.sale.setBorder(null);
            this.sale.setForeground(Color.white);
            this.sale.setCaretColor(Color.white);
            this.sale.setFont(UIUtils.TEXT_FONT1);
            this.sale.setDocument(new NumberDocument(this.sale, 7));
            this.add(this.sale);
            (this.confirm = new JTextField()).setBounds(456, 137, 70, 18);
            this.confirm.setOpaque(false);
            this.confirm.setBorder(null);
            this.confirm.setForeground(Color.white);
            this.confirm.setCaretColor(Color.white);
            this.confirm.setFont(UIUtils.TEXT_FONT1);
            this.confirm.setDocument(new NumberDocument(this.confirm, 7));
            this.add(this.confirm);
            (this.assignID = new JTextField()).setBounds(474, 204, 90, 18);
            this.assignID.setOpaque(false);
            this.assignID.setBorder(null);
            this.assignID.setForeground(Color.white);
            this.assignID.setCaretColor(Color.white);
            this.assignID.setFont(UIUtils.TEXT_FONT1);
            this.assignID.setDocument(new NumberDocument(this.assignID, 7));
            this.add(this.assignID);
            (this.time = new JLabel()).setBounds(361, 180, 13, 13);
            this.time.setBorder(null);
            this.time.setOpaque(false);
            this.time.setIcon(CutButtonImage.getImage("inkImg/button/13.png", -1, -1));
            this.add(this.time);
            (this.buyId = new JLabel()).setBounds(361, 204, 13, 13);
            this.buyId.setBorder(null);
            this.buyId.setOpaque(false);
            this.buyId.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantSendJishoushangpinJpanel.this.buyId.getIcon() == null) {
                        TraslationWantSendJishoushangpinJpanel.this.buyId.setIcon(CutButtonImage.getImage("inkImg/button/13.png", -1, -1));
                    }
                    else {
                        TraslationWantSendJishoushangpinJpanel.this.buyId.setIcon(null);
                    }
                }
            });
            this.add(this.buyId);
            (this.sendOk = new CBGSellBtn("inkImg/button/18.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "确定销售", this)).setBounds(417, 240, 100, 26);
            this.add(this.sendOk);
            this.jScrollPane = new JScrollPane();
            (this.area = new JTextArea()).append("温馨提示：此功能是为了方便玩家更快捷寻找到需要的精品装备、道具，\n");
            this.area.append("避免有人挂太多的普通物品影响寄售的数量和质量特做以下设置；\n");
            this.area.append("1、寄售物品需缴纳%5仙玉，上架时直接从账户扣除，账户仙玉不足无法寄售；\n");
            this.area.append("2、寄售价格在100仙玉-10000000仙玉之间；\n");
            this.area.append("3、购买后请在我的集市中我的货物处取回道具。");
            this.area.setBounds(0, 0, 588, 120);
            this.area.setForeground(Color.white);
            this.area.setFont(UIUtils.TEXT_FONT);
            this.area.setOpaque(false);
            TraslationTableRuleUntil.TableModel(this.jScrollPane, this.area);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane.setBounds(2, 296, 590, 82);
            this.add(this.jScrollPane);
        }
        else {
            this.setBackground(null);
            this.setOpaque(false);
            this.setLayout(null);
            this.add(this.traslationWantSendJishoushangpinFenleiJpanel = new TraslationWantSendJishoushangpinFenleiJpanel());
            (this.wupin = new JLabel("物品")).setOpaque(true);
            this.wupin.setBounds(10, 10, 28, 63);
            this.wupin.setOpaque(false);
            this.wupin.setForeground(Color.white);
            this.wupin.setIcon(CutButtonImage.getImage("img/xy2uiimg/物品w28,h63.png", -1, -1));
            this.wupin.setFont(UIUtils.TEXT_FONT_17);
            this.wupin.setVisible(true);
            this.add(this.wupin);
            (this.lingbao = new JLabel("灵宝")).setOpaque(true);
            this.lingbao.setBounds(10, 75, 28, 63);
            this.lingbao.setOpaque(false);
            this.lingbao.setForeground(Color.white);
            this.lingbao.setIcon(CutButtonImage.getImage("img/xy2uiimg/灵宝（小）w28,h63.png", -1, -1));
            this.lingbao.setFont(UIUtils.TEXT_FONT_17);
            this.lingbao.setVisible(true);
            this.add(this.lingbao);
            (this.zhaohuanshou = new JLabel("召唤兽")).setOpaque(true);
            this.zhaohuanshou.setBounds(10, 140, 28, 63);
            this.zhaohuanshou.setOpaque(false);
            this.zhaohuanshou.setForeground(Color.white);
            this.zhaohuanshou.setIcon(CutButtonImage.getImage("img/xy2uiimg/召唤兽（小）w28,h63.png", -1, -1));
            this.zhaohuanshou.setFont(UIUtils.TEXT_FONT_17);
            this.zhaohuanshou.setVisible(true);
            this.add(this.zhaohuanshou);
            (this.tupian = new JLabel()).setBackground(null);
            this.tupian.setBounds(437, 13, 54, 53);
            this.tupian.setOpaque(false);
            this.tupian.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantSendJishoushangpinJpanel.this.thGood(null);
                }
            });
            this.add(this.tupian);
            this.wupin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantSendJishoushangpinJpanel.this.traslationWantSendJishoushangpinFenleiJpanel.change(0);
                }
            });
            this.lingbao.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantSendJishoushangpinJpanel.this.traslationWantSendJishoushangpinFenleiJpanel.change(1);
                }
            });
            this.zhaohuanshou.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    TraslationWantSendJishoushangpinJpanel.this.traslationWantSendJishoushangpinFenleiJpanel.change(2);
                }
            });
            (this.sale = new JTextField()).setBounds(456, 75, 70, 18);
            this.sale.setOpaque(false);
            this.sale.setBorder(null);
            this.sale.setForeground(Color.white);
            this.sale.setCaretColor(Color.white);
            this.sale.setFont(UIUtils.TEXT_FONT1);
            this.sale.setDocument(new NumberDocument(this.sale, 7));
            this.add(this.sale);
            (this.confirm = new JTextField()).setBounds(456, 137, 70, 18);
            this.confirm.setOpaque(false);
            this.confirm.setBorder(null);
            this.confirm.setForeground(Color.white);
            this.confirm.setCaretColor(Color.white);
            this.confirm.setFont(UIUtils.TEXT_FONT1);
            this.confirm.setDocument(new NumberDocument(this.confirm, 7));
            this.add(this.confirm);
            (this.assignID = new JTextField()).setBounds(474, 204, 90, 18);
            this.assignID.setOpaque(false);
            this.assignID.setBorder(null);
            this.assignID.setForeground(Color.white);
            this.assignID.setCaretColor(Color.white);
            this.assignID.setFont(UIUtils.TEXT_FONT1);
            this.assignID.setDocument(new NumberDocument(this.assignID, 7));
            this.add(this.assignID);
            (this.time = new JLabel()).setBounds(361, 180, 13, 13);
            this.time.setBorder(null);
            this.time.setOpaque(false);
            this.time.setIcon(CutButtonImage.getImage("img/xy2uiimg/showjadesuit.png", -1, -1));
            this.add(this.time);
            (this.buyId = new JLabel()).setBounds(361, 204, 13, 13);
            this.buyId.setBorder(null);
            this.buyId.setOpaque(false);
            this.buyId.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (TraslationWantSendJishoushangpinJpanel.this.buyId.getIcon() == null) {
                        TraslationWantSendJishoushangpinJpanel.this.buyId.setIcon(CutButtonImage.getImage("inkImg/button/13.png", -1, -1));
                    }
                    else {
                        TraslationWantSendJishoushangpinJpanel.this.buyId.setIcon(null);
                    }
                }
            });
            this.add(this.buyId);
            (this.sendOk = new CBGSellBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "确定销售", this)).setBounds(417, 240, 80, 26);
            this.add(this.sendOk);
            this.jScrollPane = new JScrollPane();
            (this.area = new JTextArea()).append("温馨提示：此功能是为了方便玩家更快捷寻找到需要的精品装备、道具，\n");
            this.area.append("避免有人挂太多的普通物品影响寄售的数量和质量特做以下设置；\n");
            this.area.append("1、寄售物品需缴纳%5仙玉，上架时直接从账户扣除，账户仙玉不足无法寄售；\n");
            this.area.append("2、寄售价格在100仙玉-10000000仙玉之间；\n");
            this.area.append("3、购买后请在我的集市中我的货物处取回道具。");
            this.area.setBounds(0, 0, 588, 120);
            this.area.setForeground(Color.white);
            this.area.setFont(UIUtils.TEXT_FONT);
            this.area.setOpaque(false);
            TraslationTableRuleUntil.TableModel(this.jScrollPane, this.area);
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane.setBounds(2, 298, 586, 79);
            this.add(this.jScrollPane);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/170.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 590, 380, this);
        }
        else {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/我要卖-寄售商品w590,h380px，top97,left22px (1).png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 590, 380, this);
        }
    }
    
    public void thGood(Salegoods salegoods) {
        this.salegoods = salegoods;
        if (salegoods == null) {
            this.tupian.setIcon(null);
        }
        else {
            this.tupian.setIcon(CutButtonImage.getCBG((int)salegoods.getSaletype(), salegoods.getSaleskin(), 49, 49));
        }
        this.sale.setText("");
        this.confirm.setText("");
        this.assignID.setText("");
    }
    
    public Salegoods getSalegoods() {
        return this.salegoods;
    }
    
    public void setSalegoods(Salegoods salegoods) {
        this.salegoods = salegoods;
    }
    
    public BigDecimal getJG() {
        BigDecimal j1 = ((NumberDocument)this.sale.getDocument()).getNum();
        BigDecimal j2 = ((NumberDocument)this.confirm.getDocument()).getNum();
        if (j1.compareTo(j2) == 0) {
            return j1;
        }
        return null;
    }
    
    public BigDecimal getZD() {
        if (this.buyId.getIcon() != null) {
            BigDecimal j1 = ((NumberDocument)this.assignID.getDocument()).getNum();
            return j1;
        }
        return new BigDecimal(0);
    }
    
    public void qkPet(RoleSummoning pet) {
        this.traslationWantSendJishoushangpinFenleiJpanel.getTraslationWantSendJishoushangpinFenleiZhaohuanshouJpanel().chang(pet, false);
    }
}
