package org.come.login;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import org.come.view.View;

public class MainView extends View
{
    private ImageIcon background;
    private ImageIcon top_background;
    private ImageIcon botton_background;
    private ImageIcon logo;
    private SpriteBtn enterTheGameBtn;
    private SpriteBtn registerAccBtn;
    private SpriteBtn dianKaBtn;
    private SpriteBtn hasGameHomepageBtn;
    private SpriteBtn productionTeamBtn;
    private SpriteBtn exitGameBtn;
    private SpriteBtn btnlogo;
    
    public MainView(LoginJpanel loginJpanel) {
        this.setBounds(0, 0, 1027, 720);
        this.background = new ImageIcon("resource/xinUI/004.png");
        (this.btnlogo = new SpriteBtn("resource/xinUI/004.jpeg", 0, 30, false)).setBounds(0, 30, 1027, 720);
        this.add(this.btnlogo);
        int y = 80;
        (this.enterTheGameBtn = new SpriteBtn("resource/xinUI/01.was", 620, y + 514, false)).setBounds(620, y + 514, 143, 37);
        this.enterTheGameBtn.addMouseListener(new MainMouslisten(1, this.enterTheGameBtn, loginJpanel));
        this.add(this.enterTheGameBtn);
        (this.exitGameBtn = new SpriteBtn("resource/xinUI/06.was", 220, y + 514, false)).setBounds(220, y + 514, 143, 37);
        this.exitGameBtn.addMouseListener(new MainMouslisten(5, this.exitGameBtn, loginJpanel));
        this.add(this.exitGameBtn);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long l = System.currentTimeMillis();
        this.enterTheGameBtn.draw(g);
        this.exitGameBtn.draw(g);
    }
}
