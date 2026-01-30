package org.come.Jpanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import org.come.until.ScrollUI;
import javax.swing.table.JTableHeader;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.Graphics;
import org.come.until.CutButtonImage;
import com.tool.role.RoleData;
import com.updateNew.MyIsif;
import javax.swing.Icon;
import java.util.List;
import org.come.entity.Gangapplytable;
import org.come.until.MessagrFlagUntil;
import org.come.until.AnalysisString;
import java.util.Vector;
import org.come.bean.LoginResult;
import org.come.bean.GangResultBean;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import com.tool.btn.FactionBtn;
import javax.swing.JPanel;

public class FactionMemberJpanel extends JPanel
{
    private FactionBtn btnMenuAll;
    private FactionBtn btnMenuCore;
    private FactionBtn btnMenuApply;
    private FactionBtn btnOffice;
    private FactionBtn btnOutgoing;
    private FactionBtn btnKick;
    private FactionBtn btnBreak;
    private FactionBtn btnAddFriend;
    private int menuType;
    private JTextField textField;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JLabel labCheck;
    private FactionCardJpanel factionCardJpanel;
    private int olineNum;
    private ImageIcon icon;
    private ImageIcon iconCoumn;
    
    public FactionMemberJpanel(FactionCardJpanel factionCardJpanel) {
        this.menuType = 5;
        this.factionCardJpanel = factionCardJpanel;
        this.setPreferredSize(new Dimension(662, 475));
        this.setOpaque(false);
        this.setLayout(null);
        this.getBtnMenuAll();
        this.getBtnMenuApply();
        this.getBtnMenuCore();
        this.getTextField();
        this.getScrollPane();
        this.getBtnOffice();
        this.getBtnOutgoing();
        this.getBtnKick();
        this.getBtnBreak();
        this.getBtnAddFriend();
        this.getLabCheck();
    }
    
    public void changeTable() {
        if (this.menuType < 7) {
            this.tableModel.setColumnCount(6);
            this.table.getColumnModel().getColumn(0).setPreferredWidth(110);
            this.table.getColumnModel().getColumn(1).setPreferredWidth(90);
            this.table.getColumnModel().getColumn(2).setPreferredWidth(105);
            this.table.getColumnModel().getColumn(3).setPreferredWidth(80);
            this.table.getColumnModel().getColumn(4).setPreferredWidth(120);
            this.table.getColumnModel().getColumn(5).setPreferredWidth(65);
        }
        else if (this.menuType == 7) {
            this.tableModel.setColumnCount(3);
            this.table.getColumnModel().getColumn(0).setPreferredWidth(190);
            this.table.getColumnModel().getColumn(1).setPreferredWidth(190);
            this.table.getColumnModel().getColumn(2).setPreferredWidth(190);
        }
    }
    
    public boolean Important(String post) {
        return post != null && (post.equals("帮主") || post.equals("护法"));
    }
    
    public boolean ImportantWang(String post) {
        return post != null && post.equals("帮主");
    }
    
