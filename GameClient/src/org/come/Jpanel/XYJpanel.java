package org.come.Jpanel;

import org.come.until.CutButtonImage;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Graphics;
import org.come.mouslisten.WLLMouslisten;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.btn.XYBtn;
import javax.swing.JPanel;

public class XYJpanel extends JPanel
{
    public static String num;
    public static XYBtn[] xiulian;
    public XYBtn QICHENG;
    public XYBtn CHONGZHI;
    public XYBtn[] xinlu;
    public static String jidian;
    private ImageIcon icon;
    
    public XYJpanel() {
        this.xinlu = new XYBtn[2];
        this.setPreferredSize(new Dimension(901, 548));
        this.setOpaque(false);
        this.setLayout(null);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 124);
        offBtn.setBounds(800, 10, 25, 25);
        this.add(offBtn);
        (XYJpanel.xiulian[0] = new XYBtn("inkImg/background1/S413.png", 1, 0, this)).setBounds(464, 362, 27, 27);
        XYJpanel.xiulian[0].addMouseListener(new WLLMouslisten(400));
        this.add(XYJpanel.xiulian[0]);
        (XYJpanel.xiulian[1] = new XYBtn("inkImg/background1/S413.png", 1, 1, this)).setBounds(397, 332, 27, 27);
        XYJpanel.xiulian[1].addMouseListener(new WLLMouslisten(401));
        this.add(XYJpanel.xiulian[1]);
        (XYJpanel.xiulian[2] = new XYBtn("inkImg/background1/S413.png", 1, 2, this)).setBounds(389, 235, 27, 27);
        XYJpanel.xiulian[2].addMouseListener(new WLLMouslisten(402));
        this.add(XYJpanel.xiulian[2]);
        (XYJpanel.xiulian[3] = new XYBtn("inkImg/background1/S413.png", 1, 3, this)).setBounds(325, 432, 27, 27);
        XYJpanel.xiulian[3].addMouseListener(new WLLMouslisten(403));
        this.add(XYJpanel.xiulian[3]);
        (XYJpanel.xiulian[4] = new XYBtn("inkImg/background1/S413.png", 1, 4, this)).setBounds(239, 339, 27, 27);
        XYJpanel.xiulian[4].addMouseListener(new WLLMouslisten(404));
        this.add(XYJpanel.xiulian[4]);
        (XYJpanel.xiulian[5] = new XYBtn("inkImg/background1/S413.png", 1, 5, this)).setBounds(211, 207, 27, 27);
        XYJpanel.xiulian[5].addMouseListener(new WLLMouslisten(405));
        this.add(XYJpanel.xiulian[5]);
        (XYJpanel.xiulian[6] = new XYBtn("inkImg/background1/S413.png", 1, 6, this)).setBounds(276, 180, 27, 27);
        XYJpanel.xiulian[6].addMouseListener(new WLLMouslisten(406));
        this.add(XYJpanel.xiulian[6]);
        (XYJpanel.xiulian[7] = new XYBtn("inkImg/background1/S413.png", 1, 7, this)).setBounds(233, 123, 27, 27);
        XYJpanel.xiulian[7].addMouseListener(new WLLMouslisten(407));
        this.add(XYJpanel.xiulian[7]);
        (XYJpanel.xiulian[8] = new XYBtn("inkImg/background1/S413.png", 1, 8, this)).setBounds(553, 327, 27, 27);
        XYJpanel.xiulian[8].addMouseListener(new WLLMouslisten(408));
        this.add(XYJpanel.xiulian[8]);
        (XYJpanel.xiulian[9] = new XYBtn("inkImg/background1/S413.png", 1, 9, this)).setBounds(548, 233, 27, 27);
        XYJpanel.xiulian[9].addMouseListener(new WLLMouslisten(409));
        this.add(XYJpanel.xiulian[9]);
        (XYJpanel.xiulian[10] = new XYBtn("inkImg/background1/S413.png", 1, 10, this)).setBounds(469, 192, 27, 27);
        XYJpanel.xiulian[10].addMouseListener(new WLLMouslisten(410));
        this.add(XYJpanel.xiulian[10]);
        (XYJpanel.xiulian[11] = new XYBtn("inkImg/background1/S413.png", 1, 11, this)).setBounds(614, 420, 27, 27);
        XYJpanel.xiulian[11].addMouseListener(new WLLMouslisten(411));
        this.add(XYJpanel.xiulian[11]);
        (XYJpanel.xiulian[12] = new XYBtn("inkImg/background1/S413.png", 1, 12, this)).setBounds(698, 337, 27, 27);
        XYJpanel.xiulian[12].addMouseListener(new WLLMouslisten(412));
        this.add(XYJpanel.xiulian[12]);
        (XYJpanel.xiulian[13] = new XYBtn("inkImg/background1/S413.png", 1, 13, this)).setBounds(727, 204, 27, 27);
        XYJpanel.xiulian[13].addMouseListener(new WLLMouslisten(413));
        this.add(XYJpanel.xiulian[13]);
        (XYJpanel.xiulian[14] = new XYBtn("inkImg/background1/S413.png", 1, 14, this)).setBounds(665, 175, 27, 27);
        XYJpanel.xiulian[14].addMouseListener(new WLLMouslisten(414));
        this.add(XYJpanel.xiulian[14]);
        (XYJpanel.xiulian[15] = new XYBtn("inkImg/background1/S413.png", 1, 15, this)).setBounds(701, 110, 27, 27);
        XYJpanel.xiulian[15].addMouseListener(new WLLMouslisten(415));
        this.add(XYJpanel.xiulian[15]);
        for (int i = 1; i <= XYJpanel.xiulian.length - 1; ++i) {
            if (XYJpanel.xiulian[i] != null) {
                XYJpanel.xiulian[i].setBounds(XYJpanel.xiulian[i].getX() - 8, XYJpanel.xiulian[i].getY() - 8, 27, 27);
            }
        }
        (this.QICHENG = new XYBtn("inkImg/background/cxbtn.png", 1, 44, this, "确认")).setBounds(380, 460, 80, 50);
        this.add(this.QICHENG);
        (this.CHONGZHI = new XYBtn("inkImg/background/cxbtn.png", 1, 46, this, "重置")).setBounds(500, 460, 80, 50);
        this.add(this.CHONGZHI);
        (this.xinlu[0] = new XYBtn("inkImg/button1/XYXL2.png", 1, 16, this)).setBounds(833, 104, 30, 68);
        this.add(this.xinlu[0]);
        (this.xinlu[1] = new XYBtn("inkImg/button1/XYXY1.png", 1, 17, this)).setBounds(833, 175, 30, 68);
        this.add(this.xinlu[1]);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background1/S414.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 901, 548, this);
        g.setColor(Color.yellow);
        g.setFont(UIUtils.TEXT_FONT60);
        g.drawString(this.getNum(), 238, 440);
    }
    
    public String getNum() {
        if (XYJpanel.num == null) {
            XYJpanel.num = String.valueOf(0);
        }
        return XYJpanel.num;
    }
    
    public static void CS(int k, String mes) {
        if (mes == null) {
            for (int i = 0; i <= 15; ++i) {
                try {
                    XYJpanel.xiulian[i].setIcons(CutButtonImage.cuts("inkImg/background1/S413.png"));
                    XYJpanel.xiulian[i].setText("");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            XYJpanel.num = String.valueOf(k);
            XYJpanel.jidian = null;
            return;
        }
        else {
            XYJpanel.num = String.valueOf(k);
            String[] mes2 = mes.split("#");
            for (int j = 0; j <= 15; ++j) {
                try {
                    XYJpanel.xiulian[j].setIcons(CutButtonImage.cuts("inkImg/background1/S413.png"));
                    XYJpanel.xiulian[j].setText("");
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            for (int j = 1; j <= mes2.length - 2; ++j) {
                if (!mes2[j].equals("")) {
                    try {
                        XYJpanel.xiulian[Integer.parseInt(mes2[j])].setIcons(CutButtonImage.cuts("inkImg/background1/S412.png"));
                        XYJpanel.xiulian[Integer.parseInt(mes2[j])].setText("true");
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            return;
        }
    }
    
    public void setNum(String num) {
        XYJpanel.num = num;
    }
    
    public static XYBtn[] getXiulian() {
        return XYJpanel.xiulian;
    }
    
    public void setXiulian(XYBtn[] xiulian) {
        XYJpanel.xiulian = xiulian;
    }
    
    public static String getJidian() {
        return XYJpanel.jidian;
    }
    
    public static void setJidian(String jidian) {
        XYJpanel.jidian = jidian;
    }
    
    static {
        XYJpanel.num = "11";
        XYJpanel.xiulian = new XYBtn[17];
        XYJpanel.jidian = null;
    }
}
