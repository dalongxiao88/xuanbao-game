package com.tool.role;

import org.come.bean.RoleShow;
import com.tool.time.TimeLimit;
import org.come.mouslisten.GoodsMouslisten;
import java.util.ArrayList;
import java.util.List;
import org.come.entity.Goodstable;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.ZhuFrame;
import org.come.bean.RoleTxBean;
import org.come.until.UserMessUntil;
import com.tool.tcp.SpriteFactory;
import java.math.BigDecimal;
import com.tool.image.ImageMixDeal;
import com.updateNew.MyIsif;
import java.awt.Graphics;
import com.tool.tcp.NewPart;

public class RoleTX
{
    private static RoleTX roleTX;
    private NewPart part1;
    private int dir1;
    private TxImg[] txImgs;
    private TxImg[] eTxImg;
    private int txType;
    private int txYs;
    private NewPart part2;
    private int dir2;
    private static String labroletxk;
    
    public static RoleTX getRoleTX() {
        if (RoleTX.roleTX == null) {
            RoleTX.roleTX = new RoleTX();
        }
        return RoleTX.roleTX;
    }
    
    public RoleTX() {
        this.dir1 = 0;
        this.txType = 0;
        this.txYs = 0;
        this.dir2 = 0;
        this.txImgs = new TxImg[25];
        this.eTxImg = new TxImg[5];
    }
    
    public void draw(Graphics g, int type) {
        if (MyIsif.getStyle().equals("水墨")) {
            if (type == 0) {
                this.part1.draw(g, 208, 310, this.dir1, ImageMixDeal.userimg.getTime());
                for (int i = 0; i < this.txImgs.length; ++i) {
                    if (this.txImgs[i] != null) {
                        g.drawImage(this.txImgs[i].getIcon().getImage(), 328 + i % 5 * 51, 96 + i / 5 * 51, 49, 49, null);
                    }
                }
                for (int i = 0; i < this.eTxImg.length; ++i) {
                    if (this.eTxImg[i] != null) {
                        g.drawImage(this.eTxImg[i].getIcon().getImage(), 60, 107 + i * 45, 38, 38, null);
                    }
                }
            }
            else {
                this.part2.draw(g, 160, 230, this.dir2, ImageMixDeal.userimg.getTime());
            }
        }
        else if (type == 0) {
            this.part1.draw(g, 208, 310, this.dir1, ImageMixDeal.userimg.getTime());
            for (int i = 0; i < this.txImgs.length; ++i) {
                if (this.txImgs[i] != null) {
                    g.drawImage(this.txImgs[i].getIcon().getImage(), 302 + i % 5 * 51, 136 + i / 5 * 51, 49, 49, null);
                }
            }
            for (int i = 0; i < this.eTxImg.length; ++i) {
                if (this.eTxImg[i] != null) {
                    g.drawImage(this.eTxImg[i].getIcon().getImage(), 33, 120 + i * 45, 38, 38, null);
                }
            }
        }
        else {
            this.part2.draw(g, 160, 230, this.dir2, ImageMixDeal.userimg.getTime());
        }
    }
    
