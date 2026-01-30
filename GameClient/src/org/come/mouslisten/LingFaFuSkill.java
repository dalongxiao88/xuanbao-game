package org.come.mouslisten;

import org.come.Frame.ZhuFrame;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import org.come.until.FormsManagement;
import java.math.BigDecimal;
import org.come.Frame.LingbaoJframe;
import org.come.Frame.MsgJframe;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import org.come.model.Lingbao;
import com.tool.role.RoleLingFa;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LingFaFuSkill implements MouseListener
{
    private boolean shijian;
    private int path;
    private int s;
    
    public LingFaFuSkill(boolean shijian, int path) {
        this.s = -1;
        this.shijian = shijian;
        this.path = path;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            Lingbao lingbao = RoleLingFa.getRoleLingFa().getChoseBao();
            if (lingbao != null) {
                this.OpenGrid(lingbao);
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        Lingbao lingbao = RoleLingFa.getRoleLingFa().getChoseBao();
        if (lingbao != null && !lingbao.getBaotype().equals("法宝")) {
            if (this.shijian) {
                BigDecimal id = lingbao.isfushi(this.path);
                if (id != null) {
                    Goodstable goodstable = (Goodstable)GoodsListFromServerUntil.fushis.get(id);
                    if (goodstable != null) {
                        MsgJframe.getJframe().getJapnel().zsfs(goodstable, LingbaoJframe.getLingbaoJframe().getLocation().x + 285 + this.path * 40, LingbaoJframe.getLingbaoJframe().getLocation().y + 230);
                    }
                    else {
                        Goodstable goodstable2 = GoodsListFromServerUntil.chaxunsNew(id.intValue());
                        if (goodstable2 != null) {
                            MsgJframe.getJframe().getJapnel().zsfs(goodstable2, LingbaoJframe.getLingbaoJframe().getLocation().x + 285 + this.path * 40, LingbaoJframe.getLingbaoJframe().getLocation().y + 230);
                        }
                    }
                }
            }
            else {
                String v = lingbao.skillmsg(this.path);
                if (v != null) {
                    MsgJframe.getJframe().getJapnel().zsskill(v, LingbaoJframe.getLingbaoJframe().getLocation().x + 285 + this.path * 40, LingbaoJframe.getLingbaoJframe().getLocation().y + 300);
                }
            }
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        FormsManagement.HideForm(46);
    }
    
    public void OpenGrid(Lingbao lingbao) {
        if (!lingbao.getBaotype().equals("法宝")) {
            this.s = -1;
            if (this.shijian) {
                this.s = lingbao.FushiOpen(this.path);
                if (this.s != -1) {
                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.lingfushi, this, "#Y确定要将花  #G" + this.s * 8 + "#Y个灵宝天威印开启第  #G" + this.s + "#Y个符石格子吗?");
                }
                else {
                    BigDecimal id = lingbao.isfushi(this.path);
                    if (id != null) {
                        Goodstable goodstable = null;
                        if (GoodsListFromServerUntil.fushis.get(id) == null) {
                            goodstable = GoodsListFromServerUntil.chaxunsNew(id.intValue());
                        }
                        else {
                            goodstable = (Goodstable)GoodsListFromServerUntil.fushis.get(id);
                        }
                        if (goodstable != null) {
                            RoleLingFa.getRoleLingFa().fushichange(lingbao, goodstable, false);
                        }
                    }
                }
            }
            else {
                this.s = lingbao.SkillOpen(this.path);
                if (this.s != -1) {
                    if ((this.s - 2) * 30 + 1 > lingbao.getLingbaolvl().intValue()) {
                        ZhuFrame.getZhuJpanel().addPrompt2("需要等级大于" + ((this.s - 2) * 30 + 1) + "才能开启" + this.s + "个技能格子");
                        return;
                    }
                    if (this.s >= 4 && !lingbao.getBaoquality().equals("无价") && !lingbao.getBaoquality().equals("传世")) {
                        ZhuFrame.getZhuJpanel().addPrompt2("灵宝品质需要是无价或者传世才能开启第4和5的技能格子");
                        return;
                    }
                    OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.lingskill, this, "#Y确定要将花 #G" + this.s * 8 + "#Y个灵宝天威印开启第 #G" + this.s + "#Y个技能格子吗?");
                }
                else {
                    String skill = lingbao.isskill(this.path);
                    if (skill != null) {
                        String[] v = skill.split("=");
                        OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.SkillDiscatd, skill, "#Y确定要花 #G800000#Y银两删除 #G" + v[0] + "【" + v[1] + "人合技】");
                    }
                }
            }
        }
    }
    
    public boolean isShijian() {
        return this.shijian;
    }
    
    public void setShijian(boolean shijian) {
        this.shijian = shijian;
    }
    
    public int getPath() {
        return this.path;
    }
    
    public void setPath(int path) {
        this.path = path;
    }
    
    public int getS() {
        return this.s;
    }
    
    public void setS(int s) {
        this.s = s;
    }
}
