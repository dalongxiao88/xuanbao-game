package org.come.mouslisten;

import java.awt.event.MouseEvent;
import org.come.entity.TeamRole;
import java.awt.event.MouseListener;

public class TeamCreateModelListener implements MouseListener
{
    private TeamRole teamRole;
    
    public TeamCreateModelListener(TeamRole teamRole) {
        this.teamRole = teamRole;
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
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public TeamRole getTeamRole() {
        return this.teamRole;
    }
    
    public void setTeamRole(TeamRole teamRole) {
        this.teamRole = teamRole;
    }
}
