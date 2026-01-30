package com.tool.fuben;

import com.tool.btn.FormsOnOffBtn;
import com.tool.tcp.Sprite;
import com.tool.tcp.SpriteFactory;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;
import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import org.come.until.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FuBenJpanel extends JPanel {
    private List<TaskList> taskLists;
    private fubenBtn btn1, btn2 ;
    public static int index;
    private JLabel txet1 ;
    private JLabel txet2 ;
    private JLabel txet3 ;
    private JLabel txet4 ;

    private List<Goodstable> goodstables = new ArrayList<Goodstable>();
    private List<JLabel> goodstabless = new ArrayList<JLabel>();

    private static JList<String> listfuben;
    // 列表中的对象
    private static DefaultListModel<String> listModel;// 放置对象
    // 列表中对象的名字颜色
    private Color fontcolor;
    // 滚动条
    private JScrollPane jScrollPane;

    private JTextPane Vues;
    private ImageIcon icon;
    public FuBenJpanel() {
        this.setPreferredSize(new Dimension(629, 398));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        this.setLayout(null);
        // 关闭按钮事件
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 3111);
        offBtn.setBounds(629-37, 8, 25, 25);
        this.add(offBtn);

        setTaskLists(UserMessUntil.getTaskListAll().getTaskLists());

        listModel = new DefaultListModel<String>();
        for (int i = 0; i < taskLists.size(); i++) {
            listModel.addElement(taskLists.get(i).getTaskName());
        }
        // 列表
        listfuben = new JList<String>() {
            //        listfuben = new JList<String>() {
            {
                setOpaque(false);
            } // instance initializer
        };
//            listfuben.setSelectionBackground(new Color(33, 42, 52));
//        listfuben.setCellRenderer(new MyRenderer());
        listfuben.addMouseListener(new FuBenMouslisten(listfuben, this));
        listfuben.addMouseMotionListener(new FuBenMouslisten(listfuben, this));
        fontcolor = Color.white;// 203,181,91

        listfuben.setSelectionForeground(fontcolor);
        listfuben.setForeground(fontcolor);
        listfuben.setFont(new Font("宋体", 0, 17));//随风——列表字体
        listfuben.setBackground(UIUtils.Color_BACK); // 设置列表框为透明背景
        listfuben.setModel(listModel);
        listfuben.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        // 列表滚动条
        jScrollPane = new JScrollPane(listfuben);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
        jScrollPane.getViewport().setOpaque(false);
        jScrollPane.setOpaque(false);
        jScrollPane.setBounds(50, 43, 149, 330);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(jScrollPane);

        for (int j = 0; j < 4; j++) {
            JLabel jLabel = new JLabel();
            jLabel.setBounds(389+j * 50, 278, 48, 48);
            int finalI = j;
            jLabel.addMouseListener(new MouseListener() {
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
                    if (goodstables != null) {
                        if (finalI < goodstables.size()) {
                            if (goodstables.get(finalI)!= null) {
                                ZhuFrame.getZhuJpanel().creatgoodtext(goodstables.get(finalI));
                            }
                        }
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    ZhuFrame.getZhuJpanel().cleargoodtext();
                }
            });
            this.add(jLabel);
            goodstabless.add(jLabel);
        }

        Color  fontcolor = new Color(255,255,255);
        Font font = new Font("宋体", Font.PLAIN, 14);
        int x = 290,y = 236,add=29;
        txet1= new JLabel();
        txet1.setForeground(fontcolor);
        txet1.setFont(font);
        txet1.setBounds(x, y + add, 80, 20);
        y=  y + add;
        this.add(txet1);

        txet2= new JLabel();
        txet2.setForeground(fontcolor);
        txet2.setFont(font);
        txet2.setBounds(x, y + add, 80, 20);
        y=  y + add;
        this.add(txet2);

        txet3= new JLabel();
        txet3.setForeground(fontcolor);
        txet3.setFont(font);
        txet3.setBounds(x, y + add, 80, 20);
        y=  y + add;
        this.add(txet3);

        txet4= new JLabel();
        txet4.setForeground(fontcolor);
        txet4.setFont(font);
        txet4.setBounds(x, y + add, 80, 20);
        y=  y + add;
        this.add(txet4);
        if (MyIsif.getStyle().equals("水墨") ) {//副本图片
            if (icon == null) {
        btn1 = new fubenBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_FONT811, "领取任务",  this);
        btn1.setBounds(x+100, y -10, 99, 24);
        this.add(btn1);

        btn2 = new fubenBtn("inkImg/button1/B22.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_FONT811, "领取奖励",  this);
        btn2.setBounds(x + 210, y -10, 99, 24);
        this.add(btn2);
    }
        }
    else{if (icon == null) {
            btn1 = new fubenBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_FONT811, "领取任务",  this);
            btn1.setBounds(x+100, y -10, 99, 24);
            this.add(btn1);

            btn2 = new fubenBtn("inkImg/hongmu1/B463.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_FONT811, "领取奖励",  this);
            btn2.setBounds(x + 210, y -10, 99, 24);
            this.add(btn2);
        }
    }
    }



    /**
     *
     * @param index
     * @param drops
     * @param txet1 人数
     * @param txet2 等级
     * @param txet3 次数
     * @param txet4 重置时间
     */
    public void UpShow(int index, String drops,int txet1,String txet2,String txet3,String txet4,String hard,String time,int chongzhi) {

        // 移除旧的组件
        if (Vues != null) {
            this.remove(Vues);
        }
        String p ="";
        if (chongzhi==3)p="月";
        if (chongzhi==2)p="周";
        if (chongzhi==1)p="日";

        String text = "";
        text="<html>" +
                "<span style='color: white;'>参与条件：</span><span style='color: yellow;'>角色等级≥"+txet2+"</span><br>" +
                "<span style='color: white;'>难度：</span><span style='color: red;'>"+hard+"</span><br>" +
                "<span style='color: white;'>时间：</span><span style='color: yellow;'>"+time+"</span><br>" +
                "<span style='color: white;'>次数：</span><span style='color: yellow;'>1次/"+p+"</span><br>" +
                "<span style='color: white;'>玩法流程：</span><span style='color: yellow;'>通过副本界面，点击进入进入副本。</span><br>" +
                "</html>";

        Font font = new Font("宋体", Font.PLAIN, 15);
        Vues = new JTextPane();
        Vues.setContentType("text/html");
        Vues.setText(text);
        Vues.setEditable(false);
        Vues.setBackground(UIUtils.Color_BACK); // 设置背景透明或匹配面板
        Vues.setBounds(385, 65, 200, 400);
        this.add(Vues);
        this.add(Vues);

        if (goodstables != null) {
            String[] dropss = drops.split("\\|");
            goodstables = new ArrayList<Goodstable>();
            if (dropss.length>0) {
                for (int i = 0; i < dropss.length; i++) {
                    if (i >= 4) {
                        break;
                    }
                    if (dropss[i] != null && !dropss[i].equals("")) {
                        Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(dropss[i]));
                        if (goodstable != null) {
                            goodstables.add(goodstable);
                            goodstabless.get(i).setIcon(GoodsListFromServerUntil.imgpath2(goodstable.getSkin()));
                        }
                    }else {
                        goodstabless.get(i).setIcon(null);
                    }
                }
            }
        }

