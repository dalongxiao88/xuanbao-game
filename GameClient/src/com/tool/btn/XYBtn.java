package com.tool.btn;

import java.util.Map;
import org.come.bean.ConfigureBean;
import org.come.entity.RoleSummoning;
import com.tool.role.RoleData;
import org.come.Frame.ZhuFrame;
import org.come.until.CutButtonImage;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import org.come.until.FormsManagement;
import java.math.BigDecimal;
import org.come.model.Configure;
import org.come.until.UserMessUntil;
import java.awt.event.MouseEvent;
import org.come.Jpanel.XinYuanChengShengJpane;
import org.come.Jpanel.XYXYDJpanel;
import org.come.Jpanel.XYJpanel;

public class XYBtn extends MoBanBtn
{
    public int caozuo;
    public XYJpanel xyJpanel;
    public XYXYDJpanel xyxydJpanel;
    public XinYuanChengShengJpane XinYuanChengShengJpane;
    
    public XYBtn(String iconpath, int type, int cao, XYJpanel xyJpanel) {
        super(iconpath, type);
        this.caozuo = cao;
        this.xyJpanel = xyJpanel;
    }
    
    public XYBtn(String iconpath, int type, int cao, XYXYDJpanel xyxydJpanel) {
        super(iconpath, type);
        this.caozuo = cao;
        this.xyxydJpanel = xyxydJpanel;
    }
    
