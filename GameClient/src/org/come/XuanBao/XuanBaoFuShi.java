package org.come.XuanBao;

import come.tool.JDialog.TiShiUtil;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.come.Frame.MsgJframe;
import org.come.Frame.OptionsJframe;
import org.come.Frame.ZhuFrame;
import org.come.Jpanel.GameJpanel;
import org.come.bean.PathPoint;
import org.come.bean.XuanBao;
import org.come.entity.Goodstable;
import org.come.until.FormsManagement;
import org.come.until.GoodsListFromServerUntil;


public class XuanBaoFuShi
        implements MouseListener {
    public int id;


    public XuanBaoFuShi(int id) {
        this.id = id;

    }


    public void mouseClicked(MouseEvent e) {
    }


    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            XuanBao lingbao = (RoleXuanBao.getRoleXuanBao()).zhuangbei;
            if (lingbao != null) {
                OpenGrid(lingbao);
            }

        }

    }


    public void mouseReleased(MouseEvent e) {
    }


    public void mouseEntered(MouseEvent e) {
        XuanBao lingbao = (RoleXuanBao.getRoleXuanBao()).zhuangbei;
        if (lingbao != null) {
            if (lingbao.getFushi() != null && !lingbao.getFushi().equals("")) {
                String[] fushis = lingbao.getFushi().split("\\|");
                if (fushis.length > this.id) {
                    Goodstable goodstable = (Goodstable) GoodsListFromServerUntil.fushis.get(new BigDecimal(fushis[this.id]));
                    if (goodstable != null) {
                        ZhuFrame.getZhuJpanel().creatgoodtext2(goodstable);

                    }

                } else {
                    PathPoint point = GameJpanel.getGameJpanel().mousepath();
                    MsgJframe.getJframe().getJapnel().showZXY("可镶嵌玄印", point.getX(), point.getY());

                }

            } else if (this.id < lingbao.getNum()) {
                String[] v = lingbao.getRgb().split("\\|");

                String v1 = v[this.id].replace("=", "");
                PathPoint point = GameJpanel.getGameJpanel().mousepath();
                MsgJframe.getJframe().getJapnel().showZXY("此槽位可镶嵌#R" + v1 + "#Y色的玄印", point.getX(), point.getY());

            } else {

                PathPoint point = GameJpanel.getGameJpanel().mousepath();
                MsgJframe.getJframe().getJapnel().showZXY("点击可开启栏位", point.getX(), point.getY());

            }


        } else {

            PathPoint point = GameJpanel.getGameJpanel().mousepath();
            MsgJframe.getJframe().getJapnel().showZXY("点击可开启栏位", point.getX(), point.getY());

        }

    }


    public void mouseExited(MouseEvent e) {
        FormsManagement.HideForm(46);
        FormsManagement.HideForm(24);

    }


    public void OpenGrid(XuanBao lingbao) {
        int s = -1;
        int lvl = lingbao.getLvl();
        switch (this.id) {
            case 0:
                if (lvl < 40) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先将玄宝等级提升到40级以上");
                    return;
                }
                break;

            case 1:
                if (lvl < 60) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先将玄宝等级提升到60级以上");

                    return;

                }

                break;

            case 2:
                if (lvl < 80) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先将玄宝等级提升到80级以上");

                    return;

                }

                break;

            case 3:
                if (lvl < 100) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先将玄宝等级提升到100级以上");

                    return;

                }

                break;

            case 4:
                if (lvl < 120) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先将玄宝等级提升到120级以上");

                    return;

                }

                break;

        }

        s = lingbao.FushiOpen(this.id);
        if (s != -1) {
            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.lingfushi, this, "#W消耗#G" + (s * 8) + "#W个玄珏有几率解锁此玄印槽,是否确认解锁？");

        } else {

            BigDecimal id = lingbao.isfushi(this.id);

            if (id != null) {
                int extId = this.id;
                if (lingbao.isfushi(this.id + 1) != null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先将第#G" + (this.id + 2) + "#Y个槽位清空");

                    return;

                }
                Goodstable goodstable = null;
                if (GoodsListFromServerUntil.fushis.get(id) == null) {
                    goodstable = GoodsListFromServerUntil.chaxunsNew(id.intValue());

                } else {
                    goodstable = (Goodstable) GoodsListFromServerUntil.fushis.get(id);

                }
                if (goodstable != null) {
                    if (XuanBaoPackJframe.getXuanBaoPackJframe().isVisible()) {
                        ZhuFrame.getZhuJpanel().addPrompt2("符石报错");


                        return;

                    }
                    RoleXuanBao.getRoleXuanBao().fushichange(lingbao, goodstable, false);

                }

//                else {
//                    goodstable = new Goodstable();
//                    goodstable.setRgid(new BigDecimal(extId));
//                    RoleXuanBao.getRoleXuanBao().fushichange(lingbao, goodstable, false);
//                }

            } else {

                XuanBaoPackJframe.getXuanBaoPackJframe().getXuanBaoPackJpanel().getGoodstableList().clear();
                String[] v = lingbao.getRgb().split("\\|");
                String v1 = v[this.id].replace("=", "");
                if (this.id != 0 &&
                        lingbao.isfushi(this.id - 1) == null) {
                    ZhuFrame.getZhuJpanel().addPrompt2("请先镶嵌上一槽位");


                    return;

                }
                List<Goodstable> goodstableList = new ArrayList<>();
                for (Goodstable goodstable : GoodsListFromServerUntil.getGoodslist()) {
                    if (goodstable != null && (
                            goodstable.getType() == 10013 || goodstable.getType() == 10014 || goodstable.getType() == 10012 || goodstable.getType() == 10018) &&
                            v1.contains(String.valueOf(goodstable.getGoodsname().charAt(0)))) {
                        goodstableList.add(goodstable);

                    }

                }

                goodstableList.removeIf(goodstable -> (GoodsListFromServerUntil.fushis.get(goodstable.getRgid()) != null));
                PathPoint point = GameJpanel.getGameJpanel().mousepath();
                XuanBaoPackJframe.getXuanBaoPackJframe().setLocation(point.getX() + 20, point.getY());
                XuanBaoPackJframe.getXuanBaoPackJframe().getXuanBaoPackJpanel().getGoodstableList().addAll(goodstableList);
                XuanBaoPackJframe.getXuanBaoPackJframe().getXuanBaoPackJpanel().update(v1);
                XuanBaoPackJframe.getXuanBaoPackJframe().setLocation(60, 60);
                FormsManagement.upgradForm(8034);

            }

        }

    }

}


