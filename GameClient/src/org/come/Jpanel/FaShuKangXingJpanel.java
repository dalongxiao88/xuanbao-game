package org.come.Jpanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import com.tool.btn.FaShuKangXingBtn;
import com.updateNew.MyIsif;
import org.come.Frame.*;
import org.come.until.CutButtonImage;
import org.come.until.FormsManagement;

import com.tool.tcpimg.UIUtils;
import org.come.until.UserMessUntil;

public class FaShuKangXingJpanel extends JPanel {
    private DefaultListModel<String> dlm;
    private DefaultListModel<String> dlm1;
    public static int idx;

    public DefaultListModel<String> getDlm() {
        return dlm;
    }

    public void setDlm(DefaultListModel<String> dlm) {
        this.dlm = dlm;
    }

    public DefaultListModel<String> getDlm1() {
        return dlm1;
    }

    public void setDlm1(DefaultListModel<String> dlm1) {
        this.dlm1 = dlm1;
    }

    public JList<String> getListNo1() {
        return listNo1;
    }

    public void setListNo1(JList<String> listNo1) {
        this.listNo1 = listNo1;
    }

    public JList<String> getListNo2() {
        return listNo2;
    }

    public void setListNo2(JList<String> listNo2) {
        this.listNo2 = listNo2;
    }

    private JList<String> listNo1;
    private JList<String> listNo2;

    /**
     * 1法术抗性 2法术增强 3物理属性 4五行属性 5其他属性
     */
    private int type;

    private FaShuKangXingBtn icon;
    private Boolean isOpen = true;


