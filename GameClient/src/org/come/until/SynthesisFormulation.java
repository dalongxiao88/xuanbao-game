package org.come.until;

import java.math.BigDecimal;
import com.tool.btn.DazaoBtn;
import java.util.Objects;
import org.come.Jpanel.ForgeJpanel;
import javax.swing.Icon;
import org.come.Frame.ForgeJframe;
import org.come.entity.Goodstable;
import org.come.mouslisten.TransmuteMouslisten;

public class SynthesisFormulation
{
    private static SynthesisFormulation synthesisFormulation;
    
    public static SynthesisFormulation getSynthesisFormulation() {
        if (SynthesisFormulation.synthesisFormulation == null) {
            SynthesisFormulation.synthesisFormulation = new SynthesisFormulation();
        }
        return SynthesisFormulation.synthesisFormulation;
    }
    
    public static void type(String type, TransmuteMouslisten transmuteMouslisten) {
        if (type != null) {
            if (type.equals("我要合成仙器")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_1(transmuteMouslisten, TransmuteMouslisten.goods2);
            }
            else if (type.equals("我要升级仙器")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_2(transmuteMouslisten, TransmuteMouslisten.goods2);
            }
            else if (type.equals("我要分解仙器")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_2222(transmuteMouslisten, TransmuteMouslisten.goods2);
            }
            else if (type.equals("洗炼仙器-超级悔梦")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_33(transmuteMouslisten, TransmuteMouslisten.goods2);
            }
            else if (type.equals("重铸仙器-悔梦石")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_3(transmuteMouslisten, TransmuteMouslisten.goods2);
            }
            else if (type.equals("改变仙器模型")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_34(transmuteMouslisten, TransmuteMouslisten.goods2);
            }
            else if (type.equals("打造11-16级装备")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_4(transmuteMouslisten, TransmuteMouslisten.goods2);
            }
            else if (type.equals("我要打造普通装备")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_5(transmuteMouslisten, TransmuteMouslisten.goods2);
            }
            else if (type.equals("我要升级神兵")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_6(transmuteMouslisten, TransmuteMouslisten.goods2);
            }
            else if (type.equals("我要合成炼妖石")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_7(transmuteMouslisten, TransmuteMouslisten.goods2);
            }
            else if (type.equals("我要培养饰品")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_9(transmuteMouslisten, TransmuteMouslisten.goods2);
            }
            else if (type.equals("我要重铸饰品")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_10(transmuteMouslisten, TransmuteMouslisten.goods2);
            } else if (type.equals("我要合成玄印")) {
                synthesisFormulation.GoodItem_xuanyin(transmuteMouslisten, TransmuteMouslisten.goods2);
            }
            else if (type.equals("我要解封神饰")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_35(transmuteMouslisten, TransmuteMouslisten.goods2);
            }
            else if (type.equals("我要合成符石")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_11(transmuteMouslisten, TransmuteMouslisten.goods4);
            }
            else if (type.equals("我要洗练符石")) {
                SynthesisFormulation.synthesisFormulation.GoodItem_12(transmuteMouslisten, TransmuteMouslisten.goods3);
            }
            else if (!type.equals("我要上神兵石") && !type.equals("炼化神兵")) {
                if (type.equals("我要培养护身符")) {
                    SynthesisFormulation.synthesisFormulation.GoodItem_15(transmuteMouslisten, TransmuteMouslisten.goods2);
                }
                else if (type.equals("我要重铸护身符")) {
                    SynthesisFormulation.synthesisFormulation.GoodItem_16(transmuteMouslisten, TransmuteMouslisten.goods2);
                }
                else if (type.equals("炼化仙器")) {
                    SynthesisFormulation.synthesisFormulation.GoodItem_17(transmuteMouslisten, TransmuteMouslisten.goods2);
                }
                else if (type.equals("培养彩晶石")) {
                    SynthesisFormulation.synthesisFormulation.GoodItem_18(transmuteMouslisten, TransmuteMouslisten.goods2);
                }
                else if (type.equals("精炼神兵")) {
                    SynthesisFormulation.synthesisFormulation.GoodItem_19(transmuteMouslisten, TransmuteMouslisten.goods2);
                }
            }
            else {
                SynthesisFormulation.synthesisFormulation.GoodItem_14(transmuteMouslisten, TransmuteMouslisten.goods2);
            }
        }
    }

