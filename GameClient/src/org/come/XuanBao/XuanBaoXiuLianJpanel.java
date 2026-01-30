package org.come.XuanBao;


import com.tool.role.RoleData;
import com.tool.tcpimg.UIUtils;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.come.Frame.ZhuFrame;
import org.come.XuanBao.RoleXuanBao;
import org.come.XuanBao.XuanBaoPagingBtn;
import org.come.bean.LoginResult;
import org.come.bean.XuanBao;


public class XuanBaoXiuLianJpanel extends JPanel {
    ImageIcon imageIcon = new ImageIcon("img/xuan/玄蕴转换.png");
    public static long exp = 30000000L;

    XuanBao xuanBao;


    private XuanBaoPagingBtn zhuanhuan;


    public ImageIcon jdt;


    public XuanBaoXiuLianJpanel() {
        this.jdt = new ImageIcon("inkImg/button/66.png");
        setPreferredSize(new Dimension(347, 392));
        setLayout((LayoutManager) null);
        setOpaque(false);
        setBackground(UIUtils.Color_BACK);

        this.zhuanhuan = new XuanBaoPagingBtn("inkImg/button1/B20.png", 1, 5, "转换", UIUtils.COLOR_WHITE2);

        this.zhuanhuan.setBounds(150, 313, 59, 72);
        add(this.zhuanhuan);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.imageIcon.getImage(), 0, 0, 347, 392, this);
        if (this.xuanBao != null) {
            RoleData roleData = RoleData.getRoleData();
            LoginResult loginResult = roleData.getLoginResult();
            g.setColor(Color.WHITE);
            g.setFont(UIUtils.TEXT_FONT15);
            g.drawImage(this.jdt.getImage(), 180, 98, Math.min(115, (int) ((115 * this.xuanBao.getXuanyun()) / LFExp(this.xuanBao.getLvl()))), 15, null);
            g.drawString(this.xuanBao.getName(), 180, 53);
            g.drawString(this.xuanBao.getLvl() + "", 180, 80);
            g.drawString(this.xuanBao.getXuanyun() + "/" + LFExp(this.xuanBao.getLvl()), 180, 110);
            g.drawString(exp + "", 180, 200);
            g.drawString(loginResult.getExperience() + "", 180, 230);
            g.drawString("1500", 180, 259);
            if (loginResult.getExperience().longValue() >= exp) {
                this.zhuanhuan.setType(1);
            } else {
                this.zhuanhuan.setType(-1);
            }


        }
    }

    public static long LFExp(int lvl) {
        if (lvl <= 0)
            return 500L;
        return ((long) lvl * lvl * 290 - (long) (lvl - 1) * (lvl - 1) * 290);

    }
    public void update() {
        if ((RoleXuanBao.getRoleXuanBao()).choseBao == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("请先选择一个玄宝");
            return;
        }
        this.xuanBao = (RoleXuanBao.getRoleXuanBao()).choseBao;
    }
}


