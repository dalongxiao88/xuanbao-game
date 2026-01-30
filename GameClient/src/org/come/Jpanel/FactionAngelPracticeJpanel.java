package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.until.Util;
import java.awt.Graphics;
import org.come.bean.LoginResult;
import com.tool.role.RoleData;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.math.BigDecimal;
import com.tool.btn.FactionBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FactionAngelPracticeJpanel extends JPanel
{
    private JLabel labLvl;
    private JLabel labUpLvl;
    private JLabel labNeedExp;
    private JLabel labHaveExp;
    private JLabel labNeedGoog;
    private JLabel labHaveGoog;
    private FactionBtn btnPractice;
    private BigDecimal money;
    private int menuType;
    private ImageIcon icon;
    
    public FactionAngelPracticeJpanel() {
        this.money = new BigDecimal(100000);
        this.menuType = -1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(363, 304));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 106);
            offBtn.setBounds(326, 10, 25, 25);
            this.add(offBtn);
        }
        else {
            this.setPreferredSize(new Dimension(401, 384));
            this.setOpaque(false);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/S74.png", 1, 106);
            offBtn.setBounds(376, 0, 25, 25);
            this.add(offBtn);
        }
        this.getLabHaveExp();
        this.getLabHaveGoog();
        this.getLabLvl();
        this.getLabNeedExp();
        this.getLabNeedGoog();
        this.getLabUpLvl();
        this.getBtnPractice();
    }
    
    public void showPanel(int type) {
        this.menuType = type;
        if (this.menuType == 1) {
            this.btnPractice.setText("小成修炼");
            this.money = new BigDecimal(2000000);
            this.labNeedExp.setText("2000000");
            this.labNeedGoog.setText("300");
        }
        else if (this.menuType == 2) {
            this.btnPractice.setText("大成修炼");
            this.labNeedExp.setText("5000000");
            this.money = new BigDecimal(5000000);
            this.labNeedGoog.setText("1500");
        }
        this.showMessage();
    }
    
    public void showMessage() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (this.menuType == 1) {
            int extraPointInt = loginResult.getExtraPointInt("X");
            this.labLvl.setText(String.valueOf(extraPointInt));
            this.labUpLvl.setText(String.valueOf(30 - extraPointInt));
        }
        else if (this.menuType == 2) {
            int extraPointInt = loginResult.getExtraPointInt("D");
            this.labLvl.setText(String.valueOf(extraPointInt));
            this.labUpLvl.setText(String.valueOf(60 - extraPointInt));
        }
        this.labHaveExp.setText(loginResult.getExperience().toString());
        this.labHaveGoog.setText(loginResult.getContribution().toString());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S175.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 363, 304, null);
            Util.drawMoney(g, 188, 230);
            if (this.money != null) {
                Util.drawPrice(g, this.money, 188, 206);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/S175.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 401, 384, null);
            Util.drawMoney(g, 188, 320);
            if (this.money != null) {
                Util.drawPrice(g, this.money, 188, 290);
            }
        }
    }
    
    public JLabel getLabLvl() {
        if (this.labLvl == null) {
            this.labLvl = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labLvl.setBounds(167, 29, 40, 16);
            }
            else {
                this.labLvl.setBounds(167, 43, 40, 16);
            }
            this.labLvl.setForeground(Color.white);
            this.labLvl.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labLvl);
        }
        return this.labLvl;
    }
    
    public void setLabLvl(JLabel labLvl) {
        this.labLvl = labLvl;
    }
    
    public JLabel getLabUpLvl() {
        if (this.labUpLvl == null) {
            this.labUpLvl = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labUpLvl.setBounds(286, 29, 40, 16);
            }
            else {
                this.labUpLvl.setBounds(325, 43, 40, 16);
            }
            this.labUpLvl.setForeground(Color.white);
            this.labUpLvl.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labUpLvl);
        }
        return this.labUpLvl;
    }
    
    public void setLabUpLvl(JLabel labUpLvl) {
        this.labUpLvl = labUpLvl;
    }
    
    public JLabel getLabNeedExp() {
        if (this.labNeedExp == null) {
            this.labNeedExp = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labNeedExp.setBounds(188, 89, 127, 16);
            }
            else {
                this.labNeedExp.setBounds(188, 155, 127, 16);
            }
            this.labNeedExp.setForeground(Color.white);
            this.labNeedExp.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labNeedExp);
        }
        return this.labNeedExp;
    }
    
    public void setLabNeedExp(JLabel labNeedExp) {
        this.labNeedExp = labNeedExp;
    }
    
    public JLabel getLabHaveExp() {
        if (this.labHaveExp == null) {
            this.labHaveExp = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labHaveExp.setBounds(188, 115, 127, 16);
            }
            else {
                this.labHaveExp.setBounds(188, 185, 127, 16);
            }
            this.labHaveExp.setForeground(Color.white);
            this.labHaveExp.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labHaveExp);
        }
        return this.labHaveExp;
    }
    
    public void setLabHaveExp(JLabel labHaveExp) {
        this.labHaveExp = labHaveExp;
    }
    
    public JLabel getLabNeedGoog() {
        if (this.labNeedGoog == null) {
            this.labNeedGoog = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labNeedGoog.setBounds(188, 141, 127, 16);
            }
            else {
                this.labNeedGoog.setBounds(188, 215, 127, 16);
            }
            this.labNeedGoog.setForeground(Color.white);
            this.labNeedGoog.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labNeedGoog);
        }
        return this.labNeedGoog;
    }
    
    public void setLabNeedGoog(JLabel labNeedGoog) {
        this.labNeedGoog = labNeedGoog;
    }
    
    public JLabel getLabHaveGoog() {
        if (this.labHaveGoog == null) {
            this.labHaveGoog = new JLabel();
            if (MyIsif.getStyle().equals("水墨")) {
                this.labHaveGoog.setBounds(188, 167, 127, 16);
            }
            else {
                this.labHaveGoog.setBounds(188, 245, 127, 16);
            }
            this.labHaveGoog.setForeground(Color.white);
            this.labHaveGoog.setFont(UIUtils.TEXT_FONT1);
            this.add(this.labHaveGoog);
        }
        return this.labHaveGoog;
    }
    
    public void setLabHaveGoog(JLabel labHaveGoog) {
        this.labHaveGoog = labHaveGoog;
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public FactionBtn getBtnPractice() {
        if (this.btnPractice == null) {
            if (MyIsif.getStyle().equals("水墨")) {
                (this.btnPractice = new FactionBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, "修炼", UIUtils.TEXT_HY16, 24, this)).setBounds(146, 250, 99, 24);
            }
            else {
                (this.btnPractice = new FactionBtn("inkImg/hongmu/1_png.button.btn_xy.png", 1, UIUtils.COLOR_BTNPUTONG, "修炼", UIUtils.TEXT_HY88, 24, this)).setBounds(196, 335, 80, 26);
            }
            this.add(this.btnPractice);
        }
        return this.btnPractice;
    }
    
    public void setBtnPractice(FactionBtn btnPractice) {
        this.btnPractice = btnPractice;
    }
    
    public int getMenuType() {
        return this.menuType;
    }
    
    public void setMenuType(int menuType) {
        this.menuType = menuType;
    }
}
