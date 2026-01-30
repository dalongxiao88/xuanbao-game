package org.come.Jpanel.spot.commodity;

import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.tool.Stall.CommodityBean;
import java.util.List;
import org.come.Jpanel.spot.stall.SpotStallBaseJpanel;
import java.awt.Dimension;
import org.come.Jpanel.spot.buy.SpotBuyBaseJpanel;
import org.come.Jpanel.spot.SpotJpanel;
import javax.swing.JPanel;

public class SpotShowJpanel extends JPanel
{
    private int type;
    private SpotCommodityShowJpanel[] spotShowJpanels;
    private SpotJpanel spotJpanel;
    
    public SpotShowJpanel(SpotBuyBaseJpanel spotBuyBaseJpanel, int type) {
        this.setLayout(null);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(612, 338));
        this.spotShowJpanels = new SpotCommodityShowJpanel[24];
        this.type = type;
        this.spotJpanel = spotBuyBaseJpanel;
    }
    
    public SpotShowJpanel(SpotStallBaseJpanel spotStallBaseJpanel, int type) {
        this.setLayout(null);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(612, 338));
        this.spotShowJpanels = new SpotCommodityShowJpanel[24];
        this.type = type;
        this.spotJpanel = spotStallBaseJpanel;
    }
    
    public void updateCommoditys(List<CommodityBean> list, int pageNumber) {
        this.selectCommodity(0, -2);
        if (list != null) {
            CommodityBean selectCommodity = this.getSelectCommodity(pageNumber);
            for (int i = 0; i < this.spotShowJpanels.length; ++i) {
                int index = (pageNumber - 1) * 24 + i;
                CommodityBean commodity = (list.size() > index) ? ((CommodityBean)list.get(index)) : null;
                if (commodity != null) {
                    if (this.spotShowJpanels[i] == null) {
                        (this.spotShowJpanels[i] = new SpotCommodityShowJpanel(commodity, i, this.type)).addMouseListener(new CommodityListener(i));
                        this.spotShowJpanels[i].getHeadLab().addMouseListener(new HeadListener(i));
                        this.add(this.spotShowJpanels[i]);
                    }
                    else {
                        this.spotShowJpanels[i].setVisible(true);
                        if (selectCommodity != null && selectCommodity.getCommodityId().compareTo(commodity.getCommodityId()) == 0) {
                            this.spotShowJpanels[i].setSelect(true);
                        }
                        else {
                            this.spotShowJpanels[i].setSelect(false);
                        }
                        this.spotShowJpanels[i].setCommodityBean(commodity);
                        if (this.spotShowJpanels[i].isSelect()) {
                            this.selectCommodity(i, -1);
                        }
                    }
                }
                else if (this.spotShowJpanels[i] != null) {
                    this.spotShowJpanels[i].setCommodityBean(null);
                    this.spotShowJpanels[i].setVisible(false);
                    this.spotShowJpanels[i].setSelect(false);
                }
                else {
                    break;
                }
            }
        }
    }
    
    private CommodityBean getSelectCommodity(int pageNumber) {
        for (int i = 0; i < this.spotShowJpanels.length; ++i) {
            if (this.spotShowJpanels[i] != null && this.spotShowJpanels[i].isSelect()) {
                return this.spotShowJpanels[i].getCommodityBean();
            }
        }
        return null;
    }
    
    private void selectCommodity(int index, int buttonType) {
        if (this.spotJpanel instanceof SpotBuyBaseJpanel) {
            SpotBuyBaseJpanel spotBuyBaseJpanel = (SpotBuyBaseJpanel)this.spotJpanel;
            if (buttonType == -2) {
                spotBuyBaseJpanel.setCurrentCommodity(null, 0);
                return;
            }
            CommodityBean commodity = this.spotShowJpanels[index].getCommodityBean();
            if (buttonType == -1) {
                spotBuyBaseJpanel.setCurrentCommodity(commodity, spotBuyBaseJpanel.getNumber());
            }
            else if (buttonType == 1) {
                spotBuyBaseJpanel.setCurrentCommodity(commodity);
            }
            else if (buttonType == 3) {
                spotBuyBaseJpanel.setCurrentCommodity(commodity, commodity.getSum());
            }
        }
        else if (this.spotJpanel instanceof SpotStallBaseJpanel) {
            SpotStallBaseJpanel stallBaseJpanel = (SpotStallBaseJpanel)this.spotJpanel;
            if (buttonType == -2) {
                stallBaseJpanel.setCurrentCommodity(null, 0);
                return;
            }
            CommodityBean commodity = this.spotShowJpanels[index].getCommodityBean();
            if (buttonType == -1) {
                stallBaseJpanel.setCurrentCommodity(commodity, stallBaseJpanel.getNumber());
            }
            else if (buttonType == 1) {
                stallBaseJpanel.setCurrentCommodity(commodity);
            }
            else if (buttonType == 3) {
                stallBaseJpanel.setCurrentCommodity(commodity, commodity.getSum());
                stallBaseJpanel.withdraw();
            }
        }
    }
    
    private class CommodityListener extends MouseAdapter
    {
        protected int index;
        
        public CommodityListener(int index) {
            this.index = index;
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            SpotShowJpanel.this.selectCommodity(this.index, e.getButton());
            for (int i = 0; i < SpotShowJpanel.this.spotShowJpanels.length; ++i) {
                if (SpotShowJpanel.this.spotShowJpanels[i] != null) {
                    SpotShowJpanel.this.spotShowJpanels[i].setSelect(i == this.index);
                }
            }
        }
    }
    
    private class HeadListener extends CommodityListener
    {
        public HeadListener(int index) {
            super(index);
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            CommodityBean commodityBean = SpotShowJpanel.this.spotShowJpanels[this.index].getCommodityBean();
            if (commodityBean.getType() == 0 || commodityBean.getType() == 3) {
                ZhuFrame.getZhuJpanel().creatgoodtext(commodityBean.getGoods());
            }
            else if (commodityBean.getType() == 1) {
                ZhuFrame.getZhuJpanel().creatpettext(commodityBean.getPet());
            }
            else if (commodityBean.getType() == 2) {
                ZhuFrame.getZhuJpanel().creatlingtext(commodityBean.getLingbao());
            }
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            ZhuFrame.getZhuJpanel().cleargoodtext();
            ZhuFrame.getZhuJpanel().pettext();
            ZhuFrame.getZhuJpanel().clearlingtext();
        }
    }
}
