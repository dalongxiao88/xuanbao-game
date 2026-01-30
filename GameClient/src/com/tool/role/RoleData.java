package com.tool.role;

import org.come.entity.RoleSummoning;
import org.come.model.Title;
import org.come.entity.Fly;
import org.come.entity.Goodstable;
import org.come.until.UserMessUntil;
import com.tool.time.TimeLimit;
import org.come.until.GoodsListFromServerUntil;
import org.come.until.SendRoleAndRolesummingUntil;
import org.come.until.AnalysisString;
import com.tool.image.ImageMixDeal;
import java.util.ArrayList;
import org.come.bean.TeamBean;
import org.come.entity.Hang;
import org.come.entity.Pal;
import org.come.bean.RoleAttribute;
import java.math.BigDecimal;
import java.util.List;
import org.come.bean.PrivateData;
import org.come.entity.PackRecord;
import org.come.bean.LoginResult;

public class RoleData
{
    private static RoleData roleData;
    private LoginResult loginResult;
    private PackRecord packRecord;
    private RoleSystem roleSystem;
    private PrivateData privateData;
    private List<BigDecimal> helpBb;
    private List<BigDecimal> petOrder;
    private RoleAttribute roleAttribute;
    private List fly;
    private List<Pal> pals;
    private List<Hang> helpFs;
    private TeamBean teamBean;
    
    public static RoleData getRoleData() {
        if (RoleData.roleData == null) {
            RoleData.roleData = new RoleData();
        }
        return RoleData.roleData;
    }
    
    public RoleData() {
        this.fly = new ArrayList<>();
        this.packRecord = new PackRecord();
        this.roleSystem = new RoleSystem();
        this.privateData = new PrivateData();
        this.petOrder = new ArrayList<>();
        this.helpBb = new ArrayList<>();
    }
    
    public void init(PackRecord packRecord, RoleSystem roleSystem, PrivateData privateData, LoginResult role) {
        if (role != null) {
            this.loginResult = role;
        }
        if (packRecord != null) {
            this.packRecord = packRecord;
            String bbs = packRecord.getHelpBb();
            if (bbs != null) {
                String[] v = bbs.split("\\|");
                this.helpBb.clear();
                for (int i = 0; i < v.length; ++i) {
                    BigDecimal a = new BigDecimal(v[i]);
                    this.helpBb.add(a);
                }
            }
            String petIds = packRecord.getPetOrder();
            if (petIds != null) {
                String[] vs = petIds.split("\\|");
                this.petOrder.clear();
                for (int j = 0; j < vs.length; ++j) {
                    BigDecimal v2 = new BigDecimal(vs[j]);
                    this.petOrder.add(v2);
                }
            }
            RoleTX roleTX = RoleTX.getRoleTX();
            roleTX.Toggle2(0);
            roleTX.EToggle(-1);
            roleTX.EToggle(-2);
            roleTX.EToggle(-3);
            roleTX.EToggle(-4);
            roleTX.EToggle(-5);
            List<String> list = this.packRecord.getPutTX();
            List<String> list2 = this.packRecord.getPutTXK();
            if (list != null) {
                for (int k = 0; k < list.size(); ++k) {
                    roleTX.EToggle(Integer.parseInt((String)list.get(k)));
                }
            }
            if (list2 != null) {
                for (int k = 0; k < list2.size(); ++k) {
                    roleTX.EToggle(Integer.parseInt((String)list2.get(k)));
                }
            }
            roleTX.chushihuaWing();
        }
        if (roleSystem != null) {
            this.roleSystem = roleSystem;
        }
        if (privateData != null) {
            this.privateData = privateData;
            String[] vs2 = privateData.getSkill("S");
            if (vs2 != null) {
                boolean is = false;
                List<Integer> list3 = new ArrayList<>();
                for (int j = 0; j < vs2.length; ++j) {
                    String[] vss = vs2[j].split("_");
                    int id = Integer.parseInt(vss[0]);
                    if (!list3.contains(Integer.valueOf(id))) {
                        list3.add(Integer.valueOf(Integer.parseInt(vss[0])));
                    }
                    else {
                        is = true;
                    }
                }
                String[] ses = SkillUtil.getSepciesS(SkillUtil.getSepciesN(ImageMixDeal.userimg.getRoleShow().getSpecies_id()));
                for (int l = list3.size() - 1; l >= 0; --l) {
                    int id = SkillUtil.changeSkillId((int)list3.get(l), ses);
                    if (id != (int)list3.get(l)) {
                        list3.remove(l);
                        is = true;
                    }
                }
                if (is) {
                    int sld = AnalysisString.shuliandu((int)ImageMixDeal.userimg.getRoleShow().getGrade());
                    StringBuffer buffer = new StringBuffer();
                    for (int m = list3.size() - 1; m >= 0; --m) {
                        if (buffer.length() != 0) {
                            buffer.append("#");
                        }
                        buffer.append(list3.get(m));
                        buffer.append("_");
                        buffer.append(sld);
                    }
                    this.privateData.setSkills("S", (buffer.length() == 0) ? null : buffer.toString());
                    SendRoleAndRolesummingUntil.sendRole(this.privateData);
                }
            }
        }
    }
    