    public void showMenuMessage(GangResultBean gangResultBean) {
        this.tableModel.setRowCount(0);
        this.olineNum = 0;
        if (gangResultBean == null) {
            return;
        }
        if (this.menuType == 5) {
            List<LoginResult> roleTables = gangResultBean.getRoleTables();
            Icon icon2 = this.labCheck.getIcon();
            for (int i = 0; i < roleTables.size(); ++i) {
                LoginResult loginResult = (LoginResult)roleTables.get(i);
                if (icon2 == null || "在线".equals(loginResult.getUptime())) {
                    Vector<String> rowData = new Vector<>();
                    rowData.add(loginResult.getRolename());
                    rowData.add(loginResult.getGangpost());
                    rowData.add(loginResult.getRace_name());
                    rowData.add(AnalysisString.lvl((int)loginResult.getGrade()));
                    rowData.add(loginResult.getContribution().toString());
                    if (!"在线".equals(loginResult.getUptime())) {
                        rowData.add(MessagrFlagUntil.timeStamp2Date((long)new Long(loginResult.getUptime()), "yy-MM-dd"));
                    }
                    else {
                        ++this.olineNum;
                        rowData.add(loginResult.getUptime());
                    }
                    this.tableModel.addRow(rowData);
                }
            }
        }
        else if (this.menuType == 6) {
            List<LoginResult> roleTables = gangResultBean.getRoleTables();
            Icon icon2 = this.labCheck.getIcon();
            for (int i = 0; i < roleTables.size(); ++i) {
                LoginResult loginResult = (LoginResult)roleTables.get(i);
                if ((icon2 == null || "在线".equals(loginResult.getUptime())) && this.Important(loginResult.getGangpost())) {
                    Vector<String> rowData = new Vector<>();
                    rowData.add(loginResult.getRolename());
                    rowData.add(loginResult.getGangpost());
                    rowData.add(loginResult.getRace_name());
                    rowData.add(AnalysisString.lvl((int)loginResult.getGrade()));
                    rowData.add(loginResult.getContribution().toString());
                    if (!"在线".equals(loginResult.getUptime())) {
                        rowData.add(MessagrFlagUntil.timeStamp2Date((long)new Long(loginResult.getUptime()), "yy-MM-dd"));
                    }
                    else {
                        ++this.olineNum;
                        rowData.add(loginResult.getUptime());
                    }
                    this.tableModel.addRow(rowData);
                }
            }
        }
        else if (this.menuType == 7) {
            List<Gangapplytable> gangapplytables = gangResultBean.getGangapplytables();
            for (int j = 0; j < gangapplytables.size(); ++j) {
                Gangapplytable gangapplytable = (Gangapplytable)gangapplytables.get(j);
                Vector<String> rowData2 = new Vector<>();
                rowData2.add(gangapplytable.getRolename());
                rowData2.add(gangapplytable.getRace_name());
                rowData2.add(AnalysisString.lvl(gangapplytable.getGrade().intValue()));
                this.tableModel.addRow(rowData2);
            }
        }
    }
    
