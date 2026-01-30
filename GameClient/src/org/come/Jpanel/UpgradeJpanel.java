package org.come.Jpanel;

import org.come.until.GoodsListFromServerUntil;
import org.come.until.Util;
import java.awt.Graphics;
import org.come.mouslisten.StorageJadeMouslisten;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.entity.Goodstable;
import org.come.bean.JadeorGoodstableBean;
import com.tool.btn.SwitchPageBtn;
import java.math.BigDecimal;
import org.come.mouslisten.RoleSuitMouslisten;
import javax.swing.JLabel;
import com.tool.btn.WorkshopBtn;
import javax.swing.JPanel;

public class UpgradeJpanel extends JPanel
{
    private WorkshopBtn workshopBtn;
    public static JLabel[] GoodsListLabel;
    private static RoleSuitMouslisten[] suitMouslisten;
    private static JLabel labtz1;
    private static JLabel labtz2;
    private static JLabel labyf;
    private static BigDecimal money;
    private SwitchPageBtn leftpageBtn;
    private SwitchPageBtn rightpageBtn;
    private static JadeorGoodstableBean goodstableBean;
    private static Goodstable goodstable;
    public static int page;
    private ImageIcon icon;
    
    public UpgradeJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(490, 428));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (UpgradeJpanel.labtz1 = new JLabel()).setBounds(239, 83, 54, 51);
            UpgradeJpanel.labtz1.addMouseListener(new MouseListener() {
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
                    Goodstable goodstable = UpgradeJpanel.goodstableBean.getGoodstable();
                    if (goodstable == null) {
                        return;
                    }
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(UpgradeJpanel.labtz1);
            (UpgradeJpanel.labyf = new JLabel()).setBounds(360, 83, 54, 51);
            UpgradeJpanel.labyf.addMouseListener(new MouseListener() {
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
                    int index = UpgradeJpanel.goodstableBean.getType() - 1;
                    if (UpgradeJpanel.goodstableBean.getPartJade() != null && index >= 0 && index < 5) {
                        StorageJadeMouslisten.showMsg(index);
                    }
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(UpgradeJpanel.labyf);
            (UpgradeJpanel.labtz2 = new JLabel()).setBounds(299, 183, 54, 51);
            UpgradeJpanel.labtz2.addMouseListener(new MouseListener() {
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
                    Goodstable goodstable = UpgradeJpanel.getGoodstable();
                    if (goodstable == null) {
                        return;
                    }
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(UpgradeJpanel.labtz2);
            (this.leftpageBtn = new SwitchPageBtn("inkImg/button/10.png", 1, 5)).setBounds(120, 397, 18, 18);
            this.add(this.leftpageBtn);
            (this.rightpageBtn = new SwitchPageBtn("inkImg/button/9.png", 1, 6)).setBounds(145, 397, 18, 18);
            this.add(this.rightpageBtn);
            for (int i = 0; i < 21; ++i) {
                UpgradeJpanel.GoodsListLabel[i] = new JLabel();
                int row = i % 3;
                int col = i / 3;
                UpgradeJpanel.GoodsListLabel[i].setBounds(15 + row * 51, 37 + col * 51, 49, 49);
                UpgradeJpanel.suitMouslisten[i] = new RoleSuitMouslisten(i, null, this);
                UpgradeJpanel.GoodsListLabel[i].addMouseListener(UpgradeJpanel.suitMouslisten[i]);
                this.add(UpgradeJpanel.GoodsListLabel[i]);
            }
            (this.workshopBtn = new WorkshopBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "升级", 4)).setBounds(288, 342, 59, 24);
            this.add(this.workshopBtn);
        }
        else {
            this.setPreferredSize(new Dimension(490, 428));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            (UpgradeJpanel.labtz1 = new JLabel()).setBounds(239, 83, 54, 51);
            UpgradeJpanel.labtz1.addMouseListener(new MouseListener() {
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
                    Goodstable goodstable = UpgradeJpanel.goodstableBean.getGoodstable();
                    if (goodstable == null) {
                        return;
                    }
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(UpgradeJpanel.labtz1);
            (UpgradeJpanel.labyf = new JLabel()).setBounds(360, 83, 54, 51);
            UpgradeJpanel.labyf.addMouseListener(new MouseListener() {
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
                    int index = UpgradeJpanel.goodstableBean.getType() - 1;
                    if (UpgradeJpanel.goodstableBean.getPartJade() != null && index >= 0 && index < 5) {
                        StorageJadeMouslisten.showMsg(index);
                    }
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(UpgradeJpanel.labyf);
            (UpgradeJpanel.labtz2 = new JLabel()).setBounds(299, 183, 54, 51);
            UpgradeJpanel.labtz2.addMouseListener(new MouseListener() {
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
                    Goodstable goodstable = UpgradeJpanel.getGoodstable();
                    if (goodstable == null) {
                        return;
                    }
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(UpgradeJpanel.labtz2);
            (this.leftpageBtn = new SwitchPageBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 5)).setBounds(120, 397, 18, 18);
            this.add(this.leftpageBtn);
            (this.rightpageBtn = new SwitchPageBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 6)).setBounds(145, 397, 18, 18);
            this.add(this.rightpageBtn);
            for (int i = 0; i < 21; ++i) {
                UpgradeJpanel.GoodsListLabel[i] = new JLabel();
                int row = i % 3;
                int col = i / 3;
                UpgradeJpanel.GoodsListLabel[i].setBounds(15 + row * 51, 37 + col * 51, 49, 49);
                UpgradeJpanel.suitMouslisten[i] = new RoleSuitMouslisten(i, null, this);
                UpgradeJpanel.GoodsListLabel[i].addMouseListener(UpgradeJpanel.suitMouslisten[i]);
                this.add(UpgradeJpanel.GoodsListLabel[i]);
            }
            (this.workshopBtn = new WorkshopBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "升级", 4)).setBounds(288, 342, 60, 26);
            this.add(this.workshopBtn);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B244.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 490, 428, this);
            Util.drawMoney(g, 313, 312);
            if (UpgradeJpanel.money != null) {
                Util.drawPrice(g, UpgradeJpanel.money, 313, 286);
            }
            GoodsListFromServerUntil.drawRoleSuit(g, 15, 37, UpgradeJpanel.page, 21);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/setsynthesis_tzsj.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 490, 428, this);
            Util.drawMoney(g, 313, 312);
            if (UpgradeJpanel.money != null) {
                Util.drawPrice(g, UpgradeJpanel.money, 313, 286);
            }
            GoodsListFromServerUntil.drawRoleSuit(g, 15, 37, UpgradeJpanel.page, 21);
        }
    }
    
    public static void clearInterface() {
        UpgradeJpanel.money = null;
        UpgradeJpanel.goodstableBean.setGoodstable(null);
        UpgradeJpanel.goodstableBean.setPartJade(null);
        UpgradeJpanel.goodstableBean.setType(0);
        UpgradeJpanel.labtz1.setIcon(null);
        UpgradeJpanel.labyf.setIcon(null);
    }
    
    public static JLabel getLabtz1() {
        return UpgradeJpanel.labtz1;
    }
    
    public static void setLabtz1(JLabel labtz1) {
        UpgradeJpanel.labtz1 = labtz1;
    }
    
    public static JLabel getLabtz2() {
        return UpgradeJpanel.labtz2;
    }
    
    public static void setLabtz2(JLabel labtz2) {
        UpgradeJpanel.labtz2 = labtz2;
    }
    
    public static JLabel getLabyf() {
        return UpgradeJpanel.labyf;
    }
    
    public static void setLabyf(JLabel labyf) {
        UpgradeJpanel.labyf = labyf;
    }
    
    public static RoleSuitMouslisten[] getSuitMouslisten() {
        return UpgradeJpanel.suitMouslisten;
    }
    
    public static void setSuitMouslisten(RoleSuitMouslisten[] suitMouslisten) {
        UpgradeJpanel.suitMouslisten = suitMouslisten;
    }
    
    public static BigDecimal getMoney() {
        return UpgradeJpanel.money;
    }
    
    public static void setMoney(BigDecimal money) {
        UpgradeJpanel.money = money;
    }
    
    public static JadeorGoodstableBean getGoodstableBean() {
        return UpgradeJpanel.goodstableBean;
    }
    
    public static void setGoodstableBean(JadeorGoodstableBean goodstableBean) {
        UpgradeJpanel.goodstableBean = goodstableBean;
    }
    
    public static Goodstable getGoodstable() {
        return UpgradeJpanel.goodstable;
    }
    
    public static void setGoodstable(Goodstable goodstable) {
        UpgradeJpanel.goodstable = goodstable;
    }
    
    static {
        UpgradeJpanel.GoodsListLabel = new JLabel[21];
        UpgradeJpanel.suitMouslisten = new RoleSuitMouslisten[21];
        UpgradeJpanel.goodstableBean = new JadeorGoodstableBean();
        UpgradeJpanel.goodstable = new Goodstable();
        UpgradeJpanel.page = 0;
    }
}
