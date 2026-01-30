package org.come.Jpanel;

import java.awt.Stroke;
import org.come.bean.NpcInfoBean;
import org.come.bean.LoginResult;
import java.awt.geom.Line2D;
import org.come.bean.PathPoint;
import java.awt.BasicStroke;
import org.come.until.SplitStringTool;
import org.come.until.UserMessUntil;
import org.come.model.Gamemap;
import java.awt.Graphics2D;
import org.come.good.Flight;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.tool.image.ImageMixDeal;
import org.come.until.GoodsListFromServerUntil;
import com.tool.role.RoleData;
import java.awt.Graphics;
import java.util.Iterator;
import java.io.IOException;
import org.come.until.CutButtonImage;
import org.come.mouslisten.SystemMouslisten;
import org.come.until.Util;
import com.updateNew.MyIsif;
import com.tool.tcp.SpriteFactory;
import java.util.HashMap;
import com.tool.tcpimg.UIUtils;
import java.util.ArrayList;
import org.come.Frame.TestsmallmapJframe;
import java.awt.Color;
import java.awt.Font;
import org.come.entity.Goodstable;
import com.tool.btn.FormsOnOffBtn;
import javax.swing.ImageIcon;
import com.tool.tcp.Sprite;
import javax.swing.JLabel;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;

public class TestsmallmapJpanel extends JPanel
{
    private long time;
    private int Dx;
    private int Dy;
    private String xy;
    public static Map<Integer, String> MapName;
    public List pathPoints;
    public JLabel[] sp;
    public Sprite tcp;
    public static ImageIcon icony;
    private static JLabel labNoC;
    private static JLabel move;
    private static JLabel synpc;
    private static JLabel rwnpc;
    private static JLabel qbnpx;
    public JLabel m1207;
    private FormsOnOffBtn offBtn;
    public static List<String> npcfunction;
    private static List<JLabel> tps;
    public static List<Goodstable> chaxunss;
    public int pathPointsx;
    public int pathPointsy;
    ImageIcon icon;
    Font f5;
    Color color;
    Color color1;
    Color color2;
    
