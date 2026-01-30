package com.tool.btn;

import org.come.Jpanel.*;
import org.come.socket.SendMessageUntil;
import org.come.socket.Agreement;
import javax.swing.JLabel;
import org.come.until.FormsManagement;
import org.come.Frame.TrueFeedbackMainJframe;
import org.come.Frame.ZhuFrame;
import org.come.until.CutButtonImage;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

public class TrueFeedbackBtn extends MoBanBtn
{
    public int getCaozuo() {
        return caozuo;
    }

    public void setCaozuo(int caozuo) {
        this.caozuo = caozuo;
    }

    private int caozuo;
    private TrueFeedbackMainJpanel trueFeedbackMainJpanel;
    private TrueFeedbackScPanel trueFeedbackScPanel;
    private TrueFeedbackLotteyJPanel trueFeedbackLotteyJPanel;
    private AthChartJPanel athChartJPanel;
    private LotteryJpanel lotteryJpanel;
    private Lottery1Jpanel lottery1Jpanel;

    
    public TrueFeedbackBtn(String iconpath, int type, int caozuo, TrueFeedbackMainJpanel trueFeedbackMainJpanel) {
        super(iconpath, type);
        this.trueFeedbackMainJpanel = trueFeedbackMainJpanel;
        this.caozuo = caozuo;
    }
    
    public TrueFeedbackBtn(String iconpath, int type, int caozuo, TrueFeedbackScPanel trueFeedbackScPanel) {
        super(iconpath, type);
        this.trueFeedbackScPanel = trueFeedbackScPanel;
        this.caozuo = caozuo;
    }
    
    public TrueFeedbackBtn(String iconpath, int type, int caozuo, String text, Color[] colors, Font font, TrueFeedbackScPanel trueFeedbackScPanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.trueFeedbackScPanel = trueFeedbackScPanel;
        this.caozuo = caozuo;
    }
    
    public TrueFeedbackBtn(String iconpath, int type, int caozuo, String text, Color[] colors, Font font, TrueFeedbackLotteyJPanel trueFeedbackLotteyJPanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.trueFeedbackLotteyJPanel = trueFeedbackLotteyJPanel;
        this.caozuo = caozuo;
    }
    
    public TrueFeedbackBtn(String iconpath, int type, int caozuo, String text, Color[] colors, Font font, AthChartJPanel athChartJPanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.athChartJPanel = athChartJPanel;
        this.caozuo = caozuo;
    }
    public TrueFeedbackBtn(String iconpath, int type, int caozuo, String text, Color[] colors, Font font, LotteryJpanel lotteryJpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.lotteryJpanel = lotteryJpanel;
        this.caozuo = caozuo;
    }
    public TrueFeedbackBtn(String iconpath, int type, int caozuo, String text, Color[] colors, Font font, Lottery1Jpanel lottery1Jpanel) {
        super(iconpath, type, colors);
        this.setText(text);
        this.setFont(font);
        this.setHorizontalTextPosition(0);
        this.setVerticalTextPosition(0);
        this.lottery1Jpanel = lottery1Jpanel;
        this.caozuo = caozuo;
    }
    @Override
    public void chooseyes() {
    }
    
    @Override
    public void chooseno() {
    }
    
