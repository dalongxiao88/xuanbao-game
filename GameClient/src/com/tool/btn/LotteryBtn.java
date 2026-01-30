package com.tool.btn;

import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.math.BigDecimal;
import com.tool.role.RoleData;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.RewardHallJpanel;

public class LotteryBtn extends MoBanBtn
{
    private RewardHallJpanel rewardHallJpanel;
    
    public LotteryBtn(String iconpath, int type, Color[] colors, Font font, String text, RewardHallJpanel rewardHallJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.rewardHallJpanel = rewardHallJpanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        if (RoleData.getRoleData().getLoginResult().getScoretype("帮派积分") != null && RoleData.getRoleData().getLoginResult().getScoretype("帮派积分").compareTo(new BigDecimal(50)) > 0) {
            this.rewardHallJpanel.setTotalsum(this.rewardHallJpanel.getTotalsum().subtract(new BigDecimal(50)));
            this.rewardHallJpanel.setLotteryNum((this.rewardHallJpanel.getLotteryNum().subtract(new BigDecimal(1)).compareTo(new BigDecimal(0)) >= 0) ? this.rewardHallJpanel.getLotteryNum().subtract(new BigDecimal(1)) : new BigDecimal(0));
            String senmes = null;
            try {
                senmes = Agreement.getAgreement().drawnitemsAgreement();
            }
            catch (Exception var4) {
                var4.printStackTrace();
            }
            SendMessageUntil.toServer(senmes);
        }
        else {
            ZhuFrame.getZhuJpanel().addPrompt("您的战功不足以进行抽奖活动！！");
        }
    }
}
