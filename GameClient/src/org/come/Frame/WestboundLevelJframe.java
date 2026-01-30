package org.come.Frame;

import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.WestboundLevelJpanel;
import org.come.until.FormsManagement;
import org.come.until.Music;
import org.come.until.ScrenceUntil;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 八十一难
 * @author admin
 *
 */
public class WestboundLevelJframe extends JInternalFrame implements MouseListener {

	private static WestboundLevelJpanel westboundLevelJpanel;
	private int first_x, first_y;// x、y坐标

	public static WestboundLevelJframe getRankingListJframe() {
		return (WestboundLevelJframe) FormsManagement.getInternalForm(3079).getFrame();
	}

	public WestboundLevelJframe() {

		super();
		westboundLevelJpanel = new WestboundLevelJpanel();
		this.getContentPane().add(westboundLevelJpanel);

		this.setBorder(BorderFactory.createEmptyBorder());// 去除内部窗体的边框
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);// 去除顶部的边框

		this.setBounds(ScrenceUntil.Screen_x/2-746/2, ScrenceUntil.Screen_y/2-514/2, 746, 514);// 设置窗口出现的位置
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
					setBounds(x + getX(), y + getY(), getWidth(), getHeight());
				}
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == 1) {
//			FormsManagement.HideForm(3078);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 关闭按钮
		// 开启窗口音效
		Music.addyinxiao("关闭窗口.mp3");
		// 打开了窗体
		if (e.isMetaDown()) {// 检测鼠标右键单击
			FormsManagement.HideForm(3079);
		} else {
			FormsManagement.Switchinglevel(3079);
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



	public static WestboundLevelJpanel getWestboundLevelJpanel() {
		return westboundLevelJpanel;
	}

	public static void setWestboundLevelJpanel(WestboundLevelJpanel westboundLevelJpanel) {
		WestboundLevelJframe.westboundLevelJpanel = westboundLevelJpanel;
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
