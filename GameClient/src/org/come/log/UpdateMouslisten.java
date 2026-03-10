package org.come.log;

import java.io.IOException;
import com.main.UpdateMain;
import java.awt.event.MouseEvent;
import org.come.login.LoginJpanel;
import org.come.login.SpriteBtn;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class UpdateMouslisten implements MouseListener
{
    private int i;
    private SpriteBtn btn;
    private LoginJpanel loginJpanel;
    
    public UpdateMouslisten(int i, SpriteBtn btn) {
        this.i = i;
        this.btn = btn;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.btn.isChoose()) {
            if (this.btn.getZhen() != 2) {
                this.btn.btn(2);
            }
        }
        else {
            this.btn.btn(2);
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (!this.btn.isChoose()) {
            this.btn.btn(0);
            if (this.i == 2) {
                System.exit(1);
            }
            if (this.i == 1) {
                UpdateMain.setJframe.setVisible(!UpdateMain.setJframe.isVisible());
            }
            else if (this.i == 4) {
                // 安全修复：原逻辑会把外部 DLL 当成可执行入口直接拉起。
                // 当前先保留按钮与界面交互，但禁止继续执行危险二进制文件。
                JOptionPane.showMessageDialog(null,
                        "已禁用不安全的启动器执行逻辑，请后续替换为安全启动流程。",
                        "安全提示",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.btn.getZhen() != 2) {
            this.btn.btn(1);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (this.btn.getZhen() != 2) {
            this.btn.btn(0);
        }
    }
}
