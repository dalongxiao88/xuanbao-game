package org.come.action.role;

import org.come.entity.Goodstable;
import org.come.entity.RoleSummoning;
import java.util.List;
import org.come.entity.RoleTable;
import org.come.tool.EquipTool;
import org.come.entity.Present;
import org.come.server.GameServer;
import org.come.redis.RedisCacheUtil;
import java.math.BigDecimal;
import come.tool.Calculation.BaseValue;
import org.come.until.AllServiceUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.bean.LoginResult;
import io.netty.channel.ChannelHandlerContext;
import org.come.until.StringUtil;
import org.come.action.IAction;

public class CreateRoleAction implements IAction
{
    private String[] nameCondition;
    
    public CreateRoleAction() {
        this.nameCondition = StringUtil.nameCondition;
    }
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        System.out.println(message);
        LoginResult createRole = (LoginResult)GsonUtil.getGsonUtil().getgson().fromJson(message, LoginResult.class);
        String checkRes = this.roleNameCheck(createRole.getRolename());
        if (!"true".equals(checkRes)) {
            String mes = Agreement.getAgreement().errorCreateAgreement(checkRes);
            SendMessage.sendMessageToSlef(ctx, mes);
            return;
        }
        RoleTable role = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(createRole.getRolename());
        if (null == role) {
            List<LoginResult> results = AllServiceUtil.getUserTableService().findRoleByUserNameAndPassword(createRole.getUserName(), createRole.getUserPwd(), createRole.getServerMeString());
            if (results != null && results.size() > 5) {
                String mes2 = Agreement.getAgreement().errorCreateAgreement();
                SendMessage.sendMessageToSlef(ctx, mes2);
                return;
            }
            createRole.setHp(new BigDecimal(BaseValue.getRoleValue(createRole.getRace_id(), 0, 0, 0)));
            createRole.setMp(new BigDecimal(BaseValue.getRoleValue(createRole.getRace_id(), 0, 0, 0)));
            createRole.setRole_id(RedisCacheUtil.getRole_pk());
            String belongId = AllServiceUtil.getOpenareatableService().selectBelong(createRole.getServerMeString());
            if (belongId == null) {
                return;
            }
            createRole.setServerMeString(belongId);
            createRole.setExtPoint(BigDecimal.ZERO);
            boolean isSuccess = AllServiceUtil.getRoleTableService().insertIntoRoleTable(createRole);
            if (isSuccess) {
                List<Present> presents = GameServer.getPresents();
                for (int i = presents.size() - 1; i >= 0; --i) {
                    Present present = (Present)presents.get(i);
                    if (!createRole.isGolem()) {
                        if (present.getType() == 0) {
                            RoleSummoning pet = GameServer.getPet(present.getId());
                            if (pet != null) {
                                pet.setBasishp(pet.getHp());
                                pet.setBasismp(pet.getMp());
                                pet.setRoleid(createRole.getRole_id());
                                AllServiceUtil.getRoleSummoningService().insertRoleSummoning(pet);
                            }
                        }
                        else if (present.getType() == 1) {
                            Goodstable goodstable = GameServer.getGood(present.getId());
                            if (goodstable != null) {
                                goodstable.setRole_id(createRole.getRole_id());
                            }
                            if (goodstable != null) {
                                if (EquipTool.canSuper(goodstable.getType())) {
                                    goodstable.setUsetime(Integer.valueOf(present.getNum()));
                                    AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                                }
                                else {
                                    goodstable.setUsetime(Integer.valueOf(1));
                                    for (int j = 0; j < present.getNum(); ++j) {
                                        AllServiceUtil.getGoodsTableService().insertGoods(goodstable);
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.println(createRole.getRole_id());
                LoginResult loginResult = AllServiceUtil.getRoleTableService().selectRoleID(createRole.getRole_id());
                System.out.println(GsonUtil.getGsonUtil().getgson().toJson(loginResult));
                String mes3 = Agreement.getAgreement().successCreateAgreement(GsonUtil.getGsonUtil().getgson().toJson(loginResult));
                SendMessage.sendMessageToSlef(ctx, mes3);
            }
        }
        else {
            String mes4 = Agreement.getAgreement().errorCreateAgreement();
            SendMessage.sendMessageToSlef(ctx, mes4);
        }
    }
    
    public String roleNameCheck(String rolename) {
        if ("".equals(rolename)) {
            return "角色名不可空";
        }
        if (rolename.length() > 6) {
            return "角色名不可超过六个字符";
        }
        for (int i = 0; i < this.nameCondition.length; ++i) {
            if (rolename.contains(this.nameCondition[i])) {
                return "非法角色名";
            }
        }
        if (rolename.matches("^[一-龥0-9a-zA-Z]+$")) {
            return "true";
        }
        return "角色名格式为6个字符(包括中文、数字和字母),不可带有特殊符号";
    }
}
