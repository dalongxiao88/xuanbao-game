package org.come.task;

import come.tool.Good.DropModel;
import org.come.server.GameServer;

public class MonsterExp
{
    private int maxSize;
    private long exp;
    private int size;
    
    public MonsterExp(int maxSize, long exp) {
        this.maxSize = maxSize;
        this.exp = exp;
    }
    
    public String addEXP(MapMonsterBean bean, String name) {
        if (this.size < this.maxSize) {
            ++this.size;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("#G");
        buffer.append(bean.getRobotname());
        buffer.append("#Y在#c00FFFF");
        buffer.append(GameServer.getMapName(bean.getMap() + ""));
        buffer.append("(");
        buffer.append((int)bean.getX() / 20);
        buffer.append(",");
        buffer.append(bean.getY() / 20L);
        buffer.append(")击杀#R");
        buffer.append(name);
        buffer.append("#Y为首的队伍。积累");
        buffer.append(this.exp * (long)this.size / 10000L);
        buffer.append("万经验,等待其他队伍来战");
        return buffer.toString();
    }
    
    public DropModel getDropModel(int sum) {
        if (this.size == 0) {
            return null;
        }
        return new DropModel(new String[] { "经验=" + this.exp * (long)this.size / (long)sum });
    }
    
    public int getMaxSize() {
        return this.maxSize;
    }
    
    public long getExp() {
        return this.exp;
    }
}
