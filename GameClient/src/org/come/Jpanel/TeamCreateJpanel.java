package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import org.come.until.SrcollPanelUI;
import javax.swing.BorderFactory;
import java.awt.Graphics;
import java.awt.Component;
import org.come.until.FormsManagement;
import org.come.bean.TeamBean;
import java.util.List;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import com.tool.btn.TeamCreateBtn;
import javax.swing.JPanel;

public class TeamCreateJpanel extends JPanel
{
    private TeamCreateBtn btnMenuTeam;
    private TeamCreateBtn btnReleaseTeamMsg;
    private JScrollPane scrollPane;
    private JList<TeamCreateModelJpanel> modelJpanelList;
    private DefaultListModel<TeamCreateModelJpanel> listModel;
    private ImageIcon icon;
    
    public TeamCreateJpanel() {
        this.setPreferredSize(new Dimension(539, 510));
        this.setOpaque(false);
        this.setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 31);
        offBtn.setBounds(502, 10, 25, 25);
        this.add(offBtn);
        this.getBtnReleaseTeamMsg();
        this.getScrollPane();
        this.getBtnMenuTeam();
    }
    
    public void initializeJlist(List<TeamBean> teamBeans) {
        Component[] components = this.modelJpanelList.getComponents();
        int num = components.length;
        int length = teamBeans.size();
        if (num >= length) {
            for (int i = 0; i < num; ++i) {
                if (i >= teamBeans.size()) {
                    components[i].setVisible(false);
                }
                else {
                    TeamBean teamBean = (TeamBean)teamBeans.get(i);
                    if (components[i] instanceof TeamCreateModelJpanel) {
                        TeamCreateModelJpanel modelJpanel = (TeamCreateModelJpanel)components[i];
                        modelJpanel.showTeamBean(teamBean);
                        if (!modelJpanel.isVisible()) {
                            modelJpanel.setVisible(true);
                        }
                    }
                }
            }
        }
        else {
            for (int i = 0; i < length; ++i) {
                TeamBean teamBean = (TeamBean)teamBeans.get(i);
                if (i >= num) {
                    TeamCreateModelJpanel modelJpanel = new TeamCreateModelJpanel();
                    modelJpanel.showTeamBean(teamBean);
                    modelJpanel.setBounds(0, 50 * i, 456, 50);
                    this.modelJpanelList.add(modelJpanel);
                }
                else if (components[i] instanceof TeamCreateModelJpanel) {
                    TeamCreateModelJpanel modelJpanel = (TeamCreateModelJpanel)components[i];
                    modelJpanel.showTeamBean(teamBean);
                    if (!modelJpanel.isVisible()) {
                        modelJpanel.setVisible(true);
                    }
                }
            }
        }
        this.modelJpanelList.setPreferredSize(new Dimension(456, 50 * teamBeans.size()));
        this.scrollPane.getVerticalScrollBar().setValue(0);
        if (!FormsManagement.getframe(31).isVisible()) {
            FormsManagement.showForm(31);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background1/B208.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 539, 510, null);
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public JScrollPane getScrollPane() {
        if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane(this.getModelJpanelList())).setBounds(30, 85, 481, 365);
            this.scrollPane.getViewport().setOpaque(false);
            this.scrollPane.setOpaque(false);
            this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.scrollPane.getVerticalScrollBar().setUnitIncrement(60);
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.scrollPane);
        }
        return this.scrollPane;
    }
    
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    
    public JList<TeamCreateModelJpanel> getModelJpanelList() {
        if (this.modelJpanelList == null) {
            (this.modelJpanelList = new JList<>()).setOpaque(false);
            this.modelJpanelList.removeAll();
        }
        return this.modelJpanelList;
    }
    
    public void setModelJpanelList(JList<TeamCreateModelJpanel> modelJpanelList) {
        this.modelJpanelList = modelJpanelList;
    }
    
    public DefaultListModel<TeamCreateModelJpanel> getListModel() {
        if (this.listModel == null) {
            this.listModel = new DefaultListModel<>();
        }
        return this.listModel;
    }
    
    public void setListModel(DefaultListModel<TeamCreateModelJpanel> listModel) {
        this.listModel = listModel;
    }
    
    public TeamCreateBtn getBtnMenuTeam() {
        if (this.btnMenuTeam == null) {
            (this.btnMenuTeam = new TeamCreateBtn("inkImg/button1/K14.png", 1, 10, this)).setBounds(66, 21, 95, 33);
            this.add(this.btnMenuTeam);
        }
        return this.btnMenuTeam;
    }
    
    public void setBtnMenuTeam(TeamCreateBtn btnMenuTeam) {
        this.btnMenuTeam = btnMenuTeam;
    }
    
    public TeamCreateBtn getBtnReleaseTeamMsg() {
        if (this.btnReleaseTeamMsg == null) {
            (this.btnReleaseTeamMsg = new TeamCreateBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_WHITE2, UIUtils.TEXT_HY16, "发布征召", 0, this)).setBounds(219, 461, 99, 24);
            this.add(this.btnReleaseTeamMsg);
        }
        return this.btnReleaseTeamMsg;
    }
    
    public void setBtnReleaseTeamMsg(TeamCreateBtn btnReleaseTeamMsg) {
        this.btnReleaseTeamMsg = btnReleaseTeamMsg;
    }
}
