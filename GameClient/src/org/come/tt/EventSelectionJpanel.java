package org.come.tt;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Graphics;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class EventSelectionJpanel extends JPanel
{
    private ImageIcon beijing;
    private EventSelecton laddBox;
    private EventSelecton petBox;
    private EventSelecton leagueBox;
    private JLabel ttBox_1_0;
    private JLabel ttBox_1_1;
    private JLabel ttBox_2_0;
    private JLabel ttBox_2_1;
    private JLabel ttBox_3_0;
    private JLabel ttBox_3_1;
    private Component component;
    
    public JLabel getTtBox_1_0() {
        return this.ttBox_1_0;
    }
    
    public void setTtBox_1_0(JLabel ttBox_1_0) {
        this.ttBox_1_0 = ttBox_1_0;
    }
    
    public JLabel getTtBox_1_1() {
        return this.ttBox_1_1;
    }
    
    public void setTtBox_1_1(JLabel ttBox_1_1) {
        this.ttBox_1_1 = ttBox_1_1;
    }
    
    public JLabel getTtBox_2_0() {
        return this.ttBox_2_0;
    }
    
    public void setTtBox_2_0(JLabel ttBox_2_0) {
        this.ttBox_2_0 = ttBox_2_0;
    }
    
    public JLabel getTtBox_2_1() {
        return this.ttBox_2_1;
    }
    
    public void setTtBox_2_1(JLabel ttBox_2_1) {
        this.ttBox_2_1 = ttBox_2_1;
    }
    
    public JLabel getTtBox_3_0() {
        return this.ttBox_3_0;
    }
    
    public void setTtBox_3_0(JLabel ttBox_3_0) {
        this.ttBox_3_0 = ttBox_3_0;
    }
    
    public JLabel getTtBox_3_1() {
        return this.ttBox_3_1;
    }
    
    public void setTtBox_3_1(JLabel ttBox_3_1) {
        this.ttBox_3_1 = ttBox_3_1;
    }
    
    public EventSelectionJpanel() {
        this.setPreferredSize(new Dimension(684, 455));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        this.beijing = new ImageIcon("inkImg/background/ttBox.png");
        (this.ttBox_1_0 = new JLabel()).setBounds(40, 30, 192, 378);
        ImageIcon Img_0_0 = new ImageIcon("inkImg/background/ttBox-1-0.png");
        this.ttBox_1_0.setIcon(Img_0_0);
        this.ttBox_1_0.addMouseListener(new EventSelectionMouseListener(this, Integer.valueOf(1)));
        (this.ttBox_1_1 = new JLabel()).setBounds(40, 30, 192, 378);
        ImageIcon Img_0_2 = new ImageIcon("inkImg/background/ttBox-1-1.png");
        this.ttBox_1_1.setIcon(Img_0_2);
        this.ttBox_1_1.setVisible(false);
        this.ttBox_1_1.addMouseListener(new EventSelectionMouseListener(this, Integer.valueOf(1)));
        (this.ttBox_2_0 = new JLabel()).setBounds(242, 30, 192, 378);
        ImageIcon Img_2_0 = new ImageIcon("inkImg/background/ttBox-2-0.png");
        this.ttBox_2_0.setIcon(Img_2_0);
        this.ttBox_2_0.addMouseListener(new EventSelectionMouseListener(this, Integer.valueOf(2)));
        (this.ttBox_2_1 = new JLabel()).setBounds(242, 30, 192, 378);
        ImageIcon Img_2_2 = new ImageIcon("inkImg/background/ttBox-2-1.png");
        this.ttBox_2_1.setIcon(Img_2_2);
        this.ttBox_2_1.setVisible(false);
        this.ttBox_2_1.addMouseListener(new EventSelectionMouseListener(this, Integer.valueOf(2)));
        (this.ttBox_3_0 = new JLabel()).setBounds(444, 30, 192, 378);
        ImageIcon Img_3_0 = new ImageIcon("inkImg/background/ttBox-3-0.png");
        this.ttBox_3_0.setIcon(Img_3_0);
        this.ttBox_3_0.addMouseListener(new EventSelectionMouseListener(this, Integer.valueOf(3)));
        (this.ttBox_3_1 = new JLabel()).setBounds(444, 30, 192, 378);
        ImageIcon Img_3_2 = new ImageIcon("inkImg/background/ttBox-3-1.png");
        this.ttBox_3_1.setIcon(Img_3_2);
        this.ttBox_3_1.setVisible(false);
        this.ttBox_3_1.addMouseListener(new EventSelectionMouseListener(this, Integer.valueOf(3)));
        this.add(this.ttBox_1_0);
        this.add(this.ttBox_1_1);
        this.add(this.ttBox_2_1);
        this.add(this.ttBox_2_0);
        this.add(this.ttBox_3_1);
        this.add(this.ttBox_3_0);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.beijing.getImage(), 0, 0, 684, 455, null);
    }
    
    public ImageIcon getBeijing() {
        return this.beijing;
    }
    
    public void setBeijing(ImageIcon beijing) {
        this.beijing = beijing;
    }
    
    public EventSelecton getLaddBox() {
        return this.laddBox;
    }
    
    public void setLaddBox(EventSelecton laddBox) {
        this.laddBox = laddBox;
    }
    
    public EventSelecton getPetBox() {
        return this.petBox;
    }
    
    public void setPetBox(EventSelecton petBox) {
        this.petBox = petBox;
    }
    
    public EventSelecton getLeagueBox() {
        return this.leagueBox;
    }
    
    public void setLeagueBox(EventSelecton leagueBox) {
        this.leagueBox = leagueBox;
    }
    
    class imgFocusListener implements FocusListener
    {
        EventSelecton eventSelecton;
        
        public imgFocusListener(EventSelecton eventSelecton) {
            this.eventSelecton = eventSelecton;
        }
        
        @Override
        public void focusGained(FocusEvent e) {
            this.eventSelecton.setIndex(Integer.valueOf(1));
            EventSelectionJpanel.this.ttBox_1_0.setBounds(30, 10, 192, 378);
            System.out.println("11111111111");
        }
        
        @Override
        public void focusLost(FocusEvent e) {
            this.eventSelecton.setIndex(Integer.valueOf(0));
            System.out.println("22222222222222");
        }
    }
}
