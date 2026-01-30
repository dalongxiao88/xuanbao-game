package org.come.control;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.List;
import org.come.until.LotteryFromServerUntil;
import java.util.Random;
import org.come.entity.LotteryGood;
import java.util.ArrayList;
import org.come.until.GsonUtil;
import org.come.bean.LotteryBean;
import org.come.action.FromServerAction;

public class LotteryControl implements FromServerAction
{
    @Override
    public void controlMessFromServer(String mes, String type) {
        if (mes.startsWith("GETITEMS")) {
            String[] result = mes.split("\\|");
            if (result[1] != null) {
                LotteryBean list = (LotteryBean)GsonUtil.getGsonUtil().getgson().fromJson(result[1], LotteryBean.class);
                List<LotteryGood> olist = new ArrayList<>();
                List<LotteryGood> nlist = new ArrayList<>();
                LotteryGood goodstable1 = new LotteryGood();
                LotteryGood goodstable2 = new LotteryGood();
                LotteryGood goodstable3 = new LotteryGood();
                LotteryGood goodstable4 = new LotteryGood();
                LotteryGood goodstable5 = new LotteryGood();
                LotteryGood goodstable6 = new LotteryGood();
                goodstable1.setLotterytype(2);
                goodstable1.setLotterrate(100);
                goodstable1.setGoodsname("X2");
                goodstable1.setLotterymultiple(2);
                goodstable1.setSkin("FF002");
                goodstable1.setLotterylevel(1);
                goodstable1.setLotterybg("FFBG01");
                olist.add(goodstable1);
                goodstable2.setLotterytype(2);
                goodstable2.setLotterrate(100);
                goodstable2.setGoodsname("X3");
                goodstable2.setLotterymultiple(3);
                goodstable2.setSkin("FF003");
                goodstable2.setLotterylevel(1);
                goodstable2.setLotterybg("FFBG01");
                olist.add(goodstable2);
                goodstable3.setLotterytype(2);
                goodstable3.setLotterrate(100);
                goodstable3.setGoodsname("X5");
                goodstable3.setLotterymultiple(5);
                goodstable3.setSkin("FF005");
                goodstable3.setLotterylevel(2);
                goodstable3.setLotterybg("FFBG02");
                olist.add(goodstable3);
                goodstable4.setLotterytype(2);
                goodstable4.setLotterrate(60);
                goodstable4.setGoodsname("X10");
                goodstable4.setLotterymultiple(10);
                goodstable4.setSkin("FF010");
                goodstable4.setLotterylevel(3);
                goodstable4.setLotterybg("FFBG03");
                olist.add(goodstable4);
                goodstable5.setLotterytype(2);
                goodstable5.setGoodsname("X15");
                goodstable5.setLotterrate(5);
                goodstable5.setLotterymultiple(15);
                goodstable5.setSkin("FF015");
                goodstable5.setLotterylevel(4);
                goodstable5.setLotterybg("FFBG04");
                olist.add(goodstable5);
                goodstable6.setLotterytype(2);
                goodstable6.setLotterrate(1);
                goodstable6.setGoodsname("X20");
                goodstable6.setSkin("FF020");
                goodstable6.setLotterylevel(4);
                goodstable6.setLotterybg("FFBG04");
                goodstable6.setLotterymultiple(20);
                olist.add(goodstable6);
                Random random = new Random();
                while (nlist.size() < 3) {
                    int idx = random.nextInt(olist.size());
                    if (random.nextInt(100) <= ((LotteryGood)olist.get(idx)).getLotterrate()) {
                        LotteryGood lotteryGood = (LotteryGood)olist.get(idx);
                        olist.remove(lotteryGood);
                        nlist.add(lotteryGood);
                    }
                }
                list.getList().addAll(nlist);
                if (list != null && list.getList() != null && list.getList().size() > 0) {
                    for (LotteryGood lotteryGood : list.getList()) {
                        if (lotteryGood.getLotterytype() != 2) {
                            lotteryGood.setLotterytype(1);
                            lotteryGood.setLotterylevel(lotteryGood.getQuality().intValue());
                            switch (lotteryGood.getLotterylevel()) {
                                case 1: {
                                    lotteryGood.setLotterybg("FFBG01");
                                    continue;
                                }
                                case 2: {
                                    lotteryGood.setLotterybg("FFBG02");
                                    continue;
                                }
                                case 3: {
                                    lotteryGood.setLotterybg("FFBG03");
                                    continue;
                                }
                                case 4: {
                                    lotteryGood.setLotterybg("FFBG04");
                                    continue;
                                }
                            }
                        }
                    }
                }
                LotteryFromServerUntil.setGoods(list.getList());
            }
        }
        else if (mes.startsWith("GETITEMSTWO")) {
            String[] result = mes.split("\\|");
            if (result[1] != null) {
                LotteryBean list = (LotteryBean)GsonUtil.getGsonUtil().getgson().fromJson(result[1], LotteryBean.class);
                List<LotteryGood> olist = new ArrayList<>();
                List<LotteryGood> nlist = new ArrayList<>();
                LotteryGood goodstable1 = new LotteryGood();
                LotteryGood goodstable2 = new LotteryGood();
                LotteryGood goodstable3 = new LotteryGood();
                LotteryGood goodstable4 = new LotteryGood();
                LotteryGood goodstable5 = new LotteryGood();
                LotteryGood goodstable6 = new LotteryGood();
                goodstable1.setLotterytype(2);
                goodstable1.setLotterrate(100);
                goodstable1.setGoodsname("X2");
                goodstable1.setLotterymultiple(2);
                goodstable1.setSkin("FF002");
                goodstable1.setLotterylevel(1);
                goodstable1.setLotterybg("FFBG01");
                olist.add(goodstable1);
                goodstable2.setLotterytype(2);
                goodstable2.setLotterrate(100);
                goodstable2.setGoodsname("X3");
                goodstable2.setLotterymultiple(3);
                goodstable2.setSkin("FF003");
                goodstable2.setLotterylevel(1);
                goodstable2.setLotterybg("FFBG01");
                olist.add(goodstable2);
                goodstable3.setLotterytype(2);
                goodstable3.setLotterrate(100);
                goodstable3.setGoodsname("X5");
                goodstable3.setLotterymultiple(5);
                goodstable3.setSkin("FF005");
                goodstable3.setLotterylevel(2);
                goodstable3.setLotterybg("FFBG02");
                olist.add(goodstable3);
                goodstable4.setLotterytype(2);
                goodstable4.setLotterrate(60);
                goodstable4.setGoodsname("X10");
                goodstable4.setLotterymultiple(10);
                goodstable4.setSkin("FF010");
                goodstable4.setLotterylevel(3);
                goodstable4.setLotterybg("FFBG03");
                olist.add(goodstable4);
                goodstable5.setLotterytype(2);
                goodstable5.setGoodsname("X15");
                goodstable5.setLotterrate(5);
                goodstable5.setLotterymultiple(15);
                goodstable5.setSkin("FF015");
                goodstable5.setLotterylevel(4);
                goodstable5.setLotterybg("FFBG04");
                olist.add(goodstable5);
                goodstable6.setLotterytype(2);
                goodstable6.setLotterrate(1);
                goodstable6.setGoodsname("X20");
                goodstable6.setSkin("FF020");
                goodstable6.setLotterylevel(4);
                goodstable6.setLotterybg("FFBG04");
                goodstable6.setLotterymultiple(20);
                olist.add(goodstable6);
                Random random = new Random();
                while (nlist.size() < 3) {
                    int idx = random.nextInt(olist.size());
                    if (random.nextInt(100) <= ((LotteryGood)olist.get(idx)).getLotterrate()) {
                        LotteryGood lotteryGood = (LotteryGood)olist.get(idx);
                        olist.remove(lotteryGood);
                        nlist.add(lotteryGood);
                    }
                }
                list.getList().addAll(nlist);
                if (list != null && list.getList() != null && list.getList().size() > 0) {
                    for (LotteryGood lotteryGood : list.getList()) {
                        if (lotteryGood.getLotterytype() != 2) {
                            lotteryGood.setLotterytype(1);
                            lotteryGood.setLotterylevel(lotteryGood.getQuality().intValue());
                            switch (lotteryGood.getLotterylevel()) {
                                case 1: {
                                    lotteryGood.setLotterybg("FFBG01");
                                    continue;
                                }
                                case 2: {
                                    lotteryGood.setLotterybg("FFBG02");
                                    continue;
                                }
                                case 3: {
                                    lotteryGood.setLotterybg("FFBG03");
                                    continue;
                                }
                                case 4: {
                                    lotteryGood.setLotterybg("FFBG04");
                                    continue;
                                }
                            }
                        }
                    }
                }
                LotteryFromServerUntil.setGoods(list.getList());
            }
        }
    }
    
    public static Set<Integer> generateRandomNumbers(int n, int max) {
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        while (set.size() < n) {
            int num = random.nextInt(max) + 1;
            set.add(Integer.valueOf(num));
        }
        return set;
    }
}
