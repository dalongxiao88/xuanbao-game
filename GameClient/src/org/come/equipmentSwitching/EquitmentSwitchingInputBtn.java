package org.come.equipmentSwitching;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.come.until.GsonUtil;
import org.come.bean.ChangeRoleNameBean;
import org.come.until.Util;
import com.tool.image.ImageMixDeal;
import org.come.bean.PrivateData;
import org.come.entity.Goodstable;
import org.come.bean.LoginResult;
import org.come.until.SendRoleAndRolesummingUntil;
import com.tool.role.RoleProperty;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.GoodsListFromServerUntil;
import com.tool.role.RoleReborn;
import com.tool.role.RoleSkill;
import java.math.BigDecimal;
import org.come.Jpanel.TestpackJapnel;
import org.come.Frame.TestpackJframe;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.apache.commons.lang.StringUtils;
import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.Font;
import com.tool.tcpimg.UIUtils;
import com.tool.btn.MoBanBtn;

public class EquitmentSwitchingInputBtn extends MoBanBtn
{
    private EquipmentSwitchingInputJpanel equipmentSwitchingInputJpanel;
    private int caozuo;
    
    public EquitmentSwitchingInputBtn(String iconpath, int type, String text, EquipmentSwitchingInputJpanel equipmentSwitchingInputJpanel) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.setText(text);
        this.setFont(new Font("微软雅体", 1, 18));
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.equipmentSwitchingInputJpanel = equipmentSwitchingInputJpanel;
    }
    
    public EquitmentSwitchingInputBtn(String iconpath, int type, String text, EquipmentSwitchingInputJpanel equipmentSwitchingInputJpanel, int caozuo) {
        super(iconpath, type, UIUtils.COLOR_BTNPUTONG);
        this.setText(text);
        this.setFont(new Font("微软雅体", 1, 18));
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.equipmentSwitchingInputJpanel = equipmentSwitchingInputJpanel;
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
        if (this.caozuo == 1) {
            FormsManagement.HideForm(73);
            return;
        }
        String text = this.equipmentSwitchingInputJpanel.getTextCode().getText();
        if (text == null || text.equals("")) {
            ZhuFrame.getZhuJpanel().addPrompt("输入为空");
            return;
        }
        if (this.equipmentSwitchingInputJpanel.getTextCode().getText().equals("请输入对应的文本")) {
            ZhuFrame.getZhuJpanel().addPrompt("输入为空");
            return;
        }
        if (this.equipmentSwitchingInputJpanel.getTextCode().getText().length() > 3) {
            ZhuFrame.getZhuJpanel().addPrompt("最多可输入三个字符#46");
            return;
        }
        if (this.equipmentSwitchingInputJpanel.getCaozuo() == 0) {
            String equipments = RoleData.getRoleData().getLoginResult().getEquipments();
            if (StringUtils.isNotBlank(equipments)) {
                String[] v = equipments.split("\\$");
                String[] split = v[1].split("&");
                String[] split2 = split[EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel().getSelectIndex()].split("#");
                if (split2.length == 2) {
                    String s = this.equipmentSwitchingInputJpanel.getTextCode().getText() + "#" + split2[1];
                    split[EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel().getSelectIndex()] = s;
                }
                else {
                    String s = this.equipmentSwitchingInputJpanel.getTextCode().getText() + "#";
                    split[EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel().getSelectIndex()] = s;
                }
                String join = StringUtils.join(split, "&");
                equipments = v[0] + "$" + join;
                RoleData.getRoleData().getLoginResult().setEquipments(equipments);
                String mes = Agreement.getAgreement().rolechangeAgreement("switchEquip@" + RoleData.getRoleData().getLoginResult().getEquipments());
                SendMessageUntil.toServer(mes);
                String[] split3 = join.split("&");
                for (int i = 0; i < EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel().getEquipmentSwitchingMenus().size(); ++i) {
                    ((EquipmentSwitchingBtn)EquipmentSwitchingJframe.getAssistantJframe().getAssistantJpanel().getEquipmentSwitchingMenus().get(i)).setTzName(split3[i].split("#")[0]);
                }
                int i = 0;
                while (true) {
                    int n = i;
                    TestpackJframe.getTestpackJframe().getJpac();
                    if (n < TestpackJapnel.getReplacementList().size() - 2) {
                        TestpackJframe.getTestpackJframe().getJpac();
                        ((EquipmentSwitchingBtn)TestpackJapnel.getReplacementList().get(i)).setText(split3[i].split("#")[0]);
                        if (i == (int)TestpackJapnel.tzIndex) {
                            TestpackJframe.getTestpackJframe().getJpac();
                            TestpackJapnel.getReplacement().setText("换装");
                        }
                        ++i;
                    }
                    else {
                        break;
                    }
                }
                ZhuFrame.getZhuJpanel().addPrompt("套装名称已修改#28");
                FormsManagement.HideForm(100001);
            }
        }
    }
    
    public void unJF(String ChangeBorn, BigDecimal rgid) {
        String sendmes = Agreement.getAgreement().userAgreement(rgid + "|" + ChangeBorn);
        SendMessageUntil.toServer(sendmes);
        FormsManagement.HiddenDisplay(73);
    }
    
    public void unJJ(String ChangeBorn, BigDecimal rgid) {
        if (!ChangeBorn.matches("[0-9]+")) {
            ZhuFrame.getZhuJpanel().addPrompt2("请输入数字");
            return;
        }
        String sendmes = Agreement.getAgreement().userAgreement(rgid + "|" + ChangeBorn);
        SendMessageUntil.toServer(sendmes);
        FormsManagement.HiddenDisplay(73);
    }
    
    public static void ChangeBorn(String ChangeBorn, BigDecimal rgid) {
        String[] v = ChangeBorn.trim().split("-");
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (v.length > loginResult.getTurnAround() || v.length > 3) {
            ZhuFrame.getZhuJpanel().addPrompt2("只能输入于自身相同的转生次数");
            return;
        }
        int[] vv = getbz(v);
        if (vv == null) {
            ZhuFrame.getZhuJpanel().addPrompt2("输入格式有误");
            return;
        }
        String yuben = null;
        for (int i = 0; i < vv.length; ++i) {
            yuben = RoleReborn.reborn(RoleSkill.getRoleSkill().getAllSkill(vv[i], i * 5000 + 10000), yuben);
        }
        Goodstable goodstable = GoodsListFromServerUntil.Uerbiaoid(rgid);
        if (goodstable != null) {
            ZhuFrame.getZhuJpanel().addPrompt2("更改修正成功");
            GoodsMouslisten.gooduse(goodstable, 1);
            RoleProperty.Resetborn(null, yuben);
            PrivateData data = RoleData.getRoleData().getPrivateData();
            data.setBorn(yuben);
            SendRoleAndRolesummingUntil.sendRole(data);
            FormsManagement.HideForm(73);
        }
    }
    
    public static int[] getbz(String[] v) {
        try {
            int[] a = new int[v.length];
            for (int i = 0; i < v.length; ++i) {
                a[i] = Integer.parseInt(v[i]);
                if (a[i] < 1 || a[i] > 10) {
                    return null;
                }
            }
            return a;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static void ChangeNameupload(String ChangeName, BigDecimal rgid) {
        ChangeName = ChangeName.trim();
        int size = 0;
        char[] nz = ChangeName.toCharArray();
        for (int i = 0; i < nz.length; ++i) {
            String a = nz[i] + "";
            if (a.getBytes().length == 1) {
                ++size;
            }
            else {
                size += 2;
            }
        }
        if (size > 12) {
            ZhuFrame.getZhuJpanel().addPrompt2("名称太长");
            return;
        }
        if (ImageMixDeal.userimg.getRoleShow().getTeamInfo() == null || ImageMixDeal.userimg.getRoleShow().getTeamInfo().equals("")) {
            if (!ChangeName.equals(ImageMixDeal.userimg.getRoleShow().getRolename())) {
                if (special(ChangeName)) {
                    if (!Util.isIllegal(ChangeName)) {
                        ZhuFrame.getZhuJpanel().addPrompt("名称中包含非法字符！！");
                        return;
                    }
                    ChangeRoleNameBean bean = new ChangeRoleNameBean();
                    bean.setOldName(ImageMixDeal.userimg.getRoleShow().getRolename());
                    bean.setNewName(ChangeName);
                    bean.setRgid(rgid);
                    String sendmes = Agreement.getAgreement().ChangeRoleNameAgreement(GsonUtil.getGsonUtil().getgson().toJson(bean));
                    SendMessageUntil.toServer(sendmes);
                    FormsManagement.HideForm(73);
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt2("修改后的名字不能有特殊符号");
                }
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("修改后的名字不能和自己重复");
            }
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("组队状态下不能使用改名卡");
        }
    }
    
    public static boolean special(String ChangeName) {
        String[] a = { "!", "|", "*", "&", "@", "#", "$", "%", "^", "/" };
        for (int i = 0; i < a.length; ++i) {
            if (ChangeName.indexOf(a[i]) != -1) {
                return false;
            }
        }
        return true;
    }
    
    public void recruit(String text) {
        if (!this.isNumeric(text)) {
            ZhuFrame.getZhuJpanel().addPrompt2("请输入正确的数字");
            return;
        }
        SendMessageUntil.toServer(Agreement.getAgreement().hjslAgreement("Z" + text));
        FormsManagement.HideForm(73);
    }
    
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
}
