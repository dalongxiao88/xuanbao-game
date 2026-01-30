package org.cbg.frame;

import org.come.until.Util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.come.until.Music;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import com.tool.tcpimg.UIUtils;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.BorderFactory;
import com.updateNew.MyIsif;
import org.come.until.FormsManagement;
import java.math.BigDecimal;
import java.util.List;
import org.cbg.panel.TrslationTreasurepavilionJpanel;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;

public class TrslationMainJframe extends JInternalFrame implements MouseListener
{
    private static long dateTime;
    private TrslationTreasurepavilionJpanel trslationMainJpanel;
    private int first_x;
    private int first_y;
    private int panelOpen;
    private Object shoucangBtn;
    private int xiaoxiGouxuangeshu;
    private int xiaoxiGeshuMax;
    private int goodsGouxuangeshu;
    private int goodsGeshuMax;
    private List<BigDecimal> shoucangWupinList;
    
    public static TrslationMainJframe getTrslationMainJframe() {
        return (TrslationMainJframe)FormsManagement.getInternalForm(78).getFrame();
    }
    
    public TrslationMainJframe() {
        this.panelOpen = 0;
        this.xiaoxiGouxuangeshu = 0;
        this.xiaoxiGeshuMax = 0;
        this.goodsGouxuangeshu = 0;
        this.goodsGeshuMax = 0;
        if (MyIsif.getStyle().equals("水墨")) {
            this.trslationMainJpanel = new TrslationTreasurepavilionJpanel();
            this.getContentPane().add(this.trslationMainJpanel);
            this.setBorder(BorderFactory.createEmptyBorder());
            ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
            this.setBounds(200, 40, 662, 515);
            this.setVisible(false);
            this.setBackground(UIUtils.Color_BACK);
            this.pack();
            this.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseMoved(MouseEvent e) {
                }
                
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (TrslationMainJframe.this.isVisible()) {
                        int x = e.getX() - TrslationMainJframe.this.first_x;
                        int y = e.getY() - TrslationMainJframe.this.first_y;
                        TrslationMainJframe.this.setBounds(x + TrslationMainJframe.this.getX(), y + TrslationMainJframe.this.getY(), TrslationMainJframe.this.getWidth(), TrslationMainJframe.this.getHeight());
                    }
                }
            });
            this.addMouseListener(this);
        }
        else {
            this.trslationMainJpanel = new TrslationTreasurepavilionJpanel();
            this.getContentPane().add(this.trslationMainJpanel);
            this.setBorder(BorderFactory.createEmptyBorder());
            ((BasicInternalFrameUI)this.getUI()).setNorthPane(null);
            this.setBounds(200, 40, 636, 537);
            this.setVisible(false);
            this.setBackground(UIUtils.Color_BACK);
            this.pack();
            this.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseMoved(MouseEvent e) {
                }
                
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (TrslationMainJframe.this.isVisible()) {
                        int x = e.getX() - TrslationMainJframe.this.first_x;
                        int y = e.getY() - TrslationMainJframe.this.first_y;
                        TrslationMainJframe.this.setBounds(x + TrslationMainJframe.this.getX(), y + TrslationMainJframe.this.getY(), TrslationMainJframe.this.getWidth(), TrslationMainJframe.this.getHeight());
                    }
                }
            });
            this.addMouseListener(this);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Music.addyinxiao("关闭窗口.mp3");
        if (e.isMetaDown()) {
            FormsManagement.HideForm(78);
        }
        else {
            FormsManagement.Switchinglevel(78);
        }
        this.first_x = e.getX();
        this.first_y = e.getY();
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public static String remainingTime(Date upTime, Integer number) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.setTime(upTime);
        calendar.add(11, (int)number);
        Date date = calendar.getTime();
        TrslationMainJframe.dateTime = date.getTime();
        int compareTo = date.compareTo(new Date(Util.getTime2()));
        long timeChazhi = date.getTime() - Util.getTime2();
        long day = timeChazhi / 86400000L;
        long hour = timeChazhi / 3600000L - day * 24L;
        long min = timeChazhi / 60000L - day * 24L * 60L - hour * 60L;
        if (compareTo > 0 && (int)number <= 1) {
            return day + "天" + hour + "小时" + min + "分";
        }
        if (compareTo <= 0 && (int)number <= 1) {
            return "已下架";
        }
        long timeChazhi2 = date.getTime() - Util.getTime2();
        long day2 = timeChazhi2 / 86400000L;
        long hour2 = timeChazhi2 / 3600000L - day2 * 24L;
        long min2 = timeChazhi2 / 60000L - day2 * 24L * 60L - hour2 * 60L;
        return day2 + "天" + hour2 + "小时" + min2 + "分";
    }
    
    public TrslationTreasurepavilionJpanel getTrslationMainJpanel() {
        return this.trslationMainJpanel;
    }
    
    public void setTrslationMainJpanel(TrslationTreasurepavilionJpanel trslationMainJpanel) {
        this.trslationMainJpanel = trslationMainJpanel;
    }
    
    public int getPanelOpen() {
        return this.panelOpen;
    }
    
    public void setPanelOpen(int panelOpen) {
        this.panelOpen = panelOpen;
    }
    
    public Object getShoucangBtn() {
        return this.shoucangBtn;
    }
    
    public void setShoucangBtn(Object shoucangBtn) {
        this.shoucangBtn = shoucangBtn;
    }
    
    public int getXiaoxiGouxuangeshu() {
        return this.xiaoxiGouxuangeshu;
    }
    
    public void setXiaoxiGouxuangeshu(int xiaoxiGouxuangeshu) {
        this.xiaoxiGouxuangeshu = xiaoxiGouxuangeshu;
    }
    
    public int getXiaoxiGeshuMax() {
        return this.xiaoxiGeshuMax;
    }
    
    public void setXiaoxiGeshuMax(int xiaoxiGeshuMax) {
        this.xiaoxiGeshuMax = xiaoxiGeshuMax;
    }
    
    public int getGoodsGouxuangeshu() {
        return this.goodsGouxuangeshu;
    }
    
    public void setGoodsGouxuangeshu(int goodsGouxuangeshu) {
        this.goodsGouxuangeshu = goodsGouxuangeshu;
    }
    
    public int getGoodsGeshuMax() {
        return this.goodsGeshuMax;
    }
    
    public void setGoodsGeshuMax(int goodsGeshuMax) {
        this.goodsGeshuMax = goodsGeshuMax;
    }
    
    public List<BigDecimal> getShoucangWupinList() {
        return this.shoucangWupinList;
    }
    
    public void setShoucangWupinList(List<BigDecimal> shoucangWupinList) {
        this.shoucangWupinList = shoucangWupinList;
    }
}
