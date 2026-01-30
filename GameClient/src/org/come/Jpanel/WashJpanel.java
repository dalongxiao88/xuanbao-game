package org.come.Jpanel;

import org.come.until.GoodsListFromServerUntil;
import java.awt.Graphics;
import java.awt.Color;
import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.bean.JadeorGoodstableBean;
import com.tool.btn.SwitchPageBtn;
import org.come.mouslisten.RoleSuitMouslisten;
import javax.swing.JLabel;
import com.tool.btn.WorkshopBtn;
import javax.swing.JPanel;

public class WashJpanel extends JPanel
{
    private WorkshopBtn workshopBtn;
    public static JLabel[] GoodsListLabel;
    private static RoleSuitMouslisten[] suitMouslisten;
    private static JLabel labSet;
    private static JLabel[] labsx;
    private SwitchPageBtn leftpageBtn;
    private SwitchPageBtn rightpageBtn;
    public static int page;
    private static JadeorGoodstableBean goodstableBean;
    private ImageIcon icon;
    
    public WashJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(490, 428));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (WashJpanel.labSet = new JLabel("", 0)).setBounds(300, 103, 54, 51);
            WashJpanel.labSet.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    ZhuFrame.getZhuJpanel().cleargoodtext();
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    Goodstable goodstable = WashJpanel.goodstableBean.getGoodstable();
                    if (goodstable == null) {
                        return;
                    }
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(WashJpanel.labSet);
            (this.leftpageBtn = new SwitchPageBtn("inkImg/button/10.png", 1, 3)).setBounds(120, 397, 18, 18);
            this.add(this.leftpageBtn);
            (this.rightpageBtn = new SwitchPageBtn("inkImg/button/9.png", 1, 4)).setBounds(145, 397, 18, 18);
            this.add(this.rightpageBtn);
            for (int i = 0; i < 4; ++i) {
                (WashJpanel.labsx[i] = new JLabel("", 0)).setForeground(Color.white);
                WashJpanel.labsx[i].setFont(UIUtils.TEXT_FONT1);
                if (i % 2 == 0) {
                    WashJpanel.labsx[i].setBounds(234, 201 + i / 2 * 27, 129, 21);
                }
                else {
                    WashJpanel.labsx[i].setBounds(369, 201 + i / 2 * 27, 50, 21);
                }
                this.add(WashJpanel.labsx[i]);
            }
            for (int i = 0; i < 21; ++i) {
                WashJpanel.GoodsListLabel[i] = new JLabel();
                int row = i % 3;
                int col = i / 3;
                WashJpanel.GoodsListLabel[i].setBounds(15 + row * 51, 37 + col * 51, 49, 49);
                WashJpanel.suitMouslisten[i] = new RoleSuitMouslisten(i, this, null);
                WashJpanel.GoodsListLabel[i].addMouseListener(WashJpanel.suitMouslisten[i]);
                this.add(WashJpanel.GoodsListLabel[i]);
            }
            (this.workshopBtn = new WorkshopBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "洗炼", 3)).setBounds(288, 315, 59, 24);
            this.add(this.workshopBtn);
        }
        else {
            this.setPreferredSize(new Dimension(490, 428));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (WashJpanel.labSet = new JLabel("", 0)).setBounds(300, 103, 54, 51);
            WashJpanel.labSet.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    ZhuFrame.getZhuJpanel().cleargoodtext();
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    Goodstable goodstable = WashJpanel.goodstableBean.getGoodstable();
                    if (goodstable == null) {
                        return;
                    }
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(WashJpanel.labSet);
            (this.leftpageBtn = new SwitchPageBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 3)).setBounds(120, 397, 18, 18);
            this.add(this.leftpageBtn);
            (this.rightpageBtn = new SwitchPageBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 4)).setBounds(145, 397, 18, 18);
            this.add(this.rightpageBtn);
            for (int i = 0; i < 4; ++i) {
                (WashJpanel.labsx[i] = new JLabel("", 0)).setForeground(Color.white);
                WashJpanel.labsx[i].setFont(UIUtils.TEXT_FONT1);
                if (i % 2 == 0) {
                    WashJpanel.labsx[i].setBounds(234, 201 + i / 2 * 27, 129, 21);
                }
                else {
                    WashJpanel.labsx[i].setBounds(369, 201 + i / 2 * 27, 50, 21);
                }
                this.add(WashJpanel.labsx[i]);
            }
            for (int i = 0; i < 21; ++i) {
                WashJpanel.GoodsListLabel[i] = new JLabel();
                int row = i % 3;
                int col = i / 3;
                WashJpanel.GoodsListLabel[i].setBounds(15 + row * 51, 37 + col * 51, 49, 49);
                WashJpanel.suitMouslisten[i] = new RoleSuitMouslisten(i, this, null);
                WashJpanel.GoodsListLabel[i].addMouseListener(WashJpanel.suitMouslisten[i]);
                this.add(WashJpanel.GoodsListLabel[i]);
            }
            (this.workshopBtn = new WorkshopBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "洗炼", 3)).setBounds(288, 291, 60, 26);
            this.add(this.workshopBtn);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B243.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 490, 428, this);
            GoodsListFromServerUntil.drawRoleSuit(g, 15, 37, WashJpanel.page, 21);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/setsynthesis_xl.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 490, 428, this);
            GoodsListFromServerUntil.drawRoleSuit(g, 15, 37, WashJpanel.page, 21);
        }
    }
    
    public static void clearInterface() {
        WashJpanel.goodstableBean.setGoodstable(null);
        WashJpanel.labSet.setIcon(null);
        for (int i = 0; i < 4; ++i) {
            WashJpanel.labsx[i].setText("");
        }
    }
    
    public WorkshopBtn getWorkshopBtn() {
        return this.workshopBtn;
    }
    
    public void setWorkshopBtn(WorkshopBtn workshopBtn) {
        this.workshopBtn = workshopBtn;
    }
    
    public static RoleSuitMouslisten[] getSuitMouslisten() {
        return WashJpanel.suitMouslisten;
    }
    
    public static void setSuitMouslisten(RoleSuitMouslisten[] suitMouslisten) {
        WashJpanel.suitMouslisten = suitMouslisten;
    }
    
    public static JLabel getLabSet() {
        return WashJpanel.labSet;
    }
    
    public static void setLabSet(JLabel labSet) {
        WashJpanel.labSet = labSet;
    }
    
    public static JadeorGoodstableBean getGoodstableBean() {
        return WashJpanel.goodstableBean;
    }
    
    public static void setGoodstableBean(JadeorGoodstableBean goodstableBean) {
        WashJpanel.goodstableBean = goodstableBean;
    }
    
    public static JLabel[] getLabsx() {
        return WashJpanel.labsx;
    }
    
    public static void setLabsx(JLabel[] labsx) {
        WashJpanel.labsx = labsx;
    }
    
    static {
        WashJpanel.GoodsListLabel = new JLabel[21];
        WashJpanel.suitMouslisten = new RoleSuitMouslisten[21];
        WashJpanel.labsx = new JLabel[4];
        WashJpanel.page = 0;
        WashJpanel.goodstableBean = new JadeorGoodstableBean();
    }
}
