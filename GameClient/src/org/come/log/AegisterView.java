package org.come.log;

import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.bean.UserTable;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import org.come.login.LoginMouslisten;
import java.awt.Point;
import org.come.test.Main;
import java.awt.MouseInfo;
import com.tool.tcp.Sprite;
import org.come.until.MessagrFlagUntil;
import java.awt.Graphics;
import java.awt.Dimension;
import org.come.login.LoginJpanel;
import org.come.bean.PathPoint;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.come.login.SpriteBtn;
import org.come.view.View;

public class AegisterView extends View
{
    public SpriteBtn btnBackground;
    public SpriteBtn btnRegister;
    public JTextField textAcc;
    public JTextField textScode;
    public JTextField texttuiji;
    public long time;
    public JPasswordField textPwd;
    public JPasswordField textPwdA;
    public JLabel labMsgTip;
    public JLabel mouse;
    private PathPoint pathPoint;
    
    public AegisterView(LoginJpanel loginJpanel) {
        super(-1);
        this.time = 100L;
        this.pathPoint = new PathPoint(0, 0);
        this.setPreferredSize(new Dimension(424, 406));
        this.setLayout(null);
        this.rdoe(loginJpanel);
        try {
            (this.mouse = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    AegisterView this$0 = AegisterView.this;
                    AegisterView.this.time += 20L;
                    PathPoint point = AegisterView.this.mousepath();
                    Sprite mouse = MessagrFlagUntil.getMouse();
                    if (mouse != null) {
                        mouse.updateToTime(AegisterView.this.time, 0);
                        mouse.draw(g, point.getX(), point.getY());
                    }
                }
            }).setBounds(0, 0, LanderJPanel.width, LanderJPanel.high);
            this.add(this.mouse);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public PathPoint mousepath() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        if (Main.index >= 0) {
            Point p2 = Main.frame.getLocation();
            this.pathPoint.setX((int)(p.getX() - p2.getX() - (double)this.getX() - 4.0));
            this.pathPoint.setY((int)(p.getY() - p2.getY() - (double)this.getY()));
        }
        else {
            Point p2 = Main.frame.getLocation();
            this.pathPoint.setX((int)(p.getX() - p2.getX() - (double)this.getX() - 4.0));
            this.pathPoint.setY((int)(p.getY() - p2.getY() - (double)this.getY() - 21.0));
        }
        return this.pathPoint;
    }
    
    public void rdoe(LoginJpanel loginJpanel) {
        (this.btnBackground = new SpriteBtn("resource/intro/background", 0, 0, false)).setBounds(0, 0, 300, 262);
        this.add(this.btnBackground);
        (this.btnRegister = new SpriteBtn("resource/intro/按钮_立即注册22", 110, 210, false)).setBounds(110, 210, 100, 26);
        this.btnRegister.addMouseListener(new LoginMouslisten(14, this.btnRegister, loginJpanel));
        this.add(this.btnRegister);
        (this.labMsgTip = new JLabel()).setBounds(65, 40, 300, 30);
        this.labMsgTip.setFont(new Font("楷体", 1, 16));
        this.labMsgTip.setForeground(Color.red);
        this.add(this.labMsgTip);
        (this.textAcc = new JTextField()).setBounds(93, 45, 245, 23);
        this.textAcc.setOpaque(false);
        this.textAcc.setBorder(BorderFactory.createEmptyBorder());
        this.textAcc.setForeground(Color.WHITE);
        this.textAcc.setFont(new Font("宋体", 0, 14));
        this.textAcc.setText("请输入您的账号");
        this.textAcc.setCaretColor(Color.WHITE);
        this.textAcc.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                String str = AegisterView.this.textAcc.getText();
                if (str.matches("[0-9a-zA-Z]*")) {
                    AegisterView.this.labMsgTip.setText("");
                }
                else {
                    AegisterView.this.labMsgTip.setText("账号不能包含中文或特殊字符！");
                }
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                String str = AegisterView.this.textAcc.getText();
                if (str.matches("[0-9a-zA-Z]*")) {
                    AegisterView.this.labMsgTip.setText("");
                }
                else {
                    AegisterView.this.labMsgTip.setText("账号不能包含中文或特殊字符！");
                }
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.textAcc.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                if (AegisterView.this.textAcc.getText() == null) {
                    AegisterView.this.labMsgTip.setText("请输入您要注册的账号！");
                }
                else {
                    AegisterView.this.labMsgTip.setText("");
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        this.textAcc.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!AegisterView.this.textAcc.getText().matches("[0-9a-zA-Z]*")) {
                    AegisterView.this.textAcc.setText("");
                }
            }
            
            @Override
            public void focusGained(FocusEvent e) {
                AegisterView.this.textAcc.setText("");
            }
        });
        this.add(this.textAcc);
        (this.textPwd = new JPasswordField()).setBounds(93, 76, 245, 23);
        this.textPwd.setOpaque(false);
        this.textPwd.setBorder(BorderFactory.createEmptyBorder());
        this.textPwd.setForeground(Color.WHITE);
        this.textPwd.setFont(new Font("宋体", 0, 14));
        this.textPwd.setCaretColor(Color.WHITE);
        this.textPwd.setEchoChar('*');
        this.textPwd.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                if (AegisterView.this.textPwd.getText() == null) {
                    AegisterView.this.labMsgTip.setText("请输入您要注册的密码！");
                }
                else {
                    AegisterView.this.labMsgTip.setText("");
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        this.add(this.textPwd);
        (this.textPwdA = new JPasswordField()).setBounds(93, 108, 245, 23);
        this.textPwdA.setOpaque(false);
        this.textPwdA.setBorder(BorderFactory.createEmptyBorder());
        this.textPwdA.setForeground(Color.WHITE);
        this.textPwdA.setFont(new Font("宋体", 0, 14));
        this.textPwdA.setCaretColor(Color.WHITE);
        this.textPwdA.setEchoChar('*');
        this.textPwdA.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                if (AegisterView.this.textPwdA.getText() != null && AegisterView.this.textPwd.getText() != null) {
                    if (AegisterView.this.textPwdA.getText().equals(AegisterView.this.textPwd.getText())) {
                        AegisterView.this.labMsgTip.setText("");
                    }
                    else {
                        AegisterView.this.labMsgTip.setText("两次输入的密码不相等！");
                    }
                }
                else if (AegisterView.this.textPwdA.getText() == null && AegisterView.this.textPwd.getText() != null) {
                    AegisterView.this.labMsgTip.setText("请再次输入密码！");
                }
                else if (AegisterView.this.textPwdA.getText() != null && AegisterView.this.textPwd.getText() == null) {
                    AegisterView.this.labMsgTip.setText("请输入您要注册的密码！");
                }
                else {
                    AegisterView.this.labMsgTip.setText("请输入您要注册的密码！");
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        this.add(this.textPwdA);
        (this.textScode = new JTextField()).setBounds(93, 138, 245, 23);
        this.textScode.setOpaque(false);
        this.textScode.setBorder(BorderFactory.createEmptyBorder());
        this.textScode.setForeground(Color.WHITE);
        this.textScode.setFont(new Font("宋体", 0, 14));
        this.textScode.setCaretColor(Color.WHITE);
        this.textScode.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                String str = AegisterView.this.textScode.getText();
                if (str.matches("[0-9a-zA-Z]*")) {
                    AegisterView.this.labMsgTip.setText("");
                }
                else {
                    AegisterView.this.labMsgTip.setText("账号不能包含中文或特殊字符！");
                }
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                String str = AegisterView.this.textScode.getText();
                if (str.matches("[0-9a-zA-Z]*")) {
                    AegisterView.this.labMsgTip.setText("");
                }
                else {
                    AegisterView.this.labMsgTip.setText("安全码不能包含中文或特殊字符！");
                }
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.textScode.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                if (AegisterView.this.textScode.getText() == null) {
                    AegisterView.this.labMsgTip.setText("请输入安全码！");
                }
                else {
                    AegisterView.this.labMsgTip.setText("");
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        this.add(this.textScode);
        (this.texttuiji = new JTextField("")).setBounds(93, 172, 245, 23);
        this.texttuiji.setOpaque(false);
        this.texttuiji.setBorder(BorderFactory.createEmptyBorder());
        this.texttuiji.setForeground(Color.WHITE);
        this.texttuiji.setFont(new Font("宋体", 0, 14));
        this.texttuiji.setCaretColor(Color.WHITE);
        this.texttuiji.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) {
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.texttuiji.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                if (AegisterView.this.textScode.getText() == null) {
                    AegisterView.this.labMsgTip.setText("请输入推荐码！");
                }
                else {
                    AegisterView.this.labMsgTip.setText("");
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        this.add(this.texttuiji);
    }
    
    public void zc() {
        String acc = this.textAcc.getText();
        if (acc.equals("") || acc.equals("请输入您的账号") || !acc.matches("[0-9a-zA-Z]*")) {
            this.labMsgTip.setText("账号不能包含中文或特殊字符！");
            this.textAcc.setText("");
            return;
        }
        String pwd = this.textPwd.getText();
        String pwdA = this.textPwdA.getText();
        if (pwd.equals("") || !pwd.equals(pwdA)) {
            this.labMsgTip.setText("两次输入的密码不相等！");
            this.textPwd.setText("");
            return;
        }
        if (pwd.length() <= 1 || pwd.length() > 12) {
            this.labMsgTip.setText("密码长度需要2-12位字符！");
            return;
        }
        String Aqm = this.textScode.getText();
        if (Aqm.equals("")) {
            this.labMsgTip.setText("请输入安全码!!!");
            return;
        }
        if (Aqm.length() <= 1 || Aqm.length() > 18) {
            this.labMsgTip.setText("安全码长度需要1-18位字符！");
            return;
        }
        String tuiji = this.texttuiji.getText();
        if (tuiji == null || "".equals(tuiji)) {
            this.labMsgTip.setText("推荐码不能为空");
            return;
        }
        this.labMsgTip.setText("");
        UserTable userTable = new UserTable();
        userTable.setUsername(acc);
        userTable.setUserpwd(pwd);
        userTable.setSafety(Aqm);
        userTable.setTuiji(tuiji);
        String json = GsonUtil.getGsonUtil().getgson().toJson(userTable);
        String serverMes = Agreement.getAgreement().registerAgreement(json);
        SendMessageUntil.toServer(serverMes);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.btnBackground.draw(g);
        this.btnRegister.draw(g);
        this.labMsgTip.setBounds(35, 20, 300, 30);
        this.time += 12L;
    }
    
    public JLabel getLabMsgTip() {
        return this.labMsgTip;
    }
    
    public void setLabMsgTip(JLabel labMsgTip) {
        this.labMsgTip = labMsgTip;
    }
    
    public SpriteBtn getBtnBackground() {
        return this.btnBackground;
    }
    
    public void setBtnBackground(SpriteBtn btnBackground) {
        this.btnBackground = btnBackground;
    }
    
    public SpriteBtn getBtnRegister() {
        return this.btnRegister;
    }
    
    public void setBtnRegister(SpriteBtn btnRegister) {
        this.btnRegister = btnRegister;
    }
    
    public JTextField getTextAcc() {
        return this.textAcc;
    }
    
    public void setTextAcc(JTextField textAcc) {
        this.textAcc = textAcc;
    }
    
    public JTextField getTextScode() {
        return this.textScode;
    }
    
    public void setTextScode(JTextField textScode) {
        this.textScode = textScode;
    }
    
    public JTextField getTexttuiji() {
        return this.texttuiji;
    }
    
    public void setTexttuiji(JTextField texttuiji) {
        this.texttuiji = texttuiji;
    }
    
    public JPasswordField getTextPwd() {
        return this.textPwd;
    }
    
    public void setTextPwd(JPasswordField textPwd) {
        this.textPwd = textPwd;
    }
    
    public JPasswordField getTextPwdA() {
        return this.textPwdA;
    }
    
    public void setTextPwdA(JPasswordField textPwdA) {
        this.textPwdA = textPwdA;
    }
}
