package org.come.Jpanel;

import com.tool.tcp.SpriteFactory;
import org.come.Frame.TestSetupJframe;
import java.awt.Graphics;
import java.util.Iterator;
import org.come.until.SrcollPanelUI;
import org.come.mouslisten.MountMouslisten;
import javax.swing.BorderFactory;
import org.come.until.CutButtonImage;
import java.io.File;
import org.come.until.UserMessUntil;
import com.tool.image.ImageMixDeal;
import org.come.until.ExpIncreaseUntil;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListModel;
import java.awt.Font;
import org.come.mouslisten.SystemMouslisten;
import java.awt.Color;
import org.come.until.FormsManagement;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.Frame.ZhuFrame;
import org.come.mouslisten.ChangeMouseSymbolMouslisten;
import org.come.entity.Mount;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.come.entity.RoleSummoning;
import java.util.Map;
import java.math.BigDecimal;
import com.tool.tcp.NewPart;
import javax.swing.ImageIcon;
import java.util.Vector;
import com.tool.btn.MountPanelBtn;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import com.tool.btn.FormsOnOffBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mountJPanel extends JPanel
{
    private JLabel labmountimg;
    private JLabel labmountlevel;
    private JLabel labmounttili;
    private JLabel labmountintelligence;
    private JLabel labmountpower;
    private JLabel labmountRootbone;
    private JLabel labmountexp;
    private JLabel hidden;
    public static int idx;
    public static int idx2;
    private FormsOnOffBtn offBtn;
    private JList<String> listmount;
    private static JList<String> listpet;
    private DefaultListModel<String> modelmount;
    private static DefaultListModel<String> listModel;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private MountPanelBtn btnRiding;
    private MountPanelBtn btnControl;
    private MountPanelBtn btnFeeding;
    private MountPanelBtn btnKills;
    private MountPanelBtn btnRelease;
    private JLabel[] petlist;
    private int caozuio;
    public static int idxx;
    private Vector<Vector<String>> verVectors;
    private ImageIcon icon;
    private ImageIcon iconH;
    private ImageIcon iconS;
    private NewPart newPart;
    private BigDecimal se;
    private int mountid;
    public long time;
    private Map<Integer, RoleSummoning> p;
    private List<MountPanelBtn> shouhu;
    
    public mountJPanel() throws Exception {
        this.petlist = new JLabel[3];
        this.iconH = new ImageIcon("inkImg/Client/坐骑背景.png");
        this.iconS = new ImageIcon("inkImg/Client/坐骑背景.png");
        this.p = new HashMap<>();
        this.shouhu = new ArrayList<>();
        this.caozuio = 0;
        this.time = 0L;
        this.setPreferredSize(new Dimension(740, 516));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        this.trisd();
    }
    
    public void trisd() {
        for (int i = 0; i < 3; ++i) {
            (this.petlist[i] = new JLabel()).setBounds(600, 80 + i * 68, 45, 45);
            this.petlist[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (e.getButton() == 3) {
                        JLabel label = (JLabel)e.getSource();
                        int c = 35;
                        if (mountJPanel.this.listmount.getSelectedIndex() >= 0) {
                            Mount mount = (Mount)ZhuJpanel.getListMount().get(mountJPanel.this.listmount.getSelectedIndex());
                            if (mount != null && (int)mount.getMountlvl() > 100) {
                                c = 64;
                            }
                            else if (mount != null && (int)mount.getMountlvl() <= 100) {
                                c = 97;
                            }
                        }
                        int k = 3;
                        if (label.getY() == c) {
                            k = 0;
                        }
                        else if (label.getY() == c + 68) {
                            k = 1;
                        }
                        else if (label.getY() == 200) {
                            k = 2;
                        }
                        if (mountJPanel.this.p.size() != 0 && k != 3) {
                            RoleSummoning pet = (RoleSummoning)mountJPanel.this.p.get(Integer.valueOf(k));
                            if (pet == null) {
                                return;
                            }
                            Mount mount2 = (Mount)ZhuJpanel.getListMount().get(mountJPanel.this.listmount.getSelectedIndex());
                            if (mount2 != null) {
                                int type = 0;
                                if (mount2.getSid() != null && pet.getSid().compareTo(mount2.getSid()) == 0) {
                                    type = -1;
                                    mount2.setSid(null);
                                    mountJPanel.this.p.remove(Integer.valueOf(0));
                                    mountJPanel.this.petlist[0].setIcon(null);
                                }
                                else if (mount2.getOthrersid() != null && pet.getSid().compareTo(mount2.getOthrersid()) == 0) {
                                    type = -2;
                                    mount2.setOthrersid(null);
                                    mountJPanel.this.p.remove(Integer.valueOf(1));
                                    mountJPanel.this.petlist[1].setIcon(null);
                                }
                                else if (mount2.getSid3() != null && pet.getSid().compareTo(mount2.getSid3()) == 0) {
                                    type = -3;
                                    mount2.setSid3(null);
                                    mountJPanel.this.p.remove(Integer.valueOf(2));
                                    mountJPanel.this.petlist[2].setIcon(null);
                                }
                                if (type < 0) {
                                    ChangeMouseSymbolMouslisten.clearPropertie(pet);
                                    ZhuFrame.getZhuJpanel().addPrompt2("#R" + pet.getSummoningname() + "#W已被#G" + mount2.getMountname() + "#W取消管制");
                                }
                                String sendmes = Agreement.getAgreement().changeMountValue(GsonUtil.getGsonUtil().getgson().toJson(mount2));
                                SendMessageUntil.toServer(sendmes);
                            }
                        }
                    }
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    JLabel label = (JLabel)e.getSource();
                    int c = 35;
                    if (mountJPanel.this.listmount.getSelectedIndex() >= 0) {
                        Mount mount = (Mount)ZhuJpanel.getListMount().get(mountJPanel.this.listmount.getSelectedIndex());
                        if (mount != null && (int)mount.getMountlvl() > 100) {
                            c = 64;
                        }
                        else if (mount != null && (int)mount.getMountlvl() <= 100) {
                            c = 97;
                        }
                    }
                    int k = 3;
                    if (label.getY() == c) {
                        k = 0;
                    }
                    else if (label.getY() == c + 68) {
                        k = 1;
                    }
                    else if (label.getY() == 200) {
                        k = 2;
                    }
                    if (k != 3 && mountJPanel.this.p.get(Integer.valueOf(k)) != null) {
                        ZhuFrame.getZhuJpanel().creatpettext((RoleSummoning)mountJPanel.this.p.get(Integer.valueOf(k)));
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    FormsManagement.HideForm(42);
                }
            });
            this.petlist[i].setOpaque(false);
            this.petlist[i].setBackground(Color.white);
            this.add(this.petlist[i]);
        }
        (this.offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 7)).setBounds(703, 15, 17, 17);
        this.add(this.offBtn);
        (this.labmountimg = new JLabel()).setBounds(25, 43, 125, 185);
        this.add(this.labmountimg);
        (this.hidden = new JLabel()).addMouseListener(new SystemMouslisten(23));
        this.add(this.hidden);
        Font font = new Font("微软雅黑", 1, 12);
        (this.labmountlevel = new JLabel()).setBounds(220, 40, 99, 17);
        this.labmountlevel.setForeground(Color.WHITE);
        this.labmountlevel.setFont(font);
        this.add(this.labmountlevel);
        (this.labmounttili = new JLabel()).setBounds(220, 78, 99, 17);
        this.labmounttili.setForeground(Color.WHITE);
        this.labmounttili.setFont(font);
        this.add(this.labmounttili);
        (this.labmountintelligence = new JLabel()).setBounds(220, 113, 99, 17);
        this.labmountintelligence.setForeground(Color.WHITE);
        this.labmountintelligence.setFont(font);
        this.add(this.labmountintelligence);
        (this.labmountpower = new JLabel()).setBounds(220, 148, 99, 17);
        this.labmountpower.setForeground(Color.WHITE);
        this.labmountpower.setFont(font);
        this.add(this.labmountpower);
        (this.labmountRootbone = new JLabel()).setBounds(220, 183, 99, 17);
        this.labmountRootbone.setForeground(Color.WHITE);
        this.labmountRootbone.setFont(font);
        this.add(this.labmountRootbone);
        (this.labmountexp = new JLabel()).setBounds(220, 217, 99, 17);
        this.labmountexp.setForeground(Color.WHITE);
        this.labmountexp.setFont(font);
        this.add(this.labmountexp);
        mountJPanel.listModel = new DefaultListModel<>();
        mountJPanel.listpet = new JList<String>(mountJPanel.listModel) {
            {
                this.setOpaque(false);
            }
        };
        this.modelmount = new DefaultListModel<>();
        (this.listmount = new JList<String>() {
            {
                this.setOpaque(false);
            }
        }).setSelectionBackground(new Color(33, 42, 52));
        this.listmount.setSelectionForeground(Color.GREEN);
        this.listmount.setForeground(Color.GREEN);
        this.listmount.setFont(new Font("微软雅黑", 1, 14));
        this.listmount.setBackground(UIUtils.Color_BACK);
        this.listmount.setModel(this.modelmount);
        this.listmount.setCellRenderer(new MyRendererXIAO());
        this.listmount.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
            }
        });
        this.listmount.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    if (mountJPanel.this.listmount.getSelectedIndex() >= 0) {
                        Mount mount = (Mount)ZhuJpanel.getListMount().get(mountJPanel.this.listmount.getSelectedIndex());
                        ExpIncreaseUntil.showMountValue(mount);
                        if ((int)mount.getMountlvl() > 100) {
                            mountJPanel.this.iconH = new ImageIcon("inkImg/Client/坐骑背景_飞升后.png");
                            for (int i = 0; i <= mountJPanel.this.petlist.length - 1; ++i) {
                                mountJPanel.this.petlist[i].setBounds(643, 64 + i * 68, 45, 45);
                            }
                        }
                        else {
                            for (int i = 0; i <= mountJPanel.this.petlist.length - 1; ++i) {
                                mountJPanel.this.petlist[i].setBounds(643, 97 + i * 68, 45, 45);
                            }
                            mountJPanel.this.iconH = new ImageIcon("inkImg/Client/坐骑背景_飞升前.png");
                        }
                        if ((int)mount.getMountid() == ImageMixDeal.userimg.getRoleShow().getMount_id()) {
                            mountJPanel.this.btnRiding.setText("休息");
                        }
                        else {
                            mountJPanel.this.btnRiding.setText("骑乘");
                        }
                        if (mount.getSid() != null) {
                            RoleSummoning roleSummoning = UserMessUntil.getPetRgid(mount.getSid());
                            if (roleSummoning == null) {
                                return;
                            }
                            String path = "img/head/p" + roleSummoning.getSummoningskin() + ".png";
                            File file = new File(path);
                            if (file.exists()) {
                                ImageIcon icon2 = CutButtonImage.size(path, 45, 45);
                                mountJPanel.this.petlist[0].setIcon(icon2);
                                mountJPanel.this.p.put(Integer.valueOf(0), roleSummoning);
                            }
                            else {
                                ImageIcon icon2 = CutButtonImage.getImage("img/xy2uiimg/101_png.xy2uiimg.pet_def.png", -1, -1);
                                mountJPanel.this.petlist[0].setIcon(icon2);
                                mountJPanel.this.p.put(Integer.valueOf(0), roleSummoning);
                            }
                        }
                        else {
                            mountJPanel.this.petlist[0].setIcon(null);
                            mountJPanel.this.p.remove(Integer.valueOf(0));
                        }
                        if (mount.getOthrersid() != null) {
                            RoleSummoning roleSummoning = UserMessUntil.getPetRgid(mount.getOthrersid());
                            if (roleSummoning == null) {
                                return;
                            }
                            String path = "img/head/p" + roleSummoning.getSummoningskin() + ".png";
                            File file = new File(path);
                            if (file.exists()) {
                                ImageIcon icon2 = CutButtonImage.size(path, 45, 45);
                                mountJPanel.this.petlist[1].setIcon(icon2);
                                mountJPanel.this.p.put(Integer.valueOf(1), roleSummoning);
                            }
                            else {
                                ImageIcon icon2 = CutButtonImage.getImage("img/xy2uiimg/101_png.xy2uiimg.pet_def.png", -1, -1);
                                mountJPanel.this.petlist[1].setIcon(icon2);
                                mountJPanel.this.p.put(Integer.valueOf(1), roleSummoning);
                            }
                        }
                        else {
                            mountJPanel.this.petlist[1].setIcon(null);
                            mountJPanel.this.p.remove(Integer.valueOf(1));
                        }
                        if (mount.getSid3() != null) {
                            RoleSummoning roleSummoning = UserMessUntil.getPetRgid(mount.getSid3());
                            if (roleSummoning == null) {
                                return;
                            }
                            String path = "img/head/p" + roleSummoning.getSummoningskin() + ".png";
                            File file = new File(path);
                            if (file.exists()) {
                                ImageIcon icon2 = CutButtonImage.size(path, 45, 45);
                                mountJPanel.this.petlist[2].setIcon(icon2);
                                mountJPanel.this.p.put(Integer.valueOf(2), roleSummoning);
                            }
                            else {
                                ImageIcon icon2 = CutButtonImage.getImage("img/xy2uiimg/101_png.xy2uiimg.pet_def.png", -1, -1);
                                mountJPanel.this.petlist[2].setIcon(icon2);
                                mountJPanel.this.p.put(Integer.valueOf(2), roleSummoning);
                            }
                        }
                        else {
                            mountJPanel.this.petlist[2].setIcon(null);
                            mountJPanel.this.p.remove(Integer.valueOf(2));
                        }
                    }
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        (this.jScrollPane1 = new JScrollPane(this.listmount)).setVerticalScrollBarPolicy(22);
        this.jScrollPane1.getVerticalScrollBar().setUI(null);
        this.jScrollPane1.getViewport().setOpaque(false);
        this.jScrollPane1.setOpaque(false);
        this.jScrollPane1.setBounds(27, 277, 132, 155);
        this.jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane1.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane1);
        this.verVectors = new Vector<>();
        Vector<String> verStrings = new Vector<>();
        verStrings.add("");
        verStrings.add("");
        mountJPanel.listpet.setCellRenderer(new MyRendererxL());
        mountJPanel.listpet.setSelectionBackground(new Color(33, 42, 52));
        mountJPanel.listpet.setSelectionMode(0);
        mountJPanel.listpet.addMouseListener(new MountMouslisten(mountJPanel.listpet, this));
        mountJPanel.listpet.addMouseMotionListener(new MountMouslisten(mountJPanel.listpet, this));
        (this.jScrollPane2 = new JScrollPane(mountJPanel.listpet)).setVerticalScrollBarPolicy(22);
        this.jScrollPane2.getVerticalScrollBar().setUI(new SrcollPanelUI());
        this.jScrollPane2.getViewport().setOpaque(false);
        this.jScrollPane2.setOpaque(false);
        this.jScrollPane2.setBounds(173, 277, 194, 155);
        this.jScrollPane2.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane2.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane2);
        (this.btnRiding = new MountPanelBtn("", 1, "骑乘", this, 1)).setBounds(32, 448, 64, 24);
        this.add(this.btnRiding);
        (this.btnControl = new MountPanelBtn("", 1, "管制", this, 3)).setBounds(98, 448, 64, 24);
        (this.btnFeeding = new MountPanelBtn("", 1, "喂养", this, 4)).setBounds(164, 448, 64, 24);
        this.add(this.btnFeeding);
        (this.btnKills = new MountPanelBtn("", 1, "技能", this, 5)).setBounds(230, 448, 64, 24);
        this.add(this.btnKills);
        (this.btnRelease = new MountPanelBtn("inkImg/Client/nex.png", 1, "", this, 6)).setBounds(228, 267, 29, 23);
        this.add(this.btnRelease);
        MountPanelBtn btn = new MountPanelBtn("inkImg/Client/未选中按钮.png", 1, "坐骑", this, 7, UIUtils.Black);
        btn.setBounds(50, 15, 100, 33);
        this.add(btn);
        this.shouhu.add(btn);
        MountPanelBtn btn2 = new MountPanelBtn("inkImg/Client/未选中按钮.png", 1, "守护", this, 8, UIUtils.Black);
        btn2.setBounds(160, 15, 100, 33);
        this.add(btn2);
        this.shouhu.add(btn2);
    }
    
    public static void showListModel(List<RoleSummoning> pets, BigDecimal petid) {
        mountJPanel.listModel.removeAllElements();
        for (RoleSummoning pet : pets) {
            mountJPanel.listModel.addElement(pet.getSummoningname());
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.time += 24L;
        TestSetupJpanel ss = TestSetupJframe.getTestSetupJframe().getTestSetupJpanel();
        if (this.iconH == null) {
            this.iconH = new ImageIcon("inkImg/Client/坐骑背景.png");
        }
        g.drawImage(this.iconH.getImage(), 0, 0, 740, 516, this);
        this.labmountlevel.setBounds(92, 300, 99, 18);
        this.labmounttili.setBounds(92, 330, 99, 18);
        this.labmountintelligence.setBounds(92, 360, 99, 18);
        this.labmountpower.setBounds(92, 390, 99, 18);
        this.labmountRootbone.setBounds(92, 420, 99, 18);
        this.labmountexp.setBounds(92, 450, 99, 18);
        this.jScrollPane1.setBounds(50, 74, 200, 200);
        this.jScrollPane2.setBounds(280, 300, 425, 200);
        this.hidden.setBounds(570, 25, 15, 15);
        this.offBtn.setBounds(703, 15, 17, 17);
        try {
            this.btnRiding.setIcons(CutButtonImage.cuts("inkImg/Client/60X26按钮.png"));
            this.btnRiding.setBounds(45, 475, 60, 26);
            this.btnFeeding.setIcons(CutButtonImage.cuts("inkImg/Client/60X26按钮.png"));
            this.btnFeeding.setBounds(200, 475, 60, 26);
            this.btnKills.setIcons(CutButtonImage.cuts("inkImg/Client/60X26按钮.png"));
            this.btnKills.setBounds(123, 475, 60, 26);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (this.listmount.getSelectedIndex() != -1) {
            int index = this.listmount.getSelectedIndex();
            if (ZhuJpanel.getListMount().size() > index) {
                Mount mount = (Mount)ZhuJpanel.getListMount().get(index);
                if (this.newPart == null) {
                    this.mountid = (int)mount.getMountid();
                    this.se = ImageMixDeal.userimg.getRoleShow().getSpecies_id();
                    this.newPart = SpriteFactory.createPart((long)this.mountid << 40 | this.se.longValue(), 2, 1, null);
                }
                else if (this.mountid != (int)mount.getMountid() || this.se.compareTo(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) != 0) {
                    this.mountid = (int)mount.getMountid();
                    this.se = ImageMixDeal.userimg.getRoleShow().getSpecies_id();
                    this.newPart = SpriteFactory.createPart((long)this.mountid << 40 | this.se.longValue(), 2, 1, null);
                }
                this.newPart.draw(g, 380, 220, 0, ImageMixDeal.userimg.getTime());
            }
        }
    }
    
    public JLabel getLabmountimg() {
        return this.labmountimg;
    }
    
    public void setLabmountimg(JLabel labmountimg) {
        this.labmountimg = labmountimg;
    }
    
    public JLabel getLabmountlevel() {
        if (this.labmountlevel != null) {
            this.labmountlevel.setBounds(92, 300, 99, 18);
        }
        return this.labmountlevel;
    }
    
    public void setLabmountlevel(JLabel labmountlevel) {
        this.labmountlevel = labmountlevel;
    }
    
    public JLabel getLabmounttili() {
        return this.labmounttili;
    }
    
    public void setLabmounttili(JLabel labmounttili) {
        this.labmounttili = labmounttili;
    }
    
    public JLabel getLabmountintelligence() {
        return this.labmountintelligence;
    }
    
    public void setLabmountintelligence(JLabel labmountintelligence) {
        this.labmountintelligence = labmountintelligence;
    }
    
    public JLabel getLabmountpower() {
        return this.labmountpower;
    }
    
    public void setLabmountpower(JLabel labmountpower) {
        this.labmountpower = labmountpower;
    }
    
    public JLabel getLabmountRootbone() {
        return this.labmountRootbone;
    }
    
    public void setLabmountRootbone(JLabel labmountRootbone) {
        this.labmountRootbone = labmountRootbone;
    }
    
    public JLabel getLabmountexp() {
        return this.labmountexp;
    }
    
    public void setLabmountexp(JLabel labmountexp) {
        this.labmountexp = labmountexp;
    }
    
    public JList<String> getListmount() {
        return this.listmount;
    }
    
    public void setListmount(JList<String> listmount) {
        this.listmount = listmount;
    }
    
    public DefaultListModel<String> getModelmount() {
        return this.modelmount;
    }
    
    public void setModelmount(DefaultListModel<String> modelmount) {
        this.modelmount = modelmount;
    }
    
    public JScrollPane getjScrollPane1() {
        return this.jScrollPane1;
    }
    
    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }
    
    public JScrollPane getjScrollPane2() {
        return this.jScrollPane2;
    }
    
    public void setjScrollPane2(JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }
    
    public MountPanelBtn getBtnRiding() {
        return this.btnRiding;
    }
    
    public void setBtnRiding(MountPanelBtn btnRiding) {
        this.btnRiding = btnRiding;
    }
    
    public MountPanelBtn getBtnControl() {
        return this.btnControl;
    }
    
    public void setBtnControl(MountPanelBtn btnControl) {
        this.btnControl = btnControl;
    }
    
    public MountPanelBtn getBtnFeeding() {
        return this.btnFeeding;
    }
    
    public void setBtnFeeding(MountPanelBtn btnFeeding) {
        this.btnFeeding = btnFeeding;
    }
    
    public MountPanelBtn getBtnKills() {
        return this.btnKills;
    }
    
    public void setBtnKills(MountPanelBtn btnKills) {
        this.btnKills = btnKills;
    }
    
    public static JList<String> getListpet() {
        return mountJPanel.listpet;
    }
    
    public static void setListpet(JList<String> listpet) {
        mountJPanel.listpet = listpet;
    }
    
    public static int getIdx() {
        return mountJPanel.idx;
    }
    
    public static void setIdx(int idx) {
        mountJPanel.idx = idx;
    }
    
    public static DefaultListModel<String> getListModel() {
        return mountJPanel.listModel;
    }
    
    public static void setListModel(DefaultListModel<String> listModel) {
        mountJPanel.listModel = listModel;
    }
    
    public MountPanelBtn getBtnRelease() {
        return this.btnRelease;
    }
    
    public void setBtnRelease(MountPanelBtn btnRelease) {
        this.btnRelease = btnRelease;
    }
    
    public static int getIdxx() {
        return mountJPanel.idxx;
    }
    
    public static void setIdxx(int idxx) {
        mountJPanel.idxx = idxx;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public NewPart getNewPart() {
        return this.newPart;
    }
    
    public void setNewPart(NewPart newPart) {
        this.newPart = newPart;
    }
    
    public BigDecimal getSe() {
        return this.se;
    }
    
    public void setSe(BigDecimal se) {
        this.se = se;
    }
    
    public int getMountid() {
        return this.mountid;
    }
    
    public void setMountid(int mountid) {
        this.mountid = mountid;
    }
    
    public Vector<Vector<String>> getVerVectors() {
        return this.verVectors;
    }
    
    public void setVerVectors(Vector<Vector<String>> verVectors) {
        this.verVectors = verVectors;
    }
    
    public FormsOnOffBtn getOffBtn() {
        return this.offBtn;
    }
    
    public void setOffBtn(FormsOnOffBtn offBtn) {
        this.offBtn = offBtn;
    }
    
    public JLabel getHidden() {
        return this.hidden;
    }
    
    public int getCaozuio() {
        return this.caozuio;
    }
    
    public void setCaozuio(int caozuio) {
        this.caozuio = caozuio;
    }
    
    public JLabel[] getPetlist() {
        return this.petlist;
    }
    
    public void setPetlist(JLabel[] petlist) {
        this.petlist = petlist;
    }
    
    public Map<Integer, RoleSummoning> getP() {
        return this.p;
    }
    
    public void setP(Map<Integer, RoleSummoning> p) {
        this.p = p;
    }
    
    public List<MountPanelBtn> getShouhu() {
        return this.shouhu;
    }
    
    public void setShouhu(List<MountPanelBtn> shouhu) {
        this.shouhu = shouhu;
    }
    
    static {
        mountJPanel.idx = -1;
        mountJPanel.idx2 = -1;
        mountJPanel.idxx = -1;
    }
}
