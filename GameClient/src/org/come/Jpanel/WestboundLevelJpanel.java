
package org.come.Jpanel;

import com.tool.btn.BjczBtn;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import org.come.action.MapAction;
import org.come.entity.Goodstable;
import org.come.until.CutButtonImage;
import org.come.until.FormsManagement;
import org.come.until.NpcMenuUntil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 八十一难
 * 
 * @author admin
 *
 */
public class WestboundLevelJpanel extends JPanel {
	
	public WestboundLevelSubJpanel westboundLevelSubJpanel;
	public WestboundLevelTextJpanel westboundLevelTextJpanel;
	
	private ImageIcon imgwc = CutButtonImage.getImage("inkImg/hongmu/完成.png", 50, 45);

	private static BjczBtn addBtn,dwBtn,htBtn;

	public static Goodstable goods;

	public static JLabel[] sxmLab,sxzLab,sximgLab;

	public JLabel dqlab; 
	
	public WestboundLevelJpanel() {

		this.setPreferredSize(new Dimension(746, 514));
		this.setLayout(null);
		this.setBackground(UIUtils.Color_BACK);
		westboundLevelTextJpanel = new WestboundLevelTextJpanel();
		westboundLevelTextJpanel.setBounds(420, 350, 315, 112); // 设置 WestboundLevelSubJpanel 的位置和大小
		westboundLevelTextJpanel.setVisible(false);
		westboundLevelTextJpanel.addMouseListener(new MouseListener() {
			
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
				westboundLevelTextJpanel.setVisible(false);
				westboundLevelTextJpanel.GBTS = false;
				FormsManagement.HideForm(3079);
				FormsManagement.HideForm(3000);
				MapAction.npcmenuAction.get(NpcMenuUntil.getMenu48()).menuControl("我要挑战八十一难");
			}
		});
		add(westboundLevelTextJpanel);
		
		westboundLevelSubJpanel = new WestboundLevelSubJpanel();
		westboundLevelSubJpanel.setBounds(90, 70, 600, 280); // 设置 WestboundLevelSubJpanel 的位置和大小
		add(westboundLevelSubJpanel);
		
		
		FormsOnOffBtn offBtn = new FormsOnOffBtn("img/xy2uiimg/8_jpg.button.btn_close.jpg", 1, 3079);
		offBtn.setBounds(261-25, 0, 23, 23);
//		this.add(offBtn);
		
		sxmLab = new JLabel[4];
		sxzLab = new JLabel[4];
		sximgLab = new JLabel[4];
		Font font = new Font("楷体", 1, 16);

		addBtn = new BjczBtn("inkImg/button/32n.png", 1, UIUtils.COLOR_BLACK, "启程", 30791);
		addBtn.setBounds(500, 325, 74, 36);
    	this.add(addBtn);
    	
    	htBtn = new BjczBtn("inkImg/button/32n.png", 1, UIUtils.COLOR_BLACK, "后退", 30792);
    	htBtn.setBounds(300, 325, 74, 36);
    	this.add(htBtn);
    	
    	dwBtn = new BjczBtn("inkImg/button/32n.png", 1, UIUtils.COLOR_BLACK, "当前", 30793);
    	dwBtn.setBounds(400, 325, 74, 36);
    	this.add(dwBtn);
		
    	dqlab = new JLabel();
    	dqlab.setFont(UIUtils.TEXT_FONT22);
    	dqlab.setForeground(Color.white);
    	dqlab.setBounds(697, 275, 40, 40);
    	add(dqlab);
    	
	}

	public ImageIcon icon1 = CutButtonImage.getImage("difficult/bsyndt.png", 746, 514);
	
//	public Image icon1 = ZipUtils.read(ZipUtils.getInputStream("difficult/bsyndt.png"));
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(icon1.getImage(), 0, 0, 746, 514, this);
		dqlab.setText("当前第"+((WestboundLevelSubJpanel.createNum)<10?"0"+(WestboundLevelSubJpanel.createNum):(WestboundLevelSubJpanel.createNum))+"难");
		htBtn.setBounds(250, 400, 74, 36);
		dwBtn.setBounds(350, 400, 74, 36);
		addBtn.setBounds(450, 400, 74, 36);
		dqlab.setFont(UIUtils.TEXT_MSG1);
		dqlab.setForeground(Color.black);
		dqlab.setBounds(345, 45, 340, 40);
	}

	public static void updateWH() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for(int i=0;i<20;i++) {
						if(WestboundLevelSubJpanel.POX<5500) {
							WestboundLevelSubJpanel.POX += 2; 
							Thread.sleep(50);
						}
					}
					return;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
	
	
	public static void updateHT() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for(int i=0;i<50;i++) {
						if(WestboundLevelSubJpanel.POX>6) {
							WestboundLevelSubJpanel.POX -= 2; 
							Thread.sleep(50);
						}
					}
					return;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
	
	//定位
	public static void updateDW() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int xt = (5700/81)*WestboundLevelSubJpanel.createNum -100;
//					int num = xt/4;
					boolean isf = false;
					if (Math.abs(WestboundLevelSubJpanel.POX - xt) <= 10) {
						isf = false;
					} else {
						isf = true;
					}
					if(WestboundLevelSubJpanel.createNum<7) {
						isf = true;
					}
					if(isf) {
						if(WestboundLevelSubJpanel.createNum>7) {
							boolean idssa = false;
							boolean idssb = false;
							while(!idssa || !idssb) {
								for(int i=0;i<xt;i++) {
									if(WestboundLevelSubJpanel.POX<=5600) {
										if(WestboundLevelSubJpanel.POX<xt) {
											WestboundLevelSubJpanel.POX += 5;
											Thread.sleep(3);
											idssa = true;
										}else {
											WestboundLevelSubJpanel.POX -= 5;
											Thread.sleep(3);
											idssb = true;
										}
									}
									if(idssa && idssb) {
										return;
									}
								}
							}
						}else {
							while(WestboundLevelSubJpanel.POX>=4) {
								WestboundLevelSubJpanel.POX -= 4;
								Thread.sleep(3);
							}
						}
					}
					return;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
}