    public XYBtn(String iconpath, int type, int cao, XYJpanel xyJpanel, String text) {
        super(iconpath, type);
        this.caozuo = cao;
        this.xyJpanel = xyJpanel;
        this.setText(text);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public XYBtn(String iconpath, int type, int cao, XYXYDJpanel xyxydJpanel, String text) {
        super(iconpath, type);
        this.caozuo = cao;
        this.xyxydJpanel = xyxydJpanel;
        this.setText(text);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    public XYBtn(String iconpath, int type, int cao, XinYuanChengShengJpane XinYuanChengShengJpane, String text) {
        super(iconpath, type);
        this.caozuo = cao;
        this.XinYuanChengShengJpane = XinYuanChengShengJpane;
        this.setText(text);
        this.setVerticalTextPosition(0);
        this.setHorizontalTextPosition(0);
    }
    
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        RoleSummoning pet = UserMessUntil.getChosePetMes();
        ConfigureBean allConfigure = UserMessUntil.getConfigureBean();
        Map<BigDecimal, Configure> item = allConfigure.getAllConfigure();
        Configure configure = (Configure)item.get(new BigDecimal(1));
        int skllNum = Integer.parseInt(configure.getZhsjngs());
        if (skllNum == 7) {
            if (this.caozuo == 17) {
                return;
            }
            if (this.caozuo == 16) {
                FormsManagement.HideForm(124);
                FormsManagement.showForm(125);
                return;
            }
            if (this.caozuo == 45) {
                FormsManagement.HideForm(125);
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("OPENXY&" + pet.getSummoningid() + "&" + pet.getSid()));
                return;
            }
            if (this.caozuo < 30) {
                try {
                    if (Integer.parseInt(this.xyJpanel.getNum()) > 0) {
                        XYJpanel xyJpanel = this.xyJpanel;
                        if (XYJpanel.getXiulian()[this.caozuo].getText().equals("")) {
                            switch (this.caozuo) {
                                default: {
                                    XYJpanel xyJpanel2 = this.xyJpanel;
                                    XYJpanel.getXiulian()[this.caozuo].setIcons(CutButtonImage.cuts("inkImg/background1/S412.png"));
                                    XYJpanel xyJpanel3 = this.xyJpanel;
                                    XYJpanel.getXiulian()[this.caozuo].setText("true");
                                    this.xyJpanel.setNum(String.valueOf(Integer.parseInt(this.xyJpanel.getNum()) - 1));
                                    XYJpanel xyJpanel4 = this.xyJpanel;
                                    if (XYJpanel.getJidian() == null) {
                                        XYJpanel xyJpanel5 = this.xyJpanel;
                                        XYJpanel.setJidian("#" + this.caozuo);
                                        break;
                                    }
                                    else {
                                        XYJpanel xyJpanel6 = this.xyJpanel;
                                        StringBuilder sb = new StringBuilder();
                                        XYJpanel xyJpanel7 = this.xyJpanel;
                                        XYJpanel.setJidian(sb.append(XYJpanel.getJidian()).append("#").append(this.caozuo).toString());
                                        break;
                                    }
                                }
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("#不能重复点击");
                        }
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("#R当前心意点不足");
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else if (this.caozuo <= 42) {
                if (this.caozuo != 30) {
                    XYXYDJpanel xyxydJpanel = this.xyxydJpanel;
                    if (XYXYDJpanel.panduan[this.caozuo - 31] == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2(this.caozuo - 30 + "号心路未完成！无法点亮新心路");
                        return;
                    }
                }
                int lvl = Integer.parseInt(changeGrade((int)pet.getGrade()).split("转")[1]);
                int grad = Integer.parseInt(changeGrade((int)pet.getGrade()).split("转")[0]);
                int num = 0;
                int zhongji = 0;
                int gezi = (int)pet.getOpenSeal();
                int longgu = pet.getDragon();
                if (pet.getPetSkills() != null) {
                    String[] skill = pet.getPetSkills().split("\\|");
                    for (String m : skill) {
                        if ((Integer.parseInt(m) <= 1605 && Integer.parseInt(m) >= 1600) || (Integer.parseInt(m) <= 1612 && Integer.parseInt(m) >= 1611) || (Integer.parseInt(m) <= 1827 && Integer.parseInt(m) >= 1815) || Integer.parseInt(m) == 1831 || (Integer.parseInt(m) <= 1839 && Integer.parseInt(m) >= 1833) || Integer.parseInt(m) == 1811 || Integer.parseInt(m) == 1850 || Integer.parseInt(m) == 1852 || Integer.parseInt(m) == 1854 || Integer.parseInt(m) == 1858 || Integer.parseInt(m) == 1860 || Integer.parseInt(m) == 1862 || Integer.parseInt(m) == 1864 || (Integer.parseInt(m) <= 1878 && Integer.parseInt(m) >= 1871) || Integer.parseInt(m) == 1880) {
                            ++num;
                        }
                    }
                    for (String m : skill) {
                        if ((Integer.parseInt(m) <= 1608 && Integer.parseInt(m) >= 1606) || Integer.parseInt(m) == 1814 || (Integer.parseInt(m) <= 1830 && Integer.parseInt(m) >= 1828) || (Integer.parseInt(m) <= 1842 && Integer.parseInt(m) >= 1840) || Integer.parseInt(m) == 1881 || (Integer.parseInt(m) <= 1869 && Integer.parseInt(m) >= 1865)) {
                            ++zhongji;
                        }
                    }
                }
                switch (this.caozuo - 30) {
                    case 0: {
                        if (lvl < 50) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 1: {
                        if ((long)pet.getFriendliness() < 100000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 2: {
                        if (lvl < 120 || (long)pet.getFriendliness() < 200000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 3: {
                        if (lvl < 100 || grad < 2 || (long)pet.getFriendliness() < 300000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 4: {
                        if (lvl < 120 || grad < 2 || (long)pet.getFriendliness() < 400000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 5: {
                        if (lvl < 140 || gezi < 2) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 6: {
                        if ((long)pet.getFriendliness() < 500000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 7: {
                        if (num < 2 || grad < 3 || lvl < 140) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 8: {
                        if ((long)pet.getFriendliness() < 1000000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 9: {
                        if (num < 3 || (long)pet.getFriendliness() < 2000000L || lvl < 160) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 10: {
                        if (gezi < 4 || (long)pet.getFriendliness() < 4000000L || lvl < 175 || longgu < 1) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 11: {
                        if (gezi < 6 || (long)pet.getFriendliness() < 6000000L || lvl < 180 || longgu < 3) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 12: {
                        if (num < 4 || (long)pet.getFriendliness() < 10000000L || lvl < 190 || zhongji < 1) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                }
                try {
                    XYXYDJpanel xyxydJpanel2 = this.xyxydJpanel;
                    XYXYDJpanel.getXiulian()[this.caozuo - 30].setIcons(CutButtonImage.cuts("inkImg/background1/S412.png"));
                }
                catch (Exception exception2) {
                    exception2.printStackTrace();
                }
                XYXYDJpanel xyxydJpanel3 = this.xyxydJpanel;
                XYXYDJpanel.panduan[this.caozuo - 30] = 1;
            }
            if (this.caozuo == 43) {
                int k = 0;
                for (int i = 0; i <= this.xyxydJpanel.getPanduan().length - 1; ++i) {
                    if (this.xyxydJpanel.getPanduan()[i] == 1) {
                        ++k;
                    }
                }
                XYJpanel xyJpanel8 = this.xyJpanel;
                if (XYJpanel.getJidian() == null) {
                    SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("Y#" + k + "&" + pet.getSummoningid() + "&" + pet.getSid()));
                }
                else {
                    int l = 0;
                    int n = 0;
                    while (true) {
                        int n4 = n;
                        XYJpanel xyJpanel9 = this.xyJpanel;
                        if (n4 <= XYJpanel.getXiulian().length - 1) {
                            XYJpanel xyJpanel10 = this.xyJpanel;
                            if (XYJpanel.getXiulian()[n] != null) {
                                XYJpanel xyJpanel11 = this.xyJpanel;
                                if (XYJpanel.getXiulian()[n].getText().equals("true")) {
                                    ++l;
                                }
                            }
                            ++n;
                        }
                        else {
                            break;
                        }
                    }
                    k -= l;
                    Agreement agreement = Agreement.getAgreement();
                    StringBuilder append = new StringBuilder().append("Y#");
                    XYJpanel xyJpanel12 = this.xyJpanel;
                    SendMessageUntil.toServer(agreement.rolechangeAgreement(append.append(XYJpanel.getJidian()).append("#").append(k).append("&").append(pet.getSummoningid()).append("&").append(pet.getSid()).toString()));
                }
            }
            if (this.caozuo == 44) {
                Agreement agreement2 = Agreement.getAgreement();
                StringBuilder append2 = new StringBuilder().append("Y#");
                XYJpanel xyJpanel13 = this.xyJpanel;
                SendMessageUntil.toServer(agreement2.rolechangeAgreement(append2.append(XYJpanel.getJidian()).append("#").append(this.xyJpanel.getNum()).append("&").append(pet.getSummoningid()).append("&").append(pet.getSid()).toString()));
            }
            if (this.caozuo == 46) {
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("Y#重置&" + pet.getSummoningid() + "&" + pet.getSid()));
            }
            if (this.caozuo == 444) {
                FormsManagement.showForm(126);
                FormsManagement.HideForm(124);
                FormsManagement.HideForm(125);
                FormsManagement.HideForm(18);
                FormsManagement.HideForm(6);
            }
            if (this.caozuo == 445) {
                int num2 = 0;
                int zhongji2 = 0;
                int gezi2 = (int)pet.getOpenSeal();
                int longgu2 = pet.getDragon();
                if (pet.getPetSkills() != null) {
                    String[] skill2 = pet.getPetSkills().split("\\|");
                    for (String j : skill2) {
                        if ((Integer.parseInt(j) <= 1605 && Integer.parseInt(j) >= 1600) || (Integer.parseInt(j) <= 1612 && Integer.parseInt(j) >= 1611) || (Integer.parseInt(j) <= 1827 && Integer.parseInt(j) >= 1815) || Integer.parseInt(j) == 1831 || (Integer.parseInt(j) <= 1839 && Integer.parseInt(j) >= 1833) || Integer.parseInt(j) == 1811 || Integer.parseInt(j) == 1850 || Integer.parseInt(j) == 1852 || Integer.parseInt(j) == 1854 || Integer.parseInt(j) == 1858 || Integer.parseInt(j) == 1860 || Integer.parseInt(j) == 1862 || Integer.parseInt(j) == 1864 || (Integer.parseInt(j) <= 1878 && Integer.parseInt(j) >= 1871) || Integer.parseInt(j) == 1880) {
                            ++num2;
                        }
                    }
                    for (String j : skill2) {
                        if ((Integer.parseInt(j) <= 1608 && Integer.parseInt(j) >= 1606) || Integer.parseInt(j) == 1814 || (Integer.parseInt(j) <= 1830 && Integer.parseInt(j) >= 1828) || (Integer.parseInt(j) <= 1842 && Integer.parseInt(j) >= 1840) || Integer.parseInt(j) == 1881 || (Integer.parseInt(j) <= 1869 && Integer.parseInt(j) >= 1865)) {
                            ++zhongji2;
                        }
                    }
                }
                BigDecimal sid = RoleData.getRoleData().getLoginResult().getSummoning_id();
                pet = null;
                if (sid != null) {
                    int i2 = 0;
                    while (i2 < UserMessUntil.getPetListTable().size()) {
                        if (((RoleSummoning)UserMessUntil.getPetListTable().get(i2)).getSid().longValue() == sid.longValue()) {
                            pet = (RoleSummoning)UserMessUntil.getPetListTable().get(i2);
                            break;
                        }
                        else {
                            ++i2;
                        }
                    }
                }
                if (pet != null && !pet.getSummoningid().equals("200192")) {
                    ZhuFrame.getZhuJpanel().addPrompt2("当前参战召唤兽不是心猿，想成圣？做梦去吧！");
                    return;
                }
                if (pet != null && ((int)pet.getGrade() < 190 || (long)pet.getFriendliness() < 10000000L || zhongji2 < 1 || num2 < 4 || pet.getDragon() < 3 || (int)pet.getOpenSeal() < 6)) {
                    ZhuFrame.getZhuJpanel().addPrompt2("当前参战召唤兽未满足条件！");
                    return;
                }
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("Y#成圣&" + pet.getSummoningid() + "&" + pet.getSid()));
                FormsManagement.HideForm(126);
            }
            if (this.caozuo == 446) {
                FormsManagement.HideForm(126);
            }
        }
        else if (skllNum == 5) {
            if (this.caozuo == 17) {
                return;
            }
            if (this.caozuo == 16) {
                FormsManagement.HideForm(124);
                FormsManagement.showForm(125);
                return;
            }
            if (this.caozuo == 45) {
                FormsManagement.HideForm(125);
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("OPENXY&" + pet.getSummoningid() + "&" + pet.getSid()));
                return;
            }
            if (this.caozuo < 30) {
                try {
                    if (Integer.parseInt(this.xyJpanel.getNum()) > 0) {
                        XYJpanel xyJpanel14 = this.xyJpanel;
                        if (XYJpanel.getXiulian()[this.caozuo].getText().equals("")) {
                            switch (this.caozuo) {
                                default: {
                                    XYJpanel xyJpanel15 = this.xyJpanel;
                                    XYJpanel.getXiulian()[this.caozuo].setIcons(CutButtonImage.cuts("inkImg/background1/S412.png"));
                                    XYJpanel xyJpanel16 = this.xyJpanel;
                                    XYJpanel.getXiulian()[this.caozuo].setText("true");
                                    this.xyJpanel.setNum(String.valueOf(Integer.parseInt(this.xyJpanel.getNum()) - 1));
                                    XYJpanel xyJpanel17 = this.xyJpanel;
                                    if (XYJpanel.getJidian() == null) {
                                        XYJpanel xyJpanel18 = this.xyJpanel;
                                        XYJpanel.setJidian("#" + this.caozuo);
                                        break;
                                    }
                                    else {
                                        XYJpanel xyJpanel19 = this.xyJpanel;
                                        StringBuilder sb2 = new StringBuilder();
                                        XYJpanel xyJpanel20 = this.xyJpanel;
                                        XYJpanel.setJidian(sb2.append(XYJpanel.getJidian()).append("#").append(this.caozuo).toString());
                                        break;
                                    }
                                }
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("#不能重复点击");
                        }
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("#R当前心意点不足");
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else if (this.caozuo <= 42) {
                if (this.caozuo != 30) {
                    XYXYDJpanel xyxydJpanel4 = this.xyxydJpanel;
                    if (XYXYDJpanel.panduan[this.caozuo - 31] == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2(this.caozuo - 30 + "号心路未完成！无法点亮新心路");
                        return;
                    }
                }
                int lvl = Integer.parseInt(changeGrade((int)pet.getGrade()).split("转")[1]);
                int grad = Integer.parseInt(changeGrade((int)pet.getGrade()).split("转")[0]);
                int num = 0;
                int zhongji = 0;
                int gezi = (int)pet.getOpenSeal();
                int longgu = pet.getDragon();
                if (pet.getPetSkills() != null) {
                    String[] skill = pet.getPetSkills().split("\\|");
                    for (String m : skill) {
                        if ((Integer.parseInt(m) <= 1605 && Integer.parseInt(m) >= 1600) || (Integer.parseInt(m) <= 1612 && Integer.parseInt(m) >= 1611) || (Integer.parseInt(m) <= 1827 && Integer.parseInt(m) >= 1815) || Integer.parseInt(m) == 1831 || (Integer.parseInt(m) <= 1839 && Integer.parseInt(m) >= 1833) || Integer.parseInt(m) == 1811 || Integer.parseInt(m) == 1850 || Integer.parseInt(m) == 1852 || Integer.parseInt(m) == 1854 || Integer.parseInt(m) == 1858 || Integer.parseInt(m) == 1860 || Integer.parseInt(m) == 1862 || Integer.parseInt(m) == 1864 || (Integer.parseInt(m) <= 1878 && Integer.parseInt(m) >= 1871) || Integer.parseInt(m) == 1880) {
                            ++num;
                        }
                    }
                    for (String m : skill) {
                        if ((Integer.parseInt(m) <= 1608 && Integer.parseInt(m) >= 1606) || Integer.parseInt(m) == 1814 || (Integer.parseInt(m) <= 1830 && Integer.parseInt(m) >= 1828) || (Integer.parseInt(m) <= 1842 && Integer.parseInt(m) >= 1840) || Integer.parseInt(m) == 1881 || (Integer.parseInt(m) <= 1869 && Integer.parseInt(m) >= 1865)) {
                            ++zhongji;
                        }
                    }
                }
                switch (this.caozuo - 30) {
                    case 0: {
                        if (lvl < 50) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 1: {
                        if ((long)pet.getFriendliness() < 100000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 2: {
                        if (lvl < 120 || (long)pet.getFriendliness() < 200000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 3: {
                        if (lvl < 100 || grad < 2 || (long)pet.getFriendliness() < 300000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 4: {
                        if (lvl < 120 || grad < 2 || (long)pet.getFriendliness() < 400000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 5: {
                        if (lvl < 140 || gezi < 2) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 6: {
                        if ((long)pet.getFriendliness() < 500000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 7: {
                        if (num < 1 || grad < 3 || lvl < 140) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 8: {
                        if ((long)pet.getFriendliness() < 1000000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 9: {
                        if (num < 2 || (long)pet.getFriendliness() < 2000000L || lvl < 160) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 10: {
                        if (gezi < 3 || (long)pet.getFriendliness() < 4000000L || lvl < 175 || longgu < 1) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 11: {
                        if (gezi < 5 || (long)pet.getFriendliness() < 6000000L || lvl < 180 || longgu < 3) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 12: {
                        if (num < 3 || (long)pet.getFriendliness() < 10000000L || lvl < 190 || zhongji < 1) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                }
                try {
                    XYXYDJpanel xyxydJpanel5 = this.xyxydJpanel;
                    XYXYDJpanel.getXiulian()[this.caozuo - 30].setIcons(CutButtonImage.cuts("inkImg/background1/S412.png"));
                }
                catch (Exception exception2) {
                    exception2.printStackTrace();
                }
                XYXYDJpanel xyxydJpanel6 = this.xyxydJpanel;
                XYXYDJpanel.panduan[this.caozuo - 30] = 1;
            }
            if (this.caozuo == 43) {
                int k = 0;
                for (int i = 0; i <= this.xyxydJpanel.getPanduan().length - 1; ++i) {
                    if (this.xyxydJpanel.getPanduan()[i] == 1) {
                        ++k;
                    }
                }
                XYJpanel xyJpanel21 = this.xyJpanel;
                if (XYJpanel.getJidian() == null) {
                    SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("Y#" + k + "&" + pet.getSummoningid() + "&" + pet.getSid()));
                }
                else {
                    int l = 0;
                    int n = 0;
                    while (true) {
                        int n9 = n;
                        XYJpanel xyJpanel22 = this.xyJpanel;
                        if (n9 <= XYJpanel.getXiulian().length - 1) {
                            XYJpanel xyJpanel23 = this.xyJpanel;
                            if (XYJpanel.getXiulian()[n] != null) {
                                XYJpanel xyJpanel24 = this.xyJpanel;
                                if (XYJpanel.getXiulian()[n].getText().equals("true")) {
                                    ++l;
                                }
                            }
                            ++n;
                        }
                        else {
                            break;
                        }
                    }
                    k -= l;
                    Agreement agreement3 = Agreement.getAgreement();
                    StringBuilder append3 = new StringBuilder().append("Y#");
                    XYJpanel xyJpanel25 = this.xyJpanel;
                    SendMessageUntil.toServer(agreement3.rolechangeAgreement(append3.append(XYJpanel.getJidian()).append("#").append(k).append("&").append(pet.getSummoningid()).append("&").append(pet.getSid()).toString()));
                }
            }
            if (this.caozuo == 44) {
                Agreement agreement4 = Agreement.getAgreement();
                StringBuilder append4 = new StringBuilder().append("Y#");
                XYJpanel xyJpanel26 = this.xyJpanel;
                SendMessageUntil.toServer(agreement4.rolechangeAgreement(append4.append(XYJpanel.getJidian()).append("#").append(this.xyJpanel.getNum()).append("&").append(pet.getSummoningid()).append("&").append(pet.getSid()).toString()));
            }
            if (this.caozuo == 46) {
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("Y#重置&" + pet.getSummoningid()));
            }
            if (this.caozuo == 444) {
                FormsManagement.showForm(126);
                FormsManagement.HideForm(124);
                FormsManagement.HideForm(125);
                FormsManagement.HideForm(18);
                FormsManagement.HideForm(6);
            }
            if (this.caozuo == 445) {
                int num2 = 0;
                int zhongji2 = 0;
                if (pet.getPetSkills() != null) {
                    String[] skill3 = pet.getPetSkills().split("\\|");
                    for (String m2 : skill3) {
                        if ((Integer.parseInt(m2) <= 1605 && Integer.parseInt(m2) >= 1600) || (Integer.parseInt(m2) <= 1612 && Integer.parseInt(m2) >= 1611) || (Integer.parseInt(m2) <= 1827 && Integer.parseInt(m2) >= 1815) || Integer.parseInt(m2) == 1831 || (Integer.parseInt(m2) <= 1839 && Integer.parseInt(m2) >= 1833) || Integer.parseInt(m2) == 1811 || Integer.parseInt(m2) == 1850 || Integer.parseInt(m2) == 1852 || Integer.parseInt(m2) == 1854 || Integer.parseInt(m2) == 1858 || Integer.parseInt(m2) == 1860 || Integer.parseInt(m2) == 1862 || Integer.parseInt(m2) == 1864 || (Integer.parseInt(m2) <= 1878 && Integer.parseInt(m2) >= 1871) || Integer.parseInt(m2) == 1880) {
                            ++num2;
                        }
                    }
                    for (String m2 : skill3) {
                        if ((Integer.parseInt(m2) <= 1608 && Integer.parseInt(m2) >= 1606) || Integer.parseInt(m2) == 1814 || (Integer.parseInt(m2) <= 1830 && Integer.parseInt(m2) >= 1828) || (Integer.parseInt(m2) <= 1842 && Integer.parseInt(m2) >= 1840) || Integer.parseInt(m2) == 1881 || (Integer.parseInt(m2) <= 1869 && Integer.parseInt(m2) >= 1865)) {
                            ++zhongji2;
                        }
                    }
                }
                BigDecimal sid2 = RoleData.getRoleData().getLoginResult().getSummoning_id();
                pet = null;
                if (sid2 != null) {
                    int i3 = 0;
                    while (i3 < UserMessUntil.getPetListTable().size()) {
                        if (((RoleSummoning)UserMessUntil.getPetListTable().get(i3)).getSid().longValue() == sid2.longValue()) {
                            pet = (RoleSummoning)UserMessUntil.getPetListTable().get(i3);
                            break;
                        }
                        else {
                            ++i3;
                        }
                    }
                }
                if (pet != null && !pet.getSummoningid().equals("200192")) {
                    ZhuFrame.getZhuJpanel().addPrompt2("当前参战召唤兽不是心猿，想成圣？做梦去吧！");
                    return;
                }
                if (pet != null && ((int)pet.getGrade() < 190 || (long)pet.getFriendliness() < 10000000L || zhongji2 < 1 || num2 < 3 || pet.getDragon() < 3 || (int)pet.getOpenSeal() < 5)) {
                    ZhuFrame.getZhuJpanel().addPrompt2("当前参战召唤兽未满足条件！");
                    return;
                }
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("Y#成圣&" + pet.getSummoningid() + "&" + pet.getSid()));
                FormsManagement.HideForm(126);
            }
            if (this.caozuo == 446) {
                FormsManagement.HideForm(126);
            }
        }
        else {
            if (this.caozuo == 17) {
                return;
            }
            if (this.caozuo == 16) {
                FormsManagement.HideForm(124);
                FormsManagement.showForm(125);
                return;
            }
            if (this.caozuo == 45) {
                FormsManagement.HideForm(125);
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("OPENXY&" + pet.getSummoningid() + "&" + pet.getSid()));
                return;
            }
            if (this.caozuo < 30) {
                try {
                    if (Integer.parseInt(this.xyJpanel.getNum()) > 0) {
                        XYJpanel xyJpanel27 = this.xyJpanel;
                        if (XYJpanel.getXiulian()[this.caozuo].getText().equals("")) {
                            switch (this.caozuo) {
                                default: {
                                    XYJpanel xyJpanel28 = this.xyJpanel;
                                    XYJpanel.getXiulian()[this.caozuo].setIcons(CutButtonImage.cuts("inkImg/background1/S412.png"));
                                    XYJpanel xyJpanel29 = this.xyJpanel;
                                    XYJpanel.getXiulian()[this.caozuo].setText("true");
                                    this.xyJpanel.setNum(String.valueOf(Integer.parseInt(this.xyJpanel.getNum()) - 1));
                                    XYJpanel xyJpanel30 = this.xyJpanel;
                                    if (XYJpanel.getJidian() == null) {
                                        XYJpanel xyJpanel31 = this.xyJpanel;
                                        XYJpanel.setJidian("#" + this.caozuo);
                                        break;
                                    }
                                    else {
                                        XYJpanel xyJpanel32 = this.xyJpanel;
                                        StringBuilder sb3 = new StringBuilder();
                                        XYJpanel xyJpanel33 = this.xyJpanel;
                                        XYJpanel.setJidian(sb3.append(XYJpanel.getJidian()).append("#").append(this.caozuo).toString());
                                        break;
                                    }
                                }
                            }
                        }
                        else {
                            ZhuFrame.getZhuJpanel().addPrompt2("#不能重复点击");
                        }
                    }
                    else {
                        ZhuFrame.getZhuJpanel().addPrompt2("#R当前心意点不足");
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            else if (this.caozuo <= 42) {
                if (this.caozuo != 30) {
                    XYXYDJpanel xyxydJpanel7 = this.xyxydJpanel;
                    if (XYXYDJpanel.panduan[this.caozuo - 31] == 0) {
                        ZhuFrame.getZhuJpanel().addPrompt2(this.caozuo - 30 + "号心路未完成！无法点亮新心路");
                        return;
                    }
                }
                int lvl = Integer.parseInt(changeGrade((int)pet.getGrade()).split("转")[1]);
                int grad = Integer.parseInt(changeGrade((int)pet.getGrade()).split("转")[0]);
                int num = 0;
                int zhongji = 0;
                int gezi = (int)pet.getOpenSeal();
                int longgu = pet.getDragon();
                if (pet.getPetSkills() != null) {
                    String[] skill = pet.getPetSkills().split("\\|");
                    for (String m : skill) {
                        if ((Integer.parseInt(m) <= 1605 && Integer.parseInt(m) >= 1600) || (Integer.parseInt(m) <= 1612 && Integer.parseInt(m) >= 1611) || (Integer.parseInt(m) <= 1827 && Integer.parseInt(m) >= 1815) || Integer.parseInt(m) == 1831 || (Integer.parseInt(m) <= 1839 && Integer.parseInt(m) >= 1833) || Integer.parseInt(m) == 1811 || Integer.parseInt(m) == 1850 || Integer.parseInt(m) == 1852 || Integer.parseInt(m) == 1854 || Integer.parseInt(m) == 1858 || Integer.parseInt(m) == 1860 || Integer.parseInt(m) == 1862 || Integer.parseInt(m) == 1864 || (Integer.parseInt(m) <= 1878 && Integer.parseInt(m) >= 1871) || Integer.parseInt(m) == 1880) {
                            ++num;
                        }
                    }
                    for (String m : skill) {
                        if ((Integer.parseInt(m) <= 1608 && Integer.parseInt(m) >= 1606) || Integer.parseInt(m) == 1814 || (Integer.parseInt(m) <= 1830 && Integer.parseInt(m) >= 1828) || (Integer.parseInt(m) <= 1842 && Integer.parseInt(m) >= 1840) || Integer.parseInt(m) == 1881 || (Integer.parseInt(m) <= 1869 && Integer.parseInt(m) >= 1865)) {
                            ++zhongji;
                        }
                    }
                }
                switch (this.caozuo - 30) {
                    case 0: {
                        if (lvl < 50) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 1: {
                        if ((long)pet.getFriendliness() < 100000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 2: {
                        if (lvl < 120 || (long)pet.getFriendliness() < 200000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 3: {
                        if (lvl < 100 || grad < 2 || (long)pet.getFriendliness() < 300000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 4: {
                        if (lvl < 120 || grad < 2 || (long)pet.getFriendliness() < 400000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 5: {
                        if (lvl < 140 || gezi < 2) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 6: {
                        if ((long)pet.getFriendliness() < 500000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 7: {
                        if (num < 2 || grad < 3 || lvl < 140) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 8: {
                        if ((long)pet.getFriendliness() < 1000000L) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 9: {
                        if (num < 3 || (long)pet.getFriendliness() < 2000000L || lvl < 160) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 10: {
                        if (gezi < 4 || (long)pet.getFriendliness() < 4000000L || lvl < 175 || longgu < 1) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 11: {
                        if (gezi < 8 || (long)pet.getFriendliness() < 6000000L || lvl < 180 || longgu < 3) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                    case 12: {
                        if (num < 4 || (long)pet.getFriendliness() < 10000000L || lvl < 190 || zhongji < 2) {
                            ZhuFrame.getZhuJpanel().addPrompt2("条件未满足！无法点亮新心路");
                            return;
                        }
                        else {
                            break;
                        }
                    }
                }
                try {
                    XYXYDJpanel xyxydJpanel8 = this.xyxydJpanel;
                    XYXYDJpanel.getXiulian()[this.caozuo - 30].setIcons(CutButtonImage.cuts("inkImg/background1/S412.png"));
                }
                catch (Exception exception2) {
                    exception2.printStackTrace();
                }
                XYXYDJpanel xyxydJpanel9 = this.xyxydJpanel;
                XYXYDJpanel.panduan[this.caozuo - 30] = 1;
            }
            if (this.caozuo == 43) {
                int k = 0;
                for (int i = 0; i <= this.xyxydJpanel.getPanduan().length - 1; ++i) {
                    if (this.xyxydJpanel.getPanduan()[i] == 1) {
                        ++k;
                    }
                }
                XYJpanel xyJpanel34 = this.xyJpanel;
                if (XYJpanel.getJidian() == null) {
                    SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("Y#" + k + "&" + pet.getSummoningid() + "&" + pet.getSid()));
                }
                else {
                    int l = 0;
                    int n = 0;
                    while (true) {
                        int n14 = n;
                        XYJpanel xyJpanel35 = this.xyJpanel;
                        if (n14 <= XYJpanel.getXiulian().length - 1) {
                            XYJpanel xyJpanel36 = this.xyJpanel;
                            if (XYJpanel.getXiulian()[n] != null) {
                                XYJpanel xyJpanel37 = this.xyJpanel;
                                if (XYJpanel.getXiulian()[n].getText().equals("true")) {
                                    ++l;
                                }
                            }
                            ++n;
                        }
                        else {
                            break;
                        }
                    }
                    k -= l;
                    Agreement agreement5 = Agreement.getAgreement();
                    StringBuilder append5 = new StringBuilder().append("Y#");
                    XYJpanel xyJpanel38 = this.xyJpanel;
                    SendMessageUntil.toServer(agreement5.rolechangeAgreement(append5.append(XYJpanel.getJidian()).append("#").append(k).append("&").append(pet.getSummoningid()).append("&").append(pet.getSid()).toString()));
                }
            }
            if (this.caozuo == 44) {
                Agreement agreement6 = Agreement.getAgreement();
                StringBuilder append6 = new StringBuilder().append("Y#");
                XYJpanel xyJpanel39 = this.xyJpanel;
                SendMessageUntil.toServer(agreement6.rolechangeAgreement(append6.append(XYJpanel.getJidian()).append("#").append(this.xyJpanel.getNum()).append("&").append(pet.getSummoningid()).append("&").append(pet.getSid()).toString()));
            }
            if (this.caozuo == 46) {
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("Y#重置&" + pet.getSummoningid() + "&" + pet.getSid()));
            }
            if (this.caozuo == 444) {
                FormsManagement.showForm(126);
                FormsManagement.HideForm(124);
                FormsManagement.HideForm(125);
                FormsManagement.HideForm(18);
                FormsManagement.HideForm(6);
            }
            if (this.caozuo == 445) {
                int num2 = 0;
                int zhongji2 = 0;
                if (pet.getPetSkills() != null) {
                    String[] skill3 = pet.getPetSkills().split("\\|");
                    for (String m2 : skill3) {
                        if ((Integer.parseInt(m2) <= 1605 && Integer.parseInt(m2) >= 1600) || (Integer.parseInt(m2) <= 1612 && Integer.parseInt(m2) >= 1611) || (Integer.parseInt(m2) <= 1827 && Integer.parseInt(m2) >= 1815) || Integer.parseInt(m2) == 1831 || (Integer.parseInt(m2) <= 1839 && Integer.parseInt(m2) >= 1833) || Integer.parseInt(m2) == 1811 || Integer.parseInt(m2) == 1850 || Integer.parseInt(m2) == 1852 || Integer.parseInt(m2) == 1854 || Integer.parseInt(m2) == 1858 || Integer.parseInt(m2) == 1860 || Integer.parseInt(m2) == 1862 || Integer.parseInt(m2) == 1864 || (Integer.parseInt(m2) <= 1878 && Integer.parseInt(m2) >= 1871) || Integer.parseInt(m2) == 1880) {
                            ++num2;
                        }
                    }
                    for (String m2 : skill3) {
                        if ((Integer.parseInt(m2) <= 1608 && Integer.parseInt(m2) >= 1606) || Integer.parseInt(m2) == 1814 || (Integer.parseInt(m2) <= 1830 && Integer.parseInt(m2) >= 1828) || (Integer.parseInt(m2) <= 1842 && Integer.parseInt(m2) >= 1840) || Integer.parseInt(m2) == 1881 || (Integer.parseInt(m2) <= 1869 && Integer.parseInt(m2) >= 1865)) {
                            ++zhongji2;
                        }
                    }
                }
                BigDecimal sid2 = RoleData.getRoleData().getLoginResult().getSummoning_id();
                pet = null;
                if (sid2 != null) {
                    int i3 = 0;
                    while (i3 < UserMessUntil.getPetListTable().size()) {
                        if (((RoleSummoning)UserMessUntil.getPetListTable().get(i3)).getSid().longValue() == sid2.longValue()) {
                            pet = (RoleSummoning)UserMessUntil.getPetListTable().get(i3);
                            break;
                        }
                        else {
                            ++i3;
                        }
                    }
                }
                if (pet != null && !pet.getSummoningid().equals("200192")) {
                    ZhuFrame.getZhuJpanel().addPrompt2("当前参战召唤兽不是心猿，想成圣？做梦去吧！");
                    return;
                }
                if (pet != null && (pet.getSummoningskin().equals("500196") || pet.getSummoningskin().equals("500195"))) {
                    ZhuFrame.getZhuJpanel().addPrompt2("你已经成圣了，你还想做什么？");
                    return;
                }
                if (pet != null && ((int)pet.getGrade() < 190 || (long)pet.getFriendliness() < 10000000L || zhongji2 < 2 || num2 < 4 || pet.getDragon() < 3 || (int)pet.getOpenSeal() < 8)) {
                    ZhuFrame.getZhuJpanel().addPrompt2("当前参战召唤兽未满足条件！");
                    return;
                }
                SendMessageUntil.toServer(Agreement.getAgreement().rolechangeAgreement("Y#成圣&" + pet.getSummoningid() + "&" + pet.getSid()));
                FormsManagement.HideForm(126);
            }
            if (this.caozuo == 446) {
                FormsManagement.HideForm(126);
            }
        }
    }
    
    public static String changeGrade(int grade) {
        String mes = null;
        if (grade <= 100) {
            mes = "0转" + grade;
        }
        else if (grade <= 221) {
            mes = "1转" + (grade - 101);
        }
        else if (grade <= 362) {
            mes = "2转" + (grade - 222);
        }
        else if (grade <= 543) {
            mes = "3转" + (grade - 363);
        }
        else {
            mes = "4转" + (grade - 544);
        }
        return mes;
    }
}
