package com.tool.btn;

import java.awt.event.MouseEvent;
import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.spot.SpotBoxJframe;
import org.come.Frame.PawnJfram;
import org.come.Frame.AlchemyJframe;
import org.come.Frame.RuneOperateJframe;
import org.come.Frame.TransJframe;
import org.come.Frame.CollectionJadeJframe;
import org.come.Frame.PetEquipmentJframe;
import org.come.Frame.ExchangeValueJframe;
import org.come.Frame.WorkshopRefiningJframe;
import org.come.Frame.TradeJframe;
import org.come.Frame.GiveJframe;
import org.come.Frame.ForgeJframe;
import org.come.Frame.TestpackJframe;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.Jpanel.spot.stall.SpotStallSellJpanel;
import org.come.Jpanel.PawnJfpanel;
import org.come.Jpanel.RuneOperateJpanel;
import org.come.Jpanel.AlchemyJpanel;
import org.come.Jpanel.TransJpanel;
import org.come.Jpanel.RefiningEquiJpanel;
import org.come.Jpanel.PetEquipmentJpanel;
import org.come.Jpanel.CollectionJadeJpanel;
import org.come.Jpanel.ExchangeValueJpanel;
import org.come.Jpanel.RefinersJpanel;
import org.come.Jpanel.TradeJpanel;
import org.come.Jpanel.GiveJpanel;
import org.come.Jpanel.ForgeJpanel;
import org.come.Jpanel.TestpackJapnel;
import javax.swing.JPanel;

public class goodbtn extends MoBanBtn
{
    private JPanel jpanel;
    private int path;
    
    public goodbtn(String iconpath, int type, JPanel jpanel, int path) {
        super(iconpath, type);
        this.jpanel = jpanel;
        this.path = path;
    }
    
    public void dianjisz(int leixing) {
        this.btnchange(this.type = 2);
        if (this.jpanel instanceof TestpackJapnel) {
            TestpackJapnel testpackJapnel = (TestpackJapnel)this.jpanel;
            BtnUtil.btnBinding(testpackJapnel.getBtnrights(), this.path);
        }
        else if (this.jpanel instanceof ForgeJpanel) {
            ForgeJpanel testpackJapnel2 = (ForgeJpanel)this.jpanel;
            BtnUtil.btnBinding(testpackJapnel2.getBtnrights(), this.path);
        }
        else if (this.jpanel instanceof GiveJpanel) {
            GiveJpanel testpackJapnel3 = (GiveJpanel)this.jpanel;
            BtnUtil.btnBinding(testpackJapnel3.getBtnrights(), this.path);
        }
        else if (this.jpanel instanceof TradeJpanel) {
            TradeJpanel testpackJapnel4 = (TradeJpanel)this.jpanel;
            BtnUtil.btnBinding(testpackJapnel4.getBtnrights(), this.path);
        }
        else if (this.jpanel instanceof RefinersJpanel) {
            RefinersJpanel refinersJpanel = (RefinersJpanel)this.jpanel;
            BtnUtil.btnBinding(refinersJpanel.getBtnrights(), this.path);
        }
        else if (this.jpanel instanceof ExchangeValueJpanel) {
            ExchangeValueJpanel valueJpanel = (ExchangeValueJpanel)this.jpanel;
            BtnUtil.btnBinding(valueJpanel.getBtnrights(), this.path);
        }
        else if (this.jpanel instanceof CollectionJadeJpanel) {
            CollectionJadeJpanel collectionJadeJpanel = (CollectionJadeJpanel)this.jpanel;
            BtnUtil.btnBinding(collectionJadeJpanel.getBtnrights(), this.path);
        }
        else if (this.jpanel instanceof PetEquipmentJpanel) {
            PetEquipmentJpanel equipmentJpanel = (PetEquipmentJpanel)this.jpanel;
            BtnUtil.btnBinding(equipmentJpanel.getBtnrights(), this.path);
        }
        else if (this.jpanel instanceof RefiningEquiJpanel) {
            RefiningEquiJpanel equiJpanel = (RefiningEquiJpanel)this.jpanel;
            BtnUtil.btnBinding(equiJpanel.getBtnrights(), this.path);
        }
        else if (this.jpanel instanceof TransJpanel) {
            TransJpanel transJpanel = (TransJpanel)this.jpanel;
            BtnUtil.btnBinding(transJpanel.getBtnrights(), this.path);
        }
        else if (this.jpanel instanceof AlchemyJpanel) {
            AlchemyJpanel alchemyJpanel = (AlchemyJpanel)this.jpanel;
            BtnUtil.btnBinding(alchemyJpanel.getBtnrights(), this.path);
        }
        else if (this.jpanel instanceof RuneOperateJpanel) {
            RuneOperateJpanel runeOperateJpanel = (RuneOperateJpanel)this.jpanel;
            BtnUtil.btnBinding(runeOperateJpanel.getBtnrights(), this.path);
        }
        else if (this.jpanel instanceof PawnJfpanel) {
            PawnJfpanel pawnJfpanel = (PawnJfpanel)this.jpanel;
            BtnUtil.btnBinding(pawnJfpanel.getBtnrights(), this.path);
        }
        else if (this.jpanel instanceof GiveJpanel) {
            GiveJpanel giveJpanel = (GiveJpanel)this.jpanel;
            BtnUtil.btnBinding(giveJpanel.getBtnrights(), this.path);
        }
        else if (this.jpanel instanceof SpotStallSellJpanel) {
            SpotStallSellJpanel giveJpanel2 = (SpotStallSellJpanel)this.jpanel;
            BtnUtil.btnBinding(giveJpanel2.getBtnRights(), this.path);
        }
    }
    
    @Override
    public void chooseyes() {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        TestpackJframe.getTestpackJframe().getJpac().getBtnrights()[this.path].dianjisz(2);
        ForgeJframe.getForgejframe().getJpanel().getBtnrights()[this.path].dianjisz(2);
        GiveJframe.getGivejframe().getGivejpanel().getBtnrights()[this.path].dianjisz(2);
        TradeJframe.getTradejframe().getTradejpanel().getBtnrights()[this.path].dianjisz(2);
        if (!configure.getLzjskg().equals("3")) {
            WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getRefinersJpanel().getBtnrights()[this.path].dianjisz(2);
        }
        ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().getBtnrights()[this.path].dianjisz(2);
        PetEquipmentJframe.getPetEquipmentJframe().getEquipmentJpanel().getBtnrights()[this.path].dianjisz(2);
        CollectionJadeJframe.getCollectionJadeJframe().getJadeJpanel().getBtnrights()[this.path].dianjisz(2);
        WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel().getBtnrights()[this.path].dianjisz(2);
        TransJframe.getTransJframe().getTransJpanel().getBtnrights()[this.path].dianjisz(2);
        RuneOperateJframe.getRuneOperateJframe().getOperateJpanel().getBtnrights()[this.path].dianjisz(2);
        AlchemyJframe.getAlchemyjframe().getalchemyJpanel().getBtnrights()[this.path].dianjisz(2);
        PawnJfram.getPawnJfram().getPawnjpanel().getBtnrights()[this.path].dianjisz(2);
        GiveJframe.getGivejframe().getGivejpanel().getBtnrights()[this.path].dianjisz(2);
        SpotBoxJframe.getSpotBoxJframe().getSpotBoxJpanel().getSpotSellBoxJpanel().getBtnRights()[this.path].dianjisz(2);
        GoodsListFromServerUntil.PageNumberChange(this.path);
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
    }
}
