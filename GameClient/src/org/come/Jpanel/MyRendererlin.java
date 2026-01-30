package org.come.Jpanel;

import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import com.tool.time.Limit;
import com.tool.time.TimeLimit;
import com.updateNew.MyIsif;
import come.tool.Fighting.FightingMixDeal;
import come.tool.Fighting.Fightingimage;
import come.tool.Fighting.TypeState;
import org.come.Frame.TestSetupJframe;
import org.come.entity.RoleSummoning;
import org.come.until.AnalysisString;
import org.come.until.UserMessUntil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class MyRendererlin extends JPanel implements ListCellRenderer {
    private int index;
    public static String vs;
    private boolean isSelected;
    private static DefaultListModel<String> listModel;

    public MyRendererlin() {
    }

    public MyRendererlin(final int index, final boolean isSelected) {
        this.index = index;
        this.isSelected = isSelected;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth() / 2, 22);
    }

    @Override
    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
        final MyRendererlin mr = new MyRendererlin(index, isSelected);
        mr.removeAll();
        if (SpiritualJpanel.idx == index || isSelected) {
            mr.setBackground(Color.GRAY);
        } else {
            mr.setOpaque(false);
        }
        mr.setLayout(new FlowLayout(0));
        final Box box = Box.createHorizontalBox();
        box.add(new JLabel() {
            {
                this.setForeground(Color.BLACK);
                this.setText("");
                try {
                    if (ImageMixDeal.userimg.getRoleShow().getFighting() != 0) {
                        final Fightingimage fightingimage = FightingMixDeal.getdata(0);
                        final List<TypeState> data = fightingimage.getFightingManData().cxxx("召唤兽");
                        final Map<String, Integer> map = new HashMap<String, Integer>() {

                            {
                                final Iterator<RoleSummoning> iterator = UserMessUntil.getPetListTable().iterator();
                                while (iterator.hasNext()) {
                                    final RoleSummoning roleSummoning = (RoleSummoning) iterator.next();
                                    final Iterator<TypeState> iterator2 = data.iterator();
                                    while (iterator2.hasNext()) {
                                        final TypeState datum = (TypeState) iterator2.next();
                                        if (datum.getType().equals(roleSummoning.getSid().toString())) {
                                            this.put(roleSummoning.getSummoningname(), Integer.valueOf(datum.getState()));
                                        }
                                    }
                                }
                            }
                        };
                        final int sid = UserMessUntil.getChosePetMes().getSid().intValue();
                        int i = 0;
                        while (i < data.size()) {
                            if (UserMessUntil.getChosePetMes() == null) {
                                if (((Integer) map.get(value.toString())).intValue() != 0) {
                                    this.setForeground(Color.red);
                                } else {
                                    if (sid == Integer.parseInt(((TypeState) data.get(i)).getType())) {
                                        this.setForeground(Color.white);
                                    }
                                }
                            }
                            ++i;
                        }
                    }
                } catch (Exception ex) {
                }
            }
        });
        mr.add((Component) box);
        return (Component) mr;
    }

    @Override
    public void paint(final Graphics g) {
        super.paintComponent(g);
        if(index>=UserMessUntil.getPetListTable().size()) return;
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        final TestSetupJpanel ss = TestSetupJframe.getTestSetupJframe().getTestSetupJpanel();
        final List<RoleSummoning> pets = UserMessUntil.getPetListTable();
        if (!MyIsif.getStyle().equals("水墨UI")) {
            final ImageIcon peiimg = new ImageIcon("inkImg/hongmu/b2.png");
            g.drawImage(peiimg.getImage(), 15, 3, 17, 17, null);
            final Font fon1t = new Font("宋体", 0, 13);
            g2d.setFont(fon1t);
            g2d.setColor(new Color(187, 165, 75));
            if (this.index >= 9) {
                g2d.drawString(this.index + 1 + "", 17, 15);
            } else {
                g2d.drawString(this.index + 1 + "", 20, 16);
            }
            if (this.index <= 1) {
                g2d.drawString(this.index + 1 + "", 20, 16);
            }
        } else {
            final ImageIcon peiimg = new ImageIcon("img/toutusm/zhsbh.png");
            g.drawImage(peiimg.getImage(), 15, 3, 17, 17, null);
            final Font fon1t = new Font("宋体", 0, 13);
            g2d.setFont(fon1t);
            g2d.setColor(new Color(255, 255, 255));
            if (this.index >= 9) {
                g2d.drawString(this.index + 1 + "", 17, 15);
            } else {
                g2d.drawString(this.index + 1 + "", 20, 16);
            }
            if (this.index <= 1) {
                g2d.drawString(this.index + 1 + "", 20, 16);
            }
        }
        final Font font = new Font("宋体", 0, 17);
        g2d.setFont(font);
        if (this.isSelected) {
            g2d.setColor(Color.green);
            g2d.drawString("√", -2, 17);
            if(UserMessUntil.getChosePetMes()== null) return;
            final int lvltruepet = AnalysisString.petTurnRount(UserMessUntil.getChosePetMes().getGrade().intValue());
//            if (GameClientcaonimab.getClassic() == 1) {
//                TestPetJframe.getTestPetJframe().getTestPetJpanel().changeSoaringPanelS(lvltruepet);
//            }
//            if (GameClientcaonimab.getClassic() == 2) {
//                TestPetJframe.getTestPetJframe().getTestPetJpanel().changeSoaringPanelH(lvltruepet);
//            }
//            if (GameClientcaonimab.getClassic() == 3) {
//                TestPetJframe.getTestPetJframe().getTestPetJpanel().changeSoaringPanelD(lvltruepet);
//            }
        } else {
            g2d.setColor(Color.white);
        }
        if (ImageMixDeal.userimg.getRoleShow().getFighting() != 0) {
            if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(((RoleSummoning) UserMessUntil.getPetListTable().get(this.index)).getSid()) == 0) {
                g2d.setColor(new Color(255, 0, 0));
            }
        } else {
            if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(((RoleSummoning) UserMessUntil.getPetListTable().get(this.index)).getSid()) == 0) {
                g2d.setColor(new Color(187, 165, 75));
            }
        }
        try {
            RoleSummoning roleSummoning = UserMessUntil.getPetListTable().get(this.index);
        }catch (Exception e){
            return;
        }
        g2d.drawString(MyRendererlin.vs = ((RoleSummoning) UserMessUntil.getPetListTable().get(this.index)).getSummoningname(), 35, 17);
        final ImageIcon iconem = new ImageIcon("inkimg/danxin/ss286.png");
        if (TimeLimit.getLimits().getLimit("枯荣丹") != null && RoleData.getRoleData().getLoginResult().getSummoning_id() != null) {
            final Limit limit = TimeLimit.getLimits().getLimit("枯荣丹");
            if (((RoleSummoning) UserMessUntil.getPetListTable().get(this.index)).getSid().compareTo(new BigDecimal(limit.getValue())) == 0) {
                g.drawImage(iconem.getImage(), 124, 2, 19, 19, (ImageObserver) this);
            }
        }
    }
}
