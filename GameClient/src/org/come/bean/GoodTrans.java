package org.come.bean;

import java.util.ArrayList;
import org.come.until.GoodsListFromServerUntil;
import com.updateNew.MyIsif;
import java.awt.Color;
import java.awt.Graphics;
import java.math.BigDecimal;
import org.come.model.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import java.util.List;

public class GoodTrans
{
    private List<Goodstable> goods;
    private List<RoleSummoning> pets;
    private List<Lingbao> lingbaos;
    private BigDecimal money;
    
    public void updateGood(GoodTrans2 goodTrans2) {
        this.setGood(goodTrans2.getGoods());
        this.setPet(goodTrans2.getPet(), goodTrans2.isI());
        this.setLingbao(goodTrans2.getLingbao(), goodTrans2.isI());
        if (goodTrans2.getMoney() != null) {
            this.money = goodTrans2.getMoney();
        }
    }
    
    public void paint(Graphics g, int type) {
        g.setColor(Color.white);
        if (MyIsif.getStyle().equals("水墨")) {
            if (this.goods != null) {
                for (int i = 0; i < this.goods.size(); ++i) {
                    int x = 20 + i % 4 * 51;
                    int y = 357 + i / 4 * 51;
                    if (type == 1) {
                        x += 270;
                    }
                    g.drawImage(GoodsListFromServerUntil.imgpath(((Goodstable)this.goods.get(i)).getSkin()).getImage(), x + 23, y - 9, 49, 49, null);
                    g.drawString(((Goodstable)this.goods.get(i)).getUsetime().toString(), x + 4 + 23, y + 13 - 13);
                }
            }
            if (this.pets != null) {
                for (int i = 0; i < this.pets.size(); ++i) {
                    if (type == 0) {
                        g.drawString(((RoleSummoning)this.pets.get(i)).getSummoningname(), 43 + i / 2 * 110, 317 + i % 2 * 20);
                    }
                    else {
                        g.drawString(((RoleSummoning)this.pets.get(i)).getSummoningname(), 313 + i / 2 * 110, 317 + i % 2 * 20);
                    }
                }
            }
            if (this.lingbaos != null) {
                for (int i = 0; i < this.lingbaos.size(); ++i) {
                    if (type == 0) {
                        g.drawString(((Lingbao)this.lingbaos.get(i)).getBaoname(), 43 + (i + 2) / 2 * 110, 317 + i % 2 * 20);
                    }
                    else {
                        g.drawString(((Lingbao)this.lingbaos.get(i)).getBaoname(), 313 + (i + 2) / 2 * 110, 317 + i % 2 * 20);
                    }
                }
            }
        }
        else {
            if (this.goods != null) {
                for (int i = 0; i < this.goods.size(); ++i) {
                    int x = 20 + i % 4 * 51;
                    int y = 357 + i / 4 * 51;
                    if (type == 1) {
                        x += 270;
                    }
                    g.drawImage(GoodsListFromServerUntil.imgpath(((Goodstable)this.goods.get(i)).getSkin()).getImage(), x, y, 49, 49, null);
                    g.drawString(((Goodstable)this.goods.get(i)).getUsetime().toString(), x + 4, y + 13);
                }
            }
            if (this.pets != null) {
                for (int i = 0; i < this.pets.size(); ++i) {
                    if (type == 0) {
                        g.drawString(((RoleSummoning)this.pets.get(i)).getSummoningname(), 20 + i / 2 * 110, 330 + i % 2 * 20);
                    }
                    else {
                        g.drawString(((RoleSummoning)this.pets.get(i)).getSummoningname(), 290 + i / 2 * 110, 330 + i % 2 * 20);
                    }
                }
            }
            if (this.lingbaos != null) {
                for (int i = 0; i < this.lingbaos.size(); ++i) {
                    if (type == 0) {
                        g.drawString(((Lingbao)this.lingbaos.get(i)).getBaoname(), 20 + (i + 2) / 2 * 110, 330 + i % 2 * 20);
                    }
                    else {
                        g.drawString(((Lingbao)this.lingbaos.get(i)).getBaoname(), 290 + (i + 2) / 2 * 110, 330 + i % 2 * 20);
                    }
                }
            }
        }
    }
    
    public List<Goodstable> getGoods() {
        return this.goods;
    }
    
    public void setGoods(List<Goodstable> goods) {
        this.goods = goods;
    }
    
    public void setGood(Goodstable good) {
        if (good == null) {
            return;
        }
        if (this.goods == null) {
            this.goods = new ArrayList<>();
        }
        int i = this.goods.size() - 1;
        while (i >= 0) {
            if (((Goodstable)this.goods.get(i)).getRgid().compareTo(good.getRgid()) == 0) {
                if ((int)good.getUsetime() <= 0) {
                    this.goods.remove(i);
                }
                else {
                    this.goods.set(i, good);
                }
                return;
            }
            else {
                --i;
            }
        }
        if ((int)good.getUsetime() > 0) {
            this.goods.add(good);
        }
    }
    
    public List<RoleSummoning> getPets() {
        return this.pets;
    }
    
    public void setPets(List<RoleSummoning> pets) {
        this.pets = pets;
    }
    
    public void setPet(RoleSummoning pet, boolean is) {
        if (pet == null) {
            return;
        }
        if (this.pets == null) {
            this.pets = new ArrayList<>();
        }
        if (is) {
            this.pets.add(pet);
        }
        else {
            for (int i = this.pets.size() - 1; i >= 0; --i) {
                if (((RoleSummoning)this.pets.get(i)).getSid().compareTo(pet.getSid()) == 0) {
                    this.pets.remove(i);
                    return;
                }
            }
        }
    }
    
    public List<Lingbao> getLingbaos() {
        return this.lingbaos;
    }
    
    public void setLingbaos(List<Lingbao> lingbaos) {
        this.lingbaos = lingbaos;
    }
    
    public void setLingbao(Lingbao lingbao, boolean is) {
        if (lingbao == null) {
            return;
        }
        if (this.lingbaos == null) {
            this.lingbaos = new ArrayList<>();
        }
        if (is) {
            this.lingbaos.add(lingbao);
        }
        else {
            for (int i = this.lingbaos.size() - 1; i >= 0; --i) {
                if (((Lingbao)this.lingbaos.get(i)).getBaoid().compareTo(lingbao.getBaoid()) == 0) {
                    this.lingbaos.remove(i);
                    return;
                }
            }
        }
    }
    
    public BigDecimal getMoney() {
        return this.money;
    }
    
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
    public Goodstable getGood(BigDecimal rgid) {
        if (this.goods == null) {
            return null;
        }
        for (int i = 0; i < this.goods.size(); ++i) {
            if (((Goodstable)this.goods.get(i)).getRgid().compareTo(rgid) == 0) {
                return (Goodstable)this.goods.get(i);
            }
        }
        return null;
    }
    
    public Goodstable getGood(int p) {
        if (this.goods == null || this.goods.size() <= p) {
            return null;
        }
        return (Goodstable)this.goods.get(p);
    }
    
    public RoleSummoning getPet(int p) {
        if (this.pets == null || this.pets.size() <= p) {
            return null;
        }
        return (RoleSummoning)this.pets.get(p);
    }
    
    public Lingbao getLingbao(int p) {
        if (this.lingbaos == null || this.lingbaos.size() <= p) {
            return null;
        }
        return (Lingbao)this.lingbaos.get(p);
    }
}
