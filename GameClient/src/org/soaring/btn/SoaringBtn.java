package org.soaring.btn;

import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.mouslisten.PetAddPointMouslisten;
import com.tool.role.RoleProperty;
import org.come.until.ExpIncreaseUntil;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.math.BigDecimal;
import org.come.until.AnalysisString;
import org.come.Frame.ZhuFrame;
import org.come.bean.LoginResult;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import java.awt.Color;
import org.soaring.panel.SoaringPanel;
import com.tool.btn.MoBanBtn;

public class SoaringBtn extends MoBanBtn
{
    private int leixing;
    private SoaringPanel soaringPanel;
    private static final long CONVERSION_EXP = 1000000000L;
    private static final int CONVERSION_NUM = 10;
    
    public SoaringBtn(String iconpath, int type, Color[] colors, String text, int leixing, SoaringPanel soaringPanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.leixing = leixing;
        this.soaringPanel = soaringPanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        if (loginResult.getTurnAround() > 3) {
            if (this.leixing == 1) {
                this.zhuanhuan();
            }
            else if (this.leixing == 2) {
                this.upLvl();
            }
            else if (this.leixing == 3) {
                this.duihuan();
            }
        }
    }
    
    public void zhuanhuan() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        long exp = loginResult.getExperience().longValue();
        int num = (int)(exp / 1000000000L);
        if (num > 10) {
            num = 10;
        }
        if (num == 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("当前经验不足10E");
        }
        else {
            int up = AnalysisString.xwUP(AnalysisString.lvlint((int)loginResult.getGrade()));
            if ((int)loginResult.getXiuwei() >= up) {
                ZhuFrame.getZhuJpanel().addPrompt2("修为点以达到上限,无法继续转换");
            }
            else {
                if ((int)loginResult.getXiuwei() + num > up) {
                    num = up - (int)loginResult.getXiuwei();
                }
                exp -= 1000000000L * (long)num;
                loginResult.setExperience(new BigDecimal(exp));
                loginResult.setXiuwei(Integer.valueOf((int)loginResult.getXiuwei() + num));
                String mes = Agreement.getAgreement().rolechangeAgreement("3" + num);
                SendMessageUntil.toServer(mes);
                ZhuFrame.getZhuJpanel().addPrompt2("修为值加" + num);
                this.soaringPanel.SoaringExprience((int)loginResult.getGrade(), loginResult.getExperience(), loginResult.getExtraPointInt("F"), (int)loginResult.getXiuwei());
            }
        }
    }
    
    public void upLvl() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        int lvl = AnalysisString.lvlint((int)loginResult.getGrade());
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        int max = 200;
        if (loginResult.getGradeincrease() != null && (int)loginResult.getGradeincrease() > 0) {
            max += (int)loginResult.getGradeincrease();
        }
        if (lvl >= max) {
            ZhuFrame.getZhuJpanel().addPrompt2("已达到等级上限");
        }
        else {
            int sxXW = AnalysisString.xiuwei(lvl);
            if (sxXW > (int)loginResult.getXiuwei()) {
                ZhuFrame.getZhuJpanel().addPrompt2("不够修为点升级");
            }
            else {
                ++lvl;
                ZhuFrame.getZhuJpanel().addPrompt2("升级成功");
                loginResult.setXiuwei(Integer.valueOf((int)loginResult.getXiuwei() - sxXW));
                ExpIncreaseUntil.increasePointAndValue();
                String msg = Agreement.getAgreement().rolechangeAgreement("4");
                SendMessageUntil.toServer(msg);
                RoleProperty.Resetgrade((int)loginResult.getGrade(), loginResult.getRace_id());
                PetAddPointMouslisten.getplayerValue();
                String mes = Agreement.getAgreement().RoleLevelUpAgreement(loginResult.getRolename() + "|" + loginResult.getGrade());
                SendMessageUntil.toServer(mes);
                this.soaringPanel.SoaringExprience((int)loginResult.getGrade(), loginResult.getExperience(), loginResult.getExtraPointInt("F"), (int)loginResult.getXiuwei());
            }
        }
    }
    
    public void duihuan() {
        LoginResult loginResult = RoleData.getRoleData().getLoginResult();
        int E = loginResult.getExtraPointInt("F") + 1;
        if (E > (int)loginResult.getXiuwei()) {
            ZhuFrame.getZhuJpanel().addPrompt2("修为点不够兑换");
        }
        else if (E >= 61) {
            ZhuFrame.getZhuJpanel().addPrompt2("属性点兑换达到上限");
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt2("兑换了一点属性点");
            loginResult.setXiuwei(Integer.valueOf((int)loginResult.getXiuwei() - E));
            loginResult.setExtraPoint("F", 1);
            String mes = Agreement.getAgreement().rolechangeAgreement("5");
            SendMessageUntil.toServer(mes);
            PetAddPointMouslisten.getplayerValue();
            this.soaringPanel.SoaringExprience((int)loginResult.getGrade(), loginResult.getExperience(), loginResult.getExtraPointInt("F"), (int)loginResult.getXiuwei());
        }
    }
}
