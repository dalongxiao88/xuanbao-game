package org.come.Jpanel;

import java.awt.image.ImageObserver;
import org.come.until.flyunit;
import come.tool.Fighting.FightingMixDeal;
import java.awt.Graphics2D;
import java.awt.Point;
import org.come.test.Main;
import org.come.control.TestMain;
import java.awt.MouseInfo;
import com.updateNew.MyIsif;
import java.awt.Color;
import org.come.Frame.ZhuFrame;
import com.tool.tcp.Sprite;
//import javax.swing.ImageIcon;
import javax.swing.*;
import com.tool.image.ImageMixDeal;
import org.come.until.MessagrFlagUntil;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;
import org.come.mouslisten.GoodsMouslisten;
import java.awt.Graphics;
import java.awt.Dimension;
import org.come.until.ScrenceUntil;
import com.tool.tcpimg.UIUtils;
import java.util.ArrayList;
import org.come.bean.PathPoint;
import com.tool.tcpimg.SystemBoos;
import com.tool.tcpimg.SystemBox;
//import javax.swing.JLabel;
import com.tool.tcpimg.NoticeBox;
import com.tool.tcpimg.TipBox;
import java.util.List;
import java.awt.Image;
import org.come.bean.Skill;
import javax.swing.JPanel;

public class GameJpanel extends JPanel
{
    public static Skill skill;
    private static GameJpanel gameJpanel;
    public static Image imagefly;
    public static String XNMSXZ;
    private List<TipBox> SystemPrompt;
    private List<NoticeBox> SystemNotice;
   // private JLabel mouse;
   private JLabel            label;
    private SystemBox systemBox;
    private SystemBoos systemBoos;
    private PathPoint pathPoint;
    static final int DEFAULT_FPS = 60;
    static final int DEFAULT_FPSZ = 120;
    static final int DEFAULT_FPSG = 180;
    static final int DEFAULT_CHA = 1000000;
    static final long fpsTime;
    static final long fpsTimeZ;
    static final long fpsTimeG;
    long now;
    long now2;
    long total;
    public static boolean isAlpha;
    public static float alpha;
    boolean isBox;
    boolean isNotice;
    
    public static GameJpanel getGameJpanel() {
        if (GameJpanel.gameJpanel != null) {
            return GameJpanel.gameJpanel;
        }
        return GameJpanel.gameJpanel = new GameJpanel();
    }
    
    public GameJpanel() {
        this.SystemPrompt = new ArrayList<>();
        this.SystemNotice = new ArrayList<>();
        this.pathPoint = new PathPoint(0, 0);
        this.now = System.nanoTime();
        this.now2 = 0L;
        this.total = 0L;
        this.isBox = false;
        this.isNotice = false;
        this.setBackground(UIUtils.Color_BACK);
        this.setPreferredSize(new Dimension(ScrenceUntil.Screen_x + ScrenceUntil.ChatFram_X, ScrenceUntil.Screen_y));
        this.setLayout(null);
//        this.setBorder(BorderFactory.createLineBorder(Color.RED));//这里是界面边框颜色
        try {
          //  (this.mouse = new JLabel() {
            this.label = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // 铃铛显示位置-公告显示
                    if (GameJpanel.this.isNotice) {
                        int chatY = 100;
                        g.translate(0, chatY);
                        for (int i = GameJpanel.this.SystemNotice.size() - 1; i >= 0; --i) {
                            NoticeBox chatPanel = (NoticeBox)GameJpanel.this.SystemNotice.get(i);
                            if (chatPanel.IsTime()) {
                                g.translate(0, -chatPanel.getHeight());
                                chatY -= chatPanel.getHeight();
                                chatPanel.paint(g);
                            }
                            else {
                                GameJpanel.this.SystemNotice.remove(i);
                            }
                        }
                        g.translate(0, -chatY);
                        if (GameJpanel.this.SystemNotice.size() == 0) {
                            GameJpanel.this.isNotice = false;
                        }
                    }
                    if (GameJpanel.this.isBox) {
                        int chatY = ScrenceUntil.Screen_y / 2;
                        g.translate(0, chatY);
                        for (int i = GameJpanel.this.SystemPrompt.size() - 1; i >= 0; --i) {
                            TipBox chatPanel2 = (TipBox)GameJpanel.this.SystemPrompt.get(i);
                            if (chatPanel2.IsTime()) {
                                g.translate(0, -chatPanel2.getHeight());
                                chatY -= chatPanel2.getHeight();
                                chatPanel2.paint(g);
                            }
                            else {
                                GameJpanel.this.SystemPrompt.remove(i);
                            }
                        }
                        g.translate(0, -chatY);
                        if (GameJpanel.this.SystemPrompt.size() == 0) {
                            GameJpanel.this.isBox = false;
                        }
                    }
                    if (GameJpanel.this.systemBox != null) {
                        GameJpanel.this.systemBox.draw(g);
                    }
                    if (GameJpanel.this.systemBoos != null) {
                        GameJpanel.this.systemBoos.draw(g);
                    }
                    PathPoint point = GameJpanel.this.mousepath();
                    try {
                        if (GoodsMouslisten.replacepath != -1) {
                            ImageIcon icon = GoodsListFromServerUntil.imgpath(GoodsListFromServerUntil.getGoodslist()[GoodsMouslisten.replace].getSkin());
                            g.drawImage(icon.getImage(), point.getX() - 17, point.getY() - 17, 49, 49, this);
                        }
                        if (GameJpanel.skill != null) {
                            ImageIcon image = CutButtonImage.getImage("img/fighting-skill/" + GameJpanel.skill.getSkillid() + ".png", 40, 40);
                            g.drawImage(image.getImage(), point.getX() - 17, point.getY() - 17, 35, 35, this);
                        }
                    }
                    catch (Exception e) {
                        GoodsMouslisten.replacepath = -1;
                    }
          //          Sprite mouse = MessagrFlagUntil.getMouse();
          //          if (mouse != null) {
          //              mouse.updateToTime(ImageMixDeal.userimg.getTime(), 0);
           //             mouse.draw(g, point.getX(), point.getY());
                    Sprite sprite = MessagrFlagUntil.getMouse();
                    if (sprite != null) {
                        sprite.updateToTime(ImageMixDeal.userimg.getTime(), 0);
                        sprite.draw(g, point.getX(), point.getY());
                    }
                }
            };//setBounds(0, 0, ScrenceUntil.Screen_x + ScrenceUntil.ChatFram_X, ScrenceUntil.Screen_y);

