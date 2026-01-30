package org.come.Jpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.come.entity.Goodstable;
import org.come.mouslisten.MyOptionalMouslisten;
import org.come.until.CutButtonImage;
import org.come.until.UserMessUntil;

import com.tool.btn.FormsOnOffBtn;
import com.tool.btn.RoleCaoZuoBtn;
import com.tool.tcp.Sprite;
import com.tool.tcp.SpriteFactory;
import com.tool.tcpimg.UIUtils;
/**
 * 自选礼包
 * @author admin
 *
 */
public class MyOptionalJpanel extends JPanel {

	private static RoleCaoZuoBtn[] purchasebtn,purchasebtn1;//
	public static String msg = "";
	public static JLabel[] goodsname = new JLabel[5];
	public JLabel labGoodsImgsx1;
	public JLabel labGoodsImgsx2;
	public JLabel labGoodsImgsx3;
	public JLabel labGoodsImgsx4;
	public JLabel labGoodsImgsx5;
	private static JTextField findName;
	public static MyOptionalMouslisten[] flipMouslisten = new MyOptionalMouslisten[5];
	public static MyOptionalMouslisten[] flipGoodsMouslisten = new MyOptionalMouslisten[5];
	public static Goodstable goods;
	public static Goodstable zhjgood = new Goodstable();
	public static int it = 4000;
	public static boolean b = false;
	public static boolean start = false;
	public static List<String> godList;
	public static List<Goodstable> goodsList;

	public MyOptionalJpanel() {
		this.setPreferredSize(new Dimension(554,362));
		this.setLayout(null);
		this.setBackground(UIUtils.Color_BACK);
		purchasebtn = new RoleCaoZuoBtn[5];
		Font font = new Font("楷体", 1, 16);
		for(int i=0;i<5;i++) {
			goodsname[i] = new JLabel();
			purchasebtn[i] = new RoleCaoZuoBtn("inkImg/button/自选领取.png", 1, UIUtils.COLOR_BLACK, font, "", 3011+i,this);
			flipMouslisten[i] = new MyOptionalMouslisten(i, this, 1);
			purchasebtn[i].setBounds(90*i+60, 150, 95,33);
			goodsname[i].setBounds(95*i+40, 120, 190,90);
			purchasebtn[i].setVisible(true);
			this.add(purchasebtn[i]);
			if(i == 0){
				labGoodsImgsx1 = new JLabel();
				labGoodsImgsx1.setBounds(90*i+60, 45, 90,90);
				flipGoodsMouslisten[i] = new MyOptionalMouslisten(i, this, 1);
				this.add(labGoodsImgsx1);
			}else if(i == 1){
				labGoodsImgsx2 = new JLabel();
				labGoodsImgsx2.setBounds(90*i+60, 45, 90,90);
				flipGoodsMouslisten[i] = new MyOptionalMouslisten(i, this, 1);
				this.add(labGoodsImgsx2);
			}else if(i == 2){
				labGoodsImgsx3 = new JLabel();
				labGoodsImgsx3.setBounds(90*i+60, 45, 90,90);
				flipGoodsMouslisten[i] = new MyOptionalMouslisten(i, this, 1);
				this.add(labGoodsImgsx3);
			}else if(i == 3){
				labGoodsImgsx4 = new JLabel();
				labGoodsImgsx4.setBounds(90*i+60, 45, 90,90);
				flipGoodsMouslisten[i] = new MyOptionalMouslisten(i, this, 1);
				this.add(labGoodsImgsx4);
			}else {
				labGoodsImgsx5 = new JLabel();
				labGoodsImgsx5.setBounds(90*i+60, 45, 90,90);
				flipGoodsMouslisten[i] = new MyOptionalMouslisten(i, this, 1);
				this.add(labGoodsImgsx5);
			}
		}
		showViewData();
	}

	/**展示物品*/
	public void showViewData(){
		Font font = new Font("楷体",1,14);
		goodsList = new ArrayList<Goodstable>();
		for(int i=0;i<5;i++) {
			Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(godList.get(i)));
			goodsList.add(goodstable);
			goodsname[i].setFont(font);
			goodsname[i].setForeground(UIUtils.COLOR_BLACK[0]);
			goodsname[i].setText(goodstable.getGoodsname());
			if(i == 0){
				labGoodsImgsx1.setIcon(CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 70,70));
				labGoodsImgsx1.addMouseListener(flipGoodsMouslisten[i]);
			}else if(i == 1){
				labGoodsImgsx2.setIcon(CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 70,70));
				labGoodsImgsx2.addMouseListener(flipGoodsMouslisten[i]);
			}else if(i == 2){
				labGoodsImgsx3.setIcon(CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 70,70));
				labGoodsImgsx3.addMouseListener(flipGoodsMouslisten[i]);
			}else if(i == 3){
				labGoodsImgsx4.setIcon(CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 70,70));
				labGoodsImgsx4.addMouseListener(flipGoodsMouslisten[i]);
			}else if(i == 4){
				labGoodsImgsx5.setIcon(CutButtonImage.getImage("img/item/" + goodstable.getSkin() + ".png", 70,70));
				labGoodsImgsx5.addMouseListener(flipGoodsMouslisten[i]);
			}
		}
	}

	private ImageIcon icon = new ImageIcon("inkImg/hongmu/自选底图.png");


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(icon.getImage(), 0, 0, 554, 362, this);
		for(int i=0;i<5;i++) {
			purchasebtn[i].setBounds(80*i+52, 317, 65,23);
			if(i == 0){
				labGoodsImgsx1.setBounds(80*i+50, 230, 90,90);
			}else if(i == 1){
				labGoodsImgsx2.setBounds(80*i+50, 230, 90,90);
			}else if(i == 2){
				labGoodsImgsx3.setBounds(80*i+50, 230, 90,90);
			}else if(i == 3){
				labGoodsImgsx4.setBounds(80*i+50, 230, 90,90);
			}else {
				labGoodsImgsx5.setBounds(80*i+50, 230, 90,90);
			}
		}
	}

	public static JTextField getFindName() {
		return findName;
	}

	public static void setFindName(JTextField findName) {
		MyOptionalJpanel.findName = findName;
	}

	public JLabel getLabGoodsImgsx1() {
		return labGoodsImgsx1;
	}

	public void setLabGoodsImgsx1(JLabel labGoodsImgsx1) {
		this.labGoodsImgsx1 = labGoodsImgsx1;
	}

	public JLabel getLabGoodsImgsx2() {
		return labGoodsImgsx2;
	}

	public void setLabGoodsImgsx2(JLabel labGoodsImgsx2) {
		this.labGoodsImgsx2 = labGoodsImgsx2;
	}

	public JLabel getLabGoodsImgsx3() {
		return labGoodsImgsx3;
	}

	public void setLabGoodsImgsx3(JLabel labGoodsImgsx3) {
		this.labGoodsImgsx3 = labGoodsImgsx3;
	}

	public JLabel getLabGoodsImgsx4() {
		return labGoodsImgsx4;
	}

	public void setLabGoodsImgsx4(JLabel labGoodsImgsx4) {
		this.labGoodsImgsx4 = labGoodsImgsx4;
	}

	public JLabel getLabGoodsImgsx5() {
		return labGoodsImgsx5;
	}

	public void setLabGoodsImgsx5(JLabel labGoodsImgsx5) {
		this.labGoodsImgsx5 = labGoodsImgsx5;
	}
}

