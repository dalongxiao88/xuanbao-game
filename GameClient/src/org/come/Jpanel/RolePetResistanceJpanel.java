package org.come.Jpanel;

import java.awt.*;

import javax.swing.*;

import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;


public class RolePetResistanceJpanel extends JPanel {

    // 几个属性分类面板
    private FaShuKangXingJpanel[] shuXingJpanel = new FaShuKangXingJpanel[5];

    private FormsOnOffBtn closeBtn;

    public RolePetResistanceJpanel() throws Exception {

        this.setBackground(new Color(0, 0, 0, 0));
        this.setLayout(null);
        if ("水墨".equals(MyIsif.getStyle())) {
            closeBtn = new FormsOnOffBtn("inkImg/button/smkxguanbi.png", 1, 58);
            closeBtn.setBounds(281, 1, 17, 17);
            this.add(closeBtn);
        } else {

            closeBtn = new FormsOnOffBtn("inkImg/Client/hmkxguanbi.png", 1, 58);
            closeBtn.setBounds(270, 1, 19, 20);
            this.add(closeBtn);
        }
        int hight = 0;
        for (int i = 0; i < shuXingJpanel.length; i++) {
            shuXingJpanel[i] = new FaShuKangXingJpanel(i + 10, true);
            shuXingJpanel[i].setOpaque(false);
            shuXingJpanel[i].setBounds(0, hight, 300, 24);
            hight += shuXingJpanel[i].getHeight();
            this.add(shuXingJpanel[i]);
        }
        this.setPreferredSize(new Dimension(300, hight));

    }
    public RolePetResistanceJpanel(int type) throws Exception {

        this.setBackground(new Color(0, 0, 0, 0));
        this.setLayout(null);
//        if ("水墨".equals(MyIsif.getStyle())) {
//            closeBtn = new FormsOnOffBtn("inkImg/button/smkxguanbi.png", 1, 58);
//            closeBtn.setBounds(281, 1, 17, 17);
//            this.add(closeBtn);
//        } else {
//
//            closeBtn = new FormsOnOffBtn("inkImg/Client/hmkxguanbi.png", 1, 58);
//            closeBtn.setBounds(270, 1, 19, 20);
//            this.add(closeBtn);
//        }
        int hight = 0;
        for (int i = 0; i < shuXingJpanel.length; i++) {
            shuXingJpanel[i] = new FaShuKangXingJpanel(i + 10, true);
            shuXingJpanel[i].setOpaque(false);
            shuXingJpanel[i].setBounds(0, hight, 300, 24);
            hight += shuXingJpanel[i].getHeight();
            this.add(shuXingJpanel[i]);
        }
        this.setPreferredSize(new Dimension(300, hight));

    }
    private ImageIcon icon;
    private ImageIcon icon1;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 技能

        if ("水墨".equals(MyIsif.getStyle())) {
            if (icon == null) {
                icon = new ImageIcon("inkImg/Client/kx_zj.png");
                icon1 = new ImageIcon("inkImg/button/smkxxlk.png");
            }
        } else {
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

}
