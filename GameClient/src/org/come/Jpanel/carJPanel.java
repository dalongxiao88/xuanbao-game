package org.come.Jpanel;

import com.tool.btn.FormsOnOffBtn;
import com.tool.btn.MountPanelBtn;
import com.tool.image.ImageMixDeal;
import com.tool.tcp.NewPart;
import com.tool.tcp.SpriteFactory;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;
import org.come.Frame.TestSetupJframe;
import org.come.Frame.ZhuFrame;
import org.come.entity.Car;
import org.come.entity.Mount;
import org.come.entity.RoleSummoning;
import org.come.mouslisten.ChangeMouseSymbolMouslisten;
import org.come.mouslisten.MountMouslisten;
import org.come.mouslisten.SystemMouslisten;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.*;

public class carJPanel extends JPanel
{
    private JLabel labmountimg;
//    private JLabel labmountlevel;
//    private JLabel labmounttili;
    private JLabel labmountintelligence;
    private JLabel labmountpower;
    private JLabel labmountRootbone;
    private JLabel labmountexp;
    private JLabel hidden;
    public static int idx;
    public static int idx2;
    private FormsOnOffBtn offBtn;
    private JList<String> listmount;
//    private static JList<String> listpet;
    private DefaultListModel<String> modelmount;
//    private static DefaultListModel<String> listModel;
    private JScrollPane jScrollPane1;
//    private JScrollPane jScrollPane2;
    private MountPanelBtn btnRiding;
//    private MountPanelBtn btnControl;
//    private MountPanelBtn btnFeeding;
//    private MountPanelBtn btnKills;
    private MountPanelBtn btnRelease;
//    private JLabel[] petlist;
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
//    private Map<Integer, RoleSummoning> p;
//    private List<MountPanelBtn> shouhu;

    public carJPanel() throws Exception {

        this.iconH = new ImageIcon("inkImg/background1/坐驾背景.png");
        this.iconS = new ImageIcon("inkImg/background1/坐驾背景hongmu.png");
        this.caozuio = 0;
        this.time = 0L;
        this.setPreferredSize(new Dimension(534, 516));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        this.trisd();
    }
    
