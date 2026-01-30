package org.come.login;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.GsonUtil;
import org.come.bean.UserTable;
import org.come.test.Main;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.MouseListener;
import org.come.view.View;

public class RegisterView extends View implements MouseListener
{
    private SpriteBtn btnBackground;
    private SpriteBtn btnRegister;
    private JTextField textAcc;
    private JTextField textScode;
    private JTextField texttuiji;
    private JPasswordField textPwd;
    private JPasswordField textPwdA;
    private JLabel labMsgTip;
    
    public RegisterView(LoginJpanel loginJpanel) {
        super(2);
        this.setPreferredSize(new Dimension(424, 406));
        this.setLayout(null);
        (this.btnBackground = new SpriteBtn("resource/xinUI/注册界面", -8, -8, false)).setBounds(0, 0, 424, 406);
        this.add(this.btnBackground);
        (this.btnRegister = new SpriteBtn("resource/xinUI/注册按钮新", 96, 276, false)).setBounds(96, 276, 190, 50);
        this.btnRegister.addMouseListener(new LoginMouslisten(6, this.btnRegister, loginJpanel));
        this.add(this.btnRegister);
        (this.labMsgTip = new JLabel()).setBounds(65, 40, 300, 30);
        this.labMsgTip.setFont(new Font("楷体", 1, 16));
        this.labMsgTip.setForeground(Color.red);
        this.add(this.labMsgTip);
        (this.textAcc = new JTextField()).setBounds(112, 65, 245, 23);
        this.textAcc.setOpaque(false);
        this.textAcc.setBorder(BorderFactory.createEmptyBorder());
        this.textAcc.setForeground(Color.WHITE);
        this.textAcc.setFont(new Font("宋体", 0, 14));
        this.textAcc.setText("请输入您的账号");
        this.textAcc.setCaretColor(Color.WHITE);
        this.textAcc.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                String str = RegisterView.this.textAcc.getText();
                if (str.matches("[0-9a-zA-Z]*")) {
                    RegisterView.this.labMsgTip.setText("");
                }
                else {
                    RegisterView.this.labMsgTip.setText("账号不能包含中文或特殊字符！");
                }
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                String str = RegisterView.this.textAcc.getText();
                if (str.matches("[0-9a-zA-Z]*")) {
                    RegisterView.this.labMsgTip.setText("");
                }
                else {
                    RegisterView.this.labMsgTip.setText("账号不能包含中文或特殊字符！");
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
                if (RegisterView.this.textAcc.getText() == null) {
                    RegisterView.this.labMsgTip.setText("请输入您要注册的账号！");
                }
                else {
                    RegisterView.this.labMsgTip.setText("");
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        this.textAcc.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!RegisterView.this.textAcc.getText().matches("[0-9a-zA-Z]*")) {
                    RegisterView.this.textAcc.setText("");
                }
            }
            
            @Override
            public void focusGained(FocusEvent e) {
                RegisterView.this.textAcc.setText("");
            }
        });
        this.add(this.textAcc);
        (this.textPwd = new JPasswordField()).setBounds(112, 102, 245, 23);
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
                if (RegisterView.this.textPwd.getText() == null) {
                    RegisterView.this.labMsgTip.setText("请输入您要注册的密码！");
                }
                else {
                    RegisterView.this.labMsgTip.setText("");
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        this.add(this.textPwd);
        (this.textPwdA = new JPasswordField()).setBounds(112, 139, 245, 23);
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
                if (RegisterView.this.textPwdA.getText() != null && RegisterView.this.textPwd.getText() != null) {
                    if (RegisterView.this.textPwdA.getText().equals(RegisterView.this.textPwd.getText())) {
                        RegisterView.this.labMsgTip.setText("");
                    }
                    else {
                        RegisterView.this.labMsgTip.setText("两次输入的密码不相等！");
                    }
                }
                else if (RegisterView.this.textPwdA.getText() == null && RegisterView.this.textPwd.getText() != null) {
                    RegisterView.this.labMsgTip.setText("请再次输入密码！");
                }
                else if (RegisterView.this.textPwdA.getText() != null && RegisterView.this.textPwd.getText() == null) {
                    RegisterView.this.labMsgTip.setText("请输入您要注册的密码！");
                }
                else {
                    RegisterView.this.labMsgTip.setText("请输入您要注册的密码！");
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        this.add(this.textPwdA);
        (this.textScode = new JTextField()).setBounds(112, 178, 245, 23);
        this.textScode.setBounds(112, 178, 245, 23);
        this.textScode.setOpaque(false);
        this.textScode.setBorder(BorderFactory.createEmptyBorder());
        this.textScode.setForeground(Color.WHITE);
        this.textScode.setFont(new Font("宋体", 0, 14));
        this.textScode.setCaretColor(Color.WHITE);
        this.textScode.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                String str = RegisterView.this.textScode.getText();
                if (str.matches("[0-9a-zA-Z]*")) {
                    RegisterView.this.labMsgTip.setText("");
                }
                else {
                    RegisterView.this.labMsgTip.setText("账号不能包含中文或特殊字符！");
                }
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                String str = RegisterView.this.textScode.getText();
                if (str.matches("[0-9a-zA-Z]*")) {
                    RegisterView.this.labMsgTip.setText("");
                }
                else {
                    RegisterView.this.labMsgTip.setText("安全码不能包含中文或特殊字符！");
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
                if (RegisterView.this.textScode.getText() == null) {
                    RegisterView.this.labMsgTip.setText("请输入安全码！");
                }
                else {
                    RegisterView.this.labMsgTip.setText("");
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        this.add(this.textScode);
        (this.texttuiji = new JTextField()).setBounds(112, 215, 245, 23);
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
                if (RegisterView.this.textScode.getText() == null) {
                    RegisterView.this.labMsgTip.setText("请输入推荐码！");
                }
                else {
                    RegisterView.this.labMsgTip.setText("");
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
        System.out.println(tuiji);
        if (tuiji != null && !"".equals(tuiji)) {
            RegisterView registerView = Main.frame.getLoginJpanel().getLoginView().getRegisterView();
            this.labMsgTip.setText("");
            UserTable userTable = new UserTable();
            userTable.setUsername(acc);
            userTable.setUserpwd(pwd);
            userTable.setSafety(Aqm);
            userTable.setTuiji(tuiji);
            String json = GsonUtil.getGsonUtil().getgson().toJson(userTable);
            String serverMes = Agreement.getAgreement().registerAgreement(json);
            SendMessageUntil.toServer(serverMes);
            return;
        }
        this.labMsgTip.setText("推荐码不能为空");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.btnBackground.draw(g);
        this.btnRegister.draw(g);
    }
    
    public JLabel getLabMsgTip() {
        return this.labMsgTip;
    }
    
    public void setLabMsgTip(JLabel labMsgTip) {
        this.labMsgTip = labMsgTip;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        System.out.printf("ooo", new Object[0]);
        if (e.getButton() == 3) {
            this.setVisible(false);
        }
    }
}
