package org.come.Jpanel;

import java.awt.Graphics;
import java.util.Map;
import org.come.bean.ConfigureBean;
import java.util.List;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.apache.commons.lang.StringUtils;
import org.come.until.Util;
import org.come.entity.MountSkill;
import javax.swing.BorderFactory;
import org.come.until.SrcollPanelUI;
import org.come.Frame.MountSkillsJframe;
import org.come.entity.Mount;
import org.come.Frame.MountJframe;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Color;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import com.tool.tcpimg.RichLabel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

public class MountSkillsJpanel extends JPanel
{
    private JList<String> listmountskill;
    private DefaultListModel<String> modelmountskill;
    private RichLabel box;
    private JScrollPane jScrollPane;
    private JLabel labProficiency;
    ImageIcon icon;
    
    public MountSkillsJpanel() throws Exception {
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(318, 327));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 20);
            offBtn.setBounds(281, 10, 25, 25);
            this.add(offBtn);
            this.modelmountskill = new DefaultListModel<>();
            (this.listmountskill = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            this.listmountskill.setSelectionForeground(Color.GREEN);
            this.listmountskill.setForeground(Color.GREEN);
            this.listmountskill.setFont(new Font("微软雅黑", 0, 16));
            this.listmountskill.setBackground(UIUtils.Color_BACK);
            this.listmountskill.setModel(this.modelmountskill);
            this.listmountskill.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex() != -1) {
                        int index = MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex();
                        Mount mount = (Mount)ZhuJpanel.getListMount().get(index);
                        if (((Mount)ZhuJpanel.getListMount().get(index)).getMountskill().size() != 0) {
                            int i = (MountSkillsJpanel.this.listmountskill.getSelectedIndex() != -1) ? MountSkillsJpanel.this.listmountskill.getSelectedIndex() : 0;
                            MountSkillsJpanel.this.showSkillMsg(mount, i);
                        }
                        else {
                            MountSkillsJframe.getMountSkillsJframe().getMountSkillsJpanel().getModelmountskill().removeAllElements();
                            MountSkillsJframe.getMountSkillsJframe().getMountSkillsJpanel().showSkillMsg(null);
                        }
                    }
                }
            });
            this.listmountskill.setBounds(47, 52, 247, 70);
            this.add(this.listmountskill);
            (this.box = new RichLabel()).setFont(UIUtils.TEXT_FONT3);
            (this.jScrollPane = new JScrollPane(this.box)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(50, 122, 250, 160);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.labProficiency = new JLabel()).setBounds(104, 291, 188, 12);
            this.labProficiency.setForeground(Color.WHITE);
            this.labProficiency.setFont(new Font("微软雅黑", 0, 13));
            this.add(this.labProficiency);
        }
        else {
            this.setPreferredSize(new Dimension(337, 357));
            this.setLayout(null);
            this.setBackground(UIUtils.Color_BACK);
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 20);
            offBtn.setBounds(317, 0, 23, 23);
            this.add(offBtn);
            this.modelmountskill = new DefaultListModel<>();
            (this.listmountskill = new JList<String>() {
                {
                    this.setOpaque(false);
                }
            }).setSelectionBackground(new Color(33, 42, 52));
            this.listmountskill.setSelectionForeground(Color.GREEN);
            this.listmountskill.setForeground(Color.GREEN);
            this.listmountskill.setFont(new Font("微软雅黑", 0, 16));
            this.listmountskill.setBackground(UIUtils.Color_BACK);
            this.listmountskill.setModel(this.modelmountskill);
            this.listmountskill.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex() != -1) {
                        int index = MountJframe.getMountjframe().getMountjpanel().getListmount().getSelectedIndex();
                        Mount mount = (Mount)ZhuJpanel.getListMount().get(index);
                        if (((Mount)ZhuJpanel.getListMount().get(index)).getMountskill().size() != 0) {
                            int i = (MountSkillsJpanel.this.listmountskill.getSelectedIndex() != -1) ? MountSkillsJpanel.this.listmountskill.getSelectedIndex() : 0;
                            MountSkillsJpanel.this.showSkillMsg(mount, i);
                        }
                        else {
                            MountSkillsJframe.getMountSkillsJframe().getMountSkillsJpanel().getModelmountskill().removeAllElements();
                            MountSkillsJframe.getMountSkillsJframe().getMountSkillsJpanel().showSkillMsg(null);
                        }
                    }
                }
            });
            this.listmountskill.setBounds(35, 46, 247, 70);
            this.add(this.listmountskill);
            (this.box = new RichLabel()).setFont(UIUtils.TEXT_FONT22);
            (this.jScrollPane = new JScrollPane(this.box)).setVerticalScrollBarPolicy(22);
            this.jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
            this.jScrollPane.getVerticalScrollBar().setUnitIncrement(30);
            this.jScrollPane.getViewport().setOpaque(false);
            this.jScrollPane.setOpaque(false);
            this.jScrollPane.setBounds(37, 128, 265, 170);
            this.jScrollPane.setBorder(BorderFactory.createEmptyBorder());
            this.jScrollPane.setHorizontalScrollBarPolicy(31);
            this.add(this.jScrollPane);
            (this.labProficiency = new JLabel()).setBounds(146, 317, 130, 17);
            this.labProficiency.setForeground(Color.WHITE);
            this.labProficiency.setFont(new Font("微软雅黑", 0, 13));
            this.add(this.labProficiency);
        }
    }
    
    public void showSkillMsg(Mount mount, int index) {
        StringBuffer buffer = new StringBuffer();
        List<MountSkill> mountskill = mount.getMountskill();
        if (mountskill != null && mountskill.size() > index) {
            String skillname = ((MountSkill)mount.getMountskill().get(index)).getSkillname();
            String skillMsg = Util.getSkillMsg(skillname);
            if (StringUtils.isNotBlank(skillMsg)) {
                buffer.append("#W");
                buffer.append(skillMsg);
                buffer.append("#r");
                buffer.append("【熟练度】");
                buffer.append(mount.getProficiency());
                buffer.append("/");
                int up = 100000;
                if ((int)mount.getMountlvl() > 100) {
                    ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
                    Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
                    Configure configure = (Configure)item.get(new BigDecimal(1));
                    up = Integer.parseInt(configure.getZqsld());
                }
                buffer.append(up);
            }
            String[] mountaut = Util.calculateAddition(mount, skillname).split("\\|");
            if (mountaut != null) {
                buffer.append("#r");
                for (int i = 0; i < mountaut.length; ++i) {
                    buffer.append("#G");
                    if (i > 0) {
                        buffer.append("，");
                    }
                    buffer.append(Util.changeToPercentage(mountaut[i]));
                }
            }
        }
        this.showSkillMsg(buffer.toString());
    }
    
    public void showSkillMsg(String msg) {
        this.box.setText("");
        this.box.addText(msg, UIUtils.TEXT_FONT78);
        Dimension d = this.box.computeSize(110);
        this.box.setSize(d);
        this.box.setPreferredSize(d);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B226.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 318, 327, this);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/71_png.xy2uiimg.mountskill.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 337, 357, this);
        }
    }
    
    public JList<String> getListmountskill() {
        return this.listmountskill;
    }
    
    public void setListmountskill(JList<String> listmountskill) {
        this.listmountskill = listmountskill;
    }
    
    public DefaultListModel<String> getModelmountskill() {
        return this.modelmountskill;
    }
    
    public void setModelmountskill(DefaultListModel<String> modelmountskill) {
        this.modelmountskill = modelmountskill;
    }
    
    public JLabel getLabProficiency() {
        return this.labProficiency;
    }
    
    public void setLabProficiency(JLabel labProficiency) {
        this.labProficiency = labProficiency;
    }
}
