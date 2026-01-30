package org.come.Jpanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.tool.tcpimg.RichLabel;
import org.come.Frame.ActivityJframe;
import org.come.model.ActiveBase;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;

import com.tool.btn.ActivityBtn;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;

/**
 * 活动model面板
 *
 * @author HGC<br>
 * @time 2020年3月2日 下午1:12:27<br>
 * @class 类名:ActivityModelJpanel<br>
 */
public class ActivityModelJpanel extends JPanel {

    private ActivityBtn btnGo;

    private ActiveBase activeBase;
    private JLabel labImg, labName, labSite, labNum;
    private RichLabel labvitalityNum;
    public static ActivityModelJpanel selectActivityModelJpanel;

    public ActivityModelJpanel() {
        setPreferredSize(new Dimension(371, 76));
        setOpaque(false);
        setLayout(null);

        getBtnGo();
        getLabImg();
        getLabName();
        getLabNum();
        getLabSite();
        getLabvitalityNum();
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                extracted();
                selectActivityModelJpanel= (ActivityModelJpanel) e.getSource();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void extracted() {
        String menu = "";
        if (activeBase.getSid() == 101) {
            menu = "小鬼";
        }
        if (activeBase.getSid() == 102) {
            menu = "天庭";
        }
        if (activeBase.getSid() == 103) {
            menu = "鬼王";
        }
        if (activeBase.getSid() == 104) {
            menu = "修罗";
        }
        if (activeBase.getSid() == 105) {
            menu = "域外";
        }
        if (activeBase.getSid() == 106) {
            menu = "宝象";
        }
        ActivityJpanel.WBXS = menu;
        ActivityJpanel.CJTYPENUM = menu;
        try {
            ActivityJframe.getActivityJframe().getActivityJpanel().updateButtonState(ActivityJpanel.CJTYPE[activeBase.getSid() - 101], menu, activeBase.getSid());
        }catch (Exception e1){
            ActivityJframe.getActivityJframe().getActivityJpanel().updateButtonState(0, menu, activeBase.getSid());

        }
    }

    public void showActiveBase(ActiveBase activeBase, int sumReceive) {
        if (activeBase == null) {
            return;
        }
        this.activeBase = activeBase;
        int num = activeBase.getNum();
        StringBuffer buffer = new StringBuffer();
        buffer.append("日次数 ");
        buffer.append(sumReceive);
        buffer.append("/");
        buffer.append(num);
        labNum.setText(buffer.toString());
        buffer.setLength(0);
        int value = activeBase.getValue();
        buffer.append("活跃度 ");
        buffer.append(((sumReceive > num ? num : sumReceive) * value));
        buffer.append("/");
        buffer.append((num * value));
//        labvitalityNum.setText(buffer.toString());
        labvitalityNum.setTextSize(activeBase.getText(), 200);
        labSite.setText(activeBase.getHead());
        labName.setText(activeBase.getaName());
        labImg.setIcon(GoodsListFromServerUntil.imgpath2(activeBase.getSkin()));
        String guide = activeBase.getGuide();
        if (guide != null && !"".equals(guide)) {
            if (getStringOneChar(guide, "-") == 3) {
                btnGo.setText("前往");
            } else {
                btnGo.setText("前往");
            }
            btnGo.setVisible(true);
        } else {
            btnGo.setVisible(false);
        }
//        labSite.setVisible(false);
//        labvitalityNum.setVisible(false);
//        labNum.setVisible(false);
    }

    /**
     * 计算某个字符串中包含多少个某个字符串
     */
    public int getStringOneChar(String value, String oldChar) {
        String replace = value.replace(oldChar, "");
        return value.length() - replace.length();
    }

    private ImageIcon icon, imgBack;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        if(MyIsif.getStyle().equals("水墨")) {
        if (icon == null) {
            icon = CutButtonImage.getImage("inkImg/background/activeItemback.png", 358, 71);
        }
        g.drawImage(icon.getImage(), 1, 5, null);
        labImg.setBounds(17, 18, 45, 45);
        labName.setBounds(65, 9, 100, 20);
        labName.setFont(new Font("楷体", Font.BOLD, 15));
        labName.setForeground(new Color(75, 54, 33));
        labNum.setBounds(263, 50, 120, 20);

//        if (imgBack == null) {
//            imgBack = CutButtonImage.getImage("inkImg/background/58.png", -1, -1);
//        }
//        g.drawImage(imgBack.getImage(), 10, 10, 45, 44, null);
//        }else {
//        	if (icon == null) {
//        		icon = CutButtonImage.getImage("inkImg/background/S177.png", 345, 2);
//        	}
//        	g.drawImage(icon.getImage(), 13, 59, 345, 2, null);
//        	if (imgBack == null) {
//        		imgBack = CutButtonImage.getImage("img/xy2uiimg/border_quac1k.png", -1, -1);
//        	}
//        	g.drawImage(imgBack.getImage(), 10, 10, 45, 44, null);
//        }

    }

    public ActivityBtn getBtnGo() {
        if (btnGo == null) {
//            if (MyIsif.getStyle().equals("水墨")) {
            btnGo = new ActivityBtn("inkImg/button/32n.png", 1, UIUtils.COLOR_HUI1, "前往", UIUtils.TEXT_FONT61, 2,
                    this);
            btnGo.setBounds(275, 20, 73, 36);
            this.add(btnGo);
//            } else {
//                btnGo = new ActivityBtn("inkImg/hongmu/6026.png", 1, UIUtils.COLOR_BTNPUTONG, "前往", UIUtils.TEXT_HY88, 2,
//                        this);
//                btnGo.setBounds(295, 20, 60, 26);
//                this.add(btnGo);
//            }
        }
        return btnGo;
    }

    public void setBtnGo(ActivityBtn btnGo) {
        this.btnGo = btnGo;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public ActiveBase getActiveBase() {
        return activeBase;
    }

    public void setActiveBase(ActiveBase activeBase) {
        this.activeBase = activeBase;
    }

    public JLabel getLabImg() {
        if (labImg == null) {
            labImg = new JLabel();
            labImg.setBounds(17, 18, 45, 45);
            this.add(labImg);
        }
        return labImg;
    }

    public void setLabImg(JLabel labImg) {
        this.labImg = labImg;
    }

    public JLabel getLabName() {
        if (labName == null) {
            labName = new JLabel();
            labName.setBounds(57, 9, 100, 20);
            labName.setFont(UIUtils.TEXT_KT_13);
            labName.setForeground(UIUtils.COLOR_CBG2);
            this.add(labName);
        }
        return labName;
    }

    public void setLabName(JLabel labName) {
        this.labName = labName;
    }

    public JLabel getLabSite() {
        if (labSite == null) {
            labSite = new JLabel();
            labSite.setBounds(152, 9, 150, 20);
            labSite.setFont(UIUtils.TEXT_KT_13);
            labSite.setForeground(UIUtils.COLOR_CBG2);
            this.add(labSite);
        }
        return labSite;
    }

    public void setLabSite(JLabel labSite) {
        this.labSite = labSite;
    }

    public JLabel getLabNum() {
        if (labNum == null) {
            labNum = new JLabel();
            labNum.setBounds(57, 34, 100, 20);
            labNum.setFont(UIUtils.TEXT_KT_13);
            labNum.setForeground(UIUtils.COLOR_CBG2);
            this.add(labNum);
        }
        return labNum;
    }

    public void setLabNum(JLabel labNum) {
        this.labNum = labNum;
    }

    public RichLabel getLabvitalityNum() {
        if (labvitalityNum == null) {
            labvitalityNum = new RichLabel();
            labvitalityNum.setBounds(68, 28, 200, 20);
            labvitalityNum.setFont(UIUtils.TEXT_KT_13);
            labvitalityNum.setForeground(UIUtils.COLOR_CBG2);
            this.add(labvitalityNum);
        }
        return labvitalityNum;
    }

    public void setLabvitalityNum(RichLabel labvitalityNum) {
        this.labvitalityNum = labvitalityNum;
    }

    public ImageIcon getImgBack() {

        return imgBack;
    }

    public void setImgBack(ImageIcon imgBack) {
        this.imgBack = imgBack;
    }


}
