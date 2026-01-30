package org.come.bean;

import come.tool.Calculation.BaseQl;

public class UseCardBean
{
    private String lCard;
    private String name;
    private String type;
    private String skin;
    private String value;
    private String Zz;
    private long time;
    private transient BaseQl[] qls;
    
    public UseCardBean() {
    }
    
    public UseCardBean(String name, String type, String skin, long time, String value) {
        this.name = name;
        this.type = type;
        this.skin = skin;
        this.time = time;
        this.value = value;
    }
    
    public UseCardBean(String name, String type, String skin, long time, String value, String Zz) {
        this.name = name;
        this.type = type;
        this.skin = skin;
        this.time = time;
        this.value = value;
        this.Zz = Zz;
    }
    
    public String getlCard() {
        return this.lCard;
    }
    
    public void setlCard(String lCard) {
        this.lCard = lCard;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getSkin() {
        return this.skin;
    }
    
    public void setSkin(String skin) {
        this.skin = skin;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public String getValue() {
        if (this.value == null) {
            return "";
        }
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
        this.qls = null;
    }
    
    public String getZz() {
        return this.Zz;
    }
    
    public void setZz(String zz) {
        this.Zz = zz;
    }
    
    public void upValue(String msg, int wei) {
        int path = msg.indexOf("=");
        String key = msg.substring(0, path);
        double value = Double.parseDouble(msg.substring(path + 1));
        if (this.value == null || this.value.equals("")) {
            this.setValue(key + "=" + ((value != (double)(int)value) ? value : ((double)(int)value)));
            return;
        }
        BaseQl baseQl = (this.qls == null || this.qls.length >= wei) ? null : this.qls[wei];
        StringBuffer buffer = new StringBuffer();
        if (baseQl != null) {
            baseQl.setKey(key);
            baseQl.setValue(value);
            for (int i = 0; i < this.qls.length; ++i) {
                BaseQl base = this.qls[i];
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(base.getKey());
                buffer.append("=");
                buffer.append((base.getValue() != (double)(int)base.getValue()) ? base.getValue() : ((double)(int)base.getValue()));
            }
            this.value = buffer.toString();
            return;
        }
        else {
            String[] vs = this.value.split("\\|");
            String mm = key + "=" + ((value != (double)(int)value) ? value : ((double)(int)value));
            for (int j = 0; j < vs.length; ++j) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                if (j != wei) {
                    buffer.append(vs[j]);
                }
                else {
                    buffer.append(mm);
                    mm = null;
                }
            }
            if (mm != null) {
                if (buffer.length() != 0) {
                    buffer.append("|");
                }
                buffer.append(mm);
            }
            this.setValue(buffer.toString());
            return;
        }
    }
    
    public Double getQlKey(String key) {
        this.getQls();
        if (this.qls == null) {
            return null;
        }
        for (int i = 0; i < this.qls.length; ++i) {
            if (this.qls[i] != null && this.qls[i].getKey().equals(key)) {
                return Double.valueOf(this.qls[i].getValue());
            }
        }
        return null;
    }
    
    public BaseQl[] getQls() {
        if (this.qls == null && this.value != null && !this.value.equals("")) {
            String[] vs = this.value.split("\\|");
            this.qls = new BaseQl[vs.length];
            for (int i = 0; i < vs.length; ++i) {
                String[] vss = vs[i].split("=");
                if (vss.length == 2) {
                    try {
                        if (i == 2 && vss[0].equals("种族")) {
                            this.qls[i] = new BaseQl(vss[0], vss[1]);
                        }
                        else {
                            this.qls[i] = new BaseQl(vss[0], Double.parseDouble(vss[1]));
                        }
                    }
                    catch (Exception ex) {}
                }
            }
        }
        return this.qls;
    }
}