    public FaShuKangXingJpanel(int type) {
        this.type = type;
        this.setBackground(new Color(0, 0, 0, 0));
        // this.setPreferredSize(new Dimension(290, 200));
        this.setLayout(null);
        dlm = new DefaultListModel<String>();
        dlm1 = new DefaultListModel<String>();
        if ("水墨".equals(MyIsif.getStyle())) {
            if (type == 1) {
                icon = new FaShuKangXingBtn("inkImg/button/smfskx.png", 1, type, this);
            } else if (type == 2) {
                icon = new FaShuKangXingBtn("inkImg/button/smwlsx.png", 1, type, this);
            } else if (type == 3) {
                icon = new FaShuKangXingBtn("inkImg/button/smwxsx.png", 1, type, this);
            } else if (type == 4) {
                icon = new FaShuKangXingBtn("inkImg/button/smfszq.png", 1, type, this);
            } else if (type == 5) {
                icon = new FaShuKangXingBtn("inkImg/button/smqtsx.png", 1, type, this);
            }
            icon.setBounds(0, 0, 299, 21);
        } else {
            if (type == 1) {
                icon = new FaShuKangXingBtn("inkImg/Client/hmfskx.png", 1, type, this);
            } else if (type == 2) {
                icon = new FaShuKangXingBtn("inkImg/Client/hmwlkx.png", 1, type, this);
            } else if (type == 3) {
                icon = new FaShuKangXingBtn("inkImg/Client/hmwxsx.png", 1, type, this);
            } else if (type == 4) {
                icon = new FaShuKangXingBtn("inkImg/Client/hmfszq.png", 1, type, this);
            } else if (type == 5) {
                icon = new FaShuKangXingBtn("inkImg/Client/hmqtsx.png", 1, type, this);
            }
            icon.setBounds(1, 0, 299, 24);

        }
        icon.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
                RoleResistanceJframe.getResistancejframe().mouseDragged(e);
            }
        });
        this.add(icon);

        listNo1 = new JList<String>();
        // listNo1.setBounds(20, 26, 130, 300);
        listNo1.setForeground(Color.white);
        listNo1.setFont(UIUtils.TEXT_FONT2);
        listNo1.setBackground(UIUtils.Color_BACK);
        listNo1.setModel(dlm);
        listNo1.setCellRenderer(new MyRenderera());
        this.listNo1.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

                if (e.getY() / 20 < listNo1.getModel().getSize()) {
                    int index = listNo1.locationToIndex(e.getPoint());
                    String s = dlm.get(index);
                    MsgJframe6.getJframe6().getJapnel6().xy(s, type);
//                    System.out.printf(s);
                }

            }
        });
        listNo1.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                FormsManagement.HideForm(6333);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // 打开了窗体
                if (e.isMetaDown()) {// 检测鼠标右键单击
                    FormsManagement.HideForm(8);
                }

            }
        });
        this.add(listNo1);
        listNo2 = new JList<String>();
        // listNo2.setBounds(150, 26, 130, 100);
        listNo2.setOpaque(false);
        listNo2.setForeground(Color.white);
        listNo2.setFont(UIUtils.TEXT_FONT2);
        listNo2.setBackground(UIUtils.Color_BACK);
        listNo2.setModel(dlm1);
        listNo2.setCellRenderer(new MyRenderera());
        this.listNo2.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

                if (e.getY() / 20 < listNo2.getModel().getSize()) {
                    int index = listNo2.locationToIndex(e.getPoint());
                    String s = dlm1.get(index);
                    MsgJframe6.getJframe6().getJapnel6().xy(s, type);
//                    System.out.printf(s);
                }

            }
        });
        listNo2.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                FormsManagement.HideForm(6333);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // 打开了窗体
                if (e.isMetaDown()) {// 检测鼠标右键单击
                    FormsManagement.HideForm(8);
                }

            }
        });
        this.add(listNo2);

        // this.pack();
    }


    public FaShuKangXingJpanel(int type, Boolean b) {
        this.type = type;
        this.setBackground(new Color(0, 0, 0, 0));
        // this.setPreferredSize(new Dimension(290, 200));
        this.setLayout(null);
        dlm = new DefaultListModel<String>();

        dlm1 = new DefaultListModel<String>();

        if ("水墨".equals(MyIsif.getStyle())) {
            if (type == 10) {
                icon = new FaShuKangXingBtn("inkImg/button/smfskx.png", 1, type, this);
            } else if (type == 11) {
                icon = new FaShuKangXingBtn("inkImg/button/smwlsx.png", 1, type, this);
            } else if (type == 12) {
                icon = new FaShuKangXingBtn("inkImg/button/smwxsx.png", 1, type, this);
            } else if (type == 13) {
                icon = new FaShuKangXingBtn("inkImg/button/smfszq.png", 1, type, this);
            } else if (type == 14) {
                icon = new FaShuKangXingBtn("inkImg/button/smqtsx.png", 1, type, this);
            }
            icon.setBounds(0, 0, 299, 21);

        }else{

            if (type == 10) {
                icon = new FaShuKangXingBtn("inkImg/Client/hmfskx.png", 1, type, this);
            } else if (type == 11) {
                icon = new FaShuKangXingBtn("inkImg/Client/hmwlkx.png", 1, type, this);
            } else if (type == 12) {
                icon = new FaShuKangXingBtn("inkImg/Client/hmwxsx.png", 1, type, this);
            } else if (type == 13) {
                icon = new FaShuKangXingBtn("inkImg/Client/hmfszq.png", 1, type, this);
            } else if (type == 14) {
                icon = new FaShuKangXingBtn("inkImg/Client/hmqtsx.png", 1, type, this);
            }
            icon.setBounds(1, 0, 299, 24);
        }

        icon.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
                RolePetResistanceJframe.getResistancejframe().mouseDragged(e);
            }
        });
        this.add(icon);

        listNo1 = new JList<String>();
        // listNo1.setBounds(20, 26, 130, 300);
        listNo1.setForeground(Color.white);
        listNo1.setFont(UIUtils.TEXT_FONT2);
        listNo1.setBackground(UIUtils.Color_BACK);
        listNo1.setModel(dlm);
        listNo1.setCellRenderer(new MyRenderera1());
        this.listNo1.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

                if (e.getY() / 20 < listNo1.getModel().getSize()) {
                    int index = listNo1.locationToIndex(e.getPoint());
                    String s = dlm.get(index);
                    MsgJframe6.getJframe6().getJapnel6().pet(s, type);
//                    System.out.printf(s);
                }

            }
        });
        listNo1.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                FormsManagement.HideForm(6333);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // 打开了窗体
                if (e.isMetaDown()) {// 检测鼠标右键单击
                    FormsManagement.HideForm(8);
                }

            }
        });
        this.add(listNo1);
        listNo2 = new JList<String>();
        // listNo2.setBounds(150, 26, 130, 100);
        listNo2.setOpaque(false);
        listNo2.setForeground(Color.white);
        listNo2.setFont(UIUtils.TEXT_FONT2);
        listNo2.setBackground(UIUtils.Color_BACK);
        listNo2.setModel(dlm1);
        listNo2.setCellRenderer(new MyRenderera1());
        this.listNo2.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

                if (e.getY() / 20 < listNo2.getModel().getSize()) {
                    int index = listNo2.locationToIndex(e.getPoint());
                    String s = dlm1.get(index);
                    MsgJframe6.getJframe6().getJapnel6().pet(s, type);
//                    System.out.printf(s);
                }

            }
        });
        listNo2.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                FormsManagement.HideForm(6333);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // 打开了窗体
                if (e.isMetaDown()) {// 检测鼠标右键单击
                    FormsManagement.HideForm(8);
                }

            }
        });
        this.add(listNo2);

        // this.pack();
    }


    private ImageIcon iconBack = new ImageIcon("img/xy2uiimg/kx_zj.png");
    private ImageIcon iconBottom = CutButtonImage.getImage("img/xy2uiimg/personalLast.png", -1, -1);
    private ImageIcon openImage;

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        if ("水墨".equals(MyIsif.getStyle())) {
//            if (icon == null) {
//                openImage = CutButtonImage.getImage("img/xy2uiimg/smkxsqd.png", -1, -1);
//            }
//            if (!isOpen) {
//                g.drawImage(openImage.getImage(), 50, 2, null);
//            }
//            // 设置背景
//            if (this.getHeight() > 34)
//                g.drawImage(iconBack.getImage(), 0, 4, 290, this.getHeight() - 8, this);
//            // 设置底框
//            if (this.getHeight() > 34)
//                g.drawImage(iconBottom.getImage(), 0, this.getHeight() - 4, 290, 4, this);

//            g.drawImage(icon.getImage(), 0, 0, 290, 20, this);
        } else {
            if (icon == null) {
                openImage = CutButtonImage.getImage("inkImg/Client/smkxsqd.png", -1, -1);
            }

        }
    }
