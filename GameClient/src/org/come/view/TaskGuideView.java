package org.come.view;

import org.come.mouslisten.Mouselistener;
import java.awt.event.MouseEvent;
import org.come.until.CutButtonImage;
import com.tool.tcpimg.RichLabel;
import java.util.List;
import com.tool.ModerateTask.Task;
import com.tool.ModerateTask.Hero;
import java.awt.Graphics;
import com.updateNew.MyIsif;
import javax.swing.ImageIcon;
import com.tool.tcpimg.InputBean;
import com.tool.btn.TaskGuideBtn;
import javax.swing.event.MouseInputListener;
import javax.swing.JComponent;

public class TaskGuideView extends JComponent implements MouseInputListener
{
    private boolean isV;
    private boolean isTypeOne;
    private boolean isTypeTwo;
    private TaskGuideBtn guideBtn1;
    private TaskGuideBtn guideBtn2;
    private int H;
    private InputBean inputBean;
    ImageIcon icon;
    private int first_x;
    private int first_y;
    
    public TaskGuideView() {
        this.icon = null;
        this.setBounds(0, 200, 188, this.H = 121);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setVisible(false);
        this.isV = false;
        this.isTypeOne = false;
        this.isTypeTwo = false;
        if (MyIsif.getStyle().equals("水墨")) {
            (this.guideBtn1 = new TaskGuideBtn("inkImg/button/12.png", 1, 0, this)).setBounds(5, 2, 20, 20);
            this.add(this.guideBtn1);
            (this.guideBtn2 = new TaskGuideBtn("inkImg/button1/syrw.png", 1, 1, this)).setBounds(0, 0, 188, 23);
            this.guideBtn2.addMouseMotionListener(this);
            this.add(this.guideBtn2);
        }
        else {
            (this.guideBtn1 = new TaskGuideBtn("img/xy2uiimg/30_png.button.btn_8.png", 1, 0, this)).setBounds(5, 2, 20, 20);
            this.add(this.guideBtn1);
            (this.guideBtn2 = new TaskGuideBtn("inkImg/button1/syrwh.png", 1, 1, this)).setBounds(0, 0, 188, 23);
            this.guideBtn2.addMouseMotionListener(this);
            this.add(this.guideBtn2);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (this.isTypeOne || this.isTypeTwo) {
            return;
        }
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/button/B75.png");
            }
            g.drawImage(this.icon.getImage(), 0, 23, 188, this.getHeight() - 23, null);
        }
        else {
            if (this.icon == null) {
                this.icon = new ImageIcon("inkImg/hongmu/renwuzhui.png");
            }
            g.drawImage(this.icon.getImage(), 0, 23, 188, this.getHeight() - 23, null);
        }
        List<Task> tasks = Hero.getList();
        if (tasks != null && tasks.size() != 0) {
            int History = 0;
            int hig = 23;
            g.translate(5, 23);
            for (int i = 0; i < tasks.size(); ++i) {
                RichLabel richLabel = ((Task)tasks.get(i)).getRichLabel();
                if (richLabel != null) {
                    g.translate(0, History);
                    History = richLabel.getHeight();
                    hig += History;
                    richLabel.paint(g);
                }
            }
            g.translate(-5, -hig + History);
            hig += 5;
            if (hig != this.H) {
                this.H = hig;
                this.setBounds(this.getX(), this.getY(), this.getWidth(), this.H);
            }
        }
        else if (this.H != 121) {
            this.H = 121;
            this.setBounds(this.getX(), this.getY(), this.getWidth(), this.H);
        }
    }
    
    public void guideSX(List<Task> tasks) {
        if (this.isV != (tasks != null && tasks.size() != 0)) {
            this.setVisible(this.isV = !this.isV);
        }
    }
    
    public void guideShow(int type) {
        try {
            if (MyIsif.getStyle().equals("水墨")) {
                if (type == 0) {
                    this.isTypeOne = !this.isTypeOne;
                    if (this.isTypeOne) {
                        this.guideBtn1.setIcons(CutButtonImage.cuts("inkImg/button/11.png"));
                        this.guideBtn1.setBounds(5, 1, 20, 20);
                        this.setBounds(0, 200, 20, 20);
                    }
                    else {
                        this.guideBtn1.setIcons(CutButtonImage.cuts("inkImg/button/12.png"));
                        this.guideBtn1.setBounds(5, 2, 20, 20);
                        this.setBounds(0, 200, 188, this.H);
                    }
                }
                else if (type == 1) {
                    this.isTypeOne = !this.isTypeOne;
                    if (this.isTypeOne) {
                        this.setBounds(this.getX(), this.getY(), 188, 23);
                    }
                    else {
                        this.setBounds(this.getX(), this.getY(), 188, this.H);
                    }
                }
            }
            else if (type == 0) {
                this.isTypeOne = !this.isTypeOne;
                if (this.isTypeOne) {
                    this.guideBtn1.setIcons(CutButtonImage.cuts("img/xy2uiimg/36_png.button.btn_7.png"));
                    this.guideBtn1.setBounds(1, 1, 20, 20);
                    this.setBounds(0, 200, 20, 20);
                }
                else {
                    this.guideBtn1.setIcons(CutButtonImage.cuts("img/xy2uiimg/30_png.button.btn_8.png"));
                    this.guideBtn1.setBounds(5, 2, 20, 20);
                    this.setBounds(0, 200, 188, this.H);
                }
            }
            else if (type == 1) {
                this.isTypeOne = !this.isTypeOne;
                if (this.isTypeOne) {
                    this.setBounds(this.getX(), this.getY() + 20, 188, 23);
                }
                else {
                    this.setBounds(this.getX(), this.getY() + 20, 188, this.H);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void DJ(int x, int y) {
        this.first_x = x;
        this.first_y = y;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        this.first_x = e.getX();
        this.first_y = e.getY();
        List<Task> tasks = Hero.getList();
        if (tasks != null && tasks.size() != 0) {
            int hig = 23;
            for (int i = 0; i < tasks.size(); ++i) {
                RichLabel richLabel = ((Task)tasks.get(i)).getRichLabel();
                if (richLabel != null) {
                    InputBean bean = richLabel.isMonitor(this.first_x - 5, this.first_y - hig);
                    if (bean != null) {
                        (this.inputBean = bean).setM(true);
                        return;
                    }
                    hig += richLabel.getHeight();
                }
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.inputBean != null) {
            Mouselistener.DJInputBean(this.inputBean);
            this.inputBean = null;
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX() - this.first_x;
        int y = e.getY() - this.first_y;
        this.setBounds(x + this.getX(), y + this.getY(), this.getWidth(), this.getHeight());
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    public boolean isV() {
        return this.isV;
    }
}
