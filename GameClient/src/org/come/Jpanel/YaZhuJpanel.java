package org.come.Jpanel;

import com.tool.role.RoleData;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.come.until.ScrollUI;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import com.tool.tcpimg.RichLabel;
import com.tool.btn.YaZhuBtn;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class YaZhuJpanel extends JPanel
{
    public static int max;
    private int type;
    private JTextField lingText;
    private JLabel textLab;
    private YaZhuBtn maxBtn;
    private YaZhuBtn yatn;
    private RichLabel richLabel;
    private JScrollPane jScrollPane;
    private ImageIcon icon;
    
    public YaZhuJpanel() {
        (this.lingText = new JTextField()).setForeground(Color.white);
        this.lingText.setOpaque(false);
        this.lingText.setBorder(BorderFactory.createEmptyBorder());
        this.lingText.setFont(UIUtils.TEXT_FONT78);
        this.lingText.setCaretColor(Color.WHITE);
        this.lingText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                YaZhuJpanel.this.lingTextUpdate(e);
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.textLab = new JLabel();
        this.richLabel = new RichLabel("", UIUtils.TEXT_FONT1);
        (this.jScrollPane = new JScrollPane(this.richLabel)).setVerticalScrollBarPolicy(22);
        this.jScrollPane.getVerticalScrollBar().setUI(new ScrollUI());
        this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(369, 270));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 713);
            offBtn.setBounds(332, 10, 25, 25);
            this.add(offBtn);
            this.lingText.setBounds(229, 40, 54, 18);
            this.textLab.setBounds(60, 46, 70, 70);
            this.jScrollPane.setBounds(50, 136, 282, 100);
            (this.maxBtn = new YaZhuBtn("inkImg/button/2.png", 1, "最大", UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, 0, this)).setBounds(288, 39, 34, 17);
            (this.yatn = new YaZhuBtn("inkImg/button/32.png", 1, "押注", UIUtils.COLOR_BLACK, UIUtils.TEXT_HY88, 1, this)).setBounds(228, 95, 60, 26);
        }
        else {
            this.setPreferredSize(new Dimension(338, 296));
            this.setBackground(UIUtils.Color_BACK);
            this.setLayout(null);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/S74.png", 1, 713);
            offBtn.setBounds(313, 0, 25, 25);
            this.add(offBtn);
            this.lingText.setBounds(202, 52, 54, 18);
            this.textLab.setBounds(32, 60, 70, 70);
            this.jScrollPane.setBounds(28, 150, 272, 98);
            (this.maxBtn = new YaZhuBtn("inkImg/hongmu/B30h.png", 1, "最大", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT2, 0, this)).setBounds(258, 52, 34, 17);
            (this.yatn = new YaZhuBtn("inkImg/hongmu/w50.png", 1, "押注", UIUtils.COLOR_BTNPUTONG, UIUtils.TEXT_FONT_17, 1, this)).setBounds(200, 110, 59, 25);
        }
        this.add(this.lingText);
        this.add(this.textLab);
        this.add(this.maxBtn);
        this.add(this.yatn);
        this.add(this.jScrollPane);
    }
    
    private void lingTextUpdate(KeyEvent e) {
        int charstr = e.getKeyChar();
        String str = this.lingText.getText();
        if (charstr < 48 || charstr > 57) {
            e.consume();
        }
        if (str.length() == 1 && str.equals("0")) {
            this.lingText.setText("");
        }
        if (str.length() > 4 && Integer.parseInt(str) > YaZhuJpanel.max) {
            e.consume();
            this.lingText.setText(YaZhuJpanel.max + "");
        }
    }
    
    public void initType(int type) {
        this.type = type;
        this.lingText.setText("");
        this.textLab.setIcon(new ImageIcon("img/jiegua/81" + (type + 1) + ".png"));
    }
    
    public void setText(String text) {
        StringBuffer buffer = new StringBuffer();
        String[] vals = text.split("\\|");
        if (!vals[this.type].equals("null")) {
            String[] vs;
            for (String v : vs = vals[this.type].split(",")) {
                if (buffer.length() > 0) {
                    buffer.append("#r");
                }
                buffer.append("#G");
                buffer.append(v.split("_")[0]);
                buffer.append("#cBBA54B");
                buffer.append("押注");
                buffer.append("#R");
                buffer.append(v.split("_")[1]);
                buffer.append("灵力");
            }
        }
        this.richLabel.setText(buffer.toString());
        Dimension dimension = this.richLabel.computeSize(240);
        this.richLabel.setSize(dimension);
        this.richLabel.setPreferredSize(dimension);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("img/jiegua/xz1.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 0, 0, this);
            g.setFont(UIUtils.TEXT_FONT78);
            g.setColor(Color.WHITE);
            g.drawString(RoleData.getRoleData().getLoginResult().getScoretype("解卦灵力").toString(), 229, 80);
        }
        else {
            if (this.icon == null) {
                this.icon = CutButtonImage.getImage("img/jiegua/xz2.png", -1, -1);
            }
            g.drawImage(this.icon.getImage(), 0, 0, this);
            g.setFont(UIUtils.TEXT_FONT78);
            g.setColor(Color.WHITE);
            g.drawString(RoleData.getRoleData().getLoginResult().getScoretype("解卦灵力").toString(), 202, 92);
        }
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setLingText(int value) {
        this.lingText.setText(value + "");
    }
    
    public String getLingText() {
        return this.lingText.getText();
    }
    
    static {
        YaZhuJpanel.max = 20000;
    }
}
