package org.come.login;

import java.awt.Point;
import java.awt.MouseInfo;
import org.come.bean.PathPoint;
import java.awt.Image;
import java.awt.Toolkit;
import org.come.test.Main;
import com.updateNew.MyIsif;
import org.come.until.GsonUtil;
import org.apache.commons.lang.StringUtils;
import org.come.until.Util;
import org.come.until.Music;
import org.come.bean.LoginRoleInfo;
import org.come.bean.LoginInfo;
import javax.swing.JFrame;

public class LoginFrame extends JFrame
{
    private LoginJpanel loginJpanel;
    private LoginInfo loginInfo;
    private LoginRoleInfo roleInfo;
    
    public LoginFrame() {
        Music.addbeijing("0001-615041B0.mp3");
        String userInfoStr = Util.readUserInfo();
        if (StringUtils.isNotBlank(userInfoStr)) {
            try {
                this.loginInfo = GsonUtil.getGsonUtil().getgson().fromJson(userInfoStr, LoginInfo.class);
            }
            catch (Exception ex) {}
        }
        if (this.loginInfo == null) {
            this.loginInfo = new LoginInfo();
        }
        this.loginJpanel = new LoginJpanel(this.loginInfo);
        String a = MyIsif.ifs;
        this.setContentPane(this.loginJpanel);
        if (a.equals("D")) {
            this.setTitle("大话西游Ⅱ");
        }
        else {
            this.setUndecorated(true);
            this.setTitle("大话西游Ⅱ" + Main.index + Main.name.toString());
            this.setLocation(5, 55);
        }
        this.setResizable(false);
        Toolkit tool = this.getToolkit();
        Image myimage = tool.getImage("img/icon/ico.png");
        this.setIconImage(myimage);
        this.pack();
        this.setVisible(true);
        if (a.equals("D")) {
            this.setLocationRelativeTo(null);
        }
        this.setDefaultCloseOperation(3);
    }
    
    public LoginJpanel getLoginJpanel() {
        return this.loginJpanel;
    }
    
    public void setLoginJpanel(LoginJpanel loginJpanel) {
        this.loginJpanel = loginJpanel;
    }
    
    public PathPoint mousepath() {
        Point p = MouseInfo.getPointerInfo().getLocation();
        Point p2 = this.getLocation();
        Main.pathPoint.setX((int)(p.getX() - p2.getX() - 4.0));
        Main.pathPoint.setY((int)(p.getY() - p2.getY() - 26.0));
        return Main.pathPoint;
    }
    
    public void setRoleInfo(LoginRoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }
    
    public LoginRoleInfo getRoleInfo() {
        if (this.roleInfo == null) {
            this.roleInfo = new LoginRoleInfo();
        }
        return this.roleInfo;
    }
    
    public LoginInfo getLoginInfo() {
        return this.loginInfo;
    }
}
