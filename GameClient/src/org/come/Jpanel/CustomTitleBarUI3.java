package org.come.Jpanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import java.awt.Image;
import java.util.List;
import com.tool.btn.TitlelBtn;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomTitleBarUI3 extends JPanel
{
    private JLabel titleLabel;
    private TitlelBtn b1;
    private TitlelBtn b2;
    private List<TitlelBtn> titlelBtns;
    private String title;
    private Image myimage;
    private Integer index;
    private int first_x;
    private int first_y;
    Boolean b;
    
    public CustomTitleBarUI3(JFrame frame) {
        this.index = Integer.valueOf(0);
        this.b = Boolean.valueOf(false);
        this.setLayout(new FlowLayout(0, 5, 5));
        this.setPreferredSize(new Dimension(5, 5));
       // this.setCursor(new Cursor(0));
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                CustomTitleBarUI3.this.first_x = e.getX();
                CustomTitleBarUI3.this.first_y = e.getY();
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
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX() - CustomTitleBarUI3.this.first_x;
                int y = e.getY() - CustomTitleBarUI3.this.first_y;
                frame.setBounds(x + frame.getX(), y + frame.getY(), frame.getWidth(), frame.getHeight());
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Color color = new Color(113, 154, 194);
        Color color2 = new Color(113, 154, 194);
        g.setColor(color);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setComposite(AlphaComposite.getInstance(1, 1.0f));
        g2d.setColor(Color.black);
        g2d.fillRect(0, -4, 1, this.getHeight());
    }
    
    public void updateTitle(String newTitle) {
        this.titleLabel.setText(newTitle);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Title Bar UI Demo");
        frame.setDefaultCloseOperation(3);
        frame.setSize(400, 300);
        CustomTitleBarUI3 customTitleBarUI = new CustomTitleBarUI3(frame);
        customTitleBarUI.setOpaque(false);
        frame.add(customTitleBarUI, "North");
        frame.setVisible(true);
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Integer getIndex() {
        return this.index;
    }
    
    public void setIndex(Integer index) {
        this.index = index;
    }
}
