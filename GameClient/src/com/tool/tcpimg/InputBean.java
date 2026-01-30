package com.tool.tcpimg;

import java.awt.Color;
import org.come.bean.Coordinates;
import java.math.BigDecimal;

public class InputBean
{
    private transient Integer index;
    private int type;
    private BigDecimal id;
    private String name;
    private String goodNum;
    private String goodNumType;
    private String color;
    private Coordinates zb;
    private transient Color color2;
    private transient Integer s_x;
    private transient Integer s_y;
    private transient Integer e_x;
    private transient Integer e_y;
    private transient Integer height;
    private transient boolean isM;
    
    public String getGoodNumType() {
        return this.goodNumType;
    }
    
    public void setGoodNumType(String goodNumType) {
        this.goodNumType = goodNumType;
    }
    
    public InputBean() {
    }
    
    public InputBean(int type) {
        this.type = type;
    }
    
    public InputBean(int type, BigDecimal id, String name, String color) {
        this(null, type, id, name, color, null);
    }
    
    public InputBean(int type, String name, String color, Coordinates zb) {
        this(null, type, null, name, color, zb);
    }
    
    public InputBean(int index, int type, BigDecimal id, String name, String color) {
        this(Integer.valueOf(index), type, id, name, color, null);
    }
    
    public InputBean(int type, String name, Coordinates zb) {
        this(null, type, null, name, null, zb);
    }
    
    public InputBean(int type, BigDecimal id, String name, Coordinates zb) {
        this(null, type, id, name, null, zb);
    }
    
    public InputBean(Integer index, int type, BigDecimal id, String name, String color, Coordinates zb) {
        this.index = index;
        this.type = type;
        this.id = id;
        this.name = name;
        this.color = color;
        this.zb = zb;
    }
    
    public boolean isIndex(int offset) {
        return offset > (int)this.index && (int)this.index + this.name.length() > offset;
    }
    
    public boolean isMonitor(int x, int y) {
        if (this.height != null && y >= (int)this.s_y && y <= (int)this.e_y + (int)this.height) {
            if ((int)this.s_y == (int)this.e_y) {
                return x >= (int)this.s_x && x <= (int)this.e_x;
            }
            if (y <= (int)this.s_y + (int)this.height) {
                return x >= (int)this.e_x;
            }
            if (y < (int)this.e_y) {
                return true;
            }
            if (x <= (int)this.e_x) {
                return true;
            }
        }
        return false;
    }
    
    public int getIndex() {
        return (int)this.index;
    }
    
    public void setIndex(int index) {
        this.index = Integer.valueOf(index);
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public Color getColor2() {
        return this.color2;
    }
    
    public void setColor2(Color color2) {
        this.color2 = color2;
    }
    
    public Integer getS_x() {
        return this.s_x;
    }
    
    public void setS_x(Integer s_x) {
        this.s_x = s_x;
    }
    
    public Integer getS_y() {
        return this.s_y;
    }
    
    public void setS_y(Integer s_y) {
        this.s_y = s_y;
    }
    
    public Integer getE_x() {
        return this.e_x;
    }
    
    public void setE_x(Integer e_x) {
        this.e_x = e_x;
    }
    
    public Integer getE_y() {
        return this.e_y;
    }
    
    public void setE_y(Integer e_y) {
        this.e_y = e_y;
    }
    
    public Integer getHeight() {
        return this.height;
    }
    
    public void setHeight(Integer height) {
        this.height = height;
    }
    
    public Coordinates getZb() {
        return this.zb;
    }
    
    public void setZb(Coordinates zb) {
        this.zb = zb;
    }
    
    public void setIndex(Integer index) {
        this.index = index;
    }
    
    public boolean isM() {
        return this.isM;
    }
    
    public void setM(boolean isM) {
        this.isM = isM;
    }
    
    public String getGoodNum() {
        return this.goodNum;
    }
    
    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum;
    }
}