    public void changeSkin(boolean is) {
        String skin = this.loginResult.getSkin();
        String skin2 = this.getSkin();
        if (skin == null) {
            skin = "";
        }
        if (skin2 == null) {
            skin2 = "";
        }
        if (!skin.equals(skin2)) {
            skin2 = (skin2.equals("") ? null : skin2);
            this.loginResult.setSkin(skin2);
            ImageMixDeal.userimg.getRoleShow().setSkin(this.loginResult.getSkin());
            ImageMixDeal.userimg.getRoleShow().setFlyskin(this.loginResult.getFlyskin());
            ImageMixDeal.userimg.changeskin(null);
            if (is) {
                List<String> list = this.packRecord.getPutTX();
                if (list != null && list.size() != 0) {
                    StringBuffer buffer = new StringBuffer();
                    buffer.append("E");
                    buffer.append((String)list.get(0));
                    for (int i = 1; i < list.size(); ++i) {
                        buffer.append("_");
                        buffer.append((String)list.get(i));
                    }
                    if (skin2 == null) {
                        skin2 = buffer.toString();
                    }
                    else {
                        skin2 = skin2 + "|" + buffer;
                    }
                }
            }
            GoodsListFromServerUntil.sendPackRecord(5, skin2);
        }
    }
    
    public String getSkin() {
        String fSkin = null;
        String eSkin = TimeLimit.getLimits().getSkin();
        if (eSkin == null) {
            Goodstable good = GoodsListFromServerUntil.getChoseGoodsList()[0];
            if (good != null) {
                long weaponSkin = (long)good(Integer.parseInt(good.getSkin()));
                if (weaponSkin != 0L) {
                    weaponSkin += 18L;
                    eSkin = String.valueOf(weaponSkin << 32 | this.loginResult.getSpecies_id().longValue());
                }
            }
        }
        Fly fly = (this.loginResult.getFly_id() != null) ? this.getFlyOne(this.loginResult.getFly_id()) : null;
        if (fly != null) {}
        StringBuffer buffer = this.packRecord.getPutTXBuffer();
        if (eSkin != null && !eSkin.equals("")) {
            if (buffer == null) {
                buffer = new StringBuffer();
            }
            else if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append("S");
            buffer.append(eSkin);
        }
        if (fSkin != null && !fSkin.equals("")) {
            if (buffer == null) {
                buffer = new StringBuffer();
            }
            else if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append("F");
            buffer.append(fSkin);
        }
        if (this.loginResult.getTitle() != null && !this.loginResult.getTitle().equals("")) {
            Title title = UserMessUntil.getTitle(this.loginResult.getTitle());
            if (title != null && title.getSkin() != null && !title.getSkin().equals("")) {
                if (buffer == null) {
                    buffer = new StringBuffer();
                }
                else if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append("C");
                buffer.append(title.getSkin());
            }
        }
        if (GoodsListFromServerUntil.getChoseGoodsList()[12] != null) {
            if (buffer == null) {
                buffer = new StringBuffer();
            }
            else if (buffer.length() != 0) {
                buffer.append("|");
            }
            buffer.append("B");
            buffer.append(GoodsListFromServerUntil.getChoseGoodsList()[12].getSkin());
        }
        return (buffer != null && buffer.length() != 0) ? buffer.toString() : null;
    }
    
