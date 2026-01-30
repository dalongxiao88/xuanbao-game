package org.come.Jpanel;

import java.awt.Graphics;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.until.AnalysisString;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import javax.swing.ImageIcon;
import org.come.entity.Friendtable;
import com.tool.btn.RoleCaoZuoBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FriendMsgJpanel extends JPanel
{
    private JLabel labTxk;
    private JLabel labTxkD;
    private JLabel labName;
    private JLabel labCw;
    private JLabel labId;
    private JLabel labGrade;
    private JLabel labZz;
    private JLabel labGang;
    private RoleCaoZuoBtn btnDj;
    private RoleCaoZuoBtn btnLsxx;
    private RoleCaoZuoBtn btnFsxx;
    private RoleCaoZuoBtn btnJy;
    private RoleCaoZuoBtn btnSqrd;
    private Friendtable friend;
    private ImageIcon icon;
    
    public void show(Friendtable friend) {
        this.friend = friend;
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        String nao = "新";
        if (configure.getNeworold() != null) {
            nao = configure.getNeworold();
        }
        if (nao.equals("新")) {
            this.labTxk.setIcon(new ImageIcon("img/head/" + friend.getSpecies_id() + "-1.png"));
            this.labTxkD.setIcon(new ImageIcon("img/head/Ds.png"));
        }
        else {
            this.labTxk.setIcon(new ImageIcon("img/head/" + friend.getSpecies_id() + ".png"));
            this.labTxkD.setIcon(new ImageIcon("img/head/Ds.png"));
        }
        this.labId.setText(friend.getRole_id().toString());
        this.labName.setText(friend.getRolename());
        this.labGrade.setText(AnalysisString.lvl(Integer.parseInt(friend.getGrade().toString())) + "级");
        this.labZz.setText(friend.getRace_name());
        this.labCw.setText("");
        this.labGang.setText("");
    }
    
    public FriendMsgJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(590, 409));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 76);
            offBtn.setBounds(553, 10, 25, 25);
            this.add(offBtn);
            (this.labTxk = new JLabel("", 0)).setBounds(50, 68, 75, 100);
            this.add(this.labTxk);
            (this.labTxkD = new JLabel("", 0)).setBounds(344, 48, 218, 298);
            this.add(this.labTxkD);
            (this.labName = new JLabel()).setBounds(210, 38, 115, 16);
            this.labName.setForeground(Color.WHITE);
            this.labName.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labName);
            (this.labCw = new JLabel()).setBounds(210, 65, 115, 16);
            this.labCw.setForeground(Color.WHITE);
            this.labCw.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labCw);
            (this.labId = new JLabel()).setBounds(210, 85, 115, 16);
            this.labId.setForeground(Color.WHITE);
            this.labId.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labId);
            (this.labGrade = new JLabel()).setBounds(210, 107, 115, 16);
            this.labGrade.setForeground(Color.WHITE);
            this.labGrade.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labGrade);
            (this.labZz = new JLabel()).setBounds(210, 130, 115, 16);
            this.labZz.setForeground(Color.WHITE);
            this.labZz.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labZz);
            (this.labGang = new JLabel()).setBounds(210, 180, 115, 16);
            this.labGang.setForeground(Color.WHITE);
            this.labGang.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labGang);
            (this.btnDj = new RoleCaoZuoBtn("inkImg/button1/B30.png", 1, "断交", 8, UIUtils.COLOR_BTNTEXT)).setBounds(43, 36, 34, 17);
            this.add(this.btnDj);
            (this.btnLsxx = new RoleCaoZuoBtn("inkImg/button1/B34.png", 1, "历史消息", 9, UIUtils.COLOR_BTNTEXT)).setBounds(54, 178, 68, 17);
            this.add(this.btnLsxx);
            (this.btnFsxx = new RoleCaoZuoBtn("inkImg/button1/B22.png", 1, "发送消息", 10, UIUtils.COLOR_BLACK)).setBounds(49, 359, 99, 24);
            this.add(this.btnFsxx);
            (this.btnJy = new RoleCaoZuoBtn("inkImg/button1/B20.png", 1, "交易", 10, UIUtils.COLOR_BLACK)).setBounds(158, 359, 59, 24);
            this.add(this.btnJy);
            (this.btnSqrd = new RoleCaoZuoBtn("inkImg/button1/B22.png", 1, "申请入队", 10, UIUtils.COLOR_BLACK)).setBounds(227, 359, 99, 24);
            this.add(this.btnSqrd);
        }
        else {
            this.setPreferredSize(new Dimension(325, 275));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 76);
            offBtn.setBounds(299, 0, 23, 23);
            this.add(offBtn);
            (this.labTxk = new JLabel("", 0)).setBounds(22, 56, 75, 100);
            this.add(this.labTxk);
            (this.labName = new JLabel()).setBounds(187, 52, 115, 16);
            this.labName.setForeground(Color.WHITE);
            this.labName.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labName);
            (this.labCw = new JLabel()).setBounds(187, 75, 115, 16);
            this.labCw.setForeground(Color.WHITE);
            this.labCw.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labCw);
            (this.labId = new JLabel()).setBounds(187, 98, 115, 16);
            this.labId.setForeground(Color.WHITE);
            this.labId.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labId);
            (this.labGrade = new JLabel()).setBounds(187, 121, 115, 16);
            this.labGrade.setForeground(Color.WHITE);
            this.labGrade.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labGrade);
            (this.labZz = new JLabel()).setBounds(187, 144, 115, 16);
            this.labZz.setForeground(Color.WHITE);
            this.labZz.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labZz);
            (this.labGang = new JLabel()).setBounds(187, 167, 115, 16);
            this.labGang.setForeground(Color.WHITE);
            this.labGang.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labGang);
            (this.btnDj = new RoleCaoZuoBtn("inkImg/hongmu/21_png.button.tab_nex.png", 1, "断交", 8, UIUtils.COLOR_BTNTEXT)).setBounds(21, 32, 48, 17);
            this.add(this.btnDj);
            (this.btnLsxx = new RoleCaoZuoBtn("inkImg/hongmu/a2.png", 1, "历史消息", 9, UIUtils.COLOR_BTNPUTONG)).setBounds(29, 166, 85, 26);
            this.add(this.btnLsxx);
            (this.btnFsxx = new RoleCaoZuoBtn("inkImg/hongmu/a2.png", 1, "发送消息", 10, UIUtils.COLOR_BTNPUTONG)).setBounds(25, 209, 85, 26);
            this.add(this.btnFsxx);
            (this.btnJy = new RoleCaoZuoBtn("inkImg/hongmu/a1.png", 1, "交易", 10, UIUtils.COLOR_BTNPUTONG)).setBounds(125, 209, 60, 30);
            this.add(this.btnJy);
            (this.btnSqrd = new RoleCaoZuoBtn("inkImg/hongmu/a2.png", 1, "申请入队", 10, UIUtils.COLOR_BTNPUTONG)).setBounds(205, 209, 85, 26);
            this.add(this.btnSqrd);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B329.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 590, 409, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/FriendMsg.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 325, 275, this);
        }
    }
    
    public JLabel getLabTxk() {
        return this.labTxk;
    }
    
    public void setLabTxk(JLabel labTxk) {
        this.labTxk = labTxk;
    }
    
    public JLabel getLabName() {
        return this.labName;
    }
    
    public void setLabName(JLabel labName) {
        this.labName = labName;
    }
    
    public JLabel getLabCw() {
        return this.labCw;
    }
    
    public void setLabCw(JLabel labCw) {
        this.labCw = labCw;
    }
    
    public JLabel getLabId() {
        return this.labId;
    }
    
    public void setLabId(JLabel labId) {
        this.labId = labId;
    }
    
    public JLabel getLabGrade() {
        return this.labGrade;
    }
    
    public void setLabGrade(JLabel labGrade) {
        this.labGrade = labGrade;
    }
    
    public JLabel getLabZz() {
        return this.labZz;
    }
    
    public void setLabZz(JLabel labZz) {
        this.labZz = labZz;
    }
    
    public JLabel getLabGang() {
        return this.labGang;
    }
    
    public void setLabGang(JLabel labGang) {
        this.labGang = labGang;
    }
    
    public Friendtable getFriend() {
        return this.friend;
    }
    
    public void setFriend(Friendtable friend) {
        this.friend = friend;
    }
}
