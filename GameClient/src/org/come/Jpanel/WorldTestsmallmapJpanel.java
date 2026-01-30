package org.come.Jpanel;

import java.awt.Stroke;
import org.come.bean.NpcInfoBean;
import org.come.bean.LoginResult;
import java.awt.geom.Line2D;
import java.awt.BasicStroke;
import org.come.bean.PathPoint;
import org.come.until.SplitStringTool;
import org.come.until.UserMessUntil;
import org.come.model.Gamemap;
import java.awt.Graphics2D;
import org.come.good.Flight;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.come.until.GoodsListFromServerUntil;
import com.tool.role.RoleData;
import com.tool.image.ImageMixDeal;
import java.awt.Graphics;
import java.util.Iterator;
import java.io.IOException;
import org.come.until.CutButtonImage;
import org.come.mouslisten.SystemMouslisten;
import com.updateNew.MyIsif;
import com.tool.tcp.SpriteFactory;
import java.util.HashMap;
import com.tool.tcpimg.UIUtils;
import java.util.ArrayList;
import org.come.Frame.WorldTestsmallmapJframe;
import java.awt.Color;
import java.awt.Font;
import org.come.entity.Goodstable;
import java.util.List;
import javax.swing.ImageIcon;
import com.tool.tcp.Sprite;
import javax.swing.JLabel;
import java.util.Map;
import com.tool.btn.FormsOnOffBtn;
import javax.swing.JPanel;

public class WorldTestsmallmapJpanel extends JPanel
{
    private int Dx;
    private int Dy;
    private String xy;
    private FormsOnOffBtn offBtn;
    public static Map<Integer, String> MapName;
    public JLabel[] sp;
    public Sprite tcp;
    public JLabel m1207;
    private long time;
    public static ImageIcon icony;
    private static JLabel labNoCWorld;
    private static JLabel moveWorld;
    private static JLabel synpcWorld;
    private static JLabel rwnpcWorld;
    private static JLabel qbnpxWorld;
    public List pathPoints;
    private static List<JLabel> tps;
    public static List<Goodstable> chaxunss;
    public int pathPointsx;
    public int pathPointsy;
    ImageIcon icon;
    Font f5;
    Color color;
    Color color1;
    Color color2;
    
