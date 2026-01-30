package org.cbg.panel;

import java.awt.Graphics;
import org.cbg.mouslisten.CBGmoveMouslisten;
import org.cbg.entity.Roleorder;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.until.CutButtonImage;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.cbg.btn.TrslationBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TraslationMyMainMyorderModelJpanel extends JPanel
{
    private JLabel dingdanhao;
    private JLabel zhanshidikuang;
    private JLabel tupian;
    private JLabel dahuabixia;
    private JLabel danjia;
    private JLabel price;
    private TrslationBtn buy;
    private int stateOrNo;
    private ImageIcon icon;
    
    public TraslationMyMainMyorderModelJpanel() {
        this.stateOrNo = 1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setLayout(null);
            this.setPreferredSize(new Dimension(550, 50));
            this.setOpaque(false);
            this.dingdanhao = new JLabel("12312568411");
            this.tupian = new JLabel();
            this.zhanshidikuang = new JLabel();
            this.dahuabixia = new JLabel("四千八百万");
            this.danjia = new JLabel("48.6万两/元");
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(70, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", 39, 39));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel("￥100.0");
            this.buy = new TrslationBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "购买");
            this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
            this.dahuabixia.setForeground(Color.white);
            this.danjia.setFont(UIUtils.TEXT_FONT2);
            this.danjia.setForeground(Color.white);
            this.price.setFont(UIUtils.TEXT_FONT2);
            this.price.setForeground(Color.white);
            this.dingdanhao.setBounds(2, 15, 70, 20);
            this.dingdanhao.setForeground(Color.white);
            this.dingdanhao.setFont(UIUtils.TEXT_FONT);
            this.tupian.setBounds(70, 5, 39, 39);
            this.dahuabixia.setBounds(155, 15, 100, 24);
            this.price.setBounds(280, 0, 125, 40);
            this.danjia.setBounds(380, 0, 125, 40);
            this.buy.setBounds(470, 10, 58, 30);
            this.buy.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FormsManagement.showForm(79);
                }
            });
            this.add(this.buy);
            this.add(this.dingdanhao);
            this.add(this.tupian);
            this.add(this.dahuabixia);
            this.add(this.danjia);
            this.add(this.price);
        }
        else {
            this.setLayout(null);
            this.setPreferredSize(new Dimension(550, 50));
            this.setOpaque(false);
            this.dingdanhao = new JLabel("12312568411");
            this.tupian = new JLabel();
            this.zhanshidikuang = new JLabel();
            this.dahuabixia = new JLabel("四千八百万");
            this.danjia = new JLabel("48.6万两/元");
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(70, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", 39, 39));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel("￥100.0");
            this.buy = new TrslationBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "购买");
            this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
            this.dahuabixia.setForeground(Color.white);
            this.danjia.setFont(UIUtils.TEXT_FONT2);
            this.danjia.setForeground(Color.white);
            this.price.setFont(UIUtils.TEXT_FONT2);
            this.price.setForeground(Color.white);
            this.dingdanhao.setBounds(2, 15, 70, 20);
            this.dingdanhao.setForeground(Color.white);
            this.dingdanhao.setFont(UIUtils.TEXT_FONT);
            this.tupian.setBounds(70, 5, 39, 39);
            this.dahuabixia.setBounds(155, 15, 100, 24);
            this.price.setBounds(280, 0, 125, 40);
            this.danjia.setBounds(380, 0, 125, 40);
            this.buy.setBounds(470, 10, 58, 30);
            this.buy.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FormsManagement.showForm(79);
                }
            });
            this.add(this.buy);
            this.add(this.dingdanhao);
            this.add(this.tupian);
            this.add(this.dahuabixia);
            this.add(this.danjia);
            this.add(this.price);
        }
    }
    
    public TraslationMyMainMyorderModelJpanel(Roleorder roleorder) {
        this.stateOrNo = 1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setLayout(null);
            this.setPreferredSize(new Dimension(550, 50));
            this.setOpaque(false);
            (this.dingdanhao = new JLabel(roleorder.getOrderid().toString())).setHorizontalAlignment(0);
            this.tupian = new JLabel();
            this.zhanshidikuang = new JLabel();
            this.dahuabixia = new JLabel(roleorder.getSalename());
            this.danjia = new JLabel(roleorder.getSaleprice().toString());
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(70, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", -1, -1));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel();
            this.buy = new TrslationBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "购买");
            switch ((int)roleorder.getStatus()) {
                case 1: {
                    this.price.setText("未付钱");
                    this.buy.setText("付 款");
                    break;
                }
                case 2: {
                    this.price.setText("超时");
                    this.buy.setVisible(false);
                    break;
                }
                case 3: {
                    this.price.setText("已付钱");
                    this.buy.setVisible(false);
                    break;
                }
                case 4: {
                    this.price.setText("已取货");
                    this.buy.setVisible(false);
                    break;
                }
            }
            this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
            this.dahuabixia.setForeground(Color.white);
            this.danjia.setFont(UIUtils.TEXT_FONT2);
            this.danjia.setForeground(Color.white);
            this.price.setFont(UIUtils.TEXT_FONT2);
            this.price.setForeground(Color.white);
            this.tupian.setIcon(CutButtonImage.getCBG((int)roleorder.getSaletype(), roleorder.getSaleskin(), 39, 39));
            this.dingdanhao.setBounds(2, 15, 70, 20);
            this.dingdanhao.setForeground(Color.white);
            this.dingdanhao.setFont(UIUtils.TEXT_FONT);
            this.tupian.setBounds(70, 5, 39, 39);
            this.dahuabixia.setBounds(155, 5, 100, 24);
            this.price.setBounds(280, 0, 125, 40);
            this.danjia.setBounds(380, 0, 125, 40);
            this.buy.setBounds(470, 10, 58, 30);
            this.buy.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FormsManagement.showForm(79);
                }
            });
            this.add(this.buy);
            this.add(this.dingdanhao);
            this.add(this.tupian);
            this.add(this.dahuabixia);
            this.add(this.danjia);
            this.add(this.price);
            this.tupian.addMouseListener(new CBGmoveMouslisten(roleorder.getSaletype(), roleorder.getOtherid()));
        }
        else {
            this.setLayout(null);
            this.setPreferredSize(new Dimension(550, 50));
            this.setOpaque(false);
            (this.dingdanhao = new JLabel(roleorder.getOrderid().toString())).setHorizontalAlignment(0);
            this.tupian = new JLabel();
            this.zhanshidikuang = new JLabel();
            this.dahuabixia = new JLabel(roleorder.getSalename());
            this.danjia = new JLabel(roleorder.getSaleprice().toString());
            this.zhanshidikuang.setBorder(null);
            this.zhanshidikuang.setBounds(70, 5, 39, 39);
            this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", -1, -1));
            this.zhanshidikuang.setOpaque(false);
            this.add(this.zhanshidikuang);
            this.price = new JLabel();
            this.buy = new TrslationBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, "购买");
            switch ((int)roleorder.getStatus()) {
                case 1: {
                    this.price.setText("未付钱");
                    this.buy.setText("付 款");
                    break;
                }
                case 2: {
                    this.price.setText("超时");
                    this.buy.setVisible(false);
                    break;
                }
                case 3: {
                    this.price.setText("已付钱");
                    this.buy.setVisible(false);
                    break;
                }
                case 4: {
                    this.price.setText("已取货");
                    this.buy.setVisible(false);
                    break;
                }
            }
            this.dahuabixia.setFont(UIUtils.TEXT_FONT2);
            this.dahuabixia.setForeground(Color.white);
            this.danjia.setFont(UIUtils.TEXT_FONT2);
            this.danjia.setForeground(Color.white);
            this.price.setFont(UIUtils.TEXT_FONT2);
            this.price.setForeground(Color.white);
            this.tupian.setIcon(CutButtonImage.getCBG((int)roleorder.getSaletype(), roleorder.getSaleskin(), 39, 39));
            this.dingdanhao.setBounds(2, 15, 70, 20);
            this.dingdanhao.setForeground(Color.white);
            this.dingdanhao.setFont(UIUtils.TEXT_FONT);
            this.tupian.setBounds(70, 5, 39, 39);
            this.dahuabixia.setBounds(155, 5, 100, 24);
            this.price.setBounds(280, 0, 125, 40);
            this.danjia.setBounds(380, 0, 125, 40);
            this.buy.setBounds(470, 10, 58, 30);
            this.buy.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FormsManagement.showForm(79);
                }
            });
            this.add(this.buy);
            this.add(this.dingdanhao);
            this.add(this.tupian);
            this.add(this.dahuabixia);
            this.add(this.danjia);
            this.add(this.price);
            this.tupian.addMouseListener(new CBGmoveMouslisten(roleorder.getSaletype(), roleorder.getOtherid()));
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("inkImg/background/12.png", 548, 2);
            }
            g.drawImage(this.icon.getImage(), 0, 48, 548, 2, this);
        }
        else {
            super.paintComponent(g);
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("inkImg/background/12.png", 548, 2);
            }
            g.drawImage(this.icon.getImage(), 0, 48, 548, 2, this);
        }
    }
}
