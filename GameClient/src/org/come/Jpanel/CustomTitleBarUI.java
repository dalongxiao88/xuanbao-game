package org.come.Jpanel;

import org.come.control.TestMain;
import com.tool.image.ImageMixDeal;
import org.come.socket.GameClient;
import org.apache.commons.lang.StringUtils;
import java.awt.AlphaComposite;
import java.awt.geom.RoundRectangle2D;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.util.Iterator;
import com.tool.tab.TabJFrame;
import org.come.bean.LoginResult;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics;
import java.util.concurrent.atomic.AtomicInteger;
import com.tool.tab.Main;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import org.come.until.CutButtonImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Image;
import java.util.List;
import com.tool.btn.TitlelBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomTitleBarUI extends JPanel
{
    private JLabel titleLabel;
    private TitlelBtn b1;
    private TitlelBtn b2;
    private TitlelBtn c1;
    private TitlelBtn c2;
    private TitlelBtn c3;
    private TitlelBtn c4;
    private List<TitlelBtn> titlelBtns;
    private String title;
    private Image myimage;
    private Integer index;
    private JFrame jFrame;
    private int first_x;
    private int first_y;
    public ImageIcon image;
    private JLabel jd;
    private int jdNum;
    public static Boolean isShow;
    
    public CustomTitleBarUI(JFrame frame) {
        this.index = Integer.valueOf(0);
        this.image = CutButtonImage.getImage("Client/神通天演册/40×40/仙/dlsc.png", 1016, 705);//多开登录界面
        this.jdNum = 0;
        this.jFrame = frame;
        this.titlelBtns = new ArrayList<>();
        this.myimage = CutButtonImage.getImage("img/icon/ico.png", 16, 16).getImage();
        this.setPreferredSize(new Dimension(frame.getWidth(), 768));
        this.setOpaque(true);
        this.setForeground(Color.red);
        (this.titleLabel = new JLabel(frame.getTitle())).setOpaque(false);
        this.add(this.titleLabel);
       // this.setCursor(new Cursor(0));
        (this.b1 = new TitlelBtn("inkImg/background/titleBtn1.png", 1, "", 98)).setBounds(5, 33, 12, 16);
        this.add(this.b1);
        (this.c1 = new TitlelBtn("inkImg/background/title-3.png", 1, "", 99, this.jFrame)).setBounds(5, 33, 17, 17);
        this.add(this.c1);
        (this.c2 = new TitlelBtn("inkImg/background/title-4.png", 1, "", 97)).setBounds(5, 33, 17, 17);
        this.add(this.c2);
        (this.c3 = new TitlelBtn("inkImg/background/title-5.png", 1, "", 96)).setBounds(5, 33, 17, 17);
        this.add(this.c3);
        (this.c4 = new TitlelBtn("inkImg/background/title-6.png", 1, "", 95)).setBounds(5, 33, 17, 17);
        this.add(this.c4);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                CustomTitleBarUI.this.first_x = e.getX();
                CustomTitleBarUI.this.first_y = e.getY();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX() - CustomTitleBarUI.this.first_x;
                int y = e.getY() - CustomTitleBarUI.this.first_y;
                frame.setBounds(x + frame.getX(), y + frame.getY(), frame.getWidth(), frame.getHeight());
                Main.lastX = -1;
                Main.lastY = -1;
            }
        });
        (this.jd = new JLabel()).setIcon(new ImageIcon("resource/NewRoleUi/jd.png1"));
        this.jd.setBounds(260, 675, 0, 50);
        this.jd.setOpaque(false);
        this.add(this.jd);
    }
    
    public TitlelBtn refreshRoles() {
        TabJFrame jFrame = Main.tabJFrame;
        for (TitlelBtn titlelBtn : this.titlelBtns) {
            this.remove(titlelBtn.getDelete());
            this.remove(titlelBtn);
        }
        this.titlelBtns.clear();
        int[] j = { 0 };
        AtomicInteger i = new AtomicInteger();
        jFrame.games.forEach((k, v)/* java.lang.Integer,java.lang.Process, */ -> {
            TitlelBtn delBtn = new TitlelBtn("inkImg/background/title-7.png", 1, "", 100 + (int)k);
            TitlelBtn titlelBtn2 = new TitlelBtn("inkImg/background/titleBtn2.png", 1, "", (int)k, Boolean.valueOf(true), delBtn) {
                ImageIcon head;
                
                {
                    this.head = null;
                }
                
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setFont(UIUtils.TEXT_FONT);
                    LoginResult loginResult = (LoginResult)Main.tabJFrame.roles.get(Integer.valueOf(this.getCaozuo()));
                    if (loginResult != null) {
                        if (this.head == null) {
                            this.head = CutButtonImage.getImage("img/head/s" + loginResult.getSpecies_id().toString() + ".png", 18, 18);
                        }
                        if (this.head != null) {
                            g.drawImage(this.head.getImage(), 5, 3, this);
                        }
                        g.setColor(Color.BLACK);
                        g.drawString(loginResult.getRolename(), 30, 16);
                        if (Main.tabJFrame.getShouId() == (int)k) {
                            CustomTitleBarUI.this.title = "大话西游2经典版 $Revision:1600153 - " + loginResult.getBT() + " - " + loginResult.getRolename() + " (ID: " + loginResult.getRole_id().toString() + ")";
                        }
                    }
                    else {
                        g.drawImage(CustomTitleBarUI.this.myimage, 5, 3, this);
                        g.setColor(Color.BLACK);
                        g.drawString("大话西游2经典版", 30, 16);
                        CustomTitleBarUI.this.title = "大话西游2经典版 ";
                    }
                }
            };
            titlelBtn2.setDelete(delBtn);
            titlelBtn2.getDelete().setBounds(175 + j[0] * 275, 23, 13, 39);
            if (j[0] == (int)this.index) {
                titlelBtn2.btnchange(1);
            }
            else {
                titlelBtn2.setBounds(20 + j[0] * 175, 35, 172, 23);
            }
            this.add(titlelBtn2.getDelete());
            this.add(titlelBtn2);
            this.titlelBtns.add(titlelBtn2);
            ++j[0];
            this.c1.setBounds(20 + this.titlelBtns.size() * 175, 33, 17, 17);
            i.addAndGet(1);
            return;
        });
        this.showBtnPath();
        return (this.titlelBtns.size() == 0) ? null : ((TitlelBtn)this.titlelBtns.get(this.titlelBtns.size() - 1));
    }
    
    public void changeSelect(int index) {
        for (int i = 0; i < this.titlelBtns.size(); ++i) {
            if (((TitlelBtn)this.titlelBtns.get(i)).getCaozuo() == index) {
                ((TitlelBtn)this.titlelBtns.get(i)).btnchange(1);
                ((TitlelBtn)this.titlelBtns.get(i)).setGl(Boolean.valueOf(true));
                ((TitlelBtn)this.titlelBtns.get(i)).setBounds(20 + i * 175, 30, 172, 23);
            }
            else {
                ((TitlelBtn)this.titlelBtns.get(i)).setBounds(20 + i * 175, 31, 172, 23);
                ((TitlelBtn)this.titlelBtns.get(i)).btnchange(0);
                ((TitlelBtn)this.titlelBtns.get(i)).setGl(Boolean.valueOf(false));
            }
            ((TitlelBtn)this.titlelBtns.get(i)).validate();
            ((TitlelBtn)this.titlelBtns.get(i)).getDelete().setBounds(20 + i * 175, 31, 17, 17);
        }
        this.repaint();
    }
    
    public void showBtnPath() {
        try {
            this.b1.setBounds(5, 33, 12, 16);
            this.c2.setBounds(this.getWidth() - 25, 7, 17, 17);
            this.c3.setBounds(this.getWidth() - 45, 5, 17, 17);
            this.c4.setBounds(this.getWidth() - 65, 3, 17, 17);
            this.c1.setBounds(20 + this.titlelBtns.size() * 175, 33, 17, 17);
            for (int i = 0; i < this.titlelBtns.size(); ++i) {
                if (i == (int)this.index) {
                    ((TitlelBtn)this.titlelBtns.get(i)).btnchange(1);
                    ((TitlelBtn)this.titlelBtns.get(i)).setBounds(20 + i * 175, 30, 172, 23);
                }
                else {
                    ((TitlelBtn)this.titlelBtns.get(i)).setBounds(20 + i * 175, 31, 172, 23);
                }
                if (((TitlelBtn)this.titlelBtns.get(i)).getDelete() != null) {
                    ((TitlelBtn)this.titlelBtns.get(i)).getDelete().setBounds(175 + i * 175, 23, 13, 39);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.showBtnPath();
        Graphics2D g2d = (Graphics2D)g;
        g2d.setFont(UIUtils.TEXT_FONT21);
        Color color = new Color(92, 122, 155);
        Color color2 = new Color(113, 154, 194);
        GradientPaint gradient = new GradientPaint(0.0f, 0.0f, color, 0.0f, (float)(this.getHeight() - 1), color2);
        g2d.setPaint(gradient);
        RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0.0f, 0.0f, (float)this.getWidth(), (float)this.getHeight(), 10.0f, 10.0f);
        g2d = (Graphics2D)g;
        g2d.fill(roundedRect);
        g2d.setComposite(AlphaComposite.getInstance(3, 0.03f));
        g2d.fill(roundedRect);
        g2d.fill(roundedRect);
        g2d.setComposite(AlphaComposite.getInstance(3, 0.8f));
        g2d.setColor(color);
        g2d.fillRect(0, 40, this.getWidth(), 35);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(6, 52, this.getWidth() - 12, 1);
        g2d.setColor(color);
        roundedRect = new RoundRectangle2D.Float(0.0f, 53.0f, (float)this.getWidth(), 15.0f, 0.0f, 0.0f);
        g2d.fill(roundedRect);
        g2d.setColor(new Color(238, 238, 238));
        roundedRect = new RoundRectangle2D.Float(5.0f, 53.0f, (float)(this.getWidth() - 10), 20.0f, 5.0f, 5.0f);
        g2d.fill(roundedRect);
        g2d.setComposite(AlphaComposite.getInstance(3, 1.0f));
        g2d.setColor(Color.BLACK);
        if (StringUtils.isNotBlank(this.title)) {
            g2d.setFont(UIUtils.TEXT_FONT);
            g2d.drawString(this.title, 30, 20);
        }
        // 在title-6.png左边20像素处绘制官网链接文字
//        if (c4 != null) {
//            g2d.setFont(UIUtils.TEXT_FONT);
//            int textX = c4.getX() - 120;
//            int textY = 20;
//
//            // 添加凸陷感（绘制阴影，左上角）
//            g2d.setColor(Color.GRAY);
//            g2d.drawString("官网:xy2.163.com", textX - 1, textY - 1);
//            // 绘制主要文字
//            g2d.setColor(Color.BLACK);
//            g2d.drawString("官网:xy2.163.com", textX, textY);
//
//            // 仅为"xy2.163.com"添加下划线
//            FontMetrics fm = g2d.getFontMetrics();
//            int underlineStartX = textX + fm.stringWidth("官网:");
//            int underlineY = textY + 2;
//            int underlineWidth = fm.stringWidth("xy2.163.com");
//            g2d.drawLine(underlineStartX, underlineY, underlineStartX + underlineWidth, underlineY);
//        }
        g2d.drawImage(this.myimage, 5, 7, null);
        if ((boolean)CustomTitleBarUI.isShow && this.getHeight() != 662) {
            g.drawImage(this.image.getImage(), 5, 60, null);
        }
    }
    
    public void updateTitle(String newTitle) {
        this.titleLabel.setText(newTitle);
    }
    
    public void initJD() {
        this.jd.setVisible(true);
        this.jdNum = 0;
        this.jd.setBounds(260, 675, 0, 50);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Title Bar UI Demo");
        frame.setDefaultCloseOperation(3);
        frame.setSize(400, 300);
        CustomTitleBarUI customTitleBarUI = new CustomTitleBarUI(frame);
        customTitleBarUI.setOpaque(false);
        frame.add(customTitleBarUI, "North");
        frame.setVisible(true);
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setTitle() {
        this.setTitle("大话西游2经典版 $Revision:1600153 - " + GameClient.BT + " - " + ImageMixDeal.userimg.getRoleShow().getRolename() + " (ID: " + ImageMixDeal.userimg.getRoleShow().getRole_id() + ")");
        TestMain.gameJframe.setTitle("大话西游2经典版 $Revision:1600153 - " + GameClient.BT + " - " + ImageMixDeal.userimg.getRoleShow().getRolename() + " (ID: " + ImageMixDeal.userimg.getRoleShow().getRole_id() + ")");
    }
    
    public Integer getIndex() {
        return this.index;
    }
    
    public void setIndex(Integer index) {
        this.index = index;
    }
    
    public void removeMenus(Integer i) {
        TitlelBtn titlelBtn = (TitlelBtn)this.titlelBtns.get((int)i);
        this.remove(titlelBtn.getDelete());
        this.remove(titlelBtn);
        this.showBtnPath();
    }
    
    public TitlelBtn getC1() {
        return this.c1;
    }
    
    public void setC1(TitlelBtn c1) {
        this.c1 = c1;
    }
    
    public List<TitlelBtn> getTitlelBtns() {
        return this.titlelBtns;
    }
    
    public void setTitlelBtns(List<TitlelBtn> titlelBtns) {
        this.titlelBtns = titlelBtns;
    }
    
    static {
        CustomTitleBarUI.isShow = Boolean.valueOf(true);
    }
}
