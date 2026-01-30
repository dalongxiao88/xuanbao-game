package org.come.Jpanel.spot;

import java.awt.event.MouseEvent;
import com.tool.btn.MoBanBtn;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import com.tool.Stall.CommodityBean;
import javax.swing.JPanel;

public abstract class SpotJpanel extends JPanel
{
    private SwitchBtn[] switchBtns;
    private SpotBox spotBox;
    
    public SpotJpanel(SpotBox spotBox, String type) {
        this.setLayout(null);
        this.setOpaque(false);
        this.spotBox = spotBox;
        this.switchBtns = this.initSwitchBtns(type);
        for (int i = 0; i < this.switchBtns.length; ++i) {
            this.add(this.switchBtns[i]);
        }
    }
    
    protected abstract SwitchBtn[] initSwitchBtns(String p0);
    
    public abstract void updateCommoditys();
    
    public void setCurrentCommodity(CommodityBean commodity) {
        this.setCurrentCommodity(commodity, 1);
    }
    
    public void setCurrentCommodity(CommodityBean commodity, int sum) {
    }
    
    public void showSelelctBorder(int type, int index) {
    }
    
    public void showEnteredBorder(int type, int index) {
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D)g).setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
    }
    
    protected class SwitchBtn extends MoBanBtn
    {
        private String panelName;
        
        public SwitchBtn(String iconpath, int type, String panelName) {
            super(iconpath, type);
            this.panelName = panelName;
        }
        
        @Override
        public void chooseyes() {
        }
        
        @Override
        public void chooseno() {
        }
        
        @Override
        public void nochoose(MouseEvent e) {
            SpotJpanel.this.spotBox.cardLayoutShow(this.panelName);
        }
    }
}