    public WorldTestsmallmapJpanel(WorldTestsmallmapJframe worldTestsmallmapJframe) throws IOException {
        this.Dx = 0;
        this.Dy = 0;
        this.xy = "";
        this.pathPoints = new ArrayList<>();
        this.icon = new ImageIcon("inkImg/button1/DWTB5.png");
        this.f5 = new Font("TimesRoman", 3, 14);
        this.color = new Color(32, 17, 10);
        this.color1 = new Color(151, 82, 37);
        this.color2 = new Color(103, 15, 8);
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        this.time = 0L;
        WorldTestsmallmapJpanel.icony = new ImageIcon("inkImg/button1/CSD.png");
        WorldTestsmallmapJpanel.MapName = new HashMap<>();
        this.tcp = SpriteFactory.VloadSprite("resource/mouse/ZD.tcp", null);
        if (MyIsif.getStyle().equals("水墨")) {
            this.add(this.offBtn = new FormsOnOffBtn("inkImg/button1/xdtgbaj.png", 1, 1103));
        }
        else {
            this.add(this.offBtn = new FormsOnOffBtn("inkImg/button1/xdtgbajh.png", 1, 1103));
        }
        this.add(this.m1207 = new JLabel());
        (WorldTestsmallmapJpanel.qbnpxWorld = new JLabel()).setOpaque(false);
        WorldTestsmallmapJpanel.qbnpxWorld.setBounds(50, WorldMapJpanel.mapmodel.getMin_y() + 30, 15, 15);
        WorldTestsmallmapJpanel.qbnpxWorld.addMouseListener(new SystemMouslisten(36));
        WorldTestsmallmapJpanel.qbnpxWorld.addMouseListener(new SystemMouslisten(31));
        this.add(WorldTestsmallmapJpanel.qbnpxWorld);
        (WorldTestsmallmapJpanel.moveWorld = new JLabel()).setOpaque(false);
        WorldTestsmallmapJpanel.moveWorld.setBounds(100, WorldMapJpanel.mapmodel.getMin_y() + 30, 15, 15);
        WorldTestsmallmapJpanel.moveWorld.addMouseListener(new SystemMouslisten(33));
        WorldTestsmallmapJpanel.moveWorld.addMouseListener(new SystemMouslisten(28));
        this.add(WorldTestsmallmapJpanel.moveWorld);
        (WorldTestsmallmapJpanel.labNoCWorld = new JLabel()).setOpaque(false);
        WorldTestsmallmapJpanel.labNoCWorld.setBounds(150, WorldMapJpanel.mapmodel.getMin_y() + 30, 15, 15);
        WorldTestsmallmapJpanel.labNoCWorld.addMouseListener(new SystemMouslisten(32));
        WorldTestsmallmapJpanel.labNoCWorld.addMouseListener(new SystemMouslisten(27));
        this.add(WorldTestsmallmapJpanel.labNoCWorld);
        (WorldTestsmallmapJpanel.rwnpcWorld = new JLabel()).setOpaque(false);
        WorldTestsmallmapJpanel.rwnpcWorld.setBounds(200, WorldMapJpanel.mapmodel.getMin_y() + 30, 15, 15);
        WorldTestsmallmapJpanel.rwnpcWorld.addMouseListener(new SystemMouslisten(34));
        WorldTestsmallmapJpanel.rwnpcWorld.addMouseListener(new SystemMouslisten(29));
        this.add(WorldTestsmallmapJpanel.rwnpcWorld);
        (WorldTestsmallmapJpanel.synpcWorld = new JLabel()).setOpaque(false);
        WorldTestsmallmapJpanel.synpcWorld.setBounds(250, WorldMapJpanel.mapmodel.getMin_y() + 30, 15, 15);
        WorldTestsmallmapJpanel.synpcWorld.addMouseListener(new SystemMouslisten(35));
        WorldTestsmallmapJpanel.synpcWorld.addMouseListener(new SystemMouslisten(30));
        this.add(WorldTestsmallmapJpanel.synpcWorld);
        this.sp = new JLabel[5];
        for (int i = 0; i < this.sp.length; ++i) {
            (this.sp[i] = new JLabel()).setOpaque(false);
            if (MyIsif.getStyle().equals("水墨")) {
                this.sp[i].setIcon(CutButtonImage.getImage("inkImg/button1/XDRXZK.png", 18, 18));
            }
            else {
                this.sp[i].setIcon(CutButtonImage.getImage("inkImg/button1/XDRXZKh.png", 18, 18));
            }
            this.add(this.sp[i]);
        }
    }
    
