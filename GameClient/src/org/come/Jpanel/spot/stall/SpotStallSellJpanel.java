package org.come.Jpanel.spot.stall;

import java.awt.event.MouseEvent;
import com.tool.btn.MoBanBtn;
import java.util.List;
import org.come.until.Util;
import java.awt.Graphics;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.Color;
import org.come.until.AnalysisString;
import com.tool.Stall.CommodityBean;
import org.come.Jpanel.spot.Commodity;
import org.come.until.UserStallUntil;
import org.come.model.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import java.math.BigDecimal;
import com.tool.role.RoleLingFa;
import org.come.Jpanel.ZhuJpanel;
import com.tool.role.RoleData;
import org.come.until.UserMessUntil;
import org.come.until.GoodsListFromServerUntil;
import org.come.Frame.spot.InputMoneyJframe;
import org.come.Frame.ZhuFrame;
import com.tool.tcpimg.UIUtils;
import org.come.mouslisten.ChoseGoodsStallMouslisten;
import org.come.mouslisten.TradeChoseGoodsMouslisten;
import org.come.Jpanel.spot.box.SpotBoxJpanel;
import javax.swing.ImageIcon;
import com.tool.btn.spot.SpotPublishBtn;
import javax.swing.JLabel;
import com.tool.btn.goodbtn;
import com.tool.btn.spot.CommoditySwitchBtn;
import org.come.Jpanel.spot.BuyBox;

public class SpotStallSellJpanel extends SpotStallBaseJpanel implements BuyBox
{
    private CommoditySwitchBtn[] commoditySwitchBtns;
    private goodbtn[] btnRights;
    private final JLabel[] goodsListLabel;
    private final JLabel[] petsListLabel;
    private final SpotPublishBtn btnTheshelves;
    private final SpotPublishBtn btnUpdate;
    private final SpotPublishBtn shareBtn;
    private final SpotPublishBtn sharePointBtn;
    private final SpotPublishBtn shareStallBtn;
    private final ChangePageNumberBtn changePageNumberBtn1;
    private final ChangePageNumberBtn changePageNumberBtn2;
    private int selectGoodsIndex;
    private int selectPetIndex;
    private int selectBaoIndex;
    private int index;
    private int totalNumber;
    private int pageNumber;
    private static final ImageIcon stallimg;
    
