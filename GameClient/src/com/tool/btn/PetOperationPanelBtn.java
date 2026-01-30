package com.tool.btn;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.Map;

import javax.swing.SwingConstants;

import come.tool.JDialog.TiShiUtil;
import org.apache.commons.lang.StringUtils;
import org.come.Frame.AlchemyJframe;
import org.come.Frame.OptionsJframe;
import org.come.Frame.SpiritualJframe;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.*;
import org.come.bean.ConfigureBean;
import org.come.bean.SummonPetBean;
import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;
import org.come.model.Configure;
import org.come.mouslisten.GoodsMouslisten;
import org.come.mouslisten.PetAddPointMouslisten;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.*;

import com.tool.pet.PetProperty;
import com.tool.tcpimg.UIUtils;
import come.tool.Fighting.FightingMixDeal;
import come.tool.handle.HandleState;

public class PetOperationPanelBtn extends MoBanBtn {
    private int caozuo;
    private AlchemyJpanel alchemyJpanel;
    private AlchemyCardJpanel alchemyCardJpanel;

    public PetOperationPanelBtn(String iconpath, int type, String text) {
        super(iconpath, type);
        this.setText(text);
        setFont(UIUtils.TEXT_FONT1);
        setForeground(Color.orange);
        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
    }

    public PetOperationPanelBtn(final String iconpath, final int type, final Color[] colors, final Font font, final String text, final int caozuo) {
        super(iconpath, type, colors);
        this.caozuo = caozuo;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }


