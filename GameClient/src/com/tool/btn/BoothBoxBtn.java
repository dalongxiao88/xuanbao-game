package com.tool.btn;

import org.come.bean.RoleShow;
import com.tool.Stall.Commodity;
import com.tool.Stall.Stall;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import com.tool.Stall.StallBean;
import org.come.until.Util;
import come.tool.Fighting.FightingMixDeal;
import com.tool.image.ImageMixDeal;
import org.come.Frame.TradeJframe;
import org.come.entity.RoleSummoning;
import org.come.until.UserMessUntil;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.FormsManagement;
import org.come.Frame.ZhuFrame;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.come.Jpanel.BoothBoxJpanel;

public class BoothBoxBtn extends MoBanBtn
{
    private final BoothBoxJpanel boxJpanel;
    
    public BoothBoxBtn(String path, int type, Color[] colors, String text, BoothBoxJpanel boxJpanel) {
        super(path, type, colors);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.boxJpanel = boxJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        Stall stall = this.boxJpanel.getStall();
        if ("上架".equals(this.getText())) {
            if (stall.getId() > 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你已经在摆摊了");
                return;
            }
            if (FormsManagement.getframe(14).isVisible()) {
                return;
            }
            this.boxJpanel.SJ();
        }
        else if ("下架".equals(this.getText())) {
            if (stall.getId() > 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你已经在摆摊了");
                return;
            }
            Commodity commodity = this.boxJpanel.getCommodity();
            if (commodity == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("没有选中的商品");
                return;
            }
            int i = this.boxJpanel.getStall().Buy(commodity);
            if (i == -1) {
                ZhuFrame.getZhuJpanel().addPrompt2("不是已上架的商品");
            }
            else {
                if (commodity.getGood() != null) {
                    this.boxJpanel.GoodsListLabel[i].setIcon(null);
                    this.boxJpanel.Usetime[i] = null;
                    GoodsListFromServerUntil.stall2(commodity.getGood());
                }
                else if (commodity.getPet() != null) {
                    this.boxJpanel.PetsListLabel[i].setIcon(null);
                    int p = 0;
                    for (int j = UserMessUntil.getPetListTable().size() - 1; j >= 0; --j) {
                        if (((RoleSummoning)UserMessUntil.getPetListTable().get(j)).getSid().compareTo(commodity.getPet().getSid()) == 0) {
                            p = -1;
                        }
                    }
                    if (p != -1) {
                        UserMessUntil.getPetListTable().add(commodity.getPet());
                        TradeJframe.getTradejframe().getTradejpanel().getModelname().addElement(commodity.getPet().getSummoningname());
                    }
                }
                this.boxJpanel.XZBuy(null);
            }
        }
        else if ("摆摊".equals(this.getText())) {
            RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
            if ((long)roleShow.getMapid() != 1236L) {
                ZhuFrame.getZhuJpanel().addPrompt("只能在洛阳集市摆摊");
                return;
            }
            if (roleShow.getX() < 5977 || roleShow.getX() > 8290) {
                ZhuFrame.getZhuJpanel().addPrompt("该区域不可以摆摊！");
                return;
            }
            if (roleShow.getY() < 3099 || roleShow.getY() > 4174) {
                ZhuFrame.getZhuJpanel().addPrompt("该区域不可以摆摊！");
                return;
            }
            if (roleShow.getTroop_id() != null || FormsManagement.getframe(14).isVisible() || FightingMixDeal.State != 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("当前状态不能摆摊！");
            }
            if (stall.getId() > 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("你已经在摆摊了");
                return;
            }
            if ((int)roleShow.getGrade() <= 50) {
                ZhuFrame.getZhuJpanel().addPrompt2("50级以上可以摆摊");
                return;
            }
            if (roleShow.getMount_id() != 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("在摆摊了还要骑着坐骑干什么");
                return;
            }
            stall.setMapid(Util.ditubianma);
            stall.setRoleid(roleShow.getRole_id());
            stall.setRole(roleShow.getRolename());
            if (this.boxJpanel.getTw() != null && this.boxJpanel.getTw() != "") {
                stall.setStall(this.boxJpanel.getTw());
            }
            else {
                stall.setStall(roleShow.getRolename() + "的摊位");
            }
            stall.setState(StallBean.PREPARE);
            stall.setId(1);
            stall.setX(roleShow.getX() - 50);
            stall.setY(roleShow.getY() - 135);
            String sendMes = Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall));
            SendMessageUntil.toServer(sendMes);
            this.setText("收摊");
            FormsManagement.HideForm(15);
            FormsManagement.HideForm(16);
        }
        else if ("收摊".equals(this.getText())) {
            RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
            stall.setMapid(Util.ditubianma);
            stall.setRoleid(roleShow.getRole_id());
            stall.setRole(roleShow.getRolename());
            stall.setStall(this.boxJpanel.getTw());
            stall.setState(StallBean.NO);
            String sendMes = Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(stall));
            SendMessageUntil.toServer(sendMes);
            this.setText("摆摊");
        }
    }
}
