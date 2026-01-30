package org.come.extInterface;

import org.come.entity.UserTable;
import org.come.entity.RoleTable;
import java.util.List;
import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.bean.UserRoleChangeBean;
import org.come.until.AllServiceUtil;
import java.util.ArrayList;
import org.come.until.GsonUtil;
import org.come.extInterBean.UserRoleChangeReqBean;
import io.netty.channel.ChannelHandlerContext;
import org.come.action.IAction;

public class UserRoleChange implements IAction
{
    @Override
    public void action(ChannelHandlerContext ctx, String message) {
        UserRoleChangeReqBean request = (UserRoleChangeReqBean)GsonUtil.getGsonUtil().getgson().fromJson(message, UserRoleChangeReqBean.class);
        String userName = request.getUserName();
        String otherUserName = request.getOtherUserName();
        String type = request.getType();
        if (type.equals("1")) {
            List<RoleTable> roleList = new ArrayList<>();
            List<RoleTable> otherRoleList = new ArrayList<>();
            if (userName != null && !"".equals(userName)) {
                UserTable user = AllServiceUtil.getUserTableService().selectForUsername(userName);
                if (user != null) {
                    roleList = AllServiceUtil.getUserTableService().selectAllRoleTable(userName);
                }
            }
            if (otherUserName != null && !"".equals(otherUserName)) {
                UserTable user = AllServiceUtil.getUserTableService().selectForUsername(otherUserName);
                if (user != null) {
                    otherRoleList = AllServiceUtil.getUserTableService().selectAllRoleTable(otherUserName);
                }
            }
            UserRoleChangeBean roleChangeBean = new UserRoleChangeBean();
            roleChangeBean.setRoleList(roleList);
            roleChangeBean.setOtherRoleList(otherRoleList);
            String msg = Agreement.getAgreement().userRoleChangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(roleChangeBean));
            SendMessage.sendMessageToSlef(ctx, msg);
        }
        else if (type.equals("2")) {
            String roleId = request.getRoleId();
            int con = 0;
            if (otherUserName != null && !"".equals(otherUserName)) {
                UserTable user = AllServiceUtil.getUserTableService().selectForUsername(otherUserName);
                if (user != null) {
                    List<RoleTable> otherRoleList2 = AllServiceUtil.getUserTableService().selectAllRoleTable(otherUserName);
                    if (otherRoleList2.size() >= 5) {
                        con = -11;
                    }
                    else {
                        con = AllServiceUtil.getUserTableService().roleChangeUser(userName, user.getUser_id(), roleId);
                    }
                }
                else {
                    con = -12;
                }
            }
            String msg2 = Agreement.getAgreement().userRoleChangeAgreement(GsonUtil.getGsonUtil().getgson().toJson(Integer.valueOf(con)));
            SendMessage.sendMessageToSlef(ctx, msg2);
        }
    }
}
