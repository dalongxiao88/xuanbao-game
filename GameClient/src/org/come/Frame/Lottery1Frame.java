package org.come.Frame;

import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.Lottery1Jpanel;
import org.come.Jpanel.LotteryJpanel;
import org.come.until.FormsManagement;
import org.come.until.Music;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Lottery1Frame extends JInternalFrame implements MouseListener {
    private static Lottery1Jpanel lotteryJpanel;
    private int first_x,first_y;//x、y坐标

    public static Lottery1Frame getLottery1Frame() {
        return (Lottery1Frame) FormsManagement.getInternalForm(3005).getFrame();
    }

    public Lottery1Frame() {

        super();
        lotteryJpanel=new Lottery1Jpanel();
        this.getContentPane().add(lotteryJpanel);

        this.setBorder(BorderFactory.createEmptyBorder());//去除内部窗体的边框
        ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);//去除顶部的边框

        this.setBounds(100, 100, 880, 502);//设置窗口出现的位置
        this.setBackground(UIUtils.Color_BACK);
        this.pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionListener() {//判断窗口移动的位置

            @Override
            public void mouseMoved(MouseEvent e) {
                first_x = e.getX();
                first_y = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (isVisible()) {
                    int x = e.getX() - first_x;
                    int y = e.getY() - first_y;
                    setBounds(x + getX(), y + getY(),getWidth(),getHeight());
                }
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //关闭按钮
        //开启窗口音效
        Music.addyinxiao("关闭窗口.mp3");
        //打开了窗体
        if(e.isMetaDown()){//检测鼠标右键单击
//            FormsManagement.HideForm(3004);
        }else {
            FormsManagement.Switchinglevel(3005);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
    private double centerX;
    private double centerY;
    public void rotatePoint(double x, double y, double angle, double centerX, double centerY) {
        // 平移到以中心点为原点的坐标系
        x += centerX;
        y += centerY;

        // 将角度转换为弧度
        double theta = Math.toRadians(angle);

        // 计算旋转矩阵中的 cos(θ) 和 sin(θ)
        double cosTheta = Math.cos(theta);
        double sinTheta = Math.sin(theta);

        // 应用旋转矩阵得到旋转后的坐标
        double newX = x * cosTheta - y * sinTheta;
        double newY = x * sinTheta + y * cosTheta;

        this.centerX = newX+centerX;
        this.centerY = newY+centerY;
    }


    public int getFirst_x() {
        return first_x;
    }

    public void setFirst_x(int first_x) {
        this.first_x = first_x;
    }

    public int getFirst_y() {
        return first_y;
    }

    public void setFirst_y(int first_y) {
        this.first_y = first_y;
    }

    public static Lottery1Jpanel getLotteryJpanel() {
        return lotteryJpanel;
    }
}
