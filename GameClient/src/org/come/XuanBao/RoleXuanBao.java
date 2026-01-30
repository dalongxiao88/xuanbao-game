package org.come.XuanBao;

import com.tool.role.RoleData;
import com.tool.role.RoleProperty;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

import org.come.Frame.ZhuFrame;
import org.come.XuanBao.XuanBaoCardJpanel;
import org.come.XuanBao.XuanBaoJframe;
import org.come.XuanBao.XuanBaoXiuLianJframe;
import org.come.bean.Skill;
import org.come.bean.XuanBao;
import org.come.entity.Goodstable;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.CutButtonImage;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.UserData;
import org.come.until.UserMessUntil;
import org.come.until.Util;


public class RoleXuanBao {
    private static org.come.XuanBao.RoleXuanBao roleLingFa;

    public static org.come.XuanBao.RoleXuanBao getRoleXuanBao() {
        if (roleLingFa == null) {
            roleLingFa = new org.come.XuanBao.RoleXuanBao();
        }
        return roleLingFa;
    }

    public static ImageIcon LF_G = new ImageIcon("img/lingbao/msg/lf_g.png");
    private int lingNum = 0;
    public XuanBao choseBao = null;
    private XuanBao[] lingBaos = new XuanBao[48];
    public XuanBao[] equipBao = new XuanBao[3];
    private ImageIcon[] LFimg = new ImageIcon[20];
    public XuanBao zhuangbei = null;
    private int faNum = 0;
    private ImageIcon[] Fimg = new ImageIcon[8];

