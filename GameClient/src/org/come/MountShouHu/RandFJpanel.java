package org.come.MountShouHu;

import java.util.List;
import org.come.bean.LoginResult;
import java.math.BigDecimal;
import org.come.until.Util;
import com.tool.role.RoleData;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import org.come.until.FormsManagement;
import java.util.Objects;
import org.come.until.UserMessUntil;
import org.come.bean.Skill;
import org.come.Frame.MsgJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.JLabel;
import org.come.entity.Goodstable;
import com.tool.btn.MountShouhuBtn;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class RandFJpanel extends JPanel
{
    public ImageIcon ico;
    public ImageIcon ico1;
    public ImageIcon ico2;
    public ImageIcon IamelingqiaoL;
    public ImageIcon Iamelingqiao;
    public ImageIcon ditu;
    public ImageIcon jindutiao;
    public int type;
    public MountShouhuBtn xuanxiangka1;
    public MountShouhuBtn xuanxiangka2;
    public MountShouhuBtn kaishifuling;
    public MountShouhuBtn kaishironglian;
    public MountShouhuBtn FST;
    public MountShouhuBtn FLT;
    public MountShouhuBtn RST1;
    public MountShouhuBtn RST2;
    public MountShouhuBtn img1;
    public MountShouhuBtn img2;
    public MountShouhuBtn img3;
    public MountShouhuBtn img4;
    public Goodstable FL;
    public Goodstable RL1;
    public Goodstable RL2;
    public JLabel panel1;
    public JLabel panel2;
    public JLabel panel3;
    public JLabel panel4;
    public int num;
    
    public RandFJpanel() {
        this.ico = new ImageIcon("inkImg/Client/守护石附灵背景.png");
        this.ico1 = new ImageIcon("inkImg/Client/守护石融炼背景.png");
        this.IamelingqiaoL = new ImageIcon("inkImg/Client/灵窍_点亮.png");
        this.Iamelingqiao = new ImageIcon("inkImg/Client/灵窍_未点亮.png");
        this.ditu = new ImageIcon("inkImg/Client/12862-1.png");
        this.jindutiao = new ImageIcon("inkImg/Client/12861-1.png");
        this.type = 1;
        this.FL = null;
        this.RL1 = null;
        this.RL2 = null;
        this.panel1 = new JLabel();
        this.panel2 = new JLabel();
        this.panel3 = new JLabel();
        this.panel4 = new JLabel();
        this.num = 0;
        this.setPreferredSize(new Dimension(740, 516));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        try {
            String imagePath = "inkImg/Client/灵元晶.png";
            File imageFile = new File(imagePath);
            BufferedImage originalImage = ImageIO.read(imageFile);
            int maxSize = Math.max(originalImage.getWidth(), originalImage.getHeight());
            int scaledWidth = originalImage.getWidth() * 50 / maxSize;
            int scaledHeight = originalImage.getHeight() * 50 / maxSize;
            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, 4);
            this.ico2 = new ImageIcon(scaledImage);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        (this.xuanxiangka1 = new MountShouhuBtn("inkImg/Client/选中按钮.png", 1, 8, "附灵", UIUtils.Black, UIUtils.TEXT_HY19, this)).setBounds(70, 25, 100, 33);
        this.add(this.xuanxiangka1);
        (this.xuanxiangka2 = new MountShouhuBtn("inkImg/Client/未选中按钮.png", 1, 9, "熔炼", UIUtils.Black, UIUtils.TEXT_HY19, this)).setBounds(170, 25, 100, 33);
        this.add(this.xuanxiangka2);
        (this.kaishifuling = new MountShouhuBtn("inkImg/Client/92X26按钮.png", 1, 15, "开始附灵", UIUtils.Black, UIUtils.TEXT_HY16, this)).setBounds(330, 455, 92, 26);
        this.add(this.kaishifuling);
        (this.kaishironglian = new MountShouhuBtn("inkImg/Client/92X26按钮.png", 1, 16, "开始熔炼", UIUtils.Black, UIUtils.TEXT_HY16, this)).setBounds(580, 455, 92, 26);
        this.add(this.kaishironglian);
        (this.FST = new MountShouhuBtn("inkImg/Client/加号.png", 1, 17, this)).setBounds(130, 185, 17, 17);
        this.add(this.FST);
        (this.FLT = new MountShouhuBtn("inkImg/Client/加号.png", 1, 18, this)).setVisible(false);
        this.FLT.setBounds(100, 150, 17, 17);
        this.add(this.FLT);
        (this.RST1 = new MountShouhuBtn("inkImg/Client/加号.png", 1, 19, this)).setBounds(135, 95, 17, 17);
        this.add(this.RST1);
        (this.RST2 = new MountShouhuBtn("inkImg/Client/加号.png", 1, 20, this)).setBounds(485, 95, 17, 17);
        this.add(this.RST2);
        (this.img1 = new MountShouhuBtn("inkImg/Client/守护石.png", 1, 21, this)).setBounds(115, 173, 45, 45);
        this.img1.addMouseListener(new ShouhuPackGoodsMouslisten(10, this));
        this.img1.setVisible(false);
        this.add(this.img1);
        (this.img2 = new MountShouhuBtn("inkImg/Client/守护石.png", 1, 22, this)).setBounds(this.FST.getX(), this.FST.getY(), 45, 45);
        (this.img3 = new MountShouhuBtn("inkImg/Client/守护石.png", 1, 23, this)).setBounds(120, 86, 45, 45);
        this.img3.addMouseListener(new ShouhuPackGoodsMouslisten(11, this));
        this.img3.setVisible(false);
        this.add(this.img3);
        (this.img4 = new MountShouhuBtn("inkImg/Client/守护石.png", 1, 24, this)).setBounds(470, 86, 45, 45);
        this.img4.addMouseListener(new ShouhuPackGoodsMouslisten(12, this));
        this.img4.setVisible(false);
        this.add(this.img4);
        this.panel1.setOpaque(false);
        this.panel1.setForeground(Color.GREEN);
        this.panel1.setFont(UIUtils.TEXT_NAME_FONT);
        this.panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (RandFJpanel.this.panel1.getText() != null) {
                    MsgJframe.getJframe().getJapnel().skillmsg((Skill)Objects.requireNonNull(UserMessUntil.getskill1(RandFJpanel.this.panel1.getText().split(":")[0])));
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                FormsManagement.HideForm(46);
            }
        });
        this.add(this.panel1);
        this.panel2.setOpaque(false);
        this.panel2.setForeground(Color.GREEN);
        this.panel2.setFont(UIUtils.TEXT_NAME_FONT);
        this.panel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (RandFJpanel.this.panel2.getText() != null) {
                    MsgJframe.getJframe().getJapnel().skillmsg(UserMessUntil.getskill1(RandFJpanel.this.panel2.getText().split(":")[0]));
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                FormsManagement.HideForm(46);
            }
        });
        this.add(this.panel2);
        this.panel3.setOpaque(false);
        this.panel3.setForeground(Color.GREEN);
        this.panel3.setFont(UIUtils.TEXT_NAME_FONT);
        this.panel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (RandFJpanel.this.panel3.getText() != null) {
                    MsgJframe.getJframe().getJapnel().skillmsg((Skill)Objects.requireNonNull(UserMessUntil.getskill1(RandFJpanel.this.panel3.getText().split(":")[0])));
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                FormsManagement.HideForm(46);
            }
        });
        this.add(this.panel3);
        this.panel4.setOpaque(false);
        this.panel4.setForeground(Color.GREEN);
        this.panel4.setFont(UIUtils.TEXT_NAME_FONT);
        this.panel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (RandFJpanel.this.panel4.getText() != null) {
                    MsgJframe.getJframe().getJapnel().skillmsg((Skill)Objects.requireNonNull(UserMessUntil.getskill1(RandFJpanel.this.panel4.getText().split(":")[0])));
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                FormsManagement.HideForm(46);
            }
        });
        this.add(this.panel4);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        LoginResult login = RoleData.getRoleData().getLoginResult();
        this.panel1.setVisible(false);
        this.panel2.setVisible(false);
        this.panel3.setVisible(false);
        this.panel4.setVisible(false);
        if (this.getType() == 1) {
            g.drawImage(this.ico.getImage(), 0, 0, 740, 516, null);
            Util.drawPrice(g, login.getGold(), 195, 300);
            Util.drawPrice(g, new BigDecimal(500000), 195, 270);
            if (ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getXuanzhong1() != -1) {
                g.drawImage(this.ico2.getImage(), 275, 170, this.ico2.getIconWidth() - 2, this.ico2.getIconHeight() - 2, null);
                this.FST.setVisible(false);
                Goodstable ltrGoods = MountShouhuBtn.getGoodType(2258L);
                int num = (ltrGoods == null) ? 0 : ((int)ltrGoods.getUsetime());
                if (num > 0) {
                    this.img1.setVisible(true);
                    g.setColor(Color.white);
                    g.drawString(num + "", 277, 182);
                }
                else {
                    this.img1.setVisible(true);
                }
            }
            else {
                this.img1.setVisible(false);
            }
            this.pain(g);
        }
        else {
            g.drawImage(this.ico1.getImage(), 0, 0, 740, 516, null);
            Util.drawPrice(g, login.getGold(), 435, 458);
            Util.drawPrice(g, new BigDecimal(1000000), 180, 458);
            g.setColor(Color.black);
            g.drawString("50", 180, 490);
            g.drawString(RoleData.getRoleData().getLoginResult().getShouhu() + "", 435, 490);
            if (ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getXuanzhong1() != -1) {
                this.img3.setVisible(true);
                this.RST1.setVisible(false);
            }
            else {
                this.img3.setVisible(false);
                this.RST1.setVisible(true);
            }
            if (ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getXuanzhong2() != -1) {
                this.img4.setVisible(true);
                this.RST2.setVisible(false);
            }
            else {
                this.img4.setVisible(false);
                this.RST2.setVisible(true);
            }
            this.pain1(g);
        }
    }
    
    public void pain(Graphics g) {
        super.paintComponent(g);
        if (ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getXuanzhong1() == -1) {
            return;
        }
        if (ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().size() == 0) {
            return;
        }
        int l = ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getXuanzhong1();
        if (l >= 0) {
            this.FL = (Goodstable)ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().get(l);
        }
        if (this.FL != null) {
            if (this.FL.getValue() != null && !this.FL.getValue().equals("")) {
                String[] mes = this.FL.getValue().split("\\|");
                g.setColor(Color.WHITE);
                for (int i = 0; i < 7; ++i) {
                    if (i == 5) {
                        if (mes.length > 6 && !mes[i + 1].equals("0")) {
                            g.drawImage(this.IamelingqiaoL.getImage(), 410, 290, 32, 35, null);
                            this.panel1.setBounds(440, 297, 200, 20);
                            this.panel1.setVisible(true);
                            this.panel1.setText(UserMessUntil.getSkillId(mes[i + 1].split("=")[0]).getSkillname() + ":" + mes[i + 1].split("=")[1] + "级");
                        }
                        else {
                            g.setColor(new Color(-205809026, true));
                            g.drawImage(this.Iamelingqiao.getImage(), 410, 290, 32, 35, null);
                            g.drawString("灵窍无灵气", 440, 315);
                        }
                    }
                    else if (i == 6) {
                        if (mes.length > 7 && !mes[i + 1].equals("0")) {
                            g.drawImage(this.IamelingqiaoL.getImage(), 410, 320, 32, 35, null);
                            this.panel2.setBounds(440, 327, 200, 20);
                            this.panel2.setVisible(true);
                            this.panel2.setText(UserMessUntil.getSkillId(mes[i + 1].split("=")[0]).getSkillname() + ":" + mes[i + 1].split("=")[1] + "级");
                        }
                        else {
                            g.setColor(new Color(-205809026, true));
                            g.drawImage(this.Iamelingqiao.getImage(), 410, 320, 32, 35, null);
                            g.drawString("灵窍无灵气", 440, 345);
                        }
                    }
                    else if (mes.length > i + 1 && !mes[i + 1].equals("0")) {
                        g.drawImage(this.IamelingqiaoL.getImage(), 410, 130 + i * 30, 32, 35, null);
                        g.drawString(mes[i + 1].split("=")[0], 440, 130 + i * 30 + 25);
                        g.drawImage(this.ditu.getImage(), 580, 130 + i * 30 + 10, 74, 20, null);
                        g.drawImage(this.jindutiao.getImage(), 581, 130 + i * 30 + 10, (int)(72.0 * this.bili(mes[i + 1].split("=")[0], Double.parseDouble(mes[i + 1].split("=")[1]))), 17, null);
                        g.drawString(this.shuzhi(mes[i + 1].split("=")[1], mes[i + 1].split("=")[0]), 600, 130 + i * 30 + 23);
                    }
                    else {
                        g.setColor(new Color(-205809026, true));
                        g.drawImage(this.Iamelingqiao.getImage(), 410, 130 + i * 30, 32, 35, null);
                        g.drawString("灵窍无灵气", 440, 130 + i * 30 + 25);
                    }
                }
            }
            else {
                g.setColor(new Color(-205809026, true));
                for (int j = 0; j < 7; ++j) {
                    if (j == 5) {
                        g.drawImage(this.Iamelingqiao.getImage(), 410, 290, 32, 35, null);
                        g.drawString("灵窍无灵气", 440, 315);
                    }
                    else if (j == 6) {
                        g.drawImage(this.Iamelingqiao.getImage(), 410, 320, 32, 35, null);
                        g.drawString("灵窍无灵气", 440, 345);
                    }
                    else {
                        g.drawImage(this.Iamelingqiao.getImage(), 410, 130 + j * 30, 32, 35, null);
                        g.drawString("灵窍无灵气", 440, 130 + j * 30 + 25);
                    }
                }
            }
        }
        else {
            g.setColor(new Color(-205809026, true));
            for (int j = 0; j < 7; ++j) {
                if (j == 5) {
                    g.drawImage(this.Iamelingqiao.getImage(), 410, 290, 32, 35, null);
                    g.drawString("灵窍无灵气", 440, 315);
                }
                else if (j == 6) {
                    g.drawImage(this.Iamelingqiao.getImage(), 410, 320, 32, 35, null);
                    g.drawString("灵窍无灵气", 440, 345);
                }
                else {
                    g.drawImage(this.Iamelingqiao.getImage(), 410, 130 + j * 30, 32, 35, null);
                    g.drawString("灵窍无灵气", 440, 130 + j * 30 + 25);
                }
            }
        }
    }
    
    public void pain1(Graphics g) {
        super.paintComponent(g);
        if (ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getXuanzhong1() == -1) {
            return;
        }
        int l = ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getXuanzhong1();
        if (l >= 0 && l < ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().size()) {
            this.RL1 = (Goodstable)ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().get(l);
        }
        int k = ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getXuanzhong2();
        if (k >= 0 && k < ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().size()) {
            this.RL2 = (Goodstable)ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().get(k);
        }
        if (ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getXuanzhong2() == -1) {
            this.RL2 = null;
        }
        if (this.RL1 != null) {
            int y = -30;
            if (this.RL1.getValue() != null && !this.RL1.getValue().equals("")) {
                g.setColor(Color.white);
                String[] mes = this.RL1.getValue().split("\\|");
                g.drawString(mes[0].split("=")[1] + "级", 265, 128);
                for (int i = 0; i < 7; ++i) {
                    if (i == 5) {
                        if (mes.length > 6 && !mes[i + 1].equals("0")) {
                            g.drawImage(this.IamelingqiaoL.getImage(), 70, 290 - y, 32, 35, null);
                            this.panel1.setBounds(100, 327, 200, 20);
                            this.panel1.setText(UserMessUntil.getSkillId(mes[i + 1].split("=")[0]).getSkillname() + ":" + mes[i + 1].split("=")[1] + "级");
                            this.panel1.setVisible(true);
                        }
                        else {
                            g.setColor(new Color(-205809026, true));
                            g.drawImage(this.Iamelingqiao.getImage(), 70, 290 - y, 32, 35, null);
                            g.drawString("灵窍无灵气", 100, 315 - y);
                        }
                    }
                    else if (i == 6) {
                        if (mes.length > 7 && !mes[i + 1].equals("0")) {
                            g.drawImage(this.IamelingqiaoL.getImage(), 70, 320 - y, 32, 35, null);
                            this.panel2.setBounds(100, 357, 200, 20);
                            this.panel2.setText(UserMessUntil.getSkillId(mes[i + 1].split("=")[0]).getSkillname() + ":" + mes[i + 1].split("=")[1] + "级");
                            this.panel2.setVisible(true);
                        }
                        else {
                            g.setColor(new Color(-205809026, true));
                            g.drawImage(this.Iamelingqiao.getImage(), 70, 320 - y, 32, 35, null);
                            g.drawString("灵窍无灵气", 100, 345 - y);
                        }
                    }
                    else if (mes.length > i + 1 && !mes[i + 1].equals("0")) {
                        g.drawImage(this.IamelingqiaoL.getImage(), 70, 130 + i * 30 - y, 32, 35, null);
                        g.drawString(mes[i + 1].split("=")[0], 100, 130 + i * 30 + 25 - y);
                        g.drawImage(this.ditu.getImage(), 265, 130 + i * 30 + 10 - y, 74, 20, null);
                        g.drawImage(this.jindutiao.getImage(), 265, 130 + i * 30 + 10 - y, (int)(72.0 * this.bili(mes[i + 1].split("=")[0], Double.parseDouble(mes[i + 1].split("=")[1]))), 17, null);
                        g.drawString(this.shuzhi(mes[i + 1].split("=")[1], mes[i + 1].split("=")[0]), 280, 130 + i * 30 + 23 - y);
                    }
                    else {
                        g.setColor(new Color(-205809026, true));
                        g.drawImage(this.Iamelingqiao.getImage(), 70, 130 + i * 30 - y, 32, 35, null);
                        g.drawString("灵窍无灵气", 100, 130 + i * 30 + 25 - y);
                    }
                }
            }
            else {
                g.setColor(new Color(-205809026, true));
                for (int j = 0; j < 7; ++j) {
                    if (j == 5) {
                        g.drawImage(this.Iamelingqiao.getImage(), 70, 290 - y, 32, 35, null);
                        g.drawString("灵窍无灵气", 100, 315 - y);
                    }
                    else if (j == 6) {
                        g.drawImage(this.Iamelingqiao.getImage(), 70, 320 - y, 32, 35, null);
                        g.drawString("灵窍无灵气", 100, 345 - y);
                    }
                    else {
                        g.drawImage(this.Iamelingqiao.getImage(), 70, 130 + j * 30 - y, 32, 35, null);
                        g.drawString("灵窍无灵气", 100, 130 + j * 30 + 25 - y);
                    }
                }
            }
        }
        if (this.RL2 != null) {
            int y = -30;
            int x = 360;
            if (this.RL2.getValue() != null && !this.RL2.getValue().equals("")) {
                g.setColor(Color.white);
                String[] mes2 = this.RL2.getValue().split("\\|");
                g.drawString(mes2[0].split("=")[1] + "级", 610, 128);
                for (int m = 0; m < 7; ++m) {
                    if (m == 5) {
                        if (mes2.length > 6 && !mes2[m + 1].equals("0")) {
                            g.drawImage(this.IamelingqiaoL.getImage(), 70 + x, 290 - y, 32, 35, null);
                            this.panel3.setBounds(460, 327, 200, 20);
                            this.panel3.setVisible(true);
                            this.panel3.setText(UserMessUntil.getSkillId(mes2[m + 1].split("=")[0]).getSkillname() + ":" + mes2[m + 1].split("=")[1] + "级");
                        }
                        else {
                            g.setColor(new Color(-205809026, true));
                            g.drawImage(this.Iamelingqiao.getImage(), 70 + x, 290 - y, 32, 35, null);
                            g.drawString("灵窍无灵气", 100 + x, 315 - y);
                        }
                    }
                    else if (m == 6) {
                        if (mes2.length > 7 && !mes2[m + 1].equals("0")) {
                            g.drawImage(this.IamelingqiaoL.getImage(), 70 + x, 320 - y, 32, 35, null);
                            this.panel4.setBounds(460, 357, 200, 20);
                            this.panel4.setVisible(true);
                            this.panel4.setText(UserMessUntil.getSkillId(mes2[m + 1].split("=")[0]).getSkillname() + ":" + mes2[m + 1].split("=")[1] + "级");
                        }
                        else {
                            g.setColor(new Color(-205809026, true));
                            g.drawImage(this.Iamelingqiao.getImage(), 70 + x, 320 - y, 32, 35, null);
                            g.drawString("灵窍无灵气", 100 + x, 345 - y);
                        }
                    }
                    else if (mes2.length > m + 1 && !mes2[m + 1].equals("0")) {
                        g.drawImage(this.ditu.getImage(), 615, 130 + m * 30 + 10 + 30, 74, 20, null);
                        g.drawImage(this.jindutiao.getImage(), 615, 130 + m * 30 + 10 + 30, (int)(72.0 * this.bili(mes2[m + 1].split("=")[0], Double.parseDouble(mes2[m + 1].split("=")[1]))), 17, null);
                        g.drawImage(this.IamelingqiaoL.getImage(), 70 + x, 130 + m * 30 - y, 32, 35, null);
                        g.drawString(mes2[m + 1].split("=")[0], 100 + x, 130 + m * 30 + 25 - y);
                        g.drawString(this.shuzhi(mes2[m + 1].split("=")[1], mes2[m + 1].split("=")[0]), 630, 130 + m * 30 + 23 - y);
                    }
                    else {
                        g.setColor(new Color(-205809026, true));
                        g.drawImage(this.Iamelingqiao.getImage(), 70 + x, 130 + m * 30 - y, 32, 35, null);
                        g.drawString("灵窍无灵气", 100 + x, 130 + m * 30 + 25 - y);
                    }
                }
            }
            else {
                g.setColor(new Color(-205809026, true));
                for (int i = 0; i < 7; ++i) {
                    if (i == 5) {
                        g.drawImage(this.Iamelingqiao.getImage(), 70 + x, 290 - y, 32, 35, null);
                        g.drawString("灵窍无灵气", 100 + x, 315 - y);
                    }
                    else if (i == 6) {
                        g.drawImage(this.Iamelingqiao.getImage(), 70 + x, 320 - y, 32, 35, null);
                        g.drawString("灵窍无灵气", 100 + x, 345 - y);
                    }
                    else {
                        g.drawImage(this.Iamelingqiao.getImage(), 70 + x, 130 + i * 30 - y, 32, 35, null);
                        g.drawString("灵窍无灵气", 100 + x, 130 + i * 30 + 25 - y);
                    }
                }
            }
        }
    }
    
    public double bili(String mes, double value) {
        List<MountShouhu> mountShouhus = UserMessUntil.getAllMountShouhu().getAllmountshouhu();
        for (int i = 0; i <= mountShouhus.size() - 1; ++i) {
            if (((MountShouhu)mountShouhus.get(i)).type.equals(mes)) {
                return value / ((MountShouhu)mountShouhus.get(i)).value;
            }
        }
        return 0.0;
    }
    
    public String shuzhi(String v1, String v) {
        if (v.contains("速度") || v.contains("攻击")) {
            return v1;
        }
        if (Double.parseDouble(v1) < 100.0) {
            return v1 + "%";
        }
        return v1;
    }
    
    public int getType() {
        if (this.type == 1) {
            this.kaishifuling.setVisible(true);
            this.kaishironglian.setVisible(false);
            this.FST.setVisible(true);
            this.RST1.setVisible(false);
            this.RST2.setVisible(false);
        }
        else if (this.type == 2) {
            this.kaishifuling.setVisible(false);
            this.kaishironglian.setVisible(true);
            this.FST.setVisible(false);
            this.RST1.setVisible(true);
            this.RST2.setVisible(true);
        }
        else {
            this.kaishifuling.setVisible(false);
            this.kaishironglian.setVisible(false);
        }
        return this.type;
    }
    
    public void setType(int type) {
        ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().setXuanzhong1(-1);
        ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().setXuanzhong2(-1);
        this.FL = null;
        this.RL1 = null;
        this.RL2 = null;
        this.img1.setVisible(false);
        this.img3.setVisible(false);
        this.img4.setVisible(false);
        if (type == 1) {}
        this.type = type;
    }
    
    public MountShouhuBtn getXuanxiangka1() {
        return this.xuanxiangka1;
    }
    
    public void setXuanxiangka1(MountShouhuBtn xuanxiangka1) {
        this.xuanxiangka1 = xuanxiangka1;
    }
    
    public MountShouhuBtn getXuanxiangka2() {
        return this.xuanxiangka2;
    }
    
    public void setXuanxiangka2(MountShouhuBtn xuanxiangka2) {
        this.xuanxiangka2 = xuanxiangka2;
    }
    
    public MountShouhuBtn getKaishifuling() {
        return this.kaishifuling;
    }
    
    public void setKaishifuling(MountShouhuBtn kaishifuling) {
        this.kaishifuling = kaishifuling;
    }
}
