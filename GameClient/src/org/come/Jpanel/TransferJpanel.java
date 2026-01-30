package org.come.Jpanel;

import com.tool.role.RoleData;
import java.awt.Color;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.Util;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import org.come.entity.Goodstable;
import org.come.bean.JadeorGoodstableBean;
import org.come.mouslisten.TransferEquiMouslisten;
import com.tool.btn.SwitchPageBtn;
import javax.swing.JLabel;
import java.math.BigDecimal;
import com.tool.btn.WorkshopBtn;
import javax.swing.JPanel;

public class TransferJpanel extends JPanel
{
    private static WorkshopBtn workshopBtn;
    private static BigDecimal money;
    private static BigDecimal value;
    private static JLabel labtz;
    private static JLabel labzb;
    private SwitchPageBtn leftpageBtn1;
    private SwitchPageBtn rightpageBtn1;
    private SwitchPageBtn leftpageBtn2;
    private SwitchPageBtn rightpageBtn2;
    public static JLabel[] EquipListJlabel;
    public static TransferEquiMouslisten[] equiMouslistens;
    public static JLabel[] EquipListJlabel2;
    public static TransferEquiMouslisten[] equiMouslistens2;
    private static JadeorGoodstableBean goodstableBean;
    private static Goodstable goodstable;
    public static int page;
    public static int page2;
    private ImageIcon icon;
    
