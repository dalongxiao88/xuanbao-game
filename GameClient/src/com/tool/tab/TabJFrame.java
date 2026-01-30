package com.tool.tab;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.util.List;
import java.io.InputStream;
import org.come.socket.GameClient;
import java.math.BigDecimal;
import com.tool.btn.TitlelBtn;
import com.updateNew.MyIsif;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.io.IOException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import java.awt.Color;
import org.come.bean.LoginResult;
import java.util.HashMap;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import org.come.Jpanel.CustomTitleBarUI;
import org.come.entity.ServerInfo;
import javax.swing.JFrame;

public class TabJFrame extends JFrame
{
    public static ServerInfo serverInfo;
    public CustomTitleBarUI customTitleBarUI;
    JSplitPane jSplitPane;
    JPanel add;
    JPanel addBTC;
    public JTabbedPane jTabbedPane;
    public static int index;
    public static int shouId;
    public HashMap<Integer, Process> games;
    public HashMap<Integer, LoginResult> roles;
    private int jdNum;
    public static Boolean start;
    
    public TabJFrame() {
        this.jTabbedPane = new JTabbedPane();
        this.games = new HashMap<>();
        this.roles = new HashMap<>();
        this.jdNum = 100;
        this.setResizable(false);
        Toolkit tool = this.getToolkit();
        Image myimage = tool.getImage("img/icon/ico.png");
        this.setIconImage(myimage);
        (this.customTitleBarUI = new CustomTitleBarUI(this)).setTitle("大话西游2经典版");
        this.add(this.jTabbedPane, "Center");
        this.add(this.customTitleBarUI, "North");
        this.jTabbedPane.setDoubleBuffered(true);
        this.jTabbedPane.setOpaque(false);
        this.jTabbedPane.setVisible(false);
        this.jTabbedPane.setBackground(Color.yellow);
        this.add(this.add = this.createContent(-1));
        this.addBTC = new ButtonTabComponent(this.jTabbedPane, false);
        this.jTabbedPane.add("  +  ", this.add);
        this.jTabbedPane.setTabComponentAt(0, this.addBTC);
        this.jTabbedPane.setBounds(6, 20, 1026, 78);
        this.jTabbedPane.setBorder(BorderFactory.createEmptyBorder());
        int id = this.addGame();
        this.showGame(id);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }
            
            @Override
            public void windowClosing(WindowEvent e) {
            }
            
            @Override
            public void windowClosed(WindowEvent e) {
            }
            
            @Override
            public void windowIconified(WindowEvent e) {
                Main.tabJFrame.games.forEach((k, v)/* java.lang.Integer,java.lang.Process, */ -> {
                    Process process = (Process)Main.tabJFrame.games.get(k);
                    try {
                        process.getOutputStream().write("chatHide\n".getBytes());
                        process.getOutputStream().flush();
                    }
                    catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    return;
                });
            }
            
