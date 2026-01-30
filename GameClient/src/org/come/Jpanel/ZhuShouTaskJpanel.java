package org.come.Jpanel;

import com.tool.ModerateTask.TaskRoleData;
import com.tool.tcpimg.UIUtils;
import java.awt.Graphics2D;
import org.come.until.CutButtonImage;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import org.come.model.ActiveBase;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ZhuShouTaskJpanel extends JPanel
{
    private boolean slelct;
    private JLabel labCheckBox;
    private JTextField finishSum;
    private ActiveBase zhuShouBean;
    private int count;
    private ImageIcon iconBack;
    private ImageIcon iconCheckBox;
    private ImageIcon iconSlelct;
    
    public ZhuShouTaskJpanel(ActiveBase zhuShouBean) {
        this.setPreferredSize(new Dimension(862, 411));
        this.setOpaque(false);
        this.setLayout(null);
        this.zhuShouBean = zhuShouBean;
        this.getLabCheckBox();
        this.getFinishSum();
    }
    
    public JLabel getLabCheckBox() {
        if (this.labCheckBox == null) {
            (this.labCheckBox = new JLabel()).setBounds(10, 15, 15, 15);
            this.labCheckBox.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ZhuShouTaskJpanel.this.slelct = !ZhuShouTaskJpanel.this.slelct;
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            this.add(this.labCheckBox);
        }
        return this.labCheckBox;
    }
    
    public JTextField getFinishSum() {
        if (this.finishSum == null) {
            (this.finishSum = new JTextField()).setBounds(385, 15, 60, 18);
            this.finishSum.setOpaque(false);
            this.finishSum.setBorder(BorderFactory.createEmptyBorder());
            this.finishSum.setForeground(Color.white);
            this.finishSum.setCaretColor(Color.white);
            this.finishSum.setFont(new Font("宋体", 0, 15));
            this.finishSum.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int keyChar = e.getKeyChar();
                    if (keyChar < 48 || keyChar > 57) {
                        e.consume();
                        return;
                    }
                }
                
                @Override
                public void keyReleased(KeyEvent e) {
                    if (ZhuShouTaskJpanel.this.finishSum.getText() != null && ZhuShouTaskJpanel.this.finishSum.getText().length() > 0) {
                        ZhuShouTaskJpanel.this.finishSum.setText(ZhuShouTaskJpanel.this.getNum() + "");
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            this.add(this.finishSum);
        }
        this.finishSum.setText(this.getNum() + "");
        return this.finishSum;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = CutButtonImage.getImage("inkImg/hongmu1/S342.png", -1, -1);
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 468, 53, this);
        if (this.iconCheckBox == null) {
            this.iconCheckBox = CutButtonImage.getImage("inkImg/hongmu1/14.png", -1, -1);
        }
        g.drawImage(this.iconCheckBox.getImage(), 10, 15, 15, 15, this);
        if (this.slelct) {
            if (this.iconSlelct == null) {
                this.iconSlelct = CutButtonImage.getImage("inkImg/hongmu1/13.png", -1, -1);
            }
            g.drawImage(this.iconSlelct.getImage(), 10, 15, 15, 15, this);
        }
        Graphics2D g2 = (Graphics2D)g;
        g2.setFont(UIUtils.TEXT_FONT78);
        g2.setColor(UIUtils.COLOR_NAME5);
        g2.drawString(this.zhuShouBean.getaName(), 40, 30);
        g2.drawString("(已完成任务", 40 + this.zhuShouBean.getaName().length() * 14, 30);
        g2.setColor(UIUtils.COLOR_NAME3);
        this.count = TaskRoleData.SumReceive(this.zhuShouBean.getSid(), 2);
        String count = String.valueOf(this.count);
        g2.drawString(count, 120 + this.zhuShouBean.getaName().length() * 14, 30);
        g2.setColor(UIUtils.COLOR_NAME5);
        g2.drawString("次)", 120 + this.zhuShouBean.getaName().length() * 14 + count.length() * 9, 30);
    }
    
    public boolean isSlelct() {
        return this.slelct;
    }
    
    public ActiveBase getZhuShouBean() {
        return this.zhuShouBean;
    }
    
    public int getNum() {
        int maxNum = this.zhuShouBean.getNum();
        try {
            int num = Integer.parseInt(this.finishSum.getText());
            if (num < maxNum) {
                maxNum = num;
            }
        }
        catch (Exception ex) {}
        return maxNum;
    }
}
