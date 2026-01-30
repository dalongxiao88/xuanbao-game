package org.come.Jpanel;

import java.util.ArrayList;
import com.tool.image.ImageMixDeal;
import org.come.until.CutButtonImage;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Graphics;
import org.come.until.FormsManagement;
import org.come.entity.TeamRole;
import java.math.BigDecimal;
import java.awt.LayoutManager;
import java.awt.Dimension;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.Sprite;
import javax.swing.ImageIcon;
import com.tool.btn.TtBmbtn;
import java.util.List;
import javax.swing.JPanel;

public class TtBmJpanel extends JPanel
{
    private List<TeamArenaModelJPanel> listTeamArenaModel;
    private TtBmbtn btnYes;
    private TtBmbtn btnNo;
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
    
    public TtBmJpanel() {
        this.timeNum = 60;
        this.typeMenu = 1;
        this.xNum = 225;
        this.typeCount = 0;
        this.teamNum = 0;
        this.buffer = new StringBuffer();
        this.tcp = SpriteFactory.VloadSprite("resource/mouse/teamArenaBack.tcp", null);
        this.setPreferredSize(new Dimension(472, 146));
        this.setOpaque(false);
        this.setLayout((LayoutManager)null);
        this.getListTeamArenaModel();
        this.getBtnNo();
        this.getBtnYes();
    }
    
    public void btnShow(int type) {
        if (type == 0) {
            this.btnNo.setText("拒绝");
            this.btnYes.setText("同意");
            this.btnNo.setVisible(true);
            this.btnYes.setVisible(true);
        }
        else if (type == 1) {
            this.btnNo.setVisible(false);
            this.btnYes.setVisible(false);
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
    }
    
    public void showMine(List<TeamRole> teamRoles, int numType) {
        int num = numType * 5;
        if (teamRoles != null && teamRoles.size() != 0) {
            int size = teamRoles.size();
            for (int i = 0, len = this.listTeamArenaModel.size(); i < len && i < 5; ++i) {
                if (i < size) {
                    TeamRole teamRole = (TeamRole)teamRoles.get(i);
                    if (i < 5) {
                        ((TeamArenaModelJPanel)this.listTeamArenaModel.get(i + num)).showRole(teamRole);
                    }
                }
                else {
                    ((TeamArenaModelJPanel)this.listTeamArenaModel.get(i + num)).showRole((TeamRole)null);
                }
            }
        }
        if (numType == 1) {
            this.changeCountDown("倒计时", 30, 1);
            this.teamNum = teamRoles.size();
        }
    }
    
    public void changeCountDown(String str, int countDown, int typeMenu) {
        this.countDown = countDown;
        this.strMatch = str;
        this.typeMenu = typeMenu;
    }
    
    public void hideShow() {
        this.typeCount = 0;
        this.buffer.setLength(0);
        FormsManagement.HideForm(610);
        for (int i = 0; i < this.listTeamArenaModel.size(); ++i) {
            ((TeamArenaModelJPanel)this.listTeamArenaModel.get(i)).showRole((TeamRole)null);
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
            g.drawString(this.buffer.toString(), this.xNum, 50);
        }
        if (this.timeNum > 55) {
            if (this.strMatch == null) {
                this.getTime();
            }
            else {
                this.getCountDown();
            }
            int canDisplayUpTo = g.getFontMetrics().stringWidth(this.buffer.toString());
            this.xNum = 225 - canDisplayUpTo / 2;
            this.timeNum = 0;
        }
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
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background/ttbm.png");
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
                    this.tcp.draw(g, 17 + i % 5 * 96 - 10, i / 5 * 157 - 90);
                }
            }
            else if (((TeamArenaModelJPanel)this.listTeamArenaModel.get(i)).getTcpType() == 1) {
                this.tcp.draw(g, 17 + i % 5 * 96 - 10, i / 5 * 157 - 90);
            }
        }
    }
    
    public List<TeamArenaModelJPanel> getListTeamArenaModel() {
        if (this.listTeamArenaModel == null) {
            this.listTeamArenaModel = new ArrayList<>();
            for (int i = 0; i < 10; ++i) {
                TeamArenaModelJPanel arenaModelJPanel = new TeamArenaModelJPanel();
                arenaModelJPanel.setBounds(i % 5 * 96 - 10, i / 5 * 157 - 88, 93, 110);
                this.add(arenaModelJPanel);
                this.listTeamArenaModel.add(arenaModelJPanel);
            }
        }
        return this.listTeamArenaModel;
    }
    
    public void setListTeamArenaModel(List<TeamArenaModelJPanel> listTeamArenaModel) {
        this.listTeamArenaModel = listTeamArenaModel;
    }
    
    public TtBmbtn getBtnYes() {
        if (this.btnYes == null) {
            (this.btnYes = new TtBmbtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "同意", 1, this)).setBounds(32, 29, 60, 26);
            this.add(this.btnYes);
        }
        return this.btnYes;
    }
    
    public void setBtnYes(TtBmbtn btnYes) {
        this.btnYes = btnYes;
    }
    
    public TtBmbtn getBtnNo() {
        if (this.btnNo == null) {
            (this.btnNo = new TtBmbtn("inkImg/button/32.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "拒绝", 2, this)).setBounds(397, 29, 60, 26);
            this.add(this.btnNo);
        }
        return this.btnNo;
    }
    
    public void setBtnNo(TtBmbtn btnNo) {
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
    
    public StringBuffer getBuffer() {
        return this.buffer;
    }
    
    public void setBuffer(StringBuffer buffer) {
        this.buffer = buffer;
    }
    
    public Sprite getTcp() {
        return this.tcp;
    }
    
    public void setTcp(Sprite tcp) {
        this.tcp = tcp;
    }
    
    public void setCountDown(int countDown) {
        this.countDown = countDown;
    }
}
