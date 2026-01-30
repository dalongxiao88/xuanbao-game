package org.come.bean;

public class RequestReturnBean
{
    private String requresHeader;
    private String style;
    private String content;
    private String returnDate;
    
    public RequestReturnBean() {
        this.requresHeader = "1003";
        this.style = "error";
    }
    
    public String getRequresHeader() {
        return this.requresHeader;
    }
    
    public void setRequresHeader(String requresHeader) {
        this.requresHeader = requresHeader;
    }
    
    public String getStyle() {
        return this.style;
    }
    
    public void setStyle(String style) {
        this.style = style;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getReturnDate() {
        return this.returnDate;
    }
    
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
