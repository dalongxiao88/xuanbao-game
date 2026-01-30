package org.come.Jpanel;

import com.tool.image.ImageMixDeal;
import com.tool.tcp.Sprite;
import com.tool.tcp.SpriteFactory;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;
import org.come.until.CutButtonImage;

import javax.swing.*;
import java.awt.*;

/**
 * 功绩千秋
 * @author admin
 *
 */
public class AchievemMinJpanel extends JPanel {

	public static int x,w;
	
	public JLabel mcbel,gjbel,msgbel,imgbel;
	
	
    private ImageIcon imgwc = CutButtonImage.getImage("inkImg/hongmu/完成.png", 55, 45);

	private static Sprite triple = SpriteFactory.VloadSprite("resource/mouse/AB88DE4F.tcp", null);
	public AchievemMinJpanel() {

		this.setPreferredSize(new Dimension(345, 81));
		this.setLayout(null);
		this.setBackground(UIUtils.Color_BACK);

		Font font = new Font("楷体", 1, 16);
		
		mcbel = new JLabel();
		mcbel.setFont(font);
		mcbel.setForeground(Color.WHITE);
		add(mcbel);
		gjbel = new JLabel();
		gjbel.setFont(font);
		if(MyIsif.getStyle().equals("水墨")){
			mcbel.setForeground(Color.black);
			gjbel.setForeground(Color.WHITE);
		}else{
			mcbel.setForeground(Color.YELLOW);
			gjbel.setForeground(Color.WHITE);
		}

		add(gjbel);
		font = new Font("楷体", 0, 16);
		msgbel = new JLabel();
		msgbel.setFont(font);
		if(MyIsif.getStyle().equals("水墨")){
			msgbel.setForeground(Color.black);
		}else {
			msgbel.setForeground(Color.WHITE);
		}
		add(msgbel);
	}

	public ImageIcon icon1 = CutButtonImage.getImage("inkImg/hongmu/gjxx.png", 345, 81);
	public ImageIcon icon2 = CutButtonImage.getImage("inkImg/background/gjxx.png", 345, 81);

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(MyIsif.getStyle().equals("水墨")){
			g.drawImage(icon2.getImage(), x, 0, w, 81, this);
			int x = 300;
			if (gjbel.getText()!=null) {
				if (gjbel.getText().length()>3) {
					x=300-gjbel.getText().length();
				}
			}
			gjbel.setBounds(x, 2, 100, 30);
			g.setColor(Color.BLACK);
			g.drawLine(80, 40, 320, 40);
		}else{
			g.drawImage(icon1.getImage(), x, 0, w, 81, this);
			int x = 300;
			if (gjbel.getText()!=null) {
				if (gjbel.getText().length()>3) {
					x=300-gjbel.getText().length();
				}
			}
			gjbel.setBounds(x, 9, 100, 30);
			g.setColor(Color.gray);
			g.drawLine(80, 40, 320, 40);
		}
		mcbel.setBounds(80, 15, 200, 20);

		msgbel.setBounds(80, 45, 300, 20);
//		imgbel.setBounds(15, 15, 56, 50);
		g.drawImage(imgwc.getImage(), 210, 20, this);
		triple.updateToTime(ImageMixDeal.userimg.getTime(), 0);
		triple.draw(g, 43, 69);
	}

	public JLabel getMcbel() {
		return mcbel;
	}

	public void setMcbel(JLabel mcbel) {
		this.mcbel = mcbel;
	}

	public JLabel getGjbel() {
		return gjbel;
	}

	public void setGjbel(JLabel gjbel) {
		this.gjbel = gjbel;
	}

	public JLabel getMsgbel() {
		return msgbel;
	}

	public void setMsgbel(JLabel msgbel) {
		this.msgbel = msgbel;
	}

}
