package org.come.Jpanel;

import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import com.tool.tcp.Sprite;
import com.tool.tcp.SpriteFactory;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;
import org.apache.commons.lang.StringUtils;
import org.come.model.Achievement;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.CutButtonImage;
import org.come.until.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

/**
 * 功绩千秋
 * @author admin
 *
 */
public class SpotAchievemShowJpanel extends JPanel {
    private Achievement achievement;
    private JLabel headLab;
    private JLabel priceLab;
    private JLabel lbLab;
    private JLabel jlTextLab;
    private JLabel smTextLab;
    private JLabel taskNameLab;
//    private JLabel imgLab;
    private int type;
    private int index;
    
    private ImageIcon pslb = CutButtonImage.getImage("inkImg/hongmu/pslb.png", 25, 25);
    
    private ImageIcon pslbg = CutButtonImage.getImage("inkImg/hongmu/pslbg.png", 25, 25);
    
//    private ImageIcon imgsd = CutButtonImage.getImage("inkImg/background/S192.png", 56, 50);
    private static Sprite triple0 = SpriteFactory.VloadSprite("resource/mouse/AB88DE4F.tcp", null);//特效路径
    private static Sprite triple1 = SpriteFactory.VloadSprite("resource/mouse/C7E7EE0C.tcp", null);//特效路径
    private static Sprite triple2 = SpriteFactory.VloadSprite("resource/mouse/C722D404.tcp", null);//特效路径
    private static Sprite triple3 = SpriteFactory.VloadSprite("resource/mouse/CE2BAC1B.tcp", null);//特效路径
    private static Sprite triple4 = SpriteFactory.VloadSprite("resource/mouse/D76BEF75.tcp", null);//特效路径
    private static Sprite triple5 = SpriteFactory.VloadSprite("resource/mouse/D0639C94.tcp", null);//特效路径

    private static Sprite triple = SpriteFactory.VloadSprite("resource/mouse/D0639C94.tcp", null);//特效路径
    private ImageIcon imgwc = CutButtonImage.getImage("inkImg/hongmu/完成1.png", 50, 45);//图片大小
    private ImageIcon imgwc1 = CutButtonImage.getImage("inkImg/hongmu/完成.png", 50, 45);

    public String cjjf = "0"; 
    
    
    public SpotAchievemShowJpanel(Achievement achievement, int index, int type) {
        this.setLayout(null);
        this.setOpaque(false);
        headLab = new JLabel();
        priceLab = new JLabel();
        priceLab.setHorizontalAlignment(SwingConstants.CENTER);
        lbLab = new JLabel();
        jlTextLab = new JLabel();
        smTextLab = new JLabel();
        taskNameLab = new JLabel();
//        imgLab = new JLabel();
        this.add(priceLab);
        this.add(lbLab);
        this.add(jlTextLab);
        this.add(smTextLab);
        this.add(taskNameLab);
        init(index, type);
        this.add(headLab);

//        this.add(imgLab);
        setAchievementBean(achievement);
        this.type = type;
        this.index = index;

    }

    private void init(int index, int type) {
        this.setPreferredSize(new Dimension(373, 83));
        this.setBounds(0, 0 + 83 * index, 373 , 83);
        backIcon = new ImageIcon("inkImg/hongmu/gjxx.png");
        headLab.setBounds(6 + 2, 6 + 2, 40, 40);
    }

    public void setAchievementBean(Achievement achievement) {
        int x = Util.random.nextInt(6);
        triple = x==0?triple0:x==1?triple1:x==2?triple2:x==3?triple3:x==4?triple4:x==5?triple5:triple;
    	cjjf = RoleData.getRoleData().getLoginResult().getAchieveRecordtype(achievement.getConditions());
        this.achievement = achievement;
        priceLab.setBounds(300, 10, 100, 30);
        priceLab.setFont(UIUtils.TEXT_FONT33);
        priceLab.setForeground(Color.yellow);
        priceLab.setText(achievement.getPrice());
        taskNameLab.setBounds(80, 9, 200, 20);
        taskNameLab.setFont(new Font("楷体", 1, 18));
        if(MyIsif.getStyle().equals("水墨")){
            priceLab.setForeground(Color.white);//功绩数值颜色
            taskNameLab.setForeground(Color.BLACK);//任务名称颜色
            priceLab.setBounds(333, 1, 45, 30);//标签位置-大小
        }else{
            priceLab.setForeground(Color.white);
            priceLab.setBounds(333, 1, 45, 30);
            taskNameLab.setForeground(UIUtils.COLOR_BTNPUTONG[0]);
        }
        taskNameLab.setText(achievement.getTaskName());
        smTextLab.setBounds(80, 40, 300, 20);
        smTextLab.setFont(new Font("楷体", 0, 16));
        if(!MyIsif.getStyle().equals("水墨")){
            smTextLab.setForeground(Color.white);
        }else{
            smTextLab.setForeground(Color.black);
        }
        //介绍的字体颜色
        smTextLab.setText(achievement.getConditions());
        if (MyIsif.getStyle().equals("水墨") ) {
            jlTextLab.setBounds(20, 63, 300, 20);
            jlTextLab.setFont(new Font("楷体", 1, 16));
            jlTextLab.setForeground(Color.black);//水墨
            jlTextLab.setText(achievement.getDescribeText());
            jlTextLab.setVisible(false);
        }else {
            jlTextLab.setBounds(20, 63, 300, 20);
            jlTextLab.setFont(new Font("楷体", 1, 16));
            jlTextLab.setForeground(Color.orange);//红木
            jlTextLab.setText(achievement.getDescribeText());
            jlTextLab.setVisible(false);
        }

        if(StringUtils.isNotEmpty(achievement.getGoodsId())) {
        	if(cjjf.equals("-1")) {
        		lbLab.setIcon(pslbg);
        	}else {
        		lbLab.setIcon(pslb);
        	}
        	lbLab.setBounds(taskNameLab.getWidth(), 10, 25, 25);
        	lbLab.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	if(!cjjf.equals("-1") && Integer.parseInt(cjjf) == Integer.parseInt(achievement.getNum())) {
                		if (e.getClickCount() == 1) { // 单击事件
                			String sendmes = Agreement.getAgreement().roleAchieveAgreement("领取奖励="+achievement.getConditions());
                			SendMessageUntil.toServer(sendmes);
                			lbLab.setIcon(pslbg);
                		}
                	}
                }
            });
        }else {
        	lbLab.setIcon(null);
        }
  //      imgLab.setIcon(imgsd);
