package com.tool.btn;

import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import org.come.Frame.RaceChangeMainJframe;
import java.awt.event.MouseEvent;
import com.tool.tcpimg.UIUtils;
import org.come.Jpanel.RaceChangeMainJpanel;

public class RoleMetemBtn extends MoBanBtn
{
    private RaceChangeMainJpanel roleMetempsychosisJpanel;
    private int caozuo;
    
    public RoleMetemBtn(String iconpath, int type, int caozuo, RaceChangeMainJpanel roleMetempsychosisJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.roleMetempsychosisJpanel = roleMetempsychosisJpanel;
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
    }
    
    public RoleMetemBtn(String iconpath, int type, String text, int caozuo, RaceChangeMainJpanel roleMetempsychosisJpanel) {
        super(iconpath, type);
        this.caozuo = caozuo;
        this.roleMetempsychosisJpanel = roleMetempsychosisJpanel;
        this.setText(text);
        this.setFont(UIUtils.TEXT_HY16);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        RaceChangeMainJpanel raceChangeMainJpanel = RaceChangeMainJframe.getRaceChangeMainJframe().getRaceChangeMainJpanel();
        switch (this.caozuo) {
            case 1: {
                if (raceChangeMainJpanel.getRoleType() != 1 && raceChangeMainJpanel.getRoleType() != 2) {
                    raceChangeMainJpanel.setRoleType(1);
                    raceChangeMainJpanel.changSexNames();
                }
                this.changMenu(194);
                break;
            }
            case 2: {
                if (raceChangeMainJpanel.getRoleType() != 3 && raceChangeMainJpanel.getRoleType() != 4) {
                    raceChangeMainJpanel.setRoleType(3);
                    raceChangeMainJpanel.changSexNames();
                }
                this.changMenu(192);
                break;
            }
            case 3: {
                if (raceChangeMainJpanel.getRoleType() != 5 && raceChangeMainJpanel.getRoleType() != 6) {
                    raceChangeMainJpanel.setRoleType(5);
                    raceChangeMainJpanel.changSexNames();
                }
                this.changMenu(196);
                break;
            }
            case 4: {
                if (raceChangeMainJpanel.getRoleType() != 7 && raceChangeMainJpanel.getRoleType() != 8) {
                    raceChangeMainJpanel.setRoleType(7);
                    raceChangeMainJpanel.changSexNames();
                }
                this.changMenu(188);
                break;
            }
            case 5: {
                if (raceChangeMainJpanel.getRoleType() != 9 && raceChangeMainJpanel.getRoleType() != 10) {
                    raceChangeMainJpanel.setRoleType(9);
                    raceChangeMainJpanel.changSexNames();
                }
                this.changMenu(190);
                break;
            }
        }
        raceChangeMainJpanel.reloadRace(raceChangeMainJpanel.getRoleType(), raceChangeMainJpanel.getNumber());
    }
    
    public void changMenu(int type) {
        try {
            if (MyIsif.getStyle().equals("水墨")) {
                for (int i = 0; i < this.roleMetempsychosisJpanel.getMenuBtn().length; ++i) {
                    if (i + 1 == this.caozuo) {
                        if (this.roleMetempsychosisJpanel.getMenuBtn()[i] != null) {
                            this.roleMetempsychosisJpanel.getMenuBtn()[i].setIcons(CutButtonImage.cuts("inkImg/button1/K86" + this.roleMetempsychosisJpanel.getMenuName()[i] + ".png"));
                        }
                    }
                    else if (this.roleMetempsychosisJpanel.getMenuBtn()[i] != null) {
                        this.roleMetempsychosisJpanel.getMenuBtn()[i].setIcons(CutButtonImage.cuts("inkImg/button1/K85" + this.roleMetempsychosisJpanel.getMenuName()[i] + ".png"));
                    }
                }
            }
            else {
                for (int i = 0; i < this.roleMetempsychosisJpanel.getMenuBtn().length; ++i) {
                    if (i + 1 == this.caozuo) {
                        if (this.roleMetempsychosisJpanel.getMenuBtn()[i] != null) {
                            this.roleMetempsychosisJpanel.getMenuBtn()[i].setIcons(CutButtonImage.cuts("img/xy2uiimg/一级选项卡_" + this.roleMetempsychosisJpanel.getMenuName()[i] + "族_选中_w84,h120.png"));
                        }
                    }
                    else if (this.roleMetempsychosisJpanel.getMenuBtn()[i] != null) {
                        this.roleMetempsychosisJpanel.getMenuBtn()[i].setIcons(CutButtonImage.cuts("img/xy2uiimg/一级选项卡_" + this.roleMetempsychosisJpanel.getMenuName()[i] + "族_未选中_w84,h120.png"));
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