    public TestsmallmapJpanel(TestsmallmapJframe testsmallmapJframe) throws IOException {
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
        TestsmallmapJpanel.icony = new ImageIcon("inkImg/button1/CSD.png");
        TestsmallmapJpanel.MapName = new HashMap<>();
        this.tcp = SpriteFactory.VloadSprite("resource/mouse/ZD.tcp", null);
        if (MyIsif.getStyle().equals("水墨")) {
            this.add(this.offBtn = new FormsOnOffBtn("inkImg/button1/xdtgbaj.png", 1, 22));
        }
        else {
            this.add(this.offBtn = new FormsOnOffBtn("inkImg/button1/xdtgbajh.png", 1, 22));
        }
        this.add(this.m1207 = new JLabel());
        (TestsmallmapJpanel.qbnpx = new JLabel()).setOpaque(false);
        TestsmallmapJpanel.qbnpx.setBounds(50, Util.mapmodel.getMin_y() + 30, 15, 15);
        TestsmallmapJpanel.qbnpx.addMouseListener(new SystemMouslisten(31));
        TestsmallmapJpanel.qbnpx.addMouseListener(new SystemMouslisten(36));
        this.add(TestsmallmapJpanel.qbnpx);
        (TestsmallmapJpanel.move = new JLabel()).setOpaque(false);
        TestsmallmapJpanel.move.setBounds(100, Util.mapmodel.getMin_y() + 30, 15, 15);
        TestsmallmapJpanel.move.addMouseListener(new SystemMouslisten(28));
        TestsmallmapJpanel.move.addMouseListener(new SystemMouslisten(33));
        this.add(TestsmallmapJpanel.move);
        (TestsmallmapJpanel.labNoC = new JLabel()).setOpaque(false);
        TestsmallmapJpanel.labNoC.setBounds(150, Util.mapmodel.getMin_y() + 30, 15, 15);
        TestsmallmapJpanel.labNoC.addMouseListener(new SystemMouslisten(27));
        TestsmallmapJpanel.labNoC.addMouseListener(new SystemMouslisten(32));
        this.add(TestsmallmapJpanel.labNoC);
        (TestsmallmapJpanel.rwnpc = new JLabel()).setOpaque(false);
        TestsmallmapJpanel.rwnpc.setBounds(200, Util.mapmodel.getMin_y() + 30, 15, 15);
        TestsmallmapJpanel.rwnpc.addMouseListener(new SystemMouslisten(29));
        TestsmallmapJpanel.rwnpc.addMouseListener(new SystemMouslisten(34));
        this.add(TestsmallmapJpanel.rwnpc);
        (TestsmallmapJpanel.synpc = new JLabel()).setOpaque(false);
        TestsmallmapJpanel.synpc.setBounds(250, Util.mapmodel.getMin_y() + 30, 15, 15);
        TestsmallmapJpanel.synpc.addMouseListener(new SystemMouslisten(30));
        TestsmallmapJpanel.synpc.addMouseListener(new SystemMouslisten(35));
        this.add(TestsmallmapJpanel.synpc);
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
        for (JLabel tp : TestsmallmapJpanel.tps) {
            this.remove(tp);
        }
        TestsmallmapJpanel.tps.clear();
        TestsmallmapJpanel.chaxunss = null;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        TestsmallmapJpanel.qbnpx.setBounds(22, Util.mapmodel.getMin_y() + 41, 15, 15);
        TestsmallmapJpanel.move.setBounds(90, Util.mapmodel.getMin_y() + 41, 15, 15);
        TestsmallmapJpanel.labNoC.setBounds(158, Util.mapmodel.getMin_y() + 41, 15, 15);
        TestsmallmapJpanel.rwnpc.setBounds(226, Util.mapmodel.getMin_y() + 41, 15, 15);
        TestsmallmapJpanel.synpc.setBounds(294, Util.mapmodel.getMin_y() + 41, 15, 15);
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        this.time += 12L;
        super.paintComponent(g);
        if (TestsmallmapJpanel.chaxunss == null) {
            TestsmallmapJpanel.chaxunss = GoodsListFromServerUntil.chaxunss(2011L);
            if (TestsmallmapJpanel.chaxunss == null) {
                TestsmallmapJpanel.chaxunss = new ArrayList<>();
            }
            TestsmallmapJpanel.chaxunss.addAll(GoodsListFromServerUntil.chaxunss(2012L));
            Long mapid = ImageMixDeal.userimg.getRoleShow().getMapid();
            for (Goodstable goodstable : TestsmallmapJpanel.chaxunss) {
                String[] split = goodstable.getValue().split("\\|");
                for (int i = 0; i < split.length; ++i) {
                    String[] strings = split[i].split(",");
                    if (strings[0].contains(String.valueOf(mapid))) {
                        JLabel tp = new JLabel();
                        tp.setBounds((int)((double)(Integer.parseInt(strings[2]) / 20) / Util.mapmodel.getBili_x()) + 3, (int)((double)(Integer.parseInt(strings[3]) / 20) / Util.mapmodel.getBili_y()) + 18, 16, 21);
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
                        TestsmallmapJpanel.tps.add(tp);
                    }
                }
            }
        }
        Iterator<JLabel> iterator2 = TestsmallmapJpanel.tps.iterator();
        while (iterator2.hasNext()) {
            JLabel tp = (JLabel)iterator2.next();
            this.add(tp);
        }
        Util.mapmodel.getZoom().draw(g);
        this.m1207.setBounds(Util.mapmodel.getMin_x() + 4, 50, 78, Util.mapmodel.getMin_y() - 25);
        this.offBtn.setBounds(Util.mapmodel.getMin_x() + 48, 6, 40, 25);
        g.drawImage(this.icon.getImage(), (int)((double)(ImageMixDeal.userimg.getRoleShow().getX() / 20) / Util.mapmodel.getBili_x()) - 6, (int)((double)(ImageMixDeal.userimg.getRoleShow().getY() / 20) / Util.mapmodel.getBili_y()) + 15, 20, 25, this);
        g.setColor(Color.RED);
        Graphics2D g2 = (Graphics2D)g;
        g.setFont(this.f5);
        g.drawString(this.xy, this.Dx, this.Dy);
        g.setFont(UIUtils.TEXT_FONT);
        g.setFont(UIUtils.TEXT_FONT);
        Gamemap game = (Gamemap)UserMessUntil.getAllmapbean().getAllMap().get(loginResult.getMapid() + "");
        List<String> strings2 = SplitStringTool.splitString(game.getMapnpc());
        g.setColor(Color.white);
        g.drawString("全部", 42, Util.mapmodel.getMin_y() + 53);
        this.sp[4].setBounds(20, Util.mapmodel.getMin_y() + 40, 18, 18);
        g.drawString("传送", 110, Util.mapmodel.getMin_y() + 53);
        this.sp[1].setBounds(88, Util.mapmodel.getMin_y() + 40, 18, 18);
        g.drawString("普通", 178, Util.mapmodel.getMin_y() + 53);
        this.sp[0].setBounds(156, Util.mapmodel.getMin_y() + 40, 18, 18);
        g.drawString("任务", 246, Util.mapmodel.getMin_y() + 53);
        this.sp[2].setBounds(224, Util.mapmodel.getMin_y() + 40, 18, 18);
        g.drawString("商业", 314, Util.mapmodel.getMin_y() + 53);
        this.sp[3].setBounds(292, Util.mapmodel.getMin_y() + 40, 18, 18);
        for (int j = 0; j < strings2.size(); ++j) {
            NpcInfoBean infoBean = UserMessUntil.getnpc((String)strings2.get(j));
            if (infoBean != null) {
                try {
                    TestsmallmapJpanel.MapName.put(Integer.valueOf(j), infoBean.getNpctable().getNpcname());
                    int x1 = (int)((double)(Integer.parseInt(infoBean.getNpctable().getTx()) / 20) / Util.mapmodel.getBili_x() + 25.0);
                    int y1 = (int)((double)(Integer.parseInt(infoBean.getNpctable().getTy()) / 20) / Util.mapmodel.getBili_x() + 25.0);
                    if (TestsmallmapJpanel.labNoC.getIcon() != null && (infoBean.getNpctable().getTypexdt().equals("2") || infoBean.getNpctable().getTypexdt().equals("222") || infoBean.getNpctable().getTypexdt().equals("2222"))) {
                        g.setColor(Color.green);
                        g.drawString((String)TestsmallmapJpanel.MapName.get(Integer.valueOf(j)), x1 - 40, y1 + 10);
                    }
                    else {
                        g.setColor(Color.green);
                        g.drawString("", x1 - 25, y1 + 10);
                    }
                    if (TestsmallmapJpanel.rwnpc.getIcon() != null && infoBean.getNpctable().getTypexdt().equals("3")) {
                        g.setColor(Color.orange);
                        g.drawString((String)TestsmallmapJpanel.MapName.get(Integer.valueOf(j)), x1 - 40, y1 + 10);
                    }
                    else {
                        g.setColor(Color.orange);
                        g.drawString("", x1 - 25, y1 + 10);
                    }
                    if (TestsmallmapJpanel.synpc.getIcon() != null && infoBean.getNpctable().getTypexdt().equals("4")) {
                        g.setColor(Color.cyan);
                        g.drawString((String)TestsmallmapJpanel.MapName.get(Integer.valueOf(j)), x1 - 40, y1 + 10);
                    }
                    else {
                        g.setColor(Color.cyan);
                        g.drawString("", x1 - 25, y1 + 10);
                    }
                    if (TestsmallmapJpanel.move.getIcon() != null && infoBean.getNpctable().getTypexdt().equals("1")) {
                        g.drawImage(TestsmallmapJpanel.icony.getImage(), x1 - 30, y1 - 5, this);
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
            for (int k = 0; k < this.pathPoints.size() - 1; ++k) {
                Stroke dash = new BasicStroke(5.0f, 1, 1, 3.5f, new float[] { 2.5f, 10.0f }, 0.0f);
                g2.setStroke(dash);
                g2.draw(new Line2D.Double((double)(int)((double)(((PathPoint)this.pathPoints.get(k)).getX() / 20) / Util.mapmodel.getBili_x() + 7.0), (double)(int)((double)(((PathPoint)this.pathPoints.get(k)).getY() / 20) / Util.mapmodel.getBili_x() + 40.0), (double)(int)((double)(((PathPoint)this.pathPoints.get(k + 1)).getX() / 20) / Util.mapmodel.getBili_x() + 7.0), (double)(int)((double)(((PathPoint)this.pathPoints.get(k + 1)).getY() / 20) / Util.mapmodel.getBili_x() + 40.0)));
            }
            this.tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
            this.tcp.draw(g, (int)((double)(((PathPoint)this.pathPoints.get(this.pathPoints.size() - 1)).getX() / 20) / Util.mapmodel.getBili_x() + 10.0) - 28, (int)((double)(((PathPoint)this.pathPoints.get(this.pathPoints.size() - 1)).getY() / 20) / Util.mapmodel.getBili_x() + 20.0) - 10);
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
    
    public static JLabel getLabNoC() {
        return TestsmallmapJpanel.labNoC;
    }
    
    public static void setLabNoC(JLabel labNoC) {
        TestsmallmapJpanel.labNoC = labNoC;
    }
    
    public static JLabel getMove() {
        return TestsmallmapJpanel.move;
    }
    
    public static void setMove(JLabel move) {
        TestsmallmapJpanel.move = move;
    }
    
    public static JLabel getQbnpx() {
        return TestsmallmapJpanel.qbnpx;
    }
    
    public static void setQbnpx(JLabel qbnpx) {
        TestsmallmapJpanel.qbnpx = qbnpx;
    }
    
    public static JLabel getRwnpc() {
        return TestsmallmapJpanel.rwnpc;
    }
    
    public static void setRwnpc(JLabel rwnpc) {
        TestsmallmapJpanel.rwnpc = rwnpc;
    }
    
    public static JLabel getSynpc() {
        return TestsmallmapJpanel.synpc;
    }
    
    public static void setSynpc(JLabel synpc) {
        TestsmallmapJpanel.synpc = synpc;
    }
    
    static {
        TestsmallmapJpanel.tps = new ArrayList<>();
    }
}
