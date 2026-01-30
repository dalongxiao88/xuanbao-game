package org.come.npc;

import org.come.Frame.ForgeJframe;
import org.come.until.FormsManagement;
import org.come.action.NpcMenuAction;

public class Synthesis implements NpcMenuAction
{
    @Override
    public void menuControl(String type) {
        if (type.equals("符石合成重铸")) {
            FormsManagement.showForm(70);
        }
        else {
            FormsManagement.HideForm(26);
            ForgeJframe.getForgejframe().getForgejpanel().setType(type);
            ForgeJframe.getForgejframe().getForgejpanel().remove();
            if (type.equals("我要炼器")) {
                for (int i = 0; i < ForgeJframe.getForgejframe().getJpanel().getTopTitle().length; ++i) {
                    if (i == 0) {
                        ForgeJframe.getForgejframe().getJpanel().getTopTitle()[i].setText("仙器");
                    }
                    else {
                        ForgeJframe.getForgejframe().getJpanel().getTopTitle()[i].setText("材料");
                    }
                }
                ForgeJframe.getForgejframe().getJpanel().getBtndazao().setText("炼器");
            }
            else if (type.equals("我要培养饰品")) {
                for (int i = 0; i < ForgeJframe.getForgejframe().getJpanel().getTopTitle().length; ++i) {
                    if (i == 0) {
                        ForgeJframe.getForgejframe().getJpanel().getTopTitle()[i].setText("主饰品");
                    }
                    else {
                        ForgeJframe.getForgejframe().getJpanel().getTopTitle()[i].setText("副饰品");
                    }
                }
                ForgeJframe.getForgejframe().getJpanel().getBtndazao().setText("培养");
            } else if (type.equals("我要合成玄印")) {
                for (int i = 0; i < (ForgeJframe.getForgejframe().getJpanel().getTopTitle()).length; i++) {
                    if (i == 0) {
                        ForgeJframe.getForgejframe().getJpanel().getTopTitle()[i].setText("玄印");
                    } else {
                        ForgeJframe.getForgejframe().getJpanel().getTopTitle()[i].setText("玄印");
                    }
                }
                ForgeJframe.getForgejframe().getJpanel().getBtndazao().setText("合成");
            }
            else if (type.equals("我要重铸饰品")) {
                for (int i = 0; i < ForgeJframe.getForgejframe().getJpanel().getTopTitle().length; ++i) {
                    if (i == 0) {
                        ForgeJframe.getForgejframe().getJpanel().getTopTitle()[i].setText("饰品");
                    }
                    else {
                        ForgeJframe.getForgejframe().getJpanel().getTopTitle()[i].setText("矿石");
                    }
                }
                ForgeJframe.getForgejframe().getJpanel().getBtndazao().setText("重铸");
            }
            else if (type.equals("我要解封神饰")) {
                for (int i = 0; i < ForgeJframe.getForgejframe().getJpanel().getTopTitle().length; ++i) {
                    if (i == 0) {
                        ForgeJframe.getForgejframe().getJpanel().getTopTitle()[i].setText("饰品");
                    }
                    else {
                        ForgeJframe.getForgejframe().getJpanel().getTopTitle()[i].setText("矿石");
                    }
                }
                ForgeJframe.getForgejframe().getJpanel().getBtndazao().setText("重铸");
            }
            else {
                for (int i = 0; i < ForgeJframe.getForgejframe().getJpanel().getTopTitle().length; ++i) {
                    ForgeJframe.getForgejframe().getJpanel().getTopTitle()[i].setText("物品");
                }
                ForgeJframe.getForgejframe().getJpanel().getBtndazao().setText("打造");
            }
            FormsManagement.showForm(26);
        }
    }
}
