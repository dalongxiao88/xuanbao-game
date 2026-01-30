package org.come.Jpanel;

import com.tool.tcpimg.UIUtils;
import come.tool.utilEnum.AnswerEnum;
import org.apache.commons.lang.StringUtils;
import org.come.Frame.AnswerMinJframe;
import org.come.Frame.ZhuFrame;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.CutButtonImage;
import org.come.until.FormsManagement;
import org.come.until.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 种树
 * 
 * @author admin
 *
 */
public class AnswerMinJpanel extends JPanel {

	private String text = "床前明月光，疑是地上霜。这首诗的作者在创作时候身高多少？";
	
	private JLabel d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12,d13,d14,d15,d16,d17,d18,d19,d20,d21,d22,d23,d24,d25,d26,d27,d28,d29,d30,d31,d32,d33,d34,d35,d36,d37,d38,d39,d40,d41,d42,d43,d44,d45,d46,d47,d48,d49,d50,d51,d52,d53,d54,d55,d56,d57,d58,d59,d60,d61,d62,d63,d64,d65,d66,d67,d68,d69,d70,d71,d72,d73,d74,d75,d76,d77,d78,d79,d80,d81,d82,d83,d84,d85,d86,d87,d88,d89,d90,d91,d92,d93,d94,d95,d96,d97,d98,d99,d100,d101,d102,d103,d104,d105,d106,d107,d108,d109,d110,d111,d112,d113,d114,d115,d116,d117,d118,d119,d120,d121,d122,d123,d124,d125,d126,d127,d128,d129,d130,d131
			;
	
	public static String[] da = {"A：0.5米","B：3米","C：不知道","D：问老G"};
	
	public static String answer = "";
	private int currentChar = 0;
	private Timer timer; // 定时器变量
	
	public static String NPCID = "";
	
