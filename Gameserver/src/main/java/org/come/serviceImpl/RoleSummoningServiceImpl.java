package org.come.serviceImpl;

import org.come.entity.RolesummoningRoleUser;
import org.come.entity.Goodstable;
import org.come.redis.RedisCacheUtil;
import come.tool.Role.RoleData;
import org.come.until.AllServiceUtil;
import come.tool.Role.RolePool;
import java.util.Iterator;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.Collator;
import java.util.Locale;
import org.come.redis.RedisControl;
import org.come.redis.RedisParameterUtil;
import org.come.entity.RoleSummoning;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.context.ApplicationContext;
import org.come.until.MybatisUntil;
import org.come.mapper.RoleSummoningMapper;
import org.come.service.IRoleSummoningService;

public class RoleSummoningServiceImpl implements IRoleSummoningService
{
    private RoleSummoningMapper roleSummoningMapper;
    private final Integer limit;
    
    public RoleSummoningServiceImpl() {
        this.limit = Integer.valueOf(10);
        ApplicationContext ctx = MybatisUntil.getApplicationContext();
        this.roleSummoningMapper = (RoleSummoningMapper)ctx.getBean("roleSummoningMapper");
    }
    
    @Override
    public List<RoleSummoning> selectRoleSummoningsByRoleID(BigDecimal roleid) {
        List<RoleSummoning> roleSummonings = RedisControl.getS(RedisParameterUtil.PET, roleid.toString(), RoleSummoning.class);
        if (roleSummonings.size() > 1) {
            String[] names = new String[roleSummonings.size()];
            for (int k = 0; k < roleSummonings.size(); ++k) {
                names[k] = ((RoleSummoning)roleSummonings.get(k)).getSummoningname() + "+" + ((RoleSummoning)roleSummonings.get(k)).getSid();
            }
            Comparator<Object> com = (Collator)Collator.getInstance(Locale.CHINA);
            Arrays.sort(names, com);
            List<RoleSummoning> newRoleSummonings = new ArrayList<>();
            for (int j = 0; j < names.length; ++j) {
                for (RoleSummoning roleSummoning : roleSummonings) {
                    String subName = names[j].substring(0, names[j].indexOf(43));
                    String subId = names[j].substring(names[j].indexOf(43) + 1, names[j].length());
                    if (roleSummoning.getSummoningname().equals(subName) && roleSummoning.getSid().toString().equals(subId)) {
                        newRoleSummonings.add(roleSummoning);
                    }
                }
            }
            return newRoleSummonings;
        }
        else {
            return roleSummonings;
        }
    }
    
