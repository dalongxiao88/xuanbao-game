package org.come.Jpanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import org.come.Frame.ZhuFrame;
import org.come.until.sendsms;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.come.until.Util;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.CutButtonImage;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.bean.PhoneNumberSGBean;
import javax.swing.JTextField;
import com.tool.btn.IphoneVerifyBtn;
import javax.swing.JPanel;

public class IphoneVerifyPanel extends JPanel
{
    private IphoneVerifyBtn bindingBtn;
    private IphoneVerifyBtn unbundleBtn;
    private IphoneVerifyBtn iphoneBtn;
    private IphoneVerifyBtn verificationBtn;
    private JTextField iphoneText;
    private JTextField verificationText;
    private JTextField safetyText;
    private String verification;
    private String iphoneNumber;
    private PhoneNumberSGBean numberSGBean;
    private boolean btnchange;
    private int typeMenu;
    int time;
    private ImageIcon iconBack;
    int in;
    
    public IphoneVerifyPanel() {
        this.btnchange = false;
        this.typeMenu = -1;
        this.time = -2;
        this.in = 0;
        this.setPreferredSize(new Dimension(362, 308));
        this.setOpaque(false);
        this.setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 99);
        offBtn.setBounds(335, 2, 25, 25);
        this.add(offBtn);
        this.getBindingBtn();
        this.getUnbundleBtn();
        this.getIphoneText();
        this.getVerificationText();
        this.getSafetyText();
        this.getVerificationBtn();
        this.getIphoneBtn();
    }
    
    public void changeMenu(int type) {
        if (type == -1) {
            if (this.typeMenu == -1) {
                this.typeMenu = 1;
            }
            else {
                return;
            }
        }
        else {
            if (type == this.typeMenu) {
                this.cleanText();
                return;
            }
            this.typeMenu = type;
        }
        try {
            if (this.typeMenu == 1) {
                this.getBindingBtn().setIcons(CutButtonImage.cuts("inkImg/button/B2.png"));
                this.getUnbundleBtn().setIcons(CutButtonImage.cuts("inkImg/button/B3.png"));
                this.getIphoneBtn().setIcons(CutButtonImage.cuts("inkImg/button/B6.png"));
            }
            else if (this.typeMenu == 2) {
                this.getBindingBtn().setIcons(CutButtonImage.cuts("inkImg/button/B1.png"));
                this.getUnbundleBtn().setIcons(CutButtonImage.cuts("inkImg/button/B4.png"));
                this.getIphoneBtn().setIcons(CutButtonImage.cuts("inkImg/button/B7.png"));
            }
            if (this.time < -1) {
                SendMessageUntil.toServer(Agreement.getAgreement().PhoneNumberIsNoGetAgreement());
            }
            this.cleanText();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void cleanText() {
        this.iphoneText.setText("");
        this.safetyText.setText("");
        this.verificationText.setText("");
    }
    
    public boolean judgeTime() {
        if (this.numberSGBean.getNumbertime() == null) {
            return true;
        }
        String dateToStamp = dateToStamp(this.numberSGBean.getNumbertime());
        long parseLong = Long.parseLong(dateToStamp);
        long time = Util.getTime();
        return (int)((time - parseLong) / 6000L / 60L) >= 24;
    }
    
    public static String dateToStamp(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        String res = String.valueOf(ts);
        return res;
    }
    
    public void changBtn(int btn, String text, String path, int type) {
        if (this.time >= -1) {
            return;
        }
        try {
            this.verificationBtn.setBtn(btn);
            this.verificationBtn.setText(text);
            if (type == 1) {
                this.verificationBtn.setIcon(CutButtonImage.getImage(path, -1, -1));
            }
            else if (type == 3) {
                this.verificationBtn.setIcons(CutButtonImage.cuts(path));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sendIphonegetVerification() {
        if (this.iphoneText.getText() != null && !"".equals(this.iphoneText.getText())) {
            this.iphoneNumber = this.iphoneText.getText();
            this.verification = sendsms.sendUNtil(this.iphoneNumber);
            if ("error".equals(this.verification)) {
                ZhuFrame.getZhuJpanel().addPrompt2("获取验证码失败，请查看手机是否正确");
            }
            else if ("logup".equals(this.verification)) {
                ZhuFrame.getZhuJpanel().addPrompt2("获取验证码失败，手机发送超过次数");
            }
            else {
                this.changBtn(-1, "300", "inkImg/button/B10.png", 1);
                this.time = 300;
                ZhuFrame.getZhuJpanel().addPrompt2("发送验证码成功，请及时验证");
            }
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("请先输入手机号");
        }
    }
    
    public void judgePhone(PhoneNumberSGBean sgBean) {
        if (sgBean == null) {
            return;
        }
        this.numberSGBean = sgBean;
        if ("nophone".equals(this.numberSGBean.getPhone()) && this.typeMenu == 2) {
            this.changBtn(-1, "未绑定", "inkImg/button/B10.png", 1);
            return;
        }
        if (!"nophone".equals(this.numberSGBean.getPhone()) && this.typeMenu == 1) {
            this.changBtn(-1, "已绑定", "inkImg/button/B10.png", 1);
            return;
        }
        if ("nophone".equals(this.numberSGBean.getPhone()) && this.typeMenu == 1) {
            if (!this.judgeTime()) {
                this.changBtn(-1, "无法绑定", "inkImg/button/B10.png", 1);
                ZhuFrame.getZhuJpanel().addPrompt2("解绑还没有过24小时，无法绑定");
                return;
            }
            this.changBtn(1, "", "inkImg/button/B5.png", 3);
        }
        else {
            this.changBtn(1, "", "inkImg/button/B5.png", 3);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = CutButtonImage.getImage("inkImg/background/S2.png", -1, -1);
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 362, 308, this);
        if (this.time >= -1) {
            if (this.in >= 100) {
                this.verificationBtn.setText(((this.time > 0) ? this.time : 0) + "");
                --this.time;
                this.in = 0;
                if (this.time < -1) {
                    SendMessageUntil.toServer(Agreement.getAgreement().PhoneNumberIsNoGetAgreement());
                }
            }
            ++this.in;
        }
    }
    
    public IphoneVerifyBtn getBindingBtn() {
        if (this.bindingBtn == null) {
            (this.bindingBtn = new IphoneVerifyBtn("inkImg/button/B2.png", 1, 1, this)).setBounds(44, 21, 70, 35);
            this.add(this.bindingBtn);
        }
        return this.bindingBtn;
    }
    
    public void setBindingBtn(IphoneVerifyBtn bindingBtn) {
        this.bindingBtn = bindingBtn;
    }
    
    public IphoneVerifyBtn getUnbundleBtn() {
        if (this.unbundleBtn == null) {
            (this.unbundleBtn = new IphoneVerifyBtn("inkImg/button/B3.png", 1, 2, this)).setBounds(116, 21, 70, 35);
            this.add(this.unbundleBtn);
        }
        return this.unbundleBtn;
    }
    
    public void setUnbundleBtn(IphoneVerifyBtn unbundleBtn) {
        this.unbundleBtn = unbundleBtn;
    }
    
    public IphoneVerifyBtn getIphoneBtn() {
        if (this.iphoneBtn == null) {
            (this.iphoneBtn = new IphoneVerifyBtn("inkImg/button/B6.png", 1, 3, this)).setBounds(123, 170, 140, 26);
            this.add(this.iphoneBtn);
        }
        return this.iphoneBtn;
    }
    
    public void setIphoneBtn(IphoneVerifyBtn iphoneBtn) {
        this.iphoneBtn = iphoneBtn;
    }
    
    public IphoneVerifyBtn getVerificationBtn() {
        if (this.verificationBtn == null) {
            (this.verificationBtn = new IphoneVerifyBtn("inkImg/button/B5.png", 1, 4, this)).setBounds(248, 70, 68, 17);
            this.add(this.verificationBtn);
        }
        return this.verificationBtn;
    }
    
    public void setVerificationBtn(IphoneVerifyBtn verificationBtn) {
        this.verificationBtn = verificationBtn;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    public JTextField getIphoneText() {
        if (this.iphoneText == null) {
            (this.iphoneText = new JTextField()).setBorder(null);
            this.iphoneText.setOpaque(false);
            this.iphoneText.setBounds(134, 71, 108, 16);
            this.iphoneText.setForeground(Color.white);
            this.iphoneText.setCaretColor(Color.white);
            this.iphoneText.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int keyChar = e.getKeyChar();
                    if (keyChar < 48 || keyChar > 57) {
                        e.consume();
                        return;
                    }
                    String key = IphoneVerifyPanel.this.iphoneText.getText();
                    if (key.length() >= 11) {
                        e.consume();
                        return;
                    }
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            this.add(this.iphoneText);
        }
        return this.iphoneText;
    }
    
    public void setIphoneText(JTextField iphoneText) {
        this.iphoneText = iphoneText;
    }
    
    public JTextField getVerificationText() {
        if (this.verificationText == null) {
            (this.verificationText = new JTextField()).setBorder(null);
            this.verificationText.setOpaque(false);
            this.verificationText.setBounds(134, 104, 181, 16);
            this.verificationText.setForeground(Color.white);
            this.verificationText.setCaretColor(Color.white);
            this.add(this.verificationText);
        }
        return this.verificationText;
    }
    
    public void setVerificationText(JTextField verificationText) {
        this.verificationText = verificationText;
    }
    
    public JTextField getSafetyText() {
        if (this.safetyText == null) {
            (this.safetyText = new JTextField()).setBorder(null);
            this.safetyText.setOpaque(false);
            this.safetyText.setBounds(134, 138, 181, 16);
            this.safetyText.setForeground(Color.white);
            this.safetyText.setCaretColor(Color.white);
            this.add(this.safetyText);
        }
        return this.safetyText;
    }
    
    public void setSafetyText(JTextField safetyText) {
        this.safetyText = safetyText;
    }
    
    public int getTypeMenu() {
        return this.typeMenu;
    }
    
    public void setTypeMenu(int typeMenu) {
        this.typeMenu = typeMenu;
    }
    
    public String getVerification() {
        return this.verification;
    }
    
    public void setVerification(String verification) {
        this.verification = verification;
    }
    
    public String getIphoneNumber() {
        return this.iphoneNumber;
    }
    
    public void setIphoneNumber(String iphoneNumber) {
        this.iphoneNumber = iphoneNumber;
    }
    
    public PhoneNumberSGBean getNumberSGBean() {
        return this.numberSGBean;
    }
    
    public void setNumberSGBean(PhoneNumberSGBean numberSGBean) {
        this.numberSGBean = numberSGBean;
    }
}
