package org.come.Jpanel;

import org.come.until.FormsManagement;
import org.come.entity.TeamRole;
import org.come.bean.TeamBean;
import org.come.bean.RoleShow;
import com.tool.tcp.Sprite;
import com.tool.tcp.SpriteFactory;
import com.tool.tcp.GetTcpPath;
import com.tool.image.ImageMixDeal;
import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.TeamPanelBtn;
import javax.swing.JPanel;

public class TeamJpanel extends JPanel
{
    private TeamModelJpanel[] modelJpanels;
    private TeamPanelBtn[] operates;
    private TeamPanelBtn btnOut;
    private TeamPanelBtn btnTobecaptain;
    private TeamPanelBtn btnfiend;
    private TeamPanelBtn btnKickout;
    private TeamPanelBtn btnApply;
    private int xz;
    private int sxz;
    private ImageIcon icon;
    private ImageIcon icon2;
    
    public TeamJpanel() throws Exception {
        this.xz = -1;
        this.sxz = -1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(538, 279));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            this.modelJpanels = new TeamModelJpanel[5];
            this.operates = new TeamPanelBtn[4];
            for (int i = 0; i < this.modelJpanels.length; ++i) {
                (this.modelJpanels[i] = new TeamModelJpanel(this, i)).setBounds(53 + i * 95, 48, 84, 174);
                this.add(this.modelJpanels[i]);
            }
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 13);
            offBtn.setBounds(501, 10, 25, 25);
            this.add(offBtn);
            (this.btnTobecaptain = new TeamPanelBtn("inkImg/button1/B22.png", 1, "移交队长", UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, this, null)).setBounds(385, 20, 99, 24);
            this.add(this.btnTobecaptain);
            (this.btnApply = new TeamPanelBtn("inkImg/button1/B22.png", 1, "申请列表", UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, this, null)).setBounds(53, 232, 99, 24);
            this.add(this.btnApply);
            (this.btnOut = new TeamPanelBtn("inkImg/button1/B22.png", 1, "解散队伍", UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, this, null)).setBounds(173, 232, 99, 24);
            this.add(this.btnOut);
            (this.btnKickout = new TeamPanelBtn("inkImg/button1/B22.png", 1, "请离队伍", UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, this, null)).setBounds(293, 232, 99, 24);
            this.add(this.btnKickout);
            (this.btnfiend = new TeamPanelBtn("inkImg/button1/B22.png", 1, "加为好友", UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, this, null)).setBounds(413, 232, 99, 24);
            this.add(this.btnfiend);
        }
        else {
            this.setPreferredSize(new Dimension(515, 306));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            this.modelJpanels = new TeamModelJpanel[5];
            this.operates = new TeamPanelBtn[4];
            for (int i = 0; i < this.modelJpanels.length; ++i) {
                (this.modelJpanels[i] = new TeamModelJpanel(this, i)).setBounds(29 + i * 95, 63, 84, 174);
                this.add(this.modelJpanels[i]);
            }
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/S74.png", 1, 13);
            offBtn.setBounds(490, 0, 23, 23);
            this.add(offBtn);
            (this.btnTobecaptain = new TeamPanelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "移交队长", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, this, null)).setBounds(365, 33, 99, 26);
            this.add(this.btnTobecaptain);
            (this.btnApply = new TeamPanelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "申请列表", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, this, null)).setBounds(30, 247, 99, 26);
            this.add(this.btnApply);
            (this.btnOut = new TeamPanelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "解散队伍", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, this, null)).setBounds(150, 247, 99, 26);
            this.add(this.btnOut);
            (this.btnKickout = new TeamPanelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "请离队伍", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, this, null)).setBounds(270, 247, 99, 26);
            this.add(this.btnKickout);
            (this.btnfiend = new TeamPanelBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, "加为好友", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, this, null)).setBounds(390, 247, 99, 26);
            this.add(this.btnfiend);
        }
    }
    
    public void paintPeople(Graphics g) {
        if (MyIsif.getStyle().equals("水墨")) {
            for (int i = 0; i < 5; ++i) {
                if (this.modelJpanels[i].getTeamRole() != null) {
                    if (i == this.xz || i == this.sxz) {
                        if (this.icon2 == null) {
                            this.icon2 = new ImageIcon("inkImg/background/S163.png");
                        }
                        g.drawImage(this.icon2.getImage(), this.modelJpanels[i].getX(), this.modelJpanels[i].getY(), this);
                    }
                    if (this.modelJpanels[i].getPart() != null) {
                        this.modelJpanels[i].getPart().draw(g, 90 + i * 95, 160, 4, ImageMixDeal.userimg.getTime());
                    }
                    if (i == 0) {
                        Sprite mouse = SpriteFactory.Prepare(GetTcpPath.LIN);
                        if (mouse != null) {
                            mouse.updateToTime(ImageMixDeal.userimg.getTime(), 0);
                            mouse.draw(g, 80, 25);
                        }
                    }
                }
            }
        }
        else {
            for (int i = 0; i < 5; ++i) {
                if (this.modelJpanels[i].getTeamRole() != null) {
                    if (i == this.xz || i == this.sxz) {
                        if (this.icon2 == null) {
                            this.icon2 = new ImageIcon("inkImg/hongmu/S28952.png");
                        }
                        g.drawImage(this.icon2.getImage(), this.modelJpanels[i].getX(), this.modelJpanels[i].getY(), this);
                    }
                    if (this.modelJpanels[i].getPart() != null) {
                        this.modelJpanels[i].getPart().draw(g, 70 + i * 95, 173, 4, ImageMixDeal.userimg.getTime());
                    }
                    if (i == 0) {
                        Sprite mouse = SpriteFactory.Prepare(GetTcpPath.LIN);
                        if (mouse != null) {
                            mouse.updateToTime(ImageMixDeal.userimg.getTime(), 0);
                            mouse.draw(g, 80, 25);
                        }
                    }
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/B228.png");
            g.drawImage(this.icon.getImage(), 0, 0, 538, 279, this);
        }
        else {
            this.icon = new ImageIcon("inkImg/hongmu/S28955.png");
            g.drawImage(this.icon.getImage(), 0, 0, 515, 306, this);
        }
        this.paintPeople(g);
    }
    
    public void upSXz(int v) {
        this.sxz = v;
    }
    
    public void upXz(int v) {
        this.xz = v;
    }
    
    public void show(RoleShow roleShow, TeamBean teamBean) {
        if (MyIsif.getStyle().equals("水墨")) {
            boolean isCaptian = ((TeamRole)teamBean.getTeams().get(0)).getRoleId().compareTo(roleShow.getRole_id()) == 0;
            this.btnApply.setVisible(isCaptian);
            this.btnOut.setVisible(isCaptian);
            this.btnKickout.setText(isCaptian ? "请离队伍" : "离开队伍");
            int i = 0;
            int length = teamBean.getTeams().size();
            while (i < this.modelJpanels.length) {
                TeamModelJpanel jpanel = this.modelJpanels[i];
                TeamRole teamRole = (i < length) ? ((TeamRole)teamBean.getTeams().get(i)) : null;
                jpanel.showRoleShow(teamRole);
                if (i != 0) {
                    if (isCaptian && teamRole != null && teamRole.getState() < 0) {
                        if (this.operates[i - 1] == null) {
                            (this.operates[i - 1] = new TeamPanelBtn("inkImg/button1/B30.png", 1, i, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT)).setText("召回");
                            this.operates[i - 1].setBounds(97 + i * 95, 51, 34, 17);
                            this.add(this.operates[i - 1], 0);
                        }
                        this.operates[i - 1].setVisible(true);
                    }
                    else if (this.operates[i - 1] != null) {
                        this.operates[i - 1].setVisible(false);
                    }
                }
                if (teamRole != null && teamRole.getRoleId().compareTo(roleShow.getRole_id()) == 0) {
                    this.btnTobecaptain.setText(isCaptian ? "移交队长" : ((teamRole.getState() >= 0) ? "暂离队伍" : "回归队伍"));
                }
                ++i;
            }
            this.upSXz(-1);
            this.upXz(-1);
            FormsManagement.showForm(13);
        }
        else {
            boolean isCaptian = ((TeamRole)teamBean.getTeams().get(0)).getRoleId().compareTo(roleShow.getRole_id()) == 0;
            this.btnApply.setVisible(isCaptian);
            this.btnOut.setVisible(isCaptian);
            this.btnKickout.setText(isCaptian ? "请离队伍" : "离开队伍");
            int i = 0;
            int length = teamBean.getTeams().size();
            while (i < this.modelJpanels.length) {
                TeamModelJpanel jpanel = this.modelJpanels[i];
                TeamRole teamRole = (i < length) ? ((TeamRole)teamBean.getTeams().get(i)) : null;
                jpanel.showRoleShow(teamRole);
                if (i != 0) {
                    if (isCaptian && teamRole != null && teamRole.getState() < 0) {
                        if (this.operates[i - 1] == null) {
                            (this.operates[i - 1] = new TeamPanelBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, i, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT)).setText("召回");
                            this.operates[i - 1].setBounds(73 + i * 95, 65, 34, 17);
                            this.add(this.operates[i - 1], 0);
                        }
                        this.operates[i - 1].setVisible(true);
                    }
                    else if (this.operates[i - 1] != null) {
                        this.operates[i - 1].setVisible(false);
                    }
                }
                if (teamRole != null && teamRole.getRoleId().compareTo(roleShow.getRole_id()) == 0) {
                    this.btnTobecaptain.setText(isCaptian ? "移交队长" : ((teamRole.getState() >= 0) ? "暂离队伍" : "回归队伍"));
                }
                ++i;
            }
            this.upSXz(-1);
            this.upXz(-1);
            FormsManagement.showForm(13);
        }
    }
    
    public TeamRole getXZ() {
        if (this.xz == -1) {
            return null;
        }
        return this.modelJpanels[this.xz].getTeamRole();
    }
}