//        txet1 = "人数测试";
//        txet2 = "等级测试";
//        txet3 = "次数测试";
//        txet4 = "重置时间测试";
        getTxet1().setText(txet1+"");
        getTxet2().setText(txet2);
        getTxet3().setText(txet3+"/"+p);
        if (!p.equals("")) {
            getTxet4().setText("每"+p);
        }else {
            getTxet4().setText("");
        }


        revalidate();
        repaint();

    }

    public void init() {
        setTaskLists(UserMessUntil.getTaskListAll().getTaskLists());

        listModel = new DefaultListModel<String>();
        for (int i = 0; i < taskLists.size(); i++) {
            listModel.addElement(taskLists.get(i).getTaskName());
        }


        // 列表
        listfuben = new JList<String>() {
            //        listfuben = new JList<String>() {
            {
                setOpaque(false);
            } // instance initializer
        };
//            listfuben.setSelectionBackground(new Color(33, 42, 52));
//        listfuben.setCellRenderer(new MyRenderer());
        listfuben.addMouseListener(new FuBenMouslisten(listfuben, this));
        listfuben.addMouseMotionListener(new FuBenMouslisten(listfuben, this));
        fontcolor = Color.white;// 203,181,91

        listfuben.setSelectionForeground(fontcolor);
        listfuben.setForeground(fontcolor);
        listfuben.setFont(new Font("仿宋", 1, 17));//随风——列表字体
        listfuben.setBackground(UIUtils.Color_BACK); // 设置列表框为透明背景
        listfuben.setModel(listModel);
        listfuben.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        // 列表滚动条
        jScrollPane = new JScrollPane(listfuben);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.getVerticalScrollBar().setUI(new SrcollPanelUI());
        jScrollPane.getViewport().setOpaque(false);
        jScrollPane.setOpaque(false);
        jScrollPane.setBounds(50, 43, 149, 330);
        jScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(jScrollPane);

        for (int j = 0; j < 4; j++) {
            JLabel jLabel = new JLabel();
            jLabel.setBounds(389+j * 50, 278, 48, 48);
            int finalI = j;
            jLabel.addMouseListener(new MouseListener() {
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
                    if (goodstables != null) {
                        if (finalI < goodstables.size()) {
                            if (goodstables.get(finalI)!= null) {
                                ZhuFrame.getZhuJpanel().creatgoodtext(goodstables.get(finalI));
                            }
                        }
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    ZhuFrame.getZhuJpanel().cleargoodtext();
                }
            });
            this.add(jLabel);
            goodstabless.add(jLabel);
        }
    }
