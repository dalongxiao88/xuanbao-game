package org.come.Jpanel;

import com.tool.time.Limit;

import java.io.File;
import java.util.Iterator;
import come.tool.Fighting.Fightingimage;
import java.util.List;
import java.math.BigDecimal;
import com.tool.time.TimeLimit;
import com.tool.role.RoleData;
import org.come.entity.RoleSummoning;
import come.tool.Fighting.TypeState;
import come.tool.Fighting.FightingMixDeal;
import java.awt.Font;
import javax.swing.ImageIcon;
import com.updateNew.MyIsif;
import org.come.Frame.TestSetupJframe;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;

import org.come.until.CutButtonImage;
import org.come.until.UserMessUntil;
import com.tool.image.ImageMixDeal;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JList;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

import static org.come.Jpanel.TestPetJpanel.TouOrName;
import static org.come.Jpanel.TestPetJpanel.bigOsmall;

public class MyRenderer extends JPanel implements ListCellRenderer
{
    private int index;
    public static String vs;
    private static DefaultListModel<String> listModel;
    private Color color;
    
    public MyRenderer() {
        this.color = Color.white;
        this.index = 67;
    }
    
    public MyRenderer(int index) {
        this.color = Color.white;
        this.index = 67;
        this.index = index;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getWidth() / 2, 22);
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        MyRenderer mr = new MyRenderer(index);
        mr.removeAll();
        if (isSelected) {
            mr.setBackground(Color.gray);
        }
        else {
            mr.setOpaque(false);
        }
        mr.setLayout(new FlowLayout(0));
        Box box = Box.createHorizontalBox();
        box.add(new JLabel() {
            {
                this.setForeground(Color.BLACK);
                this.setText("");
                try {
                    if (ImageMixDeal.userimg.getRoleShow().getFighting() != 0) {
                        int sid = UserMessUntil.getChosePetMes().getSid().intValue();
                    }
                }
                catch (Exception ex) {}
            }
        });
        mr.add(box);
        return (Component)mr;
    }
    ImageIcon k = CutButtonImage.getImage("inkImg/button/81-2.png", -1, -1);
