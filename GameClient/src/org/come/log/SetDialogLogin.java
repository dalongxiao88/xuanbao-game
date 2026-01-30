package org.come.log;

import java.awt.event.ActionEvent;
import java.awt.Graphics2D;
import java.awt.Graphics;
import org.apache.commons.lang.StringUtils;
import org.come.mouslisten.SystemMouslisten;
import com.main.UpdateMain;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class SetDialogLogin extends JPanel implements ActionListener
{
    private ImageIcon iconBack;
    private JLabel name;
    private JComboBox<String> comboBox;
    
    public SetDialogLogin(SetJframe tj) {
        this.setPreferredSize(new Dimension(400, 300));
        this.setLayout(null);
        String[] options = { "多标签", "独立窗口" };
        (this.comboBox = new JComboBox(options)).setFont(UIUtils.TEXT_FONT2);
        this.comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getItem().equals("多标签")) {
                    UpdateMain.Param = "DK";
                }
                else {
                    UpdateMain.Param = "D";
                }
                SystemMouslisten.writeTxt("gameParam", UpdateMain.Param);
            }
        });
        this.comboBox.setBounds(150, 20, 120, 25);
        this.add(this.comboBox);
        String gameParam = SystemMouslisten.readSysteminit("gameParam");
        if (StringUtils.isNotBlank(gameParam)) {
            if (gameParam.equals("D")) {
                this.comboBox.setSelectedItem("独立窗口");
                UpdateMain.Param = "D";
            }
            else {
                this.comboBox.setSelectedItem("多标签");
                UpdateMain.Param = "DK";
            }
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics = (Graphics2D)g.create();
        graphics.setFont(UIUtils.TEXT_FONT2);
        graphics.drawString("选择登录方式", 40, 40);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
