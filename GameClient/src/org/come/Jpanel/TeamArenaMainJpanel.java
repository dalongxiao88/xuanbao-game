package org.come.Jpanel;

import java.util.ArrayList;
import com.tool.image.ImageMixDeal;
import org.come.until.CutButtonImage;
import java.awt.Color;
import java.awt.Graphics;
import org.come.entity.TeamRole;
import java.math.BigDecimal;
import org.come.until.FormsManagement;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.Sprite;
import javax.swing.ImageIcon;
import com.tool.btn.TeamArenaBtn;
import java.util.List;
import javax.swing.JPanel;

public class TeamArenaMainJpanel extends JPanel
{
    private List<TeamArenaModelJPanel> listTeamArenaModel;
    private TeamArenaBtn btnYes;
    private TeamArenaBtn btnNo;
    private TeamArenaBtn btnYes1;
    private TeamArenaBtn btnNo1;
    private ImageIcon icon;
    private ImageIcon iconIco;
    private String strMatch;
    private int countDown;
    private int timeNum;
    private int typeMenu;
    private int xNum;
    private int typeCount;
    private int teamNum;
    private StringBuffer buffer;
    private Sprite tcp;
    
    public TeamArenaMainJpanel() {
        this.timeNum = 60;
        this.typeMenu = 1;
        this.xNum = 287;
        this.typeCount = 0;
        this.teamNum = 0;
        this.buffer = new StringBuffer();
        this.tcp = SpriteFactory.VloadSprite("resource/mouse/teamArenaBack.tcp", null);
        this.setPreferredSize(new Dimension(591, 406));
        this.setOpaque(false);
        this.setLayout(null);
        this.getListTeamArenaModel();
        this.getBtnNo();
        this.getBtnYes();
        this.getBtnYes1();
        this.getBtnNo1();
    }
    
