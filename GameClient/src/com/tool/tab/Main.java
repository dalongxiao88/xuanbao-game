package com.tool.tab;

import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;
import java.util.HashMap;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import java.util.Iterator;
import org.come.socket.GameClient;
import com.sun.jna.platform.win32.User32;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import com.updateNew.MyIsif;
import java.util.Map;
import com.sun.jna.platform.win32.WinDef;
import java.awt.Button;

public class Main
{
    public static TabJFrame tabJFrame;
    private static Button bt;
    public static String dir;
    public static WinDef.HWND showHWND;
    public static WinDef.HWND ts;
    public static boolean exit;
    public static boolean addIng;
    public static int lastX;
    public static int lastY;
    public static String title;
    private static Main instance;
    public static String tabName;
    public static Map<Integer, WinDef.HWND> hwnds;
    
    public static Main getInstance() {
        return Main.instance;
    }
    
    public static void main(String[] args) {
        MyIsif.ifs = "DK";
        Main.tabJFrame = new TabJFrame();
        Main.tabJFrame.customTitleBarUI.refreshRoles();
        Main.tabJFrame.setVisible(true);
        TabJFrame tabJFrame = Main.tabJFrame;
        TabJFrame.start = Boolean.valueOf(true);
        Main.tabJFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Main.tabJFrame.games.forEach((k, p)/* java.lang.Integer,java.lang.Process, */ -> p.destroy());
                Main.exit = true;
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                }, 1000L);
            }
        });
        Main.tabJFrame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                User32.INSTANCE.AttachThreadInput(new WinDef.DWORD((long)User32.INSTANCE.GetWindowThreadProcessId(Main.ts, null)), new WinDef.DWORD((long)User32.INSTANCE.GetWindowThreadProcessId(Main.showHWND, null)), true);
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread(()/*  */ -> {
            Main.tabJFrame.games.forEach((k, p)/* java.lang.Integer,java.lang.Process, */ -> p.destroy());
            Main.exit = true;
            return;
        }));
        new Thread(()/*  */ -> {
            while (!Main.exit) {
                Main.tabJFrame.customTitleBarUI.repaint();
                try {
                    for (Map.Entry<Integer, Process> entry : Main.tabJFrame.games.entrySet()) {
                        Main.hwnds.put(entry.getKey(), null);
                        Process process = (Process)Main.tabJFrame.games.get(entry.getKey());
                        if (!process.isAlive()) {}
                    }
                    if (Main.tabJFrame.getX() != Main.lastX || Main.tabJFrame.getY() != Main.lastY) {
                        Process process2 = (Process)Main.tabJFrame.games.get(Integer.valueOf(Main.tabJFrame.getShouId()));
                        try {
                            process2.getOutputStream().write((Main.tabJFrame.getX() + ":" + Main.tabJFrame.getY() + "\n").getBytes());
                            process2.getOutputStream().flush();
                            Main.lastY = Main.tabJFrame.getY();
                            Main.lastX = Main.tabJFrame.getX();
                        }
                        catch (Exception ex) {}
                    }
                    if (Main.tabJFrame.games.size() <= 0) {
                        System.exit(0);
                    }
                    if (Main.ts == null) {
                        Main.ts = User32.INSTANCE.FindWindow("SunAwtFrame", Main.title);
                    }
                    if (Main.hwnds.containsValue(null)) {
                        Main.hwnds.forEach((k, v)/* java.lang.Integer,com.sun.jna.platform.win32.WinDef.HWND, */ -> {
                            WinDef.HWND hWnd = User32.INSTANCE.FindWindow("SunAwtFrame", GameClient.BT + k + Main.tabName);
                            if (hWnd != null) {
                                User32.INSTANCE.SetParent(hWnd, Main.ts);
                                User32.INSTANCE.SetWindowPos(hWnd, null, 0, 0, 0, 0, 135);
                            }
                            return;
                        });
                    }
                    User32.INSTANCE.EnumChildWindows(Main.ts, (hWnd, arg1)/* com.sun.jna.platform.win32.WinDef.HWND,com.sun.jna.Pointer, */ -> {
                        byte[] windowText = new byte[512];
                        User32_2.INSTANCE.GetWindowTextA(hWnd, windowText, 512);
                        String title = Native.toString(windowText, "GBK");
                        if (title.equals(GameClient.BT + Main.tabJFrame.getShouId() + Main.tabName)) {
                            if (!User32.INSTANCE.IsWindowVisible(hWnd)) {
                                Main.showHWND = hWnd;
                                User32.INSTANCE.ShowWindow(hWnd, 9);
                            }
                            WinDef.RECT qqwin_rect = new WinDef.RECT();
                            User32.INSTANCE.GetWindowRect(hWnd, qqwin_rect);
                            int initWidth = 1026;
                            int tmpWidth = qqwin_rect.right - qqwin_rect.left;
                            if (tmpWidth == 806) {
                                tmpWidth = 806;
                                initWidth = 806;
                            }
                            int width = Math.max(tmpWidth, initWidth);
                            int height = Math.max(qqwin_rect.bottom - qqwin_rect.top, 720);
                            int min = Math.min(height + 48, 835);
                            if (qqwin_rect.bottom - qqwin_rect.top == 595) {
                                Main.tabJFrame.setSize(tmpWidth + 10, 660);
                                Main.tabJFrame.customTitleBarUI.setSize(tmpWidth + 10, 662);
                            }
                            else if (qqwin_rect.bottom - qqwin_rect.top == 710) {
                                Main.tabJFrame.setSize((width == 1026) ? width : (width + 10), (width == 1026) ? 768 : 831);
                                Main.tabJFrame.customTitleBarUI.setSize((width == 1026) ? width : (width + 10), (width == 1026) ? 768 : 833);
                            }
                            else if (width == 1026) {
                                Main.tabJFrame.setSize(width + 6, (width == 1026) ? 831 : 831);
                                Main.tabJFrame.customTitleBarUI.setSize(width + 6, (width == 1026) ? 833 : 833);
                            }
                            else {
                                Main.tabJFrame.setSize((width == 1026) ? width : (width + 9), (width == 1026) ? 768 : 833);
                                Main.tabJFrame.customTitleBarUI.setSize((width == 1026) ? width : (width + 9), (width == 1026) ? 768 : 833);
                            }
                        }
                        else if (User32.INSTANCE.IsWindowVisible(hWnd)) {
                            User32.INSTANCE.ShowWindow(hWnd, 0);
                        }
                        return true;
                    }, null);
                    Thread.sleep(50L);
                }
                catch (InterruptedException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
            return;
        }).start();
    }
    
    static {
        Main.dir = System.getProperty("user.dir");
        Main.ts = null;
        Main.exit = false;
        Main.addIng = true;
        Main.lastX = -1;
        Main.lastY = -1;
        Main.title = GameClient.BT;
        Main.instance = null;
        Main.tabName = null;
        Main.hwnds = new HashMap<>();
    }
    
    public interface User32_2 extends StdCallLibrary
    {
        public static final User32_2 INSTANCE = (User32_2)Native.loadLibrary("user32", User32_2.class);
        
        boolean EnumWindows(WinUser.WNDENUMPROC p0, Pointer p1);
        
        int GetWindowTextA(WinDef.HWND p0, byte[] p1, int p2);
    }
}
