package org.come.Jpanel;

import javax.swing.BorderFactory;
import org.come.mouslisten.ChoseLingbaoTypeMouslisten;
import com.tool.btn.FormsOnOffBtn;
import java.awt.Dimension;
import com.updateNew.MyIsif;
import java.awt.LayoutManager;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.LingBaoZhiYuanBtn;
import javax.swing.JButton;
import org.come.Frame.LingbaoJframe;
import javax.swing.JPanel;

public class LingbaoJpanel extends JPanel
{
    private LingbaoJframe lingbaoJframe;
    private LingbaoCardJpanel lingbaoCardJpanel;
    private JButton btnEquipment;
    private JButton btnAttribute;
    private LingBaoZhiYuanBtn lingBaoZhiYuanBtn;
    
    public LingbaoJpanel(LingbaoJframe lingbaoJframe) throws Exception {
        this.lingbaoJframe = lingbaoJframe;
        this.lingbaoCardJpanel = new LingbaoCardJpanel();
        this.setBackground(UIUtils.Color_BACK);
        this.setLayout((LayoutManager)null);
        if (MyIsif.getStyle().equals("水墨")) {
            this.setPreferredSize(new Dimension(538, 469));
            FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 43);
            offBtn.setBounds(501, 10, 25, 25);
            this.add(offBtn);
            (this.btnEquipment = new JButton()).setBounds(40, 35, 35, 82);
            this.btnEquipment.addMouseListener(new ChoseLingbaoTypeMouslisten("灵宝装备", this.lingbaoCardJpanel));
            this.btnEquipment.setBackground(UIUtils.Color_BACK);
            this.btnEquipment.setBorder(BorderFactory.createEmptyBorder());
            this.btnEquipment.setBorderPainted(false);
            this.btnEquipment.setContentAreaFilled(false);
            this.btnEquipment.setHorizontalTextPosition(0);
            this.add(this.btnEquipment);
            (this.btnAttribute = new JButton()).setBounds(40, 111, 35, 82);
            this.btnAttribute.addMouseListener(new ChoseLingbaoTypeMouslisten("灵宝属性", this.lingbaoCardJpanel));
            this.btnAttribute.setBackground(UIUtils.Color_BACK);
            this.btnAttribute.setBorder(BorderFactory.createEmptyBorder());
            this.btnAttribute.setBorderPainted(false);
            this.btnAttribute.setContentAreaFilled(false);
            this.btnAttribute.setHorizontalTextPosition(0);
            this.add(this.btnAttribute);
            this.lingbaoCardJpanel.setBounds(0, 0, 538, 469);
            this.add(this.lingbaoCardJpanel);
        }
        else {
            this.setPreferredSize(new Dimension(512, 496));
            FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 43);
            offBtn.setBounds(492, 0, 23, 23);
            this.add(offBtn);
            (this.btnEquipment = new JButton()).setBounds(23, 54, 26, 82);
            this.btnEquipment.addMouseListener(new ChoseLingbaoTypeMouslisten("灵宝装备", this.lingbaoCardJpanel));
            this.btnEquipment.setBackground(UIUtils.Color_BACK);
            this.btnEquipment.setBorder(BorderFactory.createEmptyBorder());
            this.btnEquipment.setBorderPainted(false);
            this.btnEquipment.setContentAreaFilled(false);
            this.btnEquipment.setHorizontalTextPosition(0);
            this.add(this.btnEquipment);
            (this.btnAttribute = new JButton()).setBounds(23, 130, 26, 82);
            this.btnAttribute.addMouseListener(new ChoseLingbaoTypeMouslisten("灵宝属性", this.lingbaoCardJpanel));
            this.btnAttribute.setBackground(UIUtils.Color_BACK);
            this.btnAttribute.setBorder(BorderFactory.createEmptyBorder());
            this.btnAttribute.setBorderPainted(false);
            this.btnAttribute.setContentAreaFilled(false);
            this.btnAttribute.setHorizontalTextPosition(0);
            this.add(this.btnAttribute);
            this.lingbaoCardJpanel.setBounds(0, 0, 512, 496);
            this.add(this.lingbaoCardJpanel);
        }
    }
    
    public LingbaoJframe getLingbaoJframe() {
        return this.lingbaoJframe;
    }
    
    public void setLingbaoJframe(LingbaoJframe lingbaoJframe) {
        this.lingbaoJframe = lingbaoJframe;
    }
    
    public LingbaoCardJpanel getLingbaoCardJpanel() {
        return this.lingbaoCardJpanel;
    }
    
    public void setLingbaoCardJpanel(LingbaoCardJpanel lingbaoCardJpanel) {
        this.lingbaoCardJpanel = lingbaoCardJpanel;
    }
    
    public JButton getBtnEquipment() {
        return this.btnEquipment;
    }
    
    public void setBtnEquipment(JButton btnEquipment) {
        this.btnEquipment = btnEquipment;
    }
    
    public JButton getBtnAttribute() {
        return this.btnAttribute;
    }
    
    public void setBtnAttribute(JButton btnAttribute) {
        this.btnAttribute = btnAttribute;
    }
}