    public void showBtn(boolean type) {
        if (MyIsif.getStyle().equals("水墨")) {
            try {
                LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                if (this.menuType < 7) {
                    if (type) {
                        if (this.ImportantWang(loginResult.getGangpost())) {
                            this.btnOffice.setBounds(204, 429, 59, 24);
                            this.btnOutgoing.setText("卸任");
                            this.btnOutgoing.setIcons(CutButtonImage.cuts("inkImg/button1/B20.png"));
                            this.btnOutgoing.setBounds(267, 429, 59, 24);
                            this.btnBreak.setText("脱离帮派");
                            this.btnKick.setText("踢出帮派");
                            this.btnKick.setVisible(type);
                            this.btnOffice.setVisible(type);
                            this.btnOutgoing.setVisible(type);
                        }
                        else if (this.Important(loginResult.getGangpost())) {
                            this.btnBreak.setText("脱离帮派");
                        }
                    }
                    else {
                        this.btnKick.setVisible(type);
                        this.btnOffice.setVisible(type);
                        this.btnOutgoing.setVisible(type);
                    }
                }
                else if (this.menuType == 7) {
                    if (type) {
                        if (this.Important(loginResult.getGangpost())) {
                            this.btnOutgoing.setText("拒绝玩家");
                            this.btnOutgoing.setIcons(CutButtonImage.cuts("inkImg/button1/B22.png"));
                            this.btnOutgoing.setBounds(227, 429, 99, 24);
                            this.btnBreak.setText("清空列表");
                            this.btnKick.setText("接收玩家");
                            this.btnKick.setVisible(type);
                            this.btnOutgoing.setVisible(type);
                        }
                    }
                    else {
                        this.btnKick.setVisible(type);
                        this.btnOutgoing.setVisible(type);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                if (this.menuType < 7) {
                    if (type) {
                        if (this.ImportantWang(loginResult.getGangpost())) {
                            this.btnOffice.setBounds(204, 429, 60, 26);
                            this.btnOutgoing.setText("卸任");
                            this.btnOutgoing.setIcons(CutButtonImage.cuts("inkImg/hongmu/6026.png"));
                            this.btnOutgoing.setBounds(267, 429, 60, 26);
                            this.btnBreak.setText("脱离帮派");
                            this.btnKick.setText("踢出帮派");
                            this.btnKick.setVisible(type);
                            this.btnOffice.setVisible(type);
                            this.btnOutgoing.setVisible(type);
                        }
                        else if (this.Important(loginResult.getGangpost())) {
                            this.btnBreak.setText("脱离帮派");
                        }
                    }
                    else {
                        this.btnKick.setVisible(type);
                        this.btnOffice.setVisible(type);
                        this.btnOutgoing.setVisible(type);
                    }
                }
                else if (this.menuType == 7) {
                    if (type) {
                        if (this.Important(loginResult.getGangpost())) {
                            this.btnOutgoing.setText("拒绝玩家");
                            this.btnOutgoing.setIcons(CutButtonImage.cuts("inkImg/hongmu/6026.png"));
                            this.btnOutgoing.setBounds(227, 429, 100, 26);
                            this.btnBreak.setText("清空列表");
                            this.btnKick.setText("接收玩家");
                            this.btnKick.setVisible(type);
                            this.btnOutgoing.setVisible(type);
                        }
                    }
                    else {
                        this.btnKick.setVisible(type);
                        this.btnOutgoing.setVisible(type);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B223.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 662, 475, null);
            if (this.iconCoumn != null) {
                g.drawImage(this.iconCoumn.getImage(), 49, 89, 589, 21, null);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S167.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 662, 475, null);
            if (this.iconCoumn != null) {
                g.drawImage(this.iconCoumn.getImage(), 49, 89, 589, 21, null);
            }
        }
    }
    
    public FactionBtn getBtnMenuAll() {
        if (this.btnMenuAll == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnMenuAll = new FactionBtn("inkImg/button/B273.png", 1, 5, this)).setBounds(56, 63, 96, 26);
            }
            else {
                (this.btnMenuAll = new FactionBtn("img/xy2uiimg/B273.png", 1, 5, this)).setBounds(56, 63, 75, 26);
            }
            this.add(this.btnMenuAll);
        }
        return this.btnMenuAll;
    }
    
    public void setBtnMenuAll(FactionBtn btnMenuAll) {
        this.btnMenuAll = btnMenuAll;
    }
    
    public FactionBtn getBtnMenuCore() {
        if (this.btnMenuCore == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnMenuCore = new FactionBtn("inkImg/button/B274.png", 1, 6, this)).setBounds(154, 63, 96, 26);
            }
            else {
                (this.btnMenuCore = new FactionBtn("img/xy2uiimg/B274.png", 1, 6, this)).setBounds(154, 63, 75, 26);
            }
            this.add(this.btnMenuCore);
        }
        return this.btnMenuCore;
    }
    
    public void setBtnMenuCore(FactionBtn btnMenuCore) {
        this.btnMenuCore = btnMenuCore;
    }
    
