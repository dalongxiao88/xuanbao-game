package org.come.mouslisten;

import org.come.until.FormsManagement;
import org.come.entity.RoleSummoning;
import org.come.Frame.MsgJframe;
import com.tool.pet.PetProperty;
import org.come.until.UserMessUntil;
import com.tool.role.RoleProperty;
import com.tool.role.RoleData;
import org.come.good.Medicine;
import come.tool.Fighting.FightingMixDeal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ZDCYMouslisten implements MouseListener
{
    private int i;
    
    public ZDCYMouslisten(int i) {
        this.i = i;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (FightingMixDeal.State == 0) {
            Medicine.zdchiyao(this.i);
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        int z = 0;
        long d = 0L;
        if (this.i == 0) {
            z = RoleProperty.getHp(RoleData.getRoleData().getLoginResult());
            d = (long)RoleData.getRoleData().getLoginResult().getHp().intValue();
        }
        else if (this.i == 1) {
            z = RoleProperty.getMp(RoleData.getRoleData().getLoginResult());
            d = (long)RoleData.getRoleData().getLoginResult().getMp().intValue();
        }
        else if (this.i == 2) {
            if (RoleData.getRoleData().getLoginResult().getSummoning_id() == null) {
                return;
            }
            RoleSummoning rs = UserMessUntil.getPetRgid(RoleData.getRoleData().getLoginResult().getSummoning_id());
            if (rs != null) {
                int[] pets = PetProperty.getPetHMASp(rs);
                z = pets[0];
                d = rs.getBasishp();
            }
        }
        else if (this.i == 3) {
            if (RoleData.getRoleData().getLoginResult().getSummoning_id() == null) {
                return;
            }
            RoleSummoning rs = UserMessUntil.getPetRgid(RoleData.getRoleData().getLoginResult().getSummoning_id());
            int[] pets = PetProperty.getPetHMASp(rs);
            z = pets[1];
            if (rs != null) {
                d = rs.getBasismp();
            }
        }
        MsgJframe.getJframe().getJapnel().showXF(((this.i % 2 == 0) ? "HP:" : "MP:") + d + "/" + z, (this.i % 2 == 0) ? "FFFFFF" : "FFFFFF");
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        FormsManagement.HideForm(46);
    }
}