    public void addHelpBb(List<BigDecimal> Bbs) {
        for (int i = this.helpBb.size() - 1; i >= 0; --i) {
            if (!Bbs.contains(this.helpBb.get(i))) {
                this.helpBb.remove(i);
            }
        }
        for (int i = 0; i < Bbs.size(); ++i) {
            if (!this.helpBb.contains(Bbs.get(i))) {
                this.helpBb.add(Bbs.get(i));
            }
        }
    }
    
    public void addOrderPet(List<RoleSummoning> pets) {
        boolean is = false;
        List<BigDecimal> ids = new ArrayList<>();
        for (int i = 0; i < pets.size(); ++i) {
            if (!this.petOrder.contains(((RoleSummoning)pets.get(i)).getSid())) {
                this.petOrder.add(((RoleSummoning)pets.get(i)).getSid());
                is = true;
            }
            ids.add(((RoleSummoning)pets.get(i)).getSid());
        }
        for (int i = this.petOrder.size() - 1; i >= 0; --i) {
            if (!ids.contains(this.petOrder.get(i))) {
                this.petOrder.remove(i);
                is = true;
            }
        }
        if (is) {
            this.sendOrderPet();
        }
    }
    
    public List<String> getDepositBbName() {
        List<String> list = new ArrayList<>();
        List<RoleSummoning> pets = UserMessUntil.getDepositPetListTable();
        for (int i = 0; i < pets.size(); ++i) {
            list.add(((RoleSummoning)pets.get(i)).getSummoningname());
        }
        return list;
    }
    
    public List<BigDecimal> getHelpBbId() {
        int[] vs = { 1806, 1820, 1821, 1822, 1823, 1824, 1825, 1826, 1827 };
        List<BigDecimal> list = new ArrayList<>();
        List<RoleSummoning> pets = UserMessUntil.getPetListTable();
        LOOP:
        for (int i = pets.size() - 1; i >= 0; --i) {
            String skill = ((RoleSummoning)pets.get(i)).getPetSkills();
            if (skill != null && !skill.equals("")) {
                String[] v = skill.split("\\|");
                for (int j = 0; j < v.length; ++j) {
                    int id = Integer.parseInt(v[j]);
                    int k = 0;
                    while (k < vs.length) {
                        if (vs[k] == id) {
                            list.add(((RoleSummoning)pets.get(i)).getSid());
                            continue LOOP;
                        }
                        else {
                            ++k;
                        }
                    }
                }
            }
        }
        return list;
    }
    
