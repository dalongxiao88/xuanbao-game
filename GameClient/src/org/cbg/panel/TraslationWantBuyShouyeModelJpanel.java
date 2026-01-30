package org.cbg.panel;

import org.cbg.entity.Salegoods;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import org.come.until.CutButtonImage;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TraslationWantBuyShouyeModelJpanel extends JPanel
{
    private JLabel[] tupianList;
    private JLabel[] mingziList;
    private JLabel[] jiageList;
    
    public TraslationWantBuyShouyeModelJpanel(int geshu) {
        this.setLayout(null);
        this.setBounds(0, 280, 590, 87);
        this.setOpaque(false);
        this.tupianList = new JLabel[7];
        this.mingziList = new JLabel[7];
        this.jiageList = new JLabel[7];
        for (int i = 0; i < this.tupianList.length; ++i) {
            (this.tupianList[i] = new JLabel()).setBounds(30 + 75 * i, 0, 52, 50);
            this.tupianList[i].setOpaque(false);
            (this.mingziList[i] = new JLabel()).setOpaque(false);
            this.mingziList[i].setForeground(Color.black);
            this.mingziList[i].setFont(UIUtils.TEXT_FONT2);
            this.mingziList[i].setBounds(30 + 75 * i, 55, 75, 15);
            (this.jiageList[i] = new JLabel()).setOpaque(false);
            this.jiageList[i].setForeground(Color.black);
            this.jiageList[i].setFont(UIUtils.TEXT_FONT2);
            this.jiageList[i].setBounds(30 + 75 * i, 72, 75, 15);
            this.add(this.tupianList[i]);
            this.add(this.mingziList[i]);
            this.add(this.jiageList[i]);
        }
    }
    
    public void setZuixinshangjia(JPanel jPanel, int geshu) {
        this.removeAll();
        this.repaint();
        for (int i = 0; i < geshu; ++i) {
            JLabel tupian = new JLabel();
            tupian.setBackground(Color.red);
            tupian.setOpaque(false);
            tupian.setIcon(CutButtonImage.getImage("img/xy2uiimg/202.png", 52, 50));
            tupian.setBounds(30 + 75 * i, 0, 52, 50);
            this.add(tupian);
            JLabel mingzi = new JLabel("名字" + i);
            mingzi.setOpaque(false);
            mingzi.setForeground(Color.black);
            mingzi.setFont(new Font("宋体", 0, 14));
            mingzi.setBounds(30 + 75 * i, 55, 52, 15);
            this.add(mingzi);
            JLabel jiage = new JLabel("价格" + i);
            jiage.setOpaque(false);
            jiage.setForeground(Color.black);
            jiage.setFont(new Font("宋体", 0, 14));
            jiage.setBounds(30 + 75 * i, 72, 52, 15);
            this.add(jiage);
            tupian.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                }
            });
        }
    }
    
    public void setZuixinshangjia(List<Salegoods> saleGoodsList) {
        for (int i = 0; i < 7; ++i) {
            if (saleGoodsList.size() > i) {
                if ((int)((Salegoods)saleGoodsList.get(i)).getSaletype() == 4) {
                    this.tupianList[i].setIcon(CutButtonImage.getImage("img/head/p" + ((Salegoods)saleGoodsList.get(i)).getSaleskin() + ".png", 52, 50));
                }
                else if ((int)((Salegoods)saleGoodsList.get(i)).getSaletype() == 6) {
                    this.tupianList[i].setIcon(CutButtonImage.getImage("img/lingbao/" + ((Salegoods)saleGoodsList.get(i)).getSaleskin() + ".png", 52, 50));
                }
                else {
                    this.tupianList[i].setIcon(CutButtonImage.getImage("img/item/" + ((Salegoods)saleGoodsList.get(i)).getSaleskin() + ".png", 52, 50));
                }
                this.mingziList[i].setText(((Salegoods)saleGoodsList.get(i)).getSalename());
                this.jiageList[i].setText(((Salegoods)saleGoodsList.get(i)).getSaleprice() + "");
            }
            else {
                this.tupianList[i].setIcon(null);
                this.jiageList[i].setText("");
                this.mingziList[i].setText("");
            }
        }
    }
}
