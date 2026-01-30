package org.come.init;

import java.awt.Image;
import java.awt.Toolkit;
import org.come.socket.GameClient;
import javax.swing.JFrame;

public class CheckUpdate extends JFrame implements Runnable
{
    CheckUpdateJPanel checkUpdateJpanel;
    
    public CheckUpdate() {
        this.setTitle(GameClient.BT);
        this.setResizable(false);
        this.setContentPane(this.checkUpdateJpanel = new CheckUpdateJPanel());
        Toolkit tool = this.getToolkit();
        Image myimage = tool.getImage("img/icon/ico.png");
        this.setIconImage(myimage);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
    }
    
    @Override
    public void run() {
        try {
            this.checkUpdateJpanel.getUpdateView().update();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public CheckUpdateJPanel getCheckUpdateJpanel() {
        return this.checkUpdateJpanel;
    }
}