    public static int good(int id) {
        if ((id >= 1600 && id <= 1616) || id == 6100 || id == 7006) {
            return 1;
        }
        if ((id >= 1400 && id <= 1416) || id == 6106 || id == 7012) {
            return 2;
        }
        if ((id >= 1100 && id <= 1116) || id == 6124 || id == 7021) {
            return 3;
        }
        if ((id >= 1200 && id <= 1216) || id == 6122 || id == 7022) {
            return 4;
        }
        if ((id >= 2201 && id <= 2216) || id == 6109 || id == 7016) {
            return 5;
        }
        if ((id >= 2400 && id <= 2416) || id == 6119 || id == 7020) {
            return 6;
        }
        if ((id >= 1300 && id <= 1316) || id == 6103 || id == 7009) {
            return 7;
        }
        if ((id >= 1700 && id <= 1716) || id == 6102 || id == 7008) {
            return 8;
        }
        if ((id >= 2101 && id <= 2116) || id == 7013 || id == 6105) {
            return 9;
        }
        if ((id >= 1000 && id <= 1016) || id == 6118 || id == 7007) {
            return 10;
        }
        if ((id >= 2501 && id <= 2516) || id == 7019 || id == 6120) {
            return 11;
        }
        if ((id >= 1800 && id <= 1816) || id == 6104 || id == 7011) {
            return 12;
        }
        if ((id >= 1900 && id <= 1916) || id == 6108 || id == 7017) {
            return 13;
        }
        if ((id >= 2301 && id <= 2316) || id == 6109 || id == 7010) {
            return 14;
        }
        if ((id >= 1500 && id <= 1516) || id == 7014 || id == 6117) {
            return 15;
        }
        if ((id >= 2001 && id <= 2016) || id == 6107 || id == 7015) {
            return 16;
        }
        if ((id >= 2601 && id <= 2616) || id == 7018 || id == 6121) {
            return 17;
        }
        if ((id >= 2617 && id <= 2632) || id == 6125 || id == 7023) {
            return 18;
        }
        return 0;
    }
    
    public List<String> getHelpBbName(List<BigDecimal> Bbs) {
        List<String> list = new ArrayList<>();
        List<RoleSummoning> pets = UserMessUntil.getPetListTable();
        for (int i = 0; i < this.helpBb.size(); ++i) {
            BigDecimal id = (BigDecimal)this.helpBb.get(i);
            int j = pets.size() - 1;
            while (j >= 0) {
                if (((RoleSummoning)pets.get(j)).getSid().compareTo(id) == 0) {
                    list.add(((RoleSummoning)pets.get(j)).getSummoningname());
                    break;
                }
                else {
                    --j;
                }
            }
        }
        return list;
    }
    
    public List<String> CHelpBb(int v, int v2) {
        if (v2 > this.helpBb.size() - 1) {
            v2 = this.helpBb.size() - 1;
        }
        if (v > this.helpBb.size() - 1) {
            v = this.helpBb.size() - 1;
        }
        if (v == v2) {
            return null;
        }
        if (v < 0 || v2 < 0) {
            return null;
        }
        BigDecimal id = (BigDecimal)this.helpBb.remove(v);
        this.helpBb.add(v2, id);
        this.sendHelpBb();
        return this.getHelpBbName(this.helpBb);
    }
    
