package com.tool.btn;

import java.time.LocalTime;
import java.util.Calendar;
import org.come.bean.AuctionExchange;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import com.tool.role.RoleData;
import java.math.BigDecimal;
import org.come.Frame.AuctionGoodsExchangeJframe;
import org.come.until.Music;
import java.util.ArrayList;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import java.awt.Color;
import com.tool.tcpimg.UIUtils;
import org.come.bean.AuctionGoodsForGoodsBean;
import org.come.bean.GoodSEarchBean;
import org.come.Jpanel.AuctionGoodsExchangeJpanel;

public class AuctionGoodsExchangeBtn extends MoBanBtn
{
    private final AuctionGoodsExchangeJpanel goodJpanel;
    private final int caozuo;
    GoodSEarchBean goods;
    public static AuctionGoodsForGoodsBean goodsbean;
    
    public AuctionGoodsExchangeBtn(String iconpath, int type, String text, int caozuo, AuctionGoodsExchangeJpanel goodJpanel) {
        super(iconpath, type);
        this.goods = new GoodSEarchBean();
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT1);
        this.setForeground(Color.yellow);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.goodJpanel = goodJpanel;
        this.caozuo = caozuo;
    }
    
    public AuctionGoodsExchangeBtn(String iconpath, int type, String text, int caozuo, Color[] colors, AuctionGoodsExchangeJpanel goodJpanel) {
        super(iconpath, type, colors);
        this.goods = new GoodSEarchBean();
        this.setText(text);
        this.setFont(UIUtils.TEXT_FONT81);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.goodJpanel = goodJpanel;
        this.caozuo = caozuo;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        int type = -1;
        switch (this.caozuo) {
            case 1: {
                String mes = this.goodJpanel.getSrmc().getText();
                if (mes == null || mes == "") {
                    ZhuFrame.getZhuJpanel().addPrompt2("搜索名称不能为空!!");
                    return;
                }
                this.goodJpanel.setGoodgodbean(new ArrayList<>());
                this.goodJpanel.setSearch(mes);
                this.goodJpanel.initGoodsList();
                Music.addyinxiao("打开窗口.mp3");
                break;
            }
            case 2: {
                AuctionExchange auctionExchange = AuctionGoodsExchangeBtn.goodsbean.getAuctionExchange();
                String res = withinTheTimeFrame(auctionExchange);
                if (res != null) {
                    ZhuFrame.getZhuJpanel().addPrompt(res);
                    return;
                }
                Integer moneyType = auctionExchange.getMoneyType();
                BigDecimal money = new BigDecimal(AuctionGoodsExchangeJframe.getGoodDetailedJframe().getAuctionGoodsExchangeJpanel().getOffer().getText().replace(",", ""));
                if ((int)moneyType == 1) {
                    if (money.longValue() > RoleData.getRoleData().getLoginResult().getGold().longValue()) {
                        ZhuFrame.getZhuJpanel().addPrompt2("你的银两不足以支付竞拍费用");
                        return;
                    }
                }
                else if (money.longValue() > RoleData.getRoleData().getLoginResult().getCodecard().longValue()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你的银两不足以支付竞拍费用");
                    return;
                }
                String mes2 = Agreement.getAgreement().auctionGoods(auctionExchange.geteId() + "=" + money);
                SendMessageUntil.toServer(mes2);
                break;
            }
            case 3: {
                type = 0;
            }
            case 4: {
                if (type == -1) {
                    type = 1;
                }
            }
            case 5: {
                if (type == -1) {
                    type = 2;
                }
            }
            case 6: {
                if (type == -1) {
                    type = 3;
                }
            }
            case 7: {
                if (type == -1) {
                    type = 4;
                }
            }
            case 8: {
                if (type == -1) {
                    type = 5;
                }
                this.goodJpanel.setType(type);
                this.goodJpanel.setGoodgodbean(new ArrayList<>());
                this.goodJpanel.initGoodsList();
                break;
            }
        }
    }
    
    public static String withinTheTimeFrame(AuctionExchange auctionExchange) {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(7);
        String[] days = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        String today = days[dayOfWeek - 1];
        if (!auctionExchange.getWeek().contains(today)) {
            return "当前物品还未开始竞拍#76!!";
        }
        String time = auctionExchange.getTime();
        String[] v = time.split("-");
        LocalTime startTime = LocalTime.parse(v[0]);
        LocalTime endTime = LocalTime.parse(v[1]);
        LocalTime currentTime = LocalTime.now();
        if (!currentTime.isAfter(startTime) || !currentTime.isBefore(endTime)) {
            return "当前物品还未开始竞拍#76!!";
        }
        return null;
    }
}