    public void addlingfa(XuanBao lingbao) {
        int i;
        for (i = 0; i <= this.lingBaos.length - 1; i++) {
            if (this.lingBaos[i] != null && (this.lingBaos[i]).bid.compareTo(lingbao.bid) == 0 &&
                    this.choseBao != null && this.choseBao.getBid().compareTo(lingbao.bid) == 0) {
                if (this.zhuangbei != null && this.zhuangbei.getBid().compareTo(lingbao.bid) == 0) {
                    this.zhuangbei = lingbao;
                }
                this.choseBao = lingbao;
                this.lingBaos[i] = lingbao;
                (XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getEquipmentJpanel().update(lingbao);
                (XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getAttributeJpanel().update(lingbao);
                (XuanBaoXiuLianJframe.getXuanBaoXiuLianJframe()).xuanBaoXiuLianJpanel.update();

                return;
            }
        }
        for (i = 0; i <= this.lingBaos.length - 1; i++) {
            if (this.lingBaos[i] == null) {
                this.lingBaos[i] = lingbao;

                break;
            }
        }
        lingNumChange(this.lingNum);
        faNumChange(this.faNum);
    }

    public void fushichange(XuanBao lingbao, Goodstable goodstable, boolean type) {
        if (type) {
            if (lingbao.fashijihe(goodstable.getRgid() + "")) {
                goodstable.setStatus(1);
                GoodsListFromServerUntil.fushis.put(goodstable.getRgid(), goodstable);
                UserData.upling(lingbao);
                GoodsMouslisten.gooduse(goodstable, 0);
                RoleProperty.ResetEw();

            }

        } else if (GoodsListFromServerUntil.newgood(goodstable)) {
            lingbao.fashijihe(goodstable.getRgid().toString());
            GoodsListFromServerUntil.fushis.remove(goodstable.getRgid());
            (XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getEquipmentJpanel().update(lingbao);
            (XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getAttributeJpanel().update(lingbao);
            UserData.upling(lingbao);
            GoodsMouslisten.gooduse(goodstable, 0);
            RoleProperty.ResetEw();
        } else {
            ZhuFrame.getZhuJpanel().addPrompt("背包已经满了");
        }
    }

    public void lingChange(List<XuanBao> list) {
        if (list == null)
            return;
        int i;
        for (i = 0; i < this.lingBaos.length; i++) {
            this.lingBaos[i] = null;
        }
        for (i = 0; i < this.equipBao.length; i++) {
            this.equipBao[i] = null;
        }
        XuanBaoCardJpanel equipmentJpanel = (XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel;
        for (int j = 0; j < list.size(); j++) {
            XuanBao lingbao = list.get(j);
            if (lingbao.getEquipment() == 1) {
                for (int p = 0; p <= this.equipBao.length - 1; p++) {
                    if (this.equipBao[p] == null) {
                        this.equipBao[p] = lingbao;
                        ((XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getEquipmentJpanel()).jPanelxuanbao[p].setIcon(lingbaoimg(lingbao.getSkin(), 40, 40));
                        break;
                    }
                }
            }
            for (int l = 0; l < this.lingBaos.length; l++) {
                if (this.lingBaos[l] == null) {
                    this.lingBaos[l] = lingbao;

                    break;
                }
            }
        }
        lingNumChange(this.lingNum);
        faNumChange(this.faNum);
    }

    public void lingNumChange(int number) {
        this.lingNum = number;
        for (int i = this.lingNum * 20; i < (this.lingNum + 1) * 20; i++) {
            if (this.lingBaos[i] != null) {
                lingImg(i - this.lingNum * 20, lingbaoimg(this.lingBaos[i].getSkin(), -1, -1));
            } else {
                lingImg(i - this.lingNum * 20, null);
            }
        }
    }

    public void faNumChange(int number) {
        this.faNum = number;
        for (int i = this.faNum * 8; i < (this.faNum + 1) * 8; i++) {
            if (this.lingBaos[i] != null) {
                faImg(i - this.faNum * 8, lingbaoimg(this.lingBaos[i].getSkin(), -1, -1));
            } else {
                faImg(i - this.faNum * 8, null);
            }
        }
    }

    public void lingImg(int Position, ImageIcon imageIcon) {
        this.LFimg[Position] = imageIcon;
    }

    public void faImg(int Position, ImageIcon imageIcon) {
        this.Fimg[Position] = imageIcon;
    }

    public void FaImg(int Position, ImageIcon imageIcon) {
        this.LFimg[Position + 8] = imageIcon;
    }

    public static ImageIcon lingbaoimg(String lingbao, int w, int h) {
        return CutButtonImage.getImage("img/item/" + lingbao + ".png", w, h);
    }

    public void drawL(Graphics g, int x, int y) {
        for (int i = 0; i < 20; i++) {
            int row = i % 10 * 51;
            int col = i / 10 * 51;
            XuanBao lingbao = getlingbao_(i);
            if (lingbao != null) {
                if (this.LFimg[i] != null) {
                    g.drawImage(this.LFimg[i].getImage(), x + row, y + col, 55, 55, null);
                }
                if (lingbao.getEquipment() == 1)
                    g.drawImage(LF_G.getImage(), x + row, y + col, null);
            }
        }
    }

    public void drawF(Graphics g, int x, int y) {
        for (int i = 0; i < 8; i++) {
            int row = i % 4 * 51;
            int col = i / 4 * 51;
            XuanBao lingbao = getfagbao_(i);
            if (lingbao != null) {
                if (this.Fimg[i] != null) {
                    g.drawImage(this.Fimg[i].getImage(), x + row, y + col, 52, 52, null);
                }
                if (this.choseBao != null && this.choseBao.bid == lingbao.bid)
                    g.drawImage(LF_G.getImage(), x + row, y + col, null);
            }
        }
    }

    public XuanBao getlingbao_(int path) {
        String value1 = ((XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getEquipmentJpanel()).resolutiontext.getText();
        String value2 = ((XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getEquipmentJpanel()).resolutiontext2.getText();
        String value3 = ((XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getEquipmentJpanel()).resolutiontext3.getText();
        XuanBao xuanBao = this.lingBaos[path + this.lingNum * 20];
        if (xuanBao == null) return null;
        if (!value1.equals("通用") &&
                !xuanBao.leixing.equals(value1)) {
            return null;
        }
        if (!value2.equals("类型") &&
                !xuanBao.type.equals(value2)) {
            return null;
        }
        if (!value3.equals("品质") &&
                !xuanBao.pinzhi.equals(value3)) {
            return null;
        }
        return xuanBao;
    }

    public XuanBao getfagbao_(int path) {
        String value1 = ((XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getAttributeJpanel()).resolutiontext.getText();
        String value2 = ((XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getAttributeJpanel()).resolutiontext2.getText();
        String value3 = ((XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getAttributeJpanel()).resolutiontext3.getText();
        XuanBao xuanBao = this.lingBaos[path + this.faNum * 8];
        if (xuanBao == null) return null;
        if (!value1.equals("通用") &&
                !xuanBao.leixing.equals(value1)) {
            return null;
        }
        if (!value2.equals("类型") &&
                !xuanBao.type.equals(value2)) {
            return null;
        }
        if (!value3.equals("品质") &&
                !xuanBao.pinzhi.equals(value3)) {
            return null;
        }
        return xuanBao;
    }

    public XuanBao getlingbao(int path) {
        return this.lingBaos[path + this.lingNum * 20];
    }

    public XuanBao getfa(int path) {
        return this.lingBaos[path + this.faNum * 8];
    }

    public void lingFan(boolean type) {
        if (this.lingNum >= 3 && type)
            return;
        if (type) {
            if ((this.lingNum + 1) * 20 < this.lingBaos.length && this.lingBaos[(this.lingNum + 1) * 20] != null) {
                lingNumChange(this.lingNum + 1);
            }
        } else if (this.lingNum > 0) {
            lingNumChange(this.lingNum - 1);
        }
    }

    public void faFan(boolean type) {
        if (this.faNum >= 5 && type)
            return;
        if (type) {
            if ((this.faNum + 1) * 8 < this.lingBaos.length && this.lingBaos[(this.faNum + 1) * 8] != null) {
                faNumChange(this.faNum + 1);
            }
        } else if (this.faNum > 0) {
            faNumChange(this.faNum - 1);
        }
    }

    public void choseuse(XuanBao lingbao, boolean type) {
        if (type) {
            for (int i = 0; i <= this.equipBao.length - 1; i++) {
                if (this.equipBao[i] != null && (this.equipBao[i]).bid.intValue() == lingbao.bid.intValue()) {
                    lingbao.setEquipment(0);
                    this.equipBao[i] = null;
                    ((XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getEquipmentJpanel()).jPanelxuanbao[i].setIcon(null);
                    UserData.upling(lingbao);
                    RoleProperty.ResetEw();
                    return;
                }
            }
            int chupin = 0;
            int zhongpin = 0;
            int zhenpin = 0;
            for (int j = 0; j <= this.equipBao.length - 1; j++) {
                if (this.equipBao[j] != null) {
                    if (this.equipBao[j].getPinzhi().equals("初品")) {
                        chupin++;
                    } else if (this.equipBao[j].getPinzhi().equals("中品")) {
                        zhongpin++;
                    } else if (this.equipBao[j].getPinzhi().equals("珍品")) {
                        zhenpin++;
                    }
                }
            }
            if (lingbao.getPinzhi().equals("初品")) {
                chupin++;
            } else if (lingbao.getPinzhi().equals("中品")) {
                zhongpin++;
            } else if (lingbao.getPinzhi().equals("珍品")) {
                zhenpin++;
            }
            int lvl = RoleData.getRoleData().getLoginResult().getGrade();
            if (checkEquipmentRequirements(lvl, chupin, zhongpin, zhenpin)) {
                return;
            }
            String[] s = RoleData.getRoleData().getPrivateData().getSkill("S");
            List<String> stringList = new ArrayList<>();
            int k;
            if (s != null) {
                for (k = 0; k <= s.length - 1; k++) {
                    Skill skill = UserMessUntil.getSkillId(s[k].split("_")[0]);
                    if (skill != null) {
                        String[] strings = skill.getRemark().split("#r");
                        for (int m = 0; m <= strings.length - 1; m++) {
                            if (strings[m].startsWith("【门派】")) {
                                stringList.add(strings[m].split("】")[1]);

                                break;
                            }
                        }
                    }
                }
            }
            if (!lingbao.getRolelvl().equals("全种族通用")) {
                if (lingbao.getRolelvl().equals("龙族门派")) {
                    if (!Util.getRaceSting(RoleData.getRoleData().getLoginResult().getSpecies_id()).equals("龙")) {
                        ZhuFrame.getZhuJpanel().addPrompt2("无法装备该门派玄宝");
                        return;
                    }
                } else if (lingbao.getRolelvl().equals("鬼族门派")) {
                    if (!Util.getRaceSting(RoleData.getRoleData().getLoginResult().getSpecies_id()).equals("鬼")) {
                        ZhuFrame.getZhuJpanel().addPrompt2("无法装备该门派玄宝");
                        return;
                    }
                } else if (lingbao.getRolelvl().equals("仙族门派")) {
                    if (!Util.getRaceSting(RoleData.getRoleData().getLoginResult().getSpecies_id()).equals("仙")) {
                        ZhuFrame.getZhuJpanel().addPrompt2("无法装备该门派玄宝");
                        return;
                    }
                } else if (lingbao.getRolelvl().equals("人族门派")) {
                    if (!Util.getRaceSting(RoleData.getRoleData().getLoginResult().getSpecies_id()).equals("人")) {
                        ZhuFrame.getZhuJpanel().addPrompt2("无法装备该门派玄宝");
                        return;
                    }
                } else if (lingbao.getRolelvl().equals("魔族门派")) {
                    if (!Util.getRaceSting(RoleData.getRoleData().getLoginResult().getSpecies_id()).equals("魔")) {
                        ZhuFrame.getZhuJpanel().addPrompt2("无法装备该门派玄宝");
                        return;
                    }
                } else {
                    if (stringList.isEmpty()) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请先学习门派技能");
                        return;
                    }
                    if (!stringList.contains(lingbao.getRolelvl())) {
                        ZhuFrame.getZhuJpanel().addPrompt2("请选择" + lingbao.getRolelvl() + "门派");
                        return;
                    }
                }
            }
            for (k = 0; k <= this.equipBao.length - 1; k++) {
                if (this.equipBao[k] == null) {
                    lingbao.setEquipment(1);
                    this.equipBao[k] = lingbao;
                    ((XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getEquipmentJpanel()).jPanelxuanbao[k].setIcon(lingbaoimg(lingbao.getSkin(), 52, 52));
                    UserData.upling(lingbao);
                    RoleProperty.ResetEw();
                    return;
                }
            }
            ZhuFrame.getZhuJpanel().addPrompt2("暂无空槽位");
        } else {
            for (int i = 0; i <= this.equipBao.length - 1; i++) {
                if (this.equipBao[i] != null &&
                        (this.equipBao[i]).bid.intValue() == lingbao.bid.intValue()) {
                    lingbao.setEquipment(0);
                    this.equipBao[i] = null;
                    ((XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getEquipmentJpanel()).jPanelxuanbao[i].setIcon(null);
                    UserData.upling(lingbao);
                    RoleProperty.ResetEw();
                    return;
                }
            }
        }
    }


    public boolean checkEquipmentRequirements(int lvl, int chupin, int zhongpin, int zhenpin) {
        int[] levelThresholds = {188, 296, 389, 460, 490, 505};
        String[] levelNames = {"188级", "296级", "389级", "460级", "490级", "505级"};
        int[][] itemLimits = {{1, 0, 0}, {2, 0, 0}, {2, 1, 0}, {1, 2, 0}, {1, 1, 1}, {0, 2, 1}};
        for (int i = levelThresholds.length - 1; i >= 0; i--) {
            if (lvl >= levelThresholds[i]) {
                if (chupin > itemLimits[i][0] || zhongpin > itemLimits[i][1] || zhenpin > itemLimits[i][2]) {
                    int remainingChu = Math.max(0, itemLimits[i][0] - chupin);
                    int remainingZhong = Math.max(0, itemLimits[i][1] - zhongpin);
                    int remainingZhen = Math.max(0, itemLimits[i][2] - zhenpin);
                    StringBuilder message = new StringBuilder("当前人物等级最多可携带:");
                    if (itemLimits[i][0] > 0) {
                        message.append(" 初品").append(remainingChu).append("个");
                    }
                    if (itemLimits[i][1] > 0) {
                        message.append(" 中品").append(remainingZhong).append("个");
                    }
                    if (itemLimits[i][2] > 0) {
                        message.append(" 珍品").append(remainingZhen).append("个");
                    }
                    ZhuFrame.getZhuJpanel().addPrompt2(message.toString());
                    return true;
                }
                return false;
            }
        }
        ZhuFrame.getZhuJpanel().addPrompt2("人物等级不足，无法装备该玄宝");
        return true;
    }
    /**
     * 删除玄宝
     */
    public void deleteling(XuanBao xuanBao) {
        for (int i = 0; i < lingBaos.length; i++) {
            if (lingBaos[i] != null) {
                if (xuanBao.getBid().compareTo(lingBaos[i].getBid()) == 0) {
                    lingBaos[i] = null;
                    break;
                }
            }
        }
        lingChange(lingchangelist2());
    }

    /**
     * 将玄宝数组转为list
     */
    public java.util.List<XuanBao> lingchangelist2() {
        java.util.List<XuanBao> xuanBaoList = new java.util.ArrayList<>();
        for (int i = 0; i < lingBaos.length; i++) {
            if (lingBaos[i] != null) {
                xuanBaoList.add(lingBaos[i]);
            }
        }
        return xuanBaoList;
    }
    public void choseuse_fa(XuanBao lingbao) {
        this.choseBao = lingbao;
        ((XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getAttributeJpanel()).jPanelxuanbao.setIcon(lingbaoimg(lingbao.getSkin(), 140, 140));
        (XuanBaoJframe.getXuanBaoJframe().getXuanBaoJpanel()).xuanBaoCardJpanel.getAttributeJpanel().update(lingbao);
    }

    public XuanBao[] getLingBaos() {
        return lingBaos;
    }

    public void setLingBaos(XuanBao[] lingBaos) {
        this.lingBaos = lingBaos;
    }

    public XuanBao getXuanBaoByName(String name) {
        if (lingBaos == null) return null;
        if (lingBaos.length == 0) return null;
        for (XuanBao lingBao : lingBaos) {
            if (lingBao == null) continue;
            if (lingBao.getName().equals(name))
                return lingBao;
        }
        return null;
    }
}

