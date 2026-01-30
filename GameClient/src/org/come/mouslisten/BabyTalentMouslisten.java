package org.come.mouslisten;

import org.come.until.FormsManagement;
import org.come.Frame.MsgJframe;
import org.come.bean.Talent;
import org.come.bean.BabyResult;
import org.come.entity.Baby;
import org.come.entity.Goodstable;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.UserMessUntil;
import org.come.Frame.TestChildJframe;
import org.come.Frame.ZhuFrame;
import org.come.until.GoodsListFromServerUntil;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BabyTalentMouslisten implements MouseListener
{
    private int path;
    
    public BabyTalentMouslisten(int path) {
        this.path = path;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (this.path >= 6) {
            int cx = GoodsListFromServerUntil.chaxuns(615);
            if (cx == -1) {
                ZhuFrame.getZhuJpanel().addPrompt2("你背包没有琼浆玉液");
            }
            else {
                Goodstable goodstable = GoodsListFromServerUntil.getGoodslist()[cx];
                Baby baby = UserMessUntil.getbaby(TestChildJframe.getTestChildJframe().getTestChildJpanel().getBabyid());
                if (baby != null) {
                    String outcome = baby.getOutcome();
                    if (outcome != null && !outcome.equals("")) {
                        BabyResult babyResult = UserMessUntil.getBabyResult(outcome);
                        String Talents = baby.getTalents();
                        if (Talents != null && !Talents.equals("")) {
                            int weizhi = this.path - 6;
                            String[] v = Talents.split("\\|");
                            if (weizhi >= 0 && weizhi < 3) {
                                String[] vs = v[weizhi].split("=");
                                int id = Integer.parseInt(vs[0]);
                                if (id <= 3) {
                                    ZhuFrame.getZhuJpanel().addPrompt2("该技能无法强化");
                                    return;
                                }
                                int lvl = Integer.parseInt(vs[1]);
                                if (lvl >= 10) {
                                    ZhuFrame.getZhuJpanel().addPrompt2("该技能已经满级了");
                                    return;
                                }
                                Talent talent = UserMessUntil.getTalent(id);
                                int camp = 0;
                                if (weizhi == 0) {
                                    camp = babyResult.getQ1();
                                }
                                else if (weizhi == 1) {
                                    camp = babyResult.getQ2();
                                }
                                else if (weizhi == 2) {
                                    camp = babyResult.getQ3();
                                }
                                if (!this.iscamp(camp, talent.getCamp())) {
                                    ZhuFrame.getZhuJpanel().addPrompt2("此技能格子无法强化该天资");
                                    return;
                                }
                                String sendmes = Agreement.getAgreement().userbabyAgreement(goodstable.getRgid().toString() + "|" + baby.getBabyid() + "|" + weizhi);
                                SendMessageUntil.toServer(sendmes);
                            }
                        }
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("成年后才能强化技能");
                    }
                }
            }
        }
    }
    
    public boolean iscamp(int v1, int v2) {
        if (v1 == 0) {
            return false;
        }
        if (v1 == 5) {
            if (v2 >= 1 && v2 <= 5) {
                return true;
            }
        }
        else if (v1 == 10) {
            if (v2 >= 6 && v2 <= 10) {
                return true;
            }
        }
        else {
            if (v1 == 11) {
                return true;
            }
            if (v1 == v2) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.path != 3 && this.path != 4 && this.path != 5) {
            Baby baby = UserMessUntil.getbaby(TestChildJframe.getTestChildJframe().getTestChildJpanel().getBabyid());
            if (baby != null) {
                int type = (this.path > 3) ? 1 : 0;
                int p = this.path;
                if (p > 2) {
                    p -= 6;
                }
                String Talents = baby.getTalents();
                if (Talents != null && !Talents.equals("")) {
                    String[] v = Talents.split("\\|");
                    if (v.length > p) {
                        String[] vs = v[p].split("=");
                        int id = Integer.parseInt(vs[0]);
                        int lvl = Integer.parseInt(vs[1]);
                        MsgJframe.getJframe().getJapnel().talent(Integer.valueOf(id), lvl, type, baby.getOutcome(), p);
                    }
                }
            }
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        FormsManagement.HideForm(46);
    }
}
