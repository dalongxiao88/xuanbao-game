package org.come.Jpanel;

import org.come.until.Util;
import java.awt.Graphics;
import org.come.bean.LoginResult;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Color;
import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.btn.RoleCaoZuoBtn;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class DonationsJpanel extends JPanel
{
    private static JTextField giveSumMoney;
    private RoleCaoZuoBtn sureGive;
    private RoleCaoZuoBtn deleteGive;
    private ImageIcon icon;
    
    public DonationsJpanel() throws Exception {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(296, 257));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            (DonationsJpanel.giveSumMoney = new JTextField()).setBounds(128, 78, 128, 20);
            DonationsJpanel.giveSumMoney.setOpaque(false);
            DonationsJpanel.giveSumMoney.setBorder(BorderFactory.createEmptyBorder());
            DonationsJpanel.giveSumMoney.setForeground(Color.red);
            DonationsJpanel.giveSumMoney.setCaretColor(Color.white);
            DonationsJpanel.giveSumMoney.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int keyChar = e.getKeyChar();
                    if (keyChar < 48 || keyChar > 57) {
                        e.consume();
                    }
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            this.add(DonationsJpanel.giveSumMoney);
            (this.sureGive = new RoleCaoZuoBtn("inkImg/button/B59.png", 1, "确认", 40, UIUtils.COLOR_BLACK)).setBounds(57, 110, 68, 26);
            this.add(this.sureGive);
            (this.deleteGive = new RoleCaoZuoBtn("inkImg/button/B59.png", 1, "取消", 41, UIUtils.COLOR_BLACK)).setBounds(157, 110, 68, 26);
            this.add(this.deleteGive);
        }
        else {
            this.setPreferredSize(new Dimension(267, 221));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            (DonationsJpanel.giveSumMoney = new JTextField()).setBounds(100, 121, 128, 20);
            DonationsJpanel.giveSumMoney.setOpaque(false);
            DonationsJpanel.giveSumMoney.setBorder(BorderFactory.createEmptyBorder());
            DonationsJpanel.giveSumMoney.setForeground(Color.white);
            DonationsJpanel.giveSumMoney.setCaretColor(Color.white);
            DonationsJpanel.giveSumMoney.setFont(new Font("宋体", 0, 15));
            DonationsJpanel.giveSumMoney.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int keyChar = e.getKeyChar();
                    if (keyChar < 48 || keyChar > 57) {
                        e.consume();
                        return;
                    }
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    if (DonationsJpanel.giveSumMoney.getText() != null && DonationsJpanel.giveSumMoney.getText().length() > 0) {
                        if (RoleData.getRoleData().getLoginResult().getGold().compareTo(new BigDecimal(DonationsJpanel.giveSumMoney.getText())) == -1) {
                            DonationsJpanel.giveSumMoney.setFont(new Font("宋体", 0, 15));
                            DonationsJpanel.giveSumMoney.setText(RoleData.getRoleData().getLoginResult().getGold().toString());
                        }
                        else {
                            DonationsJpanel.giveSumMoney.setForeground(Color.white);
                            if (DonationsJpanel.giveSumMoney.getText().length() > 6) {
                                DonationsJpanel.giveSumMoney.setForeground(Color.YELLOW);
                            }
                            if (DonationsJpanel.giveSumMoney.getText().length() > 7) {
                                DonationsJpanel.giveSumMoney.setForeground(UIUtils.COLOR_NAME2);
                            }
                            if (DonationsJpanel.giveSumMoney.getText().length() > 8) {
                                DonationsJpanel.giveSumMoney.setForeground(Color.GREEN);
                            }
                            if (DonationsJpanel.giveSumMoney.getText().length() > 9) {
                                DonationsJpanel.giveSumMoney.setForeground(Color.RED);
                            }
                        }
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            this.add(DonationsJpanel.giveSumMoney);
            (this.sureGive = new RoleCaoZuoBtn("inkImg/hongmu/6026.png", 1, "确认", 40, UIUtils.COLOR_BTNPUTONG)).setBounds(62, 170, 68, 26);
            this.add(this.sureGive);
            (this.deleteGive = new RoleCaoZuoBtn("inkImg/hongmu/6026.png", 1, "取消", 41, UIUtils.COLOR_BTNPUTONG)).setBounds(162, 170, 68, 26);
            this.add(this.deleteGive);
        }
    }
    
    public static void giveGangMoney() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (DonationsJpanel.giveSumMoney.getText() == null || DonationsJpanel.giveSumMoney.getText().length() == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("为了帮派更好的发展别犹豫了先捐上一个亿");
            return;
        }
        BigDecimal money = new BigDecimal(DonationsJpanel.giveSumMoney.getText());
        if (money == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("想什么呢？你的兜里比脸上还干净");
            return;
        }
        int sy = loginResult.getGold().subtract(money).compareTo(new BigDecimal(0));
        if (sy == -1) {
            ZhuFrame.getZhuJpanel().addPrompt2("自己有多少钱心里没点数吗？");
            return;
        }
        String senmes = Agreement.givemoneyAgreement(money.toString());
        SendMessageUntil.toServer(senmes);
        loginResult.setGold(loginResult.getGold().subtract(money));
        ZhuFrame.getZhuJpanel().addPrompt("扣除了" + money + "金币");
        DonationsJpanel.giveSumMoney.setText("");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background/S142.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 296, 257, this);
            Util.drawMoney(g, 128, 48);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/86_png.xy2uiimg.donate.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 267, 221, this);
            Util.drawMoney(g, 100, 83);
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
    
    public static JTextField getGiveSumMoney() {
        return DonationsJpanel.giveSumMoney;
    }
    
    public static void setGiveSumMoney(JTextField giveSumMoney) {
        DonationsJpanel.giveSumMoney = giveSumMoney;
    }
}
