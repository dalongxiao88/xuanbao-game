package org.cbg.panel;

import org.come.Frame.ZhuFrame;
import org.come.model.Lingbao;
import org.come.entity.Goodstable;
import org.cbg.frame.TrslationMainJframe;
import org.come.until.Goodtype;
import org.cbg.entity.Salegoods;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.tool.role.RoleLingFa;
import org.come.until.GoodsListFromServerUntil;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TraslationWantSendJishoushangpinFenleiWupinJpanel extends JPanel
{
    private int type;
    private int ys;
    private JLabel[] labels;
    private ImageIcon icon;
    
    public TraslationWantSendJishoushangpinFenleiWupinJpanel() {
        this.type = 0;
        this.setPreferredSize(new Dimension(310, 259));
        this.setBackground(null);
        this.setOpaque(false);
        this.setLayout(null);
        this.labels = new JLabel[30];
        for (int i = 0; i < this.labels.length; ++i) {
            (this.labels[i] = new JLabel()).setBounds(2 + i % 6 * 51, 2 + i / 6 * 51, 49, 49);
            this.labels[i].addMouseListener(new gz(i));
            this.add(this.labels[i]);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background/172.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 310, 259, this);
        if (this.type == 0) {
            GoodsListFromServerUntil.drawCBG(g, 2, 2, this.ys);
        }
        else if (this.type == 1) {
            RoleLingFa.getRoleLingFa().drawCBG(g, 2, 2, this.ys);
        }
    }
    
    public void change(int type) {
        this.ys = 0;
        this.type = type;
    }
    
    class gz implements MouseListener
    {
        private int p;
        
        public gz(int p) {
            this.p = p;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            Salegoods salegoods = new Salegoods();
            if (TraslationWantSendJishoushangpinFenleiWupinJpanel.this.type == 0) {
                Goodstable good = GoodsListFromServerUntil.getCBG(TraslationWantSendJishoushangpinFenleiWupinJpanel.this.ys, this.p);
                if (good == null) {
                    return;
                }
                int Equipment = Goodtype.EquipmentType((long)good.getType());
                if (Equipment == -1) {
                    salegoods.setSaletype(Integer.valueOf(3));
                }
                else {
                    salegoods.setSaletype(Integer.valueOf(5));
                }
                salegoods.setSalename(good.getGoodsname());
                salegoods.setOtherid(good.getRgid());
                salegoods.setSaleskin(good.getSkin());
            }
            else {
                Lingbao lingbao = RoleLingFa.getRoleLingFa().getCBG(TraslationWantSendJishoushangpinFenleiWupinJpanel.this.ys, this.p);
                if (lingbao == null) {
                    return;
                }
                salegoods.setSalename(lingbao.getBaoname());
                salegoods.setSaletype(Integer.valueOf(6));
                salegoods.setOtherid(lingbao.getBaoid());
                salegoods.setSaleskin(lingbao.getSkin());
            }
            TraslationWantSendCardJpanel traslationWantSendCardJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationWantSentJpanel().getTraslationWantSendCardJpanel();
            traslationWantSendCardJpanel.getTraslationWantSendJishoushangpinJpanel().thGood(salegoods);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            if (TraslationWantSendJishoushangpinFenleiWupinJpanel.this.type == 0) {
                Goodstable good = GoodsListFromServerUntil.getCBG(TraslationWantSendJishoushangpinFenleiWupinJpanel.this.ys, this.p);
                if (good == null) {
                    return;
                }
                ZhuFrame.getZhuJpanel().creatgoodtext(good);
            }
            else {
                Lingbao lingbao = RoleLingFa.getRoleLingFa().getCBG(TraslationWantSendJishoushangpinFenleiWupinJpanel.this.ys, this.p);
                if (lingbao == null) {
                    return;
                }
                ZhuFrame.getZhuJpanel().creatlingtext(lingbao);
            }
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            if (TraslationWantSendJishoushangpinFenleiWupinJpanel.this.type == 0) {
                ZhuFrame.getZhuJpanel().cleargoodtext();
            }
            else {
                ZhuFrame.getZhuJpanel().clearlingtext();
            }
        }
    }
}