//    static Sprite dengguang = SpriteFactory.VloadSprite("img/fb/was/灯光秀.was", null);
    static Sprite part= SpriteFactory.VloadSprite("img/fb/was/凤凰1.tcp", null);

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨") ) {//副本图片
            if (icon == null) {
                icon = CutButtonImage.getImage("resource/xinUI/xin/smfbmb.png", -1, -1);
            }
        }else{
            if (icon == null) {
                icon = CutButtonImage.getImage("resource/xinUI/xin/smfbmb1.png", -1, -1);
            }
        }

        g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);

        long l = System.currentTimeMillis();
//        dengguang.updateToTime(l, 0);
//        dengguang.draw(g, 280, 230);
        part.updateToTime(l, 0);
        part.draw(g, 280, 230);


        g.setFont(UIUtils.TEXT_FONT811);
        g.setColor(new Color(187, 165, 75));

    }

    public List<TaskList> getTaskLists() {
        return taskLists;
    }

    public void setTaskLists(List<TaskList> taskLists) {
        this.taskLists = taskLists;
    }

    public JLabel getTxet1() {
        return txet1;
    }

    public void setTxet1(JLabel txet1) {
        this.txet1 = txet1;
    }

    public JLabel getTxet2() {
        return txet2;
    }

    public void setTxet2(JLabel txet2) {
        this.txet2 = txet2;
    }

    public JLabel getTxet3() {
        return txet3;
    }

    public void setTxet3(JLabel txet3) {
        this.txet3 = txet3;
    }

    public JLabel getTxet4() {
        return txet4;
    }

    public void setTxet4(JLabel txet4) {
        this.txet4 = txet4;
    }
}

