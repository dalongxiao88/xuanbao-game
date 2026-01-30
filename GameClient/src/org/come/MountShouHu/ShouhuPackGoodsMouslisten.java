package org.come.MountShouHu;

import com.google.gson.Gson;
import java.util.List;
import org.come.until.CutButtonImage;
import org.come.Frame.ZhuFrame;
import org.come.socket.SendMessageUntil;
import org.come.until.GsonUtil;
import org.come.socket.Agreement;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Mount;
import org.come.entity.Goodstable;
import com.tool.role.RoleData;
import org.come.until.UserMessUntil;
import org.come.Frame.MountShouhuJframe;
import org.come.until.FormsManagement;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShouhuPackGoodsMouslisten implements MouseListener
{
    private static ShouhuPackJpanel shouhuPackJpanel;
    private int[] xy;
    private int goodPlace;
    private static RandFJpanel randFJpanel;
    int p;
    
    public ShouhuPackGoodsMouslisten(int i, ShouhuPackJpanel shouhuPackJpanel, int[] xy) {
        this.p = -1;
        ShouhuPackGoodsMouslisten.shouhuPackJpanel = shouhuPackJpanel;
        this.xy = xy;
        this.goodPlace = i;
    }
    
    public ShouhuPackGoodsMouslisten(int i, RandFJpanel randFJpanel) {
        this.p = -1;
        ShouhuPackGoodsMouslisten.randFJpanel = randFJpanel;
        this.p = i;
    }
    
    public ShouhuPackGoodsMouslisten(int i) {
        this.p = -1;
        this.p = i;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1 && e.getClickCount() == 1) {
            ShouhuPackGoodsMouslisten.shouhuPackJpanel.xuanzhong(this.goodPlace);
        }
        else if (e.getButton() == 1 && e.getClickCount() == 2) {
            FormsManagement.HideForm(2257);
            RandFJpanel randFJpanel = RandFJframe.getRandFJframe().getRandFJpanel();
            if (MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().s) {
                List<Mount> list = UserMessUntil.getMountlsit();
                if (RoleData.getRoleData().getLoginResult().getJiesuo() == null) {
                    return;
                }
                int l = Integer.parseInt(RoleData.getRoleData().getLoginResult().getJiesuo().split("\\|")[MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().l]);
                if (l == -1 || l == 0) {
                    return;
                }
                int k = this.goodPlace * ((ShouhuPackGoodsMouslisten.shouhuPackJpanel.page == 0) ? 1 : ShouhuPackGoodsMouslisten.shouhuPackJpanel.page);
                if (k >= ShouhuPackGoodsMouslisten.shouhuPackJpanel.getGoodstableList().size()) {
                    return;
                }
                if (ShouhuPackGoodsMouslisten.shouhuPackJpanel.getGoodstableList().size() == 0) {
                    return;
                }
                Goodstable goodstable = ShouhuPackGoodsMouslisten.shouhuPackJpanel.getGoodstableList().get(k);
                for (int y = 0; y <= list.size() - 1; ++y) {
                    if (list.get(y).getMountid() == l) {
                        if (list.get(y).getShouhu() != 0) {
                            int f = 0;
                            while (true) {
                                int n = f;
                                ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                                if (n <= ShouhuPackJpanel.Eqment.size() - 1) {
                                    ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                                    if (ShouhuPackJpanel.Eqment.get(f).getRgid().intValue() == ((Mount)list.get(y)).getShouhu()) {
                                        ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                                        ShouhuPackJpanel.Eqment.get(f).setStatus(Integer.valueOf(0));
                                        ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                                        GoodsListFromServerUntil.newgood((Goodstable)ShouhuPackJpanel.Eqment.get(f));
                                        List<Goodstable> goodstableList = ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList();
                                        ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                                        goodstableList.add(ShouhuPackJpanel.Eqment.get(f));
                                        Agreement agreement = Agreement.getAgreement();
                                        StringBuilder append = new StringBuilder().append("upshuanimabi&");
                                        Gson getgson = GsonUtil.getGsonUtil().getgson();
                                        ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                                        String sendmes2 = agreement.rolechangeAgreement(append.append(getgson.toJson(ShouhuPackJpanel.Eqment.get(f))).toString());
                                        SendMessageUntil.toServer(sendmes2);
                                    }
                                    ++f;
                                }
                                else {
                                    break;
                                }
                            }
                            ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                            ShouhuPackJpanel.Eqment.removeIf(goodstable1/* org.come.entity.Goodstable, */ -> (int)goodstable1.getStatus() == 0);
                            ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().removeIf(goodstable1/* org.come.entity.Goodstable, */ -> (int)goodstable1.getStatus() == 1);
                        }
                        ((Mount)list.get(y)).setShouhu(goodstable.getRgid().intValue());
                        String sendmes3 = Agreement.getAgreement().changeMountValue(GsonUtil.getGsonUtil().getgson().toJson(list.get(y)));
                        SendMessageUntil.toServer(sendmes3);
                        goodstable.setStatus(Integer.valueOf(1));
                        ShouhuPackJpanel shouhuPackJpanel = ShouhuPackGoodsMouslisten.shouhuPackJpanel;
                        ShouhuPackJpanel.Eqment.add(goodstable);
                        ShouhuPackGoodsMouslisten.shouhuPackJpanel.getGoodstableList().remove(goodstable);
                        ShouhuPackGoodsMouslisten.shouhuPackJpanel.updata();
                        sendmes3 = Agreement.getAgreement().rolechangeAgreement("upshuanimabi&" + GsonUtil.getGsonUtil().getgson().toJson(goodstable));
                        SendMessageUntil.toServer(sendmes3);
                        ZhuFrame.getZhuJpanel().addPrompt2("#G" + goodstable.getGoodsname() + "#Y已经装备到#R" + ((Mount)list.get(y)).getMountname() + "#Y身上#46");
                    }
                }
                GoodsListFromServerUntil.shouhu(goodstable.getRgid().intValue());
                MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().s = false;
                try {
                    MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().shouhuBtn[MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().l].setIcons(CutButtonImage.cuts("inkImg/Client/守护石圆圈带守护石.png"));
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
                MountShouhuJframe.getMountShouhuJframe().getMountShouhuJpanel().l = -1;
            }
            else {
                if (ShouhuPackGoodsMouslisten.shouhuPackJpanel.getGoodstableList().size() == 0) {
                    return;
                }
                if (randFJpanel.getType() == 1) {
                    ShouhuPackGoodsMouslisten.shouhuPackJpanel.setXuanzhong1(this.goodPlace + ShouhuPackGoodsMouslisten.shouhuPackJpanel.page * 12);
                }
                else if (randFJpanel.getType() == 2) {
                    if (ShouhuPackGoodsMouslisten.shouhuPackJpanel.getIdx() == 1) {
                        ShouhuPackGoodsMouslisten.shouhuPackJpanel.setXuanzhong1(this.goodPlace + ShouhuPackGoodsMouslisten.shouhuPackJpanel.page * 12);
                    }
                    else if (ShouhuPackGoodsMouslisten.shouhuPackJpanel.getIdx() == 2) {
                        ShouhuPackGoodsMouslisten.shouhuPackJpanel.setXuanzhong2(this.goodPlace + ShouhuPackGoodsMouslisten.shouhuPackJpanel.page * 12);
                    }
                }
            }
            FormsManagement.HideForm(24);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if (this.p == 10) {
            int i = ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getXuanzhong1();
            Goodstable goodstable = ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().get(i);
            if (goodstable != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
        else if (this.p == 11) {
            int i = ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getXuanzhong1();
            Goodstable goodstable = ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().get(i);
            if (goodstable != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
        else if (this.p == 12) {
            int i = ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getXuanzhong2();
            Goodstable goodstable = ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel().getGoodstableList().get(i);
            if (goodstable != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
        else if (this.p >= 60 && this.p <= 66) {
            int l = this.p - 60;
            if (RoleData.getRoleData().getLoginResult().getJiesuo() == null) {
                return;
            }
            int n = Integer.parseInt(RoleData.getRoleData().getLoginResult().getJiesuo().split("\\|")[l]);
            if (n == -1) {
                return;
            }
            List<Mount> list = UserMessUntil.getMountlsit();
            list.forEach(el/* org.come.entity.Mount, */ -> {
                if ((int)el.getMountid() == n) {
                    ShouhuPackJframe.getShouhuPackJframe().getShouhuPackJpanel();
                    ShouhuPackJpanel.Eqment.forEach(p/* org.come.entity.Goodstable, */ -> {
                        if (p.getRgid().intValue() == el.getShouhu()) {
                            ZhuFrame.getZhuJpanel().creatgoodtext(p);
                        }
                        return;
                    });
                }
                return;
            });
        }
        else {
            ShouhuPackGoodsMouslisten.shouhuPackJpanel.effect(this.goodPlace);
            int k = this.goodPlace + ShouhuPackGoodsMouslisten.shouhuPackJpanel.page * 12;
            if (k >= ShouhuPackGoodsMouslisten.shouhuPackJpanel.getGoodstableList().size()) {
                return;
            }
            Goodstable goodstable = (Goodstable)ShouhuPackGoodsMouslisten.shouhuPackJpanel.getGoodstableList().get(k);
            if (goodstable != null) {
                ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
            }
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (ShouhuPackGoodsMouslisten.shouhuPackJpanel != null) {
            ShouhuPackGoodsMouslisten.shouhuPackJpanel.effect(-1);
            FormsManagement.HideForm(24);
        }

    }
}
