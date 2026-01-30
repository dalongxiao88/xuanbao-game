package org.come.Jpanel;

import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.until.CutButtonImage;
import org.come.entity.TeamRole;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.bean.TeamBean;
import com.tool.btn.TeamCreateBtn;
import org.come.mouslisten.TeamCreateModelListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TeamCreateModelJpanel extends JPanel
{
    private JLabel labNum;
    private JLabel labRestrain;
    private JLabel labTeam;
    private JLabel labMsg;
    private JLabel[] labArrTeamImgs;
    private TeamCreateModelListener[] listenerTeamImgs;
    private TeamCreateBtn btnJoin;
    private TeamCreateBtn btnChat;
    private TeamBean teamBean;
    private ImageIcon icon;
    
    public TeamCreateModelJpanel() {
        this.setPreferredSize(new Dimension(456, 60));
        this.setOpaque(false);
        this.setLayout(null);
        this.getLabArrTeamImgs();
        this.getLabNum();
        this.getLabRestrain();
        this.getLabTeam();
        this.getLabMsg();
        this.getBtnChat();
        this.getBtnJoin();
    }
    
    public void showTeamBean(TeamBean teamBean) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        if (nao.equals("新")) {
            if ((this.teamBean = teamBean) != null) {
                this.labMsg.setText(teamBean.geteMsg());
                this.labRestrain.setText(teamBean.geteTeam());
                this.labNum.setText(null);
                this.labTeam.setText(teamBean.geteTask());
                List<TeamRole> teams = teamBean.getTeams();
                for (int i = 0; i < this.labArrTeamImgs.length; ++i) {
                    if (i < teams.size()) {
                        TeamRole teamRole = (TeamRole)teams.get(i);
                        this.labArrTeamImgs[i].setIcon(CutButtonImage.getImage("img/head/s" + teamRole.getSpeciesId() + "-1.png", -1, -1));
                        this.listenerTeamImgs[i].setTeamRole(teamRole);
                    }
                    else {
                        this.labArrTeamImgs[i].setIcon(null);
                        this.listenerTeamImgs[i].setTeamRole(null);
                    }
                }
            }
        }
        else if ((this.teamBean = teamBean) != null) {
            this.labMsg.setText(teamBean.geteMsg());
            this.labRestrain.setText(teamBean.geteTeam());
            this.labNum.setText(null);
            this.labTeam.setText(teamBean.geteTask());
            List<TeamRole> teams = teamBean.getTeams();
            for (int i = 0; i < this.labArrTeamImgs.length; ++i) {
                if (i < teams.size()) {
                    TeamRole teamRole = (TeamRole)teams.get(i);
                    this.labArrTeamImgs[i].setIcon(CutButtonImage.getImage("img/head/s" + teamRole.getSpeciesId() + ".png", -1, -1));
                    this.listenerTeamImgs[i].setTeamRole(teamRole);
                }
                else {
                    this.labArrTeamImgs[i].setIcon(null);
                    this.listenerTeamImgs[i].setTeamRole(null);
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = CutButtonImage.getImage("inkImg/background/S162.png", -1, -1);
        }
        g.drawImage(this.icon.getImage(), 14, 59, 428, 1, null);
    }
    
    public JLabel getLabNum() {
        if (this.labNum == null) {
            (this.labNum = new JLabel("171", 0)).setBounds(3, 13, 20, 16);
            this.labNum.setFont(UIUtils.TEXT_FONT);
            this.labNum.setForeground(Color.white);
            this.add(this.labNum);
        }
        return this.labNum;
    }
    
    public void setLabNum(JLabel labNum) {
        this.labNum = labNum;
    }
    
    public JLabel getLabRestrain() {
        if (this.labRestrain == null) {
            (this.labRestrain = new JLabel("无属性")).setBounds(243, 10, 60, 16);
            this.labRestrain.setForeground(Color.white);
            this.labRestrain.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labRestrain);
        }
        return this.labRestrain;
    }
    
    public void setLabRestrain(JLabel labRestrain) {
        this.labRestrain = labRestrain;
    }
    
    public JLabel getLabTeam() {
        if (this.labTeam == null) {
            (this.labTeam = new JLabel("其他")).setBounds(330, 10, 60, 16);
            this.labTeam.setForeground(Color.white);
            this.labTeam.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labTeam);
        }
        return this.labTeam;
    }
    
    public void setLabTeam(JLabel labTeam) {
        this.labTeam = labTeam;
    }
    
    public JLabel[] getLabArrTeamImgs() {
        if (this.labArrTeamImgs == null) {
            this.labArrTeamImgs = new JLabel[5];
            this.listenerTeamImgs = new TeamCreateModelListener[5];
            for (int i = 0; i < this.labArrTeamImgs.length; ++i) {
                this.labArrTeamImgs[i] = new JLabel() {
                    private ImageIcon icon;
                    
                    @Override
                    protected void paintComponent(Graphics g) {
                        if (this.getIcon() == null) {
                            if (this.icon == null) {
                                this.icon = CutButtonImage.getImage("inkImg/background/S158.png", -1, -1);
                            }
                            g.drawImage(this.icon.getImage(), 0, 0, 39, 39, null);
                        }
                        super.paintComponent(g);
                    }
                };
                if (i == 0) {
                    this.labArrTeamImgs[i].setBounds(25, 0, 39, 39);
                }
                else {
                    this.labArrTeamImgs[i].setBounds(35 + i * 40, 0, 39, 39);
                }
                this.listenerTeamImgs[i] = new TeamCreateModelListener(null);
                this.labArrTeamImgs[i].addMouseListener(this.listenerTeamImgs[i]);
                this.add(this.labArrTeamImgs[i]);
            }
        }
        return this.labArrTeamImgs;
    }
    
    public void setLabArrTeamImgs(JLabel[] labArrTeamImgs) {
        this.labArrTeamImgs = labArrTeamImgs;
    }
    
    public TeamCreateBtn getBtnJoin() {
        if (this.btnJoin == null) {
            (this.btnJoin = new TeamCreateBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "加入", 2, this)).setBounds(378, 10, 34, 17);
            this.add(this.btnJoin);
        }
        return this.btnJoin;
    }
    
    public void setBtnJoin(TeamCreateBtn btnJoin) {
        this.btnJoin = btnJoin;
    }
    
    public TeamCreateBtn getBtnChat() {
        if (this.btnChat == null) {
            (this.btnChat = new TeamCreateBtn("inkImg/button/2.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "交谈", 2, this)).setBounds(414, 10, 34, 17);
            this.add(this.btnChat);
        }
        return this.btnChat;
    }
    
    public void setBtnChat(TeamCreateBtn btnChat) {
        this.btnChat = btnChat;
    }
    
    public JLabel getLabMsg() {
        if (this.labMsg == null) {
            (this.labMsg = new JLabel("是兄弟就来砍我快来玩是兄弟就来砍我快来玩是兄弟就来砍我快")).setBounds(25, 40, 400, 16);
            this.labMsg.setForeground(Color.white);
            this.labMsg.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labMsg);
        }
        return this.labMsg;
    }
    
    public void setLabMsg(JLabel labMsg) {
        this.labMsg = labMsg;
    }
    
    public TeamBean getTeamBean() {
        return this.teamBean;
    }
}