    public PetOperationPanelBtn(String iconpath, int type, int p, AlchemyCardJpanel alchemyCardJpanel) {
        super(iconpath, type);
        // TODO Auto-generated constructor stub
        this.caozuo = p;
        this.alchemyCardJpanel = alchemyCardJpanel;

        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);

    }

    public PetOperationPanelBtn(String iconpath, int type, Color[] colors, Font font, String text) {
        super(iconpath, type, colors);
        this.setText(text);
        setFont(font);
        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
    }

    @Override
    public void chooseyes() {
        // TODO Auto-generated method stub

    }

    @Override
    public void chooseno() {
        // TODO Auto-generated method stub

    }

    @Override
    public void nochoose(MouseEvent e) {
        if (this.getText().equals("炼妖") && FightingMixDeal.State == HandleState.USUAL) {
            artificePet();
        } else if (this.caozuo == 11 && FightingMixDeal.State == 0) {
            this.artificePet3();
        } else if (caozuo == 101 || caozuo == 100) {
            alchemyCardJpanel.qh(caozuo);
        }
    }

    public void artificePet3() {
        if (UserStallUntil.isStall()) {
            return;
        }
        if ( UserMessUntil.getChosePetMes().getGrade()<=485) {
            ZhuFrame.getZhuJpanel().addPrompt2("召唤兽等级不够,需三转120以上方可灵返。");
            return;
        }
        final SpiritualJpanel ss = AlchemyJframe.getAlchemyjframe().getAlchemyCardJpanel().getAlchemyMainJpanel().getSpiritualJpanel();
        if (ss.getListpet().getSelectedIndex() == -1) {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择你要的召唤兽！");
            return;
        } else {
            final Goodstable goodstable = ZhuJpanel.getGoodstableAlf();
            if (goodstable == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("请放入对应的物品");
                return;
            } else {
                if (GoodsListFromServerUntil.isExist(goodstable)) {
                    return;
                } else {
                    int i = 0;
                    while (i < ss.getLabPetskills().length) {
                        if (ss.getPetskillID() == null) {
                            ZhuFrame.getZhuJpanel().addPrompt("技能错误重新选择");
                            return;
                        } else {
                            ++i;
                        }
                    }
                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.Lingua, UserMessUntil.getChosePetMes(), "#W确定要将选中的技能#G#W用于灵返吗？消耗亲密20W。#R灵返后技能将消失?");
                    return;
                }
            }
        }
    }


    /**
     * 炼妖的方法
     */
    public void artificePet() {
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = item.get(new BigDecimal(1));


        if (AlchemyJframe.getAlchemyjframe().getalchemyJpanel().getListpet().getSelectedIndex() == -1) {
            ZhuFrame.getZhuJpanel().addPrompt2("请选择你要炼化的召唤兽！");
            return;
        }
        Goodstable goodstable = ZhuJpanel.getGoodstableAl();
        if (goodstable == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("你还没选中物品");
            return;
        } else {
            if (GoodsListFromServerUntil.isExist(goodstable)) {
                return;
            }
            if (goodstable.getGoodlock() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt("该物品已被加锁");
                return;
            }
        }
        int index = AlchemyJframe.getAlchemyjframe().getalchemyJpanel().getListpet().getSelectedIndex();
        RoleSummoning pet = UserMessUntil.getPetListTable().get(index);
        if (goodstable.getType() == 701) {//金柳露
            jll(pet, goodstable);
            ZhuJpanel.setGoodstableAl(null);
            AlchemyJframe.getAlchemyjframe().getalchemyJpanel().getLabRefined().setIcon(null);
            return;
        } else if (goodstable.getType() == 713) {
            // 卧龙丹
            pet.setAlchemynum(0);
            pet.setLyk("");
            ZhuFrame.getZhuJpanel().addPrompt("恭喜您，您的召唤兽炼化成功！！！");
        } else if (goodstable.getType() == 714) {
            if (StringUtils.isNotBlank(goodstable.getValue())) {
                pet.setResistance(goodstable.getValue());
            } else {
                // 龙凤石
                String[] kxs = {"抗混乱=30", "抗封印=30", "抗昏睡=30", "抗中毒=30", "物理吸收率=30", "抗风=30", "抗火=30", "抗水=30", "抗雷=30",
                        "抗鬼火=30", "抗遗忘=30"};
                int p = Util.random.nextInt(kxs.length);
                int p2 = Util.random.nextInt(kxs.length);
                while (p2 == p) {
                    p2 = Util.random.nextInt(kxs.length);
                }
                pet.setResistance(kxs[p] + "|" + kxs[p2]);
            }

            ZhuFrame.getZhuJpanel().addPrompt("恭喜您，您的召唤兽炼化成功！！！");
        } else if (pet.getAlchemynum() >= Integer.parseInt(configure.getLyssx())) {
            ZhuFrame.getZhuJpanel().addPrompt2("召唤兽" + pet.getSummoningname() + "的炼妖次数已经超过" + configure.getLyssx() + "次！");
            return;
        } else {
            String Arraystoneat[] = goodstable.getValue().split("\\|");
            if (pet.getLyk() != null && !pet.getLyk().equals("")) {
                for (int i = 1; i < Arraystoneat.length; i++) {
                    String values = "";
                    int have = 0;
                    String Arraystone[] = Arraystoneat[i].split("=");
                    String Arraypetat[] = pet.getLyk().split("\\|");
                    for (int j = 0; j < Arraypetat.length; j++) {
                        String Arraypet[] = Arraypetat[j].split("=");
                        if (Arraypet[0].equals(Arraystone[0])) {
                            Arraypet[1] = (new StringBuilder(String.valueOf(Double.parseDouble(Arraypet[1])
                                    + Double.parseDouble(Arraystone[1])))).toString();
                            String value = (new StringBuilder(String.valueOf(Arraypet[0]))).append("=")
                                    .append(Arraypet[1]).append("|").toString();
                            values = (new StringBuilder(String.valueOf(values))).append(value).toString();
                            have = 1;
                        } else {
                            String value = (new StringBuilder(String.valueOf(Arraypet[0]))).append("=")
                                    .append(Arraypet[1]).append("|").toString();
                            values = (new StringBuilder(String.valueOf(values))).append(value).toString();
                        }
                    }

                    if (have == 1)
                        pet.setLyk(values);
                    else if (have == 0)
                        pet.setLyk((new StringBuilder(String.valueOf(values))).append(Arraystone[0]).append("=")
                                .append(Arraystone[1]).append("|").toString());
                }

            } else {
                String values = "";
                for (int i = 1; i < Arraystoneat.length; i++) {
                    String Arraystone[] = Arraystoneat[i].split("=");
                    String value = (new StringBuilder(String.valueOf(Arraystone[0]))).append("=").append(Arraystone[1])
                            .append("|").toString();
                    values = (new StringBuilder(String.valueOf(values))).append(value).toString();
                }

                pet.setLyk(values);
            }
            pet.setAlchemynum(pet.getAlchemynum() + 1);
            ZhuFrame.getZhuJpanel().addPrompt("恭喜您，您的召唤兽炼化成功！！！");
        }
        goodstable.setUsetime(goodstable.getUsetime() - 1);
        GoodsMouslisten.gooduse(goodstable, 1);
        if (goodstable.getUsetime() <= 0) {
            GoodsListFromServerUntil.Deletebiaoid(goodstable.getRgid());
        }
        SendRoleAndRolesummingUntil.sendRoleSumming(pet);
        ZhuJpanel.setGoodstableAl(null);
        AlchemyJframe.getAlchemyjframe().getalchemyJpanel().getLabRefined().setIcon(null);
        // 更新面板
        PetAddPointMouslisten.showPetValue();
        // 刷新召唤兽抗性面板
        if (FormsManagement.getframe(58).isVisible()) {
            PetProperty.ShowQl(UserMessUntil.getChosePetMes());
        }
    }

    /**
     * 金柳露
     */
    public void jll(RoleSummoning pet, Goodstable good) {
        if (pet.getSsn() != null && !pet.getSsn().equals("0")) {
            ZhuFrame.getZhuJpanel().addPrompt2("这只召唤兽无法使用金柳露");
            return;
        } else if (pet.getTurnRount() != 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("未转的宝宝才可以使用金柳露");
            return;
        }
        SummonPetBean summonPetBean = new SummonPetBean();
        summonPetBean.setOpertype(1);// 使用金柳露
        summonPetBean.setPetid(pet.getSid());
        summonPetBean.setGoodid(good.getRgid());
        String mes = Agreement.getAgreement().summonpetAgreement(GsonUtil.getGsonUtil().getgson().toJson(summonPetBean));
        SendMessageUntil.toServer(mes);// 向服务器发送信息
    }
}
