package org.come.init;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import org.come.view.View;

public class UpdateView extends View
{
    private ImageIcon background;
    private JLabel title;
    private JLabel progressBg;
    private JLabel progress;
    private ClientCheckUpdate checkUpdate;
    
    public UpdateView() {
        this.setBounds(0, 0, 800, 600);
        this.setBackground(Color.YELLOW);
        this.background = new ImageIcon("resource/NewUi/11.png");
        (this.title = new JLabel("正在检查更新...")).setBounds(100, 565, 600, 29);
        this.title.setForeground(Color.black);
        this.title.setHorizontalAlignment(0);
        Font font = new Font("楷体", 1, 25);
        this.title.setFont(font);
        this.add(this.title);
        (this.progressBg = new JLabel(new ImageIcon("resource/NewUi/21.png"))).setBounds(100, 565, 600, 29);
        this.add(this.progressBg);
        (this.progress = new JLabel(new ImageIcon("resource/NewUi/22.png"))).setBounds(105, 565, 0, 29);
        this.add(this.progress);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background.getImage(), 0, 0, 800, 600, this);
    }
    
    public JLabel getTitle() {
        return this.title;
    }
    
    public void update() {
        (this.checkUpdate = new ClientCheckUpdate(this)).start();
        if ((boolean)this.checkUpdate.isDone() && !(boolean)this.checkUpdate.isFailure()) {
            Main.checkUpdate.setVisible(false);
            try {
                com.main.Main.main(new String[0]);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public JLabel getProgress() {
        return this.progress;
    }
    
    public ClientCheckUpdate getCheckUpdate() {
        return this.checkUpdate;
    }
}
