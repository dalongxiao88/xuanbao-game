package org.come.mouslisten;

import javax.swing.ImageIcon;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.Music;
import com.tool.btn.AuctionGoodsExchangeBtn;
import org.cbg.mouslisten.GodsForGodMouslisten;
import org.come.until.CutButtonImage;
import org.come.entity.Goodstable;
import java.awt.event.MouseEvent;
import org.come.Jpanel.AuctionGoodsExchangeJpanel;
import org.come.bean.AuctionGoodsForGoodsBean;
import org.come.Jpanel.AuctionGoodsForDelGoodsJpanel;
import org.come.Jpanel.AuctionGoodForGoodJpanel;
import java.awt.event.MouseListener;

public class AuctionGoodsForGoodsChooseGetMouslisten implements MouseListener
{
    private final AuctionGoodForGoodJpanel[] goodForGoodJpanel;
    private final int chosetype;
    private final AuctionGoodsForDelGoodsJpanel auctionGoodsForDelGoodsJpanel;
    private final AuctionGoodsForGoodsBean goodsbean;
    private final AuctionGoodsExchangeJpanel goodsExchangeJpanel;
    
    public AuctionGoodsForGoodsChooseGetMouslisten(AuctionGoodForGoodJpanel[] goodForGoodJpanel, int chosei, AuctionGoodsForDelGoodsJpanel auctionGoodsForDelGoodsJpanel, AuctionGoodsForGoodsBean goodsbean, AuctionGoodsExchangeJpanel goodsExchangeJpanel) {
        this.chosetype = chosei;
        this.goodForGoodJpanel = goodForGoodJpanel;
        this.auctionGoodsForDelGoodsJpanel = auctionGoodsForDelGoodsJpanel;
        this.goodsbean = goodsbean;
        this.goodsExchangeJpanel = goodsExchangeJpanel;
    }
    
    public AuctionGoodsExchangeJpanel getAuctionGoodsExchangeJpanel() {
        return this.goodsExchangeJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < this.goodForGoodJpanel.length; ++i) {
            if (i != this.chosetype) {
                this.goodForGoodJpanel[i].setBorder(null);
                this.goodForGoodJpanel[i].repaint();
            }
        }
        Goodstable goodstable = (Goodstable)this.goodsbean.getGetApplyGoods().get(0);
        ImageIcon image = CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 57, 57);
        this.getAuctionGoodsExchangeJpanel().getSelectIcon().setIcon(image);
        GodsForGodMouslisten goomouslinte = new GodsForGodMouslisten(goodstable);
        this.getAuctionGoodsExchangeJpanel().getSelectIcon().addMouseListener(goomouslinte);
        this.getAuctionGoodsExchangeJpanel().getSelectIcon().addMouseListener(goomouslinte);
        this.getAuctionGoodsExchangeJpanel().getSelectText().setText(this.goodsbean.getNameString());
        this.auctionGoodsForDelGoodsJpanel.repaint();
        AuctionGoodsExchangeBtn.goodsbean = this.goodsbean;
        this.auctionGoodsForDelGoodsJpanel.removeAll();
        this.auctionGoodsForDelGoodsJpanel.initGoodMes(this.goodsbean);
        Music.addyinxiao("打开窗口.mp3");
        String mes = Agreement.getAgreement().auctionGoods("Find" + this.goodsbean.getAuctionExchange().geteId());
        SendMessageUntil.toServer(mes);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.goodsbean != null) {
            AuctionGoodsExchangeJpanel.forGoodsBean = this.goodsbean;
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
