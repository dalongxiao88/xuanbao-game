package org.gemstone.panel;

import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import org.come.until.CutButtonImage;
import java.math.BigDecimal;
import org.come.until.AccessSuitMsgUntil;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Goodtype;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.entity.Goodstable;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GemMakeTabPanel extends JPanel
{
    private JLabel[] mountLabel;
    private GemMakeTabMouseListener[] MouseListeners;
    private JLabel goodsName;
    private Goodstable good;
    private int type;
    private ImageIcon icon1;
    private ImageIcon icon2;
    
    public GemMakeTabPanel(Goodstable good) {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(160, 67));
            this.setOpaque(false);
            this.setLayout(null);
            (this.goodsName = new JLabel()).setBounds(68, 8, 80, 20);
            this.goodsName.setForeground(new Color(187, 165, 75));
            this.goodsName.setFont(UIUtils.TEXT_HY16);
            this.add(this.goodsName);
            this.mountLabel = new JLabel[4];
            this.MouseListeners = new GemMakeTabMouseListener[4];
            (this.mountLabel[0] = new JLabel()).setBounds(10, 8, 53, 51);
            this.MouseListeners[0] = new GemMakeTabMouseListener(null);
            this.mountLabel[0].addMouseListener(this.MouseListeners[0]);
            this.add(this.mountLabel[0]);
            for (int i = 1; i < this.mountLabel.length; ++i) {
                (this.mountLabel[i] = new JLabel()).setBounds(68 + (i - 1) * 29, 33, 25, 25);
                this.mountLabel[i].setOpaque(false);
                this.MouseListeners[i] = new GemMakeTabMouseListener(null);
                this.mountLabel[i].addMouseListener(this.MouseListeners[i]);
                this.add(this.mountLabel[i]);
            }
            this.SX(good);
        }
        else {
            this.setPreferredSize(new Dimension(150, 68));
            this.setOpaque(false);
            this.setLayout(null);
            (this.goodsName = new JLabel()).setBounds(68, 8, 80, 20);
            this.goodsName.setForeground(new Color(187, 165, 75));
            this.goodsName.setFont(UIUtils.TEXT_HY16);
            this.add(this.goodsName);
            this.mountLabel = new JLabel[4];
            this.MouseListeners = new GemMakeTabMouseListener[4];
            (this.mountLabel[0] = new JLabel()).setBounds(10, 8, 53, 51);
            this.MouseListeners[0] = new GemMakeTabMouseListener(null);
            this.mountLabel[0].addMouseListener(this.MouseListeners[0]);
            this.add(this.mountLabel[0]);
            for (int i = 1; i < this.mountLabel.length; ++i) {
                (this.mountLabel[i] = new JLabel()).setBounds(68 + (i - 1) * 29, 33, 25, 25);
                this.mountLabel[i].setOpaque(false);
                this.MouseListeners[i] = new GemMakeTabMouseListener(null);
                this.mountLabel[i].addMouseListener(this.MouseListeners[i]);
                this.add(this.mountLabel[i]);
            }
            this.SX(good);
        }
    }
    
    public void SX(Goodstable good) {
        this.good = good;
        this.type = Goodtype.EquipmentType((long)good.getType());
        this.goodsName.setText((this.type == 0) ? "武器" : ((this.type == 3) ? "衣服" : ((this.type == 1) ? "帽子" : ((this.type == 2) ? "项链" : "鞋子"))));
        this.mountLabel[0].setIcon(GoodsListFromServerUntil.imgpathAdaptive(good.getSkin(), 49, 49));
        this.MouseListeners[0].rgid = good.getRgid();
        for (int i = 1; i < 4; ++i) {
            this.MouseListeners[i].rgid = null;
            this.mountLabel[i].setIcon(null);
        }
        String extra = AccessSuitMsgUntil.getExtra(good.getValue(), "宝石属性");
        if (extra != null) {
            String[] extras = extra.split("&");
            for (int j = 1; j < extras.length; ++j) {
                BigDecimal id = new BigDecimal(extras[j]);
                Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.fushis.get(id);
                if (goodstable != null) {
                    int path = Goodtype.GemPath(this.type, (long)goodstable.getType());
                    if (path != 0) {
                        this.mountLabel[path].setIcon(CutButtonImage.getGemstoneIcon(goodstable.getSkin(), 25, 25));
                        this.MouseListeners[path].rgid = id;
                    }
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.type == 0) {
                if (this.icon1 == null) {
                    this.icon1 = new ImageIcon("inkImg/background1/B270.png");
                }
                g.drawImage(this.icon1.getImage(), 0, 0, 160, 67, this);
            }
            else {
                if (this.icon2 == null) {
                    this.icon2 = new ImageIcon("inkImg/background1/B270.png");
                }
                g.drawImage(this.icon2.getImage(), 0, 0, 160, 67, this);
            }
        }
        else if (this.type == 0) {
            if (this.icon1 == null) {
                this.icon1 = new ImageIcon("img/gemstone/选项卡3个宝石w150,h68px.png");
            }
            g.drawImage(this.icon1.getImage(), 0, 0, 150, 68, this);
        }
        else {
            if (this.icon2 == null) {
                this.icon2 = new ImageIcon("img/gemstone/选项卡2个宝石w150,h68px.png");
            }
            g.drawImage(this.icon2.getImage(), 0, 0, 150, 68, this);
        }
    }
    
    public Goodstable getGood() {
        return this.good;
    }
    
    class GemMakeTabMouseListener implements MouseListener
    {
        private BigDecimal rgid;
        
        public GemMakeTabMouseListener(BigDecimal rgid) {
            this.rgid = rgid;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            if (this.rgid != null) {
                Goodstable goodstable = GoodsListFromServerUntil.getRgid(this.rgid);
                if (goodstable != null) {
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
            }
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            ZhuFrame.getZhuJpanel().cleargoodtext();
        }
    }
}
