package org.come.Jpanel;

import org.come.until.AnalysisString;
import org.come.until.FormsManagement;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.image.ImageMixDeal;
import org.come.Frame.ZhuFrame;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;
import org.come.until.Util;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.util.ArrayList;
import org.come.entity.TeamRole;
import java.util.List;
import javax.swing.ImageIcon;
import com.tool.btn.TeamPanelBtn;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JPanel;

public class TeamApplyJpanel extends JPanel
{
    private JTable jTable;
    private DefaultTableModel defaultTableModel;
    private Vector<Vector<String>> verVectors;
    private TeamPanelBtn btnAgree;
    private TeamPanelBtn btnRefuse;
    private TeamPanelBtn btnClear;
    ImageIcon icon;
    List<TeamRole> teamRoles;
    
    public TeamApplyJpanel() throws Exception {
        this.teamRoles = new ArrayList<>();
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(344, 284));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 30);
            offBtn.setBounds(315, 4, 25, 25);
            this.add(offBtn);
            this.jTable = new JTable() {
                {
                    this.setOpaque(false);
                }
                
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            this.defaultTableModel = new DefaultTableModel();
            this.verVectors = new Vector<>();
            this.defaultTableModel.setDataVector(this.verVectors, Util.vector2);
            this.jTable.setShowGrid(false);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(0);
            this.jTable.setDefaultRenderer(Object.class, renderer);
            this.jTable.setSelectionBackground(new Color(8, 8, 0));
            this.jTable.setSelectionForeground(Color.red);
            this.jTable.setForeground(Color.red);
            this.jTable.setFont(new Font("微软雅黑", 0, 12));
            this.jTable.setBackground(UIUtils.Color_BACK);
            this.jTable.setModel(this.defaultTableModel);
            this.jTable.getColumnModel().getColumn(0).setPreferredWidth(90);
            this.jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            this.jTable.getColumnModel().getColumn(2).setPreferredWidth(60);
            this.jTable.getColumnModel().getColumn(3).setPreferredWidth(80);
            this.jTable.setBounds(25, 68, 292, 170);
            this.add(this.jTable);
            (this.btnAgree = new TeamPanelBtn("inkImg/button1/B20.png", 1, "允许", UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, null, this)).setBounds(50, 240, 59, 24);
            this.add(this.btnAgree);
            (this.btnRefuse = new TeamPanelBtn("inkImg/button1/B20.png", 1, "拒绝", UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, null, this)).setBounds(150, 240, 59, 24);
            this.add(this.btnRefuse);
            (this.btnClear = new TeamPanelBtn("inkImg/button1/B20.png", 1, "清空", UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, null, this)).setBounds(250, 240, 59, 24);
            this.add(this.btnClear);
        }
        else {
            this.setPreferredSize(new Dimension(333, 277));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 30);
            offBtn.setBounds(308, 0, 25, 25);
            this.add(offBtn);
            this.jTable = new JTable() {
                {
                    this.setOpaque(false);
                }
                
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            this.defaultTableModel = new DefaultTableModel();
            this.verVectors = new Vector<>();
            this.defaultTableModel.setDataVector(this.verVectors, Util.vector2);
            this.jTable.setShowGrid(false);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(0);
            this.jTable.setDefaultRenderer(Object.class, renderer);
            this.jTable.setSelectionBackground(new Color(8, 8, 0));
            this.jTable.setSelectionForeground(Color.red);
            this.jTable.setForeground(Color.red);
            this.jTable.setFont(new Font("微软雅黑", 0, 12));
            this.jTable.setBackground(UIUtils.Color_BACK);
            this.jTable.setModel(this.defaultTableModel);
            this.jTable.getColumnModel().getColumn(0).setPreferredWidth(90);
            this.jTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            this.jTable.getColumnModel().getColumn(2).setPreferredWidth(60);
            this.jTable.getColumnModel().getColumn(3).setPreferredWidth(80);
            this.jTable.setBounds(21, 65, 292, 170);
            this.add(this.jTable);
            (this.btnAgree = new TeamPanelBtn("inkImg/hongmu/6026.png", 1, "允许", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, null, this)).setBounds(40, 225, 60, 26);
            this.add(this.btnAgree);
            (this.btnRefuse = new TeamPanelBtn("inkImg/hongmu/6026.png", 1, "拒绝", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, null, this)).setBounds(140, 225, 60, 26);
            this.add(this.btnRefuse);
            (this.btnClear = new TeamPanelBtn("inkImg/hongmu/6026.png", 1, "清空", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY88, null, this)).setBounds(240, 225, 60, 26);
            this.add(this.btnClear);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/B229.png");
            g.drawImage(this.icon.getImage(), 0, 0, 344, 284, this);
        }
        else {
            this.icon = new ImageIcon("img/xy2uiimg/102_png.xy2uiimg.team_request_panel.png");
            g.drawImage(this.icon.getImage(), 0, 0, 333, 277, this);
        }
    }
    
    public void teamAgree() {
        int index = this.jTable.getSelectedRow();
        if (index == -1) {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择你要拒绝加入的对象！");
            return;
        }
        if (ImageMixDeal.userimg.getRoleShow().getTroop_id() == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你已经没有队伍了");
            return;
        }
        if (ImageMixDeal.userimg.getTeams() == null || ImageMixDeal.userimg.getTeams().length >= 5) {
            ZhuFrame.getZhuJpanel().addPrompt2("无法加人");
            return;
        }
        TeamRole teamRole = (TeamRole)this.teamRoles.remove(index);
        this.verVectors.remove(index);
        String sendmes = Agreement.getAgreement().team5Agreement("A" + teamRole.getRoleId());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void teamRefruse() {
        int index = this.jTable.getSelectedRow();
        if (index == -1) {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择你要拒绝加入的对象！");
            return;
        }
        TeamRole teamRole = (TeamRole)this.teamRoles.remove(index);
        this.verVectors.remove(index);
        String sendmes = Agreement.getAgreement().team5Agreement("R" + teamRole.getRoleId());
        SendMessageUntil.toServer(sendmes);
    }
    
    public void teamClear() {
        this.teamRoles.clear();
        this.verVectors.removeAllElements();
        String sendmes = Agreement.getAgreement().team5Agreement("E");
        SendMessageUntil.toServer(sendmes);
    }
    
    public void showTeamRoleList(List<TeamRole> teamRoles) {
        this.teamRoles.clear();
        this.verVectors.removeAllElements();
        for (int j = 0; j < teamRoles.size(); ++j) {
            TeamRole teamRole = (TeamRole)teamRoles.get(j);
            this.showTeamRole(teamRole);
        }
        FormsManagement.showForm(30);
    }
    
    public void showTeamRole(TeamRole teamRole) {
        Vector<String> vector = new Vector<>();
        vector.add(0, teamRole.getName());
        vector.add(1, AnalysisString.lvl(teamRole.getGrade()) + "");
        vector.add(2, Util.getRaceSting(teamRole.getSpeciesId()));
        vector.add(3, Util.getSexSting(teamRole.getSpeciesId()));
        this.teamRoles.add(teamRole);
        this.verVectors.add(vector);
    }
}
