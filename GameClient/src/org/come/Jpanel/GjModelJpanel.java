package org.come.Jpanel;

import com.tool.ModerateTask.TaskData;
import com.tool.btn.GJBtn;
import com.tool.tcpimg.RichLabel;
import com.tool.tcpimg.UIUtils;
import org.come.model.ActiveBase;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 活动model面板
 *
 * @author HGC<br>
 * @time 2020年3月2日 下午1:12:27<br>
 * @class 类名:ActivityModelJpanel<br>
 */
public class GjModelJpanel extends JPanel {
    private GJBtn lq, dhBtn;
//    private ActivityBtn btnGo;


    private ActiveBase activeBase;
    private JLabel labImg, labName, labSite, labNum;
    private RichLabel labvitalityNum;
    private TaskData taskData;
    private String gz;
    private GZJpanel  gzJpanel;

    public GjModelJpanel(TaskData taskData, GZJpanel gzJpanel) {
        setPreferredSize(new Dimension(371, 76));

        setOpaque(false);
        setLayout(null);
        this.taskData = taskData;
        this.gzJpanel = gzJpanel;
        lq = new GJBtn("inkImg/button/32n.png", 1, UIUtils.COLOR_Pack1,
                UIUtils.TEXT_FONT61, "领取", 9999, gzJpanel, taskData);
        lq.setBounds(220, 10, 74, 36);
        lq.setGjName(taskData.getTaskOpen());
        this.add(lq);
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
//                String menu = "";
//                if (activeBase.getSid() == 101) {
//                    menu = "小鬼";
//                }
//                if (activeBase.getSid() == 102) {
//                    menu = "天庭";
//                }
//                if (activeBase.getSid() == 103) {
//                    menu = "鬼王";
//                }
//                if (activeBase.getSid() == 104) {
//                    menu = "修罗";
//                }
//                if (activeBase.getSid() == 105) {
//                    menu = "域外";
//                }
//                if (activeBase.getSid() == 106) {
//                    menu = "宝象";
//                }
//                ActivityJpanel.WBXS = menu;
//                ActivityJpanel.CJTYPENUM = menu;
//                try {
//                    ActivityJframe.getActivityJframe().getActivityJpanel().updateButtonState(ActivityJpanel.CJTYPE[activeBase.getSid() - 101], menu, activeBase.getSid());
//                } catch (Exception e1) {
//                    ActivityJframe.getActivityJframe().getActivityJpanel().updateButtonState(0, menu, activeBase.getSid());
//
//                }
                gzJpanel.showGoods(taskData, taskData.getTaskOpen(),null);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
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
            icon = CutButtonImage.getImage("inkImg/background/activeItemback.png", 300, 58);
        }
        g.drawImage(icon.getImage(), 1, 1, null);
        labImg.setBounds(11, 3, 45, 45);
        labName.setBounds(65, 9, 100, 20);
        labName.setFont(new Font("楷体", Font.BOLD, 15));
        labName.setForeground(new Color(75, 54, 33));
        labNum.setBounds(263, 50, 120, 20);


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
            labImg.setBounds(17, 5, 45, 45);
            labImg.setIcon(CutButtonImage.getImage("img/head/N"+ taskData.getTaskName()+".png",43,43));
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
            labName.setFont(UIUtils.TEXT_MSG1);
            labName.setForeground(UIUtils.Color_BACK);
            labName.setText(taskData.getTaskName());
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
            labSite.setFont(UIUtils.TEXT_MSG1);
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
            labNum.setFont(UIUtils.TEXT_MSG1);
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
            labvitalityNum.setFont(UIUtils.TEXT_MSG1);
            labvitalityNum.setForeground(UIUtils.COLOR_CBG2);
            String taskText = taskData.getTaskText();
            String replace = taskText.replace("#W", "#K").replace("#Y","#R");
            labvitalityNum.setTextSize(replace, 200);
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
