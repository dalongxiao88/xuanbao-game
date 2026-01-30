package org.come.Jpanel;

import java.awt.Graphics;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import java.awt.Font;
import com.tool.role.RoleData;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Color;
import javax.swing.BorderFactory;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import java.math.BigDecimal;
import com.tool.btn.RoleCaoZuoBtn;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class QZCQJpanel extends JPanel
{
    private static JTextField cunqianMoney;
    private RoleCaoZuoBtn sureGive;
    private RoleCaoZuoBtn deleteGive;
    private BigDecimal gold;
    private BigDecimal Moneyshopgold;
    private ImageIcon icon;
    
    public QZCQJpanel() throws Exception {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(268, 309));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 911);
            offBtn.setBounds(233, 10, 25, 25);
            this.add(offBtn);
            (QZCQJpanel.cunqianMoney = new JTextField()).setBounds(103, 197, 128, 20);
            QZCQJpanel.cunqianMoney.setOpaque(false);
            QZCQJpanel.cunqianMoney.setBorder(BorderFactory.createEmptyBorder());
            QZCQJpanel.cunqianMoney.setForeground(Color.white);
            QZCQJpanel.cunqianMoney.setCaretColor(Color.white);
            QZCQJpanel.cunqianMoney.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int keyChar = e.getKeyChar();
                    if (keyChar < 48 || keyChar > 57) {
                        e.consume();
                    }
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    if (QZCQJpanel.cunqianMoney.getText() != null && QZCQJpanel.cunqianMoney.getText().length() > 0) {
                        if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal(QZCQJpanel.cunqianMoney.getText())) == -1) {
                            QZCQJpanel.cunqianMoney.setFont(new Font("宋体", 0, 15));
                            QZCQJpanel.cunqianMoney.setText(RoleData.getRoleData().getLoginResult().getGold().toString());
                        }
                        else {
                            QZCQJpanel.cunqianMoney.setForeground(Color.white);
                            if (QZCQJpanel.cunqianMoney.getText().length() > 6) {
                                QZCQJpanel.cunqianMoney.setForeground(Color.YELLOW);
                            }
                            if (QZCQJpanel.cunqianMoney.getText().length() > 7) {
                                QZCQJpanel.cunqianMoney.setForeground(UIUtils.COLOR_NAME2);
                            }
                            if (QZCQJpanel.cunqianMoney.getText().length() > 8) {
                                QZCQJpanel.cunqianMoney.setForeground(Color.GREEN);
                            }
                            if (QZCQJpanel.cunqianMoney.getText().length() > 9) {
                                QZCQJpanel.cunqianMoney.setForeground(Color.RED);
                            }
                        }
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            this.add(QZCQJpanel.cunqianMoney);
            (this.sureGive = new RoleCaoZuoBtn("inkImg/button1/B20.png", 1, "确认", 44, UIUtils.COLOR_BLACK)).setBounds(57, 250, 59, 24);
            this.add(this.sureGive);
            (this.deleteGive = new RoleCaoZuoBtn("inkImg/button1/B20.png", 1, "取消", 45, UIUtils.COLOR_BLACK)).setBounds(157, 250, 59, 24);
            this.add(this.deleteGive);
        }
        else {
            this.setPreferredSize(new Dimension(294, 344));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 911);
            offBtn.setBounds(269, 1, 23, 23);
            this.add(offBtn);
            (QZCQJpanel.cunqianMoney = new JTextField()).setBounds(103, 223, 128, 15);
            QZCQJpanel.cunqianMoney.setOpaque(false);
            QZCQJpanel.cunqianMoney.setBorder(BorderFactory.createEmptyBorder());
            QZCQJpanel.cunqianMoney.setForeground(Color.white);
            QZCQJpanel.cunqianMoney.setCaretColor(Color.white);
            QZCQJpanel.cunqianMoney.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int keyChar = e.getKeyChar();
                    if (keyChar < 48 || keyChar > 57) {
                        e.consume();
                    }
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    if (QZCQJpanel.cunqianMoney.getText() != null && QZCQJpanel.cunqianMoney.getText().length() > 0) {
                        if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal(QZCQJpanel.cunqianMoney.getText())) == -1) {
                            QZCQJpanel.cunqianMoney.setFont(new Font("宋体", 0, 15));
                            QZCQJpanel.cunqianMoney.setText(RoleData.getRoleData().getLoginResult().getGold().toString());
                        }
                        else {
                            QZCQJpanel.cunqianMoney.setForeground(Color.white);
                            if (QZCQJpanel.cunqianMoney.getText().length() > 6) {
                                QZCQJpanel.cunqianMoney.setForeground(Color.YELLOW);
                            }
                            if (QZCQJpanel.cunqianMoney.getText().length() > 7) {
                                QZCQJpanel.cunqianMoney.setForeground(UIUtils.COLOR_NAME2);
                            }
                            if (QZCQJpanel.cunqianMoney.getText().length() > 8) {
                                QZCQJpanel.cunqianMoney.setForeground(Color.GREEN);
                            }
                            if (QZCQJpanel.cunqianMoney.getText().length() > 9) {
                                QZCQJpanel.cunqianMoney.setForeground(Color.RED);
                            }
                        }
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            this.add(QZCQJpanel.cunqianMoney);
            (this.sureGive = new RoleCaoZuoBtn("inkImg/hongmu/6026.png", 1, "确认", 44, UIUtils.COLOR_BTNPUTONG)).setBounds(62, 300, 60, 25);
            this.add(this.sureGive);
            (this.deleteGive = new RoleCaoZuoBtn("inkImg/hongmu/6026.png", 1, "取消", 45, UIUtils.COLOR_BTNPUTONG)).setBounds(172, 300, 60, 25);
            this.add(this.deleteGive);
        }
    }
    
    public static void CUNMoney() {
        if (QZCQJpanel.cunqianMoney.getText() == null || QZCQJpanel.cunqianMoney.getText().isEmpty()) {
            ZhuFrame.getZhuJpanel().addPrompt2("没输入存款金额你要存什么？");
            return;
        }
        BigDecimal money = new BigDecimal(QZCQJpanel.cunqianMoney.getText());
        String senmes = Agreement.getAgreement().QzCUNQUAgreement("CUN=" + money);
        SendMessageUntil.toServer(senmes);
        QZCQJpanel.cunqianMoney.setText("");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.gold = RoleData.getRoleData().getLoginResult().getGold();
        this.Moneyshopgold = RoleData.getRoleData().getLoginResult().getMoneyshop();
        if (MyIsif.getStyle().equals("水墨")) {//水墨
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/QZCUN.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 268, 309, this);
            g.setColor(Color.white);
            g.setFont(UIUtils.TEXT_FONT1);
            g.drawString(this.gold.toString(), 104, 84);
            g.drawString(this.Moneyshopgold.toString(), 104, 118);
            g.setColor(Color.red);
            g.drawString("（存款扣除1%手续费）", 110, 183);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/hongmu/cunhm.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 294, 344, this);
            g.setColor(Color.white);
            g.setFont(UIUtils.TEXT_FONT1);
            g.drawString(this.gold.toString(), 104, 84);
            g.drawString(this.Moneyshopgold.toString(), 104, 118);//存款界面
            g.setColor(Color.red);
            g.drawString("（存款扣除1%手续费）", 100, 215);
        }
    }
    
    public RoleCaoZuoBtn getSureGive() {
        return this.sureGive;
    }
    
    public void setSureGive(RoleCaoZuoBtn sureGive) {
        this.sureGive = sureGive;
    }
    
    public RoleCaoZuoBtn getDeleteGive() {
        return this.deleteGive;
    }
    
    public void setDeleteGive(RoleCaoZuoBtn deleteGive) {
        this.deleteGive = deleteGive;
    }
    
    public static JTextField getCunqianMoney() {
        return QZCQJpanel.cunqianMoney;
    }
    
    public static void setCunqianMoney(JTextField cunqianMoney) {
        QZCQJpanel.cunqianMoney = cunqianMoney;
    }
    
    public BigDecimal getGold() {
        return this.gold;
    }
    
    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }
    
    public BigDecimal getMoneyshopgold() {
        return this.Moneyshopgold;
    }
    
    public void setMoneyshopgold(BigDecimal moneyshopgold) {
        this.Moneyshopgold = moneyshopgold;
    }
}
