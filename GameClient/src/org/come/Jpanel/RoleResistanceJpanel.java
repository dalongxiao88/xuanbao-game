package org.come.Jpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;

import com.tool.btn.FaShuKangXingBtn;
import org.come.mouslisten.WLLMouslisten;

public class RoleResistanceJpanel extends JPanel {

    // 几个属性分类面板
    private FaShuKangXingJpanel[] shuXingJpanel;
    private FaShuKangXingBtn jBtn, rBtn;
    private FormsOnOffBtn closeBtn;

    public RoleResistanceJpanel() throws Exception {

        this.setBackground(new Color(0, 0, 0, 0));
        this.setLayout(null);
        if ("水墨".equals(MyIsif.getStyle())) {
            closeBtn = new FormsOnOffBtn("inkImg/button/smkxguanbi.png", 1, 8);
            closeBtn.setBounds(281, 1, 17, 17);
            closeBtn.addMouseListener(new WLLMouslisten(125));
            this.add(closeBtn);
            jBtn = new FaShuKangXingBtn("inkImg/button/smkxshuaxin.png", 1, 990);
            jBtn.setBounds(263, 1, 18, 18);
            jBtn.addMouseListener(new WLLMouslisten(124));
            this.add(jBtn);
            rBtn = new FaShuKangXingBtn("inkImg/button1/B31.png", 1, 991, "全", UIUtils.White);
            rBtn.setBounds(1, 1, 18, 18);
            rBtn.addMouseListener(new WLLMouslisten(122));
            this.add(rBtn);
        }else{

            closeBtn = new FormsOnOffBtn("inkImg/Client/hmkxguanbi.png", 1, 8);
            closeBtn.setBounds(278, 1, 19, 20);
            closeBtn.addMouseListener(new WLLMouslisten(125));
            this.add(closeBtn);
            jBtn = new FaShuKangXingBtn("inkImg/Client/hmkxshuaxin.png", 1, 990);
            jBtn.setBounds(258, 1, 19, 20);
            jBtn.addMouseListener(new WLLMouslisten(124));
            this.add(jBtn);
            rBtn = new FaShuKangXingBtn("inkImg/hongmu/btn-small-1-bg.png", 1, 991, "全", UIUtils.COLOR_RED_BTNTEXT);
            rBtn.setBounds(4, 3, 17, 17);
            rBtn.addMouseListener(new WLLMouslisten(122));
            this.add(rBtn);

        }

        // setSize(new Dimension(290, 450));
        // 实力化面板
        shuXingJpanel = new FaShuKangXingJpanel[5];
        int hight = 0;
        for (int i = 0; i < shuXingJpanel.length; i++) {
            shuXingJpanel[i] = new FaShuKangXingJpanel(i + 1);
            shuXingJpanel[i].setOpaque(false);
            shuXingJpanel[i].setBounds(0, hight, 600, 24);
            hight += shuXingJpanel[i].getHeight();
            this.add(shuXingJpanel[i]);
        }
        this.setPreferredSize(new Dimension(600, hight));
    }

    private ImageIcon icon;
    private ImageIcon icon1;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if ("水墨".equals(MyIsif.getStyle())) {
            // 技能
            if (icon == null) {
                icon = new ImageIcon("inkImg/Client/kx_zj.png");
                icon1 = new ImageIcon("inkImg/button/smkxxlk.png");
            }
        }else{
            if (icon == null) {
                icon = new ImageIcon("inkImg/Client/kx_zj_hm.png");
                icon1 = new ImageIcon("inkImg/Client/hmkxxlk.png");
            }
        }

        g.drawImage(icon.getImage(), 0, 0, 300, this.getHeight() - 2, this);
        g.drawImage(icon1.getImage(), 0, this.getHeight() - 8, 300, 9, this);


    }

    public FaShuKangXingJpanel[] getShuXingJpanel() {
        return shuXingJpanel;
    }

    public void setShuXingJpanel(FaShuKangXingJpanel[] shuXingJpanel) {
        this.shuXingJpanel = shuXingJpanel;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public FaShuKangXingBtn getjBtn() {
        return jBtn;
    }

    public void setjBtn(FaShuKangXingBtn jBtn) {
        this.jBtn = jBtn;
    }

    public FaShuKangXingBtn getrBtn() {
        return rBtn;
    }

    public void setrBtn(FaShuKangXingBtn rBtn) {
        this.rBtn = rBtn;
    }

    public FormsOnOffBtn getCloseBtn() {
        return closeBtn;
    }

    public void setCloseBtn(FormsOnOffBtn closeBtn) {
        this.closeBtn = closeBtn;
    }
}
