package org.come.Jpanel;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import com.tool.btn.ExchangeCodeBtn;
import javax.swing.JTextArea;
import javax.swing.JPanel;

public class WxchangeAwardJpanel extends JPanel
{
    private JTextArea testMes;
    private ExchangeCodeBtn codeBtn1;
    private ExchangeCodeBtn codeBtn2;
    private JTextField textCode;
    private ImageIcon icon;
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon == null) {
            this.icon = new ImageIcon("inkImg/button1/x.png");
        }
        g.drawImage(this.icon.getImage(), 0, 0, 0, 0, this);
    }
}
