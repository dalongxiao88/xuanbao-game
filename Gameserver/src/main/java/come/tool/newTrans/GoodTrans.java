package come.tool.newTrans;

import java.util.ArrayList;
import come.tool.Stall.AssetUpdate;
import org.come.tool.EquipTool;
import org.come.entity.Record;
import come.tool.Mixdeal.AnalysisString;
import org.come.until.AllServiceUtil;
import java.util.Map;
import java.math.BigDecimal;
import org.come.entity.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import java.util.List;

public class GoodTrans
{
    private List<Goodstable> goods;
    private List<RoleSummoning> pets;
    private List<Lingbao> lingbaos;
    private BigDecimal money;
    
    public boolean check(Map<BigDecimal, Goodstable> map, BigDecimal role_id) {
        if (this.goods != null) {
            for (int i = this.goods.size() - 1; i >= 0; --i) {
                Goodstable good = AllServiceUtil.getGoodsTableService().getGoodsByRgID(((Goodstable)this.goods.get(i)).getRgid());
                if (good == null || good.getRole_id().compareTo(role_id) != 0) {
                    return false;
                }
                if (AnalysisString.jiaoyi((long)good.getQuality())) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append(role_id);
                    buffer.append("欲交易禁交易物品:");
                    buffer.append(good.getRgid());
                    buffer.append(":");
                    buffer.append(good.getGoodsname());
                    AllServiceUtil.getRecordService().insert(new Record(5, buffer.toString()));
                    return false;
                }
                if (EquipTool.canSuper(good.getType())) {
                    if (good.getType() != ((Goodstable)this.goods.get(i)).getType()) {
                        return false;
                    }
                    if ((int)((Goodstable)this.goods.get(i)).getUsetime() < 0) {
                        return false;
                    }
                    good.setUsetime(Integer.valueOf((int)good.getUsetime() - (int)((Goodstable)this.goods.get(i)).getUsetime()));
                    if ((int)good.getUsetime() < 0) {
                        return false;
                    }
                    map.put(good.getRgid(), good);
                }
                else {
                    this.goods.set(i, good);
                }
            }
        }
        if (this.pets != null) {
            for (int i = this.pets.size() - 1; i >= 0; --i) {
                RoleSummoning pet = AllServiceUtil.getRoleSummoningService().selectRoleSummoningsByRgID(((RoleSummoning)this.pets.get(i)).getSid());
                if (pet == null || pet.getRoleid().compareTo(role_id) != 0) {
                    return false;
                }
                this.pets.set(i, pet);
            }
        }
        if (this.lingbaos != null) {
            for (int i = this.lingbaos.size() - 1; i >= 0; --i) {
                Lingbao lingbao = AllServiceUtil.getLingbaoService().selectLingbaoByID(((Lingbao)this.lingbaos.get(i)).getBaoid());
                if (lingbao == null || lingbao.getRoleid().compareTo(role_id) != 0) {
                    return false;
                }
                this.lingbaos.set(i, lingbao);
            }
        }
        return true;
    }
    
    public AssetUpdate goTrans(AssetUpdate asset, Map<BigDecimal, Goodstable> map, BigDecimal role_id) {
        if (this.goods != null && this.goods.size() != 0) {
            if (asset == null) {
                asset = new AssetUpdate();
            }
            for (int i = this.goods.size() - 1; i >= 0; --i) {
                Goodstable good = (Goodstable)this.goods.get(i);
                AllServiceUtil.getGoodsrecordService().insert(good, role_id, good.getUsetime(), Integer.valueOf(5));
                if (EquipTool.canSuper(good.getType())) {
                    Goodstable good2 = ((Goodstable)map.get(good.getRgid())).clone();
                    List<Goodstable> goods = AllServiceUtil.getGoodsTableService().selectGoodsByRoleIDAndGoodsID(role_id, good2.getGoodsid());
                    if (goods.size() != 0) {
                        ((Goodstable)goods.get(0)).setUsetime(Integer.valueOf((int)((Goodstable)goods.get(0)).getUsetime() + (int)good.getUsetime()));
                        AllServiceUtil.getGoodsTableService().updateGoodRedis((Goodstable)goods.get(0));
                        asset.setGood((Goodstable)goods.get(0));
                    }
                    else {
                        good2.setRole_id(role_id);
                        good2.setUsetime(good.getUsetime());
                        AllServiceUtil.getGoodsTableService().insertGoods(good2);
                        asset.setGood(good2);
                    }
                }
                else {
                    good.setUsetime(Integer.valueOf(1));
                    AllServiceUtil.getGoodsTableService().updateGoodsIndex(good, role_id, null, null);
                    asset.setGood(good);
                }
            }
        }
        if (this.pets != null && this.pets.size() != 0) {
            if (asset == null) {
                asset = new AssetUpdate();
            }
            for (int i = this.pets.size() - 1; i >= 0; --i) {
                RoleSummoning pet = (RoleSummoning)this.pets.get(i);
                pet.setFriendliness(Long.valueOf(0L));
                AllServiceUtil.getRoleSummoningService().updateRoleSummoningIndex(pet, role_id);
                asset.setPet(pet);
                List<BigDecimal> list = pet.getGoods();
                if (list != null) {
                    for (int j = 0; j < list.size(); ++j) {
                        Goodstable good3 = AllServiceUtil.getGoodsTableService().getGoodsByRgID((BigDecimal)list.get(j));
                        if (good3 != null) {
                            AllServiceUtil.getGoodsTableService().updateGoodsIndex(good3, role_id, null, null);
                            asset.setGood(good3);
                        }
                    }
                }
            }
        }
        if (this.lingbaos != null && this.lingbaos.size() != 0) {
            if (asset == null) {
                asset = new AssetUpdate();
            }
            for (int i = this.lingbaos.size() - 1; i >= 0; --i) {
                Lingbao lingbao = (Lingbao)this.lingbaos.get(i);
                AllServiceUtil.getLingbaoService().updateLingbaoIndex(lingbao, role_id);
                asset.setLingbao(lingbao);
                if (lingbao.getFushis() != null && !lingbao.getFushis().equals("")) {
                    String[] vs = lingbao.getFushis().split("\\|");
                    for (int j = 0; j < vs.length; ++j) {
                        Goodstable good3 = AllServiceUtil.getGoodsTableService().getGoodsByRgID(new BigDecimal(vs[j]));
                        if (good3 != null) {
                            AllServiceUtil.getGoodsTableService().updateGoodsIndex(good3, role_id, null, null);
                            asset.setGood(good3);
                        }
                    }
                }
            }
        }
        return asset;
    }
    
    public void updateGood(GoodTrans2 goodTrans2) {
        this.setGood(goodTrans2.getGoods());
        this.setPet(goodTrans2.getPet(), goodTrans2.isI());
        this.setLingbao(goodTrans2.getLingbao(), goodTrans2.isI());
        if (goodTrans2.getMoney() != null) {
            this.money = goodTrans2.getMoney();
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
}
