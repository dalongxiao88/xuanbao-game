package org.come.lianhua;

import org.come.entity.Goodstable;
import com.tool.time.Limit;
import org.come.Frame.DdianJframe;
import org.come.Frame.RuneOperateJframe;
import org.come.Frame.SuitBaptizeJframe;
import org.come.summonequip.JframeSummonEquipMain;
import org.come.until.RefiningUtil;
import org.come.Frame.WorkshopRefiningJframe;
import org.wing.panel.LHMainFrame;
import org.come.MountShouHu.RandFJframe;
import org.come.Frame.NewRefiningJframe;
import org.come.Frame.ZhuFrame;
import com.tool.time.TimeLimit;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.come.Jpanel.ForgeJpanel;
import com.tool.btn.MoBanBtn;

public class AotoMaticRefiningBtn extends MoBanBtn
{
    private ForgeJpanel forgeJpanel;
    private AutoMaticRefiningJpanel autoMaticRefiningJpanel;
    private int caoz;
    
    public AotoMaticRefiningBtn(String iconpath, int type, Color[] colors, Font font, String text, AutoMaticRefiningJpanel autoMaticRefiningJpanel, int caoz) {
        super(iconpath, type, colors);
        this.autoMaticRefiningJpanel = autoMaticRefiningJpanel;
        this.setText(text);
        this.setFont(font);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
        this.caoz = caoz;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        Limit vip = TimeLimit.getLimits().getLimit("VIP");
        if (vip == null) {
            vip = TimeLimit.getLimits().getLimit("JVIP");
            if (vip == null) {
                ZhuFrame.getZhuJpanel().addPrompt2("请先开通月卡或季卡功能#46");
                return;
            }
        }
        if (this.caoz == 0) {
            this.autoMaticRefiningJpanel.getOptionJpanel().setVisible(!this.autoMaticRefiningJpanel.getOptionJpanel().isVisible());
        }
        else if (this.caoz == 1) {
            if ((this.autoMaticRefiningJpanel.getDisplaymodetext().getText().equals("炼化") || this.autoMaticRefiningJpanel.getDisplaymodetext().getText().equals("星卡洗炼") || this.autoMaticRefiningJpanel.getDisplaymodetext().getText().equals("星卡五行") || this.autoMaticRefiningJpanel.getDisplaymodetext().getText().equals("炼器")) && this.autoMaticRefiningJpanel.getStart().getText().equals("运行")) {
                NewRefiningJframe.getNewRefiningJframe().getRefiningJpanel().getOperBtn3().nochoose(null);
                this.autoMaticRefiningJpanel.getStart().setText("停止");
                this.autoMaticRefiningJpanel.setCount(0);
            }
            else if (this.autoMaticRefiningJpanel.getDisplaymodetext().getText().equals("坐骑守护") && this.autoMaticRefiningJpanel.getStart().getText().equals("运行")) {
                RandFJframe.getRandFJframe().getRandFJpanel().getKaishifuling().nochoose(null);
                this.autoMaticRefiningJpanel.getStart().setText("停止");
                this.autoMaticRefiningJpanel.setCount(0);
            }
            else if (this.autoMaticRefiningJpanel.getDisplaymodetext().getText().equals("靓号炼化") && this.autoMaticRefiningJpanel.getStart().getText().equals("运行")) {
                LHMainFrame.LHMainFrame().getLhMainPanel().getRefineryBtn().nochoose(null);
                this.autoMaticRefiningJpanel.getStart().setText("停止");
                this.autoMaticRefiningJpanel.setCount(0);
            }
            else if (this.autoMaticRefiningJpanel.getDisplaymodetext().getText().equals("配饰重铸") && this.autoMaticRefiningJpanel.getStart().getText().equals("运行")) {
                Goodstable[] goods = WorkshopRefiningJframe.getWorkshopRefiningJframe().getWorkshopRefiningJpanel().getCardJpanel().getEquiJpanel().getGoods();
                String detection = RefiningUtil.detection(goods, 2);
                if (detection.equals("佩饰重铸")) {
                    this.autoMaticRefiningJpanel.getStart().setText("停止");
                    this.autoMaticRefiningJpanel.petShidetectionProperties(null);
                    this.autoMaticRefiningJpanel.setCount(0);
                }
                else {
                    ZhuFrame.getZhuJpanel().addPrompt("请检查炼化材料#46");
                }
            }
            else if ((this.autoMaticRefiningJpanel.getDisplaymodetext().getText().equals("兽装重悟技能") || this.autoMaticRefiningJpanel.getDisplaymodetext().getText().equals("兽装重洗属性")) && this.autoMaticRefiningJpanel.getStart().getText().equals("运行")) {
                this.autoMaticRefiningJpanel.getStart().setText("停止");
                JframeSummonEquipMain.getJframeSummonEquipMain().getJpanelSummonEquipMain().getAckBtn().nochoose(null);
                this.autoMaticRefiningJpanel.setCount(0);
            }
            else if (this.autoMaticRefiningJpanel.getDisplaymodetext().getText().equals("套装洗炼") && this.autoMaticRefiningJpanel.getStart().getText().equals("运行")) {
                SuitBaptizeJframe.getSuitBaptizeJframe().getBaptizeJpanel().getBaptizeBtn1().nochoose(null);
                this.autoMaticRefiningJpanel.getStart().setText("停止");
            }
            else if (this.autoMaticRefiningJpanel.getDisplaymodetext().getText().equals("符石重铸") && this.autoMaticRefiningJpanel.getStart().getText().equals("运行")) {
                RuneOperateJframe.getRuneOperateJframe().getOperateJpanel().getPerBtn1().nochoose(null);
                this.autoMaticRefiningJpanel.getStart().setText("停止");
            }
            else if (this.autoMaticRefiningJpanel.getStart().getText().equals("停止")) {
                this.autoMaticRefiningJpanel.setCount(0);
                this.autoMaticRefiningJpanel.getStart().setText("运行");
            }
            else if (this.autoMaticRefiningJpanel.getDisplaymodetext().getText().equals("点粹洗炼") && this.autoMaticRefiningJpanel.getStart().getText().equals("运行")) {
                DdianJframe.getDdianJframe().getDianJpanel().getOperBtn3().nochoose(null);
                this.autoMaticRefiningJpanel.getStart().setText("停止");
                this.autoMaticRefiningJpanel.setCount(0);
            }
        }
    }
}
