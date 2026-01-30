
package org.come.XuanBao;

import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;

import java.awt.*;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.come.XuanBao.ChoseXuanBaoTypeMouslisten;
import org.come.XuanBao.XuanBaoCardJpanel;
import org.come.XuanBao.XuanBaoJframe;

public class XuanBaoJpanel
        extends JPanel {
    private JButton btnEquipment;
    private JButton btnAttribute;
    public XuanBaoJframe xuanBaoJframe;
    public XuanBaoCardJpanel xuanBaoCardJpanel;
    private FormsOnOffBtn tjBtn;


    public XuanBaoJpanel(XuanBaoJframe xuanBaoJframe) {
        this.xuanBaoJframe = xuanBaoJframe;
        this.xuanBaoCardJpanel = new XuanBaoCardJpanel();
        setBackground(UIUtils.Color_BACK);
        setLayout(null);
        setPreferredSize(new Dimension(619, 482));
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 8031);
        offBtn.setBounds(582, 10, 25, 25);
        add(offBtn);
        tjBtn = new FormsOnOffBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT,UIUtils.TEXT_FONT2, "玄宝图鉴", 8038);
        tjBtn.setBounds(41, 15, 68, 17);
        this.add(tjBtn);
        // 添加"我要合成玄印"按钮，使用ID 803
//        FormsOnOffBtn synthesisBtn = new FormsOnOffBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT,UIUtils.TEXT_FONT2, "合成玄印", 803);
////        synthesisBtn.setFont(UIUtils.TEXT_FONT1);
//        synthesisBtn.setBounds(115, 15, 90, 17);
        // 添加"删除玄宝"按钮，使用ID 8039
//        FormsOnOffBtn deleteBtn = new FormsOnOffBtn("inkImg/button/49.png", 1, UIUtils.COLOR_BTNTEXT,UIUtils.TEXT_FONT2, "删除玄宝", 8039);
////        deleteBtn.setFont(UIUtils.TEXT_FONT1);
//        deleteBtn.setBounds(190, 15, 90, 17);
//        this.add(deleteBtn);

//        this.add(synthesisBtn);
        this.btnEquipment = new JButton();
        this.btnEquipment.setBounds(40, 35, 35, 82);
        this.btnEquipment.addMouseListener((MouseListener) new ChoseXuanBaoTypeMouslisten("玄宝装备", this.xuanBaoCardJpanel));
        this.btnEquipment.setBackground(UIUtils.Color_BACK);
        this.btnEquipment.setBorder(BorderFactory.createEmptyBorder());
        this.btnEquipment.setBorderPainted(false);
        this.btnEquipment.setContentAreaFilled(false);
        this.btnEquipment.setHorizontalTextPosition(0);
        add(this.btnEquipment);
        this.btnAttribute = new JButton();
        this.btnAttribute.setBounds(40, 111, 35, 82);
        this.btnAttribute.addMouseListener((MouseListener) new ChoseXuanBaoTypeMouslisten("玄宝属性", this.xuanBaoCardJpanel));
        this.btnAttribute.setBackground(UIUtils.Color_BACK);
        this.btnAttribute.setBorder(BorderFactory.createEmptyBorder());
        this.btnAttribute.setBorderPainted(false);
        this.btnAttribute.setContentAreaFilled(false);
        this.btnAttribute.setHorizontalTextPosition(0);
        add(this.btnAttribute);
        this.xuanBaoCardJpanel.setBounds(0, 0, 619, 482);
        add((Component) this.xuanBaoCardJpanel);
    }
}
