package org.come.Jpanel;

import com.tool.btn.FormsOnOffBtn;
import com.tool.btn.RoleCaoZuoBtn;
import com.tool.image.ImageMixDeal;
import com.tool.tcp.Sprite;
import com.tool.tcp.SpriteFactory;
import com.tool.tcpimg.UIUtils;
import org.come.entity.Goodstable;
import org.come.mouslisten.WeaponGodMouslisten;

import javax.swing.*;
import java.awt.*;

//获得神兵展示
public class WeaponGodJpanel extends JPanel {

    private static RoleCaoZuoBtn purchasebtn,purchasebtn1;//
    private static FormsOnOffBtn offBtn;//
    public static String msg = "";
    public static JLabel labGoodsImg,labGoodsImgsx;
    public static JLabel labGoodsImgwx,labGoodsImgwsx,yidong;
    private static JTextField findName,findName1;
    public static JLabel goodsname;
    public static WeaponGodMouslisten weaponGodMouslisten;
    public static Goodstable goods;

    public static int x,w;
    public static int himg,wimg,yimg=10;
    public static Sprite tcp = SpriteFactory.VloadSprite("resource/mouse/sb_tx.was", null);

    public WeaponGodJpanel() {

        this.setPreferredSize(new Dimension(466,155));
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        // 关闭按钮事件
        offBtn = new FormsOnOffBtn("inkImg/background/gift-close-btn1.png", 1, 3007);
        offBtn.setBounds(466-35, 10, 18, 18);
        offBtn.setVisible(true);
        this.add(offBtn);

        Font font = new Font("楷体", 1, 16);

        purchasebtn = new RoleCaoZuoBtn("inkImg/hongmu/领取神兵.png", 1, UIUtils.COLOR_BTNPUTONG, font, "  收取", 3008);
        purchasebtn.setBounds(180,110, 70, 35);
        purchasebtn.setVisible(true);
        this.add(purchasebtn);

        purchasebtn1 = new RoleCaoZuoBtn("inkImg/hongmu/领取神兵.png", 1, UIUtils.COLOR_BTNPUTONG, font, "  分享", 3009);
        purchasebtn1.setBounds(280,110, 70, 35);
        purchasebtn1.setVisible(true);
        this.add(purchasebtn1);



        labGoodsImg = new JLabel();
        labGoodsImg.setBounds(51,28,220,220);
        labGoodsImg.setVisible(false);
        weaponGodMouslisten = new WeaponGodMouslisten(0, this, 1);
        labGoodsImg.addMouseListener(weaponGodMouslisten);
        labGoodsImg.setOpaque(false);
        this.add(labGoodsImg);

        goodsname = new JLabel();
        goodsname.setFont(new Font("楷体", Font.BOLD, 22));
        goodsname.setForeground(UIUtils.COLOR_Wing1);
        goodsname.setBounds(280,28, 280, 20);
        goodsname.setText("");
        goodsname.setVisible(false);
        this.add(goodsname);


    }

    /**展示面板*/
    public void showViewData(){


    }

    //    private Image icon = ZipUtils.read(ZipUtils.getInputStream("thefiveelements/S3010.png"));
//    private Image icon = ZipUtils.read(ZipUtils.getInputStream("thefiveelements/S3010.png"));
    private ImageIcon icon = new ImageIcon("inkImg/hongmu/神兵背景1.png");
    public static ImageIcon iconGoods;
//    private ImageIcon icon = CutButtonImage.getImage("inkImg/hongmu/bjS3010.png", 466,155);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(icon.getImage(), x, 0, w,155, this);
        tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
        tcp.draw(g,x+100,120);
//    	tcp.updateToTime(ImageMixDeal.userimg.getTime(), 0);
//    	tcp.draw(g,120,70);
        labGoodsImg.setBounds(x+60,30,80,80);
        g.drawImage(iconGoods.getImage(), x+60, yimg, wimg,himg, this);
        if(x<=5){
            goodsname.setVisible(true);
            purchasebtn.setVisible(true);
            purchasebtn1.setVisible(true);
            offBtn.setVisible(true);
        }else{
            goodsname.setVisible(false);
            purchasebtn.setVisible(false);
            purchasebtn1.setVisible(false);
            offBtn.setVisible(false);
        }
        goodsname.setBounds(230,23, 280, 20);
        g.setColor(Color.gray);
        g.setFont(UIUtils.TEXT_FONT78);
//    	g.drawString("在这茫茫人海中，鸡驴大神与你相逢，", 220, 70);
//    	g.drawString("今天你就是最幸运的仔！", 220, 90);
    }

    public static JTextField getFindName() {
        return findName;
    }

    public static void setFindName(JTextField findName) {
        WeaponGodJpanel.findName = findName;
    }




}

