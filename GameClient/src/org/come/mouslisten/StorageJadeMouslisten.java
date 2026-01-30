package org.come.mouslisten;

import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.JadeUpJpanel;
import org.come.Jpanel.StorageJadeJpanel4;
import org.come.Jpanel.SynthesisJpanel;
import org.come.Jpanel.StorageJadeJpanel3;
import org.come.Jpanel.CollectionJadeJpanel;
import org.come.Frame.CollectionJadeJframe;
import org.come.Jpanel.StorageJadeJpanel2;
import org.come.Jpanel.ExchangeValueJpanel;
import java.math.BigDecimal;
import org.come.until.AccessSuitMsgUntil;
import javax.swing.ImageIcon;
import org.come.Frame.ExchangeValueJframe;
import org.come.Jpanel.StorageJadeJpanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StorageJadeMouslisten implements MouseListener
{
    private int index;
    private int type;
    private String skin;
    private int[] jadeNum;
    
    public StorageJadeMouslisten(int index, int type) {
        this.jadeNum = new int[5];
        this.index = index;
        this.type = type;
        this.skin = "tzyf" + (index + 1);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            if (this.type == 1) {
                if (StorageJadeJpanel.partJade != null) {
                    this.jadeNum[0] = StorageJadeJpanel.partJade.getJade1();
                    this.jadeNum[1] = StorageJadeJpanel.partJade.getJade2();
                    this.jadeNum[2] = StorageJadeJpanel.partJade.getJade3();
                    this.jadeNum[3] = StorageJadeJpanel.partJade.getJade4();
                    this.jadeNum[4] = StorageJadeJpanel.partJade.getJade5();
                    if (this.jadeNum[this.index] > 0) {
                        ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().getLabTzyf().setIcon(new ImageIcon(new ImageIcon("img/item/" + this.skin + ".png").getImage().getScaledInstance(54, 51, 10)));
                        ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().getTextNum().setText("1");
                        ExchangeValueJpanel.khdlxz = new BigDecimal(AccessSuitMsgUntil.returnExcNum(this.index + 1));
                        ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().getJadeorGoodstableBean().setType(this.index + 1);
                        ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().getJadeorGoodstableBean().setGoodstable(null);
                        ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().getJadeorGoodstableBean().setPartJade(StorageJadeJpanel.partJade);
                    }
                    else {
                        ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().getLabTzyf().setIcon(null);
                        ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().getTextNum().setText("");
                        ExchangeValueJpanel.khdlxz = null;
                        ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().getJadeorGoodstableBean().setType(0);
                        ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().getJadeorGoodstableBean().setGoodstable(null);
                        ExchangeValueJframe.getExchangeValueJframe().getValueJpanel().getJadeorGoodstableBean().setPartJade(null);
                    }
                }
            }
            else if (this.type == 2) {
                if (StorageJadeJpanel2.partJade != null) {
                    this.jadeNum[0] = StorageJadeJpanel2.partJade.getJade1();
                    this.jadeNum[1] = StorageJadeJpanel2.partJade.getJade2();
                    this.jadeNum[2] = StorageJadeJpanel2.partJade.getJade3();
                    this.jadeNum[3] = StorageJadeJpanel2.partJade.getJade4();
                    this.jadeNum[4] = StorageJadeJpanel2.partJade.getJade5();
                    if (this.jadeNum[this.index] > 0) {
                        CollectionJadeJframe.getCollectionJadeJframe().getJadeJpanel().getLabTzyf().setIcon(new ImageIcon(new ImageIcon("img/item/" + this.skin + ".png").getImage().getScaledInstance(54, 51, 10)));
                        int num = AccessSuitMsgUntil.getCollNum(StorageJadeJpanel2.partJade.getSuitid());
                        CollectionJadeJpanel.sxlxz = new BigDecimal(50);
                        CollectionJadeJpanel.money = new BigDecimal((num + 1) * 10000000);
                        CollectionJadeJframe.getCollectionJadeJframe().getJadeJpanel().getGoodstableBean().setType(this.index + 1);
                        CollectionJadeJframe.getCollectionJadeJframe().getJadeJpanel().getGoodstableBean().setPartJade(StorageJadeJpanel2.partJade);
                    }
                    else {
                        CollectionJadeJframe.getCollectionJadeJframe().getJadeJpanel().getLabTzyf().setIcon(null);
                        CollectionJadeJpanel.money = null;
                        CollectionJadeJpanel.sxlxz = null;
                        CollectionJadeJframe.getCollectionJadeJframe().getJadeJpanel().getGoodstableBean().setType(0);
                        CollectionJadeJframe.getCollectionJadeJframe().getJadeJpanel().getGoodstableBean().setPartJade(null);
                    }
                }
            }
            else if (this.type == 3) {
                if (StorageJadeJpanel3.partJade != null) {
                    this.jadeNum[0] = StorageJadeJpanel3.partJade.getJade1();
                    this.jadeNum[1] = StorageJadeJpanel3.partJade.getJade2();
                    this.jadeNum[2] = StorageJadeJpanel3.partJade.getJade3();
                    this.jadeNum[3] = StorageJadeJpanel3.partJade.getJade4();
                    this.jadeNum[4] = StorageJadeJpanel3.partJade.getJade5();
                    if (this.jadeNum[this.index] > 0) {
                        SynthesisJpanel.getGoodstableBean().setPartJade(StorageJadeJpanel3.partJade);
                        SynthesisJpanel.getGoodstableBean().setType(this.index + 1);
                        SynthesisJpanel.getLabyf().setIcon(new ImageIcon(new ImageIcon("img/item/tzyf" + (this.index + 1) + ".png").getImage().getScaledInstance(53, 51, 10)));
                        BigDecimal big = AccessSuitMsgUntil.returnMoney(SynthesisJpanel.getGoodstableBean(), 1);
                        if (big != null) {
                            SynthesisJpanel.setMoney(big);
                        }
                    }
                }
            }
            else if (this.type == 4 && StorageJadeJpanel4.partJade != null) {
                this.jadeNum[0] = StorageJadeJpanel4.partJade.getJade1();
                this.jadeNum[1] = StorageJadeJpanel4.partJade.getJade2();
                this.jadeNum[2] = StorageJadeJpanel4.partJade.getJade3();
                this.jadeNum[3] = StorageJadeJpanel4.partJade.getJade4();
                this.jadeNum[4] = StorageJadeJpanel4.partJade.getJade5();
                if (this.jadeNum[this.index] > 0) {
                    JadeUpJpanel.getLabyf().setIcon(new ImageIcon(new ImageIcon("img/item/tzyf" + (this.index + 1) + ".png").getImage().getScaledInstance(54, 51, 10)));
                    JadeUpJpanel.getLabpz().setText(AccessSuitMsgUntil.returnJadeName(this.index + 1));
                    JadeUpJpanel.getLabgs().setText(this.jadeNum[this.index] + "");
                    if (AccessSuitMsgUntil.returnJadeMoney(this.index + 1) != null) {
                        JadeUpJpanel.setMoney(AccessSuitMsgUntil.returnJadeMoney(this.index + 1));
                    }
                    int num = AccessSuitMsgUntil.returnJadeNum(this.index + 1);
                    if (num != 0) {
                        JadeUpJpanel.setNumber((this.jadeNum[this.index] - num >= 0) ? 0 : (num - this.jadeNum[this.index]));
                        JadeUpJpanel.getGoodstableBean().setPartJade(StorageJadeJpanel4.partJade);
                        JadeUpJpanel.getGoodstableBean().setType(this.index + 1);
                    }
                    else {
                        JadeUpJpanel.setNumber(-1);
                    }
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
        showMsg(this.index);
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
    
    public static void showMsg(int index) {
        String instruction = "";
        if (index == 0) {
            instruction = "灵宝天尊炼制的玉符，可辟邪，可祛病，可祈福，可令装备产生玄妙的变化。        品质：把玩";
        }
        else if (index == 1) {
            instruction = "灵宝天尊炼制的玉符，可辟邪，可祛病，可祈福，可令装备产生玄妙的变化。        品质：贴身";
        }
        else if (index == 2) {
            instruction = "灵宝天尊炼制的玉符，可辟邪，可祛病，可祈福，可令装备产生玄妙的变化。        品质：珍藏";
        }
        else if (index == 3) {
            instruction = "灵宝天尊炼制的玉符，可辟邪，可祛病，可祈福，可令装备产生玄妙的变化。        品质：无价";
        }
        else if (index == 4) {
            instruction = "灵宝天尊炼制的玉符，可辟邪，可祛病，可祈福，可令装备产生玄妙的变化。        品质：传世";
        }
        Goodstable goodstable = new Goodstable();
        goodstable.setSkin("tzyf" + (index + 1));
        goodstable.setGoodsname("灵宝玉符");
        goodstable.setInstruction(instruction);
        goodstable.setValue("");
        goodstable.setQuality(Long.valueOf(1L));
        ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
    }
}
