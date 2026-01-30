package org.come.mouslisten;

import com.tool.image.ImageMixDeal;
import com.tool.role.RoleData;
import com.tool.time.Limit;
import come.tool.JDialog.TiShiUtil;
import org.apache.commons.lang.StringUtils;
import org.come.Frame.*;
import org.come.Jpanel.*;
import org.come.bean.*;
import org.come.entity.*;
import org.come.entity.Fly;
import org.come.good.*;
import org.come.model.Configure;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.thread.TimeControlRunnable;
import org.come.until.*;
import org.lottery.frame.LotteryMainFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *功能道具栏按钮
 * @author admin
 *
 */
public class BackPackMissionMouslisten implements MouseListener {

    private int index;
    private BackpackMissionJpanel backpackMissionJpanel;
    private int type;

    public BackPackMissionMouslisten(int index, BackpackMissionJpanel backpackMissionJpanel, int type) {
        this.index = index;
        this.backpackMissionJpanel = backpackMissionJpanel;
        this.type = type;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    	if(type == 1){
            // 右键点击
            if (e.getButton() == MouseEvent.BUTTON3) {
                Goodstable goodstable = GoodsListFromServerUntil.goodsBackpackMissionList.get(index);
                try {
                    ManNoCombat(goodstable, type);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        if(type == 1){
            backpackMissionJpanel.getXuanLabel().setVisible(true);
            backpackMissionJpanel.getXuanLabel().setBounds(backpackMissionJpanel.getGoodsBel()[index].getX(), backpackMissionJpanel.getGoodsBel()[index].getY(), 50,50);
        }
    }


    public void mouseEntered(MouseEvent e) {
    	if(this.type == 1) {
            if(index>GoodsListFromServerUntil.goodsBackpackMissionList.size()-1){
                return;
            }
//    		Goodstable goodstable = GoodsListFromServerUntil.goodsBackpackMissionList.get(index);
//    		if(goodstable.getType() == 502) {//如果是召唤兽卡
//				petBean petBean = UserMessUntil.getPetBean();
//				HashMap<BigDecimal, RoleSummoning> pet = petBean.getAllRoleSummoning();
//				RoleSummoning roleSummoning = pet.get(new BigDecimal(goodstable.getValue()));
//				if(roleSummoning!=null) {
//					ZhuFrame.getZhuJpanel().creatpettext(roleSummoning);
//				}else {
//					ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
//				}
//			}else {
//				ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
//			}
    	}
        if(type == 1){
            backpackMissionJpanel.getXuanLabel().setVisible(true);
            backpackMissionJpanel.getXuanLabel().setBounds(backpackMissionJpanel.getGoodsBel()[index].getX(), backpackMissionJpanel.getGoodsBel()[index].getY(), 50,50);
        }
    }

    public void mouseExited(MouseEvent e) {
    	ZhuFrame.getZhuJpanel().cleargoodtext();
    	ZhuFrame.getZhuJpanel().pettext();
        if(type == 1){
            backpackMissionJpanel.getXuanLabel().setVisible(false);
        }
    }

    public void ManNoCombat(Goodstable goodstable, long type) throws Exception {
//    	boolean gemtype = Goodtype.baoshi(type);

        //判断是否可叠加的
        boolean b = EquipTool.isEquip(goodstable.getType());


        // 使用的物品属于装备
        int EquipmentType = Goodtype.EquipmentType(type);
        if (type == 0) {// 药品使用
            int usetime = goodstable.getUsetime();
            Medicine.RedAndBlue(goodstable, goodstable.getValue());
            if (usetime != goodstable.getUsetime()) {
                GoodsMouslisten.gooduse(goodstable, 1);
                String mes = Agreement.getAgreement().rolechangeAgreement(
                        "H" + RoleData.getRoleData().getLoginResult().getHp() + "="
                                + RoleData.getRoleData().getLoginResult().getMp());
                SendMessageUntil.toServer(mes);
                PetAddPointMouslisten.getplayerValue();// 进行面板刷新
                Music.addyinxiao("战斗、站立、行走使用药品.mp3");// 药品使用监听
            }
        } else if (type == 60001 || type == 60002) {// 新手礼包使用
            org.come.good.Box.Novice(goodstable);
        } else if (type == 60004) {// 自选礼包使用
            if (GoodsListFromServerUntil.Surplussum("-1", "-1", 1) == 0) {// 先判断是否还有空闲的格子
                ZhuFrame.getZhuJpanel().addPrompt2("你的背包已满");
                return;
            }
            MyOptionalJpanel.godList = new ArrayList<>();
            for(int i =0;i<goodstable.getValue().split("\\|").length;i++) {
                MyOptionalJpanel.godList.add(goodstable.getValue().split("\\|")[i]);
            }
            MyOptionalJpanel.zhjgood = goodstable;
            FormsManagement.showForm(3015);
            MyOptionalJframe.getRankingListJframe();
            MyOptionalJframe.getMyOptionalJpanel().showViewData();
            goodstable.goodxh(1);
        } else if (type == 2114) {// 星梦石使用
//        	ZhuFrame.getZhuJpanel().addPrompt2("#R星梦石已废弃，请前往属性面板点击【#G切属性#R】重置！");
//            return;
            org.come.good.Box.xms(goodstable);
        } else if (type == 2042) {// 蟠桃使用
            Consumptions.Consumption(goodstable, goodstable.getValue());
        } else if (Goodtype.Flightchess(type)) {// 飞行旗使用
            Flight.Flightchess(goodstable, type);
        }else if (type == 1688 || type == 300 || type == 121 || type == 122 || type == 2121) {// 1688改名卡使用
            // 300修正卡
            // 121解禁
            // 122解封
            // 2121点卡充值
            ExchangeAwardJframe.getExchangeAwardJframe().getAwardJpanel().use((type == 1688 ? 0 : type == 300 ? 1 : type == 121 ? 3 : type == 2121 ? 888 : 4), goodstable.getRgid());
        } else if (type == 213) {// 特赦令使用
            Box.tesheling(goodstable);
        } else if (type >= 50 && type <= 61) {// 孩子物品
            BabyGood.BabyGoods(goodstable, type);
        } else if (type == 1003) {// 任务道具使用
            TaskGood.gainTask(goodstable);
        }
        // else if (type == 80156) {// 使用夜明珠（获得物品或触发战斗）
        // usePearl();
        // }
        else if (type == 729) {
//            PetEquip.RoleCJS(goodstable);
        } else if (type == 889) { // 通灵宝券
            usePsyVou();
        } else if (type == 60003) {
            LotteryMainFrame.getLotteryMainFrame().getLotteryMainPanel()
                    .getlotteryTypeGoods(Integer.parseInt(goodstable.getValue()));
            return;
        } else if (type == 506) { // 吉祥如意蛋
            NPCJfram.getNpcJfram().getNpcjpanel().openJxryd();
        } else if (type == 520) {
            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.starCardDeposit, goodstable, "真的要放入地煞星录吗?");
        }  else if (type == 66666) {// 神行符
            String sendmes = Agreement.getAgreement().userAgreement(goodstable.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            goodstable.goodxh(1);
        }   else if (type == 777) {// 减PK点道具
            String sendmes = Agreement.getAgreement().userAgreement(goodstable.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            goodstable.goodxh(1);
        }
        else if (Goodtype.isPalGoods(type)) {// 伙伴物品使用
            PartnerMainJpanel mainJpanel = PartnerJframe.getPartnerJframe().getPartnerMainJpanel();
            if (mainJpanel.getPalDataChooseId() < 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("请选择一个伙伴");
                return;
            }
            Pal pidGetPal = mainJpanel.pidGetPal(mainJpanel.getPalDataChooseId());
            if (pidGetPal == null) {
                return;
            }
            if (goodstable != null) {
                if (goodstable.getType() == 7500) {
                    return;
                }
                String sendmes = Agreement.getAgreement().userpalAgreement(pidGetPal.getId() + "|" + goodstable.getRgid());
                SendMessageUntil.toServer(sendmes);
            }

        } else {
            userRole(goodstable);
        }
        if (goodstable.getUsetime() <= 0) {
            GoodsListFromServerUntil.goodsBackpackMissionList.remove(goodstable);
        }
    }

    public void usePsyVou() {
        // 通灵宝券的数量
        QuackGameJframe.getQuackGameJframe().getGameJpanel().kyNum =  GoodsListFromServerUntil.goodsBackpackMissionList.get(index)
                .getUsetime();
        // 向服务器获取上面一排五个的图片信息
        String sendMes = Agreement.getFiveMsgAgreement("G");
        SendMessageUntil.toServer(sendMes);
    }



    public void userRole(Goodstable good) {
        // 消耗物品的属性
        Limit limit = new Limit();
        long type = good.getType();
        if (type == 100 || type == 888 || type == 2041||type==935||type==936 || type==2126 ) {
            long[] xiaohao = AnalysisString.xiaohao(good.getValue());
            if (xiaohao[0] != 0) {
                if (RoleData.getRoleData().getLoginResult().getGold().longValue() + xiaohao[0] > Util.GoldUP
                        .longValue()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("金钱上限9999.99亿");
                    return;
                }
            }
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
        } else if (type == 7005 || type == 118 || type == 2051 || type == 2052 || type == 2057 || type == 1007) {// 获得物品类的
            if (GoodsListFromServerUntil.Surplussum("-1", "-1", 1) == 0) {// 先判断是否还有空闲的格子
                ZhuFrame.getZhuJpanel().addPrompt2("你的背包已满");
                return;
            }
            good.goodxh(1);
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
        } else if(type == 2122){
            if (GoodsListFromServerUntil.Surplussum("-1", "-1", 1) == 0) {// 先判断是否还有空闲的格子
                ZhuFrame.getZhuJpanel().addPrompt2("你的背包已满");
                return;
            }
            String[] vs = good.getValue().split("\\=");
            if (vs[0].equals("宝图")) {
                vs = vs[1].split(",");
                // 地图id 地图名 位置 位置 掉落
                int mapid = Integer.parseInt(vs[0]);
                int x = Integer.parseInt(vs[2]) / 20;
                int y = Integer.parseInt(vs[3]) / 20;
                if (mapid != Util.ditubianma) {
                    ZhuFrame.getZhuJpanel().addPrompt2("好像不是在这个场景吧");
                    return;
                }
                AthChartJframe.getAthChartJPanel();
                FormsManagement.showForm(3001);
                LaborRank laborRank = new LaborRank();
                laborRank.setRank(11);
                laborRank.setValue1("");
                AthChartJframe.getAthChartJPanel().showViewData(laborRank);
                String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
                SendMessageUntil.toServer(sendmes);
                good.goodxh(1);
            }
        }else if(type == 2123){
            if (GoodsListFromServerUntil.Surplussum("-1", "-1", 1) == 0) {// 先判断是否还有空闲的格子
                ZhuFrame.getZhuJpanel().addPrompt2("你的背包已满");
                return;
            }
            AthChartJframe.getAthChartJPanel();
            if(Util.nums!=0) {
                ZhuFrame.getZhuJpanel().addPrompt2("抽奖还没有结束，请别着急!");
                return;
            }
            FormsManagement.showForm(3001);
            LaborRank laborRank = new LaborRank();
            laborRank.setRank(11);
            laborRank.setValue1("");
            AthChartJframe.getAthChartJPanel().showViewData(laborRank);
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
        }else if(type == 2124){
            //钥匙 宝箱
            if (GoodsListFromServerUntil.Surplussum("-1", "-1", 1) == 0) {// 先判断是否还有空闲的格子
                ZhuFrame.getZhuJpanel().addPrompt2("你的背包已满");
                return;
            }
            boolean falg = GoodsListFromServerUntil.isgood(80659,1);
            if(falg) {
                GoodsListFromServerUntil.consume(80659, 1);
                String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
                SendMessageUntil.toServer(sendmes);
                good.goodxh(1);
            }else {
                ZhuFrame.getZhuJpanel().addPrompt2("金钥匙不足！");
                return;
            }
        }

        else if (type == 717) {// 坐骑卡
            int lvl = Integer.parseInt(good.getValue()) / 100;
            if (lvl <= 12) {
                if (ZhuJpanel.getListMount() != null && ZhuJpanel.getListMount().size() != 0) {
                    for (int i = ZhuJpanel.getListMount().size() - 1; i >= 0; i--) {
                        Mount mount = ZhuJpanel.getListMount().get(i);
                        if (mount.getMountid() == lvl) {
                            ZhuFrame.getZhuJpanel().addPrompt("你已有这种坐骑！");
                            return;
                        }
                    }
                }
                String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
                SendMessageUntil.toServer(sendmes);
                good.goodxh(1);
            }
        }else if (type == 7171) {// 坐架卡
            int lvl = Integer.parseInt(good.getValue()) / 100;
            if (lvl <= 12) {
                if (ZhuJpanel.getListCar() != null && ZhuJpanel.getListCar().size() != 0) {
                    for (int i = ZhuJpanel.getListCar().size() - 1; i >= 0; i--) {
                        Car mount = ZhuJpanel.getListCar().get(i);
                        if (mount.getMountid() == lvl) {
                            ZhuFrame.getZhuJpanel().addPrompt("你已有这种坐架！");
                            return;
                        }
                    }
                }
                String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
                SendMessageUntil.toServer(sendmes);
                good.goodxh(1);
            }
        } else if(type==2235){//飞行器卡
            String[] vs = good.getValue().split("\\=");
            String nane=vs[0];
            if (ZhuJpanel.getListFly()!=null&&ZhuJpanel.getListFly().size()!=0){
                for(int i=ZhuJpanel.getListFly().size()-1;i>=0;i--){
                    Fly fly=ZhuJpanel.getListFly().get(i);
                    if(nane.equals("香叶扇")) {
                        if (fly.getFlyname().contains("香叶扇") || fly.getFlyname().contains("寒露扇") || fly.getFlyname().contains("雕龙玉扇") || fly.getFlyname().contains("春秋乾坤扇") || fly.getFlyname().contains("火凰焚天扇")){
                            ZhuFrame.getZhuJpanel().addPrompt("你已有这种飞行器");
                            return;
                        }
                    }else if(nane.equals("富贵锦")) {
                        if (fly.getFlyname().contains("富贵锦") || fly.getFlyname().contains("无妄锦") || fly.getFlyname().contains("玲珑素锦") || fly.getFlyname().contains("遮霞闭月锦") || fly.getFlyname().contains("叠彩狮王锦")){
                            ZhuFrame.getZhuJpanel().addPrompt("你已有这种飞行器");
                            return;
                        }
                    }else if(nane.equals("奔云燕")) {
                        if (fly.getFlyname().contains("奔云燕") || fly.getFlyname().contains("冰霓蝶") || fly.getFlyname().contains("妙音彩鱼") || fly.getFlyname().contains("紫蜈追梦筝") || fly.getFlyname().contains("飞龙流珠席")){
                            ZhuFrame.getZhuJpanel().addPrompt("你已有这种飞行器");
                            return;
                        }
                    }else if(nane.equals("净心荷")) {
                        if (fly.getFlyname().contains("净心荷") || fly.getFlyname().contains("定魂莲") || fly.getFlyname().contains("画水尘莲") || fly.getFlyname().contains("碧影琉璃台") || fly.getFlyname().contains("金玉宝莲台")){
                            ZhuFrame.getZhuJpanel().addPrompt("你已有这种飞行器");
                            return;
                        }
                    }else if(nane.equals("轻鸿羽")) {
                        if (fly.getFlyname().contains("轻鸿羽") || fly.getFlyname().contains("藏虹羽") || fly.getFlyname().contains("百灵风羽") || fly.getFlyname().contains("青澜牵星羽") || fly.getFlyname().contains("流冥翠羽")){
                            ZhuFrame.getZhuJpanel().addPrompt("你已有这种飞行器");
                            return;
                        }
                    }else if(nane.equals("筋斗云")) {
                        if (fly.getFlyname().contains("筋斗云") || fly.getFlyname().contains("旋霜云") || fly.getFlyname().contains("万象星云") || fly.getFlyname().contains("电闪雷鸣云") || fly.getFlyname().contains("五色祥云")){
                            ZhuFrame.getZhuJpanel().addPrompt("你已有这种飞行器");
                            return;
                        }
                    }
                }
            }
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
        }else if (type == 2525) {// 挑战卡
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
        }else if (type == 66668 || type == 66669) {// 月卡
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
        } else if (type == 924) {// 月卡
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
        } else if (type == 780) {// 全服三倍经验
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
        }else if (type == 926) {// 技能卡
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
        } else if (Goodtype.TimingGood(type) ||Goodtype.Medicine(type) || Goodtype.BlueBack(type) || type == 88 || type == 99 || type == 111
                || type == 113) {// 时效类相关物品
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
        }
        else if (type == 1006) {// 徽章使用
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
        }else if (type == 6699) {//包裹扩展卡
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            int attachPack = loginResult.getAttachPack();
            if (attachPack >= 1) {
                ZhuFrame.getZhuJpanel().addPrompt2(good.getGoodsname() + "最多只能使用一次");
                return;
            }
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
            loginResult.setAttachPack(++attachPack);
            GoodsListFromServerUntil.GoodExpansion(loginResult.getTurnAround() , loginResult.getAttachPack());
        }else if(type == 9002) { //法门修炼丹
//            if (!AnalysisString.lvlfull(ImageMixDeal.userimg.getRoleShow().getGrade(), "1转120")) {
//                ZhuFrame.getZhuJpanel().addPrompt2("飞升后才能学习法门");
//                return;
//            }
//            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
//            if (loginResult.getFmsld()>10000) {
//                ZhuFrame.getZhuJpanel().addPrompt2("法门熟练度已满");
//                return;
//            }
//            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
//            SendMessageUntil.toServer(sendmes);
//            good.goodxh(1);
//            loginResult.setFmsld(loginResult.getFmsld()+20);
//            SkillSMGatePanel2.getSkillValue()[0].setText(loginResult.getFmsld()+"/10000");
//            SkillSMGatePanel2.getSkillValue()[1].setText(loginResult.getFmsld()+"/10000");
//            SkillSMGatePanel2.getSkillValue()[2].setText(loginResult.getFmsld()+"/10000");
//            SkillSMGatePanel2.getSkillValue()[3].setText(loginResult.getFmsld()+"/10000");
//            SkillSMGatePanel2.getSkillValue()[4].setText(loginResult.getFmsld()+"/10000");
//            SkillSMGatePanel2.getSkillValue()[5].setText(loginResult.getFmsld()+"/10000");
//            ZhuFrame.getZhuJpanel().addPrompt2("法门熟练度+20");
        }else if(type == 2120) {
            LoginResult loginResult = RoleData.getRoleData().getLoginResult();
            ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
            Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
            Configure configure = item.get(new BigDecimal(1));

            if(loginResult.getGradeincrease()!=null) {
                if(loginResult.getGradeincrease()>=Integer.parseInt(configure.getRwdjsx())){
                    ZhuFrame.getZhuJpanel().addPrompt2("等级突破丹最大可服用#G"+configure.getRwdjsx()+"#Y次！#R您已达到上限！");
                    return;
                }
            }
            if(loginResult.getGradeincrease()==null) {
                loginResult.setGradeincrease(0);
            }
            loginResult.setGradeincrease(loginResult.getGradeincrease()+1);
            good.goodxh(1);
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
        }else if(type == 776) { //右击碎片合成召唤兽
            boolean flag = GoodsListFromServerUntil.isgood(good.getGoodsid().intValue(), 30);
            if(!flag) {
                ZhuFrame.getZhuJpanel().addPrompt2(good.getGoodsname()+"不足30个，无法合成");
                return;
            }
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
        }else if(type == 7878) {
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
        }else if(type == 60027) {
            if(!RoleData.getRoleData().getLoginResult().getMapid().toString().equals("1207")) {
                ZhuFrame.getZhuJpanel().addPrompt2("请前往长安桥燃放！");
                return;
            }
            if (GoodsListFromServerUntil.Surplussum("-1", "-1", 1) == 0) {// 先判断是否还有空闲的格子
                ZhuFrame.getZhuJpanel().addPrompt2("你的背包已满");
                return;
            }
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
        }else if(type == 60028) {
            if(!RoleData.getRoleData().getLoginResult().getMapid().toString().equals("1193")) {
                ZhuFrame.getZhuJpanel().addPrompt2("请前往长安东种植！");
                return;
            }
            String sendmes = Agreement.getAgreement().userAgreement(good.getRgid().toString());
            SendMessageUntil.toServer(sendmes);
            good.goodxh(1);
        }
    }
}
