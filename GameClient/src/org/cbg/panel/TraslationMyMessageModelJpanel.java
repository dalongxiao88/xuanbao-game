package org.cbg.panel;

import java.awt.Graphics;
import java.text.SimpleDateFormat;
import org.cbg.entity.Message;
import org.cbg.frame.TrslationMainJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.CutButtonImage;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TraslationMyMessageModelJpanel extends JPanel
{
    private JLabel biaoti;
    private JLabel mingzi;
    private JLabel dengji;
    private JLabel quanxuankuang;
    private JLabel gouxuan;
    private Integer gouxuanzhuangtai;
    private ImageIcon icon;
    
    public TraslationMyMessageModelJpanel() {
        this.gouxuanzhuangtai = Integer.valueOf(1);
        this.setPreferredSize(new Dimension(304, 50));
        this.setOpaque(false);
        this.setLayout(null);
        (this.biaoti = new JLabel("该商品已下架")).setBorder(null);
        this.biaoti.setBounds(40, 5, 150, 25);
        this.biaoti.setHorizontalAlignment(0);
        this.biaoti.setOpaque(false);
        this.biaoti.setForeground(Color.white);
        this.add(this.biaoti);
        (this.mingzi = new JLabel("名字")).setBorder(null);
        this.mingzi.setBounds(315, 5, 60, 39);
        this.mingzi.setOpaque(false);
        this.mingzi.setForeground(Color.white);
        this.add(this.mingzi);
        (this.dengji = new JLabel("120")).setBorder(null);
        this.dengji.setBounds(450, 5, 60, 39);
        this.dengji.setOpaque(false);
        this.dengji.setForeground(Color.white);
        this.add(this.dengji);
        (this.gouxuan = new JLabel()).setBounds(13, 12, 15, 15);
        this.gouxuan.setOpaque(false);
        this.gouxuan.setIcon(null);
        this.gouxuan.setName("1");
        this.add(this.gouxuan);
        (this.quanxuankuang = new JLabel()).setBounds(13, 12, 15, 15);
        this.quanxuankuang.setOpaque(false);
        this.quanxuankuang.setIcon(CutButtonImage.getImage("inkImg/button/14.png", 15, 15));
        this.add(this.quanxuankuang);
        this.gouxuan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TraslationMyMessageJpanel traslationMyMessageJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationMyMessageJpanel();
                if (TraslationMyMessageModelJpanel.this.gouxuan.getName().equals("1")) {
                    TraslationMyMessageModelJpanel.this.gouxuan.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                    TraslationMyMessageModelJpanel.this.gouxuan.setName("0");
                    TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() + 1);
                    if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() == 10) {
                        traslationMyMessageJpanel.getGouxuan().setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        traslationMyMessageJpanel.getGouxuan().setName("0");
                    }
                }
                else {
                    TraslationMyMessageModelJpanel.this.gouxuan.setIcon(null);
                    TraslationMyMessageModelJpanel.this.gouxuan.setName("1");
                    TrslationMainJframe.getTrslationMainJframe().setGoodsGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() - 1);
                    if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() != 10) {
                        traslationMyMessageJpanel.getGouxuan().setIcon(null);
                        traslationMyMessageJpanel.getGouxuan().setName("1");
                    }
                }
            }
        });
    }
    
    public TraslationMyMessageModelJpanel(Message message) {
        this.gouxuanzhuangtai = Integer.valueOf(1);
        this.setPreferredSize(new Dimension(304, 50));
        this.setOpaque(false);
        this.setLayout(null);
        (this.biaoti = new JLabel()).setBorder(null);
        this.biaoti.setBounds(40, 5, 150, 25);
        this.biaoti.setHorizontalAlignment(0);
        this.biaoti.setOpaque(false);
        this.biaoti.setForeground(Color.white);
        this.add(this.biaoti);
        (this.mingzi = new JLabel("系统提示")).setBorder(null);
        this.mingzi.setBounds(290, 5, 60, 39);
        this.mingzi.setOpaque(false);
        this.mingzi.setForeground(Color.white);
        this.add(this.mingzi);
        (this.dengji = new JLabel("120")).setBorder(null);
        this.dengji.setBounds(400, 5, 150, 39);
        this.dengji.setOpaque(false);
        this.dengji.setForeground(Color.white);
        this.add(this.dengji);
        this.biaoti.setName(message.getMesid().toString());
        this.biaoti.setText(message.getMescontent());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dengji.setText(sdf.format(message.getGettime()));
        (this.gouxuan = new JLabel()).setBounds(13, 12, 15, 15);
        this.gouxuan.setOpaque(false);
        this.gouxuan.setIcon(null);
        this.gouxuan.setName("1");
        this.add(this.gouxuan);
        (this.quanxuankuang = new JLabel()).setBounds(13, 12, 15, 15);
        this.quanxuankuang.setOpaque(false);
        this.quanxuankuang.setIcon(CutButtonImage.getImage("inkImg/button/14.png", 15, 15));
        this.add(this.quanxuankuang);
        this.gouxuan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TraslationMyMessageJpanel traslationMyMessageJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationMyMessageJpanel();
                if (TraslationMyMessageModelJpanel.this.gouxuan.getName().equals("1")) {
                    TraslationMyMessageModelJpanel.this.gouxuan.setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                    TraslationMyMessageModelJpanel.this.gouxuan.setName("0");
                    TrslationMainJframe.getTrslationMainJframe().setXiaoxiGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getXiaoxiGouxuangeshu() + 1);
                    if (TrslationMainJframe.getTrslationMainJframe().getGoodsGouxuangeshu() == TrslationMainJframe.getTrslationMainJframe().getXiaoxiGeshuMax()) {
                        traslationMyMessageJpanel.getGouxuan().setIcon(CutButtonImage.getImage("inkImg/button/13.png", 15, 15));
                        traslationMyMessageJpanel.getGouxuan().setName("0");
                    }
                }
                else {
                    TraslationMyMessageModelJpanel.this.gouxuan.setIcon(null);
                    TraslationMyMessageModelJpanel.this.gouxuan.setName("1");
                    TrslationMainJframe.getTrslationMainJframe().setXiaoxiGouxuangeshu(TrslationMainJframe.getTrslationMainJframe().getXiaoxiGouxuangeshu() - 1);
                    if (TrslationMainJframe.getTrslationMainJframe().getXiaoxiGouxuangeshu() != TrslationMainJframe.getTrslationMainJframe().getXiaoxiGeshuMax()) {
                        traslationMyMessageJpanel.getGouxuan().setIcon(null);
                        traslationMyMessageJpanel.getGouxuan().setName("1");
                    }
                }
            }
        });
    }
    
    public JLabel getBiaoti() {
        return this.biaoti;
    }
    
    public void setBiaoti(JLabel biaoti) {
        this.biaoti = biaoti;
    }
    
    public JLabel getMingzi() {
        return this.mingzi;
    }
    
    public void setMingzi(JLabel mingzi) {
        this.mingzi = mingzi;
    }
    
    public JLabel getDengji() {
        return this.dengji;
    }
    
    public void setDengji(JLabel dengji) {
        this.dengji = dengji;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = CutButtonImage.getImage("inkImg/background/12.png", 548, 2);
        }
        g.drawImage(this.icon.getImage(), 0, 48, 548, 2, this);
    }
}
