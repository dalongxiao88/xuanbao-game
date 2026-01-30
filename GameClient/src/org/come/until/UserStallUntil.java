package org.come.until;

import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import com.tool.Document.RichDocument;
import com.tool.tcpimg.InputBean;
import org.come.model.Gamemap;
import org.come.bean.Coordinates;
import org.come.bean.RoleShow;
import come.tool.Fighting.FightingMixDeal;
import com.tool.image.ImageMixDeal;
import org.come.model.Lingbao;
import org.come.entity.RoleSummoning;
import org.come.entity.Goodstable;
import java.math.BigDecimal;
import com.tool.role.RoleLingFa;
import com.tool.Stall.CommodityBean;
import org.come.Frame.ZhuFrame;
import org.come.Frame.spot.SpotBoxJframe;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.Frame.spot.SpotBuyBoxJframe;
import com.tool.Stall.StallBean;
import java.util.List;
import com.tool.Stall.Stall;

public class UserStallUntil
{
    public static final int offsetX = 50;
    public static final int offsetY = 135;
    private static Stall stall;
    private static List<Integer> followList;
    private static StallBean stallBean;
    
    private UserStallUntil() {
    }
    
    public static Stall getStall() {
        if (UserStallUntil.stall == null) {
            setStall(new Stall());
        }
        return UserStallUntil.stall;
    }
    
    public static void setStall(Stall stall) {
        UserStallUntil.stall = stall;
    }
    
    public static Stall copyStall() {
        Stall stall = new Stall();
        stall.setId(UserStallUntil.stall.getId());
        stall.setMapid(UserStallUntil.stall.getMapid());
        stall.setRoleid(UserStallUntil.stall.getRoleid());
        stall.setRole(UserStallUntil.stall.getRole());
        stall.setState(UserStallUntil.stall.getState());
        return stall;
    }
    
    public static void close(StallBean bean) {
        if (SpotBuyBoxJframe.getSpotBuyBoxJframe().getSpotBuyBoxJpanel().closeStall(bean.getId())) {
            FormsManagement.HideForm(35);
        }
    }
    
    public static void updateStall() {
        SendMessageUntil.toServer(Agreement.getAgreement().stallgetAgreement(UserStallUntil.stall.getId() + ""));
    }
    
    public static void updateStall(Stall stall) {
        UserStallUntil.stall.setId(stall.getId());
        UserStallUntil.stall.setState(stall.getState());
        UserStallUntil.stall.setStall(stall.getStall());
        UserStallUntil.stall.setGoodsList(stall.getGoodsList());
        UserStallUntil.stall.setPetList(stall.getPetList());
        UserStallUntil.stall.setBaoList(stall.getBaoList());
        UserStallUntil.stall.setCollectGoodsList(stall.getCollectGoodsList());
        UserStallUntil.stall.setCommodityId(stall.getCommodityId());
        UserStallUntil.stall.setSellMsgs(stall.getSellMsgs());
        UserStallUntil.stall.setCollectMsg(stall.getCollectMsg());
        SpotBoxJframe.getSpotBoxJframe().getSpotBoxJpanel().updateCommoditys();
        updateStallState();
    }
    
    public static boolean isStall() {
        return isStall(true);
    }
    
