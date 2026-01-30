package org.come.Frame;

import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.WeaponGodJpanel;
import org.come.until.FormsManagement;
import org.come.until.Music;
import org.come.until.ScrenceUntil;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//获得神兵展示
public class WeaponGodJframe extends JInternalFrame implements MouseListener{

	private static WeaponGodJpanel weaponGodJpanel;
	private int first_x,first_y;//x、y坐标

	public static WeaponGodJframe getRankingListJframe() {
		return (WeaponGodJframe) FormsManagement.getInternalForm(3007).getFrame();
	}

	public WeaponGodJframe() {
		
		super();
		weaponGodJpanel=new WeaponGodJpanel();
		this.getContentPane().add(weaponGodJpanel);

		this.setBorder(BorderFactory.createEmptyBorder());//去除内部窗体的边框
		((BasicInternalFrameUI)this.getUI()).setNorthPane(null);//去除顶部的边框

		this.setBounds(ScrenceUntil.Screen_x/4, ScrenceUntil.Screen_y/3+120, 466, 191);//设置窗口出现的位置
		this.setBackground(UIUtils.Color_BACK);
		this.pack();
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(new MouseMotionListener() {//判断窗口移动的位置
		
		@Override
		public void mouseMoved(MouseEvent e) {
			
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
			FormsManagement.HideForm(3007);
		}else {
			FormsManagement.Switchinglevel(3007);
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

	public static WeaponGodJpanel getWeaponGodJpanel() {
		return weaponGodJpanel;
	}

	public static void setWeaponGodJpanel(WeaponGodJpanel weaponGodJpanel) {
		WeaponGodJframe.weaponGodJpanel = weaponGodJpanel;
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