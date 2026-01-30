package com.tool.btn;

import java.io.IOException;
import java.util.Iterator;
import org.come.Jpanel.CustomTitleBarUI;
import java.util.Map;
import com.tool.tab.TabJFrame;
import javax.swing.JOptionPane;
import com.tool.tab.Main;
import java.awt.event.MouseEvent;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import javax.swing.JFrame;

public class TitlelBtn extends MoBanBtn
{
    private int caozuo;
    private Boolean b;
    private Boolean gl;
    private TitlelBtn delete;
    private int changForm;
    private JFrame jFrame;
    private int paneIndex;
    
    public TitlelBtn(String iconpath, int type, String text, int caozuo) {
        super(iconpath, type);
        this.b = Boolean.valueOf(false);
        this.gl = Boolean.valueOf(false);
        this.changForm = 1;
        this.paneIndex = 0;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT1);
        this.setForeground(Color.orange);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
    }
    
    public TitlelBtn(String iconpath, int type, String text, int caozuo, JFrame jFrame) {
        super(iconpath, type);
        this.b = Boolean.valueOf(false);
        this.gl = Boolean.valueOf(false);
        this.changForm = 1;
        this.paneIndex = 0;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT1);
        this.setForeground(Color.orange);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.jFrame = jFrame;
        this.caozuo = caozuo;
    }
    
    public TitlelBtn(String iconpath, int type, String text, int caozuo, Boolean b, TitlelBtn t) {
        super(iconpath, type);
        this.b = Boolean.valueOf(false);
        this.gl = Boolean.valueOf(false);
        this.changForm = 1;
        this.paneIndex = 0;
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT1);
        this.setForeground(Color.orange);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caozuo = caozuo;
        this.b = b;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.caozuo == 99) {
            if (Main.tabJFrame.customTitleBarUI.getTitlelBtns().size() > 4) {
                JOptionPane.showMessageDialog(Main.tabJFrame, "最多5个窗口,如需更多请再次运行客户端。", "提示信息", 1);
                return;
            }
            TabJFrame jFrame = (TabJFrame)this.jFrame;
            int i = jFrame.addGame();
            Main.tabJFrame.customTitleBarUI.setIndex(Integer.valueOf(i));
            Main.tabJFrame.customTitleBarUI.changeSelect(i);
            TitlelBtn titlelBtn = Main.tabJFrame.customTitleBarUI.refreshRoles();
            Main.tabJFrame.jTabbedPane.setSelectedIndex(i);
            titlelBtn.nochoose(null);
            Main.tabJFrame.setSize(1026, 768);
            Main.tabJFrame.customTitleBarUI.initJD();
            TabJFrame tabJFrame = Main.tabJFrame;
            TabJFrame.start = Boolean.valueOf(true);
            this.chatBoxShowAndHide();
            Main.tabJFrame.showGame(i);
        }
        else if (this.caozuo == 96) {
            Main.tabJFrame.setExtendedState(1);
        }
        else if (this.caozuo >= 100 && this.caozuo <= 9999) {
            int j = this.caozuo - 100;
            Process process = (Process)Main.tabJFrame.getGames().get(Integer.valueOf(j));
            if (process != null) {
                process.destroy();
            }
            Iterator<Map.Entry<Integer, Process>> iterator = Main.tabJFrame.games.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Process> entry = (Map.Entry<Integer, Process>)iterator.next();
                Main.hwnds.put(entry.getKey(), null);
                process = (Process)Main.tabJFrame.games.get(entry.getKey());
                if (!process.isAlive()) {
                    Main.tabJFrame.removeGame((int)entry.getKey());
                    iterator.remove();
                }
            }
            Main.tabJFrame.roles.remove(Integer.valueOf(j));
            Main.tabJFrame.removeGame(j);
            TitlelBtn titlelBtn = Main.tabJFrame.customTitleBarUI.refreshRoles();
            if (titlelBtn != null) {
                titlelBtn.nochoose(null);
            }
        }
        else if (this.caozuo == 97) {
            System.exit(1);
        }
        else if ((boolean)this.b) {
            CustomTitleBarUI.isShow = Boolean.valueOf(false);
            Main.tabJFrame.customTitleBarUI.refreshRoles();
            Main.tabJFrame.customTitleBarUI.setIndex(Integer.valueOf(this.caozuo));
            Main.tabJFrame.customTitleBarUI.changeSelect(this.caozuo);
            Main.tabJFrame.jTabbedPane.setSelectedIndex(this.caozuo);
            Main.tabJFrame.showGame(this.caozuo);
            this.chatBoxShowAndHide();
            CustomTitleBarUI.isShow = Boolean.valueOf(true);
        }
    }
    
    private void chatBoxShowAndHide() {
        Main.tabJFrame.games.forEach((k, v)/* java.lang.Integer,java.lang.Process, */ -> {
            Process process = (Process)Main.tabJFrame.games.get(k);
            if ((int)k == this.caozuo) {
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
    public void mouseEntered(MouseEvent e) {
        if (this.btn == -1) {
            return;
        }
        if (this.btn != 0 && this.zhen != 2) {
            this.btnchange(1);
        }
        this.type = 1;
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (!(boolean)this.gl) {
            if (this.btn == -1) {
                return;
            }
            if ((this.btn != 0 || this.btn != -1) && this.zhen != 2) {
                this.btnchange(0);
            }
            this.type = 0;
        }
    }
    
    public Boolean getGl() {
        return this.gl;
    }
    
    public void setGl(Boolean gl) {
        this.gl = gl;
    }
    
    public TitlelBtn getDelete() {
        return this.delete;
    }
    
    public void setDelete(TitlelBtn delete) {
        this.delete = delete;
    }
    
    public int getChangForm() {
        return this.changForm;
    }
    
    public void setChangForm(int changForm) {
        this.changForm = changForm;
    }
    
    public int getCaozuo() {
        return this.caozuo;
    }
    
    public void setCaozuo(int caozuo) {
        this.caozuo = caozuo;
    }
}
