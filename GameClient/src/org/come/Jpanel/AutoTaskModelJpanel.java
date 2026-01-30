package org.come.Jpanel;

import javax.swing.BorderFactory;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import java.awt.Font;
import org.come.until.CutButtonImage;
import com.tool.ModerateTask.TaskRoleData;
import java.awt.Graphics;
import java.util.List;
import org.come.bean.AutoActiveBaseBean;
import org.come.until.UserMessUntil;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import org.come.bean.AutoActiveBase;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class AutoTaskModelJpanel extends JPanel
{
    private JTextField numField;
    private AutoActiveBase activeBase;
    private JCheckBox checkBox;
    private ImageIcon icon;
    
    public AutoTaskModelJpanel() {
        this.setPreferredSize(new Dimension(465, 61));
        this.setOpaque(false);
        this.setLayout(null);
        this.getNumField();
        this.getJCheckBox();
    }
    
    public void showActiveBase(AutoActiveBase activeBase, int sumReceive) {
        if (activeBase == null) {
            return;
        }
        this.activeBase = activeBase;
        this.numField.setText(String.valueOf(activeBase.getGoodNum()));
    }
    
    public int getStringOneChar(String value, String oldChar) {
        String replace = value.replace(oldChar, "");
        return value.length() - replace.length();
    }
    
    public AutoActiveBase getCaab() {
        AutoActiveBaseBean autoActiveBaseBean = UserMessUntil.getAllAutoActive();
        List<AutoActiveBase> allActives = autoActiveBaseBean.getAllautobase();
        if (allActives != null && allActives.size() > 0) {
            for (int i = 0; i < allActives.size(); ++i) {
                if (this.activeBase.getId() == ((AutoActiveBase)allActives.get(i)).getId()) {
                    return (AutoActiveBase)allActives.get(i);
                }
            }
        }
        return null;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        AutoActiveBase autoActiveBase = this.getCaab();
        int sumReceive = 0;
        int num1 = TaskRoleData.SumReceive(this.activeBase.getTasksetId(), 2);
        int num2 = autoActiveBase.getComNum();
        int num3 = this.activeBase.getComNum();
        sumReceive = ((num1 > num2) ? num1 : num2);
        this.activeBase.setComNum((sumReceive > num3) ? sumReceive : num3);
        this.activeBase.setComNum(sumReceive);
        AutoActiveBaseBean autoActiveBaseBean = UserMessUntil.getAllAutoActive();
        List<AutoActiveBase> allActives = autoActiveBaseBean.getAllautobase();
        if (allActives != null && allActives.size() > 0) {
            for (int i = 0; i < allActives.size(); ++i) {
                AutoActiveBase activeBase = (AutoActiveBase)allActives.get(i);
                if (activeBase == null || activeBase.getId() == this.activeBase.getId()) {}
            }
        }
        if (this.icon == null) {
            this.icon = CutButtonImage.getImage("inkImg/background1/S342.png", 465, 53);
        }
        g.drawImage(this.icon.getImage(), 1, 1, 465, 53, null);
        Font TEXT_FONT60 = new Font("汉仪小隶书", 1, 16);
        Color color = new Color(147, 39, 30);
        Color color2 = new Color(2, 240, 30);
        g.setFont(TEXT_FONT60);
        g.setColor(color);
        g.drawString(this.activeBase.getaName() + "（已完成", 30, 30);
        g.setColor(color2);
        if (this.activeBase.getaName().length() % 3 == 0) {
            g.drawString(String.valueOf(this.activeBase.getComNum()), 8 + 21 * (this.activeBase.getaName().length() + 4), 30);
        }
        else {
            g.drawString(String.valueOf(this.activeBase.getComNum()), 5 + 21 * (this.activeBase.getaName().length() + 4), 30);
        }
        g.setColor(color);
        if (String.valueOf(this.activeBase.getComNum()).length() % 2 == 0) {
            g.drawString("次）", 10 + 21 * (this.activeBase.getaName().length() + 4 + (int)Math.ceil((double)((float)String.valueOf(this.activeBase.getComNum()).length() / 2.0f))), 30);
        }
        else if (String.valueOf(this.activeBase.getComNum()).length() % 3 == 0) {
            g.drawString("次）", 21 * (this.activeBase.getaName().length() + 4 + (int)Math.ceil((double)((float)String.valueOf(this.activeBase.getComNum()).length() / 2.0f))), 30);
        }
        else {
            g.drawString("次）", 21 * (this.activeBase.getaName().length() + 4 + (int)Math.ceil((double)((float)String.valueOf(this.activeBase.getComNum()).length() / 2.0f))), 30);
        }
    }
    
    public JTextField getNumField() {
        if (this.numField == null) {
            (this.numField = new JTextField()).setText("0");
            this.numField.setFont(UIUtils.TEXT_FONT1B);
            this.numField.setBackground(UIUtils.Color_BACK);
            this.numField.setBorder(BorderFactory.createEmptyBorder());
            this.numField.setForeground(Color.white);
            this.numField.setCaretColor(Color.white);
            this.numField.setBounds(392, 14, 60, 22);
            this.add(this.numField);
        }
        return this.numField;
    }
    
    public JCheckBox getJCheckBox() {
        if (this.checkBox == null) {
            ImageIcon icon = new ImageIcon("inkImg/background1/B461.png");
            ImageIcon selectIcon = new ImageIcon("inkImg/background1/B460.png");
            (this.checkBox = new JCheckBox(icon, false)).setBackground(UIUtils.Color_BACK);
            this.checkBox.setSelectedIcon(selectIcon);
            this.checkBox.setBounds(5, 15, 20, 20);
            this.add(this.checkBox);
        }
        return this.checkBox;
    }
    
    public ImageIcon getIcon() {
        return this.icon;
    }
    
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    public AutoActiveBase getActiveBase() {
        return this.activeBase;
    }
    
    public void setActiveBase(AutoActiveBase activeBase) {
        this.activeBase = activeBase;
    }
}
