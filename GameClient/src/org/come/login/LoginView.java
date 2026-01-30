package org.come.login;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import org.come.bean.LoginRoleInfo;
import org.come.test.Main;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.socket.GameClient;
import org.come.bean.LoginUserBean;
import org.apache.commons.lang.StringUtils;
import org.come.until.UserData;
import org.come.until.Util;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import org.come.view.View;

public class LoginView extends View
{
    public static final String T = "";
    private SpriteBtn btnLogopic;
    private SpriteBtn btnCancel;
    private SpriteBtn btnExit;
    private SpriteBtn btnLogin;
    private SpriteBtn btnRegister;
    private SpriteBtn btnRemId;
    private SpriteBtn btnPwdPro;
    private SpriteBtn btnDropdown;
    private SpriteBtn btnChangePwd;
    private ImageIcon background;
    private final ImageIcon background2;
    private final ImageIcon Sunlight;
    private final ImageIcon putongdl;
    private final ImageIcon erweimadl;
    private final ImageIcon zhangh;
    private final ImageIcon mim;
    private final ImageIcon zhaohui;
    private final ImageIcon zhanhui2;
    private final ImageIcon kehu;
    private final ImageIcon guanwang;
    private SpriteBtn btngouzi1;
    private SpriteBtn btngouzi2;
    private JTextField userAccount;
    private JPasswordField textPasswordField;
    private final ImageIcon userAccountBg;
    private int jiaodian;
    private final JLabel jizhuzhanghao;
    private KeyView keyView;
    private RegisterView registerView;
    private IphoneView iphoneView;
    SpriteBtn fanghui;
    private DownView downView;
    private String msg;
    
    public LoginView(LoginJpanel loginJpanel) {
        this.setBounds(0, 0, 1027, 720);
        this.background = new ImageIcon("resource/xinUI/xin/font421_@.png");
        this.background2 = new ImageIcon("resource/xinUI/xin/jrbj.png");
        this.userAccountBg = new ImageIcon("resource/xinUI/xin/176.png");
        this.putongdl = new ImageIcon("resource/xinUI/xin/179.png");
        this.erweimadl = new ImageIcon("resource/xinUI/xin/180.png");
        this.zhangh = new ImageIcon("resource/xinUI/xin/177.png");
        this.mim = new ImageIcon("resource/xinUI/xin/178.png");
        this.zhaohui = new ImageIcon("resource/xinUI/xin/183.png");
        this.zhanhui2 = new ImageIcon("resource/xinUI/xin/184.png");
        this.kehu = new ImageIcon("resource/xinUI/xin/182.png");
        this.guanwang = new ImageIcon("resource/xinUI/xin/181.png");
        this.Sunlight = new ImageIcon("resource/xinUI/xin/186.png");
        (this.fanghui = new SpriteBtn("resource/xinUI/xin/返回按钮", 5, 685, false)).setBounds(5, 685, 60, 30);
        this.fanghui.addMouseListener(new RoleMouslisten(20, this.fanghui, loginJpanel));
        this.add(this.fanghui);
        (this.btnLogopic = new SpriteBtn("resource/xinUI/xin/logo", 360, 150, false)).setBounds(240, 100, 1027, 720);
        this.add(this.btnLogopic);
        (this.btnRemId = new SpriteBtn("resource/xinUI/xin/按钮_勾选框", 406, 386, true)).setBounds(398, 379, 20, 20);
        this.btnRemId.addMouseListener(new LoginMouslisten(0, this.btnRemId, loginJpanel));
        this.add(this.btnRemId);
        (this.btnPwdPro = new SpriteBtn("resource/xinUI/小键盘", 577, 342, true)).setBounds(577, 342, 30, 20);
        this.btnPwdPro.addMouseListener(new LoginMouslisten(1, this.btnPwdPro, loginJpanel));
        this.add(this.btnPwdPro);
        (this.btnRegister = new SpriteBtn("resource/xinUI/xin/注册按钮", 623, 271, false)).setBounds(623, 271, 50, 19);
        this.btnRegister.addMouseListener(new LoginMouslisten(2, this.btnRegister, loginJpanel));
        this.add(this.btnRegister);
        (this.btnDropdown = new SpriteBtn("resource/xinUI/xin/下拉按钮2", 594, 326, false)).setBounds(594, 316, 20, 20);
        this.btnDropdown.addMouseListener(new LoginMouslisten(4, this.btnDropdown, loginJpanel));
        this.add(this.btnDropdown);
        (this.btnLogin = new SpriteBtn("resource/xinUI/进入游戏_按钮", 424, 394, false)).setBounds(439, 405, 155, 35);
        this.btnLogin.addMouseListener(new LoginMouslisten(5, this.btnLogin, loginJpanel));
        this.add(this.btnLogin);
        (this.jizhuzhanghao = new JLabel()).setBounds(422, 370, 50, 30);
        this.jizhuzhanghao.setFont(UIUtils.TEXT_FONT11);
        this.jizhuzhanghao.setForeground(new Color(227, 238, 231));
        this.jizhuzhanghao.setText("记住账号");
        this.add(this.jizhuzhanghao);
        (this.userAccount = new JTextField()).setBounds(404, 314, 200, 23);
        this.userAccount.setOpaque(false);
        this.userAccount.setBorder(BorderFactory.createEmptyBorder());
        this.userAccount.setForeground(Color.WHITE);
        this.userAccount.setFont(new Font("宋体", 0, 14));
        this.userAccount.setCaretColor(Color.WHITE);
        this.userAccount.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
            }
            
