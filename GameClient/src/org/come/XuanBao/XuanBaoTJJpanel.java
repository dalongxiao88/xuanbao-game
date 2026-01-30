
package org.come.XuanBao;

import com.tool.Document.RichDocument;
import com.tool.btn.FormsOnOffBtn;
import com.tool.image.ImageMixDeal;
import com.tool.role.RoleLingFa;
import com.tool.tcpimg.RichLabel;
import com.tool.tcpimg.UIUtils;
import come.tool.FightingData.FBUtil;
import org.apache.commons.lang.StringUtils;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.GameJpanel;
import org.come.Jpanel.ZhuShouTaskJpanel;
import org.come.bean.OneArenaNotes;
import org.come.bean.Skill;
import org.come.bean.XuanBao;
import org.come.entity.Goodstable;
import org.come.model.AllXuanbao;
import org.come.model.Lingbao;
import org.come.until.*;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.come.until.ScrenceUntil.Screen_y;

public class XuanBaoTJJpanel
        extends JPanel {

    public XuanBaoTJJframe xuanBaoJframe;

    private List<Goodstable> XBList;
    public static List<String> jg = new ArrayList<>();
    public static List<String> fz = new ArrayList<>();
    public static List<String> fy = new ArrayList<>();
    private Goodstable goodstable;
    private XuanBao xuanBao;
    private XuanBao extXuanBao;

    static {
        jg.add("斗魂幡");
        jg.add("");
        jg.add("");
        jg.add("");
        jg.add("");
    }

    private List<XuanBaoTJModelJpanel> xuanBaoTJModelJpanels;
    private JScrollPane scrollPane, xuanBaoInfoScrollPane;
    private XuanBaoTJBtn b1, b2, b3, t1, t2, t3, t4, s1, s2, s3, s4, p1, p2, p3, p4;
    private XuanBaoTJBtn fBtn;
    private List<XuanBaoTJBtn> tyBtns;
    private List<XuanBaoTJBtn> allBtns;
    private List<XuanBaoTJBtn> pzBtns;
    private XBMsgJapnel xbMsgJapnel;
    private JTextField findTxt;

    public XuanBaoTJJpanel(XuanBaoTJJframe xuanBaoTJJframe) {
        this.xuanBaoJframe = xuanBaoTJJframe;
        setBackground(UIUtils.Color_BACK);
        tyBtns = new ArrayList<>();
        allBtns = new ArrayList<>();
        pzBtns = new ArrayList<>();
        setLayout(null);
        setPreferredSize(new Dimension(690, 470));
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 8038);
        offBtn.setBounds(690 - 25, 10, 25, 25);
        add((Component) offBtn);

        this.findTxt = new JTextField(40);
        this.findTxt.setBounds(33, 421, 240, 21);
        this.findTxt.setForeground(Color.white);
        this.findTxt.setBackground(UIUtils.Color_BACK);
        this.findTxt.setBorder(BorderFactory.createEmptyBorder());
        this.findTxt.setCaretColor(Color.WHITE);
        this.findTxt.setFont(UIUtils.TEXT_FONT);
        this.findTxt.setDocument(new RichDocument());
//        this.SendMes.setFocusable(true);
        this.add(this.findTxt);

        p1 = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 31, "所有品质", this, UIUtils.xbTotBtnColor);
        p1.setVisible(false);
        this.add(p1);
        p2 = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 32, "初品", this, UIUtils.xbTotBtnColor);
        p2.setVisible(false);
        this.add(p2);
        p3 = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 33, "中品", this, UIUtils.xbTotBtnColor);
        p3.setVisible(false);
        this.add(p3);
        p4 = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 34, "珍品", this, UIUtils.xbTotBtnColor);
        p4.setVisible(false);
        this.add(p4);
        p1.setBounds(85 + 35 + 85, 54, 76, 20);
        p2.setBounds(85 + 35 + 85, 75, 76, 20);
        p3.setBounds(85 + 35 + 85, 96, 76, 20);
        p4.setBounds(85 + 35 + 85, 117, 76, 20);

        t1 = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 21, "所有类型", this, UIUtils.xbTotBtnColor);
        t1.setBounds(35, 31, 76, 20);
        t1.setVisible(false);
        this.add(t1);
        t2 = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 22, "进攻", this, UIUtils.xbTotBtnColor);
        t2.setBounds(35, 31, 76, 20);
        t2.setVisible(false);
        this.add(t2);
        t3 = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 23, "防御", this, UIUtils.xbTotBtnColor);
        t3.setBounds(35, 31, 76, 20);
        t3.setVisible(false);
        this.add(t3);
        t4 = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 24, "辅助", this, UIUtils.xbTotBtnColor);
        t4.setBounds(35, 31, 76, 20);
        t4.setVisible(false);
        this.add(t4);
        t1.setBounds(85 + 35, 54, 76, 20);
        t2.setBounds(85 + 35, 75, 76, 20);
        t3.setBounds(85 + 35, 96, 76, 20);
        t4.setBounds(85 + 35, 117, 76, 20);

        s1 = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 11, "所有玄宝", this, UIUtils.xbTotBtnColor);
        s1.setVisible(false);
        this.add(s1);
        s2 = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 12, "通用玄宝", this, UIUtils.xbTotBtnColor);
        s2.setVisible(false);
        this.add(s2);
        s3 = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 13, "承脉玄宝", this, UIUtils.xbTotBtnColor);
        s3.setVisible(false);
        this.add(s3);
        s1.setBounds(35, 54, 76, 20);
        s2.setBounds(35, 75, 76, 20);
        s3.setBounds(35, 96, 76, 20);

        b1 = new XuanBaoTJBtn("img/xuan/tj/topUpBtn.png", 1, 1, "通用玄宝", this);
        b1.setBounds(35, 31, 75, 21);
        this.add(b1);
        b2 = new XuanBaoTJBtn("img/xuan/tj/topUpBtn.png", 1, 2, "所有类型", this);
        b2.setBounds(35 + 85, 31, 75, 21);
        this.add(b2);
        b3 = new XuanBaoTJBtn("img/xuan/tj/topUpBtn.png", 1, 3, "所有品质", this);
        b3.setBounds(35 + 85 + 85, 31, 75, 21);
        this.add(b3);

        fBtn = new XuanBaoTJBtn("inkImg/button/xb_find.png", 1, 4, "", this);
        fBtn.setBounds(35 + 85 + 85, 421, 21, 22);
        this.add(fBtn);


        getScrollPane();
        xuanBaoTJModelJpanels = new ArrayList<>();
        findXBList();
        xbMsgJapnel = new XBMsgJapnel();
        getXuanBaoInfoScrollPane();
        xuanBaoTJModelJpanels.get(0).getMouseListeners()[0].mouseReleased(null);
    }

    public JScrollPane getScrollPane() {
        if (scrollPane == null) {
            // 属性滚动条
            scrollPane = new JScrollPane();
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.getVerticalScrollBar().setUI(new SrcollPaneTJlUI());
            scrollPane.getVerticalScrollBar().setUnitIncrement(20);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.setOpaque(false);
            scrollPane.setBounds(32, 55, 270, 360);
            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            JPanel jPanel = new JPanel();
            jPanel.setBackground(null);
            jPanel.setOpaque(false);
            jPanel.setBorder(null);
            jPanel.setLayout(null);
            scrollPane.setViewportView(jPanel);
            this.add(scrollPane);

        }
        return scrollPane;
    }

    public JScrollPane getXuanBaoInfoScrollPane() {
        if (xuanBaoInfoScrollPane == null) {
            // 属性滚动条
            xuanBaoInfoScrollPane = new JScrollPane();
            xuanBaoInfoScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            xuanBaoInfoScrollPane.getVerticalScrollBar().setUI(new SrcollPaneTJlUI());
            xuanBaoInfoScrollPane.getVerticalScrollBar().setUnitIncrement(20);
            xuanBaoInfoScrollPane.getViewport().setOpaque(false);
            xuanBaoInfoScrollPane.setOpaque(false);
            xuanBaoInfoScrollPane.setBounds(320, 197, 332, 195);
            xuanBaoInfoScrollPane.setBorder(BorderFactory.createEmptyBorder());
            xuanBaoInfoScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            JPanel jPanel = new JPanel();
            jPanel.setBackground(null);
            jPanel.setOpaque(false);
            jPanel.setBorder(null);
            jPanel.setLayout(null);
            xuanBaoInfoScrollPane.setViewportView(jPanel);
            this.add(xuanBaoInfoScrollPane);

        }
        return xuanBaoInfoScrollPane;
    }

    public void addData() {
        JPanel view = (JPanel) scrollPane.getViewport().getView();
        view.removeAll();

        int colCount = 3;       // 每行 3 个
        int itemW = 78;         // 每个组件宽度
        int itemH = 93;         // 每个组件高度
        int spaceX = 10;        // 横向间距
        int spaceY = 10;        // 纵向间距

        for (int i = 0; i < xuanBaoTJModelJpanels.size(); i++) {

            int col = i % colCount;      // 当前列
            int row = i / colCount;      // 当前行

            int x = col * (itemW + spaceX);
            int y = row * (itemH + spaceY);

            XuanBaoTJModelJpanel p = xuanBaoTJModelJpanels.get(i);
            p.setBounds(x, y, itemW, itemH);

            view.add(p);
        }

        // ====== 关键：更新 view 的首选高度，让 ScrollPane 能滚动 ======
        int count = xuanBaoTJModelJpanels.size();
        int rowCount = (int) Math.ceil(count / 3.0);   // 计算行数
        int totalHeight = rowCount * (itemH + spaceY);

        view.setPreferredSize(new Dimension(468, totalHeight));

        // ====== 刷新 ======
        view.revalidate();
        view.repaint();
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public void addDataInFo() {
        xbMsgJapnel.XB(xuanBao, extXuanBao);
        JPanel view = (JPanel) xuanBaoInfoScrollPane.getViewport().getView();
        view.removeAll();
        view.add(xbMsgJapnel);
        view.setPreferredSize(new Dimension(xbMsgJapnel.getWidth(), xbMsgJapnel.getHeight()));

        // ====== 刷新 ======
        view.revalidate();
        view.repaint();
        xuanBaoInfoScrollPane.revalidate();
        xuanBaoInfoScrollPane.repaint();
    }

    public void showXuanBaoList() {

    }

    private ImageIcon icon;

    @Override
    protected void paintComponent(Graphics g) {
        fBtn.setBounds(276, 420, 21, 22);
        super.paintComponent(g);
        if (icon == null)
            icon = CutButtonImage.getImage("img/xuan/tj/tjbg.png", -1, -1);
        g.drawImage(icon.getImage(), 0, 0, null);
        if (xuanBao != null) {
            ImageIcon image = CutButtonImage.getImage("img/xuan/" + xuanBao.getMp() + "玄宝/" + xuanBao.getName() + ".png", 140, 140);
            g.drawImage(image.getImage(), getWidth() - 270, 40, null);
            FontMetrics fontMetrics = g.getFontMetrics();
            char[] chars = xuanBao.getName().toCharArray();
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            if (chars.length > 3) {
                for (int i = 0; i < chars.length; i++) {
                    g2d.setColor(UIUtils.COLOR_cbg3);
                    g2d.setFont(UIUtils.hywbt);
                    g2d.drawString(chars[i] + "", 343, 75 + i * 20);
                    g2d.drawString(chars[i] + "", 343, 75 + i * 20);
                }
            } else {
                for (int i = 0; i < chars.length; i++) {
                    g2d.setColor(UIUtils.COLOR_cbg3);
                    g2d.setFont(UIUtils.hywbt);
                    g2d.drawString(chars[i] + "", 343, 75 + i * 30);
                    g2d.drawString(chars[i] + "", 343, 75 + i * 30);
                }
            }
            g.setColor(Color.yellow);
            g.drawString(xuanBao.getPinzhi(), getWidth() - 85, 185);

        }
    }

    public void showXuanBaoInfo(XuanBao xuanBao, XuanBao extXuanBao) {
        this.xuanBao = xuanBao;
        this.extXuanBao = extXuanBao;
        addDataInFo();
    }

    public void changeSelect() {
        for (XuanBaoTJModelJpanel xuanBaoTJModelJpanel : xuanBaoTJModelJpanels) {
            xuanBaoTJModelJpanel.setSelect(false);
        }
    }

    public XuanBaoTJBtn getB1() {
        return b1;
    }

    public void setB1(XuanBaoTJBtn b1) {
        this.b1 = b1;
    }

    public XuanBaoTJBtn getB2() {
        return b2;
    }

    public void setB2(XuanBaoTJBtn b2) {
        this.b2 = b2;
    }

    public XuanBaoTJBtn getB3() {
        return b3;
    }

    public void setB3(XuanBaoTJBtn b3) {
        this.b3 = b3;
    }

    public XuanBaoTJBtn getT1() {
        return t1;
    }

    public void setT1(XuanBaoTJBtn t1) {
        this.t1 = t1;
    }

    public XuanBaoTJBtn getT2() {
        return t2;
    }

    public void setT2(XuanBaoTJBtn t2) {
        this.t2 = t2;
    }

    public XuanBaoTJBtn getT3() {
        return t3;
    }

    public void setT3(XuanBaoTJBtn t3) {
        this.t3 = t3;
    }

    public XuanBaoTJBtn getT4() {
        return t4;
    }

    public void setT4(XuanBaoTJBtn t4) {
        this.t4 = t4;
    }

    public XuanBaoTJBtn getS1() {
        return s1;
    }

    public void setS1(XuanBaoTJBtn s1) {
        this.s1 = s1;
    }

    public XuanBaoTJBtn getS2() {
        return s2;
    }

    public void setS2(XuanBaoTJBtn s2) {
        this.s2 = s2;
    }

    public XuanBaoTJBtn getS3() {
        return s3;
    }

    public void setS3(XuanBaoTJBtn s3) {
        this.s3 = s3;
    }

    public XuanBaoTJBtn getS4() {
        return s4;
    }

    public void setS4(XuanBaoTJBtn s4) {
        this.s4 = s4;
    }

    public XuanBaoTJBtn getP1() {
        return p1;
    }

    public void setP1(XuanBaoTJBtn p1) {
        this.p1 = p1;
    }

    public XuanBaoTJBtn getP2() {
        return p2;
    }

    public void setP2(XuanBaoTJBtn p2) {
        this.p2 = p2;
    }

    public XuanBaoTJBtn getP3() {
        return p3;
    }

    public void setP3(XuanBaoTJBtn p3) {
        this.p3 = p3;
    }

    public XuanBaoTJBtn getP4() {
        return p4;
    }

    public void setP4(XuanBaoTJBtn p4) {
        this.p4 = p4;
    }

    public static String param1 = "所有玄宝";
    public static String param2 = "所有类型";
    public static String param3 = "所有品质";
    public static String param4 = "";

    public void findXBList() {
        for (XuanBaoTJModelJpanel xuanBaoTJModelJpanel : xuanBaoTJModelJpanels) {
            this.remove(xuanBaoTJModelJpanel);
        }

        xuanBaoTJModelJpanels.clear();
        param1 = b1.getText();
        param2 = b2.getText();
        param3 = b3.getText();
        param4 = findTxt.getText();


        Map<Integer, XuanBao> integerXuanBaoMap = UserMessUntil.getAllXuanbao().getaMap();

        integerXuanBaoMap.forEach((k, v) -> {
            if ((param1.startsWith("所有") || param1.startsWith(v.getLeixing()) || v.getLeixing().startsWith("通用"))
                    && (param2.startsWith("所有") || v.getType().startsWith(param2))
                    && (param3.startsWith("所有") || param3.equals(v.getPinzhi()))
                    && (StringUtils.isBlank(param4) || v.getName().contains(param4)))
                xuanBaoTJModelJpanels.add(new XuanBaoTJModelJpanel(this, v));
        });
        addData();
    }
}
