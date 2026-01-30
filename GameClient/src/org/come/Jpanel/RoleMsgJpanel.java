package org.come.Jpanel;

import java.awt.Graphics;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.bean.Role_bean;
import cn.hutool.core.thread.ThreadUtil;
import org.come.until.CutButtonImage;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.until.AnalysisString;
import java.awt.Font;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.RoleMsgBtn;
import javax.swing.JLabel;
import java.math.BigDecimal;
import javax.swing.JPanel;

public class RoleMsgJpanel extends JPanel
{
    private BigDecimal id;
    private String role;
    private JLabel labTx;
    private JLabel labname;
    private JLabel lablvl;
    private JLabel labchenwei;
    private JLabel labgang;
    private RoleMsgBtn btnzudui;
    private RoleMsgBtn btnhaoyou;
    private RoleMsgBtn btnjiaoyi;
    private RoleMsgBtn labgive;
    private RoleMsgBtn labpk;
    private RoleMsgBtn lbasuod;
    private RoleMsgBtn labjiaotan;
    private ImageIcon icon;
    private ImageIcon iconx;
    private ImageIcon icont;
    
    public RoleMsgJpanel() {
        if ("水墨".equals(MyIsif.getStyle())) {
            this.setPreferredSize(new Dimension(160, 185));
            this.setLayout(null);
            this.setBackground(new Color(0, 0, 0, 0));
            (this.labTx = new JLabel()).setBounds(22, 18, 40, 40);
            this.add(this.labTx);
            (this.labname = new JLabel()).setBounds(22, 18, 40, 40);
            this.add(this.labname);
            (this.lablvl = new JLabel()).setBounds(22, 18, 40, 40);
            this.add(this.lablvl);
            (this.labchenwei = new JLabel()).setBounds(22, 18, 40, 40);
            this.add(this.labchenwei);
            (this.labgang = new JLabel()).setBounds(22, 18, 40, 40);
            this.add(this.labgang);
            (this.btnzudui = new RoleMsgBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "组队", 1)).setBounds(22, 18, 40, 40);
            this.add(this.btnzudui);
            (this.btnjiaoyi = new RoleMsgBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "交易", 2)).setBounds(22, 18, 40, 40);
            this.add(this.btnjiaoyi);
            (this.btnhaoyou = new RoleMsgBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "好友", 3)).setBounds(22, 18, 40, 40);
            this.add(this.btnhaoyou);
            (this.labgive = new RoleMsgBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "给与", 4)).setBounds(22, 18, 40, 40);
            this.add(this.labgive);
            (this.labpk = new RoleMsgBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "切磋", 5)).setBounds(22, 18, 40, 40);
            this.add(this.labpk);
            (this.lbasuod = new RoleMsgBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "锁定", 20)).setBounds(22, 18, 40, 40);
            this.add(this.lbasuod);
            (this.labjiaotan = new RoleMsgBtn("inkImg/button1/B30.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT, "交谈", 7)).setBounds(22, 18, 40, 40);
            this.add(this.labjiaotan);
        }
        else {
            this.setPreferredSize(new Dimension(160, 185));
            this.setLayout(null);
            this.setBackground(new Color(0, 0, 0, 0));
            (this.labTx = new JLabel()).setBounds(22, 18, 40, 40);
            this.add(this.labTx);
            (this.labname = new JLabel()).setBounds(22, 18, 40, 40);
            this.add(this.labname);
            (this.lablvl = new JLabel()).setBounds(22, 18, 40, 40);
            this.add(this.lablvl);
            (this.labchenwei = new JLabel()).setBounds(22, 18, 40, 40);
            this.add(this.labchenwei);
            (this.labgang = new JLabel()).setBounds(22, 18, 40, 40);
            this.add(this.labgang);
            (this.btnzudui = new RoleMsgBtn("inkImg/button1/ss531.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT, "组队", 1)).setBounds(22, 18, 40, 40);
            this.add(this.btnzudui);
            (this.btnjiaoyi = new RoleMsgBtn("inkImg/button1/ss531.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT, "交易", 2)).setBounds(22, 18, 40, 40);
            this.add(this.btnjiaoyi);
            (this.btnhaoyou = new RoleMsgBtn("inkImg/button1/ss531.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT, "好友", 3)).setBounds(22, 18, 40, 40);
            this.add(this.btnhaoyou);
            (this.labgive = new RoleMsgBtn("inkImg/button1/ss531.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT, "给与", 4)).setBounds(22, 18, 40, 40);
            this.add(this.labgive);
            (this.labpk = new RoleMsgBtn("inkImg/button1/ss531.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT, "切磋", 5)).setBounds(22, 18, 40, 40);
            this.add(this.labpk);
            (this.lbasuod = new RoleMsgBtn("inkImg/button1/ss531.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT, "锁定", 20)).setBounds(22, 18, 40, 40);
            this.add(this.lbasuod);
            (this.labjiaotan = new RoleMsgBtn("inkImg/button1/ss531.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT, "交谈", 7)).setBounds(22, 18, 40, 40);
            this.add(this.labjiaotan);
        }
    }
    
    public void show(BigDecimal id, String name) {
        this.id = id;
        this.role = name;
        try {
            RoleMsgBtn.role_bean = null;
            String msg = Agreement.getAgreement().searcahChatRoleIdAgreement(id.toString());
            SendMessageUntil.toServer(msg);
            ThreadUtil.execAsync(new Thread(()/*  */ -> {
                try {
                    Role_bean role_bean;
                    for (role_bean = RoleMsgBtn.role_bean; role_bean == null; role_bean = RoleMsgBtn.role_bean) {
                        Thread.sleep(100L);
                    }
                    this.labchenwei.setForeground(Color.green);
                    this.labchenwei.setText(role_bean.getTitle());
                    this.labchenwei.setFont(new Font("宋体", 0, 13));
                    this.labgang.setForeground(Color.white);
                    this.labgang.setText(role_bean.getGangname());
                    this.labgang.setFont(new Font("宋体", 0, 13));
                    this.lablvl.setForeground(Color.white);
                    this.lablvl.setText(AnalysisString.lvl((int)role_bean.getGrade()) + "级");
                    this.lablvl.setFont(new Font("宋体", 0, 13));
                    this.labname.setText(name);
                    this.labname.setForeground(UIUtils.getcolor(role_bean.getTurnAround()));
                    this.labname.setFont(new Font("宋体", 0, 13));
                    ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                    Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                    Configure configure = (Configure)item.get(new BigDecimal(1));
                    String nao = "新";
                    if (configure.getNeworold() != null) {
                        nao = configure.getNeworold();
                    }
                    if (nao.equals("新")) {
                        this.labTx.setIcon(CutButtonImage.getImage("img/head/s" + role_bean.getSpeciesId() + "-1.png", 40, 40));
                    }
                    else {
                        this.labTx.setIcon(CutButtonImage.getImage("img/head/s" + role_bean.getSpeciesId() + ".png", 40, 40));
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }));
        }
        catch (Exception ex) {}
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if ("水墨".equals(MyIsif.getStyle())) {
            this.labTx.setBounds(11, 15, 40, 40);
            this.labname.setBounds(61, 5, 120, 15);
            this.lablvl.setBounds(61, 20, 120, 15);
            this.labgang.setBounds(61, 35, 120, 15);
            this.labchenwei.setBounds(61, 50, 120, 15);
            this.btnzudui.setBounds(114, 75, 37, 17);
            this.btnjiaoyi.setBounds(114, 97, 34, 17);
            this.btnhaoyou.setBounds(114, 119, 34, 17);
            this.labgive.setBounds(114, 141, 34, 17);
            this.labpk.setBounds(114, 163, 34, 17);
            this.lbasuod.setBounds(64, 97, 34, 17);
            this.labjiaotan.setBounds(64, 75, 34, 17);
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/button1/s600.png");
            }
            if (this.iconx == null) {
                this.iconx = new ImageIcon("inkImg/button1/s602.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, null);
            g.drawImage(this.iconx.getImage(), 5, 9, 52, 52, null);
            if (this.icont == null) {
                this.icont = new ImageIcon("inkImg/button1/s601.png");
            }
            g.drawImage(this.icont.getImage(), 58, 70, 96, 115, null);
        }
        else {
            this.labTx.setBounds(11, 15, 40, 40);
            this.labname.setBounds(61, 5, 120, 15);
            this.lablvl.setBounds(61, 20, 120, 15);
            this.labgang.setBounds(61, 35, 120, 15);
            this.labchenwei.setBounds(61, 50, 120, 15);
            this.btnzudui.setBounds(114, 75, 37, 17);
            this.btnjiaoyi.setBounds(114, 97, 34, 17);
            this.btnhaoyou.setBounds(114, 119, 34, 17);
            this.labgive.setBounds(114, 141, 34, 17);
            this.labpk.setBounds(114, 163, 34, 17);
            this.lbasuod.setBounds(64, 97, 34, 17);
            this.labjiaotan.setBounds(64, 75, 34, 17);
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/button1/s600h.png");
            }
            if (this.iconx == null) {
                this.iconx = new ImageIcon("inkImg/button1/s602h.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, null);
            g.drawImage(this.iconx.getImage(), 5, 9, 52, 52, null);
            if (this.icont == null) {
                this.icont = new ImageIcon("inkImg/button1/s601h.png");
            }
            g.drawImage(this.icont.getImage(), 58, 70, 96, 115, null);
        }
    }
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public RoleMsgBtn getLbasuod() {
        return this.lbasuod;
    }
    
    public void setLbasuod(RoleMsgBtn lbasuod) {
        this.lbasuod = lbasuod;
    }
    
    public RoleMsgBtn getLabjiaotan() {
        return this.labjiaotan;
    }
    
    public void setLabjiaotan(RoleMsgBtn labjiaotan) {
        this.labjiaotan = labjiaotan;
    }
}