    public SpotStallSellJpanel(SpotBoxJpanel spotBoxJpanel) {
        super(spotBoxJpanel, "inkImg/background/S335.png", "sell");
        this.btnRights = new goodbtn[6];
        this.goodsListLabel = new JLabel[24];
        this.petsListLabel = new JLabel[6];
        this.selectGoodsIndex = -1;
        this.selectPetIndex = -1;
        this.selectBaoIndex = -1;
        this.index = 0;
        this.totalNumber = 1;
        this.pageNumber = 1;
        for (int i = 0; i < this.goodsListLabel.length; ++i) {
            (this.goodsListLabel[i] = new JLabel()).addMouseListener(new TradeChoseGoodsMouslisten(i, this));
        }
        for (int j = 0; j < this.petsListLabel.length; ++j) {
            (this.petsListLabel[j] = new JLabel()).addMouseListener(new ChoseGoodsStallMouslisten(j, 1, this));
        }
        this.commoditySwitchBtns = new CommoditySwitchBtn[2];
        for (int i = 0; i < this.commoditySwitchBtns.length; ++i) {
            (this.commoditySwitchBtns[i] = new CommoditySwitchBtn("inkImg/button/B" + (332 + i * 2) + ".png", "inkImg/button/B" + (331 + i * 2) + ".png", this, i, i == 0)).setBounds(54 + i * 65, 284, 63, 23);
        }
        for (int i = 0; i < this.btnRights.length; ++i) {
            (this.btnRights[i] = new goodbtn("inkImg/button/" + (41 + i) + ".png", 0, this, i)).setBounds(362, 65 + i * 36, 39, 31);
        }
        for (int i = 0; i < this.goodsListLabel.length; ++i) {
            this.goodsListLabel[i].setBounds(55 + i % 6 * 51, 67 + i / 6 * 51, 49, 49);
        }
        for (int j = 0; j < this.petsListLabel.length; ++j) {
            this.petsListLabel[j].setBounds(57 + j * 57, 311, 47, 47);
        }
        (this.btnTheshelves = new SpotPublishBtn("inkImg/button1/B409.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "上架", 100, this)).setBounds(435, 319, 79, 25);
        (this.btnUpdate = new SpotPublishBtn("inkImg/button1/B409.png", 1, UIUtils.COLOR_BLACK, UIUtils.TEXT_HY16, "更新", 101, this)).setBounds(525, 319, 79, 25);
        (this.shareBtn = new SpotPublishBtn("inkImg/button1/B34.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "分享摊位", 102, this)).setBounds(48, 390, 68, 17);
        (this.sharePointBtn = new SpotPublishBtn("inkImg/button1/syaj.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "分享坐标", 103, this)).setBounds(48, 407, 117, 24);
        (this.shareStallBtn = new SpotPublishBtn("inkImg/button1/syaj.png", 1, UIUtils.COLOR_BTNTEXT, UIUtils.TEXT_FONT2, "分享摊位", 104, this)).setBounds(48, 432, 117, 24);
        (this.changePageNumberBtn1 = new ChangePageNumberBtn("inkImg/button1/7.png", false)).setBounds(395, 308, 18, 18);
        (this.changePageNumberBtn2 = new ChangePageNumberBtn("inkImg/button1/8.png", true)).setBounds(395, 344, 18, 18);
        for (int i = 0; i < this.btnRights.length; ++i) {
            this.add(this.btnRights[i]);
        }
        for (int i = 0; i < this.goodsListLabel.length; ++i) {
            this.add(this.goodsListLabel[i]);
        }
        for (int i = 0; i < this.commoditySwitchBtns.length; ++i) {
            this.add(this.commoditySwitchBtns[i]);
        }
        for (int j = 0; j < this.petsListLabel.length; ++j) {
            this.add(this.petsListLabel[j]);
        }
        this.add(this.btnTheshelves);
        this.add(this.btnUpdate);
        this.add(this.shareBtn);
        this.sharePointBtn.setVisible(false);
        this.add(this.sharePointBtn);
        this.shareStallBtn.setVisible(false);
        this.add(this.shareStallBtn);
        this.add(this.changePageNumberBtn1);
        this.add(this.changePageNumberBtn2);
    }
    
    @Override
    public void listing() {
        if (this.commodity.getState() == 1) {
            this.withdraw();
            return;
        }
        if (this.commodity == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("没有选中的商品");
            return;
        }
        int sum = this.getNumber();
        if (sum <= 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("最少也的卖一个吧");
            return;
        }
        long money = this.getUnitprice();
        if (money <= 0L) {
            InputMoneyJframe.open(this);
            return;
        }
        if (this.commodity.getType() == 0) {
            Goodstable goods = GoodsListFromServerUntil.czgood(this.commodity.getCommodityId());
            if (GoodsListFromServerUntil.isJY(goods)) {
                ZhuFrame.getZhuJpanel().addPrompt("物品不可交易");
                return;
            }
            if (goods == null || sum > (int)goods.getUsetime()) {
                ZhuFrame.getZhuJpanel().addPrompt2("数量不足");
                return;
            }
            if (this.commodity.getId() != null) {
                goods.setCommodityId(this.commodity.getId());
            }
        }
        else if (this.commodity.getType() == 1) {
            RoleSummoning pet = UserMessUntil.getPetRgid(this.commodity.getCommodityId());
            if (pet.getPetlock() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt("召唤兽" + pet.getSummoningname() + "已被加锁，不可摆摊！！");
                return;
            }
            if (RoleData.getRoleData().getLoginResult().getSummoning_id() != null && pet.getSid().compareTo(RoleData.getRoleData().getLoginResult().getSummoning_id()) == 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你的召唤兽#G" + pet.getSummoningname() + "#Y在参战中，不可摆摊！");
                return;
            }
            if (ZhuJpanel.getPetMount(pet.getSid()) != null) {
                ZhuFrame.getZhuJpanel().addPrompt2("你的召唤兽" + pet.getSummoningname() + "被管制中，不可摆摊！！！");
                return;
            }
            if (pet == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("召唤兽不存在");
                return;
            }
            if (this.commodity.getId() != null) {
                pet.setCommodityId(this.commodity.getId());
            }
        }
        else if (this.commodity.getType() == 2) {
            Lingbao lingbao = RoleLingFa.getRoleLingFa().czGBG(this.commodity.getCommodityId());
            if (lingbao != null && lingbao.getEquipment() != 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你的这个灵宝还在装备中，不可摆摊！！！");
                return;
            }
            if (lingbao == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("灵宝不存在");
                return;
            }
            if (this.commodity.getId() != null) {
                lingbao.setCommodityId(this.commodity.getId());
            }
        }
        this.commodity.setState(1);
        this.commodity.setSum(sum);
        this.commodity.setMoney(BigDecimal.valueOf(money));
        SendMessageUntil.toServer(Agreement.getAgreement().listingAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.commodity)));
    }
    
    @Override
    public void withdraw() {
        if (this.commodity.getState() == 1) {
            if (this.commodity.getType() == 0) {
                Goodstable goods = GoodsListFromServerUntil.czgood(this.commodity.getCommodityId());
                if (goods != null) {
                    goods.setCommodityId(null);
                }
            }
            else if (this.commodity.getType() == 1) {
                RoleSummoning pet = UserMessUntil.getPetRgid(this.commodity.getCommodityId());
                if (pet != null) {
                    pet.setCommodityId(null);
                }
            }
            else if (this.commodity.getType() == 1) {
                Lingbao lingbao = RoleLingFa.getRoleLingFa().czGBG(this.commodity.getCommodityId());
                if (lingbao != null) {
                    lingbao.setCommodityId(null);
                }
            }
            this.commodity.setState(0);
            UserStallUntil.getStall().removeCommodity(this.commodity);
            SendMessageUntil.toServer(Agreement.getAgreement().listingAgreement(GsonUtil.getGsonUtil().getgson().toJson(this.commodity)));
        }
    }
    
    public void setShareVisible() {
        this.setShareVisible(!this.sharePointBtn.isVisible());
    }
    
    public void setShareVisible(boolean aFlag) {
        this.sharePointBtn.setVisible(aFlag);
        this.shareStallBtn.setVisible(aFlag);
    }
    
    @Override
    public CommodityBean getCommodity(Commodity commodity) {
        BigDecimal commodityId = super.getCommodityId(commodity, 0);
        CommodityBean commodityBean = UserStallUntil.getStall().getCommodityById(commodity.getCommodityId());
        if (commodityBean == null) {
            commodityBean = UserStallUntil.getStall().getCommodityByCommodityId(commodityId);
        }
        if (commodityBean != null) {
            return commodityBean;
        }
        commodityBean = super.getCommodity();
        if (commodityBean != null) {
            if (commodityBean.getType() == 0) {
                if (commodityId.compareTo(commodityBean.getGoods().getRgid()) == 0) {
                    commodityBean.setState(0);
                    return commodityBean;
                }
            }
            else if (commodityBean.getType() == 1) {
                if (commodityId.compareTo(commodityBean.getPet().getSid()) == 0) {
                    commodityBean.setState(0);
                    return commodityBean;
                }
            }
            else if (commodityBean.getType() == 2 && commodityId.compareTo(commodityBean.getLingbao().getBaoid()) == 0) {
                commodityBean.setState(0);
                return commodityBean;
            }
        }
        if (commodity instanceof Goodstable) {
            Goodstable goods = (Goodstable)commodity;
            commodityBean = new CommodityBean(goods, 0);
            commodityBean.setCommodityId(goods.getRgid());
            commodityBean.setCommodityName(goods.getGoodsname());
            commodityBean.setCommoditySkin(goods.getSkin());
        }
        else if (commodity instanceof RoleSummoning) {
            RoleSummoning pet = (RoleSummoning)commodity;
            commodityBean = new CommodityBean(pet, 1);
            commodityBean.setCommodityId(pet.getSid());
            commodityBean.setCommodityName(pet.getSummoningname());
            commodityBean.setCommodityLvl(AnalysisString.petLvl((int)pet.getGrade()) + "级");
            commodityBean.setCommoditySkin(pet.getSummoningskin());
        }
        else if (commodity instanceof Lingbao) {
            Lingbao lingbao = (Lingbao)commodity;
            commodityBean = new CommodityBean(lingbao, 2);
            commodityBean.setCommoditySkin(lingbao.getSkin());
            commodityBean.setCommodityName(lingbao.getBaoname());
            commodityBean.setCommodityLvl(lingbao.getLingbaolvl().intValue() + "级");
            commodityBean.setCommodityId(lingbao.getBaoid());
        }
        commodityBean.setCurrency("金钱");
        commodityBean.setSum(0);
        return commodityBean;
    }
    
    @Override
    public void showSelelctBorder(int type, int index) {
        if (type == 0) {
            for (int i = 0; i < this.goodsListLabel.length; ++i) {
                if (index == i) {
                    this.goodsListLabel[i].setBorder(BorderFactory.createLineBorder(Color.red));
                }
                else {
                    this.goodsListLabel[i].setBorder(BorderFactory.createEmptyBorder());
                }
            }
            this.selectGoodsIndex = index;
        }
        else {
            for (int i = 0; i < this.petsListLabel.length; ++i) {
                if (index == i) {
                    this.petsListLabel[i].setBorder(BorderFactory.createLineBorder(Color.red));
                }
                else {
                    this.petsListLabel[i].setBorder(BorderFactory.createEmptyBorder());
                }
            }
            if (type == 1) {
                this.selectPetIndex = index;
            }
            else {
                this.selectBaoIndex = index;
            }
        }
    }
    
    @Override
    public void showEnteredBorder(int type, int index) {
        if (type == 0) {
            for (int i = 0; i < this.goodsListLabel.length; ++i) {
                if (this.selectGoodsIndex != i) {
                    if (index == i) {
                        this.goodsListLabel[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    }
                    else if (this.selectGoodsIndex != i) {
                        this.goodsListLabel[i].setBorder(BorderFactory.createEmptyBorder());
                    }
                }
            }
        }
        else {
            for (int i = 0; i < this.petsListLabel.length; ++i) {
                if ((type == 1 && this.selectPetIndex != i) || (type == 2 && this.selectBaoIndex != i)) {
                    if (index == i) {
                        this.petsListLabel[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    }
                    else {
                        this.petsListLabel[i].setBorder(BorderFactory.createEmptyBorder());
                    }
                }
            }
        }
    }
    
    public void changePublishBtn(boolean is) {
        if (is) {
            this.btnTheshelves.setText("下架");
        }
        else {
            this.btnTheshelves.setText("上架");
        }
    }
    
    @Override
    public void switchTo(int index) {
        this.index = index;
        this.showSelelctBorder(index + 1, (index + 1 == 1) ? this.selectPetIndex : this.selectBaoIndex);
    }
    
    @Override
    public CommoditySwitchBtn[] getCommoditySwitchBtns() {
        return this.commoditySwitchBtns;
    }
    
    @Override
    void setCommodityBounds(JTextField number, JTextField unitprice, JLabel price, JLabel commodity) {
        number.setBounds(488, 167, 60, 18);
        unitprice.setBounds(475, 213, 114, 18);
        price.setBounds(475, 245, 114, 18);
        commodity.setBounds(488, 98, 64, 64);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GoodsListFromServerUntil.draw(g, 55, 67);
        this.paintPet(g, 57, 311);
        Util.drawMoney(g, 475, 289);
    }
    
    private void paintPet(Graphics g, int x, int y) {
        if (this.index == 0) {
            List<RoleSummoning> petList = UserMessUntil.getPetListTable();
            if (petList.size() > 0) {
                this.totalNumber = petList.size() / 6 + ((petList.size() % 6 == 0) ? 0 : 1);
            }
            else {
                this.totalNumber = 1;
            }
            if (this.pageNumber > this.totalNumber) {
                this.pageNumber = this.totalNumber;
            }
            for (int i = (this.pageNumber - 1) * 6; i < petList.size() && i < this.pageNumber * 6; ++i) {
                int lx = x + i % 6 * 57;
                ImageIcon img = new ImageIcon("img/head/p" + ((RoleSummoning)petList.get(i)).getSummoningskin() + ".png");
                g.drawImage(img.getImage(), lx, y, 47, 47, null);
                if (((RoleSummoning)petList.get(i)).getCommodityId() != null) {
                    g.drawImage(SpotStallSellJpanel.stallimg.getImage(), lx + 25, y + 25, 22, 21, null);
                }
            }
        }
        else if (this.index == 1) {
            List<Lingbao> lingbaos = RoleLingFa.getRoleLingFa().lingchangelist();
            this.totalNumber = lingbaos.size() / 6 + ((lingbaos.size() % 6 == 0) ? 0 : 1);
            if (this.pageNumber > this.totalNumber) {
                this.pageNumber = this.totalNumber;
            }
            for (int i = (this.pageNumber - 1) * 6; i < lingbaos.size() && i < this.pageNumber * 6; ++i) {
                int lx = x + i % 6 * 57;
                ImageIcon img = new ImageIcon("img/lingbao/" + ((Lingbao)lingbaos.get(i)).getSkin() + ".png");
                g.drawImage(img.getImage(), lx, y, 47, 47, null);
                if (((Lingbao)lingbaos.get(i)).getCommodityId() != null) {
                    g.drawImage(SpotStallSellJpanel.stallimg.getImage(), lx + 25, y + 25, 22, 21, null);
                }
            }
        }
    }
    
    public RoleSummoning getPet(int index) {
        index += (this.pageNumber - 1) * 6;
        if (index < UserMessUntil.getPetListTable().size()) {
            return (RoleSummoning)UserMessUntil.getPetListTable().get(index);
        }
        return null;
    }
    
    public Lingbao getBao(int index) {
        index += (this.pageNumber - 1) * 6;
        if (index < RoleLingFa.getRoleLingFa().lingchangelist().size()) {
            return (Lingbao)RoleLingFa.getRoleLingFa().lingchangelist().get(index);
        }
        return null;
    }
    
    public goodbtn[] getBtnRights() {
        return this.btnRights;
    }
    
    public int getPageNumber() {
        return this.pageNumber;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    static {
        stallimg = new ImageIcon("inkImg/background/S341.png");
    }
    
    private class ChangePageNumberBtn extends MoBanBtn
    {
        private boolean isAdd;
        
        public ChangePageNumberBtn(String path, boolean isAdd) {
            super(path, 1);
            this.isAdd = isAdd;
        }
        
        @Override
        public void chooseyes() {
        }
        
        @Override
        public void chooseno() {
        }
        
        @Override
        public void nochoose(MouseEvent e) {
            if (this.isAdd) {
                if (SpotStallSellJpanel.this.totalNumber > SpotStallSellJpanel.this.pageNumber) {
                    SpotStallSellJpanel.this.pageNumber++;
                }
            }
            else if (SpotStallSellJpanel.this.pageNumber > 1) {
                SpotStallSellJpanel.this.pageNumber--;
            }
        }
    }
}