    public TeamArenaBtn getBtnYes1() {
        if (this.btnYes1 == null) {
            (this.btnYes1 = new TeamArenaBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "匹配", 3, this)).setBounds(82, 177, 60, 26);
            this.btnYes1.setVisible(false);
            this.add(this.btnYes1);
        }
        return this.btnYes1;
    }
    
    public TeamArenaBtn getBtnNo1() {
        if (this.btnNo1 == null) {
            (this.btnNo1 = new TeamArenaBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "拒绝", 4, this)).setBounds(447, 177, 60, 26);
            this.btnNo1.setVisible(false);
            this.add(this.btnNo1);
        }
        return this.btnNo1;
    }
    
    public void btnShow(int type, Integer typesOfEvents) {
        if (type == 0) {
            if ((int)typesOfEvents == 1) {
                this.btnNo.setText("拒绝");
                this.btnYes.setText("匹配");
                this.btnNo.setVisible(true);
                this.btnYes.setVisible(true);
                this.btnNo1.setVisible(false);
                this.btnYes1.setVisible(false);
            }
            else if ((int)typesOfEvents == 2) {
                this.btnNo1.setText("拒绝");
                this.btnYes1.setText("匹配");
                this.btnNo1.setVisible(true);
                this.btnYes1.setVisible(true);
                this.btnNo.setVisible(false);
                this.btnYes.setVisible(false);
            }
        }
        else if (type == 1) {
            if ((int)typesOfEvents == 1) {
                this.btnNo.setVisible(false);
                this.btnYes.setVisible(false);
            }
            else if ((int)typesOfEvents == 2) {
                this.btnNo1.setVisible(false);
                this.btnYes1.setVisible(false);
            }
        }
        else if (type == 2) {
            if ((int)typesOfEvents == 1) {
                this.btnNo.setVisible(true);
                this.btnNo.setText("取消");
                this.btnYes.setVisible(false);
            }
            else if ((int)typesOfEvents == 2) {
                this.btnNo1.setVisible(true);
                this.btnNo1.setText("取消");
                this.btnYes1.setVisible(false);
            }
        }
    }
    
    public void hideShow() {
        this.typeCount = 0;
        this.buffer.setLength(0);
        FormsManagement.HideForm(108);
        for (int i = 0; i < this.listTeamArenaModel.size(); ++i) {
            ((TeamArenaModelJPanel)this.listTeamArenaModel.get(i)).showRole(null);
        }
    }
    
    public void addCount(BigDecimal decimal) {
        ++this.typeCount;
        for (int i = 0; i < this.listTeamArenaModel.size(); ++i) {
            TeamArenaModelJPanel teamArenaModelJPanel = (TeamArenaModelJPanel)this.listTeamArenaModel.get(i);
            if (teamArenaModelJPanel.getTeamRoleId() != null && teamArenaModelJPanel.getTeamRoleId().compareTo(decimal) == 0) {
                teamArenaModelJPanel.setTcpType(1);
            }
        }
        if (this.typeCount == this.teamNum) {
            this.changeCountDown(null, 0, 1);
        }
    }
    
    public void showMine(List<TeamRole> teamRoles, int numType) {
        int num = numType * 5;
        if (teamRoles != null && teamRoles.size() != 0) {
            int size = teamRoles.size();
            int i = 0;
            int len = this.listTeamArenaModel.size();
            while (i < len) {
                if (i >= 5) {
                    break;
                }
                else {
                    if (i < size) {
                        TeamRole teamRole = (TeamRole)teamRoles.get(i);
                        if (i < 5) {
                            ((TeamArenaModelJPanel)this.listTeamArenaModel.get(i + num)).showRole(teamRole);
                        }
                    }
                    else {
                        ((TeamArenaModelJPanel)this.listTeamArenaModel.get(i + num)).showRole(null);
                    }
                    ++i;
                }
            }
        }
        if (numType == 1) {
            this.changeCountDown("倒计时", 30, 1);
            this.teamNum = teamRoles.size();
        }
        else if (numType == 0) {
            this.btnShow(1, Integer.valueOf(-1));
            this.changeCountDown(null, 0, 0);
        }
    }
    
    public void changeCountDown(String str, int countDown, int typeMenu) {
        this.countDown = countDown;
        this.strMatch = str;
        this.typeMenu = typeMenu;
    }
    
    public void getTime() {
        ++this.countDown;
        int min = this.countDown / 60;
        int sec = this.countDown % 60;
        this.buffer.setLength(0);
        if (min > 0) {
            this.buffer.append(min);
        }
        else {
            this.buffer.append("0");
        }
        this.buffer.append(":");
        if (sec > 9) {
            this.buffer.append(sec);
        }
        else {
            this.buffer.append("0");
            this.buffer.append(sec);
        }
    }
    
    public void getCountDown() {
        this.buffer.setLength(0);
        this.buffer.append(this.strMatch);
        this.buffer.append("0:");
        if (this.countDown > 9) {
            this.buffer.append(this.countDown);
        }
        else {
            this.buffer.append("0");
            this.buffer.append(this.countDown);
        }
        if (this.countDown > 0) {
            --this.countDown;
        }
    }
    
    private void grawTime(Graphics g) {
        g.setColor(Color.white);
        g.setFont(UIUtils.TEXT_FONT22);
        ++this.timeNum;
        if (this.buffer.length() != 0) {
            g.drawString(this.buffer.toString(), this.xNum, 199);
        }
        if (this.timeNum > 55) {
            if (this.strMatch == null) {
                this.getTime();
            }
            else {
                this.getCountDown();
            }
            int canDisplayUpTo = g.getFontMetrics().stringWidth(this.buffer.toString());
            this.xNum = 287 - canDisplayUpTo / 2;
            this.timeNum = 0;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background/S1912.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, null);
        if (this.iconIco == null) {
            this.iconIco = CutButtonImage.getImage("inkImg/background/S192.png", -1, -1);
        }
        if (this.typeMenu == 0) {
            g.drawImage(this.iconIco.getImage(), 258, 169, null);
        }
        else {
            this.grawTime(g);
        }
    }
    
    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        this.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
        for (int i = 0, len = this.listTeamArenaModel.size(); i < len; ++i) {
            if (this.typeMenu == 0) {
                if (((TeamArenaModelJPanel)this.listTeamArenaModel.get(i)).getTeamRoleId() != null) {
                    this.tcp.draw(g, 67 + i % 5 * 96, 57 + i / 5 * 157);
                }
            }
            else if (((TeamArenaModelJPanel)this.listTeamArenaModel.get(i)).getTcpType() == 1) {
                this.tcp.draw(g, 67 + i % 5 * 96, 57 + i / 5 * 157);
            }
        }
    }
    
    public List<TeamArenaModelJPanel> getListTeamArenaModel() {
        if (this.listTeamArenaModel == null) {
            this.listTeamArenaModel = new ArrayList<>();
            for (int i = 0; i < 10; ++i) {
                TeamArenaModelJPanel arenaModelJPanel = new TeamArenaModelJPanel();
                arenaModelJPanel.setBounds(50 + i % 5 * 96, 59 + i / 5 * 157, 93, 110);
                this.add(arenaModelJPanel);
                this.listTeamArenaModel.add(arenaModelJPanel);
            }
        }
        return this.listTeamArenaModel;
    }
    
    public void setListTeamArenaModel(List<TeamArenaModelJPanel> listTeamArenaModel) {
        this.listTeamArenaModel = listTeamArenaModel;
    }
    
    public TeamArenaBtn getBtnYes() {
        if (this.btnYes == null) {
            (this.btnYes = new TeamArenaBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "匹配", 1, this)).setBounds(82, 177, 60, 26);
            this.add(this.btnYes);
        }
        return this.btnYes;
    }
    
    public void setBtnYes(TeamArenaBtn btnYes) {
        this.btnYes = btnYes;
    }
    
    public TeamArenaBtn getBtnNo() {
        if (this.btnNo == null) {
            (this.btnNo = new TeamArenaBtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "拒绝", 2, this)).setBounds(447, 177, 60, 26);
            this.add(this.btnNo);
        }
        return this.btnNo;
    }
    
    public void setBtnNo(TeamArenaBtn btnNo) {
        this.btnNo = btnNo;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public ImageIcon getIconIco() {
        return this.iconIco;
    }
    
    public void setIconIco(ImageIcon iconIco) {
        this.iconIco = iconIco;
    }
    
    public String getStrMatch() {
        return this.strMatch;
    }
    
    public void setStrMatch(String strMatch) {
        this.strMatch = strMatch;
    }
    
    public int getTimeNum() {
        return this.timeNum;
    }
    
    public void setTimeNum(int timeNum) {
        this.timeNum = timeNum;
    }
    
    public int getTypeMenu() {
        return this.typeMenu;
    }
    
    public void setTypeMenu(int typeMenu) {
        this.typeMenu = typeMenu;
    }
    
    public int getxNum() {
        return this.xNum;
    }
    
    public void setxNum(int xNum) {
        this.xNum = xNum;
    }
    
    public StringBuffer getBuffer() {
        return this.buffer;
    }
    
    public void setBuffer(StringBuffer buffer) {
        this.buffer = buffer;
    }
    
    public void setCountDown(int countDown) {
        this.countDown = countDown;
    }
    
    public int getTypeCount() {
        return this.typeCount;
    }
    
    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }
    
    public int getTeamNum() {
        return this.teamNum;
    }
    
    public void setTeamNum(int teamNum) {
        this.teamNum = teamNum;
    }
    
    public Sprite getTcp() {
        return this.tcp;
    }
    
    public void setTcp(Sprite tcp) {
        this.tcp = tcp;
    }
}
