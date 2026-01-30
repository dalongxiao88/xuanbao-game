package org.come.Jpanel;

import com.tool.ModerateTask.TaskData;
import com.tool.btn.FormsOnOffBtn;
import com.tool.btn.GJBtn;
import com.tool.role.RoleData;
import com.tool.tcpimg.RichLabel;
import com.tool.tcpimg.UIUtils;
import org.come.Frame.ZhuFrame;
import org.come.bean.ConfigureBean;
import org.come.entity.Goodstable;
import org.come.model.Configure;
import org.come.until.ActiveSrcollPanelUI;
import org.come.until.CutButtonImage;
import org.come.until.UserMessUntil;
import org.come.until.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class GZJpanel extends JPanel {

    private GjModelJpanel[] gjBtns;
    private GJBtn lq, dhBtn;
    private JLabel[] jLabels;

    private Goodstable[] goodstables;

    private TaskData taskData;
    private RichLabel richLabel;
    private String gz = "";
    private String gz1 = "";
    private ImageIcon goodItemImg = CutButtonImage.getImage("inkImg/Client/无加号坐骑格.png", 60, 60);
    private JScrollPane paneList;

    public GZJpanel() {

        this.setPreferredSize(new Dimension(871, 540));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        // 关闭按钮事件
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button1/B448.png", 1, 60888);
        offBtn.setBounds(670 , 14, 22, 21);
        this.add(offBtn);
        BigDecimal zgj = RoleData.getRoleData().getLoginResult().getScoretype("功绩");
        gz = Util.setZhiWeiRank(zgj.intValue());
        goodstables = new Goodstable[6];
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = item.get(new BigDecimal(11));
        String zqsld = configure.getZqsld();
        String[] v = zqsld.split(",");
        jLabels = new JLabel[6];
        for (int i = 0; i < jLabels.length; i++) {
            int finalI = i;
            jLabels[i] = new JLabel(i + "") {
                int index = finalI;

                @Override
                protected void paintComponent(Graphics g) {
//                    super.paintComponent(g);
                    Goodstable goodstable = goodstables[index];
                    if (goodstable != null) {
                        g.drawImage(goodItemImg.getImage(), 0, 0, null);
                        final ImageIcon image = CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 57, 57);
                        g.drawImage(image.getImage(), 0, 0, null);
                    }
                }
            };
//            if (i < 2) {
            jLabels[i].setBounds(317 + i * 80, 320, 60, 70);
//            } else {
//                jLabels[i].setBounds(84 + i * 76, 290, 64, 60);
//            }
            jLabels[i].addMouseListener(new MouseListener() {
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
                    JLabel source = (JLabel) e.getSource();
                    int index = Integer.parseInt(source.getText());
                    Goodstable goodstable = goodstables[index];
                    if (goodstable != null) {
                        ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
                    }

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    ZhuFrame.getZhuJpanel().cleargoodtext();
                }
            });
            this.add(jLabels[i]);
        }

        getPaneList();

//        BigDecimal zgj = RoleData.getRoleData().getLoginResult().getScoretype("功绩");
        gjBtns = new GjModelJpanel[v.length];
        for (int i = 0; i < v.length; i++) {
            String[] v1 = v[i].split("=");
            int i1 = Integer.parseInt(v1[0]);
            String s = v1[1];
            TaskData taskData = UserMessUntil.getTaskData(i1);
            gjBtns[i] = new GjModelJpanel(taskData, this);

//            gjBtns[i].setGjName(s);
            gjBtns[i].setBounds(8, 5+ i * 66, 300, 60);
            showView(gjBtns[i]);
//            this.add(gjBtns[i]);
        }
        richLabel = new RichLabel();

        richLabel.setBounds(270, 15, 120, 40);
        this.add(richLabel);

//        lq = new GJBtn("inkImg/hongmu1/B464.png", 1, UIUtils.COLOR_BLACK,
//                UIUtils.HYXLSJ17, "领取", 9999, this, taskData);
//        lq.setBounds(480, 190, 100, 30);
//        this.add(lq);
//        if (MyIsif.getStyle().equals("水墨")) {
            dhBtn = new GJBtn("inkImg/button/32n.png", 1,UIUtils.COLOR_Pack1,
                    UIUtils.TEXT_FONT61, "兑换", 9998, this, taskData);
            dhBtn.setBounds(180, 331, 74, 36);
            this.add(dhBtn);
