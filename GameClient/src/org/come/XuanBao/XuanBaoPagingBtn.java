
package org.come.XuanBao;


import com.tool.btn.MoBanBtn;
import com.tool.role.RoleData;
import com.tool.tcpimg.UIUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

import come.tool.JDialog.TiShiUtil;
import org.come.Frame.NPCJfram;
import org.come.Frame.OptionsJframe;
import org.come.Frame.ZhuFrame;
import org.come.XuanBao.RoleXuanBao;
import org.come.XuanBao.XuanBaoPackJ_xuanyunpanel;
import org.come.XuanBao.XuanBaoPackJpanel;
import org.come.XuanBao.XuanBaogood;
import org.come.bean.LoginResult;
import org.come.bean.XuanBao;
import org.come.socket.Agreement;
import org.come.socket.SendMessageUntil;
import org.come.until.FormsManagement;


public class XuanBaoPagingBtn
        extends MoBanBtn {
    private int btnname;
    private XuanBaoPackJpanel xuanBaoPackJpanel;
    private XuanBaoPackJ_xuanyunpanel xuanBaoPackJ_xuanyunpanel;


    public XuanBaoPagingBtn(String iconpath, int type, int btnname, String text) {
        super(iconpath, type);
        this.btnname = btnname;
        if (text != null) {
            setText(text);
            setFont(UIUtils.TEXT_FONT);
            setForeground(Color.yellow);
            setVerticalTextPosition(0);
            setHorizontalTextPosition(0);
        }
    }


    public XuanBaoPagingBtn(String iconpath, int type, int btnname, String text, XuanBaoPackJ_xuanyunpanel xuanBaoPackJ_xuanyunpanel) {
        super(iconpath, type);
        this.btnname = btnname;
        if (text != null) {
            setText(text);
            setFont(UIUtils.TEXT_FONT);
            setForeground(Color.yellow);
            setVerticalTextPosition(0);
            setHorizontalTextPosition(0);
        }
    }


    public XuanBaoPagingBtn(String iconpath, int type, int btnname, String text, Color[] font) {
        super(iconpath, type, font);
        this.btnname = btnname;
        if (text != null) {
            setText(text);
            setFont(UIUtils.TEXT_HY88);
            setForeground(Color.black);
            setVerticalTextPosition(0);
            setHorizontalTextPosition(0);
        }
    }


    public XuanBaoPagingBtn(String iconpath, int type, int btnname, String text, Color[] font, XuanBaoPackJpanel xuanBaoPackJpanel) {
        super(iconpath, type, font);
        this.btnname = btnname;
        if (text != null) {
            setText(text);
            setFont(UIUtils.TEXT_HY88);
            setForeground(Color.black);
            setVerticalTextPosition(0);
            setHorizontalTextPosition(0);
        }
        this.xuanBaoPackJpanel = xuanBaoPackJpanel;
    }


    public XuanBaoPagingBtn(String iconpath, int type, Color[] colors, Font font, int btnname, String text) {
        super(iconpath, type, colors);
        this.btnname = btnname;
        if (text != null) {
            setText(text);
            setFont(font);
            setVerticalTextPosition(0);
            setHorizontalTextPosition(0);
        }
    }


    public void chooseyes() {
    }


    public void chooseno() {
    }


    public void nochoose(MouseEvent e) {
        switch (this.btnname) {
            case 0:
                RoleXuanBao.getRoleXuanBao().lingFan(false);
                break;
            case 1:
                RoleXuanBao.getRoleXuanBao().lingFan(true);
                break;
            case 2:
                RoleXuanBao.getRoleXuanBao().faFan(false);
                break;
            case 3:
                RoleXuanBao.getRoleXuanBao().faFan(true);
                break;
            case 4:
                if ((RoleXuanBao.getRoleXuanBao()).choseBao == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先选择一个玄宝");
                    return;
                }
                NPCJfram.getNpcJfram().getNpcjpanel().xuanbaoxiulian();
                break;
            case 5:
                if ((RoleXuanBao.getRoleXuanBao()).choseBao == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先选择一个玄宝");
                    return;
                }
                LoginResult loginResult = RoleData.getRoleData().getLoginResult();
                long needmoney = 1500*600;
                if (loginResult.getGold().longValue() < needmoney){
                    ZhuFrame.getZhuJpanel().addPrompt2("金币不足，一次需要+#R"+needmoney+"#Y金币");
                    return;
                }
                loginResult.setGold(new BigDecimal(loginResult.getGold().longValue() - needmoney));
//                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("XB=" + (RoleXuanBao.getRoleXuanBao()).choseBao.getBid() + "=" + 'ל'));
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("XB=" + RoleXuanBao.getRoleXuanBao().choseBao.getBid() + "=" + 1500));
                break;
            case 6:
                if (!FormsManagement.getframe(8035).isVisible()) {
                    FormsManagement.showForm(8035);
                    break;
                }
                FormsManagement.HideForm(8035);
                break;
            case 7:
                if (!FormsManagement.getframe(8036).isVisible()) {
                    FormsManagement.showForm(8036);
                    break;
                }
                FormsManagement.HideForm(8036);
                break;
            case 8:
                if (XuanBaoPackJpanel.xz == -1) {
                    return;
                }
                XuanBaogood.fushi(this.xuanBaoPackJpanel.getGoodstableList().get(XuanBaoPackJpanel.xz));
                FormsManagement.HideForm(8034);
                FormsManagement.HideForm(24);
                break;
            case 8039: // 添加删除玄宝功能
                XuanBao chosenXuanBao = RoleXuanBao.getRoleXuanBao().choseBao;
                if (chosenXuanBao == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先选择一个玄宝");
                    return;
                }
                // 显示确认对话框，确认是否删除玄宝
                OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(
                        TiShiUtil.DeleteXuanBao,
                        chosenXuanBao,
                        "#W此功能将删除当前选中的玄宝，删除后无法恢复，你确定要删除吗？"
                );
                break;
        }

    }

}
