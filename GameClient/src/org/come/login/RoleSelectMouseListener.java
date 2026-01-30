package org.come.login;

import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.until.AnalysisString;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import javax.swing.JLabel;
import java.util.List;
import org.come.entity.RoleTableNew;
import java.awt.event.MouseListener;

public class RoleSelectMouseListener implements MouseListener
{
    private RoleView roleView;
    private RoleTableNew roleTableNew;
    private List<JLabel> roles;
    private Integer i;
    private LoginJpanel loginJpanel;
    
    public RoleSelectMouseListener(RoleView roleView, RoleTableNew role, List<JLabel> roles, Integer i, LoginJpanel loginJpanel) {
        this.roleView = roleView;
        this.roleTableNew = role;
        this.roles = roles;
        this.loginJpanel = loginJpanel;
        this.i = i;
    }
    
    public JLabel getRoleJLabel() {
        for (JLabel role : this.roles) {
            if (role.getText().contains(this.roleTableNew.getRolename())) {
                return role;
            }
        }
        return null;
    }
    
    private void initRoleName() {
        List<RoleTableNew> roleList = this.loginJpanel.getRoleArr().getRoleList();
        for (int i1 = 0; i1 < this.roles.size(); ++i1) {
            JLabel role = (JLabel)this.roles.get(i1);
            if (role.getText().equals(this.roleTableNew.getRolename())) {
                role.setText("(√) " + this.roleTableNew.getRolename());
            }
            else {
                role.setText(((RoleTableNew)roleList.get(i1)).getRolename());
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel roleJLabel = this.getRoleJLabel();
        this.roleView.setRoleInfo(AnalysisString.lvl((int)this.roleTableNew.getGrade()), this.roleTableNew.getSex(), this.roleTableNew.getRace_id().toString(), UIUtils.getcolor((int)this.roleTableNew.getTurnaround()));
        this.initRoleName();
        this.roleView.setRoleTableNew(this.roleTableNew);
        this.loginJpanel.loginSelected((int)this.i);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        JLabel roleJLabel = this.getRoleJLabel();
        roleJLabel.setForeground(new Color(90, 5, 5));
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        JLabel roleJLabel = this.getRoleJLabel();
        roleJLabel.setForeground(UIUtils.getcolor((int)this.roleTableNew.getTurnaround()));
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel roleJLabel = this.getRoleJLabel();
        roleJLabel.setForeground(Color.red);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        JLabel roleJLabel = this.getRoleJLabel();
        roleJLabel.setForeground(UIUtils.getcolor((int)this.roleTableNew.getTurnaround()));
    }
}