            @Override
            public void windowDeiconified(WindowEvent e) {
                Main.tabJFrame.games.forEach((k, v)/* java.lang.Integer,java.lang.Process, */ -> {
                    Process process = (Process)Main.tabJFrame.games.get(k);
                    if ((int)k == TabJFrame.shouId) {
                        try {
                            process.getOutputStream().write("chatShow\n".getBytes());
                            process.getOutputStream().flush();
                        }
                        catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    else {
                        try {
                            process.getOutputStream().write("chatHide\n".getBytes());
                            process.getOutputStream().flush();
                        }
                        catch (IOException ioException2) {
                            ioException2.printStackTrace();
                        }
                    }
                    return;
                });
            }
            
            @Override
            public void windowActivated(WindowEvent e) {
            }
            
            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        this.jTabbedPane.setVisible(false);
        this.setUndecorated(true);
        this.setPreferredSize(new Dimension(1026, 768));
        this.setTitle(Main.title);
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    public int addGame() {
        ProcessBuilder processBuilder = this.getProcess(TabJFrame.index);
        Process process = null;
        try {
            Process finalProcess;
            process = (finalProcess = processBuilder.start());
            int id = TabJFrame.index;
            new Thread(()/*  */ -> {
                InputStream in = finalProcess.getInputStream();
                try {
                    InputStreamReader in2 = new InputStreamReader(in, MyIsif.debug ? "UTF-8" : "GBK");
                    BufferedReader reader = new BufferedReader(in2);
                    while (true) {
                        String s = reader.readLine();
                        String line;
                        if ((line = s) != null) {
                            if (line.startsWith("switch")) {
                                TabJFrame tabJFrame = Main.tabJFrame;
                                Integer index = Integer.valueOf(TabJFrame.shouId);
                                List<TitlelBtn> titlelBtns = this.customTitleBarUI.getTitlelBtns();
                                int i = 0;
                                while (i < titlelBtns.size()) {
                                    if (((TitlelBtn)titlelBtns.get(i)).getCaozuo() == (int)index) {
                                        if (i == titlelBtns.size() - 1) {
                                            ((TitlelBtn)titlelBtns.get(0)).nochoose(null);
                                            break;
                                        }
                                        else {
                                            ((TitlelBtn)titlelBtns.get(i + 1)).nochoose(null);
                                            break;
                                        }
                                    }
                                    else {
                                        ++i;
                                    }
                                }
                            }
                            if (line.startsWith("Rolename")) {
                                String[] v = line.split("\n");
                                LoginResult loginResult = new LoginResult();
                                String[] v2 = v[0].split("=");
                                loginResult.setRolename(v2[1]);
                                loginResult.setSpecies_id(new BigDecimal(v2[2]));
                                loginResult.setRace_name(v2[4]);
                                loginResult.setRole_id(new BigDecimal(v2[5]));
                                loginResult.setBT(v2[6]);
                                int j = Integer.parseInt(v2[3]);
                                Main.tabJFrame.roles.put(Integer.valueOf(j), loginResult);
                            }
                            else {
                                continue;
                            }
                        }
                        else {
                            break;
                        }
                    }
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
                return;
            }).start();
            new Thread(()/*  */ -> {
                try {
                    InputStream in3 = finalProcess.getErrorStream();
                    byte[] bytes = new byte[1024];
                    while (true) {
                        int len = in3.read(bytes);
                        if (len > 0) {
                            String str = new String(bytes, 0, len);
                            System.out.println(str);
                        }
                        else {
                            break;
                        }
                    }
                }
                catch (Exception e3) {
                    e3.printStackTrace();
                }
                return;
            }).start();
        }
        catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        this.games.put(Integer.valueOf(TabJFrame.index), process);
        JPanel tab = this.createContent(TabJFrame.index);
        ButtonTabComponent btc = new ButtonTabComponent(this.jTabbedPane, true);
        this.jTabbedPane.add(tab, GameClient.BT, TabJFrame.index);
        this.jTabbedPane.add(this.add, "+", TabJFrame.index + 1);
        this.jTabbedPane.setTabComponentAt(TabJFrame.index, btc);
        this.jTabbedPane.setTabComponentAt(TabJFrame.index + 1, this.addBTC);
        if (Main.tabJFrame != null) {
            Main.tabJFrame.setComponentZOrder(this.customTitleBarUI, 1);
        }
        return TabJFrame.index++;
    }
    
    public void removeGame(int id) {
        this.jTabbedPane.setTabComponentAt(id, new JLabel());
    }
    
    public void showGame(int id) {
        Main.lastX = -1;
        Main.lastY = -1;
        TabJFrame.shouId = id;
    }
    
    public JSplitPane getJSplitPane() {
        return this.jSplitPane;
    }
    
    public HashMap<Integer, Process> getGames() {
        return this.games;
    }
    
    public int getShouId() {
        return TabJFrame.shouId;
    }
    
    public ProcessBuilder getProcess(int id) {
        List<String> res = new ArrayList<>();
        if (MyIsif.debug) {
            String b = System.getProperty("java.home");
            String subStringB = b.substring(b.lastIndexOf("/") + 1);
            int index = subStringB.lastIndexOf("\\");
            String pa = subStringB.substring(0, index) + "\\bin\\java.exe";
            pa = pa.replaceAll("\\\\", "\\\\\\\\");
            res.add(pa);
            res.add("-Dfile.encoding=UTF-8");
            res.add("-classpath");
            res.add("\"" + Main.dir + "\\out\\production\\GameClient;" + Main.dir + "\\jre\\lib\\charsets.jar;" + Main.dir + "\\jre\\lib\\deploy.jar;" + Main.dir + "\\jre\\lib\\ext\\access-bridge-64.jar;" + Main.dir + "\\jre\\lib\\ext\\cldrdata.jar;" + Main.dir + "\\jre\\lib\\ext\\dnsns.jar;" + Main.dir + "\\jre\\lib\\ext\\jaccess.jar;" + Main.dir + "\\jre\\lib\\ext\\jfxrt.jar;" + Main.dir + "\\jre\\lib\\ext\\localedata.jar;" + Main.dir + "\\jre\\lib\\ext\\nashorn.jar;" + Main.dir + "\\jre\\lib\\ext\\sunec.jar;" + Main.dir + "\\jre\\lib\\ext\\sunjce_provider.jar;" + Main.dir + "\\jre\\lib\\ext\\sunmscapi.jar;" + Main.dir + "\\jre\\lib\\ext\\sunpkcs11.jar;" + Main.dir + "\\jre\\lib\\ext\\zipfs.jar;" + Main.dir + "\\jre\\lib\\javaws.jar;" + Main.dir + "\\jre\\lib\\jce.jar;" + Main.dir + "\\jre\\lib\\jfr.jar;" + Main.dir + "\\jre\\lib\\jfxswt.jar;" + Main.dir + "\\jre\\lib\\jsse.jar;" + Main.dir + "\\jre\\lib\\management-agent.jar;" + Main.dir + "\\jre\\lib\\plugin.jar;" + Main.dir + "\\jre\\lib\\resources.jar;" + Main.dir + "\\jre\\lib\\rt.jar;" + Main.dir + "\\jre\\lib\\security\\policy\\limited\\local_policy.jar;" + Main.dir + "\\jre\\lib\\security\\policy\\limited\\US_export_policy.jar;" + Main.dir + "\\jre\\lib\\security\\policy\\unlimited\\local_policy.jar;" + Main.dir + "\\jre\\lib\\security\\policy\\unlimited\\US_export_policy.jar;" + Main.dir + "\\libs\\commons-beanutils-1.8.0.jar;" + Main.dir + "\\libs\\commons-beanutils-core-1.8.2.jar;" + Main.dir + "\\libs\\commons-codec-1.3.jar;" + Main.dir + "\\libs\\commons-collections-3.2.1.jar;" + Main.dir + "\\libs\\commons-dbutils-1.3.jar;" + Main.dir + "\\libs\\commons-httpclient-3.0-rc4.jar;" + Main.dir + "\\libs\\commons-jexl-2.0.1.jar;" + Main.dir + "\\libs\\commons-lang-2.6.jar;" + Main.dir + "\\libs\\hutool-all-5.8.5.jar;" + Main.dir + "\\libs\\commons-logging-1.0.4.jar;" + Main.dir + "\\libs\\commons-logging-1.1.1.jar;" + Main.dir + "\\libs\\dom4j-1.6.1.jar;" + Main.dir + "\\libs\\gson-2.2.4.jar;" + Main.dir + "\\libs\\mp3spi1.9.5.jar;" + Main.dir + "\\libs\\netty-all-4.1.19.Final-sources.jar;" + Main.dir + "\\libs\\netty-all-4.1.19.Final.jar;" + Main.dir + "\\libs\\tritonus_share.jar;" + Main.dir + "\\libs\\poi-1.8.0-dev-20020919.jar;" + Main.dir + "\\libs\\poi-3.9.20130515.jar;" + Main.dir + "\\libs\\ImageEditor.jar;" + Main.dir + "\\libs\\spark-excel_2.12-0.11.5.jar;" + Main.dir + "\\libs\\slf4j-jdk14-1.7.13.jar;" + Main.dir + "\\libs\\tritonus_share.jar;" + Main.dir + "\\libs\\mp3spi1.9.5.jar;" + Main.dir + "\\libs\\ImageEditor.jar;" + Main.dir + "\\libs\\json.jar;" + Main.dir + "\\libs\\commons-io-1.4.jar;\"");
            res.add("org.come.test.Main");
            if (Main.tabName == null) {
                Main.tabName = System.currentTimeMillis() + "";
            }
            res.add("-name=" + Main.tabName);
            res.add("-index=" + id);
            res.add("-Dfile.encoding=UTF-8");
        }
        else {
            res.add("sdls.dll");
            if (Main.tabName == null) {
                Main.tabName = System.currentTimeMillis() + "";
            }
            res.add("-name=" + Main.tabName);
            res.add("-index=" + id);
        }
        ProcessBuilder processBuilder = new ProcessBuilder(res);
        processBuilder.directory(new File(Main.dir + "\\"));
        return processBuilder;
    }
    
    public JPanel createContent(int id) {
        JPanel panel = new JPanel() {
            @Override
            public void setVisible(boolean aFlag) {
                if (aFlag == this.isVisible()) {
                    return;
                }
                super.setVisible(aFlag);
                if (aFlag) {
                    if (id >= 0) {
                        if (TabJFrame.shouId != id) {
                            TabJFrame.this.showGame(id);
                        }
                    }
                    else if (TabJFrame.index > 0 && !Main.addIng) {
                        int ii = TabJFrame.shouId;
                        Main.addIng = true;
                        TabJFrame.this.jTabbedPane.setSelectedIndex(ii);
                        Main.addIng = false;
                    }
                }
            }
        };
        return panel;
    }
    
    public static void setShouId(int shouId) {
        TabJFrame.shouId = shouId;
    }
    
    public static void setIndex(int index) {
        TabJFrame.index = index;
    }
    
    public static ServerInfo getServerInfo() {
        return TabJFrame.serverInfo;
    }
    
    public static void setServerInfo(ServerInfo serverInfo) {
        TabJFrame.serverInfo = serverInfo;
    }
    
    public CustomTitleBarUI getCustomTitleBarUI() {
        return this.customTitleBarUI;
    }
    
    public void setCustomTitleBarUI(CustomTitleBarUI customTitleBarUI) {
        this.customTitleBarUI = customTitleBarUI;
    }
    
    static {
        TabJFrame.index = 0;
        TabJFrame.shouId = 0;
        TabJFrame.start = Boolean.valueOf(false);
    }
}
