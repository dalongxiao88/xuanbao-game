package org.come.Jpanel;

import java.awt.Graphics;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BorderFactory;
import com.updateNew.MyIsif;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.btn.RoleCaoZuoBtn;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class FindDropJpanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    private static JTextField findName;
    private RoleCaoZuoBtn sureGive;
    private RoleCaoZuoBtn deleteGive;
    private ImageIcon icon;
    
    public FindDropJpanel() throws Exception {
        this.setPreferredSize(new Dimension(350, 269));
        this.setBackground(UIUtils.Color_BACK);
        this.setLayout(null);
        if (MyIsif.getStyle().equals("水墨")) {
            (FindDropJpanel.findName = new JTextField()).setBounds(70, 119, 205, 20);
            FindDropJpanel.findName.setOpaque(false);
            FindDropJpanel.findName.setBorder(BorderFactory.createEmptyBorder());
            FindDropJpanel.findName.setForeground(Color.white);
            FindDropJpanel.findName.setCaretColor(Color.white);
            FindDropJpanel.findName.setFont(new Font("宋体", 0, 15));
            this.add(FindDropJpanel.findName);
            (this.sureGive = new RoleCaoZuoBtn("inkImg/button1/B21.png", 1, "搜 索", 600, UIUtils.COLOR_BLACK)).setBounds(70, 190, 79, 24);
            this.add(this.sureGive);
            (this.deleteGive = new RoleCaoZuoBtn("inkImg/button1/B21.png", 1, "取 消", 601, UIUtils.COLOR_BLACK)).setBounds(202, 190, 79, 24);
            this.add(this.deleteGive);
        }
        else {
            (FindDropJpanel.findName = new JTextField()).setBounds(128, 68, 128, 20);
            FindDropJpanel.findName.setOpaque(false);
            FindDropJpanel.findName.setBorder(BorderFactory.createEmptyBorder());
            FindDropJpanel.findName.setForeground(Color.white);
            FindDropJpanel.findName.setCaretColor(Color.white);
            FindDropJpanel.findName.setFont(new Font("宋体", 0, 15));
            this.add(FindDropJpanel.findName);
            (this.sureGive = new RoleCaoZuoBtn("inkImg/hongmu/50x50.png", 1, "搜索", 600, UIUtils.COLOR_BTNPUTONG)).setBounds(82, 100, 68, 26);
            this.add(this.sureGive);
            (this.deleteGive = new RoleCaoZuoBtn("inkImg/hongmu/50x50.png", 1, "取消", 601, UIUtils.COLOR_BTNPUTONG)).setBounds(182, 100, 68, 26);
            this.add(this.deleteGive);
        }
    }
    
    public static void findEnsure() {
        if (FindDropJpanel.findName.getText() == null || FindDropJpanel.findName.getText().length() == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("你要查什么呢？");
            return;
        }
        String senmes = Agreement.getAgreement().findDropAgreement(FindDropJpanel.findName.getText());
        SendMessageUntil.toServer(senmes);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B330.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 350, 269, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/chaxun.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 296, 257, this);
        }
    }
    
    public RoleCaoZuoBtn getSureGive() {
        return this.sureGive;
    }
    
    public void setSureGive(RoleCaoZuoBtn sureGive) {
        this.sureGive = sureGive;
    }
    
    public RoleCaoZuoBtn getDeleteGive() {
        return this.deleteGive;
    }
    
    public void setDeleteGive(RoleCaoZuoBtn deleteGive) {
        this.deleteGive = deleteGive;
    }
    
    public static JTextField getFindName() {
        return FindDropJpanel.findName;
    }
    
    public static void setFindName(JTextField findName) {
        FindDropJpanel.findName = findName;
    }
}
