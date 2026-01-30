package org.come.action.wqx;

public class KJexamQuestions
{
    private Integer id;
    private String timuType;
    private String topicText;
    private String answer;
    private String taskProtocolHeader;
    private Integer code;
    
    public String getTimuType() {
        return this.timuType;
    }
    
    public void setTimuType(String timuType) {
        this.timuType = timuType;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTopicText() {
        return this.topicText;
    }
    
    public void setTopicText(String topicText) {
        this.topicText = topicText;
    }
    
    public String getAnswer() {
        return this.answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public String getTaskProtocolHeader() {
        return this.taskProtocolHeader;
    }
    
    public void setTaskProtocolHeader(String taskProtocolHeader) {
        this.taskProtocolHeader = taskProtocolHeader;
    }
    
    public Integer getCode() {
        return this.code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
}