            @Override
            public void focusGained(FocusEvent e) {
                LoginView.this.jiaodian = 1;
            }
        });
        this.add(this.userAccount);
        (this.textPasswordField = new JPasswordField()).setBounds(404, 347, 200, 23);
        this.textPasswordField.setOpaque(false);
        this.textPasswordField.setBorder(BorderFactory.createEmptyBorder());
        this.textPasswordField.setForeground(Color.WHITE);
        this.textPasswordField.setFont(new Font("宋体", 0, 14));
        this.textPasswordField.setEchoChar('*');
        this.textPasswordField.setCaretColor(Color.WHITE);
        this.textPasswordField.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
            }
            
            @Override
            public void focusGained(FocusEvent e) {
                LoginView.this.jiaodian = 2;
            }
        });
        this.add(this.textPasswordField);
        this.cshtext();
    }
    
    public void cshtext() {
        String v = Util.readUserPwd();
        String[] vs = v.split("\\|");
        String[] vs2 = vs[0].split("=");
        if (vs2.length == 2) {
            this.userAccount.setText(vs2[0]);
            this.textPasswordField.setText(vs2[1]);
        }
    }
    
    public void cshtext(String text) {
        String[] vs = text.split("=");
        this.userAccount.setText(vs[0]);
        this.textPasswordField.setText(vs[1]);
        this.downView.setVisible(false);
    }
    
    public void addtext(String sr) {
        if (this.jiaodian == 1) {
            this.userAccount.setText(this.userAccount.getText() + sr);
        }
        else if (this.jiaodian == 2) {
            this.textPasswordField.setText(this.textPasswordField.getText() + sr);
        }
    }
    
    public void jilu(String userandpwd) {
        if (this.btngouzi1 != null) {
            String v = Util.readUserPwd();
            String[] vs = v.split("\\|");
            if (vs.length >= 6) {
                v = UserData.Splice(v, vs[0], 4);
            }
            Util.writeUserPwd(UserData.Splice(UserData.Splice(v, userandpwd, 4), userandpwd, 0));
        }
    }
    
    public String removeJiLu(String userandpwd) {
        String v = Util.readUserPwd();
        String[] vs = v.split("\\|");
        int i = 0;
        while (i < vs.length) {
            if (vs[i].equals(userandpwd)) {
                v = UserData.Splice(v, vs[i], 4);
                break;
            }
            else {
                ++i;
            }
        }
        Util.writeUserPwd(v);
        if (StringUtils.isBlank(v)) {
            this.userAccount.setText("");
            this.textPasswordField.setText("");
        }
        return v;
    }
    
    public void login() {
        this.msg = null;
        LoginUserBean loginUserBean = new LoginUserBean();
        if ("".equals(this.userAccount.getText()) || "".equals(this.textPasswordField.getText())) {
            this.msg = "账号/密码不能为空！";
        }
        loginUserBean.setUsername(this.userAccount.getText());
        String pwd = new String(this.textPasswordField.getText());
        loginUserBean.setPassword(pwd);
        if (!loginUserBean.getUsername().equals("") && !loginUserBean.getPassword().equals("")) {
            this.jilu(this.userAccount.getText() + "=" + this.textPasswordField.getText());
            GameClient.username = loginUserBean.getUsername();
            GameClient.userpasw = loginUserBean.getPassword();
            String mesage = Agreement.getAgreement().LoginAgreement(GsonUtil.getGsonUtil().getgson().toJson(loginUserBean));
            SendMessageUntil.toServer(mesage);
        }
        LoginRoleInfo roleInfo = Main.frame.getRoleInfo();
        roleInfo.setAccount(loginUserBean.getUsername());
        roleInfo.setPassword(loginUserBean.getPassword());
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background.getImage(), 0, 0, 1027, 720, (ImageObserver)null);
        g.drawImage(this.background2.getImage(), 310, 283, 460, 180, null);
        g.drawImage(this.userAccountBg.getImage(), 394, 311, 215, 29, null);
        g.drawImage(this.userAccountBg.getImage(), 394, 343, 215, 29, null);
        g.drawImage(this.putongdl.getImage(), 358, 269, 73, 20, null);
        g.drawImage(this.erweimadl.getImage(), 438, 269, 86, 20, null);
        g.drawImage(this.zhangh.getImage(), 358, 316, 35, 20, null);
        g.drawImage(this.mim.getImage(), 358, 349, 35, 20, null);
        g.drawImage(this.zhaohui.getImage(), 610, 313, 68, 26, null);
        g.drawImage(this.zhanhui2.getImage(), 610, 345, 68, 26, null);
        g.drawImage(this.guanwang.getImage(), 945, 661, 67, 22, null);
        g.drawImage(this.kehu.getImage(), 945, 685, 67, 22, null);
        g.drawImage(this.Sunlight.getImage(), 0, 0, 1024, 720, null);
        this.fanghui.draw(g);
        this.btnLogopic.draw(g);
        this.btnRegister.draw(g);
        this.btnLogin.draw(g);
        this.btnRemId.draw(g);
        this.btnPwdPro.draw(g);
        this.btnDropdown.draw(g);
        if (this.btngouzi1 != null) {
            this.btngouzi1.draw(g);
        }
        if (this.btngouzi2 != null) {
            this.btngouzi2.draw(g);
        }
        if (this.msg != null) {
            g.setColor(Color.red);
            g.drawString(this.msg, 410, 310);
        }
    }
    
    public SpriteBtn getBtngouzi1() {
        return this.btngouzi1;
    }
    
    public void setBtngouzi1(SpriteBtn btngouzi1) {
        this.btngouzi1 = btngouzi1;
    }
    
    public SpriteBtn getBtngouzi2() {
        return this.btngouzi2;
    }
    
    public void setBtngouzi2(SpriteBtn btngouzi2) {
        this.btngouzi2 = btngouzi2;
    }
    
    public KeyView getKeyView() {
        return this.keyView;
    }
    
    public void setKeyView(KeyView keyView) {
        this.keyView = keyView;
    }
    
    public RegisterView getRegisterView() {
        return this.registerView;
    }
    
    public void setRegisterView(RegisterView registerView) {
        this.registerView = registerView;
    }
    
    public DownView getDownView() {
        return this.downView;
    }
    
    public void setDownView(DownView downView) {
        this.downView = downView;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public IphoneView getIphoneView() {
        return this.iphoneView;
    }
    
    public void setIphoneView(IphoneView iphoneView) {
        this.iphoneView = iphoneView;
    }
}
