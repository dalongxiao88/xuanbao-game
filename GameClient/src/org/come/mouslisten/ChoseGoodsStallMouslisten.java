package org.come.mouslisten;

import com.tool.Stall.Commodity;
import org.come.model.Lingbao;
import com.tool.time.Limit;
import com.tool.time.TimeLimit;
import org.come.Jpanel.ZhuJpanel;
import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import org.come.Jpanel.spot.stall.SpotStallSellJpanel;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import org.come.Jpanel.BoothBoxJpanel;
import java.awt.event.MouseListener;

public class ChoseGoodsStallMouslisten implements MouseListener
{
    private final int type;
    private final int index;
    private BoothBoxJpanel boxJpanel;
    private Goodstable goodstable;
    private RoleSummoning roleSummoning;
    private SpotStallSellJpanel spotStallSellJpanel;
    
    public ChoseGoodsStallMouslisten(int index, int type, SpotStallSellJpanel spotStallSellJpanel) {
        this.type = type;
        this.index = index;
        this.spotStallSellJpanel = spotStallSellJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.spotStallSellJpanel.getIndex() == 0) {
            RoleSummoning pet = this.spotStallSellJpanel.getPet(this.index);
            if (!pet.getQuality().equals("2")) {
                ZhuFrame.getZhuJpanel().addPrompt("#R提示：#Y召唤兽#G【" + pet.getSummoningname() + "】#Y禁止交易！！！");
                return;
            }
            if (pet.getPetlock() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt("召唤兽" + pet.getSummoningname() + "已被加锁，不可摆摊！！");
                return;
            }
            if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && pet.getSid().compareTo(RoleData.getRoleData().getLoginResult().getSummoning_id()) == 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("该召唤兽在参战中，不可摆摊！");
                return;
            }
            if (ZhuJpanel.getPetMount(pet.getSid()) != null) {
                ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽被管制中，不可摆摊！！！");
                return;
            }
            Limit limit = TimeLimit.getLimits().getLimit("枯荣丹");
            if (limit != null && limit.getValue().equals(pet.getSid().toString())) {
                ZhuFrame.getZhuJpanel().addPrompt2("召唤兽已使用枯荣丹，不可摆摊！！！");
                return;
            }
            if (pet != null) {
                this.spotStallSellJpanel.setCurrentCommodity(this.spotStallSellJpanel.getCommodity(pet));
                this.spotStallSellJpanel.showSelelctBorder(1, this.index);
                if (e.getButton() == 3) {
                    this.spotStallSellJpanel.listing();
                }
            }
        }
        else if (this.spotStallSellJpanel.getIndex() == 1) {
            Lingbao bao = this.spotStallSellJpanel.getBao(this.index);
            if (bao != null && bao.getEquipment() != 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你的这个灵宝还在装备中，不可摆摊！！！");
                return;
            }
            if (bao != null) {
                this.spotStallSellJpanel.setCurrentCommodity(this.spotStallSellJpanel.getCommodity(bao));
                this.spotStallSellJpanel.showSelelctBorder(2, this.index);
                if (e.getButton() == 3) {
                    this.spotStallSellJpanel.listing();
                }
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Goodstable good = null;
        RoleSummoning pet = null;
        Lingbao bao = null;
        if (this.boxJpanel != null) {
            Commodity commodity = this.boxJpanel.getStall().getCommodity(this.type, this.index);
            if (commodity != null) {
                good = commodity.getGood();
                pet = commodity.getPet();
            }
        }
        else if (this.spotStallSellJpanel != null) {
            if (this.spotStallSellJpanel.getIndex() == 0) {
                pet = this.spotStallSellJpanel.getPet(this.index);
                if (pet != null) {
                    this.spotStallSellJpanel.showEnteredBorder(1, this.index);
                }
            }
            else if (this.spotStallSellJpanel.getIndex() == 1) {
                bao = this.spotStallSellJpanel.getBao(this.index);
                if (bao != null) {
                    this.spotStallSellJpanel.showEnteredBorder(2, this.index);
                }
            }
        }
        if (good != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(good);
        }
        else if (pet != null) {
            ZhuFrame.getZhuJpanel().creatpettext(pet);
        }
        else if (bao != null) {
            ZhuFrame.getZhuJpanel().creatlingtext(bao);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (this.spotStallSellJpanel != null) {
            if (this.spotStallSellJpanel.getIndex() == 0) {
                ZhuFrame.getZhuJpanel().pettext();
                this.spotStallSellJpanel.showEnteredBorder(1, -1);
            }
            else if (this.spotStallSellJpanel.getIndex() == 1) {
                ZhuFrame.getZhuJpanel().clearlingtext();
                this.spotStallSellJpanel.showEnteredBorder(2, -1);
            }
        }
        else if (this.type == 0) {
            ZhuFrame.getZhuJpanel().cleargoodtext();
        }
        else if (this.type == 1) {
            ZhuFrame.getZhuJpanel().pettext();
        }
        else if (this.type == 2) {
            ZhuFrame.getZhuJpanel().clearlingtext();
        }
    }
    
    public Goodstable getGoodstable() {
        return this.goodstable;
    }
    
    public void setGoodstable(Goodstable goodstable) {
        this.goodstable = goodstable;
    }
    
    public RoleSummoning getRoleSummoning() {
        return this.roleSummoning;
    }
    
    public void setRoleSummoning(RoleSummoning roleSummoning) {
        this.roleSummoning = roleSummoning;
    }
}
