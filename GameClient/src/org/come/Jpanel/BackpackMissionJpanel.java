package org.come.Jpanel;

import com.tool.btn.FormsOnOffBtn;
import com.tool.role.RoleData;
import com.tool.tcpimg.UIUtils;
import com.updateNew.MyIsif;
import org.apache.commons.lang.StringUtils;
import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;
import org.come.model.Achievement;
import org.come.mouslisten.BackPackMissionMouslisten;
import org.come.until.*;

import javax.swing.*;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 功能道具栏按钮
 * @author admin
 *
 */
public class BackpackMissionJpanel extends JPanel {

	public JLabel[] goodsBel = new JLabel[24];
	public JLabel[] goodnumLab = new JLabel[24];

	public JLabel xuanLabel;
	public static int index = 0;
	public BackpackMissionJpanel() {

		this.setPreferredSize(new Dimension(307, 205));
		this.setLayout(null);
		this.setBackground(UIUtils.Color_BACK);


		for(int i=0;i<24;i++){
			index = i;
			int x = 1 + (i % 6) * 51; // 每行6个，计算x坐标
			int y = 2 + (i / 6) * 51; // 每行6个，计算y坐标

			goodnumLab[i] = new JLabel();
			goodnumLab[i].setBounds(x+5, y+10, 50, 13);
			goodnumLab[i].setVerticalAlignment(SwingConstants.CENTER);
			goodnumLab[i].setForeground(Color.WHITE);
			goodnumLab[i].setFont(new Font("宋体", Font.BOLD, 14).deriveFont(1));
			this.add(goodnumLab[i]);


			goodsBel[i] = new JLabel();
			goodsBel[i].setBounds(x, y, 49, 49);
			if(GoodsListFromServerUntil.goodsBackpackMissionList.size() > i){
				goodsBel[i].setIcon(CutButtonImage.getImage("img/item/"+GoodsListFromServerUntil.goodsBackpackMissionList.get(i)+".png", 49, 49));
			}
			goodsBel[i].addMouseListener(new BackPackMissionMouslisten(i,this,1));
			this.add(goodsBel[i]);

		}
		xuanLabel = new JLabel();
		xuanLabel.setBounds(0, 0, 50, 50);
		xuanLabel.setIcon(new ImageIcon("inkImg/hongmu/XZ.png"));
		xuanLabel.setVisible(false);
		add(xuanLabel);
	}


	public ImageIcon icon2 = CutButtonImage.getImage("inkImg/hongmu/rwbb.png", 307, 205);
	public ImageIcon icon3 = CutButtonImage.getImage("inkImg/background1/rwbb.png", 309, 207);

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        if(!MyIsif.getStyle().equals("水墨")){
            g.drawImage(icon2.getImage(), 0, 0, 307, 205, this);
        }else{
            g.drawImage(icon3.getImage(), 0, 0, 309, 207, this);
        }
		for(int i=0;i<24;i++){
			goodsBel[i].setIcon(null);
			goodnumLab[i].setText("");
			if(GoodsListFromServerUntil.goodsBackpackMissionList.size() > i){
				int x = 1 + (i % 6) * 51; // 每行6个，计算x坐标
				int y = 2 + (i / 6) * 51; // 每行6个，计算y坐标
				goodnumLab[i].setBounds(x, y, 50, 13);
				if(GoodsListFromServerUntil.goodsBackpackMissionList.get(i).getUsetime()>0){
					if(GoodsListFromServerUntil.goodsBackpackMissionList.get(i).getUsetime()>1){
						goodnumLab[i].setText(GoodsListFromServerUntil.goodsBackpackMissionList.get(i).getUsetime()+"");
					}
					goodsBel[i].setIcon(CutButtonImage.getImage("img/item/"+GoodsListFromServerUntil.goodsBackpackMissionList.get(i).getSkin()+".png", 49, 49));
				}
			}
		}
	}

	public JLabel[] getGoodsBel() {
		return goodsBel;
	}

	public void setGoodsBel(JLabel[] goodsBel) {
		this.goodsBel = goodsBel;
	}


	public JLabel getXuanLabel() {
		return xuanLabel;
	}

	public void setXuanLabel(JLabel xuanLabel) {
		this.xuanLabel = xuanLabel;
	}
}