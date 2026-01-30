
package org.come.XuanBao;


import com.tool.btn.MoBanBtn;
import com.tool.role.RoleData;
import com.tool.tcpimg.UIUtils;
import org.come.Frame.NPCJfram;
import org.come.Frame.ZhuFrame;
import org.come.bean.LoginResult;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.FormsManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;


public class XuanBaoTJBtn extends MoBanBtn {
    private int caozuo;
    private XuanBaoTJJpanel xuanBaoTJJpanel;
    private static Boolean is = false;
    private static Boolean is1 = false;
    private static Boolean is2 = false;

    public XuanBaoTJBtn(String iconpath, int type, int btnname, String text) {
        super(iconpath, type);
        this.caozuo = btnname;
        if (text != null) {
            setText(text);
            setFont(UIUtils.TEXT_FONT);
            setForeground(Color.yellow);
            setVerticalTextPosition(0);
            setHorizontalTextPosition(0);
        }
    }


    public XuanBaoTJBtn(String iconpath, int type, int btnname, String text, XuanBaoTJJpanel xuanBaoTJJpanel) {
        super(iconpath, type);
        this.caozuo = btnname;
        setText(text);
        setFont(UIUtils.TEXT_FONT);
        setVerticalTextPosition(0);
        setHorizontalTextPosition(0);
        this.xuanBaoTJJpanel = xuanBaoTJJpanel;
    }

    public XuanBaoTJBtn(String iconpath, int type, int btnname, String text, XuanBaoTJJpanel xuanBaoTJJpanel, Color[] colors) {
        super(iconpath, type, colors);
        this.caozuo = btnname;
        setText(text);
        setFont(UIUtils.TEXT_FONT);
        setVerticalTextPosition(0);
        setHorizontalTextPosition(0);
        this.xuanBaoTJJpanel = xuanBaoTJJpanel;
    }


    public void chooseyes() {
    }


    public void chooseno() {
    }


    public void nochoose(MouseEvent e) {
        if (caozuo == 1) {
            if (!is) {
                is = true;
                xuanBaoTJJpanel.getS1().setVisible(is);
                xuanBaoTJJpanel.getS2().setVisible(is);
                xuanBaoTJJpanel.getS3().setVisible(is);
            } else {
                is = false;
                xuanBaoTJJpanel.getS1().setVisible(is);
                xuanBaoTJJpanel.getS2().setVisible(is);
                xuanBaoTJJpanel.getS3().setVisible(is);
            }
        } else if (caozuo == 2) {
            if (!is1) {
                is1 = true;
                xuanBaoTJJpanel.getT1().setVisible(is1);
                xuanBaoTJJpanel.getT2().setVisible(is1);
                xuanBaoTJJpanel.getT3().setVisible(is1);
                xuanBaoTJJpanel.getT4().setVisible(is1);
            } else {
                is1 = false;
                xuanBaoTJJpanel.getT1().setVisible(is1);
                xuanBaoTJJpanel.getT2().setVisible(is1);
                xuanBaoTJJpanel.getT3().setVisible(is1);
                xuanBaoTJJpanel.getT4().setVisible(is1);
            }
        } else if (caozuo == 3) {
            if (!is2) {
                is2 = true;
                xuanBaoTJJpanel.getP1().setVisible(is2);
                xuanBaoTJJpanel.getP2().setVisible(is2);
                xuanBaoTJJpanel.getP3().setVisible(is2);
                xuanBaoTJJpanel.getP4().setVisible(is2);
            } else {
                is2 = false;
                xuanBaoTJJpanel.getP1().setVisible(is2);
                xuanBaoTJJpanel.getP2().setVisible(is2);
                xuanBaoTJJpanel.getP3().setVisible(is2);
                xuanBaoTJJpanel.getP4().setVisible(is2);
            }
        }else if (caozuo == 4) {
            xuanBaoTJJpanel.findXBList();
        } else if (caozuo == 11 || caozuo == 12 || caozuo == 13) {
            JLabel source = (JLabel) e.getSource();
            xuanBaoTJJpanel.getB1().setText(source.getText());
            xuanBaoTJJpanel.getS1().setVisible(false);
            xuanBaoTJJpanel.getS2().setVisible(false);
            xuanBaoTJJpanel.getS3().setVisible(false);
            is = false;
            xuanBaoTJJpanel.findXBList();
        } else if (caozuo == 21 || caozuo == 22 || caozuo == 23|| caozuo == 24) {
            JLabel source = (JLabel) e.getSource();
            xuanBaoTJJpanel.getB2().setText(source.getText());
            xuanBaoTJJpanel.getT1().setVisible(false);
            xuanBaoTJJpanel.getT2().setVisible(false);
            xuanBaoTJJpanel.getT3().setVisible(false);
            xuanBaoTJJpanel.getT4().setVisible(false);
            is1 = false;
            xuanBaoTJJpanel.findXBList();
        } else if (caozuo == 31 || caozuo == 32 || caozuo == 33|| caozuo == 34) {
            JLabel source = (JLabel) e.getSource();
            xuanBaoTJJpanel.getB3().setText(source.getText());
            xuanBaoTJJpanel.getP1().setVisible(false);
            xuanBaoTJJpanel.getP2().setVisible(false);
            xuanBaoTJJpanel.getP3().setVisible(false);
            xuanBaoTJJpanel.getP4().setVisible(false);
            is2 = false;
            xuanBaoTJJpanel.findXBList();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}

