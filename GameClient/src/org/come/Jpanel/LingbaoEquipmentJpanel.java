package org.come.Jpanel;

import com.tool.role.RoleLingFa;
import java.awt.Graphics;
import java.awt.Color;
import org.come.mouslisten.LingFaFanYeMouslisten;
import com.tool.tcpimg.UIUtils;
import java.awt.LayoutManager;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import com.tool.btn.LingBaoZhiYuanBtn;
import javax.swing.ImageIcon;
import com.tool.btn.LingbaoPagingBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LingbaoEquipmentJpanel extends JPanel
{
    private JLabel labLingbao;
    private JLabel labMagicOne;
    private JLabel labMagicTwo;
    private LingbaoPagingBtn btnLast1;
    private LingbaoPagingBtn btnNext1;
    private LingbaoPagingBtn btnLast2;
    private LingbaoPagingBtn btnNext2;
    private JLabel[] LingbaoListLabel;
    private JLabel[] MagicListLabel;
    private int Flag;
    private int count;
    private ImageIcon icon;
    private LingBaoZhiYuanBtn lingBaoZhiYuanBtn;
    
    public LingbaoEquipmentJpanel() {
        this.LingbaoListLabel = new JLabel[8];
        this.MagicListLabel = new JLabel[8];
        this.Flag = 0;
        this.count = 1;
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(538, 469));
            this.setLayout((LayoutManager)null);
            this.setBackground(UIUtils.Color_BACK);
            (this.labLingbao = new JLabel()).setBounds(265, 115, 40, 40);
            this.labLingbao.addMouseListener(new LingFaFanYeMouslisten(true, -1));
            this.add(this.labLingbao);
            (this.labMagicOne = new JLabel()).setBounds(215, 175, 37, 36);
            this.labMagicOne.addMouseListener(new LingFaFanYeMouslisten(false, -1));
            this.add(this.labMagicOne);
            (this.labMagicTwo = new JLabel()).setBounds(323, 175, 37, 36);
            this.labMagicTwo.addMouseListener(new LingFaFanYeMouslisten(false, -2));
            this.add(this.labMagicTwo);
            (this.lingBaoZhiYuanBtn = new LingBaoZhiYuanBtn("inkImg/button/49.png", 1, "支援列表")).setBounds(200, 50, 68, 17);
            this.add(this.lingBaoZhiYuanBtn);
            this.lingBaoZhiYuanBtn.setBounds(70, 435, 68, 17);
        }
        else {
            this.setPreferredSize(new Dimension(512, 496));
            this.setLayout((LayoutManager)null);
            this.setBackground(Color.BLACK);
            (this.labLingbao = new JLabel()).setBounds(240, 130, 40, 40);
            this.labLingbao.addMouseListener(new LingFaFanYeMouslisten(true, -1));
            this.add(this.labLingbao);
            (this.labMagicOne = new JLabel()).setBounds(193, 187, 37, 36);
            this.labMagicOne.addMouseListener(new LingFaFanYeMouslisten(false, -1));
            this.add(this.labMagicOne);
            (this.labMagicTwo = new JLabel()).setBounds(298, 187, 37, 36);
            this.labMagicTwo.addMouseListener(new LingFaFanYeMouslisten(false, -2));
            this.add(this.labMagicTwo);
            (this.lingBaoZhiYuanBtn = new LingBaoZhiYuanBtn("inkImg/hongmu/B34h.png", 1, "支援列表")).setBounds(45, 450, 100, 17);
            this.add(this.lingBaoZhiYuanBtn);
        }
        if (MyIsif.getStyle().equals("水墨")) {
            (this.btnLast1 = new LingbaoPagingBtn("inkImg/button/10.png", 1, 0, (String)null)).setBounds(215, 435, 19, 20);
            this.add(this.btnLast1);
            (this.btnNext1 = new LingbaoPagingBtn("inkImg/button/9.png", 1, 1, (String)null)).setBounds(235, 435, 19, 20);
            this.add(this.btnNext1);
            (this.btnLast2 = new LingbaoPagingBtn("inkImg/button/10.png", 1, 2, (String)null)).setBounds(447, 435, 19, 20);
            this.add(this.btnLast2);
            (this.btnNext2 = new LingbaoPagingBtn("inkImg/button/9.png", 1, 3, (String)null)).setBounds(467, 435, 19, 20);
            this.add(this.btnNext2);
            for (int i = 0; i < 8; ++i) {
                this.LingbaoListLabel[i] = new JLabel();
                this.MagicListLabel[i] = new JLabel();
                if (this.Flag < 4 && this.count == 1) {
                    this.LingbaoListLabel[i].setBounds(75 + this.Flag * 51, 333, 48, 48);
                    this.LingbaoListLabel[i].addMouseListener(new LingFaFanYeMouslisten(true, i));
                    this.MagicListLabel[i].setBounds(306 + this.Flag * 51, 333, 48, 48);
                    this.MagicListLabel[i].addMouseListener(new LingFaFanYeMouslisten(false, i));
                    ++this.Flag;
                    this.add(this.LingbaoListLabel[i]);
                    this.add(this.MagicListLabel[i]);
                }
                if (this.Flag < 4 && this.count == 2) {
                    this.LingbaoListLabel[i].setBounds(75 + this.Flag * 51, 381, 48, 48);
                    this.LingbaoListLabel[i].addMouseListener(new LingFaFanYeMouslisten(true, i));
                    this.MagicListLabel[i].setBounds(306 + this.Flag * 51, 381, 48, 48);
                    this.MagicListLabel[i].addMouseListener(new LingFaFanYeMouslisten(false, i));
                    ++this.Flag;
                    this.add(this.LingbaoListLabel[i]);
                    this.add(this.MagicListLabel[i]);
                }
                else if (this.Flag == 4) {
                    this.Flag = 0;
                    ++this.count;
                }
            }
        }
        else {
            (this.btnLast1 = new LingbaoPagingBtn("img/xy2uiimg/29_png.button.btn_8.png", 1, 0, (String)null)).setBounds(215, 450, 19, 20);
            this.add(this.btnLast1);
            (this.btnNext1 = new LingbaoPagingBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 1, (String)null)).setBounds(235, 450, 19, 20);
            this.add(this.btnNext1);
            (this.btnLast2 = new LingbaoPagingBtn("img/xy2uiimg/29_png.button.btn_8.png", 1, 2, (String)null)).setBounds(447, 450, 19, 20);
            this.add(this.btnLast2);
            (this.btnNext2 = new LingbaoPagingBtn("img/xy2uiimg/36_png.button.btn_7.png", 1, 3, (String)null)).setBounds(467, 450, 19, 20);
            this.add(this.btnNext2);
            for (int i = 0; i < 8; ++i) {
                this.LingbaoListLabel[i] = new JLabel();
                this.MagicListLabel[i] = new JLabel();
                if (this.Flag < 4 && this.count == 1) {
                    this.LingbaoListLabel[i].setBounds(48 + this.Flag * 51, 346, 48, 48);
                    this.LingbaoListLabel[i].addMouseListener(new LingFaFanYeMouslisten(true, i));
                    this.MagicListLabel[i].setBounds(282 + this.Flag * 51, 346, 48, 48);
                    this.MagicListLabel[i].addMouseListener(new LingFaFanYeMouslisten(false, i));
                    ++this.Flag;
                    this.add(this.LingbaoListLabel[i]);
                    this.add(this.MagicListLabel[i]);
                }
                if (this.Flag < 4 && this.count == 2) {
                    this.LingbaoListLabel[i].setBounds(48 + this.Flag * 51, 397, 48, 48);
                    this.LingbaoListLabel[i].addMouseListener(new LingFaFanYeMouslisten(true, i));
                    this.MagicListLabel[i].setBounds(282 + this.Flag * 51, 397, 48, 48);
                    this.MagicListLabel[i].addMouseListener(new LingFaFanYeMouslisten(false, i));
                    ++this.Flag;
                    this.add(this.LingbaoListLabel[i]);
                    this.add(this.MagicListLabel[i]);
                }
                else if (this.Flag == 4) {
                    this.Flag = 0;
                    ++this.count;
                }
            }
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/background1/B237.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 538, 469, this);
            RoleLingFa.getRoleLingFa().drawL(g, 75, 333);
            RoleLingFa.getRoleLingFa().drawF(g, 306, 333);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("img/xy2uiimg/灵宝穿戴.png");
            }
            g.drawImage(this.icon.getImage(), 0, 0, 512, 496, this);
            RoleLingFa.getRoleLingFa().drawL(g, 48, 346);
            RoleLingFa.getRoleLingFa().drawF(g, 282, 346);
        }
    }
    
    public JLabel getLabLingbao() {
        return this.labLingbao;
    }
    
    public void setLabLingbao(JLabel labLingbao) {
        this.labLingbao = labLingbao;
    }
    
    public JLabel getLabMagicOne() {
        return this.labMagicOne;
    }
    
    public void setLabMagicOne(JLabel labMagicOne) {
        this.labMagicOne = labMagicOne;
    }
    
    public JLabel getLabMagicTwo() {
        return this.labMagicTwo;
    }
    
    public void setLabMagicTwo(JLabel labMagicTwo) {
        this.labMagicTwo = labMagicTwo;
    }
    
    public JLabel[] getLingbaoListLabel() {
        return this.LingbaoListLabel;
    }
    
    public void setLingbaoListLabel(JLabel[] lingbaoListLabel) {
        this.LingbaoListLabel = lingbaoListLabel;
    }
    
    public JLabel[] getMagicListLabel() {
        return this.MagicListLabel;
    }
    
    public void setMagicListLabel(JLabel[] magicListLabel) {
        this.MagicListLabel = magicListLabel;
    }
}
