package org.come.Jpanel;

import com.updateNew.MyIsif;
import java.awt.Graphics;
import com.tool.tcp.SpriteFactory;
import org.come.until.Util;
import org.come.until.AnalysisString;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.tcp.NewPart;
import org.come.entity.TeamRole;
import com.tool.btn.TeamPanelBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TeamModelJpanel extends JPanel
{
    private JLabel labName;
    private JLabel labLvl;
    private TeamPanelBtn btnRace;
    private TeamRole teamRole;
    private TeamJpanel teamJpanel;
    private NewPart part;
    private int index;
    private boolean isState;
    private static ImageIcon icon;
    
    public TeamModelJpanel(TeamJpanel jpanel, int path) {
        this.teamJpanel = jpanel;
        this.index = path;
        this.setPreferredSize(new Dimension(84, 174));
        this.setOpaque(false);
        this.setLayout(null);
        this.getLabName();
        this.getLabLvl();
        this.getBtnRace();
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                if (TeamModelJpanel.this.teamRole != null) {
                    TeamModelJpanel.this.teamJpanel.upXz(TeamModelJpanel.this.index);
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (TeamModelJpanel.this.teamRole != null) {
                    TeamModelJpanel.this.teamJpanel.upSXz(-1);
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                if (TeamModelJpanel.this.teamRole != null) {
                    TeamModelJpanel.this.teamJpanel.upSXz(TeamModelJpanel.this.index);
                }
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
    }
    
    public void showRoleShow(TeamRole teamRole) {
        this.teamRole = teamRole;
        if (teamRole != null) {
            this.getLabName().setFont(UIUtils.TEXT_FONT1);
            this.getLabName().setForeground(Color.WHITE);
            this.getLabLvl().setFont(UIUtils.TEXT_FONT1);
            this.getLabLvl().setForeground(Color.WHITE);
            this.getLabName().setText(teamRole.getName());
            this.getLabLvl().setText(AnalysisString.lvl(teamRole.getGrade()));
            this.getBtnRace().setText(Util.getRaceSting(teamRole.getSpeciesId()) + "族");
            this.getBtnRace().setVisible(true);
            this.isState = (teamRole.getState() < 0);
            this.part = SpriteFactory.createPart(teamRole.getSpeciesId().toString(), 2, 1, null);
        }
        else {
            this.getLabName().setText(null);
            this.getLabLvl().setText(null);
            this.getBtnRace().setVisible(false);
            this.isState = false;
            this.part = null;
        }
    }
    
    public void clearMessage() {
        this.teamRole = null;
        this.part = null;
        this.labName.setText(null);
        this.labLvl.setText(null);
        this.getBtnRace().setBtn(0);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (this.isState) {
            if (TeamModelJpanel.icon == null) {
                TeamModelJpanel.icon = new ImageIcon("inkImg/background/S164.png");
            }
            g.drawImage(TeamModelJpanel.icon.getImage(), 0, 0, this);
        }
    }
    
    public JLabel getLabName() {
        if (this.labName == null) {
            (this.labName = new JLabel("", 0)).setBounds(1, 120, 76, 23);
            this.add(this.labName);
        }
        return this.labName;
    }
    
    public void setLabName(JLabel labName) {
        this.labName = labName;
    }
    
    public JLabel getLabLvl() {
        if (this.labLvl == null) {
            (this.labLvl = new JLabel("", 0)).setBounds(1, 144, 76, 23);
            this.add(this.labLvl);
        }
        return this.labLvl;
    }
    
    public void setLabLvl(JLabel labLvl) {
        this.labLvl = labLvl;
    }
    
    public TeamPanelBtn getBtnRace() {
        if (this.btnRace == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnRace = new TeamPanelBtn("inkImg/button1/B30.png", 1, "", UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT)).setForeground(UIUtils.COLOR_BTNTEXT[1]);
            }
            else {
                (this.btnRace = new TeamPanelBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "", UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT)).setForeground(UIUtils.COLOR_BTNTEXT[1]);
            }
            this.btnRace.setBounds(1, 100, 34, 17);
            this.add(this.btnRace);
        }
        return this.btnRace;
    }
    
    public void setBtnRace(TeamPanelBtn btnRace) {
        this.btnRace = btnRace;
    }
    
    public TeamRole getTeamRole() {
        return this.teamRole;
    }
    
    public void setTeamRole(TeamRole teamRole) {
        this.teamRole = teamRole;
    }
    
    public NewPart getPart() {
        return this.part;
    }
    
    public void setPart(NewPart part) {
        this.part = part;
    }
}
