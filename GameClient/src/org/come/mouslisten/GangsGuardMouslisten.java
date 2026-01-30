package org.come.mouslisten;

import java.awt.Color;
import org.come.until.FormsManagement;
import com.tool.role.RoleProperty;
import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.GangsGuardJframe;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GangsGuardMouslisten implements MouseListener
{
    private int index;
    
    public GangsGuardMouslisten(int index) {
        this.index = index;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        String v = RoleData.getRoleData().getLoginResult().getResistance();
        String add = GangsGuardJframe.getGangsGuardJframe().getGangsGuardJpanel().getLabResistance()[this.index].getText();
        boolean type = add.indexOf("主") != -1;
        add = add.split(type ? "主" : "副")[1];
        if (v.indexOf(add) == -1) {
            v = v.replace(v.split("\\|")[type ? 0 : 1], type ? ("主-" + add) : ("副-" + add));
            RoleData.getRoleData().getLoginResult().setResistance(v);
            String mes = Agreement.getAgreement().rolechangeAgreement("2" + v);
            SendMessageUntil.toServer(mes);
            ZhuFrame.getZhuJpanel().addPrompt2("你更改" + (type ? "主" : "副") + "帮派守护为" + add);
            RoleProperty.getRoleProperty();
            RoleProperty.ResetBp();
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("你已有该属性");
        }
        FormsManagement.HideForm(53);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        GangsGuardJframe.getGangsGuardJframe().getGangsGuardJpanel().getLabResistance()[this.index].setForeground(Color.red);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        GangsGuardJframe.getGangsGuardJframe().getGangsGuardJpanel().getLabResistance()[this.index].setForeground(Color.green);
    }
}
