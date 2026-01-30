package org.come.XuanBao;

import com.tool.tcpimg.ChatBox;
import com.tool.tcpimg.UIUtils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.come.XuanBao.RoleXuanBao;
import org.come.bean.XuanBao;
import org.come.entity.Goodstable;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;


public class XuanBaoMsgJpanel
        extends JPanel {
    private JLabel quality = new JLabel();
    private JLabel baoname;
    private JLabel baolvl;
    private JLabel baotext;
    private ChatBox bao = new ChatBox();
    private JLabel fushi;
    private JLabel[] fushiimg = new JLabel[5];
    private ChatBox shi = new ChatBox();
    private JLabel[] skillimg = new JLabel[5];
    private JLabel[] skillname = new JLabel[5];
    private ImageIcon kaiqi = new ImageIcon("img/lingbao/msg/小开.png");
    private ImageIcon guanbi = new ImageIcon("img/lingbao/msg/小封.png");
    private ImageIcon fushikaiqi = new ImageIcon("img/lingbao/msg/小圆开.png");
    private ImageIcon fushiguanbi = new ImageIcon("img/lingbao/msg/小圆封.png");

    private ImageIcon icon;
    private ImageIcon lingimg;
    private JLabel lexing = new JLabel();
    private JLabel menkai = new JLabel();
    private JLabel suoshuxuanzhi = new JLabel();
    private JLabel jinengxiaohao = new JLabel();
    private JLabel xuanyinjineng = new JLabel();
    private ChatBox skill = new ChatBox();
    private ChatBox skill1 = new ChatBox();
    private ChatBox skill2 = new ChatBox();


    public int y;


    public int y1;


    public int y2;


    public int y3;


    public XuanBaoMsgJpanel() {
        this.y = 0;
        this.y1 = 0;
        this.y2 = 0;
        this.y3 = 0;
        this.quality.setBounds(15, 5, 90, 25);
        this.quality.setForeground(new Color(205, 79, 57));
        this.quality.setFont(UIUtils.TEXT_MSG);
        this.quality.setHorizontalAlignment(0);
        this.quality.setVerticalAlignment(0);
        add(this.quality);
        this.baoname = new JLabel();
        this.baoname.setBounds(110, 5, 55, 25);
        this.baoname.setFont(UIUtils.TEXT_MSG);
        this.baoname.setForeground(Color.WHITE);
        add(this.baoname);
        this.baolvl = new JLabel();
        this.baolvl.setBounds(165, 5, 80, 25);
        this.baolvl.setFont(UIUtils.TEXT_MSG);
        this.baolvl.setForeground(new Color(205, 79, 57));
        add(this.baolvl);
        this.baotext = new JLabel();
        this.baotext.setBounds(110, 30, 300, 25);
        this.baotext.setFont(UIUtils.TEXT_MSG);
        this.baotext.setForeground(Color.WHITE);
        add(this.baotext);
        this.lexing.setBounds(110, 55, 200, 25);
        this.lexing.setFont(UIUtils.TEXT_MSG);
        this.lexing.setForeground(Color.WHITE);
        add(this.lexing);
        this.menkai.setBounds(110, 80, 200, 25);
        this.menkai.setFont(UIUtils.TEXT_MSG);
        this.menkai.setForeground(Color.WHITE);
        add(this.menkai);
        this.suoshuxuanzhi.setBounds(110, 105, 200, 25);
        this.suoshuxuanzhi.setFont(UIUtils.TEXT_MSG);
        this.suoshuxuanzhi.setForeground(Color.WHITE);
        add(this.suoshuxuanzhi);
        this.jinengxiaohao.setBounds(110, 130, 200, 25);
        this.jinengxiaohao.setFont(UIUtils.TEXT_MSG);
        this.jinengxiaohao.setForeground(Color.WHITE);
        add(this.jinengxiaohao);
        this.xuanyinjineng.setBounds(110, 130, 200, 25);
        this.xuanyinjineng.setFont(UIUtils.TEXT_MSG);
        this.xuanyinjineng.setForeground(Color.GREEN);
        add(this.xuanyinjineng);
        this.fushi = new JLabel();
        this.fushi.setBounds(125, 140, 30, 25);
        this.fushi.setFont(UIUtils.TEXT_MSG);
        this.fushi.setForeground(new Color(210, 180, 140));
        this.fushi.setText("玄印");
        add(this.fushi);
        for (int i = 0; i < 5; i++) {
            this.fushiimg[i] = new JLabel();
            this.fushiimg[i].setBounds(145, 145 + i * 18, 18, 18);
            add(this.fushiimg[i]);
        }
        setPreferredSize(new Dimension(470, 460));
        setLayout((LayoutManager) null);
        setBackground(new Color(0, 0, 0, 0));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/background/S144.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        if (this.lingimg != null) {
            g.drawImage(this.lingimg.getImage(), 10, 30, 96, 96, this);
        }

        Graphics g2 = g.create(110, 150, 360, 125);
        this.bao.paint(g2);
        g2.dispose();
        Graphics g3 = g.create(180, this.y, 285, 105);
        this.shi.paint(g3);
        g3.dispose();
        Graphics g4 = g.create(110, this.y1, 360, 125);
        this.skill.paint(g4);
        g4.dispose();
        Graphics g5 = g.create(110, this.y2, 360, 125);
        this.skill1.paint(g5);
        g5.dispose();
        Graphics g6 = g.create(110, this.y3, 360, 125);
        this.skill2.paint(g6);
        g6.dispose();
    }

    public void xuanbaoshou(XuanBao xuanBao) {
        this.bao.removemsg();
        this.quality.setText("品质  " + xuanBao.getPinzhi());
        this.baoname.setText(xuanBao.getName());
        this.baolvl.setText(xuanBao.getLvl() + " 级");
        this.baotext.setText(" 1111111111111111111");
        this.lingimg = RoleXuanBao.lingbaoimg(xuanBao.getSkin(), -1, -1);
        this.lexing.setText("【玄宝类型】  " + xuanBao.type);
        this.menkai.setText("【所属门派】  无");
        this.suoshuxuanzhi.setText("【所属玄支】  无");
        this.jinengxiaohao.setText("【技能消耗】  无");
        String m = xuanBao.getSkill1().replace("x", "#R50#G");
        this.bao.addText("#G" + m, 350);
        this.fushi.setBounds(125, this.bao.getHeight() + 150, 30, 25);
        for (int i1 = 0; i1 < 5; i1++) {
            int y = this.bao.getHeight() + 145 + 30 + i1 * 18;
            this.fushiimg[i1].setBounds(145, y, 18, 18);
        }

        int i = 0;
        String[] shanb = null;
        if (xuanBao.getFushi() != null && !xuanBao.getFushi().equals("")) {
            shanb = xuanBao.getFushi().split("\\|");
        }
        this.shi.removemsg();
        for (i = 0; i < 5; i++) {
            if (i >= xuanBao.getNum()) {
                fushixiugai(i, "#cD2B48C未开启", this.fushiguanbi);
            } else if (shanb != null && i < shanb.length) {
                Goodstable goodstable = (Goodstable) GoodsListFromServerUntil.fushis.get(new BigDecimal(shanb[i]));
                if (goodstable != null) {
                    String[] vfu = goodstable.getValue().split("\\|");
                    StringBuffer fua = new StringBuffer();
                    fua.append("#cD2B48C");

                    for (int j = 0; j < vfu.length; j++) {
                        if (vfu[j].isEmpty()) vfu[j] = "测试数据=测试数据";
                        if (j == 0) {
                            fua.append("[" + vfu[j].split("=")[1] + "]");
                        } else {
                            fua.append(vfu[j].split("=")[0] + vfu[j].split("=")[1]);
                        }
                    }

                    fushixiugai(i, fua.toString(), CutButtonImage.getImage("img/lingbao/msg/" + goodstable.getSkin() + ".png", 28, 28));
                }
            } else {
                fushixiugai(i, "#cD2B48C已开启", this.fushikaiqi);
            }
        }
        this.xuanyinjineng.setText("【玄宝印鸣技能】");
        this.xuanyinjineng.setBounds(110, this.fushiimg[4].getY() - 75 + 100, 200, 25);
        this.y1 = this.fushiimg[4].getY() - 75 + 120;
        this.skill.removemsg();
        this.skill.addText("#G" + xuanBao.getSkill2().replace("x", "#R50#G").split("玄印槽中镶嵌")[0], 350);
        this.skill1.removemsg();
        this.y2 = this.y1 + this.skill.getHeight();
        this.skill1.addText("#G" + xuanBao.getSkill3().replace("x", "#R50#G").split("玄印槽中镶嵌")[0], 350);
        this.skill2.removemsg();
        this.y3 = this.y2 + this.skill1.getHeight();
        this.skill2.addText("#G" + xuanBao.getSkill4().replace("x", "#R50#G").split("玄印槽中镶嵌")[0], 350);
    }

    public void fushixiugai(int i, String text, ImageIcon imageIcon) {
        this.shi.addText(text, 285);
        this.y = this.fushiimg[i].getY() - 75;
        this.fushiimg[i].setIcon(imageIcon);
    }


    public List<String> extractBracketContents(String input) {
        List<String> contents = new ArrayList<>();

        String regex = "\\[(.*?)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {

            String content = matcher.group(1);
            contents.add(content);
        }

        return contents;
    }

    public float setaplpha(String text, int r, int g, int y, int b) {
        String[] mes = ((String) extractBracketContents(text).get(0)).split(",");
        if (mes[0].equals("蓝") && mes[1].equals("红") && b >= 1 && r >= 1) {
            return 1.0F;
        }
        if (mes[0].equals("蓝") && mes[1].equals("蓝") && b >= 2) {
            return 1.0F;
        }
        if (mes[0].equals("蓝") && mes[1].equals("绿") && b >= 1 && g >= 1) {
            return 1.0F;
        }
        if (mes[0].equals("蓝") && mes[1].equals("黄") && b >= 1 && r >= 1) {
            return 1.0F;
        }

        if (mes[0].equals("红") && mes[1].equals("蓝") && r >= 1 && b >= 1) {
            return 1.0F;
        }
        if (mes[0].equals("红") && mes[1].equals("红") && r >= 2) {
            return 1.0F;
        }
        if (mes[0].equals("红") && mes[1].equals("绿") && r >= 1 && g >= 1) {
            return 1.0F;
        }
        if (mes[0].equals("红") && mes[1].equals("黄") && r >= 1 && y >= 1) {
            return 1.0F;
        }


        if (mes[0].equals("绿") && mes[1].equals("蓝") && g >= 1 && b >= 1) {
            return 1.0F;
        }
        if (mes[0].equals("绿") && mes[1].equals("绿") && g >= 2) {
            return 1.0F;
        }
        if (mes[0].equals("绿") && mes[1].equals("红") && g >= 1 && r >= 1) {
            return 1.0F;
        }
        if (mes[0].equals("绿") && mes[1].equals("黄") && g >= 1 && y >= 1) {
            return 1.0F;
        }


        if (mes[0].equals("黄") && mes[1].equals("蓝") && y >= 1 && b >= 1) {
            return 1.0F;
        }
        if (mes[0].equals("黄") && mes[1].equals("黄") && y >= 2) {
            return 1.0F;
        }
        if (mes[0].equals("黄") && mes[1].equals("红") && y >= 1 && r >= 1) {
            return 1.0F;
        }
        if (mes[0].equals("黄") && mes[1].equals("绿") && y >= 1 && g >= 1) {
            return 1.0F;
        }
        return 0.4F;
    }
}


/* Location:              C:\Users\Administrator\Desktop\3.zip!\3\ab0cf7d5.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */