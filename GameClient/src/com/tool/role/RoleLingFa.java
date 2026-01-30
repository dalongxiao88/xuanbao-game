package com.tool.role;

import java.util.Map;
import come.tool.JDialog.TiShiUtil;
import org.come.Frame.OptionsJframe;
import java.util.HashMap;
import org.come.Frame.ZhuFrame;
import org.come.mouslisten.GoodsMouslisten;
import org.come.until.GoodsListFromServerUntil;
import org.come.entity.Goodstable;
import org.come.Jpanel.LingbaoEquipmentJpanel;
import org.come.until.UserData;
import org.come.Frame.LingbaoJframe;
import java.math.BigDecimal;
import java.awt.Graphics;
import org.come.until.CutButtonImage;
import com.updateNew.MyIsif;
import javax.swing.DefaultListModel;
import org.come.Jpanel.FaShuKangXingJpanel;
import org.come.until.FormsManagement;
import org.come.Frame.RolePetResistanceJframe;
import java.util.ArrayList;
import java.util.List;
import org.come.model.Lingbao;
import javax.swing.ImageIcon;

public class RoleLingFa
{
    public static ImageIcon LF_G;
    private static RoleLingFa roleLingFa;
    public Lingbao choseBao;
    public Lingbao[] equipBao;
    private int lingNum;
    private int faNum;
    private Lingbao[] lingBaos;
    private Lingbao[] faBaos;
    private ImageIcon[] LFimg;
    static String[] fbs;
    List<String> fabao;
    
    public RoleLingFa() {
        this.choseBao = null;
        this.equipBao = new Lingbao[3];
        this.lingNum = 0;
        this.faNum = 0;
        this.lingBaos = new Lingbao[48];
        this.faBaos = new Lingbao[16];
        this.LFimg = new ImageIcon[16];
        this.fabao = new ArrayList<>();
    }
    
    public static RoleLingFa getRoleLingFa() {
        if (RoleLingFa.roleLingFa == null) {
            RoleLingFa.roleLingFa = new RoleLingFa();
        }
        return RoleLingFa.roleLingFa;
    }
    
    public static void showProperty(Lingbao lingbao) {
        clearShuXingView();
        FaShuKangXingJpanel[] shuXingJpanel = RolePetResistanceJframe.getBaoResistancejframe().getResistancejpanel().getShuXingJpanel();
        for (int i = 0; i < 5; ++i) {
            String[] propertys = lingbao.getPropertys(i);
            int a = -1;
            for (int j = 0; j < propertys.length; ++j) {
                DefaultListModel<String> dlm;
                if (++a % 2 == 0) {
                    dlm = shuXingJpanel[i].getDlm();
                }
                else {
                    dlm = shuXingJpanel[i].getDlm1();
                }
                String[] vals = propertys[j].split("=");
                if (vals.length == 1) {
                    dlm.addElement(vals[0]);
                }
                else {
                    String name = vals[0];
                    String value = vals[1];
                    int type = tianjia(name);
                    if (type == 1) {
                        dlm.addElement(name + ":" + String.format("%.1f", new Object[] { Double.valueOf(Double.parseDouble(value)) }));
                    }
                    else {
                        dlm.addElement(name + ":" + (int)Double.parseDouble(value));
                    }
                }
            }
        }
        changViewSize();
        if (!FormsManagement.getframe(711).isVisible()) {
            FormsManagement.showForm(711);
        }
        else {
            FormsManagement.Switchinglevel(711);
        }
    }
    
    public static void clearShuXingView() {
        FaShuKangXingJpanel[] shuXingJpanel = RolePetResistanceJframe.getBaoResistancejframe().getResistancejpanel().getShuXingJpanel();
        for (int i = 0; i < shuXingJpanel.length; ++i) {
            shuXingJpanel[i].getDlm().removeAllElements();
            shuXingJpanel[i].getDlm1().removeAllElements();
        }
    }
    
    public static void changViewSize() {
        RolePetResistanceJframe baoResistancejframe = RolePetResistanceJframe.getBaoResistancejframe();
        FaShuKangXingJpanel[] shuXingJpanel = baoResistancejframe.getResistancejpanel().getShuXingJpanel();
        for (int i = 0; i < shuXingJpanel.length; ++i) {
            int leftNum = shuXingJpanel[i].getDlm().getSize();
            int rightNum = shuXingJpanel[i].getDlm1().getSize();
            shuXingJpanel[i].getListNo1().setBounds(5, 26, 135, leftNum * 20);
            shuXingJpanel[i].getListNo2().setBounds(145, 26, 140, rightNum * 20);
            int num = (leftNum > rightNum) ? leftNum : rightNum;
            num = ((num > 0) ? (num * 20 + 34) : 24);
            int y = 0;
            for (int j = 0; j < i; ++j) {
                y += shuXingJpanel[j].getHeight();
            }
            shuXingJpanel[i].setBounds(0, y, 300, num);
        }
        int y2 = 0;
        for (int k = 0; k < shuXingJpanel.length; ++k) {
            y2 += shuXingJpanel[k].getHeight();
        }
        if (MyIsif.getStyle().equals("水墨")) {
            baoResistancejframe.getResistancejpanel().setSize(300, y2);
            baoResistancejframe.setSize(300, y2);
        }
        else {
            baoResistancejframe.getResistancejpanel().setSize(289, y2);
            baoResistancejframe.setSize(289, y2);
        }
    }
    
