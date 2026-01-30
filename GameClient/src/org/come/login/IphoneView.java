package org.come.login;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import org.come.bean.UserRoleArrBean;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.come.view.View;

public class IphoneView extends View
{
    private SpriteBtn affirmBtn;
    private SpriteBtn backGroundImg;
    private JTextField verificationText;
    private JLabel labMsgTip;
    private UserRoleArrBean arrBean;
    
    public IphoneView(LoginJpanel loginJpanel, UserRoleArrBean arrBean) {
        this.setPreferredSize(new Dimension(530, 168));
        this.setLayout(null);
        this.arrBean = arrBean;
        (this.backGroundImg = new SpriteBtn("resource/NewUi/底板_登录界面验证_w530,h168px", 0, 0, false)).setBounds(0, 0, 530, 168);
        this.add(this.backGroundImg);
        (this.labMsgTip = new JLabel()).setBounds(370, 75, 300, 30);
        this.labMsgTip.setFont(new Font("楷体", 1, 16));
        this.labMsgTip.setForeground(Color.red);
        this.add(this.labMsgTip);
        (this.verificationText = new JTextField()).setBounds(265, 75, 102, 23);
        this.verificationText.setOpaque(false);
        this.verificationText.setBorder(BorderFactory.createEmptyBorder());
        this.verificationText.setForeground(Color.WHITE);
        this.verificationText.setFont(new Font("宋体", 0, 14));
        this.verificationText.setCaretColor(Color.WHITE);
        this.verificationText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                if (IphoneView.this.verificationText.getText() != null) {
                    if (IphoneView.this.verificationText.getText().equalsIgnoreCase("")) {
                        IphoneView.this.labMsgTip.setText("");
                    }
                }
                else {
                    IphoneView.this.labMsgTip.setText("请输入验证码！");
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        this.add(this.verificationText);
        (this.affirmBtn = new SpriteBtn("resource/NewUi/按钮_确定3", 170, 106, false)).setBounds(170, 106, 192, 52);
        this.affirmBtn.addMouseListener(new LoginMouslisten(7, this.affirmBtn, loginJpanel));
        this.add(this.affirmBtn);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.backGroundImg.draw(g);
        this.affirmBtn.draw(g);
    }
    
    public UserRoleArrBean getArrBean() {
        return this.arrBean;
    }
    
    public void setArrBean(UserRoleArrBean arrBean) {
        this.arrBean = arrBean;
    }
    
    public JTextField getVerificationText() {
        return this.verificationText;
    }
    
    public void setVerificationText(JTextField verificationText) {
        this.verificationText = verificationText;
    }
    
    public JLabel getLabMsgTip() {
        return this.labMsgTip;
    }
    
    public void setLabMsgTip(JLabel labMsgTip) {
        this.labMsgTip = labMsgTip;
    }
}
