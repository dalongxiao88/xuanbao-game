package org.come.log;

import java.awt.Image;
import java.awt.Toolkit;
import com.main.UpdateMain;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Window;
import javax.swing.JFrame;

public class SetJframe extends JFrame
{
    private SetDialogLogin setDialogLogin;
    
    public SetJframe() {
        Toolkit tool = this.getToolkit();
        this.setType(Type.UTILITY);
        Image myimage = tool.getImage("img/icon/ico.png");
        this.setIconImage(myimage);
        this.setTitle("游戏设置");
        this.getRootPane().setWindowDecorationStyle(0);
        this.setContentPane(this.setDialogLogin = new SetDialogLogin(this));
        this.pack();
        this.setSize(400, 300);
        this.setDefaultCloseOperation(2);
        this.setVisible(true);
        this.setFocusable(false);
        this.setLocationRelativeTo(null);
        this.setFocusTraversalKeysEnabled(true);
        this.setFocusable(false);
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UpdateMain.setJframe.setVisible(false);
            }
        });
        this.setVisible(false);
    }
    
    public SetDialogLogin getSetDialogLogin() {
        return this.setDialogLogin;
    }
    
    public void setSetDialogLogin(SetDialogLogin setDialogLogin) {
        this.setDialogLogin = setDialogLogin;
    }
}
