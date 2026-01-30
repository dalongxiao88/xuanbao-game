package org.come.mouslisten;

import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Pal;
import org.come.Jpanel.PartnerMainJpanel;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.PartnerJframe;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.awt.event.MouseListener;

public class PalEquipListener implements MouseListener
{
    private BigDecimal rgid;
    
    public PalEquipListener(BigDecimal rgid) {
        this.rgid = rgid;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (3 == e.getButton()) {
            PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
            if (mainJpanel.getPalDataChooseId() < 0) {
                return;
            }
            Pal pidGetPal = mainJpanel.pidGetPal(mainJpanel.getPalDataChooseId());
            if (pidGetPal == null) {
                return;
            }
            if (this.rgid == null) {
                return;
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append(pidGetPal.getId());
            buffer.append("|");
            buffer.append(this.rgid);
            buffer.append("|T");
            String sendmes = Agreement.getAgreement().userpalAgreement(buffer.toString());
            SendMessageUntil.toServer(sendmes);
        }
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
    
    public BigDecimal getRgid() {
        return this.rgid;
    }
    
    public void setRgid(BigDecimal rgid) {
        this.rgid = rgid;
    }
}
