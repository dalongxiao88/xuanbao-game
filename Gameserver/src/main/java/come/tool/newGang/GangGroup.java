package come.tool.newGang;

import java.util.ArrayList;
import org.come.until.GsonUtil;
import org.come.protocol.Agreement;
import org.come.bean.NPCDialog;
import java.util.List;
import org.come.entity.Gang;

public class GangGroup
{
    private transient Gang gang;
    private int xy;
    private transient int xyNum;
    private int kj;
    static List<String> f1;
    static List<String> f2;
    
    public GangGroup(Gang gang) {
        this.gang = gang;
        String type = gang.getGangTxt();
        if (type == null || type.equals("")) {
            return;
        }
        String[] v = type.split("\\|");
        long time = System.currentTimeMillis() - Long.parseLong(v[0]);
        time /= 3600000L;
        this.kj = Integer.parseInt(v[1]);
        String[] xys = v[2].split("=");
        this.xy = Integer.parseInt(xys[0]);
        this.xyNum = Integer.parseInt(xys[1]);
        this.addXY((int)time);
    }
    
    public boolean addXY(int size) {
        if (this.xy == 0) {
            return false;
        }
        int max = (3 + this.xy * 2) * 999999;
        if (this.xyNum > max) {
            return false;
        }
        this.xyNum += (3 + this.xy * 5000) * size;
        if (this.xyNum > max) {
            this.xyNum = max;
        }
        return true;
    }
    
    public String getTxt() {
        if (this.xy == 0 && this.kj == 0) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(System.currentTimeMillis());
        buffer.append("|");
        buffer.append(this.kj);
        buffer.append("|");
        buffer.append(this.xy);
        buffer.append("=");
        buffer.append(this.xyNum);
        return buffer.toString();
    }
    
    public String getXYNpc() {
        NPCDialog npcDialog = new NPCDialog();
        StringBuffer buffer = new StringBuffer();
        buffer.append("当前驯养师等级");
        buffer.append(this.xy);
        if (this.xy == 0) {
            buffer.append("。无法提供驯养服务");
        }
        else {
            buffer.append("。驯养次数剩余");
            buffer.append(this.xyNum);
            buffer.append("(上限:");
            buffer.append((3 + this.xy * 2) * 999999);
            buffer.append(")。每小时恢复");
            buffer.append(3 + this.xy * 5000);
            buffer.append("次。驯养一次消耗150帮贡,每日最多驯养10次");
        }
        npcDialog.setMsg(buffer.toString());
        npcDialog.setFunctions(GangGroup.f1);
        return Agreement.getAgreement().npcAgreement(GsonUtil.getGsonUtil().getgson().toJson(npcDialog));
    }
    
    public String getKJNpc() {
        NPCDialog npcDialog = new NPCDialog();
        StringBuffer buffer = new StringBuffer();
        buffer.append("当前科技等级");
        buffer.append(this.kj);
        buffer.append("。领取消耗2000帮贡,每日最多领取二次。");
        npcDialog.setMsg(buffer.toString());
        npcDialog.setFunctions(GangGroup.f2);
        return Agreement.getAgreement().npcAgreement(GsonUtil.getGsonUtil().getgson().toJson(npcDialog));
    }
    
    public int getXy() {
        return this.xy;
    }
    
    public void setXy(int xy) {
        this.xy = xy;
    }
    
    public int getKj() {
        return this.kj;
    }
    
    public void setKj(int kj) {
        this.kj = kj;
    }
    
    public int getXyNum() {
        return this.xyNum;
    }
    
    public void setXyNum(int xyNum) {
        this.xyNum = xyNum;
    }
    
    static {
        (GangGroup.f1 = new ArrayList<>()).add("升级驯养师等级");
        GangGroup.f1.add("驯养参战召唤兽亲密");
        GangGroup.f1.add("驯养坐骑经验");
        GangGroup.f1.add("驯养坐骑技能熟练度");
        (GangGroup.f2 = new ArrayList<>()).add("升级科技等级");
        GangGroup.f2.add("领取经验加成");
        GangGroup.f2.add("领取强法加成");
        GangGroup.f2.add("领取抗性加成");
    }
}
