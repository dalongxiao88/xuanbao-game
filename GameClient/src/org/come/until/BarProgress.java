package org.come.until;

import org.come.entity.Goodstable;
import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import java.math.BigDecimal;
import org.come.Jpanel.ShaoXiangJpanel;
import com.tool.btn.ShaoXiangBtn;
import javax.swing.JProgressBar;

public class BarProgress extends Thread
{
    public JProgressBar progressBar;
    public ShaoXiangBtn shaoXiangBtn;
    public ShaoXiangJpanel shaoXiangJpanel;
    int[] progressValues;
    
    public BarProgress(JProgressBar progressBar, ShaoXiangBtn button, ShaoXiangJpanel shaoXiangJpanel) {
        this.progressValues = new int[] { 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100 };
        this.progressBar = progressBar;
        this.shaoXiangBtn = button;
        this.shaoXiangJpanel = shaoXiangJpanel;
    }
    
    @Override
    public void run() {
        this.shaoXiangBtn.setEnabled(false);
        for (int i = 0; i < this.progressValues.length; ++i) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.progressBar.setValue(this.progressValues[i]);
        }
        this.progressBar.setIndeterminate(false);
        this.shaoXiangBtn.setEnabled(true);
        Goodstable goodstable = UserMessUntil.getgoodstable(new BigDecimal(90002));
        Goodstable goodstable2 = UserMessUntil.getgoodstable(new BigDecimal(90006));
        Goodstable rgoodstable = GoodsListFromServerUntil.chaxunsNew(goodstable.getGoodsid().intValue());
        Goodstable rgoodstable2 = GoodsListFromServerUntil.chaxunsNew(goodstable2.getGoodsid().intValue());
        String val = this.shaoXiangJpanel.getChooseLeft().getText();
        if (this.shaoXiangJpanel.getChooseLeft().getText().startsWith("装备二特技")) {
            String val2 = this.shaoXiangJpanel.getChooseItemLeft().getText();
            int p = 0;
            if (val2 != null) {
                if (val2 == "武器") {
                    p = 0;
                }
                else if (val2 == "帽子") {
                    p = 1;
                }
                else if (val2 == "项链") {
                    p = 2;
                }
                else if (val2 == "衣服") {
                    p = 3;
                }
                else if (val2 == "护身符") {
                    p = 4;
                }
                else if (val2 == "鞋子") {
                    p = 5;
                }
            }
            Goodstable choosegoods = GoodsListFromServerUntil.getChoseGoodsList()[p];
            if (choosegoods != null) {
                String sendmes = Agreement.getAgreement().shaoxiangAgreement(val + "|" + rgoodstable2.getRgid() + "|" + val2 + "|" + choosegoods.getRgid());
                SendMessageUntil.toServer(sendmes);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("该位置没有穿戴装备");
            }
        }
        else if (this.shaoXiangJpanel.getChooseLeft().getText().startsWith("装备特技")) {
            String val2 = this.shaoXiangJpanel.getChooseItemLeft().getText();
            int p = 0;
            if (val2 != null) {
                if (val2 == "武器") {
                    p = 0;
                }
                else if (val2 == "帽子") {
                    p = 1;
                }
                else if (val2 == "项链") {
                    p = 2;
                }
                else if (val2 == "衣服") {
                    p = 3;
                }
                else if (val2 == "护身符") {
                    p = 4;
                }
                else if (val2 == "鞋子") {
                    p = 5;
                }
            }
            Goodstable choosegoods = GoodsListFromServerUntil.getChoseGoodsList()[p];
            if (choosegoods != null) {
                String sendmes = Agreement.getAgreement().shaoxiangAgreement(val + "|" + rgoodstable.getRgid() + "|" + val2 + "|" + choosegoods.getRgid());
                SendMessageUntil.toServer(sendmes);
            }
            else {
                ZhuFrame.getZhuJpanel().addPrompt2("该位置装备为空");
            }
        }
        else {
            String sendmes2 = Agreement.getAgreement().shaoxiangAgreement(val + "|" + rgoodstable.getRgid());
            SendMessageUntil.toServer(sendmes2);
        }
    }
}
