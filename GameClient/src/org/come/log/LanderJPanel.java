package org.come.log;

import java.awt.Image;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicProgressBarUI;
import javafx.scene.web.PopupFeatures;
import java.net.URI;
import java.awt.Desktop;
import javafx.concurrent.Worker;
import javafx.beans.value.ObservableValue;
import java.awt.AlphaComposite;
import java.awt.RenderingHints;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import org.come.socket.DownLoadTxt;
import org.come.init.ClientCheckUpdate;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import javafx.scene.web.WebEngine;
import java.io.InputStream;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import com.tool.tcpimg.UIUtils;
import javafx.application.Platform;
import javafx.scene.Scene;
import com.main.UpdateMain;
import javafx.scene.web.WebView;
import java.util.ArrayList;
import com.tool.tcp.SpriteFactory;
import javax.swing.JProgressBar;
import org.come.login.SpriteBtn;
import com.tool.tcp.Sprite;
import javafx.embed.swing.JFXPanel;
import javax.swing.ImageIcon;
import java.util.List;
import com.tool.tcpimg.ChatBox;
import org.come.init.CheckUpdate;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LanderJPanel extends JPanel
{
    private static JLabel offBtn;
    private static JLabel suoxiao;
    private static JLabel kais;
    private static JLabel jinggao;
    private static JLabel log;
    private static JLabel ziti;
    private static JLabel shezhi;
    private static JLabel version;
    private static JLabel version1;
    private static JLabel progressBg;
    private static JLabel progress;
    private static JLabel announcement;
    private static JPanel announcement1;
    private static JScrollPane jScrollPane;
    public static JLabel labCheck;
    private LanderJFrame landerJFrame;
    public static CheckUpdate checkUpdate;
    ChatBox box;
    private List<String> sb;
    private int time;
    private String versionx;
    public static String jsonObject;
    public static String ip;
    public static int port;
    public static int width;
    public static int high;
    public JLabel mouse;
    private ImageIcon iconS;
    private ImageIcon jdbSt;
    public static JLabel labtext1;
    public static JLabel labtext2;
    public static JLabel labtext3;
    private JLabel xzjd;
    public static String loadLength;
    public static String loadTime;
    public static int loadRaio;
    private JFXPanel jfxPanel;
    private Sprite tcp2;
    private Sprite tcp3;
    private Sprite tcp4;
    private Sprite gxz;
    private Sprite sgbj;
    private Sprite fg;
    private Sprite xzz;
    private Sprite dby;
    private Sprite letCao;
    private Sprite jdb;
    private SpriteBtn startGame;
    private SpriteBtn close;
    private SpriteBtn min;
    private SpriteBtn set;
    public static Boolean isUpdata;
    public static Boolean isErr;
    private JProgressBar xzjd1;
    public static CustomImageProgressBar customImageProgressBar;
    //热更界面
    public LanderJPanel(LanderJFrame landerJFrame) throws IOException {
        this.tcp2 = SpriteFactory.VloadSprite("inkImg/background/y.tcp", null);
        InputStream input = LanderJPanel.class.getResource("草").openStream();
        this.tcp3 = SpriteFactory.VloadSprite1("inkImg/background/y.tcp", input, null);
        input = LanderJPanel.class.getResource("石头").openStream();
        this.tcp4 = SpriteFactory.VloadSprite1("inkImg/background/石头.tcp", input, null);
        input = LanderJPanel.class.getResource("gxz").openStream();
        this.gxz = SpriteFactory.VloadSprite1("inkImg/background/gzx.tcp", input, null);
        input = LanderJPanel.class.getResource("发光").openStream();
        this.fg = SpriteFactory.VloadSprite1("inkImg/background/发光.tcp", input, null);
        input = LanderJPanel.class.getResource("黄色石头背景").openStream();
        this.sgbj = SpriteFactory.VloadSprite1("inkImg/background/黄色石头背景.tcp", input, "1|0|256|100|468|0|101|0|343|0|10|11");
        input = LanderJPanel.class.getResource("xzz").openStream();
        this.xzz = SpriteFactory.VloadSprite1("inkImg/background/xzz.tcp", input, null);
        input = LanderJPanel.class.getResource("顶部云").openStream();
        this.dby = SpriteFactory.VloadSprite1("inkImg/background/顶部云.tcp", input, null);
        input = LanderJPanel.class.getResource("左-草").openStream();
        this.letCao = SpriteFactory.VloadSprite1("inkImg/background/左-草.tcp", input, null);
        input = LanderJPanel.class.getResource("jdb").openStream();
        this.jdb = SpriteFactory.VloadSprite1("inkImg/background/jdb.tcp", input, null);
        input = LanderJPanel.class.getResource("set").openStream();
        (this.set = new SpriteBtn("inkImg/background/set.tcp", 990, 70, false, input)).setBounds(990, 70, 50, 35);
        this.set.addMouseListener(new UpdateMouslisten(1, this.set));
        this.add(this.set);
        input = LanderJPanel.class.getResource("close").openStream();
        (this.close = new SpriteBtn("inkImg/background/close.tcp", 1050, 75, false, input)).setBounds(1050, 75, 50, 35);
        this.close.addMouseListener(new UpdateMouslisten(2, this.close));
        this.add(this.close);
        input = LanderJPanel.class.getResource("min").openStream();
        (this.min = new SpriteBtn("inkImg/background/min.tcp", 940, 75, false, input)).setBounds(940, 75, 83, 35);
        this.min.addMouseListener(new UpdateMouslisten(3, this.min));
        this.add(this.min);
        input = LanderJPanel.class.getResource("stratGame").openStream();
        (this.startGame = new SpriteBtn("inkImg/background/startGame.tcp", 947, 450, false, input)).setBounds(947, 450, 130, 50);
        this.startGame.addMouseListener(new UpdateMouslisten(4, this.startGame));
        this.add(this.startGame);
        this.box = new ChatBox();
        this.sb = new ArrayList<>();
        this.jfxPanel = new JFXPanel();
        this.jfxPanel = new JFXPanel();
        Platform.runLater(()/*  */ -> {
            WebView webView = new WebView();
            webView.getEngine().load(UpdateMain.linkUrl);
            WebEngine webEngine = webView.getEngine();
            webEngine.setCreatePopupHandler(popupFeatures/* javafx.scene.web.PopupFeatures, */ -> null);
            webEngine.getLoadWorker().<LanderJPanel>stateProperty().addListener((observable, oldValue, newValue)/* javafx.beans.value.ObservableValue,javafx.concurrent.Worker.State,javafx.concurrent.Worker.State, */ -> {
                if (newValue == Worker.State.SUCCEEDED) {
                    webView.setOnMouseClicked(event/* javafx.scene.input.MouseEvent, */ -> {
                        double x = event.getX();
                        double y = event.getY();
                        String url = (String)webEngine.executeScript("document.elementFromPoint(" + x + ", " + y + ").href");
                        if (url != null && !url.isEmpty()) {
                            try {
                                Desktop.getDesktop().browse(new URI(url));
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
            this.jfxPanel.setScene(new Scene(webView));
            return;
        });
        this.add(this.jfxPanel);
        this.setLayout(null);
        this.setOpaque(false);
        this.setBackground(UIUtils.Color_BACK);
        this.setPreferredSize(new Dimension(1316, 646));
        (LanderJPanel.suoxiao = new JLabel()).setBounds(963, 120, 35, 35);
        LanderJPanel.suoxiao.setIcon(new ImageIcon("res/up/0244-FF5102FC (13).png"));
        this.add(LanderJPanel.suoxiao);
        LanderJPanel.suoxiao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                LanderJPanel.suoxiao.setIcon(new ImageIcon("res/up/0244-FF5102FC (122).png"));
                LanderJPanel.suoxiao.setBounds(965, 120, 35, 35);
                LanderJFrame.frame.setExtendedState(1);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                LanderJPanel.suoxiao.setIcon(new ImageIcon("res/up/0244-FF5102FC (138).png"));
                LanderJPanel.suoxiao.setBounds(964, 120, 35, 35);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                LanderJPanel.suoxiao.setIcon(new ImageIcon("res/up/0244-FF5102FC (13).png"));
                LanderJPanel.suoxiao.setBounds(963, 120, 35, 35);
            }
        });
        this.rides();
    }
    
    public void rides() {
        (LanderJPanel.customImageProgressBar = new CustomImageProgressBar(Integer.valueOf(68))).setBounds(938, 445, 145, 48);
        this.add(LanderJPanel.customImageProgressBar);
        Font font1 = null;
        font1 = new Font("楷体", 0, 20);
        (LanderJPanel.labtext1 = new JLabel()).setForeground(Color.YELLOW);
        LanderJPanel.labtext1.setFont(font1);
        LanderJPanel.labtext1.setBounds(1080, 442, 280, 30);
        LanderJPanel.labtext1.setHorizontalAlignment(0);
        LanderJPanel.labtext1.setVerticalTextPosition(0);
        LanderJPanel.labtext1.setVisible(false);
        this.add(LanderJPanel.labtext1);
        (this.xzjd = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        }).setForeground(Color.black);
        this.xzjd.setFont(UIUtils.TEXT_FONT1);
        this.xzjd.setBounds(1080, 442, 280, 30);
        this.xzjd.setHorizontalAlignment(0);
        this.xzjd.setVerticalTextPosition(0);
        this.xzjd.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.xzjd.setVisible(false);
        this.add(this.xzjd);
        (LanderJPanel.shezhi = new JLabel()).setOpaque(false);
        LanderJPanel.shezhi.setBorder(null);
        LanderJPanel.shezhi.setBackground(UIUtils.Color_BACK);
        LanderJPanel.shezhi.setBounds(1003, 120, 35, 35);
        LanderJPanel.shezhi.setIcon(new ImageIcon("res/up/0244-FF5102FC (60).png"));
        this.add(LanderJPanel.shezhi);
        LanderJPanel.shezhi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                LanderJPanel.shezhi.setIcon(new ImageIcon("res/up/0244-FF5102FC (60).png"));
                new LaddJFrame();
                LaddJFrame.readSysteminit();
                LaddJFrame.readSysteminitlog();
            }
        });
        Font font2 = new Font("宋体", 0, 12);
        (LanderJPanel.announcement1 = new JPanel()).setPreferredSize(new Dimension(390, this.sb.size() * 19));
        LanderJPanel.announcement1.setOpaque(false);
        LanderJPanel.announcement1.setLayout(null);
        (LanderJPanel.jScrollPane = new JScrollPane()).setVerticalScrollBarPolicy(22);
        LanderJPanel.jScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        LanderJPanel.jScrollPane.getViewport().setOpaque(false);
        LanderJPanel.jScrollPane.setOpaque(false);
        LanderJPanel.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        LanderJPanel.jScrollPane.setHorizontalScrollBarPolicy(31);
        LanderJPanel.jScrollPane.setViewportView(LanderJPanel.announcement1);
        LanderJPanel.jScrollPane.setBounds(560, 120, 390, 280);
        LanderJPanel.jScrollPane.getVerticalScrollBar().setUI(null);
        this.box = new ChatBox();
        for (int i = 0; i < this.sb.size(); ++i) {
            (LanderJPanel.announcement = new JLabel((String)this.sb.get(i))).setBounds(10, 18 * i, 390, 18);
            LanderJPanel.announcement.setBackground(null);
            LanderJPanel.announcement.setForeground(Color.white);
            LanderJPanel.announcement.setFont(font2);
            LanderJPanel.announcement.setOpaque(false);
            LanderJPanel.announcement1.add(this.box);
        }
        this.add(LanderJPanel.jScrollPane);
        (LanderJPanel.offBtn = new JLabel()).setBounds(1043, 120, 35, 35);
        LanderJPanel.offBtn.setIcon(new ImageIcon("res/up/0244-FF5102FC (192).png"));
        LanderJPanel.offBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                LanderJPanel.offBtn.setIcon(new ImageIcon("res/up/0244-FF5102FC (10).png"));
                LanderJPanel.offBtn.setBounds(1045, 120, 35, 35);
                System.exit(0);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                LanderJPanel.offBtn.setIcon(new ImageIcon("res/up/0244-FF5102FC (30).png"));
                LanderJPanel.offBtn.setBounds(1044, 120, 35, 35);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                LanderJPanel.offBtn.setIcon(new ImageIcon("res/up/0244-FF5102FC (192).png"));
                LanderJPanel.offBtn.setBounds(1043, 120, 35, 35);
            }
        });
        LanderJPanel.offBtn.setOpaque(false);
        this.add(LanderJPanel.offBtn);
        (LanderJPanel.kais = new JLabel()).setBounds(1028, 320, 112, 112);
        LanderJPanel.kais.setIcon(new ImageIcon("res/up/0244-FF5102FC (176).png"));
        LanderJPanel.kais.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (LanderJPanel.kais.getText().equals("更新中")) {
                    return;
                }
                LanderJPanel.kais.setIcon(new ImageIcon("res/up/0244-FF5102FC (137).png"));
                LanderJPanel.kais.setBounds(1030, 322, 112, 112);
            }
            
            @Override
            public void mouseReleased(MouseEvent ee) {
                super.mouseReleased(ee);
                if (LanderJPanel.kais.getText().equals("更新中")) {
                    return;
                }
                String ur = System.getProperty("user.dir");
                try {
                    Runtime.getRuntime().exec(ur + "/MYDATAS22.dat");
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
                LanderJFrame.frame.setVisible(false);
                System.exit(1);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (LanderJPanel.kais.getText().equals("更新中")) {
                    return;
                }
                LanderJPanel.kais.setIcon(new ImageIcon("res/up/0244-FF5102FC (153).png"));
                LanderJPanel.kais.setBounds(1029, 321, 112, 112);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (LanderJPanel.kais.getText().equals("更新中")) {
                    return;
                }
                LanderJPanel.kais.setIcon(new ImageIcon("res/up/0244-FF5102FC (176).png"));
                LanderJPanel.kais.setBounds(1028, 320, 112, 112);
            }
        });
        this.add(LanderJPanel.kais);
        (LanderJPanel.jinggao = new JLabel()).setIcon(new ImageIcon("res/up/0244-FF5102FC (15).png"));
        LanderJPanel.jinggao.setBounds(970, 220, 48, 62);
        this.add(LanderJPanel.jinggao);
        (LanderJPanel.log = new JLabel()).setIcon(new ImageIcon("res\\up\\logo.png"));
        LanderJPanel.log.setBounds(680, 40, 220, 87);
        this.add(LanderJPanel.log);
        (LanderJPanel.ziti = new JLabel()).setIcon(new ImageIcon("res\\up\\0244-FF5102FC (37).png"));
        LanderJPanel.ziti.setBounds(1020, 220, 120, 34);
        this.add(LanderJPanel.ziti);
        (LanderJPanel.version1 = new JLabel() {
            @Override
            protected void paintComponent(Graphics g2) {
                Graphics2D g3 = (Graphics2D)g2.create();
                int x = 0;
                int y = 13;
                g3.setColor(new Color(146, 141, 126, 201));
                g3.drawString(this.getText(), x - 1, y);
                g3.drawString(this.getText(), x + 1, y);
                g3.drawString(this.getText(), x, y - 1);
                g3.drawString(this.getText(), x, y + 1);
                g3.drawString(this.getText(), x - 1, y);
                g3.drawString(this.getText(), x + 1, y);
                g3.drawString(this.getText(), x, y - 1);
                g3.drawString(this.getText(), x, y + 1);
                g3.setColor(Color.WHITE);
                g3.drawString(this.getText(), x, y);
                g3.drawString(this.getText(), x, y);
                g3.drawString(this.getText(), x, y);
            }
        }).setText("本地版本：" + ClientCheckUpdate.getVersion());
        LanderJPanel.version1.setFont(new Font("宋体", 0, 13));
        LanderJPanel.version1.setForeground(Color.WHITE);
        LanderJPanel.version1.setBounds(1020, 255, 200, 15);
        this.add(LanderJPanel.version1);
        (LanderJPanel.version = new JLabel() {
            @Override
            protected void paintComponent(Graphics g2) {
                Graphics2D g3 = (Graphics2D)g2.create();
                int x = 0;
                int y = 13;
                g3.setColor(new Color(146, 141, 126, 201));
                g3.drawString(this.getText(), x - 1, y);
                g3.drawString(this.getText(), x + 1, y);
                g3.drawString(this.getText(), x, y - 1);
                g3.drawString(this.getText(), x, y + 1);
                g3.drawString(this.getText(), x - 1, y);
                g3.drawString(this.getText(), x + 1, y);
                g3.drawString(this.getText(), x, y - 1);
                g3.drawString(this.getText(), x, y + 1);
                g3.setColor(Color.WHITE);
                g3.drawString(this.getText(), x, y);
                g3.drawString(this.getText(), x, y);
                g3.drawString(this.getText(), x, y);
                g3.drawString(this.getText(), x, y);
            }
        }).setText("最新版本：" + ClientCheckUpdate.getVersion());
        LanderJPanel.version.setFont(new Font("宋体", 0, 13));
        LanderJPanel.version.setForeground(Color.WHITE);
        LanderJPanel.version.setBounds(1020, 270, 200, 15);
        this.add(LanderJPanel.version);
        (LanderJPanel.progressBg = new JLabel(new ImageIcon("res/up/0244-FF5102FC (89).png"))).setBounds(200, 410, 972, 74);
        LanderJPanel.progressBg.setVisible(false);
        this.add(LanderJPanel.progressBg);
        (LanderJPanel.progress = new JLabel(new ImageIcon("res/up/0244-FF5102FC (109).png"))).setOpaque(false);
        LanderJPanel.progress.setVisible(false);
        this.add(LanderJPanel.progress);
        font1 = new Font("楷体", 0, 20);
        (LanderJPanel.labtext2 = new JLabel()).setForeground(Color.BLUE);
        LanderJPanel.labtext2.setFont(font1);
        LanderJPanel.labtext2.setBounds(195, 453, 280, 20);
        this.add(LanderJPanel.labtext2);
        LanderJPanel.version = getVersion();
        font1 = new Font("楷体", 0, 15);
        (LanderJPanel.labtext3 = new JLabel()).setForeground(Color.BLACK);
        LanderJPanel.labtext3.setFont(font1);
        LanderJPanel.labtext3.setBounds(655, 470, 280, 20);
        this.add(LanderJPanel.labtext3);
        LanderJPanel.version.setBounds(220, 474, 200, 15);
        LanderJPanel.version1.setBounds(220, 492, 200, 15);
    }
    
    public List<String> getGG() {
        String v = DownLoadTxt.getDownLoadTxt().GetServerTxt("gg2.txt");
        if (v != null && v.length() != 0) {
            String[] vs = v.split("\r\n");
            if (vs.length == 1) {
                vs = v.split("\n");
            }
            return Arrays.asList(vs);
        }
        else {
            return new ArrayList<>();
        }
    }
    
    public static BufferedImage loadImage(String path) {
        try {
            InputStream inputStream = LanderJPanel.class.getResource(path).openStream();
            if (inputStream == null) {
                throw new IOException("Could not load resource: " + path);
            }
            return ImageIO.read(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconS == null) {
            this.iconS = new ImageIcon(loadImage("0213-48c171ed.png"));
        }
        if (this.jdbSt == null) {
            this.jdbSt = new ImageIcon(loadImage("jdb-st.png"));
        }
        this.jfxPanel.setBounds(530, 110, 402, 390);
        g.drawImage(this.iconS.getImage(), 0, 0, 1186, 605, this);
        g.drawImage(this.jdbSt.getImage(), 990, 300, null);
        this.dby.updateToTime(System.currentTimeMillis(), 0);
        this.dby.draw(g, 420, 40);
        this.letCao.updateToTime(System.currentTimeMillis(), 0);
        this.letCao.draw(g, 5, 350);
        this.jdb.updateToTime(System.currentTimeMillis(), 0);
        this.jdb.drawFD(g, 1010, 350);
        this.set.draw(g);
        this.close.draw(g);
        this.min.draw(g);
        this.tcp2.updateToTime(System.currentTimeMillis(), 0);
        this.tcp2.draw(g, 90, 430);
        this.tcp3.updateToTime(System.currentTimeMillis(), 0);
        this.tcp3.draw(g, 910, 230);
        this.tcp4.updateToTime(System.currentTimeMillis(), 0);
        this.tcp4.draw(g, 890, 395);
        if ("更新中".equals(LanderJPanel.kais.getText())) {
            LanderJPanel.labtext1.setForeground(Color.MAGENTA);
            LanderJPanel.labtext1.setVisible(true);
            LanderJPanel.labtext1.setBounds(1073, -100, 280, 30);
            this.gxz.updateToTime(System.currentTimeMillis(), 0);
            this.gxz.draw(g, 980, 470);
            this.fg.updateToTime(System.currentTimeMillis(), 0);
            this.fg.draw(g, 860, 340);
            g.drawString("下载数据完毕,正在进行更新. . .", 920, 520);
            g.drawString("下载数据完毕,正在进行更新. . .", 920, 520);
            g.drawString("下载数据完毕,正在进行更新. . .", 920, 520);
            getKais().setVisible(false);
        }
        else if ("下载中".equals(LanderJPanel.kais.getText())) {
            Graphics2D g2d = (Graphics2D)g.create();
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setComposite(AlphaComposite.getInstance(3, 0.45f));
            LanderJPanel.labtext1.setForeground(new Color(255, 237, 173));
            LanderJPanel.labtext1.setVisible(true);
            LanderJPanel.labtext1.setBounds(1073, 390, 280, 30);
            this.xzz.updateToTime(System.currentTimeMillis(), 0);
            LanderJPanel.labtext1.setBounds(970, 478, 60, 25);
            this.xzz.draw(g, 980, 457);
            this.xzjd.setBounds(950, 500, 100, 50);
            g2d.setComposite(AlphaComposite.getInstance(3, 0.55f));
            this.fg.updateToTime(System.currentTimeMillis(), 0);
            this.fg.draw(g2d, 860, 340);
            getKais().setVisible(false);
            g.drawString("下载速度：", 940, 520);
            g.drawString("下载速度：", 940, 520);
            g.drawString("下载速度：", 940, 520);
            g.drawString(LanderJPanel.loadLength, 1000, 520);
            g.drawString(LanderJPanel.loadLength, 1000, 520);
            g.drawString(LanderJPanel.loadLength, 1000, 520);
            g.drawString("    预计还需：", 940, 535);
            g.drawString("    预计还需：", 940, 535);
            g.drawString("    预计还需：", 940, 535);
            g.drawString(LanderJPanel.loadTime, 1010, 535);
            g.drawString(LanderJPanel.loadTime, 1010, 535);
            g.drawString(LanderJPanel.loadTime, 1010, 535);
            LanderJPanel.customImageProgressBar.setBounds(938, 445, 145, 80);
        }
        else if ("开始游戏".equals(LanderJPanel.kais.getText())) {
            this.startGame.draw(g);
            if (!this.startGame.isVisible()) {
                this.startGame.setVisible(true);
            }
            if ((boolean)LanderJPanel.isUpdata && !(boolean)LanderJPanel.isErr) {
                g.drawString("当前客户端已是最新版本，", 930, 520);
                g.drawString("请点击开始游戏按钮进入游戏，", 920, 535);
            }
            if ((boolean)LanderJPanel.isErr) {
                g.setColor(Color.red);
                g.drawString("游戏更新发生错误，", 930, 520);
                g.drawString("请关闭主程序客户端再进行更新", 920, 535);
            }
        }
        else {
            LanderJPanel.labtext1.setVisible(true);
        }
        String text = "Hello, World!";
        this.repaint();
    }
    
    public JLabel getOffBtn() {
        return LanderJPanel.offBtn;
    }
    
    public void setOffBtn(JLabel offBtn) {
        LanderJPanel.offBtn = offBtn;
    }
    
    public JLabel getSuoxiao() {
        return LanderJPanel.suoxiao;
    }
    
    public void setSuoxiao(JLabel suoxiao) {
        LanderJPanel.suoxiao = suoxiao;
    }
    
    public static JLabel getKais() {
        return LanderJPanel.kais;
    }
    
    public static void setKais(JLabel kais) {
        LanderJPanel.kais = kais;
    }
    
    public static JScrollPane getjScrollPane() {
        return LanderJPanel.jScrollPane;
    }
    
    public static void setjScrollPane(JScrollPane jScrollPane) {
        LanderJPanel.jScrollPane = jScrollPane;
    }
    
    public LanderJFrame getLanderJFrame() {
        return this.landerJFrame;
    }
    
    public void setLanderJFrame(LanderJFrame landerJFrame) {
        this.landerJFrame = landerJFrame;
    }
    
    public ImageIcon getIconS() {
        return this.iconS;
    }
    
    public void setIconS(ImageIcon iconS) {
        this.iconS = iconS;
    }
    
    public JLabel getJinggao() {
        return LanderJPanel.jinggao;
    }
    
    public void setJinggao(JLabel jinggao) {
        LanderJPanel.jinggao = jinggao;
    }
    
    public JLabel getShezhi() {
        return LanderJPanel.shezhi;
    }
    
    public void setShezhi(JLabel shezhi) {
        LanderJPanel.shezhi = shezhi;
    }
    
    public static JLabel getVersion() {
        return LanderJPanel.version;
    }
    
    public static void setVersion(JLabel version) {
        LanderJPanel.version = version;
    }
    
    public static JLabel getVersion1() {
        return LanderJPanel.version1;
    }
    
    public static void setVersion1(JLabel version1) {
        LanderJPanel.version1 = version1;
    }
    
    public static CheckUpdate getCheckUpdate() {
        return LanderJPanel.checkUpdate;
    }
    
    public static void setCheckUpdate(CheckUpdate checkUpdate) {
        LanderJPanel.checkUpdate = checkUpdate;
    }
    
    public ChatBox getBox() {
        return this.box;
    }
    
    public void setBox(ChatBox box) {
        this.box = box;
    }
    
    public List<String> getSb() {
        return this.sb;
    }
    
    public void setSb(List<String> sb) {
        this.sb = sb;
    }
    
    public int getTime() {
        return this.time;
    }
    
    public void setTime(int time) {
        this.time = time;
    }
    
    public String getVersionx() {
        return this.versionx;
    }
    
    public void setVersionx(String versionx) {
        this.versionx = versionx;
    }
    
    public static JLabel getProgressBg() {
        return LanderJPanel.progressBg;
    }
    
    public static void setProgressBg(JLabel progressBg) {
        LanderJPanel.progressBg = progressBg;
    }
    
    public static JLabel getProgress() {
        return LanderJPanel.progress;
    }
    
    public static void setProgress(JLabel progress) {
        LanderJPanel.progress = progress;
    }
    
    public static JLabel getAnnouncement() {
        return LanderJPanel.announcement;
    }
    
    public static void setAnnouncement(JLabel announcement) {
        LanderJPanel.announcement = announcement;
    }
    
    public static JPanel getAnnouncement1() {
        return LanderJPanel.announcement1;
    }
    
    public static void setAnnouncement1(JPanel announcement1) {
        LanderJPanel.announcement1 = announcement1;
    }
    
    public static JLabel getLabCheck() {
        return LanderJPanel.labCheck;
    }
    
    public static void setLabCheck(JLabel labCheck) {
        LanderJPanel.labCheck = labCheck;
    }
    
    public static String getJsonObject() {
        return LanderJPanel.jsonObject;
    }
    
    public static void setJsonObject(String jsonObject) {
        LanderJPanel.jsonObject = jsonObject;
    }
    
    public static String getIp() {
        return LanderJPanel.ip;
    }
    
    public static void setIp(String ip) {
        LanderJPanel.ip = ip;
    }
    
    public static int getPort() {
        return LanderJPanel.port;
    }
    
    public static void setPort(int port) {
        LanderJPanel.port = port;
    }
    
    static {
        LanderJPanel.loadLength = "";
        LanderJPanel.loadTime = "";
        LanderJPanel.loadRaio = 1;
        LanderJPanel.isUpdata = Boolean.valueOf(false);
        LanderJPanel.isErr = Boolean.valueOf(false);
    }
    
    class VerticalProgressBarUI extends BasicProgressBarUI
    {
        @Override
        protected void paintIndeterminate(Graphics g, JComponent c) {
            super.paintIndeterminate(g, c);
            Insets b = this.progressBar.getInsets();
            int barRectWidth = this.progressBar.getWidth() - (b.right + b.left);
            int barRectHeight = this.progressBar.getHeight() - (b.top + b.bottom);
            int amountFull = this.getAmountFull(b, barRectWidth, barRectHeight);
            Graphics2D g2d = (Graphics2D)g;
            Image image = new ImageIcon("progress_bar_image.jpg").getImage();
            g2d.drawImage(image, b.left, b.bottom + barRectHeight - amountFull, barRectWidth, amountFull, null);
        }
    }
    
    class CustomProgressBarUI extends BasicProgressBarUI
    {
        @Override
        protected void paintIndeterminate(Graphics g, JComponent c) {
            super.paintIndeterminate(g, c);
            Insets b = this.progressBar.getInsets();
            int barRectWidth = this.progressBar.getWidth() - (b.right + b.left);
            int barRectHeight = this.progressBar.getHeight() - (b.top + b.bottom);
            int amountFull = this.getAmountFull(b, barRectWidth, barRectHeight);
            Graphics2D g2d = (Graphics2D)g;
            Image image = new ImageIcon("E:\\素材\\登录器\\登录器\\0074-a796355a.png").getImage();
            g2d.drawImage(image, b.left, b.top, amountFull, barRectHeight, null);
        }
    }
}
