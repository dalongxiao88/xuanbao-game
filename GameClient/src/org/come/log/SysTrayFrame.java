package org.come.log;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.SystemTray;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.TrayIcon;
import javax.swing.JFrame;

public class SysTrayFrame extends JFrame
{
    private TrayIcon trayIcon;
    BorderLayout borderLayout1;
    JPanel root;
    JButton exit;
    
    public SysTrayFrame() {
        this.borderLayout1 = new BorderLayout();
        this.root = new JPanel();
        this.exit = new JButton();
        try {
            this.jbInit();
            this.pack();
            this.initTrayIcon();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.setDefaultCloseOperation(1);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (SystemTray.isSupported()) {
                    SysTrayFrame.this.setVisible(false);
                    SysTrayFrame.this.minimizeToTray();
                }
                else {
                    System.exit(0);
                }
            }
            
            @Override
            public void windowIconified(WindowEvent e) {
                if (SystemTray.isSupported()) {
                    SysTrayFrame.this.setVisible(false);
                    SysTrayFrame.this.minimizeToTray();
                }
                else {
                    System.exit(0);
                }
            }
        });
        this.getContentPane().setLayout(this.borderLayout1);
        this.exit.setText("exit");
        this.exit.addActionListener(new SysTrayFrame_exit_actionAdapter(this));
        this.getContentPane().add(this.root, "Center");
        this.root.add(this.exit);
    }
    
    private void initTrayIcon() {
        Image image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Duke16.gif"));
        PopupMenu popup = new PopupMenu();
        MenuItem exitItem = new MenuItem("Show");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SysTrayFrame.this.setVisible(true);
                SysTrayFrame.this.setExtendedState(0);
                SystemTray.getSystemTray().remove(SysTrayFrame.this.trayIcon);
            }
        };
        exitItem.addActionListener(listener);
        popup.add(exitItem);
        (this.trayIcon = new TrayIcon(image, "MyTray", popup)).addActionListener(listener);
    }
    
    public void minimizeToTray() {
        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(this.trayIcon);
        }
        catch (AWTException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        SysTrayFrame systrayframe = new SysTrayFrame();
        systrayframe.setTitle("MyTray");
        systrayframe.setVisible(true);
    }
    
    public void exit_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