    public void upDir(int type, boolean is) {
        if (type == 0) {
            switch (this.dir1) {
                case 4: {
                    if (is) {
                        this.dir1 = 1;
                        break;
                    }
                    else {
                        this.dir1 = 0;
                        break;
                    }
                }
                case 0: {
                    if (is) {
                        this.dir1 = 4;
                        break;
                    }
                    else {
                        this.dir1 = 7;
                        break;
                    }
                }
                case 7: {
                    if (is) {
                        this.dir1 = 0;
                        break;
                    }
                    else {
                        this.dir1 = 3;
                        break;
                    }
                }
                case 3: {
                    if (is) {
                        this.dir1 = 7;
                        break;
                    }
                    else {
                        this.dir1 = 6;
                        break;
                    }
                }
                case 6: {
                    if (is) {
                        this.dir1 = 3;
                        break;
                    }
                    else {
                        this.dir1 = 2;
                        break;
                    }
                }
                case 2: {
                    if (is) {
                        this.dir1 = 6;
                        break;
                    }
                    else {
                        this.dir1 = 5;
                        break;
                    }
                }
                case 5: {
                    if (is) {
                        this.dir1 = 2;
                        break;
                    }
                    else {
                        this.dir1 = 1;
                        break;
                    }
                }
                case 1: {
                    if (is) {
                        this.dir1 = 5;
                        break;
                    }
                    else {
                        this.dir1 = 4;
                        break;
                    }
                }
            }
        }
        else {
            switch (this.dir2) {
                case 4: {
                    if (is) {
                        this.dir2 = 1;
                        break;
                    }
                    else {
                        this.dir2 = 0;
                        break;
                    }
                }
                case 0: {
                    if (is) {
                        this.dir2 = 4;
                        break;
                    }
                    else {
                        this.dir2 = 7;
                        break;
                    }
                }
                case 7: {
                    if (is) {
                        this.dir2 = 0;
                        break;
                    }
                    else {
                        this.dir2 = 3;
                        break;
                    }
                }
                case 3: {
                    if (is) {
                        this.dir2 = 7;
                        break;
                    }
                    else {
                        this.dir2 = 6;
                        break;
                    }
                }
                case 6: {
                    if (is) {
                        this.dir2 = 3;
                        break;
                    }
                    else {
                        this.dir2 = 2;
                        break;
                    }
                }
                case 2: {
                    if (is) {
                        this.dir2 = 6;
                        break;
                    }
                    else {
                        this.dir2 = 5;
                        break;
                    }
                }
                case 5: {
                    if (is) {
                        this.dir2 = 2;
                        break;
                    }
                    else {
                        this.dir2 = 1;
                        break;
                    }
                }
                case 1: {
                    if (is) {
                        this.dir2 = 5;
                        break;
                    }
                    else {
                        this.dir2 = 4;
                        break;
                    }
                }
            }
        }
    }
    
    public void addPart(int type, NewPart part) {
        if (type == 0) {
            this.part1 = this.part1.addPart(part);
        }
        else {
            this.part2 = this.part2.addPart(part);
        }
    }
    
    public void updatePart(int type, int lvl, BigDecimal species_id) {
        if (type == 0) {
            this.part1 = SpriteFactory.setPart(this.part1, lvl, species_id.toString());
        }
        else {
            this.part2 = SpriteFactory.setPart(this.part2, lvl, species_id.toString());
        }
    }
    
    public void removePart(int type, String skin) {
        if (type == 0) {
            this.part1.removePart(skin);
        }
        else {
            this.part2.removePart(skin);
        }
    }
    
    public void initRole(BigDecimal species_id) {
        if (this.part1 == null) {
            this.part1 = SpriteFactory.createPart(species_id.toString(), 2, 1, null);
        }
        else {
            this.updatePart(0, 1, species_id);
        }
        if (this.part2 == null) {
            this.part2 = SpriteFactory.createPart(species_id.toString(), 2, 1, null);
        }
        else {
            this.updatePart(1, 1, species_id);
        }
    }
    
    public void addTX(int type, int id) {
        RoleTxBean txBean = UserMessUntil.getTxBean(id);
        if (txBean == null) {
            return;
        }
        NewPart part = SpriteFactory.createPart("tx/tx" + txBean.getRdId(), -2, txBean.getRdStatues() - txBean.getRdType(), null);
        if (type == 0) {
            this.part1 = this.part1.addPart(part);
        }
        else {
            this.part2 = this.part2.addPart(part);
        }
    }
    
    public void addTX(int type, String skin) {
        NewPart tx1 = SpriteFactory.createPart("tx/" + skin + "0", -2, -5, null);
        NewPart tx2 = SpriteFactory.createPart("tx/" + skin + "1", -2, 5, null);
        if (type == 0) {
            this.part1 = this.part1.addPart(tx1);
            this.part1 = this.part1.addPart(tx2);
        }
        else {
            this.part2 = this.part2.addPart(tx1);
            this.part2 = this.part2.addPart(tx2);
        }
    }
    
    public static void addTXK(int type, int id) {
        RoleTxBean txBean = UserMessUntil.getTxBean(id);
        if (txBean == null) {
            return;
        }
        RoleTX.labroletxk = "inkImg/txk/tx" + txBean.getRdId() + ".tcp";
    }
    
    public static void removeTXK(int type, int id) {
        RoleTxBean txBean = UserMessUntil.getTxBean(id);
        if (txBean == null) {
            return;
        }
        RoleTX.labroletxk = "";
    }
    