//        } else {
//            dhBtn = new GJBtn("inkImg/hongmu/B30h.png", 1, UIUtils.COLOR_RED_BTNTEXT,
//                    UIUtils.TEXT_FONT, "兑换", 9998, this, taskData);
//            dhBtn.setBounds(575, 18, 34, 17);
//            this.add(dhBtn);
//        }
    }

    private JList<GjModelJpanel> JlisJalb;

    public JList<GjModelJpanel> getJlisJalb() {
        if (JlisJalb == null) {
            JlisJalb = new JList<GjModelJpanel>();
            JlisJalb.setSelectionBackground(new Color(122, 117, 112));
            JlisJalb.setSelectionForeground(Color.white);
            JlisJalb.setForeground(Color.white);
            JlisJalb.setFont(UIUtils.TEXT_HY16);
            JlisJalb.removeAll();
            JlisJalb.setBackground(UIUtils.Color_BACK); // 设置列表框为透明背景
            DefaultListCellRenderer cellRenderer = new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    return this;
                }
            };
            JlisJalb.setCellRenderer(cellRenderer);
            JlisJalb.setOpaque(false);
        }
        return JlisJalb;

    }

    public JScrollPane getPaneList() {
//        if (MyIsif.getStyle().equals("水墨")) {
        if (paneList == null) {
            paneList = new JScrollPane(getJlisJalb());
            paneList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            paneList.getVerticalScrollBar().setUI(new ActiveSrcollPanelUI());
            paneList.getVerticalScrollBar().setUnitIncrement(20);
            paneList.getViewport().setOpaque(false);
            paneList.setOpaque(false);
            paneList.setBounds(320, 73, 379, 205);
            paneList.setBorder(BorderFactory.createEmptyBorder());
            paneList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            this.add(paneList);
        }
        return paneList;
    }

    private static void BjczJpanel() {
        // TODO Auto-generated method stub

    }


    private static void BjczJpanel(List<Map<String, Object>> dataNpcMapList) {
        // TODO Auto-generated method stub

    }


    private ImageIcon icon;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        icon = CutButtonImage.getImage("inkImg/background/gj-back.png", 710, 413);
        g.drawImage(icon.getImage(), 0, 0, this);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setFont(UIUtils.TEXT_HY99);
        g2d.setColor(new Color(134, 27, 16));
        g2d.drawString(gz, 190, 295);
        g2d.drawString(gz, 190, 295);
        g2d.drawString(gz, 190, 295);
        BigDecimal zgj = RoleData.getRoleData().getLoginResult().getScoretype("功绩积分");
        g2d.setFont(UIUtils.TEXT_FONT1);
        g2d.drawString(zgj.toString(), 190, 317);
//        Util.drawPrice(g2d, new BigDecimal(99999), 190, 317);


    }

    public void showGoods(TaskData taskData, String gzName, GJBtn gjBtn) {
//        for (GjModelJpanel btn : gjBtns) {
//            try {
//                if (gjBtn.getText().equals(btn.getText())) {
//                    btn.setIcons(CutButtonImage.cuts("inkImg/hongmu1/B4641.png"));
//                    btn.btnchange(1);
//                } else {
//                    btn.setIcons(CutButtonImage.cuts("inkImg/hongmu1/B4642.png"));
//                    btn.btnchange(0);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
        BigDecimal zgj = RoleData.getRoleData().getLoginResult().getScoretype("功绩");
        gz = Util.setZhiWeiRank(zgj.intValue());
        gz1 = gzName;
        Integer i1 = Util.gjMap.get(gz);
        Integer i2 = Util.gjMap.get(gzName);
//        richLabel.setText("#K" + taskData.getTaskText());
//        richLabel.setBounds(210, 95, 120, 40);
//        Dimension d = richLabel.computeSize(400);
//        richLabel.setSize(d);
//        richLabel.setPreferredSize(d);
        richLabel.setVisible(false);
        this.taskData = taskData;
        String[] v = taskData.getGoods().split("\\|");
        int length = v.length > 5 ? 5 : v.length;
        for (int i = 0; i < goodstables.length; i++) {
            goodstables[i] = null;
        }
        for (int i = 0; i < length; i++) {
            try {
                Goodstable getgoodstable = UserMessUntil.getgoodstable(new BigDecimal(v[i]));
                goodstables[i] = getgoodstable;
            } catch (Exception e) {

            }
        }

    }

    public GjModelJpanel[] getGjBtns() {
        return gjBtns;
    }

    public void setGjBtns(GjModelJpanel[] gjBtns) {
        this.gjBtns = gjBtns;
    }

    public GJBtn getLq() {
        return lq;
    }

    public void setLq(GJBtn lq) {
        this.lq = lq;
    }

    public String getGz() {
        return gz;
    }

    public void setGz(String gz) {
        this.gz = gz;
    }

    public String getGz1() {
        return gz1;
    }

    public void setGz1(String gz1) {
        this.gz1 = gz1;
    }

    public TaskData getTaskData() {
        return taskData;
    }

    public void setTaskData(TaskData taskData) {
        this.taskData = taskData;
    }

    /**
     * 展示面板
     */
    public void showView(GjModelJpanel jLabel) {
        JlisJalb.add(jLabel);
        JlisJalb.setPreferredSize(new Dimension(350, 76 * jLabels.length));
    }
}
