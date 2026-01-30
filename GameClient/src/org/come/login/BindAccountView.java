package org.come.login;

import java.awt.Graphics;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import org.come.view.View;

public class BindAccountView extends View
{
    private SpriteBtn btnYes;
    private SpriteBtn Btnno;
    private SpriteBtn backImg;
    private JLabel labMsgTip;
    private JTextField textAcc;
    private JPasswordField textPwd;
    private JPasswordField textScode;
    private LoginJpanel loginJpanel;
    
    public BindAccountView(LoginJpanel loginJpanel) {
        this.loginJpanel = loginJpanel;
        this.setPreferredSize(new Dimension(530, 168));
        this.setLayout(null);
        (this.backImg = new SpriteBtn("resource/NewUi/绑定账号", 0, 0, false)).setBounds(0, 0, 424, 278);
        this.add(this.backImg);
        (this.labMsgTip = new JLabel()).setBounds(60, 45, 300, 30);
        this.labMsgTip.setFont(new Font("楷体", 1, 16));
        this.labMsgTip.setForeground(Color.red);
        this.add(this.labMsgTip);
        (this.textAcc = new JTextField()).setBounds(138, 88, 245, 23);
        this.textAcc.setOpaque(false);
        this.textAcc.setBorder(BorderFactory.createEmptyBorder());
        this.textAcc.setForeground(Color.WHITE);
        this.textAcc.setFont(new Font("宋体", 0, 14));
        this.textAcc.setText("请输入您要绑定的账号");
        this.textAcc.setCaretColor(Color.WHITE);
        this.textAcc.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                String str = BindAccountView.this.textAcc.getText();
                if (!str.matches("[一-龥]+")) {
                    BindAccountView.this.labMsgTip.setText("");
                }
                else {
                    BindAccountView.this.labMsgTip.setText("账号不能包含中文！");
                }
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                String str = BindAccountView.this.textAcc.getText();
                if (!str.matches("[一-龥]+")) {
                    BindAccountView.this.labMsgTip.setText("");
                }
                else {
                    BindAccountView.this.labMsgTip.setText("账号不能包含中文！");
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
                if (BindAccountView.this.textAcc.getText() == null) {
                    BindAccountView.this.labMsgTip.setText("请输入您要绑定的账号！");
                }
                else if ("".equals(BindAccountView.this.textAcc.getText())) {
                    BindAccountView.this.labMsgTip.setText("请输入您要绑定的账号！");
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        this.textAcc.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if (BindAccountView.this.textAcc.getText().matches("[一-龥]+")) {
                    BindAccountView.this.textAcc.setText("");
                }
            }
            
            @Override
            public void focusGained(FocusEvent e) {
                BindAccountView.this.textAcc.setText("");
            }
        });
        this.add(this.textAcc);
        (this.textPwd = new JPasswordField()).setBounds(138, 127, 245, 23);
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
                if (BindAccountView.this.textPwd.getText() == null) {
                    BindAccountView.this.labMsgTip.setText("请输入您绑定账号的密码！");
                }
                else {
                    BindAccountView.this.labMsgTip.setText("");
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        this.add(this.textPwd);
        (this.textScode = new JPasswordField()).setBounds(138, 166, 245, 23);
        this.textScode.setOpaque(false);
        this.textScode.setBorder(BorderFactory.createEmptyBorder());
        this.textScode.setForeground(Color.WHITE);
        this.textScode.setFont(new Font("宋体", 0, 14));
        this.textScode.setCaretColor(Color.WHITE);
        this.textScode.setEchoChar('*');
        this.textScode.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                String str = BindAccountView.this.textScode.getText();
                if (str.matches("[0-9a-zA-Z]*")) {
                    BindAccountView.this.labMsgTip.setText("");
                }
                else {
                    BindAccountView.this.labMsgTip.setText("安全码不能包含中文或特殊字符！");
                }
            }
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                String str = BindAccountView.this.textScode.getText();
                if (str.matches("[0-9a-zA-Z]*")) {
                    BindAccountView.this.labMsgTip.setText("");
                }
                else {
                    BindAccountView.this.labMsgTip.setText("安全码不能包含中文或特殊字符！");
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
                if (BindAccountView.this.textScode.getText() == null) {
                    BindAccountView.this.labMsgTip.setText("请输入您绑定账号的安全码！");
                }
                else {
                    BindAccountView.this.labMsgTip.setText("");
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        this.add(this.textScode);
        (this.btnYes = new SpriteBtn("resource/NewUi/按钮_确定3", 20, 205, false)).setBounds(20, 205, 192, 52);
        this.btnYes.addMouseListener(new LoginMouslisten(10, this.btnYes, loginJpanel));
        this.add(this.btnYes);
        (this.Btnno = new SpriteBtn("resource/NewUi/按钮_取消4", 220, 213, false)).setBounds(220, 213, 159, 44);
        this.Btnno.addMouseListener(new LoginMouslisten(11, this.Btnno, loginJpanel));
        this.add(this.Btnno);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.backImg.draw(g);
        this.btnYes.draw(g);
        this.Btnno.draw(g);
    }
    
    public JTextField getTextAcc() {
        return this.textAcc;
    }
    
    public void setTextAcc(JTextField textAcc) {
        this.textAcc = textAcc;
    }
    
    public JPasswordField getTextPwd() {
        return this.textPwd;
    }
    
    public void setTextPwd(JPasswordField textPwd) {
        this.textPwd = textPwd;
    }
    
    public JPasswordField getTextScode() {
        return this.textScode;
    }
    
    public void setTextScode(JPasswordField textScode) {
        this.textScode = textScode;
    }
    
    public SpriteBtn getBtnYes() {
        return this.btnYes;
    }
    
    public void setBtnYes(SpriteBtn btnYes) {
        this.btnYes = btnYes;
    }
    
    public SpriteBtn getBtnno() {
        return this.Btnno;
    }
    
    public void setBtnno(SpriteBtn btnno) {
        this.Btnno = btnno;
    }
    
    public SpriteBtn getBackImg() {
        return this.backImg;
    }
    
    public void setBackImg(SpriteBtn backImg) {
        this.backImg = backImg;
    }
    
    public JLabel getLabMsgTip() {
        return this.labMsgTip;
    }
    
    public void setLabMsgTip(JLabel labMsgTip) {
        this.labMsgTip = labMsgTip;
    }
    
    public LoginJpanel getLoginJpanel() {
        return this.loginJpanel;
    }
    
    public void setLoginJpanel(LoginJpanel loginJpanel) {
        this.loginJpanel = loginJpanel;
    }
}