    @Override
    public void updateRoleSummoningIndex(RoleSummoning pet, BigDecimal role_id) {
        BigDecimal yrid = pet.getRoleid();
        BigDecimal nrid = role_id;
        pet.setRoleid(role_id);
        RedisControl.insertKeyT(RedisParameterUtil.PET, pet.getSid().toString(), pet);
        RedisControl.insertController(RedisParameterUtil.PET, pet.getSid().toString(), "2");
        RedisControl.deletrValue(RedisParameterUtil.PET, yrid.toString(), pet.getSid().toString());
        RedisControl.insertListRedis(RedisParameterUtil.PET, nrid.toString(), pet.getSid().toString());
        RoleData data = RolePool.getRoleData(yrid);
        if (data != null) {
            data.CPet(pet.getSid(), false);
        }
        data = RolePool.getRoleData(nrid);
        if (data != null) {
            data.CPet(pet.getSid(), true);
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("召唤兽名称=");
        buffer.append(pet.getSummoningname());
        buffer.append("|召唤兽初值=");
        buffer.append(pet.getHp());
        buffer.append(",");
        buffer.append(pet.getMp());
        buffer.append(",");
        buffer.append(pet.getAp());
        buffer.append(",");
        buffer.append(pet.getSp());
        buffer.append("|召唤兽技能=");
        buffer.append(pet.getPetSkills());
        AllServiceUtil.getGoodsrecordService().insertGoodsrecord(yrid, nrid, 50200, pet.getSid(), "召唤兽交易", buffer.toString(), 1);
    }
    
    @Override
    public void updateRoleSummoning(RoleSummoning roleSummoning) {
        RoleSummoning pet = (RoleSummoning)RedisControl.getV(RedisParameterUtil.PET, roleSummoning.getSid().toString(), RoleSummoning.class);
        if (pet != null) {
            BigDecimal yrid = pet.getRoleid();
            BigDecimal nrid = roleSummoning.getRoleid();
            if (nrid != null && nrid.compareTo(yrid) != 0) {
                return;
            }
            roleSummoning.setTrainNum(pet.getTrainNum());
            roleSummoning.setOpenSeal(pet.getOpenSeal());
            roleSummoning.setGrowUpDanNum(pet.getGrowUpDanNum());
            roleSummoning.setColorScheme(pet.getColorScheme());
            roleSummoning.setDraC(pet.getDraC());
            roleSummoning.setRevealNum(pet.getRevealNum());
            roleSummoning.setFlyupNum(pet.getFlyupNum());
            roleSummoning.setSsn(pet.getSsn());
            roleSummoning.setGold(pet.getGold());
            roleSummoning.setWood(pet.getWood());
            roleSummoning.setSoil(pet.getSoil());
            roleSummoning.setWater(pet.getWater());
            roleSummoning.setFire(pet.getFire());
            roleSummoning.setDragon(pet.getDragon());
            roleSummoning.setGrowlevel(pet.getGrowlevel());
            roleSummoning.setHp(Integer.parseInt(pet.getHp() + ""));
            roleSummoning.setMp(pet.getMp());
            roleSummoning.setAp(pet.getAp());
            roleSummoning.setSp(pet.getSp());
            roleSummoning.setSummoningid(pet.getSummoningid());
            roleSummoning.setSkill(pet.getSkill());
            roleSummoning.setPetSkills(pet.getPetSkills());
            roleSummoning.setBeastSkills(pet.getBeastSkills());
            roleSummoning.setSkillData(pet.getSkillData());
            roleSummoning.setFourattributes(pet.getFourattributes());
            roleSummoning.setFriendliness(pet.getFriendliness());
            roleSummoning.setGrade(pet.getGrade());
            roleSummoning.setExp(pet.getExp());
            roleSummoning.setXy(pet.getXy());
            if (roleSummoning.getCzjjd() < pet.getCzjjd()) {
                roleSummoning.setCzjjd(pet.getCzjjd());
            }
            if (roleSummoning.getSpdragon() < pet.getSpdragon()) {
                roleSummoning.setSpdragon(pet.getSpdragon());
            }
            if (roleSummoning.getDragon() < pet.getDragon()) {
                roleSummoning.setDragon(pet.getDragon());
            }
            RedisControl.insertKeyT(RedisParameterUtil.PET, pet.getSid().toString(), roleSummoning);
            RedisControl.insertController(RedisParameterUtil.PET, pet.getSid().toString(), "2");
        }
    }
    
    @Override
    public void updatePetRedis(RoleSummoning pet) {
        RedisControl.insertKeyT(RedisParameterUtil.PET, pet.getSid().toString(), pet);
        RedisControl.insertController(RedisParameterUtil.PET, pet.getSid().toString(), "2");
    }
    
    @Override
    public RoleSummoning selectRoleSummoningsByRgID(BigDecimal rgid) {
        RoleSummoning pet = (RoleSummoning)RedisControl.getV(RedisParameterUtil.PET, rgid.toString(), RoleSummoning.class);
        return pet;
    }
    
    @Override
    public void deleteRoleSummoningBySid(BigDecimal sid) {
        RoleSummoning pet = (RoleSummoning)RedisControl.getV(RedisParameterUtil.PET, sid.toString(), RoleSummoning.class);
        if (pet != null) {
            RedisControl.deletrValue(RedisParameterUtil.PET, pet.getRoleid().toString(), sid.toString());
            RedisControl.delForKey(RedisParameterUtil.PET, sid.toString());
            RedisControl.insertController(RedisParameterUtil.PET, sid.toString(), "3");
            RoleData data = RolePool.getRoleData(pet.getRoleid());
            if (data != null) {
                data.CPet(pet.getSid(), false);
            }
        }
    }
    
    @Override
    public void insertRoleSummoning(RoleSummoning roleSummoning) {
        roleSummoning.setSid(RedisCacheUtil.getPet_pk());
        roleSummoning.setBone(Integer.valueOf(0));
        roleSummoning.setSpir(Integer.valueOf(0));
        roleSummoning.setPower(Integer.valueOf(0));
        roleSummoning.setSpeed(Integer.valueOf(0));
        roleSummoning.setCalm(Integer.valueOf(0));
        roleSummoning.setGrade(Integer.valueOf(0));
        roleSummoning.setExp(new BigDecimal(0));
        roleSummoning.setBasishp(roleSummoning.getHp());
        roleSummoning.setBasismp(roleSummoning.getMp());
        roleSummoning.setFaithful(Integer.valueOf(100));
        roleSummoning.setFriendliness(Long.valueOf(0L));
        roleSummoning.setOpenSeal(Integer.valueOf(1));
        roleSummoning.setDragon(0);
        roleSummoning.setAlchemynum(0);
        roleSummoning.setGrowUpDanNum(0);
        roleSummoning.setDraC(0);
        RedisControl.insertKeyT(RedisParameterUtil.PET, roleSummoning.getSid().toString(), roleSummoning);
        RedisControl.insertListRedis(RedisParameterUtil.PET, roleSummoning.getRoleid().toString(), roleSummoning.getSid().toString());
        RedisControl.insertController(RedisParameterUtil.PET, roleSummoning.getSid().toString(), "1");
        RoleData data = RolePool.getRoleData(roleSummoning.getRoleid());
        if (data != null) {
            data.CPet(roleSummoning.getSid(), true);
        }
    }
    
    @Override
    public void insertitem(Goodstable item) {
        System.out.println("Sid是" + RedisCacheUtil.getGoods_pk());
        System.out.println("item是" + item);
        RedisControl.insertKeyT(RedisParameterUtil.GOODS, item.getRgid().toString(), item);
        RedisControl.insertListRedis(RedisParameterUtil.GOODS, item.getRole_id().toString(), item.getRgid().toString());
        RedisControl.insertController(RedisParameterUtil.GOODS, item.getRgid().toString(), "1");
        RoleData data = RolePool.getRoleData(item.getRole_id());
    }
    
    @Override
    public List<RoleSummoning> selectAllRoleSummonings() {
        return this.roleSummoningMapper.selectAllRoleSummonings();
    }
    
    @Override
    public BigDecimal selectMaxID() {
        return this.roleSummoningMapper.selectMaxID();
    }
    
    @Override
    public void updateRoleSummoningsql(RoleSummoning roleSummoning) {
        this.roleSummoningMapper.updateRoleSummoning(roleSummoning);
    }
    
    @Override
    public void deleteRoleSummoningBySidsql(BigDecimal sid) {
        this.roleSummoningMapper.deleteRoleSummoningBySid(sid);
    }
    
    @Override
    public void insertRoleSummoningsql(RoleSummoning roleSummoning) {
        this.roleSummoningMapper.insertRoleSummoning(roleSummoning);
    }
    
    @Override
    public List<RolesummoningRoleUser> selectRsRU(RolesummoningRoleUser rru) {
        Integer start = Integer.valueOf(((int)rru.getPageNow() - 1) * (int)this.limit + 1);
        Integer end = Integer.valueOf((int)start + (int)this.limit);
        rru.setStart(start);
        rru.setEnd(end);
        List<RolesummoningRoleUser> rsRUList = this.roleSummoningMapper.selectRsRU(rru);
        return rsRUList;
    }
    
    @Override
    public Integer selectRsRUCount(RolesummoningRoleUser rru) {
        Integer rsRUCount = this.roleSummoningMapper.selectRsRUCount(rru);
        return rsRUCount;
    }
    
    @Override
    public RolesummoningRoleUser selectRoleSummoningById(String summoningid) {
        return this.roleSummoningMapper.selectRoleSummoningById(summoningid);
    }
    
    @Override
    public void deleteRoleSummoningBySidList(List<BigDecimal> list) {
        this.roleSummoningMapper.deleteRoleSummoningBySidList(list);
    }
    
    @Override
    public void insertRoleSummoningList(List<RoleSummoning> list) {
        this.roleSummoningMapper.insertRoleSummoningList(list);
    }
    
    @Override
    public void updateRoleSummoningList(List<RoleSummoning> list) {
        this.roleSummoningMapper.updateRoleSummoningList(list);
    }
    
    @Override
    public void insertRoleSummoningSingle(RoleSummoning roleSummoning) {
        this.roleSummoningMapper.insertRoleSummoningSingle(roleSummoning);
    }
}