    public static boolean isStall(boolean isMsg) {
        if (UserStallUntil.stall != null && UserStallUntil.stall.getState() == StallBean.NO) {
            if (isMsg) {
                ZhuFrame.getZhuJpanel().addPrompt("摆摊中，不可操作。");
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    private static void updateStallState() {
        if (UserStallUntil.stall.getState() == StallBean.OFF) {
            UserStallUntil.stall.setId(0);
            List<CommodityBean> goodsList = UserStallUntil.stall.getGoodsList();
            if (goodsList != null) {
                for (int i = 0; i < goodsList.size(); ++i) {
                    BigDecimal commodityId = ((CommodityBean)goodsList.get(i)).getCommodityId();
                    Goodstable goods = GoodsListFromServerUntil.czgood(commodityId);
                    if (goods != null) {
                        goods.setCommodityId(null);
                    }
                }
            }
            List<CommodityBean> petList = UserStallUntil.stall.getPetList();
            if (petList != null) {
                for (int j = 0; j < petList.size(); ++j) {
                    BigDecimal commodityId2 = ((CommodityBean)petList.get(j)).getCommodityId();
                    RoleSummoning pet = UserMessUntil.getPetRgid(commodityId2);
                    if (pet != null) {
                        pet.setCommodityId(null);
                    }
                }
            }
            List<CommodityBean> baoList = UserStallUntil.stall.getBaoList();
            if (baoList != null) {
                for (int k = 0; k < baoList.size(); ++k) {
                    BigDecimal commodityId3 = ((CommodityBean)baoList.get(k)).getCommodityId();
                    Lingbao lingbao = RoleLingFa.getRoleLingFa().czGBG(commodityId3);
                    if (lingbao != null) {
                        lingbao.setCommodityId(null);
                    }
                }
            }
            FormsManagement.HideForm(800);
        }
    }
    
    public static void startStall() {
        if (Util.isCanBuyOrno1()) {
            return;
        }
        RoleShow roleShow = ImageMixDeal.userimg.getRoleShow();
        if (UserStallUntil.stall == null) {
            (UserStallUntil.stall = new Stall()).setState(StallBean.OFF);
        }
        if (UserStallUntil.stall.getState() == StallBean.PREPARE || UserStallUntil.stall.getState() == StallBean.OFF) {
            if (roleShow.getMapid() == null) {
                return;
            }
            if ((long)roleShow.getMapid() != 1236L) {
                ZhuFrame.getZhuJpanel().addPrompt("只能在洛阳集市或长安城摆摊");
                return;
            }
            if ((long)roleShow.getMapid() == 1236L) {
                if (roleShow.getX() < 5977 || roleShow.getX() > 8290) {
                    ZhuFrame.getZhuJpanel().addPrompt("只能在洛阳集摆摊");
                    return;
                }
                if (roleShow.getY() < 3099 || roleShow.getY() > 4174) {
                    ZhuFrame.getZhuJpanel().addPrompt("只能在洛阳集市摆摊");
                    return;
                }
            }
            if ((long)roleShow.getMapid() == 1207L) {
                if ((roleShow.getX() < 857 && roleShow.getX() > 1560) || (roleShow.getX() < 1900 && roleShow.getX() > 2900)) {
                    ZhuFrame.getZhuJpanel().addPrompt("只能在洛阳集市或长安城摆摊");
                    return;
                }
                if ((roleShow.getY() < 2500 && roleShow.getY() > 3100) || (roleShow.getY() < 3100 && roleShow.getY() > 3600)) {
                    ZhuFrame.getZhuJpanel().addPrompt("只能在洛阳集市或长安城摆摊");
                    return;
                }
            }
            if (roleShow.getTroop_id() != null || FormsManagement.getframe(14).isVisible() || FightingMixDeal.State != 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("当前状态不能摆摊！");
                return;
            }
            if ((int)roleShow.getGrade() <= 102) {
                ZhuFrame.getZhuJpanel().addPrompt2("转生之后才可以摆摊");
                return;
            }
            if (roleShow.getMount_id() != 0) {
                ZhuFrame.getZhuJpanel().addPrompt2("在摆摊了还要骑着坐骑干什么");
                return;
            }
            UserStallUntil.stall.setStall("商店");
            UserStallUntil.stall.setMapid(Util.ditubianma);
            UserStallUntil.stall.setRoleid(roleShow.getRole_id());
            UserStallUntil.stall.setRole(roleShow.getRolename());
            UserStallUntil.stall.setState(StallBean.NO);
            UserStallUntil.stall.setId(0);
            UserStallUntil.stall.setX(roleShow.getX() - 50 - 8);
            UserStallUntil.stall.setY(roleShow.getY() - 135);
            String sendMes = Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(UserStallUntil.stall));
            SendMessageUntil.toServer(sendMes);
            SpotBoxJframe.getSpotBoxJframe().setLocation(320, 70);
        }
        FormsManagement.showForm(800);
        FormsManagement.HideForm(0);
    }
    
    public static void endStall() {
        if (Util.isCanBuyOrno1()) {
            return;
        }
        if (UserStallUntil.stall.getState() != StallBean.OFF) {
            UserStallUntil.stall.setState(StallBean.OFF);
            String sendMes = Agreement.getAgreement().stallAgreement(GsonUtil.getGsonUtil().getgson().toJson(UserStallUntil.stall));
            SendMessageUntil.toServer(sendMes);
            FormsManagement.HideForm(800);
        }
    }
    
    public static void shareStallPoint() {
        Coordinates coordinates = new Coordinates(UserStallUntil.stall.getMapid(), UserStallUntil.stall.getX() + 50, UserStallUntil.stall.getY() + 135);
        Gamemap gamemap = (Gamemap)UserMessUntil.getAllmapbean().getAllMap().get(UserStallUntil.stall.getMapid() + "");
        StringBuffer mapName = new StringBuffer();
        mapName.append("[");
        mapName.append(gamemap.getMapname());
        mapName.append("(");
        mapName.append((UserStallUntil.stall.getX() + 50) / 20);
        mapName.append(",");
        mapName.append((UserStallUntil.stall.getY() + 135) / 20);
        mapName.append(")");
        mapName.append("]");
        InputBean inputBean = new InputBean(10, mapName.toString(), "G", coordinates);
        try {
            JTextField sendMes = ZhuFrame.getZhuJpanel().getSendMes();
            RichDocument richDocument = (RichDocument)sendMes.getDocument();
            richDocument.insertString(sendMes.getCaretPosition(), "我在", null);
            richDocument.insertRich(sendMes.getCaretPosition(), inputBean, null);
            richDocument.insertString(sendMes.getCaretPosition(), "挥泪大甩卖，快来看看", null);
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    
    public static void shareStall() {
        try {
            JTextField sendMes = ZhuFrame.getZhuJpanel().getSendMes();
            RichDocument richDocument = (RichDocument)sendMes.getDocument();
            richDocument.insertRich(sendMes.getCaretPosition(), 13, BigDecimal.valueOf((long)UserStallUntil.stall.getId()), "[" + UserStallUntil.stall.getStall() + "]", "G", null);
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateFollow(Integer followId) {
        if (!UserStallUntil.followList.contains(followId)) {
            UserStallUntil.followList.add(followId);
            ZhuFrame.getZhuJpanel().addPrompt2("#G关注成功！");
        }
        else {
            UserStallUntil.followList.remove(followId);
            ZhuFrame.getZhuJpanel().addPrompt2("取消关注！");
        }
    }
    
    public static boolean isFollow(Integer followId) {
        return UserStallUntil.followList.contains(followId);
    }
    
    public static void showBuyStall() {
        showBuyStall(UserStallUntil.stall.getId());
    }
    
    public static void showBuyStall(int id) {
        showBuyStall(new StallBean(id));
    }
    
    public static void showBuyStall(StallBean bean) {
        UserStallUntil.stallBean = bean;
        FormsManagement.showForm(801);
        updateBuyStall();
    }
    
    public static void hideBuyStall() {
        FormsManagement.HideForm(801);
        UserStallUntil.stallBean = null;
    }
    
    public static void updateBuyStall() {
        SendMessageUntil.toServer(Agreement.getAgreement().stallgetAgreement(UserStallUntil.stallBean.getId() + ""));
    }
    
    public static void updateBuyStall(Stall stall) {
        if (UserStallUntil.stallBean != null && UserStallUntil.stallBean.getId() == stall.getId()) {
            SpotBuyBoxJframe.getSpotBuyBoxJframe().getSpotBuyBoxJpanel().setStall(stall);
        }
        if (stall.getRoleid().compareTo(ImageMixDeal.userimg.getRoleShow().getRole_id()) == 0) {
            updateStall(stall);
        }
    }
    
    static {
        UserStallUntil.followList = new ArrayList<>();
    }
}