    public void removeTX(int type, int id) {
        RoleTxBean txBean = UserMessUntil.getTxBean(id);
        if (txBean == null) {
            return;
        }
        if (type == 0) {
            this.part1 = this.part1.removePart("tx/tx" + txBean.getRdId());
        }
        else {
            this.part2 = this.part2.removePart("tx/tx" + txBean.getRdId());
        }
    }
    
    public void removeTX(int type, String skin) {
        if (type == 0) {
            this.part1 = this.part1.removePart("tx/" + skin + "0");
            this.part1 = this.part1.removePart("tx/" + skin + "1");
        }
        else {
            this.part2 = this.part2.removePart("tx/" + skin + "0");
            this.part2 = this.part2.removePart("tx/" + skin + "1");
        }
    }
    
    public void Toggle(int type, int is) {
        if (type != -1) {
            this.txType = type;
            this.Toggle2(0);
        }
        else {
            this.Toggle2(this.txYs + ((is == 0) ? -1 : 1));
        }
    }
    
    public void Toggle2(int ys) {
        if (ys < 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("已经是首页");
            return;
        }
        if (this.txType == 3) {
            List<Goodstable> wingGoodsList = GoodsListFromServerUntil.getWingGoodsList();
            int size = wingGoodsList.size();
            if (size < ys * 25) {
                ZhuFrame.getZhuJpanel().addPrompt2("已经是尾页");
                return;
            }
            for (int i = 0; i < this.txImgs.length; ++i) {
                if (i + ys * 25 < size) {
                    if (wingGoodsList.get(i + ys * 25) != null) {
                        if (this.txImgs[i] == null) {
                            this.txImgs[i] = new TxImg();
                        }
                        this.txImgs[i].setId(((Goodstable)wingGoodsList.get(i + ys * 25)).getRgid().intValue());
                        this.txImgs[i].setIcon(GoodsListFromServerUntil.imgpathAdaptive(((Goodstable)wingGoodsList.get(i + ys * 25)).getSkin(), 49, 49));
                    }
                }
                else {
                    this.txImgs[i] = null;
                }
            }
            this.txYs = ys;
        }
        else if (this.txType == 4) {
            List<Integer> txLis = this.getTX(this.txType);
            int size = txLis.size();
            if (size < ys * 25) {
                ZhuFrame.getZhuJpanel().addPrompt2("已经是尾页");
                return;
            }
            for (int i = 0; i < this.txImgs.length; ++i) {
                if (i + ys * 25 < size) {
                    RoleTxBean txBean = UserMessUntil.getTxBean((int)txLis.get(i + ys * 25));
                    if (txBean != null) {
                        if (this.txImgs[i] == null) {
                            this.txImgs[i] = new TxImg();
                        }
                        this.txImgs[i].setId(txBean.getGid());
                        this.txImgs[i].setIcon(GoodsListFromServerUntil.imgpathAdaptive("tx" + txBean.getRdId(), 49, 49));
                    }
                }
                else {
                    this.txImgs[i] = null;
                }
            }
            this.txYs = ys;
        }
        else {
            List<Integer> txLis = this.getTX(this.txType + 1);
            int size = txLis.size();
            if (size < ys * 25) {
                ZhuFrame.getZhuJpanel().addPrompt2("已经是尾页");
                return;
            }
            for (int i = 0; i < this.txImgs.length; ++i) {
                if (i + ys * 25 < size) {
                    RoleTxBean txBean = UserMessUntil.getTxBean((int)txLis.get(i + ys * 25));
                    if (txBean != null) {
                        if (this.txImgs[i] == null) {
                            this.txImgs[i] = new TxImg();
                        }
                        this.txImgs[i].setId(txBean.getGid());
                        this.txImgs[i].setIcon(GoodsListFromServerUntil.imgpathAdaptive("tx" + txBean.getRdId(), 49, 49));
                    }
                }
                else {
                    this.txImgs[i] = null;
                }
            }
            this.txYs = ys;
        }
    }
    
