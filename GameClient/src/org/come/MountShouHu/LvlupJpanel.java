package org.come.MountShouHu;

import org.come.entity.Goodstable;
import org.come.bean.LoginResult;
import org.come.Frame.MountShouhuJframe;
import java.awt.Color;
import java.math.BigDecimal;
import org.come.until.Util;
import com.tool.role.RoleData;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.tool.btn.MountShouhuBtn;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LvlupJpanel extends JPanel
{
    private final ImageIcon icon;
    private final ImageIcon icon1;
    private final ImageIcon icon2;
    public MountShouhuBtn tisheng;
    public int lvl;
    public int xiuweidian;
    public int tishengdianshu;
    public MountShouhuBtn up;
    public MountShouhuBtn down;
    public MountShouhuBtn yijiantisheng;
    public MountShouhuBtn tishengxiuweidian;
    public MountShouhuBtn tupo;
    public long dangqianyuanjing;
    public long suoxuyuanying;
    public boolean type;
    
    public LvlupJpanel() {
        this.icon = new ImageIcon("inkImg/Client/提升守护之力背景.png");
        this.icon1 = new ImageIcon("inkImg/background/ss504.png");
        this.icon2 = new ImageIcon("inkImg/Client/提升守护之力背景-突破.png");
        this.lvl = 0;
        this.xiuweidian = 4;
        this.tishengdianshu = 0;
        this.dangqianyuanjing = 0L;
        this.suoxuyuanying = 0L;
        this.type = true;
        this.setPreferredSize(new Dimension(339, 375));
        this.setBackground(UIUtils.Color_BACK);
        this.setLayout(null);
        (this.up = new MountShouhuBtn("inkImg/Client/向右箭头11X15.png", 1, 10, this)).setBounds(262, 240, 11, 15);
        this.add(this.up);
        (this.down = new MountShouhuBtn("inkImg/Client/向左箭头11X15.png", 1, 11, this)).setBounds(250, 240, 11, 15);
        this.add(this.down);
        (this.yijiantisheng = new MountShouhuBtn("inkImg/Client/100X26按钮.png", 1, 12, "一键提升", UIUtils.Black, UIUtils.TEXT_HY16, this)).setBounds(180, 280, 100, 26);
        this.add(this.yijiantisheng);
        (this.tishengxiuweidian = new MountShouhuBtn("inkImg/Client/100X26按钮.png", 1, 13, "提升修为点", UIUtils.Black, UIUtils.TEXT_HY16, this)).setBounds(60, 280, 100, 26);
        this.add(this.tishengxiuweidian);
        (this.tupo = new MountShouhuBtn("inkImg/Client/60X26按钮.png", 1, 14, "突破", UIUtils.Black, UIUtils.TEXT_HY16, this)).setBounds(160, 280, 60, 26);
        this.tupo.setVisible(false);
        this.add(this.tupo);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.type) {
            if (this.icon.getImage() != null) {
                g.drawImage(this.icon.getImage(), 0, 0, 339, 375, null);
            }
            LoginResult login = RoleData.getRoleData().getLoginResult();
            Util.drawPrice(g, login.getGold(), 140, 220);
            long n = 20000000L * (long)((this.tishengdianshu == 0) ? 1 : this.tishengdianshu);
            Util.drawPrice(g, new BigDecimal(n), 140, 190);
            g.setColor(Color.white);
            g.drawString(this.tishengdianshu + "", 190, 252);
            this.yijiantisheng.setVisible(true);
            this.tishengxiuweidian.setVisible(true);
            this.tupo.setVisible(false);
        }
        else {
            if (this.icon2.getImage() != null) {
                g.drawImage(this.icon2.getImage(), 0, 0, 339, 375, null);
            }
            this.yijiantisheng.setVisible(false);
            this.tishengxiuweidian.setVisible(false);
            this.tupo.setVisible(true);
            Goodstable ltrGoods = MountShouhuBtn.getGoodType(2258L);
            this.dangqianyuanjing = ((ltrGoods == null) ? 0L : ((long)(int)ltrGoods.getUsetime()));
            g.setColor(Color.white);
            this.suoxuyuanying = (long)((this.lvl + 1) * 12);
            g.drawString(this.suoxuyuanying + "", 180, 190);
            g.drawString(this.dangqianyuanjing + "", 180, 220);
        }
        g.setColor(Color.white);
        if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 0) {
            g.drawString("中天", 180, this.type ? 60 : 58);
            this.lvl = ((MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.zhongtianlvl == -1) ? 0 : MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.zhongtianlvl);
            this.xiuweidian = ((MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.zhongtianxiuweidian == -1) ? 0 : MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.zhongtianxiuweidian);
        }
        else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 1) {
            g.drawString("青龙", 180, this.type ? 60 : 58);
            this.lvl = ((MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.qinglonglvl == -1) ? 0 : MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.qinglonglvl);
            this.xiuweidian = ((MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.qinglongxiuweidian == -1) ? 0 : MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.qinglongxiuweidian);
        }
        else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 2) {
            g.drawString("白虎", 180, this.type ? 60 : 58);
            this.lvl = ((MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.baihulvl == -1) ? 0 : MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.baihulvl);
            this.xiuweidian = ((MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.baihuxiuweidian == -1) ? 0 : MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.baihuxiuweidian);
        }
        else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 3) {
            g.drawString("玄武", 180, this.type ? 60 : 58);
            this.lvl = ((MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.xuanwulvl == -1) ? 0 : MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.xuanwulvl);
            this.xiuweidian = ((MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.xuanwuxiuweidian == -1) ? 0 : MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.xuanwuxiuweidian);
        }
        else if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().getXuanzhong() == 4) {
            g.drawString("朱雀", 180, this.type ? 60 : 58);
            this.lvl = ((MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.zhuquelvl == -1) ? 0 : MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.zhuquelvl);
            this.xiuweidian = ((MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.zhuquexiuweidian == -1) ? 0 : MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouHu.zhuquexiuweidian);
        }
        g.drawString(this.lvl + "", 190, 85);
        double b = (double)this.xiuweidian / ((double)(this.lvl + 1) * 6.0);
        g.drawImage(this.icon1.getImage(), 139, 97, (int)(130.0 * b), 17, null);
        g.drawString(this.xiuweidian + "/" + (this.lvl + 1) * 6, 190, 110);
    }
    
    public MountShouhuBtn getTisheng() {
        return this.tisheng;
    }
    
    public void setTisheng(MountShouhuBtn tisheng) {
        this.tisheng = tisheng;
    }
    
    public int getLvl() {
        return this.lvl;
    }
    
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    
    public int getXiuweidian() {
        return this.xiuweidian;
    }
    
    public void setXiuweidian(int xiuweidian) {
        this.xiuweidian = xiuweidian;
    }
    
    public int getTishengdianshu() {
        return this.tishengdianshu;
    }
    
    public void setTishengdianshu(int tishengdianshu) {
        this.tishengdianshu = tishengdianshu;
    }
    
    public MountShouhuBtn getUp() {
        return this.up;
    }
    
    public void setUp(MountShouhuBtn up) {
        this.up = up;
    }
    
    public MountShouhuBtn getDown() {
        return this.down;
    }
    
    public void setDown(MountShouhuBtn down) {
        this.down = down;
    }
    
    public MountShouhuBtn getYijiantisheng() {
        return this.yijiantisheng;
    }
    
    public void setYijiantisheng(MountShouhuBtn yijiantisheng) {
        this.yijiantisheng = yijiantisheng;
    }
    
    public MountShouhuBtn getTishengxiuweidian() {
        return this.tishengxiuweidian;
    }
    
    public void setTishengxiuweidian(MountShouhuBtn tishengxiuweidian) {
        this.tishengxiuweidian = tishengxiuweidian;
    }
    
    public MountShouhuBtn getTupo() {
        return this.tupo;
    }
    
    public void setTupo(MountShouhuBtn tupo) {
        this.tupo = tupo;
    }
    
    public long getDangqianyuanjing() {
        return this.dangqianyuanjing;
    }
    
    public void setDangqianyuanjing(int dangqianyuanjing) {
        this.dangqianyuanjing = (long)dangqianyuanjing;
    }
    
    public long getSuoxuyuanying() {
        return this.suoxuyuanying;
    }
    
    public void setSuoxuyuanying(int suoxuyuanying) {
        this.suoxuyuanying = (long)suoxuyuanying;
    }
    
    public boolean isType() {
        return this.type;
    }
    
    public void setType(boolean type) {
        this.type = type;
    }
}