//        imgLab.setBounds(15, 15, 56, 50);
    }

    private ImageIcon icon;
    private ImageIcon backIcon;
    private boolean isSelect; // 是否选中
    private static Sprite tcp = SpriteFactory.VloadSprite("thefiveelements/cj001.was", null); //特效路径
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(MyIsif.getStyle().equals("水墨")){
            if (backIcon != null) {
                g.drawImage(backIcon.getImage(), 2, 2, this);
            }

            triple.updateToTime(ImageMixDeal.userimg.getTime(), 0);
            triple.draw(g, 35, 62);

            if (isSelect) {
                backIcon = new ImageIcon("inkImg/background/gjxx1.png");
                paintCommodityBorder((Graphics2D) g, Color.GREEN, getWidth() - 1, getHeight() - 1);
                jlTextLab.setVisible(true);
            }else {
                backIcon = new ImageIcon("inkImg/background/gjxx.png");
                jlTextLab.setVisible(false);
            }
            g.setColor(Color.BLACK);
            g.drawLine(80, 35, 320, 35);
            g.setColor(Color.black);
        }else{
            if (backIcon != null) {
                g.drawImage(backIcon.getImage(), 2, 2, this);
            }
            triple.updateToTime(ImageMixDeal.userimg.getTime(), 0);
            triple.draw(g, 35, 62);//动画位置

            if (isSelect) {
                backIcon = new ImageIcon("inkImg/hongmu/gjxx1.png");
                paintCommodityBorder((Graphics2D) g, Color.GREEN, getWidth() - 1, getHeight() - 1);
                jlTextLab.setVisible(true);
            }else {
                backIcon = new ImageIcon("inkImg/hongmu/gjxx.png");
                jlTextLab.setVisible(false);
            }
            g.setColor(Color.gray);
            g.drawLine(80, 35, 320, 35);//功绩里面横线位置
            g.setColor(Color.black);
        }
        cjjf = RoleData.getRoleData().getLoginResult().getAchieveRecordtype(achievement.getConditions());
        if((StringUtils.isNotEmpty(cjjf) && Integer.parseInt(cjjf)>=Integer.parseInt(achievement.getNum())) || cjjf.equals("-1")) {
            if(!MyIsif.getStyle().equals("水墨")){//功绩完成这个图片的位置
                g.drawImage(imgwc1.getImage(), 260, 11, this);//红木
            }else{
                g.drawImage(imgwc.getImage(), 260, 11, this);//水墨
            }
        }else {
        	g.setFont(new Font("楷体", 1, 14));
            if(!MyIsif.getStyle().equals("水墨")){
                g.setColor(UIUtils.COLOR_BTNPUTONG[0]);
            }else{
                g.setColor(Color.black);
            }
            g.setFont(new Font("宋体", 0, 14));
        	g.drawString("完成进度："+(cjjf.equals("")?0:cjjf)+"/"+achievement.getNum(), 240, 55);
        }
    } 

    protected void paintCommodityBorder(Graphics2D g2, Color color, int width, int height) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        // 绘制圆角矩形边框
        RoundRectangle2D borderShape = new RoundRectangle2D.Float(0, 0, width, height, 10, 10);
        g2.draw(borderShape);
    }

    public JLabel getHeadLab() {
        return headLab;
    }
    
    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isSelect() {
        return isSelect;
    }

	public Achievement getAchievement() {
		return achievement;
	}

	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public ImageIcon getBackIcon() {
		return backIcon;
	}

	public void setBackIcon(ImageIcon backIcon) {
		this.backIcon = backIcon;
	}

	public static Sprite getTcp() {
		return tcp;
	}

	public static void setTcp(Sprite tcp) {
		SpotAchievemShowJpanel.tcp = tcp;
	}

	public void setHeadLab(JLabel headLab) {
		this.headLab = headLab;
	}
	
}
