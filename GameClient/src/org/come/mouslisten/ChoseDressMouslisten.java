package org.come.mouslisten;

import org.come.model.Eshop;
import java.util.List;
import org.come.Jpanel.TaobaoCourtSplendidJpanel;
import org.come.Jpanel.ImageDressJpanel;
import org.come.Frame.TaobaoCourtMainJframe;
import com.tool.role.RoleTX;
import org.come.Frame.ImageDressJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChoseDressMouslisten implements MouseListener
{
    private int type;
    private int caozuo;
    
    public ChoseDressMouslisten(int type, int caozuo) {
        this.type = type;
        this.caozuo = caozuo;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        ImageDressJpanel dressJpanel = ImageDressJframe.getImageDressJframe().getDressJpanel();
        if (this.caozuo == 1) {
            if (this.type == 1) {
                dressJpanel.getBtnTx().setIcon(dressJpanel.getIcon0());
                dressJpanel.getBtnZsp().setIcon(dressJpanel.getIcon3());
                dressJpanel.getBtnZj().setIcon(dressJpanel.getIcon5());
            }
            else if (this.type == 2) {
                dressJpanel.getBtnTx().setIcon(dressJpanel.getIcon1());
                dressJpanel.getBtnZsp().setIcon(dressJpanel.getIcon2());
                dressJpanel.getBtnZj().setIcon(dressJpanel.getIcon5());
            }
            else if (this.type == 3) {
                dressJpanel.getBtnTx().setIcon(dressJpanel.getIcon1());
                dressJpanel.getBtnZsp().setIcon(dressJpanel.getIcon3());
                dressJpanel.getBtnZj().setIcon(dressJpanel.getIcon4());
            }
            RoleTX.getRoleTX().Toggle(this.type - 1, 0);
        }
        else if (this.caozuo == 2) {
            TaobaoCourtSplendidJpanel taobaoCourtSplendidJpanel = TaobaoCourtMainJframe.getTaobaoCourtJframe().getTaobaoCourtMainJpanel().getTaobaoCourtCardJpanel().getTaobaoCourtSplendidJpanel();
            if (this.type == 1) {
                taobaoCourtSplendidJpanel.getBtnTx().setIcon(dressJpanel.getIcon0());
                taobaoCourtSplendidJpanel.getBtnZsp().setIcon(dressJpanel.getIcon3());
                taobaoCourtSplendidJpanel.getBtnZj().setIcon(dressJpanel.getIcon5());
                taobaoCourtSplendidJpanel.setType(11);
            }
            else if (this.type == 2) {
                taobaoCourtSplendidJpanel.getBtnTx().setIcon(dressJpanel.getIcon1());
                taobaoCourtSplendidJpanel.getBtnZsp().setIcon(dressJpanel.getIcon2());
                taobaoCourtSplendidJpanel.getBtnZj().setIcon(dressJpanel.getIcon5());
                taobaoCourtSplendidJpanel.setType(12);
            }
            else if (this.type == 3) {
                taobaoCourtSplendidJpanel.getBtnTx().setIcon(dressJpanel.getIcon1());
                taobaoCourtSplendidJpanel.getBtnZsp().setIcon(dressJpanel.getIcon3());
                taobaoCourtSplendidJpanel.getBtnZj().setIcon(dressJpanel.getIcon4());
                taobaoCourtSplendidJpanel.setType(13);
            }
            List<Eshop> lEshops = taobaoCourtSplendidJpanel.returnlEshops(taobaoCourtSplendidJpanel.getType());
            if (lEshops == null || (lEshops != null && lEshops.size() <= 0)) {
                return;
            }
            taobaoCourtSplendidJpanel.setNowpage(1);
            taobaoCourtSplendidJpanel.refreshDbgSplendid(lEshops, taobaoCourtSplendidJpanel.getNowpage());
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
