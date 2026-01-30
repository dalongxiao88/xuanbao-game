package org.come.Jpanel;

import org.come.until.CutButtonImage;
import java.util.Map;
import org.come.bean.ConfigureBean;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.List;
import com.tool.btn.XYBtn;
import javax.swing.JPanel;

public class XYXYDJpanel extends JPanel
{
    public static XYBtn[] xiulian;
    public List<String> tiaojian;
    public XYBtn QICHENG;
    public XYBtn CHONGZHI;
    public static byte[] panduan;
    public static XYBtn chengsheng;
    public XYBtn[] xinlu;
    private ImageIcon icon;
    
    public XYXYDJpanel() {
        this.tiaojian = new ArrayList<>();
        this.xinlu = new XYBtn[2];
        this.setPreferredSize(new Dimension(901, 548));
        this.setOpaque(false);
        this.setLayout(null);
        (XYXYDJpanel.xiulian[0] = new XYBtn("inkImg/background1/S413.png", 1, 30, this)).setBounds(259, 160, 27, 27);
        this.add(XYXYDJpanel.xiulian[0]);
        (XYXYDJpanel.xiulian[1] = new XYBtn("inkImg/background1/S413.png", 1, 31, this)).setBounds(222, 247, 27, 27);
        this.add(XYXYDJpanel.xiulian[1]);
        (XYXYDJpanel.xiulian[2] = new XYBtn("inkImg/background1/S413.png", 1, 32, this)).setBounds(139, 154, 27, 27);
        this.add(XYXYDJpanel.xiulian[2]);
        (XYXYDJpanel.xiulian[3] = new XYBtn("inkImg/background1/S413.png", 1, 33, this)).setBounds(171, 305, 27, 27);
        this.add(XYXYDJpanel.xiulian[3]);
        (XYXYDJpanel.xiulian[4] = new XYBtn("inkImg/background1/S413.png", 1, 34, this)).setBounds(212, 405, 27, 27);
        this.add(XYXYDJpanel.xiulian[4]);
        (XYXYDJpanel.xiulian[5] = new XYBtn("inkImg/background1/S413.png", 1, 35, this)).setBounds(285, 300, 27, 27);
        this.add(XYXYDJpanel.xiulian[5]);
        (XYXYDJpanel.xiulian[6] = new XYBtn("inkImg/background1/S413.png", 1, 36, this)).setBounds(372, 234, 27, 27);
        this.add(XYXYDJpanel.xiulian[6]);
        (XYXYDJpanel.xiulian[7] = new XYBtn("inkImg/background1/S413.png", 1, 37, this)).setBounds(453, 144, 27, 27);
        this.add(XYXYDJpanel.xiulian[7]);
        (XYXYDJpanel.xiulian[8] = new XYBtn("inkImg/background1/S413.png", 1, 38, this)).setBounds(553, 113, 27, 27);
        this.add(XYXYDJpanel.xiulian[8]);
        (XYXYDJpanel.xiulian[9] = new XYBtn("inkImg/background1/S413.png", 1, 39, this)).setBounds(585, 165, 27, 27);
        this.add(XYXYDJpanel.xiulian[9]);
        (XYXYDJpanel.xiulian[10] = new XYBtn("inkImg/background1/S413.png", 1, 40, this)).setBounds(606, 220, 27, 27);
        this.add(XYXYDJpanel.xiulian[10]);
        (XYXYDJpanel.xiulian[11] = new XYBtn("inkImg/background1/S413.png", 1, 41, this)).setBounds(659, 280, 27, 27);
        this.add(XYXYDJpanel.xiulian[11]);
        (XYXYDJpanel.xiulian[12] = new XYBtn("inkImg/background1/S413.png", 1, 42, this)).setBounds(764, 291, 27, 27);
        this.add(XYXYDJpanel.xiulian[12]);
        for (int k = 0; k <= XYXYDJpanel.xiulian.length - 1; ++k) {
            if (k == 10) {
                XYXYDJpanel.xiulian[k].setBounds(XYXYDJpanel.xiulian[k].getX() - 2, XYXYDJpanel.xiulian[k].getY() - 5, 27, 27);
            }
            else if (XYXYDJpanel.xiulian[k] != null) {
                XYXYDJpanel.xiulian[k].setBounds(XYXYDJpanel.xiulian[k].getX() - 7, XYXYDJpanel.xiulian[k].getY() - 7, 27, 27);
            }
        }
        (this.QICHENG = new XYBtn("inkImg/background/cxbtn.png", 1, 43, this, "确认")).setBounds(430, 470, 80, 50);
        this.add(this.QICHENG);
        (this.xinlu[0] = new XYBtn("inkImg/button1/XYXL1.png", 1, 44, this)).setBounds(833, 104, 30, 68);
        this.add(this.xinlu[0]);
        (this.xinlu[1] = new XYBtn("inkImg/button1/XYXY2.png", 1, 45, this)).setBounds(833, 175, 30, 68);
        this.add(this.xinlu[1]);
        (XYXYDJpanel.chengsheng = new XYBtn("inkImg/button1/成圣按钮.png", 1, 444, this, "一念成圣")).setBounds(415, 400, 104, 32);
        XYXYDJpanel.chengsheng.setVisible(false);
        this.add(XYXYDJpanel.chengsheng);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        int skllNum = Integer.parseInt(configure.getZhsjngs());
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background1/S411.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 901, 548, this);
        g.drawRect(387, 365, 200, 95);
        g.setColor(Color.yellow);
        g.setFont(UIUtils.TEXT_FONT2);
        int k = 0;
        for (int p = 0; p <= XYXYDJpanel.panduan.length - 1; ++p) {
            if (XYXYDJpanel.panduan[p] == 1) {
                ++k;
            }
        }
        if (skllNum == 7) {
            switch (k) {
                case 0: {
                    g.drawString("等级大于等于50级", 395, 385);
                    break;
                }
                case 1: {
                    g.drawString("亲密度大于等于10万点", 395, 385);
                    break;
                }
                case 2: {
                    g.drawString("等级大于等于120级", 395, 385);
                    g.drawString("亲密度大于等于20万点", 395, 405);
                    break;
                }
                case 3: {
                    g.drawString("转生次数大于等于2次", 395, 385);
                    g.drawString("等级大于等于100级", 395, 405);
                    g.drawString("亲密度大于等于30万点", 395, 425);
                    break;
                }
                case 4: {
                    g.drawString("转生次数大于等于2次", 395, 385);
                    g.drawString("等级大于等于120级", 395, 405);
                    g.drawString("亲密度大于等于40万点", 395, 425);
                    break;
                }
                case 5: {
                    g.drawString("等级大于等于140级", 395, 385);
                    g.drawString("开启技能格子数大于等于2个", 395, 405);
                    break;
                }
                case 6: {
                    g.drawString("亲密度大于等于50万点", 395, 385);
                    break;
                }
                case 7: {
                    g.drawString("转生次数大于等于3次", 395, 385);
                    g.drawString("等级大于等于140级", 395, 405);
                    g.drawString("高级技能个数大于等于2个", 395, 425);
                    break;
                }
                case 8: {
                    g.drawString("亲密度大于等于100万点", 395, 385);
                    break;
                }
                case 9: {
                    g.drawString("等级大于等于160级", 395, 385);
                    g.drawString("高级技能个数大于等于3个", 395, 405);
                    g.drawString("亲密度大于等于200万点", 395, 425);
                    break;
                }
                case 10: {
                    g.drawString("等级大于等于175级", 395, 385);
                    g.drawString("开启技能格子数大于等于4个", 395, 405);
                    g.drawString("龙之骨数量大于等于1", 395, 425);
                    g.drawString("亲密度大于等于400万点", 395, 445);
                    break;
                }
                case 11: {
                    g.drawString("等级大于等于180级", 395, 385);
                    g.drawString("开启技能格子数大于等于6个", 395, 405);
                    g.drawString("龙之骨数量大于等于3", 395, 425);
                    g.drawString("亲密度大于等于600万点", 395, 445);
                    break;
                }
                case 12: {
                    g.drawString("等级大于等于190级", 395, 385);
                    g.drawString("高级技能个数大于等于4个", 395, 405);
                    g.drawString("终极技能个数大于等于1个", 395, 425);
                    g.drawString("亲密度大于等于1000万点", 395, 445);
                    break;
                }
            }
        }
        else if (skllNum == 5) {
            switch (k) {
                case 0: {
                    g.drawString("等级大于等于50级", 395, 385);
                    break;
                }
                case 1: {
                    g.drawString("亲密度大于等于10万点", 395, 385);
                    break;
                }
                case 2: {
                    g.drawString("等级大于等于120级", 395, 385);
                    g.drawString("亲密度大于等于20万点", 395, 405);
                    break;
                }
                case 3: {
                    g.drawString("转生次数大于等于2次", 395, 385);
                    g.drawString("等级大于等于100级", 395, 405);
                    g.drawString("亲密度大于等于30万点", 395, 425);
                    break;
                }
                case 4: {
                    g.drawString("转生次数大于等于2次", 395, 385);
                    g.drawString("等级大于等于120级", 395, 405);
                    g.drawString("亲密度大于等于40万点", 395, 425);
                    break;
                }
                case 5: {
                    g.drawString("等级大于等于140级", 395, 385);
                    g.drawString("开启技能格子数大于等于2个", 395, 405);
                    break;
                }
                case 6: {
                    g.drawString("亲密度大于等于50万点", 395, 385);
                    break;
                }
                case 7: {
                    g.drawString("转生次数大于等于3次", 395, 385);
                    g.drawString("等级大于等于140级", 395, 405);
                    g.drawString("高级技能个数大于等于1个", 395, 425);
                    break;
                }
                case 8: {
                    g.drawString("亲密度大于等于100万点", 395, 385);
                    break;
                }
                case 9: {
                    g.drawString("等级大于等于160级", 395, 385);
                    g.drawString("高级技能个数大于等于2个", 395, 405);
                    g.drawString("亲密度大于等于200万点", 395, 425);
                    break;
                }
                case 10: {
                    g.drawString("等级大于等于175级", 395, 385);
                    g.drawString("开启技能格子数大于等于3个", 395, 405);
                    g.drawString("龙之骨数量大于等于1", 395, 425);
                    g.drawString("亲密度大于等于400万点", 395, 445);
                    break;
                }
                case 11: {
                    g.drawString("等级大于等于180级", 395, 385);
                    g.drawString("开启技能格子数大于等于5个", 395, 405);
                    g.drawString("龙之骨数量大于等于3", 395, 425);
                    g.drawString("亲密度大于等于600万点", 395, 445);
                    break;
                }
                case 12: {
                    g.drawString("等级大于等于190级", 395, 385);
                    g.drawString("高级技能个数大于等于3个", 395, 405);
                    g.drawString("终极技能个数大于等于1个", 395, 425);
                    g.drawString("亲密度大于等于1000万点", 395, 445);
                    break;
                }
            }
        }
        else {
            switch (k) {
                case 0: {
                    g.drawString("等级大于等于50级", 395, 385);
                    break;
                }
                case 1: {
                    g.drawString("亲密度大于等于10万点", 395, 385);
                    break;
                }
                case 2: {
                    g.drawString("等级大于等于120级", 395, 385);
                    g.drawString("亲密度大于等于20万点", 395, 405);
                    break;
                }
                case 3: {
                    g.drawString("转生次数大于等于2次", 395, 385);
                    g.drawString("等级大于等于100级", 395, 405);
                    g.drawString("亲密度大于等于30万点", 395, 425);
                    break;
                }
                case 4: {
                    g.drawString("转生次数大于等于2次", 395, 385);
                    g.drawString("等级大于等于120级", 395, 405);
                    g.drawString("亲密度大于等于40万点", 395, 425);
                    break;
                }
                case 5: {
                    g.drawString("等级大于等于140级", 395, 385);
                    g.drawString("开启技能格子数大于等于2个", 395, 405);
                    break;
                }
                case 6: {
                    g.drawString("亲密度大于等于50万点", 395, 385);
                    break;
                }
                case 7: {
                    g.drawString("转生次数大于等于3次", 395, 385);
                    g.drawString("等级大于等于140级", 395, 405);
                    g.drawString("高级技能个数大于等于2个", 395, 425);
                    break;
                }
                case 8: {
                    g.drawString("亲密度大于等于100万点", 395, 385);
                    break;
                }
                case 9: {
                    g.drawString("等级大于等于160级", 395, 385);
                    g.drawString("高级技能个数大于等于3个", 395, 405);
                    g.drawString("亲密度大于等于200万点", 395, 425);
                    break;
                }
                case 10: {
                    g.drawString("等级大于等于175级", 395, 385);
                    g.drawString("开启技能格子数大于等于4个", 395, 405);
                    g.drawString("龙之骨数量大于等于1", 395, 425);
                    g.drawString("亲密度大于等于400万点", 395, 445);
                    break;
                }
                case 11: {
                    g.drawString("等级大于等于180级", 395, 385);
                    g.drawString("开启技能格子数大于等于8个", 395, 405);
                    g.drawString("龙之骨数量大于等于3", 395, 425);
                    g.drawString("亲密度大于等于600万点", 395, 445);
                    break;
                }
                case 12: {
                    g.drawString("等级大于等于190级", 395, 385);
                    g.drawString("高级技能个数大于等于4个", 395, 405);
                    g.drawString("终极技能个数大于等于2个", 395, 425);
                    g.drawString("亲密度大于等于1000万点", 395, 445);
                    break;
                }
            }
        }
    }
    
    public static void CS(int k, String mes) {
        if (mes == null) {
            for (int i = 0; i <= getXiulian().length - 1; ++i) {
                if (getXiulian()[i] != null) {
                    try {
                        getXiulian()[i].setIcons(CutButtonImage.cuts("inkImg/background1/S413.png"));
                        XYXYDJpanel.panduan[i] = 0;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            XYXYDJpanel.chengsheng.setVisible(false);
            return;
        }
        else {
            String[] mes2 = mes.split("#");
            int m = k;
            if (mes2.length <= 2) {
                m = Integer.parseInt(mes2[1]);
            }
            for (int j = 1; j <= mes2.length - 2; ++j) {
                if (!mes2[j].equals("")) {
                    try {
                        ++m;
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            int j;
            for (k = (j = m); j <= getXiulian().length - 1; ++j) {
                if (getXiulian()[j] != null) {
                    try {
                        getXiulian()[j].setIcons(CutButtonImage.cuts("inkImg/background1/S413.png"));
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            for (j = 0; j < k; ++j) {
                try {
                    if (getXiulian()[j] != null) {
                        getXiulian()[j].setIcons(CutButtonImage.cuts("inkImg/background1/S412.png"));
                        XYXYDJpanel.panduan[j] = 1;
                    }
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (k == 13) {
                XYXYDJpanel.chengsheng.setVisible(true);
            }
            return;
        }
    }
    
    public static XYBtn[] getXiulian() {
        return XYXYDJpanel.xiulian;
    }
    
    public void setXiulian(XYBtn[] xiulian) {
        XYXYDJpanel.xiulian = xiulian;
    }
    
    public List<String> getTiaojian() {
        return this.tiaojian;
    }
    
    public void setTiaojian(List<String> tiaojian) {
        this.tiaojian = tiaojian;
    }
    
    public XYBtn getQICHENG() {
        return this.QICHENG;
    }
    
    public void setQICHENG(XYBtn QICHENG) {
        this.QICHENG = QICHENG;
    }
    
    public byte[] getPanduan() {
        return XYXYDJpanel.panduan;
    }
    
    public void setPanduan(byte[] panduan) {
        XYXYDJpanel.panduan = panduan;
    }
    
    static {
        XYXYDJpanel.xiulian = new XYBtn[14];
        XYXYDJpanel.panduan = new byte[13];
    }
}
