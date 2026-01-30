package com.tool.btn;

public class LineReplaceEntity
{
    private static final long serialVersionUID = 1L;
    private int lineNo;
    private String replaceStr;
    
    public int getLineNo() {
        return this.lineNo;
    }
    
    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }
    
    public String getReplaceStr() {
        return this.replaceStr;
    }
    
    public void setReplaceStr(String replaceStr) {
        this.replaceStr = replaceStr;
    }
    
    public LineReplaceEntity(int lineNo, String replaceStr) {
        this.lineNo = lineNo;
        this.replaceStr = replaceStr;
    }
}
