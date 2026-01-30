package org.come.Jpanel;

import org.apache.commons.lang.StringUtils;
import org.come.entity.SellLiangHaoBase;
import javax.swing.BorderFactory;
import java.awt.AlphaComposite;
import java.awt.geom.RoundRectangle2D;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Font;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.awt.Color;
import org.come.bean.SearchSellLiangHaoResultBean;
import com.tool.btn.LiangHaoItemBtn;
import com.tool.btn.GetLiangHaoBtn;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GetLiangHaoOneJpanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    ImageIcon bg1;
    ImageIcon bg2;
    ImageIcon itemBg;
    ImageIcon fengexian;
    private JLabel searchTitle;
    private JLabel text1;
    private JLabel text2;
    private JTextField searchText;
    private GetLiangHaoBtn btnSearch;
    private LiangHaoItemBtn[] liangHaoListBtn;
    private SearchSellLiangHaoResultBean slb;
    private Color color;
    
    public GetLiangHaoOneJpanel() {
        this.color = new Color(56, 53, 46, 238);
        System.out.println(MyIsif.getStyle());
        this.setPreferredSize(new Dimension(697, 538));
        this.setLayout(null);
        this.setOpaque(false);
        this.bg1 = new ImageIcon("inkImg/background1/LiangHaoOneBg1.png");
        this.bg2 = new ImageIcon("inkImg/background1/LiangHaoOneBg3.png");
        this.itemBg = new ImageIcon("inkImg/button1/198.png");
        this.fengexian = new ImageIcon("inkImg/button1/fengexian.png");
        (this.searchTitle = new JLabel()).setText("搜索");
        this.searchTitle.setBounds(170, 75, 100, 26);
        this.searchTitle.setFont(UIUtils.TXT_hyzjt18);
        this.searchTitle.setVerticalTextPosition(0);
        this.searchTitle.setHorizontalTextPosition(0);
        this.add(this.searchTitle);
        (this.text1 = new JLabel()).setText("【规则说明】");
        this.text1.setBounds(170, 100, 100, 26);
        this.text1.setFont(UIUtils.TEXT_FONT14);
        this.text1.setVerticalTextPosition(0);
        this.text1.setHorizontalTextPosition(0);
        this.add(this.text1);
        (this.text2 = new JLabel()).setText("可以自选非5A的6位数靓号");
        this.text2.setBounds(170, 120, 300, 26);
        this.text2.setFont(UIUtils.TEXT_FONT14);
        this.text2.setVerticalTextPosition(0);
        this.text2.setHorizontalTextPosition(0);
        this.add(this.text2);
        (this.btnSearch = new GetLiangHaoBtn("inkImg/button1/188.png", 1, "", 10, this)).setBounds(316, 79, 20, 20);
        this.add(this.btnSearch);
        this.liangHaoListBtn = new LiangHaoItemBtn[16];
        for (int i = 1; i <= 16; ++i) {
            this.liangHaoListBtn[i - 1] = new LiangHaoItemBtn("inkImg/button1/189.png", 1, 1, null, null);
            int line = (i - 1) / 4;
            int index = (i - 1) % 4;
            this.liangHaoListBtn[i - 1].setBounds(170 + 158 * index, 239 + 68 * line, 27, 27);
            this.add(this.liangHaoListBtn[i - 1]);
        }
        Font font = new Font("微软雅黑", 0, 14);
        (this.searchText = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D)g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0.0, 0.0, (double)this.getWidth(), (double)this.getHeight(), 5.0, 5.0);
                g2d.setComposite(AlphaComposite.getInstance(3, 0.8f));
                g2d.setColor(GetLiangHaoOneJpanel.this.color);
                g2d.fill(roundedRectangle);
                g2d.setColor(Color.white);
                super.paintComponent(g);
            }
        }).setForeground(Color.white);
        this.searchText.setBackground(UIUtils.Color_BACK);
        this.searchText.setBorder(BorderFactory.createEmptyBorder());
        this.searchText.setCaretColor(Color.WHITE);
        this.searchText.setFont(font);
        this.searchText.setBounds(215, 80, 120, 18);
        this.add(this.searchText);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.bg2.getImage(), 45, 65, 630, 135, null);
        g.drawImage(this.bg1.getImage(), 68, 30, 81, 207, null);
        g.drawImage(this.fengexian.getImage(), 45, 210, 630, 2, null);
        if (this.slb != null && this.slb.getSellLiangHaos() != null && this.slb.getSellLiangHaos().size() > 0) {
            for (int i = 1; i <= this.slb.getSellLiangHaos().size(); ++i) {
                int line = (i - 1) / 4;
                int index = (i - 1) % 4;
                g.drawImage(this.itemBg.getImage(), 45 + 158 * index, 220 + 68 * line, 156, 66, null);
                g.setColor(new Color(79, 79, 79));
                g.setFont(new Font("汉仪中楷简", 1, 22));
                g.drawString(((SellLiangHaoBase)this.slb.getSellLiangHaos().get(i - 1)).getLianghao(), 60 + 158 * index, 245 + 68 * line);
                g.setFont(new Font("汉仪中楷简", 0, 14));
                g.drawString("仙玉：", 60 + 158 * index, 270 + 68 * line);
                g.setColor(new Color(255, 0, 0));
                g.drawString(String.valueOf(((SellLiangHaoBase)this.slb.getSellLiangHaos().get(i - 1)).getPrice()), 100 + 158 * index, 270 + 68 * line);
                this.liangHaoListBtn[i - 1].setLianghaoItem((SellLiangHaoBase)this.slb.getSellLiangHaos().get(i - 1));
            }
        }
        if (this.slb != null && this.slb.getSellLiangHaos() != null) {
            for (int i = 0; i < this.slb.getSellLiangHaos().size(); ++i) {
                this.liangHaoListBtn[i].setVisible(true);
            }
            for (int i = this.slb.getSellLiangHaos().size(); i < this.liangHaoListBtn.length; ++i) {
                this.liangHaoListBtn[i].setVisible(false);
            }
        }
        for (int j = 0; j < this.liangHaoListBtn.length; ++j) {
            if (this.liangHaoListBtn[j].getLianghaoItem() == null || StringUtils.isEmpty(this.liangHaoListBtn[j].getLianghaoItem().getLianghao())) {
                this.liangHaoListBtn[j].setVisible(false);
            }
        }
    }
    
    public void changeFrom(int caozuo) {
    }
    
    public JTextField getSearchText() {
        return this.searchText;
    }
    
    public void setSearchText(JTextField searchText) {
        this.searchText = searchText;
    }
    
    public SearchSellLiangHaoResultBean getSlb() {
        return this.slb;
    }
    
    public void setSlb(SearchSellLiangHaoResultBean slb) {
        for (int j = 0; j < this.liangHaoListBtn.length; ++j) {
            this.liangHaoListBtn[j].setLianghaoItem(null);
        }
        this.slb = slb;
    }
    
    public void resetTableData(SearchSellLiangHaoResultBean sa) {
    }
}
