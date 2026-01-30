package org.come.login;

import java.awt.Graphics;
import java.awt.Dimension;
import org.come.view.View;

public class BindAccountTipView extends View
{
    private SpriteBtn btnYes;
    private SpriteBtn Btnno;
    private SpriteBtn backImg;
    private LoginJpanel loginJpanel;
    
    public BindAccountTipView(LoginJpanel loginJpanel) {
        this.loginJpanel = loginJpanel;
        this.setPreferredSize(new Dimension(530, 168));
        this.setLayout(null);
        (this.backImg = new SpriteBtn("resource/NewUi/绑定账号提示", 0, 0, false)).setBounds(0, 0, 530, 160);
        this.add(this.backImg);
        (this.btnYes = new SpriteBtn("resource/NewUi/按钮_确定3", 70, 104, false)).setBounds(70, 104, 192, 52);
        this.btnYes.addMouseListener(new LoginMouslisten(8, this.btnYes, loginJpanel));
        this.add(this.btnYes);
        (this.Btnno = new SpriteBtn("resource/NewUi/按钮_取消4", 320, 112, false)).setBounds(320, 112, 159, 44);
        this.Btnno.addMouseListener(new LoginMouslisten(9, this.Btnno, loginJpanel));
        this.add(this.Btnno);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.backImg.draw(g);
        this.btnYes.draw(g);
        this.Btnno.draw(g);
    }
}