    public TransferJpanel() {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(490, 428));
            this.setLayout(null);
            this.setOpaque(false);
            (this.leftpageBtn1 = new SwitchPageBtn("inkImg/button/10.png", 1, 7)).setBounds(120, 190, 18, 18);
            this.add(this.leftpageBtn1);
            (this.rightpageBtn1 = new SwitchPageBtn("inkImg/button/9.png", 1, 8)).setBounds(145, 190, 18, 18);
            this.add(this.rightpageBtn1);
            (this.leftpageBtn2 = new SwitchPageBtn("inkImg/button/10.png", 1, 9)).setBounds(120, 400, 18, 18);
            this.add(this.leftpageBtn2);
            (this.rightpageBtn2 = new SwitchPageBtn("inkImg/button/9.png", 1, 10)).setBounds(145, 400, 18, 18);
            this.add(this.rightpageBtn2);
            for (int i = 0; i < 9; ++i) {
                TransferJpanel.EquipListJlabel[i] = new JLabel();
                int row = i % 3;
                int col = i / 3;
                TransferJpanel.EquipListJlabel[i].setBounds(15 + row * 51, 38 + col * 51, 49, 49);
                TransferJpanel.equiMouslistens[i] = new TransferEquiMouslisten(i, this, 2);
                TransferJpanel.EquipListJlabel[i].addMouseListener(TransferJpanel.equiMouslistens[i]);
                this.add(TransferJpanel.EquipListJlabel[i]);
                TransferJpanel.EquipListJlabel2[i] = new JLabel();
                int row2 = i % 3;
                int col2 = i / 3;
                TransferJpanel.EquipListJlabel2[i].setBounds(15 + row2 * 51, 247 + col2 * 51, 49, 49);
                TransferJpanel.equiMouslistens2[i] = new TransferEquiMouslisten(i, this, 1);
                TransferJpanel.EquipListJlabel2[i].addMouseListener(TransferJpanel.equiMouslistens2[i]);
                this.add(TransferJpanel.EquipListJlabel2[i]);
            }
            (TransferJpanel.labtz = new JLabel()).setBounds(241, 124, 54, 51);
            TransferJpanel.labtz.addMouseListener(new MouseListener() {
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
                    Goodstable goodstable = TransferJpanel.goodstableBean.getGoodstable();
                    if (goodstable == null) {
                        return;
                    }
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(TransferJpanel.labtz);
            (TransferJpanel.labzb = new JLabel()).setBounds(356, 124, 54, 51);
            TransferJpanel.labzb.addMouseListener(new MouseListener() {
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
                    Goodstable goodstable = TransferJpanel.getGoodstable();
                    if (goodstable == null) {
                        return;
                    }
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(TransferJpanel.labzb);
            (TransferJpanel.workshopBtn = new WorkshopBtn("inkImg/button1/B20.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "拆解", 6)).setBounds(288, 352, 59, 24);
            this.add(TransferJpanel.workshopBtn);
        }
        else {
            this.setPreferredSize(new Dimension(490, 428));
            this.setLayout(null);
            this.setOpaque(false);
            (this.leftpageBtn1 = new SwitchPageBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 7)).setBounds(120, 190, 18, 18);
            this.add(this.leftpageBtn1);
            (this.rightpageBtn1 = new SwitchPageBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 8)).setBounds(145, 190, 18, 18);
            this.add(this.rightpageBtn1);
            (this.leftpageBtn2 = new SwitchPageBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 9)).setBounds(120, 400, 18, 18);
            this.add(this.leftpageBtn2);
            (this.rightpageBtn2 = new SwitchPageBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 10)).setBounds(145, 400, 18, 18);
            this.add(this.rightpageBtn2);
            for (int i = 0; i < 9; ++i) {
                TransferJpanel.EquipListJlabel[i] = new JLabel();
                int row = i % 3;
                int col = i / 3;
                TransferJpanel.EquipListJlabel[i].setBounds(15 + row * 51, 38 + col * 51, 49, 49);
                TransferJpanel.equiMouslistens[i] = new TransferEquiMouslisten(i, this, 2);
                TransferJpanel.EquipListJlabel[i].addMouseListener(TransferJpanel.equiMouslistens[i]);
                this.add(TransferJpanel.EquipListJlabel[i]);
                TransferJpanel.EquipListJlabel2[i] = new JLabel();
                int row2 = i % 3;
                int col2 = i / 3;
                TransferJpanel.EquipListJlabel2[i].setBounds(15 + row2 * 51, 247 + col2 * 51, 49, 49);
                TransferJpanel.equiMouslistens2[i] = new TransferEquiMouslisten(i, this, 1);
                TransferJpanel.EquipListJlabel2[i].addMouseListener(TransferJpanel.equiMouslistens2[i]);
                this.add(TransferJpanel.EquipListJlabel2[i]);
            }
            (TransferJpanel.labtz = new JLabel()).setBounds(241, 124, 54, 51);
            TransferJpanel.labtz.addMouseListener(new MouseListener() {
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
                    Goodstable goodstable = TransferJpanel.goodstableBean.getGoodstable();
                    if (goodstable == null) {
                        return;
                    }
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(TransferJpanel.labtz);
            (TransferJpanel.labzb = new JLabel()).setBounds(356, 124, 54, 51);
            TransferJpanel.labzb.addMouseListener(new MouseListener() {
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
                    Goodstable goodstable = TransferJpanel.getGoodstable();
                    if (goodstable == null) {
                        return;
                    }
                    ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
            this.add(TransferJpanel.labzb);
            (TransferJpanel.workshopBtn = new WorkshopBtn("inkImg/hongmu/w50.png", 1, UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_HY16, "拆解", 6)).setBounds(288, 352, 60, 26);
            this.add(TransferJpanel.workshopBtn);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B246.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 490, 428, this);
            Util.drawMoney(g, 317, 274);
            if (TransferJpanel.money != null) {
                Util.drawPrice(g, TransferJpanel.money, 317, 248);
            }
            GoodsListFromServerUntil.drawRoleSuit(g, 15, 38, TransferJpanel.page, 9);
            GoodsListFromServerUntil.drawIdlEqu(g, 15, 247, TransferJpanel.page2, 9, 3);
            g.setColor(Color.white);
            if (RoleData.getRoleData().getLoginResult().getScoretype("灵修值") != null) {
                g.drawString(RoleData.getRoleData().getLoginResult().getScoretype("灵修值") + "", 317, 326);
            }
            if (TransferJpanel.value != null) {
                g.drawString(TransferJpanel.value + "", 317, 300);
            }
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/setsynthesis_cczy.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 490, 428, this);
            Util.drawMoney(g, 317, 274);
            if (TransferJpanel.money != null) {
                Util.drawPrice(g, TransferJpanel.money, 317, 248);
            }
            GoodsListFromServerUntil.drawRoleSuit(g, 15, 38, TransferJpanel.page, 9);
            GoodsListFromServerUntil.drawIdlEqu(g, 15, 247, TransferJpanel.page2, 9, 3);
            g.setColor(Color.white);
            if (RoleData.getRoleData().getLoginResult().getScoretype("灵修值") != null) {
                g.drawString(RoleData.getRoleData().getLoginResult().getScoretype("灵修值") + "", 317, 326);
            }
            if (TransferJpanel.value != null) {
                g.drawString(TransferJpanel.value + "", 317, 300);
            }
        }
    }
    
    public static void clearInterface() {
        TransferJpanel.money = null;
        TransferJpanel.value = null;
        TransferJpanel.goodstableBean.setGoodstable(null);
        TransferJpanel.goodstable = null;
        TransferJpanel.goodstableBean.setType(0);
        TransferJpanel.labtz.setIcon(null);
        TransferJpanel.labzb.setIcon(null);
        TransferJpanel.workshopBtn.setText("拆解");
    }
    
    public static BigDecimal getMoney() {
        return TransferJpanel.money;
    }
    
    public static void setMoney(BigDecimal money) {
        TransferJpanel.money = money;
    }
    
    public static BigDecimal getValue() {
        return TransferJpanel.value;
    }
    
    public static void setValue(BigDecimal value) {
        TransferJpanel.value = value;
    }
    
    public static JLabel getLabtz() {
        return TransferJpanel.labtz;
    }
    
    public static void setLabtz(JLabel labtz) {
        TransferJpanel.labtz = labtz;
    }
    
    public static JLabel getLabzb() {
        return TransferJpanel.labzb;
    }
    
    public static void setLabzb(JLabel labzb) {
        TransferJpanel.labzb = labzb;
    }
    
    public static JLabel[] getEquipListJlabel() {
        return TransferJpanel.EquipListJlabel;
    }
    
    public static void setEquipListJlabel(JLabel[] equipListJlabel) {
        TransferJpanel.EquipListJlabel = equipListJlabel;
    }
    
    public static JLabel[] getEquipListJlabel2() {
        return TransferJpanel.EquipListJlabel2;
    }
    
    public static void setEquipListJlabel2(JLabel[] equipListJlabel2) {
        TransferJpanel.EquipListJlabel2 = equipListJlabel2;
    }
    
    public static JadeorGoodstableBean getGoodstableBean() {
        return TransferJpanel.goodstableBean;
    }
    
    public static void setGoodstableBean(JadeorGoodstableBean goodstableBean) {
        TransferJpanel.goodstableBean = goodstableBean;
    }
    
    public static Goodstable getGoodstable() {
        return TransferJpanel.goodstable;
    }
    
    public static void setGoodstable(Goodstable goodstable) {
        TransferJpanel.goodstable = goodstable;
    }
    
    public static WorkshopBtn getWorkshopBtn() {
        return TransferJpanel.workshopBtn;
    }
    
    public static void setWorkshopBtn(WorkshopBtn workshopBtn) {
        TransferJpanel.workshopBtn = workshopBtn;
    }
    
    static {
        TransferJpanel.EquipListJlabel = new JLabel[9];
        TransferJpanel.equiMouslistens = new TransferEquiMouslisten[9];
        TransferJpanel.EquipListJlabel2 = new JLabel[9];
        TransferJpanel.equiMouslistens2 = new TransferEquiMouslisten[9];
        TransferJpanel.goodstableBean = new JadeorGoodstableBean();
        TransferJpanel.page = 0;
        TransferJpanel.page2 = 0;
    }
}