           // this.add(this.mouse);
            this.label.setBounds(0, 0, ScrenceUntil.Screen_x + ScrenceUntil.ChatFram_X, ScrenceUntil.Screen_y);
            this.add(this.label);
            this.add(ZhuFrame.getzhuframe());
            this.setBackground(Color.BLACK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public PathPoint mousepath() {
        if (MyIsif.ifs.equals("D")) {
            Point p = MouseInfo.getPointerInfo().getLocation();
            Point p2 = TestMain.gameJframe.getLocation();
            this.pathPoint.setX((int)(p.getX() - p2.getX()) - 4);
            this.pathPoint.setY((int)(p.getY() - p2.getY()) - 24);
            return this.pathPoint;
        }
        Point p = MouseInfo.getPointerInfo().getLocation();
        PathPoint p3 = Main.pathPoint;
        this.pathPoint.setX((int)(p.getX() - (double)p3.getX()) - 4);
        this.pathPoint.setY((int)(p.getY() - (double)p3.getY()));
        return this.pathPoint;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();
        if (FightingMixDeal.State == 0) {
            ImageMixDeal.Drawing(g2d, this.total);
        }
        else {
            ImageMixDeal.userimg.setTime(this.total);
            FightingMixDeal.Drawing(g2d, this.total);
        }
        if (FrameMessageChangeJpanel.chatbox.isDisplay()) {
            Graphics g2 = g.create(0, ScrenceUntil.Screen_y - 45 - FrameMessageChangeJpanel.chatbox.getH(), FrameMessageChangeJpanel.chatbox.getW(), FrameMessageChangeJpanel.chatbox.getH());
            FrameMessageChangeJpanel.chatbox.paint(g2);
            g2.dispose();
        }
        try {
            this.total = GameJpanel.fpsTime - System.nanoTime() + this.now;
            if (GameJpanel.alpha < 1.0f) {
                GameJpanel.alpha += 0.03f;
            }
            if (this.total > 0L) {
                Thread.sleep(this.total / 1000000L);
            }
            else {
                Thread.sleep(1L);
            }
            this.now2 = System.nanoTime();
            this.total = (this.now2 - this.now) / 1000000L;
            this.now = this.now2;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.repaint();
    }
    
   // public JLabel getMouse() {
   //     return this.mouse;
   // }
     public JLabel getLabel() {
         return this.label;
     }

   // public void setMouse(JLabel mouse) {
    //    this.mouse = mouse;
   // }
   public void setLabel(JLabel label) {
       this.label = label;
   }
    public void addPrompt(String text) {
        if (text != null) {
            this.SystemPrompt.add(new TipBox(text));
            this.isBox = true;
        }
    }
    
    public void addNotice(String text) {
        if (text != null) {
            this.SystemNotice.clear();
            this.SystemNotice.add(new NoticeBox(text));
            this.isNotice = true;
        }
    }
    /**
     * 添加滚动字体 8广告滚动   9系统滚动
     */
    public void addBox(String text, int type, String skin) {
        if (this.systemBox == null) {
            this.systemBox = new SystemBox(text, (skin != null) ? skin : "HF1");
        }
        else {
            this.systemBox.addText(text);
        }
    }
    /**
     * 10系统boos消息
     */
    public void addBoos(String text, int type, String skin) {
        if (this.systemBoos == null) {
            this.systemBoos = new SystemBoos(text, (skin != null) ? skin : "三界异象");
        }
        else {
            this.systemBoos.addText(text);
        }
    }
    
    static {
        GameJpanel.imagefly = flyunit.image;
        GameJpanel.XNMSXZ = "1";
        fpsTime = (long)(1000.0 / 60.0 * 1000000.0);
        fpsTimeZ = (long)(1100.0 / 120.0 * 1000000.0);
        fpsTimeG = (long)(1100.0 / 180.0 * 1000000.0);
        GameJpanel.isAlpha = false;
        GameJpanel.alpha = 1.0f;
    }
    
    public static class MyTH extends Thread
    {
        private Graphics g2d;
        private Image offScrenImage;
        private ImageObserver this1;
        
        public MyTH(Graphics2D g2d, Image offScrenImage, GameJpanel gameJpanel) {
            this.g2d = g2d;
            this.offScrenImage = offScrenImage;
            this.this1 = gameJpanel;
        }
        
        @Override
        public void run() {
            this.g2d.drawImage(GameJpanel.imagefly, 40, 40, this.this1);
        }
    }
}