    public List<Integer> getTX(int txType) {
        List<Integer> txList = new ArrayList<>();
        String tx = RoleData.getRoleData().getPackRecord().getTx();
        if (tx == null || "".equals(tx)) {
            return txList;
        }
        String[] txs = tx.split("\\|");
        for (int i = 0; i < txs.length; ++i) {
            if (Integer.parseInt(txs[i].substring(0, txs[i].length() - 1)) >= 1294) {
                if (txs[i].endsWith("&")) {
                    txs[i] = txs[i].substring(0, txs[i].length() - 1);
                }
            }
            else if (txs[i].endsWith("#")) {
                txs[i] = txs[i].substring(0, txs[i].length() - 1);
            }
            RoleTxBean txBean = UserMessUntil.getTxBean(Integer.parseInt(txs[i]));
            if (txBean != null && txType == txBean.getRdType()) {
                txList.add(Integer.valueOf(txBean.getGid()));
            }
        }
        return txList;
    }
    
    public void EToggle(int id) {
        if (id < 0) {
            if (id == -4) {
                if (this.eTxImg[Math.abs(id) - 1] != null) {
                    this.removeTX(0, this.eTxImg[Math.abs(id) - 1].getWingSkin());
                }
                this.eTxImg[Math.abs(id) - 1] = null;
                return;
            }
            else {
                if (id == -5 && this.eTxImg[Math.abs(id) - 1] != null) {
                    removeTXK(0, this.eTxImg[Math.abs(id) - 1].getId());
                }
                if (this.eTxImg[Math.abs(id) - 1] != null) {
                    this.removeTX(0, this.eTxImg[Math.abs(id) - 1].getId());
                }
                this.eTxImg[Math.abs(id) - 1] = null;
                return;
            }
        }
        else {
            if (id >= 1294) {
                RoleTxBean txBean = UserMessUntil.getTxBean(id);
                if (txBean == null) {
                    return;
                }
                if (this.eTxImg[txBean.getRdType()] == null) {
                    this.eTxImg[txBean.getRdType()] = new TxImg();
                }
                removeTXK(0, this.eTxImg[txBean.getRdType()].getId());
                addTXK(0, txBean.getGid());
                this.eTxImg[txBean.getRdType()].setId(txBean.getGid());
                this.eTxImg[txBean.getRdType()].setIcon(GoodsListFromServerUntil.imgpathAdaptive("tx" + txBean.getRdId(), 49, 49));
            }
            else if (this.txType != 3 && this.txType != 4) {
                RoleTxBean txBean = UserMessUntil.getTxBean(id);
                if (txBean == null) {
                    return;
                }
                if (this.eTxImg[txBean.getRdType() - 1] == null) {
                    this.eTxImg[txBean.getRdType() - 1] = new TxImg();
                }
                this.removeTX(0, this.eTxImg[txBean.getRdType() - 1].getId());
                this.addTX(0, txBean.getGid());
                this.eTxImg[txBean.getRdType() - 1].setId(txBean.getGid());
                this.eTxImg[txBean.getRdType() - 1].setIcon(GoodsListFromServerUntil.imgpathAdaptive("tx" + txBean.getRdId(), 49, 49));
            }
            else {
                Goodstable goodstable = this.getWing(id);
                if (goodstable != null) {
                    if (this.eTxImg[3] == null) {
                        this.eTxImg[3] = new TxImg();
                    }
                    this.removeTX(0, this.eTxImg[3].getWingSkin());
                    this.addTX(0, goodstable.getSkin());
                    this.eTxImg[3].setWingSkin(goodstable.getSkin());
                    this.eTxImg[3].setId(goodstable.getRgid().intValue());
                    this.eTxImg[3].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
                }
            }
            return;
        }
    }
    
    public void chushihuaWing() {
        Goodstable goodstable = GoodsListFromServerUntil.getChoseGoodsList()[12];
        if (goodstable != null) {
            if (this.eTxImg[3] == null) {
                this.eTxImg[3] = new TxImg();
            }
            this.removeTX(0, this.eTxImg[3].getWingSkin());
            this.addTX(0, goodstable.getSkin());
            this.eTxImg[3].setWingSkin(goodstable.getSkin());
            this.eTxImg[3].setId(goodstable.getRgid().intValue());
            this.eTxImg[3].setIcon(GoodsListFromServerUntil.imgpathAdaptive(goodstable.getSkin(), 49, 49));
        }
    }
    
    public int getTxYs() {
        return this.txYs;
    }
    
    public RoleTxBean getTx(int p) {
        if (p < 0) {
            if (this.eTxImg[Math.abs(p) - 1] != null) {
                return UserMessUntil.getTxBean(this.eTxImg[Math.abs(p) - 1].getId());
            }
        }
        else if (this.txImgs[p] != null) {
            return UserMessUntil.getTxBean(this.txImgs[p].getId());
        }
        return null;
    }
    