    public void GoodItem_xuanyin(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = goodstable.getType().longValue();
            if (type == 10018L || type == 10012L || type == 10013L || type == 10014L) {
                if (TransmuteMouslisten.goods2[0] == null) {
                    forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                    TransmuteMouslisten.goods2[0] = goodstable;
                } else if (TransmuteMouslisten.goods2[1] == null) {
                    forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                    TransmuteMouslisten.goods2[1] = goodstable;
                }
            }
        } else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon((Icon) null);
        }
    }

    public void GoodItem_1(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (type == 7099L) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (Goodtype.GodEquipment_xian(type)) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon((Icon)null);
        }
    }
    
    public void GoodItem_2(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (type == 7099L) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (Goodtype.Ore(type)) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon((Icon)null);
        }
    }
    
    public void GoodItem_2222(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (type == 923L) {
                this.GoodItem_34(transmuteMouslisten, goods);
                return;
            }
            if (Goodtype.GodEquipment_xian(type)) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (type == 915L) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon((Icon)null);
        }
    }
    
    public void GoodItem_3(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (Goodtype.GodEquipment_xian(type)) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (type == 212L) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon((Icon)null);
        }
    }
    
    public void GoodItem_33(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (type == 923L) {
                this.GoodItem_34(transmuteMouslisten, goods);
                return;
            }
            if (Goodtype.GodEquipment_xian(type)) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (type == 915L) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon((Icon)null);
        }
    }
    
    public void GoodItem_34(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (Goodtype.GodEquipment_xian(type) && Goodtype.Weapons(type)) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (type == 923L) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon(null);
        }
    }
    
    public void GoodItem_4(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (Goodtype.OrdinaryEquipment(type)) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (Goodtype.Ore(type)) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon((Icon)null);
        }
    }
    
    public void GoodItem_5(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (Goodtype.OrdinaryEquipment(type)) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (Goodtype.Ore(type)) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon((Icon)null);
        }
    }
    
    public void GoodItem_6(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (Goodtype.GodEquipment_God(type)) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (Goodtype.Ore(type)) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon((Icon)null);
        }
    }
    
    public void GoodItem_7(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            String Value = goodstable.getValue();
            if (Goodtype.ExerciseMonsterOre(type) && goods[0] == null) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (Goodtype.ExerciseMonsterOre(type) && (long)goods[0].getType() == type && goods[0] != goodstable && Objects.equals(goods[0].getValue(), Value)) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon((Icon)null);
        }
    }
    
    public void GoodItem_9(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (Goodtype.Accessories(type) && goods[0] == null) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if ((type == 1008L || type == 702L || type == 703L || type == 704L || type == 705L || type == 706L || type == 707L || type == 708L || type == 709L || type == 710L || type == 711L || type == 722L || type == 723L || Goodtype.Accessories(type)) && goods[0] != goodstable) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon((Icon)null);
        }
    }
    
    public void GoodItem_10(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (Goodtype.Accessories(type) && goods[0] == null) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (Goodtype.Ore(type)) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon(null);
        }
    }
    
    public void GoodItem_35(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (Goodtype.Accessories(type) && goods[0] == null) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (type == 932L) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon(null);
        }
    }
    
    public void GoodItem_11(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (type != 188L) {
                return;
            }
            int k = 0;
            for (int i = goods.length - 1; i >= 0; --i) {
                if (goods[i] == null) {
                    k = i;
                }
                else if (goodstable == goods[i]) {
                    return;
                }
            }
            if (k == 0) {
                int i = DazaoBtn.Numerical(Goodtype.StringParsing(goodstable.getValue())[0]);
                transmuteMouslisten.getRuneOperateJpanel().setMoney(new BigDecimal(1000000 * i));
            }
            TransmuteMouslisten.goods4[k] = goodstable;
            transmuteMouslisten.getRuneOperateJpanel().getGoodsListLabel()[26 + k].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
        }
        else {
            TransmuteMouslisten.goods4[transmuteMouslisten.getGoodPlace() - 26] = null;
            transmuteMouslisten.getRuneOperateJpanel().getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon(null);
        }
    }
    
    public void GoodItem_12(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (type != 188L && type != 1881L) {
                return;
            }
            int k = 0;
            for (int i = goods.length - 1; i >= 0; --i) {
                if (goods[i] == null) {
                    k = i;
                }
                else if (goodstable == goods[i]) {
                    return;
                }
            }
            if (k == 0) {
                transmuteMouslisten.getRuneOperateJpanel().setMoney(new BigDecimal(1000000));
            }
            TransmuteMouslisten.goods3[k] = goodstable;
            transmuteMouslisten.getRuneOperateJpanel().getGoodsListLabel()[24 + k].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
        }
        else {
            TransmuteMouslisten.goods3[transmuteMouslisten.getGoodPlace() - 24] = null;
            transmuteMouslisten.getRuneOperateJpanel().getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon(null);
        }
    }
    
    private void GoodItem_14(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (Goodtype.GodEquipment_God(type)) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (type == 191L) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon(null);
        }
    }
    
    private void GoodItem_15(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (Goodtype.Amulet2(type) && goods[0] == null) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (Goodtype.Amulet2(type) && goodstable != goods[0]) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon(null);
        }
    }
    
    private void GoodItem_16(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (Goodtype.Amulet2(type) && goods[0] == null) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (Goodtype.Ore(type)) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon(null);
        }
    }
    
    private void GoodItem_17(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (Goodtype.GodEquipment_xian(type) && goods[0] == null) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (Goodtype.GodEquipment_Ding(type) && goods[0] == null) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (type == 7005L || Goodtype.GodEquipment_xian(type) || (Goodtype.GodEquipment_Ding(type) && goodstable != goods[0])) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon(null);
        }
    }
    
    private void GoodItem_18(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (type == 729L && goods[0] == null) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if (type == 729L && goodstable != goods[0]) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon(null);
        }
    }
    
    private void GoodItem_19(TransmuteMouslisten transmuteMouslisten, Goodstable[] goods) {
        ForgeJpanel forgejpanel = ForgeJframe.getForgejframe().getForgejpanel();
        if (transmuteMouslisten.getGoodPlace() < 24) {
            Goodstable goodstable = GoodsListFromServerUntil.Getgood(transmuteMouslisten.getGoodPlace());
            long type = (long)goodstable.getType();
            if (Goodtype.GodEquipment_God(type) && goods[0] == null) {
                forgejpanel.getGoodsListLabel()[24].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[0] = goodstable;
            }
            else if ((Goodtype.GodEquipment_God(type) && goodstable != goods[0]) || goodstable.getGoodsid().longValue() == 80042L) {
                forgejpanel.getGoodsListLabel()[25].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                TransmuteMouslisten.goods2[1] = goodstable;
            }
        }
        else {
            TransmuteMouslisten.goods2[transmuteMouslisten.getGoodPlace() - 24] = null;
            forgejpanel.getGoodsListLabel()[transmuteMouslisten.getGoodPlace()].setIcon(null);
        }
    }
    
    static {
        getSynthesisFormulation();
    }
}
