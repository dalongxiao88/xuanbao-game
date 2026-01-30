package org.cbg.mouslisten;

import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import com.tool.tcpimg.InputBean;
import org.come.Frame.ZhuFrame;
import org.come.until.UserData;
import org.come.entity.Goodstable;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.awt.event.MouseListener;

public class CBGmoveMouslisten implements MouseListener
{
    private Integer saletype;
    private BigDecimal otherid;
    
    public CBGmoveMouslisten(Integer saletype, BigDecimal otherid) {
        this.saletype = saletype;
        this.otherid = otherid;
    }
    
    public void qh(Integer saletype, BigDecimal otherid) {
        this.saletype = saletype;
        this.otherid = otherid;
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
        if ((int)this.saletype == 2) {
            Goodstable goodstable = new Goodstable();
            goodstable.setType(Long.valueOf(100L));
            goodstable.setSkin("8");
            goodstable.setGoodsname("大话币");
            goodstable.setInstruction(UserData.getMS(this.otherid));
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
        else if ((int)this.saletype == 3 | (int)this.saletype == 5) {
            InputBean inputBean = new InputBean();
            inputBean.setType(2);
            inputBean.setId(this.otherid);
            String sendmes = Agreement.getAgreement().richMAgreement(GsonUtil.getGsonUtil().getgson().toJson(inputBean));
            SendMessageUntil.toServer(sendmes);
        }
        else if ((int)this.saletype == 4) {
            InputBean inputBean = new InputBean();
            inputBean.setType(3);
            inputBean.setId(this.otherid);
            String sendmes = Agreement.getAgreement().richMAgreement(GsonUtil.getGsonUtil().getgson().toJson(inputBean));
            SendMessageUntil.toServer(sendmes);
        }
        else if ((int)this.saletype == 6) {
            InputBean inputBean = new InputBean();
            inputBean.setType(4);
            inputBean.setId(this.otherid);
            String sendmes = Agreement.getAgreement().richMAgreement(GsonUtil.getGsonUtil().getgson().toJson(inputBean));
            SendMessageUntil.toServer(sendmes);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if ((int)this.saletype == 2 || ((int)this.saletype == 3 | (int)this.saletype == 5)) {
            ZhuFrame.getZhuJpanel().cleargoodtext();
        }
        else if ((int)this.saletype == 4) {
            ZhuFrame.getZhuJpanel().pettext();
        }
        else if ((int)this.saletype == 6) {
            ZhuFrame.getZhuJpanel().clearlingtext();
        }
    }
}
