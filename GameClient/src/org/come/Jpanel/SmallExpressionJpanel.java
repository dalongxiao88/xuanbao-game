package org.come.Jpanel;

import java.awt.Graphics;
import org.come.mouslisten.SEPMouslisten;
import java.awt.Font;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import com.tool.tcpimg.RichLabel;
import javax.swing.ImageIcon;
import org.come.mouslisten.ChoseSmallLookMouslisten;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SmallExpressionJpanel extends JPanel
{
    private JLabel[] labSmallLooks;
    private ChoseSmallLookMouslisten[] choseSmallLookMouslistens;
    private static int index;
    public static ImageIcon icon;
    private RichLabel[] bqs;
    
    public SmallExpressionJpanel() throws Exception {
        this.labSmallLooks = new JLabel[40];
        this.choseSmallLookMouslistens = new ChoseSmallLookMouslisten[40];
        this.setPreferredSize(new Dimension(504, 348));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        this.setOpaque(false);
        for (int i = 0; i < 40; ++i) {
            int row = i % 8;
            int col = i / 8;
            (this.labSmallLooks[i] = new JLabel()).setBounds(5 + 60 * row + row * 2, 5 + 60 * col + col * 2, 60, 60);
            this.labSmallLooks[i].setBackground(new Color(0, 0, 0, 0));
            this.choseSmallLookMouslistens[i] = new ChoseSmallLookMouslisten(i);
            this.labSmallLooks[i].addMouseListener(this.choseSmallLookMouslistens[i]);
            this.add(this.labSmallLooks[i]);
        }
        ImageIcon icon = new ImageIcon("img/xy2uiimg/93_png.xy2uiimg.label_chat.png");
        Font font = new Font("宋体", 1, 14);
        for (int j = 0; j < 5; ++j) {
            JLabel yb = new JLabel();
            yb.setBounds(10 + j * 48, 318, 41, 25);
            yb.setIcon(icon);
            yb.setBackground(new Color(0, 0, 0, 0));
            yb.setText(j + 1 + "");
            yb.setForeground(Color.WHITE);
            yb.setHorizontalTextPosition(0);
            yb.setFont(font);
            SEPMouslisten sepMouslisten = new SEPMouslisten(this, j + 1);
            yb.addMouseListener(sepMouslisten);
            this.add(yb);
        }
    }
    
    public RichLabel[] getbqs() {
        if (this.bqs == null) {
            this.bqs = new RichLabel[40];
        }
        for (int i = 0; i < 40; ++i) {
            int xs = (SmallExpressionJpanel.index - 1) * 40 + i;
            if (xs < 168) {
                if (this.bqs[i] != null) {
                    this.bqs[i].setText("#" + xs);
                }
                else {
                    this.bqs[i] = new RichLabel("#" + xs, UIUtils.TEXT_FONT);
                }
            }
            else if (this.bqs[i] != null) {
                this.bqs[i].setText("");
            }
        }
        return this.bqs;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (SmallExpressionJpanel.icon == null) {
            SmallExpressionJpanel.icon = new ImageIcon("inkImg/background/S145.png");
        }
        g.drawImage(SmallExpressionJpanel.icon.getImage(), 0, 0, 482, 348, this);
        super.paintComponent(g);
        if (this.bqs == null) {
            this.getbqs();
        }
        for (int i = 0; i < this.bqs.length; ++i) {
            if (this.bqs[i] != null) {
                int row = i % 8;
                int col = i / 8;
                if (!this.bqs[i].getText().equals("")) {
                    Graphics g2 = g.create(8 + 60 * row, 8 + 60 * col, 100, 100);
                    this.bqs[i].paint(g2);
                    g2.dispose();
                }
            }
        }
    }
    
    public JLabel[] getLabSmallLooks() {
        return this.labSmallLooks;
    }
    
    public void setLabSmallLooks(JLabel[] labSmallLooks) {
        this.labSmallLooks = labSmallLooks;
    }
    
    public ChoseSmallLookMouslisten[] getChoseSmallLookMouslistens() {
        return this.choseSmallLookMouslistens;
    }
    
    public void setChoseSmallLookMouslistens(ChoseSmallLookMouslisten[] choseSmallLookMouslistens) {
        this.choseSmallLookMouslistens = choseSmallLookMouslistens;
    }
    
    public static int getIndex() {
        return SmallExpressionJpanel.index;
    }
    
    public static void setIndex(int index) {
        SmallExpressionJpanel.index = index;
    }
    
    static {
        SmallExpressionJpanel.index = 1;
    }
}
