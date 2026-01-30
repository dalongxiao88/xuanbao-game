package org.come.Jpanel;

import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.tool.btn.TextBtn;
import com.tool.btn.CommonBtn;
import javax.swing.JPanel;

public class CumulativeJpanel extends JPanel
{
    private CommonBtn upPage;
    private CommonBtn downPage;
    private TextBtn shouYe;
    private TextBtn moYe;
    private TextBtn receive;
    private int maxPage;
    private int nowpage;
    private JLabel labpage;
    private JLabel jinduTiao;
    private JLabel jinduTiaoVal;
    private JLabel jinduText;
    private FundBuyGoodsJpanel[] detail;
    private ImageIcon iconBack;
    
    public CumulativeJpanel() {
        this.maxPage = 1;
        this.nowpage = 1;
        this.detail = new FundBuyGoodsJpanel[12];
        this.setPreferredSize(new Dimension(656, 445));
        this.setOpaque(false);
        this.setLayout(null);
        (this.labpage = new JLabel(this.nowpage + "/" + this.maxPage, 0)).setForeground(Color.white);
        this.labpage.setBounds(335, 378, 58, 17);
        this.labpage.setFont(UIUtils.TEXT_FONT1);
        this.add(this.labpage);
        (this.upPage = new CommonBtn("inkImg/button/10.png", 1, "", 4)).setBounds(315, 379, 18, 18);
        (this.downPage = new CommonBtn("inkImg/button/9.png", 1, "", 5)).setBounds(397, 379, 18, 18);
        (this.shouYe = new TextBtn("inkImg/button/2.png", 1, "首页", 1)).setBounds(280, 379, 34, 17);
        (this.moYe = new TextBtn("inkImg/button/2.png", 1, "末页", 2)).setBounds(415, 379, 34, 17);
        for (int i = 0; i < this.detail.length; ++i) {
            this.detail[i] = new FundBuyGoodsJpanel();
            int row = i / 3;
            int rank = i % 3;
            this.detail[i].setOpaque(false);
            this.detail[i].setBounds(rank * 185 + 77, 115 + row * 60, 178, 48);
            this.add(this.detail[i]);
        }
        (this.jinduText = new JLabel()).setText("当前进度: ");
        this.jinduText.setFont(UIUtils.TEXT_HY19);
        this.jinduText.setBounds(75, 50, 120, 15);
        this.add(this.jinduText);
        this.jinduTiaoVal = new JLabel();
        ImageIcon jinduVal = new ImageIcon("inkImg/button/27.png");
        this.jinduTiaoVal.setIcon(jinduVal);
        this.jinduTiaoVal.setBounds(177, 50, 148, 15);
        this.jinduTiaoVal.setOpaque(false);
        this.add(this.jinduTiaoVal);
        this.jinduTiao = new JLabel();
        ImageIcon jindu = new ImageIcon("inkImg/background/55.png");
        this.jinduTiao.setIcon(jindu);
        this.jinduTiao.setBounds(175, 50, 148, 15);
        this.add(this.jinduTiao);
        this.add(this.upPage);
        this.add(this.downPage);
        this.add(this.shouYe);
        this.add(this.moYe);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = new ImageIcon("inkImg/background/53.png");
        }
        g.drawImage(this.iconBack.getImage(), 53, 32, 600, 340, this);
    }
    
    public CommonBtn getUpPage() {
        return this.upPage;
    }
    
    public void setUpPage(CommonBtn upPage) {
        this.upPage = upPage;
    }
    
    public CommonBtn getDownPage() {
        return this.downPage;
    }
    
    public void setDownPage(CommonBtn downPage) {
        this.downPage = downPage;
    }
    
    public TextBtn getShouYe() {
        return this.shouYe;
    }
    
    public void setShouYe(TextBtn shouYe) {
        this.shouYe = shouYe;
    }
    
    public TextBtn getMoYe() {
        return this.moYe;
    }
    
    public void setMoYe(TextBtn moYe) {
        this.moYe = moYe;
    }
    
    public TextBtn getReceive() {
        return this.receive;
    }
    
    public void setReceive(TextBtn receive) {
        this.receive = receive;
    }
    
    public int getMaxPage() {
        return this.maxPage;
    }
    
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
    
    public int getNowpage() {
        return this.nowpage;
    }
    
    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }
    
    public JLabel getLabpage() {
        return this.labpage;
    }
    
    public void setLabpage(JLabel labpage) {
        this.labpage = labpage;
    }
    
    public JLabel getJinduTiao() {
        return this.jinduTiao;
    }
    
    public void setJinduTiao(JLabel jinduTiao) {
        this.jinduTiao = jinduTiao;
    }
    
    public JLabel getJinduTiaoVal() {
        return this.jinduTiaoVal;
    }
    
    public void setJinduTiaoVal(JLabel jinduTiaoVal) {
        this.jinduTiaoVal = jinduTiaoVal;
    }
    
    public JLabel getJinduText() {
        return this.jinduText;
    }
    
    public void setJinduText(JLabel jinduText) {
        this.jinduText = jinduText;
    }
    
    public FundBuyGoodsJpanel[] getDetail() {
        return this.detail;
    }
    
    public void setDetail(FundBuyGoodsJpanel[] detail) {
        this.detail = detail;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
}
