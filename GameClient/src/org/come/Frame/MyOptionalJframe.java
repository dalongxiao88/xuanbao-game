package org.come.Frame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.come.Jpanel.MyOptionalJpanel;
import org.come.until.FormsManagement;
import org.come.until.Music;

import com.tool.tcpimg.UIUtils;
/**
 * 自选礼包
 * @author admin
 *
 */
public class MyOptionalJframe extends JInternalFrame implements MouseListener{

	private static MyOptionalJpanel myOptionalJpanel;
	private int first_x,first_y;//x、y坐标

	public static MyOptionalJframe getRankingListJframe() {
		return (MyOptionalJframe) FormsManagement.getInternalForm(3015).getFrame();
	}

	public MyOptionalJframe() {
		
		super();
		myOptionalJpanel=new MyOptionalJpanel();
		this.getContentPane().add(myOptionalJpanel);

		this.setBorder(BorderFactory.createEmptyBorder());//去除内部窗体的边框
		((BasicInternalFrameUI)this.getUI()).setNorthPane(null);//去除顶部的边框

		this.setBounds(240, 190, 554,223);//设置窗口出现的位置
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
//			FormsManagement.HideForm(3015);
			ZhuFrame.getZhuJpanel().addPrompt2("选择领取后自动关闭！");
			return;
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

	public static MyOptionalJpanel getMyOptionalJpanel() {
		return myOptionalJpanel;
	}

	public static void setMyOptionalJpanel(MyOptionalJpanel myOptionalJpanel) {
		MyOptionalJframe.myOptionalJpanel = myOptionalJpanel;
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
