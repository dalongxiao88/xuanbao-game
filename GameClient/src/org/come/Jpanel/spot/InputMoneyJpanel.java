package org.come.Jpanel.spot;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import org.come.until.FormsManagement;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.Font;
import com.tool.btn.MoBanBtn;
import java.awt.Graphics;
import org.apache.commons.lang.StringUtils;
import java.math.BigDecimal;
import java.awt.Dimension;
import com.tool.Document.NumberDocument;
import javax.swing.BorderFactory;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import org.come.Jpanel.spot.stall.SpotStallBaseJpanel;
import com.tool.Document.InputNum;
import javax.swing.JPanel;

public class InputMoneyJpanel extends JPanel implements InputNum
{
    private SpotStallBaseJpanel spotStallBaseJpanel;
    private final JTextField textUnitprice;
    private final InputMoneyBtn okBtn;
    private final InputMoneyBtn noBtn;
    private ImageIcon icon;
    
    public InputMoneyJpanel() {
        this.setLayout(null);
        this.setOpaque(false);
        (this.textUnitprice = new JTextField()).setFont(UIUtils.TEXT_FONT1);
        this.textUnitprice.setText("0");
        this.textUnitprice.setOpaque(false);
        this.textUnitprice.setForeground(Color.WHITE);
        this.textUnitprice.setCaretColor(Color.WHITE);
        this.textUnitprice.setBorder(BorderFactory.createEmptyBorder());
        this.textUnitprice.setDocument(new NumberDocument(this.textUnitprice, 4, this));
        this.textUnitprice.addKeyListener(new TextKeyListener());
        this.setPreferredSize(new Dimension(296, 126));
        this.icon = new ImageIcon("inkImg/background/S342.png");
        this.textUnitprice.setBounds(128, 39, 145, 18);
        (this.okBtn = new InputMoneyBtn("inkImg/button/32.png", UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "确认", 1)).setBounds(68, 76, 59, 25);
        (this.noBtn = new InputMoneyBtn("inkImg/button/32.png", UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "取消", 0)).setBounds(174, 76, 59, 25);
        this.add(this.textUnitprice);
        this.add(this.okBtn);
        this.add(this.noBtn);
    }
    
    public void init(SpotStallBaseJpanel spotStallBaseJpanel) {
        this.spotStallBaseJpanel = spotStallBaseJpanel;
        this.textUnitprice.setText("");
    }
    
    public BigDecimal getUnitprice() {
        if (StringUtils.isNotBlank(this.textUnitprice.getText())) {
            return new BigDecimal(this.textUnitprice.getText().replaceAll(",", ""));
        }
        return BigDecimal.ZERO;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.icon != null) {
            g.drawImage(this.icon.getImage(), 0, 0, this);
        }
    }
    
    @Override
    public void upNum() {
    }
    
    @Override
    public boolean isChange() {
        return false;
    }
    
    private class InputMoneyBtn extends MoBanBtn
    {
        private int operationId;
        
        public InputMoneyBtn(String iconpath, Color[] colors, Font font, String text, int operationId) {
            super(iconpath, 1, colors);
            this.setText(text);
            this.setFont(font);
            this.setVerticalTextPosition(0);
            this.setHorizontalTextPosition(0);
            this.operationId = operationId;
        }
        
        @Override
        public void chooseyes() {
        }
        
        @Override
        public void chooseno() {
        }
        
        @Override
        public void nochoose(MouseEvent e) {
            if (this.operationId == 1) {
                BigDecimal unitPrice = InputMoneyJpanel.this.getUnitprice();
                if (unitPrice.compareTo(BigDecimal.ZERO) == 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请输入价格");
                    return;
                }
                InputMoneyJpanel.this.spotStallBaseJpanel.setUnitprice(unitPrice);
                InputMoneyJpanel.this.spotStallBaseJpanel.listing();
            }
            FormsManagement.HideForm(802);
        }
    }
    
    private class TextKeyListener extends KeyAdapter
    {
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
            String str = InputMoneyJpanel.this.textUnitprice.getText();
            if (str.length() > 0 && str.charAt(0) == '0') {
                InputMoneyJpanel.this.textUnitprice.setText(Long.valueOf(str).toString());
            }
        }
    }
}
