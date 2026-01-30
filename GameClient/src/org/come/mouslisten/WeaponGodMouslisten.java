package org.come.mouslisten;

import org.come.Frame.ZhuFrame;
import org.come.Jpanel.WeaponGodJpanel;
import org.come.entity.Goodstable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//获得神兵展示
public class WeaponGodMouslisten implements MouseListener {

    private int index;
    private WeaponGodJpanel weaponGodJpanel;
    private int type;

    public WeaponGodMouslisten(int index, WeaponGodJpanel weaponGodJpanel, int type) {
        this.index = index;
        this.weaponGodJpanel = weaponGodJpanel;
        this.type = type;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 左键点击
        if (e.getButton() == MouseEvent.BUTTON1) {
        	
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
        	
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
//    	System.out.println("点击");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

//    @Override
//    public void mouseEntered(MouseEvent e) {
//    	System.out.println("指上去");
//    	
////    	JLabel s = (JLabel) e.getSource();
//    }

    public void mouseEntered(MouseEvent e) {
        Goodstable goodstable = WeaponGodJpanel.goods;
        if (goodstable != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }

    }

    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
}
