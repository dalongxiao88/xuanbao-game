package org.come.mouslisten;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.HashMap;

import javax.swing.ImageIcon;

import org.come.Frame.TestpackJframe;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.MyOptionalJpanel;
import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;
import org.come.until.UserMessUntil;
/**
 * 自选礼包
 * 
 * @author admin
 *
 */
public class MyOptionalMouslisten implements MouseListener {

    private int index;
    private MyOptionalJpanel myOptionalJpanel;
    private int type;

    public MyOptionalMouslisten(int index, MyOptionalJpanel myOptionalJpanel, int type) {
        this.index = index;
        this.myOptionalJpanel = myOptionalJpanel;
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
        Goodstable goodstable = MyOptionalJpanel.goodsList.get(this.index);
        if (goodstable!=null) {

                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);

        }
    }

    public void mouseExited(MouseEvent e) {
    	ZhuFrame.getZhuJpanel().cleargoodtext();
    	ZhuFrame.getZhuJpanel().pettext();
    }
    
    
    
    
    
    
    
}
