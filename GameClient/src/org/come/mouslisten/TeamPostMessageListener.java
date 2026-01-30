package org.come.mouslisten;

import javax.swing.Icon;
import org.come.until.CutButtonImage;
import java.awt.event.MouseEvent;
import org.come.Jpanel.TeamPostMessageJpanel;
import java.awt.event.MouseListener;

public class TeamPostMessageListener implements MouseListener
{
    private int type;
    private TeamPostMessageJpanel messageJpanel;
    private int num;
    
    public TeamPostMessageListener(int type, TeamPostMessageJpanel messageJpanel, int num) {
        this.type = type;
        this.messageJpanel = messageJpanel;
        this.num = num;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.type == 1) {
            for (int i = 0; i < this.messageJpanel.getLabArrRestrains().length; ++i) {
                if (i == this.num) {
                    if (this.messageJpanel.getLabArrRestrains()[i].getIcon() == null) {
                        this.messageJpanel.getLabArrRestrains()[i].setIcon(CutButtonImage.getImage("inkImg/button/B214.png", -1, -1));
                    }
                }
                else if (this.messageJpanel.getLabArrRestrains()[i].getIcon() != null) {
                    this.messageJpanel.getLabArrRestrains()[i].setIcon((Icon)null);
                }
            }
        }
        else if (this.type == 2) {
            for (int i = 0; i < this.messageJpanel.getLabArrTask().length; ++i) {
                if (i == this.num) {
                    if (this.messageJpanel.getLabArrTask()[i].getIcon() == null) {
                        this.messageJpanel.getLabArrTask()[i].setIcon(CutButtonImage.getImage("inkImg/button/B88.png", -1, -1));
                    }
                }
                else if (this.messageJpanel.getLabArrTask()[i].getIcon() != null) {
                    this.messageJpanel.getLabArrTask()[i].setIcon((Icon)null);
                }
            }
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
