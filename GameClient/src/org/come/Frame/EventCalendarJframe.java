package org.come.Frame;

import java.util.Map;
import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import org.come.until.FormsManagement;
import org.come.Jpanel.EventCalendarJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class EventCalendarJframe extends JInternalFrame implements MouseListener
{
    private static EventCalendarJpanel eventCalendarJpanel;
    private int first_x;
    private int first_y;
    
    public static EventCalendarJframe getRankingListJframe() {
        return (EventCalendarJframe)FormsManagement.getInternalForm(60).getFrame();
    }
    
    public EventCalendarJframe() {
        EventCalendarJframe.eventCalendarJpanel = new EventCalendarJpanel();
        this.getContentPane().add(EventCalendarJframe.eventCalendarJpanel);
        this.setBorder(BorderFactory.createEmptyBorder());
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBounds(150, 100, 522, 520);
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(3);
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
            @Override
            public void mouseDragged(MouseEvent e) {
                if (EventCalendarJframe.this.isVisible()) {
                    int x = e.getX() - EventCalendarJframe.this.first_x;
                    int y = e.getY() - EventCalendarJframe.this.first_y;
                    EventCalendarJframe.this.setBounds(x + EventCalendarJframe.this.getX(), y + EventCalendarJframe.this.getY(), EventCalendarJframe.this.getWidth(), EventCalendarJframe.this.getHeight());
                }
            }
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(1104);
        }
        else {
            FormsManagement.Switchinglevel(1104);
            String text1 = "";
            String text2 = "";
            String text3 = "";
            System.out.println(this.first_x + "," + this.first_y);
            Map<String, String> maps = EventCalendarJframe.eventCalendarJpanel.getMap();
            int i = 1;
            while (i <= 30) {
                String[] xy = ((String)maps.get(i + "")).toString().split(",");
                int x = Integer.parseInt(xy[0]);
                int y = Integer.parseInt(xy[1]);
                int xx = Integer.parseInt(xy[0]) + Integer.parseInt(xy[2]);
                int yy = Integer.parseInt(xy[1]) + Integer.parseInt(xy[3]);
                if (e.getX() >= x && e.getX() <= xx && e.getY() >= y && e.getY() <= yy) {
                    if (((String)maps.get(i + "一")).toString() != "" && ((String)maps.get(i + "一")).toString().length() > 0) {
                        text1 = (String)maps.get(i + "一");
                    }
                    if (((String)maps.get(i + "二")).toString() != "" && ((String)maps.get(i + "二")).toString().length() > 0) {
                        text2 = (String)maps.get(i + "二");
                    }
                    if (maps.get(i + "三") != null) {
                        text3 = (String)maps.get(i + "三");
                        break;
                    }
                    else {
                        break;
                    }
                }
                else {
                    ++i;
                }
            }
            if (text1 != "") {
                EventCalendarJframe.eventCalendarJpanel.labtext7.setText(text1);
            }
            else {
                EventCalendarJframe.eventCalendarJpanel.labtext7.setText(text1);
            }
            if (text2 != "") {
                EventCalendarJframe.eventCalendarJpanel.labtext8.setText(text2);
            }
            else {
                EventCalendarJframe.eventCalendarJpanel.labtext8.setText(text2);
            }
            if (text3 != "") {
                EventCalendarJframe.eventCalendarJpanel.labtext9.setText(text3);
            }
            else {
                EventCalendarJframe.eventCalendarJpanel.labtext9.setText(text3);
            }
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
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
    
    public static EventCalendarJpanel getEventCalendarJpanel() {
        return EventCalendarJframe.eventCalendarJpanel;
    }
    
    public void setEventCalendarJpanel(EventCalendarJpanel eventCalendarJpanel) {
        EventCalendarJframe.eventCalendarJpanel = eventCalendarJpanel;
    }
    
    public int getFirst_x() {
        return this.first_x;
    }
    
    public void setFirst_x(int first_x) {
        this.first_x = first_x;
    }
    
    public int getFirst_y() {
        return this.first_y;
    }
    
    public void setFirst_y(int first_y) {
        this.first_y = first_y;
    }
}
