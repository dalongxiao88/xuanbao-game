package org.come.Frame;

import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.WelcomeXyJpanel;
import org.come.until.FormsManagement;
import org.come.until.Music;
import org.come.until.ScrenceUntil;
import org.come.Jpanel.AchievemMinJpanel;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import org.come.Jpanel.AnswerMinJpanel;
public class WelcomeXyJframe extends JInternalFrame implements MouseListener {

	private static WelcomeXyJpanel welcomeXyJpanel;
	private int first_x, first_y;// x、y坐标

	public static WelcomeXyJframe getRankingListJframe() {
		return (WelcomeXyJframe) FormsManagement.getInternalForm(3081).getFrame();
	}

	public WelcomeXyJframe() {

		super();
		welcomeXyJpanel = new WelcomeXyJpanel();
		this.getContentPane().add(welcomeXyJpanel);

		this.setBorder(BorderFactory.createEmptyBorder());// 去除内部窗体的边框
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);// 去除顶部的边框

		this.setBounds(ScrenceUntil.Screen_x-290, ScrenceUntil.Screen_y-217, 290, 137);// 设置窗口出现的位置
		this.setBackground(UIUtils.Color_BACK);
		this.pack();
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addMouseListener(this);
		this.addMouseMotionListener(new MouseMotionListener() {// 判断窗口移动的位置

			@Override
			public void mouseMoved(MouseEvent e) {

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (isVisible()) {
					int x = e.getX() - first_x;
					int y = e.getY() - first_y;
//					setBounds(x + getX(), y + getY(), getWidth(), getHeight());
				}
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == 1) {
//			FormsManagement.HideForm(3075);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 关闭按钮
		// 开启窗口音效
		Music.addyinxiao("关闭窗口.mp3");
		// 打开了窗体
		if (e.isMetaDown()) {// 检测鼠标右键单击
//			FormsManagement.HideForm(3081);
		} else {
			FormsManagement.Switchinglevel(3081);
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

	public static WelcomeXyJpanel getWelcomeXyJpanel() {
		return welcomeXyJpanel;
	}

	public static void setWelcomeXyJpanel(WelcomeXyJpanel welcomeXyJpanel) {
		WelcomeXyJframe.welcomeXyJpanel = welcomeXyJpanel;
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
}