    public void sendHelpBb() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < this.helpBb.size(); ++i) {
            if (i != 0) {
                buffer.append("|");
            }
            buffer.append(this.helpBb.get(i));
        }
        this.packRecord.setHelpBb(buffer.toString());
        GoodsListFromServerUntil.sendPackRecord(1, this.packRecord.getHelpBb());
    }
    
    public List<String> getOrderPetName() {
        List<String> list = new ArrayList<>();
        List<RoleSummoning> pets = UserMessUntil.getPetListTable();
        for (int i = 0; i < this.petOrder.size(); ++i) {
            BigDecimal id = (BigDecimal)this.petOrder.get(i);
            int j = pets.size() - 1;
            while (j >= 0) {
                if (((RoleSummoning)pets.get(j)).getSid().compareTo(id) == 0) {
                    list.add(((RoleSummoning)pets.get(j)).getSummoningname());
                    break;
                }
                else {
                    --j;
                }
            }
        }
        return list;
    }
    
    public List<String> updateOrderPet(int v, int v2) {
        if (v2 > this.petOrder.size() - 1) {
            v2 = this.petOrder.size() - 1;
        }
        if (v > this.petOrder.size() - 1) {
            v = this.petOrder.size() - 1;
        }
        if (v == v2) {
            return null;
        }
        if (v < 0 || v2 < 0) {
            return null;
        }
        BigDecimal id = (BigDecimal)this.petOrder.remove(v);
        this.petOrder.add(v2, id);
        this.sendOrderPet();
        return this.getOrderPetName();
    }
    
    public void sendOrderPet() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < this.petOrder.size(); ++i) {
            if (i != 0) {
                buffer.append("|");
            }
            buffer.append(this.petOrder.get(i));
        }
        this.packRecord.setPetOrder(buffer.toString());
        GoodsListFromServerUntil.sendPackRecord(6, this.packRecord.getPetOrder());
    }
    
    public void sendHelpLingbao(String helpLingbao) {
        this.packRecord.setHelpLing(helpLingbao);
        GoodsListFromServerUntil.sendPackRecord(2, this.packRecord.getHelpLing());
    }
    
    public void addTx(String id) {
        this.packRecord.addTX(id);
        RoleTX.getRoleTX().Toggle2(RoleTX.getRoleTX().getTxYs());
    }
    
    public PackRecord getPackRecord() {
        return this.packRecord;
    }
    
    public void setPackRecord(PackRecord packRecord) {
        this.packRecord = packRecord;
    }
    
    public RoleSystem getRoleSystem() {
        return this.roleSystem;
    }
    
    public void setRoleSystem(RoleSystem roleSystem) {
        this.roleSystem = roleSystem;
    }
    
    public List<BigDecimal> getPetOrder() {
        return this.petOrder;
    }
    
    public void setPetOrder(List<BigDecimal> petOrder) {
        this.petOrder = petOrder;
    }
    
    public List<BigDecimal> getHelpBb() {
        return this.helpBb;
    }
    
    public void setHelpBb(List<BigDecimal> helpBb) {
        this.helpBb = helpBb;
    }
    
    public PrivateData getPrivateData() {
        return this.privateData;
    }
    
    public void setPrivateData(PrivateData privateData) {
        this.privateData = privateData;
    }
    
    public LoginResult getLoginResult() {
        return this.loginResult;
    }
    
    public void setLoginResult(LoginResult loginResult) {
        this.loginResult = loginResult;
    }
    
    public List<Pal> getPals() {
        return this.pals;
    }
    
    public void setPals(List<Pal> pals) {
        this.pals = pals;
    }
    
    public Pal getPal(int pid) {
        for (int i = this.pals.size() - 1; i >= 0; --i) {
            if (((Pal)this.pals.get(i)).getpId() == pid) {
                return (Pal)this.pals.get(i);
            }
        }
        return null;
    }
    
    public void addPal(Pal pal) {
        for (int i = this.pals.size() - 1; i >= 0; --i) {
            if (((Pal)this.pals.get(i)).getId().compareTo(pal.getId()) == 0) {
                this.pals.set(i, pal);
                return;
            }
        }
        this.pals.add(pal);
    }
    
    public TeamBean getTeamBean() {
        return this.teamBean;
    }
    
    public void setTeamBean(TeamBean teamBean) {
        this.teamBean = teamBean;
    }
    
    public List getFly() {
        return this.fly;
    }
    
    public Fly getFlyOne(Integer fid) {
        if (this.fly != null) {
            for (int i = 0; i < this.fly.size(); ++i) {
                Fly fly2 = (Fly)this.fly.get(i);
                if (fly2.getFlytid().compareTo(fid) == 0) {
                    return fly2;
                }
            }
        }
        return null;
    }
    
    public void setFly(List fly) {
        this.fly = fly;
    }
    
    public RoleAttribute getRoleAttribute() {
        return this.roleAttribute;
    }
    
    public void setRoleAttribute(RoleAttribute roleAttribute) {
        this.roleAttribute = roleAttribute;
    }
    
    public static void setRoleData(RoleData roleData) {
        RoleData.roleData = roleData;
    }
    
    public List<Hang> getHelpFs() {
        return this.helpFs;
    }
    
    public void setHelpFs(List<Hang> helpFs) {
        this.helpFs = helpFs;
    }
}
