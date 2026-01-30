package org.come.Frame;

import org.come.bean.LoginResult;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import java.awt.*;
import java.util.TimerTask;
//import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import com.tool.btn.MyAWTEventListener;
import org.come.Jpanel.GameJpanel;
import org.come.test.Main;
import org.come.until.ScrenceUntil;
import com.tool.image.ImageMixDeal;
import org.come.socket.GameClient;
//import java.awt.Point;
import java.awt.image.MemoryImageSource;
//import java.awt.Toolkit;
import com.updateNew.MyIsif;
import javax.swing.JFrame;

public class GameJframe extends JFrame
{
    private String singletitle;
    public static int titleHeight;
    
    public GameJframe() throws Exception {
        String a = MyIsif.ifs;
        Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(0, 0, new int[0], 0, 0));
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), null));
        Toolkit tool = this.getToolkit();
        Image myimage = tool.getImage("img/icon/ico.png");
        this.setIconImage(myimage);
        //多开变单开
        // =========================内置多开=====================================================================================
        if (a.equals("D")) {
            this.setTitle("大话西游Ⅱ  【" + GameClient.BT + "】- " + ImageMixDeal.userimg.getRoleShow().getRolename() + "（ID:" + ImageMixDeal.userimg.getRoleShow().getRole_id() + "）[" + ImageMixDeal.userimg.getRoleShow().getSpecies_id() + "]");
            this.setSize(ScrenceUntil.Screen_x + ScrenceUntil.ChatFram_X + 6, ScrenceUntil.Screen_y + 30);
        }
        else {
            this.setTitle("大话西游Ⅱ" + Main.index + Main.name.toString());
            this.setUndecorated(true);
            new Thread(()/*  */ -> {
                while (true) {
                    this.setLocation(5, 60);
                    try {
                        Thread.sleep(50L);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            this.setSize(ScrenceUntil.Screen_x + ScrenceUntil.ChatFram_X, ScrenceUntil.Screen_y);
        }
        this.setContentPane(GameJpanel.getGameJpanel());
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.setResizable(false);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setFocusTraversalKeysEnabled(false);
        Toolkit tk = Toolkit.getDefaultToolkit();
        tk.addAWTEventListener(new MyAWTEventListener(), 8L);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }
            
            @Override
            public void windowClosing(WindowEvent e) {
            }
            
            @Override
            public void windowClosed(WindowEvent e) {
            }
            
            @Override
            public void windowIconified(WindowEvent e) {
            }
            
            @Override
            public void windowDeiconified(WindowEvent e) {
            }
            
            @Override
            public void windowActivated(WindowEvent e) {
            }
            
            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }
    
    public class UpdateTask extends TimerTask
    {
        @Override
        public void run() {
            LoginResult login = RoleData.getRoleData().getLoginResult();
            if (StringUtils.isNotBlank(login.getLianghaoexpire())) {
                GameJframe.this.setTitle("大话西游Ⅱ    【" + GameClient.BT + "】- " + ImageMixDeal.userimg.getRoleShow().getRolename() + "（ID:" + login.getLiangHao() + "）");
            }
            else {
                GameJframe.this.setTitle("大话西游Ⅱ    【" + GameClient.BT + "】- " + ImageMixDeal.userimg.getRoleShow().getRolename() + "（ID:" + ImageMixDeal.userimg.getRoleShow().getRole_id() + "）");
            }
        }
    }
}