	public AnswerMinJpanel() {

		this.setPreferredSize(new Dimension(473, 325));
		this.setLayout(null);
		this.setBackground(UIUtils.Color_BACK);

		Font font = new Font("楷体", 0, 16);
		int x = 150;
		int y = 30;
		d1 = new JLabel();
		d1.setForeground(Color.white);
		d1.setFont(font);
		d1.setText(da[0]);
		d1.setBounds(210-x, 120+y, 100, 20);
		d1.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
                d1.setLocation(210-x, 120+y);
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				int x = d1.getX();
                int y = d1.getY();
                d1.setLocation(x + 2, y + 2);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				d1.setForeground(Color.WHITE);
                d1.setLocation(210-x, 120+y);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				d1.setForeground(Color.GREEN);
                d1.setLocation(210-x, 120+y);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
                d1.setLocation(210-x, 120+y);
                getDa(d1.getText());
			}
		});
		add(d1);
		
		d2 = new JLabel();
		d2.setForeground(Color.white);
		d2.setFont(font);
		d2.setText(da[1]);
		d2.setBounds(350-x, 120+y, 100, 20);
		d2.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				d2.setLocation(350-x, 120+y);
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				int x = d2.getX();
                int y = d2.getY();
                d2.setLocation(x + 2, y + 2);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				d2.setForeground(Color.WHITE);
				d2.setLocation(350-x, 120+y);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				d2.setForeground(Color.GREEN);
				d2.setLocation(350-x, 120+y);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				d2.setLocation(350-x, 120+y);
                getDa(d2.getText());
			}
		});
		add(d2);
		
		d3 = new JLabel();
		d3.setForeground(Color.white);
		d3.setFont(font);
		d3.setText(da[2]);
		d3.setBounds(210-x, 155+y, 100, 20);
		d3.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				d3.setLocation(210-x, 155+y);
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				int x = d3.getX();
                int y = d3.getY();
                d3.setLocation(x + 2, y + 2);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				d3.setForeground(Color.WHITE);
				d3.setLocation(210-x, 155+y);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				d3.setForeground(Color.GREEN);
				d3.setLocation(210-x, 155+y);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				d3.setLocation(210-x, 155+y);
                getDa(d3.getText());
			}
		});
		add(d3);
		
		d4 = new JLabel();
		d4.setForeground(Color.white);
		d4.setFont(font);
		d4.setText(da[3]);
		d4.setBounds(350-x, 155+y, 100, 20);
		d4.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				d4.setLocation(350-x, 155+y);
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				int x = d4.getX();
                int y = d4.getY();
                d4.setLocation(x + 2, y + 2);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				d4.setForeground(Color.WHITE);
				d4.setLocation(350-x, 155+y);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				d4.setForeground(Color.GREEN);
				d4.setLocation(350-x, 155+y);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				d4.setLocation(350-x, 155+y);
                getDa(d4.getText());
			}
		});
		add(d4);
	}

	public void initTimer() {
//		text = "床前明月光，疑是地上霜。这首诗的作者在创作时候身高多少？";
		if (timer != null && timer.isRunning()) {
			timer.stop(); // 如果定时器正在运行，先停止
		}
		currentChar = 0; // 重置当前字符计数
		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentChar++;
				if (currentChar >= text.length()) {
					currentChar = text.length();
					timer.stop(); // 停止定时器
				}
				repaint(); // 重绘面板
			}
		});
		timer.start(); // 启动定时器
	}
	
	public ImageIcon icon1 = CutButtonImage.getImage("inkImg/hongmu/答题面板.png", 473, 325);

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(icon1.getImage(), 0, 0, 295, 257, this);
	    String displayedText = text.substring(0, currentChar);
	    int breakIndex = 13; // 设置换行的位置
	    String firstLine, secondLine;

	    if (currentChar > breakIndex) {
	        firstLine = displayedText.substring(0, breakIndex);
	        secondLine = displayedText.substring(breakIndex); // 剩下的字符
	    } else {
	        firstLine = displayedText;
	        secondLine = "";
	    }

	    g.setColor(Color.YELLOW);
	    g.setFont(UIUtils.TEXT_BOLD_FONT);
	    
	    // 显示第一行
	    g.drawString(firstLine, 65, 110); // 调整文本的显示位置
	    // 显示第二行（如果有的话）
	    if (!secondLine.isEmpty()) {
	        g.drawString(secondLine, 65, 130); // 调整第二行的显示位置
	    }
	    if(currentChar >= text.length()) {
	    	d1.setVisible(true);
	    	d2.setVisible(true);
	    	d3.setVisible(true);
	    	d4.setVisible(true);
	    }else {
	    	d1.setVisible(false);
	    	d2.setVisible(false);
	    	d3.setVisible(false);
	    	d4.setVisible(false);
	    }
	}
	
	
	
	public static void getDa(String string) {
		if(StringUtils.isNotEmpty(AnswerMinJframe.getAnswerMinJpanel().NPCID)) {
			if(answer.equals(string)) {
				ZhuFrame.getZhuJpanel().addPrompt("#R恭喜你：#W回答正确！");
				FormsManagement.HideForm(3075);
				SendMessageUntil.toServer(Agreement.getAgreement().userAgreement("我来沾沾福气="+AnswerMinJframe.getAnswerMinJpanel().NPCID));
				AnswerMinJframe.getAnswerMinJpanel().NPCID = "";
			}else {
				ZhuFrame.getZhuJpanel().addPrompt("#W回答错误！");
				FormsManagement.HideForm(3075);
			}
		}
	}

	public void getWt() {
		int value = Util.random.nextInt(AnswerEnum.values().length);
		text = AnswerEnum.getTopic(value);
		da = AnswerEnum.getOption(value).split(",");
		answer = AnswerEnum.getAnswer(value);
		d1.setText(da[0]);
		d2.setText(da[1]);
		d3.setText(da[2]);
		d4.setText(da[3]);


	}
}