//    ImageIcon hk = CutButtonImage.getImage("inkImg/button/81-2.png", -1, -1);
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        TestSetupJpanel ss = TestSetupJframe.getTestSetupJframe().getTestSetupJpanel();
        List<RoleSummoning> pets = UserMessUntil.getPetListTable();
        int krd = 130;
        if (TouOrName ) {

            String path = "img/xy2uiimg/101_png.xy2uiimg.pet_def.png";
            if (UserMessUntil.getPetListTable() != null &&UserMessUntil.getPetListTable().size()>=index) {
                path = "img/head/p" + UserMessUntil.getPetListTable().get(index).getSummoningskin() + ".png";
            }
            ImageIcon peitou = CutButtonImage.getImage(path, -1, -1);



            int xzi = -20;
            if (!bigOsmall) {
                xzi = -40;
            }
            if (MyIsif.getStyle().equals("水墨")) {
                krd = 130;
                k = CutButtonImage.getImage("inkImg/button/81-2.png", -1, -1);
                ImageIcon peiimg = new ImageIcon("inkImg/button/ss507.png");
                g.drawImage(peiimg.getImage(), 171+xzi, 4, 17, 17, null);
                Font fon1t = new Font("宋体", 0, 13);
                g2d.setColor(new Color(255, 255, 255));
                g2d.setFont(fon1t);


                if (this.index >= 9) {
                    g2d.drawString(this.index + 1 + "", 171+xzi, 16);
                } else {
                    g2d.drawString(this.index + 1 + "", 174+xzi, 17);
                }
                if (this.index <= 1) {
                    g2d.drawString(this.index + 1 + "", 174+xzi, 17);
                }
            } else {
                krd =130;

                k = CutButtonImage.getImage("inkImg/hongmu/81-2hm.png", -1, -1);
                ImageIcon peiimg = new ImageIcon("inkimg/hongmu/ss544.png");
                g.drawImage(peiimg.getImage(), 171+xzi, 4, 17, 17, null);
                Font fon1t = new Font("宋体", 0, 13);
                g2d.setFont(fon1t);
                g2d.setColor(new Color(187, 165, 75));
                if (this.index >= 9) {
                    g2d.drawString(this.index + 1 + "", 171+xzi, 16);
                } else {
                    g2d.drawString(this.index + 1 + "", 174+xzi, 17);
                }
                if (this.index <= 1) {
                    g2d.drawString(this.index + 1 + "", 174+xzi, 17);
                }
            }

            g.drawImage(k.getImage(),1,1,35,35,null);
            g.drawImage(peitou.getImage(), 4 ,4, 29, 29, null);

            Fightingimage fightingimage = FightingMixDeal.getdata(0);
            if (fightingimage != null) {
                List<TypeState> data = fightingimage.getFightingManData().cxxx("召唤兽");
                for (TypeState datum : data) {
                    boolean is = false;
                    RoleSummoning roleSummoning = (RoleSummoning) UserMessUntil.getPetListTable().get(this.index);
                    if (roleSummoning.getSid().toString().equals(datum.getType())) {
                        if (datum.getState() == 2) {
                            this.color = Color.red;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }


            int namex= 20;
            Font font = new Font("宋体", 0, 17);
            g2d.setFont(font);
            if (MyIsif.getStyle().equals("水墨")) {
                g2d.setColor(this.color);
            }else{
                g2d.setColor(Color.yellow);
            }
            if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(((RoleSummoning) UserMessUntil.getPetListTable().get(this.index)).getSid()) == 0) {
                g2d.setColor(new Color(187, 165, 75));
            }
            g2d.drawString(MyRenderer.vs = ((RoleSummoning) UserMessUntil.getPetListTable().get(this.index)).getSummoningname(), 18+namex, 22);
            ImageIcon iconem = new ImageIcon("img/item/200.png");
            if (TimeLimit.getLimits().getLimit("枯荣丹") != null && RoleData.getRoleData().getLoginResult().getSummoning_id() != null) {
                Limit limit = TimeLimit.getLimits().getLimit("枯荣丹");
                if (((RoleSummoning) UserMessUntil.getPetListTable().get(this.index)).getSid().compareTo(new BigDecimal(limit.getValue())) == 0) {
                    g.drawImage(iconem.getImage(), krd, 2, 19, 19, this);
                }
            }
        }else {
            if (MyIsif.getStyle().equals("水墨")) {
                krd=130;
                ImageIcon peiimg = new ImageIcon("inkImg/button/ss507.png");
                g.drawImage(peiimg.getImage(), 1, 3, 17, 17, null);
                Font fon1t = new Font("宋体", 0, 13);
                g2d.setColor(new Color(255, 255, 255));
                g2d.setFont(fon1t);
                if (this.index >= 9) {
                    g2d.drawString(this.index + 1 + "", 2, 15);
                } else {
                    g2d.drawString(this.index + 1 + "", 5, 16);
                }
                if (this.index <= 1) {
                    g2d.drawString(this.index + 1 + "", 5, 16);
                }
            } else {
                if (!bigOsmall) {
                    krd = 105;
                }else {
                    krd = 130;
                }

                ImageIcon peiimg = new ImageIcon("inkimg/hongmu/ss544.png");
                g.drawImage(peiimg.getImage(), 1, 3, 17, 17, null);
                Font fon1t = new Font("宋体", 0, 13);
                g2d.setFont(fon1t);
                g2d.setColor(new Color(187, 165, 75));
                if (this.index >= 9) {
                    g2d.drawString(this.index + 1 + "", 2, 15);
                } else {
                    g2d.drawString(this.index + 1 + "", 5, 16);
                }
                if (this.index <= 1) {
                    g2d.drawString(this.index + 1 + "", 5, 16);
                }
            }
            Fightingimage fightingimage = FightingMixDeal.getdata(0);
            if (fightingimage != null) {
                List<TypeState> data = fightingimage.getFightingManData().cxxx("召唤兽");
                for (TypeState datum : data) {
                    boolean is = false;
                    RoleSummoning roleSummoning = (RoleSummoning) UserMessUntil.getPetListTable().get(this.index);
                    if (roleSummoning.getSid().toString().equals(datum.getType())) {
                        if (datum.getState() == 2) {
                            this.color = Color.red;
                            break;
                        } else {
                            break;
                        }
                    }
                }
            }
            Font font = new Font("宋体", 0, 17);
            g2d.setFont(font);
            if (MyIsif.getStyle().equals("水墨")) {
                g2d.setColor(this.color);
            }else{
                g2d.setColor(Color.yellow);
            }

            if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && RoleData.getRoleData().getLoginResult().getSummoning_id().compareTo(((RoleSummoning) UserMessUntil.getPetListTable().get(this.index)).getSid()) == 0) {
                g2d.setColor(new Color(187, 165, 75));
            }
            g2d.drawString(MyRenderer.vs = ((RoleSummoning) UserMessUntil.getPetListTable().get(this.index)).getSummoningname(), 18, 17);
            ImageIcon iconem = new ImageIcon("img/item/200.png");
            if (TimeLimit.getLimits().getLimit("枯荣丹") != null && RoleData.getRoleData().getLoginResult().getSummoning_id() != null) {
                Limit limit = TimeLimit.getLimits().getLimit("枯荣丹");
                if (((RoleSummoning) UserMessUntil.getPetListTable().get(this.index)).getSid().compareTo(new BigDecimal(limit.getValue())) == 0) {
                    g.drawImage(iconem.getImage(), krd, 2, 19, 19, this);
                }
            }
        }
        if (bigOsmall) {
            int x = 7;
            if (!TouOrName) {
                x = 2;
            }else {
                x = 7;
            }
            if (UserMessUntil.getPetListTable()!=null&&UserMessUntil.getPetListTable().size()>0&&RoleData.getRoleData().getLoginResult().getSummoning_id()!=null) {
                if (RoleData.getRoleData().getLoginResult().getSummoning_id().equals(UserMessUntil.getPetListTable().get(index).getSid())) {
                    ImageIcon iconem = new ImageIcon("inkImg/button/B662.png");
                    g.drawImage(iconem.getImage(), 100, x, 35, 19, this);//参战图大小
                }
            }
        }
    }
}
