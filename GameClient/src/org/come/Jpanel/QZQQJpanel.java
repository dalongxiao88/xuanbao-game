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

public class QZQQJpanel extends JPanel
{
    private static JTextField quqianMoney;
    private RoleCaoZuoBtn sureGive;
    private RoleCaoZuoBtn deleteGive;
    private BigDecimal gold;
    private BigDecimal Moneyshopgold;
    private ImageIcon icon;
    
    public QZQQJpanel() throws Exception {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(268, 309));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 911);
            offBtn.setBounds(233, 10, 25, 25);
            this.add(offBtn);
            (QZQQJpanel.quqianMoney = new JTextField()).setBounds(103, 197, 128, 20);
            QZQQJpanel.quqianMoney.setOpaque(false);
            QZQQJpanel.quqianMoney.setBorder(BorderFactory.createEmptyBorder());
            QZQQJpanel.quqianMoney.setForeground(Color.white);
            QZQQJpanel.quqianMoney.setCaretColor(Color.white);
            QZQQJpanel.quqianMoney.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int keyChar = e.getKeyChar();
                    if (keyChar < 48 || keyChar > 57) {
                        e.consume();
                    }
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    if (QZQQJpanel.quqianMoney.getText() != null && QZQQJpanel.quqianMoney.getText().length() > 0) {
                        if (RoleData.getRoleData().getLoginResult().getMoneyshop().compareTo(new BigDecimal(QZQQJpanel.quqianMoney.getText())) == -1) {
                            QZQQJpanel.quqianMoney.setFont(new Font("宋体", 0, 15));
                            QZQQJpanel.quqianMoney.setText(RoleData.getRoleData().getLoginResult().getMoneyshop().toString());
                        }
                        else {
                            QZQQJpanel.quqianMoney.setForeground(Color.white);
                            if (QZQQJpanel.quqianMoney.getText().length() > 6) {
                                QZQQJpanel.quqianMoney.setForeground(Color.YELLOW);
                            }
                            if (QZQQJpanel.quqianMoney.getText().length() > 7) {
                                QZQQJpanel.quqianMoney.setForeground(UIUtils.COLOR_NAME2);
                            }
                            if (QZQQJpanel.quqianMoney.getText().length() > 8) {
                                QZQQJpanel.quqianMoney.setForeground(Color.GREEN);
                            }
                            if (QZQQJpanel.quqianMoney.getText().length() > 9) {
                                QZQQJpanel.quqianMoney.setForeground(Color.RED);
                            }
                        }
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            this.add(QZQQJpanel.quqianMoney);
            (this.sureGive = new RoleCaoZuoBtn("inkImg/button1/B20.png", 1, "确认", 46, UIUtils.COLOR_BLACK)).setBounds(57, 250, 59, 24);
            this.add(this.sureGive);
            (this.deleteGive = new RoleCaoZuoBtn("inkImg/button1/B20.png", 1, "取消", 47, UIUtils.COLOR_BLACK)).setBounds(157, 250, 59, 24);
            this.add(this.deleteGive);
        }
        else {
            this.setPreferredSize(new Dimension(268, 309));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 912);
            offBtn.setBounds(269, 1, 23, 23);
            this.add(offBtn);
            (QZQQJpanel.quqianMoney = new JTextField()).setBounds(103, 223, 128, 15);
            QZQQJpanel.quqianMoney.setOpaque(false);
            QZQQJpanel.quqianMoney.setBorder(BorderFactory.createEmptyBorder());
            QZQQJpanel.quqianMoney.setForeground(Color.white);
            QZQQJpanel.quqianMoney.setCaretColor(Color.white);
            QZQQJpanel.quqianMoney.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int keyChar = e.getKeyChar();
                    if (keyChar < 48 || keyChar > 57) {
                        e.consume();
                    }
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    if (QZQQJpanel.quqianMoney.getText() != null && QZQQJpanel.quqianMoney.getText().length() > 0) {
                        if (RoleData.getRoleData().getLoginResult().getMoneyshop().compareTo(new BigDecimal(QZQQJpanel.quqianMoney.getText())) == -1) {
                            QZQQJpanel.quqianMoney.setFont(new Font("宋体", 0, 15));
                            QZQQJpanel.quqianMoney.setText(RoleData.getRoleData().getLoginResult().getMoneyshop().toString());
                        }
                        else {
                            QZQQJpanel.quqianMoney.setForeground(Color.white);
                            if (QZQQJpanel.quqianMoney.getText().length() > 6) {
                                QZQQJpanel.quqianMoney.setForeground(Color.YELLOW);
                            }
                            if (QZQQJpanel.quqianMoney.getText().length() > 7) {
                                QZQQJpanel.quqianMoney.setForeground(UIUtils.COLOR_NAME2);
                            }
                            if (QZQQJpanel.quqianMoney.getText().length() > 8) {
                                QZQQJpanel.quqianMoney.setForeground(Color.GREEN);
                            }
                            if (QZQQJpanel.quqianMoney.getText().length() > 9) {
                                QZQQJpanel.quqianMoney.setForeground(Color.RED);
                            }
                        }
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            this.add(QZQQJpanel.quqianMoney);
            (this.sureGive = new RoleCaoZuoBtn("inkImg/hongmu/6026.png", 1, "确认", 46, UIUtils.COLOR_BTNPUTONG)).setBounds(57, 270, 60, 25);
            this.add(this.sureGive);
            (this.deleteGive = new RoleCaoZuoBtn("inkImg/hongmu/6026.png", 1, "取消", 47, UIUtils.COLOR_BTNPUTONG)).setBounds(167, 270, 60, 25);
            this.add(this.deleteGive);
        }
    }
    
    public static void QuMoney() {
        if (QZQQJpanel.quqianMoney.getText() == null || QZQQJpanel.quqianMoney.getText().isEmpty()) {
            ZhuFrame.getZhuJpanel().addPrompt2("没输入取款金额你要取什么？");
            return;
        }
        BigDecimal money = new BigDecimal(QZQQJpanel.quqianMoney.getText());
        String senmes = Agreement.getAgreement().QzCUNQUAgreement("QU=" + money);
        SendMessageUntil.toServer(senmes);
        QZQQJpanel.quqianMoney.setText("");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.gold = RoleData.getRoleData().getLoginResult().getGold();
        this.Moneyshopgold = RoleData.getRoleData().getLoginResult().getMoneyshop();
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/QZQU.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 268, 309, this);
            g.setColor(Color.white);
            g.setFont(UIUtils.TEXT_FONT1);
            g.drawString(this.gold.toString(), 104, 84);
            g.drawString(this.Moneyshopgold.toString(), 104, 118);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/hongmu/quhm.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 294, 344, this);
            g.setColor(Color.white);
            g.setFont(UIUtils.TEXT_FONT1);
            g.drawString(this.gold.toString(), 104, 84);
            g.drawString(this.Moneyshopgold.toString(), 104, 118);
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
    
    public static JTextField getQuqianMoney() {
        return QZQQJpanel.quqianMoney;
    }
    
    public static void setQuqianMoney(JTextField quqianMoney) {
        QZQQJpanel.quqianMoney = quqianMoney;
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