//            iconBack = new ImageIcon("img/qiehuan/resistance-content-bg.png");
//            iconBottom = CutButtonImage.getImage("img/qiehuan/resistance-footer-bg.png", -1, -1);
//            if (icon == null) {
////                switch (type) {
////                    case 1:
////                        icon = CutButtonImage.getImage("img/qiehuan/resistance-header-kx.png", -1, -1);
////                        break;
////                    case 2:
////                        icon = CutButtonImage.getImage("img/qiehuan/resistance-header-zq.png", -1, -1);
////                        break;
////                    case 3:
////                        icon = CutButtonImage.getImage("img/qiehuan/resistance-header-wl.png", -1, -1);
////                        break;
////                    case 4:
////                        icon = CutButtonImage.getImage("img/qiehuan/resistance-header-wx.png", -1, -1);
////                        break;
////                    case 5:
////                        icon = CutButtonImage.getImage("img/qiehuan/resistance-header-qt.png", -1, -1);
////                        break;
////                    default:
////                        break;
////                }
//            }

//            // 设置背景
//            if (this.getHeight() > 34)
//                g.drawImage(iconBack.getImage(), 0, 4, 290, this.getHeight() - 8, this);
//            // 设置底框
//            if (this.getHeight() > 34)
//                g.drawImage(iconBottom.getImage(), 0, this.getHeight() - 4, 290, 4, this);

//            g.drawImage(icon.getImage(), 0, 0, 290, 24, this);


    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }


}
