package org.skill.btn;

import java.math.BigDecimal;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import org.skill.panel.LxPanel;
import com.tool.btn.MoBanBtn;

public class SkillLxBtn extends MoBanBtn
{
    private int typeBtn;
    private LxPanel lxPanel;
    
    public SkillLxBtn(String iconpath, int type, Color[] colors, String text, Font font, int typeBtn, LxPanel lxPanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.typeBtn = typeBtn;
        this.lxPanel = lxPanel;
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
    }
    
    public static int realmNow(int level) {
        if (level >= 437) {
            return 4;
        }
        if (level >= 417) {
            return 3;
        }
        if (level >= 335) {
            return 2;
        }
        if (level >= 315) {
            return 1;
        }
        return 0;
    }
    
    public static String raceConfirm(BigDecimal raceId, int realmNow) {
        String string = raceId.toString();
        int n = -1;
        switch (string.hashCode()) {
            case 46730162: {
                if (string.equals("10001")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 46730163: {
                if (string.equals("10002")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 46730165: {
                if (string.equals("10004")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 46730164: {
                if (string.equals("10003")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return realmConfirm("旋照期|开光期|融合期|灵寂期", realmNow);
            }
            case 1: {
                return realmConfirm("魔光期|凝元期|结丹期|离婴期", realmNow);
            }
            case 2: {
                return realmConfirm("炼魂期|混沌期|空冥期|神游期", realmNow);
            }
            case 3: {
                return realmConfirm("地仙期|天仙期|玄仙期|金仙期", realmNow);
            }
            default: {
                return null;
            }
        }
    }
    
    public static String realmConfirm(String raceNmae, int realmNow) {
        if (realmNow <= 0) {
            return null;
        }
        return raceNmae.split("\\|")[realmNow - 1];
    }
    
    public static int realmMaxTSP(int realm) {
        switch (realm) {
            case 1: {
                return 15;
            }
            case 2: {
                return 25;
            }
            case 3: {
                return 40;
            }
            case 4: {
                return 60;
            }
            default: {
                return 0;
            }
        }
    }
}