    private static int tianjia(String name) {
        int type = 0;
        int n = -1;
        switch (name.hashCode()) {
            case 712175: {
                if (name.equals("回复")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 651535: {
                if (name.equals("伤害")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 25327575: {
                if (name.equals("抗落宝")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 783307164: {
                if (name.equals("援助几率")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 1031732391: {
                if (name.equals("落宝几率")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4: {
                type = 1;
                break;
            }
        }
        return type;
    }
    
    private static int getPropertyType(String name) {
        return 0;
    }
    
    public static ImageIcon lingbaoimg(String lingbao, int w, int h) {
        return CutButtonImage.getImage("img/lingbao/" + lingbao + ".png", w, h);
    }
    
    public void drawTrans(Graphics g, int x, int y, int ys, List<Lingbao> lingbaos) {
        ys = -ys * 9 - 1;
        LOOP:
        for (int i = 0; i < this.lingBaos.length; ++i) {
            Lingbao lingbao = this.lingBaos[i];
            if (lingbao != null && lingbao.getEquipment() == 0) {
                if (lingbaos != null) {
                    int j = lingbaos.size() - 1;
                    while (j >= 0) {
                        if (lingbao.getBaoid().compareTo(((Lingbao)lingbaos.get(j)).getBaoid()) == 0) {
                            continue LOOP;
                        }
                        else {
                            --j;
                        }
                    }
                }
                if (++ys >= 0) {
                    g.drawImage(lingbaoimg(lingbao.getSkin(), -1, -1).getImage(), x + ys % 3 * 51, y + ys / 3 * 51 + 2, 45, 45, null);
                    if (ys >= 8) {
                        return;
                    }
                }
            }
        }
    }
    
    public Lingbao getTrans(int ys, List<Lingbao> lingbaos, int p) {
        ys = -ys * 9 - 1;
        LOOP:
        for (int i = 0; i < this.lingBaos.length; ++i) {
            Lingbao lingbao = this.lingBaos[i];
            if (lingbao != null && lingbao.getEquipment() == 0) {
                if (lingbaos != null) {
                    int j = lingbaos.size() - 1;
                    while (j >= 0) {
                        if (lingbao.getBaoid().compareTo(((Lingbao)lingbaos.get(j)).getBaoid()) == 0) {
                            continue LOOP;
                        }
                        else {
                            --j;
                        }
                    }
                }
                if (++ys == p) {
                    return lingbao;
                }
            }
        }
        return null;
    }
    
    public void drawCBG(Graphics g, int x, int y, int ys) {
        ys = -ys * 30 - 1;
        for (int i = 0; i < this.lingBaos.length; ++i) {
            Lingbao lingbao = this.lingBaos[i];
            if (lingbao != null && lingbao.getEquipment() == 0 && ++ys >= 0) {
                g.drawImage(lingbaoimg(lingbao.getSkin(), -1, -1).getImage(), x + ys % 9 * 48, y + ys / 9 * 48, 45, 45, null);
                if (ys >= 29) {
                    return;
                }
            }
        }
    }
    
    public Lingbao getCBG(int ys, int p) {
        p += ys * 30;
        ys = -ys * 30 - 1;
        for (int i = 0; i < this.lingBaos.length; ++i) {
            Lingbao lingbao = this.lingBaos[i];
            if (lingbao != null && lingbao.getEquipment() == 0 && ++ys == p) {
                return lingbao;
            }
        }
        return null;
    }
    
    public Lingbao czGBG(BigDecimal rgid) {
        for (int i = 0; i < this.lingBaos.length; ++i) {
            Lingbao lingbao = this.lingBaos[i];
            if (lingbao != null && lingbao.getEquipment() == 0 && lingbao.getBaoid().compareTo(rgid) == 0) {
                return lingbao;
            }
        }
        return null;
    }
    
    public void choseuse(Lingbao lingbao, boolean type) {
        LingbaoEquipmentJpanel equipmentJpanel = LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getEquipmentJpanel();
        if (type) {
            if (lingbao.getEquipment() != 0) {
                return;
            }
            int path = -1;
            if (lingbao.getBaotype().equals("法宝")) {
                if (this.equipBao[1] == null) {
                    path = 1;
                }
                else if (this.equipBao[2] == null) {
                    path = 2;
                }
                else {
                    this.choseuse(this.equipBao[2], false);
                    path = 2;
                }
            }
            else {
                if (this.equipBao[0] != null) {
                    this.choseuse(this.equipBao[0], false);
                }
                path = 0;
            }
            if (path != -1) {
                (this.equipBao[path] = lingbao).setEquipment(1);
                UserData.upling(lingbao);
                ImageIcon imageIcon = lingbaoimg(lingbao.getSkin(), 40, 40);
                if (path == 0) {
                    equipmentJpanel.getLabLingbao().setIcon(imageIcon);
                }
                else if (path == 1) {
                    equipmentJpanel.getLabMagicOne().setIcon(imageIcon);
                }
                else {
                    equipmentJpanel.getLabMagicTwo().setIcon(imageIcon);
                }
            }
        }
        else {
            int i = 0;
            while (i < this.equipBao.length) {
                if (this.equipBao[i] == lingbao) {
                    this.equipBao[i] = null;
                    lingbao.setEquipment(0);
                    UserData.upling(lingbao);
                    if (i == 0) {
                        equipmentJpanel.getLabLingbao().setIcon(null);
                        break;
                    }
                    else if (i == 1) {
                        equipmentJpanel.getLabMagicOne().setIcon(null);
                        break;
                    }
                    else {
                        equipmentJpanel.getLabMagicTwo().setIcon(null);
                        break;
                    }
                }
                else {
                    ++i;
                }
            }
        }
        RoleProperty.getRoleProperty().equipWearOff();
    }
    
    public void fushichange(Lingbao lingbao, Goodstable goodstable, boolean type) {
        if (type) {
            goodstable.setStatus(Integer.valueOf(1));
            GoodsListFromServerUntil.fushis.put(goodstable.getRgid(), goodstable);
            lingbao.fashijihe(goodstable.getRgid() + "");
            LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().shuxingzhanshi(lingbao);
            UserData.upling(lingbao);
            GoodsMouslisten.gooduse(goodstable, 0);
        }
        else {
            if (lingbao.getGoodlock() == 1) {
                ZhuFrame.getZhuJpanel().addPrompt2("灵宝已加锁");
                return;
            }
            Map<String, Object> lm = new HashMap<>();
            lm.put("lingbao", lingbao);
            lm.put("g", goodstable);
            OptionsJframe.getOptionsJframe().getOptionsJpanel().showBox(TiShiUtil.lingbaoOut, lm, "#Y你确定要取下符石#R" + goodstable.getGoodsname());
        }
    }
    
    public BigDecimal getEquipLingBaoId() {
        if (this.equipBao[0] != null) {
            return this.equipBao[0].getBaoid();
        }
        return null;
    }
    
    public boolean fushity(String[] v, String fstype) {
        if (v == null) {
            return true;
        }
        for (int i = 0; i < v.length; ++i) {
            Goodstable good = (Goodstable)GoodsListFromServerUntil.fushis.get(new BigDecimal(v[i]));
            if (fstype.equals(good.getGoodsname())) {
                return false;
            }
        }
        return true;
    }
    
    public void drawL(Graphics g, int x, int y) {
        for (int i = 0; i < 8; ++i) {
            int row = i % 4 * 51;
            int col = i / 4 * 51;
            Lingbao lingbao = this.getlingbao(i);
            if (lingbao != null) {
                if (this.LFimg[i] != null) {
                    g.drawImage(this.LFimg[i].getImage(), x + row, y + col, 48, 48, null);
                }
                if (lingbao.getEquipment() == 1) {
                    g.drawImage(RoleLingFa.LF_G.getImage(), x + row, y + col, null);
                }
                if (lingbao.getGoodlock() == 1) {
                    g.drawImage(GoodsListFromServerUntil.lockimg.getImage(), x + row + 36, y + col, 10, 12, null);
                }
            }
        }
    }
    
    public void drawF(Graphics g, int x, int y) {
        for (int i = 0; i < 8; ++i) {
            int row = i % 4 * 51;
            int col = i / 4 * 51;
            Lingbao lingbao = this.getfabao(i);
            if (lingbao != null) {
                if (this.LFimg[i + 8] != null) {
                    g.drawImage(this.LFimg[i + 8].getImage(), x + row, y + col, 48, 48, null);
                }
                if (lingbao.getEquipment() == 1) {
                    g.drawImage(RoleLingFa.LF_G.getImage(), x + row, y + col, null);
                }
            }
        }
    }
    
    public Lingbao getlingbao(int path) {
        return this.lingBaos[path + this.lingNum * 8];
    }
    
    public Lingbao getfabao(int path) {
        return this.faBaos[path + this.faNum * 8];
    }
    
    public void lingImg(int Position, ImageIcon imageIcon) {
        this.LFimg[Position] = imageIcon;
    }
    
    public void FaImg(int Position, ImageIcon imageIcon) {
        this.LFimg[Position + 8] = imageIcon;
    }
    
    public void lingNumChange(int number) {
        this.lingNum = number;
        for (int i = this.lingNum * 8; i < (this.lingNum + 1) * 8; ++i) {
            if (this.lingBaos[i] != null) {
                this.lingImg(i - this.lingNum * 8, lingbaoimg(this.lingBaos[i].getSkin(), -1, -1));
            }
            else {
                this.lingImg(i - this.lingNum * 8, null);
            }
        }
    }
    
    public void faNumChange(int number) {
        this.faNum = number;
        for (int i = this.faNum * 8; i < (this.faNum + 1) * 8; ++i) {
            if (this.faBaos[i] != null) {
                this.FaImg(i - this.faNum * 8, lingbaoimg(this.faBaos[i].getSkin(), -1, -1));
            }
            else {
                this.FaImg(i - this.faNum * 8, null);
            }
        }
    }
    
    public void lingFan(boolean type) {
        if (type) {
            if ((this.lingNum + 1) * 8 < this.lingBaos.length && this.lingBaos[(this.lingNum + 1) * 8] != null) {
                this.lingNumChange(this.lingNum + 1);
            }
        }
        else if (this.lingNum > 0) {
            this.lingNumChange(this.lingNum - 1);
        }
    }
    
    public void faFan(boolean type) {
        if (type) {
            if ((this.faNum + 1) * 8 < this.faBaos.length && this.faBaos[(this.faNum + 1) * 8] != null) {
                this.faNumChange(this.faNum + 1);
            }
        }
        else if (this.faNum > 0) {
            this.faNumChange(this.faNum - 1);
        }
    }
    
    public void addlingfa(Lingbao lingbao) {
        if (this.choseBao != null && lingbao.getBaoid().compareTo(this.choseBao.getBaoid()) == 0) {
            this.choseBao = lingbao;
            LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().shuxingzhanshi(lingbao);
        }
        int i = 0;
        while (i < this.equipBao.length) {
            Lingbao equip = this.equipBao[i];
            if (equip != null && lingbao.getBaoid().compareTo(equip.getBaoid()) == 0) {
                this.equipBao[i] = lingbao;
                break;
            }
            else {
                ++i;
            }
        }
        if (!lingbao.getBaotype().equals("法宝")) {
            int p = -1;
            for (int j = 0; j < this.lingBaos.length; ++j) {
                if (this.lingBaos[j] == null) {
                    if (p == -1) {
                        p = j;
                    }
                }
                else if (this.lingBaos[j].getBaoid().compareTo(lingbao.getBaoid()) == 0) {
                    this.lingBaos[j] = lingbao;
                    return;
                }
            }
            if (p == -1) {
                return;
            }
            this.lingBaos[p] = lingbao;
            this.lingNumChange(this.lingNum);
        }
        else {
            int p = -1;
            for (int j = 0; j < this.faBaos.length; ++j) {
                if (this.faBaos[j] == null) {
                    if (p == -1) {
                        p = j;
                    }
                }
                else if (this.faBaos[j].getBaoid().compareTo(lingbao.getBaoid()) == 0) {
                    this.faBaos[j] = lingbao;
                    return;
                }
            }
            if (p == -1) {
                return;
            }
            if (this.isFB(lingbao.getBaoname())) {
                lingbao.setOperation("删除");
                UserData.upling(lingbao);
                return;
            }
            this.faBaos[p] = lingbao;
            this.faNumChange(this.lingNum);
        }
    }
    
    public void lingChange(List<Lingbao> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < this.lingBaos.length; ++i) {
            this.lingBaos[i] = null;
        }
        for (int i = 0; i < this.faBaos.length; ++i) {
            this.faBaos[i] = null;
        }
        for (int i = 0; i < this.equipBao.length; ++i) {
            this.equipBao[i] = null;
        }
        LingbaoEquipmentJpanel equipmentJpanel = LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getEquipmentJpanel();
        for (int j = 0; j < list.size(); ++j) {
            Lingbao lingbao = (Lingbao)list.get(j);
            if (lingbao.getEquipment() == 1) {
                if (lingbao.getBaotype().equals("法宝")) {
                    if (this.equipBao[1] == null) {
                        this.equipBao[1] = lingbao;
                        ImageIcon imageIcon = lingbaoimg(lingbao.getSkin(), 40, 40);
                        equipmentJpanel.getLabMagicOne().setIcon(imageIcon);
                    }
                    else {
                        this.equipBao[2] = lingbao;
                        ImageIcon imageIcon = lingbaoimg(lingbao.getSkin(), 40, 40);
                        equipmentJpanel.getLabMagicTwo().setIcon(imageIcon);
                    }
                }
                else {
                    this.choseBao = lingbao;
                    this.equipBao[0] = lingbao;
                    ImageIcon imageIcon = lingbaoimg(lingbao.getSkin(), 40, 40);
                    equipmentJpanel.getLabLingbao().setIcon(imageIcon);
                    LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().shuxingzhanshi(lingbao);
                }
            }
            if (!lingbao.getBaotype().equals("法宝")) {
                int l = 0;
                while (l < this.lingBaos.length) {
                    if (this.lingBaos[l] == null) {
                        this.lingBaos[l] = lingbao;
                        break;
                    }
                    else {
                        ++l;
                    }
                }
            }
            else if (!this.isFB(lingbao.getBaoname())) {
                int k = 0;
                while (k < this.faBaos.length) {
                    if (this.faBaos[k] == null) {
                        this.faBaos[k] = lingbao;
                        break;
                    }
                    else {
                        ++k;
                    }
                }
            }
            else {
                lingbao.setOperation("删除");
                UserData.upling(lingbao);
            }
        }
        this.lingNumChange(this.lingNum);
        this.faNumChange(this.faNum);
    }
    
    public void deletelingToId(BigDecimal id) {
        int i = 0;
        while (i < this.lingBaos.length) {
            if (this.lingBaos[i] != null && id.compareTo(this.lingBaos[i].getBaoid()) == 0) {
                this.lingBaos[i] = null;
                break;
            }
            else {
                ++i;
            }
        }
        this.lingChange(this.lingchangelist2());
    }
    
    public void updateKX(String kx) {
        if (this.choseBao == null) {
            ZhuFrame.getZhuJpanel().addPrompt("先选中需要转换抗性的灵宝或者法宝");
            return;
        }
        if (UserData.uptael(200000L)) {
            int lvl = this.choseBao.getLingbaolvl().intValue();
            this.choseBao.setKangxing(UserData.kangxing(lvl, kx));
            UserData.upling(this.choseBao);
            RoleProperty.getRoleProperty().equipWearOff();
            LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().shuxingzhanshi(this.choseBao);
        }
    }
    
    public void deleteling(Lingbao lingbao) {
        int i = 0;
        while (i < this.lingBaos.length) {
            if (this.lingBaos[i] != null && lingbao.getBaoid().compareTo(this.lingBaos[i].getBaoid()) == 0) {
                if (this.choseBao != null && this.choseBao.getBaoid().compareTo(this.lingBaos[i].getBaoid()) == 0) {
                    this.choseBao = null;
                    LingbaoJframe.getLingbaoJframe().getLingbaoJpanel().getLingbaoCardJpanel().getAttributeJpanel().shuxingzhanshi(this.choseBao);
                }
                this.lingBaos[i] = null;
                break;
            }
            else {
                ++i;
            }
        }
        this.lingChange(this.lingchangelist2());
    }
    
    public Lingbao getLingBao(BigDecimal baoid) {
        for (int i = 0; i < this.lingBaos.length; ++i) {
            if (this.lingBaos[i] != null && this.lingBaos[i].getBaoid().compareTo(baoid) == 0) {
                return this.lingBaos[i];
            }
        }
        return null;
    }
    
    public Lingbao getLingBao(int index) {
        return this.lingBaos[index];
    }
    
    public void deleteling(List<Lingbao> lingbaos) {
        if (lingbaos == null) {
            return;
        }
        for (int i = 0; i < lingbaos.size(); ++i) {
            Lingbao lingbao = (Lingbao)lingbaos.get(i);
            int j = 0;
            while (j < this.lingBaos.length) {
                if (this.lingBaos[j] != null && lingbao.getBaoid().compareTo(this.lingBaos[j].getBaoid()) == 0) {
                    this.lingBaos[j] = null;
                    break;
                }
                else {
                    ++j;
                }
            }
        }
        this.lingChange(this.lingchangelist2());
    }
    
    public List<Lingbao> lingchangelist() {
        List<Lingbao> lingbaolist = new ArrayList<>();
        for (int i = 0; i < this.lingBaos.length; ++i) {
            if (this.lingBaos[i] != null) {
                lingbaolist.add(this.lingBaos[i]);
            }
        }
        return lingbaolist;
    }
    
    public List<Lingbao> lingchangelist2() {
        List<Lingbao> lingbaolist = new ArrayList<>();
        for (int i = 0; i < this.lingBaos.length; ++i) {
            if (this.lingBaos[i] != null) {
                lingbaolist.add(this.lingBaos[i]);
            }
        }
        for (int i = 0; i < this.faBaos.length; ++i) {
            if (this.faBaos[i] != null) {
                lingbaolist.add(this.faBaos[i]);
            }
        }
        return lingbaolist;
    }
    
    public int getFaPJ() {
        int pf = 0;
        for (int i = 0; i < this.faBaos.length; ++i) {
            if (this.faBaos[i] != null) {
                pf += getFv(this.faBaos[i]);
            }
        }
        return pf;
    }
    
    public static int getFv(Lingbao lingbao) {
        int pf = 0;
        pf += lingbao.getLingbaolvl().intValue();
        pf += getQv(lingbao.getBaoquality());
        return pf;
    }
    
    public static int getQv(String quality) {
        int n = -1;
        switch (quality.hashCode()) {
            case 811615: {
                if (quality.equals("把玩")) {
                    n = 0;
                    break;
                }
                else {
                    break;
                }
            }
            case 1157111: {
                if (quality.equals("贴身")) {
                    n = 1;
                    break;
                }
                else {
                    break;
                }
            }
            case 953250: {
                if (quality.equals("珍藏")) {
                    n = 2;
                    break;
                }
                else {
                    break;
                }
            }
            case 828695: {
                if (quality.equals("无价")) {
                    n = 3;
                    break;
                }
                else {
                    break;
                }
            }
            case 647926: {
                if (quality.equals("传世")) {
                    n = 4;
                    break;
                }
                else {
                    break;
                }
            }
            case 1000027: {
                if (quality.equals("神迹")) {
                    n = 5;
                    break;
                }
                else {
                    break;
                }
            }
        }
        switch (n) {
            case 0: {
                return 10;
            }
            case 1: {
                return 20;
            }
            case 2: {
                return 30;
            }
            case 3: {
                return 40;
            }
            case 4: {
                return 50;
            }
            case 5: {
                return 60;
            }
            default: {
                return 10;
            }
        }
    }
    
    public boolean isFB(String bn) {
        for (int i = 0; i < this.faBaos.length; ++i) {
            if (this.faBaos[i] != null && this.faBaos[i].getBaoname().equals(bn)) {
                return true;
            }
        }
        return false;
    }
    
    public Lingbao getFabaoByName(String name) {
        for (int i = 0; i < this.faBaos.length; ++i) {
            if (this.faBaos[i] != null && this.faBaos[i].getBaoname().equals(name)) {
                return this.faBaos[i];
            }
        }
        return null;
    }
    
    public static boolean isFB2(String bn) {
        for (int i = 0; i < RoleLingFa.fbs.length; ++i) {
            if (RoleLingFa.fbs[i].equals(bn)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean addfb(String bn) {
        if (this.fabao.contains(bn)) {
            return false;
        }
        if (this.isFB(bn)) {
            return false;
        }
        this.fabao.add(bn);
        return true;
    }
    
    public Lingbao getChoseBao() {
        return this.choseBao;
    }
    
    public void setChoseBao(Lingbao choseBao) {
        this.choseBao = choseBao;
    }
    
    public int getLingNum() {
        return this.lingNum;
    }
    
    public void setLingNum(int lingNum) {
        this.lingNum = lingNum;
    }
    
    public int getFaNum() {
        return this.faNum;
    }
    
    public void setFaNum(int faNum) {
        this.faNum = faNum;
    }
    
    public Lingbao[] getLingBaos() {
        return this.lingBaos;
    }
    
    public void setLingBaos(Lingbao[] lingBaos) {
        this.lingBaos = lingBaos;
    }
    
    public Lingbao[] getFaBaos() {
        return this.faBaos;
    }
    
    public void setFaBaos(Lingbao[] faBaos) {
        this.faBaos = faBaos;
    }
    
    public ImageIcon[] getLFimg() {
        return this.LFimg;
    }
    
    public void setLFimg(ImageIcon[] lFimg) {
        this.LFimg = lFimg;
    }
    
    static {
        RoleLingFa.LF_G = new ImageIcon("img/lingbao/msg/lf_g.png");
        RoleLingFa.fbs = new String[] { "银索金铃", "将军令", "大势锤", "七宝玲珑塔", "黑龙珠", "幽冥鬼手", "大手印", "绝情鞭", "情网", "宝莲灯", "金箍儿", "番天印", "锦襕袈裟", "白骨爪", "化蝶" };
    }
}
