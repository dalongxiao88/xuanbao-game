
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
    private XuanBaoTJBtn sourceCommonBtn, typeAllBtn, qualityMenuBtn, typeFilterAllBtn, typeAttackBtn, typeDefenseBtn, typeSupportBtn, sourceFilterAllBtn, sourceGeneralBtn, sourceMeridianBtn, sourceReservedBtn, qualityAllBtn, qualityPrimaryBtn, qualityMiddleBtn, qualityRareBtn;
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

        qualityAllBtn = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 31, "所有品质", this, UIUtils.xbTotBtnColor);
        qualityAllBtn.setVisible(false);
        this.add(qualityAllBtn);
        qualityPrimaryBtn = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 32, "初品", this, UIUtils.xbTotBtnColor);
        qualityPrimaryBtn.setVisible(false);
        this.add(qualityPrimaryBtn);
        qualityMiddleBtn = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 33, "中品", this, UIUtils.xbTotBtnColor);
        qualityMiddleBtn.setVisible(false);
        this.add(qualityMiddleBtn);
        qualityRareBtn = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 34, "珍品", this, UIUtils.xbTotBtnColor);
        qualityRareBtn.setVisible(false);
        this.add(qualityRareBtn);
        qualityAllBtn.setBounds(85 + 35 + 85, 54, 76, 20);
        qualityPrimaryBtn.setBounds(85 + 35 + 85, 75, 76, 20);
        qualityMiddleBtn.setBounds(85 + 35 + 85, 96, 76, 20);
        qualityRareBtn.setBounds(85 + 35 + 85, 117, 76, 20);

        typeFilterAllBtn = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 21, "所有类型", this, UIUtils.xbTotBtnColor);
        typeFilterAllBtn.setBounds(35, 31, 76, 20);
        typeFilterAllBtn.setVisible(false);
        this.add(typeFilterAllBtn);
        typeAttackBtn = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 22, "进攻", this, UIUtils.xbTotBtnColor);
        typeAttackBtn.setBounds(35, 31, 76, 20);
        typeAttackBtn.setVisible(false);
        this.add(typeAttackBtn);
        typeDefenseBtn = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 23, "防御", this, UIUtils.xbTotBtnColor);
        typeDefenseBtn.setBounds(35, 31, 76, 20);
        typeDefenseBtn.setVisible(false);
        this.add(typeDefenseBtn);
        typeSupportBtn = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 24, "辅助", this, UIUtils.xbTotBtnColor);
        typeSupportBtn.setBounds(35, 31, 76, 20);
        typeSupportBtn.setVisible(false);
        this.add(typeSupportBtn);
        typeFilterAllBtn.setBounds(85 + 35, 54, 76, 20);
        typeAttackBtn.setBounds(85 + 35, 75, 76, 20);
        typeDefenseBtn.setBounds(85 + 35, 96, 76, 20);
        typeSupportBtn.setBounds(85 + 35, 117, 76, 20);

        sourceFilterAllBtn = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 11, "所有玄宝", this, UIUtils.xbTotBtnColor);
        sourceFilterAllBtn.setVisible(false);
        this.add(sourceFilterAllBtn);
        sourceGeneralBtn = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 12, "通用玄宝", this, UIUtils.xbTotBtnColor);
        sourceGeneralBtn.setVisible(false);
        this.add(sourceGeneralBtn);
        sourceMeridianBtn = new XuanBaoTJBtn("img/xuan/tj/topBtn.png", 1, 13, "承脉玄宝", this, UIUtils.xbTotBtnColor);
        sourceMeridianBtn.setVisible(false);
        this.add(sourceMeridianBtn);
        sourceFilterAllBtn.setBounds(35, 54, 76, 20);
        sourceGeneralBtn.setBounds(35, 75, 76, 20);
        sourceMeridianBtn.setBounds(35, 96, 76, 20);

        sourceCommonBtn = new XuanBaoTJBtn("img/xuan/tj/topUpBtn.png", 1, 1, "通用玄宝", this);
        sourceCommonBtn.setBounds(35, 31, 75, 21);
        this.add(sourceCommonBtn);
        typeAllBtn = new XuanBaoTJBtn("img/xuan/tj/topUpBtn.png", 1, 2, "所有类型", this);
        typeAllBtn.setBounds(35 + 85, 31, 75, 21);
        this.add(typeAllBtn);
        qualityMenuBtn = new XuanBaoTJBtn("img/xuan/tj/topUpBtn.png", 1, 3, "所有品质", this);
        qualityMenuBtn.setBounds(35 + 85 + 85, 31, 75, 21);
        this.add(qualityMenuBtn);

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
            g.drawString(xuanBao.getQualityName(), getWidth() - 85, 185);

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

    public XuanBaoTJBtn getSourceCommonBtn() {
        return sourceCommonBtn;
    }

    public void setSourceCommonBtn(XuanBaoTJBtn sourceCommonBtn) {
        this.sourceCommonBtn = sourceCommonBtn;
    }

    public XuanBaoTJBtn getTypeAllBtn() {
        return typeAllBtn;
    }

    public void setTypeAllBtn(XuanBaoTJBtn typeAllBtn) {
        this.typeAllBtn = typeAllBtn;
    }

    public XuanBaoTJBtn getQualityMenuBtn() {
        return qualityMenuBtn;
    }

    public void setQualityMenuBtn(XuanBaoTJBtn qualityMenuBtn) {
        this.qualityMenuBtn = qualityMenuBtn;
    }

    public XuanBaoTJBtn getTypeFilterAllBtn() {
        return typeFilterAllBtn;
    }

    public void setTypeFilterAllBtn(XuanBaoTJBtn typeFilterAllBtn) {
        this.typeFilterAllBtn = typeFilterAllBtn;
    }

    public XuanBaoTJBtn getTypeAttackBtn() {
        return typeAttackBtn;
    }

    public void setTypeAttackBtn(XuanBaoTJBtn typeAttackBtn) {
        this.typeAttackBtn = typeAttackBtn;
    }

    public XuanBaoTJBtn getTypeDefenseBtn() {
        return typeDefenseBtn;
    }

    public void setTypeDefenseBtn(XuanBaoTJBtn typeDefenseBtn) {
        this.typeDefenseBtn = typeDefenseBtn;
    }

    public XuanBaoTJBtn getTypeSupportBtn() {
        return typeSupportBtn;
    }

    public void setTypeSupportBtn(XuanBaoTJBtn typeSupportBtn) {
        this.typeSupportBtn = typeSupportBtn;
    }

    public XuanBaoTJBtn getSourceFilterAllBtn() {
        return sourceFilterAllBtn;
    }

    public void setSourceFilterAllBtn(XuanBaoTJBtn sourceFilterAllBtn) {
        this.sourceFilterAllBtn = sourceFilterAllBtn;
    }

    public XuanBaoTJBtn getSourceGeneralBtn() {
        return sourceGeneralBtn;
    }

    public void setSourceGeneralBtn(XuanBaoTJBtn sourceGeneralBtn) {
        this.sourceGeneralBtn = sourceGeneralBtn;
    }

    public XuanBaoTJBtn getSourceMeridianBtn() {
        return sourceMeridianBtn;
    }

    public void setSourceMeridianBtn(XuanBaoTJBtn sourceMeridianBtn) {
        this.sourceMeridianBtn = sourceMeridianBtn;
    }

    public XuanBaoTJBtn getSourceReservedBtn() {
        return sourceReservedBtn;
    }

    public void setSourceReservedBtn(XuanBaoTJBtn sourceReservedBtn) {
        this.sourceReservedBtn = sourceReservedBtn;
    }

    public XuanBaoTJBtn getQualityAllBtn() {
        return qualityAllBtn;
    }

    public void setQualityAllBtn(XuanBaoTJBtn qualityAllBtn) {
        this.qualityAllBtn = qualityAllBtn;
    }

    public XuanBaoTJBtn getQualityPrimaryBtn() {
        return qualityPrimaryBtn;
    }

    public void setQualityPrimaryBtn(XuanBaoTJBtn qualityPrimaryBtn) {
        this.qualityPrimaryBtn = qualityPrimaryBtn;
    }

    public XuanBaoTJBtn getQualityMiddleBtn() {
        return qualityMiddleBtn;
    }

    public void setQualityMiddleBtn(XuanBaoTJBtn qualityMiddleBtn) {
        this.qualityMiddleBtn = qualityMiddleBtn;
    }

    public XuanBaoTJBtn getQualityRareBtn() {
        return qualityRareBtn;
    }

    public void setQualityRareBtn(XuanBaoTJBtn qualityRareBtn) {
        this.qualityRareBtn = qualityRareBtn;
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
        param1 = sourceCommonBtn.getText();
        param2 = typeAllBtn.getText();
        param3 = qualityMenuBtn.getText();
        param4 = findTxt.getText();


        Map<Integer, XuanBao> integerXuanBaoMap = UserMessUntil.getAllXuanbao().getaMap();

        integerXuanBaoMap.forEach((k, v) -> {
            if ((param1.startsWith("所有") || param1.startsWith(v.getTypeName()) || v.getTypeName().startsWith("通用"))
                    && (param2.startsWith("所有") || v.getType().startsWith(param2))
                    && (param3.startsWith("所有") || param3.equals(v.getQualityName()))
                    && (StringUtils.isBlank(param4) || v.getName().contains(param4)))
                xuanBaoTJModelJpanels.add(new XuanBaoTJModelJpanel(this, v));
        });
        addData();
    }
}





