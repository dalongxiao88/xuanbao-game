package com.tool.role;

public class QualityZW
{
    public static void insertValues(Ql quality,String key,double value){
        switch (key) {
            case "加强全系法术":quality.addQ(value);break;
            case "加强昏睡":
                quality.setRoleqhs(quality.getRoleqhs()+value);break;
            case "忽视抗睡":
            case "忽视抗昏睡":
                quality.setRolehshs(quality.getRolehshs()+value);break;
            case "加强中毒":
                quality.setRoleqzd(quality.getRoleqzd()+value);break;
            case "忽视抗毒":
            case "忽视抗中毒":
                quality.setRolehszd(quality.getRolehszd()+value);break;
            case "无属性伤害":
            case "加强无属性伤害":
            case "对无属性目标伤害":
                quality.setRolewsxsh(quality.getRolewsxsh()+value);break;
            case "加强封印":
                quality.setRoleqfy(quality.getRoleqfy()+value);break;
            case "忽视抗封":
            case "忽视抗封印":
                quality.setRolehsfy(quality.getRolehsfy()+value);break;
            case "致命":
            case "致命率":
            case "致命几率":
                quality.setRolefzml(quality.getRolefzml()+value);break;
            case "命中":
            case "命中率":
            case "命中几率":
                quality.setRolefmzl(quality.getRolefmzl()+value);break;
            case "狂暴":
            case "狂暴率":
            case "狂暴几率":
                quality.setRolefkbl(quality.getRolefkbl()+value);break;
            case "连击":
            case "连击率":
            case "连击几率":
                quality.setRolefljl(quality.getRolefljl()+value);break;
            case "反击":
            case "反击率":
            case "反击几率":
                quality.setRoleffjl(quality.getRoleffjl()+value);break;
            case "忽视防御程度":
                quality.setRolehsfyv(quality.getRolehsfyv()+value);break;
            case "忽视防御几率":
                quality.setRolehsfyl(quality.getRolehsfyl()+value);break;
            case "加强混乱":
                quality.setRoleqhl(quality.getRoleqhl()+value);break;
            case "忽视抗混乱":
            case "忽视抗混":
                quality.setRolehshl(quality.getRolehshl()+value);break;
            case "忽视抗震慑":
                quality.setRolehszs(quality.getRolehszs()+value);break;
            case "鬼火伤害":
                quality.setRoleghsh(quality.getRoleghsh()+value);break;
            case "抗水":
            case "抗水法":
                quality.setRoleksf(quality.getRoleksf()+value);break;
            case "抗火":
            case "抗火法":
                quality.setRolekhf(quality.getRolekhf()+value);break;
            case "抗雷":
            case "抗雷法":
                quality.setRoleklf(quality.getRoleklf()+value);break;
            case "抗风":
            case "抗风法":
                quality.setRolekff(quality.getRolekff()+value);break;
            case "抗昏睡":
                quality.setRolekhs(quality.getRolekhs()+value);break;
            case "抗混乱":
                quality.setRolekhl(quality.getRolekhl()+value);break;
            case "抗封印":
                quality.setRolekfy(quality.getRolekfy()+value);break;
            case "抗遗忘":
                quality.setRolekyw(quality.getRolekyw()+value);break;
            case "抗鬼火":
                quality.setRolekgh(quality.getRolekgh()+value);break;
            case "抗灵宝伤害":
            case "抵抗灵宝伤害":
                quality.setRoleklb(quality.getRoleklb() + value);
                break;
            case "抗三尸":
            case "抗三尸虫":
                quality.setRoleksc(quality.getRoleksc()+value);break;
            case "抗中毒":
                quality.setRolekzd(quality.getRolekzd()+value);break;
            case "抗震慑":
                quality.setRolekzs(quality.getRolekzs()+value);break;
            case "忽视抗雷":
                quality.setRolehslf(quality.getRolehslf()+value);break;
            case "忽视抗水":
                quality.setRolehssf(quality.getRolehssf()+value);break;
            case "忽视抗火":
                quality.setRolehshf(quality.getRolehshf()+value);break;
            case "忽视抗风":
                quality.setRolehsff(quality.getRolehsff()+value);break;
            case "雷法伤害":
                quality.setRolelfsh(quality.getRolelfsh()+value);break;
            case "水法伤害":
                quality.setRolesfsh(quality.getRolesfsh()+value);break;
            case "风法伤害":
                quality.setRoleffsh(quality.getRoleffsh()+value);break;
            case "火法伤害":
                quality.setRolehfsh(quality.getRolehfsh()+value);break;
            case "加强鬼火":
                quality.setRolegstronghostfire(quality.getRolegstronghostfire()+value);break;
            case "加强遗忘":
                quality.setRolestrongforget(quality.getRolestrongforget()+value);break;
            case "加三尸":
            case "加强三尸虫":
            case "强三尸虫":
                quality.setRolesssh(quality.getRolesssh()+value);break;
            case "加强三尸虫回血程度":
                quality.setRolestrongbodyblooddeep(quality.getRolestrongbodyblooddeep()+value);break;
            case "雷法狂暴":
            case "雷系狂暴几率":
                quality.setRolelfkb(quality.getRolelfkb()+value);break;
            case "火法狂暴":
            case "火系狂暴几率":
                quality.setRolehfkb(quality.getRolehfkb()+value);break;
            case "水法狂暴":
            case "水系狂暴几率":
                quality.setRolesfkb(quality.getRolesfkb()+value);break;
            case "风法狂暴":
            case "风系狂暴几率":
                quality.setRoleffkb(quality.getRoleffkb()+value);break;

            case "雷法狂暴程度":quality.setBlfcd(quality.getBlfcd()+value);break;
            case "风法狂暴程度":quality.setBffcd(quality.getBffcd()+value);break;
            case "水法狂暴程度":quality.setBsfcd(quality.getBsfcd()+value);break;
            case "火法狂暴程度":quality.setBhfcd(quality.getBhfcd()+value);break;
            case "仙法狂暴程度":
                quality.setBlfcd(quality.getBlfcd()+value);
                quality.setBffcd(quality.getBffcd()+value);
                quality.setBsfcd(quality.getBsfcd()+value);
                quality.setBhfcd(quality.getBhfcd()+value);
                break;
            case "鬼火狂暴程度":quality.setBghcd(quality.getBghcd()+value);break;
            case "三尸虫狂暴程度":quality.setBsccd(quality.getBsccd()+value);break;

            	case "法术暴击":
            	case "法术暴击率":
            	case "法术暴击几率":
            		quality.setFsbj(quality.getFsbj() + value);
            		break;
            case "雷法暴击":
            case "雷系暴击率":
            case "雷系暴击几率":
            case "雷法暴击几率":
                quality.setBjlf(quality.getBjlf()+value);break;
            case "火法暴击":
            case "火系暴击率":
            case "火系暴击几率":
            case "火法暴击几率":
                quality.setBjhf(quality.getBjhf()+value);break;
            case "水法暴击":
            case "水系暴击率":
            case "水系暴击几率":
            case "水法暴击几率":
                quality.setBjsf(quality.getBjsf()+value);break;
            case "风法暴击":
            case "风系暴击率":
            case "风系暴击几率":
            case "风法暴击几率":
                quality.setBjff(quality.getBjff()+value);break;
            case "仙法暴击":
            case "仙法暴击率":
            case "仙法暴击几率":
                quality.setBjlf(quality.getBjlf()+value);
                quality.setBjhf(quality.getBjhf()+value);
                quality.setBjsf(quality.getBjsf()+value);
                quality.setBjff(quality.getBjff()+value);
                break;
            case "鬼火暴击":
            case "鬼火暴击率":
            case "鬼火暴击几率":
                quality.setBjgh(quality.getBjgh()+value);break;
            case "三尸虫暴击":
            case "三尸虫暴击率":
            case "三尸虫暴击几率":
                quality.setBjsc(quality.getBjsc()+value);break;
            case "法术暴击增伤":quality.setFsbjcd(quality.getFsbjcd()+value);break;
            case "雷法暴击增伤":quality.setBjlfcd(quality.getBjlfcd()+value);break;
            case "风法暴击增伤":quality.setBjffcd(quality.getBjffcd()+value);break;
            case "水法暴击增伤":quality.setBjsfcd(quality.getBjsfcd()+value);break;
            case "火法暴击增伤":quality.setBjhfcd(quality.getBjhfcd()+value);break;
            case "仙法暴击增伤":
                quality.setBjlfcd(quality.getBjlfcd()+value);
                quality.setBjffcd(quality.getBjffcd()+value);
                quality.setBjsfcd(quality.getBjsfcd()+value);
                quality.setBjhfcd(quality.getBjhfcd()+value);
                break;
            case "鬼火暴击增伤": quality.setBjghcd(quality.getBjghcd()+value);break;
            case "三尸虫暴击增伤": quality.setBjsccd(quality.getBjsccd()+value);break;

            case "抗物理":
            case "物理吸收率":
                quality.setRolekwl(quality.getRolekwl()+value);break;
            case "金":
                quality.setRolewxj( quality.getRolewxj()+value);break;
            case "木":
                quality.setRolewxm(quality.getRolewxm()+value);break;
            case "水":
                quality.setRolewxs(quality.getRolewxs()+value);break;
            case "火":
                quality.setRolewxh( quality.getRolewxh()+value);break;
            case "土":
                quality.setRolewxt(quality.getRolewxt()+value);break;
            case "躲闪":
            case "躲闪率":
            case "物理躲闪":
                quality.setRolefdsl(quality.getRolefdsl()+value);break;
            case "连击次数":
                quality.setRolefljv(quality.getRolefljv()+value);break;
            case "反击次数":
                quality.setRoleffjv(quality.getRoleffjv()+value);break;
            case "加强风":
            case "加强风法":
                quality.setRoleqff(quality.getRoleqff()+value);break;
            case "加强雷":
            case "加强雷法":
                quality.setRoleqlf(quality.getRoleqlf()+value);break;
            case "加强火":
            case "加强火法":
                quality.setRoleqhf(quality.getRoleqhf()+value);break;
            case "加强水":
            case "加强水法":
                quality.setRoleqsf(quality.getRoleqsf()+value);break;
            case "强震慑":
            case "加强震慑":
                quality.setRoleqzs(quality.getRoleqzs()+value);break;
            case "强力克金":
                quality.setRolewxqkj(quality.getRolewxqkj()+value);break;
            case "强力克水":
                quality.setRolewxqks(quality.getRolewxqks()+value);break;
            case "强力克火":
                quality.setRolewxqkh(quality.getRolewxqkh()+value);break;
            case "强力克木":
                quality.setRolewxqkm(quality.getRolewxqkm()+value);break;
            case "强力克土":
                quality.setRolewxqkt(quality.getRolewxqkt()+value);break;
            case "反震程度":
                quality.setRoleffzcd(quality.getRoleffzcd()+value);break;
            case "反震率":
                quality.setRoleffzl(quality.getRoleffzl()+value);break;
            case "鬼火狂暴":
            case "鬼火狂暴几率":
                quality.setRoleghkb(quality.getRoleghkb()+value);break;
            case "三尸虫狂暴":
            case "三尸虫狂暴几率":
                quality.setRolesskb(quality.getRolesskb()+value);break;
            case "忽视躲闪":
                quality.setRolehsds(quality.getRolehsds()+value);break;
            case "忽视反击":
                quality.setRolehsfj(quality.getRolehsfj()+value);break;
            case "仙法连击率":
                quality.setRolexfljl(quality.getRolexfljl()+value);break;
            case "仙法连击次数":
                quality.setRolexfljs(quality.getRolexfljs()+value);break;
            case "忽视仙法抗性率":
                quality.setRolehsxfkl(quality.getRolehsxfkl()+value);break;
            case "忽视仙法抗性程度":
                quality.setRolehsxfcd(quality.getRolehsxfcd()+value);break;
            case "忽视遗忘":
            case "忽视抗遗忘":
                quality.setRolehsyw(quality.getRolehsyw()+value);break;
            case "忽视鬼火":
            case "忽视抗鬼火":
                quality.setRolehsgh(quality.getRolehsgh()+value);break;
            case "加强攻击法术效果":
            case "加强加攻法术效果":
                quality.setJqgjfs(quality.getJqgjfs()+value);break;
            case "加强防御法术效果":
            case "加强加防法术效果":
                quality.setJqfyfs(quality.getJqfyfs()+value);break;
            case "加强速度法术效果":
            case "加强加速法术效果":
                quality.setJqsdfs(quality.getJqsdfs()+value);break;
            case "增加强克效果":
                quality.setQ_qk(quality.getQ_qk()+value);break;
            case "抵御强克效果":
                quality.setK_qk(quality.getK_qk()+value);break;
            case "抗无属性伤害":
                quality.setK_wsxsh(quality.getK_wsxsh()+value);break;
            case "抗震慑气血":
                quality.setK_zshp(quality.getK_zshp()+value);break;
            case "抗震慑魔法":
                quality.setK_zsmp(quality.getK_zsmp()+value);break;
            case "对召唤兽伤害":
                quality.setQ_zhssh(quality.getQ_zhssh()+value);break;
            case "抗金箍":
                quality.setK_jge(quality.getK_jge()+value);break;
            case "抗情网":
                quality.setK_qw(quality.getK_qw()+value);break;
            case "抗浩然正气":
            case "上善若水":
                quality.setK_ndhr(quality.getK_ndhr()+value);break;
            case "抗青面獠牙":
            case "美人迟暮":
                quality.setK_ndqm(quality.getK_ndqm()+value);break;
            case "抗天魔解体":
            case "化血成碧":
                quality.setK_ndtm(quality.getK_ndtm()+value);break;
            case "抗小楼夜哭":
            case "明珠有泪":
                quality.setK_ndxl(quality.getK_ndxl()+value);break;
            case "抗分光化影":
            case "灵犀一点":
                quality.setK_ndfg(quality.getK_ndfg()+value);break;
            case "抗隔山打牛":
            case "尘埃落定":
                quality.setK_ndgs(quality.getK_ndgs()+value);break;
            case "抗致命率":
                quality.setKzml(quality.getKzml()+value);break;
            case "抗反震":
                quality.setKfz(quality.getKfz()+value);break;
            case "附加水法攻击":
                quality.setF_xs(quality.getF_xs()+value);break;
            case "附加雷法攻击":
                quality.setF_xl(quality.getF_xl()+value);break;
            case "附加风法攻击":
                quality.setF_xf(quality.getF_xf()+value);break;
            case "附加火法攻击":
                quality.setF_xh(quality.getF_xh()+value);break;
            case "附加混乱攻击":
                quality.setF_h(quality.getF_h()+value);break;
            case "附加封印攻击":
                quality.setF_f(quality.getF_f()+value);break;
            case "附加毒法攻击":
                quality.setF_d(quality.getF_d()+value);break;
            case "附加震慑攻击":
                quality.setF_zs(quality.getF_zs()+value);break;
            case "附加三尸攻击":
                quality.setF_sc(quality.getF_sc()+value);break;

            case "仙法狂暴":
                quality.setRolelfkb(quality.getRolelfkb()+value);
                quality.setRolehfkb(quality.getRolehfkb()+value);
                quality.setRolesfkb(quality.getRolesfkb()+value);
                quality.setRoleffkb(quality.getRoleffkb()+value);break;
            case "忽视仙法":
                quality.setRolehslf(quality.getRolehslf()+value);
                quality.setRolehssf(quality.getRolehssf()+value);
                quality.setRolehshf(quality.getRolehshf()+value);
                quality.setRolehsff(quality.getRolehsff()+value);break;
            case "忽视人法":
                quality.setRolehshs(quality.getRolehshs()+value);
                quality.setRolehsfy(quality.getRolehsfy()+value);
                quality.setRolehshl(quality.getRolehshl()+value);
                quality.setRolehszd(quality.getRolehszd()+value);break;
            case "强毒伤":
            case "加强毒伤害":
                quality.setQzds(quality.getQzds()+value);break;
            case "抗毒伤":
                quality.setKzds(quality.getKzds()+value);break;
            case "抗风法狂暴":
                quality.setKbf(quality.getKbf()+value);break;
            case "抗火法狂暴":
                quality.setKbh(quality.getKbh()+value);break;
            case "抗水法狂暴":
                quality.setKbs(quality.getKbs()+value);break;
            case "抗雷法狂暴":
                quality.setKbl(quality.getKbl()+value);break;
            case "抗鬼火狂暴":
                quality.setKbg(quality.getKbg()+value);break;
            case "加强魅惑":
                quality.setQmh(quality.getQmh()+value);break;
            case "强金箍":
                quality.setQjg(quality.getQjg()+value);break;
            case "强情网":
                quality.setQqw(quality.getQqw()+value);break;
            case "封印狂暴":
                quality.setBfy(quality.getBfy()+value);break;
            case "混乱狂暴":
                quality.setBhl(quality.getBhl()+value);break;
            case "昏睡狂暴":
                quality.setBhs(quality.getBhs()+value);break;
            case "毒法狂暴":
                quality.setBzd(quality.getBzd()+value);break;
            case "加防狂暴":
                quality.setBjf(quality.getBjf()+value);break;
            case "加攻狂暴":
                quality.setBjg(quality.getBjg()+value);break;
            case "震慑狂暴":
                quality.setBzs(quality.getBzs()+value);break;
            case "遗忘狂暴":
                quality.setByw(quality.getByw()+value);break;
            case "魅惑狂暴":
                quality.setBmh(quality.getBmh()+value);break;

            case "法术躲闪":
            case "法术躲闪率":quality.setEfsds(quality.getEfsds()+value);break;
            case "伤害减免":quality.setEjs(quality.getEjs()+value);break;

            case "加强霹雳效果":quality.setQlpl(quality.getQlpl()+value);break;
            case "加强扶摇效果":quality.setQlfy(quality.getQlfy()+value);break;
            case "加强沧波效果":quality.setQlcb(quality.getQlcb()+value);break;
            case "加强甘霖回血值":quality.setQlglv(quality.getQlglv()+value);break;
            case "加强甘霖回血程度":quality.setQlglc(quality.getQlglc()+value);break;

            case "法术命中":
                	case "法术命中率":quality.setFsmz(quality.getFsmz()+value);break;
            case "震慑命中":
            case "震慑法术命中":
            case "震慑命中率":quality.setMzs(quality.getMzs()+value);break;
            case "火法命中":
            case "火法法术命中":
            case "火法命中率":quality.setMhf(quality.getMhf()+value);break;
            case "雷法命中":
            case "雷法法术命中":
            case "雷法命中率":quality.setMlf(quality.getMlf()+value);break;
            case "风法命中":
            case "风法法术命中":
            case "风法命中率":quality.setMff(quality.getMff()+value);break;
            case "水法命中":
            case "水法法术命中":
            case "水法命中率":quality.setMsf(quality.getMsf()+value);break;
            case "毒法命中":
            case "毒法法术命中":
            case "毒法命中率":quality.setMdf(quality.getMdf()+value);break;
            case "封印命中":
            case "封印法术命中":
            case "封印命中率":quality.setMfy(quality.getMfy()+value);break;
            case "混乱命中":
            case "混乱法术命中":
            case "混乱命中率":quality.setMhl(quality.getMhl()+value);break;
            case "昏睡命中":
            case "昏睡法术命中":
            case "昏睡命中率":quality.setMhs(quality.getMhs()+value);break;
            case "遗忘命中":
            case "遗忘法术命中":
            case "遗忘命中率":quality.setMyw(quality.getMyw()+value);break;
            case "鬼火命中":
            case "鬼火法术命中":
            case "鬼火命中率":quality.setMgh(quality.getMgh()+value);break;
            case "三尸虫命中":
            case "三尸虫法术命中":
            case "三尸虫命中率":quality.setMsc(quality.getMsc()+value);break;

            case "震慑躲闪":
            case "震慑法术躲闪":quality.setDzs(quality.getDzs()+value);break;
            case "火法法术躲闪":
            case "火法躲闪":quality.setDhf(quality.getDhf()+value);break;
            case "雷法法术躲闪":
            case "雷法躲闪":quality.setDlf(quality.getDlf()+value);break;
            case "风法法术躲闪":
            case "风法躲闪":quality.setDff(quality.getDff()+value);break;
            case "水法法术躲闪":
            case "水法躲闪":quality.setDsf(quality.getDsf()+value);break;
            case "毒法法术躲闪":
            case "毒法躲闪":quality.setDdf(quality.getDdf()+value);break;
            case "封印法术躲闪":
            case "封印躲闪":quality.setDfy(quality.getDfy()+value);break;
            case "混乱法术躲闪":
            case "混乱躲闪":quality.setDhl(quality.getDhl()+value);break;
            case "昏睡法术躲闪":
            case "昏睡躲闪":quality.setDhs(quality.getDhs()+value);break;
            case "遗忘法术躲闪":
            case "遗忘躲闪":quality.setDyw(quality.getDyw()+value);break;
            case "鬼火法术躲闪":
            case "鬼火躲闪":quality.setDgh(quality.getDgh()+value);break;
            case "三尸虫法术躲闪":
            case "三尸虫躲闪":quality.setDsc(quality.getDsc()+value);break;


            case "水法伤害减免":quality.setJsf(quality.getJsf()+value);break;
            case "风法伤害减免":quality.setJff(quality.getJff()+value);break;
            case "雷法伤害减免":quality.setJlf(quality.getJlf()+value);break;
            case "火法伤害减免":quality.setJhf(quality.getJhf()+value);break;
            case "鬼火伤害减免":quality.setJgh(quality.getJgh()+value);break;

            case "火法反击":quality.setHffj(quality.getHffj()+value);break;
            case "雷法反击":quality.setLffj(quality.getLffj()+value);break;
            case "水法反击":quality.setSffj(quality.getSffj()+value);break;
            case "风法反击":quality.setFffj(quality.getFffj()+value);break;

            case "被攻击时释放魔神附身":quality.setSfmsfs(quality.getSfmsfs()+value);break;
            case "被攻击时释放含情脉脉":quality.setSfhqmm(quality.getSfhqmm()+value);break;
            case "被攻击时释放乾坤借速":quality.setSfqkjs(quality.getSfqkjs()+value);break;
        }
    }
}
