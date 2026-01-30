package org.come.Jpanel;

import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import javax.swing.ImageIcon;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.until.AnalysisString;
import org.come.until.CutButtonImage;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.entity.TeamRole;
import java.awt.Dimension;
import java.math.BigDecimal;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TeamArenaModelJPanel extends JPanel
{
    private JLabel labImg;
    private JLabel labName;
    private JLabel labLvl;
    private BigDecimal teamRoleId;
    private int tcpType;
    
    public TeamArenaModelJPanel() {
        this.tcpType = 0;
        this.setPreferredSize(new Dimension(93, 110));
        this.setOpaque(false);
        this.setLayout(null);
        this.getLabImg();
        this.getLabLvl();
        this.getLabName();
    }
    
    public void showRole(TeamRole teamRole) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        if (teamRole == null) {
            this.teamRoleId = null;
            this.labImg.setIcon(null);
            this.labName.setText(null);
            this.labLvl.setText(null);
            this.tcpType = 0;
            return;
        }
        this.teamRoleId = teamRole.getRoleId();
        if (nao.equals("新")) {
            ImageIcon image = CutButtonImage.getImage("img/head/s" + teamRole.getSpeciesId() + "-1.png", 48, 48);
            this.labImg.setIcon(image);
            this.labName.setText(teamRole.getName());
            this.labLvl.setText(AnalysisString.lvl(teamRole.getGrade()) + "级");
        }
        else {
            ImageIcon image = CutButtonImage.getImage("img/head/s" + teamRole.getSpeciesId() + ".png", 48, 48);
            this.labImg.setIcon(image);
            this.labName.setText(teamRole.getName());
            this.labLvl.setText(AnalysisString.lvl(teamRole.getGrade()) + "级");
        }
    }
    
    public JLabel getLabImg() {
        if (this.labImg == null) {
            (this.labImg = new JLabel()).setBounds(26, 20, 40, 40);
            this.add(this.labImg);
        }
        return this.labImg;
    }
    
    public void setLabImg(JLabel labImg) {
        this.labImg = labImg;
    }
    
    public JLabel getLabName() {
        if (this.labName == null) {
            (this.labName = new JLabel()).setBounds(0, 67, 93, 15);
            this.labName.setFont(UIUtils.TEXT_FONT);
            this.labName.setHorizontalAlignment(0);
            this.add(this.labName);
        }
        return this.labName;
    }
    
    public void setLabName(JLabel labName) {
        this.labName = labName;
    }
    
    public JLabel getLabLvl() {
        if (this.labLvl == null) {
            (this.labLvl = new JLabel()).setBounds(0, 82, 93, 15);
            this.labLvl.setForeground(Color.red);
            this.labLvl.setFont(UIUtils.TEXT_FONT);
            this.labLvl.setHorizontalAlignment(0);
            this.add(this.labLvl);
        }
        return this.labLvl;
    }
    
    public void setLabLvl(JLabel labLvl) {
        this.labLvl = labLvl;
    }
    
    public BigDecimal getTeamRoleId() {
        return this.teamRoleId;
    }
    
    public void setTeamRoleId(BigDecimal teamRoleId) {
        this.teamRoleId = teamRoleId;
    }
    
    public int getTcpType() {
        return this.tcpType;
    }
    
    public void setTcpType(int tcpType) {
        this.tcpType = tcpType;
    }
}
