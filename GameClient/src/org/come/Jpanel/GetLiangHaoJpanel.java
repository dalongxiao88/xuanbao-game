package org.come.Jpanel;

import java.awt.Graphics;
import com.tool.btn.FormsOnOffBtn;
import com.tool.tcpimg.UIUtils;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import com.tool.btn.GetLiangHaoBtn;
import javax.swing.JPanel;

public class GetLiangHaoJpanel extends JPanel
{
    private GetLiangHaoTabJpanel getliangHaoTabJpanel;
    private GetLiangHaoBtn btnChoose;
    private GetLiangHaoBtn btnRob;
    private GetLiangHaoBtn btnLetter;
    private ImageIcon iconBack;
    
    public GetLiangHaoJpanel() {
        this.setPreferredSize(new Dimension(697, 538));
        this.getliangHaoTabJpanel = new GetLiangHaoTabJpanel();
        this.setLayout(null);
        this.setBackground(UIUtils.Color_BACK);
        FormsOnOffBtn offBtn = new FormsOnOffBtn("inkImg/button/1.png", 1, 641);
        offBtn.setBounds(662, 10, 25, 25);
        this.add(offBtn);
        (this.btnChoose = new GetLiangHaoBtn("inkImg/button1/xuan.png", 1, 2, this.getliangHaoTabJpanel, this)).setBounds(55, 25, 100, 35);
        this.add(this.btnChoose);
        (this.btnRob = new GetLiangHaoBtn("inkImg/button1/qiangxiao.png", 1, 3, this.getliangHaoTabJpanel, this)).setBounds(158, 25, 100, 35);
        this.add(this.btnRob);
        (this.btnLetter = new GetLiangHaoBtn("inkImg/button1/xinjianxiao.png", 1, 4, this.getliangHaoTabJpanel, this)).setBounds(261, 25, 100, 35);
        this.add(this.btnLetter);
        this.getliangHaoTabJpanel.setBounds(0, 0, 697, 538);
        this.add(this.getliangHaoTabJpanel);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.iconBack == null) {
            this.iconBack = new ImageIcon("inkImg/background1/GetLiangHao.png");
        }
        g.drawImage(this.iconBack.getImage(), 0, 0, 697, 538, this);
    }
    
    public GetLiangHaoTabJpanel getGetliangHaoTabJpanel() {
        return this.getliangHaoTabJpanel;
    }
    
    public void setGetliangHaoTabJpanel(GetLiangHaoTabJpanel getliangHaoTabJpanel) {
        this.getliangHaoTabJpanel = getliangHaoTabJpanel;
    }
    
    public GetLiangHaoBtn getBtnChoose() {
        return this.btnChoose;
    }
    
    public void setBtnChoose(GetLiangHaoBtn btnChoose) {
        this.btnChoose = btnChoose;
    }
    
    public GetLiangHaoBtn getBtnRob() {
        return this.btnRob;
    }
    
    public void setBtnRob(GetLiangHaoBtn btnRob) {
        this.btnRob = btnRob;
    }
    
    public GetLiangHaoBtn getBtnLetter() {
        return this.btnLetter;
    }
    
    public void setBtnLetter(GetLiangHaoBtn btnLetter) {
        this.btnLetter = btnLetter;
    }
    
    public ImageIcon getIconBack() {
        return this.iconBack;
    }
    
    public void setIconBack(ImageIcon iconBack) {
        this.iconBack = iconBack;
    }
}
