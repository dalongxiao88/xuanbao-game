package org.come.Jpanel;

import com.tool.btn.FormsOnOffBtn;
import com.tool.imagemonitor.ScriptTask;
import com.tool.tcpimg.RichLabel;
import com.tool.tcpimg.UIUtils;
import org.come.Frame.WelcomeXyJframe;
import org.come.thread.TimeControlRunnable;
import org.come.until.CutButtonImage;
import org.come.until.FormsManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class WelcomeXyJpanel extends JPanel {

	public String[] texts = {"#Y欢迎来到《大话西游》，在这里，每个玩家都是故事的主角，每段旅程都是一段新的传说。世界因你而精彩，让我们一起踏上这段充满挑战和奇遇的旅程！"};

	public JLabel startbel,pagebel,leftbel,rightbel,titlebel;

	public static String taskmsg = "";
	public static String taskID = "";
	private RichLabel richLabel;

	public static java.util.List<String> MsgList = new ArrayList<String>();

	public int cnum = 1;

	public static int indexs = 0;

	public static int X = 0;

	public WelcomeXyJpanel() {
		setPreferredSize(new Dimension(290, 137));
		setOpaque(false);
		setLayout(null);

		for(String string : texts) {
			MsgList.add(string);
		}



		(this.richLabel = new RichLabel("", UIUtils.TEXT_FONT, 255)).setBounds(30, 30, 260, 100);
		this.richLabel.setTextSize(MsgList.get(indexs), 230);
		this.richLabel.setBounds(33, 30, this.richLabel.getWidth(), this.richLabel.getHeight());
		this.add((Component)this.richLabel);



		startbel = new JLabel();
		startbel.setBounds(270, 155, 100, 20);
		startbel.setFont(new Font("宋体", Font.PLAIN, 12));
		startbel.setForeground(UIUtils.COLOR_BTNPUTONG[0]);
		startbel.setText("开启新手旅程");
		startbel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
//				updateCX();
				if(startbel.getText().equals("开启新手旅程")) {
					String guide = "1208-2680-2900-400343-999";//阿三
//					String guide = "1211-680-540-300025-999";//小鬼
					final String[] v = guide.split("-");
					TimeControlRunnable.addTask(new ScriptTask(v, 100));
				}
				if(startbel.getText().equals("     点击领取")) {
					final String[] v = taskmsg.split("-");
					TimeControlRunnable.addTask(new ScriptTask(v, Integer.parseInt(taskID),99),99);
				}
			}
		});
		this.add(startbel);
		pagebel = new JLabel();
		pagebel.setBounds(210, 155, 80, 20);
		pagebel.setFont(new Font("宋体", Font.PLAIN, 16));
		pagebel.setForeground(UIUtils.COLOR_BTNPUTONG[0]);
		pagebel.setText((indexs+1)+"/"+MsgList.size());
		this.add(pagebel);
		titlebel = new JLabel();
		titlebel.setBounds(210, 155, 80, 20);
		titlebel.setFont(new Font("宋体", Font.PLAIN, 16));
		titlebel.setForeground(UIUtils.COLOR_BTNPUTONG[0]);
		titlebel.setText("");
		this.add(titlebel);
		leftbel = new JLabel();
		leftbel.setBounds(190, 155, 14, 14);
		leftbel.setIcon(new ImageIcon("inkImg/button1/156.png"));
		leftbel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(indexs>0) {
					indexs -= 1;
					if(indexs == 0) {
						startbel.setText("开启新手旅程");
					}
					if(indexs > 0) {
						startbel.setText("     点击领取");
					}
				}
			}
		});
		this.add(leftbel);
		rightbel = new JLabel();
		rightbel.setBounds(253, 155, 14, 14);
		rightbel.setIcon(new ImageIcon("inkImg/button1/155.png"));
		rightbel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(indexs<MsgList.size()-1) {
					indexs += 1;
					if(indexs > 0) {
						startbel.setText("     点击领取");
					}
				}
			}
		});
		this.add(rightbel);
		// 添加关闭按钮
		FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/hongmu/gift-close-btn1.png", 1, 3081);
		offBtn.setBounds(290-25, 5, 23, 23);
		this.add(offBtn);
	}

	public ImageIcon icon1 = CutButtonImage.getImage("inkImg/hongmu/welcomejb1.png", 290, 137);
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(icon1.getImage(), 0, 0, 290, 137, this);
		this.richLabel.setTextSize(MsgList.get(indexs), 230);
		pagebel.setText((indexs+1)+"/"+MsgList.size());
		startbel.setBounds(190, 110, 100, 20);
//        pagebel.setBounds(130, 110, 80, 20);
		if(indexs+1>9) {
			pagebel.setBounds(130, 110, 80, 20);
		}else {
			pagebel.setBounds(135, 110, 80, 20);
		}
		rightbel.setBounds(163, 113, 14, 14);
		leftbel.setBounds(115, 113, 14, 14);
		titlebel.setBounds(118, 2, 80, 20);
	}


	public static void updateCX() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for(int i=0;i<217;i++) {
						X+=1;
						WelcomeXyJframe.getWelcomeXyJpanel().setBounds(0, X, 290, 137);
						Thread.sleep(1);
					}
					FormsManagement.HideForm(3081);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
	public static void updateOX() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for(int i=0;i<277;i++) {
						if(i >= 247) {
							X=X+1;
						}else {
							X-=1;
						}
						WelcomeXyJframe.getWelcomeXyJpanel().setBounds(0, X, 290, 137);
						Thread.sleep(2);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}


	public JLabel getStartbel() {
		return startbel;
	}


	public void setStartbel(JLabel startbel) {
		this.startbel = startbel;
	}

}