    @Override
    public void nochoose(MouseEvent e) {
        try {
            if (this.caozuo == 0) {
                this.trueFeedbackMainJpanel.getBtnAddRecharge().setIcons(CutButtonImage.cuts("inkImg/button/B318.png"));
                this.trueFeedbackMainJpanel.getBtntimeSummon().setIcons(CutButtonImage.cuts("inkImg/button/B321.png"));
                this.trueFeedbackMainJpanel.getBtnLottey().setIcons(CutButtonImage.cuts("inkImg/button/B319.png"));
                this.trueFeedbackMainJpanel.getTrueFeedbackCardJPanel().changeShowView(this.caozuo);
                this.sendMes("0");
            }
            else if (this.caozuo == 1) {
                this.trueFeedbackMainJpanel.getBtnAddRecharge().setIcons(CutButtonImage.cuts("inkImg/button/B317.png"));
                this.trueFeedbackMainJpanel.getBtntimeSummon().setIcons(CutButtonImage.cuts("inkImg/button/B322.png"));
                this.trueFeedbackMainJpanel.getBtnLottey().setIcons(CutButtonImage.cuts("inkImg/button/B319.png"));
                this.trueFeedbackMainJpanel.getTrueFeedbackCardJPanel().changeShowView(this.caozuo);
                this.sendMes("1");
            }
            else if (this.caozuo == 2) {
                this.trueFeedbackMainJpanel.getBtnAddRecharge().setIcons(CutButtonImage.cuts("inkImg/button/B317.png"));
                this.trueFeedbackMainJpanel.getBtntimeSummon().setIcons(CutButtonImage.cuts("inkImg/button/B321.png"));
                this.trueFeedbackMainJpanel.getBtnLottey().setIcons(CutButtonImage.cuts("inkImg/button/B320.png"));
                this.trueFeedbackMainJpanel.getTrueFeedbackCardJPanel().changeShowView(this.caozuo);
                this.sendMes("2");
            }
            else if (this.caozuo == 3) {
                if (this.trueFeedbackScPanel.getPage() <= 1) {
                    ZhuFrame.getZhuJpanel().addPrompt2("已经是首页了");
                    return;
                }
                this.trueFeedbackScPanel.setPage(1);
                int num = TrueFeedbackMainJframe.getTrueFeedbackMainJframe().getTrueFeedbackMainJpanel().getTrueFeedbackCardJPanel().getTypeMenu();
                this.trueFeedbackScPanel.addData(num);
            }
            else if (this.caozuo == 4) {
                if (this.trueFeedbackScPanel.getPage() <= 1) {
                    ZhuFrame.getZhuJpanel().addPrompt2("已经是首页了");
                    return;
                }
                this.trueFeedbackScPanel.setPage(this.trueFeedbackScPanel.getPage() - 1);
                int num = TrueFeedbackMainJframe.getTrueFeedbackMainJframe().getTrueFeedbackMainJpanel().getTrueFeedbackCardJPanel().getTypeMenu();
                this.trueFeedbackScPanel.addData(num);
            }
            else if (this.caozuo == 5) {
                if (this.trueFeedbackScPanel.getPage() >= this.trueFeedbackScPanel.getMaxPage()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("已经是末页了");
                    return;
                }
                this.trueFeedbackScPanel.setPage(this.trueFeedbackScPanel.getPage() + 1);
                int num = TrueFeedbackMainJframe.getTrueFeedbackMainJframe().getTrueFeedbackMainJpanel().getTrueFeedbackCardJPanel().getTypeMenu();
                this.trueFeedbackScPanel.addData(num);
            }
            else if (this.caozuo == 6) {
                if (this.trueFeedbackScPanel.getPage() >= this.trueFeedbackScPanel.getMaxPage()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("已经是末页了");
                    return;
                }
                this.trueFeedbackScPanel.setPage(this.trueFeedbackScPanel.getMaxPage());
                int num = TrueFeedbackMainJframe.getTrueFeedbackMainJframe().getTrueFeedbackMainJpanel().getTrueFeedbackCardJPanel().getTypeMenu();
                this.trueFeedbackScPanel.addData(num);
            }
            else if (this.caozuo == 7) {
                FormsManagement.HiddenDisplay(110);
            }
            else if (this.caozuo == 8) {
                this.sendMes(String.valueOf(10 + TrueFeedbackMainJframe.getTrueFeedbackMainJframe().getTrueFeedbackMainJpanel().getTrueFeedbackCardJPanel().getTypeMenu()));
            }
            else if (this.caozuo == 9) {
                int num = this.trueFeedbackLotteyJPanel.getNum();
                if (num != 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("抽奖还没有结束");
                    return;
                }
                int rank = this.trueFeedbackLotteyJPanel.getRank();
                JLabel labRank = this.trueFeedbackLotteyJPanel.getLabRank();
                if (rank <= 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("抽奖次数不足");
                    return;
                }
                --rank;
                this.trueFeedbackLotteyJPanel.setRank(rank);
                labRank.setText(String.valueOf(rank));
                this.sendMes("12");
            }
            else if (this.caozuo == 100) {
                int num = this.athChartJPanel.getNum();
                if (num != 0) {
                    ZhuFrame.getZhuJpanel().addPrompt2("抽奖还没有结束");
                    return;
                }
                this.sendMes("13");
            }else if (this.caozuo == 101) {
                if (lotteryJpanel.isLotteyIn()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("抽奖还没有结束");
                    return;
                }
                if (!FormsManagement.getframe(3005).isVisible()) {
                    this.sendMes("13");
                }else{
                    ZhuFrame.getZhuJpanel().addPrompt2("请先完成季卡抽奖后再点击");
                    return;
                }

            }else if (this.caozuo == 102) {
                if (lottery1Jpanel.isLotteyIn()) {
                    ZhuFrame.getZhuJpanel().addPrompt2("抽奖还没有结束");
                    return;
                }
                if (!FormsManagement.getframe(3004).isVisible()) {
                    this.sendMes("113");
                }else{
                    ZhuFrame.getZhuJpanel().addPrompt2("请先完成月卡抽奖后再点击");
                    return;
                }
            }
        }
        catch (Exception var5) {
            var5.printStackTrace();
        }
    }
    
    public void goCJ() {
        this.num = this.athChartJPanel.getNum();
        if (this.num != 0) {
            ZhuFrame.getZhuJpanel().addPrompt2("抽奖还没有结束");
            return;
        }
        this.sendMes("13");
    }
    
    public void sendMes(String type) {
        String sendmes = Agreement.getAgreement().laborAgreement(type);
        SendMessageUntil.toServer(sendmes);
    }
}
