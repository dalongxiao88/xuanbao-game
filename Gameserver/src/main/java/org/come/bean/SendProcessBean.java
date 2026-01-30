package org.come.bean;

public class SendProcessBean
{
    private byte[] cipherText;
    private String sendMes;
    
    public byte[] getCipherText() {
        return this.cipherText;
    }
    
    public void setCipherText(byte[] cipherText) {
        this.cipherText = cipherText;
    }
    
    public String getSendMes() {
        return this.sendMes;
    }
    
    public void setSendMes(String sendMes) {
        this.sendMes = sendMes;
    }
}
