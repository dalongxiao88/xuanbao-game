package com.tool.btn;

import org.come.entity.RoleSummoning;
import org.come.until.AccessNedanMsgUntil;
import com.tool.pet.PetProperty;
import org.come.until.UserMessUntil;
import com.tool.role.GetExp;
import org.come.Frame.NedanJframe;
import org.come.until.FormsManagement;
import org.come.Jpanel.ZhuJpanel;
import java.awt.event.MouseEvent;
import org.come.entity.Goodstable;

public class NeidanBtn extends MoBanBtn
{
    private int flag;
    private Goodstable goodstable;
    
    public NeidanBtn(String iconpath, int type, int flag) {
        super(iconpath, type);
        this.flag = flag;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (this.goodstable != null) {
            ZhuJpanel.setNedangoods(this.goodstable);
            ShowNedanMsg(this.goodstable);
            FormsManagement.upgradForm(47);
        }
    }
    
    public static void ShowNedanMsg(Goodstable goodstable) {
        NedanJframe.getNedanJframe().getNedanJpanel().getLabNedanName().setText(goodstable.getGoodsname());
        String[] strings = goodstable.getValue().split("\\|");
        String[] stringLevel = strings[2].split("\\=");
        String[] stringLevel2 = stringLevel[1].split("\\转");
        String[] stringExp = strings[3].split("\\=");
        NedanJframe.getNedanJframe().getNedanJpanel().getLabLevel().setText((stringLevel2[0].equals("4") ? "点化" : (stringLevel2[0] + "转")) + stringLevel2[1] + "级");
        NedanJframe.getNedanJframe().getNedanJpanel().getLabnumber().setText(stringLevel2[0]);
        NedanJframe.getNedanJframe().getNedanJpanel().getLabExp().setText(stringExp[1]);
        NedanJframe.getNedanJframe().getNedanJpanel().getLabTotalExp().setText(GetExp.getBBNeiExp(Integer.parseInt(stringLevel2[0]), Integer.parseInt(stringLevel2[1]) + 1) + "");
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        int[] pets = PetProperty.getPetHMASp(pet);
        int nddj = Integer.parseInt(stringLevel2[1]);
        int ndzscs = Integer.parseInt(stringLevel2[0]);
        String nedanmsg = AccessNedanMsgUntil.NedanMsg(pet, nddj, ndzscs, goodstable.getGoodsname(), pets[1]);
        NedanJframe.getNedanJframe().getNedanJpanel().getAreaMsg().setText(nedanmsg);
    }
    
    public Goodstable getGoodstable() {
        return this.goodstable;
    }
    
    public void setGoodstable(Goodstable goodstable) {
        this.goodstable = goodstable;
    }
}
