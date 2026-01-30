package org.come.mouslisten;

import org.come.entity.PartJade;
import java.util.List;
import com.tool.role.RoleData;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.come.Frame.ZhuFrame;
import org.come.entity.Goodstable;
import org.come.until.AccessSuitMsgUntil;
import java.awt.event.MouseEvent;
import org.come.Jpanel.UpgradeJpanel;
import org.come.Jpanel.WashJpanel;
import java.awt.event.MouseListener;

public class RoleSuitMouslisten implements MouseListener
{
    private int index;
    private WashJpanel washJpanel;
    private UpgradeJpanel upgradeJpanel;
    private int page;
    
    public RoleSuitMouslisten(int index, WashJpanel washJpanel, UpgradeJpanel upgradeJpanel) {
        this.index = index;
        this.washJpanel = washJpanel;
        this.upgradeJpanel = upgradeJpanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            List<Goodstable> listrolesuit = AccessSuitMsgUntil.accessIdlEqu(2);
            if (listrolesuit == null) {
                return;
            }
            if (this.index + WashJpanel.page * 21 + 1 > listrolesuit.size()) {
                return;
            }
            Goodstable goodstable = null;
            if (listrolesuit.get(this.index + WashJpanel.page * 21) != null) {
                goodstable = (Goodstable)listrolesuit.get(this.index + WashJpanel.page * 21);
            }
            if (goodstable == null) {
                return;
            }
            if (goodstable.getGoodlock() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt("套装 " + goodstable.getGoodsname() + " 处于加锁状态");
                return;
            }
            if (this.washJpanel != null) {
                WashJpanel.getLabSet().setIcon(new ImageIcon(new ImageIcon("img/item/" + goodstable.getSkin() + ".png").getImage().getScaledInstance(50, 50, 10)));
                WashJpanel.getGoodstableBean().setGoodstable(goodstable);
                for (int i = 0; i < 4; ++i) {
                    WashJpanel.getLabsx()[i].setText("");
                }
                List<String> attr = AccessSuitMsgUntil.getSuitAttr(AccessSuitMsgUntil.getExtra(goodstable.getValue(), "套装属性"));
                if (attr == null) {
                    return;
                }
                for (int index = (attr.size() >= 4) ? 4 : attr.size(), j = 0; j < index; ++j) {
                    WashJpanel.getLabsx()[j].setText((String)attr.get(j));
                }
            }
            if (this.upgradeJpanel != null) {
                UpgradeJpanel.setGoodstable((Goodstable)null);
                UpgradeJpanel.getLabtz2().setIcon((Icon)null);
                UpgradeJpanel.getLabtz1().setIcon(new ImageIcon(new ImageIcon("img/item/" + goodstable.getSkin() + ".png").getImage().getScaledInstance(50, 50, 10)));
                String suitname = goodstable.getGoodsname().split("\\·")[0];
                boolean partid = true;
                int index = Integer.parseInt(goodstable.getValue().split("\\|")[1].split("=")[1]);
                if (index != -1) {
                    PartJade jade = RoleData.getRoleData().getPackRecord().getPartJade((int)AccessSuitMsgUntil.returnSuitID(suitname), index);
                    if (!jade.isJade()) {
                        int pz = AccessSuitMsgUntil.returnJadeType(AccessSuitMsgUntil.returnnewEx(0, AccessSuitMsgUntil.getExtra(goodstable.getValue(), "套装属性")));
                        if (pz < 5 && jade.getJade(pz + 1) > 0) {
                            UpgradeJpanel.getGoodstableBean().setPartJade(jade);
                            UpgradeJpanel.getGoodstableBean().setType(pz + 1);
                            UpgradeJpanel.getLabyf().setIcon(new ImageIcon(new ImageIcon("img/item/tzyf" + (pz + 1) + ".png").getImage().getScaledInstance(50, 50, 10)));
                        }
                    }
                }
                UpgradeJpanel.getGoodstableBean().setGoodstable(goodstable);
            }
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
        List<Goodstable> listrolesuit = AccessSuitMsgUntil.accessIdlEqu(2);
        Goodstable goodstable = null;
        if (this.washJpanel != null) {
            if (this.index + WashJpanel.page * 21 + 1 > listrolesuit.size()) {
                return;
            }
            if (listrolesuit.get(this.index + WashJpanel.page * 21) != null) {
                goodstable = (Goodstable)listrolesuit.get(this.index + WashJpanel.page * 21);
            }
        }
        if (this.upgradeJpanel != null) {
            if (this.index + UpgradeJpanel.page * 21 + 1 > listrolesuit.size()) {
                return;
            }
            if (listrolesuit.get(this.index + UpgradeJpanel.page * 21) != null) {
                goodstable = (Goodstable)listrolesuit.get(this.index + UpgradeJpanel.page * 21);
            }
        }
        if (goodstable != null) {
            ZhuFrame.getZhuJpanel().creatgoodtext(goodstable);
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        ZhuFrame.getZhuJpanel().cleargoodtext();
    }
}