    public void clearTps() {
        for (JLabel tp : WorldTestsmallmapJpanel.tps) {
            this.remove(tp);
        }
        WorldTestsmallmapJpanel.tps.clear();
        WorldTestsmallmapJpanel.chaxunss = null;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        WorldTestsmallmapJpanel.qbnpxWorld.setBounds(22, WorldMapJpanel.mapmodel.getMin_y() + 41, 15, 15);
        WorldTestsmallmapJpanel.moveWorld.setBounds(90, WorldMapJpanel.mapmodel.getMin_y() + 41, 15, 15);
        WorldTestsmallmapJpanel.labNoCWorld.setBounds(158, WorldMapJpanel.mapmodel.getMin_y() + 41, 15, 15);
        WorldTestsmallmapJpanel.rwnpcWorld.setBounds(226, WorldMapJpanel.mapmodel.getMin_y() + 41, 15, 15);
        WorldTestsmallmapJpanel.synpcWorld.setBounds(294, WorldMapJpanel.mapmodel.getMin_y() + 41, 15, 15);
        Long mapid = ImageMixDeal.userimg.getRoleShow().getMapid();
        Long omapid = Long.valueOf((long)WorldMapJpanel.ditubianma);
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        this.time += 12L;
        super.paintComponent(g);
        if (WorldTestsmallmapJpanel.chaxunss == null) {
            WorldTestsmallmapJpanel.chaxunss = GoodsListFromServerUntil.chaxunss(2011L);
            if (WorldTestsmallmapJpanel.chaxunss == null) {
                WorldTestsmallmapJpanel.chaxunss = new ArrayList<>();
            }
            WorldTestsmallmapJpanel.chaxunss.addAll(GoodsListFromServerUntil.chaxunss(2012L));
            for (Goodstable goodstable : WorldTestsmallmapJpanel.chaxunss) {
                String[] split = goodstable.getValue().split("\\|");
                for (int i = 0; i < split.length; ++i) {
                    String[] strings = split[i].split(",");
                    if (strings[0].contains(String.valueOf(WorldMapJpanel.ditubianma))) {
                        JLabel tp = new JLabel();
                        tp.setBounds((int)((double)(Integer.parseInt(strings[2]) / 20) / WorldMapJpanel.mapmodel.getBili_x()) + 3, (int)((double)(Integer.parseInt(strings[3]) / 20) / WorldMapJpanel.mapmodel.getBili_y()) + 18, 16, 21);
                        if ((long)goodstable.getType() == 2011L) {
                            tp.setIcon(CutButtonImage.getImage("inkImg/button1/蓝旗子.png", 16, 21));
                        }
                        else {
                            tp.setIcon(CutButtonImage.getImage("inkImg/button1/黄旗子.png", 16, 21));
                        }
                        tp.addMouseListener(new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                System.out.println("mouseClicked");
                                Flight.Flightchess(goodstable, (long)goodstable.getType());
                            }
                            
                            @Override
                            public void mousePressed(MouseEvent e) {
                                System.out.println("mousePressed");
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
                        WorldTestsmallmapJpanel.tps.add(tp);
                    }
                }
            }
        }
        for (JLabel tp2 : WorldTestsmallmapJpanel.tps) {
            this.add(tp2);
        }
        WorldMapJpanel.mapmodel.getZoom().draw(g);
        this.m1207.setBounds(WorldMapJpanel.mapmodel.getMin_x() + 4, 50, 78, WorldMapJpanel.mapmodel.getMin_y() - 25);
        this.offBtn.setBounds(WorldMapJpanel.mapmodel.getMin_x() + 48, 6, 40, 25);
        if (mapid.equals(omapid)) {
            g.drawImage(this.icon.getImage(), (int)((double)(ImageMixDeal.userimg.getRoleShow().getX() / 20) / WorldMapJpanel.mapmodel.getBili_x()) - 6, (int)((double)(ImageMixDeal.userimg.getRoleShow().getY() / 20) / WorldMapJpanel.mapmodel.getBili_y()) + 15, 20, 25, this);
        }
        g.setColor(Color.RED);
        Graphics2D g2 = (Graphics2D)g;
        g.setFont(this.f5);
        g.drawString(this.xy, this.Dx, this.Dy);
        g.setFont(UIUtils.TEXT_FONT);
        g.setFont(UIUtils.TEXT_FONT);
        Gamemap game = (Gamemap)UserMessUntil.getAllmapbean().getAllMap().get(WorldMapJpanel.ditubianma + "");
        List<String> strings2 = SplitStringTool.splitString(game.getMapnpc());
        g.setColor(Color.white);
        g.drawString("全部", 42, WorldMapJpanel.mapmodel.getMin_y() + 53);
        this.sp[4].setBounds(20, WorldMapJpanel.mapmodel.getMin_y() + 40, 18, 18);
        g.drawString("传送", 110, WorldMapJpanel.mapmodel.getMin_y() + 53);
        this.sp[1].setBounds(88, WorldMapJpanel.mapmodel.getMin_y() + 40, 18, 18);
        g.drawString("普通", 178, WorldMapJpanel.mapmodel.getMin_y() + 53);
        this.sp[0].setBounds(156, WorldMapJpanel.mapmodel.getMin_y() + 40, 18, 18);
        g.drawString("任务", 246, WorldMapJpanel.mapmodel.getMin_y() + 53);
        this.sp[2].setBounds(224, WorldMapJpanel.mapmodel.getMin_y() + 40, 18, 18);
        g.drawString("商业", 314, WorldMapJpanel.mapmodel.getMin_y() + 53);
        this.sp[3].setBounds(292, WorldMapJpanel.mapmodel.getMin_y() + 40, 18, 18);
        for (int j = 0; j < strings2.size(); ++j) {
            NpcInfoBean infoBean = UserMessUntil.getnpc((String)strings2.get(j));
            if (infoBean != null) {
                try {
                    WorldTestsmallmapJpanel.MapName.put(Integer.valueOf(j), infoBean.getNpctable().getNpcname());
                    int x1 = (int)((double)(Integer.parseInt(infoBean.getNpctable().getTx()) / 20) / WorldMapJpanel.mapmodel.getBili_x() + 25.0);
                    int y1 = (int)((double)(Integer.parseInt(infoBean.getNpctable().getTy()) / 20) / WorldMapJpanel.mapmodel.getBili_x() + 25.0);
                    if (WorldTestsmallmapJpanel.labNoCWorld.getIcon() != null && (infoBean.getNpctable().getTypexdt().equals("2") || infoBean.getNpctable().getTypexdt().equals("222") || infoBean.getNpctable().getTypexdt().equals("2222"))) {
                        g.setColor(Color.green);
                        g.drawString((String)WorldTestsmallmapJpanel.MapName.get(Integer.valueOf(j)), x1 - 40, y1 + 10);
                    }
                    else {
                        g.setColor(Color.green);
                        g.drawString("", x1 - 25, y1 + 10);
                    }
                    if (WorldTestsmallmapJpanel.rwnpcWorld.getIcon() != null && infoBean.getNpctable().getTypexdt().equals("3")) {
                        g.setColor(Color.orange);
                        g.drawString((String)WorldTestsmallmapJpanel.MapName.get(Integer.valueOf(j)), x1 - 40, y1 + 10);
                    }
                    else {
                        g.setColor(Color.orange);
                        g.drawString("", x1 - 25, y1 + 10);
                    }
                    if (WorldTestsmallmapJpanel.synpcWorld.getIcon() != null && infoBean.getNpctable().getTypexdt().equals("4")) {
                        g.setColor(Color.cyan);
                        g.drawString((String)WorldTestsmallmapJpanel.MapName.get(Integer.valueOf(j)), x1 - 40, y1 + 10);
                    }
                    else {
                        g.setColor(Color.cyan);
                        g.drawString("", x1 - 25, y1 + 10);
                    }
                    if (WorldTestsmallmapJpanel.moveWorld.getIcon() != null && infoBean.getNpctable().getTypexdt().equals("1")) {
                        g.drawImage(WorldTestsmallmapJpanel.icony.getImage(), x1 - 30, y1 - 5, this);
                    }
                    else {
                        g.drawImage(null, x1 - 25, y1 + 10, this);
                    }
                }
                catch (Exception e) {
                    System.out.println(infoBean.getNpctable().getNpcid());
                }
            }
        }
        g.setFont(this.f5);
        g.setColor(Color.RED);
        if (this.pathPoints.size() > 1) {
            int ditubianma = WorldMapJpanel.ditubianma;
            WorldMapJpanel.iWantToFly(ditubianma + ",1," + ((PathPoint)this.pathPoints.get(1)).getX() + "," + ((PathPoint)this.pathPoints.get(1)).getY());
            this.pathPoints = new ArrayList<>();
            for (int k = 0; k < this.pathPoints.size() - 1; ++k) {
                Stroke dash = new BasicStroke(5.0f, 1, 1, 3.5f, new float[] { 2.5f, 10.0f }, 0.0f);
                g2.setStroke(dash);
                g2.draw(new Line2D.Double((double)(int)((double)(((PathPoint)this.pathPoints.get(k)).getX() / 20) / WorldMapJpanel.mapmodel.getBili_x() + 25.0), (double)(int)((double)(((PathPoint)this.pathPoints.get(k)).getY() / 20) / WorldMapJpanel.mapmodel.getBili_x() + 25.0), (double)(int)((double)(((PathPoint)this.pathPoints.get(k + 1)).getX() / 20) / WorldMapJpanel.mapmodel.getBili_x() + 25.0), (double)(int)((double)(((PathPoint)this.pathPoints.get(k + 1)).getY() / 20) / WorldMapJpanel.mapmodel.getBili_x() + 25.0)));
            }
            g2.dispose();
        }
    }
    
    public List<String> getNpcfunction() {
        return TestsmallmapJpanel.npcfunction;
    }
    
    public int getPathPointsx() {
        return this.pathPointsx;
    }
    
    public void setPathPointsx(int pathPointsx) {
        this.pathPointsx = pathPointsx;
    }
    
    public int getPathPointsy() {
        return this.pathPointsy;
    }
    
    public void setPathPointsy(int pathPointsy) {
        this.pathPointsy = pathPointsy;
    }
    
    public String getXy() {
        return this.xy;
    }
    
    public void setXy(String xy) {
        this.xy = xy;
    }
    
    public int getDx() {
        return this.Dx;
    }
    
    public void setDx(int dx) {
        this.Dx = dx;
    }
    
    public int getDy() {
        return this.Dy;
    }
    
    public void setDy(int dy) {
        this.Dy = dy;
    }
    
    public FormsOnOffBtn getOffBtn() {
        return this.offBtn;
    }
    
    public void setOffBtn(FormsOnOffBtn offBtn) {
        this.offBtn = offBtn;
    }
    
    public static JLabel getLabNoCWorld() {
        return WorldTestsmallmapJpanel.labNoCWorld;
    }
    
    public static void setLabNoCWorld(JLabel labNoCWorld) {
        WorldTestsmallmapJpanel.labNoCWorld = labNoCWorld;
    }
    
    public static JLabel getMoveWorld() {
        return WorldTestsmallmapJpanel.moveWorld;
    }
    
    public static void setMoveWorld(JLabel moveWorld) {
        WorldTestsmallmapJpanel.moveWorld = moveWorld;
    }
    
    public static JLabel getQbnpxWorld() {
        return WorldTestsmallmapJpanel.qbnpxWorld;
    }
    
    public static void setQbnpxWorld(JLabel qbnpxWorld) {
        WorldTestsmallmapJpanel.qbnpxWorld = qbnpxWorld;
    }
    
    public static JLabel getRwnpcWorld() {
        return WorldTestsmallmapJpanel.rwnpcWorld;
    }
    
    public static void setRwnpcWorld(JLabel rwnpcWorld) {
        WorldTestsmallmapJpanel.rwnpcWorld = rwnpcWorld;
    }
    
    public static JLabel getSynpcWorld() {
        return WorldTestsmallmapJpanel.synpcWorld;
    }
    
    public static void setSynpcWorld(JLabel synpcWorld) {
        WorldTestsmallmapJpanel.synpcWorld = synpcWorld;
    }
    
    public JLabel getM1207() {
        return this.m1207;
    }
    
    public void setM1207(JLabel m1207) {
        this.m1207 = m1207;
    }
    
    static {
        WorldTestsmallmapJpanel.tps = new ArrayList<>();
    }
}
