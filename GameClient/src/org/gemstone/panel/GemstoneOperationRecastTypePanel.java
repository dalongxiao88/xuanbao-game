package org.gemstone.panel;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.come.entity.Goodstable;
import org.come.until.CutButtonImage;

import com.updateNew.MyIsif;

public class GemstoneOperationRecastTypePanel extends JPanel
{
    private JLabel[] labelRecast;
    private Goodstable[] Goodstablelabel;
    private boolean shoptext;
    private int goodPosition;
    public static ImageIcon iconx2;
    private ImageIcon iconBack;
    
    public GemstoneOperationRecastTypePanel() {
        this.shoptext = false;
        class MListener extends MouseAdapter {
            private int num;

            public MListener(int num) {
                this.num = num;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                GemstoneOperationMainCardPanel gemstoneOperationMainCardPanel = GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel().getGemstoneOperationMainCardPanel();
                gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getReelectGoods().setType(GemstoneOperationRecastTypePanel.this.Goodstablelabel[this.num].getType());
                gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getRecastGemstone().setIcon(CutButtonImage.getGemstoneIcon(GemstoneOperationRecastTypePanel.this.Goodstablelabel[this.num].getSkin() + "", 48, 48));
                gemstoneOperationMainCardPanel.getGemstoneOperationAppraisalAndRecastPanel().getGemstoneOperationRecastTypePanel().setVisible(false);
            }
        }
        if (MyIsif.getStyle().equals("水墨")) {
            this.setBounds(42, 134, 228, 124);
            this.setOpaque(false);
            this.setLayout(null);
            this.labelRecast = new JLabel[8];
            this.Goodstablelabel = new Goodstable[8];
            for (int i = 0; i < this.labelRecast.length; ++i) {
                (this.labelRecast[i] = new JLabel()).setBounds(11 + i % 4 * 51, 11 + i / 4 * 51, 48, 48);
                this.labelRecast[i].addMouseListener(new MListener(i));
                this.labelRecast[i].setOpaque(false);
                this.add(this.labelRecast[i]);
            }
            for (int i = 0; i < this.Goodstablelabel.length; ++i) {
                (this.Goodstablelabel[i] = new Goodstable()).setType(Long.valueOf(746L + (long)(i * 3)));
            }
        }
        else {
            this.setBounds(42, 134, 225, 120);
            this.setOpaque(false);
            this.setLayout(null);
            this.labelRecast = new JLabel[8];
            this.Goodstablelabel = new Goodstable[8];
            for (int i = 0; i < this.labelRecast.length; ++i) {
                (this.labelRecast[i] = new JLabel()).setBounds(11 + i % 4 * 51, 11 + i / 4 * 51, 48, 48);
                this.labelRecast[i].addMouseListener(new MListener(i));
                this.labelRecast[i].setOpaque(false);
                this.add(this.labelRecast[i]);
            }
            for (int i = 0; i < this.Goodstablelabel.length; ++i) {
                (this.Goodstablelabel[i] = new Goodstable()).setType(Long.valueOf(746L + (long)(i * 3)));
            }
        }
    }
    
    public void PaintingText(int goodPosition) {
        this.goodPosition = goodPosition;
        this.shoptext = true;
    }
    
    public void ClearText() {
        this.shoptext = false;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("inkImg/background/S44.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 228, 124, this);
            if (this.shoptext) {
                int shop_x = this.goodPosition % 4;
                int shop_y = this.goodPosition / 4;
                g.drawImage(GemstoneOperationRecastTypePanel.iconx2.getImage(), 11 + shop_x * 51, 11 + shop_y * 51, this);
            }
        }
        else {
            if (this.iconBack == null) {
                this.iconBack = new ImageIcon("img/gemstone/重铸类型w225,h120.png");
            }
            g.drawImage(this.iconBack.getImage(), 0, 0, 225, 120, this);
            if (this.shoptext) {
                int shop_x = this.goodPosition % 4;
                int shop_y = this.goodPosition / 4;
                g.drawImage(GemstoneOperationRecastTypePanel.iconx2.getImage(), 11 + shop_x * 51, 11 + shop_y * 51, this);
            }
        }
    }
    
    public void repaintGemstoneIcon() {
        int num = 0;
        GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel();
        int parseInt = Integer.parseInt(GemstoneOperationMainPanel.cuttingString(GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel().getGoodsLabes()[0], 1));
        if (parseInt <= 3) {
            num = 0;
        }
        else if (parseInt <= 6) {
            num = 1;
        }
        else {
            num = 2;
        }
        GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel().getGemstoneOperationMainCardPanel().getGemstoneOperationAppraisalAndRecastPanel().getRecastGemstone().setIcon(CutButtonImage.getGemstoneIcon(GemstoneOperationMainFrame.getGemstoneOperationMainFrame().getGemstoneOperationMainPanel().getGoodsLabes()[0].getSkin() + "", 48, 48));
        for (int i = 0; i < this.labelRecast.length; ++i) {
            this.labelRecast[i].setIcon(CutButtonImage.getGemstoneIcon(746 + num + i * 3 + "", 48, 48));
            this.Goodstablelabel[i].setSkin(746 + num + i * 3 + "");
            this.Goodstablelabel[i].setType(Long.valueOf(746L + (long)(i * 3)));
        }
    }
    
    public JLabel[] getLabelRecast() {
        return this.labelRecast;
    }
    
    public void setLabelRecast(JLabel[] labelRecast) {
        this.labelRecast = labelRecast;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
    
    static {
        GemstoneOperationRecastTypePanel.iconx2 = new ImageIcon("jiuUI/jiuuijiemian/border_quack.png");
    }
}
