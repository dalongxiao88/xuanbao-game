package come.tool.Scene.LTS;

import org.come.handler.SendMessage;
import org.come.protocol.Agreement;
import org.come.server.GameServer;
import io.netty.channel.ChannelHandlerContext;
import java.math.BigDecimal;

public class LTSRole
{
    private BigDecimal Id;
    private String role;
    private int jf;
    private int HSnum;
    private int LSnum;
    private int ZBnum;
    private int BZnum;
    private long time;
    private boolean isAward;
    
    public LTSRole(BigDecimal id, String role) {
        this.Id = id;
        this.role = role;
        this.isAward = false;
    }
    
    public void battle(boolean is, boolean is2, int type, int ew) {
        if (is) {
            this.time = System.currentTimeMillis() + 10000L;
            int add = (type == 3) ? 4 : ((type == 2) ? 3 : 2);
            if (this.LSnum >= 10) {
                add += 2;
            }
            else if (this.LSnum >= 3) {
                ++add;
            }
            add += ew;
            ChannelHandlerContext ctx = (ChannelHandlerContext)GameServer.getRoleNameMap().get(this.role);
            if (ctx != null) {
                SendMessage.sendMessageToSlef(ctx, Agreement.getAgreement().PromptAgreement("你获得" + add + "点积分"));
            }
            this.jf += add;
            LTSUtil.getLtsUtil().addJF(this.Id, add);
            ++this.HSnum;
            ++this.LSnum;
        }
        else {
            this.time = System.currentTimeMillis() + 180000L;
            this.LSnum = 0;
            this.ZBnum += (is2 ? 2 : 1);
            ++this.jf;
            LTSUtil.getLtsUtil().addJF(this.Id, 1);
        }
    }
    
    public BigDecimal getId() {
        return this.Id;
    }
    
    public void setId(BigDecimal id) {
        this.Id = id;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public int getJf() {
        return this.jf;
    }
    
    public void setJf(int jf) {
        this.jf = jf;
    }
    
    public int getZBnum() {
        return this.ZBnum;
    }
    
    public void setZBnum(int zBnum) {
        this.ZBnum = zBnum;
    }
    
    public int getHSnum() {
        return this.HSnum;
    }
    
    public void setHSnum(int hSnum) {
        this.HSnum = hSnum;
    }
    
    public int getLSnum() {
        return this.LSnum;
    }
    
    public void setLSnum(int lSnum) {
        this.LSnum = lSnum;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public int getBZnum() {
        return this.BZnum;
    }
    
    public void setBZnum(int bZnum) {
        this.BZnum = bZnum;
    }
    
    public boolean isAward() {
        return this.isAward;
    }
    
    public void setAward(boolean isAward) {
        this.isAward = isAward;
    }
}
