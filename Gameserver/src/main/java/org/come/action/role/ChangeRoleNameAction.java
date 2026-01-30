package org.come.action.role;

import org.come.until.StringUtil;
import come.tool.Role.RoleData;
import org.come.redis.RedisControl;
import org.come.entity.RoleTable;
import java.util.concurrent.ConcurrentHashMap;
import come.tool.Role.RolePool;
import org.come.server.GameServer;
import org.come.bean.LoginResult;
import org.come.until.AllServiceUtil;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.until.GsonUtil;
import org.come.bean.ChangeRoleNameBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class ChangeRoleNameAction implements IAction
{
    public static String[] nameCondition;
    
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        ChangeRoleNameBean changeRoleNameBean = (ChangeRoleNameBean)GsonUtil.getGsonUtil().getgson().fromJson(message, ChangeRoleNameBean.class);
        String newName = changeRoleNameBean.getNewName();
        String oldName = changeRoleNameBean.getOldName();
        String checkRes = roleNameCheck(newName);
        if (!"true".equals(checkRes)) {
            changeRoleNameBean.setFlag(false);
            String msg = Agreement.getAgreement().ChangeRoleNameAgreement(GsonUtil.getGsonUtil().getgson().toJson(changeRoleNameBean));
            SendMessage.sendMessageToSlef(ctx, msg);
            return;
        }
        RoleTable role = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(newName);
        if (null == role) {
            LoginResult roleInfo = (LoginResult)GameServer.getAllLoginRole().get(ctx);
            roleInfo.setRolename(newName);
            RoleData data = RolePool.getRoleData(roleInfo.getRole_id());
            data.setLoginResult(roleInfo);
            GameServer.getRoleNameMap().put(roleInfo.getRolename(), ctx);
            ((ConcurrentHashMap<String, ChannelHandlerContext>)GameServer.getMapRolesMap().get(roleInfo.getMapid())).put(roleInfo.getRolename(), ctx);
            GameServer.getRoleNameMap().remove(oldName);
            ((ConcurrentHashMap<String, ChannelHandlerContext>)GameServer.getMapRolesMap().get(roleInfo.getMapid())).remove(oldName);
            AllServiceUtil.getGoodsTableService().deleteGoodsByRgid(changeRoleNameBean.getRgid());
            changeRoleNameBean.setFlag(true);
            String msg2 = Agreement.getAgreement().ChangeRoleNameAgreement(GsonUtil.getGsonUtil().getgson().toJson(changeRoleNameBean));
            SendMessage.sendMessageToMapRoles(roleInfo.getMapid(), msg2);
            RoleTable roleTable = new RoleTable();
            roleTable.setRole_id(roleInfo.getRole_id());
            roleTable.setRolename(roleInfo.getRolename());
            roleTable.setTitle(roleInfo.getTitle());
            AllServiceUtil.getRoleTableService().updateByPrimaryKey(roleTable);
            RedisControl.userController("R", roleInfo.getRole_id().toString(), "2", GsonUtil.getGsonUtil().getgson().toJson(roleInfo));
            if (roleInfo.getMarryObject() != null && GameServer.getRoleNameMap().get(roleInfo.getMarryObject()) == null) {
                RoleTable marryRole = AllServiceUtil.getRoleTableService().selectRoleTableByRoleName(roleInfo.getMarryObject());
                if (marryRole.getTitle() != null && marryRole.getTitle().indexOf(oldName) != -1) {
                    marryRole.setTitle(marryRole.getTitle().replace(oldName, newName));
                }
                marryRole.setMarryObject(newName);
                AllServiceUtil.getRoleTableService().updateByPrimaryKey(marryRole);
            }
        }
        else {
            changeRoleNameBean.setFlag(false);
            String msg3 = Agreement.getAgreement().ChangeRoleNameAgreement(GsonUtil.getGsonUtil().getgson().toJson(changeRoleNameBean));
            SendMessage.sendMessageToSlef(ctx, msg3);
        }
    }
    
    public static String roleNameCheck(String rolename) {
        if ("".equals(rolename)) {
            return "角色名不可空";
        }
        if (rolename.length() > 6) {
            return "角色名不可超过六个字符";
        }
        for (int i = 0; i < ChangeRoleNameAction.nameCondition.length; ++i) {
            if (rolename.contains(ChangeRoleNameAction.nameCondition[i])) {
                return "非法角色名";
            }
        }
        if (rolename.matches("^[一-龥0-9a-zA-Z]+$")) {
            return "true";
        }
        return "角色名格式为6个字符(包括中文、数字和字母),不可带有特殊符号";
    }
    
    static {
        ChangeRoleNameAction.nameCondition = StringUtil.nameCondition;
    }
}
