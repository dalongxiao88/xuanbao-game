package org.come.Jpanel;

import java.util.List;
import java.awt.Graphics;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.math.BigDecimal;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import java.awt.Font;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.come.mouslisten.SystemMouslisten;
import com.tool.role.RoleData;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.LingHelpBtn;
import javax.swing.DefaultListModel;
import org.come.model.Lingbao;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class LingHelpListJpanel extends JPanel
{
    private JScrollPane jScrollPane;
    private JList<Lingbao> listpet;
    private DefaultListModel<Lingbao> listModel;
    private LingHelpBtn btntop;
    private LingHelpBtn btnbottom;
    private LingHelpBtn btntopset;
    private LingHelpBtn btnbottomset;
    private JLabel labOpen;
    private JLabel labLocking;
    public JLabel labgundong;
    public JLabel zhiyuan;
    public JLabel shoufa;
    public JLabel zhiSwitch;
    private ImageIcon icon;
    
    public LingHelpListJpanel() {
        BigDecimal zy = RoleData.getRoleData().getLoginResult().getScoretype("灵宝支援");
        BigDecimal sf = RoleData.getRoleData().getLoginResult().getScoretype("灵宝首发");
        (this.zhiyuan = new JLabel()).setBounds(26, 355, 15, 15);
        if (ZhuJpanel.isLingZhiyuan) {
            this.zhiyuan.setIcon(SystemMouslisten.getIcon());
        }
        this.zhiyuan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                ZhuJpanel.isLingZhiyuan = !ZhuJpanel.isLingZhiyuan;
                ZhuFrame.getZhuJpanel().uptateLingZhiyuan(true);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(this.zhiyuan);
        (this.shoufa = new JLabel()).setBounds(26, 378, 15, 15);
        this.shoufa.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(this.shoufa);
        (this.zhiSwitch = new JLabel()).setBounds(26, 400, 15, 15);
        if (ZhuJpanel.battleLingZhiSwitch) {
            this.zhiSwitch.setIcon(SystemMouslisten.getIcon());
        }
        this.zhiSwitch.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                ZhuJpanel.battleLingZhiSwitch = !ZhuJpanel.battleLingZhiSwitch;
                LingHelpListJpanel.this.uptateZhiSwitch();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(this.zhiSwitch);
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(276, 441));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 62);
            offBtn.setBounds(244, 5, 25, 25);
            this.add(offBtn);
            this.zhiyuan.setBounds(28, 356, 16, 16);
            this.shoufa.setBounds(28, 379, 16, 16);
            this.zhiSwitch.setBounds(28, 401, 16, 16);
        }
        else {
            (this.labgundong = new JLabel(new ImageIcon("img/xy2uiimg/gundongtiao_副本.png"))).setBounds(204, 66, 17, 232);
            this.add(this.labgundong);
            this.setPreferredSize(new Dimension(276, 441));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/s74.png", 1, 62);
            offBtn.setBounds(276, 5, 25, 25);
            this.add(offBtn);
        }
        this.listModel = new DefaultListModel<>();
        (this.listpet = new JList<Lingbao>() {
            {
                this.setOpaque(false);
            }
        }).setSelectionBackground(new Color(33, 42, 52));
        this.listpet.setCellRenderer(new ZyLBMyRenderer());
        this.listpet.setFont(new Font("微软雅黑", 1, 14));
        this.listpet.setBackground(UIUtils.Color_BACK);
        this.listpet.setForeground(Color.green);
        this.listpet.setSelectionForeground(Color.green);
        this.listpet.setModel(this.listModel);
        (this.jScrollPane = new JScrollPane(this.listpet)).setVerticalScrollBarPolicy(22);
        this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
        this.jScrollPane.getViewport().setOpaque(false);
        this.jScrollPane.setOpaque(false);
        if (MyIsif.getStyle().equals("水墨")) {
            this.jScrollPane.setBounds(30, 45, 198, 276);
        }
        else {
            this.jScrollPane.setBounds(30, 46, 191, 274);
            this.jScrollPane.setBorder(BorderFactory.createLineBorder(new Color(31, 7, 8)));
        }
        this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.jScrollPane.setHorizontalScrollBarPolicy(31);
        this.add(this.jScrollPane);
        if (MyIsif.getStyle().equals("水墨")) {
            (this.btntop = new LingHelpBtn("inkImg/button/B42.png", 1, 0, this)).setBounds(238, 118, 17, 17);
            this.add(this.btntop);
            (this.btnbottom = new LingHelpBtn("inkImg/button/B43.png", 1, 1, this)).setBounds(238, 147, 17, 17);
            this.add(this.btnbottom);
            (this.btntopset = new LingHelpBtn("inkImg/button/19.png", 1, 2, "顶", this)).setBounds(238, 201, 18, 18);
            this.add(this.btntopset);
            (this.btnbottomset = new LingHelpBtn("inkImg/button/19.png", 1, 3, "底", this)).setBounds(238, 230, 18, 18);
            this.add(this.btnbottomset);
        }
        else {
            (this.labOpen = new JLabel()).setBounds(31, 261, 15, 15);
            this.add(this.labOpen);
            (this.labLocking = new JLabel()).setBounds(31, 282, 15, 15);
            this.add(this.labLocking);
            (this.btntop = new LingHelpBtn("img/xy2uiimg/42_png.button.btn_1.png", 1, 0, this)).setBounds(238, 118, 20, 20);
            this.add(this.btntop);
            (this.btnbottom = new LingHelpBtn("img/xy2uiimg/34_png.button.xy_vscroll$down.png", 1, 1, this)).setBounds(238, 147, 20, 20);
            this.add(this.btnbottom);
            (this.btntopset = new LingHelpBtn("img/xy2uiimg/topset.png", 1, 2, this)).setBounds(238, 201, 17, 17);
            this.add(this.btntopset);
            (this.btnbottomset = new LingHelpBtn("img/xy2uiimg/bottomset.png", 1, 3, this)).setBounds(238, 230, 17, 17);
            this.add(this.btnbottomset);
        }
        this.shoufa.setVisible(false);
    }
    
    public void uptateZhiyuan() {
        if (ZhuJpanel.isLingZhiyuan) {
            this.zhiyuan.setIcon(SystemMouslisten.getIcon());
            String mes = Agreement.getAgreement().rolechangeAgreement("lzy=1");
            SendMessageUntil.toServer(mes);
        }
        else {
            this.zhiyuan.setIcon(null);
            String mes = Agreement.getAgreement().rolechangeAgreement("lzy=2");
            SendMessageUntil.toServer(mes);
        }
    }
    
    public void uptateZhiSwitch() {
        if (ZhuJpanel.battleLingZhiSwitch) {
            this.zhiSwitch.setIcon(SystemMouslisten.getIcon());
        }
        else {
            this.zhiSwitch.setIcon(null);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            this.icon = new ImageIcon("inkImg/background1/B254-1.png");
            g.drawImage(this.icon.getImage(), 0, 0, this);
        }
        else {
            this.icon = new ImageIcon("inkImg/hongmu1/B254.png");
            g.drawImage(this.icon.getImage(), 0, 0, this);
        }
    }
    
    public void init(List<Lingbao> list) {
        this.listModel.clear();
        for (int i = 0; i < list.size(); ++i) {
            this.listModel.addElement(list.get(i));
        }
    }
    
    public DefaultListModel<Lingbao> getListModel() {
        return this.listModel;
    }
    
    public void setListModel(DefaultListModel<Lingbao> listModel) {
        this.listModel = listModel;
    }
    
    public JList<Lingbao> getListpet() {
        return this.listpet;
    }
    
    public void setListpet(JList<Lingbao> listpet) {
        this.listpet = listpet;
    }
}