    public Goodstable getWing(int p) {
        if (p < 0) {
            if (this.eTxImg[3] == null || p != -4) {
                return null;
            }
            for (int i = 0; i < GoodsListFromServerUntil.getWingGoodsList().size(); ++i) {
                if (this.eTxImg[3].getId() == ((Goodstable)GoodsListFromServerUntil.getWingGoodsList().get(i)).getRgid().intValue()) {
                    return (Goodstable)GoodsListFromServerUntil.getWingGoodsList().get(i);
                }
            }
        }
        if (this.txType != 3) {
            return null;
        }
        if (p + this.txYs * 25 >= GoodsListFromServerUntil.getWingGoodsList().size()) {
            return null;
        }
        return (Goodstable)GoodsListFromServerUntil.getWingGoodsList().get(p + this.txYs * 25);
    }
    
    public Goodstable getWingGoods(int rgid) {
        for (int i = 0; i < GoodsListFromServerUntil.getWingGoodsList().size(); ++i) {
            if (rgid == ((Goodstable)GoodsListFromServerUntil.getWingGoodsList().get(i)).getRgid().intValue()) {
                return (Goodstable)GoodsListFromServerUntil.getWingGoodsList().get(i);
            }
        }
        return null;
    }
    
    public void BCXX() {
        RoleData.getRoleData().getPackRecord().putTX(new String[] { (this.eTxImg[0] != null) ? (this.eTxImg[0].getId() + "") : null, (this.eTxImg[1] != null) ? (this.eTxImg[1].getId() + "") : null, (this.eTxImg[2] != null) ? (this.eTxImg[2].getId() + "") : null });
        RoleData.getRoleData().getPackRecord().putTXK(new String[] { (this.eTxImg[4] != null) ? (this.eTxImg[4].getId() + "") : null });
        Goodstable goodstable = GoodsListFromServerUntil.getChoseGoodsList()[12];
        if (goodstable != null) {
            goodstable.setStatus(Integer.valueOf(0));
            GoodsMouslisten.gooduse(goodstable, 0);
        }
        if (this.eTxImg[3] != null) {
            Goodstable wingGoods = this.getWingGoods(this.eTxImg[3].getId());
            if (wingGoods != null) {
                (GoodsListFromServerUntil.getChoseGoodsList()[12] = wingGoods).setStatus(Integer.valueOf(1));
                GoodsMouslisten.gooduse(wingGoods, 0);
            }
        }
        else {
            GoodsListFromServerUntil.getChoseGoodsList()[12] = null;
        }
        ZhuFrame.getZhuJpanel().addPrompt2("保存成功");
        RoleProperty.getRoleProperty().equipWearOff();
        this.skin();
    }
    
    public void skin() {
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        String skin = roleShow.getSkin();
        if (skin == null) {
            skin = "";
        }
        List<String> list = RoleData.getRoleData().getPackRecord().getPutTX();
        String skin2 = TimeLimit.getskin(TimeLimit.getLimits().getSkin(), RoleData.getRoleData().getPackRecord().getPutTX(), roleShow);
        if (!skin2.equals(skin)) {
            if (skin2.equals("")) {
                skin2 = null;
            }
            roleShow.setSkin(skin2);
            RoleData.getRoleData().getLoginResult().setSkin(skin2);
            ImageMixDeal.userimg.changeskin(null);
            if (list != null && list.size() != 0) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("E");
                buffer.append((String)list.get(0));
                for (int i = 1; i < list.size(); ++i) {
                    buffer.append("_");
                    buffer.append((String)list.get(i));
                }
                if (skin2 == null) {
                    skin2 = buffer.toString();
                }
                else {
                    skin2 = skin2 + "|" + buffer.toString();
                }
            }
            GoodsListFromServerUntil.sendPackRecord(5, skin2);
        }
        List<String> listtxk = RoleData.getRoleData().getPackRecord().getPutTXK();
        String record = "0";
        if (listtxk != null && !listtxk.isEmpty()) {
            record = (String)listtxk.get(0);
        }
        GoodsListFromServerUntil.sendPackRecord(7, record);
    }
    
    public static String getLabroletxk() {
        return RoleTX.labroletxk;
    }
    
    public static void setLabroletxk(String labroletxk) {
        RoleTX.labroletxk = labroletxk;
    }
}