    public FactionBtn getBtnMenuApply() {
        if (this.btnMenuApply == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnMenuApply = new FactionBtn("inkImg/button/B278.png", 1, 7, this)).setBounds(252, 63, 96, 26);
            }
            else {
                (this.btnMenuApply = new FactionBtn("img/xy2uiimg/v291.png", 1, 7, this)).setBounds(252, 63, 75, 26);
            }
            this.add(this.btnMenuApply);
        }
        return this.btnMenuApply;
    }
    
    public void setBtnMenuApply(FactionBtn btnMenuApply) {
        this.btnMenuApply = btnMenuApply;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public ImageIcon getIconCoumn() {
        return this.iconCoumn;
    }
    
    public void setIconCoumn(ImageIcon iconCoumn) {
        this.iconCoumn = iconCoumn;
    }
    
    public int getMenuType() {
        return this.menuType;
    }
    
    public void setMenuType(int menuType) {
        this.menuType = menuType;
    }
    
    public JTextField getTextField() {
        if (this.textField == null) {
            (this.textField = new JTextField()).setBounds(540, 69, 94, 16);
            this.textField.setOpaque(false);
            this.textField.setBorder(BorderFactory.createEmptyBorder());
            this.textField.setForeground(Color.white);
            this.textField.setCaretColor(Color.white);
            this.textField.setFont(UIUtils.TEXT_FONT1);
            this.add(this.textField);
        }
        return this.textField;
    }
    
    public void setTextField(JTextField textField) {
        this.textField = textField;
    }
    
    public JTable getTable() {
        if (this.table == null) {
            (this.table = new JTable() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            }).setShowGrid(false);
            this.table.setOpaque(false);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if (row < FactionMemberJpanel.this.olineNum) {
                        this.setForeground(Color.ORANGE);
                    }
                    else {
                        this.setForeground(Color.white);
                    }
                    return this;
                }
            };
            renderer.setHorizontalAlignment(0);
            this.table.setDefaultRenderer(Object.class, renderer);
            JTableHeader header = this.table.getTableHeader();
            header.setPreferredSize(new Dimension(header.getWidth(), 0));
            this.table.getTableHeader().setBackground(UIUtils.Color_BACK);
            this.table.getTableHeader().setVisible(true);
            this.table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            this.table.setSelectionBackground(UIUtils.Color_BACK);
            this.table.setSelectionForeground(Color.white);
            this.table.setForeground(Color.white);
            this.table.setFont(UIUtils.TEXT_FONT);
            this.table.setBackground(UIUtils.Color_BACK);
            this.table.setModel(this.getTableModel());
            this.changeTable();
            this.table.setRowHeight(25);
            this.table.setAutoResizeMode(0);
            this.table.isCellEditable(1, 1);
            this.table.setEnabled(true);
        }
        return this.table;
    }
    
    public void setTable(JTable table) {
        this.table = table;
    }
    
    public DefaultTableModel getTableModel() {
        if (this.tableModel == null) {
            this.tableModel = new DefaultTableModel();
            Vector<Vector<String>> verVectors = new Vector<>();
            for (int i = 0; i < 50; ++i) {
                Vector<String> vector = new Vector<>();
                for (int j = 0; j < 6; ++j) {
                    vector.add(String.valueOf("2020-01-09"));
                }
                verVectors.add(vector);
            }
            Vector<String> vector2 = new Vector<>();
            vector2.add("");
            vector2.add("");
            vector2.add("");
            vector2.add("");
            vector2.add("");
            vector2.add("");
            vector2.add("");
            vector2.add("");
            vector2.add("");
            this.tableModel.setDataVector(verVectors, vector2);
        }
        return this.tableModel;
    }
    
    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
    
    public JScrollPane getScrollPane() {
        if (this.scrollPane == null) {
            this.scrollPane = new JScrollPane(this.getTable());
            if (MyIsif.getStyle().equals("水墨")) {
                this.scrollPane.setBounds(51, 109, 585, 314);
            }
            else {
                this.scrollPane.setBounds(30, 109, 610, 314);
            }
            this.scrollPane.setOpaque(false);
            this.scrollPane.setVerticalScrollBarPolicy(22);
            this.scrollPane.setHorizontalScrollBarPolicy(31);
            this.scrollPane.getVerticalScrollBar().setUI(new ScrollUI());
            this.scrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.scrollPane.getViewport().setOpaque(false);
            this.add(this.scrollPane);
        }
        return this.scrollPane;
    }
    
    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    
    public FactionBtn getBtnOffice() {
        if (this.btnOffice == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnOffice = new FactionBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "任职", UIUtils.TEXT_HY16, 10, this)).setBounds(204, 429, 59, 24);
            }
            else {
                (this.btnOffice = new FactionBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, "任职", UIUtils.TEXT_HY88, 10, this)).setBounds(204, 429, 60, 26);
            }
            this.add(this.btnOffice);
        }
        return this.btnOffice;
    }
    
    public void setBtnOffice(FactionBtn btnOffice) {
        this.btnOffice = btnOffice;
    }
    
    public FactionBtn getBtnOutgoing() {
        if (this.btnOutgoing == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnOutgoing = new FactionBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, "卸任", UIUtils.TEXT_HY16, 11, this)).setBounds(267, 429, 59, 24);
            }
            else {
                (this.btnOutgoing = new FactionBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, "卸任", UIUtils.TEXT_HY88, 11, this)).setBounds(267, 429, 60, 26);
            }
            this.add(this.btnOutgoing);
        }
        return this.btnOutgoing;
    }
    
    public void setBtnOutgoing(FactionBtn btnOutgoing) {
        this.btnOutgoing = btnOutgoing;
    }
    
    public FactionBtn getBtnKick() {
        if (this.btnKick == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnKick = new FactionBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, "踢出帮派", UIUtils.TEXT_HY16, 12, this)).setBounds(330, 429, 99, 24);
            }
            else {
                (this.btnKick = new FactionBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, "踢出帮派", UIUtils.TEXT_HY88, 12, this)).setBounds(330, 429, 80, 26);
            }
            this.add(this.btnKick);
        }
        return this.btnKick;
    }
    
    public void setBtnKick(FactionBtn btnKick) {
        this.btnKick = btnKick;
    }
    
    public FactionBtn getBtnBreak() {
        if (this.btnBreak == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnBreak = new FactionBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, "脱离帮派", UIUtils.TEXT_HY16, 13, this)).setBounds(433, 429, 99, 24);
            }
            else {
                (this.btnBreak = new FactionBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, "脱离帮派", UIUtils.TEXT_HY88, 13, this)).setBounds(433, 429, 80, 26);
            }
            this.add(this.btnBreak);
        }
        return this.btnBreak;
    }
    
    public void setBtnBreak(FactionBtn btnBreak) {
        this.btnBreak = btnBreak;
    }
    
    public FactionBtn getBtnAddFriend() {
        if (this.btnAddFriend == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnAddFriend = new FactionBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, "加为好友", UIUtils.TEXT_HY16, 14, this)).setBounds(536, 429, 99, 24);
            }
            else {
                (this.btnAddFriend = new FactionBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, "加为好友", UIUtils.TEXT_HY88, 14, this)).setBounds(536, 429, 80, 26);
            }
            this.add(this.btnAddFriend);
        }
        return this.btnAddFriend;
    }
    
    public void setBtnAddFriend(FactionBtn btnAddFriend) {
        this.btnAddFriend = btnAddFriend;
    }
    
    public JLabel getLabCheck() {
        if (this.labCheck == null) {
            (this.labCheck = new JLabel()).setBounds(50, 437, 17, 16);
            this.labCheck.setOpaque(false);
            this.labCheck.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (FactionMemberJpanel.this.labCheck.getIcon() != null) {
                        FactionMemberJpanel.this.labCheck.setIcon(null);
                    }
                    else {
                        FactionMemberJpanel.this.labCheck.setIcon(CutButtonImage.getImage("inkImg/button/B88.png", -1, -1));
                    }
                    if (FactionMemberJpanel.this.menuType != 7) {
                        FactionMemberJpanel.this.showMenuMessage(FactionMemberJpanel.this.factionCardJpanel.getGangResultBean());
                    }
                }
            });
            this.add(this.labCheck);
        }
        return this.labCheck;
    }
    
    public void setLabCheck(JLabel labCheck) {
        this.labCheck = labCheck;
    }
    
    public FactionCardJpanel getFactionCardJpanel() {
        return this.factionCardJpanel;
    }
    
    public void setFactionCardJpanel(FactionCardJpanel factionCardJpanel) {
        this.factionCardJpanel = factionCardJpanel;
    }
}
