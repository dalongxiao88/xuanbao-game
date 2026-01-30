package org.cbg.panel;

import org.come.Frame.ZhuFrame;
import org.cbg.frame.TrslationMainJframe;
import org.come.until.UserMessUntil;
import org.cbg.entity.Salegoods;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import org.come.until.AnalysisString;
import java.awt.Color;
import org.come.until.CutButtonImage;
import java.awt.Dimension;
import org.come.entity.RoleSummoning;
import java.math.BigDecimal;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TraslationWantSendJishoushangpinFenleiZhaohuanshouModelJpanel extends JPanel
{
    private JLabel zhanshidikuang;
    private JLabel zhangshi;
    private JLabel mingzi;
    private JLabel dengji;
    private BigDecimal rgid;
    
    public TraslationWantSendJishoushangpinFenleiZhaohuanshouModelJpanel(RoleSummoning pet) {
        this.rgid = pet.getSid();
        this.setPreferredSize(new Dimension(304, 50));
        this.setOpaque(false);
        this.setLayout(null);
        (this.zhanshidikuang = new JLabel()).setBorder(null);
        this.zhanshidikuang.setBounds(20, 5, 39, 39);
        this.zhanshidikuang.setIcon(CutButtonImage.getImage("inkImg/background/10.png", -1, -1));
        this.zhanshidikuang.setOpaque(false);
        this.add(this.zhanshidikuang);
        (this.zhangshi = new JLabel()).setBorder(null);
        this.zhangshi.setBounds(20, 5, 39, 39);
        this.zhangshi.setOpaque(false);
        this.add(this.zhangshi);
        (this.mingzi = new JLabel()).setBorder(null);
        this.mingzi.setForeground(Color.yellow);
        this.mingzi.setBounds(95, 5, 65, 39);
        this.mingzi.setOpaque(false);
        this.add(this.mingzi);
        (this.dengji = new JLabel()).setBorder(null);
        this.dengji.setBounds(210, 5, 55, 39);
        this.dengji.setForeground(Color.yellow);
        this.dengji.setOpaque(false);
        this.add(this.dengji);
        this.chang(pet);
        this.addMouseListener(new gz());
    }
    
    public void chang(RoleSummoning pet) {
        ImageIcon icon1 = CutButtonImage.getCBG(4, pet.getSummoningskin(), 39, 39);
        this.zhangshi.setIcon(icon1);
        this.mingzi.setText(pet.getSummoningname());
        this.dengji.setText(AnalysisString.petLvl((int)pet.getGrade()));
    }
    
    public BigDecimal getRgid() {
        return this.rgid;
    }
    
    public void setRgid(BigDecimal rgid) {
        this.rgid = rgid;
    }
    
    public JLabel getZhanshidikuang() {
        return this.zhanshidikuang;
    }
    
    public void setZhanshidikuang(JLabel zhanshidikuang) {
        this.zhanshidikuang = zhanshidikuang;
    }
    
    public JLabel getZhangshi() {
        return this.zhangshi;
    }
    
    public void setZhangshi(JLabel zhangshi) {
        this.zhangshi = zhangshi;
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
    
    class gz implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            Salegoods salegoods = new Salegoods();
            RoleSummoning pet = UserMessUntil.getPetRgid(TraslationWantSendJishoushangpinFenleiZhaohuanshouModelJpanel.this.rgid);
            if (pet == null) {
                return;
            }
            salegoods.setSaletype(Integer.valueOf(4));
            salegoods.setOtherid(pet.getSid());
            salegoods.setSalename(pet.getSummoningskin());
            salegoods.setSaleskin(pet.getSummoningskin());
            TraslationWantSendCardJpanel traslationWantSendCardJpanel = TrslationMainJframe.getTrslationMainJframe().getTrslationMainJpanel().getTranslationMainCardJpanel().getTraslationWantSentJpanel().getTraslationWantSendCardJpanel();
            traslationWantSendCardJpanel.getTraslationWantSendJishoushangpinJpanel().thGood(salegoods);
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            RoleSummoning pet = UserMessUntil.getPetRgid(TraslationWantSendJishoushangpinFenleiZhaohuanshouModelJpanel.this.rgid);
            if (pet == null) {
                return;
            }
            ZhuFrame.getZhuJpanel().creatpettext(pet);
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            ZhuFrame.getZhuJpanel().pettext();
        }
    }
}