    public void trisd() {
        if (MyIsif.getStyle().equals("水墨") ) {
            (this.offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 1002)).setBounds(703, 15, 17, 17);
            this.add(this.offBtn);
            (this.labmountimg = new JLabel()).setBounds(25, 43, 125, 185);
            this.add(this.labmountimg);
            (this.hidden = new JLabel()).addMouseListener(new SystemMouslisten(23));
            this.add(this.hidden);
            Font font = new Font("微软雅黑", 1, 12);

            //座驾四维属性颜色调整地方
            (this.labmountintelligence = new JLabel()).setBounds(220, 113, 99, 17);
            this.labmountintelligence.setForeground(Color.YELLOW);
            this.labmountintelligence.setFont(font);
            this.add(this.labmountintelligence);
            (this.labmountpower = new JLabel()).setBounds(220, 148, 99, 17);
            this.labmountpower.setForeground(Color.YELLOW);
            this.labmountpower.setFont(font);
            this.add(this.labmountpower);
            (this.labmountRootbone = new JLabel()).setBounds(220, 183, 99, 17);
            this.labmountRootbone.setForeground(Color.YELLOW);
            this.labmountRootbone.setFont(font);
            this.add(this.labmountRootbone);
            (this.labmountexp = new JLabel()).setBounds(220, 217, 99, 17);
            this.labmountexp.setForeground(Color.YELLOW);
            this.labmountexp.setFont(font);
            this.add(this.labmountexp);

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
            this.listmount.setCellRenderer(new MyRendererXIAOCAR());
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
                        if (listmount.getSelectedIndex()>=0) {
                            Car mount=ZhuJpanel.getListCar().get(listmount.getSelectedIndex());
                            ExpIncreaseUntil.showCarValue(mount);
                            if (mount.getMountid()==ImageMixDeal.userimg.getRoleShow().getCar_id()) {
                                btnRiding.setText("休息");
                            }else {
                                btnRiding.setText("骑乘");
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

            (this.btnRiding = new MountPanelBtn("", 1, "骑乘", this, 11)).setBounds(32, 448, 64, 24);
            this.add(this.btnRiding);
        } else{
            (this.offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 1002)).setBounds(703, 15, 17, 17);
            this.add(this.offBtn);
            (this.labmountimg = new JLabel()).setBounds(25, 43, 125, 185);
            this.add(this.labmountimg);
            (this.hidden = new JLabel()).addMouseListener(new SystemMouslisten(23));
            this.add(this.hidden);
            Font font = new Font("微软雅黑", 1, 12);

            //座驾四维属性颜色调整地方
            (this.labmountintelligence = new JLabel()).setBounds(220, 113, 99, 17);
            this.labmountintelligence.setForeground(Color.YELLOW);
            this.labmountintelligence.setFont(font);
            this.add(this.labmountintelligence);
            (this.labmountpower = new JLabel()).setBounds(220, 148, 99, 17);
            this.labmountpower.setForeground(Color.YELLOW);
            this.labmountpower.setFont(font);
            this.add(this.labmountpower);
            (this.labmountRootbone = new JLabel()).setBounds(220, 183, 99, 17);
            this.labmountRootbone.setForeground(Color.YELLOW);
            this.labmountRootbone.setFont(font);
            this.add(this.labmountRootbone);
            (this.labmountexp = new JLabel()).setBounds(220, 217, 99, 17);
            this.labmountexp.setForeground(Color.YELLOW);
            this.labmountexp.setFont(font);
            this.add(this.labmountexp);

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
            this.listmount.setCellRenderer(new MyRendererXIAOCAR());
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
                        if (listmount.getSelectedIndex()>=0) {
                            Car mount=ZhuJpanel.getListCar().get(listmount.getSelectedIndex());
                            ExpIncreaseUntil.showCarValue(mount);
                            if (mount.getMountid()==ImageMixDeal.userimg.getRoleShow().getCar_id()) {
                                btnRiding.setText("休息");
                            }else {
                                btnRiding.setText("骑乘");
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

            (this.btnRiding = new MountPanelBtn("", 1, "骑乘", this, 11)).setBounds(32, 448, 64, 24);
            this.add(this.btnRiding);
        }

    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.time += 24L;
        TestSetupJpanel ss = TestSetupJframe.getTestSetupJframe().getTestSetupJpanel();
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconH == null) {
                this.iconH = new ImageIcon("inkImg/background1/坐驾背景.png");
            }
            g.drawImage(this.iconH.getImage(), 0, 0, 534, 516, this);

            //座驾四维属性位置调整地方
            this.labmountintelligence.setBounds(132, 345, 99, 18);
            this.labmountpower.setBounds(132, 375, 99, 18);
            this.labmountRootbone.setBounds(132, 316, 99, 18);
            this.labmountexp.setBounds(132, 405, 99, 18);
            this.jScrollPane1.setBounds(50, 74, 200, 200);
            this.hidden.setBounds(570, 25, 15, 15);
            this.offBtn.setBounds(703, 15, 17, 17);
            try {
                this.btnRiding.setIcons(CutButtonImage.cuts("inkImg/Client/60X26按钮.png"));
                this.btnRiding.setBounds(105, 475, 60, 26);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (this.listmount.getSelectedIndex() != -1) {
                int index = this.listmount.getSelectedIndex();
                if (ZhuJpanel.getListCar().size() > index) {
                    Car mount = (Car)ZhuJpanel.getListCar().get(index);
                    if (this.newPart == null) {
                        this.mountid = (int)mount.getMountid();
                        this.se = ImageMixDeal.userimg.getRoleShow().getSpecies_id();
                        long skin = Long.parseLong(this.se.longValue()+"999"+this.mountid);
                        this.newPart = SpriteFactory.createPart(skin, 2, 1, null);
                    }
                    else if (this.mountid != (int)mount.getMountid() || this.se.compareTo(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) != 0) {
                        this.mountid = (int)mount.getMountid();
                        this.se = ImageMixDeal.userimg.getRoleShow().getSpecies_id();
                        long skin = Long.parseLong(this.se.longValue()+"999"+this.mountid);
                        this.newPart = SpriteFactory.createPart(skin, 2, 1, null);
                    }
                    this.newPart.draw(g, 380, 400, 0, ImageMixDeal.userimg.getTime());//座驾显示位置
                }
            }
        }else{
            if (this.iconS == null) {
                this.iconS = new ImageIcon("inkImg/background1/坐驾背景hongmu.png");
            }
            g.drawImage(this.iconS.getImage(), 0, 0, 534, 516, this);

            //座驾四维属性位置调整地方
            this.labmountintelligence.setBounds(132, 345, 99, 18);
            this.labmountpower.setBounds(132, 375, 99, 18);
            this.labmountRootbone.setBounds(132, 316, 99, 18);
            this.labmountexp.setBounds(132, 405, 99, 18);
            this.jScrollPane1.setBounds(50, 74, 200, 200);
            this.hidden.setBounds(570, 25, 15, 15);
            this.offBtn.setBounds(703, 15, 17, 17);
            try {
                this.btnRiding.setIcons(CutButtonImage.cuts("inkImg/hongmu/aaa8.png"));
                this.btnRiding.setBounds(105, 475, 60, 26);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (this.listmount.getSelectedIndex() != -1) {
                int index = this.listmount.getSelectedIndex();
                if (ZhuJpanel.getListCar().size() > index) {
                    Car mount = (Car)ZhuJpanel.getListCar().get(index);
                    if (this.newPart == null) {
                        this.mountid = (int)mount.getMountid();
                        this.se = ImageMixDeal.userimg.getRoleShow().getSpecies_id();
                        long skin = Long.parseLong(this.se.longValue()+"999"+this.mountid);
                        this.newPart = SpriteFactory.createPart(skin, 2, 1, null);
                    }
                    else if (this.mountid != (int)mount.getMountid() || this.se.compareTo(ImageMixDeal.userimg.getRoleShow().getSpecies_id()) != 0) {
                        this.mountid = (int)mount.getMountid();
                        this.se = ImageMixDeal.userimg.getRoleShow().getSpecies_id();
                        long skin = Long.parseLong(this.se.longValue()+"999"+this.mountid);
                        this.newPart = SpriteFactory.createPart(skin, 2, 1, null);
                    }
                    this.newPart.draw(g, 380, 400, 0, ImageMixDeal.userimg.getTime());//座驾显示位置
                }
            }
        }

    }
    
    public JLabel getLabmountimg() {
        return this.labmountimg;
    }
    
    public void setLabmountimg(JLabel labmountimg) {
        this.labmountimg = labmountimg;
    }
    
//    public JLabel getLabmountlevel() {
//        if (this.labmountlevel != null) {
//            this.labmountlevel.setBounds(92, 300, 99, 18);
//        }
//        return this.labmountlevel;
//    }
    
//    public void setLabmountlevel(JLabel labmountlevel) {
//        this.labmountlevel = labmountlevel;
//    }
//
//    public JLabel getLabmounttili() {
//        return this.labmounttili;
//    }
//
//    public void setLabmounttili(JLabel labmounttili) {
//        this.labmounttili = labmounttili;
//    }
//
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
    
//    public JScrollPane getjScrollPane2() {
//        return this.jScrollPane2;
//    }
//
//    public void setjScrollPane2(JScrollPane jScrollPane2) {
//        this.jScrollPane2 = jScrollPane2;
//    }
    
    public MountPanelBtn getBtnRiding() {
        return this.btnRiding;
    }
    
    public void setBtnRiding(MountPanelBtn btnRiding) {
        this.btnRiding = btnRiding;
    }
    
//    public MountPanelBtn getBtnControl() {
//        return this.btnControl;
//    }
//
//    public void setBtnControl(MountPanelBtn btnControl) {
//        this.btnControl = btnControl;
//    }
//
//    public MountPanelBtn getBtnFeeding() {
//        return this.btnFeeding;
//    }
//
//    public void setBtnFeeding(MountPanelBtn btnFeeding) {
//        this.btnFeeding = btnFeeding;
//    }
//
//    public MountPanelBtn getBtnKills() {
//        return this.btnKills;
//    }
//
//    public void setBtnKills(MountPanelBtn btnKills) {
//        this.btnKills = btnKills;
//    }
//
//    public static JList<String> getListpet() {
//        return carJPanel.listpet;
//    }
//
//    public static void setListpet(JList<String> listpet) {
//        carJPanel.listpet = listpet;
//    }
    
    public static int getIdx() {
        return carJPanel.idx;
    }
    
    public static void setIdx(int idx) {
        carJPanel.idx = idx;
    }
//
//    public static DefaultListModel<String> getListModel() {
//        return carJPanel.listModel;
//    }
//
//    public static void setListModel(DefaultListModel<String> listModel) {
//        carJPanel.listModel = listModel;
//    }
    
    public MountPanelBtn getBtnRelease() {
        return this.btnRelease;
    }
    
    public void setBtnRelease(MountPanelBtn btnRelease) {
        this.btnRelease = btnRelease;
    }
    
    public static int getIdxx() {
        return carJPanel.idxx;
    }
    
    public static void setIdxx(int idxx) {
        carJPanel.idxx = idxx;
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
    
//    public JLabel[] getPetlist() {
//        return this.petlist;
//    }
//
//    public void setPetlist(JLabel[] petlist) {
//        this.petlist = petlist;
//    }
//
//    public Map<Integer, RoleSummoning> getP() {
//        return this.p;
//    }
//
//    public void setP(Map<Integer, RoleSummoning> p) {
//        this.p = p;
//    }
//
//    public List<MountPanelBtn> getShouhu() {
//        return this.shouhu;
//    }
//
//    public void setShouhu(List<MountPanelBtn> shouhu) {
//        this.shouhu = shouhu;
//    }
    
    static {
        carJPanel.idx = -1;
        carJPanel.idx2 = -1;
        carJPanel.idxx = -1;
    }
}
