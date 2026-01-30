package org.come.Jpanel;

import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.tool.tcpimg.UIUtils;
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

public class RechargeVIPGoodsJpanel extends JPanel
{
    private VipShopBtn vipShopBtn;
    private String name;
    private RechargeVIPJpanel rechargeVIPJpanel;
    private PayvipBean payvipBean;
    private JLabel jLabel;
    private Mlistener mlistener;
    private ImageIcon iconBack;
    private ImageIcon iconGift;
    private ImageIcon iconVip;
    private ImageIcon icon2;
    
    public RechargeVIPGoodsJpanel() {
        this.name = "V1会员奖励";
        this.setPreferredSize(new Dimension(36, 20));
        this.setOpaque(false);
        this.setLayout(null);
        this.getVipShopBtn();
        this.addMouseListener(this.mlistener = new Mlistener(this.payvipBean));
    }
    
    public void showGoods(PayvipBean payvipBean) {
        this.payvipBean = payvipBean;
        if (payvipBean != null) {
            this.mlistener.setPayvipBean(payvipBean);
            this.getIconGift().setImage(CutButtonImage.getImage("inkImg/background/gift-icon.png", -1, -1).getImage());
            this.name = "成长" + payvipBean.getPaynum() + "礼包";
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
        if (this.payvipBean != null) {
            if (this.iconGift == null) {
                this.iconGift = CutButtonImage.getImage("img/item/S13.png", -1, -1);
            }
            g.drawImage(this.iconGift.getImage(), 20, 10, 69, 96, this);
            if (this.name != null) {
                g.setFont(UIUtils.TEXT_BOLD_FONT);
                g.drawString(this.name, 0, 110);
            }
        }
    }
    
    public void paintPeople() {
    }
    
    public VipShopBtn getVipShopBtn() {
        if (this.vipShopBtn == null) {
            (this.vipShopBtn = new VipShopBtn("inkImg/hongmu/lingqu.png", 1, 57, this)).setBounds(20, 120, 65, 26);
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
            if (e.isMetaDown()) {
                FormsManagement.HideForm(3002);
            }
            else {
                if (this.payvipBean == null) {
                    return;
                }
                RechargeVIPJpanel.paintPeopleGoods(this.payvipBean);
                RechargeVIPGoodsJpanel.this.paintPeople();
            }
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
