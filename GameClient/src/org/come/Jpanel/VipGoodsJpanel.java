package org.come.Jpanel;

import org.come.until.FormsManagement;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;
import java.awt.Graphics;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.CutButtonImage;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.come.bean.PayvipBean;
import com.tool.btn.VipShopBtn;
import javax.swing.JPanel;

public class VipGoodsJpanel extends JPanel
{
    private VipShopBtn vipShopBtn;
    private String name;
    private PayvipBean payvipBean;
    private JLabel jLabel;
    private Mlistener mlistener;
    private ImageIcon iconBack;
    private ImageIcon iconGift;
    private ImageIcon iconVip;
    
    public VipGoodsJpanel() {
        this.name = "V1会员奖励";
        this.setPreferredSize(new Dimension(136, 190));
        this.setOpaque(false);
        this.setLayout(null);
        this.getVipShopBtn();
        this.addMouseListener(this.mlistener = new Mlistener(this.payvipBean));
    }
    
    public void showGoods(PayvipBean payvipBean) {
        this.payvipBean = payvipBean;
        if (payvipBean != null) {
            this.mlistener.setPayvipBean(payvipBean);
            this.getIconVip().setImage(CutButtonImage.getImage("img/item/S" + (100 + (int)payvipBean.getGrade()) + ".png", -1, -1).getImage());
            this.getIconGift().setImage(CutButtonImage.getImage("img/item/S" + (9 + (int)payvipBean.getGrade()) + ".png", -1, -1).getImage());
            this.name = "V" + payvipBean.getGrade() + "会员奖励";
        }
        else {
            this.vipShopBtn.setBtn(0);
        }
    }
    
    public void exchangeGoods() {
        String sendmes = Agreement.getAgreement().VipgradesureAgreement("v" + this.payvipBean.getGrade());
        SendMessageUntil.toServer(sendmes);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.payvipBean != null) {
                if (this.iconBack == null) {
                    this.iconBack = CutButtonImage.getImage("inkImg/background/S3?.png", -1, -1);
                }
                g.drawImage(this.iconBack.getImage(), 0, 0, 136, 134, this);
                if (this.iconGift == null) {
                    this.iconGift = CutButtonImage.getImage("img/item/S13.png", -1, -1);
                }
                g.drawImage(this.iconGift.getImage(), 20, 20, 100, 100, this);
                if (this.iconVip == null) {
                    this.iconVip = CutButtonImage.getImage("img/item/S23.png", -1, -1);
                }
                g.drawImage(this.iconVip.getImage(), 10, 10, 30, 25, this);
                if (this.name != null) {
                    g.setFont(UIUtils.TEXT_HY19);
                    g.drawString(this.name, 10, 150);
                }
            }
        }
        else if (this.payvipBean != null) {
            if (this.iconBack == null) {
                this.iconBack = CutButtonImage.getImage("inkImg/hongmu/VIPkuang.png", -1, -1);
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 136, 134, this);
            if (this.iconGift == null) {
                this.iconGift = CutButtonImage.getImage("img/item/S13.png", -1, -1);
            }
            g.drawImage(this.iconGift.getImage(), 20, 20, 100, 100, this);
            if (this.iconVip == null) {
                this.iconVip = CutButtonImage.getImage("img/item/S23.png", -1, -1);
            }
            g.drawImage(this.iconVip.getImage(), 10, 10, 30, 25, this);
            if (this.name != null) {
                g.setFont(UIUtils.TEXT_FONT78);
                g.setColor(UIUtils.COLOR_HURTB3);
                g.drawString(this.name, 25, 150);
            }
        }
    }
    
    public VipShopBtn getVipShopBtn() {
        if (this.vipShopBtn == null) {
            (this.vipShopBtn = new VipShopBtn("inkImg/hongmu/lingqu.png", 1, 1, this)).setBounds(38, 160, 65, 26);
            this.add(this.vipShopBtn);
        }
        return this.vipShopBtn;
    }
    
    public void setVipShopBtn(VipShopBtn vipShopBtn) {
        this.vipShopBtn = vipShopBtn;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    public ImageIcon getIconGift() {
        if (this.iconGift == null) {
            this.iconGift = new ImageIcon();
        }
        return this.iconGift;
    }
    
    public void setIconGift(ImageIcon iconGift) {
        this.iconGift = iconGift;
    }
    
    public ImageIcon getIconVip() {
        if (this.iconVip == null) {
            this.iconVip = new ImageIcon();
        }
        return this.iconVip;
    }
    
    public void setIconVip(ImageIcon iconVip) {
        this.iconVip = iconVip;
    }
    
    public PayvipBean getPayvipBean() {
        return this.payvipBean;
    }
    
    public void setPayvipBean(PayvipBean payvipBean) {
        this.payvipBean = payvipBean;
    }
    
    public Mlistener getMlistener() {
        return this.mlistener;
    }
    
    public void setMlistener(Mlistener mlistener) {
        this.mlistener = mlistener;
    }
    
    class Mlistener implements MouseListener
    {
        private PayvipBean payvipBean;
        
        public Mlistener(PayvipBean payvipBean) {
            this.payvipBean = payvipBean;
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
            if (this.payvipBean == null) {
                return;
            }
            ZhuFrame.getZhuJpanel().creatgoodtext(this.payvipBean);
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            FormsManagement.HideForm(24);
        }
        
        public PayvipBean getPayvipBean() {
            return this.payvipBean;
        }
        
        public void setPayvipBean(PayvipBean payvipBean) {
            this.payvipBean = payvipBean;
        }
    }
}
